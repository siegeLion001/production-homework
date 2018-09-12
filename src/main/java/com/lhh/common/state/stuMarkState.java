package com.lhh.common.state;

/**
 * FileName: stuMarkState
 * Author: cuihp
 * Date: 2018/5/31
 * Description:
 */
public enum stuMarkState {
    /**未批阅*/
    UNPERUSAL("未批阅",0),
    /**已批阅*/
    PERUSAL("已批阅",1);

    private String name;

    private int state;

    stuMarkState(String name, int state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
