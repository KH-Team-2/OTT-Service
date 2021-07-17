<%@ page import="com.dto.UserDto" %>
<%@ page import="com.dto.WishListDto" %>
<%@ page import="com.biz.wish.WishBiz" %>
<%@ page import="com.biz.wish.WishBizImpl" %>
<jsp:useBean id="dto" scope="request" type="com.dto.ContentsDto"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%
    UserDto udto = (UserDto) session.getAttribute("dto");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        $(function () {

            $("#header").load("header.jsp");

            let num;

            $('#reviewsubmit').click(function () {
                var params = "command=" + $('#command').val()
                    + "&movienum=" + $('#movienum').val()
                    + "&usernum=" + $('#usernum').val()
                    + "&movietitle=" + $('#movietitle').val()
                    + "&reviewinfo=" + $('#reviewinfo').val();

                if (confirm("작성하시겠습니까?")) {
                    $.ajax({
                        type: "post",
                        url: "review.do?" + params,
                        dataType: "text",
                        success: function (data) {
                            var check = data.toUpperCase().trim().toString().replace(/\s/g, ' ');
                            if (check == "FALSE") {
                                alert("금지어가 입력되었습니다. 새로 입력해주세요.");
                            } else {
                                location.reload();
                            }
                        },
                        error: function () {
                            alert("실패");
                        }
                    });
                }

            });


            $('.reviewupdate').click(function () {
                if (num === undefined || num == $(this).parent().parent().attr('class')) {
                    $(this).parent().parent().children('.reviewinfotd').show();
                    $(this).parent().parent().children('.onereviewinfo').hide();

                    $(this).css('display', 'none');
                    $(this).next().css('display', 'none');
                    $(this).next().next('.reviewupdatesubmit').show();
                    $(this).next().next().next('.reviewupdatecancel').show();
                } else if (num != $(this).parent().parent().attr('class')) {
                    $('.' + num).children('.reviewinfotd').hide();
                    $('.' + num).children('.onereviewinfo').show();
                    $('.' + num).children('.btns').children('.reviewupdate').show();
                    $('.' + num).children('.btns').children('.reviewupdate').next().show();
                    $('.' + num).children('.btns').children('.reviewupdate').next().next('.reviewupdatesubmit').hide();
                    $('.' + num).children('.btns').children('.reviewupdate').next().next().next('.reviewupdatecancel').hide();

                    $(this).parent().parent().children('.reviewinfotd').show();
                    $(this).parent().parent().children('.onereviewinfo').hide();

                    $(this).css('display', 'none');
                    $(this).next().css('display', 'none');
                    $(this).next().next('.reviewupdatesubmit').show();
                    $(this).next().next().next('.reviewupdatecancel').show();
                }
                num = $(this).parent().parent().attr('class');
            });

            $('.reviewupdatecancel').click(function () {
                $(this).parent().parent().children('.reviewinfotd').hide();
                $(this).parent().parent().children('.onereviewinfo').show();

                $(this).css('display', 'none');
                $(this).prev().css('display', 'none');
                $(this).prev().prev().show();
                $(this).prev().prev().prev().show();
            });

            $('.reviewupdatesubmit').click(function () {
                var reviewinfo = $(this).parent().parent().children('.reviewinfotd').children('.reviewinfo').val()
                var reviewnum = Number($(this).parent().parent().attr('class'));

                var params = "?command=updatereview" +
                    "&reviewnum=" + reviewnum +
                    "&reviewinfo=" + reviewinfo;

                if (confirm("수정하시겠습니까?")) {
                    $.ajax({
                        type: "post",
                        url: "review.do" + params,
                        success: function () {
                            alert("수정 완료");
                            location.reload();
                            $(this).focus();
                        },
                        error: function () {
                            alert("실패");
                        }
                    });
                }
            });

            $('.reviewdelete').click(function () {
                var reviewnum = Number($(this).parent().parent().attr('class'));

                const params = "?command=deletereview" +
                    "&reviewnum=" + reviewnum;

                if (confirm("삭제하시겠습니까?")) {
                    $.ajax({
                        type: "post",
                        url: "review.do" + params,
                        success: function () {
                            alert("삭제 완료");
                            location.reload();
                        },
                        error: function () {
                            alert("실패");
                        }
                    });
                }

            });

            $('.reviewreport').click(function () {
                var reviewnum = Number($(this).parent().parent().attr('class'));

                var params = "?command=reportreview" +
                    "&reviewnum=" + reviewnum;
                if (confirm("신고하시겠습니까?")) {
                    $.ajax({
                        type: "post",
                        url: "review.do" + params,
                        success: function () {
                            alert("신고 완료");
                            location.reload();
                        },
                        error: function () {
                            alert("실패");
                        }
                    });
                }

            });
            $("#wishdelbtn").click(function(){
            	wishdel()
            });
            $("#wishaddbtn").click(function(){
            	wishadd()
            });
  		  });


        function addviewlist() {
            var movienum = ${dto.movieNum};
            var usernum = <%=udto.getUserNum()%>;

            var params = "?command=viewlistadd" +
                "&movienum=" + ${dto.movieNum} +
                    "&usernum=" + <%=udto.getUserNum()%>;

            $.ajax({
                type: "post",
                url: "viewlist.do" + params,
                success: function () {
                    // alert("등록 완료");
                },
                error: function (request, status, error) {
                    // alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
                }
            })
        }

        function wishadd(){
        	 var movienum = ${dto.movieNum};
             var usernum = <%=udto.getUserNum()%>;

             var params = "?command=wishadd"+"&movienum=" + ${dto.movieNum} +
             "&usernum=" + <%=udto.getUserNum()%>;

             $.ajax({
            	 type : "post",
            	 url : "user.do"+params,
            	 success : function(data){
            		 $("#wishaddbtn").remove();
            		 $("#imgdiv").append('<button id="wishdelbtn"><img src="img/wish1.png" id="wishimg"></button>');
            		 location.reload();
            	 },
            	 error : function(){

            	 }
             })
        }
        function wishdel(){
       	 var movienum = ${dto.movieNum};
            var usernum = <%=udto.getUserNum()%>;

            var params = "?command=wishdel"+"&movienum=" + ${dto.movieNum} +
            "&usernum=" + <%=udto.getUserNum()%>;

            $.ajax({
           	 type : "post",
           	 url : "user.do"+params,
           	 success : function(data){
           		 $("#wishdelbtn").remove();
           		 $("#imgdiv").append('<button id="wishaddbtn"><img src="img/wish2.png" id="wishimg"></button>');
           		location.reload();
           	 },
           	 error : function(){

           	 }
            })
       }

    </script>
    <style type="text/css">
        @font-face {
            font-family: 'NEXON Lv1 Gothic OTF';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: 'NEXON Lv1 Gothic OTF';
        }

        #wrap {
            border: 1px solid black;
            width: 1200px;
            height: 1200px;
            margin: 0 auto;
            background: black;
        }

        #bigtable {
            margin: 30px auto;

        }

        #imgdiv {
            width: 450px;
            height: 300px;
            margin-left: 0px;
            margin-top: 50px;
            float: left;
			position: relative;
        }

        #contentimage {
            width: 100%;
            height: 100%;
            border: 0px;

        }

        #titlediv {
            width: 750px;
            height: 350px;
            float: left;
            margin-left: 0px;
            margin-top: 50px;

        }

        #detaildiv {
            border-bottom: 1px solid #A3A3A3;
            width: 100%;
            height: 180px;
            float: left;
            margin-left: 0px;
            margin-top: 30px
        }

        #reviewdiv {
            width: 100%;
            height: auto;
            float: left;
            margin-left: 0px;
            margin-top: 30px;
        }

        #writediv {
            width: 100%;
            height: 95px;
            float: left;
            margin-left: 0px;
            margin-top: 20px;
            margin-bottom: 10px;
            
        }

        #title {
            font-size: 40px;
            margin-left: 60px;
            margin-top: 50px;
        }

        #genre {
            margin-left: 60px;
            color: white;
        }

        #actor {
            margin-left: 60px;
            color: #A3A3A3;
        }

        #director {
            margin-left: 60px;
            color: #A3A3A3;
        }

        #writer {
            margin-left: 60px;
            color: #A3A3A3;
        }

        #detail {
            font-size: 20px;

        }

        .content {
            color: rgb(248, 211, 28);
            font-weight: bold;
        }

        #detail {
            color: #A3A3A3;
        }

        .btn {
            width: 70px;
            height: 40px;
            color: black;
            background-color: #f8d31c;
            border-color: #f8d31c;
            font-size: 20px;
            border-radius: 10px;
            font-family: 'NEXON Lv1 Gothic OTF';
            font-weight: bold;
        }

        #reviewinfo {
            height: 100px;
            width: 91%;
            border-radius: 10px;
            font-size: 20px;
            border: 3px solid #A3A3A3;
            background-color: black;
            color: white;
        }

        .reviewinfo {
            border: 3px solid #A3A3A3;
            background-color: black;
            color: white;
            border-radius: 10px;
        }

        #reviewsubmit {
            float: right;
            position: relative;
            top: 35px;
        }

        #reviewtb {
            color: white;
            text-align: center;
            width: 100%;
        }

        #reviewtb td {
            height: 30px;
        }

        #reviewtb tr {
            border-bottom: 1px solid rgb(248, 211, 28);
        }

        .pfimg {
            margin-left: 10px;
            width: 40px;
            height: 40px;
        }

        .reviewbtn {
            width: 50px;
            height: 30px;
            color: black;
            background-color: #f8d31c;
            border-color: #f8d31c;
            font-size: 15px;
            border-radius: 10px;
            font-family: 'NEXON Lv1 Gothic OTF';
            font-weight: bold;
        }
        #wishaddbtn{
        	position: absolute;
        	top : 260px;
        	right : 5px;
        	background: none;
        	border: none;
        	cursor: pointer;
        }
        #wishdelbtn{
        	position: absolute;
        	top : 260px;
        	right : 5px;
        	background: none;
        	border: none;
        	cursor: pointer;
        }
       	#wishimg{
       		width: 30px;
       		height: 30px;
       	}
        .imgurl {
            width: 64px;
            height: 64px;
        }
    </style>
</head>
<body>
<div id="wrap">
    <div id="header"></div>
    <div id="contentDetail">
        <div id="imgdiv">
            <img id="contentimage" src="${dto.movieImg}" alt="${dto.title}">
            <%
            	WishBiz biz = new WishBizImpl();
            	WishListDto wishdto = biz.wishfound(udto.getUserNum(),dto.getMovieNum());
            	if(wishdto==null){%>
            <button id="wishaddbtn"><img src="img/wish2.png" id="wishimg"></button>
            <%
            	}else{%>
             <button id="wishdelbtn"><img src="img/wish1.png" id="wishimg"></button>
             <%} %>
        </div>
        <div id="titlediv">
            <span id="title" class="content">${dto.title}(${dto.openYear})</span><a class="movieaddr"
                                                                                    onclick="addviewlist();"
                                                                                    href="${dto.movieAddr}"><img
                class="pfimg" src="${dto.pfimgurl}"
                alt="${dto.title}"
                style="margin-bottom:-5px;"></a>
            <br><br>
            <span id="genre" class="content">장르 ${dto.genre}</span>
            <br><br>
            <span id="actor" class="content">배우 ${dto.actor}</span>
            <br><br>
            <span id="director" class="content">감독 ${dto.director}</span>
        </div>
        <div id="detaildiv">
				<span id="detail">
                    ${dto.summary}
                </span>
        </div>
        <div id="writediv">
				<span id="reviewwrite">
						<textarea style="resize: none" cols="110" rows="6" name="reviewinfo" id="reviewinfo"></textarea>
                        <input type="hidden" id="command" name="command" value="write">
                        <input type="hidden" id="movienum" name="movienum" value="${dto.movieNum}">
                        <input type="hidden" id="usernum" name="usernum" value="<%=udto.getUserNum()%>">
                        <input type="hidden" id="movietitle" name="movietitle" value="<%=dto.getTitle()%></>">
						<input type="button" value="작성" class="btn" id="reviewsubmit" >
				</span>
        </div>
        <div id="reviewdiv">
            <table id="reviewtb">
                <col width="100">
                <col width="100">
                <col width="100">
                <col width="600">
                <thead style="background:#A3A3A3; color:black;">
                <tr>
                    <th>프로필 사진</th>
                    <th>닉네임</th>
                    <th>작성일</th>
                    <th colspan="2">리뷰내용</th>
                </tr>
                </thead>
                <tbody class="reviewbody">
                <c:choose>
                    <c:when test="${empty list }">
                        <tr>
                            <td colspan="6">리뷰가 없습니다.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${list }" var="dto">
                            <tr class="${dto.reviewNum}">
                                <td><img class="imgurl" src="${dto.imgurl}"></td>
                                <td>${dto.nickName }</td>
                                <td class="reviewdate">${dto.date }</td>
                                <td class="onereviewinfo">${dto.reviewInfo }</td>
                                <td class="reviewinfotd" style="display: none">
                                    <textarea style="resize: none" cols="70" rows="3" name="reviewinfo"
                                              class="reviewinfo">${dto.reviewInfo}</textarea>
                                </td>
                                <c:set var="usernum" value="<%=udto.getUserNum()%>"/>
                                <c:choose>
                                    <c:when test="${dto.userNum eq usernum}">
                                        <td class="btns" align="right">
                                            <button class="reviewbtn reviewupdate">수정</button>
                                            <button class="reviewbtn reviewdelete">삭제</button>
                                            <button class="reviewbtn reviewupdatesubmit" style="display: none">완료
                                            </button>
                                            <button class="reviewbtn reviewupdatecancel" style="display: none">취소
                                            </button>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="btns" align="right">
                                            <button class="reviewbtn reviewreport">신고</button>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td colspan="4">
                        <jsp:include page="../search/ReviewPaging.jsp">
                            <jsp:param name="movienum" value="${dto.movieNum}"/>
                            <jsp:param name="title" value="${dto.title}"/>
                            <jsp:param value="${paging.page }" name="page"/>
                            <jsp:param value="${paging.beginPage }" name="beginPage"/>
                            <jsp:param value="${paging.endPage }" name="endPage"/>
                            <jsp:param value="${paging.prev }" name="prev"/>
                            <jsp:param value="${paging.next }" name="next"/>
                        </jsp:include>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>

</html>