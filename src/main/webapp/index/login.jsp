<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=path%>">
	<title>登录</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="index/css/public.css">
	<link rel="stylesheet" type="text/css" href="index/css/common.css">
	<link rel="stylesheet" type="text/css" href="index/css/logon.css">
	<link rel="stylesheet" type="text/css" href="index/css/font.css">
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<div id="main" class="logincoent">
	
		<div class="rebackgroundimg">
			<div class="registruer">
				<div class="regrnamepwd">
					<p class="iteljk">登录<span><a href="register">未注册请先注册</a></span></p>
					<form action="login" method="post">
					   <ul class="ul_form">
							<li class="user">
								<i class="iconfont icon-yonghu"></i>
								<input type="text" id="username" class="username" name="username" placeholder="请输入用户名">
							</li>
							<li class="pwdmia">
								<i class="iconfont icon-mima"></i>
								<input type="password" id="password" class="pwd" name="password" placeholder="请再次输入密码">
							</li>
						</ul>
						<input type="submit" value="立即登录" class="sub" id="loginBtn">
						<div style="color: red;font-size: 16px;text-align: center;margin-top: 20px;">${msg}</div>
					</form>
				</div>
			</div>
		</div>
		
	</div>

	<jsp:include page="footer.jsp"/>

</body>
<script src="index/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	/*$("#loginBtn").click(function () {
		var username=$.trim($("#username").val())
		var password=$.trim($("#password").val())
		if (username==""||password==''){
			alert("账号和密码不能为空！")
			return ;
		}
		$.ajax({
			url:'login',
			type:'post',
			dataType:'json',
			data:{username:username,password:password},
			success:function (data) {
				if (data=="false"){
					alert("登录失败！请检查账号和密码是否正确！")
					return;
				}
				window.location.href="<%=path%>";
			}
		})
	})*/
</script>
</html>