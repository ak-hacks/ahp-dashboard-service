package com.ft.ahp.model;

import java.util.Date;

/**
 * @author anuragkapur
 *
 */
public class PrjBuildLife {

	private int id;
	private String lastStampValue;
	private Date actualWorkspaceDate;
	
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
	public Date getActualWorkspaceDate() {
		return actualWorkspaceDate;
	}
	public void setActualWorkspaceDate(Date actualWorkspaceDate) {
		this.actualWorkspaceDate = actualWorkspaceDate;
	}
}
