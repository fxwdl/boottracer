package com.yida.boottracer.web.mgn.biz;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yida.boottracer.SpringContextUtil;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.enums.SysMemberStatus;
import com.yida.boottracer.service.UserService;
import com.yida.boottracer.web.mgn.BaseController;
import com.yida.boottracer.web.mgn.ent.SysMemberController;

@Controller
@RequestMapping(value = "/mgn/biz")
public class SysMemberBizController extends BaseController
{
	// public final static String CHANGE_SYSMEMBER_INFO_PERMISSION="";

	@Autowired
	private UserService userService;

	@GetMapping(value = { "sys_member_list.html" })
	public String showList(Model model)
	{
		model.addAttribute("dictType", SysMemberStatus.getList());
		return "mgn/biz/sys_member_list.html";
	}

	@GetMapping(value = { "SysMemberBiz/GetData" })
	@ResponseBody
	public PagingModel<SysMember> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("search") String search, @RequestParam("sort") String sort,
			@RequestParam("order") String order, @RequestParam(name = "status", required = false) Integer status,
			@RequestParam(name = "start", required = false) Date start,
			@RequestParam(name = "end", required = false) Date end)
	{
		// return dictService.getStdDiseaseListWithPagination(limit, offset, search,
		// sort, order, rt_id);

		return userService.getListWithPagination(limit, offset, search, sort, order, status, start, end);
	}

	//@PreAuthorize("hasPermission('','mgn/biz/SysMemberBiz/changeSysMemberInfo')")
	@PostMapping(value = { "SysMemberBiz/save" })
	@ResponseBody
	public ResponseEntity<?> save(SysMember item, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(userService.ApproveMember(item), HttpStatus.OK);
	}
	
	@GetMapping(value = { "SysMemberBiz/Delete/{id}" })
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") long id)
	{
		userService.delete(id);
		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);		
	}
	
	@GetMapping(value = { "sys_member_edit.html" })
	public ModelAndView showEditPage(@RequestParam(name = "id", required = false) Long id)
	{
		SysMemberController c = SpringContextUtil.getBeanByName(com.yida.boottracer.web.mgn.ent.SysMemberController.class);
		c.setSaveAction("/mgn/biz/SysMemberBiz/save");
		
		c.setModule("业务管理");
		c.setFunctionName("企业管理");
		c.setSmallTitle("编辑");
		
		return c.showEditPage(id);
	}
}
