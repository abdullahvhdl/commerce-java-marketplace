<div class="box journal-carousel carousel-product"
	id="carousel-<%=request.getParameter("identifier")%>"
	ng-controller="ProductCarouselController"
	ng-init="init('<%=request.getParameter("identifier")%>','<%=request.getParameter("produtIds")%>')">
	<div>
		<div class="htabs box-heading ">
			<a href="#carousel-<%=request.getParameter("identifier")%>-0"
				class="atab"><%=request.getParameter("name")%></a>
		</div>
		<div id="carousel-<%=request.getParameter("identifier")%>-0"
			class="owl-carousel tab-content box-content">
			<div class="product-grid-item display-icon block-button "
				ng-repeat="product in products">
				<div class="product-wrapper">
					<a id="quickViewLink" style="display: none;" href=""></a>
					<div class="image">
						<a
							href="#/{{storeName+'-'+storeId}}/product/{{product.productDescription.name+'-'+product.id}}"
							class="has-second-image"><div
								class="p-over p-grid-over"></div>
							<div class="p-over p-grid-over"></div> <img width="250"
							height="250" class="lazy first-image"
							ng-src='{{product.image.image1}}?width=250&height=250'
							title="{{product.productDescription.name}}"
							alt="{{product.productDescription.name}}" style="display: block;">
						</a> <span class="label-sale" ng-show="product.originalPrice.price"><b>-{{Math.ceil((product.originalPrice.price-
								product.offerPrice.price)*100/product.originalPrice.price)}}%</b></span>

					</div>
					<div class="product-details">
						<div class="caption">
							<h4 class="name" style="height: 34px;">
								<a tabindex="-1"
									href="#/{{storeName+'-'+storeId}}/product/{{getLocaledValue(product.description).name+'-'+product.id}}">{{getLocaledValue(product.description).name}}</a>
							</h4>
							<p class="description" style="height: 96px;">{{getLocaledValue(product.description).description}}</p>
							<p class="price">
								<span class="price-old" ng-if="product.originalPrice.price">\${{product.originalPrice.price}}</span>
								<span class="price-new" ng-if="product.offerPrice.price">\${{product.offerPrice.price}}</span>
							</p>
						</div>
						<div class="button-group"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>