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
			        value={"/bbpath", "/bbpath*", "bbpath2", "bb/"},  
			        method={RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST},
			        produces = {"text/html", "application/*"}
                    )
	
	public @ResponseBody String bb(HttpServletRequest req) {
		String ret = "bb: " + req.getRequestURI();
		return ret;
	}
}

@Controller
class AbcCtr {
	@RequestMapping("/abc") 
	public @ResponseBody String abcMethod(HttpServletRequest req) {
		String ret = "abc: " + req.getRequestURI();
		return ret;
	}
}
