package com.yida.boottracer.web.mgn.system;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.boottracer.domain.DictCommon;
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
		return dictService.getDictMemberTypeWithPagination(limit, offset, search, sort, order);
	}
	
	@GetMapping(value = { "dictMemberType/Delete/{id}" })
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		dictService.deleteMemberTypeItem(id);
		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);
	}
	
	@PostMapping(value = { "dictMemberType/Save" }/* ,consumes=MediaType.APPLICATION_JSON_VALUE */)
	@ResponseBody
	public ResponseEntity<?> save(@Valid @RequestBody DictMemberType item, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(dictService.saveMemberTypeItem(item), HttpStatus.OK);
	}
}
