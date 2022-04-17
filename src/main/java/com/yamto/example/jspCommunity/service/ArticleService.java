package com.yamto.example.jspCommunity.service;

import java.util.List;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dao.ArticleDao;
import com.yamto.example.jspCommunity.dto.Article;
import com.yamto.example.jspCommunity.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao; 
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		return articleDao.getForPrintArticlesByBoardId(boardId);
	}

	public Article getForPrintArticlesById(int id) {
		return articleDao.getForPrintArticlesById(id);
	}

	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}

}
