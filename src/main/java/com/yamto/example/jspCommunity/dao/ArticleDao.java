package com.yamto.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yamto.example.jspCommunity.dto.Article;
import com.yamto.example.mysqlutil.MysqlUtil;
import com.yamto.example.mysqlutil.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticleByBoardId(int boardId) {
		List<Article> articles = new ArrayList<>();		
		
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		if(boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}
		sql.append("ORDER BY A.id DESC");
						
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);
		
		// System.out.println(sql.getRawSql());
		
		for(Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}
	
		return articles;
	}

}