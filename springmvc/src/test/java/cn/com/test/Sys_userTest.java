package cn.com.test;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.bo.entity.Sys_user;
import cn.com.bo.service.Sys_userService;

public class Sys_userTest {
	
	@Autowired
	private Sys_userService service;

	public void save(){
		Sys_user sys_user = new Sys_user();
		sys_user.setUsername("1"); 
		sys_user.setPassword("1");
		service.save(sys_user);
	}
	
	public static void main(String[] args) {
		System.out.println("plan_id,date,hour".contains("date"));
	}
}
