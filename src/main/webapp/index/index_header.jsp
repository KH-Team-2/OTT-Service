<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.dto.UserDto" %>
<!DOCTYPE html><html lang="kor">
<head>
    <title>head</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        var alarmCheck_Count = 0;
        var alarmBtn_Count = 0;
        var userBtn_Count = 0;
        var menues_count = 0;
        $(function () {
            today = new Date();
            console.log("today.toISOString() >>>" + today.toISOString());
            today = today.toISOString().slice(0, 10);
            console.log("today >>>> " + today);
            bir = document.getElementById("enddate");
            bir.value = today;

            $('.userAlarmTable tr:odd').find('td:last-child').css('border-bottom', '1px solid rgb(248, 211, 28')

            $('.alarmBtn').hover(function () {
                if (alarmBtn_Count == 0) {
                    $('.userAlarm').show('fast');
                    setTimeout(function () {
                        alarmBtn_Count = 1;
                    }, 300);
                }
            }, function () {
                if (alarmBtn_Count == 1) {
                    $('.userAlarm').hide('fast');
                    setTimeout(function () {
                        alarmBtn_Count = 0;
                    }, 300);
                }
            });

            $('.userAlarmTable').find('tr td:last-child').css('border', 'none');
            $('.alarmContentsArea').hover(function () {
                $(this).css('text-decoration', 'underline');
            }, function () {
                $(this).css('text-decoration', 'none');
            });

            $('.alarmConfirm').prop('disabled', true);

            $("#filter").hover(function () {
                $("#modal").show('fast');
            }, function () {
                $("#modal").hide('fast');
            });

            /*$("#modalalarm").click(function () {
                if ($("#modalalarm").css("border") == "0px none rgb(248, 211, 28)") {
                    $("#modalalarm").css("border", "1px solid rgb(248, 211, 28)");
                    $('#modalalarm2').css('border', 'none');
                    $('#alarm1').val('Y');
                    $('#alarm2').val('N');
                    console.log($('#alarm1').val());
                    console.log($('#alarm2').val());
                } else {
                    $("#modalalarm").css("border", "none");
                    $('#alarm1').val('N');
                    console.log($('#alarm1').val());
                    console.log($('#alarm2').val());
                }
            });
            $("#modalalarm2").click(function () {
                if ($("#modalalarm2").css("border") == "0px none rgb(248, 211, 28)") {
                    $("#modalalarm2").css("border", "1px solid rgb(248, 211, 28)");
                    $('#modalalarm').css('border', 'none');
                    $('#alarm2').val('Y');
                    $('#alarm1').val('N');
                    console.log($('#alarm1').val());
                    console.log($('#alarm2').val());
                } else {
                    $("#modalalarm2").css("border", "none");
                    $('#alarm2').val('N');
                    console.log($('#alarm1').val());
                    console.log($('#alarm2').val());
                }
            });*/
            $("#userBtnImg").click(function () {
                if (confirm("임시 확인창 입니다. \n확인 : 로그인 창 / 취소 : 개별 페이지 창")) {
                    location.href = "../user/login.jsp";
                } else {
                    if (confirm("임시 확인창 입니다. \n확인 : 회원 페이지 / 취소 : 어드민 페이지")) {
                        location.href = "../user/mypage.jsp";
                    } else {
                        location.href = "../admin/adminmypage.jsp";
                    }
                }
            });

            $("#logo").click(function () {
                location.href = "search.do?command=main";
            });
            $("#user").hover(function () {
                $("#menues").show('fast');
            }, function () {
                $("#menues").hide('fast');
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
            background: black;
        }

        #headpart {
            width: 1200px;
            height: 60px;
            margin: 0 auto;
            background: black;
            padding-top: 50px;
            padding-bottom: 100px;
            /*border: 1px white solid;*/
        }

        #logo {
            position: relative;
            display: inline-block;
            width: 200px;
            height: 60px;
            top: 30px;
            left: 30px;
            cursor: pointer;
        }
        
        #logoImg{
        	width: 100%;
        	height: 100%;
        	object-fit: cover;
        	
        }

        #search {
            display: inline-block;
            position: relative;
            width: 570px;
            top: 30px;
            left: 200px;
            height: 50px;
            border: rgb(248, 211, 28) solid 4px;
            display: inline-block;
        }

        #searchBar {
            display: inline-block;
            position: relative;
            padding: 0 0;
            top: -17px;
            width: 500px;
            height: 50px;
            font-size: 20pt;
            border: none;
            /*border-right: 4px solid #A3A3A3;*/
            background: black;
            color: rgb(248, 211, 28);
        }

        #searchBar:focus {
            outline: none;
        }

        #searchBtn {
            display: inline-block;
            position: relative;
            width: 61.8px;
            height: 50px;
            border: none;
            background: no-repeat;
        }

        #searchBtnImg {
            display: inline-block;
            position: relative;
            width: 72px;
            height: 52px;
            right: 12px;
            bottom: 2px;
            cursor: pointer;
            border: none;
            background: no-repeat;
        }

        #filter {
            display: inline-block;
            position: relative;
            top: 25px;
            left: 200px;
            color: rgb(248, 211, 28);
            font-size: 20pt;
        }

        #filterBtnImg {
            width: 40px;
            height: 40px;
            cursor: pointer;
        }

        #alarm {
            display: inline-block;
            position: relative;
            top: 25px;
            left: 340px;
            color: rgb(248, 211, 28);
        }

        #alarmBtn {
            background: no-repeat;
            border: none;
            cursor: pointer;
            width: 40px;
            height: 40px;
        }

        #user {
            display: inline-block;
            position: relative;
            top: 25px;
            left: 350px;
            color: rgb(248, 211, 28);
        }

        #userBtnImg {
            width: 40px;
            height: 40px;
            cursor: pointer;
        }

        .userAlarm {
            position: absolute;
            /*border-bottom: 3px rgb(248, 211, 28) solid;*/
            /*border: 3px rgb(248, 211, 28) solid;*/
            background: rgb(5, 5, 5);
            left: -190px;
            top: 45px;
            display: none;
        }


        .alarmConfirm {
            background: no-repeat;
            border: none;
        }

        .alarmConfirmBtn {
            width: 40px;
            height: 40px;
        }

        .userAlarmTable td {
            border-top: 1px solid rgb(248, 211, 28);
            border-bottom: 1px solid rgb(248, 211, 28);
            /*border: rgb(248, 211, 28) 3px solid;*/
            text-align: center;
        }

        .userAlarmTable {
            top: 30px;
            border-spacing: 5px 15px;
            border-collapse: separate;
            border-bottom: 2px solid rgb(248, 211, 28);
            /*display: none;*/
        }

        .alarmContentsArea {
            background: none;
            color: rgb(248, 211, 28);
            border: none;

        }

        .alarmContentsArea:focus {
            outline: none;
        }

        .alarmContentsArea:hover {
            cursor: pointer;
        }

        .userAlarmDate, .userAlarmTitle {
            background: none;
            border: none;
            color: rgb(248, 211, 28);
            text-align: center;
            width: 100px;
        }

        .alarmCircle {
            position: relative;
            top: -45px;
            left: 20px;
            width: 20px;
            height: 20px;
            background: red;
            border-radius: 50%;
            display: none;
        }

        #modal {
            position: absolute;
            top: 50px;
            display: none;
            border: 1px solid rgb(248, 211, 28);
            text-align: center;
            font-size: 16px;
            background: rgb(5, 5, 5);
        }

        .modalfilter {
            margin-top: 10px;
            margin-bottom: 10px;
        }

        /*#modalalarm {
            width: 30px;
            height: 30px;
        }

        #modalalarm2 {
            !*background: url("../img/alarm.png") no-repeat;*!

            width: 30px;
            height: 30px;
        }*/

        .menubtn {
            width: 180px;
            height: 50px;
            border: none;
            color: rgb(248, 211, 28);
            text-align: center;
            background-color: black;
            border-top: 1px solid rgb(248, 211, 28);
            font-size: 25px;
            cursor: pointer;
        }

        #adminmenutb {
        }

        .Logout {
            padding: 2px;
            background-color: #F8D31C;
            border: none;
            border-radius: 4px;
            color: black;
            font-family: 'NEXON Lv1 Gothic OTF';
            font-size: 15px;
            cursor: pointer;
            margin: 20px 2px;
        }

        #menues {
            position: absolute;
            right: 2px;
            display: none;
            z-index: 5;
        }
    </style>
</head>
<body>
<%
	UserDto dto = (UserDto)session.getAttribute("dto");
%>
<form action="search.do?command=search" method="post">
    <div id="headpart">
        <div id="logo" class="logoBtn"><img src="http://www.khproject.kr/OTT_Service/img/logo.png" id="logoImg"></div>
        <div id="search">
            <input type="text" name="searchBar" class="input_searching" id="searchBar" placeholder="검색하는곳"
                   value="">
            <button type="submit" name="searchBtn" id="searchBtn" class="searchBtn">
                <img src="img/select.png" class="searchBtnImg" id="searchBtnImg" alt="no">
            </button>
        </div>
        <div id="filter" class="filterBtn">
            <img id="filterBtnImg" src="img/filter.png">
            <div id="modal">
                <div class="modalfilter">
                    <%--<span>알람</span><br>
                    <div id="modalalarmimg">
                        <img src="../img/alarm.png" id="modalalarm">
                        <input type="hidden" name="alarm1" id="alarm1">
                        <img src="../img/alarm2.png" id="modalalarm2">
                        <input type="hidden" name="alarm2" id="alarm2">
                    </div>--%>
                </div>
                <div class="modalfilter">
                    <span>개봉일자</span><br>
                    <input type="date" name="startdate" value="1900-01-01" max="9999-12-31"> - <input type="date"
                                                                                                      name="enddate"
                                                                                                      id="enddate"
                                                                                                      max="9999-12-31">
                </div>
                <div class="modalfilter">
                    <span>평점</span><br>
                    <select name="startgrade">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                    </select> - <select name="endgrade">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10" selected="selected">10</option>
                </select>
                </div>
                <div class="modalfilter">
                    <span>장르</span><br>
                    <select name="genre">
                        <option value="none">--</option>
                        <option value="스릴러">스릴러</option>
                        <option value="코미디">코미디</option>
                        <option value="SF">SF</option>
                        <option value="액션">액션</option>
                        <option value="범죄">범죄</option>
                        <option value="음악">음악</option>
                        <option value="스포츠">스포츠</option>
                        <option value="멜로">멜로</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="alarmBtn" id="alarm">
            <img src="img/alarm.png" id="alarmBtn">
            <div class="alarmCircle"></div>
            <div class="userAlarm">
                <table class="userAlarmTable">
                    <tr style="height: 50px;">
                        <td class="userAlarmSelect">
                            <input type="text" value="09-15" class="userAlarmDate" disabled="disabled">
                        </td>
                        <td class="userAlarmSelect">
                            <input type="text" value="신작 알림" class="userAlarmTitle" disabled="disabled">
                        </td>
                        <td class="userAlarmSelect">
                            <button class="alarmConfirm"><img class="alarmConfirmBtn"
                                                              src="img/alarm(yellow).png"></img>
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" class="userAlarmSelect" style="text-align: left">
                        <textarea rows="3" cols="25" readonly="readonly" style="resize: none;"
                                  class="alarmContentsArea">여기는 알람에 대한 내용이 들어갑니다. 신작이 나왔어요! 얼른 시청해보세요!</textarea>
                        </td>
                    </tr>
                    <tr style="height: 50px;">
                        <td class="userAlarmSelect">
                            <input type="text" value="09-15" class="userAlarmDate" disabled="disabled">
                        </td>
                        <td class="userAlarmSelect">
                            <input type="text" value="신작 알림" class="userAlarmTitle" disabled="disabled">
                        </td>
                        <td class="userAlarmSelect">
                            <button class="alarmConfirm"><img class="alarmConfirmBtn"
                                                              src="img/alarm(yellow).png"></img>
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" class="userAlarmSelect" style="text-align: left">
                        <textarea rows="3" cols="25" readonly="readonly" style="resize: none;"
                                  class="alarmContentsArea">여기는 알람에 대한 내용이 들어갑니다. 신작이 나왔어요! 얼른 시청해보세요!</textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="userBtn" id="user">
            <img id="userBtnImg" src="img/user.png">
            <div id="menues">
                <div id="usermenu">
                    <table id="usermenutb">
                        <tr>
                            <td colspan="3" style="text-align: left">
                                <input type="button" value="찜" class="menubtn"
                                       onclick="location.href='user.do?command=userlist&page=1'">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align: left">
                                <input type="button" value="시청기록" class="menubtn"
                                       onclick="location.href='user.do?command=userlist&page=2'">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align: left">
                                <input type="button" value="정보수정" class="menubtn"
                                       onclick="location.href='user.do?command=userlist&page=3'">
                            </td>
                        </tr>
                        <tr>
                            <td class="Btn" colspan="3" style="text-align: right;">
                                <input type="button" value="로그아웃" class="Logout">
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="adminmenu">
                    <table id="adminmenutb">
                        <tr>
                            <td colspan="3" style="text-align: left">
                                <input type="button" value="회원리스트" class="menubtn"
                                       onclick="location.href='admin.do?command=adminlist&pages=1'">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align: left">
                                <input type="button" value="신고리스트" class="menubtn"
                                       onclick="location.href='admin.do?command=adminlist&pages=2'">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align: left">
                                <input type="button" value="금지어" class="menubtn"
                                       onclick="location.href='admin.do?command=adminlist&pages=3'">
                            </td>
                        </tr>
                        <tr>
                            <td class="Btn" colspan="3" style="text-align: right;">
                                <input type="button" value="로그아웃" class="Logout">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </div>
</form>

</body>
</html>