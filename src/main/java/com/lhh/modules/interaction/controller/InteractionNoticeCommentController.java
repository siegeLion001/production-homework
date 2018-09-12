package com.lhh.modules.interaction.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.interaction.entity.InteractionNoticeCommentEntity;
import com.lhh.modules.interaction.entity.InteractionNoticeEntity;
import com.lhh.modules.interaction.service.InteractionNoticeCommentService;
import com.lhh.modules.interaction.service.InteractionNoticeService;


/**
 * 师生互动模块公告的评论信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
@RestController
@RequestMapping("/interaction/notice/comment")
public class InteractionNoticeCommentController {
	@Autowired
	private InteractionNoticeCommentService interactionNoticeCommentService;
	
	@Autowired
	private InteractionNoticeService interactionNoticeService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<InteractionNoticeCommentEntity> interactionNoticeCommentList = interactionNoticeCommentService.queryList(query);
		int total = interactionNoticeCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
		
		return R.ok(1).put("data", interactionNoticeCommentList).put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{commentId}")
	public R info(@PathVariable("commentId") Integer commentId){
		InteractionNoticeCommentEntity interactionNoticeComment = interactionNoticeCommentService.queryObject(commentId);
		
		return R.ok().put("interactionNoticeComment", interactionNoticeComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody InteractionNoticeCommentEntity interactionNoticeComment){
		Integer noticeId = interactionNoticeComment.getNoticeId();
		String commentator= interactionNoticeComment.getCommentator();
		InteractionNoticeEntity interactionNotice = interactionNoticeService.queryObject(noticeId);
		String tchId = interactionNotice.getTchId();
		if(!tchId.equals(commentator)){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("noticeId", noticeId);
			map.put("commentator", commentator);
			int count =interactionNoticeCommentService.queryTotal(map);
			if(count>=3){
				return R.error("一则公告最多只能评论3次");
			}
		}
		interactionNoticeComment.setCreateTime(new Date());
		interactionNoticeComment.setDeleteYn("N");
		interactionNoticeCommentService.save(interactionNoticeComment);
		return R.ok(1);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody InteractionNoticeCommentEntity interactionNoticeComment){
		interactionNoticeCommentService.update(interactionNoticeComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] commentIds){
		interactionNoticeCommentService.deleteBatch(commentIds);
		
		return R.ok(1);
	}
	
}
