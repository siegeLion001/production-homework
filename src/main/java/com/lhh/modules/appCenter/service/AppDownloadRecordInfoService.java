package com.lhh.modules.appCenter.service;

import com.lhh.modules.appCenter.entity.AppDownloadRecordInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 应用下载记录
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppDownloadRecordInfoService {
	
	AppDownloadRecordInfoEntity queryObject(Integer recordId);
	
	List<AppDownloadRecordInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppDownloadRecordInfoEntity appDownloadRecordInfo);;
	
	void update(AppDownloadRecordInfoEntity appDownloadRecordInfo);
	
	void delete(Map<String,Object> params);
	
	void deleteBatch(Integer[] recordIds);
}
