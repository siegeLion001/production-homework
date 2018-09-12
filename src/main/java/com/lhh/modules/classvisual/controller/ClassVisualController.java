package com.lhh.modules.classvisual.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classvisual.entity.ClassVisualEntity;
import com.lhh.modules.classvisual.service.ClassVisualService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
@RestController
@RequestMapping("classvisual")
public class ClassVisualController {
    @Autowired
    private ClassVisualService classVisualService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("classvisual:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ClassVisualEntity> classVisualList = classVisualService.queryList(query);
        int total = classVisualService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(classVisualList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("classvisual:info")
    public R info(@PathVariable("id") Integer id) {
        ClassVisualEntity classVisual = classVisualService.queryObject(id);

        return R.ok().put("classVisual", classVisual);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("classvisual:save")
    public R save(@RequestBody ClassVisualEntity classVisual) {
        classVisualService.save(classVisual);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("classvisual:update")
    public R update(@RequestBody ClassVisualEntity classVisual) {
        classVisualService.update(classVisual);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("classvisual:delete")
    public R delete(@RequestBody Integer[] ids) {
        classVisualService.deleteBatch(ids);

        return R.ok();
    }

}
