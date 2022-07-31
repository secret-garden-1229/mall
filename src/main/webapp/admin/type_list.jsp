<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
<base href="<%=path%>">
<title>类目列表</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="admin/css/bootstrap.css" />
</head>
<body>
	<div class="container">

		<%@include file="header.jsp"%>

		<div class="text-right">
			<a class="btn btn-warning" href="redirect/type_add">添加类目</a>
		</div>

		<br>

		<table class="table table-bordered table-hover">
			<tr>
				<th width="5%">ID</th>
				<th width="10%">名称</th>
				<th width="5%">序号</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach var="type" items="${typeList}">
				<tr>
					<td><p>${type.id}</p></td>
					<td><p>${type.tname}</p></td>
					<td><p>${type.num}</p></td>
					<td>
						<a class="btn btn-primary" href="typeEdit?id=${type.id}">修改</a>
						<a class="btn btn-danger" href="typeDelete?id=${type.id}">删除</a>
					</td>
				</tr>

			</c:forEach>

		</table>

	</div>
</body>
</html>