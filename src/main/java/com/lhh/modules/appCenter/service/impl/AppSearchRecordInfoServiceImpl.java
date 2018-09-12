package com.lhh.modules.appCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.appCenter.dao.AppSearchRecordInfoDao;
import com.lhh.modules.appCenter.entity.AppSearchRecordInfoEntity;
import com.lhh.modules.appCenter.service.AppSearchRecordInfoService;



@Service("appSearchRecordInfoService")
public class AppSearchRecordInfoServiceImpl implements AppSearchRecordInfoService {
	@Autowired
	private AppSearchRecordInfoDao appSearchRecordInfoDao;
	
	@Override
	public AppSearchRecordInfoEntity queryObject(Integer appSearchRecordId){
		return appSearchRecordInfoDao.queryObject(appSearchRecordId);
	}
	
	@Override
	public List<AppSearchRecordInfoEntity> queryList(Map<String, Object> map){
		return appSearchRecordInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appSearchRecordInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(AppSearchRecordInfoEntity appSearchRecordInfo){
		appSearchRecordInfoDao.save(appSearchRecordInfo);
	}
	
	@Override
	public void update(AppSearchRecordInfoEntity appSearchRecordInfo){
		appSearchRecordInfoDao.update(appSearchRecordInfo);
	}
	
	@Override
	public void delete(Integer appSearchRecordId){
		appSearchRecordInfoDao.delete(appSearchRecordId);
	}
	
	@Override
	public void deleteBatch(Integer[] appSearchRecordIds){
		appSearchRecordInfoDao.deleteBatch(appSearchRecordIds);
	}

	@Override
	public AppSearchRecordInfoEntity queryOne(Map<String, Object> map) {
		return appSearchRecordInfoDao.queryOne(map);
	}
	
}
