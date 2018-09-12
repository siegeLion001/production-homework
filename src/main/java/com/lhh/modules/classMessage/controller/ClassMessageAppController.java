package com.lhh.modules.classMessage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classAppraisal.entity.ClassAppraisalEntity;
import com.lhh.modules.classAppraisal.service.ClassAppraisalService;
import com.lhh.modules.classMessage.domain.ClassMessageVo;
import com.lhh.modules.classMessage.domain.MapVo;
import com.lhh.modules.classMessage.domain.MsgAndAppraisalVo;
import com.lhh.modules.classMessage.entity.ClassMessageEntity;
import com.lhh.modules.classMessage.service.ClassMessageService;


/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-12 09:37:33
 */
@RestController
@RequestMapping("app/classmessage")
public class ClassMessageAppController {
	@Autowired
	private ClassMessageService classMessageService;
	@Autowired
	private ClassAppraisalService classAppraisalService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/msglist")
	public R msglist(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ClassMessageEntity> classMessageList = classMessageService.queryList(query);
		int total = classMessageService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
		
		return R.ok(1).put("data",classMessageList).put("page", pageUtil);
	}

	/**
	 * 学生寄语保存
	 * @name: msgSave 
	 * @params: [classMessage]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/7/12
	 */
	@RequestMapping("/msgSave")
	public R msgSave(@RequestBody ClassMessageEntity classMessage){
		String authId = classMessage.getAuthId();
		String classId = classMessage.getClassId();
		String targetId = classMessage.getTargetId();
//		if(StringUtils.isBlank(authId) || StringUtils.isBlank(classId) || StringUtils.isBlank(targetId))
//			return R.error("authId, classId, targetId 不能为空");
		classMessageService.save(classMessage);
		
		return R.ok(1);
	}
//	/**
//	 * 寄语回复功能
//	 * @name: msgReply
//	 * @params: [classMessageEntity]
//	 * @return: com.lhh.common.utils.R
//	 * @Author: cuihp
//	 * @Date: 2018/8/18
//	 */
//	@RequestMapping("/msgReply")
//	public R msgReply(@RequestBody ClassMessageEntity classMessageEntity){
//		Integer parentId = classMessageEntity.getParentId();
//		if(null == parentId || 0==parentId)
//			return R.error("parentId不能为空");
//		classMessageService.save(classMessageEntity);
//		return R.ok(1);
//	}

	/**
	 * 寄语列表+详情查询 (回复一块查询)[学生端,消息]
	 * @name: msgDetails
	 * @params: [params]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/8/18
	 */
	@RequestMapping("/msgDetails")
	public R msgDetails(@RequestBody Map<String,Object> params){
		Object targetId = params.get("targetId");
		if(null == targetId)
			return R.error("targetId不能为空");
		List<ClassMessageVo> classMessageVos = classMessageService.queryMsgDetails(params);
		return R.ok(1).put("date",classMessageVos);
	}
	/**
	 * 消息+详情  [教师  ]
	 */
	@RequestMapping("/messages")
	public R messages (@RequestBody Map<String,Object> params){

		Object page = params.get("page");
		if(null !=page){
			Query query = new Query(params);
			//表现评价+寄语
			List<MsgAndAppraisalVo> resultList = classMessageService.messages(query);
			int i = classMessageService.msgAndAppraisalCount(query);
			PageUtils pageUtils = new PageUtils(i, query.getLimit(), query.getPage());
			return R.ok(1).put("data",resultList).put("page",pageUtils);
		}
		//表现评价+寄语
		List<MsgAndAppraisalVo> resultList = classMessageService.messages(params);
		return R.ok(1).put("data",resultList);
	}
	/**
	 * 我的消息 回复功能
	 * @name: contentSave
	 * @params: [mapVo]
	 * @return: com.lhh.common.utils.R
	 * @Author: cuihp
	 * @Date: 2018/9/4
	 */
	@RequestMapping("/contentSave")
	public R contentSave(@RequestBody MapVo mapVo){
		//根据state判断插入哪张表
		int state = mapVo.getState();
		if(0==state){
			ClassMessageEntity classMessageEntity = new ClassMessageEntity();
			BeanUtils.copyProperties(mapVo, classMessageEntity);
			classMessageService.save(classMessageEntity);
		}
		if(1==state || 2==state){
			ClassAppraisalEntity classAppraisalEntity = new ClassAppraisalEntity();
			BeanUtils.copyProperties(mapVo,classAppraisalEntity);
			classAppraisalService.save(classAppraisalEntity);
		}
		return R.ok(1);
	}

}
