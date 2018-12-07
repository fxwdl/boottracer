package com.yida.boottracer.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class HomeController 
{
	@GetMapping(value = { "", "index.html" })
	public String Index(HttpServletRequest request)
	{
		return "index";
	}
}
