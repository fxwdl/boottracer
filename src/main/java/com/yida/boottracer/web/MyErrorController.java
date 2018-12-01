package com.yida.boottracer.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * https://www.baeldung.com/spring-boot-custom-error-page
 * 
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 * 
 * 如果不实现这个接口，就得通过server.error.whitelabel.enabled=false关闭默认的Whitelabel错误页，这样才能先于系统处理/error请求
 * 
 * @author fx__w
 *
 */
@Controller
public class MyErrorController implements ErrorController
{

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	     
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "500";
	        }
	    }
	    return "error";
	}
	
	@Override
	public String getErrorPath()
	{
		return "/error";
	}

}
