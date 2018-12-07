package com.yida.boottracer.web.mgn.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.enums.DictCommomType;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.web.mgn.BaseController;
@Controller
@RequestMapping(value = "/mgn/system")
public class DictCommonController extends BaseController
{
	@Autowired
	private DictService dictService;
	
	@GetMapping(value = { "dict_common_list.html" })
	public String showDictCommonList(Model model)
	{
		model.addAttribute("dictType", DictCommomType.getList());
		return "mgn/system/dict_common_list";
	}
	
	@GetMapping(value = { "dictCommon/GetData" })
	@ResponseBody
	public PagingModel<DictCommon> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("search") String search, @RequestParam("sort") String sort,
			@RequestParam("order") String order, @RequestParam("type") int type)
	{
		//return dictService.getStdDiseaseListWithPagination(limit, offset, search, sort, order, rt_id);
		
		return dictService.getCommonListWithPagination(limit, offset, search, sort, order, type);
	}
}
