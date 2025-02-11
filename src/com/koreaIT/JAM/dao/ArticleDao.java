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

	public int writeArticle(int id, String title, String body) {
		articleId++;
		Article article = new Article(articleId, Util.getDateStr(), Util.getDateStr(), id, title, body);
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
}
