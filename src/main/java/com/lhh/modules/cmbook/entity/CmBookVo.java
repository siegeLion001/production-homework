package com.lhh.modules.cmbook.entity;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 18:48:30
 */
public class CmBookVo extends CmBookEntity {
    private String type;
    //
    private String publisher;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
