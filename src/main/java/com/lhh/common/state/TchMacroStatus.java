package com.lhh.common.state;

/**
 * 老师宏观状态
 * @author wangcheng
 * @date 2018年5月15日 下午4:07:05
 */
public enum TchMacroStatus {
	/**未批*/
	NOCORRECT("未批", 1), 
	/**已批*/
	YESCORRECT("已批", 2), 
	/**订正*/
	REVISING("订正", 3);
	
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
	private TchMacroStatus(String name, int state) {
		this.name = name;
		this.state = state;
	}
	
	

}
