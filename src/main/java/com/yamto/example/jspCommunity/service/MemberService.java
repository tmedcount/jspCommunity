package com.yamto.example.jspCommunity.service;

import java.util.List;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dao.MemberDao;
import com.yamto.example.jspCommunity.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

}
