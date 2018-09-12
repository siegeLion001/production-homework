package com.lhh.modules.classcollection.dao;

import com.lhh.modules.classcollection.entity.ClassCollectionEntity;
import com.lhh.modules.classtop.entity.ClassTopEntity;
import com.lhh.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 收藏表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-24 16:41:18
 */
public interface ClassCollectionDao extends BaseDao<ClassCollectionEntity> {

    /**
     * 根据 消息id和人员id确定一条操作记录
     * @param topId
     * @param authId
     * @return
     */
    ClassCollectionEntity queryObjectByTopIdAndAuthId(@Param("topId") Integer topId,@Param("authId")  String authId);

    List<ClassTopEntity> queryMyCollectionTop(Map<String, Object> map);

    int queryMyCollectionTopTotal(Map<String, Object> map);

}
