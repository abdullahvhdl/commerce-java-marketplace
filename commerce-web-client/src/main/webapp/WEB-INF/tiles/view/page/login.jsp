<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div ng-controller="LoginController">
	<div class="row">
		<div id="content" class="col-sm-12">
			<div class="row login-content">
				<div class="col-sm-6 left">
					<div class="well">
						<h2 class="secondary-title">New Customer</h2>
						<div class="login-wrap">
							<p>
								<strong>Register Account</strong>
							</p>
							<p>By creating an account you will be able to shop faster, be
								up to date on an order's status, and keep track of the orders
								you have previously made.</p>
						</div>
						<hr>
						<a href='#/register'
							class="btn btn-primary button">Continue</a>
					</div>
				</div>
				<div class="col-sm-6 right">
					<div class="well">
						<h2 class="secondary-title">Returning Customer</h2>
						<form class="form" role="form" ng-submit="login($event)">
							<div class="login-wrap">
								<div class="alert alert-danger warning" ng-show="authenticationError">
									<i class="fa fa-exclamation-circle"></i> <strong>{{authenticationError}}!</strong>
								</div>

								<div class="form-group">
									<label class="control-label" for="input-email">E-Mail
										Address</label> <input type="text" name="email" value=""
										placeholder="E-Mail Address" id="input-email"
										class="form-control" ng-model="email">
								</div>
								<div class="form-group">
									<label class="control-label" for="input-password">Password</label>
									<input type="password" name="password" value=""
										placeholder="Password" id="input-password"
										class="form-control" ng-model="password"> <a
										href='<spring:url value="/reset/request"/>'>Forgotten
										Password</a>
								</div>
							</div>
							<hr>
							<button type="submit" class="btn btn-primary button">Login</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>