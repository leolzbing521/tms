package com.imhipi.app.tms.service;

import com.imhipi.app.tms.model.User;


public interface MemberService {
	
	public boolean sell(User user, Long purchaseItemId, Long memberId);

}
