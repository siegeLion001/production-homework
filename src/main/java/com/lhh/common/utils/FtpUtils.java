package com.lhh.common.utils;

import java.util.*;

/**
 * FileName: FtpUtils
 * Author: cuihp
 * Date: 2018/6/15
 * Description: FtpUtils
 */
public class FtpUtils {

   public static List transformStringList(List<String> fileList){
        Properties prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        String baseUrl = prop.getProperty("baseUrl");
        String placeholder = prop.getProperty("placeholder");
        ArrayList<String> strings = new ArrayList<>();
        for (String s : fileList) {
            String replace = s.replace(placeholder, "");
            String replace1 = replace.replace(baseUrl, "");
            strings.add(replace1);
        }
        return strings;
    }
    public static String transformString(String path){
        Properties prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        String baseUrl = prop.getProperty("baseUrl");
        String placeholder = prop.getProperty("placeholder");

        String replace = path.replace(placeholder, "");
        String replace1 = replace.replace(baseUrl, "");

        return replace1;
    }

   public static Map<String,String> getNameAndPath(List<String> fileList){
       Map<String, String> resultMap = new HashMap<>();
       for (String s : fileList) {
           int i = s.lastIndexOf("/");
           String substring = s.substring(i + 1);
           String substring1 = s.substring(0, i);
           resultMap.put(substring,substring1);
       }
       return resultMap;
   }

    public static Map<String,String> getNameAndPath(String filePath){
        Map<String, String> resultMap = new HashMap<>();

            int i = filePath.lastIndexOf("/");
            String substring = filePath.substring(i + 1);
            resultMap.put(substring,filePath);

        return resultMap;
    }
}