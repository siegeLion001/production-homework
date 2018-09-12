package com.lhh.modules.interaction.domain;

import com.lhh.modules.homework.domain.ClassInfo;
import com.lhh.modules.interaction.entity.InteractionNoticeEntity;

public class InsertNoticeVo extends InteractionNoticeEntity{
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClassInfo[] classInfos;

	public ClassInfo[] getClassInfos() {
		return classInfos;
	}

	public void setClassInfos(ClassInfo[] classInfos) {
		this.classInfos = classInfos;
	}
      
}
