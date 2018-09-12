package com.lhh.modules.homework.domain;

/**
 * 语音题口语分析bean
 *
 * @ClassName: VoiceBean
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.homework.domain
 * @CreateTime: 2018-08-14
 */
public class VoiceBean {
    //流畅度得分
    private double fluency_score;
    //完整度分
    private double integrity_score;
    //声韵分
    private double phone_score;
    //是否被拒
    private float is_rejected;
    //开始位置(帧)
    private long beg_pos;
    //结束位置(帧)
    private long end_pos;
    //异常信息，详细请见
    private Integer except_info;
    //总分
    private double total_score;
    //调型分
    private double tone_score;
    //朗读时长
    private long time_len;




}