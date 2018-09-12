package com.lhh.modules.cardtemplate.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 09:46:50
 */
public class CardTemplateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private String tchId;
    //
    private String name;
    //
    private JSONObject card;
    //
    private Date createTime;

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public String getTchId() {
        return tchId;
    }

    /**
     * 设置：
     */
    public void setTchId(String tchId) {
        this.tchId = tchId;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public JSONObject getCard() {
        return card;
    }

    /**
     * 设置：
     */
    public void setCard(JSONObject card) {
        this.card = card;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
