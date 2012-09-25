/**
 * 
 */
package com.ft.ahp.model;

import java.io.Serializable;


/**
 * @author anurag.kapur
 *
 */
public class ProjectStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private String projectName;
	private long mostRecentBuildLife;
	private String mostRecentReleaseNumber;
	private String mostRecentBuildChanges;
	private long inIntBuildLife;
	private String inIntReleaseNumber;
	private String inIntBuildChanges;
	private long readyForTestBuildLife;
	private String readyForTestReleaseNumber;
	private String readyForTestBuildChanges;
	private long inTestBuildLife;
	private String inTestReleaseNumber;
	private String inTestBuildChanges;
	private long readyForProdBuildLife;
	private String readyForProdReleaseNumber;
	private String readyForProdBuildChanges;
	private long inProdBuildLife;
	private String inProdReleaseNumber;
	private String inProdBuildChanges;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public long getMostRecentBuildLife() {
		return mostRecentBuildLife;
	}
	public void setMostRecentBuildLife(long mostRecentBuildLife) {
		this.mostRecentBuildLife = mostRecentBuildLife;
	}
	public long getInIntBuildLife() {
		return inIntBuildLife;
	}
	public void setInIntBuildLife(long inIntBuildLife) {
		this.inIntBuildLife = inIntBuildLife;
	}
	public long getReadyForTestBuildLife() {
		return readyForTestBuildLife;
	}
	public void setReadyForTestBuildLife(long readyForTestBuildLife) {
		this.readyForTestBuildLife = readyForTestBuildLife;
	}
	public long getInTestBuildLife() {
		return inTestBuildLife;
	}
	public void setInTestBuildLife(long inTestBuildLife) {
		this.inTestBuildLife = inTestBuildLife;
	}
	public long getReadyForProdBuildLife() {
		return readyForProdBuildLife;
	}
	public void setReadyForProdBuildLife(long readyForProdBuildLife) {
		this.readyForProdBuildLife = readyForProdBuildLife;
	}
	public long getInProdBuildLife() {
		return inProdBuildLife;
	}
	public void setInProdBuildLife(long inProdBuildLife) {
		this.inProdBuildLife = inProdBuildLife;
	}
	public String getMostRecentReleaseNumber() {
		return mostRecentReleaseNumber;
	}
	public void setMostRecentReleaseNumber(String mostRecentReleaseNumber) {
		this.mostRecentReleaseNumber = mostRecentReleaseNumber;
	}
	public String getInIntReleaseNumber() {
		return inIntReleaseNumber;
	}
	public void setInIntReleaseNumber(String inIntReleaseNumber) {
		this.inIntReleaseNumber = inIntReleaseNumber;
	}
	public String getReadyForTestReleaseNumber() {
		return readyForTestReleaseNumber;
	}
	public void setReadyForTestReleaseNumber(String readyForTestReleaseNumber) {
		this.readyForTestReleaseNumber = readyForTestReleaseNumber;
	}
	public String getInTestReleaseNumber() {
		return inTestReleaseNumber;
	}
	public void setInTestReleaseNumber(String inTestReleaseNumber) {
		this.inTestReleaseNumber = inTestReleaseNumber;
	}
	public String getReadyForProdReleaseNumber() {
		return readyForProdReleaseNumber;
	}
	public void setReadyForProdReleaseNumber(String readyForProdReleaseNumber) {
		this.readyForProdReleaseNumber = readyForProdReleaseNumber;
	}
	public String getInProdReleaseNumber() {
		return inProdReleaseNumber;
	}
	public void setInProdReleaseNumber(String inProdReleaseNumber) {
		this.inProdReleaseNumber = inProdReleaseNumber;
	}
	public String getMostRecentBuildChanges() {
		return mostRecentBuildChanges;
	}
	public void setMostRecentBuildChanges(String mostRecentBuildChanges) {
		this.mostRecentBuildChanges = mostRecentBuildChanges;
	}
	public String getInIntBuildChanges() {
		return inIntBuildChanges;
	}
	public void setInIntBuildChanges(String inIntBuildChanges) {
		this.inIntBuildChanges = inIntBuildChanges;
	}
	public String getReadyForTestBuildChanges() {
		return readyForTestBuildChanges;
	}
	public void setReadyForTestBuildChanges(String readyForTestBuildChanges) {
		this.readyForTestBuildChanges = readyForTestBuildChanges;
	}
	public String getInTestBuildChanges() {
		return inTestBuildChanges;
	}
	public void setInTestBuildChanges(String inTestBuildChanges) {
		this.inTestBuildChanges = inTestBuildChanges;
	}
	public String getReadyForProdBuildChanges() {
		return readyForProdBuildChanges;
	}
	public void setReadyForProdBuildChanges(String readyForProdBuildChanges) {
		this.readyForProdBuildChanges = readyForProdBuildChanges;
	}
	public String getInProdBuildChanges() {
		return inProdBuildChanges;
	}
	public void setInProdBuildChanges(String inProdBuildChanges) {
		this.inProdBuildChanges = inProdBuildChanges;
	}
}
