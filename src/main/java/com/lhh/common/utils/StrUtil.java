package com.lhh.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StrUtil
 *
 * @ClassName: StrUtil
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.common.utils
 * @CreateTime: 2018-06-25
 */
public class StrUtil {
    /**
     * 去除字符串中的 回车 换行  及水平制表符
     *
     * @name: replaceBlank
     * @params: [str]
     * @return: java.lang.String
     * @Author: cuihp
     * @Date: 2018/06/25
     */
    public static String replaceChangeLine(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}