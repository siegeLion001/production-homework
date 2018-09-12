package com.lhh.modules.stuhomework.controller.correcthandler;

import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneSelfCorrectHandler implements CorrectHandler {
    /**
     * 学生自批
     *
     * @param homeworkEntity
     * @return
     */

    private StuHomeworkService stuHomeworkService;

    public OneSelfCorrectHandler(StuHomeworkService stuHomeworkService) {
        this.stuHomeworkService = stuHomeworkService;
    }

    @Override
    public List<StuHomeworkEntity> correct(HomeworkEntity homeworkEntity) {

        Integer homeworkId = homeworkEntity.getId();

        Map qm = new HashMap();

        qm.put("homeworkId", homeworkId);
        List<StuHomeworkEntity> list = stuHomeworkService.queryList(qm);

        List<StuHomeworkEntity> newList = new ArrayList<>();
        for (StuHomeworkEntity stuHomeworkEntity : list) {
            String stuId = stuHomeworkEntity.getStuId();
            stuHomeworkEntity.setReviewerId(stuId);

            StuHomeworkEntity newEntity = new StuHomeworkEntity();
            newEntity.setId(stuHomeworkEntity.getId());
            newEntity.setReviewerId(stuHomeworkEntity.getReviewerId());
            newList.add(newEntity);
        }
        return newList;
    }
}
