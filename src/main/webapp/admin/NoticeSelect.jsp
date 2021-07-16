<%@ page import="com.dto.UserDto" %><%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2021/07/16
  Time: 9:07 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    UserDto dto = (UserDto) request.getSession();
%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(function () {
        $("#headers").load("../header.jsp");

        $('#updatebtn').click(function () {
            location.href = 'notice.do?command=update&usernum=<%=dto.getUserNum()%>&noticenum=${noticedto.num}'
        });
    });
</script>
<style>
    body {
        width: 1200px;
        margin: 0 auto;
        color: white;
    }
    td{
        height: 80px;
    }
</style>
<body>
<div id="headers"></div>
<br>
<br>
<br>
<br>
<br>
<br>
<table border="1" style="text-align: center; margin: 0 auto">
    <tr>
        <td width="100px">TITLE</td>
        <td width="600px" style="text-align: left">${noticedto.title}</td>
        <td width="100px">작성 날짜</td>
        <td style="text-align: left">${noticedto.date}</td>
    </tr>
    <tr><td></td></tr>
    <tr>
        <td style="text-align: left">
            ${noticedto.content}
        </td>
        <td>작성자</td>
        <td style="text-align: left">${noticedto.nickname}</td>
        <td>조회수</td>
        <td>${noticedto.reference}</td>
    </tr>
</table>
<button id="updatebtn">수정</button>
</body>
</html>
