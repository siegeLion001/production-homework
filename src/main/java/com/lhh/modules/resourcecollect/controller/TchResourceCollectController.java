package com.lhh.modules.resourcecollect.controller;

import com.lhh.common.utils.R;
import com.lhh.modules.resourcecollect.entity.ResourceCollectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 资源收藏表 用于表示当前用户收藏的外部资源
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-09 15:42:12
 */
@RestController
@RequestMapping("tch/resourcecollect")
public class TchResourceCollectController {
    @Autowired
    private ResourceCollectController resourceCollectController;


    /**
     * 收藏资源
     */

    @RequestMapping("/collect")
    public R collect(@RequestBody ResourceCollectEntity resourceCollectEntity) {
        return resourceCollectController.collect(resourceCollectEntity);
    }

    @RequestMapping("/unCollect")
    public R unCollect(@RequestBody ResourceCollectEntity resourceCollectEntity) {
        return resourceCollectController.unCollect(resourceCollectEntity);
    }
}
