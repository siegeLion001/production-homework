package com.lhh.modules.homework.dao;

import com.lhh.modules.homework.domain.ExcellentHomeworkVo;
import com.lhh.modules.homework.domain.HomeworkVo;
import com.lhh.modules.homework.domain.InsertHomeWorkVo;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.sys.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface HomeworkDao extends BaseDao<HomeworkEntity> {
    List<InsertHomeWorkVo> queryList4Map(Map<String, Object> params);

    HomeworkEntity queryContent(Integer homeworkId);

    int queryTotal4Map(Map<String, Object> params);

    Map<String, Object> queryComplete4Tch(Map<String, Object> params);

    /*
     * @Description: 根据类型统计作业数量
     * @Name: queryHomeworkCount4Type
     * @Params: [params]
     * @return: java.util.List<java.util.Map>
     * @Author: cuihp
     * @Date: 2018/6/8
     */
    List<Map> queryHomeworkCount4Type(Map<String, Object> params);

    List<Map<String, Object>> queryStuSubmitCount(Map<String, Object> params);

    List<Map> queryStuReport2Map(Map<String, Object> params);

    HomeworkVo queryObjectVo(Integer homeworkId);

    /**
     * 计算总分
     *
     * @return
     */
    Double fullScore(Integer homeworkId);

    /**
     * 优秀作业
     */

    List<ExcellentHomeworkVo> excellentHomework(Map<String, Object> params);

    int excellentHomeworkTotal(Map<String, Object> params);

	List<HomeworkEntity> draftsList(Map<String, Object> params);


}
