package com.koreaIT.JAM.service;

import com.koreaIT.JAM.dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = new MemberDao();
	}
	
	public boolean isLoginIdDupCheck(String loginId) {
		return memberDao.isLoginIdDupCheck(loginId);
	}

	public void joinMember(String loginId, String loginPw, String name) {
		memberDao.joinMember(loginId, loginPw, name);
	}

}
