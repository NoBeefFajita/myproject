<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>ShaMP</title>
    <link href="../../img/x-icon.png" rel="shortcut icon" type="image/x-icon">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
	<main>
	    <section>
	        <article class="logo">
	            <img src="../../img/ShaMP.png" alt="logo" width="250px">
	        </article>
	        
	        <article class="login">
	            <form action="/myproject/signup_servlet/home.go" method="post">
	                <input name="id" placeholder="사용자 이름 또는 이메일"> <br>
	                <input type="password" name="pw" placeholder="비밀번호"> <br>
	                <button type="button" id="login_btn" disabled>로그인</button>
	            </form>
	        </article>
	
	        <article class="sign_up">
	            <div class="or">
	                <div class="or_line"></div>
	                <div>or</div>
	                <div class="or_line"></div>
	            </div>
	            <br>
	            <button id="sign_up_btn">가입하기</button>
	        </article>
	    </section>
	</main>
	
	<footer>
	    본 페이지는 학업용으로 만들어진 페이지입니다. ©Jun
	</footer>
	
	<script src="js/script.js"></script>
</body>

</html>