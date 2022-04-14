package com.yamto.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yamto.example.mysqlutil.MysqlUtil;
import com.yamto.example.mysqlutil.SecSql;

@WebServlet("/usr/member/list")
public class MemberListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM member ORDER BY ID DESC");
		
		List<Map<String, Object>> memberMapList = MysqlUtil.selectRows(sql);
		
		System.out.println(memberMapList);
		
		MysqlUtil.closeConnection();
		
		req.setAttribute("memberMapList", memberMapList);
		
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/usr/member/list.jsp");
		rd.forward(req, resp);
	}
}
