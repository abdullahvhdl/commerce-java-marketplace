<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>

<html dir="ltr" lang="en"
	class="responsive-layout extended-layout header-center header-center-sticky oc2"
	data-j2v="2.5.5">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/><![endif]-->
<!--[if lt IE 9]><script src="//ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script><![endif]-->
<title><tiles:getAsString name="title" /></title>

<meta name="title" content="commerce" />
<meta name="description" content="commerce home page" />
<meta name="keywords" content="" />
<meta property="og:title" content="" />
<meta property="og:description" content="" />
<meta property="og:url" content="" />
<meta property="og:image" content="" />
<link href="assets/favicon.ico" rel="icon" />

<link rel="stylesheet"
	href="//fonts.googleapis.com/css?family=Ubuntu:700,500,regular,italic&amp;subset=latin">
<link rel="stylesheet"
	href='<spring:url value="assets/css/j-strap.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"/>'>

<link rel="stylesheet"
	href='<spring:url value="assets/css/main.min.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="assets/css/icons/style.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="assets/css/custom.css"/>'>


<!--[if (gte IE 6)&(lte IE 8)]><script src="view/theme/journal2/lib/selectivizr/selectivizr.min.js"></script><![endif]-->
<script>
	
</script>
</head>
<body ng-app="commerceApp">
	<!--[if lt IE 9]>
<div class="old-browser">You are using an old browser. Please <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">upgrade to a newer version</a> or <a href="http://browsehappy.com/">try a different browser</a>.</div>
<![endif]-->

	<div ng-view></div>
	<!-- Google Analytics: uncomment and change UA-XXXXX-X to be your site's ID.
        <script>
            (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
            e=o.createElement(i);r=o.getElementsByTagName(i)[0];
            e.src='//www.google-analytics.com/analytics.js';
            r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
            ga('create','UA-XXXXX-X');ga('send','pageview');
        </script>-->
	<!-- google map -->
	<script src="https://maps.googleapis.com/maps/api/js"></script>
	<!-- end google map -->

	<script type="text/javascript"
		src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js'></script>
	<script type="text/javascript"
		src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
	<script type="text/javascript"
		src='<spring:url value="assets/js/main.min.js"/>'></script>
	<script src="//js.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"
		type="text/javascript"></script>
	<!-- build:js scripts/vendor.js -->
	<!-- bower:js -->
	<!--     <script src="bower_components/modernizr/modernizr.js"></script> -->
	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js'></script>
	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-route.min.js'></script>
	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-messages.min.js'></script>
	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-resource.min.js'></script>
	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-sanitize.min.js'></script>
	<script
		src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-cookies.min.js'></script>
	<script
		src='<spring:url value="/bower_components/angular-local-storage/dist/angular-local-storage.min.js"/>'></script>
	<script
		src='<spring:url value="/bower_components/angular-cache-buster/angular-cache-buster.js"/>'></script>


	<!-- build:js({.tmp,src/main/webapp}) scripts/app.js -->
	<script src='<spring:url value="/scripts/app/app.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/auth.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/principal.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/authority.directive.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/services/account.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/services/activate.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/services/password.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/services/register.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/map/map.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/form/form.directive.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/form/pager.directive.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/form/pagination.directive.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/util/truncate.filter.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/util/base64.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/util/parse-links.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/order/order.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/auth/provider/auth.oauth2.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/store/store.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/category/category.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/product/product.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/product/product.search.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/prodAssignedAttr/prodAssignedAttr.service.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/common/currency.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/common/language.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/common/location.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/menu/general-menu.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/menu/store-menu.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/stores/stores.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/modules/banner.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/modules/slider.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/modules/product.carousel.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/store/store.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/product/product.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/category/category.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/search/search.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/navbar/navbar.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/navbar/menu.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/account/login/login.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/account/register/register.controller.js"/>'></script>
	<script
		src='<spring:url value="/scripts/controller/account/activate/activate.controller.js"/>'></script>
	<!-- endbuild -->


</body>
</html>