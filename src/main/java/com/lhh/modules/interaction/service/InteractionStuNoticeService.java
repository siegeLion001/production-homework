package com.lhh.modules.interaction.service;

import com.lhh.modules.interaction.entity.InteractionStuNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 记录已读，未读信息
 * 
 * @author menglimei
 * @email menglimei@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public interface InteractionStuNoticeService {
	
	InteractionStuNoticeEntity queryObject(Integer stuNoticeId);
	
	List<InteractionStuNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InteractionStuNoticeEntity interactionStuNotice);
	
	void update(InteractionStuNoticeEntity interactionStuNotice);
	
	void delete(Integer stuNoticeId);
	
	void deleteBatch(Integer[] stuNoticeIds);

	void batchSave(List<InteractionStuNoticeEntity> stuNoticeEntitys);
}
