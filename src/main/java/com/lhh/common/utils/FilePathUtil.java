package com.lhh.common.utils;

import java.io.File;
import java.text.SimpleDateFormat;

public class FilePathUtil {

    public static String IMAGE_TYPE_PNG = "PNG";
    private static String dot = ".";
    private static String sp = File.separator;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static int partNum = 6;


    /**
     * /项目号/环号/时间（年月日)/件号.类型
     *
     * @param projectId
     * @param sectorId
     * @param bundledNum
     * @param image_type
     * @return
     */
    /*public static String creatPathName(String bundledNum, String image_type) {
        Long tim = System.currentTimeMillis();
        int mo = Math.abs(tim.intValue() % partNum);
        String dataStr = sdf.format(tim);
        String fileName = dataStr + sp +mo + sp + bundledNum + dot + image_type;
        return fileName;
    }*/
    /**
     * 创建文件之前的文件夹目录
     * @param pathRoot
     * @param typePath
     * @return
     */
    public static String creatPathName(String pathRoot, String typePath) {
        Long tim = System.currentTimeMillis();
        int mo = Math.abs(tim.intValue() % partNum);
        String dataStr = sdf.format(tim);
        String fileName =pathRoot+typePath+ dataStr + sp +mo + sp;
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return fileName;
    }
    /**
     * 创建文件之前的文件夹目录
     * @param typePath
     * @return
     */
    public static String creatPathName(String typePath) {
        Long tim = System.currentTimeMillis();
        int mo = Math.abs(tim.intValue() % partNum);
        String dataStr = sdf.format(tim);
        String fileName =typePath+ dataStr + sp +mo + sp;
        return fileName;
    }
    public static String getUri(String pathName) {
        return pathName.replace(sp, "/");
    }
}
