package com.lhh.modules.interaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.interaction.dao.InteractionNoticeDao;
import com.lhh.modules.interaction.domain.NoticeListVo;
import com.lhh.modules.interaction.entity.InteractionNoticeEntity;
import com.lhh.modules.interaction.service.InteractionNoticeService;



@Service("interactionNoticeService")
public class InteractionNoticeServiceImpl implements InteractionNoticeService {
	@Autowired
	private InteractionNoticeDao interactionNoticeDao;
	
	@Override
	public InteractionNoticeEntity queryObject(Integer noticeId){
		return interactionNoticeDao.queryObject(noticeId);
	}
	
	@Override
	public List<NoticeListVo> queryList(Map<String, Object> map){
		return interactionNoticeDao.queryTchList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return interactionNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(InteractionNoticeEntity interactionNotice){
		interactionNoticeDao.save(interactionNotice);
	}
	
	@Override
	public void update(InteractionNoticeEntity interactionNotice){
		interactionNoticeDao.update(interactionNotice);
	}
	
	@Override
	public void delete(Integer noticeId){
		interactionNoticeDao.delete(noticeId);
	}
	
	@Override
	public void deleteBatch(Integer[] noticeIds){
		interactionNoticeDao.deleteBatch(noticeIds);
	}
	
}
