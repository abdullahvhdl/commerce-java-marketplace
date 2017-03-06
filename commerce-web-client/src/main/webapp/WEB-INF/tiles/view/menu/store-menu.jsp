<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="mobile-trigger">Menu</div>
<ul class="super-menu mobile-menu menu-table"
	style="table-layout: fixed;" ng-controller="MenuController">
	<li class="drop-down hide-on-mobile icon-only"><a href="#/"><i
			style="margin-right: 5px; font-size: 20px; top: -1px"
			class="fa fa-home"></i></a> <span class="mobile-plus">+</span></li>
	<li class="drop-down" ng-repeat="category in categories"><a
		tabindex="-1"
		href="#/{{storeName+'-'+storeId}}/category/{{getLocaledValue(category.description).name+'-'+category.id}}"><span
			class="main-menu-text">{{getLocaledValue(category.description).name}}</span></a>
		<ul style="min-width: 193px;">
			<li ng-repeat="category in category.subCategories"><a
				tabindex="-1"
				href="#/{{storeName+'-'+storeId}}/category/{{getLocaledValue(category.description).name+'-'+category.id}}"><span
					class="main-menu-text">{{getLocaledValue(category.description).name}}</span></a>
				<ul style="min-width: 193px;">
					<li ng-repeat="category in category.subCategories"><a
						tabindex="-1"
						href="#/{{storeName+'-'+storeId}}/category/{{getLocaledValue(category.description).name+'-'+category.id}}"><span
							class="main-menu-text">{{getLocaledValue(category.description).name}}</span></a>
				</ul></li>
		</ul> <span class="mobile-plus">+</span></li>


	<li class="drop-down"><a href="#" target="_blank"><span
			class="main-menu-text">Information</span></a>
		<ul style="min-width: 193px;">
			<li><a href="#">About Us</a></li>
			<li><a href="#">Account</a></li>
			<li><a href="#">Terms &amp; Conditions</a></li>
		</ul> <span class="mobile-plus">+</span></li>
</ul>
