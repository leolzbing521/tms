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
public class TemplateCtrl extends BaseController {
	
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
	
	@RequestMapping(value="purchaseItem/list")
	public String purchaseItemList(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/list";
	}
	
	@RequestMapping(value="purchaseItem/listTable")
	public String purchaseItemListData(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/listTable";
	}
	
	@RequestMapping(value="purchaseItem/goldAttach")
	public String goldAttach(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/goldAttach";
	}
	
	@RequestMapping(value="purchaseItem/platinumAttach")
	public String platinumAttach(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/platinumAttach";
	}
	
	@RequestMapping(value="purchaseItem/jadeiteAttach")
	public String jadeiteAttach(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/jadeiteAttach";
	}

	@RequestMapping(value="purchaseItem/goldUpdAttach")
	public String goldUpdAttach(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/goldUpdAttach";
	}
	
	@RequestMapping(value="purchaseItem/platinumUpdAttach")
	public String platinumUpdAttach(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/platinumUpdAttach";
	}
	
	@RequestMapping(value="purchaseItem/jadeiteUpdAttach")
	public String jadeiteUpdAttach(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "purchaseItem/jadeiteUpdAttach";
	}
	
	@RequestMapping(value="sale/list")
	public String saleList(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "sale/list";
	}
	
	@RequestMapping(value="sale/listTable")
	public String saleListData(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "sale/listTable";
	}
	
	@RequestMapping(value="member/list")
	public String memberList(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "member/list";
	}
	
	@RequestMapping(value="member/listTable")
	public String memberListData(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		return "member/listTable";
	}
	
}
