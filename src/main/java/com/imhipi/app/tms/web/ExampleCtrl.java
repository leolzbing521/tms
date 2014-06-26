package com.imhipi.app.tms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imhipi.app.tms.component.SystemService;

@Controller
@RequestMapping("example")
public class ExampleCtrl extends BaseController {
	
	@Autowired
	private SystemService sysService;
	
	@RequestMapping(value="pageLoad")
	public String example(Model model) {
		model.addAttribute("name", "Michael");
		
		return "jsonView";
	}

}
