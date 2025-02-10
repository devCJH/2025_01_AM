package com.koreaIT.JAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.JAM.dto.Article;
import com.koreaIT.JAM.util.Util;

public class ArticleDao {

	private List<Article> articles;
	private int articleId;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
		this.articleId = 0;
	}

	public int writeArticle(String title, String body) {
		articleId++;
		Article article = new Article(articleId, Util.getDateStr(), Util.getDateStr(), title, body);
		articles.add(article);
		return articleId;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		return null;
	}

	public void modifyArticle(Article foundArticle, String title, String body) {
		foundArticle.setTitle(title);
		foundArticle.setBody(body);
		foundArticle.setUpdateDate(Util.getDateStr());
	}

	public void deleteArticle(Article foundArticle) {
		articles.remove(foundArticle);
	}
	
	public void makeTestData() {
		System.out.println("테스트용 게시물 데이터 3개를 생성했습니다");
		for (int i = 1; i <= 3; i++) {
			articles.add(new Article(++articleId, Util.getDateStr(), Util.getDateStr(), "제목" + i, "내용" + i));
		}
	}

}
