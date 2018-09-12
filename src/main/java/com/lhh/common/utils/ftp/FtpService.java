package com.lhh.common.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.lhh.common.utils.FilePathUtil;
import com.lhh.common.utils.FtpUtils;
import com.lhh.common.utils.PropertieyUtils;
import com.lhh.modules.cnjy21.util.Log;

/**
 * FileName: FtpService
 * Author: cuihp
 * Date: 2018/6/15
 * Description: ftpService
 */
public class FtpService {

    //    private FtpResource ftpRes;
    private FTPClient ftpClient;

    public boolean connect() {
        boolean connectState = false;
        if (ftpClient == null)
            ftpClient = new FTPClient();

        try {
            if (!ftpClient.isConnected()) {
                ftpClient.connect(FtpResource.getFtpIp(), FtpResource.getFtpPort());
                ftpClient.login(FtpResource.getFtpUserName(), FtpResource.getFtpPassword());
            }
            if (FtpResource.getWorkingDirectory() != null)
                ftpClient.changeWorkingDirectory(FtpResource.getWorkingDirectory());
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            connectState = true;
        } catch (IOException e) {
            e.printStackTrace();
            if (ftpClient != null && ftpClient.isConnected())
                try {
                    ftpClient.disconnect();
                } catch (IOException e1) {
                    return false;
                } catch (IllegalStateException e1) {
                    e1.printStackTrace();
                }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return connectState;
    }

    public boolean setWorkingDirectory(String storageDir) {
        if (ftpClient == null) {
            connect();
        }
        String dir = "";
        try {
            if (ftpClient != null && ftpClient.isConnected()) {

                boolean flag = ftpClient.changeWorkingDirectory(storageDir);

                if (!flag) {
                    if (StringUtils.isNotBlank(storageDir)) {
                        storageDir = FilePathUtil.getUri(storageDir);
                        StringTokenizer s = new StringTokenizer(storageDir, "/");
                        s.countTokens();
                        String pathName = "";

                        while (s.hasMoreElements()) {
                            pathName = pathName + "/" + (String) s.nextElement();

                            try {
                                ftpClient.mkd(pathName);
                            } catch (Exception e) {
                                throw new RuntimeException("ftp创建文件夹失败:" + pathName);
                            }
                        }
                    }

                    boolean b = ftpClient.changeWorkingDirectory(storageDir);
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    return b;
                } else {
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    return flag;
                }
            }
        } catch (IOException e) {
            Log.error(dir + "\"目录不存在！");
            e.printStackTrace();
            return false;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public void storeFile(String fileName, InputStream inputStream) {
        if (ftpClient == null) {
            connect();
        }
        try {
            ftpClient.storeFile(fileName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean upload(InputStream is, String saveName) {
        boolean state = false;
        if (ftpClient == null) {
            connect();
        }
        File file = new File(FtpResource.getTempUrl() + saveName);
        OutputStream os = null;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            InputStream local = new FileInputStream(file);
            ftpClient.storeFile(file.getName(), local);
            local.close();
            state = true;
            return state;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
                file.delete();//删除临时文件
                return state;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return state;
    }
    public boolean upload4File(File file) {

        boolean state = false;
        if (ftpClient == null) {
            connect();
        }
        try {
            InputStream local = new FileInputStream(file);
            ftpClient.storeFile(file.getName(), local);
            local.close();
            state = true;
            return state;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();//删除临时文件
            return state;
        }

    }

    public String upFile(InputStream is, String saveNamePath) {
        if (ftpClient == null) {
            connect();
        }
        int i = saveNamePath.lastIndexOf("/");
        String saveName = saveNamePath.substring(i + 1);
        String savePath = saveNamePath.substring(0, i);
        boolean b = setWorkingDirectory(savePath);
        if(b){
            File file = new File(FtpResource.getTempUrl() + saveName);
            OutputStream os = null;
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                os = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                InputStream local = new FileInputStream(file);
                ftpClient.storeFile(file.getName(), local);

                local.close();
                return FtpResource.getBaseUrl()+saveNamePath;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } finally {
                try {
                    os.close();
                    is.close();
                    file.delete();//删除临时文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean deleteFile(String pathname) {
        boolean flag = false;
        pathname = FtpUtils.transformString(pathname);
        int index = pathname.lastIndexOf("/");
        String filename = pathname.substring(index+1);
        pathname = pathname.substring(0, index);
        try {
            if (ftpClient == null)
                connect();

            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.deleteFile(filename);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean deleteAndClose(String pathname) {
        boolean flag = false;

        pathname = FtpUtils.transformString(pathname);
        int index = pathname.lastIndexOf("/");
        String filename = pathname.substring(index+1);
        pathname = pathname.substring(0, index);
        try {
            if (ftpClient == null)
                connect();

            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.deleteFile(filename);
            flag = true;
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    public boolean delete4List(List<String> list) {
        boolean flag = false;

        try {
            if (ftpClient == null)
                connect();
            List list1 = FtpUtils.transformStringList(list);
            Map nameAndPath = FtpUtils.getNameAndPath(list1);
            Iterator iterator = nameAndPath.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entity = (Map.Entry)iterator.next();
                String fileName = (String)entity.getKey();
                String filePath = (String)entity.getValue();
                ftpClient.changeWorkingDirectory(filePath);
                ftpClient.deleteFile(fileName);
            }

            flag = true;
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void disconnect() {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    public UploadMessage uploadImg(FileAdapter fileAdpe) {
        UploadMessage um = new UploadMessage();
        Properties properties = PropertieyUtils.loadPropertyInstance("deploy.properties");
        String baseUrl = properties.getProperty("baseUrl");


        if (ftpClient == null) {
            connect();
        }
        File file = fileAdpe.reFile(properties);
        String fileName = file.getName();
        try {

            String filePathName = properties.getProperty("21cdnPath");
            String resPath = filePathName + fileName;
            InputStream local = new FileInputStream(file);
            ftpClient.storeFile(file.getName(), local);
            local.close();
            String uri = FilePathUtil.getUri(baseUrl + resPath);
            um.setState(true);
            um.setUrl(uri);
            return um;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.delete();//删除临时文件
                um.setState(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        um.setState(false);
        return um;
    }
}