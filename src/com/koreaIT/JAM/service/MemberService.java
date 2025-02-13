package com.koreaIT.JAM.service;

import com.koreaIT.JAM.container.Container;
import com.koreaIT.JAM.dao.MemberDao;
import com.koreaIT.JAM.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	
	public boolean isLoginIdDupCheck(String loginId) {
		return memberDao.isLoginIdDupCheck(loginId);
	}

	public void joinMember(String loginId, String loginPw, String name) {
		memberDao.joinMember(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}
}
