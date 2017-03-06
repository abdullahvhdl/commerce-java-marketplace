<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header id="header_main">
	<div class="header_main_content">
		<nav class="uk-navbar">
			<!-- main sidebar switch -->
			<a href="#" id="sidebar_main_toggle" class="sSwitch sSwitch_left">
				<span class="sSwitchIcon"></span>
			</a>
			<!-- secondary sidebar switch -->
			<a href="#" id="sidebar_secondary_toggle"
				class="sSwitch sSwitch_right sidebar_secondary_check"> <span
				class="sSwitchIcon"></span>
			</a>
			<div id="menu_top" class="uk-float-left uk-hidden-small">
				<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
					<a href="#" class="top_menu_toggle"><i
						class="material-icons md-24">&#xE8F0;</i></a>
					<div class="uk-dropdown uk-dropdown-width-2">
						<div class="uk-grid uk-dropdown-grid" data-uk-grid-margin>
							<div class="uk-width-3">
								<div
									class="uk-grid uk-grid-width-medium-1-3 uk-margin-top uk-margin-bottom uk-text-center"
									data-uk-grid-margin>
									<a href='<spring:url value="/"></spring:url>'> <i
										class="material-icons md-36">&#xE158;</i> <span
										class="uk-text-muted uk-display-block">Dashboard</span>
									</a> <a href='<spring:url value="/stores"></spring:url>'> <i
										class="material-icons md-36">store_mall_directory</i> <span
										class="uk-text-muted uk-display-block">Stores</span>
									</a> <a href='<spring:url value="/catalog"></spring:url>'> <i
										class="material-icons md-36">&#xE0B9;</i> <span
										class="uk-text-muted uk-display-block">Catalogs</span>
									</a> <a href='<spring:url value="/orders/"></spring:url>'> <i
										class="material-icons md-36 md-color-red-600">&#xE53E;</i> <span
										class="uk-text-muted uk-display-block">Orders</span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="uk-navbar-flip">
				<ul class="uk-navbar-nav user_actions">
					<li data-uk-dropdown="{mode:'click'}"><a href="#"
						class="user_action_image"><img class="md-user-image"
							src="assets/img/avatars/avatar_11_tn.png" alt="" /></a>
						<div class="uk-dropdown uk-dropdown-small uk-dropdown-flip">
							<ul class="uk-nav js-uk-prevent">
								<li><a href='<spring:url value="/logout"></spring:url>'>Logout</a></li>
							</ul>
						</div></li>
				</ul>
			</div>
		</nav>
	</div>
</header>
<!-- main header end -->