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
import com.lhh.modules.appCenter.entity.AppDownloadRecordInfoEntity;
import com.lhh.modules.appCenter.service.AppDownloadRecordInfoService;


/**
 * 应用下载记录
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
@RestController
@RequestMapping("appdownloadrecordinfo")
public class AppDownloadRecordInfoController {
	@Autowired
	private AppDownloadRecordInfoService appDownloadRecordInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("appdownloadrecordinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AppDownloadRecordInfoEntity> appDownloadRecordInfoList = appDownloadRecordInfoService.queryList(query);
		int total = appDownloadRecordInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(appDownloadRecordInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{recordId}")
	@RequiresPermissions("appdownloadrecordinfo:info")
	public R info(@PathVariable("recordId") Integer recordId){
		AppDownloadRecordInfoEntity appDownloadRecordInfo = appDownloadRecordInfoService.queryObject(recordId);
		
		return R.ok().put("appDownloadRecordInfo", appDownloadRecordInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("appdownloadrecordinfo:save")
	public R save(@RequestBody AppDownloadRecordInfoEntity appDownloadRecordInfo){
		appDownloadRecordInfoService.save(appDownloadRecordInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("appdownloadrecordinfo:update")
	public R update(@RequestBody AppDownloadRecordInfoEntity appDownloadRecordInfo){
		appDownloadRecordInfoService.update(appDownloadRecordInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("appdownloadrecordinfo:delete")
	public R delete(@RequestBody Integer[] recordIds){
		appDownloadRecordInfoService.deleteBatch(recordIds);
		
		return R.ok();
	}
	
}
