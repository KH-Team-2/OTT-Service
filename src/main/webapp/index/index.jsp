<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.dto.UserDto" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $('.movieimg').click(function () {
                const movienum = $(this).next().val();
                const title = $(this).next().next().val();
                // console.log($(this).next().val());
                // console.log($(this).next().next().val());
                location.href = 'search.do?command=detail&page=1&title=' + title + '&movienum=' + movienum;
            });
        });
    </script>
    <style>
        body {
            font-family: 'NEXON Lv1 Gothic OTF';
            background: black;
        }

        #videoselect {
            margin: 0 auto;
            width: 1200px;
            height: 700px;
            /*border: rgb(248, 211, 28) solid 1px;*/
            color: rgb(248, 211, 28);
        }

        .videooption {
            font-size: 30px;
            color: rgb(248, 211, 28);
            margin: 0 auto;
            width: 1200px;
        }

        .fastest {
            width: 1200px;
            height: 250px;
            /*border: rgb(248, 211, 28) solid 1px;*/
            color: rgb(248, 211, 28);
        }

        .movieimg {
            /*position: relative;*/
            margin-left: 10px;
            margin-right: 5px;
            margin-bottom: 30px;
            width: 220px;
            height: 140px;
            image-rendering: auto;
            image-rendering: -moz-crisp-edges; /* Firefox */
            image-rendering: -o-crisp-edges; /* Opera */
            /*image-rendering: -webkit-optimize-contrast; !* Webkit (non-standard naming) *!*/
            image-rendering: crisp-edges;
            -ms-interpolation-mode: nearest-neighbor; /* IE (non-standard property) */
        }

        .movieimg:hover {
            transform: scale(1.2);
            transition: 0.8s;
            z-index: 100;
            cursor: pointer;
        }

        .pfimg {
            margin: 0 auto;
            width: 1200px;
        }
        .pfurl {
            width: 200px;
            height: 120px;
            margin-right: 125px;
        }
        .pfurl2 {
            width: 200px;
            height: 120px;
        }


    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
</head>
<body>

<div id="headers"></div>
<br>
<br>
<br>
<div class="pfimg">
    <a href="https://www.netflix.com/kr" target="_blank"><img class="pfurl" src="http://www.khproject.kr/OTT_Service/img/netflixlogo.png" alt="넷플릭스"></a>
    <a href="https://www.watcha.com" target="_blank"><img class="pfurl" src="http://www.khproject.kr/OTT_Service/img/watchalogo.png" alt="왓챠"></a>
    <a href="https://www.tving.com/main.do?retRef=Y&source=https://www.google.com/" target="_blank"><img class="pfurl" src="http://www.khproject.kr/OTT_Service/img/tvinglogo.jpg" alt="티빙"></a>
    <a href="https://www.wavve.com/" target="_blank"><img class="pfurl2" src="http://www.khproject.kr/OTT_Service/img/wavvelogo.png" alt="웨이브"></a>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<div id="videoselect">
    <div class="videooption">최 신 순</div>
    <br>
    <div class="fastest">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <c:choose>
                    <c:when test="${empty newList }">
                        <td colspan="3">영상이 없습니다.</td>
                    </c:when>
                    <c:otherwise>
                        <jsp:useBean id="newList" scope="request" type="java.util.List"/>
                        <c:forEach items="${newList }" var="list">
                            <div class="swiper-slide"><img class="movieimg" src="${list.movieImg}">
                                <input type="hidden" class="movienum" value="${list.movieNum}">
                                <input type="hidden" class="movietitle" value="${list.title}">
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- 네비게이션 -->
            <div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
            <div class="swiper-button-prev"></div><!-- 이전 버튼 -->
        </div>
    </div>
    <br><br>
    <div class="videooption">인 기 순</div>
    <br>
    <div class="fastest">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <c:choose>
                    <c:when test="${empty popList }">
                        <td colspan="3">영상이 없습니다.</td>
                    </c:when>
                    <c:otherwise>
                        <jsp:useBean id="popList" scope="request" type="java.util.List"/>
                        <c:forEach items="${popList }" var="list">
                            <div class="swiper-slide"><img class="movieimg" src="${list.movieImg}">
                                <input type="hidden" class="movienum" value="${list.movieNum}">
                                <input type="hidden" class="movietitle" value="${list.title}">
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- 네비게이션 -->
            <div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
            <div class="swiper-button-prev"></div><!-- 이전 버튼 -->
        </div>
    </div>
    <script>
        new Swiper('.swiper-container', {

            slidesPerView: 5, // 동시에 보여줄 슬라이드 갯수
            spaceBetween: 30, // 슬라이드간 간격
            slidesPerGroup: 1, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음

            // 그룹수가 맞지 않을 경우 빈칸으로 메우기
            // 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
            loopFillGroupWithBlank: false,

            loop: true, // 무한 반복

            navigation: { // 네비게이션
                nextEl: '.swiper-button-next', // 다음 버튼 클래스명
                prevEl: '.swiper-button-prev', // 이번 버튼 클래스명
            },
        });
    </script>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>