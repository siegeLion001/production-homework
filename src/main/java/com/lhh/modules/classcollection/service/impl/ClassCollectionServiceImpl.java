package com.lhh.modules.classcollection.service.impl;

import com.lhh.modules.classcollection.dao.ClassCollectionDao;
import com.lhh.modules.classcollection.entity.ClassCollectionEntity;
import com.lhh.modules.classcollection.service.ClassCollectionService;
import com.lhh.modules.classtop.entity.ClassTopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("classCollectionService")
public class ClassCollectionServiceImpl implements ClassCollectionService {
	@Autowired
	private ClassCollectionDao classCollectionDao;
	
	@Override
	public ClassCollectionEntity queryObject(Integer collId){
		return classCollectionDao.queryObject(collId);
	}
	
	@Override
	public List<ClassCollectionEntity> queryList(Map<String, Object> map){
		return classCollectionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classCollectionDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassCollectionEntity classCollection){
		classCollectionDao.save(classCollection);
	}
	
	@Override
	public void update(ClassCollectionEntity classCollection){
		classCollectionDao.update(classCollection);
	}
	
	@Override
	public void delete(Integer collId){
		classCollectionDao.delete(collId);
	}
	
	@Override
	public void deleteBatch(Integer[] collIds){
		classCollectionDao.deleteBatch(collIds);
	}

	@Override
	public ClassCollectionEntity queryObjectByTopIdAndAuthId(Integer topId, String authId) {
		return classCollectionDao.queryObjectByTopIdAndAuthId(topId,authId);
	}

	@Override
	public List<ClassTopEntity> queryMyCollectionTop(Map<String, Object> map) {
		return classCollectionDao.queryMyCollectionTop(map);
	}

	@Override
	public int queryMyCollectionTopTotal(Map<String, Object> map) {
		return classCollectionDao.queryMyCollectionTopTotal(map);
	}

}
