<%@ include file="../index/index_header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
            $("#headers").load("header.jsp");
    </script>
    <style>
        body {
            font-family: 'NEXON Lv1 Gothic OTF';
            background: black;
        }
        #videoselect {
            margin: 0 auto;
            width: 1200px;
            height: 1000px;
            /*border: rgb(248, 211, 28) solid 1px;*/
            color: rgb(248, 211, 28);
        }

        .videooption {
            font-size: 30px;
            color: rgb(248, 211, 28);
            margin: 0 auto;
            width: 1200px;
        }

        .fastest {
            width: 1200px;
            height: 400px;
            /*border: rgb(248, 211, 28) solid 1px;*/
            color: rgb(248, 211, 28);
        }

        .movieimg {
            /*position: relative;*/
            margin-left: 10px;
            margin-right: 5px;
            margin-bottom: 30px;
            width: 220px;
            height: 140px;
            image-rendering: auto;
            image-rendering: -moz-crisp-edges; /* Firefox */
            image-rendering: -o-crisp-edges; /* Opera */
            /*image-rendering: -webkit-optimize-contrast; !* Webkit (non-standard naming) *!*/
            image-rendering: crisp-edges;
            -ms-interpolation-mode: nearest-neighbor; /* IE (non-standard property) */
        }

        .movieimg:hover {
            transform: scale(1.2);
            transition: 0.8s;
            z-index: 100;
            cursor: pointee
        }

    </style>
</head>
<body>
<div id="headers"></div>
<br>
<div id="videoselect">
    <div class="videooption">최 신 순</div>
    <br>
    <div class="fastest">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
        <img class="movieimg"
             src="https://occ-0-3077-993.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABelTq5aZu1lyg7xMxNT1CriD16-EBoMGl9ZdcgbwqgDYGfbWCSjGYzhCs6hkGKBUZfZgvqsEyqdLTxeF7UAiEk5NTnM.jpg?r=6b7"
             alt="">
    </div>
    <br><br>
    <div class="videooption">인 기 순</div>
    <br>
    <div class="fastest">여기엔 영화 이미지가 들어갑니다.</div>
</div>
</body>
</html>