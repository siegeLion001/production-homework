package com.lhh.modules.sad.controller;

import com.lhh.common.utils.R;
import com.lhh.common.utils.SadService;
import com.lhh.modules.sad.vo.SADBook;
import com.lhh.modules.sad.vo.SADQueryVo;
import com.lhh.modules.usermaterial.entity.UserMaterialEntity;
import com.lhh.modules.usermaterial.service.UserMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 间接封装圣安德接口
 *
 * @author miaofu
 * @email miaof@lanhaihui.net
 * @date 2018-03-12 11:07:44
 */
@RestController
@RequestMapping("tch/sad")
public class TchSadController {

    @Autowired
    SadController sadController;

    @Autowired
    UserMaterialService userMaterialService;

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

    /**
     * 获取 根据学校id 获取全部课本
     */
    @RequestMapping("/ebooksBySchoolId")
    public R ebooksBySchoolId(@RequestBody SADQueryVo sadQueryVo) throws IOException {
        List<SADBook> books = SadService.ebooksBySchoolId(sadQueryVo.getSchoolId());
        return R.ok(1).put("data", books);
    }

    /**
     * 常用课本查询
     */
    @RequestMapping("/materialBookList")
    public R materialBookList(@RequestBody Map<String, Object> params) {
        List<UserMaterialEntity> userMaterialEntities = userMaterialService.queryList(params);
        return R.ok(1).put("data", userMaterialEntities);
    }

    /**
     * 教师设置常用课本
     */
    @RequestMapping("/materialBook")
    public R materialBook(@RequestBody UserMaterialEntity[] userMaterialEntitiesu) {
        for (UserMaterialEntity userMaterialEntity: userMaterialEntitiesu){
            userMaterialService.save(userMaterialEntity);
        }
        return R.ok(1);
    }

    /**
     * 取消设置常用课本
     */
    @RequestMapping("/unMaterialBook")
    public R unMaterialBook(@RequestBody UserMaterialEntity[] userMaterialEntitiesu) {
        for (UserMaterialEntity userMaterialEntity: userMaterialEntitiesu){
            userMaterialService.delete(userMaterialEntity.getUserMaterialId());
        }
        return R.ok(1);
    }
}