<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
    <base href="<%=path%>">
<!-- 自动跳转到网站首页 -->
<meta http-equiv="refresh" content="0;url=index/index.jsp">
</head>

<body>
</body>
</html>