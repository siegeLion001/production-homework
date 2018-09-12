package com.lhh.modules.appCenter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.modules.appCenter.dao.AppVersionInfoDao;
import com.lhh.modules.appCenter.entity.AppVersionInfoEntity;
import com.lhh.modules.appCenter.service.AppVersionInfoService;



@Service("appVersionInfoService")
public class AppVersionInfoServiceImpl implements AppVersionInfoService {
	@Autowired
	private AppVersionInfoDao appVersionInfoDao;
	
	@Override
	public AppVersionInfoEntity queryObject(Integer appVersionId){
		return appVersionInfoDao.queryObject(appVersionId);
	}
	
	@Override
	public List<AppVersionInfoEntity> queryList(Map<String, Object> map){
		return appVersionInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appVersionInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(AppVersionInfoEntity appVersionInfo){
		appVersionInfoDao.save(appVersionInfo);
	}
	
	@Override
	public void update(AppVersionInfoEntity appVersionInfo){
		appVersionInfoDao.update(appVersionInfo);
	}
	
	@Override
	public void delete(Integer appVersionId){
		appVersionInfoDao.delete(appVersionId);
	}
	
	@Override
	public void deleteBatch(Integer[] appVersionIds){
		appVersionInfoDao.deleteBatch(appVersionIds);
	}

	@Override
	public List<AppVersionInfoEntity> getApkVersion(Map<String, Object> params) {
		return appVersionInfoDao.getApkVersion(params);
	}
	
}
