package com.lhh.modules.classtaskfeedback.service.impl;

import com.lhh.modules.classtaskfeedback.dao.ClassTaskFeedbackDao;
import com.lhh.modules.classtaskfeedback.entity.ClassTaskFeedbackEntity;
import com.lhh.modules.classtaskfeedback.service.ClassTaskFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("classTaskFeedbackService")
public class ClassTaskFeedbackServiceImpl implements ClassTaskFeedbackService {
    @Autowired
    private ClassTaskFeedbackDao classTaskFeedbackDao;

    @Override
    public ClassTaskFeedbackEntity queryObject(Integer id) {
        return classTaskFeedbackDao.queryObject(id);
    }

    @Override
    public List<ClassTaskFeedbackEntity> queryList(Map<String, Object> map) {
        return classTaskFeedbackDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return classTaskFeedbackDao.queryTotal(map);
    }

    @Override
    public void save(ClassTaskFeedbackEntity classTaskFeedback) {
        classTaskFeedbackDao.save(classTaskFeedback);
    }

    @Override
    public void update(ClassTaskFeedbackEntity classTaskFeedback) {
        classTaskFeedbackDao.update(classTaskFeedback);
    }

    @Override
    public void delete(Integer id) {
        classTaskFeedbackDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        classTaskFeedbackDao.deleteBatch(ids);
    }
    @Override
    public Map<String, Object> queryAvgData(Map<String, Object> params) {
        Map<String, Object> avgData = classTaskFeedbackDao.queryAvgData(params);
//        Float avgDifficulty = Float.valueOf(String.valueOf(avgData.get("avgDifficulty")));
//        Float avgWorkLoad = Float.valueOf(String.valueOf(avgData.get("avgWorkLoad")));
//        Float avgUseTime = Float.valueOf(String.valueOf(avgData.get("avgUseTime")));
//
//        avgData.put("avgDifficulty",Math.round(avgDifficulty));
//        avgData.put("avgWorkLoad",Math.round(avgWorkLoad));
//        avgData.put("avgUseTime",Math.round(avgUseTime));

        return avgData;

    }

    @Override
    public Map<String, Object> queryRemark(Map<String, Object> params) {
        return classTaskFeedbackDao.queryRemark(params);
    }

}
