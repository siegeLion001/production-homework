package com.lhh.modules.cnjy21.service;

import java.util.List;

import com.lhh.modules.cnjy21.constant.APIConstant;
import com.lhh.modules.cnjy21.model.common.Book;
import com.lhh.modules.cnjy21.model.common.Chapter;
import com.lhh.modules.cnjy21.model.common.KnowledgePoint;
import com.lhh.modules.cnjy21.model.common.Province;
import com.lhh.modules.cnjy21.model.common.Subject;
import com.lhh.modules.cnjy21.model.common.Version;
import com.lhh.modules.cnjy21.util.APIRequest;

/**
 * API Common 服务
 * 
 * @author zhoushubing
 *
 */
public class APICommonService {

	protected APIRequest apiRequest = APIRequest.getInstance();

	/**
	 * 根据学段获取教材科目
	 * 
	 * @param _stage
	 *            学段ID 1:小学，2:初中，3:高中
	 * @return
	 */
	public List<Subject> getSubjects(Integer _stage) {
		return apiRequest.requestList(APIConstant.URL_SUBJECTS, Subject.class, new APIRequestParams() {
			{
				this.stage = _stage;
			}
		});
	}

	/**
	 * 获取版本列表
	 * 
	 * @param _stage
	 *            学段ID 1:小学，2:初中，3:高中
	 * @param _subjectId
	 *            科目ID
	 * @return
	 */
	public List<Version> getVersions(Integer _stage, Integer _subjectId) {
		return apiRequest.requestList(APIConstant.URL_VERSIONS, Version.class, new APIRequestParams() {
			{
				this.stage = _stage;
				this.subjectId = _subjectId;
			}
		});
	}

	/**
	 * 获取教材册别信息
	 * 
	 * @param _versionId
	 *            版本ID
	 * @return
	 */
	public List<Book> getBooks(Integer _versionId) {
		return apiRequest.requestList(APIConstant.URL_BOOKS, Book.class, new APIRequestParams() {
			{
				this.versionId = _versionId;
			}
		});
	}

	/**
	 * 获取章节信息
	 * 
	 * @param _bookId
	 * @return
	 */
	public List<Chapter> getChapters(Integer _bookId) {
		return apiRequest.requestList(APIConstant.URL_CHAPTERS, Chapter.class, new APIRequestParams() {
			{
				this.bookId = _bookId;
			}
		});
	}

	/**
	 * 获取知识点列表
	 * 
	 * @param _stage
	 *            学段ID 1:小学，2:初中，3:高中
	 * @param _subjectId
	 *            科目ID
	 * @return
	 */
	public List<KnowledgePoint> getKnowledgePoints(Integer _stage, Integer _subjectId) {
		return apiRequest.requestList(APIConstant.URL_KNOWLEDGEPOINTS, KnowledgePoint.class, new APIRequestParams() {
			{
				this.subjectId = _subjectId;
				this.stage = _stage;
			}
		});
	}


	/**
	 * 获取省份地区信息
	 * 
	 * @return
	 */
	public List<Province> getProvinces() {
		return apiRequest.requestList(APIConstant.URL_PROVINCES, Province.class);
	}

}
