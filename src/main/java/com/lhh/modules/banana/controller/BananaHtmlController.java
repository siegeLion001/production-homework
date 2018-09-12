package com.lhh.modules.banana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhh.modules.banana.entity.BananaEntity;
import com.lhh.modules.banana.service.BananaService;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-28 11:13:03
 */
@Controller
@RequestMapping("/app/banana")
public class BananaHtmlController {
    @Autowired
    private BananaService bananaService;

    /**
     * 列表
     */
    @RequestMapping("/queryContent/{id}")
    public String html(@PathVariable("id") Integer id, Model model) {
        BananaEntity banana = bananaService.queryObject(id);
        String content = banana.getContent();

        model.addAttribute("content", content);
        return "bhtml.html";
    }


}
