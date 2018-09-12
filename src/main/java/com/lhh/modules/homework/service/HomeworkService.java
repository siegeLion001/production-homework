package com.lhh.modules.homework.service;

import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.homework.domain.ExcellentHomeworkVo;
import com.lhh.modules.homework.domain.HomeworkVo;
import com.lhh.modules.homework.domain.InsertHomeWorkVo;
import com.lhh.modules.homework.entity.HomeworkEntity;

import java.util.List;
import java.util.Map;

/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface HomeworkService {
    HomeworkEntity queryObject(Integer id);

    List<HomeworkEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(HomeworkEntity homework);

    void update(HomeworkEntity homework);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<InsertHomeWorkVo> queryList4Map(Map<String, Object> params);

    HomeworkEntity queryContent(Integer homeworkId);

    int queryTotal4Map(Map<String, Object> params);

    R queryArchive4State(Query query);

    /**
     * @Description: 查询一段时间内学生作业的完成率 及 老师布置作业的类型数量   教师端 根据tchId查询
     * @Name: queryHomeworkInfo2Map
     * @Params: [params]
     * @Author: cuihp
     * @Date: 2018/6/8
     */
    Map<String, Object> queryHomeworkInfo2Map(Map<String, Object> params);

    /**
     * @Description: 查询某段时间内学生提交作业的状态   教师端 根据tchId查询
     * @Name: queryStuSubmitCount
     * @Params: [params]
     * @Author: cuihp
     * @Date: 2018/6/8
     */
    List<Map<String, Object>> queryStuSubmitCount(Map<String, Object> params);

    /**
     * @Description: 学生报告查询
     * @Name: queryStuReport2Map
     * @Params: [params]
     * @return: java.util.List<java.util.Map>
     * @Author: cuihp
     * @Date: 2018/6/11
     */
    List<Map> queryStuReport2Map(Map<String, Object> params);

    HomeworkVo queryObjectVo(Integer id);

    /**
     * 查询作业详情  包含大题 小题 结构 不包括统计出的数据
     *
     * @param subjective  null不筛选 1 只要主管题 2 只要客观题
     * @param homeworkId
     * @return
     */
    HomeworkVo queryHomeworkDetail(Integer homeworkId, Boolean subjective);

    /**
     * 优秀作业
     */

    List<ExcellentHomeworkVo> excellentHomework(Map<String, Object> params);

    int excellentHomeworkTotal(Map<String, Object> params);

	List<HomeworkEntity> draftsList(Map<String, Object> params);

}
