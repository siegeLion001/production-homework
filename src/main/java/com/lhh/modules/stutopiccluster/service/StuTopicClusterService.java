package com.lhh.modules.stutopiccluster.service;

import com.lhh.modules.stutopiccluster.domain.StuTopicClusterExcellentVo;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;

import java.util.List;
import java.util.Map;

/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface StuTopicClusterService {

    StuTopicClusterEntity queryObject(Integer id);

    List<StuTopicClusterEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(StuTopicClusterEntity stuTopicCluster);

    void update(StuTopicClusterEntity stuTopicCluster);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<StuTopicClusterVo> queryVoList(Map<String, Object> params);

    void delete4Map(Map<String, Object> params);

    int queryCount4Map(Map<String, Object> params);

    List<StuTopicClusterVo> queryTopicClusterAllfield(Map<String, Object> params);

    /**
     * 优秀作业二级列表  返回带有学生id数组的评优大题
     *
     * @param params
     * @return
     */
    List<StuTopicClusterExcellentVo> excellentStuTopicCluster(Map<String, Object> params);

}
