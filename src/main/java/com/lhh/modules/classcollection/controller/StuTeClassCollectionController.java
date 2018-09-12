package com.lhh.modules.classcollection.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.R;
import com.lhh.modules.classcollection.entity.ClassCollectionEntity;
import com.lhh.modules.classcollection.service.ClassCollectionService;
import com.lhh.modules.classtop.controller.StuClassTopController;
import com.lhh.modules.classtop.entity.vo.ClassTopPushVo;
import com.lhh.modules.classtop.service.ClassTopService;


/**
 * 收藏表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-24 16:41:18
 */
@RestController
@RequestMapping("stu/classcollection")
public class StuTeClassCollectionController {
	@Autowired
	private ClassCollectionService classCollectionService;

	@Autowired
	private ClassCollectionController classCollectionController;
	@Autowired
	private StuClassTopController stuClassTopController;
	@Autowired
	private ClassTopService classTopService;

	/**
	 * 点击 收藏/成长记录
	 */
	@RequestMapping("/collection")
	public R collection(@RequestBody ClassCollectionEntity classCollection){
		return classCollectionController.collection(classCollection);
	}

	/**
	 * 取消 收藏/成长记录
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

	/**
	 * 成长记录添加
	 * @name: growthRecordSave 
	 * @params: [classTopPushVo]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/26
	 */
	@RequestMapping("/growthRecordSave")
	public R growthRecordSave(@RequestBody ClassTopPushVo classTopPushVo){
		Integer topId=null;
		Integer overt = classTopPushVo.getOvert();
		Integer type = classTopPushVo.getType();

		if(type==5){
			if(overt== 1){
				R push = stuClassTopController.push(classTopPushVo);
				Integer code = Integer.valueOf((String) push.get("code"));
				if(code==500)
					return R.error("数据提交失败");
				topId = classTopPushVo.getId();
			}else if(overt ==2){
				classTopService.save(classTopPushVo);
				topId = classTopPushVo.getId();
			}
			ClassCollectionEntity classCollectionEntity = new ClassCollectionEntity();
			classCollectionEntity.setAuthId(classTopPushVo.getAuthId());
			classCollectionEntity.setAuthType(classTopPushVo.getAuthType());
			classCollectionEntity.setClassId(classTopPushVo.getClassId());
			classCollectionEntity.setCreateTime(new Date());
			classCollectionEntity.setTopId(topId);
			classCollectionEntity.setCollMark(2); //1,收藏  2,成长记录
			classCollectionService.save(classCollectionEntity);
			return R.ok(1);
		}

		return R.error("代码提交路径有误 或 type类型选择错误");
	}



}
