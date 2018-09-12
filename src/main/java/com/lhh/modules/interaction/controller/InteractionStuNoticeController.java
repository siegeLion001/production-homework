package com.lhh.modules.interaction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.interaction.entity.InteractionStuNoticeEntity;
import com.lhh.modules.interaction.service.InteractionStuNoticeService;


/**
 * 记录已读，未读信息
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
@RestController
@RequestMapping("interaction/stu/notice")
public class InteractionStuNoticeController {
	@Autowired
	private InteractionStuNoticeService interactionStuNoticeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<InteractionStuNoticeEntity> interactionStuNoticeList = interactionStuNoticeService.queryList(query);
		int total = interactionStuNoticeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(interactionStuNoticeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{stuNoticeId}")
	public R info(@PathVariable("stuNoticeId") Integer stuNoticeId){
		InteractionStuNoticeEntity interactionStuNotice = interactionStuNoticeService.queryObject(stuNoticeId);
		
		return R.ok().put("interactionStuNotice", interactionStuNotice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody InteractionStuNoticeEntity interactionStuNotice){
		interactionStuNoticeService.save(interactionStuNotice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody InteractionStuNoticeEntity interactionStuNotice){
		interactionStuNoticeService.update(interactionStuNotice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] stuNoticeIds){
		interactionStuNoticeService.deleteBatch(stuNoticeIds);
		
		return R.ok();
	}
	
}
