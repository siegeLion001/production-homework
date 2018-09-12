package com.lhh.modules.appCenter.service;

import com.lhh.modules.appCenter.entity.AppVersionInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 应用版本信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppVersionInfoService {
	
	AppVersionInfoEntity queryObject(Integer appVersionId);
	
	List<AppVersionInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppVersionInfoEntity appVersionInfo);
	
	void update(AppVersionInfoEntity appVersionInfo);
	
	void delete(Integer appVersionId);
	
	void deleteBatch(Integer[] appVersionIds);

	List<AppVersionInfoEntity> getApkVersion(Map<String, Object> params);
}
