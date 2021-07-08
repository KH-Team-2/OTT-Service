<jsp:useBean id="dto" scope="request" type="com.dto.ContentsDto"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
   $(function(){
    $("#header").load("../header.jsp");
   });
</script>
<style type="text/css">
    @font-face {
          font-family: 'NEXON Lv1 Gothic OTF';
          src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
          font-weight: normal;
          font-style: normal;
      }
   body { font-family:'NEXON Lv1 Gothic OTF'; }
   #wrap{
        border: 1px solid black;
        width: 1200px;
        height : 1200px;
        margin: 0 auto;
        background: black;
    }
    #bigtable{
    	margin : 30px auto;
    	
    }
    #imgdiv{
    	width : 250px;
    	height : 350px;
    	margin-left : 80px;
    	margin-top : 50px;
    	float : left;
    	
    }
    #contentimage{
    	width : 250px;
    	height : 350px;
    	border: 0px;
    	
    }
    #titlediv{
    	width : 750px;
    	height : 350px;
    	float : left;
    	margin-left : 40px;
    	margin-top : 50px;
    	
    }
    #detaildiv{
    	border-bottom : 1px solid #A3A3A3;
    	width : 1040px;
    	height : 180px;
    	float : left;
    	margin-left : 80px;
    	margin-top : 30px
    }
    #reviewdiv{
    	width : 1040px;
    	height : auto;
    	float : left;
    	margin-left : 80px;
    	margin-top : 30px;
    }
    #writediv{
    	width : 1040px;
    	height : 95px;
    	float : left;
    	margin-left : 80px;
    	margin-top : 20px;
    }
    #title{
    	font-size: 48px;
    	margin-left :60px;
    	margin-top : 50px; 
    }
    #genre{
    	margin-left : 60px;
    	color : white;
    }
    #actor{
    	margin-left : 60px;
    	color : #A3A3A3;
    }
    #director{
    	margin-left : 60px;
    	color : #A3A3A3;
    }
    #writer{
    	margin-left : 60px;
    	color : #A3A3A3;
    }
    #detail{
    	font-size : 20px;
    	
    }
    .content{
    	color:rgb(248,211,28);
    	font-weight : bold;
    }
    #detail{
    	color : #A3A3A3;
    }
    .btn{
    	width : 70px;
    	height : 40px;
    	color: black;
        background-color:#f8d31c;
        border-color:#f8d31c;
        font-size:20px;
        border-radius: 10px;
        font-family:'NEXON Lv1 Gothic OTF';
        font-weight: bold;
    }
    #reviewinfo{
    	  height:100px;
          width: 940px;
          border-radius: 10px;
          font-size: 20px;
          border:3px solid #A3A3A3;
          background-color : black;
          color : white;
    }
    #reviewsubmit{
    	float : right;
    }
    #reviewtb{
    	color :  white;
    	text-align: center;
    	width : 100%;
    }
    #reviewtb td{
    	height : 30px;
    }
    #reviewtb tr{
    	border-bottom: 1px solid  rgb(248,211,28);
    }
    .reviewbtn{
    	width : 50px;
    	height : 30px;
    	color: black;
        background-color:#f8d31c;
        border-color:#f8d31c;
        font-size:15px;
        border-radius: 10px;
        font-family:'NEXON Lv1 Gothic OTF';
        font-weight: bold;
    }
</style>
</head>
<body>
	<div id="wrap">
		<div id="header"></div>
		<div id="contentDetail">
			<div id="imgdiv">
				<img id="contentimage" src="${dto.movieImg}" alt="${dto.title}">
			</div>
			<div id="titlediv">
				<span id="title" class="content">${dto.title}(${dto.openYear})</span>
				<br><br>
				<span id="genre" class="content">장르  ${dto.genre}</span>
				<br><br>
				<span id="actor" class="content">배우  ${dto.actor}</span>
				<br><br>
				<span id="director" class="content">감독  ${dto.director}</span>
			</div>
			<div id="detaildiv">
				<span id="detail">
					${dto.summary}
				</span>
			</div>
			<div id="writediv">
				<span id="reviewwrite">
					<form action="" method="post">
						<textarea cols="110" rows="6" name="reviewinfo" id="reviewinfo"></textarea>
						<input type="submit" value="작성" class="btn" id="reviewsubmit">
					</form>
				</span>
			</div>
			<div id="reviewdiv">
				<table id="reviewtb">
				<col width="100">
				<col width="100">
				<col width="600">
					<thead style="background:#A3A3A3; color:black;">
						<tr>
							<th>닉네임</th>
							<th>작성일</th>
							<th colspan="2">리뷰내용</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>양원국</td>
								<td>21-06-23</td>
								<td>리뷰내용입니다</td>
								<td class="btns" align="right">
									<button class="reviewbtn">신고</button>
								</td>
							</tr>
							<tr>
								<td>송원국</td>
								<td>21-06-23</td>
								<td>리뷰내용입니다</td>
								<td class="btns" align="right">
									<button class="reviewbtn">신고</button>
								</td>
							</tr>
							<tr>
								<td>이원국</td>
								<td>21-06-23</td>
								<td>리뷰내용입니다</td>
								<td class="btns" align="right">
									<button class="reviewbtn">수정</button>
									<button class="reviewbtn">삭제</button>
								</td>
							</tr>
							<tr>
								<td>박원국</td>
								<td>21-06-23</td>
								<td>리뷰내용입니다</td>
								<td class="btns" align="right">
									<button class="reviewbtn">신고</button>
								</td>
							</tr>
							<tr>
								<td>최원국</td>
								<td>21-06-23</td>
								<td>리뷰내용입니다</td>
								<td class="btns" align="right">
									<button class="reviewbtn">신고</button>
								</td>
							</tr>
							<tr>
								<td>김원국</td>
								<td>21-06-23</td>
								<td>리뷰내용입니다</td>
								<td class="btns" align="right">
									<button class="reviewbtn">신고</button>
								</td>
							</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	
</body>
</html>