package com.lhh.modules.interaction.dao;

import java.util.List;

import com.lhh.modules.interaction.entity.InteractionStuNoticeEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 记录已读，未读信息
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public interface InteractionStuNoticeDao extends BaseDao<InteractionStuNoticeEntity> {

	void batchSave(List<InteractionStuNoticeEntity> stuNoticeEntitys);
	
}
