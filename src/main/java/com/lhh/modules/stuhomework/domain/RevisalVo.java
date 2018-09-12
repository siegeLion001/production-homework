package com.lhh.modules.stuhomework.domain;

import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stutopic.domain.StuTopicVo;

public class RevisalVo extends StuHomeworkEntity {

    private StuTopicVo[] stuTopicVos;

    public StuTopicVo[] getStuTopicVos() {
        return stuTopicVos;
    }

    public void setStuTopicVos(StuTopicVo... stuTopicVos) {
        this.stuTopicVos = stuTopicVos;
    }
}
