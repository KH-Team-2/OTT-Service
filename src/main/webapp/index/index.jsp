<%@ include file="../header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.dto.UserDto" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        <div class="w3-content w3-section" style="max-width:500px">

            <img class="mySlides w3-animate-top movieimg"
                 src="https://an2-img.amz.wtchn.net/image/v2/08ace33ede39f0ce84b35b90af711e2a.jpg?jwt=ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKaVlXTnJaM0p2ZFc1a0lqcDdJbklpT2pJMU5Td2laeUk2TWpVMUxDSmlJam95TlRWOUxDSmpjbTl3SWpwMGNuVmxMQ0pvWldsbmFIUWlPakkyTkN3aWNHRjBhQ0k2SWk5Mk1pOXpkRzl5WlM5cGJXRm5aUzh4TmpFMk16ZzBOVGsyT1RJME16azNNall3SWl3aWNYVmhiR2wwZVNJNk9EQXNJbmRwWkhSb0lqbzBOekI5LnltN3BBeG1uWmFwMV9wZ3I3cU9ZbEpMei00TzdMNVlnTkxVWU9ySHdseGc"
                 >
            <img class="mySlides w3-animate-bottom movieimg"
                 src="https://an2-img.amz.wtchn.net/image/v2/cd867798dc5cd23d8db57f9cc52ceb38.jpg?jwt=ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKaVlXTnJaM0p2ZFc1a0lqcDdJbklpT2pJMU5Td2laeUk2TWpVMUxDSmlJam95TlRWOUxDSmpjbTl3SWpwMGNuVmxMQ0pvWldsbmFIUWlPakkyTkN3aWNHRjBhQ0k2SWk5Mk1TOTRhVEJ6T0RadFozcG1aR0ZuWVRCeU1HOXVNaUlzSW5GMVlXeHBkSGtpT2pnd0xDSjNhV1IwYUNJNk5EY3dmUS5OYVRvV0JZeTVLUVBiTDF1RjF3eV9PN2dMR2wtcGpXM21HMUs5WlVBeXJB"
                 >
            <img class="mySlides w3-animate-top movieimg"
                 src="https://an2-img.amz.wtchn.net/image/v2/08ace33ede39f0ce84b35b90af711e2a.jpg?jwt=ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKaVlXTnJaM0p2ZFc1a0lqcDdJbklpT2pJMU5Td2laeUk2TWpVMUxDSmlJam95TlRWOUxDSmpjbTl3SWpwMGNuVmxMQ0pvWldsbmFIUWlPakkyTkN3aWNHRjBhQ0k2SWk5Mk1pOXpkRzl5WlM5cGJXRm5aUzh4TmpFMk16ZzBOVGsyT1RJME16azNNall3SWl3aWNYVmhiR2wwZVNJNk9EQXNJbmRwWkhSb0lqbzBOekI5LnltN3BBeG1uWmFwMV9wZ3I3cU9ZbEpMei00TzdMNVlnTkxVWU9ySHdseGc"
                 >
            <img class="mySlides w3-animate-bottom movieimg"
                 src="https://an2-img.amz.wtchn.net/image/v2/cd867798dc5cd23d8db57f9cc52ceb38.jpg?jwt=ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKaVlXTnJaM0p2ZFc1a0lqcDdJbklpT2pJMU5Td2laeUk2TWpVMUxDSmlJam95TlRWOUxDSmpjbTl3SWpwMGNuVmxMQ0pvWldsbmFIUWlPakkyTkN3aWNHRjBhQ0k2SWk5Mk1TOTRhVEJ6T0RadFozcG1aR0ZuWVRCeU1HOXVNaUlzSW5GMVlXeHBkSGtpT2pnd0xDSjNhV1IwYUNJNk5EY3dmUS5OYVRvV0JZeTVLUVBiTDF1RjF3eV9PN2dMR2wtcGpXM21HMUs5WlVBeXJB"
                 >

        </div>

        <script>
            var myIndex = 0;
            carousel();

            function carousel() {
                var i;
                var x = document.getElementsByClassName("mySlides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {
                    myIndex = 1
                }
                x[myIndex - 1].style.display = "block";
                setTimeout(carousel, 3000);
            }
        </script>
    </div>
    <br><br>
    <div class="videooption">인 기 순</div>
    <br>
    <div class="fastest">여기엔 영화 이미지가 들어갑니다.</div>
</div>


</body>
</html>