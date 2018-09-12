package com.lhh.modules.stuhomework.domain;

import com.alibaba.fastjson.JSONObject;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;

import java.util.List;

public class SubmitStuHomeworkVo {

    /**
     * stu题簇数组
     */
    private List<StuTopicClusterVo> topicClusterList;

    //学生id
    private String stuId;

    //班级id
    private String classId;

    //作业id
    private Integer homeworkId;

    /**
     * 作业类型  1 日常作业 2 口语评测 3 同步练习 4 阶段检测
     */
    private String type;


    private Long timeKeep = 0L;
    //作业得分
    private Float actualScore;
    //语音分析
    private JSONObject analysis;
    //学生提交语音路径
    private String record;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public List<StuTopicClusterVo> getTopicClusterList() {
        return topicClusterList;
    }

    public void setTopicClusterList(List<StuTopicClusterVo> topicClusterList) {
        this.topicClusterList = topicClusterList;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTimeKeep() {
        return timeKeep;
    }

    public void setTimeKeep(Long timeKeep) {
        this.timeKeep = timeKeep;
    }

    public Float getActualScore() {
        return actualScore;
    }

    public void setActualScore(Float actualScore) {
        this.actualScore = actualScore;
    }

    public JSONObject getAnalysis() {
        return analysis;
    }

    public void setAnalysis(JSONObject analysis) {
        this.analysis = analysis;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
