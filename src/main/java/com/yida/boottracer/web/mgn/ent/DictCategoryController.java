package com.yida.boottracer.web.mgn.ent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/ent")
public class DictCategoryController extends BaseController
{
	@GetMapping(value = { "dict_category_list.html" })
	public String showList(Model model)
	{		
		return "mgn/ent/dict_category_list.html";
	}
	
	@GetMapping(value = { "dictCategory/GetData" })
	@ResponseBody
	public PagingModel<SysMember> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("search") String search, @RequestParam("sort") String sort,
			@RequestParam("order") String order)
	{
		return null;
	}
}
