<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.dto.UserDto" %>
<%@ page import="com.dto.WishListDto" %>
<%@ page import="com.biz.wish.WishBiz" %>
<%@ page import="com.biz.wish.WishBizImpl" %>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

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
	        background-color: black;
	        color: white;
	        position: absolute;
	        top: -20px;
	    }
	
	    table {
	        text-align: left;
	        position: relative;
	        left: 130px;    top: 0;
	        padding: 3px;
	        border-spacing: 10px;
	  		border-collapse: separate;
	    }
	
	    table td {
	        padding: 3px;
	    }
	
	    ul {
	        position: absolute;
	        left: 130px;
	        bottom: 80px;
	    }
	
	    .select, #delbtn {
	        opacity: 0;
	    }
	
	    #delbtn {
	        position: relative;
	        top: 3px;
	    }
	
	    #allchk {
	        position: relative;
	        left: 2px;
	    }
	
	    .blind {
	        visibility: hidden;
	    }
	
	    .pagination {
	        display: inline-block;
	        text-align: center;
	        position: relative;
	        bottom: 20px;
	        left: 160px;
	    }
	
	    .pagination li {
	        display: inline;
	        text-align: center;
	    }
	
	    .pagination a {
	        color: white;
	        float: left;
	        padding: 8px 16px;
	        text-decoration: none;
	    }
	
	    img{
	        width: 25px;
	        height: 25px;
	        
	    }
	    button {
	    	position: relative;
	    	left: 5px;
	    	padding: 3px 10px;
	  		background-color: #F8D31C;
	  		border: none;
	  		border-radius: 4px;
	  		color: black;
	  		font-family:'NEXON Lv1 Gothic OTF';
	  		font-size: 15px;
	  		cursor: pointer;
	    }
		#paging{
			position: relative;
		}
		a{
			text-decoration: none;
		}
		.delimgbtn{
			background: none;
		}
	</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	
		$(function(){
			
		    $("#delshow").click(function(){
		       	if( $("#delbtn").css("opacity") == 1 ) { 
		       		$(".select,#delbtn").css("opacity", "0"); 
		       		$("input[type=checkbox]").prop("disabled",true);
		       	}
		       	else if($("#delbtn").css("opacity") == 0 ) { 
		       		$(".select,#delbtn").css("opacity", "1"); 
		       		$("input[type=checkbox]").prop("disabled",false);
		       	}
		    });
		    
		    $("#allchk").change(function(){
		        if($("#allchk").is(":checked")){ $(".chk").prop("checked",true); }
		        else{ $(".chk").prop("checked",false); }
		    });
		    
		    $(".chk").change(function() {
		        if( !$(this).is(":checked") ){ $("#allchk").prop("checked",false ); }
		    });
		    
		    $(".alarm > img").click(function(){
                $(this).attr("src", function(index, attr){
                    if(attr.match("alarm.png")){
                        return attr.replace("alarm.png","alarm(yellow).png");
                    } else {
                        return attr.replace("alarm(yellow).png","alarm.png");
                    }
                })
            });
		   
		});
	
</script>
</head>
<body>
<%
    String usernum = request.getParameter("usernum");

	UserDto udto = (UserDto) session.getAttribute("dto");
	
	WishBiz biz = new WishBizImpl();
	WishListDto wishdto = biz.wishfound(udto.getUserNum(),dto.getWishnum());
	
	if(wishdto==null) { %>
		<button id="wishaddbtn"><img src="img/wish2.png" id="wishimg"></button>
	<%
	} else { %>
		<button id="wishdelbtn"><img src="img/wish1.png" id="wishimg"></button>
	<% } %>
	
    <div class="area">
        
        <table>
            <col width="10"><col width="300"><col width="25">
            <thead>
                <tr>
                    <th class="select"><input type="checkbox" id="allchk"></th>
                    <th class="title"><span class="blind"></span></th>
                    <th class="alarm"><span class="blind"></span></th>
                    <th class="delete"><span class="blind"></span></th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
					<c:when test="${empty list }">
					<tr>
						<td colspan="4">찜한 영상이 없습니다</td>
					</tr>
					</c:when> 
					<c:otherwise>
						<c:forEach items="${list }" var="dto">
						<tr>
							<td class="select"><input type="checkbox" class="chk"></td>
							<td><a target="_parent" href="search.do?command=detail&title=${dto.title }&page=1">${dto.title }</a></td>
							<td class="alarm"><img src="http://www.khproject.kr/OTT_Service/img/alarm.png" alt="알림"></td>
							<td class="delete"><button class="delimgbtn" onclick="location.href='user.do?command=wishnumdel&wishnum=${dto.wishnum}&usernum=<%=usernum%>'"><img src="http://www.khproject.kr/OTT_Service/img/delete.png" alt="삭제"></button></td>
						</tr>
						</c:forEach>
					</c:otherwise>               
                </c:choose>
                
                <tr>
                    <td colspan="3">
                        <img src="http://www.khproject.kr/OTT_Service/img/delete.png" id="delbtn" class="delete">
                        <button id="delshow">선택</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <br>
        <br>
        <br>
        <jsp:include page="../user/WishList_paging.jsp">
        	<jsp:param name="usernum" value="<%=usernum%>"/>
			<jsp:param value="${paging.page }" name="page"/>
			<jsp:param value="${paging.beginPage }" name="beginPage"/>
			<jsp:param value="${paging.endPage }" name="endPage"/>
			<jsp:param value="${paging.prev }" name="prev"/>
			<jsp:param value="${paging.next }" name="next"/>
		</jsp:include>
    
    </div>


</body>
</html>