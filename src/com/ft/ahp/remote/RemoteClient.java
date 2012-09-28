package com.ft.ahp.remote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

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
import com.urbancode.anthill3.domain.project.Project;
import com.urbancode.anthill3.domain.project.ProjectFactory;
import com.urbancode.anthill3.domain.security.AuthorizationException;
import com.urbancode.anthill3.main.client.AnthillClient;
import com.urbancode.anthill3.persistence.UnitOfWork;

/**
 * AHP Remote Client. Handles all interaction with AHP
 * 
 * @author anurag.kapur
 */
public class RemoteClient {

	private static ProjectFactory projectFactory = ProjectFactory.getInstance();
	private static BuildLifeFactory buildlifeFactory = BuildLifeFactory
			.getInstance();
	private static DashboardFactory dashboardFactory = DashboardFactory
			.getInstance();

	private ResourceBundle bundle = ResourceBundle.getBundle("configs");
	private CacheManager cacheManager = new CacheManager();
	private Ehcache buildLifeChangesCache;
	private Ehcache buildLifeCache;
	private Ehcache projectStatusCache;
	private Ehcache projectBuildlivesCache;

	private static final Logger logger = Logger.getLogger(RemoteClient.class);

	/**
	 * Constructor for the RemoteClient, initializes caches
	 */
	public RemoteClient() {
		buildLifeChangesCache = cacheManager.getCache("ahpBuildLifeChanges");
		projectStatusCache = cacheManager.getCache("projectStatus");
		projectBuildlivesCache = cacheManager.getCache("projectBuildlives");
		buildLifeCache = cacheManager.getCache("ahpBuildLife");
	}

	/**
	 * Return a List of all buildLives along with metadata, as defined by the
	 * {@link PrjBuildLife} model object, for the given project name
	 * 
	 * @param projectName
	 * @return
	 */
	public List<PrjBuildLife> getBuildLives(String projectName) {

		logger.debug("Will lookup project :: " + projectName);

		UnitOfWork uw = null;
		com.urbancode.anthill3.domain.buildlife.BuildLife ahpBuildlives[] = null;
		List<PrjBuildLife> buildLives = null;

		Element buildLivesFromCache = projectBuildlivesCache.get(projectName);

		if (buildLivesFromCache != null) {
			// Buildlives for project found in cache
			logger.info("CACHE HIT >> Project " + projectName
					+ "buildlives FOUND in cache");
			buildLives = (List<PrjBuildLife>) buildLivesFromCache
					.getObjectValue();
		} else {
			// If not found in cache
			logger.info("CACHE MISS >> Project " + projectName
					+ "buildlives NOT found in cache");
			try {
				uw = anthillClient().createUnitOfWork();
				Project project = projectFactory.restoreForName(projectName);
				logger.debug(project.getDescription());
				ahpBuildlives = buildlifeFactory.restoreAllForProject(project);
				if (ahpBuildlives.length <= 0) {
					logger.info("No buildlives for project :: " + projectName
							+ " found");
				} else {
					buildLives = processAhpBuildLives(ahpBuildlives,
							projectName);
				}

			} catch (AuthorizationException e) {
				logger.error(e);
			} catch (PersistenceException e) {
				logger.error(e);
			} finally {
				buildLivesFromCache = new Element(projectName, buildLives);
				projectBuildlivesCache.put(buildLivesFromCache);
				if (uw != null) {
					uw.close();
				}
			}
		}

		return buildLives;
	}

	/**
	 * Returns the status of all projects defined under a programme. Status of a
	 * project is defined by the {@link ProjectStatus} model object
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
				logger.info("Will restore project :: " + projectName);
				Project project = projectFactory.restoreForName(projectName);
				logger.debug("Project restored :: " + projectName);
				StatusSummary[] summaries = dashboardFactory
						.getMostRecentSummaryForEachStatus(project);
				logger.debug("Status summaries for project restored :: "
						+ projectName);

				ProjectStatus projectStatus = null;

				// First look in cache and then from source
				Element element = projectStatusCache.get(projectName);
				if (element != null) {
					logger.info("CACHE HIT >> project status FOUND in Cache");
					projectStatus = (ProjectStatus) element.getValue();
				} else {
					// Not found in cache, look in source
					logger.info("CACHE MISS >> project status NOT found in cache, will compute");
					projectStatus = processStatusSummaries(summaries,
							projectName);
					element = new Element(projectName, projectStatus);
					projectStatusCache.put(element);
				}

				logger.debug("Status summaries for project processed :: "
						+ projectName);

				programmeStatus.add(projectStatus);
			} catch (PersistenceException e) {
				logger.error(e);
			} catch (AuthorizationException e) {
				logger.error(e);
			} finally {
				if (uw != null) {
					uw.close();
				}
			}
		}

		return programmeStatus;
	}

	/**
	 * Provess buildlives in the List, i.e. determine the environment which it
	 * is deployed to, change log, date of creation etc
	 * 
	 * @param ahpBuildlives
	 * @param projectName
	 * @return
	 */
	private List<PrjBuildLife> processAhpBuildLives(
			com.urbancode.anthill3.domain.buildlife.BuildLife ahpBuildlives[],
			String projectName) {

		List<PrjBuildLife> buildLives = new ArrayList<PrjBuildLife>();
		int count = 0;

		for (BuildLife ahpBuildLife : ahpBuildlives) {
			count++;
			if (count > 30) {
				break;
			}
			PrjBuildLife buildLife = null;
			
			Element buildLifeFromCache = buildLifeCache.get(ahpBuildLife.getId());
			if (buildLifeFromCache != null) {
				// First check in cache
				logger.info("CACHE HIT >> Buildlife data FOUND in cache :: " + ahpBuildLife.getId());
				buildLife = (PrjBuildLife)buildLifeFromCache.getValue();
			}else {
				logger.info("CACHE MISS >> Buildlife data NOT found in cache :: " + ahpBuildLife.getId());
				buildLife = new PrjBuildLife();
				buildLife.setId(ahpBuildLife.getId().intValue());
				buildLife.setLastStampValue(ahpBuildLife.getLatestStampValue());
				buildLife.setActualWorkspaceDate(ahpBuildLife
						.getActualWorkspaceDate());
				buildLife.setProjectName(projectName);

				logger.debug("========================");
				logger.debug(ahpBuildLife.getId());

				BuildLifeStatus[] statuses = ahpBuildLife.getStatusArray();
				for (BuildLifeStatus buildLifeStatus : statuses) {
					if (buildLifeStatus.getStatus().getName()
							.equalsIgnoreCase("Promote to Live Success")) {
						buildLife.setDeployedToProdOn(buildLifeStatus
								.getDateAssigned());
					} else if (buildLifeStatus.getStatus().getName()
							.equalsIgnoreCase("Promote to Test Success")) {
						buildLife.setDeployedToTestOn(buildLifeStatus
								.getDateAssigned());
					} else if (buildLifeStatus.getStatus().getName()
							.equalsIgnoreCase("In Int")) {
						buildLife.setDeployedToIntOn(buildLifeStatus
								.getDateAssigned());
					}
					logger.debug(buildLifeStatus.getDateAssigned().toString());
					logger.debug(buildLifeStatus.getStatus().getName());
				}

				Iterator<BuildLifeChangeSet> changes = ahpBuildLife.getChangeSets()
						.iterator();
				StringBuffer changeLog = new StringBuffer();
				while (changes.hasNext()) {
					changeLog.append(changes.next().getChangeSet().getComment());
					changeLog.append("<br/>");
				}
				buildLife.setChanges(changeLog.toString());
				buildLifeFromCache = new Element(ahpBuildLife.getId(), buildLife);
				buildLifeCache.put(buildLifeFromCache);
			}
			
			buildLives.add(buildLife);
		}

		return buildLives;
	}

	/**
	 * Process all status summaries to determine the {@link ProjectStatus}
	 * 
	 * @param summaries
	 * @param projectName
	 * @return
	 * @throws PersistenceException
	 */
	private ProjectStatus processStatusSummaries(StatusSummary[] summaries,
			String projectName) throws PersistenceException {

		ProjectStatus projectStatus = new ProjectStatus();
		projectStatus.setProjectName(projectName);

		for (StatusSummary statusSummary : summaries) {
			logger.debug(statusSummary.getBuildLifeId());
			logger.debug(statusSummary.getStatusName());

			long buildLifeId = (statusSummary.getBuildLifeId() != null) ? statusSummary
					.getBuildLifeId() : -1;
			String releaseStamp = (statusSummary.getLatestStamp() != null) ? statusSummary
					.getLatestStamp() : "NA";

			if (statusSummary.getStatusName().equalsIgnoreCase("success")) {
				projectStatus.setMostRecentBuildLife(buildLifeId);
				projectStatus.setMostRecentReleaseNumber(releaseStamp);
				projectStatus
						.setMostRecentBuildChanges(getChangesforBuildLife(buildLifeId));

			} else if (statusSummary.getStatusName().equalsIgnoreCase("In Int")) {
				projectStatus.setInIntBuildLife(buildLifeId);
				projectStatus.setInIntReleaseNumber(releaseStamp);
				projectStatus
						.setInIntBuildChanges(getChangesforBuildLife(buildLifeId));
			} else if (statusSummary.getStatusName().equalsIgnoreCase(
					"Peer Reviewed")) {
				projectStatus.setReadyForTestBuildLife(buildLifeId);
				projectStatus.setReadyForTestReleaseNumber(releaseStamp);
				projectStatus
						.setReadyForTestBuildChanges(getChangesforBuildLife(buildLifeId));
			} else if (statusSummary.getStatusName()
					.equalsIgnoreCase("In Test")) {
				projectStatus.setInTestBuildLife(buildLifeId);
				projectStatus.setInTestReleaseNumber(releaseStamp);
				projectStatus
						.setInTestBuildChanges(getChangesforBuildLife(buildLifeId));
			} else if (statusSummary.getStatusName().equalsIgnoreCase(
					"Testing PASSED")) {
				projectStatus.setReadyForProdBuildLife(buildLifeId);
				projectStatus.setReadyForProdReleaseNumber(releaseStamp);
				projectStatus
						.setReadyForProdBuildChanges(getChangesforBuildLife(buildLifeId));
			} else if (statusSummary.getStatusName()
					.equalsIgnoreCase("In Live")) {
				projectStatus.setInProdBuildLife(buildLifeId);
				projectStatus.setInProdReleaseNumber(releaseStamp);
				projectStatus
						.setInProdBuildChanges(getChangesforBuildLife(buildLifeId));
			}
		}

		return projectStatus;
	}

	/**
	 * Get a string representation of the change log for a buildlife
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	private String getChangesforBuildLife(long id) throws PersistenceException {
		logger.debug("Will get change logs for buildlife :: " + id);

		String changeLog;

		// First look in cache and then from source
		Element element = buildLifeChangesCache.get(id);
		if (element != null) {
			// If found in cache
			logger.info("CACHE HIT >> Build life change log found in cache for BL id ::"
					+ id);

			changeLog = (String) element.getValue();
		} else {
			// If not found in cache
			logger.info("CACHE MISS >> Build life change log NOT found in cache for BL id ::"
					+ id);

			BuildLife buildLife = buildlifeFactory.restore(id);
			StringBuffer changeLogBuffer = new StringBuffer();
			if (buildLife != null) {
				Iterator<BuildLifeChangeSet> changes = buildLife
						.getChangeSets().iterator();
				while (changes.hasNext()) {
					changeLogBuffer.append(changes.next().getChangeSet()
							.getComment());
					changeLogBuffer.append("<br/>");
				}
			}
			changeLog = changeLogBuffer.toString();
			// Add to cache for next time
			element = new Element(id, changeLog);
			buildLifeChangesCache.put(element);
		}

		logger.debug("Change logs for buildlife processed :: " + id);
		return changeLog;
	}

	/**
	 * 
	 * @return
	 * @throws AuthorizationException
	 */
	private AnthillClient anthillClient() throws AuthorizationException {
		// TODO: Read from config file
		return AnthillClient.connect("ahp.svc.ft.com", 7916, "anurag.kapur",
				"3ftpyrAmId2012");
	}
}