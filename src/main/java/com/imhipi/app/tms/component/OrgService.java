package com.imhipi.app.tms.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.imhipi.app.tms.model.Organization;
import com.imhipi.app.tms.service.GenericManager;

@Component
public class OrgService {
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager genericManager;
	
	private List<Organization> rootOrgs = new ArrayList<Organization>();
	
	@PostConstruct
	public void init() {
		List<Organization> orgs = genericManager.findMulti(new Organization());
		Organization rootOrg = new  Organization();
		rootOrg.setId(0l);
		List<Organization> treeOrgs = buildOrg(rootOrg, orgs);
		for(Organization org : treeOrgs) {
			rootOrgs.add(org);
		}
	}
	
	private List<Organization> buildOrg(Organization parent, List<Organization> orgs) {
		List<Organization> children = getChildren(parent.getId(), orgs);
		if(children.size() > 0) {
			parent.setChildren(children);
			for(Organization org : children) {
				buildOrg(org, orgs);
			}
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
