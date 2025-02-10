package com.koreaIT.JAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.JAM.dto.Article;
import com.koreaIT.JAM.service.ArticleService;

public class ArticleController {

	private Scanner sc;
	private ArticleService articleService;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleService = new ArticleService();
		this.articleService.makeTestData();
	}

	public void doWrite() {
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int id = articleService.writeArticle(title, body);

		System.out.printf("%d번 글이 생성되었습니다\n", id);
	}

	public void showList() {
		List<Article> articles = articleService.getArticles();
		
		if (articles.size() == 0) {
			System.out.println("게시물이 존재하지 않습니다");
			return;
		}
		
		System.out.println("번호	|	제목	|	작성일");
		
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			System.out.printf("%d	|	%s	|%s\n", article.getId(), article.getTitle(), article.getUpdateDate());
		}
	}

	public void showDetail(String cmd) {
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
		
		System.out.println("== 게시물 상세보기 ==");
		System.out.printf("번호 : %d\n", foundArticle.getId());
		System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
		System.out.printf("수정일 : %s\n", foundArticle.getUpdateDate());
		System.out.printf("제목 : %s\n", foundArticle.getTitle());
		System.out.printf("내용 : %s\n", foundArticle.getBody());
	}

	public void doModify(String cmd) {

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
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();
		
		articleService.modifyArticle(foundArticle, title, body);
		
		System.out.printf("%d번 게시물을 수정했습니다\n", id);
	}

	public void doDelete(String cmd) {

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
		
		articleService.deleteArticle(foundArticle);
		
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);
	}
}
