<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
<title>订单列表</title>
<link rel="stylesheet" href="admin/css/bootstrap.css"/>
	<link href="admin/bootstrapcss/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="admin/js/jquery.js"></script>
	<script type="text/javascript" src="admin/js/pagination.min.js"></script>
</head>
<body>
<div class="container">

	<%@include file="header.jsp" %>
	
	<br>
	
	<ul role="tablist" class="nav nav-tabs">
        <li <c:if test='${status==0}'>class="active"</c:if> role="presentation"><a href="orderList">全部订单</a></li>
        <li <c:if test='${status==1}'>class="active"</c:if> role="presentation"><a href="orderList?status=1">未付款</a></li>
        <li <c:if test='${status==2}'>class="active"</c:if> role="presentation"><a href="orderList?status=2">已付款</a></li>
        <li <c:if test='${status==3}'>class="active"</c:if> role="presentation"><a href="orderList?status=3">配送中</a></li>
        <li <c:if test='${status==4}'>class="active"</c:if> role="presentation"><a href="orderList?status=4">已完成</a></li>
    </ul>
    
    <br>
	
	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="5%">总价</th>
		<th width="15%">商品详情</th>
		<th width="20%">收货信息</th>
		<th width="10%">订单状态</th>
		<th width="10%">支付方式</th>
		<th width="10%">下单用户</th>
		<th width="10%">下单时间</th>
		<th width="10%">操作</th>
	</tr>
	
	<c:forEach var="order" items="${orderList}">
         <tr>
         	<td><p>${order.id}</p></td>
         	<td><p>${order.total}</p></td>
         	<td>
	         	<c:forEach var ="item" items="${order.itemList}">
		         	<a target="_blank" href="index/detail?id=${item.good.id}"><p>${item.good.gname}</p></a>
		         	<p>¥${item.price} x ${item.amount}</p>
	         	</c:forEach>
         	</td>
         	<td>
         		<p>${order.name}</p>
         		<p>${order.phone}</p>
         		<p>${order.address}</p>
         	</td>
			<td>
				<p>
					<c:if test="${order.status==1}">未付款</c:if>
					<c:if test="${order.status==2}"><span style="color:red;">已付款</span></c:if>
					<c:if test="${order.status==3}">配送中</c:if>
					<c:if test="${order.status==4}">已完成</c:if>
				</p>
			</td>
			<td>
				<p>
					<c:if test="${order.paytype==1}">微信</c:if>
					<c:if test="${order.paytype==2}">支付宝</c:if>
					<c:if test="${order.paytype==3}">货到付款</c:if>
				</p>
			</td>
			<td><p>${order.user.username}</p></td>
			<td><p><fmt:formatDate value="${order.systime}" pattern="yyyy-MM-dd HH:mm:ss" /></p></td>
			<td>
				<c:if test="${order.status==2}">
					<a class="btn btn-success" href="orderUpdate?id=${order.id}&status=3">发货</a>
				</c:if>
				<c:if test="${order.status==3}">
					<a class="btn btn-warning" href="orderUpdate?id=${order.id}&status=4">完成</a>
				</c:if>
				<a class="btn btn-danger" href="orderDelete?id=${order.id}">删除</a>
			</td>
       	</tr>
	</c:forEach>
     
</table>
	<center>
		<div class="pagination">
			<ul id="ul">
				<li class="pre" <c:if test="${pageInfo.hasPreviousPage}">pageNo="${pageInfo.prePage}"</c:if> ><span><<</span></li>
				<c:forEach items="${pages}" var="pageNo">
					<c:if test="${pageNo<=pageInfo.pages}">
						<li class="active"><span>${pageNo}</span></li>
					</c:if>
				</c:forEach>
				<li class="last" <c:if test="${pageInfo.hasPreviousPage}"> lastPage="${pageInfo.nextPage} </c:if> "><span >>></span></li>
			</ul>
		</div>
	</center>

<br>${pageTool}<br>
</div>
<script type="text/javascript" src="admin/js/jquery.js"></script>
<script type="text/javascript">
	$("#ul .pre").click(function () {
		<c:if test="${pageInfo.hasPreviousPage}">window.location.href='orderList?pageNo=${pageInfo.prePage}'+'&status=${status}'</c:if>
	})

	$("#ul .last").click(function () {
		<c:if test="${pageInfo.hasNextPage}">window.location.href='orderList?pageNo=${pageInfo.nextPage}'+'&status=${status}'</c:if>
	})
	$("#ul .active").click(function () {
		window.location.href='orderList?pageNo='+$(this).text()+'&status=${status}';
	})
</script>
</body>
</html>