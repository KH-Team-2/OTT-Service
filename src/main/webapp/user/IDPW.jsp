<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<html>

<head>
	<title>ID/PW 찾기</title>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<style>
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
		#selectEmail, #selectEmail2 {
			text-align: center;
			height: 28px;
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
			
		#wrap{
		    width:420px;
		    margin-left:auto; 
		    margin-right:auto;
		}
		    
		body{
	        background: black;
	        margin: 50px auto;
	        width: 700px;
	        height: 750px;
	        border: rgb(248, 211, 28) solid 1px;
	        color: rgb(248, 211, 28);
	        font-family:'NEXON Lv1 Gothic OTF'; background-color:black;   
	    }
	
	    table{ width: 470px; }
	    
	    .title{
	    	width:100px;
	        color : white;
	        background-color:black;
	    }
	    
	    body {
	        background: black;
	        color: rgb(248, 211, 28);
	    }
	
	    hr.dot{
	        border: none; border: 1px dashed white;
	        width: 400px;
	    }
	
	    .Btn{
	        position: relative;
	        left: 209px;
	        padding: 3px 10px;
	        background-color: #F8D31C;
	        border: none;
	        border-radius: 4px;
	        color: black;
	        font-family:'NEXON Lv1 Gothic OTF';
	        font-size: 15px;
	        cursor: pointer;
	     }
	
		.Btn2{
	        position: relative;
	        padding: 3px 10px;
	        background-color: #F8D31C;
	        border: none;
	        border-radius: 4px;
	        color: black;
	        font-family:'NEXON Lv1 Gothic OTF';
	        font-size: 15px;
	        cursor: pointer;
	     }
	
	    .Email{
	        padding: 3px 10px;
	        background-color: black;
	        font-family:'NEXON Lv1 Gothic OTF';
	        font-size: 15px;
	        color: white;
	        border:0px;
	    }
	    
	    .Code{
	        padding: 3px 3px;
	        background-color: #F8D31C;
	        font-family:'NEXON Lv1 Gothic OTF';
	        font-size: 15px;
	        border-radius: 4px;
	    }
	
	    .textbox{
	        height:40px;
	        width:240px;
	        border-radius: 10px;
	        font-size: 15px;
	        border:2px solid #f8d31c;
	        background-color : black;
	        color : white;
	    }
	    
	    .textbox2{
	        height:40px;
	        width:240px;
	        border-radius: 10px;
	        font-size: 15px;
	        border:2px solid #f8d31c;
	        background-color : black;
	        color : white;
	    }
	    
	</style>
	
	<script>

		function checkNumber(event) {
			if (
					event.key >= 0 && event.key <= 9) {
				return true;
			}

			return false;
		}

		var codechk = false;
		
		window.onload = function(){
			  
			  $("#pwform").submit( function() {						
					if(codechk==false) {
						alert("코드를 확인해주세요.");
						return false;
					}	
			  });
		}
	
		function sendcode() {
			var Email = $('#str_email01').val() + "@" + $('#str_email03').val();
			$.ajax({
				url:"../searchpw.do?command=SendCode&id=" + $("#UserID2").val() + "&name=" + $("#Name").val() + "&email=" + Email,
				async: false,
				success:function(msg) {
					if(msg == false) { alert("입력된 정보가 올바르지 않습니다."); }
					else { alert("코드를 전송하였습니다.") }
				},
				error:function() { alert("[ AJAX Error ]") }
			});
		}

		function codecheck() {
			$.ajax({ 
				url:"../searchpw.do?command=CodeCheck&code=" + $("#Code").val(),
				async: false,
				success:function(msg2) {			
					var check = msg2.toUpperCase().trim().toString().replace(/\s/g, ' ');
					if(check=="TRUE") { alert("인증이 완료되었습니다. "); codechk = true; }
					else { alert("올바른 코드를 입력해주세요."); codechk = false;}
				},
				error:function() { alert("[ AJAX Error ]") }
			});		
		}
		
		$(function(){
			$("#cancel1, #cancel2").click(function(){
				location.href="login.jsp"
			});
			
		});
	</script>
	
</head>

<body>
	
	<div id="wrap">
	
	    <br> <h2>ID 찾기</h2> <br>
	    
	    <form action="../user.do" method="get">
	    
	    	<input type="hidden" name="command" value="searchID">
	    	
	        <table>
	        
	            <tr>
	                <td class="title">이름</td>
	                <td> <input type="text" name="Name" maxlength="40" class="textbox"> </td>
	            </tr>
	            
	            <tr>
	                <td class="title"><br>Email</td>
					<td>
						<br><input type="text" name="Email_1" maxlength="30" class="updateval">
						<span>@</span>
						<input type="text" name="Email_5" id="str_email02" style="width:100px;" readonly
							   value="naver.com"><br>
						<select style="margin-right:10px" name="selectEmail"
								id="selectEmail">
							<option value="1">직접입력</option>
							<option value="naver.com" selected>naver.com</option>
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
	                <td class="title"><br>휴대전화</td>
					<td>
						<br><input type="tel" name="Phone_1" onkeypress="return checkNumber(event)" class="updateval"
							   minlength="3" maxlength="3"> -
						<input type="tel" name="Phone_2" onkeypress="return checkNumber(event)" class="updateval"
							   minlength="3" maxlength="4"> -
						<input type="tel" name="Phone_3" onkeypress="return checkNumber(event)" class="updateval"
							   minlength="4" maxlength="4">
					</td>
	            </tr>
	            
	            <tr>
	                <td colspan="3">
	                	<br>
	                    <input type="submit" value="확인" class="Btn">
	                    <input type="button" value="취소" class="Btn" id="cancel1">
	                    <br>
	            	</td>
	            </tr>
			</table>
		</form>
		
		<br><hr><br>
	
	    <h2>PW 찾기</h2>
	    
	    <form action="../searchpw.do?command=ResetPW" id="pwform" method="post">
	    
	   		<input type="hidden" name="command" value="SearchPW">
	   		
	   		<table>
	   		
	           <tr>
	               <td class="title">ID</td>
	               <td> <input type="text" id="UserID2" name="UserID3" maxlength="20" class="textbox2"></td>
	           </tr>
	           
	           <tr>
	               <td class="title">이름</td>
	               <td> <input type="text" id="Name" name="Name" maxlength="40" class="textbox2"> </td>
	           </tr>
	           
	           <tr>
	               <td class="title">Email</td>
<%--	               <td> <input type="text" id="Email" name="Email" maxlength="30" class="textbox2"></td>--%>
				   <td><input type="text" name="Email_1" id="str_email01" maxlength="30" class="updateval">
					   <span>@</span>
					   <input type="text" name="Email_2" id="str_email03" style="width:100px;" readonly
							  value="naver.com"><br>
					   <select style="margin-right:10px" name="selectEmail"
							   id="selectEmail2">
						   <option value="1">직접입력</option>
						   <option value="naver.com" selected>naver.com</option>
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
						   $('#selectEmail2').change(function () {
							   $("#selectEmail2 option:selected").each(function () {
								   if ($(this).val() == '1') {
									   $("#str_email03").val('');
									   $("#str_email03").attr("disabled", false);
								   } else {
									   $("#str_email03").val($(this).text());
									   $("#str_email03").attr("disabled", true);
								   }
							   });
						   });

					   </script></td>
	               <td><input type="button" name="CodeSend" class="btn2" value="코드 전송" onclick="sendcode()"></td>
	           </tr>
	           
	           <tr>
	               <td class="title">Code</td>
	               <td><input type="text" id="Code" name="Code" maxlength="40" class="textbox2"></td>
	               <td><input type="button" name="CodeCheck" class="btn2" value="코드 확인" onclick="codecheck()"></td>
	           </tr>
	   
	           <tr>
	               <td colspan="3" ><br>
	                   <input type="submit" value="확인" class="Btn">
	                   <input type="button" value="취소" class="Btn" id="cancel2">
	               </td>
	           </tr>
	    	</table>
	    </form>
	    
	    <br>
	    
	</div>
	
</body>

</html>