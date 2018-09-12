package com.lhh.modules.stuhomework.entity;

import com.alibaba.fastjson.JSONObject;
import com.lhh.modules.stuhomework.domain.Evaluate;

import java.io.Serializable;
import java.util.Date;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-29 09:38:19
 */
public class StuHomeworkEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //作业id
    private Integer homeworkId;
    //班级id
    private String classId;
    //学生id
    private String stuId;
    //学生宏观状态
    private Integer stuMacroStatus;
    //教师宏观状态
    private Integer tchMacroStatus;
    //学生状态
    private Integer stuState;
    //老师状态
    private Integer tchState;
    //操作提交时间
    private Date submitTime;
    //打回原因
    private String backReason;
    /**
     * zuoye   yongshi
     */
    private Long timeKeep = 0L;

    //作业评价
    private Evaluate evaluate;
    //作业得分
    private Float actualScore;
    //语音分析
    private JSONObject analysis;
    //学生提交语音路径
    private String record;
    //批阅者id  默认教师id  设置互批后 学生id
    private String reviewerId;
    //0包含人工打分 1 系统打分
    private Integer evaluationer;


    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：作业id
     */
    public Integer getHomeworkId() {
        return homeworkId;
    }

    /**
     * 设置：作业id
     */
    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    /**
     * 获取：班级id
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置：班级id
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * 获取：学生id
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * 设置：学生id
     */
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    /**
     * 获取：学生宏观状态
     */
    public Integer getStuMacroStatus() {
        return stuMacroStatus;
    }

    /**
     * 设置：学生宏观状态
     */
    public void setStuMacroStatus(Integer stuMacroStatus) {
        this.stuMacroStatus = stuMacroStatus;
    }

    /**
     * 获取：教师宏观状态
     */
    public Integer getTchMacroStatus() {
        return tchMacroStatus;
    }

    /**
     * 设置：教师宏观状态
     */
    public void setTchMacroStatus(Integer tchMacroStatus) {
        this.tchMacroStatus = tchMacroStatus;
    }

    /**
     * 获取：学生状态
     */
    public Integer getStuState() {
        return stuState;
    }

    /**
     * 设置：学生状态
     */
    public void setStuState(Integer stuState) {
        this.stuState = stuState;
    }

    /**
     * 获取：老师状态
     */
    public Integer getTchState() {
        return tchState;
    }

    /**
     * 设置：老师状态
     */
    public void setTchState(Integer tchState) {
        this.tchState = tchState;
    }

    /**
     * 获取：操作提交时间
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置：操作提交时间
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取：打回原因
     */
    public String getBackReason() {
        return backReason;
    }

    /**
     * 设置：打回原因
     */
    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public Long getTimeKeep() {
        return timeKeep;
    }

    public void setTimeKeep(Long timeKeep) {
        this.timeKeep = timeKeep;
    }

    /**
     * 获取：作业评价
     */
    public Evaluate getEvaluate() {
        return evaluate;
    }

    /**
     * 设置：作业评价
     */
    public void setEvaluate(Evaluate evaluate) {
        this.evaluate = evaluate;
    }

    /**
     * 获取：作业得分
     */
    public Float getActualScore() {
        return actualScore;
    }

    /**
     * 设置：作业得分
     */
    public void setActualScore(Float actualScore) {
        this.actualScore = actualScore;
    }

    /**
     * 获取：语音分析
     */
    public JSONObject getAnalysis() {
        return analysis;
    }

    /**
     * 设置：语音分析
     */
    public void setAnalysis(JSONObject analysis) {
        this.analysis = analysis;
    }

    /**
     * 获取：学生提交语音路径
     */
    public String getRecord() {
        return record;
    }

    /**
     * 设置：学生提交语音路径
     */
    public void setRecord(String record) {
        this.record = record;
    }

    /**
     * 获取：批阅者id  默认教师id  设置互批后 学生id
     */
    public String getReviewerId() {
        return reviewerId;
    }

    /**
     * 设置：批阅者id  默认教师id  设置互批后 学生id
     */
    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }
    /**
     * 设置：0包含人工打分 1 系统打分
     */
    public void setEvaluationer(Integer evaluationer) {
        this.evaluationer = evaluationer;
    }
    /**
     * 获取：0包含人工打分 1 系统打分
     */
    public Integer getEvaluationer() {
        return evaluationer;
    }
}
