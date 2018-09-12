package com.lhh.modules.classAssist.dao;


import java.util.List;
import java.util.Map;

import com.lhh.modules.classAssist.domain.ClassAssistVo;
import com.lhh.modules.classAssist.entity.ClassAssistEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 点赞表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-26 09:52:54
 */
public interface ClassAssistDao extends BaseDao<ClassAssistEntity> {

    List<ClassAssistVo> assustMine(Map<String,Object> map);
}
