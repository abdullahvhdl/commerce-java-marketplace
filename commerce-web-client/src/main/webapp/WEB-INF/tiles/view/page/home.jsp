<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:putAttribute name="top-module" value="PageTitle" />
<div class="row">
	<div id="content" class="col-sm-12">
		<jsp:include page="../modules/carousel.jsp" >
			<jsp:param value="topRated" name="identifier"/>
			<jsp:param value="Top Rated" name="name"/>
			<jsp:param value="502,503,504,505,510,511" name="produtIds"/>
		</jsp:include>
		
		<jsp:include page="../modules/carousel.jsp" >
			<jsp:param value="bestSeller" name="identifier"/>
			<jsp:param value="Best Seller" name="name"/>
			<jsp:param value="504,505,506,507,508,509,510,511,512,513" name="produtIds"/>
		</jsp:include>
	</div>

</div>