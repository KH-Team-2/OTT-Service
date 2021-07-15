<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2021/07/15
  Time: 5:26 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(function () {
        var customWindow = window.open('', '_blank', '');
        customWindow.close();
        window.open("login.jsp");
    });
</script>
<body>

</body>
</html>
