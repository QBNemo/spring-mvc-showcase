package org.springframework.samples.mvc.exceptions;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.act.ActUtil;
import org.springframework.act.Mojo;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Log logger = LogFactory.getLog(getClass());
	
	@ExceptionHandler
	public @ResponseBody String handleBusinessException(BusinessException ex) {
		logger.error("GlobalExceptionHandler handleBusinessException: " + ex.getMessage());
		return "Handled BusinessException";
	}
	
	@ModelAttribute("noUse")
	// mojo参数 测试ModelFactory(List<InvocableHandlerMethod> invocableMethods, WebDataBinderFactory dataBinderFactory, SessionAttributesHandler sessionAttributesHandler)
	NoUse testCAMA(@ModelAttribute("mojo") Mojo mojo) {
		logger.error("GlobalExceptionHandler testCAMA: " + ActUtil.hashCode(mojo));
		return new NoUse();
	}
	
	private class NoUse {
		String calss = "NoUse";
	}
	
	@InitBinder("ABC")
	// InitBinderDataBinderFactory: boolean isBinderMethodApplicable(HandlerMethod initBinderMethod, WebDataBinder binder) 决定是否运行
	public void testCAIB(WebDataBinder binder) {
		logger.error("GlobalExceptionHandler testCAIB: " + ActUtil.hashCode(binder));
		binder.toString();
	}
}
