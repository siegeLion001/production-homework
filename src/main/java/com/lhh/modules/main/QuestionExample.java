package com.lhh.modules.main;

import java.util.List;

import com.google.gson.Gson;
import com.lhh.modules.cnjy21.constant.APIConstant;
import com.lhh.modules.cnjy21.model.common.Book;
import com.lhh.modules.cnjy21.model.common.Chapter;
import com.lhh.modules.cnjy21.model.common.KnowledgePoint;
import com.lhh.modules.cnjy21.model.common.Subject;
import com.lhh.modules.cnjy21.model.common.Version;
import com.lhh.modules.cnjy21.model.question.Question;
import com.lhh.modules.cnjy21.model.question.QuestionType;
import com.lhh.modules.cnjy21.service.APIQuestionService;
import com.lhh.modules.cnjy21.service.APIRequestParams;
import com.lhh.modules.cnjy21.util.APIResult;

/**
 * 试题例子
 * 
 * @author zhoushubing
 *
 */
public class QuestionExample {
	public static void main(String[] args) {
		APIQuestionService service = new APIQuestionService();
		// 获取小学教材科目
		List<Subject> subjects = service.getSubjects(APIConstant.STAGE_GAOZHONG);
		System.out.println(new Gson().toJson(subjects));
		System.out.println(subjects.size());
		// 获取小学第一个科目下的教材版本列表
		List<Version> versions = service.getVersions(APIConstant.STAGE_GAOZHONG, subjects.get(0).getSubjectId());
		System.out.println("versions size:" + versions.size());
		System.out.println(new Gson().toJson(versions));
		// 获取版本教材下的册别信息列表
		List<Book> books = service.getBooks(versions.get(2).getVersionId());
		System.out.println("books size:" + books.size());
		System.out.println(new Gson().toJson(books));

		// 此处可获取Book下所有章节目录树
		List<Chapter> chapters = service.getChapters(books.get(1).getBookId());
		System.out.println("chapters size:" + chapters.size());
		System.out.println(new Gson().toJson(chapters));
		//根据章节获取试题
		APIResult<Question> questions = service.getQuestions(new APIRequestParams() {
			{
				this.chapterId = chapters.get(0).getId();
				this.stage = APIConstant.STAGE_XIAOXUE;
				this.subjectId = subjects.get(0).getSubjectId();
			}
		});
		System.out.println(new Gson().toJson(questions));
		System.out.println(new Gson().toJson(questions.getPage()));
		
		List<KnowledgePoint> knowledges = service.getKnowledgePoints(APIConstant.STAGE_GAOZHONG, 2);
		System.out.println(new Gson().toJson(knowledges));
		//根据知识点获取试题
		questions = service.getQuestions(new APIRequestParams() {
			{
				this.knowledgeId = knowledges.get(0).getId();
				this.stage = APIConstant.STAGE_GAOZHONG;
				this.subjectId = subjects.get(0).getSubjectId();
			}
		});
		System.out.println("---------------------------");
		System.out.println( new Gson().toJson(questions));
		System.out.println("---------------------------");


		System.out.println(new Gson().toJson(questions.getPage()));
		
		//获取试题类型
		List<QuestionType> list = service.getQuestionTypes(APIConstant.STAGE_XIAOXUE, subjects.get(0).getSubjectId());
		System.out.println(new Gson().toJson(list));

	}
}
