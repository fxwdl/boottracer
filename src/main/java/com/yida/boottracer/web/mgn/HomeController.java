package com.yida.boottracer.web.mgn;

import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yida.boottracer.domain.test.Order;
import com.yida.boottracer.dto.ImageResultDto;
import com.yida.boottracer.repo.impl.mybatis.mapper.BizCodeMapper;
import com.yida.boottracer.service.BizCodeService;
import com.yida.service.FileStorageService;

@Controller(value = "mgn_HomeController")
@RequestMapping(value = "/mgn")
public class HomeController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Value("${my.upload-dir}")
	private String uploadDir;
	
	@Autowired
	private BizCodeMapper bizCodeMapper;
	
	@Autowired
	private BizCodeService bizCodeService;

	@GetMapping(value = {"index.html" })
	public String Index(HttpServletRequest request)
	{
		Order o = new Order();
		o.setOrderNumber("20190101");
		request.setAttribute("order", o);

		return "mgn/index";
	}

	@GetMapping(value = { "403.html" })
	public String handle403()
	{
		return "mgn/403";
	}

	@ResponseBody
	@PostMapping(value = "uploadImange")
	public ImageResultDto uploadImage(@RequestParam("files") List<MultipartFile> files, HttpServletRequest request,
			HttpServletResponse response)
	{
		ImageResultDto r = new ImageResultDto();
		try {
			Pattern pattern = Pattern.compile(".+(jpg|jpeg|png|bmp|gif|webp)$", Pattern.CASE_INSENSITIVE);
			for (MultipartFile f : files)
			{
				String name = f.getOriginalFilename();
				Matcher isMatch = pattern.matcher(name);
				if (!isMatch.matches())
				{
					continue;
				}
				String newName = FileStorageService.storeFile(uploadDir, f);
				// String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				// .path("/uploadImange/")
				// .path(newName)
				// .toUriString();
				String pString = request.getContextPath();
				String fileDownloadUri = pString + "/uploadImange/" + newName;
				r.getData().add(fileDownloadUri);
			}
		}
		catch(RuntimeException ex)
		{
			r.setErrno(-1);
			ex.printStackTrace();
		}

		return r;
	}


	@GetMapping(value = "testAdmin")
	// @Secured(value = "ROLE_管理员")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView testAdmin()
	{
		ModelAndView m = new ModelAndView();

		m.addObject("msg", "成功");
		m.setViewName("mgn/success");

		return m;
	}

	@GetMapping(value = "testAdmin.html")
	// @Secured(value = "ROLE_管理员")
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView testAdminHtml()
	{
		ModelAndView m = new ModelAndView();

		m.addObject("msg", "成功");
		m.setViewName("mgn/success");

		return m;
	}

	// 分页示例/posts?page=0&size=2&sort=createdAt,desc
	// @GetMapping("/posts")
	// public Page<Post> getAllPosts(Pageable pageable) {
	// return postRepository.findAll(pageable);

	@GetMapping(value = "test")
	public String test()
	{
		bizCodeService.generateCode(1,"");

		return "mgn/index";
	}

}