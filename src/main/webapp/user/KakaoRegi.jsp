<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html><html>

<head>
	<title>소셜 회원가입</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
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
	
		window.onload = function(){
		  
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
									
					if(count>0) { return false; } 
			  });
		}
		
	</script>

</head>
<body>
<div id="wrap">
    <br><br>
    <h2>추가 정보 입력이 필요합니다.</h2>
    
    <form id="reg_form" method="post" action="../reg.do">
    
    	<input type="hidden" value="kakaoreg" name="command">
    
        <table>
 
            <tr>
                <td>휴대전화</td>
                <td> <input type="text" name="Phone" class="updateval"> </td>
            </tr>
                
            <tr>
                <td>이름</td>
                <td> <input type="text" name="Name" maxlength="40" class="updateval"> </td>
            </tr>

            <tr>
                <td>생일</td>
                <td><input type="date" name="Birth" placeholder="YYYY-MM-DD" class="updateval" max="9999-12-31"></td>
            </tr>
                
            <tr>
                <td>성별</td>
                <td>
                    <input type="radio" name="Gender" value="남" checked>남자&nbsp;
                    <input type="radio" name="Gender" value="여">여자
                </td>
            </tr>
         <!-- 
            <tr>
                <td>프로필 사진</td>
                    <td>
                    <label for="input-file" class="filebtn">업로드</label> &nbsp;
                    <span id="imgurl" name="ImgName"></span>
                    <input type="file" id="input-file" name="profile" accept="image/*">
                    </td>
            </tr> -->
 
       		<tr>
       			<td colspan="2">
		        	<input type="submit" value="완료" class="btn">
		        	<input type="button" value="취소" class="btn" onclick="location.href='../user/login.jsp'">
       			</td>
       		</tr>
        </table>
        <br>
    </form>
    
</div>
</body>
</html>