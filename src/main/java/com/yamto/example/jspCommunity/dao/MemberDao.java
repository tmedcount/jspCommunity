package com.yamto.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yamto.example.jspCommunity.dto.Member;
import com.yamto.example.mysqlutil.MysqlUtil;
import com.yamto.example.mysqlutil.SecSql;

public class MemberDao {

	public List<Member> getForPrintMembers() {
		List<Member> members = new ArrayList<>();		
		
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("ORDER BY M.id DESC");
						
		List<Map<String, Object>> MemberMapList = MysqlUtil.selectRows(sql);		
		
		for(Map<String, Object> memberMap : MemberMapList) {
			members.add(new Member(memberMap));
		}
	
		return members;
	}

}
