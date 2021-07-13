<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>마이페이지</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $("#headers").load("index/index_header.jsp");
        });
    </script>
    <style>
        body {
            font-family: 'NEXON Lv1 Gothic OTF';
            background: black;
        }

        #menu {
            margin: 0 auto;
            width: 1200px;
            /*border: 1px solid yellow;*/
        }

        #menuBar {
            display: inline-block;
            width: 300px;
            /*border: 1px solid yellow;*/
            text-align: right;
            height: 1000px;
        }

        .menuSelect {
            font-family: 'NEXON Lv1 Gothic OTF';
            text-decoration: none;
            /*color: rgb(248, 211, 28);*/
            color: white;
            border: none;
            background: no-repeat;
            cursor: pointer;
            font-size: 25px;
            font-family: 'NEXON Lv1 Gothic OTF';
        }

        .menuSelect:hover {
            color: rgb(248, 211, 28);
        }

        #menuTable {
            width: 300px;
            height: 300px;
            vertical-align: middle;
            text-align: center;
        }

        #menuTable td, #menuTable tr {
            border: 3px solid #A3A3A3;
        }

        #menuTable tr td {
            border-bottom: none;
        }

        #menuTable tr:last-child td {
            border: 3px solid #A3A3A3;
        }

        #menuTable tr td:first-child {
            border-left: 0;
        }

        #menuTable tr td:last-child {
            border-right: 0;
        }

        #logout {
            position: relative;
            top: 240px;
            font-size: 25px;
            color: #a3a3a3;
        }

        #logout:hover {
            color: rgb(248, 211, 28);
        }

        #iframe {
            float: right;
            width: 850px;
            height: 900px;
            color: rgb(248, 211, 28);
            text-align: center;

        }

        #iframe_in {
            margin-top: -25px;
            margin-left: -120px;
        }

    </style>
</head>
<body>
<%
    	int usernum = Integer.parseInt(request.getParameter("usernum"));
        int num = (int) request.getAttribute("page");
             if (num == 1) {
%>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script>
                $(function () {
                    $('#iframe_in').attr('src', 'user.do?command=wishlist&usernum=<%=usernum%>');
                });
            </script>
<%
            } else if (num == 2) {
%>
            <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script type="text/javascript">
                $(function () {
                    $('#iframe_in').attr('src', 'viewlist.do?command=viewhistory&page=2&usernum=<%=usernum%>');
                });
            </script>
            <%
            } else if (num == 3) {
            %>
            <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script type="text/javascript">
                $(function () {
                    $('#iframe_in').attr('src', 'user.do?command=updateuser&usernum=<%=usernum%>');
                });
            </script>
            <%
                }
            %>
<div id="headers"></div>
<div id="menu">
    <div id="menuBar">
        <table id="menuTable">
            <tr>
                <td>
                    <input type="button" class="menuSelect" id="menu_1" value="찜"
                           onclick="$('#iframe_in').attr('src','user.do?command=wishlist&usernum=<%=usernum%>')">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" class="menuSelect" id="menu_2" value="시청기록"
                           onclick="$('#iframe_in').attr('src','viewlist.do?command=viewhistory&page=2&usernum=<%=usernum%>')">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" class="menuSelect" id="menu_3" value="정보수정"
                           onclick="$('#iframe_in').attr('src','user.do?command=updateuser&usernum=<%=usernum%>')">
                </td>
            </tr>
        </table>
        <input type="button" class="menuSelect" id="logout" value="로그아웃">
    </div>
    <div id="iframe">
        <iframe id="iframe_in" src="user.do?command=wishlist&usernum=<%=usernum%>" width="100%" height="100%" frameborder="0"
                allowfullscreen></iframe>
    </div>
</div>
</body>
</html>
