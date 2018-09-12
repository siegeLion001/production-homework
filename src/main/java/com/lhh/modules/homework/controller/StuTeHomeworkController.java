package com.lhh.modules.homework.controller;

import com.lhh.common.state.TopicState;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.homework.domain.ExcellentHomeworkVo;
import com.lhh.modules.homework.service.HomeworkService;
import com.lhh.modules.stuhomework.controller.ParamsVo;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("stu/homework")
public class StuTeHomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;

    /**
     * 优秀作业 评优 小题 大题更改状态
     * 展示大题 没有小题
     */

    /**
     * 优秀作业 一级列表 展示所有包含优秀题目的作业
     * 一级列表
     */
    @RequestMapping("/excellentHomework")
    public R excellentHomework(@RequestBody Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ExcellentHomeworkVo> excellentHomeworkVoList = homeworkService.excellentHomework(query);
        int total = homeworkService.excellentHomeworkTotal(query);
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("page", pageUtil).put("data", excellentHomeworkVoList);
    }


    /**
     * 优秀作业 题目结构 细分到大题 下方包含学生id列表
     * 二级列表
     */
    @RequestMapping("/excellentTopicClusterList")
    public R excellentTopicClusterList(@RequestBody ParamsVo paramsVo) {
        Integer homeworkId = paramsVo.getHomeworkId();
        Map qm = new HashMap();
        qm.put("homeworkId", homeworkId);
        qm.put("excellent", TopicState.EXCELLENT.getCode().intValue());
        List list = stuTopicClusterService.excellentStuTopicCluster(qm);
        return R.ok(1).put("data", list);
    }


    /**
     * 优秀作业 题目详情 根据学生id和大题号 查询
     * 题目内容
     */

    @RequestMapping("/excellentInfo")
    public R excellentInfo(@RequestBody QueryVo queryVo) {
        Integer homeworkId = queryVo.getHomeworkId();
        String stuId = queryVo.getStuId();
        String bNum = queryVo.getbNum();
        Map qm = new HashMap();
        qm.put("homeworkId", homeworkId);
        qm.put("stuId", stuId);
        qm.put("bNum", bNum);
        qm.put("publish", false);
        List list = stuTopicClusterService.queryTopicClusterAllfield(qm);
        return R.ok(1).put("data", list);
    }


}

