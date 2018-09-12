package com.lhh.modules.stuhomework.service.impl;

import com.alibaba.fastjson.JSON;
import com.lhh.common.state.StuMacroStatus;
import com.lhh.common.state.TchMacroStatus;
import com.lhh.modules.homework.dao.HomeworkDao;
import com.lhh.modules.stuhomework.dao.StuHomeworkDao;
import com.lhh.modules.stuhomework.domain.StuHomeworkVo;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopic.service.StuTopicService;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("stuHomeworkService")
public class StuHomeworkServiceImpl implements StuHomeworkService {
    @Autowired
    private StuHomeworkDao stuHomeworkDao;
    @Autowired
    private StuTopicClusterService stuTopicClusterService;
    @Autowired
    private StuTopicService stuTopicService;
    @Autowired
    private HomeworkDao homeworkDao;

    @Override
    public StuHomeworkEntity queryObject(Integer id) {
        return stuHomeworkDao.queryObject(id);
    }

    @Override
    public List<StuHomeworkEntity> queryList(Map<String, Object> map) {
        return stuHomeworkDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stuHomeworkDao.queryTotal(map);
    }

    @Override
    public void save(StuHomeworkEntity stuHomework) {
        stuHomeworkDao.save(stuHomework);
    }

    @Override
    public void update(StuHomeworkEntity stuHomework) {
        stuHomeworkDao.update(stuHomework);
    }

    @Override
    public void delete(Integer id) {
        stuHomeworkDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        stuHomeworkDao.deleteBatch(ids);
    }

    @Override
    public List<StuHomeworkVo> queryListByWhere(Map<String, Object> map) {
        return stuHomeworkDao.queryListByWhere(map);
    }

    @Override
    public int queryListByWhereTotal(Map<String, Object> map) {
        return stuHomeworkDao.queryListByWhereTotal(map);
    }

    /**
     * 按照题簇插入
     */
    @Transactional
    @Override
    public void saveTopics(List<StuTopicVo> stuTopicEntityList, StuTopicClusterVo stuTopicClusterEntity) {
        stuTopicClusterService.save(stuTopicClusterEntity);
        Integer id = stuTopicClusterEntity.getId();
        for (StuTopicVo stuTopicEntity : stuTopicEntityList) {
            stuTopicEntity.setStuTopicClusterId(id);
            System.out.println("-----" + JSON.toJSONString(stuTopicEntity));
        }
        stuTopicService.batchSave(stuTopicEntityList);


    }

    /**
     * 按照题簇更新
     */
    @Transactional
    @Override
    public void updateTopics(List<StuTopicVo> stuTopicEntityList, StuTopicClusterVo stuTopicClusterEntity) {
        stuTopicClusterService.update(stuTopicClusterEntity);
        Integer id = stuTopicClusterEntity.getId();
        for (StuTopicVo stuTopicEntity : stuTopicEntityList) {
            stuTopicEntity.setStuTopicClusterId(id);
            System.out.println("-----" + JSON.toJSONString(stuTopicEntity));
        }
        stuTopicService.batchUpdate(stuTopicEntityList);


    }

    @Override
    public StuHomeworkVo queryStuHomeworkVo(Map<String, Object> map) {
        return stuHomeworkDao.queryStuHomeworkVo(map);
    }

    @Override
    public void batchSave(List<StuHomeworkEntity> stuHomeworkList) {
        stuHomeworkDao.saveBatch(stuHomeworkList);
    }

    @Override
    public void delete4Map(Map<String, Object> params) {
        stuHomeworkDao.delete4Map(params);
    }

    @Override
    public int querTotal4Map(Map<String, Object> params) {
        return stuHomeworkDao.querTotal4Map(params);
    }

    @Override
    public List<StuHomeworkVo> queryStu4Map(Map<String, Object> params) {
        return stuHomeworkDao.queryStu4Map(params);
    }

    @Override
    public Long queryTimeKeep4Homework(Map<String, Object> params) {
        return stuHomeworkDao.queryTimeKeep4Homework(params);
    }

    @Override
    public Map<String, Object> queryStuStat(Map<String, Object> params) {
        return stuHomeworkDao.queryStuStat(params);
    }

    @Override
    public List<StuHomeworkEntity> queryList4StuAndStuTopic(Map<String, Object> params) {
        return stuHomeworkDao.queryList4StuAndStuTopic(params);
    }

    /**
     * 判断 如果 改作业下所有学生作业 存在未批的，返回false
     * 否则 已批 和订正的作业 返回 true
     *
     * @param homeworkId
     * @return
     */
    @Override
    public boolean allHomeworkIsTchYesCorrect(Integer homeworkId) {
        List<Map<Integer, Integer>> maps = stuHomeworkDao.groupByTchMacroStatus(homeworkId);
        for (Map<Integer, Integer> map : maps) {
            Integer tchMacroStatus = map.get("tchMacroStatus");
            if (tchMacroStatus.equals(TchMacroStatus.NOCORRECT.getState())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Float getActualScore(String stuId, Integer homeworkId) {
        Map<String, Object> map = new HashMap<>();
        map.put("stuId", stuId);
        map.put("homeworkId", homeworkId);
        return stuHomeworkDao.getActualScore(map);
    }

    @Override
    public Map numStatistics(String classId, int homeworkId) {

        Double fullScore = homeworkDao.fullScore(homeworkId);
        Map map = stuHomeworkDao.numStatistics(classId, homeworkId);
        if (map == null) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("maxClass", null);
            resultMap.put("avgClass", null);
            resultMap.put("avgClassTime", null);
            resultMap.put("fullScore", fullScore);
            return resultMap;
        }
        map.put("fullScore", fullScore);
        return map;
    }

    @Override
    public List<Map<String, Object>> queryOralReviewsRank(Map<String, Object> params) {
        return stuHomeworkDao.queryOralReviewsRank(params);
    }

    @Override
    public Map<String, Object> queryOralReviewsScore(Map<String, Object> params) {
        return stuHomeworkDao.queryOralReviewsScore(params);
    }

    @Override
    public Map<String, Object> queryOralReviewsRate(Map<String, Object> params) {
        int fail = 0;
        int pass = 0;
        int excellent = 0;
        int well = 0;
        List<Map<String, Object>> list = queryOralReviewsRank(params);

        for (Map<String, Object> map : list) {
            Double scoreRate = (Double) map.get("scoreRate");
            if (scoreRate < 0.6)
                fail += 1;
            if (scoreRate >= 0.6 && scoreRate < 0.8)
                pass += 1;
            if (scoreRate >= 0.8 && scoreRate < 0.9)
                well += 1;
            if (scoreRate >= 0.9 && scoreRate <= 1)
                excellent += 1;
        }
        HashMap<String, Object> resultMap = new HashMap<>();
        int size = list.size();

        resultMap.put("fail", SplitAndRound((double) fail / size, 1));
        resultMap.put("pass", SplitAndRound((double) pass / size, 1));
        resultMap.put("well", SplitAndRound((double) well / size, 1));
        resultMap.put("excellent", SplitAndRound((double) excellent / size, 1));
        return resultMap;
    }

    @Override
    public List<StuHomeworkEntity> queryAllSthwByHidAmdCid(Integer homeworkId, String classId) {
        Map qm = new HashMap();
        qm.put("stuMacroStatus", StuMacroStatus.DONE.getState());
        qm.put("homeworkId", homeworkId);
        qm.put("classId", classId);
        return this.queryList(qm);
    }

    @Override
    public StuHomeworkEntity queryByHomeworkIdAndStuId(Integer homeworkId, String stuId) {
        Map qm = new HashMap();
        qm.put("homeworkId", homeworkId);
        qm.put("stuId", stuId);
        List<StuHomeworkEntity> list = this.queryList(qm);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }


    public double SplitAndRound(double a, int n) {
        a = a * Math.pow(10, n);
        return (Math.round(a)) / (Math.pow(10, n));
    }

    @Override
    public List<String> selectAllStuId(Map<String, Object> params) {
        return stuHomeworkDao.selectAllStuId(params);
    }

    @Override
    public void batchUpdate(List<? extends StuHomeworkEntity> stuHomeworkEntities) {
        stuHomeworkDao.batchUpdate(stuHomeworkEntities);
    }

}

