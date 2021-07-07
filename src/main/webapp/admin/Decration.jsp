<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset:UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html><html>

<head>
	<meta charset="UTF-8">
	<title>Decration Page</title>
	
	<style>
	
		@font-face {
          font-family: 'NEXON Lv1 Gothic OTF';
          src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
          font-weight: normal;
          font-style: normal;
      	}
		        
        body { font-family:'NEXON Lv1 Gothic OTF'; font-size: 15px; background-color:black; }
         
        #dec_area {
         
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
        
        table { 
        	margin : auto; 
        	border-collapse: collapse;
        	background-color:black;
        	color:white;
        }
        th { background-color:#A3A3A3; color:black;  padding:5px; }
        td { background-color:white; color:white;  background-color:black; padding:5px; }
        
        img { width : 80px; heigth : 30px; }
            
	</style>
	
</head>

<body>

	<div id="dec_area">
	
		<table>
	
			<col width="60"> <col width="150"> <col width="250"> <col width="100"> <col width="100"> <col width="100">
			
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>리뷰 내용</th>
				<th>신고 횟수</th>
				<th>처리 판별</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5">등록된 신고글이 없습니다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.reviewNum }</td>
							<td>${dto.name }</td>
							<td>${dto.reviewInfo }</td>
							<td>${dto.count }</td>
							<td><img src="..\img\Yet.png?ver=1"></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
		</table>

		<br><br>
	
		<span style="font-size:20px; color:white;">&lt;&lt; &nbsp;&nbsp; <b>1</b> &nbsp;&nbsp; 2 &nbsp;&nbsp; 3 &nbsp;&nbsp; 4 &nbsp;&nbsp; 5  &nbsp;&nbsp; &gt;&gt;</span>
		
	</div>
	
</body>
</html>