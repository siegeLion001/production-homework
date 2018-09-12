package com.lhh.modules.stutopic.entity.po;

public class CorrectImg {

    // 目标图片路径
    private String sourceUrl;
    // 订正图片路径
    private String correctUrl;
    // 订正标识
    private Integer revisal;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getCorrectUrl() {
        return correctUrl;
    }

    public void setCorrectUrl(String correctUrl) {
        this.correctUrl = correctUrl;
    }

    public Integer getRevisal() {
        return revisal;
    }

    public void setRevisal(Integer revisal) {
        this.revisal = revisal;
    }
}
