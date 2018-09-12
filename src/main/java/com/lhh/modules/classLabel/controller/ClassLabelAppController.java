package com.lhh.modules.classLabel.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.R;
import com.lhh.modules.classLabel.entity.ClassLabelEntity;
import com.lhh.modules.classLabel.service.ClassLabelService;


@RestController
@RequestMapping("app/classlabel")
public class ClassLabelAppController {
	@Autowired
	private ClassLabelService classLabelService;

	/**
	 * 标签查询
	 * @name: labelList 
	 * @params: [params]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/9
	 */
	@RequestMapping("/labelList")
	public R labelList(@RequestBody Map<String, Object> params){
		Object type = params.get("type");
		String createId = (String)params.get("createId");
		if(StringUtils.isBlank(createId))
			return  R.error("创建人不能为空");
		if(null == type)
			return R.error("标签类型不能为空!");
		String limit = String.valueOf(params.get("limit"));
		if(StringUtils.isNotBlank(limit))
			params.put("offset",0);

		//查询列表数据
//		Query query = new Query(params);
		List<ClassLabelEntity> classLabelList = classLabelService.queryLabelList(params);
//		int total = classLabelService.queryTotal(query);
//		PageUtilUtil = new PageUtils(total, query.getLimit(), query.getPage());

		return R.ok(1).put("data", classLabelList);
	}
	/**
	 * 标签保存
	 * @name: labelSave 
	 * @params: [classLabel]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/9
	 */
	@RequestMapping("/labelSave")
	public R labelSave(@RequestBody ClassLabelEntity classLabel){
		Integer type = classLabel.getType();
		String labelName = classLabel.getLabelName();
		String createId = classLabel.getCreateId();
		if(StringUtils.isBlank(labelName) || StringUtils.isBlank(createId)|| type== null)
			return R.error("参数传输有误");
		Map<String, Object> params = new HashMap<>();
		params.put("type",type);
		params.put("labelName",labelName);
		params.put("createId",createId);
		List<ClassLabelEntity> list = classLabelService.queryLabelList(params);
		if(list !=null &&list.size()>0){
			return R.error("该标签已经存在!");
		}
		classLabel.setCreateTime(new Date());
		classLabelService.save(classLabel);
		return R.ok(1);
	}
	

	/**
	 * 
	 * @name: labelDelete
	 * @params: [labelId]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/9
	 */
	@RequestMapping("/labelDelete")
	public R labelDelete(@RequestBody Integer labelId){
		if(labelId== null)
			return R.error("标签id不能为空");
		classLabelService.delete(labelId);
		
		return R.ok(1);
	}
	
}
