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
		#allvideoview{
			margin : 0 auto;
			text-align: center;
			margin-bottom: 20px;
		}
		#allvideobtn{
			text-decoration: none;
			color : rgb(248, 211, 28);
			font-size: 30px;
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
    <a href="https://www.netflix.com/kr" target="_blank"><img class="pfurl" src="http://www.khproject.kr/OTT_Service/img/netflixlogo.png" alt="????????????"></a>
    <a href="https://www.watcha.com" target="_blank"><img class="pfurl" src="http://www.khproject.kr/OTT_Service/img/watchalogo.png" alt="??????"></a>
    <a href="https://www.tving.com/main.do?retRef=Y&source=https://www.google.com/" target="_blank"><img class="pfurl" src="http://www.khproject.kr/OTT_Service/img/tvinglogo.jpg" alt="??????"></a>
    <a href="https://www.wavve.com/" target="_blank"><img class="pfurl2" src="http://www.khproject.kr/OTT_Service/img/wavvelogo.png" alt="?????????"></a>
</div>
<br>
<br>
<br>
<br>
<div id="allvideoview">
	<a href="search.do?command=contentallview&page=1" id = "allvideobtn">&lt;&lt;?????? ?????? ??????&gt;&gt;</a>
</div>
<div id="videoselect">
    <div class="videooption">??? ??? ???</div>
    <br>
    <div class="fastest">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <c:choose>
                    <c:when test="${empty newList }">
                        <td colspan="3">????????? ????????????.</td>
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

            <!-- ??????????????? -->
            <div class="swiper-button-next"></div><!-- ?????? ?????? (???????????? ?????? ??????) -->
            <div class="swiper-button-prev"></div><!-- ?????? ?????? -->
        </div>
    </div>
    <br><br>
    <div class="videooption">??? ??? ???</div>
    <br>
    <div class="fastest">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <c:choose>
                    <c:when test="${empty popList }">
                        <td colspan="3">????????? ????????????.</td>
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

            <!-- ??????????????? -->
            <div class="swiper-button-next"></div><!-- ?????? ?????? (???????????? ?????? ??????) -->
            <div class="swiper-button-prev"></div><!-- ?????? ?????? -->
        </div>
    </div>
    <script>
        new Swiper('.swiper-container', {

            slidesPerView: 5, // ????????? ????????? ???????????? ??????
            spaceBetween: 30, // ??????????????? ??????
            slidesPerGroup: 1, // ???????????? ?????? ???, slidesPerView ??? ?????? ?????? ??????????????? ??????

            // ???????????? ?????? ?????? ?????? ???????????? ?????????
            // 3?????? ????????? ????????? 1?????? ????????? 2?????? ???????????? ????????? 3?????? ??????
            loopFillGroupWithBlank: false,

            loop: true, // ?????? ??????

            navigation: { // ???????????????
                nextEl: '.swiper-button-next', // ?????? ?????? ????????????
                prevEl: '.swiper-button-prev', // ?????? ?????? ????????????
            },
        });
    </script>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>