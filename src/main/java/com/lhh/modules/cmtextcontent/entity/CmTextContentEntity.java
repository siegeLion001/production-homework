package com.lhh.modules.cmtextcontent.entity;

import java.io.Serializable;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public class CmTextContentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //类型id
    private Integer typeId;
    //出版社code
    private Integer publisherId;
    //教材code
    private Integer bookId;
    //文章内容
    private String articleName;
    //文章结果
    private String articleValue;

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置：类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取：出版社code
     */
    public Integer getPublisherId() {
        return publisherId;
    }

    /**
     * 设置：出版社code
     */
    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * 获取：教材code
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置：教材code
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取：文章内容
     */
    public String getArticleName() {
        return articleName;
    }

    /**
     * 设置：文章内容
     */
    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    /**
     * 获取：文章结果
     */
    public String getArticleValue() {
        return articleValue;
    }

    /**
     * 设置：文章结果
     */
    public void setArticleValue(String articleValue) {
        this.articleValue = articleValue;
    }
}
