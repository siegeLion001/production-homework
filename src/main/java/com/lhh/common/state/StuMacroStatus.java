package com.lhh.common.state;

/**
 * 学生宏观状态
 * @author wangcheng
 * @date 2018年5月15日 下午3:36:36
 */
public enum StuMacroStatus {
	/**待做*/
	WAITDO("待做", 1), 
	/**重做*/
	AGAINDO("重做", 2), 
	/**已做*/
	DONE("已做", 3);
	
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
	private StuMacroStatus(String name, int state) {
		this.name = name;
		this.state = state;
	}
	
	

}
