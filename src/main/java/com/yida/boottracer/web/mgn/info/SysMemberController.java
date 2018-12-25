package com.yida.boottracer.web.mgn.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yida.boottracer.domain.DictRegion;
import com.yida.boottracer.enums.DictCommomType;
import com.yida.boottracer.enums.SysMemberStatus;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/info")
public class SysMemberController extends BaseController
{
	@Autowired
	private DictService dictService;

	@GetMapping(value = { "sys_member_edit.html" })
	public String edit(Model model)
	{
		model.addAttribute("ds_Industry", dictService.getCommonListByType(DictCommomType.Industry))
				.addAttribute("ds_CompanyType", dictService.getCommonListByType(DictCommomType.CompanyType))
				.addAttribute("ds_status", SysMemberStatus.getList());
		
		List<DictRegion> dsRegion = dictService.getRegionListByParentId(1);
		DictRegion empItem=new DictRegion();
		empItem.setId(0);
		empItem.setName("请选择");
		dsRegion.add(0, empItem);
		model.addAttribute("ds_province", dsRegion);

		return "mgn/info/sys_member_edit.html";
	}
}
