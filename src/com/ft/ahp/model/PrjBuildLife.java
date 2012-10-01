package com.ft.ahp.model;

import java.io.Serializable;
import java.util.Date;

import com.ft.ahp.util.DateFormatHelper;

/**
 * Model object defining the metadata associated with a project's buildlife
 * 
 * @author anuragkapur
 */
public class PrjBuildLife implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String lastStampValue;
	private String projectName;
	private Date actualWorkspaceDate;
	private Date deployedToProdOn;
	private Date deployedToTestOn;
	private Date deployedToIntOn;
	private Date testingPassedOn;
	private Date testingFailedOn;
	private Date liveVerifPassedOn;
	private Date liveVerifFailedOn;
	private String changes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastStampValue() {
		return lastStampValue;
	}

	public void setLastStampValue(String lastStampValue) {
		this.lastStampValue = lastStampValue;
	}

	public String getActualWorkspaceDate() {
		return DateFormatHelper.getISOFormattedDate(actualWorkspaceDate);
	}

	public void setActualWorkspaceDate(Date actualWorkspaceDate) {
		this.actualWorkspaceDate = actualWorkspaceDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDeployedToProdOn() {
		
		return (deployedToProdOn != null) ? DateFormatHelper.getISOFormattedDate(deployedToProdOn) : "-";
	}

	public void setDeployedToProdOn(Date deployedToProdOn) {
		this.deployedToProdOn = deployedToProdOn;
	}

	public String getDeployedToTestOn() {
		return (deployedToTestOn != null) ? DateFormatHelper.getISOFormattedDate(deployedToTestOn) : "-";
	}

	public void setDeployedToTestOn(Date deployedToTestOn) {
		this.deployedToTestOn = deployedToTestOn;
	}

	public String getDeployedToIntOn() {
		return (deployedToIntOn != null) ? DateFormatHelper.getISOFormattedDate(deployedToIntOn) : "-";
	}

	public void setDeployedToIntOn(Date deployedToIntOn) {
		this.deployedToIntOn = deployedToIntOn;
	}

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getTestingPassedOn() {
		return (testingPassedOn != null) ? testingPassedOn.toString() : "-";
	}

	public void setTestingPassedOn(Date testingPassedOn) {
		this.testingPassedOn = testingPassedOn;
	}

	public String getTestingFailedOn() {
		return (testingFailedOn != null) ? testingFailedOn.toString() : "-";
	}

	public void setTestingFailedOn(Date testingFailedOn) {
		this.testingFailedOn = testingFailedOn;
	}

	public String getLiveVerifPassedOn() {
		return (liveVerifPassedOn != null) ? liveVerifPassedOn.toString() : "-";
	}

	public void setLiveVerifPassedOn(Date liveVerifPassedOn) {
		this.liveVerifPassedOn = liveVerifPassedOn;
	}

	public String getLiveVerifFailedOn() {
		return (liveVerifFailedOn != null) ? liveVerifFailedOn.toString() : "-";
	}

	public void setLiveVerifFailedOn(Date liveVerifFailedOn) {
		this.liveVerifFailedOn = liveVerifFailedOn;
	}
}
