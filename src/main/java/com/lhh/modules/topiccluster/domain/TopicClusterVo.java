package com.lhh.modules.topiccluster.domain;


import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topiccluster.entity.TopicClusterEntity;

import java.util.List;

public class TopicClusterVo extends TopicClusterEntity {


    /**
     * 教师打成订正的人数
     */
    private Integer tchClusterRevising;
    /**
     * 学生订正作业的人数
     */
    private Integer stuClusterRevisinged;

    //作业类型
    private String homeworkType;
    /**
     * 未批改人数
     */
    private Integer noPerusel;
    /**
     * 已批改人数
     */
    private Integer perusel;
    /**
     * 题簇下单题数组
     */
    private List<TopicVo> topicList;
    /**
     * 题目基础题型，基础题型是固定不变的 说明：[ 1 = '单选题', 2 = '多选题', 3 = '判断题', 4 = '填空题', 5 =
     * '解答题', 6 = '完形填空题', 7 = '复合题' ]
     * <p>
     * 21 世纪  精品试题 同步练习 提交该字段
     */
    private Integer baseType;

    /**
     * 未批改人数
     */
    public Integer getNoPerusel() {
        return noPerusel;
    }

    /**
     * 未批改人数
     */
    public void setNoPerusel(Integer noPerusel) {
        this.noPerusel = noPerusel;
    }

    /**
     * 已批改人数
     */
    public Integer getPerusel() {
        return perusel;
    }

    /**
     * 已批改人数
     */
    public void setPerusel(Integer perusel) {
        this.perusel = perusel;
    }


    public Integer getBaseType() {
        return baseType;
    }

    public void setBaseType(Integer baseType) {
        this.baseType = baseType;
    }

    public List<TopicVo> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicVo> topicList) {
        this.topicList = topicList;
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
    private List<StuTopicClusterEntity> canBeCorrectedStu;

    public List<StuTopicClusterEntity> getCanBeCorrectedStu() {
        return canBeCorrectedStu;
    }

    public void setCanBeCorrectedStu(List<StuTopicClusterEntity> canBeCorrectedStu) {
        this.canBeCorrectedStu = canBeCorrectedStu;
    }

    public Integer getTchClusterRevising() {
        return tchClusterRevising;
    }

    public void setTchClusterRevising(Integer tchClusterRevising) {
        this.tchClusterRevising = tchClusterRevising;
    }

    public Integer getStuClusterRevisinged() {
        return stuClusterRevisinged;
    }

    public void setStuClusterRevisinged(Integer stuClusterRevisinged) {
        this.stuClusterRevisinged = stuClusterRevisinged;
    }
}
