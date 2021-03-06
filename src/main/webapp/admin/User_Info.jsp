<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset:UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="com.dto.UserDto" %>
<!DOCTYPE html><html>

<head>
	<meta charset="UTF-8">
	<title>User Search Page</title>
	
	<style>
	
		@font-face {
          font-family: 'NEXON Lv1 Gothic OTF';
          src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
          font-weight: normal;
          font-style: normal;
      	}
		        
        body { font-family:'NEXON Lv1 Gothic OTF'; font-size: 15px; background-color:black; }
	
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
         
         #search_area {
         
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
        td { background-color:white; color:white;  background-color:black; padding:8px; }
        #updatebtn{
        	color:black;
            background-color:white;
            border-color:white;
            
            border-radius: 10px;
            font-family:'NEXON Lv1 Gothic OTF';
            font-weight:bold;
            padding: 1px 12px;
        }
        a{
        	text-decoration: none;
        }
	</style>
</head>

<body>
	<div id="search_area">

		<form action="admin.do?command=adminusersearch" method="post">
			<input type="text" placeholder="  Search !!" name="info"> &nbsp;&nbsp;
			<input type="submit" value="??????" class="btn-blue-eft">
		</form>
		
		<br><br>
		
		<table>
	
			<col width="70"> <col width="160"> <col width="160"> <col width="130"> <col width="80">
			
			<tr>
				<th>??????</th>
				<th>?????????</th>
				<th>?????????</th>
				<th>??????</th>
				<th>??????</th>
				<th></th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5">????????? ????????? ????????????</td>
					</tr>
				</c:when>
				<c:otherwise>
					<jsp:useBean id="list" scope="request" type="java.util.List"/>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.userNum }</td>
							<td>${dto.ID }</td>
							<td>${dto.nickName }</td>
							<td>${dto.name }</td>
							<td>${dto.gender }</td>
							<td><button id="updatebtn" onclick="location.href='admin.do?command=adminupdate&UserNum=${dto.userNum}'">??????</button></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	</table>
	
	<br><br>
	<jsp:include page="../admin/UserInfo_paging.jsp">
			<jsp:param value="${paging.page }" name="page"/>
			<jsp:param value="${paging.beginPage }" name="beginPage"/>
			<jsp:param value="${paging.endPage }" name="endPage"/>
			<jsp:param value="${paging.prev }" name="prev"/>
			<jsp:param value="${paging.next }" name="next"/>
		</jsp:include>	
	
	</div>
	
</body>
</html>