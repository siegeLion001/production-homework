package com.lhh.modules.classAppraisal.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhh.common.utils.MyDateUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.SadService;
import com.lhh.modules.classAppraisal.dao.ClassAppraisalDao;
import com.lhh.modules.classAppraisal.entity.ClassAppraisalEntity;
import com.lhh.modules.classAppraisal.entity.ClassAppraisalVo;
import com.lhh.modules.classAppraisal.service.ClassAppraisalService;
import com.lhh.modules.classMessage.domain.MsgAndAppraisalVo;


@Service("classAppraisalService")
public class ClassAppraisalServiceImpl implements ClassAppraisalService {
    @Autowired
    private ClassAppraisalDao classAppraisalDao;

	
	@Override
	public ClassAppraisalEntity queryObject(Integer aprId){
		return classAppraisalDao.queryObject(aprId);
	}
	
	@Override
	public List<ClassAppraisalEntity> queryList(Map<String, Object> map){
		return classAppraisalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classAppraisalDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassAppraisalEntity classAppraisal){
		classAppraisalDao.save(classAppraisal);
	}
	
	@Override
	public void update(ClassAppraisalEntity classAppraisal){
		classAppraisalDao.update(classAppraisal);
	}
	
	@Override
	public void delete(Integer aprId){
		classAppraisalDao.delete(aprId);
	}
	
	@Override
	public void deleteBatch(Integer[] aprIds){
		classAppraisalDao.deleteBatch(aprIds);
	}

    @Override
    public List<Map> roster(Map<String, Object> params) throws IOException {
        String stuId = (String) params.get("targetId");
        String tchId = (String) params.get("authId");
        String classId = (String) params.get("classId");

		Map userMap = SadService.getUserMap(stuId, tchId, classId);
		Iterator iterator = userMap.entrySet().iterator();

        ArrayList<Map> resultList = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
//		param.put("tchId", tchId);
        param.put("classId", classId);
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            String key = (String) next.getKey();
            param.put("targetId", key);
            //查询学生表扬次数  批评次数
            Map<String, Object> resultMap = classAppraisalDao.queryAppraise4stu(param);
            if (resultMap.size() > 2) {
                String dayBegin = MyDateUtil.getStringDate(MyDateUtil.getDayBegin());
                String dayEnd = MyDateUtil.getStringDate(MyDateUtil.getDayEnd());
                param.put("createTimeStart", dayBegin);
                param.put("createTimeEnd", dayEnd);
                Map<String, Object> resultMap2 = classAppraisalDao.queryAppraise4stu(param);
                resultMap.put("praiseCountNow", resultMap2.get("praiseCount"));
                resultMap.put("unPraiseCountNow", resultMap2.get("unPraiseCount"));
                resultList.add(resultMap);
            } else {
                Map<String, Object> results = new HashMap<>();
                results.put("targetId", key);
                results.put("praiseCount", 0);
                results.put("unPraiseCount", 0);
                results.put("praiseCountNow", 0);
                results.put("unPraiseCountNow", 0);
                resultList.add(results);
            }
        }
        String sort = (String) params.get("sort");
        if (StringUtils.isNotBlank(sort)) {
            List<Map> maps = sortList(resultList, sort);
            return maps;
        }

        return resultList;
    }

    /**
     * 花名册排序
     * @name: sortList 
     * @params: [resultList, sort]
     * @return: void
     * @Author: cuihp
     * @Date: 2018/7/10
     */
    private List<Map> sortList(List<Map> resultList, String sort) {
        Map[] array = new Map[resultList.size()];
        resultList.toArray(array);
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                Map map = array[j];
                int i1 = Integer.parseInt(String.valueOf(map.get(sort)));
                Map map2 = array[j+1];
                int i2 = Integer.parseInt(String.valueOf(map2.get(sort)));
                if (i1<i2){
                    array[j] = map2;
                    array[j+1] = map;
                }
            }
        }
        resultList.clear();
        resultList = Arrays.asList(array);
        return resultList;
    }

    /**
     * 根据学生id 学期查询该学生的表扬批评百分比   每种评价的次数
     *
     * @name: queryAppraisalCount4Stu
     * @params: [params]
     * @return: java.util.Map<java.lang.String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/11
     */
    public Map<String, Object> queryAppraisalCount4Stu(Map<String, Object> params) {

        Map<String, Object> result = new HashMap<String, Object>();
        //根据班级id 学生id 学期(目前没有可为空) 查询表扬批评次数  统计百分比
        Map<String, Object> stringObjectMap = classAppraisalDao.queryAppraise4stu(params);
        if (stringObjectMap.size() <= 2) {
            result.put("praise", 0);
            result.put("appraise", null);
            return result;
        }
        //查询表扬批评详情列表 ----此处修改  保证单调表扬批评通用
        List<ClassAppraisalEntity> classAppraisalList = null;
        Object page = params.get("page");
        if (null != page) {
            Query query = new Query(params);
            classAppraisalList = queryList(query);
            int total = queryTotal(query);
            PageUtils pageUtils = new PageUtils(total, query.getLimit(), query.getPage());
            if (classAppraisalList != null && classAppraisalList.size() > 0) {
                result.put("detailList", classAppraisalList);
                result.put("page", pageUtils);
            }
        } else {
            classAppraisalList = queryList(params);
            if (classAppraisalList != null && classAppraisalList.size() > 0)
                result.put("detailList", classAppraisalList);
        }

        //根据班级id 学生id 学期(目前没有可为空)  查询每种评价的次数
        //查询表扬
        params.put("state", 1);
        List<Map<String, Object>> resultList = new ArrayList<>();
        appraisalManage(params, resultList);
        //查询批评
        params.put("state", 2);
        appraisalManage(params, resultList);
        //表扬率
        Integer praiseCount = Integer.valueOf(String.valueOf(stringObjectMap.get("praiseCount")));
        Integer unpPaiseCount = Integer.valueOf(String.valueOf(stringObjectMap.get("unPraiseCount")));

        int round = Math.round(praiseCount * 100 / (unpPaiseCount + praiseCount));
        //表扬率
        result.put("praise", round);
        //评价列表
        result.put("appraise", resultList);
        //

        return result;
    }

    @Override
    public void saveBatch(ArrayList<ClassAppraisalEntity> appraisalList) {
        classAppraisalDao.saveBatch(appraisalList);
    }

    @Override
    public List<ClassAppraisalVo> queryVoList(Map<String, Object> params) {
        return classAppraisalDao.queryVoList(params);
    }

    @Override
    public List<ClassAppraisalVo> queryAppraisals(Map<String, Object> params) {
        /*Integer aprId = (Integer) params.get("aprId");
        ClassAppraisalEntity entity = classAppraisalDao.queryObject(aprId);*/
        List<ClassAppraisalVo> classAppraisalVoList = classAppraisalDao.queryListNoChild(params);
        for (ClassAppraisalVo classAppraisalVo : classAppraisalVoList) {
            Integer aprId = classAppraisalVo.getAprId();
            Map<String, Object> map = new HashMap<>();
            map.put("parentId", aprId);
            List<ClassAppraisalVo> classAppraisalVos = classAppraisalDao.queryVoList(map);
            classAppraisalVo.setReplyList(classAppraisalVos);
        }
//


        return classAppraisalVoList;
    }

	@Override
	public List<MsgAndAppraisalVo> appraisalList(Map<String, Object> params) {
		return classAppraisalDao.appraisalList(params);
	}

    @Override
    public List<ClassAppraisalVo> queryListNoChild(Map<String, Object> params) {
        return classAppraisalDao.queryListNoChild(params);
    }

    @Override
    public int queryTotalNoChild(Map<String, Object> params) {
        return classAppraisalDao.queryTotalNoChild(params);
    }

    /**
	 * 根据评论统计数据并处理
	 * @name: appraisalManage 
	 * @params: [params, resultList]
	 * @return: void
	 * @Author: cuihp
	 * @Date: 2018/7/11
	 */
	private void appraisalManage(Map<String,Object> params, List<Map<String,Object>> resultList){
		List<Map<String,Object>> list = classAppraisalDao.queryAppraisalCount4Stu(params);
		Integer totalCount =0;
		if(list!=null &&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> stringObjectMap1 = list.get(i);
				if (i<3){

					resultList.add(stringObjectMap1);
				}else{
					Integer numCount = Integer.parseInt(String.valueOf(stringObjectMap1.get("numCount")));
					totalCount=totalCount+numCount;
				}
			}
		}
		if (totalCount !=0){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("appraisal","其它");
			map.put("state",params.get("state"));
			map.put("numCount",totalCount);
			resultList.add(map);
		}
	}


}
