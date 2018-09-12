package com.lhh.modules.cnjy21.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lhh.modules.cnjy21.constant.APIConstant;
import com.lhh.modules.cnjy21.model.question.Question;
import com.lhh.modules.cnjy21.model.question.QuestionType;
import com.lhh.modules.cnjy21.util.APIResult;

/**
 * 
 * API Question 服务
 * 
 * @author zhoushubing
 *
 */
public class APIQuestionService extends APICommonService {

	/**
	 * 获取试题列表
	 * 
	 * @param params
	 *            请求参数 {@value chapterId | knowledgeId = 必填项} {@value stage &
	 *            subjectId = 必填项}
	 * @return
	 */
	public APIResult<Question> getQuestions(APIRequestParams params) {
		if (params.chapterId == null && params.knowledgeId == null) {
			throw new RuntimeException("chapterId or knowledgeId is null.");
		}
		if (params.stage == null || params.subjectId == null) {
			throw new RuntimeException("stage and subjectId cannot be null.");
		}
		return apiRequest.request(APIConstant.URL_QUESTIONS, Question.class, params);
	}

	/**
	 * 获取试题类型列表
	 * 
	 * @param _stage
	 *            学段ID APIConstant.STAGE_
	 * @param _subjectId
	 *            学科ID
	 * @return
	 */
	public List<QuestionType> getQuestionTypes(int _stage, int _subjectId) {
		return apiRequest.requestList(APIConstant.URL_QUESTION_TYPES, QuestionType.class, new APIRequestParams() {
			{
				this.stage = _stage;
				this.subjectId = _subjectId;
			}
		});
	}

	/**
	 * 获取试题答案和解析
	 * 
	 * @param questions
	 *            试题列表
	 * @return
	 */
	public List<Question> getQuestionsAnswer(List<Question> questions) {
		if (questions.isEmpty()) {
			return questions;
		}
		StringBuffer _questionIds = new StringBuffer();
		for (Question question : questions) {
			if (question.getQuestionId() == null) {
				throw new RuntimeException("question ID is null.");
			}
			_questionIds.append(question.getQuestionId()).append(",");
		}
		APIResult<Question> result = apiRequest.request(APIConstant.URL_QUESTION_DETAIL, Question.class,
				new APIRequestParams() {
					{
						this.questionIds = _questionIds.toString();
					}
				});
		List<Question> data = result.getData();
		for (Question datum : data) {
			List<Object> answerJson = datum.getAnswerJson();


			Object obj = answerJson.get(0);
			if (obj instanceof ArrayList<?>) {
				List<Object> objects = new ArrayList<>();

				for (Object o : answerJson) {
					List<Object> taskList = (ArrayList<Object>) o;
					for (Object o1 : taskList) {
						String o11 = (String) o1;
						if(StringUtils.isNotBlank(o11)){
							objects.add(o11);
						}
					}

					datum.setAnswerJson(objects);
				}

			}
		}
		return data;

	}
	/**
	 * 获取试题答案和解析
	 * 
	 * @param question
	 *            试题对象
	 * @return
	 */
	public Question getQuestionAnswer(Question question) {
		List<Question> questions = new ArrayList<Question>(1);
		questions.add(question);
		return getQuestionsAnswer(questions).get(0);
	}

}
