package com.lhh.modules.homework.domain;

import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;

import java.util.List;

@SuppressWarnings("serial")
public class InsertHomeWorkVo extends HomeworkEntity {

    /**
     * 题目来源 1 教辅 2 同步题库 3 校本卷库 4 精品卷库
     */
    private String topicSource;

    /**
     * 作业总人数
     */
    private Integer headCount;
    /**
     * 已提交人数
     */
    private Integer submitCount;
    /**
     * 未批阅人数
     */
    private Integer notReadCount;
    /**
     * 订正人数
     */
    private Integer correctCount;
    /**
     * 作业标识
     */
    private Integer homeworkId;

    private List<TopicClusterVo> topicClusterList;

    private ClassInfo[] classInfos;


    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public Integer getNotReadCount() {
        return notReadCount;
    }

    public void setNotReadCount(Integer notReadCount) {
        this.notReadCount = notReadCount;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public List<TopicClusterVo> getTopicClusterList() {
        return topicClusterList;
    }

    public void setTopicClusterList(List<TopicClusterVo> topicClusterList) {
        this.topicClusterList = topicClusterList;
    }

    public ClassInfo[] getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(ClassInfo[] classInfos) {
        this.classInfos = classInfos;
    }

    public String getTopicSource() {
        return topicSource;
    }

    public void setTopicSource(String topicSource) {
        this.topicSource = topicSource;
    }

	public Integer getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
    
}
