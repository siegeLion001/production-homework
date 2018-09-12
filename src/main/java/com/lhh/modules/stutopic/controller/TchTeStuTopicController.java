package com.lhh.modules.stutopic.controller;

import com.lhh.common.utils.R;
import com.lhh.modules.stutopic.service.StuTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("tch/stutopic")
public class TchTeStuTopicController {
    @Autowired
    private StuTopicService stuTopicService;

    /**
     * 班级错题本
     */
    public R classWrongTopics() {

        return R.ok(1);
    }


}
