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
	                <span id="title">제목</span>
            	</div>
            	<div id="genrediv" class="content">
	                <span id="date">연도</span>
	                <span id="director">감독</span>
	                <span id="actor">배우</span>
	                <span id="genre">장르</span>
	                <span id="score">평점</span>
            	</div>
            	<div id="detaildiv" class="content">
	                <span id="content">내용</span>
            	</div>
            </div>
        </div>
        <div class="contentwrap">
            <div id="imgdiv">
                <img  id="image"src="" alt="">
            </div>
            <div id="contentdiv" class="content">
            	<div id="titlediv" class="content">
	                <span id="title">제목</span>
            	</div>
            	<div id="genrediv" class="content">
	                <span id="date">연도</span>
	                <span id="director">감독</span>
	                <span id="actor">배우</span>
	                <span id="genre">장르</span>
	                <span id="score">평점</span>
            	</div>
            	<div id="detaildiv" class="content">
	                <span id="content">내용</span>
            	</div>
            </div>
        </div>
        <div class="contentwrap">
            <div id="imgdiv">
                <img  id="image"src="" alt="">
            </div>
            <div id="contentdiv" class="content">
            	<div id="titlediv" class="content">
	                <span id="title">제목</span>
            	</div>
            	<div id="genrediv" class="content">
	                <span id="date">연도</span>
	                <span id="director">감독</span>
	                <span id="actor">배우</span>
	                <span id="genre">장르</span>
	                <span id="score">평점</span>
            	</div>
            	<div id="detaildiv" class="content">
	                <span id="content">내용</span>
            	</div>
            </div>
        </div>
    </div>
    
</body>
</html>