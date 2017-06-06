package org.springframework.act;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.samples.mvc.data.JavaBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

// handler处理完将模型对象存入session status.setComplete()清除该session
@SessionAttributes(value="javaBean", types={JavaBean.class})
@Controller
public class BindController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@ModelAttribute
	public List<JavaBean> method0() {
		// 处理器方法前执行
		logger.error("method0");
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
	
	// 参数加入HttpSession 如果当前无session会生成一个新session
	@RequestMapping(value="/method1", method=RequestMethod.GET)
	public ModelAndView method1(Model model, JavaBean In, HttpServletRequest request, SessionStatus status) {
		logger.error("method1");
		// In自动暴露到模型对象  先取session，如果没有（最开始没有），模型对象有就直接使用，没有就反射创建一个 http://localhost:8080/spring-mvc-showcase/method1?param1=x&param2=y
		// @SessionAttributes注解后 In对象不存在就抛异常
		//Object ob = session.getAttribute("javaBean");
		//Object ob2 = session.getAttribute("haha");
		
		// method0  将javaBeanList存入map
		Map<String, Object> map = model.asMap();
		Object javaBeanList = map.get("javaBeanList");

		ModelAndView mv = new ModelAndView("bind");
		// 暴露为模型数据 页面展示 
		map.put("testAttr", "testAttrValue"); // mv.addObject("testAttr", "testAttrValue"); 
		mv.addObject("mapSize", map.size());
		// 仅仅清除session的javaBean属性
		status.setComplete();
		return mv;
	}
	
	@RequestMapping(value="/bind", method=RequestMethod.GET)
	@ModelAttribute
	public JavaBean method2(Model model, JavaBean In, HttpServletRequest request, SessionStatus status) {
		logger.error("bind");
		
		//Object ob = session.getAttribute("javaBean");
		//Object ob2 = session.getAttribute("haha");
		
		Map<String, Object> map = model.asMap();
		// 方法返回后bean存入模型对象
		Object javaBean = map.get("javaBean");
		
		// bean会覆盖In 暴露为模型数据  
        JavaBean bean = new JavaBean("bind1", "bind2", "bind3");
		status.setComplete();
		return bean;
	}
}
