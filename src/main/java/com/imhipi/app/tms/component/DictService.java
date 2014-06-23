package com.imhipi.app.tms.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.imhipi.app.tms.enums.DictRootKey;
import com.imhipi.app.tms.model.Dictionary;
import com.imhipi.app.tms.service.GenericManager;

@Component
public class DictService {
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager genericManager;
	
	private static Map<String, Map<String, Dictionary>> tDictMap = new HashMap<String, Map<String, Dictionary>>();

	@PostConstruct
	public void init() {
		List<Dictionary> typeDict = null;
		for(Dictionary dict : genericManager.findMulti(new Dictionary(DictRootKey.ROOT.value()))) {
			Dictionary searchDict = new Dictionary();
			searchDict.setType(dict.getType());
			typeDict = genericManager.findMulti(searchDict);
			if(null != typeDict) {
				Map<String, Dictionary> dictMap = new HashMap<String, Dictionary>();
				for(Dictionary t : typeDict) {
					dictMap.put(t.getKey(), t);
				}
				tDictMap.put(dict.getType(), dictMap);
			}
		}
	}
	
	public static Map<String, String> getDictsByType(String type) {
		Map<String, String> keyValueMap = null;
		
		if(StringUtils.hasText(type)) {
			Map<String, Dictionary> dictMap = tDictMap.get(type);
			keyValueMap = new HashMap<String, String>();
			for(String key : dictMap.keySet()) {
				keyValueMap.put(key, dictMap.get(key).getName());
			}
		}
		return keyValueMap;
	}
	
	public static String getDictByTypeAndKey(String type, String key) {
		if(!StringUtils.hasText(type) || !StringUtils.hasText(key) || tDictMap == null)
			return null;
		return tDictMap.get(type).get(key).getName();
	}
	
}
