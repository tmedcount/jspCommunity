package com.yamto.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dto.Member;
import com.yamto.example.jspCommunity.service.MemberService;
import com.yamto.example.util.Util;

public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해 주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해 주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
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

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해 주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		return "usr/member/login";
	}
	
	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "이미 로그아웃 상태입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		session.removeAttribute("loginedMemberId");
		
		req.setAttribute("alertMsg", "로그아웃 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		
		return "common/redirect";
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해 주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if(member.getLoginPw().equals(loginPw) == false) {
			req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		session.setAttribute("loginedMemberId", member.getId());

		req.setAttribute("alertMsg", String.format("%s님 환영합니다!", member.getNickname()));
		req.setAttribute("replaceUrl", "../home/main");
		
		return "common/redirect";
	}

	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		Map<String, Object> rs = new HashMap<>();
		
		String resultCode = null;
		String msg = null;
		
		if(member != null) {
			resultCode = "F-1";
			msg = "이미 사용 중인 아이디입니다.";
		} else {
			resultCode = "S-1";
			msg = "사용 가능한 아이디입니다.";
		}
		
		rs.put("resultCode", resultCode);
		rs.put("msg", msg);
		rs.put("loginId", loginId);
				
		req.setAttribute("data", Util.getJsonText(rs));
		
		return "common/pure";
	}
}
