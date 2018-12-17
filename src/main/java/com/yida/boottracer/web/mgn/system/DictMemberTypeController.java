package com.yida.boottracer.web.mgn.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.boottracer.domain.DictMemberType;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/system")
public class DictMemberTypeController  extends BaseController
{
	@Autowired
	private DictService dictService;
	
	@GetMapping(value = { "dict_member_type_list.html" })
	public String showList(Model model)
	{
		return "mgn/system/dict_member_type_list";
	}
	
	@GetMapping(value = { "dictMemberType/GetData" })
	@ResponseBody
	public PagingModel<DictMemberType> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("search") String search, @RequestParam("sort") String sort,
			@RequestParam("order") String order)
	{
		// return dictService.getStdDiseaseListWithPagination(limit, offset, search,
		// sort, order, rt_id);

		return dictService.getDictMemberTypeWithPagination(limit, offset, search, sort, order);
	}	
}
