package com.lhh.modules.appCenter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.modules.appCenter.dao.AppInfoDao;
import com.lhh.modules.appCenter.entity.AppInfoEntity;
import com.lhh.modules.appCenter.entity.AppInfoModel;
import com.lhh.modules.appCenter.service.AppInfoService;



@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
	@Autowired
	private AppInfoDao appInfoDao;
	
	@Override
	public AppInfoEntity queryObject(Integer appId){
		return appInfoDao.queryObject(appId);
	}
	
	@Override
	public List<AppInfoEntity> queryList(Map<String, Object> map){
		return appInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(AppInfoEntity appInfo){
		appInfoDao.save(appInfo);
	}
	
	@Override
	public void update(AppInfoEntity appInfo){
		appInfoDao.update(appInfo);
	}
	
	@Override
	public void delete(Integer appId){
		appInfoDao.delete(appId);
	}
	
	@Override
	public void deleteBatch(Integer[] appIds){
		appInfoDao.deleteBatch(appIds);
	}

	@Override
	public List<AppInfoModel> queryAppList(Map<String, Object> map) {
		return appInfoDao.queryAppList(map);
	}
	
}
