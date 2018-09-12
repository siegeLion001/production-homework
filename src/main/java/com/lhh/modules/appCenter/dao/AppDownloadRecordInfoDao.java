package com.lhh.modules.appCenter.dao;

import java.util.Map;

import com.lhh.modules.appCenter.entity.AppDownloadRecordInfoEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 应用下载记录
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppDownloadRecordInfoDao extends BaseDao<AppDownloadRecordInfoEntity> {

	int queryTotalForApp(Map<String, Object> param);

	void updateDeleteYn(Map<String, Object> param);
	
}
