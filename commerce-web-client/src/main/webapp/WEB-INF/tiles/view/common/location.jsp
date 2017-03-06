<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="search" class="input-group j-min">
	<input type="text" name="searchLocation" value=""
		placeholder="Near location..." autocomplete="off"
		class="form-control input-lg" autocomplete2="off" ng-model="address"
		ng-model-options="{debounce: 500}" ng-blur="clearSuggestions()">
	<div class="button-location">
		<button type="button" ng-click="locateMe()">
			<i class="icon-location"></i>
		</button>
	</div>
	<div class="autocomplete2-suggestions"
		ng-show="locationSuggestions.length">
		<div class="autocomplete2-suggestion"
			ng-repeat="location in locationSuggestions |limitTo:6"
			ng-click="setAddress(location)">
			<a><span>{{location.formatted_address}}</span>
				<div class="clearfix"></div></a>
		</div>
	</div>
</div>