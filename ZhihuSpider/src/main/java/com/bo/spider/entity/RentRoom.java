package com.bo.spider.entity;

public class RentRoom implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1852090301980997803L;
	// 标题
	private String title;
	// 金额
	private String money;
	// 整租or合租
	private String rent_type;
	// 房间大小
	private String size;
	// 房间朝向：南，南北
	private String room_orientation;
	// 小区名称
	private String cell_name;
	// 楼层
	private String floor;
	// 格局
	private String pattern;
	// 图片路径
	private String img_path;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRent_type() {
		return rent_type;
	}

	public void setRent_type(String rent_type) {
		this.rent_type = rent_type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getRoom_orientation() {
		return room_orientation;
	}

	public void setRoom_orientation(String room_orientation) {
		this.room_orientation = room_orientation;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getCell_name() {
		return cell_name;
	}

	public void setCell_name(String cell_name) {
		this.cell_name = cell_name;
	}

}
