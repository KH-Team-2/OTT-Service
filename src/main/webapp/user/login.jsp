<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>head</title>
<style>
    body{
        background: black;
        margin: 50px auto;
        width: 500px;
        height: 500px;
        border: rgb(248, 211, 28) solid 1px;
        color: rgb(248, 211, 28);
       
    }
    #logo{
        text-align:center;
        cursor: pointer;
    }
    #login{
        text-align:center;
    }
    #socal{
        text-align:center;
    }
    #loginbtn {
        text-align: center;
}

    input.img-button {
        background: url( "../img/KaKaO.png" ) no-repeat;
        border: none;
        width: 50px;
        height: 50px;
        cursor: pointer;
    }
       input.img-button2 {
        background: url( "../img/Naver.png" ) no-repeat;
        border: none;
        width: 50px;
        height: 50px;
        cursor: pointer;
    }
    input.img-button3 {
        background: url( "../img/Google.png" ) no-repeat;
        border: none;
        width: 50px;
        height: 50px;
        cursor: pointer;
    }
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#logo").click(function(){
			location.href="../index/index.jsp";
		});
	});
</script>
</head>

<body>
    <div id="logo"><h2>로고</h2></div><br>
    <br>
    <div id="login">
        <tr>
            <td id="ID">
                <input type="text" placeholder="ID">
            </td>
        </tr>
        <br>
        <br>
        <tr>
            <td id="PW">
                <input type="text" placeholder="PW">
            </td>
            </tr>

    </div>
    <br>
    <div id="loginbtn">
        <input type="button" value="Login">
        </div>    
    <br>
    <div id="socal">
        <input type="button" value="회원가입" onclick="location.href='regi.jsp'">
        <tr>
            <td id="kakao">
                <input type="button" class="img-button">
            </td>
            <td id="kakao">
                <input type="button" class="img-button2">
            </td>
            <td id="kakao">
                <input type="button" class="img-button3">
            </td>
        </tr>

    </div>

</body>
</html>