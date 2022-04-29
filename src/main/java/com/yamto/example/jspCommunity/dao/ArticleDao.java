package com.yamto.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yamto.example.jspCommunity.dto.Article;
import com.yamto.example.jspCommunity.dto.Board;
import com.yamto.example.mysqlutil.MysqlUtil;
import com.yamto.example.mysqlutil.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeyWord, String searchKeyWordType) {
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
		
		if(searchKeyWord != null) {
			if(searchKeyWordType == null || searchKeyWordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyWord);
			} else if(searchKeyWordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyWord);
			} else if(searchKeyWordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyWord, searchKeyWord);
			}
		}
		
		sql.append("ORDER BY A.id DESC");
		
		if(limitCount != -1) {
			sql.append("LIMIT ?, ?", limitStart, limitCount);
		}
						
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);
				
		for(Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}
	
		return articles;
	}

	public Article getForPrintArticleById(int id) {
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
		sql.append("WHERE A.id = ?", id);
						
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.isEmpty()) {
			return null;
		}
		
		return new Article(map);
	}

	public Board getBoardById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT B.*");
		sql.append("FROM board AS B");
		sql.append("WHERE B.id = ?", id);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.isEmpty()) {
			return null;
		}

		return new Board(map);
	}

	public int write(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", boardId = ?", args.get("boardId"));
		sql.append(", memberId = ?", args.get("memberId"));
		sql.append(", title = ?", args.get("title"));
		sql.append(", body = ?", args.get("body"));
		
		return MysqlUtil.insert(sql);
	}

	public int delete(int id) {
		SecSql sql = new SecSql();
		sql.append("Delete from article");
		sql.append("WHERE id = ?", id);
		
		return MysqlUtil.delete(sql);
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		
		boolean needToUpdate = false;
		
		if(args.get("title") != null) {
			needToUpdate = true;
			sql.append(", title = ?", args.get("title"));
		}
		if(args.get("body") != null) {
			needToUpdate = true;
			sql.append(", body = ?", args.get("body"));
		}
		
		if(needToUpdate == false) {
			return 0;
		}
		
		sql.append("WHERE id = ?", args.get("id"));
		
		return MysqlUtil.update(sql);
	}

	public int getArticlesCountByBoardId(int boardId, String searchKeyWord, String searchKeyWordType) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");
		
		if(boardId != 0) {
			sql.append("AND boardId = ?", boardId);
		}
		
		if(searchKeyWord != null) {
			if(searchKeyWordType == null || searchKeyWordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyWord);
			} else if(searchKeyWordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyWord);
			} else if(searchKeyWordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyWord, searchKeyWord);
			}
		}
					
		return MysqlUtil.selectRowIntValue(sql);
	}
}
