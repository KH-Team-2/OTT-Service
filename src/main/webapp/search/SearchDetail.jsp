<%@ page import="com.dto.UserDto" %>
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
            $("#header").load("../header.jsp");

            // var title = $('#movietitle').val();
            // var movienum = $('#movienum').val();


            <%--$.ajax({--%>
            <%--    url: "review.do?command=detail&title=" + title + "&movienum=" + movienum,--%>
            <%--    dataType: "json",--%>
            <%--    success: function (data) {--%>
            <%--        $.each(data, function (key, val) {--%>
            <%--            if (key == "reviewlist") {--%>
            <%--                var list = val;--%>

            <%--                for (let i = 0; i < list.length; i++) {--%>
            <%--                    var str = list[i];--%>
            <%--                    var reviewnum = str.reviewnum;--%>
            <%--                    var body = '';--%>
            <%--                    body += '<tr>';--%>
            <%--                    body += '<td>' + str.nickname + '</td>';--%>
            <%--                    body += '<td>' + str.date + '</td>';--%>
            <%--                    body += '<td>' + str.reviewinfo + '</td>';--%>
            <%--                    body += '<td class="btns" align="right">';--%>
            <%--                    if (str.usernum == <%=udto.getUserNum()%>) {--%>
            <%--                        body += '<button class="reviewbtn reviewupdate" onclick="update(this);">����</button>';--%>
            <%--                        body += '<button class="reviewbtn reviewdelete">����</button>';--%>
            <%--                        body += '</td>';--%>
            <%--                    } else {--%>
            <%--                        body += '<button class="reviewbtn">�Ű�</button>';--%>
            <%--                        body += '</td>';--%>
            <%--                    }--%>
            <%--                    body += '</tr>';--%>
            <%--                    body += "<input type = 'hidden' name='reviewtest' value='" +--%>
            <%--                        str.reviewnum + "/" + str.reviewinfo + "'/>";--%>
            <%--                    $('#reviewtb > tbody:last').append(body);--%>
            <%--                }--%>
            <%--            }--%>
            <%--        });--%>
            <%--    },--%>
            <%--    error: function (request, status, error) {--%>
            <%--        alert("code:" + request.style + "message = " + request.responseText + "error : " + error);--%>
            <%--    }--%>
            <%--})--%>

            let num;
            let text;
            let count = 0;

            $('#reviewsubmit').click(function () {
                var params = "command=" + $('#command').val()
                    + "&movienum=" + $('#movienum').val()
                    + "&usernum=" + $('#usernum').val()
                    + "&movietitle=" + $('#movietitle').val()
                    + "&reviewinfo=" + $('#reviewinfo').val();

                $.ajax({
                    type: "post",
                    url: "review.do?" + params,
                    success: function (data) {
                        location.reload();
                    },
                    error: function () {
                        alert("����");
                    }
                })
            });

            /*$('.reviewupdate').click(function () {
                var a = $(this);
                const area = '<td><textarea style="resize: none" cols="70" rows="3" name="reviewinfo" class="reviewinfo"></textarea></td>';
                if (num === undefined || num == $(this).parent().parent().attr('class')) {
                    text = $(this).parent().parent().children('.onereviewinfo').text();
                    $(this).parent().parent().children('.onereviewinfo').html(area);
                    $('.reviewinfo').val(text);
                    $(this).html('�Ϸ�');
                    $(this).next().html('���');
                    a.addClass('reviewupdatesubmit');
                    a.next().addClass('reviewupdatecancel');
                    if ($(this).text() == "�Ϸ�") {
                        $('.reviewupdatesubmit').click(function (e) {
                            e.stopImmediatePropagation();
                            console.log(num);
                            console.log($(this).parent().parent().children('.reviewinfo').text())
                        });
                    }

                } else if (num != $(this).parent().parent().attr('class')) {
                    $('.' + num).children('.onereviewinfo').html(text);
                    $('.' + num).children('.btns').children('.reviewupdatecancel').html('����');
                    $('.' + num).children('.btns').children('.reviewupdatecancel').prev().html('����');
                    $('.' + num).children('.btns').children('.reviewupdatecancel').prev().removeClass('reviewupdatesubmit');
                    $('.' + num).children('.btns').children('.reviewupdatecancel').removeClass('reviewupdatecancel');
                    text = $(this).parent().parent().children('.onereviewinfo').text();
                    $(this).parent().parent().children('.onereviewinfo').html(area);
                    $('.reviewinfo').val(text);
                    $(this).html('�Ϸ�');
                    $(this).addClass('reviewupdatesubmit');
                    $(this).next().html('���');
                    $(this).next().addClass('reviewupdatecancel');
                    if ($(this).text() == "�Ϸ�") {
                        $('.reviewupdatesubmit').click(function (e) {
                            e.stopImmediatePropagation();
                            console.log(num);
                        });
                    }
                }
                num = $(this).parent().parent().attr('class');

                $('.reviewupdatecancel').click(function () {
                    $(this).parent().parent().children('.onereviewinfo').html(text);
                    $(this).html('����');
                    $(this).prev().html('����');
                    $(this).prev().removeClass('reviewupdatesubmit');
                    $(this).removeClass('reviewupdatecancel');
                });

                // $(this).parent().parent().children('.reviewdate').append(area);
                // $(this).parent().parent().children('.reviewinfo').val(text);
            });*/

            $('.reviewupdate').click(function () {
                if (num === undefined || num == $(this).parent().parent().attr('class')) {
                    $(this).parent().parent().children('.reviewinfotd').show();
                    $(this).parent().parent().children('.onereviewinfo').hide();

                    $(this).css('display', 'none');
                    $(this).next().css('display', 'none');
                    $(this).next().next('.reviewupdatesubmit').show();
                    $(this).next().next().next('.reviewupdatecancel').show();
                } else if (num != $(this).parent().parent().attr('class')) {
                    $('.'+num).children('.reviewinfotd').hide();
                    $('.'+num).children('.onereviewinfo').show();
                    $('.'+num).children('.btns').children('.reviewupdate').show();
                    $('.'+num).children('.btns').children('.reviewupdate').next().show();
                    $('.'+num).children('.btns').children('.reviewupdate').next().next('.reviewupdatesubmit').hide();
                    $('.'+num).children('.btns').children('.reviewupdate').next().next().next('.reviewupdatecancel').hide();

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

                var params = "?command=updatereview"+
                    "&reviewnum=" + reviewnum +
                    "&reviewinfo=" + reviewinfo;

                $.ajax({
                    type: "post",
                    url: "review.do" + params,
                    success: function () {
                        alert("���� �Ϸ�");
                        location.reload();
                        $(this).focus();
                    },
                    error: function () {
                        alert("����");
                    }
                })

                console.log(reviewinfo, reviewnum);
            });

            $('.reviewdelete').click(function () {
                var reviewnum = Number($(this).parent().parent().attr('class'));

                const params = "?command=deletereview" +
                    "&reviewnum=" + reviewnum;

                $.ajax({
                    type: "post",
                    url: "review.do" + params,
                    success: function () {
                        alert("���� �Ϸ�");
                        location.reload();
                    },
                    error: function () {
                        alert("����");
                    }
                })
            });

            $('.reviewreport').click(function () {
                var reviewnum = Number($(this).parent().parent().attr('class'));

                var params = "?command=reportreview" +
                    "&reviewnum=" + reviewnum;
                $.ajax({
                    type: "post",
                    url: "review.do" + params,
                    success: function () {
                        alert("�Ű� �Ϸ�");
                        location.reload();
                    },
                    error: function () {
                        alert("����");
                    }
                })
            });


        });

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
            width: 250px;
            height: 350px;
            margin-left: 80px;
            margin-top: 50px;
            float: left;

        }

        #contentimage {
            width: 250px;
            height: 350px;
            border: 0px;

        }

        #titlediv {
            width: 750px;
            height: 350px;
            float: left;
            margin-left: 40px;
            margin-top: 50px;

        }

        #detaildiv {
            border-bottom: 1px solid #A3A3A3;
            width: 1040px;
            height: 180px;
            float: left;
            margin-left: 80px;
            margin-top: 30px
        }

        #reviewdiv {
            width: 1040px;
            height: auto;
            float: left;
            margin-left: 80px;
            margin-top: 30px;
        }

        #writediv {
            width: 1040px;
            height: 95px;
            float: left;
            margin-left: 80px;
            margin-top: 20px;
        }

        #title {
            font-size: 48px;
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
            width: 940px;
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
    </style>
</head>
<body>
<div id="wrap">
    <div id="header"></div>
    <div id="contentDetail">
        <div id="imgdiv">
            <img id="contentimage" src="${dto.movieImg}" alt="${dto.title}">
        </div>
        <div id="titlediv">
            <span id="title" class="content">${dto.title}(${dto.openYear})</span>
            <br><br>
            <span id="genre" class="content">�帣 ${dto.genre}</span>
            <br><br>
            <span id="actor" class="content">��� ${dto.actor}</span>
            <br><br>
            <span id="director" class="content">���� ${dto.director}</span>
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
                        <input type="hidden" id="movietitle" name="movietitle" value="<%=dto.getTitle()%>">
						<input type="button" value="�ۼ�" class="btn" id="reviewsubmit">
				</span>
        </div>
        <div id="reviewdiv">
            <table id="reviewtb">
                <col width="100">
                <col width="100">
                <col width="600">
                <thead style="background:#A3A3A3; color:black;">
                <tr>
                    <th>�г���</th>
                    <th>�ۼ���</th>
                    <th colspan="2">���䳻��</th>
                </tr>
                </thead>
                <tbody class="reviewbody">
                <c:choose>
                    <c:when test="${empty list }">
                        <tr>
                            <td colspan="5">���䰡 �����ϴ�.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${list }" var="dto">
                            <tr class="${dto.reviewNum}">
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
                                            <button class="reviewbtn reviewupdate">����</button>
                                            <button class="reviewbtn reviewdelete">����</button>
                                            <button class="reviewbtn reviewupdatesubmit" style="display: none">�Ϸ�
                                            </button>
                                            <button class="reviewbtn reviewupdatecancel" style="display: none">���
                                            </button>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="btns" align="right">
                                            <button class="reviewbtn reviewreport">�Ű�</button>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>

</html>