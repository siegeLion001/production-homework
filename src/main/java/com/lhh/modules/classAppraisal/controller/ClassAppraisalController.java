package com.lhh.modules.classAppraisal.controller;

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
import com.lhh.modules.classAppraisal.entity.ClassAppraisalEntity;
import com.lhh.modules.classAppraisal.service.ClassAppraisalService;


/**
 * 学生表扬批评记录表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 17:39:58
 */
@RestController
@RequestMapping("classappraisal")
public class ClassAppraisalController {
	@Autowired
	private ClassAppraisalService classAppraisalService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("classappraisal:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ClassAppraisalEntity> classAppraisalList = classAppraisalService.queryList(query);
		int total = classAppraisalService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(classAppraisalList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{aprId}")
	@RequiresPermissions("classappraisal:info")
	public R info(@PathVariable("aprId") Integer aprId){
		ClassAppraisalEntity classAppraisal = classAppraisalService.queryObject(aprId);
		
		return R.ok().put("classAppraisal", classAppraisal);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("classappraisal:save")
	public R save(@RequestBody ClassAppraisalEntity classAppraisal){
		classAppraisalService.save(classAppraisal);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("classappraisal:update")
	public R update(@RequestBody ClassAppraisalEntity classAppraisal){
		classAppraisalService.update(classAppraisal);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("classappraisal:delete")
	public R delete(@RequestBody Integer[] aprIds){
		classAppraisalService.deleteBatch(aprIds);
		
		return R.ok();
	}
	
}
