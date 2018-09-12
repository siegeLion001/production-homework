package com.lhh.modules.stuhomework.controller;

public class ParamsVo {

    private Integer homeworkId;

    private Integer stuHomeworkId;

    private String stuId;

    private String bNum;

    private String sNum;

    private Boolean publish = false;


    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getbNum() {
        return bNum;
    }

    public void setbNum(String bNum) {
        this.bNum = bNum;
    }

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public Integer getStuHomeworkId() {
        return stuHomeworkId;
    }

    public void setStuHomeworkId(Integer stuHomeworkId) {
        this.stuHomeworkId = stuHomeworkId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

}
