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
	
	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개를 생성했습니다");
		for (int i = 1; i <= 3; i++) {
			members.add(new Member(++memberId, Util.getDateStr(), Util.getDateStr(), "test" + i, "test" + i, "유저" + i));
		}
	}

}
