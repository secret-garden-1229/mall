<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
<base href="<%=path%>">
<meta charset="utf-8"/>
<link rel="stylesheet" type="text/css" href="index/css/common.css"/>
<link rel="stylesheet" type="text/css" href="index/css/search.css"/>
<link rel="stylesheet" type="text/css" href="index/css/index.css"/>
<link rel="stylesheet" type="text/css" href="index/css/font.css" />
</head>
<body>

<div id="searchss">
	<div class="search">
		<div class="logo">
			<a href="index"><img src="index/img/logo.jpg"/></a>
		</div>
		<div class="sear_input">
			<form action="search" method="get">
				<input type="text" name="name" value="${search}">
				<button><i class="iconfont icon-icon--"></i></button>
			</form>
		</div>
		<div class="sera_cart">
			<a href="tocart"><p><i class="iconfont icon-gouwuche5"></i>我的购物车</p></a>
			<c:if test="${sessionScope.cartCount!=null && sessionScope.cartCount>0}">
				<span class="cartnum">${sessionScope.cartCount}</span>
			</c:if>
		</div>
		
		 <div class="sear_fir">
		 	<c:if test="${sessionScope.user==null}">
				<div class="sinup">
					<a href="index/login.jsp">登录</a>
					<a>/</a>
					<a href="index/register.jsp">注册</a>
				</div>
				<div class="sinup">
					<a target="_blank" href="admin.jsp">后台管理</a>
				</div>
			</c:if>
			
			<c:if test="${sessionScope.user!=null}">
				<div class="sigin">
	     			<span class="wel" id="wel">Hi,${sessionScope.user.username}</span>
	     		</div>
				<div class="my">
					<a>个人中心</a>
					<i class="iconfont icon-zheng-triangle1"></i>
					<ul class="uls">
						<li><a href="toOrder">我的订单</a></li>
						<li><a href="toAddress">收货地址</a></li>
						<li><a href="toPassword">修改密码</a></li>
						<li><a href="logout">退出</a></li>
					</ul>
				</div>
			</c:if>	
			
		 </div>
	</div>
</div>

<!--导航nav-->
<div id="navall">
	<div id="nav">
		<!--产品分类-->
		<div class="proclass">
			<p>产品分类</p>
			
			<div class="prolist" style="display:none;">
			
				<c:forEach items="${typeList}" var="type">
					
					<div class="list">
						<dl>
							<a href="type?id=${type.id}"><dt>${type.name}</dt></a>
						</dl>
					</div>
					
				</c:forEach>
				
			</div>
		</div>
					
		<!--导航-->
		<ul id="inav">
			<li <c:if test="${flag==1}">class="active"</c:if>><a href="<%=path%>">商城首页</a></li>
			<li <c:if test="${flag==2}">class="active"</c:if>><a href="today">今日推荐</a></li>
			<li <c:if test="${flag==3}">class="active"</c:if>><a href="hot">热销排行</a></li>
			<li <c:if test="${flag==4}">class="active"</c:if>><a href="newGoods">新品上市</a></li>
		</ul>
		
	</div>
</div>


<script src="index/js/jquery.min.js" type="text/javascript"></script>
<script src="index/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
//显示隐藏分类
$(".proclass").on("mouseenter", function() {
	$(".prolist").css("display", "block");
});
$(".proclass").on("mouseleave", function() {
	$(".prolist").css("display", "none");
});
// 显示隐藏个人中心
$(".my").on("mouseenter",function(){
	$(".uls").show();
})
$(".my").on("mouseleave",function(){
	$(".uls").hide();
})
$(".uls li a").on("mouseenter",function(){
	$(this).css('color','#ff712b');
})
$(".uls li a").on("mouseleave",function(){
	$(this).css('color','#919191');
})
	
// 加入购物车
$(document).on("click", ".addcart", function(){
	var goodId = $(this).attr("data-id");
	var index = layer.load();
	$.post("cartBuy", {goodId:goodId}, function(data){
		layer.close(index);
		if(data==true){
			layer.msg("加入成功", {
                icon: 1,   // 成功图标
                time: 1200 //1.2秒关闭（如果不配置，默认是3秒）
            });
			updateCartnum(1); // 更新数量
		}else if(data==false){
            layer.msg("操作失败", {
                icon: 2,   // 失败图标
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        }else{ // 重新渲染页面
        	document.write(data);
        }
	});
});

// 更新购物车显示数量
function updateCartnum(num){
	if($(".cartnum").length > 0){
		$(".cartnum").text(parseInt($(".cartnum").text()) + num);
	}else{ // 第一个
		$(".sera_cart").append('<span class="cartnum">1</span>');
	}
}
</script>
</body>
</html>