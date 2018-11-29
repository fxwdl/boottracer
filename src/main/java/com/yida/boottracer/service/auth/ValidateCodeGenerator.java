package com.yida.boottracer.service.auth;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator
{
	/**
	 * 创建验证码
	 */
	ImageCode createCode(ServletWebRequest request);
}
