package com.lhh.modules.homework.controller;

public class QueryVo {
    /**
     * 优秀作业 使用字段
     */
    private String stuId;

    private String bNum;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getbNum() {
        return bNum;
    }

    public void setbNum(String bNum) {
        this.bNum = bNum;
    }

    /**
     * t 主管题  f 客观题 null 不筛选
     */
    private Boolean subjective;

    private Integer homeworkId;

    private String classId;

    private String tchId;

    private Integer queryType;

    private Integer page = 1;

    private Integer limit = 10;


    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getTchId() {
        return tchId;
    }

    public void setTchId(String tchId) {
        this.tchId = tchId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public Boolean getSubjective() {
        return subjective;
    }

    public void setSubjective(Boolean subjective) {
        this.subjective = subjective;
    }
}
