package com.lhh.modules.classcomment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.entity.vo.ClassCommentVo;
import com.lhh.modules.classcomment.service.ClassCommentService;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:30
 */
@RestController
@RequestMapping("tch/classcomment")
public class TchClassCommentController {
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

    @RequestMapping("/commentList")
    public R commentList(@RequestBody Map<String,Object> params){
        Object authId = params.get("authId");
        if(authId ==null){
            return R.error("authId不能为空");
        }
        Object page = params.get("page");
        if(page ==null){
            List<ClassCommentVo> classCommentVos = classCommentService.commentList(params);
            return R.ok(1).put("data",classCommentVos);
        }
        Query query = new Query(params);
        List<ClassCommentVo> classCommentList = classCommentService.commentList(query);
        int i = classCommentService.queryTotal(query);
        PageUtils pageUtils = new PageUtils(i,query.getLimit(),query.getPage());
        return R.ok(1).put("data",classCommentList).put("page",pageUtils);
    }

}
