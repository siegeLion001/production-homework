package com.lhh.modules.topic.domain;

import java.util.List;

import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.topic.entity.TopicEntity;

public class TopicVo extends TopicEntity {

    /**
     * 教师打成订正
     */
    private Integer tchRevising;
    /**
     * 学生订正作业
     */
    private Integer stuRevisinged;

    //作业类型
    private String homeworkType;
    /**
     * 未批改人数
     */
    private Integer snoPerusel;
    /**
     * 已批改人数
     */
    private Integer sperusel;
    /**
     * 题目基础题型，基础题型是固定不变的 说明：[ 1 = '单选题', 2 = '多选题', 3 = '判断题', 4 = '填空题', 5 =
     * '解答题', 6 = '完形填空题', 7 = '复合题' ]
     * <p>
     * 21 世纪  精品试题 同步练习 提交该字段
     */
    private Integer baseType;
    //得分率
    private String scoreRate;
    //班级id
    private String classId;

    //真实得分
    private Float actualScore;

    public Integer getSnoPerusel() {
        return snoPerusel;
    }

    public void setSnoPerusel(Integer snoPerusel) {
        this.snoPerusel = snoPerusel;
    }

    public Integer getSperusel() {
        return sperusel;
    }

    public void setSperusel(Integer sperusel) {
        this.sperusel = sperusel;
    }

    public Integer getBaseType() {
        return baseType;
    }

    public void setBaseType(Integer baseType) {
        this.baseType = baseType;
    }

    public String getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(String homeworkType) {
        this.homeworkType = homeworkType;
    }


    /**
     * 临时字段
     */
    private List<StuTopicEntity> canBeCorrectedStu;

    public List<StuTopicEntity> getCanBeCorrectedStu() {
        return canBeCorrectedStu;
    }

    public void setCanBeCorrectedStu(List<StuTopicEntity> canBeCorrectedStu) {
        this.canBeCorrectedStu = canBeCorrectedStu;
    }

    public Integer getTchRevising() {
        return tchRevising;
    }

    public void setTchRevising(Integer tchRevising) {
        this.tchRevising = tchRevising;
    }

    public Integer getStuRevisinged() {
        return stuRevisinged;
    }

    public void setStuRevisinged(Integer stuRevisinged) {
        this.stuRevisinged = stuRevisinged;
    }

    public String getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(String scoreRate) {
        this.scoreRate = scoreRate;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Float getActualScore() {
        return actualScore;
    }

    public void setActualScore(Float actualScore) {
        this.actualScore = actualScore;
    }
}

