package org.springframework.act;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(name="BBT")
public class BB {
	// value path只可取一个  spring会自动在前面补/
	@RequestMapping(name="BBM", 
			        // bb/模式就是/bb/,匹配路径/bb/,不匹配路径/bb
			        value={"/bbpath*", "/bbpath", "bbpath2", "bb/"},  
			        method={RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST},
			        produces = {"text/html", "application/*"}
                    )
	
	public @ResponseBody String bb(HttpServletRequest req) {
		String ret = "BB bb: " + req.getRequestURI();
		return ret;
	}
}

@Controller
class AbcCtr {
	//@RequestMapping(name="abcMethod", value="/abc/{abcCtrUriTemplateVariable}") 
	public @ResponseBody String abcMethod(HttpServletRequest req) {
		String ret = "AbcCtr abc : " + req.getRequestURI();
		return ret;
	}
	
	// URL匹配，但方法不支持
	@RequestMapping(name="abcMethod1", value="/abc/{abcCtrUriTemplateVariable}", method={RequestMethod.POST}) 
	public @ResponseBody String abcMethod1(HttpServletRequest req) {
		String ret = "AbcCtr abc post: "  + req.getRequestURI();
		return ret;
	}
	
	// URL匹配，但param不支持 此处只要该参数存在即可
	@RequestMapping(name="abcMethod2", value="/abc/{abcCtrUriTemplateVariable}", params="paramtest") 
	public @ResponseBody String abcMethod2(HttpServletRequest req) {
		String ret = "AbcCtr abc params: "  + req.getRequestURI();
		return ret;
	}
	
	// URL匹配，但header不支持
	@RequestMapping(name="abcMethod3", value="/abc/{abcCtrUriTemplateVariable}", params="paramtest2=testparam2", headers="headertest") 
	public @ResponseBody String abcMethod3(HttpServletRequest req) {
		String ret = "AbcCtr abc headers: "  + req.getRequestURI();
		return ret;
	}
}
