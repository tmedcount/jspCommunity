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

	public int modify(Map<String, Object> args) {
		return articleDao.modify(args);
	}

	public int getArticlesCountByBoardId(int boardId, String searchKeyWord, String searchKeyWordType) {
		return articleDao.getArticlesCountByBoardId(boardId, searchKeyWord, searchKeyWordType);
	}
	
	public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeyword, String searchKeywordType) {
		return articleDao.getForPrintArticlesByBoardId(boardId, limitStart, limitCount, searchKeyword, searchKeywordType);
	}

}
