package com.lhh.modules.classAssist.service;


import java.util.List;
import java.util.Map;

import com.lhh.modules.classAssist.entity.ClassAssistEntity;

/**
 * 点赞表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-26 09:52:54
 */
public interface ClassAssistService {
	
	ClassAssistEntity queryObject(Integer assId);
	
	List<ClassAssistEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassAssistEntity classAssist);
	
	void update(ClassAssistEntity classAssist);
	
	void delete(Integer assId);
	
	void deleteBatch(Integer[] assIds);
	/**
	 * 赞我的人  及任务查询
	 * @name: assustMine 
	 * @params: [map]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: cuihp
	 * @Date: 2018/8/16
	 */
    List<Map<String,Object>> assustMine(Map<String,Object> map);
}
