package com.lhh.modules.appCenter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.modules.appCenter.dao.AppLableInfoDao;
import com.lhh.modules.appCenter.entity.AppLableInfoEntity;
import com.lhh.modules.appCenter.service.AppLableInfoService;



@Service("appLableInfoService")
public class AppLableInfoServiceImpl implements AppLableInfoService {
	@Autowired
	private AppLableInfoDao appLableInfoDao;
	
	@Override
	public AppLableInfoEntity queryObject(Integer labeId){
		return appLableInfoDao.queryObject(labeId);
	}
	
	@Override
	public List<AppLableInfoEntity> queryList(Map<String, Object> map){
		return appLableInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appLableInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(AppLableInfoEntity appLableInfo){
		appLableInfoDao.save(appLableInfo);
	}
	
	@Override
	public void update(AppLableInfoEntity appLableInfo){
		appLableInfoDao.update(appLableInfo);
	}
	
	@Override
	public void delete(Integer labeId){
		appLableInfoDao.delete(labeId);
	}
	
	@Override
	public void deleteBatch(Integer[] labeIds){
		appLableInfoDao.deleteBatch(labeIds);
	}
	
}
