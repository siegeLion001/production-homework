package com.lhh.modules.stutopiccluster.entity;

import com.lhh.modules.stutopic.entity.po.CorrectImg;

import java.io.Serializable;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-29 09:38:19
 */
public class StuTopicClusterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //作业id
    private Integer homeworkId;
    //班级id
    private String classId;
    //学生id
    private String stuId;
    //题目类型
    private Integer type;
    //大题题号
    private String bNum;
    //大题分数
    private Float bScore;
    //学生提交的作业内容（主观题）
    private CorrectImg[] submitContent;
    //评优表示字段 1,未评优  2,评优
    private Integer excellent;
    //收藏字段   1未收藏  2,已收藏
    private Integer collect;
    //订正标识 0 没有 1 有
    private Integer revisal;
    //是否批阅  0未批阅  1已批阅  2批阅中  只有大题才有批阅中状态
    private Integer mark;
    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：作业id
     */
    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    /**
     * 获取：作业id
     */
    public Integer getHomeworkId() {
        return homeworkId;
    }

    /**
     * 设置：班级id
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * 获取：班级id
     */
    public String getClassId() {
        return classId;
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
     * 设置：题目类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：题目类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：大题题号
     */
    public void setBNum(String bNum) {
        this.bNum = bNum;
    }

    /**
     * 获取：大题题号
     */
    public String getBNum() {
        return bNum;
    }

    /**
     * 设置：大题分数
     */
    public void setBScore(Float bScore) {
        this.bScore = bScore;
    }

    /**
     * 获取：大题分数
     */
    public Float getBScore() {
        return bScore;
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
     * 设置：是否批阅  0未批阅  1已批阅 2批阅中
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }
    /**
     * 获取：是否批阅  0未批阅  1已批阅 2批阅中
     */
    public Integer getMark() {
        return mark;
    }

}
