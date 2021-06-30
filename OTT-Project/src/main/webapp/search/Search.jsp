<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>5page</title>
<style type="text/css">
    #wrap{
        border: 1px solid black;
        width: 1200px;
        height: 1500px;
        margin: 0 auto;
        background: black;
        position: relative;
    }
    #imgdiv{
        border: 1px solid rgb(248,211,28);
        width: 250px;
        height: 350px;
        float: left;
        margin-left: 50px;
        margin-top: 50px;
    }
    #contentdiv{
        border: 1px solid rgb(248,211,28);
        float: left;
        margin-left: 40px;
        margin-top: 50px;
        width: 800px;
        height: 350px;
        color : rgb(248,211,28);
    }
    #image{
        width: 250px;
        height: 350px;
    }
    #title{
    	font-size : 48px;
    }
    #genrediv{
    	font-size : 20px;
    }
    #detaildiv{
    	font-size : 32px;
    }
    .content{
    	margin-left : 20px;
    	margin-bottom: 20px;
    }
    
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
   $(function(){
    $("#header").load("../header.jsp");
    $(".contentwrap").click(function(){
    	location.href="SearchDetail.jsp";
    });
   });
</script>
</head>
<body>
    <div id="wrap">
        <div id="header">
        </div>
        <div class="contentwrap">
            <div id="imgdiv">
                <img  id="image"src="" alt="">
            </div>
            <div id="contentdiv" class="content">
            	<div id="titlediv" class="content">
	                <span id="title">����</span>
            	</div>
            	<div id="genrediv" class="content">
	                <span id="date">����</span>
	                <span id="director">����</span>
	                <span id="actor">���</span>
	                <span id="genre">�帣</span>
	                <span id="score">����</span>
            	</div>
            	<div id="detaildiv" class="content">
	                <span id="content">����</span>
            	</div>
            </div>
        </div>
        <div class="contentwrap">
            <div id="imgdiv">
                <img  id="image"src="" alt="">
            </div>
            <div id="contentdiv" class="content">
            	<div id="titlediv" class="content">
	                <span id="title">����</span>
            	</div>
            	<div id="genrediv" class="content">
	                <span id="date">����</span>
	                <span id="director">����</span>
	                <span id="actor">���</span>
	                <span id="genre">�帣</span>
	                <span id="score">����</span>
            	</div>
            	<div id="detaildiv" class="content">
	                <span id="content">����</span>
            	</div>
            </div>
        </div>
        <div class="contentwrap">
            <div id="imgdiv">
                <img  id="image"src="" alt="">
            </div>
            <div id="contentdiv" class="content">
            	<div id="titlediv" class="content">
	                <span id="title">����</span>
            	</div>
            	<div id="genrediv" class="content">
	                <span id="date">����</span>
	                <span id="director">����</span>
	                <span id="actor">���</span>
	                <span id="genre">�帣</span>
	                <span id="score">����</span>
            	</div>
            	<div id="detaildiv" class="content">
	                <span id="content">����</span>
            	</div>
            </div>
        </div>
    </div>
    
</body>
</html>