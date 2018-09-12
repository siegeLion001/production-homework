package com.lhh.modules.cmtextcontent.controller;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.cmbook.service.CmBookService;
import com.lhh.modules.cmpublisher.service.CmPublisherService;
import com.lhh.modules.cmtextcontent.entity.CmTextContentEntity;
import com.lhh.modules.cmtextcontent.entity.CmTextContentVo;
import com.lhh.modules.cmtextcontent.service.CmTextContentService;
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
@RequestMapping("cmtextcontent")
public class CmTextContentController {
    @Autowired
    private CmTextContentService cmTextContentService;

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
    @RequiresPermissions("cmtextcontent:list")
    public R list(@RequestParam Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
        //查询列表数据
        Query query = new Query(params);

        Map<Integer, String> bookMap = cmBookService.getBookMap();
        Map<Integer, String> publisherMap = cmPublisherService.getPublisherMap();
        Map<Integer, String> typeMap = cmTypeService.getTypeMap();


        List<CmTextContentEntity> cmTextContentList = cmTextContentService.queryList(query);
        List<CmTextContentVo> newList = new ArrayList<>();
        for (CmTextContentEntity cmTextContentEntity : cmTextContentList) {
            CmTextContentVo vo = new CmTextContentVo();
            BeanUtils.copyProperties(vo, cmTextContentEntity);

            vo.setBook(bookMap.get(vo.getBookId()));
            vo.setPublisher(publisherMap.get(vo.getPublisherId()));
            vo.setType(typeMap.get(vo.getTypeId()));

            newList.add(vo);
        }
        int total = cmTextContentService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(newList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cmtextcontent:info")
    public R info(@PathVariable("id") Integer id) {
        CmTextContentEntity cmTextContent = cmTextContentService.queryObject(id);

        return R.ok().put("cmTextContent", cmTextContent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cmtextcontent:save")
    public R save(@RequestBody CmTextContentEntity cmTextContent) {
        cmTextContentService.save(cmTextContent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cmtextcontent:update")
    public R update(@RequestBody CmTextContentEntity cmTextContent) {
        cmTextContentService.update(cmTextContent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cmtextcontent:delete")
    public R delete(@RequestBody Integer[] ids) {
        cmTextContentService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * @Description: 内容查询
     * @Name: contentList
     * @Params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/5/29
     */
    @RequestMapping("/contentList")
    public R contentList(@RequestBody Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        if (query.getLimit() == 0) {
            params.remove("page");
            params.remove("limit");
            List<CmTextContentEntity> cmTextContentList = cmTextContentService.queryList(params);
            return R.ok(1).put("data", cmTextContentList);
        }
        int total = cmTextContentService.queryTotal(query);
        List<CmTextContentEntity> cmTextContentList = cmTextContentService.queryList(query);

        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());

        return R.ok(1).put("page", pageUtil).put("data", cmTextContentList);
    }
}
