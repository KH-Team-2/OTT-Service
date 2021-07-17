<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(function () {
        $("#headers").load("header.jsp");

        $('#insertbtn').click(function () {
            location.href = 'notice.do?command=write';
        });
    });
</script>
<style>
    body {
        width: 1200px;
        margin: 0 auto;
        color: white;
    }

    .notice {
        border: 1px solid yellow;
    }
    tr{
        text-align: center;
    }
    td{
        height: 50px;
    }
    #insertbtn{
        margin-left: 1150px;
    }
    a:link{
        color: white;
    }
    a:visited{
        color: white;
    }
    a:hover{
        color: #a3a3a3;
    }
</style>
<body>
<%
    int num = (int) request.getAttribute("usernum");
%>
<div id="headers"></div>
<h1 style="color: white">공지사항</h1>
<br>
<br>
<div class="notice">
    <table class="noticetable" border="1px solid blue">
        <col width="135px"/>
        <col width="175px"/>
        <col width="570px"/>
        <col width="175px"/>
        <col width="135px"/>
        <tr>
            <td>번호</td>
            <td>닉네임</td>
            <td>제목</td>
            <td>날짜</td>
            <td>조회</td>
        </tr>
        <c:choose>
            <c:when test="${empty list }">
                <tr>
                    <td colspan="5">공지사항이 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="list" scope="request" type="java.util.List"/>
                <c:forEach items="${list }" var="dto">
                    <tr>
                        <td>${dto.num}</td>
                        <td>${dto.nickname}</td>
                        <td><a href="notice.do?command=watch&noticenum=${dto.num}&usernum=<%=num%>">${dto.title}</a></td>
                        <td>${dto.date}</td>
                        <td>${dto.reference}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
    <button id="deletetbtn">삭제</button>
    <button id="insertbtn">작성</button>
    <jsp:include page="../admin/NoticePaging.jsp">
        <jsp:param name="usernum" value="<%=num%>"/>
        <jsp:param value="${paging.page }" name="page"/>
        <jsp:param value="${paging.beginPage }" name="beginPage"/>
        <jsp:param value="${paging.endPage }" name="endPage"/>
        <jsp:param value="${paging.prev }" name="prev"/>
        <jsp:param value="${paging.next }" name="next"/>
    </jsp:include>
</div>
</body>
</html>