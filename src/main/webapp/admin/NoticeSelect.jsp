<%@ page import="com.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<html>
<head>
    <title>Title</title>
</head>
<%
    UserDto dto = (UserDto) session.getAttribute("dto");
%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(function () {
        $("#headers").load("header.jsp");

        $('#updatebtn').click(function () {
            $('#noticeupdate').show();
            $('#noticeone').hide();
        });
        $('#updatecancel').click(function () {
            $('#noticeone').show();
            $('#noticeupdate').hide();
        });

        $('#updatesubmit').click(function () {
            if ($('#noticearea').val().trim() == "" || $('#inputnoticetitle').val().trim() == "") {
                alert('빈 칸을 채워주세요');
                return false;
            } else {
                $('#formsubmit').submit();
            }
        });
        <%
        if (dto.getGrade().equals("USER")){
            %>
        $('#updatebtn').css('display', 'none');
        <%

        }else{
            %>
        $('#updatebtn').show();
        <%

        }

        %>
    });
</script>
<style>
    body {
        width: 1200px;
        margin: 0 auto;
        color: white;
    }

    td {
        height: 80px;
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

    #noticeupdate {
        width: 1000px;
        margin: 0 auto;
    }
    button {
		height:25px;
	    width:50px;
	    border-radius: 5px;
	    font-size: 15px;
	    border:2px solid #f8d31c;
	    background-color : #f8d31c;
	    color : black;
    }
</style>
<body>
<div id="headers"></div>
<br>
<br>
<a href="notice.do?command=list&page=1&usernum=<%=dto.getUserNum()%>"><h2>공지사항</h2></a>
<br>
<br>
<br>
<br>
<div id="noticeone">
    <span id="noticetitle" style="font-size: 30px">${noticedto.title}</span><br><br>
    <span id="noticedate" style="font-size: 20px">${noticedto.date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        ${noticedto.nickname}</span><span style="float: right; font-size: 20px">조회 ${noticedto.reference}</span>
    <hr>
    <br><br><br>
    <textarea disabled="disabled" style="background: black; resize: none; border: none;
color: white; font-size: 20px">${noticedto.content}</textarea>
    <div class="updatebutton" style="float: right">
        <button id="updatebtn">수정</button>
    </div>
</div>


<form id="formsubmit" action="notice.do?command=update" method="post">
    <input type="hidden" value="${noticedto.num}" name="noticenum">
    <div id="noticeupdate" style="display: none">
    <span id="noticetitleupdate" style="font-size: 30px">
        <input type="text" id="inputnoticetitle" name="title" value="${noticedto.title}"
               style="background: black; width: 100%; color: white; font-size: 30px">
    </span><br><br>
        <span id="noticedateupdate" style="font-size: 20px">${noticedto.date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${noticedto.nickname}</span>
        <hr>
        <br><br><br>
        <textarea name="content" id="noticearea" style="background: black; resize: none; border: 1px solid yellow;
color: white; font-size: 20px">${noticedto.content}</textarea>
        <div class="updatebutton" style="float: right">
            <button id="updatesubmit">완료</button>
            <button id="updatecancel">취소</button>
        </div>
    </div>
</form>
</body>
</html>
