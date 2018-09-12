package com.lhh.modules.cnjy21.controller;

import com.lhh.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/21api/paper")
//@RequestMapping("/21api/paper/test")
public class APIPaperTestController {

    @Autowired
    private APIPaperController apiPaperController;

    private Map<String, Object> map = new HashMap<>();
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
        if (map.containsKey("getPaperList")) {
            return (R) map.get("getPaperList");
        } else {
            R r = apiPaperController.getPaperList(authId, chapterId, stage, subjectId, type, versionId, province, bookId, year, page, limit, title);
            map.put("getPaperList", r);
            return r;
        }
    }

    /**
     * 获取试卷类型列表
     *
     * @return
     */
    @RequestMapping("/getPaperTypes")
    public R getPaperTypes() {
        if (map.containsKey("getPaperTypes")) {
            return (R) map.get("getPaperTypes");
        } else {
            R r = apiPaperController.getPaperTypes();
            map.put("getPaperTypes", r);
            return r;
        }
    }

    /**
     * 获取试卷详情
     *
     * @return
     */
    @RequestMapping("/getPaperView")
    public R getPaperView(Integer id) {
        if (map.containsKey("getPaperView")) {
            return (R) map.get("getPaperView");
        } else {
            R r = apiPaperController.getPaperView(id);
            map.put("getPaperView", r);
            return r;
        }
    }

    /**
     * 我的收藏试题
     */
    @RequestMapping("/getMyCollectPaperView")
    public R getMyCollectPaperView(@RequestBody Map<String, Object> params) {
        if (map.containsKey("getMyCollectPaperView")) {
            return (R) map.get("getMyCollectPaperView");
        } else {
            R r = apiPaperController.getMyCollectPaperView(params);
            map.put("getMyCollectPaperView", r);
            return r;
        }
    }


}
