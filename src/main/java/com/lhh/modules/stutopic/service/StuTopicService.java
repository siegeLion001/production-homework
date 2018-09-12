package com.lhh.modules.stutopic.service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopic.domain.WYStatisticsMap;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;

public interface StuTopicService {
    StuTopicEntity queryObject(Integer id);

    List<StuTopicEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(StuTopicEntity stuTopic);

    void update(StuTopicEntity stuTopic);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    void batchSave(List<? extends StuTopicEntity> stuTopicEntityList);

    /**
     * 根据大题号和小题号更新
     *
     * @param stuTopicEntityList
     */
    void batchUpdate(List<? extends StuTopicEntity> stuTopicEntityList);

    List<StuTopicVo> queryList4Vo(Map<String, Object> map);

    /**
     * @Description: 学生观测台   成绩排名
     * @Name: queryRank4Map
     * @Params: [params]
     * @Author: cuihp
     * @Date: 2018/6/3
     */
    List<Map<String, Object>> queryRank4Map(Map<String, Object> params);

    /**
     * @Description: 未批改/批改人数查询
     * @Name: queryCount4Group
     * @Params: [params]
     * @return: java.util.List<TopicVo>
     * @Author: cuihp
     * @Date: 2018/5/31
     */
    List<TopicVo> queryCount4Group(Map<String, Object> params);

    List<Map> queryCountByGroup4Map(Map<String, Object> params);

    /**
     * @Description: 查询每个大题的批改状态  test
     * @Name: queryTotalByMap
     * @Params: [params]
     * @return: int
     * @Author: cuihp
     * @Date: 2018/5/31
     */
    int queryTotalByMap(Map<String, Object> params);

    void delete4Map(Map<String, Object> params);

    /**
     * @Description: 教师端 统计作业满分 平均分 最高分 最低分
     * @Name: queryScore4Homework
     * @Params: [params]
     * @Author: cuihp
     * @Date: 2018/6/13
     */
    Map<String, Object> queryScore4Homework(Map<String, Object> params);

    /**
     * @Description: 查询作业优秀/良好/及格/不及格人数
     * @Name: queryScoring
     * @Params: [params]  homeowrkId classId  作业类型查询需要 typeList list<int>
     * @Author: cuihp
     * @Date: 2018/6/13
     */
    Map<String, Object> queryScoring(Map<String, Object> params);

    /**
     * 教师端 作业分析 -> 掌握情况 -> 成绩排名
     *
     * @Name: queryStuScoreRank
     * @Params: [params]
     * @return: java.util.List<java.util.Map>
     * @Author: cuihp
     * @Date: 2018/6/14
     */
    List<Map> queryStuScoreRank(Map<String, Object> params);

    /**
     * @Description: 作业分析 -掌握情况 -答题情况
     * @Name: queryAnswerIS
     * @Params: [params]
     * @return: java.util.List<java.util.Map>
     * @Author: cuihp
     * @Date: 2018/6/14
     */
    List<TopicClusterVo> queryAnswerIS(Map<String, Object> params);

    Map<String, Object> queryStu4Map(Map<String, Object> params);

    int queryCount4Map(Map<String, Object> params);

    /**
     * 统计出大题 小题 未批改 已批改的情况
     */
    WYStatisticsMap wyStatistics(String bNum,String classId, int homeworkId);
    /**
     * 统计出大题 小题 教师订正 学生批改的情况
     */
    WYStatisticsMap wyStatisticsRevisal(String bNum,String classId, int homeworkId);


    /**
     * 该学生某个作业是否有没批改的
     *
     * @return
     */
    Boolean haveNoMark(Map<String, Object> params);

    /**
     * 该学生某个作业是否有批改的
     *
     * 未批改的作业存在两种情况
     * 1 真的没批改 re false
     * 2 批改中 re true
     * 区分这两种状态 需要根据 这个学生作业下 所有的题目中是否包含批改的题目来确定
     * 所以需要用这个方法
     * @return
     */
    Boolean haveMark(Map<String, Object> params);
    /**
     * 判断是否有订正的题目
     */
    Boolean haveRevisal(Map<String, Object> params);
}
