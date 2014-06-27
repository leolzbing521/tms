package com.imhipi.app.tms.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.view.json.JsonWriterConfiguratorTemplateRegistry;
import org.springframework.web.servlet.view.json.writer.jsonlib.JsonlibJsonWriterConfiguratorTemplate;

import com.imhipi.app.tms.util.DateJsonValueProcessor;

@Controller
public class BaseController {

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		JsonWriterConfiguratorTemplateRegistry registry = JsonWriterConfiguratorTemplateRegistry
				.load(request);
		registry.registerConfiguratorTemplate(new JsonlibJsonWriterConfiguratorTemplate() {
			@Override
			public JsonConfig getJsonConfig() {
				JsonConfig config = new JsonConfig();
				config.setIgnoreDefaultExcludes(false);
				config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
				config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); 
				return config;
			}
		});
	}

}
