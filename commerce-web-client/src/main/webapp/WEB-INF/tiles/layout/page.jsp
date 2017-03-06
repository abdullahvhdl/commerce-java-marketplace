<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertAttribute name="header" />
<div id="top-modules">
	<tiles:insertAttribute name="top-module" />
</div>
<div class="exended-container">
	<div id="container" class="container j-container">
		<tiles:insertAttribute name="body" />
	</div>
</div>
<div id="bottom-modules"></div>
<tiles:insertAttribute name="footer" />
<div class="scroll-top"></div>