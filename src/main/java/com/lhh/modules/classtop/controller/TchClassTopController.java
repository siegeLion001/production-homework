package com.lhh.modules.classtop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhh.common.utils.FastMap;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.classcollection.entity.ClassCollectionEntity;
import com.lhh.modules.classcollection.service.ClassCollectionService;
import com.lhh.modules.classcomment.entity.ClassCommentEntity;
import com.lhh.modules.classcomment.service.ClassCommentService;
import com.lhh.modules.classtop.domain.QueryVo;
import com.lhh.modules.classtop.entity.vo.ClassTopParam;
import com.lhh.modules.classtop.entity.vo.ClassTopPushVo;
import com.lhh.modules.classtop.entity.vo.ClassTopVo;
import com.lhh.modules.classtop.service.ClassTopService;
import com.lhh.modules.classvisual.entity.ClassVisualEntity;
import com.lhh.modules.classvisual.service.ClassVisualService;
import com.lhh.modules.homework.domain.ClassInfo;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
@RestController
@RequestMapping("tch/classtop")
public class TchClassTopController {
    @Autowired
    private ClassTopService classTopService;

    @Autowired
    private ClassVisualService classVisualService;

    @Autowired
    private ClassTopController classTopController;

    @Autowired
    private ClassCommentService classCommentService;

    @Autowired
    private ClassCollectionService classCollectionService;

    /**
     * 教师端 消息列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {


        params.put("overt",1);//查询朋友圈展示的  2018/7/26 添加  对不对不知道    ??what  这也行?  对不对问苗芾 他写的
        // 查询班级下所有信息
        Query query = new Query(params);
        List<ClassTopVo> classTopList = classTopService.queryTopListByTarget(query);

        for (ClassTopVo classTopVo : classTopList) {
            Integer topId = classTopVo.getId();
            String authId = classTopVo.getAuthId();
            //收藏表/成长记录与top关联表
            ClassCollectionEntity classCollectionEntity = classCollectionService.queryObjectByTopIdAndAuthId(topId, authId);

            if(classCollectionEntity==null){
                classTopVo.setCollection(false);
            }else {
                classTopVo.setCollection(true);
            }

            List<ClassCommentEntity> classCommentEntityList = classCommentService.queryList(new FastMap().putChain("topId", topId));
            classTopVo.setClassComments(classCommentEntityList);
        }

        int total = classTopService.queryTopTotalByTarget(query);
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("page", pageUtil).put("data", classTopList);
    }

    /**
     * 发送消息 、任务 ，分享
     */
    @RequestMapping("/push")
    public R push(@RequestBody ClassTopPushVo classTopPushVo) {

        ClassInfo[] classInfos = classTopPushVo.getClassInfos();
        String authId = classTopPushVo.getAuthId();
        List<ClassVisualEntity> cvEntityList = new ArrayList<>();
        classTopPushVo.setNotOperateds(classInfos);
        classTopPushVo.setAlreadyOperateds(new ClassInfo[0]);
//        ClassTopEntity classTopEntity = new ClassTopEntity();
//        BeanUtils.copyProperties(classTopPushVo,classTopEntity);
        classTopService.save(classTopPushVo);
        for (ClassInfo classInfo : classInfos) {
            /**
             * 教师发送的时候 精确到学生 ，不需要填写班级id
             *
             * 可以根据这个字段来判断 发送者的类型
             *
             * 学生端查询的时候  联合 关联学生查询 union 关联班级查询
             *
             * 教师对学生 学生对学生 学生对教师 教师对教师
             * 无classId  有classId  有classId  有classId
             *
             * 上述逻辑已经废弃？？  ！！！废弃
             */
            String classsId = classInfo.getClassId();
            String[] stuIds = classInfo.getStuIds();
            for (String stuId : stuIds) {
                ClassVisualEntity cvEntity = new ClassVisualEntity();
                cvEntity.setTopId(classTopPushVo.getId());
                cvEntity.setLookOver(0);
                cvEntity.setAuthId(authId);
                cvEntity.setTargetId(stuId);
                cvEntity.setTargetClassId(classsId);
                cvEntityList.add(cvEntity);
            }
        }

        classVisualService.batchSave(cvEntityList);

        return R.ok(1);
    }


    /**
     * 任务列表
     */
    @RequestMapping("/mytaskList")
    public R mytaskList(@RequestParam Map<String, Object> params) {
        // 查询班我发布的任务
        params.put("type", 2);
        return classTopController.myList(params);
    }

    @RequestMapping("/look")
    public R look(@RequestParam ClassTopParam classTopParam) {
        return classTopController.look(classTopParam);
    }


    /**
     * 点赞
     *
     * @param classTopParam
     * @return
     */
    @RequestMapping("/praise")
    public R praise(@RequestParam ClassTopParam classTopParam) {
        return classTopController.praise(classTopParam);
    }

    /**
     * 取消点赞
     *
     * @param classTopParam
     * @return
     */
    @RequestMapping("/unPraise")
    public R unPraise(@RequestParam ClassTopParam classTopParam) {
        return classTopController.unPraise(classTopParam);
    }
    /**
     * 我的发布
     * @name: topList
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/17
     */
    @RequestMapping("/topList")
    public R topList (@RequestBody Map<String,Object> params){//必传参数 authId
        return classTopController.topList(params);
    }
    /**
     * 我的发布 删除
     * @name: deleteTop
     * @params: [id]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/7/17
     */
    @RequestMapping("/deleteTop")
    public R deleteTop(@RequestBody Integer id) {
        return classTopController.deleteTop(id);
    }


    /**
     * 消息详情
     * h5专用
     */
    @RequestMapping("/myTopDetails")
    public R myTopDetails(@RequestParam QueryVo queryVo) {
        return classTopController.myTopDetails(queryVo);
    }
}
