package com.lhh.modules.classMessage.domain;

import java.util.Date;
import java.util.List;

/**
 * vo
 *
 * @ClassName: MsgAndAppraisalVo
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.classMessage.domain
 * @CreateTime: 2018-08-20
 */
public class MsgAndAppraisalVo {

    private Integer id;

    private String authId;

    private String targetId;

    private String content;

    private Integer state;

    private Integer parentId;

    private Date createTime;

    private String classId;

    private List<MsgAndAppraisalVo> childList;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
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

    public List<MsgAndAppraisalVo> getChildList() {
        return childList;
    }

    public void setChildList(List<MsgAndAppraisalVo> childList) {
        this.childList = childList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}