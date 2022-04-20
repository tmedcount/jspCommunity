package com.yamto.example.jspCommunity.container;

import com.yamto.example.jspCommunity.controller.usr.ArticleController;
import com.yamto.example.jspCommunity.controller.usr.MemberController;
import com.yamto.example.jspCommunity.dao.ArticleDao;
import com.yamto.example.jspCommunity.dao.MemberDao;
import com.yamto.example.jspCommunity.service.ArticleService;
import com.yamto.example.jspCommunity.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	public static ArticleController articleController;

	public static MemberDao memberDao;
	public static MemberService memberService;
	public static MemberController memberController;
	public static com.yamto.example.jspCommunity.controller.adm.MemberController admMemberController;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();

		memberService = new MemberService();
		articleService = new ArticleService();
		
		memberController = new MemberController();
		admMemberController = new com.yamto.example.jspCommunity.controller.adm.MemberController();
		articleController = new ArticleController();
	}
}
