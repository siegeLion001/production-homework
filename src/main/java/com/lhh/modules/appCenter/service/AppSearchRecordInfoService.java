package com.lhh.modules.appCenter.service;

import com.lhh.modules.appCenter.entity.AppSearchRecordInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-29 13:53:13
 */
public interface AppSearchRecordInfoService {
	
	AppSearchRecordInfoEntity queryObject(Integer appSearchRecordId);
	
	List<AppSearchRecordInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	AppSearchRecordInfoEntity queryOne(Map<String, Object> map);
	
	void save(AppSearchRecordInfoEntity appSearchRecordInfo);
	
	void update(AppSearchRecordInfoEntity appSearchRecordInfo);
	
	void delete(Integer appSearchRecordId);
	
	void deleteBatch(Integer[] appSearchRecordIds);
}
