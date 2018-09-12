package com.lhh.modules.interaction.service;

import com.lhh.modules.interaction.domain.NoticeListVo;
import com.lhh.modules.interaction.entity.InteractionNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 师生互动模块的公告信息表
 * 
 * @author menglimei
 * @email menglimei@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public interface InteractionNoticeService {
	
	InteractionNoticeEntity queryObject(Integer noticeId);
	
	List<NoticeListVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InteractionNoticeEntity interactionNotice);
	
	void update(InteractionNoticeEntity interactionNotice);
	
	void delete(Integer noticeId);
	
	void deleteBatch(Integer[] noticeIds);
}
