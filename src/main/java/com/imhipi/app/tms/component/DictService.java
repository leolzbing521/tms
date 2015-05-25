package com.imhipi.app.tms.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.imhipi.app.tms.enums.DictRootType;
import com.imhipi.app.tms.model.Dictionary;
import com.imhipi.app.tms.service.GenericManager;

@Component
public class DictService {
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager genericManager;
	
	private static Map<String, LinkedHashMap<String, Dictionary>> tDictMap = new HashMap<String, LinkedHashMap<String, Dictionary>>();


	@PostConstruct
	public void init() {
		List<Dictionary> typeDict = null;
		for(Dictionary dict : genericManager.findByNamedQuery("findRootDictGroupType", null, Dictionary.class)) {
			Dictionary searchDict = new Dictionary();
			searchDict.setType(dict.getType());
			typeDict = genericManager.findMulti(searchDict);
			if(null != typeDict) {
				LinkedHashMap<String, Dictionary> dictMap = new LinkedHashMap<String, Dictionary>();
				for(Dictionary t : typeDict) {
					dictMap.put(t.getKey(), t);
				}
				tDictMap.put(dict.getType(), dictMap);
			}
		}
	}
	
	public static List<Dictionary> getDictsByType(DictRootType rootType) {
		String type = rootType.value();
		List<Dictionary> dicts = new ArrayList<Dictionary>();
		
		if(StringUtils.hasText(type) && tDictMap.containsKey(type)) {
			LinkedHashMap<String, Dictionary> dictMap = tDictMap.get(type);
			for(String key : dictMap.keySet()) {
				Dictionary dict = new Dictionary();
				dict.setId(dictMap.get(key).getId());
				dict.setKey(dictMap.get(key).getKey());
				dict.setName(dictMap.get(key).getName());
				dicts.add(dict);
			}
		}
		return dicts;
	}
	
	public static String getDictByTypeAndKey(DictRootType rootType, String key) {
		String type = rootType.value();
		if(!StringUtils.hasText(type) || !StringUtils.hasText(key) || tDictMap == null)
			return null;
			
		try {
			return tDictMap.get(type).get(key).getName();
		} catch (Exception e) {
			return "";
		}
	}
	
}
