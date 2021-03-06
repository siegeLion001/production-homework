package com.lhh.modules.appCenter.dao;

import java.util.List;
import java.util.Map;

import com.lhh.modules.appCenter.entity.AppCommentInfoEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 应用评论信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppCommentInfoDao extends BaseDao<AppCommentInfoEntity> {

	List<AppCommentInfoEntity> list(Map<String, Object> params);
	
}
