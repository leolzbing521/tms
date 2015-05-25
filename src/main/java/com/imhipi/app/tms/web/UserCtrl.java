package com.imhipi.app.tms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imhipi.app.tms.model.Pagination;

@Controller
@RequestMapping("/user")
public class UserCtrl extends BaseController {
	
	private final String LIST_ACTION = "/web/user";
	
	public String list(Pagination page, HttpServletRequest request,
            @ModelAttribute UserCtrl searchUser, Model model, String msg) {
		
		return null;
	}

}
