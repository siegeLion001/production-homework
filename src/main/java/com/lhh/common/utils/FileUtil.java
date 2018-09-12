package com.lhh.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lhh.modules.stutopic.entity.po.CorrectImg;

public class FileUtil {
    private static Properties prop = null;
    private static String placeholder = null;
    private static String baseUrl = null;

    static {
        prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        placeholder = prop.getProperty("placeholder");
        baseUrl = prop.getProperty("baseUrl");
    }

    /**
     * 获取文件后缀名
     *
     * @param mf
     * @return
     */
    public static String getSuffixName(MultipartFile mf) {
        CommonsMultipartFile cf = (CommonsMultipartFile) mf;
        String name = cf.getFileItem().getName();
        String resType = name.substring(name.indexOf(".") + 1);
        return resType;
    }

    /**
     * 将字符串中的 http... 替换为占位符
     * @name: getStrReplaceBaseUrl
     * @params: [content]
     * @return: java.lang.String
     * @Author: cuihp
     * @Date: 2018/7/6
     */
    public static String getStrReplaceBaseUrl(String content) {
        if (StringUtils.isNotBlank(content)) {
            String replace = content.replace(baseUrl, placeholder);
            return replace;
        }
        return content;
    }

    /**
     * 将占位符替换为 http...
     * @name: getStrReplacePlaceholder
     * @params: [content]
     * @return: java.lang.String
     * @Author: cuihp
     * @Date: 2018/7/6
     */
    public static String getStrReplacePlaceholder(String content) {
        if (StringUtils.isNotBlank(content)) {
            String replace = content.replace(placeholder, baseUrl);
            return replace;
        }
        return content;
    }

    /**
     * 将传入list的占位符替换为http地址
     * @name: getStrListReplacePlaceholder
     * @params: [list]
     * @return: java.util.List<java.lang.String>
     * @Author: cuihp
     * @Date: 2018/7/6
     */
    public static List<String> getListReplacePlaceholder(List<String> list) {
        ArrayList<String> strings = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (String s : list) {
                String replace = s.replace("\\", "").replace(placeholder, baseUrl);
                strings.add(replace);
            }
        }
        return strings;
    }
    /**
     * 将传入的集合替换为占位符
     * @name: getListReplaceBaseUrl
     * @params: [list]
     * @return: java.util.List<java.lang.String>
     * @Author: cuihp
     * @Date: 2018/7/6
     */
    public static List<String> getListReplaceBaseUrl(List<String> list) {
        ArrayList<String> strings = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (String s : list) {
                String replace = s.replace("\\", "").replace(baseUrl, placeholder);
                strings.add(replace);
            }
        }
        return strings;
    }

    /**
     * 将http...替换为占位符
     * @name: getListReplacePlaceholder
     * @params: [lis]
     * @return: java.util.List<java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/6
     */
    public static List<Object> getObjListReplacePlaceholder (List<Object> lis) {
        List<Object> ts = new ArrayList<>();
        if (lis != null && lis.size() > 0) {
            for (Object li : lis) {
                String str = li.toString();
                String replace = str.replace(placeholder, baseUrl);
                ts.add(replace);
            }
        }
        return ts;
    }

    /**
     * 将http...替换为占位符
     * @name: getObjListReplaceBaseUrl
     * @params: [lis]
     * @return: java.util.List<java.lang.Object>
     * @Author: cuihp
     * @Date: 2018/7/6
     */
    public static List<Object> getObjListReplaceBaseUrl(List<Object> lis) {
        if (lis != null && lis.size() > 0) {
            for (int i = 0; i < lis.size(); i++) {
                String s = lis.get(i).toString();
                String rep= s.replace(baseUrl, placeholder);
                lis.add(i,rep);
            }
        }else{
            return null;
        }
        return lis;
    }

    /**
     * 将数组中的占位符替换为http...
     * @name: getArrayReplacePlaceholder 
     * @params: [lis]
     * @return: java.lang.String[]
     * @Date: 2018/7/6
     */
    public static String[] getArrayReplacePlaceholder(String[] lis) {
        if (lis == null) {
            return null;
        }
        for (int i = 0; i < lis.length; i++) {
            String str = lis[i];
            String replace = str.replace(placeholder, baseUrl);
            lis[i] = replace;
        }
        return lis;
    }
    /**
     * 将http...替换为占位符
     * @name: getArrayReplaceBaseUrl 
     * @params: [lis]
     * @return: java.lang.String[]
     * @Date: 2018/7/6
     */
    public static String[] getArrayReplaceBaseUrl(String[] lis) {
        if (lis == null) {
            return null;
        }
        for (int i = 0; i < lis.length; i++) {
            String str = lis[i];
            String replace = str.replace(baseUrl, placeholder);
            lis[i] = replace;
        }
        return lis;
    }

    /**
     * 入
     *
     * @param correctImgs
     * @return
     */
    public static CorrectImg[] getReplaceBaseUrl(CorrectImg[] correctImgs) {
        if (correctImgs == null || correctImgs.length<=0) {
            return correctImgs;
        }
        for (CorrectImg correctImg : correctImgs) {

            String correctUrl = correctImg.getCorrectUrl();

            String sourceUrl = correctImg.getSourceUrl();

            if (StringUtils.trimToNull(correctUrl) != null) {
                correctImg.setCorrectUrl(correctUrl.replace(baseUrl, placeholder));
            }
            if (StringUtils.trimToNull(sourceUrl) != null) {
                correctImg.setSourceUrl(sourceUrl.replace(baseUrl, placeholder));
            }
        }

        return correctImgs;
    }

    /**
     * 出
     *
     * @param correctImgs
     * @return
     */
    public static CorrectImg[] getReplacePlaceholder(CorrectImg[] correctImgs) {
        if (correctImgs == null || correctImgs.length<=0) {
            return correctImgs;
        }
        for (CorrectImg correctImg : correctImgs) {
            String correctUrl = correctImg.getCorrectUrl();
            String sourceUrl = correctImg.getSourceUrl();

            if (StringUtils.trimToNull(correctUrl) != null) {
                correctImg.setCorrectUrl(correctUrl.replace(placeholder, baseUrl));
            }
            if (StringUtils.trimToNull(sourceUrl) != null) {
                correctImg.setSourceUrl(sourceUrl.replace(placeholder, baseUrl));
            }
        }

        return correctImgs;
    }

    /**
     * 字符串替换
     * @name: replace
     * @params: [url,  oldStr,  newStr]
     *          字符串  被替代的  替代的
     * @return: java.lang.String
     * @Author: cuihp
     * @Date: 2018/7/6
     */
    public static String replace(String url, String oldStr, String newStr) {
        if (StringUtils.isNotBlank(url)) {
            String replace = url.replace(oldStr, newStr);
            return replace;
        }
        return url;
    }

}
