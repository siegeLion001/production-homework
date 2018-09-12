package com.lhh.modules.HomeworkExplain.service.impl;

import com.lhh.modules.HomeworkExplain.dao.HomeworkExplainDao;
import com.lhh.modules.HomeworkExplain.entity.HomeworkExplainEntity;
import com.lhh.modules.HomeworkExplain.service.HomeworkExplainService;
import com.lhh.modules.HomeworkExplainRel.entity.HomeworkExplainRelEntity;
import com.lhh.modules.HomeworkExplainRel.service.HomeworkExplainRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;





@Service("homeworkExplainService")
public class HomeworkExplainServiceImpl implements HomeworkExplainService {
	@Autowired
	private HomeworkExplainDao homeworkExplainDao;
	@Autowired
	private HomeworkExplainRelService homeworkExplainRelService;

	
	@Override
	public HomeworkExplainEntity queryObject(Integer id){
		return homeworkExplainDao.queryObject(id);
	}
	
	@Override
	public List<HomeworkExplainEntity> queryList(Map<String, Object> map){
		return homeworkExplainDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return homeworkExplainDao.queryTotal(map);
	}
	
	@Override
	public void save(HomeworkExplainEntity homeworkExplain){
		homeworkExplainDao.save(homeworkExplain);
	}
	
	@Override
	public void update(HomeworkExplainEntity homeworkExplain){
		homeworkExplainDao.update(homeworkExplain);
	}
	
	@Override
	public void delete(Integer id){
		homeworkExplainDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		homeworkExplainDao.deleteBatch(ids);
	}

	@Override
	@Transactional
	public void saveExplain(HomeworkExplainEntity homeworkExplainEntity, List<HomeworkExplainRelEntity> relEntityList) {
		homeworkExplainDao.save(homeworkExplainEntity);
		Integer id = homeworkExplainEntity.getId();
		for (HomeworkExplainRelEntity homeworkExplainRelEntity : relEntityList) {
			homeworkExplainRelEntity.setEId(id);
		}
		homeworkExplainRelService.saveBatch(relEntityList);
	}

}
