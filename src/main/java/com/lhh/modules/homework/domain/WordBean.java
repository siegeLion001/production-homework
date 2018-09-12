package com.lhh.modules.homework.domain;

/**
 * 口语分析音节bean
 *
 * @ClassName: wordBean
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.homework.domain
 * @CreateTime: 2018-08-14
 */
public class WordBean {
    //时长
    private long time_len;
    //拼音：数字代表声调， 5 和 5 以上表示轻声
    private String symbol;
    //试卷内容（sil、 silv 表示静音， fil 表示噪音）
    private String content;
    //始位置（单位：帧）
    private long beg_pos;
    //末位置（单位：帧）
    private long end_pos;

}