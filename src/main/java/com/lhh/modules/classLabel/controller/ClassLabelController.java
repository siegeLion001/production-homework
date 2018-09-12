package com.lhh.modules.classLabel.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classLabel.entity.ClassLabelEntity;
import com.lhh.modules.classLabel.service.ClassLabelService;


/**
 * 标签表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 13:50:28
 */
@RestController
@RequestMapping("classlabel")
public class ClassLabelController {
	@Autowired
	private ClassLabelService classLabelService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("classlabel:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ClassLabelEntity> classLabelList = classLabelService.queryList(query);
		int total = classLabelService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(classLabelList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{labelid}")
	@RequiresPermissions("classlabel:info")
	public R info(@PathVariable("labelid") Integer labelid){
		ClassLabelEntity classLabel = classLabelService.queryObject(labelid);
		
		return R.ok().put("classLabel", classLabel);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("classlabel:save")
	public R save(@RequestBody ClassLabelEntity classLabel){
		classLabelService.save(classLabel);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("classlabel:update")
	public R update(@RequestBody ClassLabelEntity classLabel){
		classLabelService.update(classLabel);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("classlabel:delete")
	public R delete(@RequestBody Integer[] labelids){
		classLabelService.deleteBatch(labelids);
		
		return R.ok();
	}
	
}
