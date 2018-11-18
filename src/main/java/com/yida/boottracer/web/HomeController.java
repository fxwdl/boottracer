package com.yida.boottracer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
	@GetMapping(value = { "/", "/index", "/index.html" })
	public String Index()
	{
		return "index";
	}
}
