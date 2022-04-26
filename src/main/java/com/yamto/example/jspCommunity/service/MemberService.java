package com.yamto.example.jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yamto.example.jspCommunity.App;
import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dao.MemberDao;
import com.yamto.example.jspCommunity.dto.Member;
import com.yamto.example.jspCommunity.dto.ResultData;
import com.yamto.example.util.Util;

public class MemberService {
	private MemberDao memberDao;
	private EmailService emailService;

	public MemberService() {
		memberDao = Container.memberDao;
		emailService = Container.emailService;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

	public int join(Map<String, Object> args) {
		return memberDao.join(args);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public ResultData sendTempLoginPwToEmail(Member actor) {
		// 메일 제목과 내용 만들기
		String siteName = App.getSite();
		String siteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인 하러가기</a>";
		
		Map<String, Object> rs = new HashMap<>();
				
		// 메일 발송
		int sendRs = emailService.send(actor.getEmail(), title, body);
		
		if(sendRs != 1) {
			return new ResultData("F-1", "메일 발송에 실패하였습니다.");
		}
			
		setTempPassword(actor, tempPassword);
		
		String resultMsg = String.format("회원님의 임시 비밀번호가 %s(으)로 발송되었습니다.", actor.getEmail());
		return new ResultData("S-1", resultMsg, "email", actor.getEmail());
	}

	private void setTempPassword(Member actor, String tempPassword) {
		Map<String, Object> modifyParam = new HashMap<>();
		modifyParam.put("id", actor.getId());
		modifyParam.put("loginPw", Util.sha256(tempPassword));
		modify(modifyParam);
	}

	private int modify(Map<String, Object> args) {
		return memberDao.modify(args);
	}

}
