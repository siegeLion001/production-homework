package com.lhh.modules.interaction.service;

import com.lhh.modules.interaction.entity.InteractionNoticeCommentEntity;

import java.util.List;
import java.util.Map;

/**
 * 师生互动模块公告的评论信息表
 * 
 * @author menglimei
 * @email menglimei@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public interface InteractionNoticeCommentService {
	
	InteractionNoticeCommentEntity queryObject(Integer commentId);
	
	List<InteractionNoticeCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InteractionNoticeCommentEntity interactionNoticeComment);
	
	void update(InteractionNoticeCommentEntity interactionNoticeComment);
	
	void delete(Integer commentId);
	
	void deleteBatch(Integer[] commentIds);
}
