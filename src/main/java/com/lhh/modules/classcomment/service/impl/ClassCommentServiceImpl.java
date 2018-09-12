package com.lhh.modules.classcomment.service.impl;

import com.lhh.modules.classcomment.dao.ClassCommentDao;
import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.entity.vo.ClassCommentVo;
import com.lhh.modules.classcomment.service.ClassCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("classCommentService")
public class ClassCommentServiceImpl implements ClassCommentService {
    @Autowired
    private ClassCommentDao classCommentDao;

    @Override
    public ClassCommentEntity queryObject(Integer id) {
        return classCommentDao.queryObject(id);
    }

    @Override
    public List<ClassCommentEntity> queryList(Map<String, Object> map) {
        return classCommentDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return classCommentDao.queryTotal(map);
    }

    @Override
    public void save(ClassCommentEntity classComment) {
        classCommentDao.save(classComment);
    }

    @Override
    public void update(ClassCommentEntity classComment) {
        classCommentDao.update(classComment);
    }

    @Override
    public void delete(Integer id) {
        classCommentDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        classCommentDao.deleteBatch(ids);
    }

    @Override
    public List<ClassCommentEntity> queryMyList(Map<String, Object> map) {
        return classCommentDao.queryMyList(map);
    }

    @Override
    public int queryMyTotal(Map<String, Object> map) {
        return classCommentDao.queryMyTotal(map);
    }

    @Override
    public List<ClassCommentVo> commentList(Map<String, Object> params) {
        return classCommentDao.commentList(params);
    }


}
