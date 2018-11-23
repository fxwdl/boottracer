package com.yida.boottracer.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yida.boottracer.BoottracerApplicationTests;
import com.yida.boottracer.domain.test.Order;
import com.yida.boottracer.domain.test.User;
import com.yida.boottracer.service.UserService;

@Controller
public class HomeController
{
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Value("${my.title}")
	private String appName;

	@Autowired
	private UserService userService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping(value = { "/", "/index", "/index.html" })
	public String Index(HttpServletRequest request)
	{
		if (jdbcTemplate != null)
		{
			log.debug("jdbcTemplate is ok");
		}
		User user = new User();
		user.setName("商务公司");
		Order o = new Order();
		o.setOrderNumber("20190101");
		request.setAttribute("appName", appName);
		request.setAttribute("order", o);

		return "index";
	}

	// 分页示例/posts?page=0&size=2&sort=createdAt,desc
	// @GetMapping("/posts")
	// public Page<Post> getAllPosts(Pageable pageable) {
	// return postRepository.findAll(pageable);
}
