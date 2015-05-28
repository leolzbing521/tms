package com.imhipi.app.tms.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.imhipi.app.tms.model.Brand;
import com.imhipi.app.tms.service.GenericManager;

@Component
public class BrandService {
	
	@Autowired
	@Qualifier("genericManager")
	private GenericManager genericManager;
	
	private static List<Brand> rootOrgs = new ArrayList<Brand>();
	
	@PostConstruct
	public void init() {
		List<Brand> orgs = genericManager.findMulti(new Brand());
		Brand rootOrg = new  Brand();
		rootOrg.setId(0l);
		List<Brand> treeOrgs = buildOrg(rootOrg, orgs);
		for(Brand org : treeOrgs) {
			rootOrgs.add(org);
		}
	}
	
	private List<Brand> buildOrg(Brand parent, List<Brand> orgs) {
		List<Brand> children = getChildren(parent.getId(), orgs);
		if(children.size() > 0) {
			parent.setChildren(children);
			for(Brand org : children) {
				buildOrg(org, orgs);
			}
		}
		return children;
	}
	
	private List<Brand> getChildren(Long parentId, List<Brand> orgs) {
		List<Brand> children = new ArrayList<Brand>();
		for(Brand org : orgs) {
			if(org.getParentId().intValue() == parentId.intValue()) {
				children.add(org);
			}
		}
		return children;
	}
	
	public static List<Brand> getRootOrgs() {
		return rootOrgs;
	}

}
