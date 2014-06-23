package com.imhipi.app.tms.util;

import com.imhipi.app.tms.model.Pagination;

public class PageUtils {

	public static boolean isEmpty(Pagination page) {
		boolean ret = true;
		if (page != null)
			ret = page.getCurrentPage() == 0 & page.getPageCount() == 0;
		return ret;
	}

}
