package com.lhh.modules.classMessage.dao;


import java.util.List;
import java.util.Map;

import com.lhh.modules.classMessage.domain.ClassMessageVo;
import com.lhh.modules.classMessage.domain.MsgAndAppraisalVo;
import com.lhh.modules.classMessage.entity.ClassMessageEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-12 09:37:33
 */
public interface ClassMessageDao extends BaseDao<ClassMessageEntity> {

    List<ClassMessageVo> queryMsgList(Map<String,Object> params);
    List<ClassMessageVo> queryVo4ListNotParent(Map<String,Object> params);
    List<MsgAndAppraisalVo> msgAndAppraisalList(Map<String,Object> params);
    int msgAndAppraisalCount(Map<String,Object> params);
    List<MsgAndAppraisalVo> msgList2Child(Map<String,Object> params);

}
