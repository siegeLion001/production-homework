package com.lhh.modules.appCenter.controller;

import java.util.Date;
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
import com.lhh.modules.appCenter.entity.AppVersionInfoEntity;
import com.lhh.modules.appCenter.service.AppVersionInfoService;


/**
 * 应用版本信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
@RestController
@RequestMapping("appversioninfo")
public class AppVersionInfoController {
	@Autowired
	private AppVersionInfoService appVersionInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("appversioninfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AppVersionInfoEntity> appVersionInfoList = appVersionInfoService.queryList(query);
		int total = appVersionInfoService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(appVersionInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{appVersionId}")
	@RequiresPermissions("appversioninfo:info")
	public R info(@PathVariable("appVersionId") Integer appVersionId){
		AppVersionInfoEntity appVersionInfo = appVersionInfoService.queryObject(appVersionId);
		return R.ok().put("appVersionInfo", appVersionInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("appversioninfo:save")
	public R save(@RequestBody AppVersionInfoEntity appVersionInfo){
    	appVersionInfo.setCreateTime(new Date());
    	appVersionInfo.setDeleteYn("N");
		appVersionInfoService.save(appVersionInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("appversioninfo:update")
	public R update(@RequestBody AppVersionInfoEntity appVersionInfo){
		appVersionInfoService.update(appVersionInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("appversioninfo:delete")
	public R delete(@RequestBody Integer[] appVersionIds){
		appVersionInfoService.deleteBatch(appVersionIds);
		
		return R.ok();
	}
	
}
