package com.lhh.modules.classcollection.controller;

import com.lhh.common.utils.R;
import com.lhh.modules.classcollection.entity.ClassCollectionEntity;
import com.lhh.modules.classcollection.service.ClassCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 收藏表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-24 16:41:18
 */
@RestController
@RequestMapping("tch/classcollection")
public class TchTeClassCollectionController {

	@Autowired
	private ClassCollectionService classCollectionService;

	@Autowired
	private ClassCollectionController classCollectionController;

	/**
	 * 点击 收藏
	 */
	@RequestMapping("/collection")
	public R collection(@RequestBody ClassCollectionEntity classCollection){
		return classCollectionController.collection(classCollection);
	}

	/**
	 * 取消 收藏
	 */
	@RequestMapping("/unCollection")
	public R unCollection(@RequestBody ClassCollectionEntity classCollection){
		return classCollectionController.unCollection(classCollection);
	}
	/**
	 * 我的收藏列表
	 */
	@RequestMapping("/myCollectionTop")
	public R myCollectionTop(@RequestBody Map<String, Object> params){
		return classCollectionController.myCollectionTop(params);
	}
}
