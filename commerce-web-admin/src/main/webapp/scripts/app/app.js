'use strict';

angular.module('commerceAdmin',
		[ 'ngResource','ngMessages', 'ngSanitize', 'LocalStorageModule' ,'ngFileUpload','ui.tree'])

.run(function($rootScope) {

	$rootScope.range = function(min, max, step) {
		step = step || 1;
		var input = [];
		for (var i = min; i < max; i += step)
			input.push(i);
		return input;
	};
	$rootScope.language="en";
	$rootScope.currency="USD";
	$rootScope.getLocaledValue=function(list){
		for(var index in list){
			var value=list[index];
			if(value.language.locale==$rootScope.language){
				return value;
			}
		}
	};
	$rootScope.getPrice=function(prices){
		for(var index in prices){
			var price=prices[index];
			if(price.currency==$rootScope.currency){
				return price;
			}
		}
	};

}).config(function(localStorageServiceProvider) {
	localStorageServiceProvider.setStorageType('sessionStorage');

}).filter('cut', function() {
	return function(value, wordwise, max, tail) {
		if (!value)
			return '';

		max = parseInt(max, 10);
		if (!max)
			return value;
		if (value.length <= max)
			return value;

		value = value.substr(0, max);
		if (wordwise) {
			var lastspace = value.lastIndexOf(' ');
			if (lastspace != -1) {
				value = value.substr(0, lastspace);
			}
		}

		return value + (tail || ' …');
	};
});

angular.module('commerceAdmin')

.directive('contextMenu', ["$parse", function ($parse) {
    var renderContextMenu = function ($scope, event, options, model) {
        if (!$) { var $ = angular.element; }
        $(event.currentTarget).addClass('context');
        var $contextMenu = $('<div>');
        var $dropDown = $('<div>');
        $contextMenu.addClass('dropdown clearfix');
        $dropDown.addClass('uk-dropdown uk-dropdown-active uk-dropdown-shown');
        
        var $ul = $('<ul>');
        $ul.addClass('uk-nav uk-nav-dropdown');
        $ul.attr({ 'role': 'menu' });
        $dropDown.css({
            display: 'block',
            position: 'absolute',
            left: event.pageX + 'px',
            top: event.pageY + 'px'
        });
        angular.forEach(options, function (item, i) {
            var $li = $('<li>');
            if (item === null) {
                $li.addClass('divider');
            } else {
                var $a = $('<a>');
                $a.attr({ tabindex: '-1', href: '#' });
                var text = typeof item[0] == 'string' ? item[0] : item[0].call($scope, $scope, event);
                $a.text(text);
                $li.append($a);
                var enabled = angular.isDefined(item[2]) ? item[2].call($scope, $scope, event, text, model) : true;
                if (enabled) {
                    $li.on('click', function ($event) {
                        $event.preventDefault();
                        $scope.$apply(function () {
                            $(event.currentTarget).removeClass('context');
                            $contextMenu.remove();
                            item[1].call($scope, $scope, event, model);
                        });
                    });
                } else {
                    $li.on('click', function ($event) {
                        $event.preventDefault();
                    });
                    $li.addClass('disabled');
                }
            }
            $ul.append($li);
        });
        $contextMenu.append($dropDown);
        $dropDown.append($ul);
        var height = Math.max(
            document.body.scrollHeight, document.documentElement.scrollHeight,
            document.body.offsetHeight, document.documentElement.offsetHeight,
            document.body.clientHeight, document.documentElement.clientHeight
        );
        $contextMenu.css({
            width: '100%',
            height: height + 'px',
            position: 'absolute',
            top: 0,
            left: 0,
            zIndex: 9999
        });
        $(document).find('body').append($contextMenu);
        $contextMenu.on("mousedown", function (e) {
            if ($(e.target).hasClass('dropdown')) {
                $(event.currentTarget).removeClass('context');
                $contextMenu.remove();
            }
        }).on('contextmenu', function (event) {
            $(event.currentTarget).removeClass('context');
            event.preventDefault();
            $contextMenu.remove();
        });
    };
    return function ($scope, element, attrs) {
        element.on('contextmenu', function (event) {
            $scope.$apply(function () {
                event.preventDefault();
                var options = $scope.$eval(attrs.contextMenu);
                var model = $scope.$eval(attrs.model);
                if (options instanceof Array) {
                    if (options.length === 0) { return; }
                    renderContextMenu($scope, event, options, model);
                } else {
                    throw '"' + attrs.contextMenu + '" not an array';
                }
            });
        });
    };
}]);

