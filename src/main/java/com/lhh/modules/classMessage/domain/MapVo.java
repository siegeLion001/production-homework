package com.lhh.modules.classMessage.domain;

import java.util.Date;

/**
 * 仅适用于评价+寄语回复功能传参
 *
 * @ClassName: MapVo
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.classMessage.domain
 * @CreateTime: 2018-09-04
 */
public class MapVo {

    private Integer id;

    private String authId;

    private String targetId;

    private String content;

    private int state;

    private Integer parentId;

    private Date createTime;

    private String classId;



    private String appraisal;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if(0==state)
            message = content;
        if(1==state || 2== state)
            appraisal = content;

        this.state = state;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }


}