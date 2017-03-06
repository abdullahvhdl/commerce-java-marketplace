<div id="page_content" ng-controller="AttributesController">
	<div class="uk-modal" id="buyableAttributeModal">
		<div class="uk-modal-dialog">
			<form name="buyableAttributeForm"
				data-ng-submit="saveBuyableAttribute()" novalidate>
				<div class="uk-modal-header"></div>
				<div class="uk-width-medium-1-1">
					<div class="uk-form-row">
						<label for="uniqueid">Unique Identifier</label> <input required
							id="uniqueid" name="uniqueId" type="text" class="md-input"
							data-ng-model="newBuyableAttribute.uniqueId" /> <span
							class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="buyableAttributeForm.uniqueId.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
					<div class="uk-form-row"
						ng-repeat="description in newBuyableAttribute.description">
						<label for="name">Name</label> <input id="name" name="name"
							required data-ng-model="description.name" type="text"
							class="md-input" /> <span class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="buyableAttributeForm.name.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
				</div>
				<div class="uk-modal-footer">
					<div class="uk-width-medium-1-1">
						<button class="md-btn md-btn-danger" type="button"
							ng-click="cancelBuyableAttribute()">Cancel</button>
						<button class="md-btn md-btn-primary" type="submit"
							ng-class="{'disabled':buyableAttributeForm.$invalid}">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="uk-modal" id="displayableAttributeModal">
		<div class="uk-modal-dialog">
			<form name="displayableAttributeForm"
				data-ng-submit="saveDisplayableAttribute()" novalidate>
				<div class="uk-modal-header"></div>
				<div class="uk-width-medium-1-1">
					<div class="uk-form-row">
						<label for="uniqueid">Unique Identifier</label> <input required
							id="uniqueid" name="uniqueId" type="text" class="md-input"
							data-ng-model="newDisplayableAttribute.uniqueId" /> <span
							class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="displayableAttributeForm.uniqueId.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
					<div class="uk-form-row"
						ng-repeat="description in newDisplayableAttribute.description">
						<label for="name">Name</label> <input id="name" name="name"
							required data-ng-model="description.name" type="text"
							class="md-input" /> <span class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="displayableAttributeForm.name.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
				</div>
				<div class="uk-modal-footer">
					<div class="uk-width-medium-1-1">
						<button class="md-btn md-btn-danger" type="button"
							ng-click="cancelDisplayableAttribute()">Cancel</button>
						<button class="md-btn md-btn-primary" type="submit"
							ng-class="{'disabled':displayableAttributeForm.$invalid}">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="page_heading">Manage Attributes</div>
	<div id="page_content_inner">
		<div class="md-card uk-margin-medium-bottom">
			<div class="md-card-toolbar">
				<div class="md-card-toolbar-actions">
					<button class="md-btn md-btn-success"
						ng-click="addNewBuyableAttribute()">Add New</button>
				</div>
				<h3 class="md-card-toolbar-heading-text">Buyable Attributes</h3>
			</div>
			<div class="md-card-content">

				<label for="attribute_filter">Filter</label> <input type="text"
					class="md-input" id="attribute_filter" ng-model="attributeFilter"
					ng-change="d_page=0">
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
								data-ng-repeat="attribute in buyableAttributes | filter:attributeFilter | limitTo:d_limit:d_page*d_limit">
								<td class="uk-text-left">{{getLocaledValue(attribute.description).name}}</td>
								<td class="uk-text-center"><a
									href="editAttribute/{{attribute.id}}"><i
										class="md-icon material-icons">edit</i></a> <a
									data-ng-click="deleteAttribute(attribute.id)"><i
										class="md-icon material-icons">delete</i></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<ul class="uk-pagination uk-margin-medium-top"
					ng-hide="attributeFilter">
					<li data-ng-class="{'uk-disabled':d_page==0}"><a
						data-ng-click="d_setPage(0)"><span><i
								class="uk-icon-angle-double-left"></i></span></a></li>
					<li data-ng-repeat="p in range(0,buyableAttributes.length/d_limit)"
						data-ng-class="{'uk-active':d_page == p}"><a
						data-ng-click="d_setPage(p)"><span>{{p+1}}</span></a></li>
					<li data-ng-class="{'uk-disabled':d_page>=d_totalNumberOfPages-1}"><a
						data-ng-click="d_setPage(d_totalNumberOfPages-1)"><i
							class="uk-icon-angle-double-right"></i></a></li>
				</ul>
			</div>

		</div>

		<div class="md-card uk-margin-medium-bottom">
			<div class="md-card-toolbar">
				<div class="md-card-toolbar-actions">
					<button class="md-btn md-btn-success"
						ng-click="addNewDisplayableAttribute()">Add New</button>
				</div>
				<h3 class="md-card-toolbar-heading-text">Displayable Attributes</h3>
			</div>
			<div class="md-card-content">
				<label for="attribute_filter">Filter</label> <input type="text"
					class="md-input" id="attribute_filter" ng-model="displayableFilter"
					ng-change="b_page=0">
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
								data-ng-repeat="attribute in displayableAttributes | filter:displayableFilter | limitTo:b_limit:b_page*b_limit">
								<td class="uk-text-left">{{getLocaledValue(attribute.description).name}}</td>
								<td class="uk-text-center"><a
									href="editAttribute/{{attribute.id}}"><i
										class="md-icon material-icons">edit</i></a> <a
									data-ng-click="deleteAttribute(attribute.id)"><i
										class="md-icon material-icons">delete</i></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<ul class="uk-pagination uk-margin-medium-top"
					ng-hide="displayableFilter">
					<li data-ng-class="{'uk-disabled':b_page==0}"><a
						data-ng-click="b_setPage(0)"><span><i
								class="uk-icon-angle-double-left"></i></span></a></li>
					<li data-ng-repeat="p in range(0,buyableAttributes.length/b_limit)"
						data-ng-class="{'uk-active':b_page == p}"><a
						data-ng-click="b_setPage(p)"><span>{{p+1}}</span></a></li>
					<li data-ng-class="{'uk-disabled':b_page>=b_totalNumberOfPages-1}"><a
						data-ng-click="b_setPage(b_totalNumberOfPages-1)"><i
							class="uk-icon-angle-double-right"></i></a></li>
				</ul>
			</div>

		</div>
	</div>
</div>
