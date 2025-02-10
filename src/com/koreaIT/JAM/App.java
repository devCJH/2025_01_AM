package com.koreaIT.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.JAM.controller.MemberController;
import com.koreaIT.JAM.dto.Article;
import com.koreaIT.JAM.util.Util;

public class App {
	
	private List<Article> articles;
	private int articleId;
	
	public App() {
		articles = new ArrayList<>();
		articleId = 0;
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");
		
		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			}
			
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			if (cmd.equals("member join")) {
				memberController.doJoin();
			} else if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				articleId++;
				
				Article article = new Article(articleId, Util.getDateStr(), Util.getDateStr(), title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다\n", articleId);
			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				}
				
				System.out.println("번호	|	제목	|	작성일");
				
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s	|%s\n", article.getId(), article.getTitle(), article.getUpdateDate());
				}
			} else if (cmd.startsWith("article detail ")) {
				
				int id = getCmdNum(cmd);

				if (id == -1) {
					System.out.println("잘못된 명령어 입니다. 다시 입력하세요");
					continue;
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				
				System.out.println("== 게시물 상세보기 ==");
				System.out.printf("번호 : %d\n", foundArticle.getId());
				System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
				System.out.printf("수정일 : %s\n", foundArticle.getUpdateDate());
				System.out.printf("제목 : %s\n", foundArticle.getTitle());
				System.out.printf("내용 : %s\n", foundArticle.getBody());
			} else if (cmd.startsWith("article modify ")) {
				
				int id = getCmdNum(cmd);

				if (id == -1) {
					System.out.println("잘못된 명령어 입니다. 다시 입력하세요");
					continue;
				}

				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.setTitle(title);
				foundArticle.setBody(body);
				foundArticle.setUpdateDate(Util.getDateStr());
				
				System.out.printf("%d번 게시물을 수정했습니다\n", id);
			} else if (cmd.startsWith("article delete ")) {
				
				int id = getCmdNum(cmd);

				if (id == -1) {
					System.out.println("잘못된 명령어 입니다. 다시 입력하세요");
					continue;
				}

				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				
				articles.remove(foundArticle);
				
				System.out.printf("%d번 게시물을 삭제했습니다\n", id);
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}
		
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");
	}
	
	private int getCmdNum(String cmd) {
		String[] cmdBits = cmd.split(" ");
		
		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		
		return null;
	}

	private void makeTestData() {
		System.out.println("테스트용 게시물 데이터 3개를 생성했습니다");
		for (int i = 1; i <= 3; i++) {
			articles.add(new Article(++articleId, Util.getDateStr(), Util.getDateStr(), "제목" + i, "내용" + i));
		}
	}
}
