package com.lhh.modules.cmpublisher.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.vo.Option;
import com.lhh.modules.cmbook.service.CmBookService;
import com.lhh.modules.cmpublisher.entity.CmPublisherEntity;
import com.lhh.modules.cmpublisher.entity.CmPublisherVo;
import com.lhh.modules.cmpublisher.service.CmPublisherService;
import com.lhh.modules.cmtextcontent.entity.CmTextContentEntity;
import com.lhh.modules.cmtextcontent.entity.CmTextContentVo;
import com.lhh.modules.cmtype.service.CmTypeService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
@RestController
@RequestMapping("cmpublisher")
public class CmPublisherController {
	@Autowired
	private CmPublisherService cmPublisherService;


	@Autowired
	private CmBookService cmBookService;

	@Autowired
	private CmTypeService cmTypeService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("cmpublisher:list")
	public R list(@RequestParam Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
		//查询列表数据
        Query query = new Query(params);


		Map<Integer, String> bookMap = cmBookService.getBookMap();
		Map<Integer, String> publisherMap = cmPublisherService.getPublisherMap();
		Map<Integer, String> typeMap = cmTypeService.getTypeMap();

		List<CmPublisherEntity> cmPublisherList = cmPublisherService.queryList(query);
		List<CmPublisherVo> newList = new ArrayList<>();
		for (CmPublisherEntity cmPublisherEntity : cmPublisherList) {
			CmPublisherVo vo = new CmPublisherVo();
			BeanUtils.copyProperties(vo, cmPublisherEntity);

			vo.setType(typeMap.get(vo.getTypeId()));

			newList.add(vo);
		}
		int total = cmPublisherService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(newList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cmpublisher:info")
	public R info(@PathVariable("id") Integer id){
		CmPublisherEntity cmPublisher = cmPublisherService.queryObject(id);
		
		return R.ok().put("cmPublisher", cmPublisher);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("cmpublisher:save")
	public R save(@RequestBody CmPublisherEntity cmPublisher){
		cmPublisherService.save(cmPublisher);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("cmpublisher:update")
	public R update(@RequestBody CmPublisherEntity cmPublisher){
		cmPublisherService.update(cmPublisher);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("cmpublisher:delete")
	public R delete(@RequestBody Integer[] ids){
		cmPublisherService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 获取类型下拉数据
	 */
	@RequestMapping("/getOptions")
	public R getOptions(@RequestParam Map<String, Object> params) {
		//查询列表数据
		List<Option> options = cmPublisherService.queryOptions(params);
		return R.ok().put("options", options);
	}

	/**
	 * @Description: 出版社查询
	 * @Name: publisherList
	 * @Params: [params]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/5/29
	 */
	@RequestMapping("/publisherList")
	public R publisherList (@RequestBody Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		if (query.getLimit() == 0) {
			params.remove("page");
			params.remove("limit");
			List<CmPublisherEntity> cmPublisherList = cmPublisherService.queryList(params);
			return R.ok(1).put("data", cmPublisherList);
		}
		int total = cmPublisherService.queryTotal(query);
		List<CmPublisherEntity> cmPublisherList = cmPublisherService.queryList(query);
		PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());

		return R.ok(1).put("page", pageUtil).put("data", cmPublisherList);
	}
}
