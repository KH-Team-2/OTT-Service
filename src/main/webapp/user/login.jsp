<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>head</title>
<style>
	@font-face {
	    font-family: 'NEXON Lv1 Gothic OTF';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
    body{
        background: black;
        margin: 50px auto;
        width: 500px;
        height: 500px;
        border: rgb(248, 211, 28) solid 1px;
        color: rgb(248, 211, 28);
       font-family:'NEXON Lv1 Gothic OTF'; font-size: 20px; background-color:black;
    }
    .Btn{
        padding: 5px;
        background-color: #F8D31C;
        border: none;
        border-radius: 4px;
        color: black;
        font-family:'NEXON Lv1 Gothic OTF';
        font-size: 15px;
        margin: 10px 2px;
        width : 210px;
        }
    #logo{
    	text-align: center;
    }
    #logintb{
    	margin : 0 auto;
    }
    input[type=text]{
    	height:40px;
        width:200px;
        border-radius: 10px;
        font-size: 15px;
        border:3px solid #f8d31c;
        background-color : black;
        color : white;
        margin-bottom: 10px;
    }
    input[type=submit]{
    	margin-bottom: 0px;
    	border-bottom: 1px solid black;
    	cursor : pointer;
    }
    #regist{
    	margin-bottom: 0px;
    	margin-top : 0px;
    	background-color: black;
    	color: #F8D31C;
    	cursor : pointer;
    }
    #social{
    	margin-top : 20px;
    	text-align: center;
    }
    #idfind{
    	margin-left : 38%;
    }
    .Btn2{
    	margin-top : 10px;
    	color : white;
    	background : black;
    	border : none;
    	cursor: pointer;
    }
    .socialimg{
    	width : 40px;
    	height : 40px;
    	cursor : pointer;
    }
    #login{
    	text-align: center;
    }
</style>
</head>

<body>
    <div id="logo"><h2>로고</h2></div><br>
    <br>
    <div id="login">
	    <form action="" method="post">
	    	<table id="logintb">
		        <tr>
	    	        <td id="ID">
	        	        <input type="text" placeholder=" ID">
	            	</td>
	       		</tr>
	        	<tr>
	            	<td id="PW">
	                	<input type="text" placeholder=" PW">
	            	</td>
	            </tr>
	            <tr>
	                <td colspan="3" style="text-align: center;">
	                    <input type="submit" value="Login" class="Btn"><br>
				    	<input type="button" value="Join us" class="Btn" id="regist" onclick="location.href='regi.jsp'">
	                </td>
	            </tr>
	    	</table>
	    </form>
    </div>
	    <div id="social">
	    	<span>
	    		<img src="../img/Naver.png" id="naver" class="socialimg">
	    	</span>
	    	<span>
	    		<img src="../img/Google.png" id="google" class="socialimg">
	    	</span>
	    	<span>
	    		<img src="../img/KaKaO.png" id="kakao" class="socialimg">
	    	</span>
	    </div>
	   	<div id="find">
	    	<span>
	    		<input type="button" value=" ID찾기 " class="Btn2" id="idfind" onclick="location.href=''">
	    	</span>
	    	<span>
	    		<input type="button" value="PW찾기" class="Btn2" onclick="location.href=''">
	    	</span>
   	</div>
    
</body>
</html>