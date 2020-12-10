package com.joffzhang.controller;

import com.joffzhang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zy
 * @date 2020/12/9 17:57
 */
@Controller
public class HelloController {

	@Autowired
	private HelloService helloService;

	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		return helloService.sayHello("tomcat..");
	}
	//WEB-INF/viewjsp/success.jsp
	@RequestMapping("/suc")
	public String success(){
		return "success";
	}


}
