<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>

<style>
    #wrap{
        width:420px;
        margin-left:auto; 
        margin-right:auto;
        text-align:center;
    }
    
    table{
        border:3px solid white
        
    }
    
    td{
        border:1px solid white
        
    }
    
    #title{
        color : rgb(248, 211, 28);
        background-color:black
    }
    body {
        background: black;
        color: rgb(248, 211, 28);
    }
</style>
</head>
<body>

<div id="wrap">
    <br><br>
    <h2>회원가입</h2>
    <br><br><br>
    
    <form>
        <table>
            <tr>
                <td id="title">아이디</td>
                <td>
                    <input type="text" name="UserID" maxlength="20">
                    <input type="button" value="중복확인" >    
                </td>
            </tr>
                    
            <tr>
                <td id="title">비밀번호</td>
                <td>
                    <input type="password" name="PW" maxlength="15">
                </td>
            </tr>
            
            <tr>
                <td id="title">비밀번호 확인</td>
                <td>
                    <input type="password" name="PW" maxlength="15">
                </td>
            </tr>

            <tr>
                <td id="title">이메일</td>
                <td>
                    <input type="text" name="Email" maxlength="30">@
                    <select name="Email_2">
                        <option>naver.com</option>
                        <option>daum.net</option>
                        <option>gmail.com</option>
                        <option>nate.com</option>                        
                    </select>
                </td>
            </tr>

            <tr>
                <td id="title">휴대전화</td>
                <td>
                    <input type="text" name="Phone" />
                </td>
            </tr>
                
            <tr>
                <td id="title">이름</td>
                <td>
                    <input type="text" name="Name" maxlength="40">
                </td>
            </tr>

            <tr>
                <td id="title">생일</td>
                <td>
                    <input type="text" name="Birth_yy" maxlength="4" placeholder="년(4자)" size="6" >
                    <select name="Birth_mm">
                        <option value="">월</option>
                        <option value="01" >1</option>
                        <option value="02" >2</option>
                        <option value="03" >3</option>
                        <option value="04" >4</option>
                        <option value="05" >5</option>
                        <option value="06" >6</option>
                        <option value="07" >7</option>
                        <option value="08" >8</option>
                        <option value="09" >9</option>
                        <option value="10" >10</option>
                        <option value="11" >11</option>
                        <option value="12" >12</option>
                    </select>
                    <input type="text" name="Birth_dd" maxlength="2" placeholder="일" size="4" >
                </td>
            </tr>
                
            <tr>
                <td id="title">성별</td>
                <td>
                    <input type="radio" name="Gender" value="남" checked>남
                    <input type="radio" name="Gender" value="여" checked>여
                </td>
            </tr>
                
            <tr>
                <td id="title">닉네임</td>
                <td>
                    <input type="text" name="NickName" maxlength="40">
                </td>
                </tr>
                <tr>
                    <td id="title">프로필</td>
                    <td>
                        <input type="file" name="profile" size=25

                        onfocus="this.style.backgroundcolor='#f0f0fe'; in_profile()"
                        onblur="this.style.backgroundcolor='#e9e9e9'">
                    </td>
                    <td colspan="2">
                        <img src="" name="profile">

                    </td>
                </tr>
        </table>
        <br>
        <input type="submit" value="가입">  <input type="button" value="취소" onclick="location.href='login.jsp'">
    </form>
</div>
</body>
</html>