package com.yida.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileStorageService
{
	public static String storeFile(String location, MultipartFile file)
	{
		
		try
		{
			Path fileStorageLocation = Paths.get(location).toAbsolutePath().normalize();
			Files.createDirectories(fileStorageLocation);
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			int index = fileName.lastIndexOf(".");
			String extName = index > -1 ? fileName.substring(index) : "";
			String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
			String newFileName = uuid + extName;

			Path targetLocation = fileStorageLocation.resolve(newFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return newFileName;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}

	}

	public static Resource loadFileAsResource(String location, String fileName)
	{
		try
		{
			Path fileStorageLocation = Paths.get(location).toAbsolutePath().normalize();
			Path filePath = fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists())
			{
				return resource;
			}
			else
			{
				throw new RuntimeException("文件未找到：" + fileName);
			}
		}
		catch (MalformedURLException ex)
		{
			throw new RuntimeException("文件未找到：" + fileName);
		}
	}
}
