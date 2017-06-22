package org.springframework.samples.mvc.exceptions;

import java.util.List;

import org.springframework.act.Mojo;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.method.annotation.SessionAttributesHandler;
import org.springframework.web.method.support.InvocableHandlerMethod;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public @ResponseBody String handleBusinessException(BusinessException ex) {
		return "Handled BusinessException";
	}
	
	@ModelAttribute("noUse")
	// mojo参数 测试ModelFactory(List<InvocableHandlerMethod> invocableMethods, WebDataBinderFactory dataBinderFactory, SessionAttributesHandler sessionAttributesHandler)
	NoUse testCAMA(@ModelAttribute("mojo") Mojo mojo) {
		return new NoUse();
	}
	
	private class NoUse {
		String calss = "NoUse";
	}
	
	@InitBinder
	public void testCAIB(WebDataBinder binder) {
		binder.toString();
	}
}
