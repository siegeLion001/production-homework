package com.lhh.modules.cardtemplate.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.cardtemplate.entity.CardTemplateEntity;
import com.lhh.modules.cardtemplate.service.CardTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 09:46:50
 */
@RestController
@RequestMapping("tch/cardtemplate")
public class TchCardTemplateController {

    @Autowired
    private CardTemplateController cardTemplateController;

    @Autowired
    private CardTemplateService cardTemplateService;

    /**
     * 列表
     */
    @RequestMapping("/getMyTemplate")
    public R getMyTemplate(@RequestBody Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CardTemplateEntity> cardTemplateList = cardTemplateService.queryList(query);
        int total = cardTemplateService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil).put("data", cardTemplateList);
    }

    /**
     * 保存
     *
     * @return
     */
    @RequestMapping("/saveMyTemplate")
    public R save(@RequestBody CardTemplateEntity cardTemplate) {
        //查询列表数据
        cardTemplate.setCreateTime(new Date());
        return cardTemplateController.save(cardTemplate);
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping("/updateMyTemplate")
    public R update(@RequestBody CardTemplateEntity cardTemplate) {
        //查询列表数据
        return cardTemplateController.update(cardTemplate);
    }
}
