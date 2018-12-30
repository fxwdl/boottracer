package com.yida.boottracer.web.mgn.ent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yida.boottracer.domain.DictRegion;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.enums.DictCommomType;
import com.yida.boottracer.enums.SysMemberStatus;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.service.UserService;
import com.yida.boottracer.web.mgn.BaseController;
import com.yida.web.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/mgn/ent")
public class SysMemberController extends BaseController
{
	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;

	@GetMapping(value = { "sys_member_edit.html" })
	public ModelAndView showEditPage(@RequestParam(name = "id", required = false) Integer id)
	{
		ModelAndView mv = new ModelAndView();

		mv.addObject("ds_Industry", dictService.getCommonListByType(DictCommomType.Industry))
				.addObject("ds_CompanyType", dictService.getCommonListByType(DictCommomType.CompanyType))
				.addObject("ds_status", SysMemberStatus.getList());

		List<DictRegion> dsRegion = dictService.getRegionListByParentId(1);
		mv.addObject("ds_province", dsRegion);

		if (this.getUser().IsEnterpriseMember())
		{
			id = this.getUser().getSysMember().getId();
		}

		SysMember m = userService.getSysMemberByUser(id);

		mv.addObject("m", m);

		Integer county = 0, city = 0, province = 0;
		if (m.getDictRegion() != null)
		{
			county = m.getDictRegion().getId();
			mv.addObject("ds_county", dictService.getRegionListByParentId(m.getDictRegion().getParent().getId()));

			city = m.getDictRegion().getParent().getId();

			mv.addObject("ds_city", dictService.getRegionListByParentId(m.getDictRegion().getParent().getParent().getId()));

			province = m.getDictRegion().getParent().getParent().getId();

		}
		mv.addObject("county", county);
		mv.addObject("city", city);
		mv.addObject("province", province);
		mv.setViewName("mgn/ent/sys_member_edit.html");
		return mv;
	}

	@PostMapping(value = { "SysMember/save" })
	@ResponseBody
	public ResponseEntity<?> save(@Valid @ModelAttribute SysMember item, Errors errors) throws NotFoundException
	{	
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(userService.editMember(item), HttpStatus.OK);
	}
}
