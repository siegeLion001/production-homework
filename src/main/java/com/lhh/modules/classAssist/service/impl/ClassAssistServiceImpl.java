package com.lhh.modules.classAssist.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.common.utils.FilePathUtil;
import com.lhh.common.utils.FileUtil;
import com.lhh.modules.classAssist.dao.ClassAssistDao;
import com.lhh.modules.classAssist.domain.ClassAssistVo;
import com.lhh.modules.classAssist.entity.ClassAssistEntity;
import com.lhh.modules.classAssist.service.ClassAssistService;
import com.lhh.modules.classtop.entity.TopContent;


@Service("classAssistService")
public class ClassAssistServiceImpl implements ClassAssistService {
	@Autowired
	private ClassAssistDao classAssistDao;
	
	@Override
	public ClassAssistEntity queryObject(Integer assId){
		return classAssistDao.queryObject(assId);
	}
	
	@Override
	public List<ClassAssistEntity> queryList(Map<String, Object> map){
		return classAssistDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classAssistDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassAssistEntity classAssist){
		classAssistDao.save(classAssist);
	}
	
	@Override
	public void update(ClassAssistEntity classAssist){
		classAssistDao.update(classAssist);
	}
	
	@Override
	public void delete(Integer assId){
		classAssistDao.delete(assId);
	}
	
	@Override
	public void deleteBatch(Integer[] assIds){
		classAssistDao.deleteBatch(assIds);
	}

	@Override
	public List<Map<String, Object>> assustMine(Map<String, Object> map) {
		List<ClassAssistVo> assistVoList = classAssistDao.assustMine(map);
		ArrayList<Map<String, Object>> maps = new ArrayList<>();
		for (ClassAssistVo classAssistVo : assistVoList) {
			HashMap<String, Object> resultMap = new HashMap<>();
			TopContent content = classAssistVo.getContent();
			resultMap.put("assId",classAssistVo.getAssId());
			resultMap.put("authId",classAssistVo.getAuthId());
			resultMap.put("createTime",classAssistVo.getCreateTime());
			resultMap.put("targetId",classAssistVo.getTargetId());
			resultMap.put("topId",classAssistVo.getTopId());
			resultMap.put("title",classAssistVo.getTitle());
			String[] imgs = content.getImgs();
			if(imgs !=null && imgs.length>0){
				String[] arrayReplacePlaceholder = FileUtil.getArrayReplacePlaceholder(imgs);
				resultMap.put("imgs",arrayReplacePlaceholder);
			}
			maps.add(resultMap);
		}
		return maps;
	}

}
