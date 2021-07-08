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
				<img id="contentimage" src="" alt="">
			</div>
			<div id="titlediv">
				<span id="title" class="content">킬러의 보디가드(2017)</span>
				<br><br>
				<span id="genre" class="content">스릴러, 범죄, 액션, 코미디, Made in Europe</span>
				<br><br>
				<span id="actor" class="content">Ryan Reynolds</span>
				<br><br>
				<span id="director" class="content">Patrick Hughes</span>
				<br><br>
				<span id="writer" class="content">작가1 작가2</span>
			</div>
			<div id="detaildiv">
				<span id="detail">
					영국 최고 실력을 자랑하는 사설 경호원 마이클 브라이스(라이언 레이놀즈)는 어이없는 실수로 의뢰인을 죽게 만든 후 하루아침에 일감이 뚝 끊긴다. 한편 역사상 최악의 독재자로 악명을 떨치던 벨라루스의 두코비치 대통령(게리 올드먼)이 인터폴에 체포되어 헤이그에서 국제사법재판을 받게 된다. 재판 시효가 다가올 때마다 범죄를 증명할 증인이 하나둘 죽어나가자, 사태의 심각성을 파악한 인터폴은 두코비치 대통령의 민간인 학살 범행을 증명할 희대의 킬러 다리우스(새뮤얼 L. 잭슨)를 교도소에서 빼내와 법정에 세울 계획을 세우는데...
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