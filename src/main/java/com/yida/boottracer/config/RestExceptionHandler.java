package com.yida.boottracer.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//说是可以用它来控制中心化的异常处理，但是在ResourceNotFoundException也进行了异常处理，后续需要再研究一下。
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{

	// @ExceptionHandler({ BookNotFoundException.class })
	// protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest
	// request)
	// {
	// return handleExceptionInternal(ex, "Book not found", new HttpHeaders(),
	// HttpStatus.NOT_FOUND, request);
	// }
	//
	// @ExceptionHandler({ BookIdMismatchException.class,
	// ConstraintViolationException.class,
	// DataIntegrityViolationException.class })
	// public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest
	// request)
	// {
	// return handleExceptionInternal(ex, ex.getLocalizedMessage(), new
	// HttpHeaders(), HttpStatus.BAD_REQUEST,
	// request);
	// }
}