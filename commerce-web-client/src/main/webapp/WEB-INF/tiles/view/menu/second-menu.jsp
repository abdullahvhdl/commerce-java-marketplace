<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div >
	<div class="links j-min" ng-if="!isAuthenticated()">
		<a href='#/login' class="secondary-menu-item-1"><i
			style="margin-right: 5px; font-size: 16px" class="icon-signin"></i><span
			class="top-menu-link">Login</span></a> <a href='#/register'
			class="secondary-menu-item-2"><i
			style="margin-right: 5px; font-size: 15px; top: -1px" class="icon-chevron-sign-up" ></i><span
			class="top-menu-link">Register</span></a>
	</div>
	<div class="links j-min" ng-if="isAuthenticated()">
		<a href='#/account' class="secondary-menu-item-1"><i
			style="margin-right: 5px; font-size: 16px" class="icon-user"></i><span
			class="top-menu-link">account</span></a> <a ng-click="logout()"
			class="secondary-menu-item-2"><i
			style="margin-right: 5px; font-size: 15px; top: -1px" class="icon-signout"></i><span
			class="top-menu-link">Logout</span></a>
	</div>
</div>