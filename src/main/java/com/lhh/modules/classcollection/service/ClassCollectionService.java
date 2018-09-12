package com.lhh.modules.classcollection.service;

import com.lhh.modules.classcollection.entity.ClassCollectionEntity;
import com.lhh.modules.classtop.entity.ClassTopEntity;

import java.util.List;
import java.util.Map;

/**
 * 收藏表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-24 16:41:18
 */
public interface ClassCollectionService {
	
	ClassCollectionEntity queryObject(Integer collId);
	
	List<ClassCollectionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassCollectionEntity classCollection);
	
	void update(ClassCollectionEntity classCollection);
	
	void delete(Integer collId);
	
	void deleteBatch(Integer[] collIds);

	ClassCollectionEntity queryObjectByTopIdAndAuthId(Integer topId,String authId );

	List<ClassTopEntity> queryMyCollectionTop(Map<String, Object> map);

	int queryMyCollectionTopTotal(Map<String, Object> map);
}
