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
	<form action="user.do" method="post">
  		<input type="hidden" name="usernum" value="${dto.userNum }">
		<input type="hidden" name="command" value="Update">
    <table>
        <tr>
            <td>ID</td>
            <td>${dto.ID }</td>
        </tr>
        <tr>
            <td>PW</td>
            <td><input type="password" name="pw" placeholder="��й�ȣ"></td>
        </tr>
        <tr>
            <td>�̸�</td>
            <td>${dto.Name }</td>
        </tr>
        <tr>
            <td>�������</td>
            <td>${dto.birth }</td>
        </tr>
        <tr>
            <td>�޴���ȭ</td>
            <td><input type="text" name="phone" placeholder="�ڵ��� ��ȣ�� �Է��ϼ���."></td>
        </tr>
        <tr>
            <td>EMAIL</td>
            <td><input type="text" name="email" placeholder="�̸��� �ּҸ� �Է��ϼ���."></td>
        </tr>
        <tr>
            <td>����</td>
            <td><input type="radio" name="gender" value="��">�� <input type="radio" name="gender" value="��">��</td>
        </tr>
        <tr>
            <td>�г���</td>
            <td><input type="text" name="nickname" placeholder="�г����� �Է��ϼ���."></td>
        </tr>
        <tr>
            <td>������ ����</td>
            <td>
            <label for="input-file" class="filebtn">���ε�</label><input type="file" id="input-file" name="imgurl">
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="ȸ��Ż��" onclick="">
            </td>
            <td style="text-align:right">
                <input type="submit" value="�Ϸ�"> <input type="button" value="���" onclick="">
            </td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>