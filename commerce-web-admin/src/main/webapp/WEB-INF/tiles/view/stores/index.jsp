<div id="page_content" data-ng-controller="StoresController">
	<div id="page_heading">
		<h1>Manage Stores</h1>
	</div>
	<div id="page_content_inner">
		<div class="md-card uk-margin-medium-bottom">
			<div class="md-card-content">
				<label for="product_search_name">Filter (name, description)</label> <input type="text" class="md-input"
					id="product_search_name" ng-model="nameFilter" ng-change="page=0">
				<div class="uk-overflow-container">
					<table class="uk-table uk-table-nowrap">
						<thead>
							<tr>
								<th class="uk-width-1-10 uk-text-left">Image</th>
								<th class="uk-width-2-10 uk-text-left">Name</th>
								<th class="uk-width-2-10 uk-text-left">Description</th>
								<th class="uk-width-2-10 uk-text-center">Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr data-ng-repeat="store in stores | filter:nameFilter | limitTo:limit:page*limit">
								<td class="uk-text-center"><img
									data-ng-if="store.image.image1"
									class="md-user-image dense-image dense-ready"
									data-ng-src="{{store.image.image1}}?height=35&width=35" alt="">
									<img data-ng-if="!store.image.image1"
									class="md-user-image dense-image dense-ready"
									src="assets/img/avatars/avatar_01_tn@2x.png" alt=""></td>
								<td class="uk-text-left">{{store.name}}</td>
								<td class="uk-text-left">{{store.description |
									cut:true:80:'...'}}</td>
								<td class="uk-text-center"><a href="editStore/{{store.id}}"><i
										class="md-icon material-icons">edit</i></a> <a
									data-ng-click="deleteStore(store.id)"><i
										class="md-icon material-icons">delete</i></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<ul class="uk-pagination uk-margin-medium-top" ng-hide="nameFilter"
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
<div class="md-fab-wrapper">
	<a class="md-fab md-fab-primary" href="newStore"> <i
		class="material-icons">add_circle_outline</i>
	</a>
</div>