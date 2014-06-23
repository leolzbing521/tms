package com.imhipi.app.tms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.imhipi.app.tms.model.Pagination;
import com.imhipi.app.tms.model.User;
import com.imhipi.app.tms.service.GenericManager;

@Controller
public class LoginCtrl {
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager gm;
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public ModelAndView login(Pagination page, HttpServletRequest request,
			@ModelAttribute User loginUser, Model model, String msg) {
		User user = gm.findObject(loginUser);
		if(user != null) {
			request.getSession().setAttribute("user", user);
			return new ModelAndView(new RedirectView("main", true));
		}
		return new ModelAndView(new RedirectView("auth/login", true));
	}
	
	@RequestMapping(value="main")
	public String main() {
		return "main";
	}

}
