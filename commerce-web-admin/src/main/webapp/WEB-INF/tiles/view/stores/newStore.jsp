<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="page_content" data-ng-controller="EditStoreController"
	data-ng-init="init()">
	<div id="page_heading">
		<h1 id="product_edit_name">New Store</h1>
	</div>
	<div id="page_content_inner">
		<div class="uk-grid uk-grid-medium" data-uk-grid-margin="">
			<div class="uk-width-xLarge-2-10 uk-width-large-3-10">
				<div class="md-card">
					<div class="md-card-toolbar">
						<h3 class="md-card-toolbar-heading-text">Photos</h3>
					</div>
					<div class="md-card-content">
						<div class="uk-margin-bottom uk-text-center uk-position-relative"
							data-ng-if="store.image.image1">
							<button type="button" data-ng-click="deleteImage1()"
								class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
							<img data-ng-src="{{store.image.image1}}?height=250&width=250"
								alt="" class="img_medium dense-image dense-ready">
						</div>
						<div class="uk-margin-bottom uk-text-center uk-position-relative"
							data-ng-if="!store.image.image1">
							<div class="uk-form-file md-btn md-btn-primary">
								Select <input id="form-file" type="file"
									data-ng-file-select="uploadImage1($files)"
									data-ngf-select="uploadImage1($files)" accept="image/*">
							</div>
						</div>
						<ul class="uk-grid uk-grid-width-small-1-3 uk-text-center"
							data-uk-grid-margin="">
							<li class="uk-position-relative" data-ng-if="store.image.image2">
								<button type="button" data-ng-click="deleteImage2()"
									class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
								<img data-ng-src="{{store.image.image2}}?height=100&width=100"
								alt="" class="img_small dense-image dense-ready">
							</li>
							<li class="uk-position-relative" data-ng-if="!store.image.image2">
								<div class="uk-form-file md-btn md-btn-primary">
									Select <input id="form-file" type="file"
										data-ngf-select="uploadImage2($files)" accept="image/*">
								</div>
							</li>
							<li class="uk-position-relative" data-ng-if="store.image.image3">
								<button type="button" data-ng-click="deleteImage3()"
									class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
								<img data-ng-src="{{store.image.image3}}?height=100&width=100"
								alt="" class="img_small dense-image dense-ready">
							</li>
							<li class="uk-position-relative" data-ng-if="!store.image.image3">
								<div class="uk-form-file md-btn md-btn-primary">
									Select <input id="form-file" type="file"
										data-ngf-select="uploadImage3($files)" accept="image/*">
								</div>
							</li>
							<li class="uk-position-relative" data-ng-if="store.image.image4">
								<button type="button" data-ng-click="deleteImage4()"
									class="uk-modal-close uk-close uk-close-alt uk-position-absolute"></button>
								<img data-ng-src="{{store.image.image4}}?height=100&width=100"
								alt="" class="img_small dense-image dense-ready">
							</li>
							<li class="uk-position-relative" data-ng-if="!store.image.image4">
								<div class="uk-form-file md-btn md-btn-primary">
									Select <input id="form-file" type="file"
										data-ngf-select="uploadImage4($files)" accept="image/*">
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="uk-width-xLarge-8-10  uk-width-large-7-10">
				<div class="md-card uk-margin-medium-bottom">
					<div class="md-card-content">
						<div class="uk-grid" data-uk-grid-margin="">
							<div class="uk-width-medium-2-2">
								<form name="newForm" data-ng-submit="saveStore($event)"
									novalidate>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="uniqueid">Unique Identifier</label> <input
												required id="uniqueid" name="uniqueId" type="text"
												class="md-input" data-ng-model="store.uniqueId" /> <span
												class="md-input-bar"></span>
										</div>
										<div class="parsley-errors-list filled"
											ng-messages="newForm.uniqueId.$error">
											<span class="parsley-required" ng-message="required">This
												value is required.</span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="name">Name</label> <input id="name" name="name"
												required data-ng-model="store.name" type="text"
												class="md-input" /> <span class="md-input-bar"></span>
										</div>
										<div class="parsley-errors-list filled"
											ng-messages="newForm.name.$error">
											<span class="parsley-required" ng-message="required">This
												value is required.</span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="description">Description</label>
											<textarea id="description" data-ng-model="store.description"
												data-autosize-on="true" rows="5" class="md-input">
													</textarea>
											<span class="md-input-bar"></span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="address1">Address 1</label> <input id="address1"
												name="address1" required
												data-ng-model="store.address.address1" type="text"
												class="md-input" /> <span class="md-input-bar"></span>
										</div>
										<div class="parsley-errors-list filled"
											ng-messages="newForm.address1.$error">
											<span class="parsley-required" ng-message="required">This
												value is required.</span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="address2">Address 2</label> <input id="address2"
												name="address2" data-ng-model="store.address2" type="text"
												class="md-input" /> <span class="md-input-bar"></span>
										</div>

									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="city">City</label> <input id="city" name="city"
												required data-ng-model="store.address.city" type="text"
												class="md-input" /> <span class="md-input-bar"></span>
										</div>
										<div class="parsley-errors-list filled"
											ng-messages="newForm.city.$error">
											<span class="parsley-required" ng-message="required">This
												value is required.</span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="state">State</label> <input id="satate"
												name="state" required data-ng-model="store.address.state"
												type="text" class="md-input" /> <span class="md-input-bar"></span>
										</div>
										<div class="parsley-errors-list filled"
											ng-messages="newForm.state.$error">
											<span class="parsley-required" ng-message="required">This
												value is required.</span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="zipcode">Zipcode</label> <input id="zipcode"
												name="zipcode" required
												data-ng-model="store.address.zipCode" type="text"
												class="md-input" /> <span class="md-input-bar"></span>
										</div>
										<div class="parsley-errors-list filled"
											ng-messages="newForm.zipcode.$error">
											<span class="parsley-required" ng-message="required">This
												value is required.</span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="md-input-wrapper">
											<label for="country">Country</label> <input id="country"
												name="country" required
												data-ng-model="store.address.country" type="text"
												class="md-input" /> <span class="md-input-bar"></span>
										</div>
										<div class="parsley-errors-list filled"
											ng-messages="newForm.country.$error">
											<span class="parsley-required" ng-message="required">This
												value is required.</span>
										</div>
									</div>
									<div class="uk-form-row">
										<div class="uk-width-medium-1-1">
											<button class="md-btn md-btn-primary" type="submit" ng-class="{'disabled':newForm.$invalid}">Save</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
