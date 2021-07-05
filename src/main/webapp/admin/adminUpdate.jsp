<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	body { font-family:'NEXON Lv1 Gothic OTF'; font-size: 20px; background-color:black;}

    div {
    	position: absolute;
    	top: -25px;
    	color: white;
        padding: 3px;
    }
    div table {
        padding: 3px;
        border-spacing: 18px;
  		border-collapse: separate;
    }
    
    div table td {
        padding: 3px;
        border: solid 0px white;
        collapse: collapse;
    }

    input[type="text"], input[type="password"], input[type="date"] {
        width: 200px;
    }
    input[type=image] {
    	position: relative;
    	top: 35px;
        width: 80px;
        height: 80px;
    }
    input[type=file] {
    	display: none;
    }   
     
        
    input[type=button], input[type=submit], .filebtn {
 		padding: 3px 10px;
  		background-color: #F8D31C;
  		border: none;
  		border-radius: 4px;
  		color: black;
  		font-family:'NEXON Lv1 Gothic OTF';
  		font-size: 15px;
  		cursor: pointer;
	}
	
	input[type=text], input[type=date], input[type=password]{
         
        height:40px;
        width:200px;
        border-radius: 10px;
        font-size: 15px;
        border:3px solid #f8d31c;
        background-color : black;
        color : white;
    }

</style>
</head>
<body>
<div>
	<form action="" method="post">
		<input type="hidden" name="usernum" value="${dto.UserNum }">
		<input type="hidden" name="command" value="userUpdateform">
    <table>
        <tr>
            <td>ID</td>
            <td><input type="text" value="${dto.ID }" name="id"></td>
        </tr>
        <tr>
            <td>PW</td>
            <td><input type="password" name="pw" value="${dto.PW }"></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><input type="text" name="name" value="${dto.Name }"></td>
        </tr>
        <tr>
            <td>생년월일</td>
            <td><input type="date" name="birth" value="${dto.Birth }"></td>
        </tr>
        <tr>
            <td>휴대전화</td>
            <td><input type="text" name="phone" value="${dto.Phone }"></td>
        </tr>
        <tr>
            <td>EMAIL</td>
            <td><input type="text" name="email" value="${dto.Email }"></td>
        </tr>
        <tr>
            <td>성별</td>
            <td><input type="radio" name="sex" value="남">남 <input type="radio" name="sex" value="여">여</td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname" value="${dto.NickName }"></td>
        </tr>
        <tr>
            <td>프로필 사진</td>
            <td>
            <label for="input-file" class="filebtn">업로드</label><input type="file" id="input-file" name="profile" accept="image/*">
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="회원탈퇴" onclick="">
            </td>
            <td style="text-align:right">
                <input type="submit" value="완료"> <input type="button" value="취소" onclick="location.href='admin.do?command=userlist'">
            </td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>