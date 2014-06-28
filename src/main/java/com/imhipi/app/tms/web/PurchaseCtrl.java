package com.imhipi.app.tms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imhipi.app.tms.component.OrgService;
import com.imhipi.app.tms.component.PageConfig;
import com.imhipi.app.tms.enums.ResponseMsgType;
import com.imhipi.app.tms.model.Pagination;
import com.imhipi.app.tms.model.Purchase;
import com.imhipi.app.tms.model.ResponseMsg;
import com.imhipi.app.tms.model.User;
import com.imhipi.app.tms.service.GenericManager;
import com.imhipi.app.tms.util.PageUtils;

@Controller
@RequestMapping("/purchase")
public class PurchaseCtrl extends BaseController {
	
	@Autowired
    private PageConfig pageConfig;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager gm;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("orgs", orgService.getRootOrgs());
		return "jsonView";
	}
	
	@RequestMapping(value="listData", method = RequestMethod.POST)
	public String listData(Pagination page, HttpServletRequest request, Model model) {
		if (PageUtils.isEmpty(page)) {
            page = pageConfig.getDefaultPage();
        }
		page.setTotal(gm.countTotalNum(new Purchase(), page));
		
		model.addAttribute("pagination", page);
		model.addAttribute("data", gm.findByNamedAndPageQuery("findPurchaseByPage", page, Purchase.class));
		return "jsonView";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String create(@ModelAttribute Purchase purchase, HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		purchase.setCuserId(user.getId());
		purchase.setOrgId(user.getOrgId());
		purchase.setUuserId(user.getId());
		gm.save(purchase);
		model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "添加成功"));
		return "jsonView";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String create(@PathVariable Long id, HttpServletRequest request, Model model) {
		Purchase purchase = gm.findObject(new Purchase(id));
		model.addAttribute(purchase);
		return "jsonView";
	}
	
	@RequestMapping(value="modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Purchase purchase, HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		purchase.setUuserId(user.getId());
		gm.update(purchase);
		model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "修改成功"));
		return "jsonView";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String remove(@PathVariable Long id, HttpServletRequest request, Model model) {
		gm.remove(id, Purchase.class);
		model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "删除成功"));
		return "jsonView";
	}

}
