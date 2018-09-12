package com.lhh.modules.homework.domain;

import com.lhh.modules.homework.entity.HomeworkEntity;


public class LanguageEvaluation extends HomeworkEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClassInfo[] classInfos;
    /**
     * 作业标识
     * @return
     */
    private Integer homeworkId;
    public ClassInfo[] getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(ClassInfo[] classInfos) {
        this.classInfos = classInfos;
    }

	public Integer getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
    
}
