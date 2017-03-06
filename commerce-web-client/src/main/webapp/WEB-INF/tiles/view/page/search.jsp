<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" ng-controller="SearchController">
	<div id="content" class="col-sm-9">
		<h1 class="heading-title">Search</h1>
		<div class="product-filter">
			<div class="display">
				<a onclick="Journal.gridView()" class="grid-view active"><i
					class="icon-grid"
					style="margin-right: 5px; color: rgb(0, 0, 0); font-size: 32px"></i></a>
				<a onclick="Journal.listView()" class="list-view"><i
					style="margin-right: 5px; color: rgb(0, 0, 0); font-size: 32px"
					class="icon-list3"></i></a>
			</div>

			<div class="limit">
				<b>Show:</b> <select ng-model="limit" ng-change="changeLimit(limit)">
					<option value="16" selected="selected">16</option>
					<option value="24">24</option>
					<option value="40">40</option>
					<option value="60">60</option>
					<option value="100">100</option>
				</select>
			</div>
			<div class="sort"></div>
		</div>

		<div class="row main-products product-grid"
			data-grid-classes="xs-100 sm-50 md-50 lg-33 xl-25 display-icon block-button">
			<div
				class="product-grid-item xs-100 sm-50 md-50 lg-33 xl-25 display-icon block-button"
				ng-repeat="product in products.content">
				<div class="product-thumb  product-wrapper">
					<a id="quickViewLink" style="display: none;" href=""></a>
					<div class="image">
						<a
							href="#/{{product.store.name+'-'+product.store.id}}/product/{{getLocaledValue(product.description).name+'-'+product.id}}"
							class="has-second-image"><div
								class="p-over p-grid-over"></div>
							<div class="p-over p-grid-over"></div> <img width="250"
							height="250" class="lazy first-image"
							ng-src='{{product.image.image1}}?width=250&height=250'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}" style="display: block;">
						</a>

					</div>
					<div class="product-details">
						<div class="caption">
							<h4 class="name" style="height: 34px;">
								<a tabindex="-1"
									href="#/{{product.store.name+'-'+product.store.id}}/product/{{getLocaledValue(product.description).name+'-'+product.id}}">{{getLocaledValue(product.description).name}}</a>
							</h4>
							<p class="description" style="height: 96px;">{{getLocaledValue(product.description).description}}</p>
							<p>Available at {{product.store.name}} within
								{{product.store.distance}} mi</p>
							<p class="price">
								<span class="price-old"
									ng-if="getPrice(product.store.originalPrice).price">\${{getPrice(product.store.originalPrice).price}}</span>
								<span class="price-new" ng-if="getPrice(product.store.offerPrice).price">\${{getPrice(product.store.offerPrice).price}}</span>
							</p>
						</div>
						<div class="button-group"></div>
					</div>
				</div>
			</div>
		</div>
		<ng-pagination pageable="products"></ng-pagination>
	</div>
</div>