package com.lhh.modules.classAppraisal.entity;

import java.util.List;

/**
 * @ClassName: ClassAppraisalVo
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.classAppraisal.entity.ClassAppraisalVo
 * @CreateTime: 2018-07-26
 */
public class ClassAppraisalVo extends ClassAppraisalEntity {

    private List<String> targetList;

    private List<ClassAppraisalVo> replyList;

    public List<ClassAppraisalVo> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ClassAppraisalVo> replyList) {
        this.replyList = replyList;
    }

    public List<String> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<String> targetList) {
        this.targetList = targetList;
    }
}