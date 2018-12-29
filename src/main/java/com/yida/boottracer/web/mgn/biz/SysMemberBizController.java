package com.yida.boottracer.web.mgn.biz;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/biz")
public class SysMemberBizController extends BaseController
{
	//public final static String CHANGE_SYSMEMBER_INFO_PERMISSION="";
	
	@PreAuthorize("hasPermission('','mgn/biz/SysMemberBiz/changeSysMemberInfo')")
	@PostMapping(value = { "SysMemberBiz/changeSysMemberInfo" })
	@ResponseBody
	public ResponseEntity<?> changeSysMemberInfo(SysMember item)
	{
		;
		return null;
	}
}
