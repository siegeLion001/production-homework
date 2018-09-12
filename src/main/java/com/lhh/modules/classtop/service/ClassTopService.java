package com.lhh.modules.classtop.service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.classtop.entity.ClassTopEntity;
import com.lhh.modules.classtop.entity.vo.ClassTopVo;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
public interface ClassTopService {

    ClassTopEntity queryObject(Integer id);

    List<ClassTopEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ClassTopEntity classTop);

    void update(ClassTopEntity classTop);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    /**
     * 学生端 查询相关信息
     */
    List<ClassTopVo> queryTopListByTarget(Map<String, Object> map);

    int queryTopTotalByTarget(Map<String, Object> map);

    List<ClassTopVo> queryMyList(Map<String, Object> map);

    int queryMyTotal(Map<String, Object> map);


    boolean deleteTop(Integer id);
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
