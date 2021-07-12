<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html> <html lang="en">

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
		    
		    for(int i=0; i<list.length; i++) {
		    	
		    	
		    }
		});
		
		function add_wish(title) {
			
			var html_txt = '<tr><td class="select"><input type="checkbox" class="chk"></td>';
			html_txt += '<td class="title"><span>' + title + '</span></td>';';
			html_txt += '<td class="alarm"><img src="../img/alarm.png" alt="알림"></td>';
			html_txt += '<td class="delete"><img src="../img/delete.png" alt="삭제"></td></tr>';
			
			$("wish_input").append(html_txt);
		}
		
		function add_wish_foot() {
			
			var html_txt = '<tr> <td colspan="3"> <img src="img/delete.png" id="delbtn" class="delete"> <button id="delshow">선택</button> </td> </tr>';
			$("wish_input").append(html_txt);

		}

	</script>
</head>

<body>

	<% 
		HttpSession dto = request.getSession(true); 
	
		dto.setAttribute("USERNUM", 1);
		dto.setAttribute("ID", "thisisid");
		dto.setAttribute("PW", "pwissecret");
		dto.setAttribute("EMAIL", "aaaa@gmail.com");
		dto.setAttribute("PHONE", "01065559222");
		dto.setAttribute("NAME", "이름이다요");
		dto.setAttribute("BIRTH", "1999-09-09");
		dto.setAttribute("GENDER", "W");
		dto.setAttribute("NICKNAME", "닉네임이다요");
		dto.setAttribute("IMGURL", "IMGURL이다요");
		dto.setAttribute("STATUS", "Y");
		dto.setAttribute("GRADE", "USER");
		dto.setAttribute("USERDATE", "2021-07-08");
	%>
	
	
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
            
            <tbody id="wish_input"></tbody>
            
        </table>
        
        <ul class="pagination">
            <li><a href="">&laquo;</a></li>
            <li><a href="">1</a></li>
            <li><a href="">2</a></li>
            <li><a href="">3</a></li>
            <li><a href="">4</a></li>
            <li><a href="">5</a></li>
            <li><a href="">6</a></li>
            <li><a href="">&raquo;</a></li>
        </ul>
    
    </div>

</body>
</html>