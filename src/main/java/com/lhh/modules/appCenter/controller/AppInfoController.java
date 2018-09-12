package com.lhh.modules.appCenter.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lhh.common.utils.FilePathUtil;
import com.lhh.common.utils.FileUpload;
import com.lhh.common.utils.FileUtil;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.PropertieyUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.utils.ReadUtil;
import com.lhh.common.utils.ftp.FtpService;
import com.lhh.modules.appCenter.entity.AppInfoEntity;
import com.lhh.modules.appCenter.service.AppInfoService;
import com.lhh.modules.appCenter.service.AppVersionInfoService;


/**
 * 应用信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
@RestController
@RequestMapping("appinfo")
public class AppInfoController {
	@Autowired
	private AppInfoService appInfoService;
	
	@Autowired
	private AppVersionInfoService appVersionInfoService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("appinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(params.isEmpty()){
			Map<String,Object> param = new HashMap<String,Object>();
			List<AppInfoEntity> appInfoList = appInfoService.queryList(param);
			
			return R.ok().put("list", appInfoList);
		}else{
			//查询列表数据
			Query query = new Query(params);
			List<AppInfoEntity> appInfoList = appInfoService.queryList(query);
			int total = appInfoService.queryTotal(query);
			PageUtils pageUtil = new PageUtils(appInfoList, total, query.getLimit(), query.getPage());
			return R.ok().put("page", pageUtil);
		}
		
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{appId}")
	@RequiresPermissions("appinfo:info")
	public R info(@PathVariable("appId") Integer appId){
		AppInfoEntity appInfo = appInfoService.queryObject(appId);
		String lables =appInfo.getLableIdList();
		// 获取用户所属的角色列表
		List<Long> listIds = Arrays.asList(lables.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		appInfo.setLables(listIds);
		return R.ok().put("appInfo", appInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("appinfo:save")
	public R save(@RequestBody AppInfoEntity appInfo){
		String lableIds = appInfo.getLableIdList().replace("[", "").replace("]", "");
		if(lableIds.startsWith(",")){
			lableIds = lableIds.substring(1);
		}
		appInfo.setLableIdList(lableIds);
		appInfo.setScore(0.00);
		appInfo.setCreateTime(new Date());
		appInfo.setDeleteYn("N");
		appInfoService.save(appInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("appinfo:update")
	public R update(@RequestBody AppInfoEntity appInfo){
		//若图片有所更改，删除ftp上原有的图片
		String picPath = appInfo.getPicPath();
		AppInfoEntity appInfoOld = appInfoService.queryObject(appInfo.getAppId());
		if(!appInfoOld.getPicPath().equals(picPath)){
			if(picPath != null && !"".equals(picPath)){
				FtpService ftpService = new FtpService();
				ftpService.deleteAndClose(picPath);
			}
		}
		appInfoService.update(appInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("appinfo:delete")
	public R delete(@RequestBody Integer[] appIds){
		appInfoService.deleteBatch(appIds);
		
		return R.ok();
	}
    /**
     * 上传图片
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadImage")
    public Object uploadImage(HttpServletRequest request,@RequestBody MultipartFile[] file) throws IOException {
    	JSONObject object = new JSONObject();
        Properties prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        String path="";
        String baseUrl = prop.getProperty("baseUrl");
        List<String> filePathList=new ArrayList<String>();
        FtpService ftpService = new FtpService();
        //str :1上传app图标 2：上传app展示图
        String str= request.getQueryString();
        for (MultipartFile mf : file) {
            if(!mf.isEmpty()){
                byte[] bytes = mf.getBytes();
                InputStream is = new ByteArrayInputStream(bytes);
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                if(str.equals("1")){
                	  path = prop.getProperty("iconPath");
                }else if(str.equals("2")){
                	path = prop.getProperty("previewPicPath");
                }else {
                    path = prop.getProperty("commonPath");
                }
                //根据时间获取文件夹名称
                String filePathName = FilePathUtil.creatPathName(path);
                //获取文件后缀名
                String suffixName = FileUtil.getSuffixName(mf);
                String resPath = filePathName + uuid + "." + suffixName;

                boolean b = ftpService.setWorkingDirectory(filePathName);
                if(!b){
                   	object.put("msg", "文件上传失败！");
                    return object;
                }
                boolean upload = ftpService.upload(is, uuid + "." + suffixName);
                if(!upload){
                   	object.put("msg", "文件上传失败！");
                    return object;
                }
                filePathList.add(FilePathUtil.getUri(baseUrl+resPath));
            }
        }
        ftpService.disconnect();
        if(str.equals("2")){
	        if(filePathList.size()>=1){
	            object.put("picPath",filePathList);
	        }else if(str.equals("1")){
	        	object.put("picPath",filePathList.get(0));
	        }else{
	        	object.put("picPath",0);
	        }
        }else{
        	object.put("picPath",filePathList.get(0));
        }
     	object.put("code", 0);
        return object;
    }
    
	 /**
     * 上传文件工具  
     * @param file
     * @param "uploadType"  0：封面图上传    1：附件上传 2:视频上传
     * @return
	 * @throws IOException 
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFiles(@RequestParam MultipartFile file,Integer appId) throws IOException {
	 	//字节转换
    	String size = this.getSize((int)file.getSize());
    	//创建本地临时文件
    	HttpServletRequest request = this.getRequest();
    	//String contextPath = request.getContextPath();
    	//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
    	String filePath = "/document/uploadFiles/apk/";
    	String localPath = request.getSession().getServletContext().getRealPath("/");
    	localPath = localPath+filePath;
    	File tempFile = FileUpload.fileUp(file, localPath, this.get32UUID());
    	Map<String, Object> map  = new HashMap<String,Object>();
    	//解析apk
    	map= ReadUtil.readAPK(tempFile.getAbsolutePath());
    	//上传ftp
    	JSONObject object = new JSONObject();
        Properties prop = PropertieyUtils.loadPropertyInstance("deploy.properties");
        String path="";
        String baseUrl = prop.getProperty("baseUrl");
        String apkPath=new String();
        FtpService ftpService = new FtpService();
        //String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        path = prop.getProperty("apkPath");
        //根据时间获取文件夹名称
        String filePathName = FilePathUtil.creatPathName(path);
        
        boolean b = ftpService.setWorkingDirectory(filePathName);
        if(!b){
           	object.put("msg", "文件上传失败！");
            return object;
        }
        boolean upload = ftpService.upload4File(tempFile);;
        if(!upload){
           	object.put("msg", "文件上传失败！");
            return object;
        }
        apkPath=FilePathUtil.getUri(baseUrl+filePathName+tempFile.getName());
        ftpService.disconnect();
    	//设置返回数据
    	object.put("msg", "上传成功");
    	object.put("apkPath",apkPath);
    	object.put("version", map.get("versionCode"));
    	object.put("size", size);
    	object.put("packageName", map.get("packageName"));
    	object.put("appId", appId);
     	object.put("code", 0);
        return object;
    }
    
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    public String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
    public String getSize(int size) {
        //获取到的size为：1705230
        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
        int MB = 1024 * 1024;//定义MB的计算常量
        int KB = 1024;//定义KB的计算常量
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B";
        }
        return resultSize;
    }
}
