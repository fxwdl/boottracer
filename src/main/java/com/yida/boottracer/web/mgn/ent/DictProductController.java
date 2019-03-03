package com.yida.boottracer.web.mgn.ent;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.management.openmbean.OpenMBeanParameterInfo;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.service.EntDictService;
import com.yida.boottracer.web.mgn.BaseController;
import com.yida.web.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/mgn/ent")
public class DictProductController extends BaseController
{
	@Autowired
	private EntDictService dictService;

	@GetMapping(value = { "dict_product_list.html" })
	public String showList(Model model)
	{
		model.addAttribute("ds_category", dictService.findEntDictCategoryList(this.getUser().getSysMember()));
		return "mgn/ent/dict_product_list.html";
	}

	@GetMapping(value = { "dict_product_edit.html" })
	public String showEdit(Model model, @RequestParam("id") Optional<Integer> id)
	{
		model.addAttribute("ds_coder", dictService.findEntDictCoderList(this.getUser().getSysMember()));
		model.addAttribute("ds_category", dictService.findEntDictCategoryList(this.getUser().getSysMember()));
		EntDictProduct m = null;
		if (id.isPresent())
		{
			// 查找当前用户所在企业的产品ID
			Optional<EntDictProduct> tmp = dictService.getProductById(this.getUser().getSysMember(), id.get());
			if (tmp.isPresent())
			{
				m = tmp.get();
			}
			else
			{
				throw new ResourceNotFoundException("未找到指定ID的产品");
			}
		}
		else
		{
			m = new EntDictProduct();
		}
		model.addAttribute("m", m);

		return "mgn/ent/dict_product_edit.html";
	}

	@GetMapping(value = { "dictProduct/GetData" })
	@ResponseBody
	public PagingModel<EntDictProduct> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("search") String search, @RequestParam("sort") String sort,
			@RequestParam("order") String order, @RequestParam(name="category",required=false) Integer category)
	{
		return dictService.getProductListWithPagination(this.getUser().getSysMember(), limit, offset, search, sort,
				order, category);
	}

	@GetMapping(value = { "dictProduct/Delete/{id}" })
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		dictService.deleteEntDictProductItem(this.getUser().getSysMember(), id);
		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);
	}

	@PostMapping(value = { "dictProduct/Save" }/*
												 * ,consumes=MediaType.APPLICATION_JSON_VALUE ,consumes =
												 * MediaType.APPLICATION_FORM_URLENCODED_VALUE
												 */)
	@ResponseBody
	public ResponseEntity<?> save(@Valid @ModelAttribute EntDictProduct item,
			@RequestParam(value = "files", required = false) MultipartFile file, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(dictService.saveEntDictProductItem(this.getUser().getSysMember(), item),
				HttpStatus.OK);
	}

	@GetMapping(value = { "dict_product_list_select.html" })
	public ModelAndView showSelectPage()
	{
		ModelAndView ma = new ModelAndView();
		ma.addObject("tblId", UUID.randomUUID().toString().replaceAll("-", ""));
		ma.setViewName("mgn/ent/dict_product_list_select");
		return ma;
	}
}
