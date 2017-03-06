<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<form action="" method="post" enctype="multipart/form-data">
	<div id="language">
		<div class="btn-group">
			<button class="dropdown-toggle" type="button" data-hover="dropdown">
				<img width="16" height="11"
					ng-src='{{language.currentLanguage.flag}}'
					alt="{{language.currentLanguage.name}}"
					title="{{language.currentLanguage.name}}" /><span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li ng-repeat="language in language.allLanguages"><a><img width="16" height="11"
						ng-src='{{language.flag}}'
						alt="{{language.name}}" title="{{language.name}}" />{{language.name}}</a></li>


			</ul>
		</div>
		<input type="hidden" name="code" value="" /> <input type="hidden"
			name="redirect" value="" />
	</div>
</form>
