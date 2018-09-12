package com.lhh.vo;

import java.io.Serializable;

public class Part implements Serializable {

    private String bNum;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private AnswerItem[] answers;

    public String getbNum() {
        return bNum;
    }

    public void setbNum(String bNum) {
        this.bNum = bNum;
    }

    public AnswerItem[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswerItem... answers) {
        this.answers = answers;
    }
}
