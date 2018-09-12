package com.lhh.modules.interaction.dao;

import java.util.List;
import java.util.Map;

import com.lhh.modules.interaction.domain.NoticeListVo;
import com.lhh.modules.interaction.entity.InteractionNoticeEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 师生互动模块的公告信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public interface InteractionNoticeDao extends BaseDao<InteractionNoticeEntity> {
	List<NoticeListVo> queryTchList(Map<String, Object> map);
}
