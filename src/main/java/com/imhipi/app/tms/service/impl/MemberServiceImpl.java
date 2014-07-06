package com.imhipi.app.tms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imhipi.app.tms.model.Member;
import com.imhipi.app.tms.model.PurchaseItem;
import com.imhipi.app.tms.model.User;
import com.imhipi.app.tms.service.MemberService;

@Service("memberService")
public class MemberServiceImpl extends GenericManagerImpl implements
		MemberService {

	@Override
	@Transactional
	public boolean sell(User user, Long purchaseItemId, Long memberId) {
		PurchaseItem item = get(purchaseItemId, PurchaseItem.class);
		if(item != null && !item.getSell()) {
			item.setSell(true);
			item.setUuserId(user.getId());
			update(item);
			if(memberId != null && memberId != 0) {
				Member member = get(memberId, Member.class);
				if(member != null) {
					//member.setIntegral(0);
					member.setUuserId(user.getId());
					update(member);
				}
			}
			return true;
		}
		
		return false;
	}

}
