<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${board.name} 게시물 리스트"></c:set>
<%@ include file="../../part/head.jspf" %>
	<h1>${pageTitle}</h1>
	
	<div>
		<a href="write?boardId=${param.boardId}">게시물 작성</a>
	</div>

	<hr />
	
	<div>
		<script>
			let doSearchForm__submited = false;
			function doSearchForm__submit(form) {
				if(doSearchForm__submited) {
					alert("처리 중입니다.");
					return;
				}
				
				form.searchKeyWord.value = form.searchKeyWord.value.trim();
				
				if(form.searchKeyWord.value.length == 0) {
					alert("검색어를 입력해 주세요.");
					form.searchKeyWord.focus();
					return;
				}
				
				form.submit();
				doSearchForm__submited = true;
			}			
		</script>
		<form action="" onsubmit="doSearchForm__submit(this); return false;">
			<input type="hidden" name="boardId" value="${param.boardId }" />
			
			<select name="searchKeyWordType">
				<option value="title">제목</option>
				<option value="body">본문</option>
			</select>
			<script>
				const param__searchKeyWordType = '${param.searchKeyWordType}';
				
				if(param__searchKeyWordType) {
					$('select[name="searchKeyWordType"]').val(param__searchKeyWordType);
				}
			</script>
			<input value="${param.searchKeyWord }" type="text" name="searchKeyWord" placeholder="검색어를 입력해 주세요." />
			<input type="submit" value="검색" />
		</form>
	</div>
	
	<hr />
	
	<div>
		총 게시물 수 : ${totalCount}
	</div>
	
	<hr />
	
	<c:forEach var="article" items="${articles}">
			<div>
				번호 : ${article.id}
				<br />
				작성날짜 :${article.regDate}
				<br />
				갱신날짜 : ${article.updateDate}
				<br />
				작성자 : ${article.extra__writer}
				<br />
				제목 : <a href="detail?id=${article.id}">${article.title}</a>
				<hr />
			</div>
	</c:forEach>
<%@ include file="../../part/foot.jspf" %>