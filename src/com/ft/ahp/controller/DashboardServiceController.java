package com.ft.ahp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ft.ahp.model.PrjBuildLife;
import com.ft.ahp.model.ProjectStatus;
import com.ft.ahp.remote.RemoteClient;

/**
 * The controller for the MVC based RESTful service
 * 
 * @author anurag.kapur
 */

@Controller
@RequestMapping("/")
public class DashboardServiceController {

	private static final Logger logger = Logger
			.getLogger(DashboardServiceController.class);
	private RemoteClient ahpClient;

	/**
	 * Returns the list and metadata, as defined by the {@link PrjBuildLife}
	 * model object, of all build lives of a project
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/project/{name}")
	public ModelAndView getBuildLives(@PathVariable String name) {
		logger.debug("Processing project buildlives view for :: " + name);
		List<PrjBuildLife> buildLives = ahpClient.getBuildLives(name);
		return new ModelAndView("projectBuildLives", "buildLives", buildLives);
	}

	/**
	 * Returns the list of projects and metadata, as defined by the
	 * {@link ProjectStatus} model object given a programme name
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/programme/{name}")
	public ModelAndView getProgrammeStatus(@PathVariable String name) {
		logger.debug("Processing programme status for :: " + name);
		List<ProjectStatus> programmeStatus = ahpClient
				.getProgrammeStatus(name);
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("projects", programmeStatus);
		modelMap.addAttribute("name", name);
		
		//return modelMap;
		return new ModelAndView("programmeStatus", "programme", modelMap);
	}

	public RemoteClient getAhpClient() {
		return ahpClient;
	}

	public void setAhpClient(RemoteClient ahpClient) {
		this.ahpClient = ahpClient;
	}
}
