<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<html>
<head>
<title>ID/PW 찾기</title>

<style> 
		@font-face {
		    font-family: 'NEXON Lv1 Gothic OTF';
		    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}  
 #wrap{
    width:420px;
    margin-left:auto; 
    margin-right:auto;
}
    
body{
        background: black;
        margin: 50px auto;
        width: 700px;
        height: 800px;
        border: rgb(248, 211, 28) solid 1px;
        color: rgb(248, 211, 28);
        font-family:'NEXON Lv1 Gothic OTF'; background-color:black;
       
    }

    table{
        width: 470px;
    }
    
    #title{
        color : white;
        background-color:black;
    }
    body {
        background: black;
        color: rgb(248, 211, 28);
    }

    hr.dot{
        border: none; border: 1px dashed white;
        width: 400px;
    }

    .Btn{
        position: relative;
        left: 209px;
        padding: 3px 10px;
        background-color: #F8D31C;
        border: none;
        border-radius: 4px;
        color: black;
        font-family:'NEXON Lv1 Gothic OTF';
        font-size: 15px;
        cursor: pointer;

        }



    .Email{
        padding: 3px 10px;
        background-color: black;
        font-family:'NEXON Lv1 Gothic OTF';
        font-size: 15px;
        color: white;
        border:0px
    }
    .Code{
        padding: 3px 3px;
        background-color: #F8D31C;
        font-family:'NEXON Lv1 Gothic OTF';
        font-size: 15px;
        border-radius: 4px;
    }

    .textbox{
        height:40px;
        width:200px;
        border-radius: 10px;
        font-size: 15px;
        border:2px solid #f8d31c;
        background-color : black;
        color : white;
    }
    .textbox2{
        height:40px;
        width:200px;
        border-radius: 10px;
        font-size: 15px;
        border:2px solid #f8d31c;
        background-color : black;
        color : white;
    }
</style>
</head>
<body>

<div id="wrap">
    <br>
    <h2>ID 찾기</h2>
    <br>
    
    <form action="../user.do" method="post">
    	<input type="hidden" name="command" value="searchID">
        <table>
            <tr>
                <td id="title">이름</td>
                <td>
                    <input type="text" name="Name" maxlength="40" class="textbox">
                </td>
            </tr>
            <tr>
                <td id="title">Email</td>
                <td>
                    <input type="text" name="Email" maxlength="30" class="textbox">

                </td>
            </tr>

            <tr>
                <td id="title">휴대전화</td>
                <td>
                    <input type="text" name="Phone" class="textbox">
                </td>
            </tr>
            <tr>
                <br>
                <td colspan="3">
                    <input type="submit" value="확인" class="Btn">
                    <input type="button" value="취소" class="Btn">
                    <br>
			</table>
	</form>

     	<h2>PW 찾기</h2>
     	<form action="user.do" method="post">
    	<input type="hidden" name="command" value="SearchPW">
    	<table>
                        <br>
                </td>
            </tr>
            
            <br>
            
            <tr>
                
                <td id="title">ID</td>
                <td>
                    <input type="text" name="UserID" maxlength="20" class="textbox2">    
                </td>
            </tr>
            <tr>
                <td id="title">이름</td>
                <td>
                    <input type="text" name="Name" maxlength="40" class="textbox2">
                </td>
            </tr>
            <tr>
                <td id="title">Email</td>
                <td>
                    <input type="text" name="Email" maxlength="30" class="textbox2">
                </td>
            </tr>
            
            <tr>
                <td id="title">Code</td>
                <td>
                    <input type="text" name="Code" maxlength="40" class="textbox2">
                </td>
            </tr>
    
            
            <tr>
                <td colspan="3" >
                    <input type="submit" value="확인" class="Btn">
                        <input type="button" value="취소" class="Btn">
                    </td>
                </td>
            </tr>
    </table>
    </form>
    <br>



</div>

</body>
</html>