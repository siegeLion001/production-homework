package com.lhh.vo;

import java.io.Serializable;

public class AnswerItem implements Serializable {


    private String bNum;

    private String sNum;

    private String type;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String[] answerJson;

    private String[] imageUrl;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getAnswerJson() {
        return answerJson;
    }

    public void setAnswerJson(String[] answerJson) {
        this.answerJson = answerJson;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String... imageUrl) {
        this.imageUrl = imageUrl;
    }
}
