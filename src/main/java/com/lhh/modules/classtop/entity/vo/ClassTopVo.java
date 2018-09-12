package com.lhh.modules.classtop.entity.vo;

import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classtop.entity.ClassTopEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
public class ClassTopVo extends ClassTopEntity {

    /**
     * 当前用户是否收藏
     */
    private  Boolean collection;

    List<ClassCommentEntity> classComments = new ArrayList<>();

    public List<ClassCommentEntity> getClassComments() {
        return classComments;
    }

    public void setClassComments(List<ClassCommentEntity> classComments) {
        this.classComments = classComments;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }
}
