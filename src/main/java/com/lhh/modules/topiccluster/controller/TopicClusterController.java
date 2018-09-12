package com.lhh.modules.topiccluster.controller;

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

import com.lhh.modules.topiccluster.entity.TopicClusterEntity;
import com.lhh.modules.topiccluster.service.TopicClusterService;


/**
 * 学生作业表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("topiccluster")
public class TopicClusterController {
	@Autowired
	private TopicClusterService topicClusterService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("topiccluster:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TopicClusterEntity> topicClusterList = topicClusterService.queryList(query);
		int total = topicClusterService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(topicClusterList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("topiccluster:info")
	public R info(@PathVariable("id") Integer id){
		TopicClusterEntity topicCluster = topicClusterService.queryObject(id);
		
		return R.ok().put("topicCluster", topicCluster);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("topiccluster:save")
	public R save(@RequestBody TopicClusterEntity topicCluster){
		topicClusterService.save(topicCluster);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("topiccluster:update")
	public R update(@RequestBody TopicClusterEntity topicCluster){
		topicClusterService.update(topicCluster);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("topiccluster:delete")
	public R delete(@RequestBody Integer[] ids){
		topicClusterService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
