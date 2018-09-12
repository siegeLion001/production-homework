package com.lhh.modules.appCenter.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.lhh.common.utils.ftp.FtpService;
import com.lhh.modules.appCenter.entity.AppLableInfoEntity;
import com.lhh.modules.appCenter.service.AppLableInfoService;


/**
 * 应用标签信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
@RestController
@RequestMapping("applableinfo")
public class AppLableInfoController {
	@Autowired
	private AppLableInfoService appLableInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("applableinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		
		if(params.size()==1){
			Map param = new HashMap();
		      List<AppLableInfoEntity> appLableInfoList = appLableInfoService.queryList(param);	
			  return R.ok().put("list", appLableInfoList);
		}else{
			Query query = new Query(params);
			List<AppLableInfoEntity> appLableInfoList = appLableInfoService.queryList(query);
			int total = appLableInfoService.queryTotal(query);
			PageUtils pageUtil = new PageUtils(appLableInfoList, total, query.getLimit(), query.getPage());
			return R.ok().put("page", pageUtil);
		}
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{labeId}")
	@RequiresPermissions("applableinfo:info")
	public R info(@PathVariable("labeId") Integer labeId){
		AppLableInfoEntity appLableInfo = appLableInfoService.queryObject(labeId);
		
		return R.ok().put("appLableInfo", appLableInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("applableinfo:save")
	public R save(@RequestBody AppLableInfoEntity appLableInfo){
		appLableInfo.setCreateTime(new Date());
		appLableInfoService.save(appLableInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("applableinfo:update")
	public R update(@RequestBody AppLableInfoEntity appLableInfo){
		AppLableInfoEntity appLableInfoOld =appLableInfoService.queryObject(appLableInfo.getLableId());
		String iconPath = appLableInfoOld.getIconPath();
		if(!appLableInfo.getIconPath().equals(iconPath)){
			String filename ="";
			if(iconPath != null && !"".equals(iconPath)){
				FtpService ftpService = new FtpService();
				boolean a =ftpService.deleteAndClose(iconPath);
			}

		}
		appLableInfoService.update(appLableInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("applableinfo:delete")
	public R delete(@RequestBody Integer[] labeIds){

		for (Integer labeId : labeIds) {
			AppLableInfoEntity appLableInfoEntity = appLableInfoService.queryObject(labeId);
			String iconPath = appLableInfoEntity.getIconPath();
			FtpService ftpService = new FtpService();
			ftpService.deleteAndClose(iconPath);
		}
		appLableInfoService.deleteBatch(labeIds);
		
		return R.ok();
	}
	
}
