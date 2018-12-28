package com.yida.boottracer.web.mgn.system;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.enums.DictCommomType;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.web.mgn.BaseController;
import com.yida.web.exception.ResourceNotFoundException;


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
	public ModelAndView showDictCommonForm(@RequestParam(name = "id", required = false) Integer id)
	{
		ModelAndView v = new ModelAndView();
		v.setViewName("mgn/system/dict_common_form");

		if (id != null)
		{
			Optional<DictCommon> item = dictService.getCommonById(id);
			if (!item.isPresent())
				throw new ResourceNotFoundException("未找到id为'" + id + "'的数据！");
			v.addObject("m", item.get());
		}

		return v;
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
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		dictService.deleteCommonItem(id);
		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);		
	}

	@PostMapping(value = { "dictCommon/Save" }/* ,consumes=MediaType.APPLICATION_JSON_VALUE */)
	@ResponseBody
	public ResponseEntity<?> save(@Valid @RequestBody DictCommon item, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(dictService.saveCommonItem(item), HttpStatus.OK);
	}
}
