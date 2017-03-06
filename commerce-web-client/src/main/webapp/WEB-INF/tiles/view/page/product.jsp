<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div ng-controller="ProductController">
	<div id="column-right" class="col-sm-3 hidden-xs side-column"></div>
	<div class="row">
		<div id="cart-popup" class="white-popup mfp-hide">
			<ng-include src="'scripts/app/template/cart-popup.tpl.html'"></ng-include>
		</div>
		<div id="content" class="product-page-content" itemscope=""
			itemtype="http://schema.org/Product">
			<h1 class="heading-title" itemprop="name">{{getLocaledValue(product.description).name}}</h1>
			<div class="row product-info split-50-50">
				<div class="left">
					<div class="image">
						<a href='{{product.image.image1}}?width=700&height=700'><img
							ng-src='{{product.image.image1}}?width=700&height=700'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}" id="image"
							data-largeimg='{{product.image.image1}}?width=700&height=700'
							itemprop="image"></a>
					</div>
					<div class="gallery-text">
						<span>Click Image for Gallery</span>
					</div>

					<div id="product-gallery" class="image-additional">
						<a ng-if="product.image.image1"
							href='{{product.image.image1}}?width=700&height=700'><img
							ng-src='{{product.image.image1}}?width=150&height=150'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}"></a> <a
							ng-if="product.image.image2"
							href='{{product.image.image2}}?width=700&height=700'><img
							ng-src='{{product.image.image2}}?width=150&height=150'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}" itemprop="image"></a>
						<a ng-if="product.image.image3"
							href='{{product.image.image3}}?width=700&height=700'><img
							ng-src='{{product.image.image3}}?width=150&height=150'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}" itemprop="image"></a>

					</div>
					<div class="image-gallery" style="display: none !important;">
						<a ng-if="product.image.image1"
							href='{{product.image.image1}}?width=700&height=700'
							class="swipebox"><img
							ng-src='{{product.image.image1}}?width=150&height=150'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}"></a> <a
							ng-if="product.image.image2"
							href='{{product.image.image2}}?width=700&height=700'
							class="swipebox"><img
							ng-src='{{product.image.image2}}?width=150&height=150'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}"></a> <a
							ng-if="product.image.image3"
							href='{{product.image.image3}}?width=700&height=700'
							class="swipebox"><img
							ng-src='{{product.image.image3}}?width=150&height=150'
							title="{{getLocaledValue(product.description).name}}"
							alt="{{getLocaledValue(product.description).name}}"></a>

					</div>


				</div>
				<div class="right">
					<div id="product" class="product-options">
						<ul class="list-unstyled description">
							<li ng-repeat="displayable in product.displayableAttributes"><span>{{getLocaledValue(displayable.attribute.description).name}}:</span>
								{{getLocaledValue(displayable.attributeValue.description).name}}</li>
						</ul>
						<ul class="list-unstyled price" ng-show="selectedProduct.id">
							<li class="price-old"
								ng-show="getPrice(selectedProduct.originalPrice).price">\${{getPrice(selectedProduct.originalPrice).price}}</li>
							<li class="price-new" itemprop="price"
								ng-show="getPrice(selectedProduct.offerPrice).price">\${{getPrice(selectedProduct.offerPrice).price}}</li>
						</ul>
						<ul class="list-unstyled price" ng-hide="selectedProduct.id">
							<li class="price-old" ng-show="getPrice(product.originalPrice).price">\${{getPrice(product.originalPrice).price}}</li>
							<li class="price-new" itemprop="price"
								ng-show="getPrice(product.offerPrice).price">\${{getPrice(product.offerPrice).price}}</li>
						</ul>
						<div class="options" ng-if="product.buyableAttributes.length">
							<h3>Available Options</h3>
							<div class="form-group"
								ng-repeat="attribute in product.buyableAttributes"
								load-dispatcher>
								<label for="option{{attribute.attribute.id}}"
									class="control-label">{{getLocaledValue(attribute.attribute.description).name}}</label> <select
									name="option{{attribute.attribute.id}}"
									id="option{{attribute.attribute.id}}" class="form-control"
									ng-model="attribute.selectedValue"
									ng-change="findSelectedItem()">
									<option value="" selected="selected">Select</option>
									<option ng-repeat="value in attribute.attributeValues"
										value="{{value}}">{{getLocaledValue(value.description).name}}</option>
								</select>

							</div>
						</div>
						<div class="form-group cart ">
							<div>
								<span class="qty"> <label class="control-label text-qty"
									for="input-quantity">Qty</label> <a ng-click="decreaseQty()"
									class="journal-stepper">-</a><input type="text" name="quantity"
									value="1" size="2" min="1" ng-model="cartProduct.quantity"
									id="input-quantity" class="form-control" required><a
									ng-click="increaseQty()" class="journal-stepper">+</a>
								</span>
								<button type="button" id="button-cart"
									data-loading-text="Loading..." class="button"
									ng-click="addToCart()">
									<span class="button-cart-text">Order Now</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="product-tabs">
				<ul id="tabs" class="nav nav-tabs htabs">
					<li class="active"><a href="#tab-description"
						data-toggle="tab" target="_blank">Description</a></li>

				</ul>
				<div class="tabs-content">
					<div class="tab-pane tab-content active" id="tab-description">
						<p>
							<font color="#222222" face="arial, sans-serif">
								{{getLocaledValue(product.description).description}}</font>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="journal2_custom_sections"
		style="background-attachment: scroll; padding-top: 30px; padding-bottom: 30px">
		<div class="box custom-sections section-product" id="cs-1779178813"
			style="max-width: 1220px">
			<div class="box-heading box-sections box-block">
				<ul>
					<li><a href="javascript:;" data-option-value="section-0"
						class="selected">Also Available at</a></li>
				</ul>
			</div>
			<div class="box-content">
				<div class=section-0">
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
					<div class=" xl-40 lg-40 sm-70 hide-on-mobile">
						<!-- Product #1 Starts -->
						<div class="store-list-container">
							<ol class="stores-list">
								<li class="store-list-item" ng-repeat="store in relativeStores"
									id="{{store.uniqueId}}" ng-click="showWindow(store)"><ng-include
										src="'scripts/app/template/relative-store-list-content.tpl.html'"></ng-include>

								</li>
							</ol>
						</div>
					</div>
					<div ng-cloak class="lg-60 xl-60 sm-100" style="padding: 0px;">
						<div id="map_canvas"></div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
