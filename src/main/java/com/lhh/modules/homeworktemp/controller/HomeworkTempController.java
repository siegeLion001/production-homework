package com.lhh.modules.homeworktemp.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homeworktemp.service.HomeworkTempService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-23 21:38:23
 */
@RestController
@RequestMapping("homeworktemp")
public class HomeworkTempController {
    @Autowired
    private HomeworkTempService homeworkTempService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("homeworktemp:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<HomeworkEntity> homeworkTempList = homeworkTempService.queryList(query);
        int total = homeworkTempService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(homeworkTempList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("homeworktemp:info")
    public R info(@PathVariable("id") Integer id) {
        HomeworkEntity homeworkTemp = homeworkTempService.queryObject(id);

        return R.ok().put("homeworkTemp", homeworkTemp);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("homeworktemp:save")
    public R save(@RequestBody HomeworkEntity homeworkTemp) {
        homeworkTempService.save(homeworkTemp);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("homeworktemp:update")
    public R update(@RequestBody HomeworkEntity homeworkTemp) {
        homeworkTempService.update(homeworkTemp);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("homeworktemp:delete")
    public R delete(@RequestBody Integer[] ids) {
        homeworkTempService.deleteBatch(ids);

        return R.ok();
    }

}
