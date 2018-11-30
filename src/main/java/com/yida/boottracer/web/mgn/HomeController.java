package com.yida.boottracer.web.mgn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yida.boottracer.BoottracerApplicationTests;
import com.yida.boottracer.domain.DictSystemFunction;
import com.yida.boottracer.domain.test.Order;
import com.yida.boottracer.domain.test.User;
import com.yida.boottracer.service.UserService;
import com.yida.boottracer.web.BaseController;
import com.yida.enums.FunctionEnum;

@Controller(value = "mgn_HomeController")
public class HomeController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);



	@Autowired
	private UserService userService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping(value = { "/mgn", "/mgn/index", "/mgn/index.html" })
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
		request.setAttribute("order", o);

		List<DictSystemFunction> menu = new ArrayList<DictSystemFunction>();

		DictSystemFunction L1 = new DictSystemFunction(UUID.randomUUID().toString(), "L1", "一级菜单A", "L1",
				FunctionEnum.Module.ordinal());
		L1.setCssClass("fa fa-ambulance");
		L1.getChildren().add(new DictSystemFunction(UUID.randomUUID().toString(), L1, "L1_1", "二级菜单A1", "L1|L1_1",
				FunctionEnum.Menu.ordinal(), "M001", "#", "", null));
		L1.getChildren().add(new DictSystemFunction(UUID.randomUUID().toString(), L1, "L1_2", "二级菜单A2", "L1|L1_2",
				FunctionEnum.Menu.ordinal(), "M002", "#", "", null));
		menu.add(L1);

		DictSystemFunction L2 = new DictSystemFunction(UUID.randomUUID().toString(), "L2", "一级菜单B", "L2",
				FunctionEnum.Module.ordinal());
		L2.getChildren().add(new DictSystemFunction(UUID.randomUUID().toString(), L1, "L2_1", "二级菜单B1", "L2|L2_1",
				FunctionEnum.Menu.ordinal(), "M001", "#", "", null));
		L2.getChildren().add(new DictSystemFunction(UUID.randomUUID().toString(), L1, "L2_2", "二级菜单B2", "L2|L2_2",
				FunctionEnum.Menu.ordinal(), "M002", "#", "", null));
		L2.setCssClass("fa fa-calculator");
		menu.add(L2);

		request.setAttribute("menu", menu);
		return "mgn/index";
	}

	// 分页示例/posts?page=0&size=2&sort=createdAt,desc
	// @GetMapping("/posts")
	// public Page<Post> getAllPosts(Pageable pageable) {
	// return postRepository.findAll(pageable);
}