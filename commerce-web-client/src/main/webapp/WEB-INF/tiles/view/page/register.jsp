<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div ng-controller="RegisterController">
	<div id="register-error">
		<div class="alert alert-success warning" ng-show="success">
			<i class="fa fa-exclamation-circle"></i> <strong>Registration
				saved!</strong> Please check your email for confirmation.
		</div>

		<div class="alert alert-danger warning" ng-show="error">
			<i class="fa fa-exclamation-circle"></i> <strong>Registration
				failed!</strong> Please check your input and try again.
		</div>
		<div class="alert alert-danger warning" ng-show="formErrors">
			<i class="fa fa-exclamation-circle"></i> <strong>Please
				check the form. 
		</div>
		<div class="alert alert-danger warning" ng-show="errorEmailExists">
			<i class="fa fa-exclamation-circle"></i> <strong>E-mail is
				already in use!</strong> Please choose another one.
		</div>
		<div class="alert alert-danger warning" ng-show="doNotMatch">
			<i class="fa fa-exclamation-circle"></i> The password and its
			confirmation do not match!
		</div>
	</div>
	<div class="row">
		<div id="content" class="col-sm-12">
			<h1 class="heading-title">Register Account</h1>

			<div class="content">
				<form ng-show="!success" name="register" role="form" novalidate
					ng-submit="registerSubmit()" >
					<p class="account-text">
						If you already have an account with us, please login at the <a
							href='#/login'>login page</a>.
					</p>
					<fieldset id="account">
						<h2 class="secondary-title">Your Personal Details</h2>
						<div class="form-group required" ng-class="{ 'has-error': (register.email.$touched || register.$submitted) && register.email.$invalid }">
							<label class="col-sm-2 control-label" for="input-email">E-Mail</label>
							<div class="col-sm-10">
								<input type="email" name="email" value="" placeholder="E-Mail"
									id="input-email" class="form-control"
									ng-model="registerAccount.email" required>
								<div class="text-danger" ng-messages="register.email.$error"
									ng-if="(register.email.$touched || register.$submitted)&& register.email.$invalid ">
									<div ng-message="required">Email Address is Required!</div>
									<div ng-message="email">Email Address is not Valid!</div>
								</div>
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.firstname.$touched || register.$submitted) && register.firstname.$invalid }">
							<label class="col-sm-2 control-label" for="input-firstname">First
								Name</label>
							<div class="col-sm-10">
								<input type="text" name="firstname" value=""
									placeholder="First Name" id="input-firstname"
									class="form-control" ng-model="registerAccount.firstName"
									required>
								<div class="text-danger" ng-messages="register.firstname.$error"
									ng-if="(register.firstname.$touched || register.$submitted) && register.firstname.$invalid ">
									<div ng-message="required">First Name is Required!</div>
								</div>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label" for="input-middlename">Middle
								Name</label>
							<div class="col-sm-10">
								<input type="text" name="middlename" value=""
									placeholder="Middle Name" id="input-middlename"
									class="form-control" ng-model="registerAccount.middleName">
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.lastname.$touched || register.$submitted) && register.lastname.$invalid }">
							<label class="col-sm-2 control-label" for="input-lastname">Last
								Name</label>
							<div class="col-sm-10">
								<input type="text" name="lastname" value=""
									placeholder="Last Name" id="input-lastname"
									class="form-control" ng-model="registerAccount.lastName"
									required>
								<div class="text-danger" ng-messages="register.lastname.$error"
									ng-if="(register.lastname.$touched || register.$submitted) && register.lastname.$invalid ">
									<div ng-message="required">Last Name is Required!</div>
								</div>
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.telephone.$touched || register.$submitted) && register.telephone.$invalid }">
							<label class="col-sm-2 control-label" for="input-phone">Phone
								Number</label>
							<div class="col-sm-10">
								<input type="tel" name="telephone" value=""
									placeholder="Phone Number" id="input-phone"
									class="form-control" ng-model="registerAccount.phoneNumber"
									required>
								<div class="text-danger" ng-messages="register.telephone.$error"
									ng-if="(register.telephone.$touched || register.$submitted)&& register.telephone.$invalid ">
									<div ng-message="required">Phone Number is Required!</div>
								</div>
							</div>
						</div>
					</fieldset>
					<br>
					<fieldset id="address" >
						<h2 class="secondary-title">Your Address</h2>
						<div class="form-group required" ng-class="{ 'has-error': (register.address_1.$touched || register.$submitted) && register.address_1.$invalid }">
							<label class="col-sm-2 control-label" for="input-address-1">Address
								1</label>
							<div class="col-sm-10">
								<input type="text" name="address_1" value=""
									placeholder="Address 1" id="input-address-1"
									class="form-control" ng-model="registerAccount.address1"
									required>
								<div class="text-danger" ng-messages="register.address_1.$error"
									ng-if="(register.address_1.$touched || register.$submitted)&& register.address_1.$invalid ">
									<div ng-message="required">Address is Required!</div>
								</div>

							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="input-address-2">Address
								2</label>
							<div class="col-sm-10">
								<input type="text" name="address_2" value=""
									placeholder="Address 2" id="input-address-2"
									class="form-control">
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.city.$touched || register.$submitted) && register.city.$invalid }">
							<label class="col-sm-2 control-label" for="input-city">City</label>
							<div class="col-sm-10">
								<input type="text" name="city" value="" placeholder="City"
									id="input-city" class="form-control"
									ng-model="registerAccount.city" required>
								<div class="text-danger" ng-messages="register.city.$error"
									ng-if="(register.city.$touched || register.$submitted)&& register.city.$invalid ">
									<div ng-message="required">City is Required!</div>
								</div>
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.zipcode.$touched || register.$submitted) && register.zipcode.$invalid }">
							<label class="col-sm-2 control-label" for="input-zipcode">Zip
								Code</label>
							<div class="col-sm-10">
								<input type="text" name="zipcode" value=""
									placeholder="Zip Code" id="input-zipcode" class="form-control"
									ng-model="registerAccount.zipCode" required>
								<div class="text-danger" ng-messages="register.zipcode.$error"
									ng-if="(register.zipcode.$touched || register.$submitted)&& register.zipcode.$invalid ">
									<div ng-message="required">Zipcode is Required!</div>
								</div>
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.state.$touched || register.$submitted) && register.state.$invalid }">
							<label class="col-sm-2 control-label" for="input-state">State</label>
							<div class="col-sm-10">
								<input type="text" name="state" value="" placeholder="State"
									id="input-state" class="form-control"
									ng-model="registerAccount.state" required>
								<div class="text-danger" ng-messages="register.state.$error"
									ng-if="(register.state.$touched || register.$submitted)&& register.state.$invalid ">
									<div ng-message="required">State is Required!</div>
								</div>
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.country.$touched || register.$submitted) && register.country.$invalid }">
							<label class="col-sm-2 control-label" for="input-country">Country</label>
							<div class="col-sm-10">
								<input type="text" name="country" value="" placeholder="Country"
									id="input-country" class="form-control"
									ng-model="registerAccount.country" required>
								<div class="text-danger" ng-messages="register.country.$error"
									ng-if="(register.country.$touched || register.$submitted)&& register.country.$invalid ">
									<div ng-message="required">Country is Required!</div>
								</div>
							</div>
						</div>
					</fieldset>
					<br>
					<fieldset>
						<h2 class="secondary-title">Your Password</h2>
						<div class="form-group required" ng-class="{ 'has-error': (register.password.$touched || register.$submitted) && register.password.$invalid }">
							<label class="col-sm-2 control-label" for="input-password">Password</label>
							<div class="col-sm-10">
								<input type="password" name="password" value=""
									placeholder="Password" id="input-password" class="form-control"
									ng-model="registerAccount.password" required>
								<div class="text-danger" ng-messages="register.password.$error"
									ng-if="(register.password.$touched || register.$submitted)&& register.password.$invalid ">
									<div ng-message="required">Password is Required!</div>
								</div>
							</div>
						</div>
						<div class="form-group required" ng-class="{ 'has-error': (register.confirm.$touched || register.$submitted) && register.confirm.$invalid }">
							<label class="col-sm-2 control-label" for="input-confirm">Password
								Confirm</label>
							<div class="col-sm-10">
								<input type="password" name="confirm" value=""
									placeholder="Password Confirm" id="input-confirm"
									class="form-control" ng-model="confirmPassword" required compare-to="register.password">
								<div class="text-danger" ng-messages="register.confirm.$error"
									ng-if="(register.confirm.$touched || register.$submitted)&& register.confirm.$invalid ">
									<div ng-message="required">Password Confirm is Required!</div>
									<div ng-message="compareTo">Passwords Do not Match!</div>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="buttons">
						<div class="pull-right">
							<input type="submit" value="Register"
								class="btn btn-primary button">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

