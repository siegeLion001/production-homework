package com.lhh.modules.stuhomework.dao;

import com.lhh.modules.stuhomework.domain.StuHomeworkVo;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface StuHomeworkDao extends BaseDao<StuHomeworkEntity> {

    List<StuHomeworkVo> queryListByWhere(Map<String, Object> map);

    int queryListByWhereTotal(Map<String, Object> map);

    StuHomeworkVo queryStuHomeworkVo(Map<String, Object> map);

    void delete4Map(Map<String, Object> params);

    int querTotal4Map(Map<String, Object> params);

    List<StuHomeworkVo> queryStu4Map(Map<String, Object> params);

    Long queryTimeKeep4Homework(Map<String, Object> params);

    Map<String, Object> queryStuStat(Map<String, Object> params);

    List<StuHomeworkEntity> queryList4StuAndStuTopic(Map<String, Object> params);

    // 根据教师宏观状态分组
    List<Map<Integer, Integer>> groupByTchMacroStatus(Integer homeworkId);

    //统计某个学生某次作业总分数
    Float getActualScore(Map<String, Object> map);

    /**
     * 计算班级最高分 班级平均分 班级平均用时
     *
     * @return
     */
    Map numStatistics(@Param("classId") String classId, @Param("homeworkId") int homeworkId);

    /**
     * 口语评测查询排名
     * homeworkId, classId
     *
     * @name: queryOralReviewsRank
     * @params: [params]
     * @return: java.util.List<java.util.Map   <   java.lang.String   ,   java.lang.Object>>
     * @Author: cuihp
     * @Date: 2018/7/23
     */
    List<Map<String, Object>> queryOralReviewsRank(Map<String, Object> params);

    /**
     * 口语评测  平均分, 最高分, 最低分, 满分, 平均用时 查询
     * homeworkId, classId
     *
     * @name: queryOralReviewsScore
     * @params: [params]
     * @return: java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/23
     */
    Map<String, Object> queryOralReviewsScore(Map<String, Object> params);

    /**
     * 根据参数 查询 作业下所有学生
     * @param params
     * @return
     */
    List<String> selectAllStuId(Map<String, Object> params);

    /**
     * 批量更新
     */
    void batchUpdate(List<? extends StuHomeworkEntity> stuHomeworkEntities);
}
