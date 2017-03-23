package com.bo.spider.entity;

public class Dynatown implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 411540758597042604L;
	private String name;
	// 服务商圈
	private String service_area;
	// 熟悉小区
	private String familiar_cell;
	// phone
	private String phone;
	// 出租数量
	private int rent_num;
	// 售卖数量
	private int sell_num;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getService_area() {
		return service_area;
	}

	public void setService_area(String service_area) {
		this.service_area = service_area;
	}

	public String getFamiliar_cell() {
		return familiar_cell;
	}

	public void setFamiliar_cell(String familiar_cell) {
		this.familiar_cell = familiar_cell;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRent_num() {
		return rent_num;
	}

	public void setRent_num(int rent_num) {
		this.rent_num = rent_num;
	}

	public int getSell_num() {
		return sell_num;
	}

	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}

}
