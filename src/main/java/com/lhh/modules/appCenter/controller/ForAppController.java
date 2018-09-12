package com.lhh.modules.appCenter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.appCenter.entity.AppCommentInfoEntity;
import com.lhh.modules.appCenter.entity.AppDownloadRecordInfoEntity;
import com.lhh.modules.appCenter.entity.AppInfoEntity;
import com.lhh.modules.appCenter.entity.AppInfoModel;
import com.lhh.modules.appCenter.entity.AppLableInfoEntity;
import com.lhh.modules.appCenter.entity.AppSearchRecordInfoEntity;
import com.lhh.modules.appCenter.entity.AppVersionInfoEntity;
import com.lhh.modules.appCenter.service.AppCommentInfoService;
import com.lhh.modules.appCenter.service.AppDownloadRecordInfoService;
import com.lhh.modules.appCenter.service.AppInfoService;
import com.lhh.modules.appCenter.service.AppLableInfoService;
import com.lhh.modules.appCenter.service.AppSearchRecordInfoService;
import com.lhh.modules.appCenter.service.AppVersionInfoService;


/**
 * 应用信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
@RestController
@RequestMapping("app")
public class ForAppController {
	@Autowired
	private AppInfoService appInfoService;
	
	@Autowired
	private AppVersionInfoService appVersionInfoService;
	@Autowired
	private AppLableInfoService appLableInfoService;
	@Autowired
	private AppDownloadRecordInfoService appDownloadRecordInfoService;
	@Autowired
	private AppCommentInfoService appCommentInfoService;
	@Autowired 
	private AppSearchRecordInfoService appSearchRecordInfoService;
	/**
	 * App列表接口
	 * @param params
	 * @return
	 */
	@RequestMapping("/queryAppList")
	public R queryApp(@RequestBody Map<String,Object> params){
		//查询列表数据
		List<String> lables= new ArrayList<String>();
		String str = (String)params.get("lableId");
		String[] arr = str.split(",");
		for(String lableId: arr ){
			lables.add(lableId);
		}
		params.put("lables", lables);
        Query query = new Query(params);
		List<AppInfoModel> appList = appInfoService.queryAppList(query);
		int total = appInfoService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
		//记录搜索记录
		if(params.get("appName") != null && !"".equals(params.get("appName"))){
			AppSearchRecordInfoEntity appSearchRecordInfo= appSearchRecordInfoService.queryOne(params);
			if(appSearchRecordInfo != null){
				appSearchRecordInfo.setCount(appSearchRecordInfo.getCount()+1);
				appSearchRecordInfoService.update(appSearchRecordInfo);
			}else{
				AppSearchRecordInfoEntity searchRecord = new AppSearchRecordInfoEntity();
				String appName = (String)params.get("appName");
				searchRecord.setContent(appName);
				searchRecord.setCount(1);
				searchRecord.setCreateTime(new Date());
				appSearchRecordInfoService.save(searchRecord);
			}
		}
		return R.ok(1).put("page", pageUtil).put("data", appList);

	}
	/**
	 * 添加下载记录
	 */
	@RequestMapping("/addDownLoad")
	@Transactional
	public R appDownLoad(@RequestBody Map<String,Object> params){
		//添加下载记录
		AppDownloadRecordInfoEntity recordInfo = new AppDownloadRecordInfoEntity();
		recordInfo.setUserId((String)params.get("userId"));
		recordInfo.setCreateTime(new Date());
		recordInfo.setAppId((Integer)params.get("appId"));
		recordInfo.setAppVersionId((Integer)params.get("appVersionId"));
		recordInfo.setDeleteYn("N");
		recordInfo.setType((String)params.get("type"));
		recordInfo.setUserName((String)params.get("userName"));
		appDownloadRecordInfoService.save(recordInfo);
		//修改app下载次数
		AppInfoEntity appInfo = new AppInfoEntity();
		appInfo.setAppId((Integer)params.get("appId"));
		appInfo.setDownloadCount(1);
	    appInfoService.update(appInfo);
		return R.ok(1);
	}
	/**
	 * 标签列表
	 * @return
	 */
	@RequestMapping("/lableList")
	public R lableList(){
		Map<String, Object> map = new HashMap<>();
		List<AppLableInfoEntity> appLableInfoList = appLableInfoService.queryList(map);
		return R.ok(1).put("data", appLableInfoList);
	}
	/**
	 * 根据应用包名查询最新安装包
	 */
	@RequestMapping("/getApkVersion")
	public R pakList(@RequestBody Map<String,Object> params){
		List<AppVersionInfoEntity> apkInfoList = appVersionInfoService.getApkVersion(params);
		return R.ok(1).put("data", apkInfoList);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/saveComment")
	@Transactional
	public R save(@RequestBody AppCommentInfoEntity appCommentInfo){
		//设置评论时间
		appCommentInfo.setCommentTime(new Date());
		//设置上级评论id
		appCommentInfo.setParentId(0);
		//添加评论信息
		appCommentInfoService.save(appCommentInfo);
		//修改app评分
		AppInfoEntity appInfo = new AppInfoEntity();
		int appVersionId = appCommentInfo.getAppVersionId();
		AppVersionInfoEntity appVersionInfo = appVersionInfoService.queryObject(appVersionId);
		appInfo = appInfoService.queryObject(appVersionInfo.getAppId());
		double totalScore = appInfo.getScore()*appInfo.getCommentCount()+appCommentInfo.getScore();
		double totalCount = appInfo.getCommentCount()+1;
		double score = totalScore/totalCount;
		appInfo.setCommentCount(appInfo.getCommentCount()+1);
		appInfo.setScore(score);
		appInfoService.update(appInfo);
		return R.ok(1);
	}
	
	/**
	 * 评论列表
	 */
	@RequestMapping("/commentList")
	public R commentList(@RequestBody Map<String,Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AppCommentInfoEntity> appCommentInfoList = appCommentInfoService.queryList(query);
	//	int total = appCommentInfoService.queryTotal(query);
			int total = appCommentInfoList.size();
		
		PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
		
		return R.ok(1).put("page", pageUtil).put("data", appCommentInfoList);
	}
	/**
	 * 相关推荐
	 */
	@RequestMapping("/recommend")
	public R recommend(@RequestBody Map<String,Object> params){
		//查询列表数据
		List<String> lables= new ArrayList<String>();
		String str = (String)params.get("lableId");
		String[] arr = str.split(",");
		for(String lableId: arr ){
			lables.add(lableId);
		}
		params.put("lables", lables);
		params.put("recommend", "1");
        Query query = new Query(params);
		List<AppInfoModel> appList = appInfoService.queryAppList(query);
		int total = appInfoService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
		
		return R.ok(1).put("page", pageUtil).put("data", appList);
	}
	/**
	 * 删除应用
	 * @param params
	 * @return
	 */
	@RequestMapping("/delete")
	public R deleteApp(@RequestBody Map<String,Object> params){
		appDownloadRecordInfoService.delete(params);
		return R.ok(1);
	}
}
