package com.lhh.modules.appCenter.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.lhh.modules.appCenter.entity.AppCommentInfoEntity;
import com.lhh.modules.appCenter.service.AppCommentInfoService;
import com.lhh.modules.sys.controller.AbstractController;
import com.lhh.modules.sys.entity.SysUserEntity;


/**
 * 应用评论信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
@RestController
@RequestMapping("appcommentinfo")
public class AppCommentInfoController  extends AbstractController{
	@Autowired
	private AppCommentInfoService appCommentInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("appcommentinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       // Query query = new Query(params);

		List<AppCommentInfoEntity> appCommentInfoList = appCommentInfoService.list(params);
		//int total = appCommentInfoService.queryTotal(query);
		
		//PageUtils pageUtil = new PageUtils(appCommentInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("list", appCommentInfoList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{commentId}")
	@RequiresPermissions("appcommentinfo:info")
	public R info(@PathVariable("commentId") Integer commentId){
		AppCommentInfoEntity appCommentInfo = appCommentInfoService.queryObject(commentId);
		
		return R.ok().put("appCommentInfo", appCommentInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("appcommentinfo:save")
	public R save(HttpServletResponse response,@RequestBody AppCommentInfoEntity appCommentInfo){
		 SysUserEntity  sysUser = getUser();
		 appCommentInfo.setUserId(sysUser.getUserId().toString());
		 appCommentInfo.setCommentTime(new Date());
		 appCommentInfo.setUserName(sysUser.getName());
		 appCommentInfoService.save(appCommentInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("appcommentinfo:update")
	public R update(@RequestBody AppCommentInfoEntity appCommentInfo){
		appCommentInfoService.update(appCommentInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("appcommentinfo:delete")
	public R delete(@RequestBody Integer[] commentIds){
		appCommentInfoService.deleteBatch(commentIds);
		
		return R.ok();
	}
	
}
