package com.lhh.modules.HomeworkExplainRel.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.HomeworkExplainRel.entity.HomeworkExplainRelEntity;
import com.lhh.modules.HomeworkExplainRel.service.HomeworkExplainRelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




/**
 * 作业讲解关联表

 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:03
 */
@RestController
@RequestMapping("homeworkexplainrel")
public class HomeworkExplainRelController {
	@Autowired
	private HomeworkExplainRelService homeworkExplainRelService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("homeworkexplainrel:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HomeworkExplainRelEntity> homeworkExplainRelList = homeworkExplainRelService.queryList(query);
		int total = homeworkExplainRelService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(homeworkExplainRelList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("homeworkexplainrel:info")
	public R info(@PathVariable("id") Integer id){
		HomeworkExplainRelEntity homeworkExplainRel = homeworkExplainRelService.queryObject(id);
		
		return R.ok().put("homeworkExplainRel", homeworkExplainRel);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("homeworkexplainrel:save")
	public R save(@RequestBody HomeworkExplainRelEntity homeworkExplainRel){
		homeworkExplainRelService.save(homeworkExplainRel);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("homeworkexplainrel:update")
	public R update(@RequestBody HomeworkExplainRelEntity homeworkExplainRel){
		homeworkExplainRelService.update(homeworkExplainRel);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("homeworkexplainrel:delete")
	public R delete(@RequestBody Integer[] ids){
		homeworkExplainRelService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
