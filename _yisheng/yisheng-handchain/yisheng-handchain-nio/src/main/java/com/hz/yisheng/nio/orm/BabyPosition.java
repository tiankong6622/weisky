package com.hz.yisheng.nio.orm;

import com.google.code.morphia.annotations.Entity;

/**
 * 婴儿手环位置数据
 * @author WeiSky
 *
 */
@Entity(value = "BabyPosition", noClassnameStored = true)
public class BabyPosition extends BascDetail{

	private static final long serialVersionUID = -5203923665566426852L;
	
	public String posiType;//位置信号类型
	public String posiId;//位置ID
	public String posisignal;//位置信号强度
	
	public String getPosiType() {
		return posiType;
	}
	public void setPosiType(String posiType) {
		this.posiType = posiType;
	}
	public String getPosiId() {
		return posiId;
	}
	public void setPosiId(String posiId) {
		this.posiId = posiId;
	}
	public String getPosisignal() {
		return posisignal;
	}
	public void setPosisignal(String posisignal) {
		this.posisignal = posisignal;
	}
	
	
}
