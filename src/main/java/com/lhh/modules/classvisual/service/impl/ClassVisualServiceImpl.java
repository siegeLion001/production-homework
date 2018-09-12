package com.lhh.modules.classvisual.service.impl;

import com.lhh.modules.classvisual.dao.ClassVisualDao;
import com.lhh.modules.classvisual.entity.ClassVisualEntity;
import com.lhh.modules.classvisual.service.ClassVisualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("classVisualService")
public class ClassVisualServiceImpl implements ClassVisualService {
    @Autowired
    private ClassVisualDao classVisualDao;

    @Override
    public ClassVisualEntity queryObject(Integer id) {
        return classVisualDao.queryObject(id);
    }

    @Override
    public List<ClassVisualEntity> queryList(Map<String, Object> map) {
        return classVisualDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return classVisualDao.queryTotal(map);
    }

    @Override
    public void save(ClassVisualEntity classVisual) {
        classVisualDao.save(classVisual);
    }

    @Override
    public void update(ClassVisualEntity classVisual) {
        classVisualDao.update(classVisual);
    }

    @Override
    public void delete(Integer id) {
        classVisualDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        classVisualDao.deleteBatch(ids);
    }

    @Override
    public void batchSave(List<? extends ClassVisualEntity> classVisualEntities) {
        classVisualDao.batchSave(classVisualEntities);
    }

    @Override
    public void delete4Map(HashMap<String, Object> params) {
        classVisualDao.delete4Map(params);
    }

}
