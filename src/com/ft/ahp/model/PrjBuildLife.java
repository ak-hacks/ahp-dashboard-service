package com.ft.ahp.model;

import java.util.Date;

/**
 * @author anuragkapur
 *
 */
public class PrjBuildLife {

	private int id;
	private String lastStampValue;
	private String projectName;
	private Date actualWorkspaceDate;
	private Date deployedToProdOn;
	private Date deployedToTestOn;
	private Date deployedToIntOn;
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
		return actualWorkspaceDate.toString();
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
		return (deployedToProdOn != null)?deployedToProdOn.toString():"-";
	}
	public void setDeployedToProdOn(Date deployedToProdOn) {
		this.deployedToProdOn = deployedToProdOn;
	}
	public String getDeployedToTestOn() {
		return (deployedToTestOn != null)?deployedToTestOn.toString():"-";
	}
	public void setDeployedToTestOn(Date deployedToTestOn) {
		this.deployedToTestOn = deployedToTestOn;
	}
	public String getDeployedToIntOn() {
		return (deployedToIntOn != null)?deployedToIntOn.toString():"-";
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
}
