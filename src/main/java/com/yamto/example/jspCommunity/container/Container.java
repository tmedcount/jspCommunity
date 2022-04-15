package com.yamto.example.jspCommunity.container;

import com.yamto.example.jspCommunity.dao.ArticleDao;
import com.yamto.example.jspCommunity.dao.MemberDao;
import com.yamto.example.jspCommunity.service.ArticleService;
import com.yamto.example.jspCommunity.service.MemberService;


public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static MemberDao memberDao;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();

		memberService = new MemberService();
		articleService = new ArticleService();		
	}
}
