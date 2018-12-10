package com.yida.boottracer.web.mgn.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.dto.SimpleResponse;
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
	
	@GetMapping(value = { "dict_common_form.html" })
	public String showDictCommonForm(Model model)
	{
		return "mgn/system/dict_common_form";
	}

	@GetMapping(value = { "dictCommon/GetData" })
	@ResponseBody
	public PagingModel<DictCommon> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("search") String search, @RequestParam("sort") String sort,
			@RequestParam("order") String order, @RequestParam("type") int type)
	{
		// return dictService.getStdDiseaseListWithPagination(limit, offset, search,
		// sort, order, rt_id);

		return dictService.getCommonListWithPagination(limit, offset, search, sort, order, type);
	}

	@GetMapping(value = { "dictCommon/Delete/{id}" })
	@ResponseBody
	public SimpleResponse delete(@PathVariable("id") int id)
	{
		return dictService.deleteCommonItem(id);
	}

	@PostMapping(value = { "dictCommon/Save" })
	@ResponseBody
	public SimpleResponse save(@RequestBody DictCommon item, BindingResult result)
	{
		if (result.hasErrors())
		{
			//return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			return new SimpleResponse(false, result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		return dictService.updateCommonItem(item);
	}
}
