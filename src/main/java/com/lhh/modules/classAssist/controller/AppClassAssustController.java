package com.lhh.modules.classAssist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classAssist.service.ClassAssistService;

/**
 * 点赞app
 *
 * @ClassName: AppClassAssustController
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.classAssist.controller
 * @CreateTime: 2018-08-16
 */
@RestController
@RequestMapping("app/classAssist")
public class AppClassAssustController {

    @Autowired
    private ClassAssistService classAssistService;
    /**
     * 我的消息->赞查询(赞我的人)
     * @name: assustMine
     * @params: [map]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/8/16
     */
    @RequestMapping("/assustMine")
    public R assustMine(@RequestBody Map<String,Object> map){
        Object page = map.get("page");
        if(page!=null){
            Query query = new Query(map);
            List<Map<String, Object>> list = classAssistService.assustMine(query);
            int i = classAssistService.queryTotal(query);
            PageUtils pageUtils = new PageUtils(i,query.getLimit(),query.getPage());

            return R.ok(1).put("data",list).put("page",pageUtils);
        }else{
            List<Map<String, Object>> list = classAssistService.assustMine(map);
            return R.ok(1).put("data",list);
        }

    }
}