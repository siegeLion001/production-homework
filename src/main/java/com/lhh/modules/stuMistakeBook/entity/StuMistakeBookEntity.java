package com.lhh.modules.stuMistakeBook.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 学生错题本
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-22 16:12:43
 */
public class StuMistakeBookEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	private Integer id;
	//科目id
	private String subjctId;
	//册别id
	private String bookId;
	//章节id
	private String sectionId;
	//知识点id
	private String knowledgeId;
	//学生id
	private String stuId;
	//题目内容
	private List<String> topContent;
	//题目解析
	private List<String> topAnalysis;
	//正确答案
	private List<String> correctAnswer;
	//题目类型
	private Integer topType;
	//错误原因
	private String mistakeReason;
	//难度
	private Integer difficulty;
	//创建日期
	private Date createTime;
	//修改日期
	private Date updateTime;
	/*是否掌握*/
	private Integer isGrasp;
	private String classId;
	//备用字段1
	private String reserve1;

	private int stuTopicClusterId;

	private int stuTopicId;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：科目id
	 */
	public void setSubjctId(String subjctId) {
		this.subjctId = subjctId;
	}
	/**
	 * 获取：科目id
	 */
	public String getSubjctId() {
		return subjctId;
	}
	/**
	 * 设置：册别id
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	/**
	 * 获取：册别id
	 */
	public String getBookId() {
		return bookId;
	}
	/**
	 * 设置：章节id
	 */
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	/**
	 * 获取：章节id
	 */
	public String getSectionId() {
		return sectionId;
	}
	/**
	 * 设置：学生id
	 */
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	/**
	 * 获取：学生id
	 */
	public String getStuId() {
		return stuId;
	}
	/**
	 * 设置：题目内容
	 */
	public void setTopContent(List<String> topContent) {
		this.topContent = topContent;
	}
	/**
	 * 获取：题目内容
	 */
	public List<String> getTopContent() {
		return topContent;
	}
	/**
	 * 设置：题目解析
	 */
	public void setTopAnalysis(List<String> topAnalysis) {
		this.topAnalysis = topAnalysis;
	}
	/**
	 * 获取：题目解析
	 */
	public List<String> getTopAnalysis() {
		return topAnalysis;
	}
	/**
	 * 设置：正确答案
	 */
	public void setCorrectAnswer(List<String> correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	/**
	 * 获取：正确答案
	 */
	public List<String> getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * 设置：题目类型
	 */
	public void setTopType(Integer topType) {
		this.topType = topType;
	}
	/**
	 * 获取：题目类型
	 */
	public Integer getTopType() {
		return topType;
	}
	/**
	 * 设置：错误原因
	 */
	public void setMistakeReason(String mistakeReason) {
		this.mistakeReason = mistakeReason;
	}
	/**
	 * 获取：错误原因
	 */
	public String getMistakeReason() {
		return mistakeReason;
	}
	/**
	 * 设置：难度
	 */
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * 获取：难度
	 */
	public Integer getDifficulty() {
		return difficulty;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getIsGrasp() {
		return isGrasp;
	}

	public void setIsGrasp(Integer isGrasp) {
		this.isGrasp = isGrasp;
	}

	/**
	 * 设置：备用字段1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 获取：备用字段1
	 */
	public String getReserve1() {
		return reserve1;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getStuTopicClusterId() {
		return stuTopicClusterId;
	}

	public void setStuTopicClusterId(int stuTopicClusterId) {
		this.stuTopicClusterId = stuTopicClusterId;
	}

	public int getStuTopicId() {
		return stuTopicId;
	}

	public void setStuTopicId(int stuTopicId) {
		this.stuTopicId = stuTopicId;
	}
}
