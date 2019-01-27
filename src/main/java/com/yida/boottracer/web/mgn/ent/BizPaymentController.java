package com.yida.boottracer.web.mgn.ent;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.yida.boottracer.domain.BizPayment;
import com.yida.boottracer.domain.DictMemberPrice;
import com.yida.boottracer.domain.DictMemberType;
import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.dto.PaymentDTO;
import com.yida.boottracer.enums.MemberPriceType;
import com.yida.boottracer.enums.PayType;
import com.yida.boottracer.service.BizPaymentService;
import com.yida.boottracer.service.DictService;
import com.yida.boottracer.web.mgn.BaseController;

@Controller
@RequestMapping(value = "/mgn/ent")
public class BizPaymentController extends BaseController
{
	@Autowired
	private BizPaymentService bizService;

	@Autowired
	private DictService dictService;

	@GetMapping(value = { "biz_payment_list.html" })
	public String showList(Model model)
	{
		return "mgn/ent/biz_payment_list.html";
	}


	@GetMapping(value = { "biz_payment_edit.html" })
	public String showEdit(Model model)
	{
		model.addAttribute("ds_payType", PayType.getList());

		return "mgn/ent/biz_payment_edit.html";
	}

	@GetMapping(value = { "bizPayment/GetData" })
	@ResponseBody
	public PagingModel<BizPayment> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("sort") String sort, @RequestParam("order") String order)
	{
		return bizService.getListWithPagination(this.getUser().getSysMember(), limit, offset, sort, order);
	}

	@GetMapping(value = { "bizPayment/GetItem" })
	public ResponseEntity<?> getById(@RequestParam(value = "id", required = false) Integer id)
	{
		BizPayment item = null;
		if (id == null)
		{
			item = bizService.getNewItem(this.getUser());
		}
		else 
		{
			item = bizService.findById(this.getUser(), id);
		}
		
		PaymentDTO dto = new PaymentDTO();
		dto.setBizPayment(item);
		dto.setPayTypeList(PayType.getList());
		
		Optional<DictMemberType> mt = dictService.getMemberTypeById(item.getSysMember().getDictMemberType().getId());

		dto.setBarcodePriceList(new ArrayList<DictMemberPrice>());
		dto.setAccountPeriodList(new ArrayList<DictMemberPrice>());
		dto.setAccountBalanceList(new ArrayList<DictMemberPrice>());

		if (mt.isPresent())
		{
			for (DictMemberPrice it : mt.get().getDictMemberPrices())
			{
				if (it.getType() == MemberPriceType.CreateBarcode.getId())
				{
					dto.getBarcodePriceList().add(it);
				}
				else if (it.getType() == MemberPriceType.Platform.getId())
				{
					dto.getAccountPeriodList().add(it);
				}
			}
		}
		dto.getBarcodePriceList().sort((v1, v2) -> v1.getQty() < v2.getQty() ? -1 : 1);
		dto.getBarcodePriceList().sort((v1, v2) -> v1.getQty() < v2.getQty() ? -1 : 1);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping(value = { "bizPayment/Delete/{id}" })
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		bizService.delete(this.getUser(), id);
		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);
	}

	@PostMapping(value = { "bizPayment/Save" })
	@ResponseBody
	public ResponseEntity<?> save(@Valid @RequestBody BizPayment item, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(bizService.save(getUser(), item), HttpStatus.OK);
	}
}
