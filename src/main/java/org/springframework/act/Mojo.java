package org.springframework.act;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Mojo {
    private String mojoName = "mojo'name";
    private static final Log logger = LogFactory.getLog(Mojo.class);
    
    public Mojo() {
    	logger.error("Mojo construct use no-arg ： " + new Date().toLocaleString());
    }
    
    public Mojo(String mojoName) {
    	this.mojoName = mojoName;
    	logger.error("Mojo construct use mojoName: " + mojoName);
    }
    
	public String getMojoName() {
		return mojoName;
	}

	public void setMojoName(String mojoName) {
		this.mojoName = mojoName;
	}

	@Override
	public String toString() {
		return "Mojo(mojoName：" + mojoName + ")";
	}
}
