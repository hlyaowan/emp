package com.quartz.monitor.entity;

@SuppressWarnings("serial")
public class VisitUser  extends BaseDO{
	public int id;
	public String mothodName;
	public int number;
	public String modifyTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMothodName() {
		return mothodName;
	}

	public void setMothodName(String mothodName) {
		this.mothodName = mothodName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}
