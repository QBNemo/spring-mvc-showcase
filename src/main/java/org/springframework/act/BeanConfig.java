package org.springframework.act;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

@Controller
public class BeanConfig implements ApplicationContextAware{
	private ApplicationContext app;
	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.error("setApplicationContext ：" + ActUtil.hashCode(applicationContext));
		this.app = applicationContext;
	}
	
	@RequestMapping("/AbstractHandlerMapping")
	public @ResponseBody String AbstractHandlerMapping() {
		Collection<AbstractHandlerMapping> col = BeanFactoryUtils.beansOfTypeIncludingAncestors(
				this.app, AbstractHandlerMapping.class, true, false).values();
		
		if(col == null) {
			return "AbstractHandlerMapping: null";
		}
		
		StringBuilder sb = new StringBuilder("AbstractHandlerMapping: ");
		Iterator<AbstractHandlerMapping> it = col.iterator();
		while(it.hasNext()) {
			AbstractHandlerMapping ahm = it.next();
			sb.append("\n\t" + ActUtil.hashCode(ahm));
		}
		return sb.toString();
	}

}
