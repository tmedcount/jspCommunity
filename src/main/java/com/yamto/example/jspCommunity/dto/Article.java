package com.yamto.example.jspCommunity.dto;

import java.util.Map;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public int memberId;
	public int boardId;
	public String title;
	public String body;
	public int hitsCount;
	
	public String extra__writer;
	public String extra__boardName;
	public String extra__boardCode;
	
	public Article(Map<String, Object> map) {
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.memberId = (int)map.get("memberId");
		this.boardId = (int)map.get("boardId");
		this.title = (String)map.get("title");
		this.body = (String)map.get("body");
		this.hitsCount = (int)map.get("hitsCount");
		
		if(map.containsKey("extra__writer")) {
			this.extra__writer = (String)map.get("extra__writer");
		}
		if(map.containsKey("extra__boardName")) {
			this.extra__boardName = (String)map.get("extra__boardName");
		}
		if(map.containsKey("extra__boardCode")) {
			this.extra__boardCode = (String)map.get("extra__boardCode");
		}
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", memberId=" + memberId
				+ ", boardId=" + boardId + ", title=" + title + ", body=" + body + ", hitsCount=" + hitsCount
				+ ", extra__writer=" + extra__writer + ", extra__boardName=" + extra__boardName + ", extra__boardCode="
				+ extra__boardCode + "]";
	}
}
