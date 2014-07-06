package com.imhipi.app.tms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.imhipi.app.tms.service.MemberService;
import com.imhipi.app.tms.util.PageUtils;

@Controller
@RequestMapping("/purchaseItem")
public class PurchaseItemCtrl extends BaseController {
	
	@Autowired
    private PageConfig pageConfig;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager gm;
	
	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("purchases", gm.findMulti(new Purchase()));
		model.addAttribute("materials", dictService.getDictsByType(DictRootType.MATERIAL_TYPE));
		model.addAttribute("types", dictService.getDictsByType(DictRootType.GEM_TYPE));
		return "jsonView";
	}
	
	@RequestMapping(value="platinumAttach", method = RequestMethod.GET)
	public String platinumAttach(Model model) {
		model.addAttribute("hasDiamonds", dictService.getDictsByType(DictRootType.HAS_DIAMOND));
		model.addAttribute("diamondDimens", dictService.getDictsByType(DictRootType.DIAMOND_DIMENSION));
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
		}
		model.addAttribute("data", items);
		return "jsonView";
	}
	
	@RequestMapping(value="sell/{purchaseItemId}/{memberId}", method = RequestMethod.GET)
	public String sell(@PathVariable Long purchaseItemId, @PathVariable Long memberId, Pagination page, HttpServletRequest request, Model model) {
		boolean result = memberService.sell((User)request.getSession().getAttribute("user"), purchaseItemId, memberId);
		if(result) {
			model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "操作成功"));
		} else {
			model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "此商品不存在或已售，操作失败"));
		}
		return "jsonView";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String create(@ModelAttribute PurchaseItem purchaseItem, HttpServletRequest request, Model model) {
		PurchaseItem searchItem = new PurchaseItem();
		searchItem.setBarcode(purchaseItem.getBarcode());
		if(gm.findMulti(searchItem).size() == 0) {
			User user = (User)request.getSession().getAttribute("user");
			purchaseItem.setCuserId(user.getId());
			purchaseItem.setUuserId(user.getId());
			gm.save(purchaseItem);
			model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "添加成功"));
		} else {
			model.addAttribute("msg", new ResponseMsg(ResponseMsgType.ERROR.value(), "条形码已存在，添加失败"));
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String create(@PathVariable Long id, HttpServletRequest request, Model model) {
		PurchaseItem purchaseItem = gm.findObject(new PurchaseItem(id));
		model.addAttribute(purchaseItem);
		return "jsonView";
	}
	
	@RequestMapping(value="modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute PurchaseItem purchaseItem, HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		purchaseItem.setUuserId(user.getId());
		gm.update(purchaseItem);
		model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "修改成功"));
		return "jsonView";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String remove(@PathVariable Long id, HttpServletRequest request, Model model) {
		gm.remove(id, PurchaseItem.class);
		model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "删除成功"));
		return "jsonView";
	}

}
