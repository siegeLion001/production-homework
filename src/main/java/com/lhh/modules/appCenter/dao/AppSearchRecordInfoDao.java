package com.lhh.modules.appCenter.dao;

import java.util.Map;

import com.lhh.modules.appCenter.entity.AppSearchRecordInfoEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-29 13:53:13
 */
public interface AppSearchRecordInfoDao extends BaseDao<AppSearchRecordInfoEntity> {

	AppSearchRecordInfoEntity queryOne(Map<String, Object> map);
	
}
