package com.lhh.modules.homework.domain;

public class ReadContent {

    // 1 独立的一段文字  2 多个单词 多个文章
    private Integer type;

    private String article;

    private String[] words;

    private String[] articles;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public String[] getArticles() {
        return articles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }
}
