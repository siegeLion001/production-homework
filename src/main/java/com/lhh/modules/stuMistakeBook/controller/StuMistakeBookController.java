package com.lhh.modules.stuMistakeBook.controller;

import java.util.List;
import java.util.Map;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.stuMistakeBook.entity.StuMistakeBookEntity;
import com.lhh.modules.stuMistakeBook.service.StuMistakeBookService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 学生错题本
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-22 16:12:43
 */
@RestController
@RequestMapping("stumistakebook")
public class StuMistakeBookController {
	@Autowired
	private StuMistakeBookService stuMistakeBookService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("stumistakebook:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<StuMistakeBookEntity> stuMistakeBookList = stuMistakeBookService.queryList(query);
		int total = stuMistakeBookService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(stuMistakeBookList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("stumistakebook:info")
	public R info(@PathVariable("id") Integer id){
		StuMistakeBookEntity stuMistakeBook = stuMistakeBookService.queryObject(id);
		
		return R.ok().put("stuMistakeBook", stuMistakeBook);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody StuMistakeBookEntity stuMistakeBook){
		stuMistakeBookService.save(stuMistakeBook);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("stumistakebook:update")
	public R update(@RequestBody StuMistakeBookEntity stuMistakeBook){
		stuMistakeBookService.update(stuMistakeBook);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("stumistakebook:delete")
	public R delete(@RequestBody Integer[] ids){
		stuMistakeBookService.deleteBatch(ids);
		
		return R.ok();
	}

}
