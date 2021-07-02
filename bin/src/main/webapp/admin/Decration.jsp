<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset:UTF-8"); %>
    
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
			
			<tr>
				<td>1</td>
				<td>박명수</td>
				<td>1000원 버는 법 빨리 오세요 !!</td>
				<td>4</td>
				<td> <img src="..\img\Yet.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>2</td>
				<td>정형돈</td>
				<td>재미있음</td>
				<td>7</td>
				<td> <img src="..\img\Yet.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>3</td>
				<td>박명수</td>
				<td>1000원 버는 법 빨리 오세요 !!</td>
				<td>4</td>
				<td> <img src="..\img\Yet.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>4</td>
				<td>유재석</td>
				<td>1500원 버는 법 빨리 오세요 !!</td>
				<td>6</td>
				<td> <img src="..\img\Yet.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>5</td>
				<td>정준하</td>
				<td>이어폰 팝니다.</td>
				<td>7</td>
				<td> <img src="..\img\Done.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>6</td>
				<td>하하</td>
				<td>ㅋㅋㅋㅋㅋ 앜ㅋㅋㅋ아컹캌ㅋㅋㅋㅋㅋ하핳핳핳ㅎㅎㅎ</td>
				<td>6</td>
				<td> <img src="..\img\Done.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>7</td>
				<td>정형돈</td>
				<td>한국 뱀이랑 일본 뱀이 싸워서 일본뱀이 진 이유는 ?<br>독도 없고 제주도 없어섴ㅋㅋㅋㅋㅋㅋ엌ㅋㅋㅋㅋ</td>
				<td>91</td>
				<td> <img src="..\img\Done.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>8</td>
				<td>노홍철</td>
				<td>대충 광고라는 내용</td>
				<td>8</td>
				<td> <img src="..\img\Done.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>9</td>
				<td>정형돈</td>
				<td>신발 게시판 신발끈</td>
				<td>5</td>
				<td> <img src="..\img\Done.png?ver=1"></td>
			</tr>
			
			<tr>
				<td>10</td>
				<td>유재석</td>
				<td>도배 배도 도배 배도 도배 배도</td>
				<td>1</td>
				<td> <img src="..\img\Done.png?ver=1"></td>
			</tr>
			
		</table>

		<br><br>
	
		<span style="font-size:20px; color:white;">&lt;&lt; &nbsp;&nbsp; <b>1</b> &nbsp;&nbsp; 2 &nbsp;&nbsp; 3 &nbsp;&nbsp; 4 &nbsp;&nbsp; 5  &nbsp;&nbsp; &gt;&gt;</span>
		
	</div>
	
</body>
</html>