<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="com.dto.ContentsDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset:UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	.contentwrap{
		border: 1px solid black;
	    width: 1200px;
	    height: 1200px;
	    margin: 0 auto;
	    background: black;
	    position: relative;
	}
	.contents{
		width : 270px;
		height : 180px;
		float: left;
		margin-left : 20px;
		margin-bottom: 50px;
	}
	.titlediv{
		color : white;
		cursor : pointer;
	}
	.imgs{
		width : 270px;
		height: 180px;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#header").load("header.jsp");
	$(".titlediv").click(function () {
        var str = "?command=detail&page=1&title="+$("input[name=titleval]").val();
		console.log(str);
        //location.href = 'search.do' + str;
    });
	});
</script>
</head>
<body>
<div id="header"></div>

<div class="contentwrap">
	<c:choose>
		<c:when test="${empty list }">
            <h1 style="color: white">영상이 없습니다.</h1>
        </c:when>
        <c:otherwise>
        	<c:forEach items="${list }" var="dto">
        		<div class="contents">
        			<div class="imgdiv"><img src="${dto.movieImg }" class="imgs"></div>
        			<div class="titlediv">${dto.title }<input type="hidden" value="${dto.title}" name="titleval"></div>
        		</div>
        	</c:forEach>
        </c:otherwise>
	</c:choose>
</div>
	<jsp:include page="../search/AllListpaging.jsp">
			<jsp:param value="${paging.page }" name="page"/>
			<jsp:param value="${paging.beginPage }" name="beginPage"/>
			<jsp:param value="${paging.endPage }" name="endPage"/>
			<jsp:param value="${paging.prev }" name="prev"/>
			<jsp:param value="${paging.next }" name="next"/>
		</jsp:include>

</body>
</html>