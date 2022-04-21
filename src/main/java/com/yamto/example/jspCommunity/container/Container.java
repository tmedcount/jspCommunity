package com.yamto.example.jspCommunity.container;

import com.yamto.example.jspCommunity.controller.AdmMemberController;
import com.yamto.example.jspCommunity.controller.UsrArticleController;
import com.yamto.example.jspCommunity.controller.UsrHomeController;
import com.yamto.example.jspCommunity.controller.UsrMemberController;
import com.yamto.example.jspCommunity.dao.ArticleDao;
import com.yamto.example.jspCommunity.dao.MemberDao;
import com.yamto.example.jspCommunity.service.ArticleService;
import com.yamto.example.jspCommunity.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	public static UsrArticleController articleController;

	public static MemberDao memberDao;
	public static MemberService memberService;
	public static UsrMemberController memberController;
	public static AdmMemberController admMemberController;
	public static UsrHomeController homeController;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();

		memberService = new MemberService();
		articleService = new ArticleService();
		
		memberController = new UsrMemberController();
		admMemberController = new AdmMemberController();
		articleController = new UsrArticleController();
		homeController = new UsrHomeController();
	}
}
