package com.lhh.modules.appCenter.service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.appCenter.entity.AppInfoEntity;
import com.lhh.modules.appCenter.entity.AppInfoModel;

/**
 * 应用信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppInfoService {
	
	AppInfoEntity queryObject(Integer appId);
	
	List<AppInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppInfoEntity appInfo);
	
	void update(AppInfoEntity appInfo);
	
	void delete(Integer appId);
	
	void deleteBatch(Integer[] appIds);

	List<AppInfoModel> queryAppList(Map<String, Object> map);
}
