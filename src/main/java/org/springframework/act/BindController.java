package org.springframework.act;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.servlet.ModelAndView;

// handler处理完将模型对象存入session status.setComplete()清除该session
@SessionAttributes(types={JavaBean.class})
@Controller
public class BindController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@InitBinder("javaBean1")
	// 存在同名模型对象就运行(反射生成的也行) 否则不运行 假如存在@ModelAttribute("javaBean1")方法，则每个处理器方法运行前都执行
	public void initBinder1(WebDataBinder binder){
		logger.error("initBinder1");
		binder.setValidator(new BeanValidator1());
	}
	
	@InitBinder("javaBean2")
	public void initBinder2(WebDataBinder binder){
		logger.error("initBinder2");
		binder.setValidator(new BeanValidator2()); 
	}
	
	@ModelAttribute
	public List<JavaBean> method0() {
		logger.error("methodList");
		JavaBean bean1 = new JavaBean("b11", "b12", "b13");
		JavaBean bean2 = new JavaBean("b21", "b22", "b23");
		return Arrays.asList(new JavaBean[] {bean1, bean2});
	}
	
	@ModelAttribute
	public JavaBean method00() {
		// 处理器方法前始终执行
		logger.error("method00");
		JavaBean bean = new JavaBean("b00", "b00", "b00");
		return bean;
	}
	
	@ModelAttribute("javaBean1")
	public JavaBean method01() {
		logger.error("method01");
		JavaBean bean = new JavaBean("01", "01", "01");
		return bean;
	}
	
	//@ModelAttribute("javaBean2")
	public JavaBean method02() {
		logger.error("method02");
		JavaBean bean = new JavaBean("02", "02", "02");
		return bean;
	}
	
	
	// 参数加入HttpSession 如果当前无session会生成一个新session
	@RequestMapping(value="/method1", method=RequestMethod.GET)
	//如果需要验证值，一定要加@Valid
	//@ModelAttribute("jb1")的value需要跟前面@InitBinder("jb1")相匹配，这样才能指定走哪一个验证
	public ModelAndView method1(Model model, @Valid @ModelAttribute("javaBean1") JavaBean In, HttpServletRequest request) {
		logger.error("method1");
		// In自动暴露到模型对象  模型对象有就直接使用，没有就反射创建一个  org.springframework.web.method.annotation.ModelFactory.initModel 
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

}

class BeanValidator1 implements Validator{
	private final Log logger = LogFactory.getLog(getClass());
	@Override
	public boolean supports(Class<?> clazz) {
		return JavaBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.error("BeanValidator1 validate");
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
		logger.error("BeanValidator2 validate");
		ValidationUtils.rejectIfEmpty(errors, "param2", "param2 is empty");	
	}
}
