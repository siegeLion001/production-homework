package com.lhh.modules.homework.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lhh.common.validator.group.DayHomeWorkGroup;
import com.lhh.modules.homework.domain.ReadContent;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;


/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-29 09:38:19
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeworkEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //教师id
    @NotEmpty(message = "教师id不能为空", groups = {DayHomeWorkGroup.class})
    private String tchId;
    //班级id
    private String[] classIds;
    //科目信息
//	@NotEmpty(message = "科目信息不能为空", groups = {DayHomeWorkGroup.class})
    private String subject;
    //学段信息
//	@NotEmpty(message = "学段信息不能为空", groups = {BoutiqueHomeworkGroup.class})
    private String period;
    //册别信息
//	@NotEmpty(message = "册别信息不能为空", groups = {BoutiqueHomeworkGroup.class})
    private String book;
    //作业类型  1 日常作业 2 口语评测 3 同步练习 4 阶段检测
    @NotEmpty(message = "作业类型不能为空", groups = {DayHomeWorkGroup.class})
    private String type;
    //标题
//	@NotNull(message = "标题信息不能为空", groups = {DayHomeWorkGroup.class})
    @NotEmpty(message = "标题信息不能为空", groups = {DayHomeWorkGroup.class})
    private String title;
    //备注
    private String remark;
    //题目内容

    //	@NotEmpty(message = "题目内容不能为空", groups = {DayHomeWorkGroup.class})
    private JSONArray content;
    //空白答题卡
//	private String answerCard;
//	@NotEmpty(message = "答题卡不能为空", groups = {DayHomeWorkGroup.class})
    private TopicClusterVo[] answerCard;
    //完成时间
//	@NotEmpty(message = "完成时间不能为空", groups = {DayHomeWorkGroup.class})
    private Date finishTime;
    //发布时间
//	@NotEmpty(message = "发布时间不能为空", groups = {DayHomeWorkGroup.class})
    private Date releaseTime;
    //公布时间
//	@NotEmpty(message = "公布时间不能为空", groups = {DayHomeWorkGroup.class})
    private Date publishTime;
    //创建时间
    private Date createTime;
    //作业状态（为批改/已批改[归档]）
    private Integer state = 1;
    /**
     * 正确答案(图片)
     */
    private List<String> answerImgs;
    /**
     * 逻辑删除标示
     */
    private Integer delMark = 1;

    //语音题内容 存放特殊json对象
    private ReadContent readContent;
    //1 中文评测 2 自由评测 3 英文评测
    private String tinyType;
    //批改类型 1学生自批 2 班级互批 3 组长批 4组内互批
    private Integer correctType;
    
    //Y：草稿箱作业 ；N一般作业
    private String draftsYn;
    //科目id  日常作业上传  学生错题本
    private String subjectId;

    public String getSubjectId() {
        return subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTchId() {
        return tchId;
    }

    public void setTchId(String tchId) {
        this.tchId = tchId;
    }

    public String[] getClassIds() {
        return classIds;
    }

    public void setClassIds(String[] classIds) {
        this.classIds = classIds;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public JSONArray getContent() {
        return content;
    }

    public void setContent(JSONArray content) {
        this.content = content;
    }

    public TopicClusterVo[] getAnswerCard() {
        return answerCard;
    }

    public void setAnswerCard(TopicClusterVo[] answerCard) {
        this.answerCard = answerCard;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<String> getAnswerImgs() {
        return answerImgs;
    }

    public void setAnswerImgs(List<String> answerImgs) {
        this.answerImgs = answerImgs;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public ReadContent getReadContent() {
        return readContent;
    }

    public void setReadContent(ReadContent readContent) {
        this.readContent = readContent;
    }

    public String getTinyType() {
        return tinyType;
    }

    public void setTinyType(String tinyType) {
        this.tinyType = tinyType;
    }

    /**
     * 获取：批改类型 1学生自批 2 班级互批 3 组长批 4组内互批
     */
    public Integer getCorrectType() {
        return correctType;
    }

    /**
     * 设置：批改类型 1学生自批 2 班级互批 3 组长批 4组内互批
     */
    public void setCorrectType(Integer correctType) {
        this.correctType = correctType;
    }

	public String getDraftsYn() {
		return draftsYn;
	}

	public void setDraftsYn(String draftsYn) {
		this.draftsYn = draftsYn;
	}

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
