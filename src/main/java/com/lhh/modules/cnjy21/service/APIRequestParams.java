package com.lhh.modules.cnjy21.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class APIRequestParams {
    // 反射使用
    private static Field[] fields;
    /**
     * 学段(APIConstant.STAGE_*)
     */
    public Integer stage;
    /**
     * 科目ID
     */
    public Integer subjectId;
    /**
     * 教程版本ID
     */
    public Integer versionId;
    /**
     * 教材测别ID
     */
    public Integer bookId;
    /**
     * 章节ID
     */
    public Integer chapterId;
    /**
     * 知识点ID
     */
    public Integer knowledgeId;
    /**
     * 试题类型或者文档类型
     */
    public Integer type;
    /**
     * 资源IDs(以逗号隔开)
     */
    public String itemIds;
    /**
     * 试题IDs(以逗号隔开)
     */
    public String questionIds;
    /**
     * 试题ID
     */
    public Integer questionId;
    /**
     * 年级(APIGrade.*)
     */
    public Integer gradeId;
    /**
     * 难易程度(APIConstant.DIFFICULT_*)
     */
    public Integer difficult;
    /**
     * 视频资源ID
     */
    public Integer videoId;
    /**
     * 分页参数-每页大小
     */
    public Integer perPage;
    /**
     * 分页参数-请求页码
     */
    public Integer page;
    /**
     * 日期条件
     */
    public Long dateline;
    /**
     * 是否显示试题的绑定知识点或者目录ID(1=显示)
     */
    public Integer kcNote;

    /**
     * 省级地区ID，通过接口 "获取省级地区数据" 获取
     */
    public Integer provinceId;

    /**
     * 资源标题，支持按标题搜索
     */
    public String title;

    /**
     * 文档ID
     */
    public Long itemId;
    /**
     * 我加的 cnm  21世纪没有试卷sdk
     */
    // 试卷id
    public Integer id;
    //省份id
    public Integer province;
    // 年份
    public Integer year;

    /**
     * 将属性转换成Map
     *
     * @return
     */
    public Map<String, Object> parseMap() {
        Map<String, Object> params = new HashMap<String, Object>();
        if (fields == null) {
            fields = this.getClass().getFields();
        }
        for (Field field : fields) {
            try {
                Object value = field.get(this);
                if (value != null) {
                    params.put(field.getName(), value);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

}
