package com.lhh.modules.resourcecollect.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 资源收藏表 用于表示当前用户收藏的外部资源
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-23 21:18:05
 */
public class ResourceCollectEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //使用者id
    private String authId;
    //1 教师 2 学生
    private Integer authType;
    //资源id
    private String resourceId;
    //资源类型
    private Integer resourceType;
    //数据创建时间
    private Date createTime;
    //科目id
    private Integer subjectId;
    //题目类型
    private Integer type;
    //难度 同步练习 我的题目收藏使用
    private Integer difficult;


    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：使用者id
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * 获取：使用者id
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * 设置：1 教师 2 学生
     */
    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    /**
     * 获取：1 教师 2 学生
     */
    public Integer getAuthType() {
        return authType;
    }

    /**
     * 设置：资源id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取：资源id
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * 设置：资源类型
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取：资源类型
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * 设置：数据创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：数据创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：科目id
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取：科目id
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * 设置：题目类型  eg:单元试卷  专题试卷
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：题目类型  eg:单元试卷  专题试卷
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：难度 同步练习 我的题目收藏使用
     */
    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    /**
     * 获取：难度 同步练习 我的题目收藏使用
     */
    public Integer getDifficult() {
        return difficult;
    }
}
