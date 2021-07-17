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
        $("#deletetbtn").click(function () {
        	var checkArr = [];
        	var params ="?command=delete";
        	
        	$("input[name=check]:checked").each(function(){
        		checkArr.push($(this).val());
        		console.log(checkArr);
        	});
        	
        	$.ajax({
        		type: "POST",
        		url: "notice.do"+params,
        		traditional: true,
        		data: {
        			checkArr : checkArr
        		},
        		success: function(data){
        			alert("공지사항 삭제 성공");
        			location.reload();
        		},
        		error: function(){
        			alert("error");
        		}
        	});
        	
        });
        $("#allchk").change(function () {
            if ($("#allchk").is(":checked")) {
                $(".chk").prop("checked", true);
            } else {
                $(".chk").prop("checked", false);
            }
        });

        $(".chk").change(function () {
            if (!$(this).is(":checked")) {
                $("#allchk").prop("checked", false);
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
    	<col width="30px" class="admin"/>
        <col width="135px"/>
        <col width="175px"/>
        <col width="570px"/>
        <col width="175px"/>
        <col width="135px"/>
        <tr>
        	<td><input type="checkbox" id="allchk"></td>
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
                    	<td><input type="checkbox" class="chk" name="check" value="${dto.num }"></td>
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