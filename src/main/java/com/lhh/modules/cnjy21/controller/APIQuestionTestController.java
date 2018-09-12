package com.lhh.modules.cnjy21.controller;

import com.lhh.common.utils.R;
import com.lhh.modules.cnjy21.service.APIQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/21api/question")
//@RequestMapping("/21api/question/test")
public class APIQuestionTestController {


    @Autowired
    private APIQuestionController apiQuestionController;

    private Map<String, Object> map = new HashMap<>();

    /**
     * 获取各阶段教材科目
     *
     * @param stage 学段ID 1:小学，2:初中，3:高中
     * @return
     */
    @RequestMapping("/getSubjects")
    public R getSubjects(Integer stage) {
        if (map.containsKey("getSubjects")) {
            return (R) map.get("getSubjects");
        } else {
            R r = apiQuestionController.getSubjects(stage);
            map.put("getSubjects", r);
            return r;
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
        if (map.containsKey("getVersions")) {
            return (R) map.get("getVersions");
        } else {
            R r = apiQuestionController.getVersions(stage, subjectId);
            map.put("getVersions", r);
            return r;
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
        if (map.containsKey("getBooks")) {
            return (R) map.get("getBooks");
        } else {
            R r = apiQuestionController.getBooks(versionId);
            map.put("getBooks", r);
            return r;
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
        if (map.containsKey("getChapters")) {
            return (R) map.get("getChapters");
        } else {
            R r = apiQuestionController.getChapters(bookId);
            map.put("getChapters", r);
            return r;
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
        if (map.containsKey("getQuestions")) {
            return (R) map.get("getQuestions");
        } else {
            R r = apiQuestionController.getQuestions(authId, subjectId, stage, chapterId, page, limit, knowledgeId);
            map.put("getQuestions", r);
            return r;
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
        if (map.containsKey("getKnowledges")) {
            return (R) map.get("getKnowledges");
        } else {
            R r = apiQuestionController.getKnowledges(stage, subjectId);
            map.put("getKnowledges", r);
            return r;
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
        if (map.containsKey("getquestionTypes")) {
            return (R) map.get("getquestionTypes");
        } else {
            R r = apiQuestionController.getquestionTypes(stage, subjectId);
            map.put("getquestionTypes", r);
            return r;
        }
    }


    /**
     * 我的收藏试题
     */
    @RequestMapping("/getMyCollectQuestion")
    public R getMyCollectQuestion(@RequestBody Map<String, Object> params) {
        if (map.containsKey("getMyCollectQuestion")) {
            return (R) map.get("getMyCollectQuestion");
        } else {
            R r = apiQuestionController.getMyCollectQuestion(params);
            map.put("getMyCollectQuestion", r);
            return r;
        }
    }
}
