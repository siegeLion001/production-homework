package com.lhh.modules.interaction.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.homework.domain.ClassInfo;
import com.lhh.modules.interaction.domain.InsertNoticeVo;
import com.lhh.modules.interaction.domain.NoticeListVo;
import com.lhh.modules.interaction.entity.InteractionNoticeEntity;
import com.lhh.modules.interaction.entity.InteractionStuNoticeEntity;
import com.lhh.modules.interaction.service.InteractionNoticeService;
import com.lhh.modules.interaction.service.InteractionStuNoticeService;


/**
 * 师生互动模块的公告信息表
 * 
 * @author menglimei
 * @email menglimei@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
@RestController
@RequestMapping("interaction/notice")
public class InteractionNoticeController {
	@Autowired
	private InteractionNoticeService interactionNoticeService;
	
	@Autowired
	private InteractionStuNoticeService interactionStuNoticeService;
	
	/**
	 * 教师端公告列表
	 * params tchId 教师id， page 页码 ， limit 条数
	 */
	@RequestMapping("/list")
	public R list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<NoticeListVo> noticeListVos = interactionNoticeService.queryList(query);
		int total = interactionNoticeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
		
		return R.ok(1).put("data",noticeListVos).put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{noticeId}")
	public R info(@PathVariable("noticeId") Integer noticeId){
		InteractionNoticeEntity interactionNotice = interactionNoticeService.queryObject(noticeId);
		
		return R.ok().put("interactionNotice", interactionNotice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@Transactional
	public R save(@RequestBody InsertNoticeVo insertNoticeVo){
	    ClassInfo[] classInfos = insertNoticeVo.getClassInfos();
	    if (classInfos == null || classInfos.length <= 0) {
	        return R.error("班级和学生不能为空");
	    }
	    InteractionNoticeEntity interactionNotice = new InteractionNoticeEntity();
	    BeanUtils.copyProperties(insertNoticeVo, interactionNotice);
		interactionNotice.setCreateTime(new Date());
		interactionNotice.setDeleteYn("N");
		interactionNoticeService.save(interactionNotice);
		//学生公告信息写入
		Integer noticeId = interactionNotice.getNoticeId();
		List<InteractionStuNoticeEntity> stuNoticeEntitys = new ArrayList<InteractionStuNoticeEntity>();
        for (ClassInfo classInfo : classInfos) {
            String[] stuIds = classInfo.getStuIds();
            String classId = classInfo.getClassId();
            for (String stuId : stuIds) {
            	InteractionStuNoticeEntity stuNoticeEntity = new InteractionStuNoticeEntity();
            	stuNoticeEntity.setClassId(classId);
            	stuNoticeEntity.setNoticeId(noticeId);
            	stuNoticeEntity.setReadYn("N");
            	stuNoticeEntity.setStuId(stuId);
            	stuNoticeEntity.setCreateTime(new Date());
            	stuNoticeEntitys.add(stuNoticeEntity);
            }
        }
        interactionStuNoticeService.batchSave(stuNoticeEntitys);
		return R.ok(1);
	}
	
	/**
	 * 修改：直可修改 content， comment_yn,push_time
	 */
	@RequestMapping("/update")
	public R update(@RequestBody InteractionNoticeEntity interactionNotice){
		if(interactionNotice.getNoticeId() ==null || "".equals(interactionNotice.getNoticeId())){
			return R.error("公告id不能为空.");
		}else{
			interactionNoticeService.update(interactionNotice);
			return R.ok(1);
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Map<String,Object> params){
		 InteractionNoticeEntity interactionNotice = new  InteractionNoticeEntity();
		 interactionNotice.setNoticeId((Integer)params.get("noticeId"));
		 interactionNotice.setDeleteYn("Y");
		 interactionNoticeService.update(interactionNotice);
		return R.ok(1);
	}
	
}
