package com.lhh.common.state;

/**
 * 老师状态
 * @author wangcheng
 * @date 2018年5月15日 下午4:08:32
 */
public enum TchStatus {
	/**空,无状态*/
	ZERO("11",11),
	/**未批阅*/
	NOCORRECT("未批阅", 1),
    /**批阅中 只用于展示 不参与流转**/
	CORRECTING("批阅中", 12),
	/**重新批阅*/
	AGAINDOCORRECT("重新批阅", 2),
	/**已批改*/
	YESCORRECT("已批改", 3),
	/**待订正*/
	WAITREVISING("待订正", 4),
	/**已订正*/
	REVISINGED("已订正", 5),
	/**待归档*/
	WAITFILE("待归档", 6);
	
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
	private TchStatus(String name, int state) {
		this.name = name;
		this.state = state;
	}
	
	

}
