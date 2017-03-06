<div id="page_content" ng-controller="EditAttributeController"
	ng-init="initAttribute(${attributeId })">
	<div class="uk-modal" id="attriubuteValueModal">
		<div class="uk-modal-dialog">
			<form name="attriubuteValueForm"
				data-ng-submit="saveAttributeValue()" novalidate>
				<div class="uk-modal-header"></div>
				<div class="uk-width-medium-1-1">
					<div class="uk-form-row">
						<label for="uniqueid">Unique Identifier</label> <input required
							id="uniqueid" name="uniqueId" type="text" class="md-input"
							data-ng-model="selectedValue.uniqueId" /> <span
							class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="attriubuteValueForm.uniqueId.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
					<div class="uk-form-row"
						ng-repeat="description in selectedValue.description">
						<label for="name">Name</label> <input id="name" name="name"
							required data-ng-model="description.name" type="text"
							class="md-input" /> <span class="md-input-bar"></span>
						<div class="parsley-errors-list filled"
							ng-messages="attriubuteValueForm.name.$error">
							<span class="parsley-required" ng-message="required">This
								value is required.</span>
						</div>
					</div>
				</div>
				<div class="uk-modal-footer">
					<div class="uk-width-medium-1-1">
						<button class="md-btn md-btn-danger" type="button"
							ng-click="cancelAttributeValue()">Cancel</button>
						<button class="md-btn md-btn-primary" type="submit"
							ng-class="{'disabled':attriubuteValueForm.$invalid}">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="page_heading">Edit Attribute</div>

	<div id="page_content_inner">

		<div class="md-card uk-margin-medium-bottom">
			<div class="md-card-toolbar">
				<h3 class="md-card-toolbar-heading-text">Attribute Description</h3>
			</div>
			<div class="md-card-content">
				<form name="editAttributeForm" novalidate
					ng-submit="saveAttribute()">
					<div class="uk-width-medium-1-1">
						<div class="uk-form-row">
							<label for="uniqueid">Unique Identifier</label> <input required
								id="uniqueid" name="uniqueId" type="text" class="md-input"
								data-ng-model="attribute.uniqueId" /> <span
								class="md-input-bar"></span>
							<div class="parsley-errors-list filled"
								ng-messages="editAttributeForm.uniqueId.$error">
								<span class="parsley-required" ng-message="required">This
									value is required.</span>
							</div>
						</div>
						<div class="uk-form-row"
							ng-repeat="description in attribute.description">
							<label for="name">Name</label> <input id="name" name="name"
								required data-ng-model="description.name" type="text"
								class="md-input" /> <span class="md-input-bar"></span>
							<div class="parsley-errors-list filled"
								ng-messages="editAttributeForm.name.$error">
								<span class="parsley-required" ng-message="required">This
									value is required.</span>
							</div>
						</div>
						<div class="uk-form-row">
							<a class="md-btn md-btn-danger" type="button" href="attributes">Cancel</a>
							<button class="md-btn md-btn-primary" type="submit"
								ng-class="{'disabled':editAttributeForm.$invalid}">Save</button>
						</div>
					</div>

				</form>

			</div>
		</div>
		<div class="md-card uk-margin-medium-bottom">
			<div class="md-card-toolbar">
				<div class="md-card-toolbar-actions">
					<button class="md-btn md-btn-success" ng-click="addNewValue()">Add
						Value</button>
				</div>
				<h3 class="md-card-toolbar-heading-text">Attribute Values</h3>
			</div>
			<div class="md-card-content">

				<label for="attribute_filter">Filter</label> <input type="text"
					class="md-input" id="attribute_filter" ng-model="valueFilter"
					ng-change="page=0">
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
								data-ng-repeat="value in attribute.attributeValues | filter:valueFilter | limitTo:limit:page*limit">
								<td class="uk-text-left">{{getLocaledValue(value.description).name}}</td>
								<td class="uk-text-center"><a ng-click="editAttributeValue(value)"><i
										class="md-icon material-icons">edit</i></a> <a
									data-ng-click="deleteValue(value)"><i
										class="md-icon material-icons">delete</i></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<ul class="uk-pagination uk-margin-medium-top" ng-hide="valueFilter">
					<li data-ng-class="{'uk-disabled':page==0}"><a
						data-ng-click="setPage(0)"><span><i
								class="uk-icon-angle-double-left"></i></span></a></li>
					<li
						data-ng-repeat="p in range(0,attribute.attributeValues.length/limit)"
						data-ng-class="{'uk-active':page == p}"><a
						data-ng-click="setPage(p)"><span>{{p+1}}</span></a></li>
					<li data-ng-class="{'uk-disabled':page>=totalNumberOfPages-1}"><a
						data-ng-click="setPage(totalNumberOfPages-1)"><i
							class="uk-icon-angle-double-right"></i></a></li>
				</ul>
			</div>

		</div>

	</div>
</div>