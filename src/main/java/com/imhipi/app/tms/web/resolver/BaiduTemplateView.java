package com.imhipi.app.tms.web.resolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class BaiduTemplateView extends AbstractUrlBasedView {

	private final static String CONTENT_TYPE = "text/pain";

	// http response conent
	private String responseContent;

	public BaiduTemplateView() {
		super();
		setContentType(CONTENT_TYPE);
	}

	@Override
	public void setContentType(String contentType) {
		super.setContentType(contentType);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 设置 content type
		response.setContentType(getContentType());
		response.setCharacterEncoding("UTF-8");
		// 写入 response 内容
		response.getWriter().write(this.responseContent);
		response.getWriter().close();
	}

	/**
	 * 设置 http response content
	 * 
	 * @param responseContent
	 */
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
}
