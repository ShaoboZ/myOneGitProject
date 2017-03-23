package cn.com.bo.web;


import java.util.List;

import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.bo.entity.Sys_user;
import cn.com.bo.service.Sys_userService;

@Controller
@RequestMapping("/user/")
public class Sys_userController {

	@Autowired
	private Sys_userService sys_userService;
	
	@RequestMapping(value={"list",""})
	public String list(Sys_user sys_user, Model model){
		List<Sys_user> list = sys_userService.getList(sys_user);
		model.addAttribute("userList", list);
		return "/user/userList";
	}
	@RequestMapping("save")
	public String save(Sys_user sys_user){
		Integer id = sys_userService.save(sys_user);
		return "redirect:/user/list?repage";
	}
	
	@RequestMapping("form")
	public String form(Sys_user sys_user, Model model){
		model.addAttribute("sys_user", sys_user);
		return "/user/userForm";
	}
	
}
