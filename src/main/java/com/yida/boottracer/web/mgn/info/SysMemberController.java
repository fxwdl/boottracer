package com.yida.boottracer.web.mgn.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yida.boottracer.domain.DictRegion;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.enums.DictCommomType;
import com.yida.boottracer.enums.SysMemberStatus;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.service.UserService;
import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/info")

public class SysMemberController extends BaseController
{
	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;

	@GetMapping(value = { "sys_member_edit.html" })
	public ModelAndView edit(@RequestParam(name = "id", required = false) Integer id)
	{
		ModelAndView mv = new ModelAndView();

		mv.addObject("ds_Industry", dictService.getCommonListByType(DictCommomType.Industry))
				.addObject("ds_CompanyType", dictService.getCommonListByType(DictCommomType.CompanyType))
				.addObject("ds_status", SysMemberStatus.getList());

		List<DictRegion> dsRegion = dictService.getRegionListByParentId(1);
		DictRegion empItem = new DictRegion();
		empItem.setId(0);
		empItem.setName("请选择");
		dsRegion.add(0, empItem);
		mv.addObject("ds_province", dsRegion);

		if (this.getUser().IsEnterpriseMember())
		{
			id=this.getUser().getSysMember().getId();
		}
		
		SysMember m = userService.getSysMemberByUser(id);

		mv.addObject("m", m);

		
		mv.setViewName("mgn/info/sys_member_edit.html");
		return mv;
	}
}
