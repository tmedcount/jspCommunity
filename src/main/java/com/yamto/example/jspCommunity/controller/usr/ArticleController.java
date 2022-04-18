package com.yamto.example.jspCommunity.controller.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dto.Article;
import com.yamto.example.jspCommunity.dto.Board;
import com.yamto.example.jspCommunity.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService; 
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		Board board = articleService.getBoardById(boardId);
		
		req.setAttribute("board", board);
		
		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId);
		
		req.setAttribute("articles", articles);
		
		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Article article = articleService.getForPrintArticleById(id);
		
		if(article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		req.setAttribute("article", article);
		
		return "usr/article/detail";
	}

	public String showWrite(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		Board board = articleService.getBoardById(boardId);
		
		req.setAttribute("board", board);
		
		return "usr/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		Map<String, Object> writeArgs = new HashMap<>();
		
		writeArgs.put("boardId", boardId);
		writeArgs.put("memberId", memberId);
		writeArgs.put("title", title);
		writeArgs.put("body", body);
		
		int newArticleId = articleService.write(writeArgs);
		
		req.setAttribute("alertMsg", newArticleId + "번 게시물을 등록하였습니다");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", newArticleId));
		
		return "common/redirect";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Article article = articleService.getForPrintArticleById(id);
		
		if(article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		articleService.delete(id);
		
		int boardId = article.boardId;
		
		req.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=%d", boardId));
		
		return "common/redirect";
	}
}
