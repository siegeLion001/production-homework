package com.lhh.common.type;

public enum HomeworkType {
	/*日常作业*/
    DailyWork("日常作业", "1"),
    OralReviews("口语评测", "2"),
    SynchronizedExercises("同步练习", "3"),
    PhaseDetection("阶段检测", "4"),
    CHINESE("中文评测","1"),
	FREEDOM("自由评测","2"),
	ENGLISH("英文评测","3");

    private String name;
    private String code;
    
    
	private HomeworkType(String name, String code) {
		this.name = name;
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
