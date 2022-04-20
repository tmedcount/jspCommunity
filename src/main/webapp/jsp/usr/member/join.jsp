<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원가입"></c:set>
<%@ include file="../../part/head.jspf" %>
	<h1>${pageTitle}</h1>
		<div>
			<form action="doJoin" method="post">
				<hr />
				<div>
					<div>로그인 아이디</div>
					<div><input type="text" name="loginId" maxlength="50" placeholder="아이디를 입력해 주세요."/></div>
				</div>
				<hr />
				<div>
					<div>로그인 비밀번호</div>
					<div><input type="password" name="loginPw" maxlength="50" placeholder="비밀번호를 입력해 주세요."/></div>
				</div>
				<hr />
				<div>
					<div>이름</div>
					<div><input type="text" name="name" maxlength="50" placeholder="이름을 입력해 주세요."/></div>
				</div>
				<hr />
				<div>
					<div>별명</div>
					<div><input type="text" name="nickname" maxlength="50" placeholder="별명을 입력해 주세요."/></div>
				</div>
				<hr />
				<div>
					<div>이메일</div>
					<div><input type="email" name="email" maxlength="100" placeholder="이메일을 입력해 주세요."/></div>
				</div>
				<hr />
				<div>
					<div>가입</div>
					<div>
						<input type="submit" value="가입" />
						<button type="button" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
			</form>
		</div>
<%@ include file="../../part/foot.jspf" %>