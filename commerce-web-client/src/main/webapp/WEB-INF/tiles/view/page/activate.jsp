<div ng-controller="ActivationController">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h1 >E-mail validation</h1>

            <div class="alert alert-success" ng-show="success">
                <strong>Your username has been validated.</strong> Please authenticate.
            </div>

            <div class="alert alert-danger" ng-show="error" >
                <strong>Your username could not be validated.</strong> Please use the registration form to register your username.
            </div>

        </div>
    </div>
</div>
