<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="비밀번호 찾기"></c:set>
<%@ include file="../../part/head.jspf" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
	<h1>${pageTitle}</h1>
		<div>
			<script>
				let doFindLoginPwForm__submited = false;
				function doFindLoginPwForm__submit(form) {
					if(doFindLoginPwForm__submited) {
						alert("처리 중 입니다.");
						return;
					}
					
					form.loginId.value = form.loginId.value.trim(); 
					
					if(form.loginId.value.length == 0) {
						alert("아이디를 입력해 주세요.");
						form.loginId.focus();
						return;
					}
					
					form.email.value = form.email.value.trim(); 
					
					if(form.email.value.length == 0) {
						alert("이메일을 입력해 주세요.");
						form.email.focus();
						return;
					}
					
					//form.loginPwReal.value = sha256(form.loginPw.value);
					//form.loginPw.value = "";

					form.submit();
					doFindLoginPwForm__submited = true;
				}
			</script>
			<form action="doFindLoginPw" method="post" onsubmit="doFindLoginPwForm__submit(this); return false;">
				<input type="hidden" name="loginPwReal" />
				<hr />
				<div>
					<div>아이디</div>
					<div><input type="text" name="loginId" maxlength="50" placeholder="아이디를 입력해 주세요."/></div>
				</div>
				<hr />
				<div>
					<div>이메일</div>
					<div><input type="email" name="email" maxlength="50" placeholder="회원의 이메일을 입력해 주세요."/></div>
				</div>
				<hr />
					<div>비밀번호 찾기</div>
					<div>
						<input type="submit" value="비밀번호 찾기" />
						<button type="button" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
			</form>
		</div>
<%@ include file="../../part/foot.jspf" %>