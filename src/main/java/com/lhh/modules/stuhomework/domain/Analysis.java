package com.lhh.modules.stuhomework.domain;


import com.lhh.modules.stuhomework.domain.kdxf.Word;

// 语音提分析结果
public class Analysis {

    private String lan;//语言
    private String total_score;//总分
    private String beg_pos;//开始位置(帧)
    private String phone_score;//声韵分
    private String is_rejected;//是否被拒（被拒时可以给0分）
    private String integrity_score;//完整度分
    private String fluency_score;//流畅度分
    private String tone_score;//调型分
    private String time_len;//朗读时长（单位：帧）
    private String end_pos;//结束位置(帧)
    private String except_info;//异常信息，详细请见 except_info 列表
    private String content;    //试卷内容

    /**
     * 单词
     */
    private Word[] word;

    public Word[] getWord() {
        return word;
    }

    public void setWord(Word[] word) {
        this.word = word;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getBeg_pos() {
        return beg_pos;
    }

    public void setBeg_pos(String beg_pos) {
        this.beg_pos = beg_pos;
    }

    public String getPhone_score() {
        return phone_score;
    }

    public void setPhone_score(String phone_score) {
        this.phone_score = phone_score;
    }

    public String getIs_rejected() {
        return is_rejected;
    }

    public void setIs_rejected(String is_rejected) {
        this.is_rejected = is_rejected;
    }

    public String getIntegrity_score() {
        return integrity_score;
    }

    public void setIntegrity_score(String integrity_score) {
        this.integrity_score = integrity_score;
    }

    public String getFluency_score() {
        return fluency_score;
    }

    public void setFluency_score(String fluency_score) {
        this.fluency_score = fluency_score;
    }

    public String getTone_score() {
        return tone_score;
    }

    public void setTone_score(String tone_score) {
        this.tone_score = tone_score;
    }

    public String getTime_len() {
        return time_len;
    }

    public void setTime_len(String time_len) {
        this.time_len = time_len;
    }

    public String getEnd_pos() {
        return end_pos;
    }

    public void setEnd_pos(String end_pos) {
        this.end_pos = end_pos;
    }

    public String getExcept_info() {
        return except_info;
    }

    public void setExcept_info(String except_info) {
        this.except_info = except_info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
