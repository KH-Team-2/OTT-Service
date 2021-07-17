<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
    <style type="text/css">
        /*body {*/
        /*    text-align: center;*/
        /*}*/

        #paging {
            font-size: 20pt;
            color: white;
        }

	a:visited {
	text-decoration: none;
	color: white;
	}
	a:active {
	text-decoration: none;
	color: white;
	}
	a:link{
	text-decoration: none;
	color: white;
	}
	a:hover{
	text-decoration: underline;
	}
    </style>
</head>
<body>
<%
    int usernum = Integer.parseInt(request.getParameter("usernum"));
%>
<div id="paging">
    <c:url var="action" value="viewlist.do?command=viewhistory"/>
    <c:if test="${param.prev }">
        <a href="${action }&page=${param.beginPage-1}&usernum=<%=usernum%>">&lt;&lt;</a>
    </c:if>
    <c:forEach begin="${param.beginPage }" end="${param.endPage }" step="1" var="index">
        <c:choose>
            <c:when test="${param.page==index }">
                ${index }
            </c:when>
            <c:otherwise>
                <a href="${action}&page=${index}&usernum=<%=usernum%>">${index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${param.next }">
        <a href="${action}&page=${param.endPage+1}&usernum=<%=usernum%>">&gt;&gt;</a>
    </c:if>
</div>
</body>
</html>