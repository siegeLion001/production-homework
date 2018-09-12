package com.lhh.modules.classtop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classAssist.entity.ClassAssistEntity;
import com.lhh.modules.classAssist.service.ClassAssistService;
import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.service.ClassCommentService;
import com.lhh.modules.classtop.domain.QueryVo;
import com.lhh.modules.classtop.entity.ClassTopEntity;
import com.lhh.modules.classtop.entity.vo.ClassTopParam;
import com.lhh.modules.classtop.entity.vo.ClassTopVo;
import com.lhh.modules.classtop.service.ClassTopService;
import com.lhh.modules.homework.domain.ClassInfo;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
@RestController
@RequestMapping("classtop")
public class ClassTopController {
    @Autowired
    private ClassTopService classTopService;

    @Autowired
    private ClassCommentService classCommentService;
    @Autowired
    private ClassAssistService classAssistService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("classtop:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ClassTopEntity> classTopList = classTopService.queryList(query);
        int total = classTopService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(classTopList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("classtop:info")
    public R info(@PathVariable("id") Integer id) {
        ClassTopEntity classTop = classTopService.queryObject(id);

        return R.ok().put("classTop", classTop);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("classtop:save")
    public R save(@RequestBody ClassTopEntity classTop) {
        classTopService.save(classTop);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("classtop:update")
    public R update(@RequestBody ClassTopEntity classTop) {
        classTopService.update(classTop);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("classtop:delete")
    public R delete(@RequestBody Integer[] ids) {
        classTopService.deleteBatch(ids);

        return R.ok();
    }


    @RequestMapping("/myList")
    public R myList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ClassTopVo> classTopList = classTopService.queryMyList(query);
        for (ClassTopVo classTopVo : classTopList) {
            Integer id = classTopVo.getId();
            HashMap<String, Object> map = new HashMap<>();
            map.put("topId", id);
            List<ClassCommentEntity> classCommentEntities = classCommentService.queryList(map);
            classTopVo.setClassComments(classCommentEntities);
        }

        int total = classTopService.queryMyTotal(query);

        PageUtils pageUtil = new PageUtils(classTopList, total, query.getLimit(), query.getPage());

        return R.ok(1).put("page", pageUtil);
    }

    /**
     * 消息详情
     * h5专用
     */
    @RequestMapping("/myTopDetails")
    public R myTopDetails(@RequestParam QueryVo queryVo) {
        //查询列表数据
        Integer topId = queryVo.getTopId();

        ClassTopEntity classTopEntity = classTopService.queryObject(topId);
        if(null ==classTopEntity)
            return R.error("该topId没有详情, 请输入正确的topId");
        HashMap<String, Object> map = new HashMap<>();
        map.put("topId", topId);
        ClassTopVo classTopVo = new ClassTopVo();
        if(null !=classTopEntity)
            BeanUtils.copyProperties(classTopEntity, classTopVo);

        List<ClassCommentEntity> classCommentEntities = classCommentService.queryList(map);
        classTopVo.setClassComments(classCommentEntities);
        return R.ok(1).put("data", classTopVo);
    }

    /**
     * 任务列表
     */
    @RequestMapping("/taskList")
    public R taskList(@RequestParam Map<String, Object> params) {
        // 查询班我发布的任务
        params.put("type", 2);
        Query query = new Query(params);

        List<ClassTopVo> classTopList = classTopService.queryMyList(query);

        int total = classTopService.queryMyTotal(query);

        PageUtils pageUtil = new PageUtils(classTopList, total, query.getLimit(), query.getPage());

        return R.ok(1).put("page", pageUtil);
    }

    /**
     * 查看
     *
     * @param classTopParam
     * @return
     */
    @RequestMapping("/look")
    public R look(@RequestParam ClassTopParam classTopParam) {
        Integer topId = classTopParam.getTopId();

        ClassTopEntity classTopEntity = classTopService.queryObject(topId);

        classTopEntity = remove(classTopEntity, classTopParam.getClassId(), classTopParam.getStuId(), classTopParam.getTchId());

        classTopService.update(classTopEntity);
        return R.ok(1);
    }


    /**
     * 点赞
     *
     * @param classTopParam
     * @return
     */
    public R praise(@RequestParam ClassTopParam classTopParam) {
        Integer topId = classTopParam.getTopId();

        ClassTopEntity classTopEntity = classTopService.queryObject(topId);
        List<String> praises = Arrays.asList(classTopEntity.getPraises());
        String stuId = classTopParam.getStuId();
        String tchId = classTopParam.getTchId();
        if (StringUtils.isNotEmpty(stuId)) {
            praises.add(stuId);
        } else {
            praises.add(tchId);
        }
        classTopEntity.setPraises(praises.toArray(new String[praises.size()]));
        classTopService.update(classTopEntity);

        ClassAssistEntity classAssistEntity = new ClassAssistEntity();
        classAssistEntity.setTopId(topId);

        if (StringUtils.isNotEmpty(stuId)) {
            classAssistEntity.setAuthId(stuId);
        } else {
            classAssistEntity.setAuthId(tchId);
        }

        String topAuthId = classTopEntity.getAuthId();
        classAssistEntity.setTargetId(topAuthId);

        classAssistEntity.setCreateTime(new Date());
        classAssistEntity.setAssistMark("点赞");

        classAssistService.save(classAssistEntity);


        return R.ok(1);
    }

    /**
     * 取消点赞
     *
     * @param classTopParam
     * @return
     */
    public R unPraise(@RequestParam ClassTopParam classTopParam) {
        Integer topId = classTopParam.getTopId();
        ClassTopEntity classTopEntity = classTopService.queryObject(topId);
        List<String> praises = Arrays.asList(classTopEntity.getPraises());
        String stuId = classTopParam.getStuId();
        String tchId = classTopParam.getTchId();
        if (StringUtils.isNotEmpty(stuId)) {
            praises.remove(stuId);
        } else {
            praises.remove(tchId);
        }
        classTopEntity.setPraises(praises.toArray(new String[praises.size()]));
        classTopService.update(classTopEntity);

        String topAuthId = classTopEntity.getAuthId();

        Map qm = new HashMap();
        qm.put("topId", topId);
        qm.put("targetId", topAuthId);
        if (StringUtils.isNotEmpty(stuId)) {
            qm.put("authId", stuId);
        } else {
            qm.put("authId", tchId);
        }
        List<ClassAssistEntity> list = classAssistService.queryList(qm);

        if (list.size() != 0) {
            ClassAssistEntity classAssistEntity = list.get(0);
            classAssistService.delete(classAssistEntity.getAssId());
        }

        return R.ok(1);
    }


    /**
     * 未阅读 已阅读 的集合处理
     *
     * @param classTop
     * @param stuId
     * @param tehId
     * @param <T>
     * @return
     */
    public <T extends ClassTopEntity> ClassTopEntity remove(T classTop,String classId, String stuId, String tehId) {
        // 已阅读的集合
        List<ClassInfo> alreadyOperateds = Arrays.asList(classTop.getAlreadyOperateds());
        // 未阅读的集合
        List<ClassInfo> notOperateds = Arrays.asList(classTop.getNotOperateds());

        /**
         * 根据传入的是学生id 还是教师id 执行下一步操作
         */
        if (StringUtils.isNotEmpty(stuId)) {

            /**
             * 解析 已阅读的集合
             */
            for (ClassInfo classInfo : alreadyOperateds) {

                /**
                 * 如果查看人的班级 等于 classInfo的班级信息
                 */
                if (classInfo.getClassId().equals(classId)) {
                    /**
                     * 将这个人放到人员集合中 这里是学生集合
                     */
                    List<String> stuIds = Arrays.asList(classInfo.getStuIds());
                    stuIds.add(stuId);
                } else {
                    /**
                     * 否则新建班级
                     */
                    ClassInfo cInfo = new ClassInfo();
                    cInfo.setClassId(classId);
                    cInfo.setStuIds(new String[]{stuId});
                    alreadyOperateds.add(cInfo);
                }
            }
            /**
             * 解析未阅读的集合
             */
            for (ClassInfo classInfo : notOperateds) {
                /**
                 * 当前操作者的班级 比对
                 */
                if (classInfo.getClassId().equals(classId)) {
                    if (classInfo.getStuIds().length > 1) {
                        /**
                         * 将该操作者的id从集合中删除
                         */
                        List<String> stuIds = Arrays.asList(classInfo.getStuIds());
                        stuIds.remove(stuId);
                    } else {
                        alreadyOperateds.remove(classInfo);
                    }
                }
            }
        } else {


            for (ClassInfo classInfo : alreadyOperateds) {
                if (classInfo.getClassId().equals(classId)) {
                    List<String> tchIds = Arrays.asList(classInfo.getTchIds());
                    tchIds.add(stuId);
                } else {
                    ClassInfo cInfo = new ClassInfo();
                    cInfo.setClassId(classId);
                    cInfo.setStuIds(new String[]{stuId});
                    alreadyOperateds.add(cInfo);
                }
            }
            for (ClassInfo classInfo : notOperateds) {
                if (classInfo.getClassId().equals(classId)) {
                    if (classInfo.getStuIds().length > 1) {
                        List<String> tchIds = Arrays.asList(classInfo.getTchIds());
                        tchIds.remove(stuId);
                    } else {
                        alreadyOperateds.remove(classInfo);
                    }
                }
            }
        }

        classTop.setAlreadyOperateds(alreadyOperateds.toArray(new ClassInfo[alreadyOperateds.size()]));
        classTop.setNotOperateds(notOperateds.toArray(new ClassInfo[notOperateds.size()]));
        return classTop;
    }

    /**
     * @name: topList
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/17
     */
    @RequestMapping("/topList")
    public R topList(@RequestBody Map<String, Object> params) {//必传参数 authId  page limit
        try {
            String authId = (String) params.get("authId");
        } catch (Exception e) {
            return R.error("authId 不能为空");
        }
        Map<String, Object> informMap = classTopService.queryInformationCount(params);
        Query query = new Query(params);
        List<ClassTopEntity> topList = classTopService.queryList(query);
        int total = classTopService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        informMap.put("topList", topList);

        return R.ok(1).put("data", informMap).put("page", pageUtil);
    }

    @RequestMapping("/deleteTop")
    public R deleteTop(@RequestBody Integer id) {
        classTopService.deleteTop(id);

        return R.ok(1);
    }
}
