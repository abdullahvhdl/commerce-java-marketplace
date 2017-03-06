<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header class="journal-header-center">
	<div class="journal-top-header j-min z-1"></div>
	<div class="journal-menu-bg j-min z-0"></div>
	<div class="journal-center-bg j-100 z-0"></div>
	<div id="header" class="journal-header row z-2" ng-controller="NavbarController">
		<div class="journal-links j-min xs-100 sm-100 md-45 lg-45 xl-45">
			<div class="links j-min">
				<jsp:include page="../menu/top-menu.jsp" />
			</div>
		</div>
		<div class="journal-language j-min xs-5 sm-5 md-5 lg-5 xl-5">
			<jsp:include page="../common/language.jsp" />
		</div>

		<div class="journal-currency j-min xs-5 sm-5 md-5 lg-5 xl-5">
			<jsp:include page="../common/currency.jsp" />
		</div>

		<div class="journal-secondary j-min xs-100 sm-100 md-45 lg-45 xl-45">
			<div class="links j-min">
				<jsp:include page="../menu/second-menu.jsp" />
			</div>
		</div>
		<div class="journal-search j-min xs-100 sm-50 md-30 lg-25 xl-25">
			<jsp:include page="../common/search.jsp" />
		</div>
		<div class="journal-logo j-100 xs-100 sm-100 md-40 lg-50 xl-50">
			<div id="logo">
				<a href='#/' > <img src='<spring:url value="/assets/logo.svg"/>' width="268" height="50"
					alt="Commerce" title="Commerce">
				</a>
			</div>
		</div>
		<div class="journal-cart row j-min xs-100 sm-50 md-30 lg-25 xl-25">
			<jsp:include page="../common/location.jsp" />
		</div>
		<div class="journal-menu j-min xs-100 sm-100 md-100 lg-100 xl-100">
			<jsp:include page="../menu/store-menu.jsp" />
		</div>
		<script>
			if ($(window).width() < 760) {
				$('.journal-header-center .journal-links').before(
						$('.journal-header-center .journal-language'));
				$('.journal-header-center .journal-logo').after(
						$('.journal-header-center .journal-search'));
			}
		</script>
	</div>
</header>