package com.lhh.modules.cmbook.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.vo.Option;
import com.lhh.modules.cmbook.entity.CmBookEntity;
import com.lhh.modules.cmbook.entity.CmBookVo;
import com.lhh.modules.cmbook.service.CmBookService;
import com.lhh.modules.cmpublisher.service.CmPublisherService;
import com.lhh.modules.cmtype.service.CmTypeService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
@RestController
@RequestMapping("cmbook")
public class CmBookController {
    @Autowired
    private CmBookService cmBookService;

    @Autowired
    private CmPublisherService cmPublisherService;

    @Autowired
    private CmTypeService cmTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cmbook:list")
    public R list(@RequestParam Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
        //查询列表数据
        Query query = new Query(params);

        Map<Integer, String> publisherMap = cmPublisherService.getPublisherMap();
        Map<Integer, String> typeMap = cmTypeService.getTypeMap();


        List<CmBookEntity> cmBookList = cmBookService.queryList(query);

        List<CmBookVo> newList = new ArrayList<>();
        for (CmBookEntity cmBookEntity : cmBookList) {
            CmBookVo vo = new CmBookVo();
            BeanUtils.copyProperties(vo, cmBookEntity);

            vo.setPublisher(publisherMap.get(vo.getPublisherId()));
            vo.setType(typeMap.get(vo.getTypeId()));

            newList.add(vo);
        }

        int total = cmBookService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(newList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cmbook:info")
    public R info(@PathVariable("id") Integer id) {
        CmBookEntity cmBook = cmBookService.queryObject(id);

        return R.ok().put("cmBook", cmBook);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cmbook:save")
    public R save(@RequestBody CmBookEntity cmBook) {
        cmBookService.save(cmBook);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cmbook:update")
    public R update(@RequestBody CmBookEntity cmBook) {
        cmBookService.update(cmBook);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cmbook:delete")
    public R delete(@RequestBody Integer[] ids) {
        cmBookService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 获取类型下拉数据
     */
    @RequestMapping("/getOptions")
    public R getOptions(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<Option> options = cmBookService.queryOptions(params);
        return R.ok().put("options", options);
    }

    /**
     * @Description: 册别列表查询
     * @Name: publisherList
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/5/29
     */
    @RequestMapping("/catalogList")
    public R publisherList(@RequestBody Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        if (query.getLimit() == 0) {
            params.remove("page");
            params.remove("limit");
            List<CmBookEntity> cmBookList = cmBookService.queryList(params);
            return R.ok(1).put("data", cmBookList);
        }
        int total = cmBookService.queryTotal(query);
        List<CmBookEntity> cmBookList = cmBookService.queryList(query);
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());

        return R.ok(1).put("page", pageUtil).put("data", cmBookList);
    }
}
