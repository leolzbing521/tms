package com.imhipi.app.tms.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.imhipi.app.tms.model.Pagination;

@Component("pageConfig")
public class PageConfig {

	@Value("${page.default.current_page}")
	private Integer defaultCurrentPage;
	@Value("${page.default.items_per_page}")
	private Integer defaultItemsPerPage;
	
	
	private Pagination defaultPage;
	
	public Pagination getDefaultPage(){
		if(defaultPage == null){
			defaultPage = new Pagination();
			defaultPage.setCurrentPage(defaultCurrentPage);
			defaultPage.setPageCount(defaultItemsPerPage);
		}
		return defaultPage;
	}
	
	
}
