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
import com.yida.boottracer.service.BizCodeService;
import com.yida.boottracer.web.mgn.BaseController;
import com.yida.web.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/mgn/ent")
public class BizCodeMgnController extends BaseController
{
	@Autowired
	private BizCodeService bizCodeService;
	
	@Value("${my.querybarcode.url}")
	private String queryUrl;

	@GetMapping(value = { "biz_code_apply_list.html" })
	public String showList(Model model)
	{
		return "mgn/ent/biz_code_apply_list.html";
	}

	@GetMapping(value = { "biz_code_approve_list.html" })
	public String showApproveList(Model model)
	{
		model.addAttribute("ds_approved", ApproveType.getList());
		return "mgn/ent/biz_code_approve_list.html";
	}

	@GetMapping(value = { "bizCodeMgn/GetMyApplyData" })
	@ResponseBody
	public PagingModel<?> getData(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			@RequestParam("sort") String sort, @RequestParam("order") String order,
			@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "batchCode", required = false) String batchCode,
			@RequestParam(name = "start", required = false) Date start,
			@RequestParam(name = "end", required = false) Date end,
			@RequestParam(name = "approved", required = false) Integer approved)
	{
		return bizCodeService.getListWithPagination(this.getUser().getSysMember(), limit, offset, sort, order,
				productName, batchCode, start, end, approved);
	}

	@GetMapping(value = { "biz_code_apply_edit.html" })
	public String showApplyPage(Model model, @RequestParam("id") Optional<Integer> id)
	{
		BizCodeApply m = null;
		if (id.isPresent())
		{
			Optional<BizCodeApply> tmp = bizCodeService.getById(this.getUser().getSysMember(), id.get());
			if (tmp.isPresent())
			{
				m = tmp.get();
			}
			else
			{
				throw new ResourceNotFoundException("未找到指定ID的数据");
			}
		}
		else
		{
			m = bizCodeService.newBizCodeApp(this.getUser());
		}

		model.addAttribute("m", m);

		return "mgn/ent/biz_code_apply_edit.html";
	}

	@GetMapping(value = { "biz_code_approve_edit.html" })
	public String showApprovPage(Model model, @RequestParam("id") Optional<Integer> id)
	{
		BizCodeApply m = null;
		if (id.isPresent())
		{
			Optional<BizCodeApply> tmp = bizCodeService.getById(this.getUser().getSysMember(), id.get());
			if (tmp.isPresent())
			{
				m = tmp.get();
			}
			else
			{
				throw new ResourceNotFoundException("未找到指定ID的数据");
			}
		}
		else
		{
			m = bizCodeService.newBizCodeApp(this.getUser());
		}

		model.addAttribute("m", m);

		return "mgn/ent/biz_code_approve_edit.html";
	}

	@PostMapping(value = { "bizCodeMgn/Save" })
	@ResponseBody
	public ResponseEntity<?> save(@Valid @ModelAttribute BizCodeApply item, Errors errors)
	{
		if (errors.hasErrors())
		{
			return ResponseEntity.badRequest().body(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<>(bizCodeService.save(this.getUser(), item), HttpStatus.OK);
	}

	@GetMapping(value = { "bizCodeMgn/Delete/{id}" })
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		bizCodeService.delete(this.getUser(), id);
		return new ResponseEntity<>("删除数据成功！", HttpStatus.OK);
	}

	@PostMapping(value = { "bizCodeMgn/Approve" })
	@ResponseBody
	public ResponseEntity<?> approve(Integer applyId, String comment)
	{
		bizCodeService.ApproveApply(this.getUser(), applyId,queryUrl);
		
		return new ResponseEntity<>("操作成功", HttpStatus.OK);
	}
}
