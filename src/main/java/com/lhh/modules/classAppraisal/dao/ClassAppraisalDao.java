package com.lhh.modules.classAppraisal.dao;


import java.util.List;
import java.util.Map;

import com.lhh.modules.classAppraisal.entity.ClassAppraisalEntity;
import com.lhh.modules.classAppraisal.entity.ClassAppraisalVo;
import com.lhh.modules.classMessage.domain.MsgAndAppraisalVo;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 学生表扬批评记录表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 17:39:58
 */
public interface ClassAppraisalDao extends BaseDao<ClassAppraisalEntity> {
    /**
     * 查询被评论人的表扬次数,批评次数
     * @name: queryAppraise4stu 
     * @params: [param]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/10
     */
    Map<String,Object> queryAppraise4stu(Map<String,Object> param);
    /**
     * 根据学生id 学期查询该学生的表扬批评百分比   每种评价的次数
     * @name: queryAppraisalCount4Stu
     * @params: [params]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: cuihp
     * @Date: 2018/7/11
     */
    List<Map<String,Object>> queryAppraisalCount4Stu(Map<String,Object> params);

    List<ClassAppraisalVo> queryVoList(Map<String,Object> params);

    List<MsgAndAppraisalVo> appraisalList(Map<String,Object> params);

    List<ClassAppraisalVo> queryListNoChild(Map<String,Object> params);
    int queryTotalNoChild(Map<String,Object> params);
}
