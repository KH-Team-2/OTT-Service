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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function UserSecession(){
		var num= confirm("추방하시겠습니까?");
		if(num){
			location.href="admin.do?command=userSecession&usernum="+${dto.userNum};
		}
	}
	
</script>
</head>
<body>
<div>
	<form action="adminUpdate.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="usernum" value="${dto.userNum }">
		<input type="hidden" name="userdate" value="${dto.userDate }">
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
            <td><input type="text" name="name" value="${dto.name }"></td>
        </tr>
        <tr>
            <td>생년월일</td>
            <td><input type="date" name="birth" value="${dto.birth }"></td>
        </tr>
        <tr>
            <td>휴대전화</td>
            <td><input type="text" name="phone" value="${dto.phone }"></td>
        </tr>
        <tr>
            <td>EMAIL</td>
            <td><input type="text" name="email" value="${dto.email }"></td>
        </tr>
        <tr>
            <td>성별</td>
            <td><input type="radio" name="sex" value="M">남 <input type="radio" name="sex" value="W">여</td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname" value="${dto.nickName }"></td>
        </tr>
        <tr>
            <td>프로필 사진</td>
            <td>
            <label for="input-file" class="filebtn">업로드</label><input type="file" id="input-file" name="profile" accept="image/*">${dto.imgURL }
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="회원추방" onclick="UserSecession();">
            </td>
            <td style="text-align:right">
                <input type="submit" value="완료"> <input type="button" value="취소" onclick="location.href='admin.do?command=User_Info'">
            </td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>