package com.lhh.modules.classLabel.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.modules.classLabel.dao.ClassLabelDao;
import com.lhh.modules.classLabel.entity.ClassLabelEntity;
import com.lhh.modules.classLabel.service.ClassLabelService;

@Service("classLabelService")
public class ClassLabelServiceImpl implements ClassLabelService {
	@Autowired
	private ClassLabelDao classLabelDao;
	
	@Override
	public ClassLabelEntity queryObject(Integer labelid){
		return classLabelDao.queryObject(labelid);
	}
	
	@Override
	public List<ClassLabelEntity> queryList(Map<String, Object> map){
		return classLabelDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classLabelDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassLabelEntity classLabel){
		classLabelDao.save(classLabel);
	}
	
	@Override
	public void update(ClassLabelEntity classLabel){
		classLabelDao.update(classLabel);
	}
	
	@Override
	public void delete(Integer labelid){
		classLabelDao.delete(labelid);
	}
	
	@Override
	public void deleteBatch(Integer[] labelids){
		classLabelDao.deleteBatch(labelids);
	}

    @Override
    public List<ClassLabelEntity> queryLabelList(Map<String, Object> params) {
        return classLabelDao.queryLabelList(params);
    }

}
