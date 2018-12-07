package com.yida.boottracer.web.mgn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yida.boottracer.BoottracerApplicationTests;
import com.yida.boottracer.domain.DictSystemFunction;
import com.yida.boottracer.domain.test.Order;
import com.yida.boottracer.domain.test.User;
import com.yida.boottracer.dto.SimpleResponse;
import com.yida.boottracer.service.UserService;
import com.yida.enums.FunctionEnum;

@Controller(value = "mgn_HomeController")
@RequestMapping(value = "/mgn")
public class HomeController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@GetMapping(value = { "", "index", "index.html" })
	public String Index(HttpServletRequest request)
	{
		Order o = new Order();
		o.setOrderNumber("20190101");
		request.setAttribute("order", o);

		return "mgn/index";
	}

	@GetMapping(value = { "403.html" })
	public String handle403()
	{
		return "mgn/403";
	}

	@GetMapping(value = { "test" })
	public String test(HttpServletRequest request)
	{
		throw new RuntimeException("test error");
	}

	@GetMapping(value = "testAdmin")
	// @Secured(value = "ROLE_管理员")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView testAdmin()
	{
		ModelAndView m = new ModelAndView();

		m.addObject("msg", "成功");
		m.setViewName("mgn/success");

		return m;
	}
	
	@GetMapping(value = "testAdmin.html")
	// @Secured(value = "ROLE_管理员")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView testAdminHtml()
	{
		ModelAndView m = new ModelAndView();

		m.addObject("msg", "成功");
		m.setViewName("mgn/success");

		return m;
	}

	// 分页示例/posts?page=0&size=2&sort=createdAt,desc
	// @GetMapping("/posts")
	// public Page<Post> getAllPosts(Pageable pageable) {
	// return postRepository.findAll(pageable);
}