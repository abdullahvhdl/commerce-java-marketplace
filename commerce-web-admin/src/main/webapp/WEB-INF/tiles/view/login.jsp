<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="login_page_wrapper">
	<div class="md-card" id="login_card">
		<div class="md-card-content large-padding" id="login_form">
			<c:if test="${param.error != null}">
				<div class="uk-alert uk-alert-danger" data-uk-alert="">
					<a href="#" class="uk-alert-close uk-close"></a>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				</div>
			</c:if>
			<div class="login_heading">
				<div class="user_avatar"></div>
			</div>
			<form action='<spring:url value="/login"></spring:url>' method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="uk-form-row">
					<label for="login_email">Email address</label> <input
						class="md-input" type="text" id="login_email" name="username" />
				</div>
				<div class="uk-form-row">
					<label for="login_password">Password</label> <input
						class="md-input" type="password" id="login_password"
						name="password" />
				</div>
				<div class="uk-margin-medium-top">
					<button class="md-btn md-btn-primary md-btn-block md-btn-large"
						type="submit">Sign In</button>
				</div>

			</form>
		</div>
	</div>
</div>
