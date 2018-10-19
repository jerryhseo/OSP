(function(window){
	'use strict';
	
	if( window.DESIGNER ){
		if( DESIGNER.Constants )	return;
	}else{
		window.DESIGNER = {};
	}
	
	DESIGNER.Constants = {
		DesignerKey:{
			TX_MIXER		:'TX-MIXER',	
			TX_BLOCK_1      :'TX-BLOCK-1',
			TX_BLOCK_2      :'TX-BLOCK-2',
			TX_BLOCK_3      :'TX-BLOCK-3',
			TX_BLOCK_4      :'TX-BLOCK-4',
			TX_BLOCK_5      :'TX-BLOCK-5',
			CHANNEL_PL      :'CHANNEL-PL',
			CHANNEL_AWGN    :'CHANNEL-AWGN',
			RX_BLOCK_1      :'RX-BLOCK-1',
			RX_BLOCK_2      :'RX-BLOCK-2',
			RX_BLOCK_3      :'RX-BLOCK-3',
			RX_MIXER        :'RX-MIXER',
			RX_LPF          :'RX-LPF',
			RX_BBA          :'RX-BBA'
		},
		getNullToZero : function(value){
			var num_check=/^([0-9]*)[\.]?([0-9])?$/;
			
			if(value === null||typeof value =="undefined"||value === ''){
				return 0;
			}else{
				if(isNaN(value)){
					return 0;
				}else{
					return value;
				}
			}
		}
	}
	
	
})(window);