package com.yida.boottracer.web.mgn.info;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/info")
public class SysMemberController   extends BaseController
{
	
	@GetMapping(value = { "sys_member_edit.html" })
	public String edit(Model model)
	{
		return "mgn/info/sys_member_edit.html";
	}	
}
