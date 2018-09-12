package com.lhh.modules.classtaskfeedback.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.R;
import com.lhh.modules.classtaskfeedback.service.ClassTaskFeedbackService;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
@RestController
@RequestMapping("tch/classtaskfeedback")
public class TchClassTaskFeedbackController {
    @Autowired
    private ClassTaskFeedbackService classTaskFeedbackService;
    @Autowired
    private ClassTaskFeedbackController classTaskFeedbackController;
    /**
     * 任务分析
     * @name: taskAnalyze
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/16
     */
    @RequestMapping("/taskAnalyze")
    public R taskAnalyze (@RequestBody Map<String,Object> params){
        return classTaskFeedbackController.taskAnalyze(params);
    }
}
