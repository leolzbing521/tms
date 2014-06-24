package com.imhipi.app.tms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imhipi.app.tms.component.PageConfig;
import com.imhipi.app.tms.model.Pagination;
import com.imhipi.app.tms.model.Purchase;
import com.imhipi.app.tms.service.GenericManager;
import com.imhipi.app.tms.util.PageUtils;

@Controller
@RequestMapping("/purchase")
public class PurchaseCtrl {
	
	@Autowired
    private PageConfig pageConfig;
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager gm;
	
	@RequestMapping(value="listData", method = RequestMethod.GET)
	public String listData(ModelMap map, Pagination page, HttpServletRequest request, Model model) {
		if (PageUtils.isEmpty(page)) {
            page = pageConfig.getDefaultPage();
        }
		
		page.setTotal(gm.countTotalNum(new Purchase(), page));
		
		model.addAttribute("pagination", page);
		model.addAttribute("data", gm.findByNamedAndPageQuery("findPurchaseByPage", page, Purchase.class));
		return "jsonView";
	}

}
