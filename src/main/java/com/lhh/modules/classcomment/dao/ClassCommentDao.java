package com.lhh.modules.classcomment.dao;

import java.util.List;
import java.util.Map;

import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.entity.vo.ClassCommentVo;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:30
 */
public interface ClassCommentDao extends BaseDao<ClassCommentEntity> {

    List<ClassCommentEntity> queryMyList(Map<String, Object> map);

    int queryMyTotal(Map<String, Object> map);
    List<ClassCommentVo> commentList (Map<String,Object> params);

}
