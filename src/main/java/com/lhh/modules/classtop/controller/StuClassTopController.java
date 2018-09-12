package com.lhh.modules.classtop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.R;
import com.lhh.modules.classtaskfeedback.entity.ClassTaskFeedbackEntity;
import com.lhh.modules.classtaskfeedback.service.ClassTaskFeedbackService;
import com.lhh.modules.classtop.domain.QueryVo;
import com.lhh.modules.classtop.entity.ClassTopEntity;
import com.lhh.modules.classtop.entity.vo.ClassTopParam;
import com.lhh.modules.classtop.entity.vo.ClassTopPushVo;
import com.lhh.modules.classtop.service.ClassTopService;
import com.lhh.modules.classvisual.service.ClassVisualService;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
@RestController
@RequestMapping("stu/classtop")
public class StuClassTopController {
    @Autowired
    private ClassTopService classTopService;

    @Autowired
    private ClassVisualService classVisualService;

    @Autowired
    private ClassTopController classTopController;

    @Autowired
    private TchClassTopController tchClassTopController;

    @Autowired
    private ClassTaskFeedbackService classTaskFeedbackService;

    /**
     * 消息列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        return tchClassTopController.list(params);
    }

    /**
     * 发送消息 、任务 ，分享
     */
    @RequestMapping("/push")
    public R push(@RequestBody ClassTopPushVo classTopPushVo) {

        if (classTopPushVo.getType() == 4) {
            Integer taskId = classTopPushVo.getTaskId();
            ClassTopEntity classTopEntity = classTopService.queryObject(taskId);
            String classId = classTopPushVo.getClassId();
            String stuId = classTopPushVo.getAuthId();
            // 学生提交任务的时候  tchId 一定为null
            classTopEntity = classTopController.remove(classTopEntity, classId, stuId, null);
            classTopService.update(classTopEntity);

            ClassTaskFeedbackEntity classTaskFeedbackEntity = classTopPushVo.getClassTaskFeedback();
            classTaskFeedbackEntity.setAuthId(classTopPushVo.getAuthId());
            classTaskFeedbackEntity.setTopId(classTopPushVo.getTaskId());
            classTaskFeedbackService.save(classTaskFeedbackEntity);
        }
        return tchClassTopController.push(classTopPushVo);
    }

    /**
     * 任务列表
     */
    @RequestMapping("/taskList")
    public R taskList(@RequestParam Map<String, Object> params) {
        return tchClassTopController.list(params);
    }


    @RequestMapping("/look")
    public R look(@RequestParam ClassTopParam classTopParam) {
        return classTopController.look(classTopParam);
    }

    /**
     * 点赞
     *
     * @param classTopParam
     * @return
     */
    @RequestMapping("/praise")
    public R praise(@RequestParam ClassTopParam classTopParam) {
        return classTopController.praise(classTopParam);
    }

    /**
     * 取消点赞
     *
     * @param classTopParam
     * @return
     */
    @RequestMapping("/unPraise")
    public R unPraise(@RequestParam ClassTopParam classTopParam) {
        return classTopController.unPraise(classTopParam);
    }

    /**
     * 我的发布
     *
     * @name: topList
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/17
     */
    @RequestMapping("/topList")
    public R topList(@RequestBody Map<String, Object> params) {//必传参数 authId
        return classTopController.topList(params);
    }

    /**
     * 我的发布 删除
     *
     * @name: deleteTop
     * @params: [id]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/17
     */
    @RequestMapping("/deleteTop")
    public R deleteTop(@RequestBody Integer id) {
        return classTopController.deleteTop(id);
    }

    /**
     * 消息详情
     * h5专用
     */
    @RequestMapping("/myTopDetails")
    public R myTopDetails(@RequestBody Map<String,Object> params) {
        Integer topId = Integer.valueOf(String.valueOf(params.get("topId")));
        QueryVo queryVo = new QueryVo();
        queryVo.setTopId(topId);
        return classTopController.myTopDetails(queryVo);
    }
}
