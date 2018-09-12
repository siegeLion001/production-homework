package com.lhh.modules.cardtemplate.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.cardtemplate.entity.CardTemplateEntity;
import com.lhh.modules.cardtemplate.service.CardTemplateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 09:46:50
 */
@RestController
@RequestMapping("cardtemplate")
public class CardTemplateController {
    @Autowired
    private CardTemplateService cardTemplateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cardtemplate:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CardTemplateEntity> cardTemplateList = cardTemplateService.queryList(query);
        int total = cardTemplateService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(cardTemplateList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cardtemplate:info")
    public R info(@PathVariable("id") Integer id) {
        CardTemplateEntity cardTemplate = cardTemplateService.queryObject(id);

        return R.ok().put("cardTemplate", cardTemplate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cardtemplate:save")
    public R save(@RequestBody CardTemplateEntity cardTemplate) {
        cardTemplateService.save(cardTemplate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cardtemplate:update")
    public R update(@RequestBody CardTemplateEntity cardTemplate) {
        cardTemplateService.update(cardTemplate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cardtemplate:delete")
    public R delete(@RequestBody Integer[] ids) {
        cardTemplateService.deleteBatch(ids);

        return R.ok();
    }

}
