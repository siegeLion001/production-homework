package com.lhh.modules.classAssist.domain;

import com.lhh.modules.classAssist.entity.ClassAssistEntity;
import com.lhh.modules.classtop.entity.TopContent;

/**
 * 点赞表vo
 *
 * @ClassName: ClassAssistVo
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.classAssist.domain
 * @CreateTime: 2018-08-16
 */
public class ClassAssistVo extends ClassAssistEntity {
    //top表content字段   内容,图片等信息
    private TopContent content;
    //top标题
    private String title;

    public TopContent getContent() {
        return content;
    }

    public void setContent(TopContent content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}