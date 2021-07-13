<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset:UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
         #deletebtn{
        	color:black;
            background-color:white;
            border-color:white;
            
            border-radius: 10px;
            font-family:'NEXON Lv1 Gothic OTF';
            font-weight:bold;
            padding: 1px 12px;
        }
	</style>
	
</head>

<body>

	<div id="ban_area">
	
		<form action="admin.do?command=addFBWord" method="post">
			<input type="text" placeholder="  금지어 입력" name="FBWords"> &nbsp;&nbsp;

			<select name="reason">
				<option value="비속어">비속어</option>
				<option value="성인">성인</option>
				<option value="저작권">저작권</option>
				<option value="기타">기타</option>
			</select> &nbsp;&nbsp;
			
			<input type="submit" value="등록" id="temp22" class="btn-blue-eft">
		</form>
		
		<br><br>

		<table>
	
			<col width="70"> <col width="160"> <col width="160"> <col width="110">
			
			<tr>
				<th colspan="2">금지어</th>
				<th>사유</th>
				<th>삭제</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="4">등록된 금지어가 없습니다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td colspan="2">${dto.FBWords }</td>
							<td>${dto.reason }</td>
							<td><button id="deletebtn" onclick="location.href='admin.do?command=FBWorddelete&FBWords=${dto.FBWords}'">삭제</button></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		
	
		<br><br>
		
		<jsp:include page="../admin/Ban_Wordpaging.jsp">
			<jsp:param value="${paging.page }" name="page"/>
			<jsp:param value="${paging.beginPage }" name="beginPage"/>
			<jsp:param value="${paging.endPage }" name="endPage"/>
			<jsp:param value="${paging.prev }" name="prev"/>
			<jsp:param value="${paging.next }" name="next"/>
		</jsp:include>
		
	</div>
	
</body>
</html>