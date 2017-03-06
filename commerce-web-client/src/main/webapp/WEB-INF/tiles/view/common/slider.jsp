<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="journal2_slider"
	style="background-attachment: scroll; padding-top: 0px; padding-bottom: 0px"
	ng-controller="SliderController">
	<div style="max-width: 100%; height: 400px;"
		class="tp-banner-container box nav-on-hover journal-fullwidth-slider   ">
		<div class="tp-banner" id="journal-slider"
			style="max-height: 400px; display: none;">
			<ul>
				<li ng-repeat="slide in slides" data-fstransition="fade"
					data-fsslotamount="0" data-fsmasterspeed="0" data-transition="fade"
					data-easing="easeInOutQuart" data-masterspeed="800"><img
					ng-src="{{slide.image}}" alt=""></li>

			</ul>
			<div class="tp-bannertimer tp-bottom"></div>
		</div>
	</div>
</div>