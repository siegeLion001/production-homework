package com.lhh.modules.appCenter.entity;

import java.io.Serializable;
import java.util.List;



/**
 * 应用信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public class AppInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer app_id;
	private String app_name;
	private String pic_path;
	private double score;
	private Integer download_count;
	private String introduce;
	private Integer comment_count;
	private List<String> preview_pic_path;
	private Integer app_version_id;
	private Integer app_version;
	private String app_size;
	private String update_introduce;
	private String apk_path;
	private String package_name;
	private String client;
	public Integer getApp_id() {
		return app_id;
	}
	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Integer getDownload_count() {
		return download_count;
	}
	public void setDownload_count(Integer download_count) {
		this.download_count = download_count;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getComment_count() {
		return comment_count;
	}
	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}
	public List<String> getPreview_pic_path() {
		return preview_pic_path;
	}
	public void setPreview_pic_path(List<String> preview_pic_path) {
		this.preview_pic_path = preview_pic_path;
	}
	public Integer getApp_version_id() {
		return app_version_id;
	}
	public void setApp_version_id(Integer app_version_id) {
		this.app_version_id = app_version_id;
	}
	public Integer getApp_version() {
		return app_version;
	}
	public void setApp_version(Integer app_version) {
		this.app_version = app_version;
	}
	public String getApp_size() {
		return app_size;
	}
	public void setApp_size(String app_size) {
		this.app_size = app_size;
	}
	public String getUpdate_introduce() {
		return update_introduce;
	}
	public void setUpdate_introduce(String update_introduce) {
		this.update_introduce = update_introduce;
	}
	public String getApk_path() {
		return apk_path;
	}
	public void setApk_path(String apk_path) {
		this.apk_path = apk_path;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}

}
