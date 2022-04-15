package com.yamto.example.jspCommunity.service;

import java.util.List;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dao.ArticleDao;
import com.yamto.example.jspCommunity.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao; 
	}

	public List<Article> getForPrintArticleByBoardId(int boardId) {
		return articleDao.getForPrintArticleByBoardId(boardId);
	}

}
