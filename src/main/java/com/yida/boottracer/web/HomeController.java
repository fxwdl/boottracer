package com.yida.boottracer.web;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yida.boottracer.dto.ImageResultDto;
import com.yida.service.FileStorageService;

@Controller
public class HomeController
{
	@Value("${my.upload-dir}")
	private String uploadDir;

	@GetMapping(value = { "", "index.html" })
	public String Index(HttpServletRequest request)
	{
		return "index";
	}

	@ResponseBody
	@GetMapping(value = "uploadImange/{fileName:.+}")
	public ResponseEntity<Resource> getUploadImage(@PathVariable String fileName, HttpServletRequest request)
	{
		Resource resource = FileStorageService.loadFileAsResource(uploadDir, fileName);

		// Try to determine file's content type
		String contentType = null;
		try
		{
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null)
		{
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				//.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
