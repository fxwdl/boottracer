package com.yida.boottracer.web;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.yida.boottracer.service.auth.ImageCode;
import com.yida.boottracer.service.auth.ValidateCodeGenerator;

@RestController
public class ValidateCodeController
{
    public final static String SESSION_KEY  = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    
    @GetMapping("/valImage")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	ImageCode imageCode = imageCodeGenerator.createCode(new ServletWebRequest(request)); 
    	//将随机数 放到Session中 
    	sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY ,imageCode); 
    	//写给response 响应 
    	ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }
}
