<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- main sidebar -->
<aside id="sidebar_main" ng-controller="CatalogSideBarController">
	<div class="uk-modal" id="categoryModal">
		<div class="uk-modal-dialog">
			<form name="editCategoryForm" data-ng-submit="saveCategory()"
				novalidate>
				<div class="uk-modal-header"></div>
				<div class="uk-width-medium-1-1">
					<div class="uk-form-row">
						<label for="uniqueid">Unique Identifier</label> <input required
							id="uniqueid" name="uniqueId" type="text" class="md-input"
							data-ng-model="editCategoryObject.uniqueId" /> <span
							class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="editCategoryForm.uniqueId.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
					<div ng-repeat="description in editCategoryObject.description">
						<fieldset>
							<h3>{{description.language.name}}</h3>
							<div class="uk-form-row">
								<label for="name">Name</label> <input id="name" name="name"
									required data-ng-model="description.name" type="text"
									class="md-input" /> <span class="md-input-bar"></span>
								<div class="parsley-errors-list filled"
									ng-messages="editCategoryForm.name.$error">
									<span class="parsley-required" ng-message="required">This
										value is required.</span>
								</div>
							</div>
							<div class="uk-form-row">
								<label for="name">Description</label>
								<textarea id="description"
									data-ng-model="description.description" data-autosize-on="true"
									rows="5" class="md-input">
													</textarea>
								<span class="md-input-bar"></span>
							</div>
						</fieldset>
					</div>
				</div>
				<div class="uk-modal-footer">
					<div class="uk-width-medium-1-1">
						<button class="md-btn md-btn-danger" type="button"
							ng-click="cancelEditCategory()">Cancel</button>
						<button class="md-btn md-btn-primary" type="submit"
							ng-class="{'disabled':editCategoryForm.$invalid}">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="uk-modal" id="catalogModal">
		<div class="uk-modal-dialog">
			<form name="editCatalogForm" data-ng-submit="saveCatalog()"
				novalidate>
				<div class="uk-modal-header"></div>
				<div class="uk-width-medium-1-1">
					<div class="uk-form-row">
						<label for="uniqueid">Unique Identifier</label> <input required
							id="uniqueid" name="uniqueId" type="text" class="md-input"
							data-ng-model="editCatalogObject.uniqueId" /> <span
							class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="editCatalogForm.uniqueId.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
					<div class="uk-form-row">
						<label for="name">Catalog Name</label> <input required id="name"
							name="name" type="text" class="md-input"
							data-ng-model="editCatalogObject.name" /> <span
							class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="editCatalogForm.name.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
				</div>
				<div class="uk-modal-footer">
					<div class="uk-width-medium-1-1">
						<button class="md-btn md-btn-danger" type="button"
							ng-click="cancelEditCatalog()">Cancel</button>
						<button class="md-btn md-btn-primary" type="submit"
							ng-class="{'disabled':editCatalogForm.$invalid}">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="sidebar_main_header">
		<div class="sidebar_logo">
			<a href='<spring:url value="/"></spring:url>' class="sSidebar_hide"><img
				src="assets/img/logo_main.png" alt="" height="15" width="71" /></a> <a
				href='<spring:url value="/"></spring:url>' class="sSidebar_show"><img
				src="assets/img/logo_main_small.png" alt="" height="32" width="32" /></a>
		</div>
	</div>
	<div id="tree"></div>
	<!-- Tree wrapper -->

</aside>
<!-- main sidebar end -->