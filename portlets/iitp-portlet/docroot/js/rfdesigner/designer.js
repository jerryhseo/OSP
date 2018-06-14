(function(window){
	'use strict';
	
	if( window.DESIGNER ){
		if( DESIGNER.Constants )	return;
	}
	else
		window.DESIGNER = {};
	
	DESIGNER.Constants = {
		SPEC_CF:'center-frequency',
		SPEC_CF_ADD:'center-frequency-addon',
		SPEC_SF:'stop-frequency',
		SPEC_SF_ADD:'stop-frequency-addon',
		SPEC_PFL:'passband-freq-L',
		SPEC_PFL_ADD:'passband-freq-L-addon',
		SPEC_SFL:'stopband-freq-L',
		SPEC_SFL_ADD:'stopband-freq-L-addon',
		SPEC_PFH:'passband-freq-H',
		SPEC_PFH_ADD:'passband-freq-H-addon',
		SPEC_SFH:'stopband-freq-H',
		SPEC_SFH_ADD:'stopband-freq-H-addon',
		SPEC_SA:'stopband-attenuation',
		SPEC_PR:'passband-ripple',
		SPEC_PA:'passband-attenuation'
	}
	
})(window);