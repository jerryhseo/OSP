(function(window){
	'use strict';
	
	if( window.DESIGNER ){
		if( DESIGNER.Constants )	return;
	}
	else
		window.DESIGNER = {};
	
	DESIGNER.Constants = {
		SPEC_CF:'center-frequency',
		SPEC_SF:'stop-frequency',
		SPEC_RP:'center-frequency',
		SPEC_SA:'stopband-attenuation',
		SPEC_PFL:'passband-freq-L',
		SPEC_SFL:'stopband-freq-L',
		SPEC_PFH:'passband-freq-H',
		SPEC_SFH:'stopband-freq-H',
		SPEC_PA:'passband-attenuation'
	}
	
})(window);