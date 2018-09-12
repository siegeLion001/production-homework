package com.lhh.modules.stutopiccluster.service.impl;

import com.lhh.common.utils.FileUtil;
import com.lhh.modules.stutopic.dao.StuTopicDao;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.stutopiccluster.dao.StuTopicClusterDao;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterExcellentVo;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;
import com.lhh.modules.topic.dao.TopicDao;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topiccluster.dao.TopicClusterDao;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("stuTopicClusterService")
public class StuTopicClusterServiceImpl implements StuTopicClusterService {
    @Autowired
    private StuTopicClusterDao stuTopicClusterDao;

    @Autowired
    private TopicClusterDao topicClusterDao;

    @Autowired
    private StuTopicDao stuTopicDao;

    @Autowired
    private TopicDao topicDao;


    @Override
    public StuTopicClusterEntity queryObject(Integer id) {
        return stuTopicClusterDao.queryObject(id);
    }

    @Override
    public List<StuTopicClusterEntity> queryList(Map<String, Object> map) {
        return stuTopicClusterDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stuTopicClusterDao.queryTotal(map);
    }

    @Override
    public void save(StuTopicClusterEntity stuTopicCluster) {
        stuTopicClusterDao.save(stuTopicCluster);
    }

    @Override
    public void update(StuTopicClusterEntity stuTopicCluster) {
        stuTopicClusterDao.update(stuTopicCluster);
    }

    @Override
    public void delete(Integer id) {
        stuTopicClusterDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        stuTopicClusterDao.deleteBatch(ids);
    }

    @Override
    public List<StuTopicClusterVo> queryVoList(Map<String, Object> params) {
        return stuTopicClusterDao.queryVoList(params);
    }

    @Override
    public void delete4Map(Map<String, Object> params) {
        stuTopicClusterDao.delete4Map(params);
    }

    @Override
    public int queryCount4Map(Map<String, Object> params) {
        return stuTopicClusterDao.queryCount4Map(params);
    }

    @Override
    public List<StuTopicClusterVo> queryTopicClusterAllfield(Map<String, Object> params) {
        String stuId = params.get("stuId").toString();
        Boolean publish = (Boolean) params.remove("publish");
        String homeworkType = (String) params.remove("homeworkType");
        List<TopicClusterVo> topicClusterVoList = topicClusterDao.queryList4Vo(params);
        List<StuTopicClusterVo> reStuTopicClusterVoList = new ArrayList<>();
        for (TopicClusterVo topicClusterVo : topicClusterVoList) {

            params.put("bNum", topicClusterVo.getBNum());
            topicClusterVo.setHomeworkType(homeworkType);
            List<StuTopicClusterVo> tmpList = stuTopicClusterDao.queryVoList(params);
            StuTopicClusterVo tmpStuTopicClusterVo = null;
            if (tmpList != null && !tmpList.isEmpty()) {
                tmpStuTopicClusterVo = tmpList.get(0);
            }
            StuTopicClusterVo stuTopicClusterVo = voToStuVo(topicClusterVo, tmpStuTopicClusterVo);
            Integer rightCount = null;
            // 填充小题
            Map qm = new HashMap();
            qm.put("topicClusterId", stuTopicClusterVo.getTopicClusterId());
            qm.put("sidx", "s_num");
            qm.put("order", "asc");
            if (params.containsKey("sNum")) {
                // 如果查询指定小题
                String sNum = params.get("sNum").toString();
                qm.put("sNum", sNum);
            }
            List<TopicVo> topicVoList = topicDao.queryList4Vo(qm);
            List<StuTopicVo> reStuTopicVoList = new ArrayList<>();
            if (!topicVoList.isEmpty() && topicVoList != null) {
                Float actualScore = null;
                if (publish) {
                    actualScore = 0F;
                    rightCount = 0;
                }
                for (TopicVo topicVo : topicVoList) {
                    topicVo.setHomeworkType(homeworkType);
                    Map qm2 = new HashMap();
                    qm2.put("homeworkId", topicVo.getHomeworkId());
                    qm2.put("bNum", topicVo.getBNum());
                    qm2.put("stuId", stuId);
                    qm2.put("sNum", topicVo.getSNum());
                    StuTopicVo tmpStuTopicVo = null;
                    List<StuTopicVo> stuTopicVoList = stuTopicDao.queryList4Vo(qm2);
                    if (!stuTopicVoList.isEmpty() && stuTopicVoList != null) {
                        tmpStuTopicVo = stuTopicVoList.get(0);
                        // 公开 则计算分数
                        Float tmpActualScore = tmpStuTopicVo.getActualScore();
                        Float sScore = tmpStuTopicVo.getSScore();
                        if (publish) {
                            actualScore = actualScore + tmpActualScore;
                            if(sScore!=null && Math.abs(sScore-tmpActualScore)==0)
                                rightCount = rightCount+1;
                        }

                        /**
                         *  判断题目分数与真实分数是否一致
                         * 一致  创建循环内变量 统计数量
                         * 在外层循环也添加变量  统计数量
                         * 外层循环 同时统计真实得分
                         */
                    }
                    StuTopicVo stuTopicVo = voToStuVo(topicVo, tmpStuTopicVo);

                    reStuTopicVoList.add(stuTopicVo);
                }
                stuTopicClusterVo.setTopicList(reStuTopicVoList);

                if (publish) {
                    stuTopicClusterVo.setActualScore(actualScore);
                }
                //答题下做对题目数
                stuTopicClusterVo.setRightCount(rightCount);
            }
            reStuTopicClusterVoList.add(stuTopicClusterVo);
        }
        return reStuTopicClusterVoList;
    }


    private StuTopicClusterVo voToStuVo(TopicClusterVo topicClusterVo, StuTopicClusterVo stuTopicClusterVo) {
        StuTopicClusterVo re = new StuTopicClusterVo();
        re.setTopicClusterId(topicClusterVo.getId());
        re.setContent(topicClusterVo.getContent());
        re.setType(topicClusterVo.getType());
        re.setBNum(topicClusterVo.getBNum());
        re.setBScore(topicClusterVo.getBScore());
        //获取作业类型 新增字段@郭
        re.setHomeworkType(topicClusterVo.getHomeworkType());
        if (stuTopicClusterVo != null) {
            re.setStuTopicClusterId(stuTopicClusterVo.getId());
            re.setHomeworkId(stuTopicClusterVo.getHomeworkId());
            re.setClassId(stuTopicClusterVo.getClassId());
            re.setStuId(stuTopicClusterVo.getStuId());
            re.setRevisal(stuTopicClusterVo.getRevisal());
            // 替换路径
            re.setSubmitContent(FileUtil.getReplacePlaceholder(stuTopicClusterVo.getSubmitContent()));
            re.setExcellent(stuTopicClusterVo.getExcellent());
            re.setCollect(stuTopicClusterVo.getCollect());
        }
        return re;
    }


    private StuTopicVo voToStuVo(TopicVo topicVo, StuTopicVo stuTopicVo) {
        StuTopicVo re = new StuTopicVo();
        re.setTopicId(topicVo.getId());
        re.setTopicClusterId(topicVo.getTopicClusterId());
        re.setContent(topicVo.getContent());
        re.setCorrectAnswer(topicVo.getCorrectAnswer());
        re.setExplanation(topicVo.getExplanation());
        re.setQuestionId(topicVo.getQuestionId());
        re.setSource(topicVo.getSource());
        re.setType(topicVo.getType());
        re.setBNum(topicVo.getBNum());
        re.setBScore(topicVo.getBScore());
        re.setSNum(topicVo.getSNum());
        re.setSScore(topicVo.getSScore());
        //获取作业类型 新增字段@郭
        re.setHomeworkType(topicVo.getHomeworkType());
        //21 cdn id
        re.setQuestionId(topicVo.getQuestionId());
        if (stuTopicVo != null) {
            re.setStuTopicId(stuTopicVo.getId());
            re.setHomeworkId(stuTopicVo.getHomeworkId());
            re.setClassId(stuTopicVo.getClassId());
            re.setStuId(stuTopicVo.getStuId());
            re.setStuTopicClusterId(stuTopicVo.getTopicClusterId());
            // 路径替换
            re.setSubmitContent(FileUtil.getReplacePlaceholder(stuTopicVo.getSubmitContent()));
            re.setSubmitAnswer(stuTopicVo.getSubmitAnswer());
            re.setRevisal(stuTopicVo.getRevisal());
            re.setRevisalContent(stuTopicVo.getRevisalContent());
            re.setMark(stuTopicVo.getMark());
            re.setActualScore(stuTopicVo.getActualScore());
            re.setExcellent(stuTopicVo.getExcellent());
            re.setCollect(stuTopicVo.getCollect());
        }
        return re;
    }

    @Override
    public List<StuTopicClusterExcellentVo> excellentStuTopicCluster(Map<String, Object> params) {
        return stuTopicClusterDao.excellentStuTopicCluster(params);
    }
}
