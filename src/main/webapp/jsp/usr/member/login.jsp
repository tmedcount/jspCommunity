<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="로그인"></c:set>
<%@ include file="../../part/head.jspf" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
	<h1>${pageTitle}</h1>
		<div>
			<script>
				let doLoginForm__submited = false;
				function doLoginForm__submit(form) {
					if(doLoginForm__submited) {
						alert("처리 중 입니다.");
						return;
					}
					
					form.loginId.value = form.loginId.value.trim(); 
					
					if(form.loginId.value.length == 0) {
						alert("아이디를 입력해 주세요.");
						form.loginId.focus();
						return;
					}
					
					form.loginPw.value = form.loginPw.value.trim(); 
					
					if(form.loginPw.value.length == 0) {
						alert("비밀번호를 입력해 주세요.");
						form.loginPw.focus();
						return;
					}
					
					form.loginPwReal.value = sha256(form.loginPw.value);
					form.loginPw.value = "";

					form.submit();
					doLoginForm__submited = true;
				}
			</script>
			<form action="doLogin" method="post" onsubmit="doLoginForm__submit(this); return false;">
				<input type="hidden" name="loginPwReal" />
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
					<div>로그인</div>
					<div>
						<input type="submit" value="로그인" />
						<button type="button" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
			</form>
		</div>
<%@ include file="../../part/foot.jspf" %>