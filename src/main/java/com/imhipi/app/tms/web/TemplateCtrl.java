package com.imhipi.app.tms.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("template")
public class TemplateCtrl {
	
	@RequestMapping(value="example/pageLoad")
	public String getDefaultCfg(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "example/pageLoad";
	}
	
	@RequestMapping(value="purchase/list")
	public String purchaseList(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchase/list";
	}
	
	@RequestMapping(value="purchase/listTable")
	public String purchaseListData(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchase/listTable";
	}

}
