package com.imhipi.app.tms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imhipi.app.tms.component.DictService;
import com.imhipi.app.tms.component.OrgService;
import com.imhipi.app.tms.component.PageConfig;
import com.imhipi.app.tms.enums.DictRootType;
import com.imhipi.app.tms.enums.ResponseMsgType;
import com.imhipi.app.tms.model.Pagination;
import com.imhipi.app.tms.model.Purchase;
import com.imhipi.app.tms.model.PurchaseItem;
import com.imhipi.app.tms.model.ResponseMsg;
import com.imhipi.app.tms.model.User;
import com.imhipi.app.tms.service.GenericManager;
import com.imhipi.app.tms.util.PageUtils;

@Controller
@RequestMapping("/sale")
public class saleCtrl extends BaseController {
	
	@Autowired
    private PageConfig pageConfig;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager gm;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("purchases", gm.findMulti(new Purchase()));
		model.addAttribute("materials", dictService.getDictsByType(DictRootType.MATERIAL_TYPE));
		model.addAttribute("types", dictService.getDictsByType(DictRootType.GEM_TYPE));
		return "jsonView";
	}
	
	@RequestMapping(value="listData", method = RequestMethod.POST)
	public String listData(Pagination page, HttpServletRequest request, Model model) {
		if (PageUtils.isEmpty(page)) {
            page = pageConfig.getDefaultPage();
        }
		
		page.setTotal(gm.countTotalNum(new PurchaseItem(), page));
		
		model.addAttribute("pagination", page);
		List<PurchaseItem> items = gm.findByNamedAndPageQuery("findPurchaseItemByPage", page, PurchaseItem.class);
		for(PurchaseItem item : items) {
			item.setTypeName(dictService.getDictByTypeAndKey(DictRootType.GEM_TYPE, item.getType()));
			item.setMaterialName(dictService.getDictByTypeAndKey(DictRootType.MATERIAL_TYPE, item.getMaterial()));
			if(item.getDiamondDimension() != null) {
				item.setDiamondDimensionName(dictService.getDictByTypeAndKey(DictRootType.DIAMOND_DIMENSION, item.getDiamondDimension()));
			}
		}
		model.addAttribute("data", items);
		return "jsonView";
	}
	
	@RequestMapping(value="modify", method = RequestMethod.GET)
	public String modify(@ModelAttribute PurchaseItem purchaseItem, HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		purchaseItem.setUuserId(user.getId());
		gm.update(purchaseItem);
		model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "修改成功"));
		return "jsonView";
	}
	
	@RequestMapping(value="exchange", method = RequestMethod.POST)
	public String exchange(@ModelAttribute PurchaseItem purchaseItem, HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		purchaseItem.setUuserId(user.getId());
		gm.update(purchaseItem);
		model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "修改成功"));
		return "jsonView";
	}
	
}
