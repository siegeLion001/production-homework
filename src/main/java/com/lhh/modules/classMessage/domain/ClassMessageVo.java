package com.lhh.modules.classMessage.domain;

import java.util.List;

import com.lhh.modules.classMessage.entity.ClassMessageEntity;

/**
 * vo类
 *
 * @ClassName: ClassMessageVo
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.classMessage.domain
 * @CreateTime: 2018-08-18
 */
public class ClassMessageVo extends ClassMessageEntity {

    private List<ClassMessageVo> msgList;

    public List<ClassMessageVo> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<ClassMessageVo> msgList) {
        this.msgList = msgList;
    }
}