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

import com.imhipi.app.tms.component.DictService;
import com.imhipi.app.tms.component.OrgService;
import com.imhipi.app.tms.component.PageConfig;
import com.imhipi.app.tms.enums.DictRootType;
import com.imhipi.app.tms.enums.ResponseMsgType;
import com.imhipi.app.tms.model.Member;
import com.imhipi.app.tms.model.Pagination;
import com.imhipi.app.tms.model.Purchase;
import com.imhipi.app.tms.model.ResponseMsg;
import com.imhipi.app.tms.model.User;
import com.imhipi.app.tms.service.GenericManager;
import com.imhipi.app.tms.util.PageUtils;

@Controller
@RequestMapping("/member")
public class MemberCtrl extends BaseController {
	
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
		model.addAttribute("sexList", dictService.getDictsByType(DictRootType.SEX));
		return "jsonView";
	}
	
	@RequestMapping(value="listData", method = RequestMethod.POST)
	public String listData(Pagination page, HttpServletRequest request, Model model) {
		if (PageUtils.isEmpty(page)) {
            page = pageConfig.getDefaultPage();
        }
		page.setTotal(gm.countTotalNum(new Member(), page));
		
		model.addAttribute("pagination", page);
		model.addAttribute("data", gm.findByNamedAndPageQuery("findMemberByPage", page, Member.class));
		return "jsonView";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String create(@ModelAttribute Member member, HttpServletRequest request, Model model) {
		Member phoneMember = new Member();
		phoneMember.setPhone(member.getPhone());
		Member checkPhone = gm.findObject(phoneMember);
		if(checkPhone != null) {
			model.addAttribute("msg", new ResponseMsg(ResponseMsgType.ERROR.value(), "此电话号码已被注册，添加失败"));
		} else if(member.getIdentityCard() != null) {
			Member cardMember = new Member();
			cardMember.setIdentityCard(member.getIdentityCard());
			Member checkCard = gm.findObject(cardMember);
			if(checkCard != null) {
				model.addAttribute("msg", new ResponseMsg(ResponseMsgType.ERROR.value(), "此身份证已被注册，添加失败"));
			} else {
				User user = (User)request.getSession().getAttribute("user");
				member.setCuserId(user.getId());
				member.setUuserId(user.getId());
				gm.save(member);
				model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "添加成功"));
			}
		} else {
			User user = (User)request.getSession().getAttribute("user");
			member.setCuserId(user.getId());
			member.setUuserId(user.getId());
			gm.save(member);
			model.addAttribute("msg", new ResponseMsg(ResponseMsgType.SUCCESS.value(), "添加成功"));
		}
		return "jsonView";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String create(@PathVariable Long id, HttpServletRequest request, Model model) {
		Purchase purchase = gm.findObject(new Purchase(id));
		model.addAttribute(purchase);
		return "jsonView";
	}
	
	@RequestMapping(value="modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Member member, HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		member.setUuserId(user.getId());
		gm.update(member);
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
