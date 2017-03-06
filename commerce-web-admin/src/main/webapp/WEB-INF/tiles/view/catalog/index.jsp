<div id="page_content" ng-controller="CatalogController">
	<div class="uk-modal" id="buyableAttributesModal">
		<div class="uk-modal-dialog">
			<button type="button" class="uk-modal-close uk-close"></button>
			<div class="uk-modal-header">Buyable Attributes</div>
			<label for="attribute_filter">Filter</label> <input type="text"
				class="md-input" id="attribute_filter" ng-model="buyableFilter">
			<div class="uk-width-medium-1-1">
				<div class="uk-overflow-container">
					<table class="uk-table uk-table-nowrap">
						<thead>
							<tr>
								<th class="uk-width-1-10 uk-text-left">Name</th>
								<th class="uk-width-2-10 uk-text-center">Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr
								data-ng-repeat="attribute in buyableAttributes | filter:buyableFilter | limitTo:10">
								<td class="uk-text-left">{{getLocaledValue(attribute.description).name}}</td>
								<td class="uk-text-center"><button
										class="md-btn md-btn-success"
										ng-click="selectBuyableAttribute(attribute)">Select</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="uk-modal" id="displayableAttributesModal">
		<div class="uk-modal-dialog">
			<button type="button" class="uk-modal-close uk-close"></button>
			<div class="uk-modal-header">Displayable Attributes</div>
			<label for="attribute_filter">Filter</label> <input type="text"
				class="md-input" id="attribute_filter" ng-model="displayableFilter">
			<div class="uk-width-medium-1-1">
				<div class="uk-overflow-container">
					<table class="uk-table uk-table-nowrap">
						<thead>
							<tr>
								<th class="uk-width-1-10 uk-text-left">Name</th>
								<th class="uk-width-2-10 uk-text-center">Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr
								data-ng-repeat="attribute in displayableAttributes | filter:displayableFilter | limitTo:10">
								<td class="uk-text-left">{{getLocaledValue(attribute.description).name}}</td>
								<td class="uk-text-center"><button
										class="md-btn md-btn-success"
										ng-click="selectDisplayableAttribute(attribute)">Select</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="uk-modal-footer"></div>
		</div>
	</div>
	<div class="uk-modal" id="newProductModal">
		<div class="uk-modal-dialog">
			<button type="button" class="uk-modal-close uk-close"></button>
			<div class="uk-modal-header">Search Product</div>
			<label for="attribute_filter">Filter</label> <input type="text"
				class="md-input" id="attribute_filter" ng-model="productName"
				ng-change="searchProducts(productName)">
				<button type="button" class="md-btn md-btn-primary" ng-click="createNewProduct()">Create New Product</button>
			<div class="uk-width-medium-1-1">
				<div class="uk-overflow-container">
					<table class="uk-table">
						<thead>
							<tr>
								<th>Image</th>
								<th>Product Name</th>
								<th>Product Description</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="product in productResults">

								<td><img class="img_thumb"
									ng-src="{{product.image.image1}}?height=80&width=80" alt=""></td>
								<td class="uk-text-large uk-text-nowrap">{{getLocaledValue(product.description).name}}</td>
								<td class="uk-text-nowrap">{{getLocaledValue(product.description).description}}</td>
								<td class="uk-text-nowrap"><a class="md-btn md-btn-primary"
									ng-click="addProductToCategory(product.id)">Add</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="page_heading">
		<h1 id="product_edit_name">Manage Catalogs</h1>
		<h3 class="heading_a">Store</h3>
		<select class="md-input" ng-model="selectedStore"
			ng-options="store as store.name for store in stores track by store.id">
		</select>

	</div>
	<div id="page_content_inner" ng-if="view =='category'">
		<div class="uk-grid" data-uk-grid-margin>
			<div class="uk-width-1-1">
				<div class="md-card">
					<div class="md-card-toolbar">
						<div class="md-card-toolbar-actions">
							<button class="md-btn md-btn-success" ng-click="addNewProduct()">Add
								New</button>
						</div>
						<h3 class="md-card-toolbar-heading-text">{{getLocaledValue(selectedCategory.description).name}}
							Products</h3>
					</div>
					<div class="md-card-content">
						<div class="uk-grid" data-uk-grid-margin>
							<div class="uk-width-1-1">
								<label for="product_search_name">Filter (name,
									description and price)</label> <input type="text" class="md-input"
									id="product_search_name" ng-model="nameFilter"
									ng-change="page=0">
								<div class="uk-overflow-container">
									<table class="uk-table">
										<thead>
											<tr>
												<th>Image</th>
												<th>Product Name</th>
												<th>Offer Price</th>
												<th>Original Price</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="product in products | filter:nameFilter">

												<td><img class="img_thumb"
													ng-src="{{product.image.image1}}?height=80&width=80" alt=""></td>
												<td class="uk-text-large uk-text-nowrap"><a
													ng-click="selectProduct(product)">{{getLocaledValue(product.description).name}}</a></td>
												<td class="uk-text-nowrap">\${{
													getPrice(product.offerPrice).price}}</td>
												<td class="uk-text-nowrap">\${{
													getPrice(product.originalPrice).price}}</td>
												<td class="uk-text-nowrap"><a ng-click="removeProduct(product)"><i
														class="material-icons md-24">&#xE872;</i></a></td>
											</tr>
										</tbody>
									</table>
								</div>
								<ul class="uk-pagination uk-margin-medium-top"
									ng-hide="nameFilter"
									ng-init="totalNumOfPages=window.ceil(stores.length/limit)">
									<li data-ng-class="{'uk-disabled':page==0}"><a
										data-ng-click="setPage(0)"><span><i
												class="uk-icon-angle-double-left"></i></span></a></li>
									<li data-ng-repeat="p in range(0,stores.length/limit)"
										data-ng-class="{'uk-active':page == p}"><a
										data-ng-click="setPage(p)"><span>{{p+1}}</span></a></li>
									<li data-ng-class="{'uk-disabled':p>totalNumberOfPages-1}"><a
										data-ng-click="setPage(totalNumberOfPages-1)"><i
											class="uk-icon-angle-double-right"></i></a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="page_content_inner" ng-if="view =='product'">
		<div class="md-fab-wrapper">
			<a class="md-fab md-fab-primary" ng-click="saveProduct()"> <i
				class="material-icons">save</i>
			</a>
		</div>
		<div class="uk-grid" data-uk-grid-margin>
			<div class="uk-width-1-1">
				<form action="" class="uk-form-stacked" id="product_edit_form" novalidate>
					<div class="uk-grid uk-grid-medium" data-uk-grid-margin>
						<div class="uk-width-xLarge-2-10 uk-width-large-3-10">
							<div class="md-card">
								<div class="md-card-toolbar">
									<h3 class="md-card-toolbar-heading-text">Photos</h3>
								</div>
								<div class="md-card-content">
									<div
										class="uk-margin-bottom uk-text-center uk-position-relative"
										data-ng-if="selectedProduct.image.image1">
										<button type="button" data-ng-click="deleteImage1()"
											class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
										<img
											data-ng-src="{{selectedProduct.image.image1}}?height=250&width=250"
											alt="" class="img_medium dense-image dense-ready">
									</div>
									<div
										class="uk-margin-bottom uk-text-center uk-position-relative"
										data-ng-if="!selectedProduct.image.image1">
										<div class="uk-form-file md-btn md-btn-primary">
											Select <input id="form-file" type="file"
												data-ng-file-select="uploadImage1($files)"
												data-ngf-select="uploadImage1($files)" accept="image/*">
										</div>
									</div>
									<ul class="uk-grid uk-grid-width-small-1-3 uk-text-center"
										data-uk-grid-margin="">
										<li class="uk-position-relative"
											data-ng-if="selectedProduct.image.image2">
											<button type="button" data-ng-click="deleteImage2()"
												class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
											<img
											data-ng-src="{{selectedProduct.image.image2}}?height=100&width=100"
											alt="" class="img_small dense-image dense-ready">
										</li>
										<li class="uk-position-relative"
											data-ng-if="!selectedProduct.image.image2">
											<div class="uk-form-file md-btn md-btn-primary">
												Select <input id="form-file" type="file"
													data-ngf-select="uploadImage2($files)" accept="image/*">
											</div>
										</li>
										<li class="uk-position-relative"
											data-ng-if="selectedProduct.image.image3">
											<button type="button" data-ng-click="deleteImage3()"
												class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
											<img
											data-ng-src="{{selectedProduct.image.image3}}?height=100&width=100"
											alt="" class="img_small dense-image dense-ready">
										</li>
										<li class="uk-position-relative"
											data-ng-if="!selectedProduct.image.image3">
											<div class="uk-form-file md-btn md-btn-primary">
												Select <input id="form-file" type="file"
													data-ngf-select="uploadImage3($files)" accept="image/*">
											</div>
										</li>
										<li class="uk-position-relative"
											data-ng-if="selectedProduct.image.image4">
											<button type="button" data-ng-click="deleteImage4()"
												class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
											<img
											data-ng-src="{{selectedProduct.image.image4}}?height=100&width=100"
											alt="" class="img_small dense-image dense-ready">
										</li>
										<li class="uk-position-relative"
											data-ng-if="!selectedProduct.image.image4">
											<div class="uk-form-file md-btn md-btn-primary">
												Select <input id="form-file" type="file"
													data-ngf-select="uploadImage4($files)" accept="image/*">
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="md-card">
								<div class="md-card-toolbar">
									<h3 class="md-card-toolbar-heading-text">Data</h3>
								</div>
								<div class="md-card-content">
									<div class="uk-form-row">
										<div class="uk-input-group">
											<span class="uk-input-group-addon"> <i
												class="uk-icon-usd"></i>
											</span> <label for="productPrice">Price</label> <input required type="text"
												class="md-input" name="productPrice" id="productPrice"
												ng-model="selectedProduct.offerPrice[0].price" />
										</div>

									</div>
									<div class="uk-form-row">
										<div class="uk-input-group">
											<span class="uk-input-group-addon"> <i
												class="uk-icon-usd"></i>
											</span> <label for="productOldPrice">Old Price</label> <input
												type="text" class="md-input" name="productOldPrice"
												id="productOldPrice"
												ng-model="selectedProduct.originalPrice[0].price" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="uk-width-xLarge-8-10  uk-width-large-7-10">
							<div class="md-card">
								<div class="md-card-toolbar">
									<h3 class="md-card-toolbar-heading-text">Details</h3>
								</div>
								<div class="md-card-content large-padding">
									<div class="uk-grid uk-grid-divider uk-grid-medium"
										data-uk-grid-margin>
										<div class="uk-width-large-1-2">
											<div class="uk-form-row">
												<label for="uniqueId">Unique Id</label> <input required
													type="text" class="md-input" id="uniqueId"
													name="uniqueId"
													ng-model="selectedProduct.uniqueId" />
											</div>
											<div class="uk-form-row">
												<label for="productName">Product Name</label> <input required
													type="text" class="md-input" id="productName"
													name="productName"
													ng-model="selectedProduct.description[0].name" />
											</div>
											<div class="uk-form-row">
												<label for="productBrand">Brand</label> <input type="text"
													class="md-input" id="productBrand" name="productBrand"
													ng-model="selectedProduct.brand" />
											</div>
										</div>
										<div class="uk-width-large-1-2">
											<div class="uk-form-row">
												<label for="product_edit_description_control">Description</label>
												<textarea class="md-input"
													name="product_edit_description_control"
													id="product_edit_description_control" cols="30" rows="5"
													ng-model="selectedProduct.description[0].description"></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="md-card">
								<div class="md-card-toolbar">
									<div class="md-card-toolbar-actions">
										<button type="button" class="md-btn md-btn-success"
											ng-click="addNewAttribute()">New Attribute</button>
									</div>
									<h3 class="md-card-toolbar-heading-text">Displayable
										Attributes</h3>
								</div>
								<div class="md-card-content">
									<div class="uk-grid" data-uk-grid-margin>
										<div class="uk-width-medium-1-1">
											<div class="uk-overflow-container">
												<table class="uk-table">
													<thead>
														<tr>
															<th class="uk-width-4-10 uk-text-nowrap">Attribute
																Name</th>
															<th class="uk-width-4-10 uk-text-center uk-text-nowrap">Attribute
																Value</th>
															<th class="uk-width-2-10 uk-text-nowrap">Delete</th>
														</tr>
													</thead>
													<tbody>
														<tr
															ng-repeat="assignedAttribute in selectedProduct.displayableAttributes">
															<td>{{getLocaledValue(assignedAttribute.attribute.description).name}}</td>
															<td class="uk-text-right uk-text-middle"><select
																required class="md-input"
																ng-model="assignedAttribute.attributeValue"
																ng-options="value as value.description[0].name for value in assignedAttribute.attribute.attributeValues track by value.id">
															</select></td>
															<td><button type="button"
																	class="md-btn md-btn-danger"
																	ng-click="deleteDisplayableAttribute(assignedAttribute)">Delete</button></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="md-card">
								<div class="md-card-toolbar">
									<div class="md-card-toolbar-actions">
										<button type="button" class="md-btn md-btn-success"
											ng-click="addNewBuaybleAttribute()">New Attribute</button>
									</div>
									<h3 class="md-card-toolbar-heading-text">Buyable
										Attributes</h3>
								</div>
								<div class="md-card-content">
									<div class="uk-grid" data-uk-grid-margin>
										<div class="uk-width-medium-1-1">
											<div class="uk-overflow-container">
												<table class="uk-table">
													<thead>
														<tr>
															<th class="uk-width-4-10 uk-text-nowrap">Attribute
																Name</th>
															<th class="uk-width-2-10 uk-text-nowrap">Delete</th>
														</tr>
													</thead>
													<tbody>
														<tr
															ng-repeat="assignedAttribute in selectedProduct.buyableAttributes">
															<td>{{getLocaledValue(assignedAttribute.attribute.description).name}}</td>
															<td><button type="button"
																	class="md-btn md-btn-danger"
																	ng-click="deleteBuyableAttribute(assignedAttribute)">Delete</button></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="uk-width-1-1" style="margin-top: 25px">
				<div class="md-card">
					<div class="md-card-toolbar">
						<div class="md-card-toolbar-actions">
							<button type="button" class="md-btn md-btn-success"
								ng-click="addNewItem()">Add Item</button>
						</div>
						<h3 class="md-card-toolbar-heading-text">Product Items</h3>
					</div>
					<div class="md-card-content">
						<div class="uk-grid" data-uk-grid-margin>
							<div class="uk-width-medium-1-1">
								<div class="uk-overflow-container">
									<table class="uk-table">
										<thead>
											<tr>
												<th>Image</th>
												<th>Product Name</th>
												<th>Product Description</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="product in selectedProduct.items">
												<td><img class="img_thumb"
													ng-src="{{product.image.image1}}?height=80&width=80" alt=""></td>
												<td class="uk-text-large uk-text-nowrap">{{getLocaledValue(product.description).name}}</td>
												<td class="uk-text-nowrap">{{getLocaledValue(product.description).description}}</td>
												<td class="uk-text-nowrap"><a
													class="md-btn md-btn-primary"
													ng-click="editItem(product)">Edit</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="page_content_inner" ng-if="view =='item'">
		<div class="md-fab-wrapper">
			<a class="md-fab md-fab-primary" ng-click="saveItem()"> <i
				class="material-icons">save</i>
			</a>
		</div>
		<div class="uk-grid" data-uk-grid-margin>
			<div class="uk-width-1-1">
				<form action="" class="uk-form-stacked" id="product_edit_form">
					<div class="uk-grid uk-grid-medium" data-uk-grid-margin>
						<div class="uk-width-xLarge-2-10 uk-width-large-3-10">
							<div class="md-card">
								<div class="md-card-toolbar">
									<h3 class="md-card-toolbar-heading-text">Photos</h3>
								</div>
								<div class="md-card-content">
									<div
										class="uk-margin-bottom uk-text-center uk-position-relative"
										data-ng-if="selectedItem.image.image1">
										<button type="button" data-ng-click="deleteImage1()"
											class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
										<img
											data-ng-src="{{selectedItem.image.image1}}?height=250&width=250"
											alt="" class="img_medium dense-image dense-ready">
									</div>
									<div
										class="uk-margin-bottom uk-text-center uk-position-relative"
										data-ng-if="!selectedItem.image.image1">
										<div class="uk-form-file md-btn md-btn-primary">
											Select <input id="form-file" type="file"
												data-ng-file-select="uploadImage1($files)"
												data-ngf-select="uploadImage1($files)" accept="image/*">
										</div>
									</div>
									<ul class="uk-grid uk-grid-width-small-1-3 uk-text-center"
										data-uk-grid-margin="">
										<li class="uk-position-relative"
											data-ng-if="selectedItem.image.image2">
											<button type="button" data-ng-click="deleteImage2()"
												class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
											<img
											data-ng-src="{{selectedItem.image.image2}}?height=100&width=100"
											alt="" class="img_small dense-image dense-ready">
										</li>
										<li class="uk-position-relative"
											data-ng-if="!selectedItem.image.image2">
											<div class="uk-form-file md-btn md-btn-primary">
												Select <input id="form-file" type="file"
													data-ngf-select="uploadImage2($files)" accept="image/*">
											</div>
										</li>
										<li class="uk-position-relative"
											data-ng-if="selectedItem.image.image3">
											<button type="button" data-ng-click="deleteImage3()"
												class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
											<img
											data-ng-src="{{selectedItem.image.image3}}?height=100&width=100"
											alt="" class="img_small dense-image dense-ready">
										</li>
										<li class="uk-position-relative"
											data-ng-if="!selectedItem.image.image3">
											<div class="uk-form-file md-btn md-btn-primary">
												Select <input id="form-file" type="file"
													data-ngf-select="uploadImage3($files)" accept="image/*">
											</div>
										</li>
										<li class="uk-position-relative"
											data-ng-if="selectedItem.image.image4">
											<button type="button" data-ng-click="deleteImage4()"
												class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
											<img
											data-ng-src="{{selectedItem.image.image4}}?height=100&width=100"
											alt="" class="img_small dense-image dense-ready">
										</li>
										<li class="uk-position-relative"
											data-ng-if="!selectedItem.image.image4">
											<div class="uk-form-file md-btn md-btn-primary">
												Select <input id="form-file" type="file"
													data-ngf-select="uploadImage4($files)" accept="image/*">
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="md-card">
								<div class="md-card-toolbar">
									<h3 class="md-card-toolbar-heading-text">Data</h3>
								</div>
								<div class="md-card-content">
									<div class="uk-form-row">
										<div class="uk-input-group">
											<span class="uk-input-group-addon"> <i
												class="uk-icon-usd"></i>
											</span> <label for="productPrice">Price</label> <input type="text"
												class="md-input" name="productPrice" id="productPrice"
												ng-model="selectedItem.offerPrice[0].price" />
										</div>

									</div>
									<div class="uk-form-row">
										<div class="uk-input-group">
											<span class="uk-input-group-addon"> <i
												class="uk-icon-usd"></i>
											</span> <label for="productOldPrice">Old Price</label> <input
												type="text" class="md-input" name="productOldPrice"
												id="productOldPrice"
												ng-model="selectedItem.originalPrice[0].price" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="uk-width-xLarge-8-10  uk-width-large-7-10">
							<div class="md-card">
								<div class="md-card-toolbar">
									<h3 class="md-card-toolbar-heading-text">Details</h3>
								</div>
								<div class="md-card-content large-padding">
									<div class="uk-grid uk-grid-divider uk-grid-medium"
										data-uk-grid-margin>
										<div class="uk-width-large-1-2">
											<div class="uk-form-row">
												<label for="uniqueId">Unique Id</label> <input required
													type="text" class="md-input" id="uniqueId"
													name="uniqueId"
													ng-model="selectedItem.uniqueId" />
											</div>
											<div class="uk-form-row">
												<label for="productName">Product Name</label> <input
													type="text" class="md-input" id="productName"
													name="productName"
													ng-model="selectedItem.description[0].name" />
											</div>
											<div class="uk-form-row">
												<label for="productBrand">Brand</label> <input type="text"
													class="md-input" id="productBrand" name="productBrand"
													ng-model="selectedItem.brand" />
											</div>
										</div>
										<div class="uk-width-large-1-2">
											<div class="uk-form-row">
												<label for="product_edit_description_control">Description</label>
												<textarea class="md-input"
													name="product_edit_description_control"
													id="product_edit_description_control" cols="30" rows="5"
													ng-model="selectedItem.description[0].description"></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="md-card">
								<div class="md-card-toolbar">
									<h3 class="md-card-toolbar-heading-text">Buyable
										Attributes</h3>
								</div>
								<div class="md-card-content">
									<div class="uk-grid" data-uk-grid-margin>
										<div class="uk-width-medium-1-1">
											<div class="uk-overflow-container">
												<table class="uk-table">
													<thead>
														<tr>
															<th class="uk-width-4-10 uk-text-nowrap">Attribute
																Name</th>
															<th class="uk-width-4-10 uk-text-center uk-text-nowrap">Attribute
																Value</th>
														</tr>
													</thead>
													<tbody>
														<tr
															ng-repeat="assignedAttribute in selectedItem.buyableAttributes">
															<td>{{getLocaledValue(assignedAttribute.attribute.description).name}}</td>
															<td class="uk-text-right uk-text-middle"><select
																required class="md-input"
																ng-model="assignedAttribute.attributeValue"
																ng-options="value as value.description[0].name for value in assignedAttribute.attribute.attributeValues track by value.id">
															</select></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
