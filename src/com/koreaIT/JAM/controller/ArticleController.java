package com.koreaIT.JAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.JAM.container.Container;
import com.koreaIT.JAM.dto.Article;
import com.koreaIT.JAM.service.ArticleService;
import com.koreaIT.JAM.service.MemberService;

public class ArticleController extends Controller {

	private ArticleService articleService;
	private MemberService memberService;
	private String cmd;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleService = Container.articleService;
		this.memberService = Container.memberService;
	}

	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
		}
	}
	
	private void doWrite() {
		if (loginedMember == null) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int id = articleService.writeArticle(loginedMember.getId(), title, body);

		System.out.printf("%d번 글이 생성되었습니다\n", id);
	}

	private void showList() {
		List<Article> articles = articleService.getArticles();
		
		if (articles.size() == 0) {
			System.out.println("게시물이 존재하지 않습니다");
			return;
		}
		
	System.out.println("번호	|	제목	|	작성일	|	작성자");
		
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			
			String writerName = memberService.getWriterName(article.getMemberId());
			
			System.out.printf("%d	|	%s	|%s|	%s\n", article.getId(), article.getTitle(), article.getUpdateDate(), writerName);
		}
	}

	private void showDetail() {
		int id = articleService.getCmdNum(cmd);

		if (id == -1) {
			System.out.println("잘못된 명령어 입니다. 다시 입력하세요");
			return;
		}
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		String writerName = memberService.getWriterName(foundArticle.getMemberId());
		
		System.out.println("== 게시물 상세보기 ==");
		System.out.printf("번호 : %d\n", foundArticle.getId());
		System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
		System.out.printf("수정일 : %s\n", foundArticle.getUpdateDate());
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.getTitle());
		System.out.printf("내용 : %s\n", foundArticle.getBody());
	}

	private void doModify() {
		if (loginedMember == null) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		int id = articleService.getCmdNum(cmd);

		if (id == -1) {
			System.out.println("잘못된 명령어 입니다. 다시 입력하세요");
			return;
		}

		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		if (loginedMember.getId() != foundArticle.getMemberId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
			return;
		}
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();
		
		articleService.modifyArticle(foundArticle, title, body);
		
		System.out.printf("%d번 게시물을 수정했습니다\n", id);
	}
	
	private void doDelete() {
		if (loginedMember == null) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		int id = articleService.getCmdNum(cmd);

		if (id == -1) {
			System.out.println("잘못된 명령어 입니다. 다시 입력하세요");
			return;
		}

		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		if (loginedMember.getId() != foundArticle.getMemberId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
			return;
		}
		
		articleService.deleteArticle(foundArticle);
		
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);
	}

	@Override
	public void makeTestData() {
		System.out.println("테스트용 게시물 데이터 3개를 생성했습니다");
		for (int i = 1; i <= 3; i++) {
			articleService.writeArticle(i, "제목" + i, "내용" + i);
		}
	}
}
