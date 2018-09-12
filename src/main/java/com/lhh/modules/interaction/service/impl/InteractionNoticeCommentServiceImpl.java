package com.lhh.modules.interaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.interaction.dao.InteractionNoticeCommentDao;
import com.lhh.modules.interaction.entity.InteractionNoticeCommentEntity;
import com.lhh.modules.interaction.service.InteractionNoticeCommentService;



@Service("interactionNoticeCommentService")
public class InteractionNoticeCommentServiceImpl implements InteractionNoticeCommentService {
	@Autowired
	private InteractionNoticeCommentDao interactionNoticeCommentDao;
	
	@Override
	public InteractionNoticeCommentEntity queryObject(Integer commentId){
		return interactionNoticeCommentDao.queryObject(commentId);
	}
	
	@Override
	public List<InteractionNoticeCommentEntity> queryList(Map<String, Object> map){
		return interactionNoticeCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return interactionNoticeCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(InteractionNoticeCommentEntity interactionNoticeComment){
		interactionNoticeCommentDao.save(interactionNoticeComment);
	}
	
	@Override
	public void update(InteractionNoticeCommentEntity interactionNoticeComment){
		interactionNoticeCommentDao.update(interactionNoticeComment);
	}
	
	@Override
	public void delete(Integer commentId){
		interactionNoticeCommentDao.delete(commentId);
	}
	
	@Override
	public void deleteBatch(Integer[] commentIds){
		interactionNoticeCommentDao.deleteBatch(commentIds);
	}
	
}
