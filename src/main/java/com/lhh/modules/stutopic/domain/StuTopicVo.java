package com.lhh.modules.stutopic.domain;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lhh.modules.stutopic.entity.StuTopicEntity;

public class StuTopicVo extends StuTopicEntity {

    //题目id
    private Integer topicId;
    //学生题目id
    private Integer stuTopicId;
    //题簇id
    private Integer topicClusterId;
    //题目内容
    private JSONObject content;
    //正确的答案
    private List<String> correctAnswer;
    //答案分析详解
    private String explanation;
    //21世纪题目id
    private String questionId;
    //题目来源 1 教辅 2 精品试题 3 校本卷库 4 精品卷库
    private String source;
    //作业类型
    private String homeworkType;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getStuTopicId() {
        return stuTopicId;
    }

    public void setStuTopicId(Integer stuTopicId) {
        this.stuTopicId = stuTopicId;
    }

    public Integer getTopicClusterId() {
        return topicClusterId;
    }

    public void setTopicClusterId(Integer topicClusterId) {
        this.topicClusterId = topicClusterId;
    }


    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(String homeworkType) {
        this.homeworkType = homeworkType;
    }


}
