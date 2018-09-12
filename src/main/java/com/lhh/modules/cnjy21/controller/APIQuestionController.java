package com.lhh.modules.cnjy21.controller;

import com.lhh.common.type.ResourceType;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.cnjy21.model.common.*;
import com.lhh.modules.cnjy21.model.question.Question;
import com.lhh.modules.cnjy21.model.question.QuestionType;
import com.lhh.modules.cnjy21.service.APIQuestionService;
import com.lhh.modules.cnjy21.service.APIRequestParams;
import com.lhh.modules.cnjy21.util.APIResult;
import com.lhh.modules.resourcecollect.service.ResourceCollectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/21api/question")
@RequestMapping("/21api/question/test")
public class APIQuestionController {

    APIQuestionService service = new APIQuestionService();

    @Autowired
    private ResourceCollectService resourceCollectService;

    /**
     * 获取各阶段教材科目
     *
     * @param stage 学段ID 1:小学，2:初中，3:高中
     * @return
     */
    @RequestMapping("/getSubjects")
    public R getSubjects(Integer stage) {
        try {
            List<Subject> subjects = service.getSubjects(stage);
            return R.ok(1).put("data", subjects);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取各阶段科目下的教材版本列表
     *
     * @param stage     学段ID 1:小学，2:初中，3:高中
     * @param subjectId 科目ID
     * @return
     */
    @RequestMapping("/getVersions")
    public R getVersions(Integer stage, Integer subjectId) {
        try {
            List<Version> versions = service.getVersions(stage, subjectId);
            return R.ok(1).put("data", versions);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取版本教材下的册别信息列表
     *
     * @param versionId 版本id
     * @return
     */
    @RequestMapping("/getBooks")
    public R getBooks(Integer versionId) {
        try {
            List<Book> books = service.getBooks(versionId);
            return R.ok(1).put("data", books);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取Book下所有章节目录树
     *
     * @param bookId 册别ID
     * @return
     */
    @RequestMapping("/getChapters")
    public R getChapters(Integer bookId) {
        try {
            List<Chapter> chapters = service.getChapters(bookId);
            return R.ok(1).put("data", chapters);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据章节获取试题
     * <p>
     * 章节id 科目id 学段id
     *
     * @return
     */
    @RequestMapping("/getQuestions")
    public R getQuestions(String authId, Integer subjectId, Integer stage, Integer chapterId, Integer page, Integer limit, Integer knowledgeId) {
        try {
            APIRequestParams apiRequestParams = new APIRequestParams();
            apiRequestParams.page = page;
            apiRequestParams.subjectId = subjectId;
            apiRequestParams.stage = stage;
            apiRequestParams.chapterId = chapterId;
            apiRequestParams.perPage = limit;
            apiRequestParams.knowledgeId = knowledgeId;

            APIResult<Question> questions = service.getQuestions(apiRequestParams);

            /**
             * 查询详情
             */
            List<Question> data = questions.getData();
            List<Question> questionsAnswer = service.getQuestionsAnswer(data);
            questions.setData(questionsAnswer);


            if (StringUtils.trimToNull(authId) != null) {
                for (Question question : questions.getData()) {
                    Long id = question.getQuestionId();
                    Boolean aBoolean = resourceCollectService.collectByAuthIdAndResourceId(authId, String.valueOf(id), ResourceType.Question.getCode().intValue());
                    question.setCollect(aBoolean);
                }
            }

            APIResult.Page questionsPage = questions.getPage();

            Map pageMap = new HashMap();
            if (questionsPage != null) {
                pageMap.put("currPage", questionsPage.getCurrentPage());
                pageMap.put("totalPage", questionsPage.getPageCount());
                pageMap.put("pageSize", questionsPage.getPerPage());
                pageMap.put("totalCount", questionsPage.getTotalCount());
            }
            return R.ok(1).put("data", questions.getData()).put("page", pageMap);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取知识点列表
     *
     * @param stage     学段ID 1:小学，2:初中，3:高中
     * @param subjectId 科目ID
     * @return
     */
    @RequestMapping("/getKnowledges")
    public R getKnowledges(Integer stage, Integer subjectId) {
        try {
            List<KnowledgePoint> knowledges = service.getKnowledgePoints(stage, subjectId);
            return R.ok(1).put("data", knowledges);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取试题类型列表
     *
     * @param stage     学段ID
     * @param subjectId 学科ID
     * @return
     */
    @RequestMapping("/getquestionTypes")
    public R getquestionTypes(Integer stage, Integer subjectId) {
        try {
            List<QuestionType> list = service.getQuestionTypes(stage, subjectId);
            return R.ok(1).put("data", list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     * 我的收藏试题
     */
    @RequestMapping("/getMyCollectQuestion")
    public R getMyCollectQuestion(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        params.put("resourceType", ResourceType.Paper.getCode().intValue());
        List<String> resourceIds = resourceCollectService.queryResourceIds(query);
        List<Question> questions = new ArrayList<>();
        for (String resourceId : resourceIds) {
            APIRequestParams apiRequestParams = new APIRequestParams();
            apiRequestParams.id = Integer.valueOf(resourceId);
            APIResult<Question> apiRequest = service.getQuestions(apiRequestParams);
            List<Question> data = apiRequest.getData();
            if (data != null) {
                /**
                 * 这里查一下详情
                 */
                List<Question> questionsAnswer = service.getQuestionsAnswer(data);
                Question question = questionsAnswer.get(0);
                questions.add(question);
            }
        }
        return R.ok(1).put("data", questions);
    }
}
