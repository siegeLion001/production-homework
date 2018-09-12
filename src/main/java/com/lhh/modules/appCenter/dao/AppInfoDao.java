package com.lhh.modules.appCenter.dao;

import java.util.List;
import java.util.Map;

import com.lhh.modules.appCenter.entity.AppInfoEntity;
import com.lhh.modules.appCenter.entity.AppInfoModel;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 应用信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppInfoDao extends BaseDao<AppInfoEntity> {

	List<AppInfoModel> queryAppList(Map<String, Object> map);
	
}
