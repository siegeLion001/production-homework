package com.lhh.modules.classcomment.entity.vo;

import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classtop.entity.TopContent;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-13 09:18:40
 */
public class ClassCommentVo extends ClassCommentEntity {

    private TopContent topContent;

    public TopContent getTopContent() {
        return topContent;
    }

    public void setTopContent(TopContent topContent) {
        this.topContent = topContent;
    }
}
