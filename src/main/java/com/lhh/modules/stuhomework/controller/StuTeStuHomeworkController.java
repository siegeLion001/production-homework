package com.lhh.modules.stuhomework.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.state.Evaluationer;
import com.lhh.common.state.HomeworkStatus;
import com.lhh.common.state.StuMacroStatus;
import com.lhh.common.state.StuStatus;
import com.lhh.common.state.TchMacroStatus;
import com.lhh.common.state.TchStatus;
import com.lhh.common.type.DateType;
import com.lhh.common.type.HomeworkType;
import com.lhh.common.type.TopicType;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.MyDateUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homework.service.HomeworkService;
import com.lhh.modules.stuMistakeBook.service.StuMistakeBookService;
import com.lhh.modules.stuhomework.domain.ScoreVo;
import com.lhh.modules.stuhomework.domain.StuHomeworkVo;
import com.lhh.modules.stuhomework.domain.SubmitStuHomeworkVo;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.stutopic.entity.po.CorrectImg;
import com.lhh.modules.stutopic.service.StuTopicService;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;


/**
 * 学生作业表
 *
 * @author wangcheng
 * @date 2018-05-15 11:57:24
 */
@RestController
@RequestMapping("stu/stuhomework")
public class StuTeStuHomeworkController {
    @Autowired
    private StuHomeworkService stuHomeworkService;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;
    @Autowired
    private StuTopicService stuTopicService;
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private StuHomeworkController stuHomeworkController;
    @Autowired
    private TchTeStuHomeworkController tchTeStuHomeworkController;
    @Autowired
    private StuMistakeBookService stuMistakeBookService;

    /**
     * 学生作业列表
     */
    @ResponseBody
    @RequestMapping("/stuHomeworkList")
    public R lists(@RequestBody Map<String, Object> params) {
        Date date = new Date();
        params.put("currentTime", date);
        long time = date.getTime();
        //查询列表数据
        Query query = new Query(params);

        List<StuHomeworkVo> stuHomeworkList = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryListByWhereTotal(query);

        /**
         * 处理公布状态
         */
        for (StuHomeworkVo stuHomeworkVo : stuHomeworkList) {
            Integer stuState = stuHomeworkVo.getStuState();
            if (stuState != null) {
                if (StuStatus.GRADE.getState() == stuState.intValue()) {
                    long publishTime = stuHomeworkVo.getPublishTime().getTime();
                    if (publishTime - time > 0) {
                        // 公布时间 大于 当前时间
                        // 未公布
                        stuHomeworkVo.setStuState(StuStatus.UNANNOUNCED.getState());
                    }
                }
            }
        }
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("page", pageUtil).put("data", stuHomeworkList);
    }

    /**
     * 学生作业列表
     */
    @RequestMapping("/info")
    public R info1(@RequestParam Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {

        StuHomeworkVo stuHomeworkVo = stuHomeworkService.queryStuHomeworkVo(params);
        // 如果学生写过作业 那么将查询单题 题簇
        if (stuHomeworkVo.getStuState() != 0) {

            // 新建题簇volist
            List<StuTopicClusterVo> stuTopicClusterVoList = new ArrayList<>();
            // 根据条件查询题簇实体类列表
            List<StuTopicClusterEntity> stuTopicClusterEntityList = stuTopicClusterService.queryList(params);
            //  循环遍历题簇实体列表
            for (StuTopicClusterEntity stuTopicClusterEntity : stuTopicClusterEntityList) {
                // 题簇实体转题簇vo
                StuTopicClusterVo stuTopicClusterVo = new StuTopicClusterVo();
                BeanUtils.copyProperties(stuTopicClusterEntity, stuTopicClusterVo);

                params.put("stuTopicClusterId", stuTopicClusterVo.getId());
                List<StuTopicEntity> stuTopicEntityList = stuTopicService.queryList(params);

                List<StuTopicVo> stuTopicVoList = new ArrayList<>();
                for (StuTopicEntity stuTopicEntity : stuTopicEntityList) {
                    StuTopicVo stuTopicVo = new StuTopicVo();
                    BeanUtils.copyProperties(stuTopicEntity, stuTopicVo);
                    stuTopicVoList.add(stuTopicVo);
                }
                stuTopicClusterVo.setTopicList(stuTopicVoList);
                stuTopicClusterVoList.add(stuTopicClusterVo);
            }
            stuHomeworkVo.setTopicClusterList(stuTopicClusterVoList);
        }
        return R.ok(1).put("data", stuHomeworkVo);
    }

    /**
     * 学生作业提交(id)
     *
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author cuihp
     */
    @RequestMapping("/insertStuHomework")
    public R insertStuHomework(@RequestBody SubmitStuHomeworkVo submitStuHomeworkVo) {

        //获取作业id查询作业和答案
        String stuId = submitStuHomeworkVo.getStuId();
        String classId = submitStuHomeworkVo.getClassId();
        Integer homeworkId = submitStuHomeworkVo.getHomeworkId();
        String homeworkType = submitStuHomeworkVo.getType();
        if (StringUtils.isEmpty(stuId))
            return R.error("请输入学生id");
        if (StringUtils.isEmpty(classId))
            return R.error("请输入classId");
        if (homeworkId == null)
            return R.error("请输入作业id");
        HomeworkEntity homeworkEntity = homeworkService.queryObject(homeworkId);
        String tchId = homeworkEntity.getTchId();

//        Date finishTime = homeworkEntity.getFinishTime();
//        Date date = new Date();
//
//        if (finishTime.getTime() < date.getTime()) {
//            return R.error(500,"超过截止日期");
//        }

        List<StuTopicClusterVo> stuTopicClusterVoList = submitStuHomeworkVo.getTopicClusterList();
        /**
         * 是否是客观题
         */
        Boolean isAllObjective = true;
        Float stuHomeworkActualScore = 0F;
        List<Map<String,Object>> mistakeList = new ArrayList<>();
        //题簇
        if (stuTopicClusterVoList != null && stuTopicClusterVoList.size() > 0) {
            for (StuTopicClusterVo stuTopicClusterVo : stuTopicClusterVoList) {
                Integer type = stuTopicClusterVo.getType();

                Integer mark;
                /**
                 * 如果作业类型为客观题 这里直接设置题目为以批改
                 */
                if (type == 1 || type == 2 || type == 3) {
                    mark = 1;
                } else {
                    isAllObjective = false;
                    mark = 0;
                }
                String bNum = stuTopicClusterVo.getBNum();
                stuTopicClusterVo.setHomeworkId(homeworkId);
                stuTopicClusterVo.setClassId(classId);
                stuTopicClusterVo.setStuId(stuId);
                stuTopicClusterVo.setMark(0);
                /**
                 * 原学生提交内容 改为对象
                 */
                CorrectImg[] submitContentC = FileUtil.getReplaceBaseUrl(stuTopicClusterVo.getSubmitContent());
                stuTopicClusterVo.setSubmitContent(submitContentC);


//            获取选择题判断题的答案和题目内容
//            params.put("bNum", bNum);
//            List<TopicClusterEntity> stuClusterList = topicClusterService.queryList(params);
//            TopicClusterEntity topicClusterEntity = stuClusterList.get(0);
//    		  StuTopicClusterEntity stuTopicClusterEntity = new StuTopicClusterEntity();

//            BeanUtils.copyProperties(stuTopicClusterVo, topicClusterEntity);
//            stuTopicClusterVo.setId(null);
//            insertTopic(stuTopicClusterVo, params);
                List<StuTopicVo> stuTopicEntityList = new ArrayList<>();
                List<StuTopicVo> stuTopicVos = stuTopicClusterVo.getTopicList();
                if (stuTopicVos != null && stuTopicVos.size() > 0) {
                    for (StuTopicVo stuTopicVo : stuTopicVos) {

                        /**
                         * 设置是否是以批改
                         */
                        stuTopicVo.setMark(mark);
                        stuTopicVo.setHomeworkId(homeworkId);
                        stuTopicVo.setClassId(classId);
                        stuTopicVo.setStuId(stuId);
                        stuTopicVo.setType(type);
                        stuTopicVo.setTchId(tchId);
                        stuHomeworkActualScore = stuTopicVo.getActualScore() + stuHomeworkActualScore;

                        if (stuTopicVo.getSubmitContent() != null && stuTopicVo.getSubmitContent().length > 0) {
                            CorrectImg[] submitContent = FileUtil.getReplaceBaseUrl(stuTopicVo.getSubmitContent());
                            stuTopicVo.setSubmitContent(submitContent);
                        }
                        stuTopicEntityList.add(stuTopicVo);
                        //学生错题本上传
                        Integer type1 = stuTopicVo.getType();
                        if(type1 == TopicType.SingleChoice.getCode() ||type1 ==TopicType.Judge.getCode() || type1 ==TopicType.MultipleChoice.getCode()){
                            Float actualScore = stuTopicVo.getActualScore();
                            Float sScore = stuTopicVo.getSScore();
                            if(sScore !=0F && sScore>actualScore){
                                Map<String,Object> mistakeMap = new HashMap<>();
                                mistakeMap.put("homeworkId",stuTopicVo.getHomeworkId());
                                mistakeMap.put("classId", stuTopicVo.getClassId());
                                mistakeMap.put("stuId", stuTopicVo.getStuId());
                                mistakeMap.put("bNum", stuTopicVo.getBNum());
                                mistakeMap.put("sNum", stuTopicVo.getSNum());
                                mistakeList.add(mistakeMap);
                            }
                        }

                    }
                } else {
                    return R.error("单题数组不能为空");
                }

                stuHomeworkService.saveTopics(stuTopicEntityList, stuTopicClusterVo);
            }
        } else if (homeworkType.equals("2")) {
            System.out.println("口语评测");
        } else {
            return R.error("题簇不能为空");
        }

        //学生提交作业状态修改
        HashMap<String, Object> param = new HashMap<>();
        param.put("homeworkId", homeworkId);
        param.put("stuId", stuId);
        param.put("classId", classId);
        List<StuHomeworkEntity> stuHomeworkEntities = stuHomeworkService.queryList(param);
        if (stuHomeworkEntities == null || stuHomeworkEntities.size() <= 0) {
            return R.error("数据传输有误,请检查后重新上传");
        }
        StuHomeworkEntity stuHomeworkEntity = stuHomeworkEntities.get(0);
        // 默认有人工批改 实际情况 看后面代码执行
        stuHomeworkEntity.setEvaluationer(Evaluationer.artificial.getCode());

        if (HomeworkType.OralReviews.getCode().equals(homeworkType)) {
            // 语音题 设置状态与去其他不同 直接打分
            stuHomeworkEntity.setRecord(submitStuHomeworkVo.getRecord());
            stuHomeworkEntity.setTimeKeep(submitStuHomeworkVo.getTimeKeep());
            stuHomeworkEntity.setActualScore(submitStuHomeworkVo.getActualScore());
            stuHomeworkEntity.setAnalysis(submitStuHomeworkVo.getAnalysis());

            stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.DONE.getState());
            stuHomeworkEntity.setStuState(StuStatus.GRADE.getState());
            stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.YESCORRECT.getState());
            stuHomeworkEntity.setTchState(TchStatus.WAITFILE.getState());

            stuHomeworkEntity.setEvaluationer(Evaluationer.system.getCode());
        } else {
            stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.DONE.getState());
            stuHomeworkEntity.setStuState(StuStatus.NOCORRECT.getState());
            stuHomeworkEntity.setTimeKeep(submitStuHomeworkVo.getTimeKeep());
            stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.NOCORRECT.getState());
            stuHomeworkEntity.setTchState(TchStatus.NOCORRECT.getState());
        }
        /**
         * 如果全部是客观题 作业状态 已经打分   并且计算分数
         */
        if (isAllObjective && !HomeworkType.OralReviews.getCode().equals(homeworkType)) {
            stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.DONE.getState());
            stuHomeworkEntity.setStuState(StuStatus.GRADE.getState());
            stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.YESCORRECT.getState());
            stuHomeworkEntity.setTchState(TchStatus.WAITFILE.getState());
            /**
             * 设置  作业全部是客观题的情况下 作业的总分 前提 非口语评测
             */
            stuHomeworkEntity.setActualScore(stuHomeworkActualScore);

            stuHomeworkEntity.setEvaluationer(Evaluationer.system.getCode());
        }

        stuHomeworkEntity.setSubmitTime(new Date());
        stuHomeworkService.update(stuHomeworkEntity);

        /**
         * 口语评测 或者全部是客观题的情况下
         * 插入后要判断一下是否所有作业都提交了 如果都提交了  homework设置为以已归档（已批改），，其他类型题目 在教师批改的时候处理
         */
        if (HomeworkType.OralReviews.getCode().equals(homeworkType) || isAllObjective) {
            boolean b = stuHomeworkService.allHomeworkIsTchYesCorrect(stuHomeworkEntity.getHomeworkId());
            if (b) {
                homeworkEntity.setState(HomeworkStatus.ARCHIVE.getState());
                homeworkService.update(homeworkEntity);
            }
        }
        // 错题插入错题本
        if(mistakeList!=null && mistakeList.size()>0){
            for (Map<String, Object> map : mistakeList) {
                boolean b = stuMistakeBookService.insertMistakeBook((Integer) map.get("homeworkId"), (String) map.get("stuId"), (String) map.get("classId"), (String) map.get("bNum"), (String) map.get("sNum"));
            }
        }
        return R.ok(1);
    }


    /**
     * 订正作业作业提交(id)
     *
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author cuihp
     */
    @RequestMapping("/secondSubmitStuHomework")
    public R secondSubmitStuHomework(@RequestBody SubmitStuHomeworkVo submitStuHomeworkVo) {

        //获取作业id查询作业和答案
        String stuId = submitStuHomeworkVo.getStuId();
        String classId = submitStuHomeworkVo.getClassId();
        Integer homeworkId = submitStuHomeworkVo.getHomeworkId();
        String homeworkType = submitStuHomeworkVo.getType();
        if (StringUtils.isEmpty(stuId))
            return R.error("请输入学生id");
        if (StringUtils.isEmpty(classId))
            return R.error("请输入classId");
        if (homeworkId == null)
            return R.error("请输入作业id");
        HomeworkEntity homeworkEntity = homeworkService.queryObject(homeworkId);
        String tchId = homeworkEntity.getTchId();


        List<StuTopicClusterVo> stuTopicClusterVoList = submitStuHomeworkVo.getTopicClusterList();

        //题簇
        if (stuTopicClusterVoList != null && stuTopicClusterVoList.size() > 0) {
            for (StuTopicClusterVo stuTopicClusterVo : stuTopicClusterVoList) {
                Integer type = stuTopicClusterVo.getType();
                String bNum = stuTopicClusterVo.getBNum();
                stuTopicClusterVo.setHomeworkId(homeworkId);
                stuTopicClusterVo.setClassId(classId);
                stuTopicClusterVo.setStuId(stuId);
                stuTopicClusterVo.setRevisal(0);

                /**
                 * 原学生提交内容 改为对象
                 */
                CorrectImg[] submitContentC = FileUtil.getReplaceBaseUrl(stuTopicClusterVo.getSubmitContent());
                stuTopicClusterVo.setSubmitContent(submitContentC);

                List<StuTopicVo> stuTopicEntityList = new ArrayList<>();
                List<StuTopicVo> stuTopicVos = stuTopicClusterVo.getTopicList();
                if (stuTopicVos != null && stuTopicVos.size() > 0) {
                    for (StuTopicVo stuTopicVo : stuTopicVos) {

                        /**
                         * 如果作业类型为客观题 这里直接设置题目为以批改
                         */
                        if (type == 1 || type == 2 || type == 3) {
                            stuTopicVo.setMark(1);
                        } else {
                            stuTopicVo.setMark(0);
                        }

                        stuTopicVo.setHomeworkId(homeworkId);
                        stuTopicVo.setClassId(classId);
                        stuTopicVo.setStuId(stuId);
                        stuTopicVo.setType(type);
                        stuTopicVo.setTchId(tchId);
                        stuTopicVo.setRevisal(0);

                        if (stuTopicVo.getSubmitContent() != null && stuTopicVo.getSubmitContent().length > 0) {
                            CorrectImg[] submitContent = FileUtil.getReplaceBaseUrl(stuTopicVo.getSubmitContent());
                            stuTopicVo.setSubmitContent(submitContent);
                        }
                        stuTopicEntityList.add(stuTopicVo);
                    }
                } else {
                    return R.error("单题数组不能为空");
                }

                stuHomeworkService.updateTopics(stuTopicEntityList, stuTopicClusterVo);
            }
        } else if (homeworkType.equals("2")) {
            System.out.println("口语评测");
        } else {
            return R.error("题簇不能为空");
        }

        //学生提交作业状态修改
        HashMap<String, Object> param = new HashMap<>();
        param.put("homeworkId", homeworkId);
        param.put("stuId", stuId);
        param.put("classId", classId);
        List<StuHomeworkEntity> stuHomeworkEntities = stuHomeworkService.queryList(param);
        if (stuHomeworkEntities == null || stuHomeworkEntities.size() <= 0) {
            return R.error("数据传输有误,请检查后重新上传");
        }
        StuHomeworkEntity stuHomeworkEntity = stuHomeworkEntities.get(0);

        if (HomeworkType.OralReviews.getCode().equals(homeworkType)) {
            // 语音题 设置状态与去其他不同 直接打分
            String record = submitStuHomeworkVo.getRecord();
            String strReplaceBaseUrl = FileUtil.getStrReplaceBaseUrl(record);
            stuHomeworkEntity.setRecord(strReplaceBaseUrl);
            stuHomeworkEntity.setActualScore(submitStuHomeworkVo.getActualScore());
            stuHomeworkEntity.setAnalysis(submitStuHomeworkVo.getAnalysis());

            stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.DONE.getState());
            stuHomeworkEntity.setStuState(StuStatus.GRADE.getState());
            stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.YESCORRECT.getState());
            stuHomeworkEntity.setTchState(TchStatus.WAITFILE.getState());
        } else {
            /**
             * 二次提交的状态修改
             */
            stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.DONE.getState());
            stuHomeworkEntity.setStuState(StuStatus.REVISINGED.getState());
            stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.REVISING.getState());
            stuHomeworkEntity.setTchState(TchStatus.NOCORRECT.getState());
        }
        stuHomeworkEntity.setTimeKeep(submitStuHomeworkVo.getTimeKeep());
        stuHomeworkEntity.setSubmitTime(new Date());
        stuHomeworkService.update(stuHomeworkEntity);

        return R.ok(1);
    }


    /**
     * 待做作业列表查询
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/toDoHomeworkList")
    public R toDoHomeworkList(@RequestParam Map<String, Object> params) {
        Date date = new Date();

        params.put("currentTime", date);
        params.put("stuMacroStatus", StuMacroStatus.WAITDO.getState());
        Query query = new Query(params);

        List<StuHomeworkVo> stuHomeworkList = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryListByWhereTotal(query);

        PageUtils pageUtil = new PageUtils(stuHomeworkList, total, query.getLimit(), query.getPage());
        return R.ok(1).put("data", pageUtil);
    }

    /**
     * 重做作业列表查询
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/exerciseHomeworkList")
    public R exerciseHomeworkList(@RequestParam Map<String, Object> params) {
        Date date = new Date();

        params.put("currentTime", date);
        params.put("stuMacroStatus", StuMacroStatus.AGAINDO.getState());
        Query query = new Query(params);

        List<StuHomeworkVo> stuHomeworkList = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryListByWhereTotal(query);

        PageUtils pageUtil = new PageUtils(stuHomeworkList, total, query.getLimit(), query.getPage());
        return R.ok(1).put("data", pageUtil);
    }

    /**
     * 已做作业列表查询
     *
     * @param params
     * @return
     */
    @RequestMapping("/finishedHomeworkList")
    public R finishedHomeworkList(@RequestParam Map<String, Object> params) {
        Date date = new Date();

        params.put("currentTime", date);
        params.put("stuMacroStatus", StuMacroStatus.DONE.getState());
        Query query = new Query(params);

        List<StuHomeworkVo> stuHomeworkList = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryListByWhereTotal(query);

        PageUtils pageUtil = new PageUtils(stuHomeworkList, total, query.getLimit(), query.getPage());
        return R.ok(1).put("data", pageUtil);
    }


    /**
     * @Description: 学生观测台   查询排名
     * @Name: queryRank4Map
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/2
     */
    @RequestMapping("/queryRank4Map")
    public R queryRank4Map(@RequestBody Map<String, Object> params) {
        //班级id学生id
        //作业创建日期开始时间    日期结束时间
        Date submitTimeStart = null;
        Date submitTimeEnd = null;
        String classId = params.get("classId").toString();
        String dateType = (String) params.get("dateType");
        if (DateType.DAY.getCode().equals(dateType)) {
            submitTimeStart = MyDateUtil.getDayBegin();
            submitTimeEnd = MyDateUtil.getDayEnd();
        }
        if (DateType.WEEK.getCode().equals(dateType)) {
            submitTimeStart = MyDateUtil.getBeginDayOfWeek();
            submitTimeEnd = MyDateUtil.getEndDayOfWeek();
        }
        if (DateType.MONTH.getCode().equals(dateType)) {
            submitTimeStart = MyDateUtil.getBeginDayOfMonth();
            submitTimeEnd = MyDateUtil.getEndDayOfWeek();
        }
        if (null != submitTimeStart && null != submitTimeEnd) {
            String startDate = MyDateUtil.getStringDate(submitTimeStart);
            String endDate = MyDateUtil.getStringDate(submitTimeEnd);
            params.put("submitTimeStart", startDate);
            params.put("submitTimeEnd", endDate);
        }


//        String stuId = params.get("stuId").toString();
       /* String createTimeStart = params.get("createTimeStart").toString();
        String createTimeEnd = params.get("createTimeEnd").toString();
        if (StringUtils.isBlank(classId) || StringUtils.isBlank(createTimeStart) || StringUtils.isBlank(createTimeEnd))
            return R.error("请传入正确的参数, classId createTimeStart, createTimeEnd");
        */

        List<Map<String, Object>> maps = stuTopicService.queryRank4Map(params);
        return R.ok(1).put("data", maps);
    }

    /**
     * 学生端查询作业详情
     */
    @RequestMapping("/queryDetails")
    public R queryDetails(@RequestBody ParamsVo paramsVo) {

        return stuHomeworkController.queryDetails(paramsVo);
    }


    /**
     * 学生互批 列表  与 教师端批改  按人 二级列表类似  多了批阅人id 做筛选
     */
    @RequestMapping("/queryListByHome")
    public R queryListByHome(@RequestBody Map<String, Object> params) {
        return tchTeStuHomeworkController.queryListByHome(params);
    }

    /**
     * 学生互批 批改打分 订正
     */
    @RequestMapping("/stuHomeworkScore")
    public R stuHomeworkScoreCurrency(@RequestBody ScoreVo scoreVo) {
        scoreVo.setScoreType(1);
        return tchTeStuHomeworkController.stuHomeworkScoreCurrency(scoreVo);
    }


    /**
     * 学生互批 一级列表
     */
    @RequestMapping("/getMyCorrectStuHomework")
    public R getMyCorrectStuHomework(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);

        List<StuHomeworkVo> stuHomeworkList = stuHomeworkService.queryListByWhere(query);
        int total = stuHomeworkService.queryListByWhereTotal(query);

        PageUtils pageUtil = new PageUtils(null, total, query.getLimit(), query.getPage());
        return R.ok(1).put("page", pageUtil).put("data", stuHomeworkList);
    }


}
