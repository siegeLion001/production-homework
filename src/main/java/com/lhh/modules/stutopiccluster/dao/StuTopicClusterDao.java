package com.lhh.modules.stutopiccluster.dao;

import java.util.List;
import java.util.Map;

import com.lhh.modules.stutopiccluster.domain.StuTopicClusterExcellentVo;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface StuTopicClusterDao extends BaseDao<StuTopicClusterEntity> {

    List<StuTopicClusterVo> queryVoList(Map<String, Object> params);

    StuTopicClusterVo queryObjectVo(Object id);

    void delete4Map(Map<String, Object> params);

    int queryCount4Map(Map<String, Object> params);

    /**
     * 优秀作业二级列表  返回带有学生id数组的评优大题
     * @param params
     * @return
     */
    List<StuTopicClusterExcellentVo> excellentStuTopicCluster(Map<String, Object> params);

    List<StuTopicClusterVo> queryList4VoJoinStu(Map<String,Object> params);
}
