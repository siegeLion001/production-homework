package com.lhh.modules.stutopic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.common.state.TopicState;
import com.lhh.common.type.TopicType;
import com.lhh.common.utils.TopicTypeUtil;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homework.service.HomeworkService;
import com.lhh.modules.stutopic.dao.StuTopicDao;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopic.domain.WYStatistics;
import com.lhh.modules.stutopic.domain.WYStatisticsMap;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.stutopic.service.StuTopicService;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;
import com.lhh.modules.topiccluster.service.TopicClusterService;


@Service("stuTopicService")
public class StuTopicServiceImpl implements StuTopicService {
    @Autowired
    private StuTopicDao stuTopicDao;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;
    @Autowired
    private TopicClusterService topicClusterService;
    @Autowired
    private HomeworkService homeworkService;

    @Override
    public StuTopicEntity queryObject(Integer id) {
        return stuTopicDao.queryObject(id);
    }

    @Override
    public List<StuTopicEntity> queryList(Map<String, Object> map) {
        return stuTopicDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stuTopicDao.queryTotal(map);
    }

    @Override
    public void save(StuTopicEntity stuTopic) {
        stuTopicDao.save(stuTopic);
    }

    @Override
    public void update(StuTopicEntity stuTopic) {
        stuTopicDao.update(stuTopic);
    }

    @Override
    public void delete(Integer id) {
        stuTopicDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        stuTopicDao.deleteBatch(ids);
    }

    @Override
    public void batchSave(List<? extends StuTopicEntity> stuTopicEntityList) {
        stuTopicDao.batchSave(stuTopicEntityList);
    }

    @Override
    public void batchUpdate(List<? extends StuTopicEntity> stuTopicEntityList) {
        stuTopicDao.batchUpdate(stuTopicEntityList);
    }

    @Override
    public List<StuTopicVo> queryList4Vo(Map<String, Object> map) {
        return stuTopicDao.queryList4Vo(map);
    }

    @Override
    public List<Map<String, Object>> queryRank4Map(Map<String, Object> params) {
        return stuTopicDao.queryRank4Map(params);
    }

    @Override
    public List<TopicVo> queryCount4Group(Map<String, Object> params) {
        return stuTopicDao.queryCount4Group(params);
    }

    @Override
    public int queryTotalByMap(Map<String, Object> params) {
        return stuTopicDao.queryTotalByMap(params);
    }

    @Override
    public void delete4Map(Map<String, Object> params) {
        stuTopicDao.delete4Map(params);
    }

    @Override
    public Map<String, Object> queryScore4Homework(Map<String, Object> params) {
        return stuTopicDao.queryScore4Homework(params);
    }

    /**
     * @Description: 查询作业优秀/良好/及格/不及格人数
     * @Name: queryScoring
     * @Params: [params]  homeowrkId classId  作业类型查询需要 typeList list<int>
     * @Author: cuihp
     * @Date: 2018/6/13
     */
    @Override
    public Map<String, Object> queryScoring(Map<String, Object> params) {
        List<Map<String, Object>> list = stuTopicDao.queryScoring(params);
        int fail = 0;
        int pass = 0;
        int well = 0;
        int excellent = 0;
        List<Map<String, Object>> failList = new ArrayList<>();
        List<Map<String, Object>> passList = new ArrayList<>();
        List<Map<String, Object>> wellList = new ArrayList<>();
        List<Map<String, Object>> excellentList = new ArrayList<>();
        Map<String, Object> scoringMap = new HashMap<>();
        for (Map<String, Object> map : list) {
            String scoreRatio = (String) map.get("scoreRatio");
            Double aFloat = Double.parseDouble(scoreRatio);
            if (aFloat < 0.6) {
                fail += 1;
                failList.add(map);
            }
            if (aFloat >= 0.6 && aFloat < 0.8) {
                pass += 1;
                passList.add(map);
            }
            if (aFloat >= 0.8 && aFloat < 0.9) {
                well += 1;
                wellList.add(map);
            }
            if (aFloat >= 0.9 && aFloat <= 1) {
                excellent += 1;
                excellentList.add(map);
            }
        }

        scoringMap.put("fail", fail);
        scoringMap.put("pass", pass);
        scoringMap.put("well", well);
        scoringMap.put("excellent", excellent);
        scoringMap.put("failList", failList);
        scoringMap.put("passList", passList);
        scoringMap.put("wellList", wellList);
        scoringMap.put("excellentList", excellentList);
        return scoringMap;
    }

    @Override
    public List<Map> queryStuScoreRank(Map<String, Object> params) {
        //客观题类型封装
        List<Integer> objectiveItemList = TopicTypeUtil.getObjectiveList();

        //主观题类型封装
        List<Integer> subjectiveItemList = TopicTypeUtil.getSubjectiveList();
        params.put("objectiveItemList", objectiveItemList);
        params.put("subjectiveItemList", subjectiveItemList);
        return stuTopicDao.queryStuScoreRank(params);
    }

    /**
     * 
     * @name: queryAnswerIS
     * @params: [params]
     * @return: java.util.List<java.util.Map>
     * @Author: cuihp
     * @Date: 2018/8/15
     */
    @Override
    public List<TopicClusterVo> queryAnswerIS(Map<String, Object> params) {
        List<TopicClusterVo> topicClusterVos = topicClusterService.queryList4Vo(params);
        List<TopicVo> topicVos = stuTopicDao.queryAnswerIS(params);
        for (TopicClusterVo topicClusterVo : topicClusterVos) {
            String bNum = topicClusterVo.getBNum();
            for (TopicVo topicVo : topicVos) {
                String bNum1 = topicVo.getBNum();
                if(bNum.equals(bNum1)){
                    List<TopicVo> topicList = topicClusterVo.getTopicList();
                    if(topicList == null || topicList.size() <=0)
                        topicList = new ArrayList<TopicVo>();
                    topicList.add(topicVo);
                    topicClusterVo.setTopicList(topicList);
                }
            }
        }

        return topicClusterVos;
    }

    @Override
    public Map<String, Object> queryStu4Map(Map<String, Object> params) {
        Integer code = 0;
        Integer collect1 = (Integer) params.get("collect");
        if (null != collect1) {
            code = TopicState.COLLECT.getCode();
        }
        Integer excellent = (Integer) params.get("excellent");
        if (null != excellent) {
            code = TopicState.EXCELLENT.getCode();
        }
        int count = 0;
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<StuTopicClusterEntity> stuTopicClusters = stuTopicClusterService.queryList(params);//题簇
        for (StuTopicClusterEntity stuTopicCluster : stuTopicClusters) {
            Integer type = stuTopicCluster.getType();
            Integer collect = stuTopicCluster.getCollect();
            Integer excellent1 = stuTopicCluster.getExcellent();
            if (TopicType.Completion.getCode() == type && code == collect || code == excellent1) {
                count += 1;
                Map<String, Object> map = new HashMap<>();
                Integer homeworkId = stuTopicCluster.getHomeworkId();
                HomeworkEntity homeworkEntity = homeworkService.queryObject(homeworkId);
                map.put("homeworkId",homeworkId);
                map.put("type",homeworkEntity.getType());
                map.put("bNum", stuTopicCluster.getBNum());
                map.put("stuId", stuTopicCluster.getStuId());
                resultList.add(map);
            }
        }
        List<StuTopicEntity> stuTopicEntities = stuTopicDao.queryList(params);
        for (StuTopicEntity stuTopicEntity : stuTopicEntities) {
            Integer collect = stuTopicEntity.getCollect();
            Integer excellent2 = stuTopicEntity.getExcellent();
            if (code == collect || code == excellent2) {
                count += 1;
                Map<String, Object> map = new HashMap<>();
                Integer homeworkId = stuTopicEntity.getHomeworkId();
                HomeworkEntity homeworkEntity = homeworkService.queryObject(homeworkId);
                map.put("homeworkId",homeworkId);
                map.put("type",homeworkEntity.getType());
                map.put("bNum", stuTopicEntity.getBNum());
                map.put("sNum", stuTopicEntity.getSNum());
                map.put("stuId", stuTopicEntity.getStuId());
                resultList.add(map);
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", count);
        resultMap.put("list", resultList);

        return resultMap;
    }

    @Override
    public int queryCount4Map(Map<String, Object> params) {
        return stuTopicDao.queryCount4Map(params);
    }


    @Override
    public List<Map> queryCountByGroup4Map(Map<String, Object> params) {
        return stuTopicDao.queryCountByGroup4Map(params);
    }

    @Override
    public WYStatisticsMap wyStatistics(String bNum, String classId, int homeworkId) {
        WYStatisticsMap map = new WYStatisticsMap();

        List<WYStatistics> wyStatistics = stuTopicDao.wyStatistics(bNum,classId, homeworkId);

        Integer minCorrect = null;
        Integer maxUnCorrect = null;
        if (wyStatistics.size() != 0) {
            minCorrect = wyStatistics.get(0).getCorrect();
            maxUnCorrect = wyStatistics.get(0).getUnCorrect();
        }

        Map re = new HashMap();
        for (WYStatistics wyStatistics1 : wyStatistics) {
            Integer correct = wyStatistics1.getCorrect();
            Integer unCorrect = wyStatistics1.getUnCorrect();
            String sNum = wyStatistics1.getSNum();
            re.put(sNum, wyStatistics1);
            if (correct < minCorrect) {
                minCorrect = correct;
            }
            if (unCorrect > maxUnCorrect) {
                maxUnCorrect = unCorrect;
            }
        }
        map.setStuTopicClusterCorrect(minCorrect);
        map.setStuTopicClusterUnCorrect(maxUnCorrect);
        map.setMap(re);
        return map;
    }

    @Override
    public WYStatisticsMap wyStatisticsRevisal(String bNum,String classId, int homeworkId) {
        WYStatisticsMap map = new WYStatisticsMap();

        List<WYStatistics> wyStatistics = stuTopicDao.wyStatisticsRevisal(bNum,classId, homeworkId);

        Integer maxTchRevising = null;
        Integer maxStuRevisinged = null;
        if (wyStatistics.size() != 0) {
            maxTchRevising = wyStatistics.get(0).getTchRevising();
            maxStuRevisinged = wyStatistics.get(0).getStuRevisinged();
        }

        Map re = new HashMap();
        for (WYStatistics wyStatistics1 : wyStatistics) {
            Integer stuRevisinged = wyStatistics1.getStuRevisinged();
            Integer tchRevising = wyStatistics1.getTchRevising();
            String sNum = wyStatistics1.getSNum();
            re.put(sNum, wyStatistics1);
            if (stuRevisinged > maxStuRevisinged) {
                maxStuRevisinged = stuRevisinged;
            }
            if (tchRevising > maxTchRevising) {
                maxTchRevising = tchRevising;
            }
        }
        map.setStuClusterRevisinged(maxStuRevisinged);
        map.setTchClusterRevising(maxTchRevising);
        map.setMap(re);
        return map;
    }

    @Override
    public Boolean haveNoMark(Map<String, Object> params) {
        //未批阅字段
        Integer mark = 0;
        params.put("mark", mark);
        List list = stuTopicDao.queryList(params);
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean haveMark(Map<String, Object> params) {
        //未批阅字段
        Integer mark = 1;
        params.put("mark", mark);
        List list = stuTopicDao.queryList(params);
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean haveRevisal(Map<String, Object> params) {
        // 0 没有 1 有
        Integer revisal = 1;
        params.put("revisal", revisal);
        List list = stuTopicDao.queryList(params);
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
