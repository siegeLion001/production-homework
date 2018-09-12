package com.lhh.modules.cmtype.service;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmtype.entity.CmTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public interface CmTypeService {
	
	CmTypeEntity queryObject(Integer id);
	
	List<CmTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CmTypeEntity cmType);
	
	void update(CmTypeEntity cmType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<Option> queryOptions(Map<String, Object> map);

	Map<Integer,String> getTypeMap();

}
