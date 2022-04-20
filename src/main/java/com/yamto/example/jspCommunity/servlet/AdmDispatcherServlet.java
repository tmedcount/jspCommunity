package com.yamto.example.jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.controller.AdmMemberController;

// adm/member/list
@WebServlet("/adm/*")
public class AdmDispatcherServlet extends DispatcherServlet {
	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName, String actionMethodName) {
		String jspPath = null;
		
		if(controllerName.equals("member")) {
			AdmMemberController memberController = Container.admMemberController;
			
			if(actionMethodName.equals("list")) {
				jspPath = memberController.showList(req, resp);
			}
		}
		
		return jspPath;
	}
}
