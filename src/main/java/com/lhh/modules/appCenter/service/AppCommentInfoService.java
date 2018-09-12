package com.lhh.modules.appCenter.service;

import com.lhh.modules.appCenter.entity.AppCommentInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 应用评论信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public interface AppCommentInfoService {
	
	AppCommentInfoEntity queryObject(Integer commentId);
	
	List<AppCommentInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppCommentInfoEntity appCommentInfo);
	
	void update(AppCommentInfoEntity appCommentInfo);
	
	void delete(Integer commentId);
	
	void deleteBatch(Integer[] commentIds);

	List<AppCommentInfoEntity> list(Map<String, Object> params);
}
