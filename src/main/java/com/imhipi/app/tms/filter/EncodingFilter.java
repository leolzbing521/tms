package com.imhipi.app.tms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * TODO EncodingFilter
 * 
 * @author michael
 * @version 1.0
 *          <p>
 *          <b>功能说明：字符编码拦截器</b>
 *          </p>
 * <br>
 *          TODO 填写该类功能
 *          <p>
 *          <b>创建时间</b>2014-4-14
 *          </p>
 *          <p>
 *          <b>修改时间</b>2010-4-14
 *          </p>
 */
public class EncodingFilter implements Filter {
	public String encoding = "UTF-8";

	public void init(FilterConfig config) throws ServletException {
		String val = config.getInitParameter("encoding");
		if (val != null && val.length() > 0) {
			this.encoding = val;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding(this.encoding);
		response.setContentType("text/html;charset=" + this.encoding);
		filterChain.doFilter(request, response);
	}

	public void destroy() {
		this.encoding = null;
	}

}
