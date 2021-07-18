<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	        cursor: pointer;
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
			cursor: pointer;
		}
	</style>
<%
    String usernum = request.getParameter("usernum");

%>
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
		   $(".delimgbtn").click(function(){
			   console.log($(this).val());
			   var usernum = <%=request.getParameter("usernum")%>;
			   var wishnum = $(this).val();
			   var params = "?command=wishnumdel&wishnum="+wishnum+"&usernum="+usernum;
			   $.ajax({
				   url : "user.do"+params,
				   type : "post",
				   success : function(){
					   alert("삭제 완료");
                       window.parent.location.reload();
				   },
				   error : function(){
					   alert("실패");
				   }
			   });
		   });
		   $("#delbtn").click(function () {
	            var checkArr = [];
	            var params = "?command=wishmuldel&usernum="+<%=usernum%>;

	            $("input[name=check]:checked").each(function () {
	                checkArr.push($(this).val());
	                console.log(checkArr);
	            });

	            $.ajax({
	                type: "POST",
	                url: "user.do" + params,
	                traditional: true,
	                data: {
	                    checkArr: checkArr
	                },
	                success: function (data) {
	                    alert("찜 삭제 성공");
	                    location.reload();
	                },
	                error: function () {
	                    alert("error");
	                }
	            });

	        });
		});
	
</script>
</head>
<body>

	
    <div class="area">
        
        <table>
            <col width="10"><col width="300"><col width="25">
            <thead>
                <tr>
                    <th class="select"><input type="checkbox" id="allchk"></th>
                    <th class="title"><span class="blind"></span></th>
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
							<td class="select"><input type="checkbox" class="chk" name="check" value="${dto.wishnum}"></td>
							<td><a target="_parent" href="search.do?command=detail&title=${dto.title }&page=1">${dto.title }</a></td>
							<td class="delete"><button class="delimgbtn" onclick="location.href='user.do?command=wishnumdel&wishnum=${dto.wishnum}&usernum=<%=usernum%>'" value="${dto.wishnum }"><img src="http://www.khproject.kr/OTT_Service/img/delete.png" alt="삭제"></button></td>
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