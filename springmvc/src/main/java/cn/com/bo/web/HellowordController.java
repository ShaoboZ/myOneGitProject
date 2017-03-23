package cn.com.bo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HellowordController extends BaseController{
	
	@RequestMapping(value = "a")
	public String helloword(){
		return "helloword";
	}
}
