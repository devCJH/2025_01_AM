package com.koreaIT.JAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.JAM.dto.Member;
import com.koreaIT.JAM.util.Util;

public class MemberDao {

	private List<Member> members;
	private int memberId;
	
	public MemberDao() {
		this.members = new ArrayList<>();
		this.memberId = 0;
	}
	
	public boolean isLoginIdDupCheck(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return true;
			}
		}
		return false;
	}

	public void joinMember(String loginId, String loginPw, String name) {
		memberId++;
		Member member = new Member(memberId, Util.getDateStr(), Util.getDateStr(), loginId, loginPw, name);
		members.add(member);
	}
}
