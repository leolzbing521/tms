package com.imhipi.app.tms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imhipi.app.tms.model.Pagination;
import com.imhipi.app.tms.model.User;
import com.imhipi.app.tms.service.GenericManager;

@Controller
public class LoginCtrl extends BaseController {
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager gm;
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(Pagination page, HttpServletRequest request,
			@ModelAttribute User loginUser, Model model, String msg) {
		User user = gm.findObject(loginUser);
		if(user != null) {
			request.getSession().setAttribute("user", user);
			return "main";
		}
		return "auth/login";
	}
	
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout(Pagination page, HttpServletRequest request,
			@ModelAttribute User loginUser, Model model, String msg) {
		request.getSession().removeAttribute("user");
		return "auth/login";
	}
	
	@RequestMapping(value="main")
	public String main() {
		return "main";
	}

}
