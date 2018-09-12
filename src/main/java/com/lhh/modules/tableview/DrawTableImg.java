package com.lhh.modules.tableview;

import com.lhh.common.utils.PropertieyUtils;
import com.lhh.common.utils.ftp.FileAdapter;
import com.lhh.common.utils.ftp.FtpService;
import com.lhh.common.utils.ftp.UploadMessage;
import gui.ava.html.image.generator.HtmlImageGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawTableImg {
    static Properties properties = PropertieyUtils.loadPropertyInstance("deploy.properties");

    public static String tableRePl(String line, Long questionId) {
        String[] strings = line.split("<table");
//        Pattern p = Pattern.compile("<table.*?>[\\s\\S]*?</table>");
        String regex = "<table.*?>[\\s\\S]*</table>";

        StringBuilder newLine = new StringBuilder();
        FtpService ftpService = new FtpService();
        String filePathName = properties.getProperty("21cdnPath");
        ftpService.setWorkingDirectory(filePathName);
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            if (s.contains("</table>")) {
                s = "<table" + s;
                Pattern p = Pattern.compile(regex);
                Matcher matcher = p.matcher(s);
                boolean matches = matcher.find();
                if (matches) {
                    Integer finalI = i;
                    String finalS = s;
                    FileAdapter fileAdapter = new FileAdapter() {
                        @Override
                        public File reFile(Properties properties) {

                            String tempUrl = properties.getProperty("tempUrl");
                            System.out.println(tempUrl + questionId + "_" + finalI + ".png");
                            File file = tableImgReFile(finalS, tempUrl + questionId + "_" + finalI + ".png");
                            return file;
                        }
                    };

                    UploadMessage uploadMessage = ftpService.uploadImg(fileAdapter);
                    String url = uploadMessage.getUrl();

                    String tableStr = matcher.group(0);
                    System.out.println("--------原始tableStr" + tableStr);
                    System.out.println("--------原始s" + s);
                    String ns = s.replace(tableStr, "<br /><img src=\"" + url + "\"/>");
                    System.out.println(ns);
                    newLine.append(ns);
                } else {
                    newLine.append(s);
                }

            } else {
                newLine.append(s);
            }
        }
        ftpService.disconnect();
        System.out.println(newLine.toString());
        return newLine.toString();
    }

    public static String tableImg(String tableValue, String path) {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(tableValue);
        imageGenerator.saveAsImage(path);
        return path;
    }


    public static File tableImgReFile(String tableValue, String path) {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(tableValue);
        imageGenerator.saveAsImage(path);
        File file = new File(path);
        return file;
    }

    @Test
    public void test() {
        String s = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width=\"657\"><tbody><tr><td valign=\"top\"><p>日用水量</p></td><td valign=\"top\"><p>[0,0.1）</p></td><td valign=\"top\"><p>[0.1,0.2）</p></td><td valign=\"top\"><p>[0.2，,0.3</p></td><td valign=\"top\"><p>[0.3,0.4）</p></td><td valign=\"top\"><p>[0.4，0.5）</p></td><td valign=\"top\"><p>[0.5,0.6）</p></td><td valign=\"top\"><p>[0.6,0.7）</p></td></tr><tr><td valign=\"top\"><p>频数</p></td><td valign=\"top\"><p>1</p></td><td valign=\"top\"><p>3</p></td><td valign=\"top\"><p>2</p></td><td valign=\"top\"><p>4</p></td><td valign=\"top\"><p>9</p></td><td valign=\"top\"><p>26</p></td><td valign=\"top\"><p>5</p></td></tr></tbody></table><p>使用节水龙头50天的日用水量频数分布表</p>";


        Pattern p = Pattern.compile("<table.*?>[\\s\\S]*</table>");
        Matcher matcher = p.matcher(s);
        boolean matches = matcher.find();
        System.out.println(matches);

    }

}
