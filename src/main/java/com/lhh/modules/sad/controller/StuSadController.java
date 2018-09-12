package com.lhh.modules.sad.controller;

import com.lhh.common.utils.R;
import com.lhh.common.utils.SadService;
import com.lhh.modules.sad.vo.SADQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 间接封装圣安德接口
 *
 * @author miaofu
 * @email miaof@lanhaihui.net
 * @date 2018-03-12 11:07:44
 */
@RestController
@RequestMapping("stu/sad")
public class StuSadController {

    @Autowired
    SadController sadController;


    /**
     * 查询学生id name 映射
     */
    @RequestMapping("/getUserMap")
    public R getUserMap(@RequestBody SADQueryVo idVo) throws IOException {
        Map map = SadService.getUserMap(idVo.getStuId(), idVo.getTchId());
        return R.ok(1).put("data", map);
    }

    /**
     * 获取 当前学校id
     */
    @RequestMapping("/getSchoolId")
    public R getSchoolId() {
        return sadController.getSchoolId();
    }

}
