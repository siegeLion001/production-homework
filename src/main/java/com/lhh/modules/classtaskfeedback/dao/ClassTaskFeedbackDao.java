package com.lhh.modules.classtaskfeedback.dao;

import java.util.Map;

import com.lhh.modules.classtaskfeedback.entity.ClassTaskFeedbackEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
public interface ClassTaskFeedbackDao extends BaseDao<ClassTaskFeedbackEntity> {
    /**
     * 查询难易程度 任务量等的平均值
     * @name: queryAvgData
     * @params: [params]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: cuihp
     */
    Map<String,Object> queryAvgData(Map<String,Object> params);
    /**
     * 点评查询(优秀/良好/合格/不合格统计)
     * @name: queryRemark
     * @params: [params]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: cuihp
     */
    Map<String,Object> queryRemark(Map<String,Object> params);
}
