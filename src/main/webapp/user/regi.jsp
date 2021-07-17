<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html><html>

<head>
	<title>회원가입</title>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<style type="text/css">

		@font-face {

		    font-family: 'NEXON Lv1 Gothic OTF';
		    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}

	    #wrap{

	        width:550px;
	        margin-left:auto;
	        margin-right:auto;
	        text-align:center;
	    }

	    body{

	   		font-family:'NEXON Lv1 Gothic OTF';
	    	font-size: 20px;
	    	background-color:black;
	    	color: rgb(248, 211, 28);
	    }

	    table { position: absolute; left: center; }

	    td{

	        height:50px;
	        width:500px;
	        font-size: 15px;
	        background-color : black;
	        color : white;
	    }


	    #title{  color : white; }
	    input[type=file] { display: none; }

		.filebtn {

	       padding: 3px 10px;
	        background-color: #F8D31C;
	        border: none;
	        border-radius: 4px;
	        color: black;
	        font-family:'NEXON Lv1 Gothic OTF';
	        font-size: 15px;
	        cursor: pointer;
	   	}

		.btn{

		    padding: 3px 10px;
	        background-color: #F8D31C;
	        border: none;
	        border-radius: 4px;
	        color: black;
	        font-family:'NEXON Lv1 Gothic OTF';
	        font-size: 15px;
	        cursor: pointer;

		}

		.emailbtn{

		    padding: 4px 3px;
		    background-color: black;
		    border: none;
		    border-radius: 4px;
		    color: white;
		    font-family:'NEXON Lv1 Gothic OTF';
		    font-size: 16px;
		    cursor: pointer;
		}

		input[type=date]{

	        height:20px;
	        width:180px;
	        border-radius: 5px;
	        font-size: 15px;
	        border:2px solid #f8d31c;
	        background-color : black;
	        color : #A3A3A3;
	    }

	    input[type=text]{

	        height:20px;
	        width:180px;
	        border-radius: 5px;
	        font-size: 15px;
	        border:2px solid #f8d31c;
	        background-color : black;
	        color : white;
	    }

	    input[type=password]{

	        height:20px;
	        width:180px;
	        border-radius: 5px;
	        font-size: 15px;
	        border:2px solid #f8d31c;
	        background-color : black;
	        color : white;
	    }

	    tr > td:nth-child(1) { width: 200px; }
	    tr > td:nth-child(2) { text-align:left; padding-left:30px; }
	    #imgurl { color:white; }
	</style>

	<script>
	
		var idcheck = false;
	
		window.onload = function(){
		  
			  $("#input-file").change(function(e){
					$("#imgurl").html($('input[type=file]')[0].files[0].name);
			  });
			  
			  $("#reg_form").submit( function() {
					
					var count = 0;
					
					$(".updateval").each( function() {
						if( $(this).val()=="" || $(this).val() == null ) {
							count++;
							alert("빈 항목이 존재합니다.");
							$(this).focus();
							return;
						}
					});
					
					if(idcheck==false) {
						alert("아이디 중복체크를 해주세요.");
						return false;
					}	
					
					if(count>0) { return false; } 
			  });
		}
		
		function overlapcheck() {
			
			$.ajax({ 
				url:"../reg.do?command=idcheck&id=" + $("#UserID").val(), // session.getval(UserID);
				dataType:"json",
				success:function(msg) {
					if(msg.check == true) { 
						alert("이미 사용중인 아이디입니다.");
						idcheck = false;
					}
					else { 
						alert("사용가능한 아이디입니다.");
						idcheck = true;
					}
				},
				error:function() { alert("Error : Ajax Part"); }
			});
		}

	</script>

</head>

<body>

<div id="wrap">
    <br><br>
    <h2>회원가입</h2>
    
    <form id="reg_form" method="post" action="../reg.do" enctype="multipart/form-data">
    
    	<input type="hidden" value="reg" name="command">
    
        <table>
            <tr>
                <td>아이디</td>
                <td>
                    <input type="text" id="UserID" name="UserID" maxlength="20" class="updateval">
                    <input type="button" value="중복확인" class="btn" onclick="overlapcheck()">    
                </td>
            </tr>
                    
            <tr>
                <td>비밀번호</td>
                <td>
                    <input type="password" name="PW" maxlength="15" class="updateval">
                </td>
            </tr>
            
            <tr>
                <td>비밀번호 확인</td>
                <td>
                    <input type="password" name="PW2" maxlength="15" class="updateval">
                </td>
            </tr>

            <tr>
                <td>이메일</td>
                <td>
                    <input type="text" name="Email" maxlength="30" class="updateval">
                </td>
            </tr>

            <tr>
                <td>휴대전화</td>
                <td>
                    <input type="text" name="Phone" class="updateval">
                </td>
            </tr>
                
            <tr>
                <td>이름</td>
                <td>
                    <input type="text" name="Name" maxlength="40" class="updateval">
                </td>
            </tr>

            <tr>
                <td>생일</td>
                <td><input type="date" name="Birth" placeholder="YYYY-MM-DD" class="updateval"></td>
            </tr>
                
            <tr>
                <td>성별</td>
                <td>
                    <input type="radio" name="Gender" value="남" checked>남자&nbsp;
                    <input type="radio" name="Gender" value="여">여자
                </td>
            </tr>
                
            <tr>
                <td>닉네임</td>
                <td>
                    <input type="text" name="NickName" maxlength="40" class="updateval">
                </td>
                </tr>
                <tr>
                    <td>프로필 사진</td>
                    <td>
                    <label for="input-file" class="filebtn">업로드</label> &nbsp;
                    <span id="imgurl" name="ImgName"></span>
                    <input type="file" id="input-file" name="profile" accept="image/*">
                    </td>
                </tr>
        		<tr>
        			<td colspan="2">
			        	<input type="submit" value="가입" class="btn">
			        	<input type="button" value="취소" class="btn" onclick="location.href='login.jsp'">
        			</td>
        		</tr>
        </table>
        <br>
    </form>
    
</div>
</body>
</html>