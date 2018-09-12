package com.lhh.modules.banana.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.utils.ftp.FtpService;
import com.lhh.modules.banana.entity.BananaEntity;
import com.lhh.modules.banana.service.BananaService;

import freemarker.template.TemplateException;


/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-28 11:13:03
 */
@RestController
@RequestMapping("banana")
public class BananaController {
	@Autowired
	private BananaService bananaService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BananaEntity> bananaList = bananaService.queryList(query);
		for (BananaEntity bananaEntity : bananaList) {
			String url = bananaEntity.getUrl();
			if(StringUtils.isNotBlank(url)){
				String strByReplacePlaceholder = FileUtil.getStrReplacePlaceholder(url);
				bananaEntity.setUrl(strByReplacePlaceholder);
			}
		}
		int total = bananaService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bananaList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("banana:info")
	public R info(@PathVariable("id") Integer id){
		BananaEntity banana = bananaService.queryObject(id);
		String url = banana.getUrl();
		String basUrl = FileUtil.getStrReplacePlaceholder(url);
		banana.setUrl(basUrl);
		return R.ok().put("banana", banana);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("banana:save")
	public R save(@RequestBody BananaEntity banana){

		Date date = new Date();
		banana.setUpdateTime(date);
		banana.setCreateTime(date);
		String url = banana.getUrl();
		if(StringUtils.isNotBlank(url)){
			String strByReplaceBase = FileUtil.getStrReplaceBaseUrl(url);
			banana.setUrl(strByReplaceBase);
		}
		bananaService.save(banana);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("banana:update")
	public R update(@RequestBody BananaEntity banana){
		Integer id = banana.getId();
		BananaEntity bananaEntity = bananaService.queryObject(id);
		String url1 = bananaEntity.getUrl();
		String url = banana.getUrl();
		url = FileUtil.getStrReplaceBaseUrl(url);
		if(!url1.equals(url)){
			FtpService ftpService = new FtpService();
			boolean b = ftpService.deleteAndClose(url1);
		}

		banana.setUpdateTime(new Date());

//		if(StringUtils.isNotBlank(url)){
//			String strByReplaceBase = FileUtil.getStrReplaceBaseUrl(url);
//			banana.setUrl(strByReplaceBase);
//		}
		bananaService.update(banana);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("banana:delete")
	public R delete(@RequestBody Integer[] ids){
		for (Integer id : ids) {
			BananaEntity bananaEntity = bananaService.queryObject(id);
			String url = bananaEntity.getUrl();
			FtpService ftpService = new FtpService();
			ftpService.deleteAndClose(url);
		}
		bananaService.deleteBatch(ids);
		
		return R.ok();
	}
	@RequestMapping("/queryBanana")
	public R queryBaanana(@RequestBody Map<String,Object> params, HttpServletRequest request) throws IOException, TemplateException {

		StringBuffer requestURL = request.getRequestURL();
		String realPath = requestURL.toString();
		int i = realPath.lastIndexOf("/");
		realPath = realPath.substring(0,i);
		int i1 = realPath.lastIndexOf("/");
		realPath = realPath.substring(0,i1);

		Object client = params.get("client");
		if(client == null){
			return R.error("client不能为空");
		}
		List<BananaEntity> bananaList = bananaService.queryList(params);
		ArrayList<String> resultList = new ArrayList<>();
		for (BananaEntity bananaEntity : bananaList) {
			String url = bananaEntity.getUrl();
			if(StringUtils.isNotBlank(url)){
				String strByReplacePlaceholder = FileUtil.getStrReplacePlaceholder(url);
				bananaEntity.setUrl(strByReplacePlaceholder);
				resultList.add(strByReplacePlaceholder);
			}
			String content = bananaEntity.getContent();
			if(StringUtils.isNotBlank(content)){


				bananaEntity.setConnect(realPath+"/app/banana/queryContent/"+bananaEntity.getId());
			}
		}

		return R.ok(1).put("data", bananaList);
//		return R.ok(1).put("data", resultList);
	}



}
