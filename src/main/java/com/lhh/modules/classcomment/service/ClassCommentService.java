package com.lhh.modules.classcomment.service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.entity.vo.ClassCommentVo;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:30
 */
public interface ClassCommentService {

    ClassCommentEntity queryObject(Integer id);

    List<ClassCommentEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ClassCommentEntity classComment);

    void update(ClassCommentEntity classComment);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<ClassCommentEntity> queryMyList(Map<String, Object> map);

    int queryMyTotal(Map<String, Object> map);
    List<ClassCommentVo> commentList (Map<String,Object> params);
}
