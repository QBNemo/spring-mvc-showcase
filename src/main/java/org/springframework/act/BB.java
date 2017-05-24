package org.springframework.act;

import java.lang.reflect.AnnotatedElement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

@RequestMapping(name="BBT")
public class BB {
	/**
	 * 	RequestMappingHandlerMapping createRequestMappingInfo(AnnotatedElement element)
	 *	RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class)
	 *  可能有bug
	 */
	
	// value path只可取一个
	@RequestMapping(name="BBM", 
			        value={"/bbpath", "bbpath2"}, 
			        method={RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST},
			        produces = {"text/plain", "application/*"}
                    )
	
	public @ResponseBody String bb(HttpServletRequest req) {
		String ret = "bb: " + req.getRequestURI();
		return ret;
	}
}
