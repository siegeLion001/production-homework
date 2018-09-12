package com.lhh.modules.classtop.service.impl;

import com.lhh.modules.classtop.dao.ClassTopDao;
import com.lhh.modules.classtop.entity.ClassTopEntity;
import com.lhh.modules.classtop.entity.vo.ClassTopVo;
import com.lhh.modules.classtop.service.ClassTopService;
import com.lhh.modules.classvisual.service.ClassVisualService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("classTopService")
public class ClassTopServiceImpl implements ClassTopService {
    @Autowired
    private ClassTopDao classTopDao;
    @Autowired
    private ClassVisualService classVisualService;

    @Override
    public ClassTopEntity queryObject(Integer id) {
        return classTopDao.queryObject(id);
    }

    @Override
    public List<ClassTopEntity> queryList(Map<String, Object> map) {
        return classTopDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return classTopDao.queryTotal(map);
    }

    @Override
    public void save(ClassTopEntity classTop) {
        classTopDao.save(classTop);
    }

    @Override
    public void update(ClassTopEntity classTop) {
        classTopDao.update(classTop);
    }

    @Override
    public void delete(Integer id) {
        classTopDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        classTopDao.deleteBatch(ids);
    }

    @Override
    public List<ClassTopVo> queryTopListByTarget(Map<String, Object> map) {
        return classTopDao.queryTopListByTarget(map);
    }

    @Override
    public int queryTopTotalByTarget(Map<String, Object> map) {
        return classTopDao.queryTopTotalByTarget(map);
    }

    @Override
    public List<ClassTopVo> queryMyList(Map<String, Object> map) {
        return classTopDao.queryMyList(map);
    }

    @Override
    public int queryMyTotal(Map<String, Object> map) {
        return classTopDao.queryMyTotal(map);
    }

    @Override
    @Transactional
    public boolean deleteTop(Integer id) {
        boolean state = false;
        delete(id);
        HashMap<String, Object> params = new HashMap<>();
        params.put("topId",id);
        classVisualService.delete4Map(params);
        state = true;
        return state;
    }

    @Override
    public Map<String, Object> queryInformationCount(Map<String, Object> params) {
        return classTopDao.queryInformationCount(params);
    }

}
