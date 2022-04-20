<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원리스트"></c:set>
<%@ include file="../../part/head.jspf" %>
	<h1>${pageTitle}</h1>
	<c:forEach items="${members}" var="member">
			<div>
				번호 : ${member.id}
				<br />
				이름 : ${member.name}
				<br />
				닉네임 : ${member.nickname}
				<hr />
			</div>
	</c:forEach>
<%@ include file="../../part/foot.jspf" %>