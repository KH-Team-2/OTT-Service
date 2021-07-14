<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	body{
		text-align: center;
	}
	#paging{
		font-size : 20pt;
		color : white;
	}
	a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div id="paging">
	<c:url var="action" value="admin.do?command=FBWList"/>
	<c:if test="${param.prev }">
		<a href="${action }&page=${param.beginPage-1}">&lt;&lt;</a>
	</c:if>
	<c:forEach begin="${param.beginPage }" end="${param.endPage }" step="1" var="index">
		<c:choose>
			<c:when test="${param.page==index }">
				${index }
			</c:when>
			<c:otherwise>
				<a href="${action}&page=${index}">${index}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${param.next }">
		<a href="${action}&page=${param.endPage+1}">&gt;&gt;</a>
	</c:if>
	</div>
</body>
</html>