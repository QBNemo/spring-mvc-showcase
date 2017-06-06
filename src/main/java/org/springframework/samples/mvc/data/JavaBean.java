package org.springframework.samples.mvc.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.act.ActUtil;

public class JavaBean {
	private final Log logger = LogFactory.getLog(getClass());
	
	private String param1;
	
	private String param2;
	
	private String param3;

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
		logger.error(ActUtil.hashCode(this) + " setParam1 :" + param1);
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
		logger.error(ActUtil.hashCode(this) + " setParam2 :" + param2);
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
		logger.error(ActUtil.hashCode(this) + " setParam3 :" + param3);
	}
	
	public JavaBean() {
		logger.error("JavaBean() " + this.toString() );
	}
	
	public JavaBean(String param1, String param2, String param3) {
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		logger.error("JavaBean(p1, p2, p3) " + this.toString());
	}

	@Override
	public String toString() {
		return ActUtil.hashCode(this) + " [param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + "]";
	}

}