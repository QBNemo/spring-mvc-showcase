package org.springframework.act;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RootContextBean {
    private static final Log logger = LogFactory.getLog(RootContextBean.class);
	
    private String name;

    public RootContextBean() {	
    	logger.error("RootContextBean construct with no-arg");
    }
    
    public RootContextBean(String name) {
    	this.name = name;
    	logger.error("RootContextBean construct use name: " + name);
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		logger.error("RootContextBean setName: " + name);
	}    
}
