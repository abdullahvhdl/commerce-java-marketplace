<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<link href="favicon.ico" rel="icon" />

<link rel="stylesheet"
	href="//fonts.googleapis.com/css?family=Ubuntu:700,500,regular,italic&amp;subset=latin">
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/j-strap.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="/view/theme/journal2/lib/jquery.ui/jquery-ui-slider.min.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="/view/theme/journal2/lib/owl-carousel/owl.carousel.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="/view/theme/journal2/lib/owl-carousel/owl.transitions.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="/view/theme/journal2/lib/magnific-popup/magnific-popup.css"/>'>
<link rel="stylesheet"
	href='<spring:url value="/view/theme/journal2/lib/rs-plugin/css/settings.css"/>'>


<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/hint.min.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/journal.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/features.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/header.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/module.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/pages.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/account.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/blog-manager.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/side-column.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/product.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/category.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/footer.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/icons.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/icons/style.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/responsive.css"/>'>
<link rel="stylesheet" href='<spring:url value="/view/theme/journal2/css/custom.css"/>'>


<script type="text/javascript"
	src='<spring:url value="/view/javascript/jquery/jquery-2.1.1.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/javascript/bootstrap/js/bootstrap.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/jquery/jquery-migrate-1.2.1.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/jquery.ui/jquery-ui-slider.min.js"/>'></script>
<%-- <script type="text/javascript" src='<spring:url value="/view/javascript/common.js"/>'></script> --%>
<script type="text/javascript"
	src='<spring:url value="/view/javascript/jquery/jquery.total-storage.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/jquery.tabs/tabs.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/owl-carousel/owl.carousel.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/magnific-popup/jquery.magnific-popup.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/actual/jquery.actual.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/countdown/jquery.countdown.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/image-zoom/jquery.imagezoom.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/lazy/jquery.lazy.1.6.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/swipebox/source/jquery.swipebox.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/rs-plugin/js/jquery.themepunch.tools.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/rs-plugin/js/jquery.themepunch.revolution.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/isotope/jquery.isotope.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/isotope/jquery.isotope.sloppy-masonry.min.js"/>'></script>
<script type="text/javascript"
	src='<spring:url value="/view/theme/journal2/lib/quovolver/jquery.quovolver.js"/>'></script>

<script type="text/javascript" src='<spring:url value="/view/theme/journal2/js/journal.js"/>'></script>
<script src="//js.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"
	type="text/javascript"></script>

<!--[if (gte IE 6)&(lte IE 8)]><script src="view/theme/journal2/lib/selectivizr/selectivizr.min.js"></script><![endif]-->
<script>
	
</script>
</head>
<body ng-app="commerceApp">
	<!--[if lt IE 9]>
<div class="old-browser">You are using an old browser. Please <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">upgrade to a newer version</a> or <a href="http://browsehappy.com/">try a different browser</a>.</div>
<![endif]-->
	<tiles:insertAttribute name="header" />
	<div id="top-modules">
		<tiles:insertAttribute name="top-module" />
	</div>
	<div class="exended-container">
		<div id="container" class="container j-container">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<div id="bottom-modules"></div>
	<tiles:insertAttribute name="footer" />
	<div class="scroll-top"></div>

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
	<!-- build:js scripts/vendor.js -->
	<!-- bower:js -->
	<!--     <script src="bower_components/modernizr/modernizr.js"></script> -->
	<script src='<spring:url value="/bower_components/json3/lib/json3.js"/>'></script>
	<script src='<spring:url value="/bower_components/angular/angular.js"/>'></script>
	<script src='<spring:url value="/bower_components/angular-resource/angular-resource.js"/>'></script>
	<script src='<spring:url value="/bower_components/angular-cookies/angular-cookies.js"/>'></script>
	<script src='<spring:url value="/bower_components/angular-sanitize/angular-sanitize.js"/>'></script>
	<script src='<spring:url value="/bower_components/angular-translate/angular-translate.js"/>'></script>
	<script
		src='<spring:url value="/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js"/>'></script>
	<script
		src='<spring:url value="/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.js"/>'></script>
	<script
		src='<spring:url value="/bower_components/angular-dynamic-locale/src/tmhDynamicLocale.js"/>'></script>
	<script
		src='<spring:url value="/bower_components/angular-local-storage/dist/angular-local-storage.js"/>'></script>
	<script
		src='<spring:url value="/bower_components/angular-cache-buster/angular-cache-buster.js"/>'></script>
	<script
		src='<spring:url value="/bower_components/ngInfiniteScroll/build/ng-infinite-scroll.js"/>'></script>

	<!-- build:js({.tmp,src/main/webapp}) scripts/app.js -->
	<script src='<spring:url value="/scripts/app/app.js"/>'></script>
	<script src='<spring:url value="/scripts/app/app.constants.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/auth.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/principal.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/authority.directive.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/services/account.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/services/activate.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/services/password.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/services/register.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/map/map.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/form/form.directive.js"/>'></script>
	<script src='<spring:url value="/scripts/component/form/pager.directive.js"/>'></script>
	<script src='<spring:url value="/scripts/component/form/pagination.directive.js"/>'></script>
	<script src='<spring:url value="/scripts/component/language/language.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/language/language.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/component/user/user.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/util/truncate.filter.js"/>'></script>
	<script src='<spring:url value="/scripts/component/util/base64.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/util/parse-links.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/order/order.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/auth/provider/auth.oauth2.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/store/store.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/store/store.search.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/category/category.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/category/category.search.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/product/product.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/product/product.search.service.js"/>'></script>
	<script src='<spring:url value="/scripts/component/breadcrumb/breadcrumb.js"/>'></script>
	<script
		src='<spring:url value="/scripts/component/prodAssignedAttr/prodAssignedAttr.service.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/common/currency.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/common/language.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/common/location.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/menu/general-menu.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/menu/store-menu.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/stores/stores.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/modules/banner.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/modules/slider.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/store/store.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/product/product.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/navbar/navbar.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/account/login/login.controller.js"/>'></script>
	<script src='<spring:url value="/scripts/controller/account/register/register.controller.js"/>'></script>
	<!-- endbuild -->
	<script type="text/javascript"
		src='<spring:url value="/view/theme/journal2/lib/swipebox/source/jquery.swipebox.js"/>'></script>
	<script type="text/javascript"
		src='<spring:url value="/view/theme/journal2/lib/hover-intent/jquery.hoverIntent.min.js"/>'></script>
	<script type="text/javascript"
		src='<spring:url value="/view/theme/journal2/lib/pnotify/jquery.pnotify.min.js"/>'></script>
	<script type="text/javascript"
		src='<spring:url value="/view/theme/journal2/lib/autocomplete2/jquery.autocomplete2.min.js"/>'></script>
	<script type="text/javascript"
		src='<spring:url value="/view/theme/journal2/lib/respond/respond.js"/>'></script>
	<script type="text/javascript"
		src='<spring:url value="/view/theme/journal2/lib/sticky/jquery.sticky.js"/>'></script>
	<script type="text/javascript" src='<spring:url value="/view/theme/journal2/js/init.js"/>'></script>
	<script type="text/javascript" src='<spring:url value="/view/theme/journal2/js/custom.js"/>'></script>
</body>
</html>