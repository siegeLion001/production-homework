package com.lhh.modules.HomeworkExplainRel.service.impl;

import com.lhh.modules.HomeworkExplainRel.dao.HomeworkExplainRelDao;
import com.lhh.modules.HomeworkExplainRel.entity.HomeworkExplainRelEntity;
import com.lhh.modules.HomeworkExplainRel.service.HomeworkExplainRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("homeworkExplainRelService")
public class HomeworkExplainRelServiceImpl implements HomeworkExplainRelService {
	@Autowired
	private HomeworkExplainRelDao homeworkExplainRelDao;
	
	@Override
	public HomeworkExplainRelEntity queryObject(Integer id){
		return homeworkExplainRelDao.queryObject(id);
	}
	
	@Override
	public List<HomeworkExplainRelEntity> queryList(Map<String, Object> map){
		return homeworkExplainRelDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return homeworkExplainRelDao.queryTotal(map);
	}
	
	@Override
	public void save(HomeworkExplainRelEntity homeworkExplainRel){
		homeworkExplainRelDao.save(homeworkExplainRel);
	}
	
	@Override
	public void update(HomeworkExplainRelEntity homeworkExplainRel){
		homeworkExplainRelDao.update(homeworkExplainRel);
	}
	
	@Override
	public void delete(Integer id){
		homeworkExplainRelDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		homeworkExplainRelDao.deleteBatch(ids);
	}

	@Override
	public void saveBatch(List<HomeworkExplainRelEntity> relList) {
		homeworkExplainRelDao.saveBatch(relList);
	}

}
