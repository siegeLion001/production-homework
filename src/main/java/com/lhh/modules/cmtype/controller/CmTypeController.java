package com.lhh.modules.cmtype.controller;

import java.util.List;
import java.util.Map;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.vo.Option;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.modules.cmtype.entity.CmTypeEntity;
import com.lhh.modules.cmtype.service.CmTypeService;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
@RestController
@RequestMapping("cmtype")
public class CmTypeController {
    @Autowired
    private CmTypeService cmTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cmtype:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CmTypeEntity> cmTypeList = cmTypeService.queryList(query);
        int total = cmTypeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(cmTypeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cmtype:info")
    public R info(@PathVariable("id") Integer id) {
        CmTypeEntity cmType = cmTypeService.queryObject(id);

        return R.ok().put("cmType", cmType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cmtype:save")
    public R save(@RequestBody CmTypeEntity cmType) {
        cmTypeService.save(cmType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cmtype:update")
    public R update(@RequestBody CmTypeEntity cmType) {
        cmTypeService.update(cmType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cmtype:delete")
    public R delete(@RequestBody Integer[] ids) {
        cmTypeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 获取类型下拉数据
     */
    @RequestMapping("/getOptions")
    public R getOptions(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<Option> options = cmTypeService.queryOptions(params);
        return R.ok().put("options", options);
    }

}
