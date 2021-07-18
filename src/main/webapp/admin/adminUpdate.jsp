<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.dto.UserDto" %>
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

	#profileimg{
		border : 1px solid white;
		width : 150px;
		height : 150px;
	}
    select {
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
		var num= confirm("�߹��Ͻðڽ��ϱ�?");
		if(num){
			location.href="admin.do?command=userSecession&usernum="+${dto.userNum};
		}
	}
	$(function(){
		$("#updateform").submit( function() {
			
			var count = 0;
			
			$(".updateval").each( function() {
				if( $(this).val()=="" || $(this).val() == null ) {
					count++;
					alert("�� �׸��� �����մϴ�.");
					$(this).focus();
					return;
				}
			});
		});
	});
</script>
</head>
<body>
<div>
	<form action="adminUpdate.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="usernum" value="${dto.userNum }">
		<table>
		    <tr>
	            <td>������ ����</td>
	            <td>
	    	        <img src="" alt="" id="profileimg">
	            </td>
	        	<td style="vertical-align: bottom">
		            <label for="input-file" class="filebtn">���ε�</label><input type="file" id="input-file" name="profile" accept="image/*">
	        	</td>
	        </tr>
	        <tr>
	        	<td></td>
		        <td align="center">
		        	<input type="submit" value="������ ���� ����">
		        </td>
	        </tr>
		</table>
	</form>
	<form action="adminUpdate.do" method="post" id="updateform">
		<input type="hidden" name="usernum" value="${dto.userNum }">
		<input type="hidden" name="command" value="userUpdateform">
    <table>
        <tr>
            <td>ID</td>
            <td><input type="text" value="${dto.ID }" name="id" class="updateval"></td>
        </tr>
        <tr>
            <td>PW</td>
            <td><input type="password" name="pw" value="${dto.PW }" class="updateval"></td>
        </tr>
        <tr>
            <td>�̸�</td>
            <td><input type="text" name="name" value="${dto.name }" class="updateval"></td>
        </tr>
        <tr>
            <td>�������</td>
            <td><input type="date" name="birth" value="${dto.birth }" class="updateval"></td>
        </tr>
        <tr>
            <td>�޴���ȭ</td>
            <td><input type="text" name="phone" value="${dto.phone }" class="updateval"></td>
        </tr>
        <tr>
            <td>EMAIL</td>
            <td><input type="text" name="email" value="${dto.email }" class="updateval"></td>
        </tr>
        <tr>
            <td>����</td>
            <td><input type="radio" name="gender" value="M" <c:if test="${dto.gender eq 'M'}">checked</c:if>>�� <input type="radio" name="gender" value="W" <c:if test="${dto.gender eq 'W'}">checked</c:if>>��</td>
        </tr>
        <tr>
            <td>�г���</td>
            <td><input type="text" name="nickname" value="${dto.nickName }" class="updateval"></td>
        </tr>
        <tr>
            <td>ȸ�� ���</td>
            <td><select name="grade" class="updateval">
                <option value="ADMIN">ADMIN</option>
                <option value="USER" selected="selected">USER</option>
            </select></td>
        </tr>
        <tr>
            <td>
                <input type="button" value="ȸ���߹�" onclick="UserSecession();">
            </td>
            <td style="text-align:right">
                <input type="submit" value="�Ϸ�"> <input type="button" value="���" onclick="location.href='admin.do?command=User_Info&page=1'">
            </td>
        </tr>

    </table>
    </form>
</div>
</body>
</html>