package org.springframework.samples.mvc;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.act.Embed;
import org.springframework.stereotype.Component;

@Component("mvcBeanAnno")
public class MvcBean {
    private String mvcBeanName = "mvcBeanAnnoted";
    private static final Log logger = LogFactory.getLog(MvcBean.class);

    public MvcBean() {
    	logger.error("MvcBean construct use no-arg :" + new Date().toLocaleString());
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
	
	// 容器会管理mbed1、mbed2、mbed3
	@Component("mbed1")
	public static class Embed1 implements Embed {
		// @Component("mbed1") public class Embed1 implements Embed {}报错：
		// Failed to instantiate [org.springframework.samples.mvc.MvcBean$Embed1]: No default constructor found; nested exception is java.lang.NoSuchMethodException: org.springframework.samples.mvc.MvcBean$Embed1.<init>()
		// 加上构造函数public Embed1() {} 依然报错 将内部类加上static修饰就好了
	}
	@Component("mbed2")
	protected static class Embed2 implements Embed {
		public Embed2() {}
	}
	@Component("mbed3")
	private static class Embed3 implements Embed {
		public Embed3() {}
	}
	public class Embed4 implements Embed {
	}  
}
