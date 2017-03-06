'use strict';
angular.module('commerceApp').controller(
		'ProductCarouselController',
		function($scope,$timeout,Product) {
			$scope.Math = window.Math;
			$scope.init=function(identifier,productIds){
				$scope.identifier=identifier;
				$scope.productIds=productIds.split(",");
				Product.getProductsByIds($scope.productIds).then(function(result) {
					
					$scope.products = result.data;
					$timeout(function() {
						var opts = $
								.parseJSON('[[0,1],[470,3],[760,4],[980,4],[1100,5]]');
						jQuery("#carousel-"+$scope.identifier+" .owl-carousel").owlCarousel({
							lazyLoad : true,
							itemsCustom : opts,
							autoPlay : 3000,
							touchDrag : true,
							stopOnHover : true,
							items : 15,
							navigation : true,
							scrollPerPage : true,
							navigationText : false,
							paginationSpeed : 400,
							margin : 20
						});
						$('#carousel-'+$scope.identifier+' .htabs a.atab').tabs();
						$('#carousel-'+$scope.identifier+' .htabs a.atab').click(function() {
							var current = $(this).attr('href');
							$('#carousel-'+$scope.identifier+' .htabs a.atab').each(function() {
								var href = $(this).attr('href');
								if (current === href) {
									jQuery(href).data('owlCarousel').play();
								} else {
									jQuery(href).data('owlCarousel').stop();
								}
							});
						});
						Journal.equalHeight(
								$('#carousel-'+$scope.identifier+' .product-grid-item'), '.name');
						Journal.equalHeight(
								$('#carousel-'+$scope.identifier+' .product-grid-item'),
								'.description');
						var default_section = '0';
						if (default_section) {
							$(
									'#carousel-'+$scope.identifier+' .htabs a.atab[href="#carousel-'+$scope.identifier+'-'
											+ default_section + '"]').click();
						} else {
							$('#carousel-'+$scope.identifier+' .htabs a.atab').first().click();
						}
					},300);
				});
			};
			
			
		});
