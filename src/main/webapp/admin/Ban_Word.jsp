<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset:UTF-8"); %>
    
<!DOCTYPE html><html>

<head>
	<meta charset="UTF-8">
	<title>Ban Word Page</title>
	
	<style>
	
		@font-face {
          font-family: 'NEXON Lv1 Gothic OTF';
          src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
          font-weight: normal;
          font-style: normal;
      	}
		        
        body { font-family:'NEXON Lv1 Gothic OTF'; font-size: 15px; background-color:black; }
        
        #temp22 { margin-top:5px;
        }
	
		.btn-blue-eft {
		
			width : 70px;
			height: 40px;
			
            color:black;
            background-color:#f8d31c;
            border-color:#f8d31c;
            
            font-size:20px;
            
            border-radius: 10px;
            font-family:'NEXON Lv1 Gothic OTF';
            font-weight:bold;
                    
         }
         
        .btn-blue-eft:hover {
        
            color:black;
            background-color:#F0E68C;
            border-color:#F0E68C;
            box-shadow:0 0 0 .3rem rgba(240,230,140,0.5);
            font-family:'NEXON Lv1 Gothic OTF';
            font-weight:bold;
         }
         
        .btn-blue-eft.disabled,.btn-blue-eft:disabled { 
        
            color:black;
            background-color:#f8d31c;
            border-color:#f8d31c;
            
            border-radius: 10px;
            font-family:'NEXON Lv1 Gothic OTF';
            font-weight:bold;

         }
         
         #ban_area {
         
         	width : 700px;
         	height: 700px;
         
         	left:0;   right:0;  margin-left:auto;   margin-right:auto; 
            top: 0;  bottom:0;   margin-top:auto;  margin-bottom:auto;
            
			text-align:center;
			
			padding : auto;
            
            position:absolute;
            
            background-color:black;
            
            border-radius: 50px
         }
         
         input[type=text]{
         
            height:40px;
            width:350px;
            border-radius: 10px;
            font-size: 20px;
            border:3px solid #f8d31c;
            background-color : black;
            color : white;
        }
        
        table { 
        	margin : auto; 
        	border-collapse: collapse;
        	background-color:black;
        	color:white;
        }
        th { background-color:#A3A3A3; color:black;  padding:5px; }
        td { background-color:white; color:white;  background-color:black; padding:5px; }
        
        .ban_word {
        
        	width : 400px;
        	height : 40px;
        	
        	font-size : 30px;
        	
        	margin : auto;
        	
        	text-align : center;
        	
        	border-bottom:3px solid #F8941C;
        }

        select {
        	font-size : 17px;
        	width : 80px;
        	height : 40px;
        	border-radius: 10px;
        	background-color : #f8d31c;
        	color:black;
        	font-family:'NEXON Lv1 Gothic OTF';
        	font-weight:bold;
        }
        
	</style>
	
</head>

<body>

	<div id="ban_area">
	
		<form action="ban_word_input.jsp" method="post">
			<input type="text" placeholder="  금지어 입력"> &nbsp;&nbsp;

			<select>
				<option>비속어</option>
				<option>성인</option>
				<option>저작권</option>
				<option>기타</option>
			</select> &nbsp;&nbsp;
			
			<input type="button" value="등록" id="temp22" class="btn-blue-eft">
		</form>
		
		<br><br>

		<table>
	
			<col width="70"> <col width="160"> <col width="160"> <col width="110">
			
			<tr>
				<th>번호</th>
				<th>금지어</th>
				<th>사유</th>
				<th>삭제</th>
			</tr>
			
			<tr>
				<td>1</td>
				<td>게시판</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>2</td>
				<td>신발</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>3</td>
				<td>볍씨</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>4</td>
				<td>M E</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>5</td>
				<td>게시판</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>6</td>
				<td>신발</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>7</td>
				<td>볍씨</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>8</td>
				<td>M E</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>9</td>
				<td>게시판</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
			<tr>
				<td>10</td>
				<td>신발</td>
				<td>비속어</td>
				<td><img src="../img/delete.png" width="25px" height="25px" ></td>
			</tr>
			
		</table>
		
	
		<br><br>
		
		<span style="font-size:20px; color:white;">
			&lt;&lt; &nbsp;&nbsp;
			<b>1</b> &nbsp;&nbsp;
			2 &nbsp;&nbsp;
			3 &nbsp;&nbsp;
			4 &nbsp;&nbsp;
			5 &nbsp;&nbsp;
			&gt;&gt;
		</span>
		
	</div>
	
</body>
</html>