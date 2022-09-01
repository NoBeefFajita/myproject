<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShaMP</title>
    <link href="../../img/x-icon.png" rel="shortcut icon" type="image/x-icon">
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<main>
	    <section class="section up">
	        <article class="logo">
	            <img id="logo" src="../../img/ShaMP.png" alt="logo" width="250px">
	        </article>
	        
	        <article class="login">
	            <form action="" method="post">
					<dl>
						<dt>
							<input type="email" name="email" placeholder="이메일 주소">
						</dt>
						<dd>
							<div class="check">
								<img id="email_false_img" src="../../img/false.svg" alt="false" width="30px" hidden>
								<img id="email_pass_img" src="../../img/hook.svg" alt="false" width="30px" hidden>
							</div>
						</dd>
					</dl>
					<dl>
						<dt>
							<input name="full_name" placeholder="성명"> <br>
						</dt>
						<dd>
							<div class="check">
								<img id="full_pass_img" src="../../img/hook.svg" alt="false" width="30px" hidden>
							</div>
						</dd>
					</dl>
					<dl>
						<dt>
							<input name="user_name" placeholder="사용자 이름"> <br>
						</dt>
						<dd>
							<div class="check">
								<img id="user_false_img" src="../../img/false.svg" alt="false" width="30px" hidden>
								<img id="user_pass_img" src="../../img/hook.svg" alt="false" width="30px" hidden>
							</div>
						</dd>
					</dl>
					<dl>
						<dt>
							<input type="password" name="pw" placeholder="비밀번호"> <br>
						</dt>
						<dd>
							<div class="check">
								<img id="pw_false_img" src="../../img/false.svg" alt="false" width="30px" hidden>
								<img id="pw_pass_img" src="../../img/hook.svg" alt="false" width="30px" hidden>
							</div>
						</dd>
					</dl>
	                <button type="button" id="sign_up_btn" disabled>가입</button>
	            </form>
	        </article>
	    </section>

		<section class="section login">
			<div>기존 계정으로 <a href="../login/index.jsp">로그인</a></div>
		</section>
	</main>
	
	<footer>
	    본 페이지는 학업용으로 만들어진 페이지입니다. ©Jun
	</footer>
	
	<script src="js/script.js"></script>
</body>

</html>