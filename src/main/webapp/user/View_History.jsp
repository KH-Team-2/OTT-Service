<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style type="text/css">

        @font-face {
            font-family: 'NEXON Lv1 Gothic OTF';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: 'NEXON Lv1 Gothic OTF';
            font-size: 20px;
            background-color: black;
        }

        div {
            background-color: black;
            color: white;
            position: absolute;
            top: -20px;
        }

        table {
            text-align: left;
            position: relative;
            left: 150px;
            top: 0;
            padding: 3px;
            border-spacing: 10px;
            border-collapse: separate;
        }

        table td {
            padding: 3px;
        }

        ul {
            position: absolute;
            left: 130px;
            bottom: 80px;
        }

        .select, #delbtn {
            opacity: 0;
        }

        #delbtn {
            position: relative;
            top: 3px;
            width: 25px;
            height: 25px;
        }

        #allchk {
            position: relative;
            left: 2px;
        }

        .blind {
            visibility: hidden;
        }

        .pagination {
            display: inline-block;
            text-align: center;
            position: relative;
            bottom: 20px;
            left: 160px;
        }

        .pagination li {
            display: inline;
            text-align: center;
        }

        .pagination a {
            color: white;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
        }

        img {
            width: 25px;
            height: 25px;

        }

        #delshow {
            position: relative;
            left: 5px;
            padding: 3px 10px;
            background-color: #F8D31C;
            border: none;
            border-radius: 4px;
            color: black;
            font-family: 'NEXON Lv1 Gothic OTF';
            font-size: 15px;
            cursor: pointer;
        }
        .delete{
            cursor: pointer;
        }

    </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        $(function () {

            $("#delshow").click(function () {
                if ($("#delbtn").css("opacity") == 1) {
                    $(".select,#delbtn").css("opacity", "0");
                    $("input[type=checkbox]").prop("disabled", true);
                } else if ($("#delbtn").css("opacity") == 0) {
                    $(".select,#delbtn").css("opacity", "1");
                    $("input[type=checkbox]").prop("disabled", false);
                }
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

            $('.delete').click(function () {
                var hsnum = $(this).prev().children('.hsnum').text();

                var params = "?command=viewdel&historynum=" + hsnum;

                $.ajax({
                    type: "post",
                    url: "viewlist.do" + params,
                    success: function () {
                        alert("성공");
                        location.reload();
                    },
                    error: function () {
                        alert("실패");
                    }
                })
            });

            $('.title').hover(function () {
                $(this).css("cursor", "pointer");
                $(this).css('text-decoration', 'underline');
            }, function () {
                $(this).css('text-decoration', 'none');
            });

            $('.title').click(function () {
                var title = $(this).children('.sptitle').text();
                var movienum = $(this).children('.movienum').text();

                window.open("search.do?command=detail&title=" + title + "&movienum=" + movienum);
            });

        });

    </script>
</head>
<body>
<form>
    <div class="area">

        <table>
            <col width="10">
            <col width="300">
            <col width="25">
            <thead>
            <tr>
                <th class="select"><input type="checkbox" id="allchk"></th>
                <th class="title"><span class="blind"></span></th>
                <th class="delete"><span class="blind"></span></th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty list }">
                    <td colspan="3">영상이 없습니다.</td>
                </c:when>
                <c:otherwise>
                    <jsp:useBean id="list" scope="request" type="java.util.List"/>
                    <c:forEach items="${list }" var="list">
                        <tr>
                            <td class="select"><input type="checkbox" class="chk"></td>
                            <td class="title"><span class="sptitle">${list.movieTitle}</span><span
                                    class="movienum" style="display: none">${list.movieNum}</span>
                                <span class="hsnum" style="display: none">${list.historyNum}</span></td>
                            <td class="delete"><img src="../img/delete.png" alt="삭제"></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <%--<tr>
                <td class="select"><input type="checkbox" class="chk"></td>
                <td class="title"><span>HOME ALONE</span></td>
                <td class="delete"><img src="../img/delete.png" alt="삭제"></td>
            </tr>--%>


            <tr>
                <td colspan="3">
                    <input type="image" src="img/delete.png" id="delbtn" class="delete">
                    <input type="button" id="delshow" value="선택" onclick="">
                </td>
            </tr>
            
            </tbody>
        </table>

        <ul class="pagination">
            <li><a href="">&laquo;</a></li>
            <li><a href="">1</a></li>
            <li><a href="">2</a></li>
            <li><a href="">3</a></li>
            <li><a href="">4</a></li>
            <li><a href="">5</a></li>
            <li><a href="">6</a></li>
            <li><a href="">&raquo;</a></li>
        </ul>

    </div>
</form>

</body>
</html>