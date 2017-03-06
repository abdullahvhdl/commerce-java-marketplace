"user strict";
angular
		.module("commerceAdmin")
		.controller(
				'CatalogSideBarController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'Stores',
						'Catalog',
						function($scope, $rootScope, $timeout, Stores, Catalog) {

							var categoryModal = UIkit.modal("#categoryModal", {
								bgclose : false
							});
							var catalogModal = UIkit.modal("#catalogModal", {
								bgclose : false
							});
							var CLIPBOARD = null;
							$scope.cancelEditCatalog = function() {
								delete $scope.editCatalogObject;
								delete $scope.event;
								delete $scope.node;
								catalogModal.hide();
							};
							$scope.saveCatalog = function() {
								if (angular
										.isDefined($scope.editCatalogObject.id)) {
									Catalog
											.updateCatalog(
													$scope.selectedStore.id,
													$scope.editCatalogObject)
											.then(
													function(data) {
														$scope.node
																.setTitle(data.name);
														$scope.node.data = data;
														$scope.node.data.type = "catalog";
														delete $scope.node;
													});
								} else {
									Catalog.saveCatalog(
											$scope.selectedStore.id,
											$scope.editCatalogObject).then(
											function(data) {
												var node = {};
												node.key = data.uniqueId;
												node.title = data.name;
												node.data = data;
												node.folder = false;
												node.data.type = "catalog";
												newSibling = $scope.node
														.addChildren(node);
												delete $scope.node;
											});
								}
								delete $scope.editCatalogObject;
								delete $scope.event;
								catalogModal.hide();
							};
							$scope.cancelEditCategory = function() {
								delete $scope.editCategoryObject;
								delete $scope.event;
								delete $scope.node;
								categoryModal.hide();
							};

							function getParentCatalog(node) {
								if (node.data.type === 'catalog') {
									return node.data.id;
								} else {
									return getParentCatalog(node.getParent());
								}
							}
							;
							$scope.saveCategory = function() {
								var catalogId = getParentCatalog($scope.node);
								if (angular
										.isDefined($scope.editCategoryObject.id)) {
									Catalog
											.updateCategory(
													$scope.selectedStore.id,
													catalogId,
													$scope.editCategoryObject)
											.then(
													function(data) {
														$scope.node
																.setTitle($rootScope
																		.getLocaledValue(data.description).name);
														$scope.node.data = data;
														$scope.node.data.type = 'category';
														delete $scope.node;
													});
								} else {
									Catalog
											.saveCategory(
													$scope.selectedStore.id,
													catalogId,
													$scope.editCategoryObject)
											.then(
													function(data) {
														var node = {};
														node.key = data.uniqueId;
														node.title = $rootScope
																.getLocaledValue(data.description).name;
														node.data = data;
														node.folder = false;
														node.data.type = "category";
														newSibling = $scope.node
																.addChildren(node);
													});
								}
								delete $scope.editCategoryObject;
								delete $scope.event;
								categoryModal.hide();
							};
							$scope.handleEvent = function(data) {
								var event = $scope.event;
								var node = $scope.node;
								switch (event) {
								case "moveUp":
									refNode = node.getPrevSibling();
									if (refNode) {
										node.moveTo(refNode, "before");
										node.setActive();
									}
									break;
								case "moveDown":
									refNode = node.getNextSibling();
									if (refNode) {
										node.moveTo(refNode, "after");
										node.setActive();
									}
									break;
								case "indent":
									refNode = node.getPrevSibling();
									if (refNode) {
										node.moveTo(refNode, "child");
										refNode.setExpanded();
										node.setActive();
									}
									break;
								case "outdent":
									if (!node.isTopLevel()) {
										node.moveTo(node.getParent(), "after");
										node.setActive();
									}
									break;
								case "edit":
									if (node.data.type === "catalog") {
										$scope.editCatalogObject = node.data;
										catalogModal.show();
									} else if (node.data.type === "category") {
										$scope.editCategoryObject = node.data;
										categoryModal.show();
									}
									$timeout(function() {
										altair_md.inputs();
									});

									break;
								case "remove":
									refNode = node.getNextSibling()
											|| node.getPrevSibling()
											|| node.getParent();
									node.remove();
									if (refNode) {
										refNode.setActive();
									}
									break;
								case "addCatalog":
									$scope.editCatalogObject = {};
									catalogModal.show();
									break;
								case "addCategory":
									$scope.editCategoryObject = {
										description : [ {
											"language" : {
												"name" : "English",
												"locale" : "en",
											}
										} ]
									};
									if (node.data.type === 'category') {
										$scope.editCategoryObject.parentId = node.data.id;
									}
									categoryModal.show();
									break;
								case "cut":
									CLIPBOARD = {
										mode : data.cmd,
										data : node
									};
									break;
								case "clear":
									CLIPBOARD = null;
									break;
								case "paste":
									$scope.editCategoryObject = CLIPBOARD.data.data;
									$scope.editCategoryObject.parentId = node.data.id;
									$scope.saveCategory();
									CLIPBOARD.data.moveTo(node, "child");
									CLIPBOARD.data.setActive();
									CLIPBOARD = null;
									break;
								default:
									alert("Unhandled command: " + data.cmd);
									return;
								}
							};
							$scope.getCatalogs = function() {
								if (angular.isDefined($scope.selectedStore)) {
									Stores.getCatalogsWithCategories(
											$scope.selectedStore.id).then(
											function(data) {
												$scope.catalogs = data;
												$timeout(function() {
													initTree();
												});
											});
								}

							};
							$rootScope.$watch('selectedStore', function() {
								$scope.selectedStore = angular
										.copy($rootScope.selectedStore);
								if (angular.isDefined($scope.selectedStore)
										&& $scope.selectedStore) {
									$scope.getCatalogs();
								}

							});

							function parseCategoryTree(categories) {
								var treeData = [];
								for ( var index in categories) {
									var category = categories[index];
									var node = {};
									node.key = category.uniqueId;
									node.title = $rootScope
											.getLocaledValue(category.description).name;
									node.children = parseCategoryTree(category.subCategories);
									node.data = category;
									node.data.type = "category";
									node.folder = false;
									treeData.push(node);
								}
								return treeData;
							}

							function initTree() {
								var root = {};
								root.key = $scope.selectedStore.uniqueId;
								root.title = $scope.selectedStore.name;
								root.data = $scope.selectedStore;
								root.folder = true;
								root.data.type = "store";
								root.expanded = true;
								var treeData = [];
								for ( var index in $scope.catalogs) {
									var catalog = $scope.catalogs[index];
									var node = {};
									node.key = catalog.uniqueId;
									node.title = catalog.name;
									node.children = parseCategoryTree(catalog.categories);
									node.data = catalog;
									node.data.type = "catalog";
									node.folder = false;
									treeData.push(node);
								}
								root.children = treeData;
								$timeout(
										function() {
											$("#tree")
													.fancytree(
															{
																// extensions:
																// ['dnd'],
																source : [ root ],
																quicksearch : true,
																dblclick : function(
																		event,
																		data) {
																	var node = data.node;
																	if (node.data.type == 'category') {
																		$rootScope.selectedCategory = node.data;
																		$rootScope
																				.$apply();
																	}
																}
															})
													.on(
															"nodeCommand",
															function(event,
																	data) {
																// Custom event
																// handler that
																// is triggered
																// by
																// keydown-handler
																// and
																// context menu:
																var refNode, moveMode, tree = $(
																		this)
																		.fancytree(
																				"getTree");
																node = tree
																		.getActiveNode();
																$scope
																		.$apply(function() {
																			$scope.event = data.cmd;
																			$scope.node = node;
																			$scope
																					.handleEvent(data);
																		});
															});
											$("#tree")
													.contextmenu(
															{
																delegate : "span.fancytree-node",
																menu : [
																		{
																			title : "Edit",
																			cmd : "edit",
																			uiIcon : "ui-icon-pencil"
																		},
																		{
																			title : "Delete",
																			cmd : "remove",
																			uiIcon : "ui-icon-trash"
																		},
																		{
																			title : "----"
																		},
																		{
																			title : "New Catalog",
																			cmd : "addCatalog",
																			uiIcon : "ui-icon-plus"
																		},
																		{
																			title : "New Category",
																			cmd : "addCategory",
																			uiIcon : "ui-icon-arrowreturn-1-e"
																		},
																		{
																			title : "----"
																		},
																		{
																			title : "Cut",
																			cmd : "cut",
																			uiIcon : "ui-icon-scissors"
																		},
																		{
																			title : "Paste",
																			cmd : "paste",
																			uiIcon : "ui-icon-clipboard",
																			disabled : true
																		} ],
																beforeOpen : function(
																		event,
																		ui) {
																	var node = $.ui.fancytree
																			.getNode(ui.target);
																	$("#tree")
																			.contextmenu(
																					"enableEntry",
																					"addCatalog",
																					node.folder);
																	$("#tree")
																			.contextmenu(
																					"enableEntry",
																					"addCategory",
																					!node.folder);
																	$("#tree")
																			.contextmenu(
																					"enableEntry",
																					"remove",
																					!node.folder);
																	$("#tree")
																			.contextmenu(
																					"enableEntry",
																					"cut",
																					node.data.type === 'category');
																	$("#tree")
																			.contextmenu(
																					"enableEntry",
																					"paste",
																					node.data.type === 'category'
																							&& !!CLIPBOARD);
																	node
																			.setActive();
																},
																select : function(
																		event,
																		ui) {
																	var that = this;
																	setTimeout(
																			function() {
																				$(
																						that)
																						.trigger(
																								"nodeCommand",
																								{
																									cmd : ui.cmd
																								});
																			},
																			100);
																}
															});
										}, 300);
							}

						} ]);