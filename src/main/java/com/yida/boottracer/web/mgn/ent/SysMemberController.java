package com.yida.boottracer.web.mgn.ent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/mgn/ent")
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
			mv.addObject("ds_county", m.getDictRegion().getParent().getChildren().stream()
					.sorted(Comparator.comparing(DictRegion::getCode)).collect(Collectors.toList()));

			city = m.getDictRegion().getParent().getId();

			mv.addObject("ds_city", m.getDictRegion().getParent().getParent().getChildren().stream()
					.sorted(Comparator.comparing(DictRegion::getCode)).collect(Collectors.toList()));

			province = m.getDictRegion().getParent().getParent().getId();

		}

		mv.addObject("county", county);
		mv.addObject("city", city);
		mv.addObject("province", province);
		mv.setViewName("mgn/info/sys_member_edit.html");
		return mv;
	}
}
