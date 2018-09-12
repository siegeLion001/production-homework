package com.lhh.modules.stuMistakeBook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.lhh.common.state.StuMistakeStatus;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.HttpUtil;
import com.lhh.common.utils.PropertieyUtils;
import com.lhh.common.utils.R;
import com.lhh.common.utils.SadService;
import com.lhh.modules.stuMistakeBook.entity.Ebook;
import com.lhh.modules.stuMistakeBook.entity.StuMistakeBookEntity;
import com.lhh.modules.stuMistakeBook.entity.StuMistakeBookVo;
import com.lhh.modules.stuMistakeBook.entity.SubjectVo;
import com.lhh.modules.stuMistakeBook.service.StuMistakeBookService;

import net.sf.json.JSONObject;

/**
 * @ClassName: StuMistakeBookCustomController
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.stuMistakeBook.controller
 * @CreateTime: 2018-07-04
 */
@RestController
@RequestMapping("stu/stumistakebook")
public class StuMistakeBookCustomController {

    @Autowired
    private StuMistakeBookService stuMistakeBookService;

    /**
     * 学生端错题上传
     *
     * @name: mistakeSave
     * @params: [stuMistakeBook]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/24
     */
    @RequestMapping("/mistakeSave")
    public R mistakeSave(@RequestBody StuMistakeBookEntity stuMistakeBook) {
        //学生上传错题
        String bookId = stuMistakeBook.getBookId();
        String sectionId = stuMistakeBook.getSectionId();
        String subjctId = stuMistakeBook.getSubjctId();
        String stuId = stuMistakeBook.getStuId();
        if (StringUtils.isBlank(bookId) || StringUtils.isBlank(sectionId) || StringUtils.isBlank(subjctId) || StringUtils.isBlank(stuId)) {
            return R.error("请检查传入数据! 学生id,科目id,册别id,章节id 不能为空");
        }
        List<String> topContent = stuMistakeBook.getTopContent();
        List<String> topAnalysis = stuMistakeBook.getTopAnalysis();
        if (topAnalysis != null && topAnalysis.size() > 0) {
            List<String> list2Base = FileUtil.getListReplaceBaseUrl(topAnalysis);
            stuMistakeBook.setTopAnalysis(list2Base);
        }
        if (topContent != null && topContent.size() > 0) {
            List<String> list2Base = FileUtil.getListReplaceBaseUrl(topContent);
            stuMistakeBook.setTopContent(list2Base);
        }
        Integer isGrasp = stuMistakeBook.getIsGrasp();
        if (isGrasp == null) {
            stuMistakeBook.setIsGrasp(1);
        }
        stuMistakeBookService.save(stuMistakeBook);
        return R.ok(1);
    }

    /**
     * 学生端错题修改
     *
     * @name: mistakeUpdate
     * @params: [stuMistakeBook]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/28
     */
    @RequestMapping("/mistakeUpdate")
    public R mistakeUpdate(@RequestBody StuMistakeBookEntity stuMistakeBook) {
        Integer id = stuMistakeBook.getId();
        if (id == null) {
            return R.error("错题本id为必传字段");
        }
        List<String> topContent = stuMistakeBook.getTopContent();
        List<String> topAnalysis = stuMistakeBook.getTopAnalysis();
        if (topAnalysis != null && topAnalysis.size() > 0) {
            List<String> list2Base = FileUtil.getListReplaceBaseUrl(topAnalysis);
            stuMistakeBook.setTopAnalysis(list2Base);
        }
        if (topContent != null && topContent.size() > 0) {
            List<String> list2Base = FileUtil.getListReplaceBaseUrl(topContent);
            stuMistakeBook.setTopContent(list2Base);
        }
        stuMistakeBookService.update(stuMistakeBook);

        return R.ok(1);
    }

    /**
     * 错题本 信息查询
     *
     * @name: querySubjectInfo
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/26
     */
    @RequestMapping("/querySubjectInfo")
    public R querySubjectInfo(@RequestBody Map<String, Object> params) throws IOException {
        String stuId4Params = String.valueOf(params.get("stuId"));
        String classId = String.valueOf(params.get("classId"));
        if (StringUtils.isBlank(stuId4Params) && StringUtils.isBlank(classId)) {
            return R.error("请传入学生id和班级id");
        }
        //击败班级百分比
        Integer beatRate = 0;
        //按科目进行错题统计
        List<Map<String, Object>> maps = stuMistakeBookService.querySubjectInfo(params);
        List<Map<String, Object>> rankList = stuMistakeBookService.queryRank(params);
        int rankCount = rankList.size();
        if (rankList != null && rankList.size() > 0) {
            for (Map<String, Object> stringObjectMap : rankList) {
                String stuId = String.valueOf(stringObjectMap.get("stuId"));
                String rank = String.valueOf(stringObjectMap.get("rank"));
                float stuRank = Float.parseFloat(rank);
                if (stuId4Params.equals(stuId)) {
                    float v = stuRank / rankCount;
                    if (v == 1f) {
                        beatRate = Math.round(100);
                    } else {
                        Float f = (1 - (stuRank / rankCount)) * 100;
                        beatRate = Math.round(f);
                    }
                }
            }
        }
        //待复习题目数(总数)
        Integer unGraspC = 0;
        //已掌握题目数(总数)
        Integer graspC = 0;
        List<SubjectVo> subjectVoList = SadService.getSubjectsSchool((String) params.get("schoolId"));
        for (Map<String, Object> map : maps) {
            Integer unGraspCount = Integer.valueOf(String.valueOf(map.get("unGraspCount")));
            Integer graspCount = Integer.valueOf(String.valueOf(map.get("graspCount")));
            for (SubjectVo subjectVo : subjectVoList) {
                String id = subjectVo.get_id();
                String subjctId = (String) map.get("subjctId");
                if (id.equals(subjctId)) {
                    subjectVo.setGraspCount(graspCount);
                    subjectVo.setUnGraspCount(unGraspCount);
                    subjectVo.setStuId((String) map.get("stuId"));
                }
            }
            unGraspC = unGraspC + unGraspCount;
            graspC = graspC + graspCount;
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("unGraspCount", unGraspC);
        resultMap.put("graspCount", graspC);
        //题目总数(待复习+已复习)
        resultMap.put("totalCount", graspC + unGraspC);
        resultMap.put("stuId", params.get("stuId"));
        resultMap.put("subjectList", subjectVoList);
        resultMap.put("beatRate", beatRate);
        return R.ok(1).put("data", resultMap);
    }

    /**
     * 册别下所有章节 待复习 已复习数量统计
     *
     * @name: querySectionInfo
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/24
     */
    @RequestMapping("/querySectionInfo")
    public R querySectionInfo(@RequestBody Map<String, Object> params) throws IOException {

        //根据册别id 查询章节树 调用递归方法 查询待复习题目数  已掌握题目数

        String bookId = (String)params.get("bookId");
        String stuId = (String)params.get("stuId");
        String subjctId = (String)params.get("subjctId");

        if (StringUtils.isBlank(bookId) || StringUtils.isBlank(stuId) ||StringUtils.isBlank(subjctId)) {
            return R.error("bookId,stuId,subjctId 不能为空");
        }

        Map<String, Object> stringObjectMap = stuMistakeBookService.queryList4noSection(params);
        Map<Object, Object> resultMap = new HashMap<>();
        resultMap.put("subject",stringObjectMap);
        List<Ebook> nodes = SadService.getEbookThree(bookId);
        if (nodes != null && nodes.size() > 0) {
            //获取所有章节id
            List<String> nodeList = new ArrayList<>();
            recursionTree(nodes, nodeList);
            params.put("nodeList", nodeList);
            List<Map<String, Object>> maps = stuMistakeBookService.querySectionInfo(params);
            //将待复习数,已掌握数封装到树中
            putCount4Tree(nodes, maps);
            resultMap.put("book",nodes);
            return R.ok(1).put("data", resultMap);
        }

        return R.ok(1).put("data",resultMap);
    }

    /**
     * 查询各章节下的题目详情
     *
     * @name: querySectionDetail
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/24
     */
    @RequestMapping("/querySectionDetail3")
    public R querySectionDetail3(@RequestBody Map<String, Object> params) {
		/*
			查询章节内所有错题
			接收参数: stuId subjctId bookId node对象
			从node对象中获取所有的章节id
			根据list查询所有的待复习题  已掌握题  并统计数量
		 */
		/*Map node = (Map) params.get("node");
		if(node ==null){
		    return R.error("章节不能为空");
        }
        List<Map> nodes = new ArrayList<>();
		nodes.add(node);*/
        //node对象处理
        Map map = (Map) params.get("node");
        JSONObject jsonObject = JSONObject.fromObject(map);
        Ebook node = (Ebook) JSONObject.toBean(jsonObject, Ebook.class);
        if (node == null) {
            return R.error("章节不能为空");
        }
        List<Ebook> nodes = new ArrayList<>();
        nodes.add(node);

        List<String> nodeList = new ArrayList<>();
        //递归获取所有子节点
//        recursionTree4Map(nodes,nodeList);
        recursionTree(nodes, nodeList);
        params.put("nodeList", nodeList);
        //查询待复习题目
        params.put("isGrasp", StuMistakeStatus.UNGRASP.getCode());
        List<StuMistakeBookVo> unGraspList = stuMistakeBookService.queryListByMap(params);
        //章节名称添加
        setName2Vo(unGraspList, nodes);
        //图片路径处理
        MistakeBookManage(unGraspList);
        //查询已掌握题目
        params.put("isGrasp", StuMistakeStatus.ISGRASP.getCode());
        List<StuMistakeBookVo> isGraspList = stuMistakeBookService.queryListByMap(params);
        //章节名称添加
        setName2Vo(isGraspList, nodes);
        //图片路径处理
        MistakeBookManage(isGraspList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("unGraspList", unGraspList);
        resultMap.put("isGraspList", isGraspList);
        resultMap.put("isGraspCount", isGraspList.size());
        resultMap.put("unGraspCount", unGraspList.size());
        return R.ok(1).put("data", resultMap);
    }

    /**
     * 查询各章节下的题目详情 版本2 不用传入node对象 直接传parentId就能获取该章节下所有节点
     *
     * @name: querySectionDetail
     * @params: [params]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     * @Date: 2018/6/24
     */
    @RequestMapping("/querySectionDetail")
    public R querySectionDetail(@RequestBody Map<String, Object> params) throws IOException {
		/*
			查询章节内所有错题
			接收参数: stuId bookId parentId(章节id)
			根据parentId查询该章节下所有子章节 (递归数据)返回类型 List 将parentId放入list
			根据list查询所有的待复习题  已掌握题  并统计数量
		 */
        String parentId = (String)params.get("parentId");
        if (StringUtils.isBlank(parentId))
            return getMap4NoSectionId(params);
        //根据parentId获取该节点对象
        Ebook node = getNode4Three(params);
        List<Ebook> nodes = new ArrayList<>();
        nodes.add(node);
        //获取该节点下所有子节点id
        List<String> nodeList = new ArrayList<>();
        recursionTree(nodes, nodeList);
        params.put("nodeList", nodeList);
        //查询待复习题目
        params.put("isGrasp", StuMistakeStatus.UNGRASP.getCode());
        List<StuMistakeBookVo> unGraspList = stuMistakeBookService.queryListByMap(params);
        //章节名称添加
        setName2Vo(unGraspList, nodes);
        //图片路径处理
        MistakeBookManage(unGraspList);
        //查询已掌握题目
        params.put("isGrasp", StuMistakeStatus.ISGRASP.getCode());
        List<StuMistakeBookVo> isGraspList = stuMistakeBookService.queryListByMap(params);
        //章节名称添加
        setName2Vo(isGraspList, nodes);
        //图片路径处理
        MistakeBookManage(isGraspList);
        //数据封装
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("unGraspList", unGraspList);
        resultMap.put("isGraspList", isGraspList);
        resultMap.put("isGraspCount", isGraspList.size());
        resultMap.put("unGraspCount", unGraspList.size());
        return R.ok(1).put("data", resultMap);
    }

    private R getMap4NoSectionId(Map<String,Object> params) {
        //查询待复习题目
        params.put("isGrasp", StuMistakeStatus.UNGRASP.getCode());
        List<StuMistakeBookVo> unGraspList = stuMistakeBookService.queryListByMap4NoSection(params);
        //图片路径处理
        MistakeBookManage(unGraspList);
        //查询已掌握题目
        params.put("isGrasp", StuMistakeStatus.ISGRASP.getCode());
        List<StuMistakeBookVo> isGraspList = stuMistakeBookService.queryListByMap4NoSection(params);
        //图片路径处理
        MistakeBookManage(isGraspList);
        //数据封装
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("unGraspList", unGraspList);
        resultMap.put("isGraspList", isGraspList);
        resultMap.put("isGraspCount", isGraspList.size());
        resultMap.put("unGraspCount", unGraspList.size());
        return R.ok(1).put("data",resultMap);
    }
    /**
     * 根据章节id添加章节名称
     *
     * @name: setName2Vo
     * @params: [list, nodes]
     * @return: void
     * @Author: cuihp
     * @Date: 2018/7/18
     */
    private void setName2Vo(List<StuMistakeBookVo> list, List<Ebook> nodes) {
        for (StuMistakeBookVo stuMistakeBookVo : list) {
            String sectionId = stuMistakeBookVo.getSectionId();
            String name4Node = getName4Node(sectionId, nodes);
            stuMistakeBookVo.setName(name4Node);
        }
    }

    /**
     * 递归获取章节名称
     *
     * @name: getName4Node
     * @params: [sectionId, nodes]
     * @return: java.lang.String
     * @Author: cuihp
     * @Date: 2018/7/18
     */
    private String getName4Node(String sectionId, List<Ebook> nodes) {
        String name = null;
        for (Ebook node : nodes) {
            String id = node.getId();
            if (sectionId.equals(id)) {
                name = node.getName();
                break;
            } else {
                List<Ebook> childNodes = node.getNodes();
                name = getName4Node(sectionId, childNodes);
            }
        }
        return name;
    }

    /**
     * 错题图片路径处理
     *
     * @name: MistakeBookManage
     * @params: [list]
     * @return: void
     * @Author: cuihp
     * @Date: 2018/7/7
     */
    private void MistakeBookManage(List<StuMistakeBookVo> list) {
        for (StuMistakeBookVo stuMistakeBook : list) {
            List<String> topContent = stuMistakeBook.getTopContent();
            List<String> topAnalysis = stuMistakeBook.getTopAnalysis();

            List<String> list2Base = FileUtil.getListReplacePlaceholder(topAnalysis);
            stuMistakeBook.setTopAnalysis(list2Base);

            List<String> list2Base2 = FileUtil.getListReplacePlaceholder(topContent);
            stuMistakeBook.setTopContent(list2Base2);

        }
    }

    /**
     * 根据章节id获取node节点
     *
     * @name: getNode4Three
     * @params: [params]
     * @return: com.lhh.modules.stuMistakeBook.entity.Ebook
     * @Author: cuihp
     * @Date: 2018/7/7
     */
    private Ebook getNode4Three(Map<String, Object> params) throws IOException {
        String bookId = String.valueOf(params.get("bookId"));
        String nodeId = String.valueOf(params.get("parentId"));
        List<Ebook> ebookThree = SadService.getEbookThree(bookId);
        Ebook node = getNode(ebookThree, nodeId);
        if (node != null)
            return node;
        return null;
    }

    /**
     * 根据章节id获取node对象的递归
     *
     * @param list
     * @param nodeId
     * @return
     */
    private Ebook getNode(List<Ebook> list, String nodeId) {
        for (Ebook ebook : list) {
            String id = ebook.getId();
            if (nodeId.equals(id)) {
                return ebook;
            } else {
                getNode(ebook.getNodes(), nodeId);
            }
        }
        return null;
    }

    /**
     * 将统计数放入章节节点中
     *
     * @name: putCount4Tree
     * @params: [nodes, maps]
     * @return: void
     * @Author: cuihp
     * @Date: 2018/7/7
     */
    private void putCount4Tree(List<Ebook> nodes, List<Map<String, Object>> maps) {
        for (Ebook node : nodes) {
            Integer pUnGraspCount;
            Integer pGraspCount;
            String id = node.getId();
            for (Map<String, Object> map : maps) {
                String sectionId = (String) map.get("sectionId");
                Integer unGraspCount = Integer.valueOf(String.valueOf(map.get("unGraspCount")));
                Integer graspCount = Integer.valueOf(String.valueOf(map.get("graspCount")));

                if (id.equals(sectionId)) {
                    node.setUnGraspCount(unGraspCount);
                    node.setGraspCount(graspCount);
                }
            }
            List<Ebook> childNode = node.getNodes();
            if (childNode != null && childNode.size() > 0) {
                putCount4Tree(childNode, maps);
            }
        }

    }

    /**
     * 获取章节下所有节点id
     *
     * @name: recursionTree
     * @params: []
     * @return: void
     * @Author: cuihp
     * @Date: 2018/6/24
     */
    private void recursionTree(List<Ebook> nodes, List<String> nodeList) {

        for (Ebook node : nodes) {

            String id = node.getId();
            nodeList.add(id);
            List<Ebook> childNode = node.getNodes();
            if (childNode != null && childNode.size() > 0) {
                recursionTree(childNode, nodeList);
            }
        }
    }

    /**
     * 获取章节下所有节点id
     *
     * @name: recursionTree4Map
     * @params: [nodes, nodeList]
     * @return: void
     * @Author: cuihp
     * @Date: 2018/7/7
     */
    private void recursionTree4Map(List<Map> nodes, List<String> nodeList) {

        for (Map node : nodes) {
            String id = (String) node.get("id");
            nodeList.add(id);
            List<Map> childNode = (List<Map>) node.get("nodes");
            if (childNode != null && childNode.size() > 0) {
                recursionTree4Map(childNode, nodeList);
            }
        }

    }

    @RequestMapping("/mistakeDelete")
    public R mistakeDelete(@RequestBody Map<String, Object> params) {
        Integer id = Integer.valueOf(String.valueOf(params.get("id")));
        if (id != null) {
            stuMistakeBookService.delete(id);
            return R.ok(1);
        }
        return R.error("id不能为空");
    }

    @Test
    public void test() throws IOException {
        Properties prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        String ebookTree = prop.getProperty("ebookTree");
        String placeholder = prop.getProperty("placeholder");
        String replace = ebookTree.replace(placeholder, "5b30ace400c39d07bb936612");

        String re = HttpUtil.get(replace);

        Object nodeObj = JSONPath.eval(JSON.parse(re), "$.data.nodes");
        List<Ebook> nodes = JSONArray.parseArray(nodeObj.toString(), Ebook.class);
        System.out.println(nodes.toString());
        System.out.println("hadsaofgh_________________________________________________________");
        List<String> li = new ArrayList<>();
        recursionTree(nodes, li);
        System.out.println(li.toString());
        System.out.println(li.size());
    }
}