package com.lhh.modules.classtop.entity;

import java.io.Serializable;
import java.util.Date;

import com.lhh.modules.homework.domain.ClassInfo;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-12 11:51:48
 */
public class ClassTopEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //已阅的/提交的人
    private ClassInfo[] alreadyOperateds = new ClassInfo[0];
    //发送人
    private String authId;
    // 教师 或学生
    private Integer authType;
    //内容标题 例如#周五英语任务#
    private String title;
    //内容
    private TopContent content;
    //创建时间
    private Date createTime;
    //任务结束时间 只有任务才会有这个字段
    private Date finishTime;
    //内容标签
    private String[] lables = new String[0];
    //未阅的/提交的人
    private ClassInfo[] notOperateds = new ClassInfo[0];
    //是否公开
    private Integer overt;
    //点赞的人
    private String[] praises = new String[0];
    //task_id 类型为2 任务 该字段有值
    private Integer taskId;
    //类型 1 发通知  2 发任务 3  晒分享 4 提交任务
    private Integer type;
    //英语朗读状态  1,开启  2,关闭
    private Integer tinyType;

    public Integer getTinyType() {
        return tinyType;
    }

    public void setTinyType(Integer tinyType) {
        this.tinyType = tinyType;
    }

    //收藏人数
    private Integer collectionNum;

    /**
     * 设置：id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 获取：已阅的/提交的人
     */
    public ClassInfo[] getAlreadyOperateds() {
        return alreadyOperateds;
    }

    /**
     * 设置：已阅的/提交的人
     */
    public void setAlreadyOperateds(ClassInfo[] alreadyOperateds) {
        this.alreadyOperateds = alreadyOperateds;
    }

    /**
     * 设置：发送人
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * 获取：发送人
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * 设置： 教师 或学生
     */
    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    /**
     * 获取： 教师 或学生
     */
    public Integer getAuthType() {
        return authType;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置：内容
     */
    public void setContent(TopContent content) {
        this.content = content;
    }

    /**
     * 获取：内容
     */
    public TopContent getContent() {
        return content;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：任务结束时间 只有任务才会有这个字段
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取：任务结束时间 只有任务才会有这个字段
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置：内容标签
     */
    public void setLables(String[] lables) {
        this.lables = lables;
    }

    /**
     * 获取：内容标签
     */
    public String[] getLables() {
        return lables;
    }

    /**
     * 获取：未阅的/提交的人
     */
    public ClassInfo[] getNotOperateds() {
        return notOperateds;
    }

    /**
     * 设置：未阅的/提交的人
     */
    public void setNotOperateds(ClassInfo[] notOperateds) {
        this.notOperateds = notOperateds;
    }

    /**
     * 设置：是否公开
     */
    public void setOvert(Integer overt) {
        this.overt = overt;
    }

    /**
     * 获取：是否公开
     */
    public Integer getOvert() {
        return overt;
    }

    /**
     * 设置：点赞的人
     */
    public void setPraises(String[] praises) {
        this.praises = praises;
    }

    /**
     * 获取：点赞的人
     */
    public String[] getPraises() {
        return praises;
    }

    /**
     * 设置：task_id 类型为2 任务 该字段有值
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取：task_id 类型为2 任务 该字段有值
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * 设置：类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 获取：收藏人数
     */
    public Integer getCollectionNum() {
        return collectionNum;
    }

    /**
     * 设置：收藏人数
     */
    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }
}
