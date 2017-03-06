<div id="page_content" ng-controller="TaskController"
	ng-init="setTaskId(${taskId })">
	<div id="page_heading">
		<h1 id="product_edit_name">Process Order</h1>
	</div>
	<div id="page_content_inner">
		<div class="uk-grid" data-uk-grid-margin>
			<div class="uk-width-1-1">
				<div class="md-card">
					<div class="md-card-toolbar"></div>
					<div class="md-card-content">
						<h3>{{task.name}}</h3>
						<div ng-bind-html="task.description"></div>
						<button class="md-btn md-btn-primary" ng-click="claim(task)"
							ng-if="!task.assignee">Claim</button>
						<form name="taskForm" class="form-horizontal"
							ng-submit="submitTask()" ng-if="task.assignee">
							<div ng-if="taskForm.$invalid" class="uk-alert uk-alert-danger" data-uk-alert="">
                                Form is Not Complete.
                            </div>
							<fieldset>
								<legend>Complete Form</legend>
								<div class="uk-form-row" ng-repeat="form in task.formProperties">
									<label for="{{form.id}}">{{form.name}} <span ng-if="form.required" class="req">*</span></label> <input
										ng-if="form.type != 'enum'" ng-required="form.required" id="uniqueid"
										 type="text" class="md-input"
										data-ng-model="form.value" /><select ng-required="form.required" class="md-input"
										ng-if="form.type == 'enum'" id="{{form.id}}"
										ng-model="form.value">
										<option ng-repeat="(key, value) in form.enumValues"
											value="{{key}}">{{value}}</option>
									</select> <span class="md-input-bar"></span>
								</div>
								<div class="form-group">
									<div class="col-lg-10 col-lg-offset-2">
										<button type="reset" class="md-btn md-btn-default"
											ng-click="cancel()">Cancel</button>
										<button type="submit" class="md-btn md-btn-primary" ng-class="{'disabled':taskForm.$invalid}">Submit</button>
									</div>
								</div>
							</fieldset>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
