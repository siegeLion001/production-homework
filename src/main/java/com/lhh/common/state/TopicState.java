package com.lhh.common.state;

/**
 * FileName: TopicState
 * Author: cuihp
 * Date: 2018/6/20
 * Description:
 */
public enum TopicState {
    /*收藏*/
    COLLECT("收藏",2),
    /*未收藏*/
    UNCOLLECT("未收藏",1),
    /*评优*/
    EXCELLENT("评优",2),
    /*未评优*/
    UNEXCELLENT("未评优",1);

    private String name;
    private Integer code;

    TopicState(String name, Integer code) {
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
