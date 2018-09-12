package com.lhh.modules.classMessage.controller;

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
import com.lhh.modules.classMessage.entity.ClassMessageEntity;
import com.lhh.modules.classMessage.service.ClassMessageService;


/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-12 09:37:33
 */
@RestController
@RequestMapping("classmessage")
public class ClassMessageController {
	@Autowired
	private ClassMessageService classMessageService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("classmessage:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ClassMessageEntity> classMessageList = classMessageService.queryList(query);
		int total = classMessageService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(classMessageList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{msgId}")
	@RequiresPermissions("classmessage:info")
	public R info(@PathVariable("msgId") Integer msgId){
		ClassMessageEntity classMessage = classMessageService.queryObject(msgId);
		
		return R.ok().put("classMessage", classMessage);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("classmessage:save")
	public R save(@RequestBody ClassMessageEntity classMessage){
		classMessageService.save(classMessage);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("classmessage:update")
	public R update(@RequestBody ClassMessageEntity classMessage){
		classMessageService.update(classMessage);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("classmessage:delete")
	public R delete(@RequestBody Integer[] msgIds){
		classMessageService.deleteBatch(msgIds);
		
		return R.ok();
	}
	
}
