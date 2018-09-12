package com.lhh.modules.classtop.dao;

import java.util.List;
import java.util.Map;

import com.lhh.modules.classtop.entity.ClassTopEntity;
import com.lhh.modules.classtop.entity.vo.ClassTopVo;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
public interface ClassTopDao extends BaseDao<ClassTopEntity> {

    // 学生端查询跟自己相关的 内容
    List<ClassTopVo> queryTopListByTarget(Map<String, Object> map);

    int queryTopTotalByTarget(Map<String, Object> map);

    /**
     * 带有提交/阅读数量的列表查询
     * @param map
     * @return
     */
    List<ClassTopVo> queryMyList(Map<String, Object> map);

    int queryMyTotal(Map<String, Object> map);
    /**
     * 动态数,已赞,被赞,以评人数统计
     * @name: queryInformationCount
     * @params: [params]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/19
     */
    Map<String,Object> queryInformationCount(Map<String,Object> params);

}
