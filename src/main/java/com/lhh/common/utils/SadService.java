package com.lhh.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.lhh.modules.sad.vo.SADBook;
import com.lhh.modules.sad.vo.SADClass;
import com.lhh.modules.sad.vo.SADUser;
import com.lhh.modules.stuMistakeBook.entity.Ebook;
import com.lhh.modules.stuMistakeBook.entity.SubjectVo;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 通产工具类
 *
 * @ClassName: TcUtils
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.common.utils
 * @CreateTime: 2018-07-09
 */
public class SadService {
    private static Properties prop = null;
    private static String placeholder = null;
    private static String ebookTree = null;
    private static String stuUrl = null;
    private static String tchUrl = null;
    private static String schoolSubject = null;
    private static String ebooksBySchoolId = null;

    static {
        prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        ebookTree = prop.getProperty("ebookTree");
        placeholder = prop.getProperty("placeholder");
        stuUrl = prop.getProperty("stuUrl");
        tchUrl = prop.getProperty("tchUrl");
        schoolSubject = prop.getProperty("schoolSubject");
        ebooksBySchoolId = prop.getProperty("ebooksBySchoolId");
    }

    /**
     * 获取章节树
     *
     * @name: getEbookThree
     * @params: [bookId]
     * @return: java.util.List<com.lhh.modules.stuMistakeBook.entity.Ebook>
     * @Author: cuihp
     * @Date: 2018/7/10
     */
    public static List<Ebook> getEbookThree(String bookId) throws IOException {
        if (StringUtils.isBlank(bookId)) {
            return null;
        }

        String replace = ebookTree.replace(placeholder, bookId);

        String re = HttpUtil.get(replace);
        Integer code = (Integer) JSONPath.read(re, "$.code");

        if (code == 1) {
            List<Ebook> nodes = null;
            Object nodeObj = JSONPath.eval(JSON.parse(re), "$.data.nodes");
            if (nodeObj != null) {
                nodes = JSONArray.parseArray(nodeObj.toString(), Ebook.class);
            }
            return nodes;
        }
        return null;
    }

    /**
     * 获取班级内学生信息
     *
     * @param stuId
     * @param tchId
     * @return
     * @throws IOException
     */
    public static Map getUserMap(String stuId, String tchId) throws IOException {

        String url;
        if (stuId == null) {
            url = tchUrl + tchId;
        } else {
            url = stuUrl + stuId;
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
    }

    /**
     * 获取学生或教师所在班级的所有学生
     *
     * @name: getUserMap
     * @params: [stuId, tchId, classId]
     * @return: java.util.Map
     * @Author: cuihp
     * @Date: 2018/7/10
     */
    public static Map getUserMap(String stuId, String tchId, String classId) throws IOException {

        String url;
        if (stuId == null) {
            url = tchUrl + tchId;
        } else {
            url = stuUrl + stuId;
        }

        String re = HttpUtil.get(url);
        System.out.println(re);

        Integer code = (Integer) JSONPath.read(re, "$.code");
        Map map = new HashMap();
        if (code == 1) {
            Object classesObj = JSONPath.eval(JSON.parse(re), "$.data.classes");
            List<SADClass> classes = JSONArray.parseArray(classesObj.toString(), SADClass.class);
            if (StringUtils.isNotBlank(classId)) {
                for (SADClass sadClass : classes) {
                    String id = sadClass.getGrade().get_id();
                    if (classId.equals(id)) {
                        if (sadClass.getStudents() != null) {
                            for (SADUser sadUser : sadClass.getStudents()) {
                                sadUser.setInfo(null);
                                sadUser.setRole(null);
                                map.put(sadUser.get_id(), sadUser);
                            }
                        }
                    }
                }
            }

        }
        return map;
    }

    public static List<SubjectVo> getSubjectsSchool(String schoolId) throws IOException {
        String replace = schoolSubject.replace(placeholder, schoolId);
        String re = HttpUtil.get(replace);
        Integer code = (Integer) JSONPath.read(re, "$.code");

        if (code == 1) {
            List<SubjectVo> subjects = null;
            Object nodeObj = JSONPath.eval(JSON.parse(re), "$.data");
            if (nodeObj != null) {
                subjects = JSONArray.parseArray(nodeObj.toString(), SubjectVo.class);
            }
            return subjects;
        }
        return null;
    }

    /**
     * 根据学校id 查询所有课本
     *
     * @param schoolId
     * @return
     * @throws IOException
     */
    public static List<SADBook> ebooksBySchoolId(String schoolId) throws IOException {
        String realUrl = ebooksBySchoolId.replace(placeholder, schoolId);
        realUrl.replace(":schoolId", schoolId);
        String re = HttpUtil.get(realUrl);
        Integer code = (Integer) JSONPath.read(re, "$.code");
        if (code == 1) {
            List<SADBook> books = null;
            Object nodeObj = JSONPath.eval(JSON.parse(re), "$.data");
            if (nodeObj != null) {
                books = JSONArray.parseArray(nodeObj.toString(), SADBook.class);
            }
            return books;
        }
        return null;
    }

}