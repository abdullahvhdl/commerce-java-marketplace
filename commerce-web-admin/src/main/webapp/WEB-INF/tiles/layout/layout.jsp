<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href='<spring:url value="/"></spring:url>' id="adminBase">
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Remove Tap Highlight on Windows Phone IE -->
<meta name="msapplication-tap-highlight" content="no" />

<link rel="icon" type="image/png" href="assets/img/favicon-16x16.png"
	sizes="16x16">
<link rel="icon" type="image/png" href="assets/img/favicon-32x32.png"
	sizes="32x32">

<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

<link rel="stylesheet" href="assets/css/angular-ui-tree.min.css"
	media="all">

<!-- uikit -->
<link rel="stylesheet" href="assets/css/uikit.almost-flat.min.css"
	media="all">

<!-- flag icons -->
<link rel="stylesheet" href="assets/icons/flags/flags.min.css"
	media="all">

<!-- altair admin -->
<link rel="stylesheet"
	href='assets/css/<tiles:insertAttribute name="style"></tiles:insertAttribute >'
	media="all">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.12.0/skin-win7/ui.fancytree.min.css" />
<link href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
	type="text/css" rel="stylesheet" />
<!-- matchMedia polyfill for testing media queries in JS -->
<!--[if lte IE 9]>
        <script type="text/javascript" src="bower_components/matchMedia/matchMedia.js"></script>
        <script type="text/javascript" src="bower_components/matchMedia/matchMedia.addListener.js"></script>
    <![endif]-->


</head>

<body class="sidebar_main_open sidebar_main_swipe"
	ng-app="commerceAdmin">
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="sidebar" />
	<tiles:insertAttribute name="body" />

</body>
<!-- common functions -->
<script src="assets/js/common.min.js"></script>

<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.12.0/jquery.fancytree-all.min.js"></script>

<script
	src="//cdn.jsdelivr.net/jquery.ui-contextmenu/1.8.2/jquery.ui-contextmenu.min.js"></script>
<!-- uikit functions -->
<script src="assets/js/uikit_custom.min.js"></script>
<!-- altair common functions/helpers -->
<script src="assets/js/altair_admin_common.min.js"></script>
<script>
	$(function() {
		// enable hires images
		altair_helpers.retina_images();
		// fastClick (touch devices)
		if (Modernizr.touch) {
			FastClick.attach(document.body);
		}
	});
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js"></script>
<script
	src='//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-route.js'></script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.6/angular-resource.js'></script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.6/angular-sanitize.min.js'></script>
<script
	src='//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-messages.js'></script>

<script
	src='https://cdnjs.cloudflare.com/ajax/libs/angular-local-storage/0.2.2/angular-local-storage.min.js'></script>

<%-- <tiles:insertAttribute name="script" /> --%>
<!-- page specific plugins -->

<!--  product edit functions -->
<script src="assets/js/ng-file-upload-all.min.js"></script>
<script src="assets/js/angular-ui-tree.min.js"></script>

<script src='<spring:url value="/scripts/app/app.js"/>'></script>
<script
	src='<spring:url value="/scripts/component/tasks/tasks.service.js"/>'></script>
<script
	src='<spring:url value="/scripts/component/stores/stores.service.js"/>'></script>
<script
	src='<spring:url value="/scripts/component/catalog/catalog.service.js"/>'></script>
<script
	src='<spring:url value="/scripts/component/attributes/attributes.service.js"/>'></script>

<script
	src='<spring:url value="/scripts/controller/tasks/tasks.controller.js"/>'></script>
<script
	src='<spring:url value="/scripts/controller/task/task.controller.js"/>'></script>
<script
	src='<spring:url value="/scripts/controller/unassigned/unassigned.controller.js"/>'></script>
<script
	src='<spring:url value="/scripts/controller/stores/stores.controller.js"/>'></script>
<script
	src='<spring:url value="/scripts/controller/catalog/catalog.controller.js"/>'></script>
<script
	src='<spring:url value="/scripts/controller/catalog/catalog.sidebar.controller.js"/>'></script>
<script
	src='<spring:url value="/scripts/controller/attributes/attributes.controller.js"/>'></script>
</html>