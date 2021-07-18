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
    input[name=Phone_1] {
        text-align: center;
        height: 30px;
        width: 50px;
        border-radius: 5px;
        font-size: 15px;
        border: 2px solid #f8d31c;
        background-color: black;
        color: white;
    }

    input[name=Phone_2] {
        text-align: center;
        height: 30px;
        width: 80px;
        border-radius: 5px;
        font-size: 15px;
        border: 2px solid #f8d31c;
        background-color: black;
        color: white;
    }

    input[name=Phone_3] {
        text-align: center;
        height: 30px;
        width: 80px;
        border-radius: 5px;
        font-size: 15px;
        border: 2px solid #f8d31c;
        background-color: black;
        color: white;
    }

    input[name=Email_1], input[name=Email_2], input[name=Email_5] {
        height: 30px;
        width: 100px;
        border-radius: 5px;
        font-size: 15px;
        border: 2px solid #f8d31c;
        background-color: black;
        color: white;
    }
    #selectEmail{
        text-align: center;
        height: 45px;
        width: 120px;
        border-radius: 5px;
        font-size: 15px;
        border: 2px solid #f8d31c;
        background-color: black;
        color: white;
    }
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
		var num= confirm("추방하시겠습니까?");
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
					alert("빈 항목이 존재합니다.");
					$(this).focus();
					return;
				}
			});
		});
	});
	
	function checkNumber(event) {
        if (
            event.key >= 0 && event.key <= 9) {
            return true;
        }

        return false;
    }
</script>
</head>
<body>
<%
    UserDto dto = (UserDto) request.getAttribute("dto");
    String phone1 = dto.getPhone().substring(0, 3);
    String phone2 = dto.getPhone().substring(4, 8);
    String phone3 = dto.getPhone().substring(9, 13);
    int email = dto.getEmail().indexOf("@");
    int emailindex = dto.getEmail().length();
    String email1 = dto.getEmail().substring(0, email);
    String email2 = dto.getEmail().substring(email + 1, emailindex);


%>
<div>
	<form action="adminUpdate.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="usernum" value="${dto.userNum }">
		<table>
		    <tr>
	            <td>프로필 사진</td>
	            <td>
	    	        <img src="${dto.imgURL }" alt="" id="profileimg" onerror="this.src='https://ssl.pstatic.net/static/pwe/address/img_profile.png'">
	            </td>
	        	<td style="vertical-align: bottom">
		            <label for="input-file" class="filebtn">업로드</label><input type="file" id="input-file" name="profile" accept="image/*">
	        	</td>
	        </tr>
	        <tr>
	        	<td></td>
		        <td align="center">
		        	<input type="submit" value="프로필 사진 변경">
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
            <td>이름</td>
            <td><input type="text" name="name" value="${dto.name }" class="updateval"></td>
        </tr>
        <tr>
            <td>생년월일</td>
            <td><input type="date" name="birth" value="${dto.birth }" class="updateval"></td>
        </tr>
        <tr>
            <td>휴대전화</td>
<%--            <td><input type="text" name="phone" value="${dto.phone }" class="updateval"></td>--%>
            <td>
            <input type="tel" name="Phone_1" onkeypress="return checkNumber(event)" class="updateval"
                   minlength="3" maxlength="3" value="<%=phone1%>"> -
            <input type="tel" name="Phone_2" onkeypress="return checkNumber(event)" class="updateval"
                   minlength="3" maxlength="4" value="<%=phone2%>"> -
            <input type="tel" name="Phone_3" onkeypress="return checkNumber(event)" class="updateval"
                   minlength="4" maxlength="4" value="<%=phone3%>">
            </td>
        </tr>
        <tr>
            <td>EMAIL</td>
            <td>
                <input type="text" name="Email_1" maxlength="30" class="updateval" value="<%=email1%>">
                <span>@</span>
                <input type="text" name="Email_5" id="str_email02" style="width:100px;"
                       value="<%=email2%>">
                <select style="margin-right:10px" name="selectEmail"
                        id="selectEmail">
                    <option value="1" selected>직접입력</option>
                    <option value="naver.com">naver.com</option>
                    <option value="hanmail.net">hanmail.net</option>
                    <option value="hotmail.com">hotmail.com</option>
                    <option value="nate.com">nate.com</option>
                    <option value="yahoo.co.kr">yahoo.co.kr</option>
                    <option value="empas.com">empas.com</option>
                    <option value="dreamwiz.com">dreamwiz.com</option>
                    <option value="freechal.com">freechal.com</option>
                    <option value="lycos.co.kr">lycos.co.kr</option>
                    <option value="korea.com">korea.com</option>
                    <option value="gmail.com">gmail.com</option>
                    <option value="hanmir.com">hanmir.com</option>
                    <option value="paran.com">paran.com</option>
                </select>
                <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
                <script type="text/javascript">
                    $('#selectEmail').change(function () {
                        $("#selectEmail option:selected").each(function () {
                            if ($(this).val() == '1') {
                                $("#str_email02").val('');
                                $("#str_email02").attr("disabled", false);
                            } else {
                                $("#str_email02").val($(this).text());
                                $("#str_email02").attr("disabled", true);
                                console.log($('#str_email02').val());
                            }
                        });
                    });

                </script>
            </td>
        </tr>
        <tr>
            <td>성별</td>
            <td><input type="radio" name="gender" value="M" <c:if test="${dto.gender eq 'M'}">checked</c:if>>남 <input type="radio" name="gender" value="W" <c:if test="${dto.gender eq 'W'}">checked</c:if>>여</td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname" value="${dto.nickName }" class="updateval"></td>
        </tr>
        <tr>
            <td>회원 등급</td>
            <td><select name="grade" class="updateval">
                <option value="ADMIN">ADMIN</option>
                <option value="USER" selected="selected">USER</option>
            </select></td>
        </tr>
        <tr>
            <td>
                <input type="button" value="회원추방" onclick="UserSecession();">
            </td>
            <td style="text-align:right">
                <input type="submit" value="완료"> <input type="button" value="취소" onclick="location.href='admin.do?command=User_Info&page=1'">
            </td>
        </tr>

    </table>
    </form>
</div>
</body>
</html>