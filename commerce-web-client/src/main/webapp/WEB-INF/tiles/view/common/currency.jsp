<form action="" method="post" enctype="multipart/form-data">
	<div id="currency">
		<div class="btn-group">
			<button class="dropdown-toggle" type="button" data-hover="dropdown">
				<span class="currency-symbol">{{currency.currentCurrency.symbol}}</span><span
					class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li ng-repeat="currency in currency.allCurrencies"><a>{{currency.symbol}}</a></li>
			</ul>
		</div>
		<input type="hidden" name="code" value="" /> <input type="hidden"
			name="redirect" value="" />
	</div>
</form>
