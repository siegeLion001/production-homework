package com.lhh.modules.classAppraisal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classAppraisal.entity.ClassAppraisalEntity;
import com.lhh.modules.classAppraisal.entity.ClassAppraisalVo;
import com.lhh.modules.classAppraisal.service.ClassAppraisalService;
import com.lhh.modules.classMessage.entity.ClassMessageEntity;
import com.lhh.modules.classMessage.service.ClassMessageService;


@RestController
@RequestMapping("app/classappraisal")
public class ClassAppraisalAppController {
	@Autowired
	private ClassAppraisalService classAppraisalService;
	@Autowired
	private ClassMessageService classMessageService;

    /**
     * 花名册列表页查询
     * @name: roster 
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/10
     */
	@RequestMapping("/roster")
	public R roster(@RequestBody Map<String,Object> params) throws IOException {
		String authId = String.valueOf(params.get("authId"));
		String classId = String.valueOf(params.get("classId"));
		if(StringUtils.isBlank(authId))
			return R.error("教师id不能为空");
		if(StringUtils.isBlank(classId))
			return R.error("班级id不能为空");
		List<Map> rosterList = classAppraisalService.roster(params);

		return R.ok(1).put("data",rosterList);
	}
	/**
	 * 学生表扬批评详情展示(成长记录)
	 * @name: queryList4stu 
	 * @params: []
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/10
	 */
	@RequestMapping("/queryList4stu")
	public R queryList4stu(@RequestBody Map<String,Object> params){

        String targetId = (String) params.get("targetId");
        if (StringUtils.isBlank(targetId))
            return R.error("被评论人不能为空");
		Object page = params.get("page");
		if(null != page){
			Query query = new Query(params);
			List<ClassAppraisalEntity> resultList = classAppraisalService.queryList(query);
			int total = classAppraisalService.queryTotal(query);
			PageUtils pageUtils = new PageUtils(total, query.getLimit(), query.getPage());

			return R.ok(1).put("data",resultList).put("page",pageUtils);
		}
		List<ClassAppraisalEntity> resultList = classAppraisalService.queryList(params);
		return R.ok(1).put("data",resultList);
    };
	/**
	 * 学业档案
	 * @name: queryRecord4stu 
	 * @params: [params]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/26
	 */
    @RequestMapping("/queryRecord4stu")
    public R queryRecord4stu(@RequestBody Map<String,Object> params){
		//班级id 学生id  学期字段验证
		String classId;
		String targetId;
		try {
			classId = (String) params.get("classId");
			targetId = (String) params.get("targetId");
			//TODO 应该验证学期id
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("班级id,学生id不能为空");
		}
		//one 根据学生id 学期查询该学生的表扬批评百分比   每种评价的次数
		Map<String, Object> appraisalCount = classAppraisalService.queryAppraisalCount4Stu(params);

		//two 考试成绩  暂时不知道数据来源  todo

		//three  随堂测试  来源暂时没有  TODO

		//成绩寄语
		HashMap<String, Object> msgMap = new HashMap<>();
		params.put("classId",classId);
		params.put("targetId",targetId);
		//TODO 应该有学期id 暂时没有以后添加
		List<ClassMessageEntity> classMessageEntities = classMessageService.queryList(params);
		Map<String,Object>resultMap = new HashMap<>();
		resultMap.put("appraisalCount",appraisalCount);
		resultMap.put("messageList",classMessageEntities);
		return R.ok(1).put("data",resultMap);



	}

	/**
	 * 花名册列表 表扬/批评  点评多人功能
	 * @name: praiseOrCriticism 
	 * @params: []
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/26
	 */
	@RequestMapping("/praiseOrCriticism")
	public R praiseOrCriticism (@RequestBody ClassAppraisalVo classAppraisalVo){
		List<String> stuList = classAppraisalVo.getTargetList();
		ArrayList<ClassAppraisalEntity> appraisalList = new ArrayList<>();
		if(null ==stuList || stuList.size()<=0)
			return R.error("被评论人集合不能为空！");
		for (String s : stuList) {
			ClassAppraisalEntity classAppraisalEntity = new ClassAppraisalEntity();
			BeanUtils.copyProperties(classAppraisalVo, classAppraisalEntity);
			classAppraisalEntity.setTargetId(s);
			appraisalList.add(classAppraisalEntity);
		}
		if( appraisalList!=null && appraisalList.size()>0)
			classAppraisalService.saveBatch(appraisalList);
		return R.ok(1);
	}
	/**
	 * 表扬批评详情接口
	 * @name: queryAppraisalDetail 
	 * @params: [params]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/8/9
	 */
	@RequestMapping("/queryAppraisalDetail")
	public R queryAppraisalDetail(@RequestBody Map<String,Object> params){

		//班级id 学生id  学期字段验证
		String classId;
		String targetId;
		try {
			classId = (String) params.get("classId");
			targetId = (String) params.get("targetId");
			//TODO 应该验证学期id
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("班级id,被评论人id不能为空");
		}

		//one 根据学生id 学期查询该学生的表扬批评百分比   每种评价的次数
		Map<String, Object> appraisalCount = classAppraisalService.queryAppraisalCount4Stu(params);
		return R.ok(1).put("data",appraisalCount);
	}
	/**
	 * 表扬/批评回复功能
	 * @name: replyAppraisal 
	 * @params: [entity]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/8/16
	 */
	@RequestMapping("/replyAppraisal")
	public R replyAppraisal(@RequestBody ClassAppraisalEntity entity){
		Integer parentId = entity.getParentId();
		if(parentId == null || parentId == 0)
			return R.error("parentId不能为空");
		classAppraisalService.save(entity);
		return R.ok(1);
	}
	/**
	 * 我的消息   表现评价
	 * @name: queryAppraisal
	 * @params: [params]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/8/16
	 */
	@RequestMapping("/queryAppraisals")
	public R queryAppraisal(@RequestBody Map<String,Object> params){
		Object page = params.get("page");
		if(page!=null){
			Query query = new Query(params);
			List<ClassAppraisalVo> classAppraisalVos = classAppraisalService.queryAppraisals(query);
			int i = classAppraisalService.queryTotalNoChild(query);
			PageUtils pageUtils = new PageUtils(i, query.getLimit(), query.getPage());
			return R.ok(1).put("data",classAppraisalVos).put("page",pageUtils);
		}
		List<ClassAppraisalVo> classAppraisalVos = classAppraisalService.queryAppraisals(params);

		return R.ok(1).put("data",classAppraisalVos);
	}
	
}
