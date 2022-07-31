<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
	<title>收货地址</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="index/css/public.css">
	<link rel="stylesheet" type="text/css" href="index/css/common.css">
	<link rel="stylesheet" type="text/css" href="index/css/address.css">
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<div id="main">
	
		<div class="new-address">
			<form method="get" class="registerform">
				<div class="box clearfix">
					<div class="left">
						<span>姓名&nbsp;：&nbsp;</span>
					</div>
					<div class="right">
						<input type="text" id="username" name="name" value="${sessionScope.user.name}" required="required" placeholder="请输入收货人姓名">
					<span class="Validform_checktip"></span></div>
				</div>
				<div class="box clearfix">
					<div class="left">
						<span>电话&nbsp;：&nbsp;</span>
					</div>
					<div class="right">
						<input type="text" id="phone" name="phone" value="${sessionScope.user.phone}" required="required" placeholder="请输入收货人电话">
					<span class="Validform_checktip"></span></div>
				</div>
				<div class="box clearfix">
					<div class="left">
						<span>地址&nbsp;：&nbsp;</span>
					</div>
					<div class="right">
						  <input type="text" id="address"  name="address" value="${sessionScope.user.address}" required="required" placeholder="请输入收货地址">
					</div>
				</div>
				<div class="save">
					<button type="button" id="btn">保存</button>
					<p style="color:green;">${msg}</p>
				</div>
			</form>
		</div>
		
	</div>

	<jsp:include page="footer.jsp"/>

</body>
<script src="index/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$("#btn").click(function () {
		var username=$.trim($("#username").val())
		var phone=$.trim($("#phone").val())
		var address=$.trim($("#address").val())
		$.ajax({
			url:'updateAddres',
			type:'get',
			dataType:"json",
			data:{username:username,phone:phone,address:address},
			success:function (data) {
				if (data==true){
					window.location.href="<%=path%>";
				}else {
					alert("系统繁忙，请稍后重试")
				}

			}
		})
	})
</script>
</html>