package com.lhh.modules.stutopiccluster.domain;


import java.util.List;

import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;


/**
 * 学生作业表
 */
public class StuTopicClusterVo extends StuTopicClusterEntity {

    /**
     * id
     */
    private Integer topicClusterId;
    private Integer stuTopicClusterId;

    private Float actualScore;
    //题目内容
    private String content;

    //作业类型
    private String homeworkType;

    private List<StuTopicVo> topicList;
    //大题下答对题目数量
    private Integer rightCount;


    public String getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(String homeworkType) {
        this.homeworkType = homeworkType;
    }

    public Integer getTopicClusterId() {
        return topicClusterId;
    }

    public void setTopicClusterId(Integer topicClusterId) {
        this.topicClusterId = topicClusterId;
    }

    public Integer getStuTopicClusterId() {
        return stuTopicClusterId;
    }

    public void setStuTopicClusterId(Integer stuTopicClusterId) {
        this.stuTopicClusterId = stuTopicClusterId;
    }

    public Float getActualScore() {
        return actualScore;
    }

    public void setActualScore(Float actualScore) {
        this.actualScore = actualScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<StuTopicVo> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<StuTopicVo> topicList) {
        this.topicList = topicList;
    }

    public Integer getRightCount() {
        return rightCount;
    }

    public void setRightCount(Integer rightCount) {
        this.rightCount = rightCount;
    }
}
