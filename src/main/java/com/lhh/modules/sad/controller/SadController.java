package com.lhh.modules.sad.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lhh.common.utils.R;
import com.lhh.common.utils.SadService;
import com.lhh.modules.sad.vo.SADQueryVo;
import com.lhh.modules.stuMistakeBook.entity.Ebook;

/**
 * 间接封装圣安德接口
 *
 * @author miaofu
 * @email miaof@lanhaihui.net
 * @date 2018-03-12 11:07:44
 */
@RestController
@RequestMapping("sad")
public class SadController {


    /**
     * 查询学生id name 映射
     */
    @RequestMapping("/getUserMap")
    public R getUserMap(@RequestBody SADQueryVo idVo) throws IOException {
        Map map = SadService.getUserMap(idVo.getStuId(), idVo.getTchId());
        return R.ok(1).put("data", map);
    }


    @Test
    public void test() throws IOException {
//        Map map = getMap("5b10b7ccc463ac69e0820a4b", null);
//        System.out.println(JSON.toJSONString(map));
        Map map = SadService.getUserMap(null, "5b10b793c463ac69e0820a4a");
        System.out.println(JSON.toJSONString(map));
    }
    /**
     * 获取章节树   调用圣安德接口
     * @name: getEbookThree
     * @params: [bookId]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/9/7
     */
    @RequestMapping("getEbookThree")
    public R getEbookThree(String bookId) throws IOException {
        List<Ebook> ebookThree = SadService.getEbookThree(bookId);
        if(ebookThree==null)
            return R.error("章节信息获取失败");
        return R.ok(1).put("data",ebookThree);
    }

    /*public Map getUserMap(String stuId, String tchId) throws IOException {

        String url;
        if (stuId == null) {
            url = "http://t.api.tcjyy.com/api/teacher/info/" + tchId;
        } else {
            url = "http://t.api.tcjyy.com/api/student/info/" + stuId;
        }


        String re = HttpUtil.get(url);
//        System.out.println(re);
//        re = new String(re.getBytes("gbk"), "utf-8");
        System.out.println(re);

        Integer code = (Integer) JSONPath.read(re, "$.code");
        Map map = new HashMap();
        if (code == 1) {
            Object classesObj = JSONPath.eval(JSON.parse(re), "$.data.classes");
            List<SADClass> classes = JSONArray.parseArray(classesObj.toString(), SADClass.class);
            for (SADClass sadClass : classes) {
                if (sadClass.getStudents() != null) {
                    for (SADUser sadUser : sadClass.getStudents()) {
                        sadUser.setInfo(null);
                        sadUser.setRole(null);
                        map.put(sadUser.get_id(), sadUser);
                    }
                }
                if (sadClass.getTeachers() != null) {
                    for (SADUser sadUser : sadClass.getTeachers()) {
                        sadUser.setInfo(null);
                        sadUser.setRole(null);
                        map.put(sadUser.get_id(), sadUser);
                    }
                }
            }
        }
        return map;
    }*/


    /**
     * 获取 当前学校id
     */
    @RequestMapping("/getSchoolId")
    public R getSchoolId() {
        //ToDO 从配置文件中获取本系统的学校id
        String schoolId = null;
        return R.ok(1).put("schoolId", schoolId);
    }
}
