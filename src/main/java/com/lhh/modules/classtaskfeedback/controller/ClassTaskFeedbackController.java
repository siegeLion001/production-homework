package com.lhh.modules.classtaskfeedback.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classtaskfeedback.entity.ClassTaskFeedbackEntity;
import com.lhh.modules.classtaskfeedback.service.ClassTaskFeedbackService;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
@RestController
@RequestMapping("classtaskfeedback")
public class ClassTaskFeedbackController {
    @Autowired
    private ClassTaskFeedbackService classTaskFeedbackService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("classtaskfeedback:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ClassTaskFeedbackEntity> classTaskFeedbackList = classTaskFeedbackService.queryList(query);
        int total = classTaskFeedbackService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(classTaskFeedbackList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("classtaskfeedback:info")
    public R info(@PathVariable("id") Integer id) {
        ClassTaskFeedbackEntity classTaskFeedback = classTaskFeedbackService.queryObject(id);

        return R.ok().put("classTaskFeedback", classTaskFeedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("classtaskfeedback:save")
    public R save(@RequestBody ClassTaskFeedbackEntity classTaskFeedback) {
        classTaskFeedbackService.save(classTaskFeedback);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("classtaskfeedback:update")
    public R update(@RequestBody ClassTaskFeedbackEntity classTaskFeedback) {
        classTaskFeedbackService.update(classTaskFeedback);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("classtaskfeedback:delete")
    public R delete(@RequestBody Integer[] ids) {
        classTaskFeedbackService.deleteBatch(ids);

        return R.ok();
    }
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
        /*
         * 根据班级id topId 查询 任务反馈表  验证classId, topId 是否为空
         */
        /*
         * 统计任务反馈人数
         * 任务量
         * 难易程度
         * 平均用时
         */
        Map<String,Object> avgDataMap = classTaskFeedbackService.queryAvgData(params);
        //评论结果: 优秀/良好/合格待/合格 百分比
        Map<String, Object> remarkMap = classTaskFeedbackService.queryRemark(params);
        //完成情况  姓名 任务完成时长  成绩
        List<ClassTaskFeedbackEntity> feedBackList = classTaskFeedbackService.queryList(params);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.putAll(avgDataMap);
        resultMap.putAll(remarkMap);
        resultMap.put("feedBackist",feedBackList);
        return R.ok(1).put("data",resultMap);
    }
    /**
     *
     * @name: feedBackList
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/17
     */
    @RequestMapping("/feedBackList")
    public R feedBackList (@RequestBody Map<String,Object> params){
        List<ClassTaskFeedbackEntity> feedBackList = classTaskFeedbackService.queryList(params);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("feedBackList",feedBackList);
        return R.ok(1).put("data",resultMap);
    }

}
