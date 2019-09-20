package com.yida.boottracer.web.mgn.ent;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import com.yida.boottracer.domain.BizCodeApply;
import com.yida.boottracer.domain.BizPayment;
import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.enums.ApproveType;
import com.yida.boottracer.enums.DeliveryQueryType;
import com.yida.boottracer.service.BizCodeService;
import com.yida.boottracer.web.mgn.BaseController;
import com.yida.web.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/mgn/ent")
public class BizCodeDeliveryController extends BaseController
{
	@Autowired
	private BizCodeService bizCodeService;
	
	@Value("${my.querybarcode.url}")
	private String queryUrl;

	@GetMapping(value = { "biz_code_delivery_list.html" })
	public String showDeliveryList(Model model)
	{
		model.addAttribute("dsQueryType", DeliveryQueryType.getList());
		return "mgn/ent/biz_code_delivery_list.html";
	}

	@GetMapping(value = { "BizCodeDelivery/GetDeliveryListData" })
	@ResponseBody
	public PagingModel<?> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("sort") String sort, @RequestParam("order") String order,
			@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "batchCode", required = false) String batchCode,
			@RequestParam(name = "start", required = false) Date start,
			@RequestParam(name = "end", required = false) Date end,
			@RequestParam(name = "queryType", required = false) Integer queryType)
	{
		return bizCodeService.getListWithPagination(this.getUser().getSysMember(), limit, offset, sort, order,
				productName, batchCode, start, end, ApproveType.Approved.getId(),queryType);
	}

}
