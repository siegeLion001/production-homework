package com.lhh.modules.classtop.entity;

public class TopContent {

    //1 纯文字 2 纯图片 3 语音图片
    private Integer type;

    // 文字内容
    private String text = "";

    //图片路径
    private String[] imgs = new String[0];

    // 录音文件路径
    private String voice = "";

    private String video = "";


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
