package com.yida.boottracer.web.mgn.ent;

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

import com.yida.boottracer.domain.EntDictCoder;
import com.yida.boottracer.domain.EntDictSupplier;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.service.EntDictService;
import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/ent")
public class DictCoderController extends BaseController
{
	@Autowired
	private EntDictService dictService;
	
	@GetMapping(value = { "dict_coder_list.html" })
	public String showList(Model model)
	{		
		return "mgn/ent/dict_coder_list.html";
	}
	
	@GetMapping(value = { "dictCoder/GetData" })
	@ResponseBody
	public PagingModel<EntDictSupplier> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("search") String search, @RequestParam("sort") String sort,
			@RequestParam("order") String order)
	{
		return dictService.getDataPagination(EntDictSupplier.class,limit, offset, search, sort, order);	
	}
	
	@GetMapping(value = { "dictCoder/Delete/{id}" })
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		dictService.deleteEntSupplierItem(this.getUser().getSysMember(), id);
		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);		
	}

	@PostMapping(value = { "dictCoder/Save" }/* ,consumes=MediaType.APPLICATION_JSON_VALUE */)
	@ResponseBody
	public ResponseEntity<?> save(@Valid @RequestBody EntDictSupplier item, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(dictService.saveEntSupplierItem(this.getUser().getSysMember(), item), HttpStatus.OK);
	}
	
	@GetMapping(value = { "dictCoder/CreateDefaultCoderData" })
	@ResponseBody
	public ResponseEntity<?> createDefaultCoderData()
	{
		EntDictCoder result = dictService.createDefaultCoder(this.getUser().getSysMember());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
