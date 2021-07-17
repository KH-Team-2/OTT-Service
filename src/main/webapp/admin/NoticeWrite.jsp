<%@ page import="com.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(function () {
        $("#headers").load("../header.jsp");


        $('#writesubmitbtn').click(function () {
            if ($('#noticearea').val().trim() == "" || $('#inputnoticetitle').val().trim() == "") {
                alert('빈 칸을 채워주세요');
                return false;
            } else {
                $('#formsubmit').submit();
            }
        });


    });
</script>
<style>
    body {
        width: 1200px;
        margin: 0 auto;
        color: white;
    }

    a {
        text-decoration: none;
    }

    a:link {
        color: white;
    }

    a:visited {
        color: white;
    }

    a:hover {
        text-decoration: underline;
    }
    #noticeone {
        width: 1000px;
        margin: 0 auto;
    }

    textarea {
        width: 100%;
        height: 500px;
    }
</style>
<body>
<%
    UserDto dto = (UserDto) session.getAttribute("dto");
%>
<div id="headers"></div>
<br>
<br>
<a href="notice.do?command=list&page=1&usernum=<%=dto.getUserNum()%>"><h2>공지사항</h2></a>
<br>
<br>
<br>
<br>
<form id="formsubmit" action="../notice.do?command=write" method="post">
    <input type="hidden" name="usernum" value="<%=dto.getUserNum()%>">
    <input type="hidden" name="nickname" value="<%=dto.getNickName()%>">
    <div id="noticeone">
        <div class="updatebutton" style="float: right">
            <button id="writesubmitbtn">작성</button>
            <button id="writecancelbtn">취소</button>
        </div>
        <br><br>
        <span style="font-size: 25px">제목</span><input type="text" id="inputnoticetitle" name="title"
               style="background: black; width: 100%; color: white; font-size: 30px"><br><br><br>
        <span style="font-size: 20px">글 내용</span>
        <textarea name="content" id="noticearea" style="background: black; resize: none; border: 1px solid #a3a3a3;
color: white; font-size: 20px"></textarea>
    </div>
</form>
</body>
</html>
