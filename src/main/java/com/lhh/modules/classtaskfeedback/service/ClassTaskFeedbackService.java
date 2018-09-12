package com.lhh.modules.classtaskfeedback.service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.classtaskfeedback.entity.ClassTaskFeedbackEntity;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
public interface ClassTaskFeedbackService {

    ClassTaskFeedbackEntity queryObject(Integer id);

    List<ClassTaskFeedbackEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ClassTaskFeedbackEntity classTaskFeedback);

    void update(ClassTaskFeedbackEntity classTaskFeedback);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);
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
