package com.lhh.modules.appCenter.controller;

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
import com.lhh.modules.appCenter.entity.AppSearchRecordInfoEntity;
import com.lhh.modules.appCenter.service.AppSearchRecordInfoService;


/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-29 13:53:13
 */
@RestController
@RequestMapping("appsearchrecordinfo")
public class AppSearchRecordInfoController {
	@Autowired
	private AppSearchRecordInfoService appSearchRecordInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("appsearchrecordinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       Query query = new Query(params);

		List<AppSearchRecordInfoEntity> appSearchRecordInfoList = appSearchRecordInfoService.queryList(params);
	//	int total = appSearchRecordInfoService.queryTotal(query);
		int total =appSearchRecordInfoList.size();
		PageUtils pageUtil = new PageUtils(appSearchRecordInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	@RequestMapping("/echartslist")
	@RequiresPermissions("appsearchrecordinfo:list")
	public R echartslist(@RequestParam Map<String, Object> params){
		//查询列表数据
		
		List<AppSearchRecordInfoEntity> appSearchRecordInfoList = appSearchRecordInfoService.queryList(params);
		//	int total = appSearchRecordInfoService.queryTotal(query);
		return R.ok().put("list", appSearchRecordInfoList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{appSearchRecordId}")
	@RequiresPermissions("appsearchrecordinfo:info")
	public R info(@PathVariable("appSearchRecordId") Integer appSearchRecordId){
		AppSearchRecordInfoEntity appSearchRecordInfo = appSearchRecordInfoService.queryObject(appSearchRecordId);
		
		return R.ok().put("appSearchRecordInfo", appSearchRecordInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("appsearchrecordinfo:save")
	public R save(@RequestBody AppSearchRecordInfoEntity appSearchRecordInfo){
		appSearchRecordInfoService.save(appSearchRecordInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("appsearchrecordinfo:update")
	public R update(@RequestBody AppSearchRecordInfoEntity appSearchRecordInfo){
		appSearchRecordInfoService.update(appSearchRecordInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("appsearchrecordinfo:delete")
	public R delete(@RequestBody Integer[] appSearchRecordIds){
		appSearchRecordInfoService.deleteBatch(appSearchRecordIds);
		
		return R.ok();
	}
	
}
