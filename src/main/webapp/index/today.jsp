<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
    <base href="<%=path%>">
    <title>今日推荐</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="index/css/public.css">
    <link rel="stylesheet" type="text/css" href="index/css/index.css">
    <link rel="stylesheet" type="text/css" href="index/css/swiper.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="main">

    <!--今日推荐-->
    <div class="m_content m_today_box">
        <div id="m_today">
            <div class="m-hot-tit clearfix">
                <div class="m-hot-tit-left"><span>今日推荐</span></div>
            </div>
            <div class="mdd_today_box clearfix">
                <div class="m_today_center clearfix">
                    <c:forEach items="${todayLists}" var="today">
                        <div class="goods-box2">
                            <div class="goods clearfix">
                                <a href="detail?id=${today.id}">
                                    <div class="img-box">
                                        <img src="${today.cover}">
                                    </div>
                                </a>
                                <div class="goods-right">
                                    <a href="detail?id=${today.id}">
                                        <p class="goodsname">${today.gname}</p>
                                    </a>
                                    <p class="goods-descript">${today.intro}</p>
                                    <p class="goods-spec">${today.spec}</p>
                                    <p class="goodsprices"><span class="yj">&yen;<span class="yjcont">${today.price}</span></span></p>
                                    <p class="addcart" data-id="${today.id}"><i class="iconfont icon-gouwuche3"></i>加入购物车</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>


    <!--产品分类-->
    <c:forEach items="${dataList}" var="data">
        <div class="main Louti vegetable">
            <div class="m-hot-tit clearfix">
                <div class="m-hot-tit-left"><span>${data.type.tname}</span></div>
                <a href="type?id=${data.type.id}"><div class="m-hot-tit-right">查看更多 </div></a>
            </div>
            <div class="main-box">
                <div class="m-swiper-container">
                    <div class="swiper-wrapper">

                        <div class="swiper-slide">
                            <c:forEach items="${data.goodList}" var="good" varStatus="status">
                            <div class="goods-box ">
                                <div class="goods">
                                    <a href="detail?id=${good.id}">
                                        <div class="img-box">
                                            <img src="${good.cover}">
                                        </div>
                                    </a>
                                    <a href="detail?id=${good.id}">
                                        <p class="goodsname">${good.gname}</p>
                                    </a>
                                    <p class="goods-descript">${good.intro}</p>
                                    <p class="goods-spec">${good.spec}</p>
                                    <p class="goodsprices"><span class="yj">&yen;<span class="yjcont">${good.price}</span></span></p>
                                    <p class="addcart" data-id="${good.id}"><i class="iconfont icon-gouwuche3"></i>加入购物车</p>
                                </div>
                            </div>
                            <c:if test="${status.count%5==0}">
                        </div><div class="swiper-slide">
                        </c:if>
                        </c:forEach>
                    </div>

                    </div>
                    <div class="swiper-pagination"></div>
                </div>
            </div>
        </div>
    </c:forEach>

</div>

<jsp:include page="footer.jsp"/>

</body>

<script src="index/js/jquery.min.js" type="text/javascript"></script>
<script src="index/js/jquery.fly.min.js" type="text/javascript"></script>
<script src="index/js/jquery.flexslider.min.js" type="text/javascript"></script>
<script src="index/js/swiper.min.js" type="text/javascript"></script>
<script type="text/javascript">
    // 商品轮播
    new Swiper('.m-swiper-container', {
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });

    // 显示购物车按钮
    $(".goods-box").on("mouseenter", function() {
        $(this).addClass("active")
        $(this).find(".addcart").show()
        $(this).find(".reserve").show()
        $(this).find(".m_stop").show()
        $(this).find(".goodsprices").hide()
    });
    $(".goods-box").on("mouseleave", function() {
        $(this).removeClass("active")
        $(this).find(".addcart").hide()
        $(this).find(".reserve").hide()
        $(this).find(".m_stop").hide()
        $(this).find(".goodsprices").show()
    });
    $(".goods-box1").on("mouseenter", function() {
        $(this).addClass("active")
    });
    $(".goods-box1").on("mouseleave", function() {
        $(this).removeClass("active")
    });
    $(".goods-box2").on("mouseenter", function() {
        $(this).addClass("active")
        $(this).find(".addcart").show()
        $(this).find(".reserve").show()
        $(this).find(".goodsprices").hide()
    });
    $(".goods-box2").on("mouseleave", function() {
        $(this).removeClass("active")
        $(this).find(".addcart").hide()
        $(this).find(".reserve").hide()
        $(this).find(".goodsprices").show()
    });

</script>
</html>
