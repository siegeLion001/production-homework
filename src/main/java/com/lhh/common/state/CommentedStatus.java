package com.lhh.common.state;

/**
 * FileName: CommentedStatus
 * Author: cuihp
 * Date: 2018/6/19
 * Description:
 */
public enum CommentedStatus {

    /* 全体学生*/
    ALLSTU("全体学生",1),
    /* 指定学生*/
    APPOINTSTU("指定学生",2),
    /* 错误学生*/
    WRONGSTU("错误学生",3);


    private String name;
    private Integer code;

    private CommentedStatus(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
