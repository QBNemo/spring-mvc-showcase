package org.springframework.act;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebFilter(value = {"/TestFilter", "/simple/revisited"})
public class TestFilter implements Filter {
    private static Log logger = LogFactory.getLog(TestFilter.class);

    public TestFilter() {
        logger.error("TestFilter construct an instance : @" + Integer.toHexString(this.hashCode()));
    }

	public void destroy() {
		logger.error("TestFilter construct an instance : @" + Integer.toHexString(this.hashCode()));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.error("doFilterPre@" + Integer.toHexString(this.hashCode())+ " :" + ((HttpServletRequest)request).getRequestURI() + " @" +Integer.toHexString(request.hashCode()));
		chain.doFilter(request, response);
		logger.error("doFilterPost@" + Integer.toHexString(this.hashCode())+ " :" +  ((HttpServletRequest)request).getRequestURI() + " @" +Integer.toHexString(request.hashCode()));
	}

	public void init(FilterConfig fConfig) throws ServletException {
		StringBuilder sb = new StringBuilder("TestFilter init \n");
		String filterName = fConfig.getFilterName();
		String testParam = fConfig.getInitParameter("test-filter-param");
		String hashCode = Integer.toHexString(this.hashCode());
		sb.append("\t" + "filterName : " + filterName + "\n");
		sb.append("\t" + "test-filter-param : " + testParam + "\n");
		sb.append("\t" + "hashCode : @" + hashCode + "\n");
		logger.error(sb.toString());
	}
}
