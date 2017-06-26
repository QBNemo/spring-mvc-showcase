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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes(types={Mojo.class})
@Controller
public class SaController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@InitBinder("mojo")
	// 存在同名模型对象就运行(反射生成的也行) 否则不运行 假如存在@ModelAttribute("javaBean1")方法，则每个处理器方法运行前都执行
	public void mojoIB(WebDataBinder binder){
		logger.error("initBinder-mojo");
	}

	@ModelAttribute
	// 测试没有@ModelAttribute
	public Mojo mojoMA() {
		// 处理器方法前始终执行
		logger.error("modelAttribute-mojo");
		Mojo m = new Mojo("m'In:mojoMA");
		return m;
	}
	
	@RequestMapping(value="/sa", method=RequestMethod.GET)
	@ModelAttribute
	public Mojo method1(Model model, @ModelAttribute("mojo") Mojo in, HttpServletRequest request) {
		logger.error("sa");

		HttpSession session = request.getSession(false);
		if(session!=null) {
		}
		
		Map<String, Object> map = model.asMap();
		// 方法返回后bean存入模型对象
		Object m = map.get("Mojo");
		Mojo out = new Mojo("m'In:sa");
		return out;
	}
	
	@RequestMapping(value="/sa2", method=RequestMethod.GET)
	@ModelAttribute
	public Mojo method2(Model model, Mojo in, HttpServletRequest request) {
		logger.error("sa2");
		
		Map<String, Object> map = model.asMap();
		// 方法返回后bean存入模型对象
		Object m = map.get("Mojo");
		Mojo out = new Mojo("m'In:sa2");
		return out;
	}
}
