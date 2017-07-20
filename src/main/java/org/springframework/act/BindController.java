package org.springframework.act;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.samples.mvc.data.JavaBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// handler处理完将模型对象存入session status.setComplete()清除该session
@SessionAttributes(types={JavaBean.class})
@Controller
public class BindController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@InitBinder("javaBean1")
	public void initBinder1(WebDataBinder binder){
		logger.error("initBinder1 : " + ActUtil.hashCode(binder));
		binder.setValidator(new BeanValidator1());
	}
	
	@InitBinder("javaBean2")
	public void initBinder2(WebDataBinder binder){
		logger.error("initBinder2 : " + ActUtil.hashCode(binder));
		binder.setValidator(new BeanValidator2()); 
	}
	
	@ModelAttribute
	// @ModelAttribute的value为"", 返回值名称为javaBeanList
	public List<JavaBean> maList() {
		logger.error("maList: {bean1, bean2}");
		JavaBean bean1 = new JavaBean("b11", "b12", "b13");
		JavaBean bean2 = new JavaBean("b21", "b22", "b23");
		return Arrays.asList(new JavaBean[] {bean1, bean2});
	}
	
	@ModelAttribute
	// 见ModelFactory的两个方法知MA方法的调用时机,给value赋值也更高效：initModel invokeModelAttributeMethods
	public JavaBean ma00() {
		// 处理器方法前始终执行
		logger.error("ma00: javaBean");
		JavaBean bean = new JavaBean("b00", "b00", "b00");
		return bean;
	}
	
	@ModelAttribute("javaBean1")
	public JavaBean ma01() {
		logger.error("ma01: javaBean1");
		JavaBean bean = new JavaBean("01", "01", "01");
		return bean;
	}
	
	@ModelAttribute("javaBean2")
	public JavaBean ma02() {
		logger.error("ma02: javaBean2");
		JavaBean bean = new JavaBean("02", "02", "02");
		return bean;
	}
	
	
	// 参数加入HttpSession 如果当前无session会生成一个新session
	@RequestMapping(value="/method1", method=RequestMethod.GET)
	//如果需要验证值，一定要加@Valid
	//@ModelAttribute("jb1")的value需要跟前面@InitBinder("jb1")相匹配，这样才能指定走哪一个验证
	public ModelAndView method1(Model model, @Valid @ModelAttribute("javaBean1") JavaBean In, HttpServletRequest request) {
		logger.error("method1");
		// In自动暴露到模型对象  模型对象有就直接使用，没有就反射创建一个  ModelFactory:initModel->invokeModelAttributeMethods(request, mavContainer) 
		// InvocableHandlerMethod invokeForRequest->getMethodArgumentValues(request, mavContainer, providedArgs)
		// @SessionAttributes注解控制器内后 始终从模型对象取同名对象
		// http://localhost:8080/spring-mvc-showcase/method1?param1=x&param2=y
		//Object ob = session.getAttribute("javaBean");
		//Object ob2 = session.getAttribute("haha");
		
		JavaBean beanInS = null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			Object sob = session.getAttribute("javaBean");
			if(sob!=null && sob instanceof JavaBean) {
				beanInS = (JavaBean) sob;
			}
			Object sob1 = session.getAttribute("javaBean1");
			Object sob2 = session.getAttribute("javaBean2");
		}
		
		// method0  将javaBeanList存入map
		Map<String, Object> map = model.asMap();
		Object javaBeanList = map.get("javaBeanList");

		ModelAndView mv = new ModelAndView("bind");
		// 暴露为模型数据 页面展示 
		map.put("testAttr", "testAttrValue"); // mv.addObject("testAttr", "testAttrValue"); 
		mv.addObject("mapSize", map.size());
		// 仅仅清除session的javaBean属性
		//status.setComplete();
		return mv;
	}
	
	@RequestMapping(value="/bind", method=RequestMethod.GET)
	@ModelAttribute
	public JavaBean method2(Model model, @Valid @ModelAttribute("javaBean2") JavaBean In, HttpServletRequest request) {
		logger.error("bind");
		
		//Object ob = session.getAttribute("javaBean");
		//Object ob2 = session.getAttribute("haha");
		
		JavaBean beanInS = null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			Object sob = session.getAttribute("javaBean");
			if(sob!=null && sob instanceof JavaBean) {
				beanInS = (JavaBean) sob;
			}
			Object sob1 = session.getAttribute("javaBean1");
			Object sob2 = session.getAttribute("javaBean2");
		}
		
		Map<String, Object> map = model.asMap();
		// 方法返回后bean存入模型对象
		Object javaBean = map.get("javaBean");
		
		// bean会覆盖In 暴露为模型数据  
        JavaBean bean = new JavaBean("bind1", "bind2", "bind3");
		//status.setComplete();
		return bean;
	}
	
	// method3-7 DispatcherServlet流程追踪 
	@RequestMapping(value="/method3", method=RequestMethod.GET)
	public String method3(Model model) {
		// 输出到bind。jsp
		// DispatcherServlet - Successfully completed request
		logger.error("method3");
        model.addAttribute("testAttr", "method3 return jsp");
		return "bind";
	}
	@RequestMapping(value="/method4", method=RequestMethod.GET)
	// @ResponseBody输出页面内容bind 而不是到bind。jsp
	// DispatcherServlet - Successfully completed request
	public @ResponseBody String method4(Model model) {
		logger.error("method4");
        model.addAttribute("testAttr", "method4 return responsebody");
		return "bind";
	}
	@RequestMapping(value="/method5", method=RequestMethod.GET)
	@ModelAttribute
	public @ResponseBody String method5(Model model) {
		// 输出到method5。jsp HTTP Status 404 - /spring-mvc-showcase/WEB-INF/views/method5.jsp
		// DispatcherServlet - Successfully completed request
		logger.error("method5");
        model.addAttribute("testAttr", "method5 return responsebody");
		return "bind";
	}
	@RequestMapping(value="/method6", method=RequestMethod.GET)
	public @ResponseBody JavaBean method6(Model model) {
		// @ResponseBody输出页面内容<JavaBean xmlns=""><param1>m6</param1><param2>m6</param2><param3>m6</param3></JavaBean> 而不是到method6。jsp
		// !!! 方法写多了消失 后台DispatcherServlet - Could not complete request : status=[failed: java.lang.IllegalStateException: Cannot create a session after the response has been committed]
		logger.error("method6");
        model.addAttribute("testAttr", "method6 return jsp");
        JavaBean bean = new JavaBean("m6", "m6", "m6");
		return bean;
	}
	@RequestMapping(value="/method7", method=RequestMethod.GET)
	@ModelAttribute
	public @ResponseBody JavaBean method7(Model model) {
		// 输出到method7。jsp JstlView - Forwarding to resource [/WEB-INF/views/method7.jsp] in InternalResourceView 'method7'
		// HTTP Status 404 - /spring-mvc-showcase/WEB-INF/views/method7.jsp
		// 后台DispatcherServlet - Successfully completed request
		logger.error("method7");
        model.addAttribute("testAttr", "method7 return method7.jsp");
        JavaBean bean = new JavaBean("m7", "m7", "m7");
		return bean;
	}
	
	@RequestMapping(value="/goredirect")
	// 重定向->
	public String goredirect(Model model, RedirectAttributes ra, HttpSession session) {
		logger.error("goredirect");
		// model重定向不会保留
        model.addAttribute("testAttr", "testAttr-BindController-goredirect");
        model.addAttribute("attr1", "attr1-BindController-goredirect");
        // 下面的属性才会重定向传过去 addFlashAttribute
        session.setAttribute("SA", "SA-BindController-goredirect");
        ra.addFlashAttribute("RA", "RA-BindController-goredirect");
        // 被重定向后的model覆盖
        session.setAttribute("OA1", "OA1-BindController-goredirect");
        ra.addFlashAttribute("OA2", "OA2-BindController-goredirect");
        ra.addAttribute(     "OA3", "OA3-test");
		return "redirect:/bcredirect";
	    /*
	        FlashMap [
	            attributes={
		            OA2=OA2-BindController-goredirect, 
			        RA=RA-BindController-goredirect
		        }, 
		        targetRequestPath=/spring-mvc-showcase/bcredirect, 
		        targetRequestParams={
		            OA3=[OA3-test]
		        }
	        ]
	    */
		/*
		mavContainer.getModel(): defaultMap->redirectModel redirectModel的使用仅仅在这之后，新请求之前：
		RequestMappingHandlerAdapter: ModelAndView invokeHandlerMethod(HttpServletRequest request,HttpServletResponse response, HandlerMethod handlerMethod)
		                             invocableMethod.invokeAndHandle(webRequest, mavContainer)
		ServletInvocableHandlerMethod: void invokeAndHandle(ServletWebRequest webRequest,ModelAndViewContainer mavContainer, Object... providedArgs)                             
		                              this.returnValueHandlers.handleReturnValue(returnValue, getReturnValueType(returnValue), mavContainer, webRequest)
		HandlerMethodReturnValueHandlerComposite: void handleReturnValue(Object returnValue, MethodParameter returnType,ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
		                                          handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest)
		ViewNameMethodReturnValueHandler: void handleReturnValue(Object returnValue, MethodParameter returnType,ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
		                                  mavContainer.setViewName(viewName); viewName = "redirect:/bcredirect"
		                                  mavContainer.setRedirectModelScenario(true);                                  
		*/
		/*
		RequestMappingHandlerAdapter invocableMethod.invokeAndHandle(webRequest, mavContainer)之后
		ModelAndViewContainer : redirectMap: {OA3=OA3-test}, redirectMap的flashAttributes: {RA=RA-BindController-goredirect, OA2=OA2-BindController-goredirect}
		*/
	}
	
	@RequestMapping(value="/nullmv")
	public ModelAndView nullmv() {
		logger.error("nullmv");
		// 状态码200; 响应头 Content-Length:"0"
		return null;
	}
}

class BeanValidator1 implements Validator{
	private final Log logger = LogFactory.getLog(getClass());
	@Override
	public boolean supports(Class<?> clazz) {
		return JavaBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.error("BeanValidator1 validate: " + target);
		ValidationUtils.rejectIfEmpty(errors, "param1", "param1 is empty");	
	}
}

class BeanValidator2 implements Validator{
	private final Log logger = LogFactory.getLog(getClass());
	@Override
	public boolean supports(Class<?> clazz) {
		return JavaBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.error("BeanValidator2 validate: " + target);
		ValidationUtils.rejectIfEmpty(errors, "param2", "param2 is empty");	
	}
}
