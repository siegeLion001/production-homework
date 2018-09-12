package com.lhh.modules.stuhomework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.lhh.common.state.HomeworkStatus;
import com.lhh.common.state.StuMacroStatus;
import com.lhh.common.state.StuStatus;
import com.lhh.common.state.TchMacroStatus;
import com.lhh.common.state.TchStatus;
import com.lhh.common.type.CorrectType;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homework.service.HomeworkService;
import com.lhh.modules.stuMistakeBook.service.StuMistakeBookService;
import com.lhh.modules.stuhomework.controller.correcthandler.ClassMutualCorrectHandler;
import com.lhh.modules.stuhomework.controller.correcthandler.CorrectHandler;
import com.lhh.modules.stuhomework.controller.correcthandler.OneSelfCorrectHandler;
import com.lhh.modules.stuhomework.domain.BackVo;
import com.lhh.modules.stuhomework.domain.ScoreVo;
import com.lhh.modules.stuhomework.domain.StuHomeworkVo;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.stutopic.entity.po.CorrectImg;
import com.lhh.modules.stutopic.service.StuTopicService;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;
import com.lhh.modules.topic.domain.WrongTopic;
import com.lhh.modules.topic.service.TopicService;


/**
 * 学生作业表
 *
 * @author wangcheng
 * @date 2018-05-15 11:57:24
 */
@RestController
@RequestMapping("tch/stuhomework")
public class TchTeStuHomeworkController {
    @Autowired
    private StuHomeworkService stuHomeworkService;
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private StuTopicService stuTopicService;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private StuHomeworkController stuHomeworkController;
    @Autowired
    private StuMistakeBookService stuMistakeBookService;

    /**
     * 作业打回
     */
    @RequestMapping("/stuHomeworkBack")
    public R stuHomeworkBack(@RequestBody BackVo backVo) {
        String backReason = backVo.getBackReason();
        Map qm = new HashMap();
        Integer homeworkId = backVo.getHomeworkId();
        String stuId = backVo.getStuId();
        qm.put("homeworkId", homeworkId);
        qm.put("stuId", stuId);
        StuHomeworkEntity stuHomeworkEntity = stuHomeworkService.queryByHomeworkIdAndStuId(backVo.getHomeworkId(), backVo.getStuId());
        if (stuHomeworkEntity == null) {
            return R.error("未找到相关作业信息");
        }
        stuHomeworkEntity.setBackReason(backReason);
        stuHomeworkEntity.setStuState(StuStatus.BACK.getState());
        stuHomeworkEntity.setTchState(TchStatus.NOCORRECT.getState());
        stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.AGAINDO.getState());
        stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.NOCORRECT.getState());
        stuHomeworkService.update(stuHomeworkEntity);

        /**
         * 删除 大题和小题
         */
        Map qmt = new HashMap();
        qmt.put("homeworkId", homeworkId);
        qmt.put("stuId", stuId);
        stuTopicClusterService.delete4Map(qmt);
        stuTopicService.delete4Map(qmt);

        return R.ok(1);
    }


    /**
     * 作业评价
     */
    @RequestMapping("/evaluate")
    public R evaluate(@RequestBody StuHomeworkVo stuHomeworkVo) {
        StuHomeworkEntity stuHomeworkEntity = stuHomeworkService.queryObject(stuHomeworkVo.getStuHomeworkId());
        stuHomeworkEntity.setEvaluate(stuHomeworkVo.getEvaluate());
        stuHomeworkService.update(stuHomeworkEntity);
        return R.ok(1);
    }

    /**
     * 作业打分 + 订正
     */
    @RequestMapping("/stuHomeworkScore")
    public R stuHomeworkScoreCurrency(@RequestBody ScoreVo scoreVo) {

        if (scoreVo.getScoreType().intValue() == 1) {//按人批改
            return stuHomeworkScore(scoreVo);
        } else {//按题批改

            return stuHomeworkScoreByTopic(scoreVo);
        }
    }

    /**
     * 按照题批改打分
     *
     * @param scoreVo
     * @return
     */
    public R stuHomeworkScoreByTopic(@RequestBody ScoreVo scoreVo) {
        String classId = scoreVo.getClassId();
        StuTopicClusterVo[] stuTopicClusterList = scoreVo.getTopicClusterList();
        String type = scoreVo.getType();
        for (StuTopicClusterVo stuTopicClusterVo : stuTopicClusterList) {

            /**
             * 根据作业id跟学生id  找到作业
             */
            String stuId = stuTopicClusterVo.getStuId();
            Integer homeworkId = stuTopicClusterVo.getHomeworkId();
            StuHomeworkEntity stuHomeworkEntity = stuHomeworkService.queryByHomeworkIdAndStuId(homeworkId, stuId);

            if (stuHomeworkEntity == null) {
                return R.error("找不到作业相关信息");
            }


            stuTopicClusterVo.setId(stuTopicClusterVo.getStuTopicClusterId());
            List<StuTopicVo> stuTopicVoList = stuTopicClusterVo.getTopicList();
            for (StuTopicVo stuTopicVo : stuTopicVoList) {
                stuTopicVo.setId(stuTopicVo.getStuTopicId());

                StuTopicEntity stuTopicEntity = new StuTopicEntity();
                stuTopicEntity.setId(stuTopicVo.getStuTopicId());
                stuTopicEntity.setRevisal(stuTopicVo.getRevisal());
                stuTopicEntity.setActualScore(stuTopicVo.getActualScore());

                // 是否批阅
                stuTopicEntity.setMark(stuTopicVo.getMark());
                // 收藏
                stuTopicEntity.setCollect(stuTopicVo.getCollect());
                //评优
                stuTopicEntity.setExcellent(stuTopicVo.getExcellent());

                stuTopicService.update(stuTopicVo);
                // 判断题目真实得分和题目分数   是否添加到学生错题本
                /*if(HomeworkType.DailyWork.getCode() ==type){
                    Float actualScore = stuTopicVo.getActualScore();
                    Float sScore = stuTopicVo.getSScore();
                    if(sScore !=0.0 && sScore>actualScore){
                        // 获取信息  保存到错题本
//                        stuMistakeBookSave(stuTopicVo);
                        String bNum = stuTopicVo.getBNum();
                        String sNum = stuTopicVo.getSNum();
                        stuMistakeBookService.insertMistakeBook(homeworkId,stuId,classId,bNum,sNum);
                    }
                }*///学生错题本
            }

            StuTopicClusterEntity stuTopicClusterEntity = new StuTopicClusterEntity();
            stuTopicClusterEntity.setId(stuTopicClusterVo.getId());

            // 收藏
            stuTopicClusterEntity.setCollect(stuTopicClusterVo.getCollect());
            //评优
            stuTopicClusterEntity.setExcellent(stuTopicClusterVo.getExcellent());

            /**
             * 根据学生单题 来确定整个大题属于 未批改 已批改 批改中
             * 原理 分组 长度为2 批改中 为1 判断值，如果是 0 未批阅 1 已批阅
             */

            Map qm2 = new HashMap();
            qm2.put("stuId", stuId);
            qm2.put("homeworkId", homeworkId);
            qm2.put("bNum", stuTopicClusterVo.getBNum());
            Boolean topicClusteraBoolean = stuTopicService.haveNoMark(qm2);
            if (topicClusteraBoolean) {
                //有未批阅的
                stuTopicClusterVo.setMark(2);
            } else {
                // 没有未批阅的
                stuTopicClusterVo.setMark(1);
            }

            stuTopicClusterService.update(stuTopicClusterVo);

            Map qm = new HashMap();
            qm.put("stuId", stuId);
            qm.put("homeworkId", homeworkId);
            Boolean aBoolean = stuTopicService.haveMark(qm);
            // 如果该学生的某个作业所有题目都批改过
            if (aBoolean) {
                /**
                 * 设置某个学生的作业状态
                 */
                Map qm1 = new HashMap();
                qm1.put("stuId", stuId);
                qm1.put("homeworkId", homeworkId);

                // 是否有订正的题目
                Boolean haveRevisal = stuTopicService.haveRevisal(qm1);

                if (haveRevisal) {
                    /**
                     * 订正作业  教师 待订正 未批阅
                     */
                    stuHomeworkEntity.setTchState(TchStatus.WAITREVISING.getState());
                    stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.NOCORRECT.getState());
                    stuHomeworkEntity.setStuState(StuStatus.WAITREVISING.getState());
                    stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.AGAINDO.getState());
                } else {
                    stuHomeworkEntity.setTchState(TchStatus.YESCORRECT.getState());
                    stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.YESCORRECT.getState());
                    stuHomeworkEntity.setStuState(StuStatus.GRADE.getState());
                    stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.DONE.getState());
                }

                stuHomeworkService.update(stuHomeworkEntity);

                /**
                 * 给某次作业设置状态
                 */
                //判断一下是否所有作业都批改了 如果都批改了  homework设置为以已归档（已批改）
                boolean b = stuHomeworkService.allHomeworkIsTchYesCorrect(stuHomeworkEntity.getHomeworkId());
                if (b) {
                    HomeworkEntity homeworkEntity = homeworkService.queryObject(stuHomeworkEntity.getHomeworkId());
                    homeworkEntity.setState(HomeworkStatus.ARCHIVE.getState());
                    homeworkService.update(homeworkEntity);
                }
            }
        }
        return R.ok(1);
    }

    /**
     * 按人批改
     *
     * @param scoreVo
     * @return
     */
    public R stuHomeworkScore(@RequestBody ScoreVo scoreVo) {
        Integer homeworkId = scoreVo.getHomeworkId();
        String stuId = scoreVo.getStuId();
        String type = scoreVo.getType();
        String classId = scoreVo.getClassId();
        StuHomeworkEntity stuHomeworkEntity = stuHomeworkService.queryByHomeworkIdAndStuId(homeworkId, stuId);
        if (stuHomeworkEntity == null) {
            return R.error("找不到作业相关信息");
        }
        StuTopicClusterVo[] stuTopicClusterList = scoreVo.getTopicClusterList();
        // 题目中是否有订正
        Boolean homeworkRevisal = false;
        for (StuTopicClusterVo stuTopicClusterVo : stuTopicClusterList) {
            if (homeworkRevisal == false) {
                homeworkRevisal = stuTopicClusterVo.getRevisal().intValue() == 1 ? true : false;
            }
            stuTopicClusterVo.setId(stuTopicClusterVo.getStuTopicClusterId());
            List<StuTopicVo> stuTopicVoList = stuTopicClusterVo.getTopicList();
            for (StuTopicVo stuTopicVo : stuTopicVoList) {
                stuTopicVo.setId(stuTopicVo.getStuTopicId());
                if (homeworkRevisal == false) {
                    homeworkRevisal = stuTopicVo.getRevisal().intValue() == 1 ? true : false;
                }
//                CorrectImg[] cmg = stuTopicVo.getCorrectImgs();
                StuTopicEntity stuTopicEntity = new StuTopicEntity();
                stuTopicEntity.setId(stuTopicVo.getStuTopicId());
                stuTopicEntity.setRevisal(stuTopicVo.getRevisal());
                stuTopicEntity.setActualScore(stuTopicVo.getActualScore());
//                stuTopicEntity.setCorrectImgs(FileUtil.getStrByReplaceBase(cmg));

                // 是否批阅
                stuTopicEntity.setMark(stuTopicVo.getMark());
                // 收藏
                stuTopicEntity.setCollect(stuTopicVo.getCollect());
                //评优
                stuTopicEntity.setExcellent(stuTopicVo.getExcellent());
                // 判断题目是否正确
                /*if(HomeworkType.DailyWork.getCode().equals(type)){
                    Float actualScore = stuTopicVo.getActualScore();
                    Float sScore = stuTopicVo.getSScore();
                    if(sScore !=0.0 && sScore>actualScore){
                        String bNum = stuTopicVo.getBNum();
                        String sNum = stuTopicVo.getSNum();
                        // 获取信息  保存到错题本
                        stuMistakeBookService.insertMistakeBook(homeworkId, stuId, classId, bNum, sNum);

                    }
                }*///学生错题本

                stuTopicService.update(stuTopicVo);
            }

            StuTopicClusterEntity stuTopicClusterEntity = new StuTopicClusterEntity();
            stuTopicClusterEntity.setId(stuTopicClusterVo.getId());
            //按照人批改 大题 已批阅
            stuTopicClusterEntity.setMark(1);

            // 收藏
            stuTopicClusterEntity.setCollect(stuTopicClusterVo.getCollect());
            //评优
            stuTopicClusterEntity.setExcellent(stuTopicClusterVo.getExcellent());

            stuTopicClusterService.update(stuTopicClusterVo);
        }

        stuHomeworkEntity.setStuState(StuStatus.GRADE.getState());
        stuHomeworkEntity.setTchState(TchStatus.YESCORRECT.getState());
        stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.DONE.getState());
        stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.YESCORRECT.getState());


        if (homeworkRevisal) {
            /**
             * 订正作业  教师 待订正 未批阅
             */
            stuHomeworkEntity.setTchState(TchStatus.WAITREVISING.getState());
            stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.NOCORRECT.getState());

            stuHomeworkEntity.setStuState(StuStatus.WAITREVISING.getState());
            stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.AGAINDO.getState());
        }
        /**
         * 作业计算总分
         */
        stuId = stuHomeworkEntity.getStuId();
        homeworkId = stuHomeworkEntity.getHomeworkId();
        Float actualScore = stuHomeworkService.getActualScore(stuId, homeworkId);
        stuHomeworkEntity.setActualScore(actualScore);

        //作业评价
        stuHomeworkEntity.setEvaluate(scoreVo.getEvaluate());
        stuHomeworkService.update(stuHomeworkEntity);


        //判断一下是否所有作业都批改了 如果都批改了  homework设置为以已归档（已批改）
        boolean b = stuHomeworkService.allHomeworkIsTchYesCorrect(stuHomeworkEntity.getHomeworkId());
        if (b) {
            HomeworkEntity homeworkEntity = homeworkService.queryObject(stuHomeworkEntity.getHomeworkId());
            homeworkEntity.setState(HomeworkStatus.ARCHIVE.getState());
            homeworkService.update(homeworkEntity);
        }
        return R.ok(1);
    }

    /**
     * 未批改作业查询
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/notCorrectHomework")
    public R notCorrectHomework(@RequestParam Map<String, Object> params) {
        params.put("tchMacroStatus", TchMacroStatus.NOCORRECT.getState());
        Query query = new Query(params);
        List<StuHomeworkVo> queryListByWhere = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryTotal(query);
        Map<String, Object> map = new HashMap<>();
        for (StuHomeworkVo stuHomeworkVo : queryListByWhere) {
            Integer homeworkId = stuHomeworkVo.getHomeworkId();
            //查询班级人数
            map.clear();
            map.put("homeworkId", homeworkId);
            int queryTotal = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setHeadCount(queryTotal);
            //查询学生
            map.put("stuMacroStatus", StuMacroStatus.DONE.getState());
            int submitCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setSubmitCount(submitCount);
            //查询教师未批阅
            map.clear();
            map.put("homeworkId", homeworkId);
            map.put("tchMacroStatus", TchMacroStatus.NOCORRECT.getState());
            int notReadCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setNotReadCount(notReadCount);
            //查询教师订正
            map.put("tchMacroStatus", TchMacroStatus.REVISING.getState());
            int correctCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setCorrectCount(correctCount);
        }

        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("page", pageUtil).put("data", queryListByWhere);
    }

    /**
     * 已批改作业查询
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/perusalHomework")
    public R perusalHomework(@RequestParam Map<String, Object> params) {
        params.put("state", TchMacroStatus.YESCORRECT.getState());
        Query query = new Query(params);
        List<StuHomeworkVo> queryListByWhere = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryTotal(query);
        Map<String, Object> map = new HashMap<>();
        for (StuHomeworkVo stuHomeworkVo : queryListByWhere) {
            Integer homeworkId = stuHomeworkVo.getHomeworkId();
            //查询班级人数
            map.clear();
            map.put("homeworkId", homeworkId);
            int queryTotal = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setHeadCount(queryTotal);
            //查询学生
            map.put("stuMacroStatus", StuMacroStatus.DONE.getState());
            int submitCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setSubmitCount(submitCount);
            //查询教师未批阅
            map.clear();
            map.put("homeworkId", homeworkId);
            map.put("tchMacroStatus", TchMacroStatus.NOCORRECT.getState());
            int notReadCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setNotReadCount(notReadCount);
            //查询教师订正
            map.put("tchMacroStatus", TchMacroStatus.REVISING.getState());
            int correctCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setCorrectCount(correctCount);
        }

        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("page", pageUtil).put("data", queryListByWhere);
    }

    /**
     * 订正作业查询
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/revisalHomework")
    public R revisalHomework(@RequestParam Map<String, Object> params) {
        params.put("tchMacroStatus", TchMacroStatus.REVISING.getState());
        Query query = new Query(params);
        List<StuHomeworkVo> queryListByWhere = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryTotal(query);
        Map<String, Object> map = new HashMap<>();
        for (StuHomeworkVo stuHomeworkVo : queryListByWhere) {
            Integer homeworkId = stuHomeworkVo.getHomeworkId();
            //查询班级人数
            map.clear();
            map.put("homeworkId", homeworkId);
            int queryTotal = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setHeadCount(queryTotal);
            //查询学生
            map.put("stuMacroStatus", StuMacroStatus.DONE.getState());
            int submitCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setSubmitCount(submitCount);
            //查询教师未批阅
            map.clear();
            map.put("homeworkId", homeworkId);
            map.put("tchMacroStatus", TchMacroStatus.NOCORRECT.getState());
            int notReadCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setNotReadCount(notReadCount);
            //查询教师订正
            map.put("tchMacroStatus", TchMacroStatus.REVISING.getState());
            int correctCount = stuHomeworkService.queryTotal(map);
            stuHomeworkVo.setCorrectCount(correctCount);
        }
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("page", pageUtil).put("data", queryListByWhere);
    }

    /**
     * 未归档作业查询
     *
     * @param params
     * @return
     */
    @RequestMapping("/unArchiveHList")
    public R unArchiveHList(@RequestBody Map<String, Object> params) {
        params.put("state", HomeworkStatus.UNARCHIVE.getState());
        Query query = new Query(params);

        R r = homeworkService.queryArchive4State(query);

        return r;

    }
    /**
     * 作业修改(归档/未归档等状态修改)
     * @name: updateHomework
     * @params: [homeworkEntity]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/9/7
     */
    @RequestMapping("/homeworkChange")
    public R updateHomework (@RequestBody HomeworkEntity homeworkEntity){
        Integer id = homeworkEntity.getId();
        if(id ==null || id==0){
            return R.error("作业id不能为空");
        }
        homeworkService.update(homeworkEntity);
        return R.ok(1);
    }

    /**
     * 已归档作业查询
     *
     * @param params
     * @return
     */
    @RequestMapping("/archiveHList")
    public R archiveHList(@RequestBody Map<String, Object> params) {
        params.put("state", HomeworkStatus.ARCHIVE.getState());
        Query query = new Query(params);
        R r = homeworkService.queryArchive4State(query);

        return r;
    }

    /**
     * @Description: 作业详情查询 (按人查询)
     * @Name: queryDetailByStu
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/1
     */
    @RequestMapping("/queryDetailByStu")
    @Deprecated
    public R queryDetailByStu(@RequestBody Map<String, Object> params) {
        //必要查询条件    homeworkId  stu_id
        //查询homework表获取题目详情
        //查询stu_homework表及题簇 单题表
        //创建map  封装题目信息,答题卡(题簇及单题 答题信息)
        String homeworkId = params.get("homeworkId").toString();
        String stuId = params.get("stuId").toString();

        if (StringUtils.isBlank(homeworkId)) {
            return R.error("请上传作业id");
        }
        if (StringUtils.isBlank(stuId)) {
            return R.error("请上传学生id");
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("homeworkId", params.get("homeworkId"));
        resultMap.put("stuId", params.get("stuId"));
        HomeworkEntity homeworkEntity = homeworkService.queryObject(Integer.valueOf(homeworkId));

        JSONArray content = homeworkEntity.getContent();
        if (content != null && content.size() > 0) {
            String tmpStr = FileUtil.getStrReplacePlaceholder(homeworkEntity.getContent().toJSONString());
            JSONArray reContent = JSONArray.parseArray(tmpStr);
            homeworkEntity.setContent(reContent);
        }
        //查询题簇及单体表

        Map qm = new HashMap();
        qm.putAll(params);
        qm.remove("type");
        List<StuTopicClusterVo> stuTopicClusterEntities = stuTopicClusterService.queryVoList(qm);

        for (StuTopicClusterVo stuTopicClusterVo : stuTopicClusterEntities) {
            Integer stuTopicClusterEntityId = stuTopicClusterVo.getId();
            params.put("stuTopicClusterId", stuTopicClusterEntityId);

            /**
             * submitContent 类型给改变
             */
            CorrectImg[] com = FileUtil.getReplacePlaceholder(stuTopicClusterVo.getSubmitContent());
            stuTopicClusterVo.setSubmitContent(com);

            //查询所有单题
            Map qm2 = new HashMap();
            qm2.put("stuTopicClusterId", stuTopicClusterVo.getId());

            List<StuTopicVo> stuTopicVoList = stuTopicService.queryList4Vo(qm2);
            for (StuTopicVo stuTopicVo : stuTopicVoList) {
                CorrectImg[] submitContent = FileUtil.getReplacePlaceholder(stuTopicVo.getSubmitContent());
                stuTopicVo.setSubmitContent(submitContent);
            }
            stuTopicClusterVo.setTopicList(stuTopicVoList);
        }
        resultMap.put("stuTopicClusterList", stuTopicClusterEntities);
        return R.ok(1).put("data", resultMap);

    }

    /**
     * 按题批改 返回信息
     * <p>
     * 最外层 学生数组
     * 大题数组
     * 单题数据
     */
    @RequestMapping("/correctDataStuGroup")
    public R correctDataStuGroup(@RequestBody ParamsVo paramsVo) {
        Integer homeworkId = paramsVo.getHomeworkId();
        String bNum = paramsVo.getbNum();
        Map map = new HashMap();
        map.put("homeworkId", homeworkId);
        map.put("bNum", bNum);
        List<StuTopicClusterEntity> stuTopicClusterEntityList = stuTopicClusterService.queryList(map);
        List<StuTopicClusterVo> stuTopicClusterVoList = new ArrayList<>();
        for (StuTopicClusterEntity stuTopicClusterEntity : stuTopicClusterEntityList) {
            Integer stuTopicClusteId = stuTopicClusterEntity.getId();
            Map qm = new HashMap();
            qm.put("stuTopicClusteId", stuTopicClusteId);
            if (paramsVo.getsNum() != null) {
                qm.put("sNum", paramsVo.getsNum());
            }
            List<StuTopicVo> stuTopicVoList = stuTopicService.queryList4Vo(qm);
            StuTopicClusterVo stuTopicClusterVo = new StuTopicClusterVo();
            BeanUtils.copyProperties(stuTopicClusterEntity, stuTopicClusterVo);
            stuTopicClusterVo.setTopicList(stuTopicVoList);
            stuTopicClusterVoList.add(stuTopicClusterVo);
        }
        return R.ok(1).put("data", stuTopicClusterVoList);
    }

    /**
     * @Description: 教师端批改  按人 二级列表
     * @Name: queryListByHome
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/7
     */
    @RequestMapping("/queryListByHome")
    public R queryListByHome(@RequestBody Map<String, Object> params) {
        /*
            根据作业id  班级id  教师id查询所有学生及状态
            查询 基本作业信息   按时提交人数  未提交人数,迟提交人数  订正人数
         */
        String homeworkId = params.get("homeworkId").toString();
        String classId = params.get("classId").toString();
//        String tchId = params.get("tchId").toString();
        if (StringUtils.isBlank(homeworkId) || StringUtils.isBlank(classId)) {
            return R.error("请输入作业id, 班级id ");
        }

        Map<Object, Object> reslultMap = new HashMap<>();        //总人数
        int total = stuHomeworkService.querTotal4Map(params);
        params.put("unPunctuality", "unPunctuality");
//        List<StuHomeworkVo> totalList  = stuHomeworkService.queryStu4Map(params);
        //未准时提交人数
        int unPunctualityCount = stuHomeworkService.querTotal4Map(params);
        List<StuHomeworkVo> unPunctualityList = stuHomeworkService.queryStu4Map(params);
        Map<String, Object> param = new HashMap<>();
        for (StuHomeworkVo stuHomeworkVo : unPunctualityList) {

            param.put("homeworkId", stuHomeworkVo.getHomeworkId());
            param.put("stuId", stuHomeworkVo.getId());
            Boolean aBoolean = stuTopicService.haveMark(param);
            if (aBoolean)
                stuHomeworkVo.setTchState(TchStatus.CORRECTING.getState());

        }
        Map<String, Object> unPunctualityMap = new HashMap<>();
        unPunctualityMap.put("number", unPunctualityCount + "/" + total);
        unPunctualityMap.put("unPunctualityList", unPunctualityList);
        reslultMap.put("unPunctualityMap", unPunctualityMap);
        //准时提交人数
        params.remove("unPunctuality");
        params.put("punctuality", "punctuality");
        int punctualityCount = stuHomeworkService.querTotal4Map(params);
        List<StuHomeworkVo> punctualityList = stuHomeworkService.queryStu4Map(params);
        for (StuHomeworkVo stuHomeworkVo : punctualityList) {
            param.put("homeworkId", stuHomeworkVo.getHomeworkId());
            param.put("stuId", stuHomeworkVo.getId());
            Boolean aBoolean = stuTopicService.haveMark(param);
            if (aBoolean)
                stuHomeworkVo.setTchState(TchStatus.CORRECTING.getState());
        }
        Map<String, Object> punctualityMap = new HashMap<>();
        punctualityMap.put("number", punctualityCount + "/" + total);
        punctualityMap.put("punctualityList", punctualityList);
        reslultMap.put("punctualityMap", punctualityMap);
        //未提交人数
        params.remove("punctuality");
        params.put("stuMacroStatus", StuMacroStatus.WAITDO.getState());
        int unCommitCount = stuHomeworkService.querTotal4Map(params);
        List<StuHomeworkVo> unCommitList = stuHomeworkService.queryStu4Map(params);
        Map<String, Object> unCommitMap = new HashMap<>();
        unCommitMap.put("number", unCommitCount + "/" + total);
        unCommitMap.put("unCommitList", unCommitList);
        reslultMap.put("unCommitMap", unCommitMap);
        //订正人数
        params.remove("stuMacroStatus");
        params.put("tchMacroStatus", TchMacroStatus.REVISING.getState());
        int correctCount = stuHomeworkService.querTotal4Map(params);
        List<StuHomeworkVo> correctList = stuHomeworkService.queryStu4Map(params);
        for (StuHomeworkVo stuHomeworkVo : correctList) {
            param.put("homeworkId", stuHomeworkVo.getHomeworkId());
            param.put("stuId", stuHomeworkVo.getId());
            Boolean aBoolean = stuTopicService.haveMark(param);
            if (aBoolean)
                stuHomeworkVo.setTchState(TchStatus.CORRECTING.getState());
        }
        Map<String, Object> correctMap = new HashMap<>();
        correctMap.put("number", correctCount + "/" + total);
        correctMap.put("correctList", correctList);
        reslultMap.put("correctMap", correctMap);
        reslultMap.put("code", 1);
        return R.ok(1).put("data", reslultMap);
    }


    /**
     * 班级错题本
     * 结构
     * <p>
     * topic list
     * <p>
     * 包含  stuTopicList
     * 正确的题数
     * 错误的题数
     */
    @RequestMapping("/classWrongTopic")
    public R classWrongTopic(@RequestBody Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<WrongTopic> wrongTopics = topicService.queryClassWrongTopicList(query);
        int total = topicService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil).put("data", wrongTopics);
    }


    /**
     * 教师端查询作业详情
     */
    @RequestMapping("/queryDetails")
    public R queryDetails(@RequestBody ParamsVo paramsVo) {
        paramsVo.setPublish(true);
        return stuHomeworkController.queryDetails(paramsVo);
    }


    /**
     * 班级错题本 查询所有学生
     */
    @RequestMapping("/selectAllStuId")
    public R selectAllStuId(@RequestBody Map<String, Object> params) {
        List<String> strings = stuHomeworkService.selectAllStuId(params);
        return R.ok(1).put("students", strings);
    }

    /**
     * 修改批改类型
     */
    @RequestMapping("/correctType")
    public R correctType(@RequestBody CorrectTypeVo correctTypeVo) {

        Integer correctType = correctTypeVo.getCorrectType();
        Integer homeworkId = correctTypeVo.getHomeworkId();
        /**
         * 获取作业id
         */
        HomeworkEntity homeworkEntity = homeworkService.queryObject(homeworkId);
        if (homeworkEntity == null) {
            return R.error("不存在的作业");
        }
        List<StuHomeworkEntity> correct = null;
        if (correctType.intValue() == CorrectType.OneSelf.getCode().intValue()) {
            CorrectHandler correctHandler = new OneSelfCorrectHandler(stuHomeworkService);
            correct = correctHandler.correct(homeworkEntity);
        } else if (correctType.intValue() == CorrectType.ClassMutual.getCode().intValue()) {
            CorrectHandler correctHandler = new ClassMutualCorrectHandler(stuHomeworkService);
            correct = correctHandler.correct(homeworkEntity);
        }
        /**
         * 批量更新
         */
        if (correct != null) {
            stuHomeworkService.batchUpdate(correct);
        }
        return R.ok(1);
    }
}



