package com.lhh.modules.topic.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-29 09:38:19
 */
public class TopicEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //作业id
    private Integer homeworkId;
    //
    private Integer topicClusterId;
    //题目类型
    private Integer type;
    //题目内容
    private JSONObject content;
    //大题题号
    private String bNum;
    //大题分数
    private Float bScore;
    //
    private String sNum;
    //小题分数
    private Float sScore;
    //正确的答案
    private List correctAnswer;
    //答案分析详解
    private String explanation;
    //21世纪题目id
    private String questionId;
    //题目来源 1 教辅 2 同步题库 3 校本卷库 4 精品卷库
    private String source;
    //教师id(新加)
    private String tchId;
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
     * 获取：
     */
    public Integer getTopicClusterId() {
        return topicClusterId;
    }

    /**
     * 设置：
     */
    public void setTopicClusterId(Integer topicClusterId) {
        this.topicClusterId = topicClusterId;
    }

    /**
     * 获取：题目类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：题目类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：题目内容
     */
    public JSONObject getContent() {
        return content;
    }

    /**
     * 设置：题目内容
     */
    public void setContent(JSONObject content) {
        this.content = content;
    }

    /**
     * 获取：大题题号
     */
    public String getBNum() {
        return bNum;
    }

    /**
     * 设置：大题题号
     */
    public void setBNum(String bNum) {
        this.bNum = bNum;
    }

    /**
     * 获取：大题分数
     */
    public Float getBScore() {
        return bScore;
    }

    /**
     * 设置：大题分数
     */
    public void setBScore(Float bScore) {
        this.bScore = bScore;
    }

    /**
     * 获取：
     */
    public String getSNum() {
        return sNum;
    }

    /**
     * 设置：
     */
    public void setSNum(String sNum) {
        this.sNum = sNum;
    }

    /**
     * 获取：小题分数
     */
    public Float getSScore() {
        return sScore;
    }

    /**
     * 设置：小题分数
     */
    public void setSScore(Float sScore) {
        this.sScore = sScore;
    }

    /**
     * 设置：正确的答案
     */
    public List getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * 获取：答案分析详解
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * 设置：答案分析详解
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * 获取：21世纪题目id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置：21世纪题目id
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取：题目来源 1 教辅 2 精品试题 3 校本卷库 4 精品卷库
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置：题目来源 1 教辅 2 精品试题 3 校本卷库 4 精品卷库
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取：教师id(新加)
     */
    public String getTchId() {
        return tchId;
    }

    /**
     * 设置：教师id(新加)
     */
    public void setTchId(String tchId) {
        this.tchId = tchId;
    }
}
