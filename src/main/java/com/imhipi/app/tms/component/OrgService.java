package com.imhipi.app.tms.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.imhipi.app.tms.model.Dictionary;
import com.imhipi.app.tms.model.Organization;
import com.imhipi.app.tms.service.GenericManager;

@Component
public class OrgService {
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager genericManager;
	
	private Map<Long, List<Organization>> orgMap = new HashMap<Long, List<Organization>>();

	@PostConstruct
	public void init() {
		List<Organization> orgs = genericManager.findMulti(new Organization());
		Organization rootOrg = new  Organization();
		rootOrg.setParentId(0l);
		List<Organization> treeOrgs = buildOrg(rootOrg, orgs);
	}
	
	private List<Organization> buildOrg(Organization parent, List<Organization> orgs) {
		List<Organization> children = getChildren(parent.getId(), orgs);
		if(!children.isEmpty()) {
			orgMap.put(parent.getId(), children);
		}
		return children;
	}
	
	private List<Organization> getChildren(Long parentId, List<Organization> orgs) {
		List<Organization> children = new ArrayList<Organization>();
		for(Organization org : orgs) {
			if(org.getParentId().intValue() == parentId.intValue()) {
				children.add(org);
			}
		}
		return children;
	}

}
