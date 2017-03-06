<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="page_content">
	<div id="page_heading">
		<h1>Upload Stores CSV file</h1>
	</div>
	<div id="page_content_inner">
		<div class="md-card uk-margin-medium-bottom">
			<div class="md-card-content">
				<div class="uk-overflow-container">
					<form action='<spring:url value="/stores/csv"></spring:url>'
						method="post" enctype="multipart/form-data" >
						Select CSV file
						<div class="uk-form-file md-btn md-btn-primary">
							Select <input id="form-file" name="file" type="file">
						</div>
						<div class="uk-form-row">
							<div class="uk-width-medium-1-1">
								<button class="md-btn md-btn-primary" type="submit">Submit</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
