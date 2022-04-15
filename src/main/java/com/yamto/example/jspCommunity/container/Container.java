package com.yamto.example.jspCommunity.container;

import com.yamto.example.jspCommunity.dao.ArticleDao;
import com.yamto.example.jspCommunity.service.ArticleService;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	
	static {
		articleDao = new ArticleDao();
		articleService = new ArticleService();
	}
}
