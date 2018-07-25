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
		SPEC_PA:'passband-attenuation',
		getNullToZero : function(value,digit){
			var num_check=/^([0-9]*)[\.]?([0-9])?$/;
			
			if(value == null||typeof value =="undefined"){
				return 0;
			}else{
				if(isNaN(value)){
					return "NaN";
				}else{
					if(arguments.length>1&&!num_check.test(value)){
						return value.toFixed(digit);
					}else{
						return value;
					}
				}
			}
		}
	}
	
	
})(window);