<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/js/swiper-5.4.1/package/css/swiper.min.css"/>
    <script src="${pageContext.request.contextPath}/res/js/jquery-color-2.1.2.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/fore/fore_home.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_home.css" rel="stylesheet"/>
    <title>首页</title>
    <script src="${pageContext.request.contextPath}/res/js/swiper-5.4.1/package/js/swiper.min.js"></script>

    <style>
        .swiper-container {
            width: 100%;
        }
        .swiper-container img{
            width: 100%;
            max-height:3000px;
        }
    </style>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <img style="width:297px;height:90px;" src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/HomeLogoA.png">
        <div class="mallSearch">
            <form action="${pageContext.request.contextPath}/product" method="get">
                <div class="mallSearch-input">
                    <input class="header_search_input" type="text" name="product_name" placeholder="搜索 商品"
                           maxlength="50">
                    <input class="header_search_button" type="submit" value="搜索">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <c:if test="${i.index<9}">
                        <li><a href="${pageContext.request.contextPath}/product?category_id=${category.category_id}"
                                <c:if
                                test="${i.index % 2 != 0}"> style="color: #FF0036"</c:if>>${fn:substring(category.category_name,0,fn:indexOf(category.category_name,' /'))}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="home_nav">
        <div class="home_nav_title">
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/header_nav_title.png">
            <span>商品分类</span>
        </div>
       <!-- <a href="https://chaoshi.tmall.com/" target="_blank"><img
                src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/TB1ztBlaMMPMeJjy1XbXXcwxVXa-200-60.png"></a>
        <a href="https://www.tmall.hk/" target="_blank"><img
                src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/TB1t5ObaBxRMKJjy0FdXXaifFXa-200-60.png"></a>
        <a href="http://vip.tmall.com/" target="_blank">会员</a>
        <a href="https://3c.tmall.com/" target="_blank">电器城</a>
        <a href="https://miao.tmall.com/" target="_blank">喵鲜生</a>
        <a href="http://yao.tmall.com/" target="_blank">医药馆</a>
        <a href="http://wt.tmall.com/" target="_blank">营业厅</a>
        <a href="https://meilihui.tmall.com/" target="_blank">魅力惠</a>
        <a href="https://www.alitrip.com/" target="_blank">飞猪旅行</a>
        <a href="https://suning.tmall.com/" target="_blank">苏宁易购</a>-->
    </div>
</nav>
<div class="banner swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide">
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/1.jpg"/>
       </div>
        <div class="swiper-slide">
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/2.jpg"/>
        </div>
        <div class="swiper-slide">
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/3.jpg"/>
        </div>
        <div class="swiper-slide">
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/4.jpg"/>
        </div>
        <div class="swiper-slide">
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/5.jpg"/>
        </div>
    </div>

</div>
<div class="banner_main" style="z-index:100">
    <ul class="banner_nav">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <li data-toggle="${category.category_id}" data-status="">
                <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/small/${category.category_id}.png">
                <a href="${pageContext.request.contextPath}/product?category_id=${category.category_id}">${category.category_name}</a>
                <div class="banner_div" name="${category.category_name}">

                </div>
            </li>
        </c:forEach>
    </ul>
    <ul class="banner_slider">
        <li id="slider_1" style="background: rgba(255,255,255,0.4)"></li>
        <li id="slider_2"></li>
        <li id="slider_3"></li>
        <li id="slider_4"></li>
        <li id="slider_5"></li>
        <li id="slider_6"></li>
    </ul>
    <a href="#"></a>
</div>
<div class="banner_do">
    <div class="banner_goods">
        <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
            <c:if test="${fn:length(category.productList)>0}">
                <div class="banner_goods_type">
                    <div class="banner_goods_title">
                        <span></span>
                        <p>${category.category_name}</p>
                    </div>
                    <c:if test="${i.index<5}">
                        <a href="#">
                     <img style="max-height:500px;" class="banner_goods_show"
                            src="res/images/fore/WebsiteImage/left/${i.index+1}.jpg">
                    </a>
                    </c:if>
                    <div class="banner_goods_items">
                        <c:forEach items="${category.productList}" var="product" varStatus="i">
                            <c:if test="${i.index<8}">
                                <div class="banner_goods_item">
                                    <a href="product/${product.product_id}" class="goods_link"></a>
                                    <img src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${product.singleProductImageList[0].productImage_src}">
                                    <a href="product/${product.product_id}"
                                       class="goods_name">${product.product_name}</a>
                                    <span class="goods_price">￥${product.product_sale_price}</span>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="endDiv"></div>
</div>
<%@ include file="include/footer_two.jsp" %>
<%@ include file="include/footer.jsp" %>
<script>
    var mySwiper = new Swiper ('.swiper-container', {
        direction:'horizontal',
        // direction: 'vertical', // 垂直切换选项
        loop: true, // 循环模式选项
        speed:500,
        autoHeight:true,

        // 如果需要分页器
        /* pagination: {
             el: '.swiper-pagination',
         },*/
        // 自动播放时间
        autoplay:{
            delay: 2000,
            disableOnInteraction: true,
        },
        effect:'fade',//淡入
        // effect:"cube",//3d方块
        // effect:"coverflow",// 3d流
        // eccect:"flip",// 3d翻转
        //effect:"slide",//位移
        // 如果需要前进后退按钮
        /*navigation: {
           nextEl: '.swiper-button-next',
               prevEl: '.swiper-button-prev',
       },*/

        // 如果需要滚动条
        /* scrollbar: {
             el: '.swiper-scrollbar',
         },*/
    })
</script>
</body>