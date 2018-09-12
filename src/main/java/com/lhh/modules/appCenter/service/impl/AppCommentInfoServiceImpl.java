package com.lhh.modules.appCenter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.modules.appCenter.dao.AppCommentInfoDao;
import com.lhh.modules.appCenter.entity.AppCommentInfoEntity;
import com.lhh.modules.appCenter.service.AppCommentInfoService;



@Service("appCommentInfoService")
public class AppCommentInfoServiceImpl implements AppCommentInfoService {
	@Autowired
	private AppCommentInfoDao appCommentInfoDao;
	
	@Override
	public AppCommentInfoEntity queryObject(Integer commentId){
		return appCommentInfoDao.queryObject(commentId);
	}
	
	@Override
	public List<AppCommentInfoEntity> queryList(Map<String, Object> map){
		return appCommentInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appCommentInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(AppCommentInfoEntity appCommentInfo){
		appCommentInfoDao.save(appCommentInfo);
	}
	
	@Override
	public void update(AppCommentInfoEntity appCommentInfo){
		appCommentInfoDao.update(appCommentInfo);
	}
	
	@Override
	public void delete(Integer commentId){
		appCommentInfoDao.delete(commentId);
	}
	
	@Override
	public void deleteBatch(Integer[] commentIds){
		appCommentInfoDao.deleteBatch(commentIds);
	}

	@Override
	public List<AppCommentInfoEntity> list(Map<String, Object> params) {
		return appCommentInfoDao.list(params);
	}
	
}
