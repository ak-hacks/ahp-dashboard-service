/**
 * 
 */
package com.ft.ahp.remote;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ft.ahp.model.PrjBuildLife;
import com.urbancode.anthill3.domain.buildlife.BuildLife;
import com.urbancode.anthill3.domain.buildlife.BuildLifeFactory;
import com.urbancode.anthill3.domain.persistent.PersistenceException;
import com.urbancode.anthill3.domain.project.Project;
import com.urbancode.anthill3.domain.project.ProjectFactory;
import com.urbancode.anthill3.domain.security.AuthorizationException;
import com.urbancode.anthill3.main.client.AnthillClient;
import com.urbancode.anthill3.persistence.UnitOfWork;

/**
 * @author anurag.kapur
 *
 */
public class RemoteClient {

	private static ProjectFactory projectFactory = ProjectFactory.getInstance();
	private static BuildLifeFactory buildlifeFactory = BuildLifeFactory.getInstance();
	
	private static final Logger logger = Logger.getLogger(RemoteClient.class);
	
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
			
			buildLives = new ArrayList<PrjBuildLife>(ahpBuildlives.length);
			if (ahpBuildlives.length <= 0) {
				logger.info("No buildlives for project :: " + projectName + " found");
			}
			for (BuildLife ahpBuildLife : ahpBuildlives) {
				PrjBuildLife buildLife = new PrjBuildLife();
				buildLife.setId(ahpBuildLife.getId().intValue());
				buildLife.setLastStampValue(ahpBuildLife.getLatestStampValue());
				buildLife.setActualWorkspaceDate(ahpBuildLife.getActualWorkspaceDate());
				buildLives.add(buildLife);
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
	
	private AnthillClient anthillClient() throws AuthorizationException {
		//TODO: Read from config file
		return AnthillClient.connect("ahp.svc.ft.com", 7916, "anurag.kapur", "3ftpyrAmId2012");
	}
}