package model;

import java.util.Date;

public class PerformanceEvent {
	
	private String fileName;
	private String account;
	private String description;
	private Date startTime;
	private String endTime;
	private double duration;
	private boolean isCopied;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public boolean isCopied() {
		return isCopied;
	}
	public void setCopied(boolean isCopied) {
		this.isCopied = isCopied;
	}
	
	
}
