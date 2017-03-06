<div id="page_content" ng-controller="TasksController">
	<div id="page_heading">
		<h1 id="product_edit_name">Assigned Orders</h1>
	</div>
	<div id="page_content_inner">
		<div class="uk-grid" data-uk-grid-margin>
			<div class="uk-width-1-1">
				<div class="md-card">
					<div class="md-card-toolbar"></div>
					<div class="md-card-content">
						<div class="uk-grid" data-uk-grid-margin>
							<div class="uk-width-1-1">
								<div class="uk-overflow-container">
									<table class="uk-table">
										<thead>
											<tr>
												<th>Id</th>
												<th>Name</th>
												<th>Description</th>
												<th>Priority</th>
												<th>Due Date</th>
												<th>Create Time</th>
												<th>Actions</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="task in tasks">
												<td>{{task.id}}</td>
												<td>{{task.name}}</td>
												<td>{{task.description}}</td>
												<td>{{task.priority}}</td>
												<td>{{task.dueDate}}</td>
												<td>{{task.createTime}}</td>
												<td><a href="orders/task/{{task.id}}"
													class="md-btn md-btn-default">View</a></td>
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
</div>
