package com.koreaIT.JAM.service;

import java.util.List;

import com.koreaIT.JAM.container.Container;
import com.koreaIT.JAM.dao.ArticleDao;
import com.koreaIT.JAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public int writeArticle(int id, String title, String body) {
		return articleDao.writeArticle(id, title, body);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void modifyArticle(Article foundArticle, String title, String body) {
		articleDao.modifyArticle(foundArticle, title, body);
	}

	public void deleteArticle(Article foundArticle) {
		articleDao.deleteArticle(foundArticle);
	}
	
	public int getCmdNum(String cmd) {
		String[] cmdBits = cmd.split(" ");
		
		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
