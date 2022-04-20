<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원가입"></c:set>
<%@ include file="../../part/head.jspf" %>
	<h1>${pageTitle}</h1>
		<div>
			<script>
				let doJoinForm__submited = false;
				function doJoinForm__submit(form) {
					if(doJoinForm__submited) {
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
					
					form.loginPwConfirm.value = form.loginPwConfirm.value.trim(); 
					
					if(form.loginPwConfirm.value.length == 0) {
						alert("비밀번호 확인을 입력해 주세요.");
						form.loginPwConfirm.focus();
						return;
					}
					
					if(form.loginPw.value != form.loginPwConfirm.value) {
						alert("비밀번호가 일치하지 않습니다.")
						form.loginPwConfirm.focus();
						return;
					}
					
					form.name.value = form.name.value.trim(); 
					
					if(form.name.value.length == 0) {
						alert("이름을 입력해 주세요.");
						form.name.focus();
						return;
					}
					
					form.nickname.value = form.nickname.value.trim(); 
					
					if(form.nickname.value.length == 0) {
						alert("별명을 입력해 주세요.");
						form.nickname.focus();
						return;
					}
					
					form.email.value = form.email.value.trim(); 
					
					if(form.email.value.length == 0) {
						alert("이메일을 입력해 주세요.");
						form.email.focus();
						return;
					}
					
					form.submit();
					doJoinForm__submited = true;
				}
			</script>
			<form action="doJoin" method="post" onsubmit="doJoinForm__submit(this); return false;">
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
					<div>로그인 비밀번호 확인</div>
					<div><input type="password" name="loginPwConfirm" maxlength="50" placeholder="비밀번호 확인을 입력해 주세요."/></div>
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