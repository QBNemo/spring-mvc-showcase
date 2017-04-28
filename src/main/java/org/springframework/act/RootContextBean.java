package org.springframework.act;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RootContextBean {
    private static final Log logger = LogFactory.getLog(RootContextBean.class);
	
    private String name;

    public RootContextBean() {	
    }
    
    public RootContextBean(String name) {
    	this.name = name;
    	logger.error("Construct RootContextBean with name : " + name);
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}    
}
