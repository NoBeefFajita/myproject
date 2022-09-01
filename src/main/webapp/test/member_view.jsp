<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDTO" %>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>member_view</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
    <% MemberDTO dto = (MemberDTO)request.getAttribute("dto"); %>
    <form name="form1" method="post">
        <table border="1">
            <tr>
                <td>아이디</td>
                <td>
                    <input name="userid" value="<%= dto.getUserid()%>" readonly>
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="pw" value="<%= dto.getPw() %>"></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input name="name" value="<%= dto.getName() %>"></td>
            </tr>
            <tr>
                <td>회원가입일자</td>
                <td>
                    <%= dto.getJoinDate() %>
                </td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input name="email" value="<%= dto.getEmail() %>"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="button" id="btnUpdate">수정</button>
                    <button type="button" id="btnDelete">삭제</button>
                </td>
            </tr>
    
    
    
        </table>
    
    </form>

    <script>
        $(function () {
            $("#btnUpdate").click(function () {
                //폼데이터를 제출할 주소(호출 url)
                document.form1.action = "/myproject/member_servlet/update.do";
                document.form1.submit();//폼데이터를 서버에 제출
            });
            $("#btnDelete").click(function () {
                if (confirm("삭제하시겠습니까?")) {
                    document.form1.action = "/myproject/member_servlet/delete.do";
                    document.form1.submit();
                }
            });
        });
    </script>
</body>

</html>