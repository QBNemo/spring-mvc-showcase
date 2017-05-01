package org.springframework.samples.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("mvcBeanAnno")
public class MvcBean {
    private String mvcBeanName = "mvcBeanAnnoted";
    private static final Log logger = LogFactory.getLog(MvcBean.class);

    public MvcBean() {
    	logger.error("MvcBean construct use no-arg");
    }
    
    public MvcBean(String mvcBeanName) {
    	this.mvcBeanName = mvcBeanName;
    	logger.error("MvcBean construct use mvcBeanName: " + mvcBeanName);
    }
    
	public String getMvcBeanName() {
		return mvcBeanName;
	}

	public void setMvcBeanName(String mvcBeanName) {
		this.mvcBeanName = mvcBeanName;
		logger.error("MvcBean setMvcBeanName: " + mvcBeanName);
	}
    
}
