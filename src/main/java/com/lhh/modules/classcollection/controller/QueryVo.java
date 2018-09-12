package com.lhh.modules.classcollection.controller;

public class QueryVo {
    //操作人id
    private String authId;
    //评论人类型  教师或学生
    private Integer authType;
    //标示字段  1,收藏  2,成长记录
    private Integer collMark;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Integer getCollMark() {
        return collMark;
    }

    public void setCollMark(Integer collMark) {
        this.collMark = collMark;
    }
}
