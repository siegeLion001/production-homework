package com.lhh.common.utils;
/**
 * sheet 的 对象 包含sheetName 和 相关part
 * sheetpart + sheetVo 共同组成一个模板
 * 
 * @author miaofu
 *
 */
public class SheetVo {
	/**
	 * sheet页名称
	 */
	private String sheetName;
	/**
	 * 模板组成部分
	 */
	private SheetPart[] sheetParts;

	public SheetVo(String sheetName, SheetPart... sheetParts) {
		super();
		this.sheetName = sheetName;
		this.sheetParts = sheetParts;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public SheetPart[] getSheetParts() {
		return sheetParts;
	}

	public void setSheetParts(SheetPart[] sheetParts) {
		this.sheetParts = sheetParts;
	}

}
