package com.lhh.modules.homework.service.impl;

import com.lhh.common.state.StuMacroStatus;
import com.lhh.common.state.TchMacroStatus;
import com.lhh.common.state.TchStatus;
import com.lhh.common.state.TopicState;
import com.lhh.common.type.HomeworkType;
import com.lhh.common.type.TopicType;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.homework.dao.HomeworkDao;
import com.lhh.modules.homework.domain.ExcellentHomeworkVo;
import com.lhh.modules.homework.domain.HomeworkVo;
import com.lhh.modules.homework.domain.InsertHomeWorkVo;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homework.service.HomeworkService;
import com.lhh.modules.stuhomework.service.StuHomeworkService;
import com.lhh.modules.stutopic.service.StuTopicService;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topic.service.TopicService;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;
import com.lhh.modules.topiccluster.service.TopicClusterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private StuHomeworkService stuHomeworkService;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;
    @Autowired
    private StuTopicService stuTopicService;
    @Autowired
    private TopicClusterService topicClusterService;
    @Autowired
    private TopicService topicService;

    @Override
    public HomeworkEntity queryObject(Integer id) {
        return homeworkDao.queryObject(id);
    }

    @Override
    public HomeworkEntity queryContent(Integer homeworkId) {
        return homeworkDao.queryContent(homeworkId);
    }

    @Override
    public List<HomeworkEntity> queryList(Map<String, Object> map) {
        return homeworkDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return homeworkDao.queryTotal(map);
    }

    @Override
    public void save(HomeworkEntity homework) {
        homeworkDao.save(homework);
    }

    @Override
    public void update(HomeworkEntity homework) {
        homeworkDao.update(homework);
    }

    @Override
    public void delete(Integer id) {
        homeworkDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        homeworkDao.deleteBatch(ids);
    }

    @Override
    public List<InsertHomeWorkVo> queryList4Map(Map<String, Object> params) {
        return homeworkDao.queryList4Map(params);
    }

    @Override
    public int queryTotal4Map(Map<String, Object> params) {
        return homeworkDao.queryTotal4Map(params);
    }

    @Override
    public R queryArchive4State(Query query) {
        List<InsertHomeWorkVo> queryList4Map = queryList4Map(query);
        int total = queryTotal4Map(query);
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (InsertHomeWorkVo dailyWorkVo : queryList4Map) {
            HashMap<String, Object> resultMap = new HashMap<>();
            Integer homeworkId = dailyWorkVo.getId();
            resultMap.put("homeworkId", homeworkId);
            resultMap.put("title", dailyWorkVo.getTitle());
            resultMap.put("finishTime", dailyWorkVo.getFinishTime());
            resultMap.put("type", dailyWorkVo.getType());
            Date createTime = dailyWorkVo.getCreateTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(createTime.getTime());
            resultMap.put("createTime", dateString);

            String[] classIds = dailyWorkVo.getClassIds();
            resultMap.put("classInfos", classIds);
            resultMap.put("classIds", classIds);
            //查询班级人数
            map.clear();
            map.put("homeworkId", homeworkId);
            int queryTotal = stuHomeworkService.queryTotal(map);
            resultMap.put("headCount", queryTotal);
            //查询已做作业学生
            map.put("stuMacroStatus", StuMacroStatus.DONE.getState());
            int submitCount = stuHomeworkService.queryTotal(map);
            resultMap.put("submitCount", submitCount);
            //查询教师未批阅
            map.clear();
            map.put("homeworkId", homeworkId);
            map.put("tchStatus", TchStatus.NOCORRECT.getState());
            int notReadCount = stuHomeworkService.queryTotal(map);
            resultMap.put("notReadCount", notReadCount);
            //查询教师订正
            map.remove("tchStatus");
            map.put("tchMacroStatus", TchMacroStatus.REVISING.getState());
            int correctCount = stuHomeworkService.queryTotal(map);
            resultMap.put("correctCount", correctCount);
            resultList.add(resultMap);
        }
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        R r = new R();
        r.put("page", pageUtil).put("data", resultList).put("code", 1);
        return r;
    }

    @Override
    public Map<String, Object> queryHomeworkInfo2Map(Map<String, Object> params) {
        //学生完成情况
        Map<String, Object> stateMap = homeworkDao.queryComplete4Tch(params);
        //查询该段时间内作业的种类数量
        List<Map> countList = homeworkDao.queryHomeworkCount4Type(params);
        Map<String, Object> typeCountMap = new HashMap<>();
        for (Map map : countList) {
            String type = map.get("type").toString();
            //日常
            //初始数值
            typeCountMap.put("dailyWork", 0);
            typeCountMap.put("oralReviews", 0);
            typeCountMap.put("synchronizedExercises", 0);
            typeCountMap.put("phaseDetection", 0);
            if (HomeworkType.DailyWork.getCode().equals(type))
                typeCountMap.put("dailyWork", map.get("typeCount"));
            //口语
            if (HomeworkType.OralReviews.getCode().equals(type))
                typeCountMap.put("oralReviews", map.get("typeCount"));
            //同步
            if (HomeworkType.SynchronizedExercises.getCode().equals(type))
                typeCountMap.put("synchronizedExercises", map.get("typeCount"));
            //阶段
            if (HomeworkType.PhaseDetection.getCode().equals(type))
                typeCountMap.put("phaseDetection", map.get("typeCount"));

            //todo
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("stuFinishMap", stateMap);
        resultMap.put("homTypeCountMap", typeCountMap);
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> queryStuSubmitCount(Map<String, Object> params) {
        //判断order字段是否存在  不存在添加默认desc
        Object order = params.get("order");
        if (order == null) {
            params.put("order", "DESC");
        }

        return homeworkDao.queryStuSubmitCount(params);
    }

    @Override
    public List<Map> queryStuReport2Map(Map<String, Object> params) {

        List<Map> maps = homeworkDao.queryStuReport2Map(params);
        for (Map map : maps) {
            params.put("stuId", map.get("stuId"));
            Map<String, Integer> stringIntegerMap = queryCollectAndExcellent4Map(params);
            map.put("collectCount", stringIntegerMap.get("collect"));
            map.put("excellentCount", stringIntegerMap.get("excellent"));
        }
        return maps;
    }

    @Override
    public HomeworkVo queryObjectVo(Integer id) {
        return homeworkDao.queryObjectVo(id);
    }

    @Override
    public HomeworkVo queryHomeworkDetail(Integer homeworkId, Boolean subjective) {
        HomeworkVo homeworkVo = this.queryObjectVo(homeworkId);
        if (homeworkVo == null) {
            return null;
        }
        String type = homeworkVo.getType();
        if ("2".equals(type)) {
            return homeworkVo;
        } else if ("1".equals(type) || "3".equals(type) || "4".equals(type)) {
            Map qm1 = new HashMap();
            qm1.put("homeworkId", homeworkId);
            qm1.put("sidx", "b_num");
            qm1.put("order", "ASC");
            List<TopicClusterVo> topicClusterVoList = topicClusterService.queryList4Vo(qm1);

            List<TopicClusterVo> topicClusterVoListSe = new ArrayList<>();
            if (subjective != null) {
                for (TopicClusterVo topicClusterVo : topicClusterVoList) {
                    Boolean isNoSubjective = topicClusterVo.getType().intValue() == TopicType.Judge.getCode().intValue()
                            || topicClusterVo.getType().intValue() == TopicType.SingleChoice.getCode().intValue()
                            || topicClusterVo.getType().intValue() == TopicType.MultipleChoice.getCode().intValue();
                    if (subjective.booleanValue() != isNoSubjective.booleanValue()) {
                        topicClusterVoListSe.add(topicClusterVo);
                    }
                }
                topicClusterVoList = topicClusterVoListSe;
            }
            for (TopicClusterVo topicClusterVo : topicClusterVoList) {
                Integer topicClusterId = topicClusterVo.getId();
                Map qm2 = new HashMap();
                qm2.put("topicClusterId", topicClusterId);
                qm2.put("sidx", "s_num");
                qm2.put("order", "asc");
                List<TopicVo> topicVoList = topicService.queryList4Vo(qm2);
                topicClusterVo.setTopicList(topicVoList);
            }

            homeworkVo.setTopicClusterList(topicClusterVoList);
            return homeworkVo;
        } else {
            return null;
        }
    }


    private Map<String, Integer> queryCollectAndExcellent4Map(Map<String, Object> params) {
        Map<String, Integer> resultMap = new HashMap<>();

        params.put("collect", TopicState.COLLECT.getCode());
        int i = stuTopicClusterService.queryCount4Map(params);
        int i2 = stuTopicService.queryCount4Map(params);
        resultMap.put("collect", i + i2);
        params.remove("collect");
        params.put("excellent", TopicState.EXCELLENT.getCode());
        int i3 = stuTopicClusterService.queryCount4Map(params);
        int i4 = stuTopicService.queryCount4Map(params);
        resultMap.put("excellent", i3 + i4);

        return resultMap;
    }


    @Override
    public List<ExcellentHomeworkVo> excellentHomework(Map<String, Object> params) {
        return homeworkDao.excellentHomework(params);
    }

    @Override
    public int excellentHomeworkTotal(Map<String, Object> params) {
        return homeworkDao.excellentHomeworkTotal(params);
    }

	@Override
	public List<HomeworkEntity> draftsList(Map<String, Object> params) {
		return homeworkDao.draftsList(params);
	}
}
