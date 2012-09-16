/**
 * 
 */
package com.ft.ahp.remote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.ft.ahp.model.PrjBuildLife;
import com.ft.ahp.model.ProjectStatus;
import com.urbancode.anthill3.dashboard.DashboardFactory;
import com.urbancode.anthill3.dashboard.StatusSummary;
import com.urbancode.anthill3.domain.buildlife.BuildLife;
import com.urbancode.anthill3.domain.buildlife.BuildLifeChangeSet;
import com.urbancode.anthill3.domain.buildlife.BuildLifeFactory;
import com.urbancode.anthill3.domain.buildlife.BuildLifeStatus;
import com.urbancode.anthill3.domain.persistent.PersistenceException;
import com.urbancode.anthill3.domain.profile.BuildProfile;
import com.urbancode.anthill3.domain.profile.BuildProfileFactory;
import com.urbancode.anthill3.domain.project.Project;
import com.urbancode.anthill3.domain.project.ProjectFactory;
import com.urbancode.anthill3.domain.security.AuthorizationException;
import com.urbancode.anthill3.domain.workflow.WorkflowFactory;
import com.urbancode.anthill3.main.client.AnthillClient;
import com.urbancode.anthill3.persistence.UnitOfWork;

/**
 * @author anurag.kapur
 *
 */
public class RemoteClient {

	private static ProjectFactory projectFactory = ProjectFactory.getInstance();
	private static BuildLifeFactory buildlifeFactory = BuildLifeFactory.getInstance();
	private static BuildProfileFactory buildProfileFactory = BuildProfileFactory.getInstance();
	private static WorkflowFactory workflowFactory = WorkflowFactory.getInstance();
	private static DashboardFactory dashboardFactory = DashboardFactory.getInstance();
	ResourceBundle bundle = ResourceBundle.getBundle("configs");
	
	private static final Logger logger = Logger.getLogger(RemoteClient.class);
	
	/**
	 * 
	 * @param projectName
	 * @return
	 */
	public List<PrjBuildLife> getBuildLives(String projectName) {
		
		logger.debug("Will lookup project :: " + projectName);
		
		UnitOfWork uw = null;
		com.urbancode.anthill3.domain.buildlife.BuildLife ahpBuildlives[] = null;
		List<PrjBuildLife> buildLives = null;
		
		try {
			uw = anthillClient().createUnitOfWork();	
			Project project = projectFactory.restoreForName(projectName);
			logger.debug(project.getDescription());
			ahpBuildlives = buildlifeFactory.restoreAllForProject(project);
			if (ahpBuildlives.length <= 0) {
				logger.info("No buildlives for project :: " + projectName + " found");
			}else {
				buildLives = processAhpBuildLives(ahpBuildlives, projectName);
			}
			
		} catch (AuthorizationException e) {
			logger.error(e);
		} catch (PersistenceException e) {
			logger.error(e);
		}finally {
			if (uw != null) {
				uw.close();
			}
		}
		
		return buildLives;
	}
	
	/**
	 * 
	 * @param programmeName
	 * @return
	 */
	public List<ProjectStatus> getProgrammeStatus(String programmeName) {
		
		UnitOfWork uw = null;
		List<ProjectStatus> programmeStatus = new ArrayList<ProjectStatus>();
		
		String projectNames = bundle.getString(programmeName);
		StringTokenizer tokenizer = new StringTokenizer(projectNames, ",");
		
		while (tokenizer.hasMoreElements()) {
			String projectName = (String) tokenizer.nextElement();
			try {
				uw = anthillClient().createUnitOfWork();
				Project project = projectFactory.restoreForName(projectName);
				
				StatusSummary[] summaries = dashboardFactory.getMostRecentSummaryForEachStatus(project);
				ProjectStatus projectStatus = processStatusSummaries(summaries, projectName);
				programmeStatus.add(projectStatus);
			} catch (PersistenceException e) {
				logger.error(e);
			} catch (AuthorizationException e) {
				logger.error(e);
			}finally {
				if (uw != null) {
					uw.close();
				}
			}
		}
		
		return programmeStatus;
	}
	
	/**
	 * 
	 * @param ahpBuildlives
	 * @param projectName
	 * @return
	 */
	private List<PrjBuildLife> processAhpBuildLives(com.urbancode.anthill3.domain.buildlife.BuildLife ahpBuildlives[], String projectName) {
		
		List<PrjBuildLife> buildLives = new ArrayList<PrjBuildLife>();
		int count = 0;
		
		for (BuildLife ahpBuildLife : ahpBuildlives) {
			count ++;
			if(count > 30) {
				break;
			}
			PrjBuildLife buildLife = new PrjBuildLife();
			buildLife.setId(ahpBuildLife.getId().intValue());
			buildLife.setLastStampValue(ahpBuildLife.getLatestStampValue());
			buildLife.setActualWorkspaceDate(ahpBuildLife.getActualWorkspaceDate());
			buildLife.setProjectName(projectName);
			
			logger.debug("========================");
			logger.debug(ahpBuildLife.getId());
			
			BuildLifeStatus[] statuses = ahpBuildLife.getStatusArray();
			for (BuildLifeStatus buildLifeStatus : statuses) {
				if (buildLifeStatus.getStatus().getName().equalsIgnoreCase("Promote to Live Success")) {
					buildLife.setDeployedToProdOn(buildLifeStatus.getDateAssigned());
				}else if(buildLifeStatus.getStatus().getName().equalsIgnoreCase("Promote to Test Success")) {
					buildLife.setDeployedToTestOn(buildLifeStatus.getDateAssigned());
				}else if(buildLifeStatus.getStatus().getName().equalsIgnoreCase("In Int")) {
					buildLife.setDeployedToIntOn(buildLifeStatus.getDateAssigned());
				}
				logger.debug(buildLifeStatus.getDateAssigned().toString());
				logger.debug(buildLifeStatus.getStatus().getName());
			}
			
			Iterator<BuildLifeChangeSet> changes= ahpBuildLife.getChangeSets().iterator();
			StringBuffer changeLog = new StringBuffer();
			while(changes.hasNext()) {
				changeLog.append(changes.next().getChangeSet().getComment());
				changeLog.append("<br/>");
			}
			buildLife.setChanges(changeLog.toString());
			buildLives.add(buildLife);
		}
		
		return buildLives;
	}
	
	/**
	 * 
	 * @param summaries
	 * @param projectName
	 * @return
	 * @throws PersistenceException 
	 */
	private ProjectStatus processStatusSummaries(StatusSummary[] summaries, String projectName) throws PersistenceException {
		
		ProjectStatus projectStatus = new ProjectStatus();
		projectStatus.setProjectName(projectName);
		
		for (StatusSummary statusSummary : summaries) {
			logger.debug(statusSummary.getBuildLifeId());
			logger.debug(statusSummary.getStatusName());
			
			long buildLifeId = (statusSummary.getBuildLifeId() != null) ? statusSummary.getBuildLifeId() : -1;
			String releaseStamp = (statusSummary.getLatestStamp() != null) ? statusSummary.getLatestStamp() : "NA";
			
			if (statusSummary.getStatusName().equalsIgnoreCase("success")) {	
				projectStatus.setMostRecentBuildLife(buildLifeId);
				projectStatus.setMostRecentReleaseNumber(releaseStamp);
				projectStatus.setMostRecentBuildChanges(getChangesforBuildLife(buildLifeId));
				
			}else if (statusSummary.getStatusName().equalsIgnoreCase("In Int")) {
				projectStatus.setInIntBuildLife(buildLifeId);
				projectStatus.setInIntReleaseNumber(releaseStamp);
				projectStatus.setInIntBuildChanges(getChangesforBuildLife(buildLifeId));
			}else if (statusSummary.getStatusName().equalsIgnoreCase("Peer Reviewed")) {
				projectStatus.setReadyForTestBuildLife(buildLifeId);
				projectStatus.setReadyForTestReleaseNumber(releaseStamp);
				projectStatus.setReadyForTestBuildChanges(getChangesforBuildLife(buildLifeId));
			}else if (statusSummary.getStatusName().equalsIgnoreCase("In Test")) {
				projectStatus.setInTestBuildLife(buildLifeId);
				projectStatus.setInTestReleaseNumber(releaseStamp);
				projectStatus.setInTestBuildChanges(getChangesforBuildLife(buildLifeId));
			}else if (statusSummary.getStatusName().equalsIgnoreCase("Testing PASSED")) {
				projectStatus.setReadyForProdBuildLife(buildLifeId);
				projectStatus.setReadyForProdReleaseNumber(releaseStamp);
				projectStatus.setReadyForProdBuildChanges(getChangesforBuildLife(buildLifeId));
			}else if (statusSummary.getStatusName().equalsIgnoreCase("In Live")) {
				projectStatus.setInProdBuildLife(buildLifeId);
				projectStatus.setInProdReleaseNumber(releaseStamp);
				projectStatus.setInProdBuildChanges(getChangesforBuildLife(buildLifeId));
			}
		}
		
		return projectStatus;
	}
	
	private String getChangesforBuildLife(long id) throws PersistenceException {
		BuildLife buildLife = buildlifeFactory.restore(id);
		StringBuffer changeLog = new StringBuffer();
		if(buildLife != null) {
			Iterator<BuildLifeChangeSet> changes= buildLife.getChangeSets().iterator();
			while(changes.hasNext()) {
				changeLog.append(changes.next().getChangeSet().getComment());
				changeLog.append("<br/>");
			}
		}
		
		return changeLog.toString();
	}
	
	/**
	 * 
	 * @return
	 * @throws AuthorizationException
	 */
	private AnthillClient anthillClient() throws AuthorizationException {
		//TODO: Read from config file
		return AnthillClient.connect("ahp.svc.ft.com", 7916, "anurag.kapur", "3ftpyrAmId2012");
	}
}