package com.lhh.common.state;

/**
 * 学生状态
 * @author wangcheng
 * @date 2018年5月15日 下午4:08:32
 */
public enum StuStatus {
	/**空,无状态*/
	ZERO("11",11),
	/**被打回*/
	BACK("被打回", 1),
	/**未批阅*/
	NOCORRECT("未批阅", 2),
	/**待订正*/
	WAITREVISING("待订正", 3),
	/**已订正*/
	REVISINGED("已订正", 4),
	/**已打分*/
	GRADE("已打分", 5),
	/**未公布*/
	UNANNOUNCED("未公布", 6),
	/**已归档*/
	FILE("已归档", 7);
	
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
	private StuStatus(String name, int state) {
		this.name = name;
		this.state = state;
	}
	
	

}
