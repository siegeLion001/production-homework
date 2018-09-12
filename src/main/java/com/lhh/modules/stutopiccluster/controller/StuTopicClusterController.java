package com.lhh.modules.stutopiccluster.controller;

import java.util.List;
import java.util.Map;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.modules.stutopiccluster.entity.StuTopicClusterEntity;
import com.lhh.modules.stutopiccluster.service.StuTopicClusterService;


/**
 * 学生作业表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("stutopiccluster")
public class StuTopicClusterController {
	@Autowired
	private StuTopicClusterService stuTopicClusterService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("stutopiccluster:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<StuTopicClusterEntity> stuTopicClusterList = stuTopicClusterService.queryList(query);
		int total = stuTopicClusterService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(stuTopicClusterList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("stutopiccluster:info")
	public R info(@PathVariable("id") Integer id){
		StuTopicClusterEntity stuTopicCluster = stuTopicClusterService.queryObject(id);
		
		return R.ok().put("stuTopicCluster", stuTopicCluster);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("stutopiccluster:save")
	public R save(@RequestBody StuTopicClusterEntity stuTopicCluster){
		stuTopicClusterService.save(stuTopicCluster);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("stutopiccluster:update")
	public R update(@RequestBody StuTopicClusterEntity stuTopicCluster){
		stuTopicClusterService.update(stuTopicCluster);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("stutopiccluster:delete")
	public R delete(@RequestBody Integer[] ids){
		stuTopicClusterService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
