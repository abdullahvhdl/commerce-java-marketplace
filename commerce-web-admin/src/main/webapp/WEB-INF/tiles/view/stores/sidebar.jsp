<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- main sidebar -->
<aside id="sidebar_main">
	<div class="sidebar_main_header">
		<div class="sidebar_logo">
			<a href='<spring:url value="/"></spring:url>' class="sSidebar_hide"><img
				src="assets/img/logo_main.png" alt="" height="15" width="71" /></a> <a
				href='<spring:url value="/"></spring:url>' class="sSidebar_show"><img
				src="assets/img/logo_main_small.png" alt="" height="32" width="32" /></a>
		</div>
	</div>
	<div class="menu_section">
		<ul>
			<li title="Stores"><a href='<spring:url value="/stores"></spring:url>'> <span
					class="menu_icon"><i class="material-icons">store_mall_directory</i></span> <span
					class="menu_title">Stores</span>
			</a></li>
			<li title="Mailbox"><a href='<spring:url value="/stores/csv"></spring:url>'> <span
					class="menu_icon"><i class="material-icons">&#xE158;</i></span> <span
					class="menu_title">Stores CSV</span>
			</a></li>
			
		</ul>
	</div>
</aside>
<!-- main sidebar end -->