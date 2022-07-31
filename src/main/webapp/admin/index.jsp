<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
<title>后台管理</title>
<link rel="stylesheet" href="admin/css/bootstrap.css"/>
</head>
<body>
<div class="container">

	<%@include file="header.jsp"%>

	<br><br>
	<div class="alert alert-success" role="alert">${msg}</div>
	
</div>	
</body>
</html>