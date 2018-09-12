package com.lhh.modules.stuhomework.service;

import com.lhh.modules.stuhomework.domain.StuHomeworkVo;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;

import java.util.List;
import java.util.Map;

/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface StuHomeworkService {

    StuHomeworkEntity queryObject(Integer id);

    List<StuHomeworkEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(StuHomeworkEntity stuHomework);

    void update(StuHomeworkEntity stuHomework);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<StuHomeworkVo> queryListByWhere(Map<String, Object> map);

    int queryListByWhereTotal(Map<String, Object> map);

    void saveTopics(List<StuTopicVo> stuTopicEntityList, StuTopicClusterVo stuTopicClusterEntity);

    void updateTopics(List<StuTopicVo> stuTopicEntityList, StuTopicClusterVo stuTopicClusterEntity);

    StuHomeworkVo queryStuHomeworkVo(Map<String, Object> map);

    /**
     * 批量插入
     *
     * @param stuHomeworkList
     * @author cuihp
     */
    void batchSave(List<StuHomeworkEntity> stuHomeworkList);

    void delete4Map(Map<String, Object> params);

    /**
     * @Description: 根据条件查询total  关联查
     * @Name: querTotal4Map
     * @Params: [params]
     * @return: int
     * @Author: cuihp
     * @Date: 2018/6/7
     */
    int querTotal4Map(Map<String, Object> params);

    /**
     * @Description: 根据条件查询学生  关联查
     * @Name: queryStu4Map
     * @Params: [params]
     * @return: java.util.List<com.lhh.modules.stuhomework.domain.StuHomeworkVo>
     * @Author: cuihp
     * @Date: 2018/6/7
     */
    List<StuHomeworkVo> queryStu4Map(Map<String, Object> params);

    /**
     * @Description: 查询作业平均用时  (已做学生)
     * @Name: queryTimeKeep4Homework
     * @Params: [params]
     * @return: java.lang.Long
     * @Author: cuihp
     * @Date: 2018/6/13
     */
    Long queryTimeKeep4Homework(Map<String, Object> params);

    /**
     * 作业未交 已交人数统计
     *
     * @Name: queryStuStat
     * @Params: [params]
     * @Author: cuihp
     * @Date: 2018/6/13
     */
    Map<String, Object> queryStuStat(Map<String, Object> params);

    List<StuHomeworkEntity> queryList4StuAndStuTopic(Map<String, Object> params);

    boolean allHomeworkIsTchYesCorrect(Integer homeworkId);

    /**
     * 统计某个学生作业总分
     */
    Float getActualScore(String stuId, Integer homeworkId);

    /**
     * 计算班级最高分 班级平均分 班级平均用时 满分
     *
     * @return
     */
    Map numStatistics(String classId, int homeworkId);

    /**
     * 口语评测查询排名
     * homeworkId, classId
     *
     * @name: queryOralReviewsRank
     * @params: [params]
     * @return: java.util.List<java.util.Map                               <                               java.lang.String                               ,                               java.lang.Object>>
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
     * @return: java.util.Map<java.lang.String                               ,                               java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/23
     */
    Map<String, Object> queryOralReviewsScore(Map<String, Object> params);

    /**
     * 查询及格,不及格,优秀,良好  百分比
     *
     * @name: queryOralReviewsCount
     * @params: [params]
     * @return: java.util.Map<java.lang.String                               ,                               java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/23
     */
    Map<String, Object> queryOralReviewsRate(Map<String, Object> params);

    /**
     * 用于 按题批改
     * 每个小题或大题携带的学生作业信息 主要使用学生id
     * <p>
     * 查询条件  homeworkId  班级id
     * 学生宏观状态 为 已做
     *
     * @return
     */
    List<StuHomeworkEntity> queryAllSthwByHidAmdCid(Integer homeworkId, String classId);

    /**
     * 根据作业id 和 学生id 查找唯一的学生作业
     */
    StuHomeworkEntity queryByHomeworkIdAndStuId(Integer homeworkId, String stuId);

    /**
     * 查询所有学生id
     */
    List<String> selectAllStuId(Map<String, Object> params);

    /**
     * 批量更新
     */
    void batchUpdate(List<? extends StuHomeworkEntity> stuHomeworkEntities);

}
