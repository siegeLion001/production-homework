package com.lhh.modules.homeworktemp.service.impl;

import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homeworktemp.dao.HomeworkTempDao;
import com.lhh.modules.homeworktemp.service.HomeworkTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("homeworkTempService")
public class HomeworkTempServiceImpl implements HomeworkTempService {
    @Autowired
    private HomeworkTempDao homeworkTempDao;

    @Override
    public HomeworkEntity queryObject(Integer id) {
        return homeworkTempDao.queryObject(id);
    }

    @Override
    public List<HomeworkEntity> queryList(Map<String, Object> map) {
        return homeworkTempDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return homeworkTempDao.queryTotal(map);
    }

    @Override
    public void save(HomeworkEntity homeworkTemp) {
        homeworkTempDao.save(homeworkTemp);
    }

    @Override
    public void update(HomeworkEntity homeworkTemp) {
        homeworkTempDao.update(homeworkTemp);
    }

    @Override
    public void delete(Integer id) {
        homeworkTempDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        homeworkTempDao.deleteBatch(ids);
    }

}
