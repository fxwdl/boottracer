package com.yida.boottracer.web.mgn;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yida.boottracer.dto.SimpleResponse;

/**
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 * mgn包全局处理：包括异常处理（html返回页面,rest请求返回json）、使用@ExceptionHandler、@InitBinder、@ModelAttribute
 * 
 * @author fx__w
 *
 */
@ControllerAdvice(basePackages = { "com.yida.boottracer.web.mgn" })
public class GlobalHandler /* extends ResponseEntityExceptionHandler */
{
	public static final String DEFAULT_ERROR_VIEW = "mgn/error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception
	{
		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it - like the OrderNotFoundException example
		// at the start of this post.
		// AnnotationUtils is a Spring Framework utility class.
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;

		// Otherwise setup and send the user to a default error-view.
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

}
