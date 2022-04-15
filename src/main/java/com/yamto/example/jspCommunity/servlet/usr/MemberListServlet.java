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
import com.yamto.example.jspCommunity.dto.Member;
import com.yamto.example.jspCommunity.service.MemberService;
import com.yamto.example.mysqlutil.MysqlUtil;

@WebServlet("/usr/member/list")
public class MemberListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		MemberService memberService = Container.memberService;
		
		List<Member> members = memberService.getForPrintMembers();
		
		MysqlUtil.closeConnection();
		
		req.setAttribute("members", members);
		
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/usr/member/list.jsp");
		rd.forward(req, resp);
	}
}
