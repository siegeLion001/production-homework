package com.lhh.modules.property.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.property.entity.PropertyEntity;
import com.lhh.modules.property.service.PropertyService;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 标段属性表
 * 
 * @author miaofu
 * @email miaof@lanhaihui.net
 * @date 2018-03-12 11:07:44
 */
@RestController
@RequestMapping("property")
public class PropertyController {
	private static Logger log = Logger.getLogger(PropertyController.class);
	@Autowired
	private PropertyService propertyService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
	@RequiresPermissions("property:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PropertyEntity> propertyList = propertyService.queryList(query);
		int total = propertyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(propertyList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("property:info")
	public R info(@PathVariable("id") Integer id){
		PropertyEntity property = propertyService.queryObject(id);
		
		return R.ok().put("property", property);
	}
	
	/**
	 * 保存-车间管理
	 */
	@RequestMapping("/workshopSave")
	@RequiresPermissions("property:workshopSave")
	public R workshopSave(@RequestBody PropertyEntity property){
		try {
			property.setType(2);
			property.setChangeFlag(1);
			property.setCreateTime(new Date());
			propertyService.save(property);
		} catch (Exception e) {
			log.error(e.toString());
			return R.error();
		}
		return R.ok();
	}
	
	/**
	 * 修改-车间管理
	 */
	@RequestMapping("/workshopUpdate")
	@RequiresPermissions("property:workshopUpdate")
	public R workshopUpdate(@RequestBody PropertyEntity property){
		try {
			property.setType(2);
			propertyService.update(property);
		} catch (Exception e) {
			log.error(e.toString());
			return R.error();
		}
		return R.ok();
	}
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("property:save")
	public R save(@RequestBody PropertyEntity property){
		try {
			if (isSave(property.getType())) {
				/*设置为可以被变更*/
				property.setChangeFlag(1);
				property.setCreateTime(new Date());
				propertyService.save(property);
			}else {
				throw new Exception("不允许写入的类型");
			}
		} catch (Exception e) {
			log.error(e.toString());
			return R.error();
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("property:update")
	public R update(@RequestBody PropertyEntity property){
		try {
			if (isSave(property.getType())) {
				propertyService.update(property);
			}else {
				throw new Exception("不允许写入的类型");
			}
		} catch (Exception e) {
			log.error(e.toString());
			return R.error();
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("property:delete")
	public R delete(@RequestBody Integer id){
		
		try {
			PropertyEntity pe = propertyService.queryObject(id);
			/*只有允许可变更的数据和用户写入的类型才可以被删除*/
			if (pe.getChangeFlag() == 1 && isSave(pe.getType())) {
				propertyService.delete(id);
			}else {
				throw new Exception("类型不允许删除");
			}
		} catch (Exception e) {
			log.error(e.toString());
			return R.error();
		}
		return R.ok();
	}
	/**
	 * 添加标段时下拉框显示标段属性信息
	 * ycc
	 * @return
	 */
	@RequestMapping("getCode")
	public R getCode() {
		List<PropertyEntity> propertyList = propertyService.findAll();
		return R.ok().put("organized", propertyList);
	}
	/**
	 * 验证类型是否可以被写入
	 * @param type
	 * @return
	 */
	public boolean isSave(int type) {
		/*允许写入的类型*/
		Integer[] types = {0, 1, 2};
		return Arrays.asList(types).contains(type);
	}
	
}
