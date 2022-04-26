<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="아이디 찾기"></c:set>
<%@ include file="../../part/head.jspf" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
	<h1>${pageTitle}</h1>
		<div>
			<script>
				let doFindLoginIdForm__submited = false;
				function doFindLoginIdForm__submit(form) {
					if(doFindLoginIdForm__submited) {
						alert("처리 중 입니다.");
						return;
					}
					
					form.name.value = form.name.value.trim(); 
					
					if(form.name.value.length == 0) {
						alert("이름을 입력해 주세요.");
						form.name.focus();
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
					doFindLoginIdForm__submited = true;
				}
			</script>
			<form action="doFindLoginId" method="post" onsubmit="doFindLoginIdForm__submit(this); return false;">
				<input type="hidden" name="loginPwReal" />
				<hr />
				<div>
					<div>이름</div>
					<div><input type="text" name="name" maxlength="50" placeholder="이름을 입력해 주세요."/></div>
				</div>
				<hr />
				<div>
					<div>이메일</div>
					<div><input type="email" name="email" maxlength="50" placeholder="가입 시 입력한 이메일을 입력해 주세요."/></div>
				</div>
				<hr />
					<div>아이디 찾기</div>
					<div>
						<input type="submit" value="아이디 찾기" />
						<button type="button" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
			</form>
		</div>
<%@ include file="../../part/foot.jspf" %>