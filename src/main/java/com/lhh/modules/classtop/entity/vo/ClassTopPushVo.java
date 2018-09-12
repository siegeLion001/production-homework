package com.lhh.modules.classtop.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lhh.modules.classtaskfeedback.entity.ClassTaskFeedbackEntity;
import com.lhh.modules.classtop.entity.ClassTopEntity;
import com.lhh.modules.homework.domain.ClassInfo;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassTopPushVo extends ClassTopEntity {

    private ClassInfo[] classInfos = new ClassInfo[0];

    public ClassInfo[] getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(ClassInfo[] classInfos) {
        this.classInfos = classInfos;
    }


    /**
     * 提交任务的时候需要传入
     */
    // 作用 增加已提交的人 需要根据classId 来进行数组添加操作
    private String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * 任务反馈
     */
    private ClassTaskFeedbackEntity classTaskFeedback;

    public ClassTaskFeedbackEntity getClassTaskFeedback() {
        return classTaskFeedback;
    }

    public void setClassTaskFeedback(ClassTaskFeedbackEntity classTaskFeedback) {
        this.classTaskFeedback = classTaskFeedback;
    }
}
