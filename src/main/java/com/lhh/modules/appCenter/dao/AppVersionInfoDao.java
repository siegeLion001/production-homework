package com.lhh.modules.appCenter.dao;

import java.util.List;
import java.util.Map;

import com.lhh.modules.appCenter.entity.AppVersionInfoEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 应用版本信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppVersionInfoDao extends BaseDao<AppVersionInfoEntity> {

	List<AppVersionInfoEntity> getApkVersion(Map<String, Object> params);
	
}
