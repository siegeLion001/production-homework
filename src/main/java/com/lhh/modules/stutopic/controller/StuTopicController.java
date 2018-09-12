package com.lhh.modules.stutopic.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.stutopic.service.StuTopicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 学生作业表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("stutopic")
public class StuTopicController {
	@Autowired
	private StuTopicService stuTopicService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("stutopic:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<StuTopicEntity> stuTopicList = stuTopicService.queryList(query);
		int total = stuTopicService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(stuTopicList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("stutopic:info")
	public R info(@PathVariable("id") Integer id){
		StuTopicEntity stuTopic = stuTopicService.queryObject(id);
		
		return R.ok().put("stuTopic", stuTopic);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("stutopic:save")
	public R save(@RequestBody StuTopicEntity stuTopic){
		stuTopicService.save(stuTopic);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("stutopic:update")
	public R update(@RequestBody StuTopicEntity stuTopic){
		stuTopicService.update(stuTopic);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("stutopic:delete")
	public R delete(@RequestBody Integer[] ids){
		stuTopicService.deleteBatch(ids);
		
		return R.ok();
	}
	@RequestMapping("/test")
	public R test (@RequestBody Map<String, Object> params){
		stuTopicService.queryTotalByMap(params);
		return  R.ok().put("data", params);
	}
}
