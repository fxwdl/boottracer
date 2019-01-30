package com.yida.boottracer.web.mgn.biz;

import java.util.ArrayList;
import java.util.Optional;
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

import com.yida.boottracer.SpringContextUtil;
import com.yida.boottracer.domain.BizPayment;
import com.yida.boottracer.domain.DictMemberPrice;
import com.yida.boottracer.domain.DictMemberType;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.dto.PaymentDTO;
import com.yida.boottracer.enums.ApproveType;
import com.yida.boottracer.enums.MemberPriceType;
import com.yida.boottracer.enums.PayType;
import com.yida.boottracer.service.BizPaymentService;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.web.mgn.BaseController;
import com.yida.boottracer.web.mgn.ent.SysMemberController;

@Controller(value="Biz_BizPaymentController")
@RequestMapping(value = "/mgn/biz")
public class BizPaymentController extends BaseController
{
	@Autowired
	private BizPaymentService bizService;

	@Autowired
	private DictService dictService;

	@GetMapping(value = { "biz_payment_list.html" })
	public String showList(Model model)
	{
		model.addAttribute("ds_payType", PayType.getList());
		model.addAttribute("ds_approved", ApproveType.getList());
		
		return "mgn/biz/biz_payment_list.html";
	}

//
	@GetMapping(value = { "biz_payment_approve.html" })
	public String showEdit(Model model)
	{
		model.addAttribute("ds_payType", PayType.getList());

		return "mgn/biz/biz_payment_approve.html";
	}
//
	@GetMapping(value = { "bizPayment/GetData" })
	@ResponseBody
	public PagingModel<BizPayment> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("sort") String sort, @RequestParam("order") String order,Integer type,Integer state,String entName)
	{
		return bizService.getListWithPagination(limit, offset, sort, order,type,entName,state);
	}
//
	@GetMapping(value = { "bizPayment/GetItem" })
	public ResponseEntity<?> getById(@RequestParam(value = "id", required = true) Integer id)
	{
		com.yida.boottracer.web.mgn.ent.BizPaymentController c = SpringContextUtil.getBeanByName(com.yida.boottracer.web.mgn.ent.BizPaymentController.class);

		return c.getById(id);
	}
//
//	@GetMapping(value = { "bizPayment/Delete/{id}" })
//	@ResponseBody
//	public ResponseEntity<?> delete(@PathVariable("id") int id)
//	{
//		bizService.delete(this.getUser(), id);
//		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);
//	}
//
	@PostMapping(value = { "bizPayment/Approve" })
	@ResponseBody
	public ResponseEntity<?> save(@Valid @RequestBody BizPayment item, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(bizService.approve(getUser(), item), HttpStatus.OK);
	}
}
