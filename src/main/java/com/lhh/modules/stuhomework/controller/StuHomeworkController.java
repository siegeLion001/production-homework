package com.lhh.modules.stuhomework.controller;

import java.util.Date;
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

import com.lhh.common.type.TopicType;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.stuhomework.domain.StuHomeworkVo;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopic.service.StuTopicService;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("stuhomework")
public class StuHomeworkController {
    @Autowired
    private StuHomeworkService stuHomeworkService;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;
    @Autowired
    private StuTopicService stuTopicService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//	@RequiresPermissions("stuhomework:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<StuHomeworkEntity> stuHomeworkList = stuHomeworkService.queryList(query);
        int total = stuHomeworkService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(stuHomeworkList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("stuhomework:info")
    public R info(@PathVariable("id") Integer id) {
        StuHomeworkEntity stuHomework = stuHomeworkService.queryObject(id);

        return R.ok().put("stuHomework", stuHomework);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("stuhomework:save")
    public R save(@RequestBody StuHomeworkEntity stuHomework) {
        stuHomeworkService.save(stuHomework);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("stuhomework:update")
    public R update(@RequestBody StuHomeworkEntity stuHomework) {
        stuHomeworkService.update(stuHomework);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("stuhomework:delete")
    public R delete(@RequestBody Integer[] ids) {
        stuHomeworkService.deleteBatch(ids);

        return R.ok();
    }


    /**
     * 查询学生作业详情
     * <p>
     * 通用接口
     * 适用与按照题目批改
     * 按照人批改
     * 可以查询单题（大题或小题）
     */
    @RequestMapping("/queryDetails")
    public R queryDetails(@RequestBody ParamsVo paramsVo) {

        Map qm1 = new HashMap();
        String stuId = paramsVo.getStuId();
        Integer homeworkId = paramsVo.getHomeworkId();

        qm1.put("stuId", stuId);
        qm1.put("homeworkId", homeworkId);

        StuHomeworkVo stuHomeworkVo = stuHomeworkService.queryStuHomeworkVo(qm1);

        if (stuHomeworkVo == null) {
            return R.error("没有作业信息");
        }
        String classId = stuHomeworkVo.getClassId();
        stuHomeworkVo.setContent(FileUtil.getListReplacePlaceholder(stuHomeworkVo.getContent()));
        stuHomeworkVo.setAnswerImgs(FileUtil.getListReplacePlaceholder(stuHomeworkVo.getAnswerImgs()));
        Long publishTime = stuHomeworkVo.getPublishTime().getTime();
        Boolean publish = paramsVo.getPublish();
        if(publish){
            stuHomeworkVo.setPublish(publish);
        }else{
            Long cruuntTime = new Date().getTime();
            publish = (cruuntTime - publishTime) > 0 ? true : false;
            stuHomeworkVo.setPublish(publish);
        }

        String type = stuHomeworkVo.getType();

        Map qm = new HashMap();
        qm.put("homeworkId", homeworkId);
        qm.put("stuId", stuId);

        //查询总分 主观题得分 客观题得分
        //班级平均分 班级最高分 班级平均用时
        Map actualScoreDetail = new HashMap();
        List<Map> actualScoreDetails = stuTopicService.queryStuScoreRank(qm);
        if (actualScoreDetails.size() > 0 && actualScoreDetails != null) {
            actualScoreDetail.putAll(actualScoreDetails.get(0));
        }
        Map numStatistics = stuHomeworkService.numStatistics(classId, homeworkId);
        if (numStatistics != null) {
            actualScoreDetail.putAll(numStatistics);
        }
        stuHomeworkVo.setActualScoreDetail(actualScoreDetail);

        if ("2".equals(type)) {
            return R.ok(1).put("data", stuHomeworkVo);
        } else if ("1".equals(type) || "3".equals(type) || "4".equals(type)) {
            Map qm2 = new HashMap();
            qm2.put("stuId", stuId);
            qm2.put("homeworkId", homeworkId);
            qm2.put("publish", publish);
            qm2.put("homeworkType", stuHomeworkVo.getType());

            qm2.put("sidx", "b_num");
            qm2.put("order", "ASC");
            String sNum = paramsVo.getsNum();
            String bNum = paramsVo.getbNum();

            if (sNum != null) {
                qm2.put("sNum", sNum);
            }
            if (bNum != null) {
                qm2.put("bNum", bNum);
            }

            List<StuTopicClusterVo> stuTopicClusterVoList = stuTopicClusterService.queryTopicClusterAllfield(qm2);
            Integer rightCount = 0;
            Float actualScore = 0F;
            for (StuTopicClusterVo stuTopicClusterVo : stuTopicClusterVoList) {
                Integer rightCount1 = stuTopicClusterVo.getRightCount();
                Float actualScore1 = stuTopicClusterVo.getActualScore();
                if(rightCount1!=null)
                    rightCount = rightCount1+rightCount;
                if(actualScore1!=null)
                    actualScore = actualScore1+actualScore;

            }
            stuHomeworkVo.setRightCount(rightCount);
            stuHomeworkVo.setActualScore(actualScore);

            stuHomeworkVo.setTopicClusterList(stuTopicClusterVoList);
            Integer id = stuHomeworkVo.getId();
            stuHomeworkVo.setStuHomeworkId(id);
            stuHomeworkVo.setId(null);

            // 主观题数量
            int subjective = 0;
            // 客观题数量
            int objective = 0;
            if (stuHomeworkVo.getTopicClusterList() != null) {
                for (StuTopicClusterVo stuTopicClusterVo : stuHomeworkVo.getTopicClusterList()) {
                    if (stuTopicClusterVo.getTopicList() != null) {
                        for (StuTopicVo stuTopicVo : stuTopicClusterVo.getTopicList()) {
                            Integer t = stuTopicVo.getType();
                            if (t == TopicType.SingleChoice.getCode().intValue() || t == TopicType.MultipleChoice.getCode().intValue() || t == TopicType.Judge.getCode().intValue()) {
                                objective = objective + 1;
                            } else {
                                subjective = subjective + 1;
                            }
                        }
                    }
                }
            }
            stuHomeworkVo.setSubjective(subjective);
            stuHomeworkVo.setObjective(objective);
            return R.ok(1).put("data", stuHomeworkVo);
        } else {
            return R.error("非法作业 类型为空");
        }
    }

}
