package com.lhh.modules.stutopic.entity;

import com.lhh.modules.stutopic.entity.po.CorrectImg;

import java.io.Serializable;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-14 14:22:54
 */
public class StuTopicEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Integer id;
    //作业id
    private Integer homeworkId;
    //班级id
    private String classId;
    //学生id
    private String stuId;
    //学生大题id
    private Integer stuTopicClusterId;
    //题目类型
    private Integer type;
    //大题题号
    private String bNum;
    //大题分数
    private Float bScore;
    //小题题号
    private String sNum;
    //小题分数
    private Float sScore;
    //学生提交的作业内容（主观题）
    private CorrectImg[] submitContent;
    //学生提交的答案(客观题)
    private String[] submitAnswer;
    //订正标识 0 没有 1 有
    private Integer revisal;
    //订正内容
    private String revisalContent;
    //是否批阅  0未批阅  1已批阅
    private Integer mark;
    //真实得分
    private Float actualScore;
    //评优表示字段 1,未评优  2,评优
    private Integer excellent;
    //收藏字段   1未收藏  2,已收藏
    private Integer collect;
    //教师id(新加)
    private String tchId;
    //21世纪题目id
    private String questionId;
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
    public String getClassId() {
        return classId;
    }

    /**
     * 设置：
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * 设置：学生id
     */
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    /**
     * 获取：学生id
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * 获取：
     */
    public Integer getStuTopicClusterId() {
        return stuTopicClusterId;
    }

    /**
     * 设置：
     */
    public void setStuTopicClusterId(Integer stuTopicClusterId) {
        this.stuTopicClusterId = stuTopicClusterId;
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
     * 获取：学生提交的作业内容（主观题）
     */
    public CorrectImg[] getSubmitContent() {
        return submitContent;
    }

    /**
     * 设置：学生提交的作业内容（主观题）
     */
    public void setSubmitContent(CorrectImg[] submitContent) {
        this.submitContent = submitContent;
    }

    /**
     * 获取：学生提交的答案(客观题)
     */
    public String[] getSubmitAnswer() {
        return submitAnswer;
    }

    /**
     * 设置：学生提交的答案(客观题)
     */
    public void setSubmitAnswer(String[] submitAnswer) {
        this.submitAnswer = submitAnswer;
    }

    /**
     * 获取：订正标识 0 没有 1 有
     */
    public Integer getRevisal() {
        return revisal;
    }

    /**
     * 设置：订正标识 0 没有 1 有
     */
    public void setRevisal(Integer revisal) {
        this.revisal = revisal;
    }

    /**
     * 设置：订正内容
     */
    public void setRevisalContent(String revisalContent) {
        this.revisalContent = revisalContent;
    }

    /**
     * 获取：订正内容
     */
    public String getRevisalContent() {
        return revisalContent;
    }

    /**
     * 设置：是否批阅  0未批阅  1已批阅
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }

    /**
     * 获取：是否批阅  0未批阅  1已批阅
     */
    public Integer getMark() {
        return mark;
    }

    /**
     * 获取：真实得分
     */
    public Float getActualScore() {
        return actualScore;
    }

    /**
     * 设置：真实得分
     */
    public void setActualScore(Float actualScore) {
        this.actualScore = actualScore;
    }

    /**
     * 获取：评优表示字段 1,未评优  2,评优
     */
    public Integer getExcellent() {
        return excellent;
    }

    /**
     * 设置：评优表示字段 1,未评优  2,评优
     */
    public void setExcellent(Integer excellent) {
        this.excellent = excellent;
    }

    /**
     * 获取：收藏字段   1未收藏  2,已收藏
     */
    public Integer getCollect() {
        return collect;
    }

    /**
     * 设置：收藏字段   1未收藏  2,已收藏
     */
    public void setCollect(Integer collect) {
        this.collect = collect;
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
}
