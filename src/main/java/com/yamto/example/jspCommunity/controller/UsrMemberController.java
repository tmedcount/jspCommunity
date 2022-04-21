package com.yamto.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dto.Board;
import com.yamto.example.jspCommunity.dto.Member;
import com.yamto.example.jspCommunity.service.MemberService;

public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		List<Member> members = memberService.getForPrintMembers();
		
		req.setAttribute("members", members);
		
		return "usr/member/list";
	}
	
	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String cellphoneNo = req.getParameter("cellphoneNo");
		
		Member oldMember = memberService.getMemberByLoginId(loginId);
		
		if(oldMember != null) {
			req.setAttribute("alertMsg", "해당 로그인 아이디는 이미 사용 중입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		Map<String, Object> joinArgs = new HashMap<>();
		
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("name", name);
		joinArgs.put("nickname", nickname);
		joinArgs.put("email", email);
		joinArgs.put("cellphoneNo", cellphoneNo);
		
		int newMemberId = memberService.join(joinArgs);
		
		req.setAttribute("alertMsg", newMemberId + "번 회원이 생성되었습니다");
		req.setAttribute("replaceUrl", "join");
		
		return "common/redirect";
	}
}