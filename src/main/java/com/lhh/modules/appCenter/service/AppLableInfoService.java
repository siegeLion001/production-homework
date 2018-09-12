package com.lhh.modules.appCenter.service;

import com.lhh.modules.appCenter.entity.AppLableInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 应用标签信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppLableInfoService {
	
	AppLableInfoEntity queryObject(Integer labeId);
	
	List<AppLableInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppLableInfoEntity appLableInfo);
	
	void update(AppLableInfoEntity appLableInfo);
	
	void delete(Integer labeId);
	
	void deleteBatch(Integer[] labeIds);
}
