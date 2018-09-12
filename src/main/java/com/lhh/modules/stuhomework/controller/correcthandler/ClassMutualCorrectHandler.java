package com.lhh.modules.stuhomework.controller.correcthandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;

public class ClassMutualCorrectHandler implements CorrectHandler {
    /**
     * 班级互批
     *
     * @param homeworkEntity
     * @return
     */

    private StuHomeworkService stuHomeworkService;

    public ClassMutualCorrectHandler(StuHomeworkService stuHomeworkService) {
        this.stuHomeworkService = stuHomeworkService;
    }

    @Override
    public List<StuHomeworkEntity> correct(HomeworkEntity homeworkEntity) {
        //TODO  互批设置
        Integer homeworkId = homeworkEntity.getId();
        String[] classIds = homeworkEntity.getClassIds();
        List<StuHomeworkEntity> newList = new ArrayList<>();
        for (String classId : classIds) {
            Map qm = new HashMap();
            qm.put("homeworkId", homeworkId);
            qm.put("classId", classId);
            List<StuHomeworkEntity> list = stuHomeworkService.queryList(qm);
            LinkedList<StuHomeworkEntity> linkedList2 = new LinkedList(list);
            if (list.size() != 0 && list != null) {
                StuHomeworkEntity pop = linkedList2.pop();
                linkedList2.add(pop);
            }
            for (int i = 0; i < list.size(); i++) {
                StuHomeworkEntity stuHomeworkEntity = list.get(i);
                StuHomeworkEntity stuHomeworkEntity1 = linkedList2.get(i);
                String stuId = stuHomeworkEntity1.getStuId();
                stuHomeworkEntity.setReviewerId(stuId);
                StuHomeworkEntity newEntity = new StuHomeworkEntity();
                newEntity.setId(stuHomeworkEntity.getId());
                newEntity.setReviewerId(stuHomeworkEntity.getReviewerId());
                newList.add(newEntity);
            }
        }
        return newList;
    }
}
