package com.lhh.modules.resourcecollect.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.resourcecollect.entity.ResourceCollectEntity;
import com.lhh.modules.resourcecollect.service.ResourceCollectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 资源收藏表 用于表示当前用户收藏的外部资源
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-09 15:42:12
 */
@RestController
@RequestMapping("resourcecollect")
public class ResourceCollectController {
    @Autowired
    private ResourceCollectService resourceCollectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("resourcecollect:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ResourceCollectEntity> resourceCollectList = resourceCollectService.queryList(query);
        int total = resourceCollectService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(resourceCollectList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("resourcecollect:info")
    public R info(@PathVariable("id") Integer id) {
        ResourceCollectEntity resourceCollect = resourceCollectService.queryObject(id);

        return R.ok().put("resourceCollect", resourceCollect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("resourcecollect:save")
    public R save(@RequestBody ResourceCollectEntity resourceCollect) {
        resourceCollectService.save(resourceCollect);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("resourcecollect:update")
    public R update(@RequestBody ResourceCollectEntity resourceCollect) {
        resourceCollectService.update(resourceCollect);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("resourcecollect:delete")
    public R delete(@RequestBody Integer[] ids) {
        resourceCollectService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 收藏资源
     */

    @RequestMapping("/collect")
    public R collect(@RequestBody ResourceCollectEntity resourceCollectEntity) {
        resourceCollectEntity.setCreateTime(new Date());
        resourceCollectService.save(resourceCollectEntity);
        return R.ok(1);
    }

    /**
     * 取消资源
     */
    @RequestMapping("/unCollect")
    public R unCollect(@RequestBody ResourceCollectEntity resourceCollectEntity) {
        String authId = resourceCollectEntity.getAuthId();
        String resourceId = resourceCollectEntity.getResourceId();
        Integer authType = resourceCollectEntity.getAuthType();
        Integer resourceType = resourceCollectEntity.getResourceType();
        Map qm = new HashMap();
        qm.put("authId", authId);
        qm.put("resourceId", resourceId);
        qm.put("authType", authType);
        qm.put("resourceType", resourceType);
        List<ResourceCollectEntity> list = resourceCollectService.queryList(qm);

        for (ResourceCollectEntity r : list) {
            resourceCollectService.delete(r.getId());
        }
        return R.ok(1);
    }


}
