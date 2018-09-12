package com.lhh.modules.cmtextcontent.service;

import com.lhh.modules.cmtextcontent.entity.CmTextContentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public interface CmTextContentService {
	
	CmTextContentEntity queryObject(Integer id);
	
	List<CmTextContentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CmTextContentEntity cmTextContent);
	
	void update(CmTextContentEntity cmTextContent);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
