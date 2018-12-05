package com.yida.boottracer.web.mgn.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yida.boottracer.web.mgn.BaseController;

@Controller
public class DictCommon extends BaseController
{
	@GetMapping(value = { "/mgn/system/dict_common_list.html" })
	public String showDictCommonList()
	{
		return "mgn/system/dict_common_list";
	}
}
