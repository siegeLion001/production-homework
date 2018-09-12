package com.lhh.modules.cnjy21.controller;

import com.lhh.common.utils.R;
import com.lhh.modules.cnjy21.model.common.*;
import com.lhh.modules.cnjy21.model.document.Document;
import com.lhh.modules.cnjy21.service.APIDocumentService;
import com.lhh.modules.cnjy21.service.APIRequestParams;
import com.lhh.modules.cnjy21.util.APIResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档资源
 *
 * @author wangcheng
 * @date 2018年5月14日 下午2:02:07
 */
@RestController
@RequestMapping("/21api/document")
public class APIDocumentController {

    APIDocumentService service = new APIDocumentService();

    /**
     * 获取省份地区信息
     */
    @RequestMapping("/getProvinces")
    public R getProvinces() {
        try {
            List<Province> provinces = service.getProvinces();
            return R.ok(1).put("data", provinces);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 根据学段获取教材科目
     *
     * @param stage 学段ID 1:小学，2:初中，3:高中
     * @return
     */
    @RequestMapping("/getSubjects")
    public R getSubjects(Integer stage) {
        if (stage == null) {
            return getAllSubjects();
        }
        try {
            List<Subject> subjects = service.getSubjects(stage);
            return R.ok(1).put("data", subjects);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 根据学段获取教材科目
     *
     * @return
     */
    @RequestMapping("/getAllSubjects")
    public R getAllSubjects() {
        try {
            List<Subject> subjects1 = service.getSubjects(1);
            List<Subject> subjects2 = service.getSubjects(2);
            List<Subject> subjects3 = service.getSubjects(3);
            List<Map> subjects = new ArrayList<>();
            for (Subject subject : subjects1) {
                Map map = new HashMap();
                map.put("subjectId", subject.getSubjectId());
                map.put("subjectName", subject.getSubjectName());
                map.put("stage", 1);
                subjects.add(map);
            }
            for (Subject subject : subjects2) {
                Map map = new HashMap();
                map.put("subjectId", subject.getSubjectId());
                map.put("subjectName", subject.getSubjectName());
                map.put("stage", 2);
                subjects.add(map);

            }
            for (Subject subject : subjects3) {
                Map map = new HashMap();
                map.put("subjectId", subject.getSubjectId());
                map.put("subjectName", subject.getSubjectName());
                map.put("stage", 3);
                subjects.add(map);
            }
            return R.ok(1).put("data", subjects);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 获取版本列表
     *
     * @param stage     学段ID 1:小学，2:初中，3:高中
     * @param subjectId 科目ID
     * @return
     */
    @RequestMapping("/getVersions")
    public R getVersions(Integer stage, Integer subjectId) {
        try {
            List<Version> versions = service.getVersions(stage, subjectId);
            return R.ok(1).put("data", versions);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 获取版本教材下的册别信息列表
     *
     * @param versionId 版本ID
     * @return
     */
    @RequestMapping("/getBooks")
    public R getBooks(Integer versionId) {
        try {
            List<Book> books = service.getBooks(versionId);
            return R.ok(1).put("data", books);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 获取Book下所有章节目录树
     *
     * @param bookId 册别ID
     * @return
     */
    @RequestMapping("/getChapters")
    public R getChapters(Integer bookId) {
        try {
            List<Chapter> chapters = service.getChapters(bookId);
            return R.ok(1).put("data", chapters);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 获取知识点列表
     *
     * @param stage     学段ID 1:小学，2:初中，3:高中
     * @param subjectId 科目ID
     * @return
     */
    @RequestMapping("/getKnowledgePoints")
    public R getKnowledgePoints(Integer stage, Integer subjectId) {
        try {
            List<KnowledgePoint> knowledges = service.getKnowledgePoints(stage, subjectId);
            return R.ok(1).put("data", knowledges);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 根据章节获取文档
     *
     * @return
     */
    @RequestMapping("/getDocuments")
    public R getDocuments(APIRequestParams apiRequestParams) {
        try {
            APIResult<Document> documents = service.getDocuments(apiRequestParams);
            return R.ok(1).put("data", documents);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 获取文档下载地址，下载地址半小时有效，半小时后需重新获取
     *
     * @param itemid 资源ID
     * @return
     */
    @RequestMapping("/getDocumentDownurl")
    public R getDocumentDownurl(long itemid) {
        try {
            return R.ok(1).put("data", service.getDocumentDownurl(itemid));
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 资源预览对象
     *
     * @param itemid 资源ID
     * @return
     */
    @RequestMapping("/getPreview")
    public R getPreview(long itemid) {
        try {
            return R.ok(1).put("data", service.getPreview(itemid));
        } catch (Exception e) {
            return R.error();
        }
    }

}
