<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>member</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
    <h2>회원관리</h2> <br>
    코드 : <input id="code"> <br>
    아이디 : <input id="userid"> 
    비번 : <input type="password" id="pw"> <br>
    이름 : <input id="name"> 
    이메일 : <input id="email"> <br>
    관리자 : <input id="admin"> <br>
    <button type="button" id="btnSave">추가</button>

    <div id="memberList"></div>


    <script>
        $(function () {
            list();
            $("#btnSave").click(function () {
                insert();
            });
        });

        function list() {
            fetch("/myproject/member_servlet/list.do", {
                method: "post"
            })
                .then(response => response.text())
                .then(data => $("#memberList").html(data))
                .catch(e => console.log(e));
        }

        function insert() {
            let param = "userid=" + $("#userid").val()
                + "&pw=" + $("#pw").val()
                + "&name=" + $("#name").val()
                + "&email=" + $("#email").val()
                + "&admin=" + $("#admin").val()
                + "&joinDate=" + $("#joinDate").val();
            fetch("/myproject/member_servlet/join.do", {
                method: "post",
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: param
            })
                .then(() => {
                    // list();
                    document.getElementById("code").value = "";
                    document.getElementById("userid").value = "";
                    document.getElementById("pw").value = "";
                    document.getElementById("name").value = "";
                    document.getElementById("email").value = "";
                    document.getElementById("admin").value = "";
                    document.getElementById("joinDate").value = "";
                } )
                .catch(e => console.log(e));
        }
    </script>
</body>

</html>