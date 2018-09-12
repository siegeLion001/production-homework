package com.lhh.modules.interaction.domain;

import com.lhh.modules.homework.domain.ClassInfo;
import com.lhh.modules.interaction.entity.InteractionNoticeEntity;

public class NoticeListVo extends InteractionNoticeEntity{
      private ClassInfo[] readY;
      private ClassInfo[] readN;
	public ClassInfo[] getReadY() {
		return readY;
	}
	public void setReadY(ClassInfo[] readY) {
		this.readY = readY;
	}
	public ClassInfo[] getReadN() {
		return readN;
	}
	public void setReadN(ClassInfo[] readN) {
		this.readN = readN;
	}
      
}
