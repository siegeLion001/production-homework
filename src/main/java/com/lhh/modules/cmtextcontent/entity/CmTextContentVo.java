package com.lhh.modules.cmtextcontent.entity;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public class CmTextContentVo extends CmTextContentEntity {


    private String type;
    //出版社code
    private String publisher;
    //教材code
    private String book;

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

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
