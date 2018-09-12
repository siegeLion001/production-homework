package com.lhh.modules.classcollection.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classcollection.entity.ClassCollectionEntity;
import com.lhh.modules.classcollection.service.ClassCollectionService;
import com.lhh.modules.classtop.entity.ClassTopEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 收藏表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-24 16:41:18
 */
@RestController
@RequestMapping("classcollection")
public class ClassCollectionController {
	@Autowired
	private ClassCollectionService classCollectionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("classcollection:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ClassCollectionEntity> classCollectionList = classCollectionService.queryList(query);
		int total = classCollectionService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(classCollectionList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{collId}")
	@RequiresPermissions("classcollection:info")
	public R info(@PathVariable("collId") Integer collId){
		ClassCollectionEntity classCollection = classCollectionService.queryObject(collId);
		return R.ok().put("classCollection", classCollection);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("classcollection:save")
	public R save(@RequestBody ClassCollectionEntity classCollection){
		classCollectionService.save(classCollection);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("classcollection:update")
	public R update(@RequestBody ClassCollectionEntity classCollection){
		classCollectionService.update(classCollection);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("classcollection:delete")
	public R delete(@RequestBody Integer[] collIds){
		classCollectionService.deleteBatch(collIds);
		
		return R.ok();
	}
	/**
	 * 点击 收藏
	 */
	@RequestMapping("/collection")
	public R collection(@RequestBody ClassCollectionEntity classCollection){
		ClassCollectionEntity classCollectionEntity = classCollectionService.queryObjectByTopIdAndAuthId(classCollection.getTopId(), classCollection.getAuthId());
		if(classCollectionEntity==null){
			classCollection.setCreateTime(new Date());
			classCollectionService.save(classCollection);
			return R.ok(1);
		}else{
			return R.error("已收藏过");
		}
	}

	/**
	 * 取消 收藏
	 */
	@RequestMapping("/unCollection")
	public R unCollection(@RequestBody ClassCollectionEntity classCollection){
		ClassCollectionEntity classCollectionEntity = classCollectionService.queryObjectByTopIdAndAuthId(classCollection.getTopId(), classCollection.getAuthId());
		if(classCollectionEntity==null){
			return R.error("没有收藏，无法删除");
		}else{
			Integer collId = classCollectionEntity.getCollId();
			classCollectionService.delete(collId);
			return R.ok(1);
		}
	}

	/**
	 * 我的收藏列表/成长记录列表
	 */
	@RequestMapping("/myCollectionTop")
	public R myCollectionTop(@RequestBody Map<String, Object> params){
		/**
		 * 方法说明(补充):
		 * 根据collMark 判断查询成长记录(2)/收藏记录(1)+authId查询class_collection+class_top表
		 *
		 */
		//查询列表数据
		Query query = new Query(params);
		List<ClassTopEntity> classTopEntities = classCollectionService.queryMyCollectionTop(query);
		int total = classCollectionService.queryMyCollectionTopTotal(query);
		PageUtils pageUtil = new PageUtils(classTopEntities, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
}
