package com.yida.boottracer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yida.boottracer.service.UserService;

@Controller
public class HomeController
{
	@Autowired
	private UserService userService;

	@GetMapping(value = { "/", "/index", "/index.html" })
	public String Index()
	{
		userService.insertTwo();
		return "index";
	}
}
