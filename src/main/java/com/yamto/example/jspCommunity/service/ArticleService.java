package com.yamto.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

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

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}

	public int write(Map<String, Object> args) {
		return articleDao.write(args);
	}

	public int delete(int id) {
		return articleDao.delete(id);
	}

}
