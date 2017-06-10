package org.springframework.samples.mvc.exceptions;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public @ResponseBody String handleBusinessException(BusinessException ex) {
		return "Handled BusinessException";
	}
	
	@ModelAttribute("noUse") 
	NoUse testCAMA() {
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
