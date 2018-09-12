package com.lhh.modules.HomeworkExplain.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.HomeworkExplain.entity.HomeworkExplainEntity;
import com.lhh.modules.HomeworkExplain.service.HomeworkExplainService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




/**
 * 作业讲解表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:07
 */
@RestController
@RequestMapping("homeworkexplain")
public class HomeworkExplainController {
	@Autowired
	private HomeworkExplainService homeworkExplainService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("homeworkexplain:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HomeworkExplainEntity> homeworkExplainList = homeworkExplainService.queryList(query);
		int total = homeworkExplainService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(homeworkExplainList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("homeworkexplain:info")
	public R info(@PathVariable("id") Integer id){
		HomeworkExplainEntity homeworkExplain = homeworkExplainService.queryObject(id);
		
		return R.ok().put("homeworkExplain", homeworkExplain);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("homeworkexplain:save")
	public R save(@RequestBody HomeworkExplainEntity homeworkExplain){
		homeworkExplainService.save(homeworkExplain);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("homeworkexplain:update")
	public R update(@RequestBody HomeworkExplainEntity homeworkExplain){
		homeworkExplainService.update(homeworkExplain);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("homeworkexplain:delete")
	public R delete(@RequestBody Integer[] ids){
		homeworkExplainService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
