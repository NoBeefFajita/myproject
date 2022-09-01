<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="member.MemberDTO" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>memberList</title>
</head>

<body>
    <%
    Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
    List<MemberDTO> list = (List<MemberDTO>)map.get("list");
    int count = (int)map.get("count");
    %>
    등록된 회원수 : <%= count %>명
    <table border="1" width="100%">
        <tr>
            <th>이름</th>
            <th>아이디</th>
            <th>가입일자</th>
            <th>이메일</th>
        </tr>
        <% for(MemberDTO dto : list) { %>
        <tr>
            <td><a href="#"><%= dto.getName() %></a></td>
            <td><%= dto.getUserid() %></td>
            <td><%= dto.getJoinDate() %></td>
            <td><%= dto.getEmail() %></td>
        </tr>
        <% } %>
    </table>

    <form name="form1" method="post" action="/myproject/member_servlet/view.do">
        <input type="hidden" name="userid">
    </form>

    <script>
        // const names = document.querySelectorAll("a");
        // const form = document.form1;
        Array.from(document.querySelectorAll("a")).forEach(name => {
            name.addEventListener('click', () =>{
                const id = name.parentNode.nextElementSibling.textContent;
                document.form1.userid.value = id;
                document.form1.submit();
            });
        });
    </script>
</body>

</html>