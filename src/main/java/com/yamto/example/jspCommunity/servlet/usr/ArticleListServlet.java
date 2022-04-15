package com.yamto.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dto.Article;
import com.yamto.example.jspCommunity.service.ArticleService;
import com.yamto.example.mysqlutil.MysqlUtil;

@WebServlet("/usr/article/list")
public class ArticleListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		if(req.getParameter("boardId") == null) {
			resp.getWriter().append("boardId를 입력해 주세요.");
			return;
		}
		
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		ArticleService articleService = Container.articleService;
		List<Article> articles = articleService.getForPrintArticleByBoardId(boardId);
		
		MysqlUtil.closeConnection();

		// System.out.println(articles);
		
		req.setAttribute("articles", articles);
		
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/usr/article/list.jsp");
		rd.forward(req, resp);
	}
}
