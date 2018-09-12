package com.lhh.common.utils.ftp;

import java.util.Properties;

import com.lhh.common.utils.PropertieyUtils;

/**
 * FileName: FtpResource
 * Author: cuihp
 * Date: 2018/6/15
 * Description: ftpResource
 */
public class FtpResource {
    private static String ftpIp;
    private static String  ftpUserName;
    private static String ftpPassword;
    private static String tempUrl;
    private static String workingDirectory;
    private static String baseUrl;
    private static int ftpPort;

    static{
        Properties prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        baseUrl = prop.getProperty("baseUrl");
        ftpIp = prop.getProperty("ftpIp");
        ftpUserName = prop.getProperty("ftpUserName");
        ftpPassword = prop.getProperty("ftpPassword");
        tempUrl = prop.getProperty("tempUrl");
        workingDirectory = prop.getProperty("workingDirectory");
        ftpPort = Integer.valueOf(prop.getProperty("ftpPort"));
    }


    public FtpResource(String ftpIp,int ftpPort, String  ftpUserName, String ftpPassword) {
        this.ftpIp = ftpIp;
        this.ftpPort = ftpPort;
        this.ftpUserName = ftpUserName;
        this.ftpPassword = ftpPassword;
    }

    public static String getFtpIp() {
        return ftpIp;
    }

    public static void setFtpIp(String ftpIp) {
        FtpResource.ftpIp = ftpIp;
    }

    public static String getFtpUserName() {
        return ftpUserName;
    }

    public static void setFtpUserName(String ftpUserName) {
        FtpResource.ftpUserName = ftpUserName;
    }

    public static String getFtpPassword() {
        return ftpPassword;
    }

    public static void setFtpPassword(String ftpPassword) {
        FtpResource.ftpPassword = ftpPassword;
    }

    public static String getTempUrl() {
        return tempUrl;
    }

    public static void setTempUrl(String tempUrl) {
        FtpResource.tempUrl = tempUrl;
    }

    public static String getWorkingDirectory() {
        return workingDirectory;
    }

    public static void setWorkingDirectory(String workingDirectory) {
        FtpResource.workingDirectory = workingDirectory;
    }

    public static int getFtpPort() {
        return ftpPort;
    }

    public static void setFtpPort(int ftpPort) {
        FtpResource.ftpPort = ftpPort;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        FtpResource.baseUrl = baseUrl;
    }
}