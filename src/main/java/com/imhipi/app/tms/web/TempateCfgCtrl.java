package com.imhipi.app.tms.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imhipi.app.tms.component.SystemService;

@Controller
@RequestMapping("templateCfg")
public class TempateCfgCtrl {
	
	@Autowired
	private SystemService sysService;
	
	@RequestMapping(value="config")
	public String getDefaultCfg(Model model) {
		Map cfgMap = sysService.getTemplateCfg();
		model.mergeAttributes(cfgMap);
		return "jsonView";
	}

}
