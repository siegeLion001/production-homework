package com.lhh.modules.appCenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.modules.appCenter.dao.AppDownloadRecordInfoDao;
import com.lhh.modules.appCenter.entity.AppDownloadRecordInfoEntity;
import com.lhh.modules.appCenter.service.AppDownloadRecordInfoService;



@Service("appDownloadRecordInfoService")
public class AppDownloadRecordInfoServiceImpl implements AppDownloadRecordInfoService {
	@Autowired
	private AppDownloadRecordInfoDao appDownloadRecordInfoDao;
	
	@Override
	public AppDownloadRecordInfoEntity queryObject(Integer recordId){
		return appDownloadRecordInfoDao.queryObject(recordId);
	}
	
	@Override
	public List<AppDownloadRecordInfoEntity> queryList(Map<String, Object> map){
		return appDownloadRecordInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appDownloadRecordInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(AppDownloadRecordInfoEntity appDownloadRecordInfo){
		//判断用户是否已经下载过
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", appDownloadRecordInfo.getUserId());
		param.put("appVersionId", appDownloadRecordInfo.getAppVersionId());
		int count = appDownloadRecordInfoDao.queryTotalForApp(param);
		//若已经有下载记录则修改删除状态，否则进行添加操作
		if(count>0){
			appDownloadRecordInfoDao.updateDeleteYn(param);
		}else{
			appDownloadRecordInfoDao.save(appDownloadRecordInfo);
		}
		
	}
	
	@Override
	public void update(AppDownloadRecordInfoEntity appDownloadRecordInfo){
		appDownloadRecordInfoDao.update(appDownloadRecordInfo);
	}
	
	@Override
	public void delete(Map<String,Object> params){
		appDownloadRecordInfoDao.delete(params);
	}
	
	@Override
	public void deleteBatch(Integer[] recordIds){
		appDownloadRecordInfoDao.deleteBatch(recordIds);
	}
	
}
