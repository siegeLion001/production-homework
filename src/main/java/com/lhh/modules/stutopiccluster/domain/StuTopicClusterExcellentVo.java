package com.lhh.modules.stutopiccluster.domain;


/**
 * 学生作业
 * 优秀作业 二级列表  大题 并且包含学生id数组
 */
public class StuTopicClusterExcellentVo {

    private Integer homeworkId;

    private String bNum;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private String[] stuList;

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String[] getStuList() {
        return stuList;
    }

    public void setStuList(String[] stuList) {
        this.stuList = stuList;
    }

    public String getbNum() {
        return bNum;
    }

    public void setbNum(String bNum) {
        this.bNum = bNum;
    }
}
