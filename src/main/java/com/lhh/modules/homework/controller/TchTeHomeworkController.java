package com.lhh.modules.homework.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lhh.common.state.HomeworkStatus;
import com.lhh.common.state.StuMacroStatus;
import com.lhh.common.state.StuStatus;
import com.lhh.common.state.TchMacroStatus;
import com.lhh.common.state.TchStatus;
import com.lhh.common.state.TopicState;
import com.lhh.common.type.CorrectType;
import com.lhh.common.type.HomeworkType;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.MyDateUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.utils.StrUtil;
import com.lhh.common.utils.TopicTypeUtil;
import com.lhh.common.validator.ValidatorUtils;
import com.lhh.common.validator.group.DayHomeWorkGroup;
import com.lhh.modules.cnjy21.model.question.Question;
import com.lhh.modules.cnjy21.service.APIQuestionService;
import com.lhh.modules.homework.domain.ClassInfo;
import com.lhh.modules.homework.domain.HomeworkVo;
import com.lhh.modules.homework.domain.InsertHomeWorkVo;
import com.lhh.modules.homework.domain.LanguageEvaluation;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homework.service.HomeworkService;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;
import com.lhh.modules.stutopic.domain.WYStatistics;
import com.lhh.modules.stutopic.domain.WYStatisticsMap;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.stutopic.service.StuTopicService;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topic.entity.TopicEntity;
import com.lhh.modules.topic.service.TopicService;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;
import com.lhh.modules.topiccluster.entity.TopicClusterEntity;
import com.lhh.modules.topiccluster.service.TopicClusterService;


/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("tch/homework")
public class TchTeHomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private TopicClusterService topicClusterService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private StuHomeworkService stuHomeworkService;
    @Autowired
    private StuTopicService stuTopicService;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;
    /**
     * 随堂练习  作业提交
     * <p>
     * <p>
     * 题目内容挂在作业下
     *
     * @throws IOException
     * @throws IllegalStateException
     */

    @RequestMapping("/subDailyWork")
    public R subDailyWork(@RequestBody InsertHomeWorkVo dailyWorkVo) {
        ValidatorUtils.validateEntity(dailyWorkVo, DayHomeWorkGroup.class);
        String tchId = dailyWorkVo.getTchId();
        HomeworkEntity homeworkEntity = new HomeworkEntity();
        if (dailyWorkVo.getReleaseTime() == null) {
            dailyWorkVo.setReleaseTime(new Date());
        }

        ClassInfo[] classInfos = dailyWorkVo.getClassInfos();
        if (classInfos == null || classInfos.length <= 0) {
            return R.error("班级和学生不能为空");
        }
        List<String> list = new ArrayList<>();
        for (ClassInfo classInfo : classInfos) {
            list.add(classInfo.getClassId());
        }
        BeanUtils.copyProperties(dailyWorkVo, homeworkEntity);
        homeworkEntity.setClassIds(list.toArray(new String[list.size()]));
        homeworkEntity.setCreateTime(new Date());

        JSONArray content = homeworkEntity.getContent();
        if (content != null) {
            String json = FileUtil.getStrReplaceBaseUrl(content.toJSONString());
            homeworkEntity.setContent(JSONArray.parseArray(json));
        }

        List<String> answerImgs = homeworkEntity.getAnswerImgs();
        if (answerImgs != null && answerImgs.size() > 0) {
            List<String> list2StringList = FileUtil.getListReplaceBaseUrl(answerImgs);
            homeworkEntity.setAnswerImgs(list2StringList);
        }

        //保存homework
        //答题卡
        List<TopicClusterVo> topicClusterVoList = dailyWorkVo.getTopicClusterList();
        homeworkEntity.setAnswerCard(topicClusterVoList.toArray(new TopicClusterVo[topicClusterVoList.size()]));


        homeworkEntity.setCorrectType(CorrectType.Default.getCode());
        	homeworkService.save(homeworkEntity);
        Integer homeworkId = homeworkEntity.getId();

        // 循环处理题簇和单个题
        for (TopicClusterVo topicClusterVo : topicClusterVoList) {
            // 获取但个题
            List<TopicVo> topicVoList = topicClusterVo.getTopicList();
            Integer type = topicClusterVo.getType();
            TopicClusterEntity topicClusterEntity = new TopicClusterEntity();
            BeanUtils.copyProperties(topicClusterVo, topicClusterEntity);
            topicClusterEntity.setHomeworkId(homeworkId);
            topicClusterService.save(topicClusterEntity);
            Integer topicClusterId = topicClusterEntity.getId();

            for (TopicVo topicVo : topicVoList) {
                TopicEntity topicEntity = new TopicEntity();
                BeanUtils.copyProperties(topicVo, topicEntity);
                topicEntity.setTchId(tchId);
                topicEntity.setType(type);
                topicEntity.setHomeworkId(homeworkId);
                topicEntity.setTopicClusterId(topicClusterId);
                topicService.save(topicEntity);
            }
        }
        //判断是否要存入草稿箱
        String draftYn ="";
        draftYn= homeworkEntity.getDraftsYn();
        if(!draftYn.equals("Y")){
        	// 添加4个状态
        	List<StuHomeworkEntity> stuHomeworkList = new ArrayList<>();
        	for (ClassInfo classInfo : classInfos) {
        		String[] ids = classInfo.getStuIds();
        		String classId = classInfo.getClassId();
        		
        		for (String i : ids) {
        			StuHomeworkEntity stuHomeworkEntity = new StuHomeworkEntity();
        			stuHomeworkEntity.setHomeworkId(homeworkId);
        			stuHomeworkEntity.setStuId(String.valueOf(i));
        			stuHomeworkEntity.setClassId(String.valueOf(classId));
        			stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.WAITDO.getState());
        			stuHomeworkEntity.setStuState(StuStatus.ZERO.getState());
        			stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.NOCORRECT.getState());
        			stuHomeworkEntity.setTchState(TchStatus.ZERO.getState());
        			/**
        			 * 设置批改人id第一次提交 默认是教师id
        			 */
        			stuHomeworkEntity.setReviewerId(tchId);
        			stuHomeworkList.add(stuHomeworkEntity);
        		}
        	}
        	stuHomeworkService.batchSave(stuHomeworkList);
        } 
        	return R.ok(1);
    }


    /**
     * 语音题
     *
     * @throws IOException
     * @throws IllegalStateException
     */

    @RequestMapping("/subLanguageEvaluation")
    public R subLanguageEvaluation(@RequestBody LanguageEvaluation languageEvaluation) {
        ValidatorUtils.validateEntity(languageEvaluation, DayHomeWorkGroup.class);
        HomeworkEntity homeworkEntity = new HomeworkEntity();
        Date publishTime = homeworkEntity.getPublishTime();
        if (publishTime == null) {
            homeworkEntity.setPublishTime(new Date());
        }
        if (languageEvaluation.getReleaseTime() == null) {
            languageEvaluation.setReleaseTime(new Date());
        }
        ClassInfo[] classInfos = languageEvaluation.getClassInfos();
        if (classInfos == null || classInfos.length <= 0) {
            return R.error("班级和学生不能为空");
        }
        List<String> list = new ArrayList<>();
        for (ClassInfo classInfo : classInfos) {
            list.add(classInfo.getClassId());
        }
        BeanUtils.copyProperties(languageEvaluation, homeworkEntity);
        homeworkEntity.setClassIds(list.toArray(new String[list.size()]));
        Date date = new Date();
        homeworkEntity.setCreateTime(date);
        /**
         * 语音题 没有公布时间 这里设置当前时间
         */
        homeworkEntity.setPublishTime(date);
        homeworkService.save(homeworkEntity);
        Integer homeworkId = homeworkEntity.getId();

        //判断是否要存入草稿箱
        String draftYn ="";
        draftYn= homeworkEntity.getDraftsYn();
        if(!draftYn.equals("Y")){
        	// 添加4个状态
        	List<StuHomeworkEntity> stuHomeworkList = new ArrayList<>();
        	for (ClassInfo classInfo : classInfos) {
        		String[] ids = classInfo.getStuIds();
        		String classId = classInfo.getClassId();
        		for (String i : ids) {
        			StuHomeworkEntity stuHomeworkEntity = new StuHomeworkEntity();
        			stuHomeworkEntity.setHomeworkId(homeworkId);
        			stuHomeworkEntity.setStuId(String.valueOf(i));
        			stuHomeworkEntity.setClassId(String.valueOf(classId));
        			stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.WAITDO.getState());
        			stuHomeworkEntity.setStuState(StuStatus.ZERO.getState());
        			stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.NOCORRECT.getState());
        			stuHomeworkEntity.setTchState(TchStatus.ZERO.getState());
        			stuHomeworkList.add(stuHomeworkEntity);
        		}
        	}
        	stuHomeworkService.batchSave(stuHomeworkList);
        }
        return R.ok(1);
    }

    /**
     * 同步练习+阶段检测 教师选题
     *
     * @param phaseWork
     * @return
     * @author
     */
    @RequestMapping("/subPhaseWork")
    public R subPhaseWork(@RequestBody InsertHomeWorkVo phaseWork) {
        String tchId = phaseWork.getTchId();
        if (phaseWork.getPublishTime() == null) {
            phaseWork.setPublishTime(new Date());
        }
        if (phaseWork.getReleaseTime() == null) {
            phaseWork.setReleaseTime(new Date());
        }

        //根据题簇查询21世纪接口题目信息
        List<TopicClusterVo> topicClusters = phaseWork.getTopicClusterList();
        ClassInfo[] classInfos = phaseWork.getClassInfos();

        APIQuestionService apiQuestionService = new APIQuestionService();
        // 题目来源
        String source = phaseWork.getTopicSource();

        //封装homework表获取id
        HomeworkEntity homeworkEntity = new HomeworkEntity();
        List<String> list = new ArrayList<>();
        for (ClassInfo classInfo : classInfos) {
            list.add(classInfo.getClassId());
        }
        BeanUtils.copyProperties(phaseWork, homeworkEntity);
        homeworkEntity.setClassIds(list.toArray(new String[list.size()]));
        homeworkEntity.setCreateTime(new Date());
        homeworkEntity.setCorrectType(CorrectType.Default.getCode());
        homeworkService.save(homeworkEntity);
        Integer homeworkId = homeworkEntity.getId();
        //题簇
        for (TopicClusterVo topicClusterVo : topicClusters) {
            List<Question> questionList = new ArrayList<Question>();
            List<TopicVo> topicVoList = topicClusterVo.getTopicList();
            //单题
            Map<String, TopicVo> topicMap = new HashMap<>();
            for (TopicVo topicVo : topicVoList) {
                Question question = new Question();
                question.setQuestionId(Long.valueOf(topicVo.getQuestionId()));
                questionList.add(question);
                topicMap.put(topicVo.getQuestionId(), topicVo);
            }
            List<Question> questionsAnswer = apiQuestionService.getQuestionsAnswer(questionList);
            //封装题簇插入题簇表
            TopicClusterEntity topicClusterEntity = new TopicClusterEntity();
            Integer baseType = topicClusterVo.getBaseType();
            BeanUtils.copyProperties(topicClusterVo, topicClusterEntity);
            topicClusterEntity.setHomeworkId(homeworkId);

            // 设置题目类型
            topicClusterEntity.setType(baseType);
            String content = topicClusterEntity.getContent();
            if (StringUtils.isNotBlank(content)) {
                String s1 = StrUtil.replaceChangeLine(content);
                topicClusterEntity.setContent(s1);
            }
            topicClusterService.save(topicClusterEntity);
            Integer topicClusterId = topicClusterEntity.getId();
            //封装单题插入批量插入单题表
            List<TopicEntity> topicList = new ArrayList<TopicEntity>();
            for (Question quest : questionsAnswer) {

                // explanation questionId 需要存到数据库 班级错题本使用
                String explanation = quest.getExplanation();
                String questionId = String.valueOf(quest.getQuestionId());
                TopicVo topicV = topicMap.get(questionId);
                topicV.setTchId(tchId);
                topicV.setQuestionId(questionId);
                topicV.setExplanation(explanation);

                // 设置题目来源
                topicV.setSource(source);
                // 设置题目类型
                topicV.setType(topicV.getBaseType());
                topicV.setHomeworkId(homeworkId);
                topicV.setTopicClusterId(topicClusterId);

                String cs = StrUtil.replaceChangeLine(JSON.toJSONString(quest));
                topicV.setContent(JSON.parseObject(cs));

                topicV.setCorrectAnswer(quest.getAnswerJson());
                topicList.add(topicV);
            }
            topicService.batchSave(topicList);
        }

        //判断是否要存入草稿箱
        String draftYn ="";
        draftYn= homeworkEntity.getDraftsYn();
        if(!draftYn.equals("Y")){
        	List<StuHomeworkEntity> stuHomeworkList = new ArrayList<StuHomeworkEntity>();
        	for (ClassInfo classInfo : classInfos) {
        		String[] ids = classInfo.getStuIds();
        		String classId = classInfo.getClassId();
        		for (String i : ids) {
        			StuHomeworkEntity stuHomeworkEntity = new StuHomeworkEntity();
        			
        			stuHomeworkEntity.setClassId(String.valueOf(classId));
        			stuHomeworkEntity.setHomeworkId(homeworkId);
        			stuHomeworkEntity.setStuMacroStatus(StuMacroStatus.WAITDO.getState());
        			stuHomeworkEntity.setStuState(StuStatus.ZERO.getState());
        			stuHomeworkEntity.setTchMacroStatus(TchMacroStatus.NOCORRECT.getState());
        			stuHomeworkEntity.setTchState(TchStatus.ZERO.getState());
        			stuHomeworkEntity.setStuId(String.valueOf(i));
        			/**
        			 * 设置批改人id第一次提交 默认是教师id
        			 */
        			stuHomeworkEntity.setReviewerId(tchId);
        			stuHomeworkList.add(stuHomeworkEntity);
        		}
        	}
        	stuHomeworkService.batchSave(stuHomeworkList);
        }
        return R.ok(1);
    }


    /**
     * 按题目批改 列表
     */
    @RequestMapping("/correctDataStuGroupList")
    public R correctDataStuGroupList(@RequestBody QueryVo queryVo) {

        Integer homeworkId = queryVo.getHomeworkId();
        String classId = queryVo.getClassId();
        if (homeworkId == null || StringUtils.isEmpty(classId)) {
            return R.error("homeworkId和classId不能为空");
        }

        /**
         * 查询作业详情 包含大题 小题  结构
         */
        HomeworkVo homeworkVo = homeworkService.queryHomeworkDetail(homeworkId, queryVo.getSubjective());
        if (homeworkVo == null) {
            return R.error("查不到作业");
        }

        if ("2".equals(homeworkVo.getType())) {
            return R.ok(1).put("data", homeworkVo);
        }

//        /**
//         * 查询可以批改的学生
//         */
//        List<StuHomeworkEntity> stuHomeworkEntityList = stuHomeworkService.queryAllSthwByHidAmdCid(homeworkId, classId);
//        List<StuHomeworkEntity> canBeCorrectedStu = new ArrayList<>();
//        for (StuHomeworkEntity stuHomeworkEntity : stuHomeworkEntityList) {
//            int tchState = stuHomeworkEntity.getTchState().intValue();
//            // 未批改的 需要判断 是批改中还是未批改
//            if (TchStatus.NOCORRECT.getState() == tchState) {
//                String stuId = stuHomeworkEntity.getStuId();
//                Map qm = new HashMap();
//                qm.put("stuId", stuId);
//                qm.put("homeworkId", homeworkId);
//                Boolean haveMark = stuTopicService.haveMark(qm);
//                if (haveMark) {
//                    stuHomeworkEntity.setTchState(TchStatus.CORRECTING.getState());
//                }
//            }
//            StuHomeworkEntity stuVo = new StuHomeworkEntity();
//            stuVo.setHomeworkId(stuHomeworkEntity.getHomeworkId());
//            stuVo.setStuId(stuHomeworkEntity.getStuId());
//            stuVo.setTchState(stuHomeworkEntity.getTchState());
//            canBeCorrectedStu.add(stuVo);
//        }

        Map qm = new HashMap();
        /**
         * 查询stuHomework表 作业完成情况 作业未交人数
         */
        qm.put("homeworkId", homeworkId);
        qm.put("classId", classId);
        qm.put("stuMacroStatus", StuMacroStatus.WAITDO.getState());
        int unCommittedCount = stuHomeworkService.queryTotal(qm);
        qm.put("stuMacroStatus", StuMacroStatus.DONE.getState());
        /**
         * 查询stuHomework表 作业完成情况 查询作业已交人数
         */
        int submitCount = stuHomeworkService.queryTotal(qm);

        homeworkVo.setSubmitCount(submitCount);
        homeworkVo.setUnCommittedCount(unCommittedCount);
        /**
         * 像大题和小题中填充未批改 已批改人数
         */
        List<TopicClusterVo> topicClusterList = homeworkVo.getTopicClusterList();

        for (TopicClusterVo topicClusterVo : topicClusterList) {
            String bNum = topicClusterVo.getBNum();
            WYStatisticsMap wyStatisticsMap;
            if (queryVo.getQueryType().intValue() == 1) {
                wyStatisticsMap = stuTopicService.wyStatistics(bNum,classId, homeworkId);
            } else {
                wyStatisticsMap = stuTopicService.wyStatisticsRevisal(bNum,classId, homeworkId);
            }


            if (queryVo.getQueryType().intValue() == 1) {
                Integer stuTopicClusterCorrect = wyStatisticsMap.getStuTopicClusterCorrect();
                Integer stuTopicClusterUnCorrect = wyStatisticsMap.getStuTopicClusterUnCorrect();
                /**
                 * 设置批改 未批改人数
                 */
                topicClusterVo.setNoPerusel(stuTopicClusterUnCorrect);
                topicClusterVo.setPerusel(stuTopicClusterCorrect);
            } else {
                Integer tchClusterRevising = wyStatisticsMap.getTchClusterRevising();
                Integer stuClusterRevisinged = wyStatisticsMap.getStuClusterRevisinged();
                /**
                 * 设置学生订正 教师订正
                 */
                topicClusterVo.setTchClusterRevising(tchClusterRevising);
                topicClusterVo.setStuClusterRevisinged(stuClusterRevisinged);
            }


            List<TopicVo> topicList = topicClusterVo.getTopicList();

            if (queryVo.getQueryType().intValue() == 1) {
                for (TopicVo topicVo : topicList) {
                    String sNum = topicVo.getSNum();
                    WYStatistics wyStatistics = wyStatisticsMap.get(sNum);
                    Integer unCorrect = wyStatistics.getUnCorrect();
                    Integer correct = wyStatistics.getCorrect();

                    /**
                     * 订正 未订正
                     */
                    topicVo.setSnoPerusel(unCorrect);
                    topicVo.setSperusel(correct);
                    /**
                     * 查询每个学生的题目状态
                     */
                    Map qm2 = new HashMap();
                    qm2.put("sNum", sNum);
                    qm2.put("bNum", bNum);
                    qm2.put("classId", classId);
                    qm2.put("homeworkId", homeworkId);
                    List<StuTopicEntity> list = stuTopicService.queryList(qm2);
                    topicVo.setCanBeCorrectedStu(list);
                }
            } else {
                for (TopicVo topicVo : topicList) {
                    String sNum = topicVo.getSNum();
                    WYStatistics wyStatistics = wyStatisticsMap.get(sNum);
                    Integer tchRevising = wyStatistics.getTchRevising();
                    Integer stuRevisinged = wyStatistics.getStuRevisinged();

                    /**
                     * 教师订正 学生订正
                     */
                    topicVo.setTchRevising(tchRevising);
                    topicVo.setStuRevisinged(stuRevisinged);

                    /**
                     * 查询每个学生的题目状态
                     */
                    Map qm2 = new HashMap();
                    qm2.put("sNum", sNum);
                    qm2.put("bNum", bNum);
                    qm2.put("classId", classId);
                    qm2.put("homeworkId", homeworkId);
                    List<StuTopicEntity> list = stuTopicService.queryList(qm2);
                    topicVo.setCanBeCorrectedStu(list);
                }
            }
            Map qm3 = new HashMap();
            qm3.put("bNum", bNum);
            qm3.put("homeworkId", homeworkId);
            qm3.put("classId", classId);
            List<StuTopicClusterEntity> list = stuTopicClusterService.queryList(qm3);
            topicClusterVo.setCanBeCorrectedStu(list);
        }
        return R.ok(1).put("data", homeworkVo);
    }


    /**
     * @Description: 教师端作业记录查看
     * @Name: homeworkHistoryList
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/4
     */
    @RequestMapping("/homeworkHistroyList")
    public R homeworkHistoryList(@RequestBody Map<String, Object> params) {
        /*
            需要的参数:
            releaseTimeStart  待发送作业查询
            releaseTimeEnd    已发送作业查询
            page              当前页
            limit             每页显示条数
            delMark           删除标识
            tchId             教师id
      '   */
        Object oHmState = params.get("hmState");
        if(oHmState == null){
            return R.error("hmState 不能为空!");
        }
        Integer hmState = (Integer) oHmState; //1,待发送  2,已发送  3,草稿箱
        Date dateNow = new Date();
        if(hmState ==1){
            params.put("releaseTimeStart",dateNow);
        }else if (hmState == 2){
            params.put("releaseTimeEnd",dateNow);
        }else if(hmState ==3){
            params.put("draftsYn","Y");
        }

        Object delMark = params.get("delMark");
        Object tchId = params.get("tchId");
        if (null == tchId) {
            return R.error("请输入教师id");
        }
        if (null == delMark) {
            params.put("delMark", HomeworkStatus.UNDELETED.getState());
        }
        Object limit = params.get("limit");
        Query query = null;
        if(null != limit)
            query = new Query(params);
        //查询列表数据

        List<HomeworkEntity> homeworkList = homeworkService.queryList(query==null?params:query);
        int total = homeworkService.queryTotal(query);
        ArrayList<Map> paramList = new ArrayList<>();

        for (HomeworkEntity homeworkEntity : homeworkList) {
            Map<String, Object> resultMap = new HashMap<>();

            String[] classInfo = homeworkEntity.getClassIds();
            //类型
            resultMap.put("type", homeworkEntity.getType());
            //接受对象
            String[] classIds = homeworkEntity.getClassIds();
            resultMap.put("classIds", classIds);
            //标题
            resultMap.put("title", homeworkEntity.getTitle());
            //公布时间
            resultMap.put("publishTime", homeworkEntity.getPublishTime());
            ///发送时间
            resultMap.put("releaseTime", homeworkEntity.getReleaseTime());
            //作业id
            resultMap.put("homeworkId", homeworkEntity.getId());
            //老师id
            resultMap.put("tchId", homeworkEntity.getTchId());

//            resultMap.put("tchName", homeworkEntity.getTchName());
            paramList.add(resultMap);
        }
        if(null !=query){
            PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
            return R.ok(1).put("page", pageUtil).put("data", paramList);
        }


        return R.ok(1).put("data", paramList);
    }

    /**
     * @Description: 教师端删除作业或撤销作业接口
     * @Name: delHomework
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/4
     */
    @RequestMapping("/delHomework")
    @Transactional
    public R delHomework(@RequestBody Map<String, Object> params) {
        String homeworkIds = params.get("homeworkId").toString();
        if (StringUtils.isEmpty(homeworkIds)) {
            return R.error("请传入作业id");
        }
        Integer homeworkId = Integer.valueOf(homeworkIds);
        //判断delMark是否为空  为空撤销  不为空逻辑删除
        Object delMark = params.get("delMark");


        if (null != delMark) {
            //逻辑删除
            int state = (Integer) delMark;
            if(state ==HomeworkStatus.DELETED.getState()) {
                HomeworkEntity homework = new HomeworkEntity();
                homework.setId(homeworkId);
                homework.setDelMark(HomeworkStatus.DELETED.getState());
                homeworkService.update(homework);
            }
        } else {
            //物理删除 同时删除学生相关表
            //删除 homework表 stu_homework表  topic_cluster topic stu_topic stu_topic_cluster
            HomeworkEntity homeworkEntity = new HomeworkEntity();
            homeworkEntity.setId(homeworkId);
            homeworkEntity.setDraftsYn("Y");
            homeworkService.update(homeworkEntity);
//            原来是删除所有逻辑
//            homeworkService.delete(homeworkId);
//            topicService.delete4Map(params);
//            topicClusterService.delete4Map(params);
            stuHomeworkService.delete4Map(params);
            stuTopicClusterService.delete4Map(params);
            stuTopicService.delete4Map(params);
        }


        return R.ok(1);
    }

    /**
     * @Description: 教师端 作业记录  详情查看
     * @Name: homeworkRecordDetail
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/5
     */
    @ResponseBody
    @RequestMapping("/homeworkRecordDetail")
    public R homeworkRecordDetail(@RequestBody Map<String, Object> params) {
        //题目信息 答题卡
        //homeworkId
        String homeworkIdStr = params.get("homeworkId").toString();
        Integer homeworkId = Integer.valueOf(homeworkIdStr);
        if (StringUtils.isEmpty(homeworkIdStr))
            return R.error("请输入作业id");

        HomeworkVo homeworkVo = homeworkService.queryHomeworkDetail(homeworkId, null);
        JSONArray content = homeworkVo.getContent();

        List<String> answerImgs = homeworkVo.getAnswerImgs();
        if (answerImgs != null && answerImgs.size() > 0) {
            List<String> list2StringList = FileUtil.getListReplacePlaceholder(answerImgs);
            homeworkVo.setAnswerImgs(list2StringList);
        }
        if (content != null && content.size() > 0) {
            String tmpStr = FileUtil.getStrReplacePlaceholder(homeworkVo.getContent().toJSONString());
            JSONArray reContent = JSONArray.parseArray(tmpStr);
            homeworkVo.setContent(reContent);
        }

        return R.ok(1).put("data", homeworkVo);
    }

    /**
     * @Description: 统计报告查询
     * @Name: queryStatisticalReport
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/6
     */
    @RequestMapping("/queryStatisticalReport")
    public R queryStatisticalReport(@RequestBody Map<String, Object> params) {
        /*
            教师id
            筛选时间  作业发布时间  release_time
            班级id
            三个service
                查询  该教师在时间范围内的所有作业提交情况   %  ,该教师提交作业按类型统计次数
                查询  成绩报告   该教师在该时间内所有作业在该班级的排名情况   总分(升序/降序)  得分率% 收藏(次) 评优(次)  订正(次)
                查询  作业提交   按时提交  迟提交   未交  次
         */
        String classId = params.get("classId").toString();
        String releaseTimeStart = params.get("releaseTimeStart").toString();
        String releaseTimeEnd = params.get("releaseTimeEnd").toString();
        String tchId = params.get("tchId").toString();
        if (StringUtils.isEmpty(classId) || StringUtils.isEmpty(releaseTimeStart) || StringUtils.isEmpty(releaseTimeEnd) || StringUtils.isEmpty(tchId)) {
            return R.error("时间范围, 班级id 教师id 不允许为空");
        }
        //one
        Map<String, Object> oneMap = homeworkService.queryHomeworkInfo2Map(params);
        //two
        List<Map> twoList = homeworkService.queryStuReport2Map(params);
        //three
        List<Map<String, Object>> threeList = homeworkService.queryStuSubmitCount(params);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("homeworkCount", oneMap);
        resultMap.put("scoreReport", twoList);
        resultMap.put("homeworkStatistical", threeList);
        return R.ok(1).put("data", resultMap);
    }

    /**
     * @Description: 教师端  作业概况
     * @Name: queryHomeworkCase
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/13
     */
    @RequestMapping("/queryHomeworkCase")
    public R queryHomeworkCase(@RequestBody Map<String, Object> params) throws ParseException {
        String homeworkId;
        String classId;
        try {
            homeworkId = params.get("homeworkId").toString();
            classId = params.get("classId").toString();
        } catch (RuntimeException e) {
            return R.error("请填写正确的参数");
        }
        HashMap<String, Object> r = new HashMap<>();

        //查询标题
        HomeworkEntity homeworkEntity = homeworkService.queryObject(Integer.valueOf(homeworkId));
        String type = homeworkEntity.getType();
        if (HomeworkType.OralReviews.getCode().equals(type)) {
            Map<String, Object> resultMap = getOralReviewsDetail(params, homeworkEntity);
            return R.ok(1).put("data", resultMap);
        }
        String title = homeworkEntity.getTitle();
        r.put("title", title);
        //满分\最高\最低\平均分查询
        Map<String, Object> stringObjectMap = stuTopicService.queryScore4Homework(params);
        r.put("fullMark", stringObjectMap.get("fullMark"));
        r.put("minScore", stringObjectMap.get("minScore"));
        r.put("maxScore", stringObjectMap.get("maxScore"));
        r.put("avgScore", stringObjectMap.get("avgScore"));
        //平均用时
        Long aLong = stuHomeworkService.queryTimeKeep4Homework(params);
        r.put("timeKeep", aLong);
        //查询未交  已交人数
        params.put("stuState", StuStatus.ZERO.getState());
        params.put("stuMacroStatus", StuMacroStatus.DONE.getState());
        Map<String, Object> stringObjectMap1 = stuHomeworkService.queryStuStat(params);
        r.put("submitCount", stringObjectMap1.get("made"));
        r.put("unSubmitCount", stringObjectMap1.get("unpaid"));
        //查询 已订正被打回 人数
        params.remove("stuState");
        params.remove("stuMacroStatus");
        params.put("stuState", StuStatus.BACK.getState());
        params.put("tchState", TchStatus.REVISINGED.getState());
        Map<String, Object> stringObjectMap2 = stuHomeworkService.queryStuStat(params);
        r.put("correctCount", stringObjectMap2.get("made"));
        r.put("repulseCount", stringObjectMap2.get("unpaid"));
        //成绩分布
        params.remove("stuState");
        params.remove("tchState");
        Map<String, Object> stringObjectMap3 = stuTopicService.queryScoring(params);
        r.put("homeworkScoreMap", stringObjectMap3);
//        List<Integer> typeList = new ArrayList<>();
        //查询客观题
        List<Integer> objectiveList = TopicTypeUtil.getObjectiveList();
        params.put("typeList", objectiveList);
        Map<String, Object> stringObjectMap4 = stuTopicService.queryScoring(params);
        r.put("objectiveMap", stringObjectMap4);
        //主观题
        List<Integer> subjectiveList = TopicTypeUtil.getSubjectiveList();
        params.put("typeList", subjectiveList);

        Map<String, Object> stringObjectMap5 = stuTopicService.queryScoring(params);
        r.put("subjectiveMap", stringObjectMap5);
        return R.ok(1).put("data", r);
    }

    /**
     * 作业分析--口语评测
     *
     * @name: getOralReviewsDetail
     * @params: [params, homeworkEntity]
     * @return: java.util.Map<java.lang.String                               ,                                                               java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/23
     */
    private Map<String, Object> getOralReviewsDetail(Map<String, Object> params, HomeworkEntity homeworkEntity) throws ParseException {
        Map<String, Object> r = new HashMap<>();
        //查询未交  已交人数
        params.put("stuState", StuStatus.ZERO.getState());
        params.put("stuMacroStatus", StuMacroStatus.DONE.getState());
        Map<String, Object> stringObjectMap1 = stuHomeworkService.queryStuStat(params);
        r.put("submitCount", stringObjectMap1.get("made"));
        r.put("unSubmitCount", stringObjectMap1.get("unpaid"));
        List<Map<String, Object>> rankList = stuHomeworkService.queryOralReviewsRank(params);
        Map<String, Object> stringObjectMap = stuHomeworkService.queryOralReviewsRate(params);
        Map<String, Object> stringObjectMap2 = stuHomeworkService.queryOralReviewsScore(params);
        Double avgScore = Double.valueOf(String.valueOf(stringObjectMap2.get("avgScore")));
        r.putAll(stringObjectMap2);
        r.putAll(stringObjectMap);
        r.put("rankList", rankList);
        r.put("title", homeworkEntity.getTitle());
        String tinyType = homeworkEntity.getTinyType();
        //口语评测  作业分析下方展示
        if (HomeworkType.CHINESE.getCode().equals(tinyType)) {
            r.put("tinyName", "课文评测-中文评测题");
            r.put("tinyType", tinyType);
        }
        if (HomeworkType.ENGLISH.getCode().equals(tinyType)) {
            r.put("tinyName", "课文评测-英文评测题");
            r.put("tinyType", tinyType);
        }
        //曲线map
        Date createTime = homeworkEntity.getCreateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(createTime);
        createTime = sdf.parse(dateNowStr);
        List<Date> frontDayList = MyDateUtil.getFrontDayList(new Date(), 6);
        ArrayList<Map<String, Object>> dateList = new ArrayList<>();
        for (Date date : frontDayList) {
            Map<String, Object> dateMap = new HashMap<>();
            if (createTime.after(date)) {
                dateMap.put("date", date);
                dateMap.put("avgScore", 0);
            } else {
                dateMap.put("date", date);
                dateMap.put("avgScore", avgScore);
            }
            dateList.add(dateMap);
        }
        r.put("dateList", dateList);

        // todo  口语评测分析结果存放


        return r;
    }


    /**
     * @Description: 作业概况->作业提交列表查看
     * @Name: stuSubmitList
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/14
     */
    @RequestMapping("/stuSubmitList")
    public R stuSubmitList(@RequestBody Map<String, Object> params) {
        String homeworkId;
        String classId;
        try {
            homeworkId = params.get("homeworkId").toString();
            classId = params.get("classId").toString();
        } catch (RuntimeException e) {
            return R.error("请填写正确的参数");
        }
        Map<String, Object> resultMap = new HashMap<>();
        //已提交查询
        params.put("stuMacroStatus", StuMacroStatus.DONE.getState());
        List<StuHomeworkEntity> stuHomeworkEntities = stuHomeworkService.queryList(params);
        resultMap.put("submitList", stuHomeworkEntities);
        //未提交查询
        params.remove("stuMacroStatus");
        params.put("stuState", StuStatus.ZERO.getState());
        List<StuHomeworkEntity> stuHomeworkEntities1 = stuHomeworkService.queryList(params);
        resultMap.put("unSubmitList", stuHomeworkEntities1);
        return R.ok(1).put("data", resultMap);
    }

    /**
     * @Description: 教师端作业分析 --
     * @Name: homeworkAnalysis
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/14
     */
    @RequestMapping("/graspTheSituation")
    public R graspTheSituation(@RequestBody Map<String, Object> params) {
        String homeworkId;
        String classId;
        try {
            homeworkId = params.get("homeworkId").toString();
            classId = params.get("classId").toString();
        } catch (RuntimeException e) {
            return R.error("请填写正确的参数");
        }

        List<Map> scoreRankList = stuTopicService.queryStuScoreRank(params);
        /*for (int i = 0; i < scoreRankList.size(); i++) {
            Map map = scoreRankList.get(i);
            String stuId = String.valueOf(map.get("stuId"));
            if (StringUtils.isNotBlank(stuId) && scoreRankList.size() == 1) {
                scoreRankList.remove(i);
            }
        }*/
        List<TopicClusterVo> answerISList = stuTopicService.queryAnswerIS(params);
        //收藏作业查询
        params.put("collect", TopicState.COLLECT.getCode());
        Map<String, Object> collectMap = stuTopicService.queryStu4Map(params);
        //优秀作业查询
        params.remove("collect");
        params.put("excellent", TopicState.EXCELLENT.getCode());
        Map<String, Object> stringObjectMap = stuTopicService.queryStu4Map(params);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("scoreRankList", scoreRankList);
        resultMap.put("answerISList", answerISList);
        resultMap.put("collects", collectMap);
        resultMap.put("excellents", stringObjectMap);
        return R.ok(1).put("data", resultMap);
    }

    /**
     * 作业详情 中作业通用通用
     */
    @RequestMapping("/queryHomeworkDetail")
    public R queryHomeworkDetail(@RequestBody QueryVo queryVo) {
        Integer homeworkId = queryVo.getHomeworkId();
        HomeworkVo homeworkVo = homeworkService.queryHomeworkDetail(homeworkId, queryVo.getSubjective());
        if (homeworkVo != null) {
            return R.ok(1).put("data", homeworkVo);
        } else {
            return R.error("非法作业 类型为空");
        }
    }

    /**
     * 统计详情查看
     *
     * @name: queryStatDetail
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/21
     */
    @RequestMapping("/queryStatDetail")
    public R queryStatDetail(@RequestBody Map<String, Object> params) {
        String statType = (String) params.get("type");
        List<Integer> typeList = null;
        if ("1".equals(statType)) {//客观题
            typeList = TopicTypeUtil.getObjectiveList();
        } else if ("2".equals(statType)) {
            typeList = TopicTypeUtil.getSubjectiveList();
        }
        if (typeList != null && typeList.size() > 0)
            params.put("typeList", typeList);
        Map<String, Object> stringObjectMap = stuTopicService.queryScoring(params);

        return R.ok(1).put("data", stringObjectMap);
    }
    /**
     * 教师端作业记录：转发给老师
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/retransmissionToTch")
    @Transactional
    public R retransmissionToTch(@RequestBody Map<String,Object> params){
    	Integer homeworkId = (Integer) params.get("homeworkId");
    	//根据homeworkId查询homework信息
    	HomeworkEntity homework = new HomeworkEntity();
    	homework=homeworkService.queryObject(homeworkId);
    	HomeworkEntity homeworkNew= new HomeworkEntity();
    	BeanUtils.copyProperties(homework, homeworkNew);
    	homeworkNew.setDraftsYn("Y");
    	homeworkNew.setId(null);
    	homeworkNew.setCreateTime(new Date());
    	
    	if(params.get("tchIdList") != null){
    		List<String> tchIds= (List<String>) params.get("tchIdList");
    		Map<String,Object> paramMap = new HashMap<String,Object>();
    		paramMap.put("homeworkId", homeworkId); 
    		//添加作业
    		for(String tchId:tchIds){
    			homework.setTchId(tchId);
    			homeworkService.save(homework);
    			Integer homeworkIdNew =homework.getId();
    			//查询大题
    			List<TopicClusterEntity> topicClusters = topicClusterService.queryList(paramMap);
    			for(TopicClusterEntity TopicCluster:topicClusters){
    				paramMap.put("topicClusterId", TopicCluster.getId());
    				TopicClusterEntity TopicClusterNew = new TopicClusterEntity();
    				BeanUtils.copyProperties(TopicCluster, TopicClusterNew);
    				TopicClusterNew.setId(null);
    				TopicClusterNew.setHomeworkId(homeworkIdNew);
    				//添加大题
    				topicClusterService.save(TopicClusterNew);
    				//设置新的topicClusterId
    				Integer topicClusterIdNew =  TopicClusterNew.getId();
    				//查询小题
    				List<TopicEntity> topics = topicService.queryList(paramMap);
    				List<TopicEntity> topicsNew = new ArrayList<TopicEntity>();
    				for(TopicEntity topic:topics){
    					TopicEntity topicNew = new TopicEntity();
    					BeanUtils.copyProperties(topic, topicNew);
    					topicNew.setHomeworkId(homeworkIdNew);
    					topicNew.setId(null);
    					topicNew.setTchId(tchId);
    					topicNew.setTopicClusterId(topicClusterIdNew);
    					topicsNew.add(topicNew);
    				}
    				//添加小题
    				topicService.batchSave(topicsNew);
    			}
    		}
    	}
    	return R.ok(1);
    }
    @RequestMapping("retransmissionToStu")
    public R retransmissionToStu(@RequestBody Map<String,Object> params){
    	
    	return R.ok(1);
    }
    /**
     * 教师端 作业记录：草稿箱列表
     * @param
     * @return
     */
    @RequestMapping("/draftsList")
    public R draftsList(@RequestBody Map<String,Object> params){
    	params.put("draftsYn", "Y");
    	//查询列表数据
         Query query = new Query(params);

 		List<HomeworkEntity> homeworks = homeworkService.draftsList(params);
 		int total = homeworkService.queryTotal(query);
 		
 	   ArrayList<Map> paramList = new ArrayList<>();

       for (HomeworkEntity homeworkEntity : homeworks) {
           Map<String, Object> resultMap = new HashMap<>();

           String[] classInfo = homeworkEntity.getClassIds();
           //类型
           resultMap.put("type", homeworkEntity.getType());
           //标题
           resultMap.put("title", homeworkEntity.getTitle());
           //公布时间
           resultMap.put("publishTime", homeworkEntity.getPublishTime());
           ///发送时间
           resultMap.put("releaseTime", homeworkEntity.getReleaseTime());
           //作业id
           resultMap.put("homeworkId", homeworkEntity.getId());
           //老师id
           resultMap.put("tchId", homeworkEntity.getTchId());

           paramList.add(resultMap);
       }
           PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
           return R.ok(1).put("page", pageUtil).put("data", paramList);
    }
    
}

