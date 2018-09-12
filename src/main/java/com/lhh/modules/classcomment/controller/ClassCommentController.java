package com.lhh.modules.classcomment.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.service.ClassCommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:30
 */
@RestController
@RequestMapping("classcomment")
public class ClassCommentController {
    @Autowired
    private ClassCommentService classCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("classcomment:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ClassCommentEntity> classCommentList = classCommentService.queryList(query);
        int total = classCommentService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(classCommentList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("classcomment:info")
    public R info(@PathVariable("id") Integer id) {
        ClassCommentEntity classComment = classCommentService.queryObject(id);

        return R.ok().put("classComment", classComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("classcomment:save")
    public R save(@RequestBody ClassCommentEntity classComment) {
        classCommentService.save(classComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("classcomment:update")
    public R update(@RequestBody ClassCommentEntity classComment) {
        classCommentService.update(classComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("classcomment:delete")
    public R delete(@RequestBody Integer[] ids) {
        classCommentService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/push")
    public R push(@RequestBody ClassCommentEntity classCommentEntity) {
        classCommentEntity.setCreateTime(new Date());
        classCommentService.save(classCommentEntity);
        return R.ok(1);
    }
}
