package com.lhh.modules.homework.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lhh.common.utils.FilePathUtil;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.PropertieyUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.utils.ftp.FtpService;
import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.homework.service.HomeworkService;


/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("homework:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<HomeworkEntity> homeworkList = homeworkService.queryList(query);
        int total = homeworkService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(homeworkList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("homework:info")
    public R info(@PathVariable("id") Integer id) {
        HomeworkEntity homework = homeworkService.queryObject(id);

        return R.ok().put("homework", homework);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("homework:save")
    public R save(@RequestBody HomeworkEntity homework) {
        homeworkService.save(homework);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("homework:update")
    public R update(@RequestBody HomeworkEntity homework) {
        homeworkService.update(homework);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("homework:delete")
    public R delete(@RequestBody Integer[] ids) {
        homeworkService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * @Description: 文件上传处理
     * @Name: uploadFile
     * @Params: [request, pictureFiles]
     * @return: com.lhh.common.utils.R
     * @Author: cuihp
     */
    @RequestMapping("/uploadFile")
    public R uploadFile(HttpServletRequest request, @RequestParam(value="file", required=true) MultipartFile[] pictureFiles) throws IOException {

        Properties prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        String path="";
        String baseUrl = prop.getProperty("baseUrl");
        List<String> filePathList=new ArrayList<String>();
        FtpService ftpService = new FtpService();
        for (MultipartFile mf : pictureFiles) {
            if(!mf.isEmpty()){
                byte[] bytes = mf.getBytes();
                InputStream is = new ByteArrayInputStream(bytes);
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String contentType = mf.getContentType();
                if (StringUtils.isNotBlank(contentType)){
                    String type = contentType.substring(0, contentType.indexOf("/"));
                    //判断类型
                    if ("image".equals(type)) {
                        path = prop.getProperty("imagePath");
                    } else if ("audio".equals(type)) {
                        path = prop.getProperty("audioPath");
                    }else if ("video".equals(type)){
                        path = prop.getProperty("videoPath");
                    }
                }else{
                    path = prop.getProperty("commonPath");
                }
                //根据时间获取文件夹名称
                String filePathName = FilePathUtil.creatPathName(path);
                //获取文件后缀名
                String suffixName = FileUtil.getSuffixName(mf);
                String resPath = filePathName + uuid + "." + suffixName;

                boolean b = ftpService.setWorkingDirectory(filePathName);
                if(!b){
                    return R.error("文件上传失败");
                }
                boolean upload = ftpService.upload(is, uuid + "." + suffixName);
                if(!upload){
                    return R.error("文件上传失败");
                }
                filePathList.add(FilePathUtil.getUri(baseUrl+resPath));
            }
        }
        ftpService.disconnect();
        return R.ok(1).put("data",filePathList);
    }

    @RequestMapping("/deleteFile")
    public R deleteFile(@RequestBody List<String> fileList){
        if(fileList== null || fileList.size()<=0)
            return R.error("请上传图片路径");
        FtpService ftpService = new FtpService();
        boolean b = ftpService.delete4List(fileList);
        if(b){
            return R.ok(1);
        }
        return R.error("删除失败");
    }
    @Test
    public void test(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("http://47.92.70.177:9090/4655912_1.png ");
        R r = deleteFile(strings);
        System.out.println(r);
    }

}


