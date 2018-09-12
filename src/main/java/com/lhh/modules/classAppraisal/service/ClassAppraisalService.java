package com.lhh.modules.classAppraisal.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lhh.modules.classAppraisal.entity.ClassAppraisalEntity;
import com.lhh.modules.classAppraisal.entity.ClassAppraisalVo;
import com.lhh.modules.classMessage.domain.MsgAndAppraisalVo;

/**
 * 学生表扬批评记录表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 17:39:58
 */
public interface ClassAppraisalService {
	
	ClassAppraisalEntity queryObject(Integer aprId);
	
	List<ClassAppraisalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassAppraisalEntity classAppraisal);
	
	void update(ClassAppraisalEntity classAppraisal);
	
	void delete(Integer aprId);
	
	void deleteBatch(Integer[] aprIds);
	/**
	 * 花名册列表页查询
	 * @name: roster
	 * @params: [params]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/10
	 */
    List<Map> roster(Map<String,Object> params) throws IOException;
	/**
	 * 根据学生id 学期查询该学生的表扬批评百分比   每种评价的次数
	 * @name: queryAppraisalCount4Stu
	 * @params: [params]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 * @Author: cuihp
	 * @Date: 2018/7/11
	 */
	Map<String,Object> queryAppraisalCount4Stu (Map<String,Object> params);

    void saveBatch(ArrayList<ClassAppraisalEntity> appraisalList);
	List<ClassAppraisalVo> queryVoList(Map<String,Object> params);
	List<ClassAppraisalVo> queryAppraisals(Map<String,Object> params);


	List<MsgAndAppraisalVo> appraisalList(Map<String,Object> params);
	List<ClassAppraisalVo> queryListNoChild(Map<String,Object> params);
	int queryTotalNoChild(Map<String,Object> params);
}
