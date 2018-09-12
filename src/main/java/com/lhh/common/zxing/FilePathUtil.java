package com.lhh.common.zxing;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePathUtil {

    public static String IMAGE_TYPE_PNG = "PNG";
    private static String dot = ".";
    private static String sp = File.separator;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static String folder = "barcode";

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
    public static String creatPathName(String projectId, String sectorId, String bundledNum, String image_type) {
        Long tim = System.currentTimeMillis();
        int mo = Math.abs(tim.intValue() % partNum);
        String dataStr = sdf.format(tim);
        String fileName = folder + sp + dataStr + sp + projectId + sp + sectorId + sp + mo + sp + bundledNum + dot + image_type;
        return fileName;
    }


    public static String[] creatPathAndName(String projectId, String sectorId, String bundledNum, String image_type) {
        Long tim = System.currentTimeMillis();
        int mo = Math.abs(tim.intValue() % partNum);
        String dataStr = sdf.format(tim);
        String path = folder + sp + dataStr + sp + projectId + sp + sectorId + sp + mo + sp;
        String name = bundledNum + dot + image_type;
        return new String[]{path, name};
    }

    public static String getUri(String pathName) {
        return pathName.replace(sp, "/");
    }

    public static OutputStream getFOStream(String webPath, String path, String name) throws FileNotFoundException {
        createFolder(webPath + path);
        OutputStream outputStream = new FileOutputStream(new File(webPath + path + name));
        return outputStream;
    }

    public static OutputStream getFOStream(File file) throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream(file);
        return outputStream;
    }

    /**
     * 创建目录
     */
    public static void createFolder(String filePath) {
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Test
    public void testCreatPath() {
        String ss = creatPathName("12", "12", "22", IMAGE_TYPE_PNG);
        System.out.println(ss);
    }

    @Test
    public void testCreatPath1() {
        String ss = getUri("\\sada\\adsadas");
        System.out.println(ss);
    }
}
