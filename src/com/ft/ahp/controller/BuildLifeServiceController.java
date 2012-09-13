/**
 * 
 */
package com.ft.ahp.controller;

import java.util.List;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ft.ahp.model.PrjBuildLife;
import com.ft.ahp.remote.RemoteClient;

/**
 * 
 * @author anurag.kapur
 *
 */

@Controller
@RequestMapping("/buildlives")
public class BuildLifeServiceController {

	private RemoteClient ahpClient;
	
	@RequestMapping(method=RequestMethod.GET, value="/project/{name}")
	public ModelAndView getDestination(@PathVariable String name) {
		
		List<PrjBuildLife> buildLives = ahpClient.getBuildLives(name);
		return new ModelAndView("projectBuildLives", "buildLives", buildLives);
	}

	public RemoteClient getAhpClient() {
		return ahpClient;
	}

	public void setAhpClient(RemoteClient ahpClient) {
		this.ahpClient = ahpClient;
	}
}
