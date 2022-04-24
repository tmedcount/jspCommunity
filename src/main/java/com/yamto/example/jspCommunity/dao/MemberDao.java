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

	public int join(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO member");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", args.get("loginId"));
		sql.append(", loginPw = ?", args.get("loginPw"));
		sql.append(", `name` = ?", args.get("name"));
		sql.append(", nickname = ?", args.get("nickname"));
		sql.append(", email = ?", args.get("email"));
		sql.append(", cellphoneNo = ?", args.get("cellphoneNo"));
		
		return MysqlUtil.insert(sql);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("WHERE loginId = ?", loginId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.isEmpty()) {
			return null;
		}
		
		return new Member(map);		
	}

	public Member getMemberById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("WHERE id = ?", id);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.isEmpty()) {
			return null;
		}
		
		return new Member(map);	
	}
}
