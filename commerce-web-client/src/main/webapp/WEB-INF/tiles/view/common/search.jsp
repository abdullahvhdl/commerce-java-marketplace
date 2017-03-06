<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="search" class="input-group j-min">
  <input type="text" name="search" value="" placeholder="search products.." autocomplete="off" class="form-control input-lg" ng-model="searchQuery" ng-keydown="($event.which === 13)?serachProducts():0" />
  <div class="button-search"><button type="button" ng-click="serachProducts()"><i></i></button></div>
</div>