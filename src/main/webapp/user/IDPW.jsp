<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<html>

<head>
	<title>ID/PW 찾기</title>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<style> 
	
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
			$.ajax({ 
				url:"../searchpw.do?command=SendCode&id=" + $("#UserID2").val() + "&name=" + $("#Name").val() + "&email=" + $("#Email").val(),
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
	</script>
	
</head>

<body>
	
	<div id="wrap">
	
	    <br> <h2>ID 찾기</h2> <br>
	    
	    <form action="../user.do" method="post">
	    
	    	<input type="hidden" name="command" value="searchID">
	    	
	        <table>
	        
	            <tr>
	                <td class="title">이름</td>
	                <td> <input type="text" name="Name" maxlength="40" class="textbox"> </td>
	            </tr>
	            
	            <tr>
	                <td class="title">Email</td>
	                <td> <input type="text" name="Email" maxlength="30" class="textbox"> </td>
	            </tr>
	
	            <tr>
	                <td class="title">휴대전화</td>
	                <td> <input type="text" name="Phone" class="textbox"></td>
	            </tr>
	            
	            <tr>
	                <td colspan="3">
	                	<br>
	                    <input type="submit" value="확인" class="Btn">
	                    <input type="button" value="취소" class="Btn">
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
	               <td> <input type="text" id="Email" name="Email" maxlength="30" class="textbox2"></td>
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
	                   <input type="button" value="취소" class="Btn">
	               </td>
	           </tr>
	    	</table>
	    </form>
	    
	    <br>
	    
	</div>
	
</body>

</html>