package com.lhh.common.state;

/**
 * 作业归档状态
 * @author cuihp
 */
public enum HomeworkStatus {
	/**未归档*/
	UNARCHIVE("未归档", 1), 
	/**已归档*/
	ARCHIVE("已归档", 2),
	/**删除标识  未删除*/
	UNDELETED("未删除", 1),
    /**删除标识  未删除*/
	DELETED("已删除",2);
	private String name;
	
	private int state;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	private HomeworkStatus(String name, int state) {
		this.name = name;
		this.state = state;
	}
	
	

}
