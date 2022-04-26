package com.yamto.example.jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yamto.example.jspCommunity.App;
import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dao.MemberDao;
import com.yamto.example.jspCommunity.dto.Member;
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

	public void sendTempLoginPwToEmail(Member actor) {
		// 메일 제목과 내용 만들기
		String siteName = App.getSite();
		String siteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인 하러가기</a>";
		
		// 메일 발송
		emailService.send(actor.getEmail(), title, body);
		
		// 회원의 비빌먼호를 임시 비밀번호로 변경
		setTempPassword(actor, tempPassword);
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
