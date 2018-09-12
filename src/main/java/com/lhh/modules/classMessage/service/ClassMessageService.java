package com.lhh.modules.classMessage.service;


import java.util.List;
import java.util.Map;

import com.lhh.modules.classMessage.domain.ClassMessageVo;
import com.lhh.modules.classMessage.domain.MsgAndAppraisalVo;
import com.lhh.modules.classMessage.entity.ClassMessageEntity;

/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-12 09:37:33
 */
public interface ClassMessageService {
	
	ClassMessageEntity queryObject(Integer msgId);
	
	List<ClassMessageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassMessageEntity classMessage);
	
	void update(ClassMessageEntity classMessage);
	
	void delete(Integer msgId);
	
	void deleteBatch(Integer[] msgIds);
	/**
	 * 寄语详情(带有回复)
	 * @name: queryMsgDetails
	 * @params: [params]
	 * @return: com.lhh.modules.classMessage.domain.ClassMessageVo
	 * @Author: cuihp
	 * @Date: 2018/8/18
	 */
	List<ClassMessageVo> queryMsgDetails(Map<String,Object> params);
	List<ClassMessageVo> queryMsgList(Map<String,Object> params);
	List<ClassMessageVo> queryVo4ListNotParent(Map<String,Object> params);
	List<MsgAndAppraisalVo> msgAndAppraisalList(Map<String,Object> params);
	int msgAndAppraisalCount(Map<String,Object> params);

	List<MsgAndAppraisalVo> messages(Map<String, Object> params);
}
