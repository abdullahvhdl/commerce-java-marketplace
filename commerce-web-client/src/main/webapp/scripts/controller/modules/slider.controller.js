'use strict';
angular.module('commerceApp').controller('SliderController',
		function($scope, $timeout) {
			$scope.slides = [ {
				image : "image/fullwidth/ny.jpg?width=1920&height=550"
			}, {
				image : "image/slider/paris.jpg?width=1920&height=550"
			} ];

			$timeout(function() {
				var opts = {
					delay : 3500,
					onHoverStop : "on",
					thumbWidth : 100,
					thumbHeight : 75,
					thumbAmount : 2,
					hideThumbs : 1,
					navigationType : "bullet",
					navigationArrows : "solo",
					navigationStyle : "round",
					navigationHAlign : "center",
					navigationVAlign : "bottom",
					navigationHOffset : "",
					navigationVOffset : 20,
					arrowsHOffset : 33,
					startwidth : 1220,
					startheight : 400
				};
				opts.hideThumbs = 0;
				$('#journal-slider').show().revolution(opts);
			}, 200);

		});
