package com.lhh.modules.stutopic.domain;

public class WYStatistics {

    //大题题号
    private String bNum;

    //小题题号
    private String sNum;

    // 未批改
    private Integer unCorrect;

    // 已批改
    private Integer correct;

    //教师订正
    private Integer tchRevising;

    //学生修改
    private Integer stuRevisinged;


    public Integer getTchRevising() {
        return tchRevising;
    }

    public void setTchRevising(Integer tchRevising) {
        this.tchRevising = tchRevising;
    }

    public Integer getStuRevisinged() {
        return stuRevisinged;
    }

    public void setStuRevisinged(Integer stuRevisinged) {
        this.stuRevisinged = stuRevisinged;
    }

    public String getBNum() {
        return bNum;
    }

    public void setBNum(String bNum) {
        this.bNum = bNum;
    }

    public String getSNum() {
        return sNum;
    }

    public void setSNum(String sNum) {
        this.sNum = sNum;
    }

    public Integer getUnCorrect() {
        return unCorrect;
    }

    public void setUnCorrect(Integer unCorrect) {
        this.unCorrect = unCorrect;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }
}
