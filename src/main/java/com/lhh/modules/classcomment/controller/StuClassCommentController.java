package com.lhh.modules.classcomment.controller;

import com.lhh.common.utils.R;
import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.service.ClassCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:30
 */
@RestController
@RequestMapping("stu/classcomment")
public class StuClassCommentController {
    @Autowired
    private ClassCommentService classCommentService;

    @Autowired
    private ClassCommentController classCommentController;

    /**
     * 评论推送
     *
     * @param classCommentEntity
     * @return
     */
    @RequestMapping("/push")
    public R push(@RequestBody ClassCommentEntity classCommentEntity) {
        classCommentController.push(classCommentEntity);
        return R.ok(1);
    }

}
