<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yamto.example.jspCommunity.dto.Board"%>
<%@ page import="com.yamto.example.jspCommunity.dto.Article"%>
<%
Board board = (Board)request.getAttribute("board");
String pageTitle = board.getName() + " 게시물 수정페이지";
%>
<%@ include file="../../part/head.jspf" %>
	<h1><%=pageTitle%></h1>
		<div>
			<form action="doModify" method="post">
				<input type="hidden" name="id" value="${article.id}" />
				<input type="hidden" name="memberId" value="1" />
				<hr />
				<div>
					<div>제목</div>
					<div><input type="text" name="title" maxlength="50" placeholder="제목을 입력해 주세요." value="${article.title}"/></div>
				</div>
				<hr />
				<div>
					<div>내용</div>
					<div><textarea name="body" maxlength="5000" placeholder="내용을 입력해 주세요.">${article.body}</textarea></div>
				</div>
				<hr />
				<div>
					<div>수정</div>
					<div>
						<input type="submit" value="수정" />
						<button type="button" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
			</form>
		</div>
<%@ include file="../../part/foot.jspf" %>