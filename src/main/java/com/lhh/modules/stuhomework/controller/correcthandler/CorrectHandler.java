package com.lhh.modules.stuhomework.controller.correcthandler;

import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;

import java.util.List;

public interface CorrectHandler {

    /**
     * 根据作业 重新设置学生作业的批改人
     *
     * @param homeworkEntity
     * @return
     */
    List<StuHomeworkEntity> correct(HomeworkEntity homeworkEntity);

}
