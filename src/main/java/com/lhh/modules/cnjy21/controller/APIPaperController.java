package com.lhh.modules.cnjy21.controller;

import com.lhh.common.type.ResourceType;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.cnjy21.model.paper.Paper;
import com.lhh.modules.cnjy21.model.paper.PaperView;
import com.lhh.modules.cnjy21.model.paper.Type;
import com.lhh.modules.cnjy21.model.question.Question;
import com.lhh.modules.cnjy21.service.APIPaperService;
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
//@RequestMapping("/21api/paper")
@RequestMapping("/21api/paper/test")
public class APIPaperController {

    APIPaperService service = new APIPaperService();
    APIQuestionService questionService = new APIQuestionService();


    @Autowired
    private ResourceCollectService resourceCollectService;


    /**
     * type	Number
     * 试卷类型 (通过试卷类型接口获取)
     *
     * versionId	Number
     * 教材版本ID
     *
     * province	Number
     * 试卷所属省份(可通过公共接口 "获取省级地区数据" 获取)
     *
     * bookId	Number
     * 教材册别ID
     *
     * year	Number
     * 试卷所属年份 (平台提供试卷资源自 2009 年开始)
     * title
     */
    /**
     * 获取试卷列表
     */
    @RequestMapping("/getPaperList")
    public R getPaperList(String authId, Integer chapterId, Integer stage, Integer subjectId, Integer type, Integer versionId, Integer province, Integer bookId, Integer year, Integer page, Integer limit, String title) {
        try {
            APIRequestParams apiRequestParams = new APIRequestParams();
            apiRequestParams.year = year;
            apiRequestParams.bookId = bookId;
            apiRequestParams.province = province;
            apiRequestParams.versionId = versionId;
            apiRequestParams.type = type;
            apiRequestParams.chapterId = chapterId;
            apiRequestParams.stage = stage;
            apiRequestParams.subjectId = subjectId;
            apiRequestParams.page = page;
            apiRequestParams.title = title;
            apiRequestParams.perPage = limit;
            APIResult<Paper> apiRequest = service.getPaperList(apiRequestParams);
            APIResult.Page ppage = apiRequest.getPage();
            if (StringUtils.trimToNull(authId) != null) {
                for (Paper paper : apiRequest.getData()) {
                    Integer id = paper.getId();
                    Boolean aBoolean = resourceCollectService.collectByAuthIdAndResourceId(authId, String.valueOf(id), ResourceType.Paper.getCode().intValue());
                    paper.setCollect(aBoolean);
                }
            }
            Map pmp = new HashMap();
            if (ppage != null) {
                pmp.put("currPage", ppage.getCurrentPage());
                pmp.put("totalPage", ppage.getPageCount());
                pmp.put("pageSize", ppage.getPerPage());
                pmp.put("totalCount", ppage.getTotalCount());
            }
            return R.ok(1).put("data", apiRequest.getData()).put("page", pmp);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取试卷类型列表
     *
     * @return
     */
    @RequestMapping("/getPaperTypes")
    public R getPaperTypes() {
        try {
            APIResult<Type> apiRequest = service.getPaperTypes(new APIRequestParams() {
            });
            return R.ok(1).put("data", apiRequest.getData()).put("page", apiRequest.getPage());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取试卷详情
     *
     * @return
     */
    @RequestMapping("/getPaperView")
    public R getPaperView(Integer id) {
        try {
            APIRequestParams apiRequestParams = new APIRequestParams();
            apiRequestParams.id = id;
            APIResult<PaperView> apiRequest = service.getPaperView(apiRequestParams);
            List<PaperView> data = apiRequest.getData();
            if (data != null) {
                PaperView paperView = data.get(0);

                /**
                 * 查询详情
                 */
                List<Question> questions = paperView.getQuestions();
                List<Question> questionsAnswer = questionService.getQuestionsAnswer(questions);
                paperView.setQuestions(questionsAnswer);

                return R.ok(1).put("data", paperView);
            } else {
                return R.error("没有数据");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 我的收藏试题
     */
    @RequestMapping("/getMyCollectPaperView")
    public R getMyCollectPaperView(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        params.put("resourceType", ResourceType.Paper.getCode());
        List<String> resourceIds = resourceCollectService.queryResourceIds(query);
        List<PaperView> paperViews = new ArrayList<>();
        for (String resourceId : resourceIds) {
            APIRequestParams apiRequestParams = new APIRequestParams();
            apiRequestParams.id = Integer.valueOf(resourceId);
            APIResult<PaperView> apiRequest = service.getPaperView(apiRequestParams);
            List<PaperView> data = apiRequest.getData();
            if (data != null) {
                PaperView paperView = data.get(0);
               /* //条件过滤
                Object subjectId = params.get("subjectId");
                if(null != subjectId && subjectId != paperView.getSubjectId())
                    paperView =null;
                Object typeId = params.get("typeId");
                if(null != typeId && typeId != paperView.getType())
                    paperView = null;
                if(null != paperView)*/
                paperViews.add(paperView);
            }
        }
        return R.ok(1).put("data", paperViews);
    }


}
