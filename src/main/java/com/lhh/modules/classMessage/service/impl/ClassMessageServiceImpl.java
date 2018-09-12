package com.lhh.modules.classMessage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.modules.classAppraisal.service.ClassAppraisalService;
import com.lhh.modules.classMessage.dao.ClassMessageDao;
import com.lhh.modules.classMessage.domain.ClassMessageVo;
import com.lhh.modules.classMessage.domain.MsgAndAppraisalVo;
import com.lhh.modules.classMessage.entity.ClassMessageEntity;
import com.lhh.modules.classMessage.service.ClassMessageService;
import com.sun.org.apache.bcel.internal.generic.NEW;


@Service("classMessageService")
public class ClassMessageServiceImpl implements ClassMessageService {
	@Autowired
	private ClassMessageDao classMessageDao;
	@Autowired
	private ClassAppraisalService classAppraisalService;
	
	@Override
	public ClassMessageEntity queryObject(Integer msgId){
		return classMessageDao.queryObject(msgId);
	}
	
	@Override
	public List<ClassMessageEntity> queryList(Map<String, Object> map){
		return classMessageDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classMessageDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassMessageEntity classMessage){
		classMessageDao.save(classMessage);
	}
	
	@Override
	public void update(ClassMessageEntity classMessage){
		classMessageDao.update(classMessage);
	}
	
	@Override
	public void delete(Integer msgId){
		classMessageDao.delete(msgId);
	}
	
	@Override
	public void deleteBatch(Integer[] msgIds){
		classMessageDao.deleteBatch(msgIds);
	}

	@Override
	public List<ClassMessageVo> queryMsgDetails(Map<String, Object> params) {

		List<ClassMessageVo> classMessageVoList = queryVo4ListNotParent(params);
		for (ClassMessageVo classMessageVo : classMessageVoList) {
			Integer msgId = classMessageVo.getMsgId();
			Map<String, Object> map = new HashMap<>();
			map.put("parentId", msgId);
			List<ClassMessageVo> classMessageVos = queryMsgList(map);
			classMessageVo.setMsgList(classMessageVos);
		}
		return classMessageVoList;
	}

	@Override
	public List<ClassMessageVo> queryMsgList(Map<String, Object> params) {
		return classMessageDao.queryMsgList(params);
	}

	@Override
	public List<ClassMessageVo> queryVo4ListNotParent(Map<String, Object> params) {
		return classMessageDao.queryVo4ListNotParent(params);
	}

	@Override
	public List<MsgAndAppraisalVo> msgAndAppraisalList(Map<String, Object> params) {
		return classMessageDao.msgAndAppraisalList(params);
	}

	@Override
	public int msgAndAppraisalCount(Map<String, Object> params) {
		return classMessageDao.msgAndAppraisalCount(params);
	}

	@Override
	public List<MsgAndAppraisalVo> messages(Map<String, Object> params) {
		List<MsgAndAppraisalVo> msgAndAppraisalVos = msgAndAppraisalList(params);
		for (MsgAndAppraisalVo msgAndAppraisalVo : msgAndAppraisalVos) {
			Integer state = msgAndAppraisalVo.getState();
			Integer id = msgAndAppraisalVo.getId();
			params.put("parentId",id);

			if(null !=state && 0 != state){
				List<MsgAndAppraisalVo> msgAndAppraisalVos1 = classAppraisalService.appraisalList(params);
				msgAndAppraisalVo.setChildList(msgAndAppraisalVos1);
			}else if (0 == state){
				List<MsgAndAppraisalVo> msgAndAppraisalVos2 = classMessageDao.msgList2Child(params);
				msgAndAppraisalVo.setChildList(msgAndAppraisalVos2);
			}
		}

		return msgAndAppraisalVos;
	}

	@Test
	public void Test(){
		Map<String, Object> map = new HashMap<>();
		map.put("authId","5b2e3a149553145b13df22ea");
		List<MsgAndAppraisalVo> messages = messages(map);
		System.out.println(messages.toString());
	}
}
