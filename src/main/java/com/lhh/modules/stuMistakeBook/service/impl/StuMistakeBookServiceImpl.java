package com.lhh.modules.stuMistakeBook.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homework.service.HomeworkService;
import com.lhh.modules.stuMistakeBook.dao.StuMistakeBookDao;
import com.lhh.modules.stuMistakeBook.entity.StuMistakeBookEntity;
import com.lhh.modules.stuMistakeBook.entity.StuMistakeBookVo;
import com.lhh.modules.stuMistakeBook.service.StuMistakeBookService;
import com.lhh.modules.topic.entity.TopicEntity;
import com.lhh.modules.topic.service.TopicService;
import com.lhh.modules.topiccluster.entity.TopicClusterEntity;
import com.lhh.modules.topiccluster.service.TopicClusterService;





@Service("stuMistakeBookService")
public class StuMistakeBookServiceImpl implements StuMistakeBookService {
	@Autowired
	private StuMistakeBookDao stuMistakeBookDao;
	@Autowired
	private HomeworkService homeworkService;
//	@Autowired
//	private StuTopicService stuTopicService;
//	@Autowired
//	private StuTopicClusterService stuTopicClusterService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicClusterService topicClusterService;
	
	@Override
	public StuMistakeBookEntity queryObject(Integer id){
		return stuMistakeBookDao.queryObject(id);
	}
	
	@Override
	public List<StuMistakeBookEntity> queryList(Map<String, Object> map){
		return stuMistakeBookDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return stuMistakeBookDao.queryTotal(map);
	}
	
	@Override
	public void save(StuMistakeBookEntity stuMistakeBook){
		stuMistakeBookDao.save(stuMistakeBook);
	}
	
	@Override
	public void update(StuMistakeBookEntity stuMistakeBook){
		stuMistakeBookDao.update(stuMistakeBook);
	}
	
	@Override
	public void delete(Integer id){
		stuMistakeBookDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		stuMistakeBookDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> querySubjectInfo(Map<String, Object> params) {
		return stuMistakeBookDao.querySubjectInfo(params);
	}

	@Override
	public Map<String, Object> queryBookInfo(Map<String, Object> params) {
		return stuMistakeBookDao.queryBookInfo(params);
	}

	@Override
	public List<Map<String, Object>> querySectionInfo(Map<String, Object> params) {
		return stuMistakeBookDao.querySectionInfo(params);
	}

	@Override
	public List<StuMistakeBookVo > queryListByMap(Map<String, Object> params) {
		return stuMistakeBookDao.queryListByMap(params);
	}

	@Override
	public Map<String, Object> queryRank4stu(Map<String, Object> params) {
		return stuMistakeBookDao.queryRank4stu(params);
	}

	@Override
	public boolean insertMistakeBook(Integer homeworkId, String stuId, String classId, String bNum, String sNum) {
		/**
		 * 查询homework表获取题目信息  科目Id
		 * 查询stu_topic表或是 stu_topic_cluster表  获取答题内容
		 * 判断题目类型  区分从大题表获取题目作答信息还是小题表获取大题信息 daiDing(没有插入作答信息)
		 */
		StuMistakeBookEntity mistakeBookEntity = new StuMistakeBookEntity();
		HomeworkEntity homeworkEntity = homeworkService.queryObject(homeworkId);
		String subjectId = homeworkEntity.getSubjectId();
		if(StringUtils.isNotBlank(subjectId)){//subjectId 不为空添加  为空不添加
			mistakeBookEntity.setSubjctId(subjectId);
			JSONArray content = homeworkEntity.getContent();
			List<String> strings = content.toJavaList(String.class);
			mistakeBookEntity.setTopContent(strings);

			Map<String, Object> map = new HashMap<>();
			map.put("homeworkId",homeworkId);
			map.put("bNum",bNum);
			List<TopicClusterEntity> topicClusterEntities = topicClusterService.queryList(map);
			Integer id = topicClusterEntities.get(0).getId();
			map.put("topicClusterId",id);
			map.put("sNum",sNum);
			List<TopicEntity> topicEntities = topicService.queryList(map);
			TopicEntity topicEntity = topicEntities.get(0);
			Integer type = topicEntity.getType();
			List correctAnswer = topicEntity.getCorrectAnswer();
			mistakeBookEntity.setCorrectAnswer(correctAnswer);
			mistakeBookEntity.setTopType(type);
			mistakeBookEntity.setStuId(stuId);
			mistakeBookEntity.setClassId(classId);
			stuMistakeBookDao.save(mistakeBookEntity);
			return true;
		}
		return false;

	}

	@Override
	public Map<String, Object> queryList4noSection(Map<String, Object> params) {
		return stuMistakeBookDao.queryList4noSection(params);
	}

	@Override
	public List<StuMistakeBookVo> queryListByMap4NoSection(Map<String, Object> params) {
		return stuMistakeBookDao.queryListByMap4NoSection(params);
	}

	@Override
	public List<Map<String, Object>> queryRank(Map<String, Object> params) {
		return stuMistakeBookDao.queryRank(params);
	}

}
