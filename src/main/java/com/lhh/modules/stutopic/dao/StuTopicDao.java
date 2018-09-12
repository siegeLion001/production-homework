package com.lhh.modules.stutopic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhh.modules.stutopic.domain.CorrectAndErrorCount;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopic.domain.WYStatistics;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.sys.dao.BaseDao;
import com.lhh.modules.topic.domain.TopicVo;

/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface StuTopicDao extends BaseDao<StuTopicEntity> {

    void batchSave(List<? extends StuTopicEntity> stuTopicEntityList);

    void batchUpdate(List<? extends StuTopicEntity> stuTopicEntityList);

    int queryTotalByMap(Map<String, Object> params);

    List<TopicVo> queryCount4Group(Map<String, Object> params);

    List<Map> queryCountByGroup4Map(Map<String, Object> params);

    List<StuTopicVo> queryList4Vo(Map<String, Object> params);

    List<Map<String, Object>> queryRank4Map(Map<String, Object> params);

    void delete4Map(Map<String, Object> params);

    Map<String, Object> queryScore4Homework(Map<String, Object> params);

    List<Map<String,Object>> queryScoring(Map<String, Object> params);

    List<Map> queryStuScoreRank(Map<String, Object> params);

    List<TopicVo> queryAnswerIS(Map<String, Object> params);

    int queryCount4Map(Map<String, Object> params);

    // 班级错题本 计算正确率错误率
    List<CorrectAndErrorCount> correctAndErrorCount(Map<String, Object> params);

    /**
     * 统计大题下 小题的批改情况
     *
     * @return
     */

    List<WYStatistics> wyStatistics(@Param("bNum") String bNum,@Param("classId") String classId, @Param("homeworkId") int homeworkId);

    /**
     * 统计教师打成订正 学生订正的情况
     * @param bNum
     * @param homeworkId
     * @return
     */
    List<WYStatistics> wyStatisticsRevisal(@Param("bNum") String bNum,@Param("classId") String classId, @Param("homeworkId") int homeworkId);

}
