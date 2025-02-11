package com.koreaIT.JAM.controller;

import java.util.Scanner;

import com.koreaIT.JAM.dto.Member;
import com.koreaIT.JAM.service.MemberService;

public class MemberController extends Controller {

	private MemberService memberService;
	private Member loginedMember;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.memberService = new MemberService();
	}

	@Override
	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
		}
	}
	
	private void doJoin() {
		if (this.loginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;
		
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("필수입력 정보입니다");
				continue;
			}
			
			boolean isLoginIdDup = memberService.isLoginIdDupCheck(loginId);
			
			if (isLoginIdDup) {
				System.out.printf("[ %s ]은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			
			System.out.printf("[ %s ]은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}
		
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("필수입력 정보입니다");
				continue;
			}
			
			System.out.printf("비밀번호 확인 : ");
			loginPwChk = sc.nextLine().trim();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("필수입력 정보입니다");
				continue;
			}
			break;
		}
		
		memberService.joinMember(loginId, loginPw, name);
		
		System.out.println("회원가입이 완료되었습니다");
	}

	private void doLogin() {
		
		if (this.loginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		System.out.printf("아이디 : ");
		String loginId = sc.nextLine().trim();
		System.out.printf("비밀번호 : ");
		String loginPw = sc.nextLine().trim();
		
		if (loginId.length() == 0) {
			System.out.println("아이디를 입력해주세요");
			return;
		}
		
		if (loginPw.length() == 0) {
			System.out.println("비밀번호를 입력해주세요");
			return;
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			System.out.printf("[ %s ]은(는) 존재하지 않는 아이디입니다\n", loginId);
			return;
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}
		
		this.loginedMember = member;
		
		System.out.printf("[ %s ] 회원님 환영합니다~!\n", member.getName());
	}
	
	private void doLogout() {
		if (this.loginedMember == null) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		this.loginedMember = null;
		System.out.println("정상적으로 로그아웃 되었습니다");
	}
	
	@Override
	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개를 생성했습니다");
		for (int i = 1; i <= 3; i++) {
			memberService.joinMember("test" + i, "test" + i, "유저" + i);
		}
	}
}
