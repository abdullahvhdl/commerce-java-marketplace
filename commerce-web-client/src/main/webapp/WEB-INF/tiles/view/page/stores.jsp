<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div ng-controller="StoresController">
	<div class="row">
		<div class="xl-90 md-80 sm-70"></div>
		<div class="xl-10 md-20 sm-30">
			Within <select id="input-limit" ng-model="maximumDistance"
				class="form-control">
				<option value="30">30</option>
				<option value="50">50</option>
				<option value="100" selected="selected">100</option>
				<option value="250" selected="selected">250</option>
			</select>
		</div>
	</div>
	<div class=" xl-40 lg-40 hide-on-mobile">
		<!-- Product #1 Starts -->
		<div class="store-list-container">
			<ol class="stores-list">
				<li class="store-list-item" ng-repeat="store in stores"
					id="{{store.uniqueId}}" ng-click="showWindow(store)"><ng-include
						src="'scripts/app/template/store-list-content.tpl.html'"></ng-include></li>
			</ol>
		</div>

		<!-- Product #1 Ends -->
	</div>

	<div ng-cloak class="lg-60 xl-60 sm-100" style="padding: 0px;">
		<div id="map_canvas"></div>
	</div>
</div>

