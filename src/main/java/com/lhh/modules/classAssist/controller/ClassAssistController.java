package com.lhh.modules.classAssist.controller;

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
import com.lhh.modules.classAssist.entity.ClassAssistEntity;
import com.lhh.modules.classAssist.service.ClassAssistService;


/**
 * 点赞表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-26 09:52:54
 */
@RestController
@RequestMapping("classassist")
public class ClassAssistController {
	@Autowired
	private ClassAssistService classAssistService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("classassist:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ClassAssistEntity> classAssistList = classAssistService.queryList(query);
		int total = classAssistService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(classAssistList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{assId}")
	@RequiresPermissions("classassist:info")
	public R info(@PathVariable("assId") Integer assId){
		ClassAssistEntity classAssist = classAssistService.queryObject(assId);
		
		return R.ok().put("classAssist", classAssist);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("classassist:save")
	public R save(@RequestBody ClassAssistEntity classAssist){
		classAssistService.save(classAssist);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("classassist:update")
	public R update(@RequestBody ClassAssistEntity classAssist){
		classAssistService.update(classAssist);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("classassist:delete")
	public R delete(@RequestBody Integer[] assIds){
		classAssistService.deleteBatch(assIds);
		
		return R.ok();
	}
	
}
