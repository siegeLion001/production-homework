package com.lhh.modules.interaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.interaction.dao.InteractionStuNoticeDao;
import com.lhh.modules.interaction.entity.InteractionStuNoticeEntity;
import com.lhh.modules.interaction.service.InteractionStuNoticeService;



@Service("interactionStuNoticeService")
public class InteractionStuNoticeServiceImpl implements InteractionStuNoticeService {
	@Autowired
	private InteractionStuNoticeDao interactionStuNoticeDao;
	
	@Override
	public InteractionStuNoticeEntity queryObject(Integer stuNoticeId){
		return interactionStuNoticeDao.queryObject(stuNoticeId);
	}
	
	@Override
	public List<InteractionStuNoticeEntity> queryList(Map<String, Object> map){
		return interactionStuNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return interactionStuNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(InteractionStuNoticeEntity interactionStuNotice){
		interactionStuNoticeDao.save(interactionStuNotice);
	}
	
	@Override
	public void update(InteractionStuNoticeEntity interactionStuNotice){
		interactionStuNoticeDao.update(interactionStuNotice);
	}
	
	@Override
	public void delete(Integer stuNoticeId){
		interactionStuNoticeDao.delete(stuNoticeId);
	}
	
	@Override
	public void deleteBatch(Integer[] stuNoticeIds){
		interactionStuNoticeDao.deleteBatch(stuNoticeIds);
	}

	@Override
	public void batchSave(List<InteractionStuNoticeEntity> stuNoticeEntitys) {
		interactionStuNoticeDao.batchSave(stuNoticeEntitys);
	}
	
}
