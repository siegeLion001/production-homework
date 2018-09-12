package com.lhh.modules.classvisual.entity;

import java.io.Serializable;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 11:26:26
 */
public class ClassVisualEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //topId
    private Integer topId;
    //发送人
    private String authId;
    //是否已阅
    private Integer lookOver;
    //目标人
    private String targetId;
    //目标班级
    private String targetClassId;

    /**
     * 获取：id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：topId
     */
    public Integer getTopId() {
        return topId;
    }

    /**
     * 设置：topId
     */
    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    /**
     * 获取：发送人
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * 设置：发送人
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * 获取：是否已阅
     */
    public Integer getLookOver() {
        return lookOver;
    }

    /**
     * 设置：是否已阅
     */
    public void setLookOver(Integer lookOver) {
        this.lookOver = lookOver;
    }

    /**
     * 获取：目标人
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * 设置：目标人
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取：目标班级
     */
    public String getTargetClassId() {
        return targetClassId;
    }

    /**
     * 设置：目标班级
     */
    public void setTargetClassId(String targetClassId) {
        this.targetClassId = targetClassId;
    }
}
