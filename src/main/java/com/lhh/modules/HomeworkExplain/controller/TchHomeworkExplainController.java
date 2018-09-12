package com.lhh.modules.HomeworkExplain.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lhh.common.state.CommentedStatus;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.R;
import com.lhh.common.validator.ValidatorUtils;
import com.lhh.common.validator.group.AddGroup;
import com.lhh.modules.HomeworkExplain.entity.HomeworkExplainEntity;
import com.lhh.modules.HomeworkExplain.entity.HomeworkExplainVo;
import com.lhh.modules.HomeworkExplain.service.HomeworkExplainService;
import com.lhh.modules.HomeworkExplainRel.entity.HomeworkExplainRelEntity;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stuhomework.service.StuHomeworkService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 作业讲解表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:07
 */
@RestController
@RequestMapping("tch/homeworkexplain")
public class TchHomeworkExplainController {
	@Autowired
	private HomeworkExplainService homeworkExplainService;
	@Autowired
	private StuHomeworkService stuHomeworkService;

	/**
	 * 教师端 题目讲解
	 * @Name: saveExplain
	 * @Params: [explainVo]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/6/20
	 */
	@RequestMapping("/homeworkExplan")
	public R saveExplain (@RequestBody HomeworkExplainVo explainVo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		ValidatorUtils.validateEntity(explainVo, AddGroup.class);
		HomeworkExplainEntity homeworkExplainEntity = new HomeworkExplainEntity();
		List vClass = explainVo.getVClass();
		if(vClass !=null && vClass.size()>0){
			List fileList4List = FileUtil.getListReplaceBaseUrl(vClass);
			explainVo.setVClass(fileList4List);
		}
		List vPicture = explainVo.getVPicture();
		if(vPicture !=null && vPicture.size()>0){
			List list2Base = FileUtil.getListReplaceBaseUrl(vPicture);
			explainVo.setVPicture(list2Base);
		}
		PropertyUtils.copyProperties(homeworkExplainEntity,explainVo);
		Integer commentedStatus = explainVo.getCommentedStatus();
		if(CommentedStatus.APPOINTSTU.getCode() == commentedStatus){//指定学生
			HashSet<String> strings = new HashSet<>();
			Map<String, List<String>> stuIdMap = explainVo.getStuIdMap();
			Iterator<Map.Entry<String, List<String>>> iterator = stuIdMap.entrySet().iterator();
			ArrayList<HomeworkExplainRelEntity> homeworkExplainRelEntities = new ArrayList<>();
			while (iterator.hasNext()){
				Map.Entry<String, List<String>> next = iterator.next();
				String classId = next.getKey();
				strings.add(classId);
				List<String> stuIdList = next.getValue();
				for (String s : stuIdList) {
					HomeworkExplainRelEntity homeworkExplainRelEntity = new HomeworkExplainRelEntity();
					homeworkExplainRelEntity.setStuId(s);
					homeworkExplainRelEntity.setClassId(classId);
					homeworkExplainRelEntities.add(homeworkExplainRelEntity);
				}
			}
			ArrayList<String> strings1 = new ArrayList<>(strings);
			homeworkExplainEntity.setClassIds(strings1);
			homeworkExplainService.saveExplain(homeworkExplainEntity,homeworkExplainRelEntities);

		}else if(CommentedStatus.ALLSTU.getCode() == commentedStatus){//所有学生
			//根据作业id 级id
			Map<String,Object> params  = new HashMap<String,Object>();
			params.put("homeworkId",explainVo.getHomeworkid());
			Map<String, Object> stringObjectMap = queryList4StuHomework(params);
			List relEntityList = (List)stringObjectMap.get("relEntityList");
			Set classIdSet = (Set) stringObjectMap.get("classIdSet");
			ArrayList<String> strings = new ArrayList<>(classIdSet);
			homeworkExplainEntity.setClassIds(strings);
			homeworkExplainService.saveExplain(homeworkExplainEntity,relEntityList);
		}else if(CommentedStatus.WRONGSTU.getCode() == commentedStatus){//错误学生
			//添加错误字段
			Map<String,Object> params  = new HashMap<String,Object>();
			params.put("homeworkId",explainVo.getHomeworkid());
			params.put("wrongStu","wrongStu");
			params.put("sNum", explainVo.getSNum());
			Map<String, Object> stringObjectMap = queryList4StuHomework(params);
			List relEntityList = (List)stringObjectMap.get("relEntityList");
			Set classIdSet = (Set) stringObjectMap.get("classIdSet");
			ArrayList<String> strings = new ArrayList<>(classIdSet);
			homeworkExplainEntity.setClassIds(strings);
			homeworkExplainService.saveExplain(homeworkExplainEntity,relEntityList);
		}
		
		return R.ok(1);
	}

	private Map<String, Object> queryList4StuHomework(Map<String,Object> params){
		Map<String, Object> resultMap = new HashMap<>();
		Set<String> classIdSet = new HashSet<>();
		List<HomeworkExplainRelEntity> relEntityList = new ArrayList<>();
		try {
			String wrongStu = params.get("wrongStu").toString();
			List<StuHomeworkEntity> stuHomeworkEntities = stuHomeworkService.queryList4StuAndStuTopic(params);
			for (StuHomeworkEntity stuHomeworkEntity : stuHomeworkEntities) {
				HomeworkExplainRelEntity homeworkExplainRelEntity = new HomeworkExplainRelEntity();
				homeworkExplainRelEntity.setStuId(stuHomeworkEntity.getStuId());
				homeworkExplainRelEntity.setClassId(stuHomeworkEntity.getClassId());
				relEntityList.add(homeworkExplainRelEntity);
			}
			resultMap.put("relEntityList",relEntityList);
			resultMap.put("classIdSet", classIdSet);
		}catch (Exception e){

			List<StuHomeworkEntity> stuHomeworkEntities = stuHomeworkService.queryList(params);

			for (StuHomeworkEntity stuHomeworkEntity : stuHomeworkEntities) {
				HomeworkExplainRelEntity homeworkExplainRelEntity = new HomeworkExplainRelEntity();
				homeworkExplainRelEntity.setStuId(stuHomeworkEntity.getStuId());
				homeworkExplainRelEntity.setClassId(stuHomeworkEntity.getClassId());
				relEntityList.add(homeworkExplainRelEntity);
				classIdSet.add(stuHomeworkEntity.getClassId());
			}
			resultMap.put("relEntityList",relEntityList);
			resultMap.put("classIdSet", classIdSet);

		}finally {
			return resultMap;
		}
	}

	
}
