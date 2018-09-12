package com.lhh.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


/**
 * 上传文件 创建人：LiuHJ 创建时间：2017年8月22日
 * 
 * @version 1.0
 */
public class FileUpload {

	/**
	 * @param file
	 *            //文件对象
	 * @param filePath
	 *            //上传路径
	 * @param fileName
	 *            //文件名
	 * @return 文件名
	 * @throws IOException 
	 */
	public static File fileUp(MultipartFile file, String filePath, String fileName) throws IOException {
		File tempFile=null;
		String extName="";
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}

			tempFile = new File(filePath, fileName + extName);
			if (!tempFile.exists()) {
				if (!tempFile.getParentFile().exists() || tempFile.getParentFile()  != null) {
					tempFile.getParentFile().mkdirs();
				}
				//tempFile.createNewFile();
				tempFile.createTempFile(fileName, ".apk");
			}
			file.transferTo(tempFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}

}
