package com.lhh.modules.usermaterial.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.usermaterial.entity.UserMaterialEntity;
import com.lhh.modules.usermaterial.service.UserMaterialService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-31 17:25:32
 */
@RestController
@RequestMapping("usermaterial")
public class UserMaterialController {
    @Autowired
    private UserMaterialService userMaterialService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("usermaterial:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserMaterialEntity> userMaterialList = userMaterialService.queryList(query);
        int total = userMaterialService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userMaterialList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userMaterialId}")
    @RequiresPermissions("usermaterial:info")
    public R info(@PathVariable("userMaterialId") Integer userMaterialId) {
        UserMaterialEntity userMaterial = userMaterialService.queryObject(userMaterialId);

        return R.ok().put("userMaterial", userMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("usermaterial:save")
    public R save(@RequestBody UserMaterialEntity userMaterial) {
        userMaterialService.save(userMaterial);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("usermaterial:update")
    public R update(@RequestBody UserMaterialEntity userMaterial) {
        userMaterialService.update(userMaterial);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("usermaterial:delete")
    public R delete(@RequestBody Integer[] userMaterialIds) {
        userMaterialService.deleteBatch(userMaterialIds);

        return R.ok();
    }

}
