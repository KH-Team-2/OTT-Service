<%@ page import="java.util.List" %>
<%@ page import="com.dto.ContentsDto" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<% String searchBar = request.getParameter("searchBar");
    String startdate = request.getParameter("startdate");
    String enddate = request.getParameter("enddate");
    String startgrade = request.getParameter("startgrade");
    String endgrade = request.getParameter("endgrade");
    String genre = request.getParameter("genre");
%></>
<jsp:include page="../header.jsp">
    <jsp:param name="searchBar1" value="<%=searchBar%>"/>
    <jsp:param name="startdate" value="<%=startdate%>"/>
    <jsp:param name="enddate" value="<%=enddate%>"/>
    <jsp:param name="startgrade" value="<%=startgrade%>"/>
    <jsp:param name="endgrade" value="<%=endgrade%>"/>
    <jsp:param name="genre" value="<%=genre%>"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>검색</title>
    <style type="text/css">
        #wrap {
            border: 1px solid black;
            width: 1200px;
            height: 1500px;
            margin: 0 auto;
            background: black;
            position: relative;
        }
        .pfimg {
            margin-left: 10px;
            width: 40px;
            height: 40px;
        }

        .imgdiv {
            border: 1px solid rgb(248, 211, 28);
            width: 250px;
            height: 300px;
            float: left;
            margin-left: 50px;
            margin-top: 50px;
        }

        .contentdiv {
            border: 1px solid rgb(248, 211, 28);
            float: left;
            margin-left: 40px;
            margin-top: 50px;
            width: 800px;
            height: 300px;
            color: rgb(248, 211, 28);
        }

        .image {
            width: 250px;
            height: 300px;
        }

        .title {
            font-size: 35px;
        }

        .genrediv {
            color: white;
            font-size: 15px;
        }

        .detaildiv {
            color: #A3A3A3;
            font-size: 20px;
        }

        .content {
            /*margin-left: 20px;*/
            margin-bottom: 20px;
            /*font-size: 30px;*/
        }

        .title:hover {
            cursor: pointer;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // $("#header").load("header.jsp");
            $('.title').hover(function () {
                $(this).css("cursor", "pointer");
                $(this).css('text-decoration', 'underline');
            }, function () {
                $(this).css('text-decoration', 'none');
            });

            $(".title").click(function () {
                var str = "?command=detail&title=" + $(this).text()+"&movienum="+ $(this).children('.movienum').val();
                location.href = 'search.do'+str;
            });
            console.log($('#header').children());
        });
    </script>
</head>
<body>
<div id="header">
</div>
<div id="wrap">
    <div class="contentwrap">
        <%--        <div class="imgdiv">--%>
        <%--            <img class="image" src="" alt="">--%>
        <%--        </div>--%>
        <%--        <div class="content contentdiv">--%>
        <%--            <div class="content titlediv">--%>
        <%--                <span class="title">제목</span>--%>
        <%--            </div>--%>
        <%--            <div class="content genrediv">--%>
        <%--                <span class="date">연도</span>--%>
        <%--                <span class="director">감독</span>--%>
        <%--                <span class="actor">배우</span>--%>
        <%--                <span class="genre">장르</span>--%>
        <%--                <span class="score">평점</span>--%>
        <%--            </div>--%>
        <%--            <div class="content detaildiv">--%>
        <%--                <span class="content content">내용</span>--%>
        <%--            </div>--%>
    </div>
    <c:choose>
        <c:when test="${empty list }">
            <h1 style="color: white">영상이 없습니다.</h1>
        </c:when>
        <c:otherwise>
            <jsp:useBean id="list" scope="request" type="java.util.List"/>
            <c:forEach items="${list }" var="list">
                <div class="imgdiv">
                    <img class="image" src="${list.movieImg}" alt="">
                </div>
                <div class="content contentdiv">
                    <div class="content titlediv">
                        <span class="title">${list.title}<input type="hidden" class="movienum" value="${list.movieNum}"></span><span class="date">(${list.openYear})</span>
                        <img src="${list.pfimgurl}" alt="" style="margin-bottom:-5px;"class="pfimg">
                    </div>
                    <div class="content genrediv">
                        <span>감독   <span class="director">${list.director}</span></span><br>
                        <span>배우   </span><span class="actor">${list.actor}</span><br>
                        <span>장르   </span><span class="genre">${list.genre}</span><br>
                        <span>평점   </span><span class="score">${list.rate}</span>
                    </div>
                    <div class="content detaildiv">
                        <span>줄거리</span><br><span class="content content">${list.summary}</span>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</div>

</body>
</html>