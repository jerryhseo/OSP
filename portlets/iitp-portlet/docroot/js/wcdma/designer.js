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
		},
		getStringToZero : function(value){
			if(value === null||typeof value =="undefined"||value === ''){
				return 0;
			}else{
				return value;
			}
		},
		randbit : function(t){
			var RBS0 = new Array(t);

			for (var i = 0; i < t; i++) {
			    RBS0[i] = (math.floor(math.random() * 2));
			}
			return RBS0;
		},
		stop : function(RBS1,t){
			var RBS2 = [new Array(t / 2), new Array(t / 2)];

			for (var i = 0; i < t; i++) {
				var ii = i % 2;
				var jj = math.floor(i / 2);
				RBS2[ii][jj] = RBS1[i] / math.sqrt(2);
            }
			return RBS2;
		},
		stretch : function(RBS2, SF){
			var len = RBS2[0].length;
			var RBS3 = [new Array(len * SF), new Array(len * SF)];

			for (var i = 0; i < len * SF; i++) {
				RBS3[0][i] = RBS2[0][math.floor(i / SF)];
				RBS3[1][i] = RBS2[1][math.floor(i / SF)];
			}
			return RBS3;
		},
		spreading : function(RBS3, SF){
			 var len = RBS3[0].length;
			var w = [[1, 1], [1, -1]];
			var w1 = [[1]];
			var n = Math.log2(SF);
			var RBS4 = [new Array(len), new Array(len)];
			
			for (var i = 0; i < n; i++) {
			    w1 = this.expand(w1, w);
			}
			
			var wn = new Array(2);
			var rand = Math.floor(Math.random() * SF);
			wn[0] = w1[rand];
			var rand = Math.floor(Math.random() * SF);
			wn[1] = w1[rand];
			
			for (var i = 0; i < 2; i++) {
			    for (var j = 0; j < RBS3[0].length; j++) {
			        RBS4[i][j] = RBS3[i][j] * wn[i][j % SF];
			    }
			}
			
			return [RBS4,wn];
		},
		expand : function(w1, w){
			var w2;
			w2 = new Array(w1.length * 2);
			
			for (var i = 0; i < w1.length * 2; i++) {
			    w2[i] = new Array(w1.length * 2);
			}
			
			for (var i = 0; i < 2; i++) {
			    for (var j = 0; j < 2; j++) {
			        for (var ii = 0; ii < w1.length; ii++) {
			            for (var jj = 0; jj < w1.length; jj++) {
			                w2[i * w1.length + ii][j * w1.length + jj] = w[i][j] * w1[ii][jj];
			            }
			        }
			    }
			}
			return w2;
		},
		zeroinsert : function(RBS4, zero){
			var RBS5 = [new Array(RBS4[0].length * zero), new Array(RBS4[0].length * zero)];
			RBS5[0].fill(0);
			RBS5[1].fill(0);
			
			for (var i = 0; i < 2; i++) {
			    for (var j = 0; j < RBS4[0].length; j++) {
			        RBS5[i][j * zero] = RBS4[i][j];
			    }
			}
			return RBS5;
		},
		pulsefilter : function(RBS5, ft, rolloff, N, zero, B){
			var t = new Array(N);
			var h1 = new Array(N);
			var h2 = new Array(N);
			var RC = new Array(N);
			var GA = new Array(N);
			var a = 1 / (B * 1000000) * 3840000 * math.sqrt(math.log(2) / 2);
			var RBStempI = new Array(RBS5[0].length + N - 1);;
			var RBStempQ = new Array(RBS5[0].length + N - 1);;
			var RBS6 = [new Array(RBS5[0].length), new Array(RBS5[0].length)];
			
			for (var i = 1; i < N; i++) {
			    t[0] = -1 * (N - 1) / zero / 2;
			    t[i] = t[i - 1] + 1 / zero;
			}
			
			for (var i = 0; i < N; i++) {
			    h1[i] = math.sin(math.PI * t[i]) / (math.PI * t[i]);
			    h2[i] = math.cos(math.PI * rolloff * t[i]) / (1 - 4 * math.pow(rolloff, 2) * math.pow(t[i], 2));
			}
			h1[(N - 1) / 2] = 1;
			
			for (var i = 0; i < N; i++) {
			    RC[i] = h1[i] * h2[i];
			    GA[i] = math.sqrt(math.PI) / a * math.exp(-1 * math.pow(math.PI, 2) * math.pow(t[i], 2) / (math.pow(a, 2)));
			}
			var sumRC = RC.reduce(function(accumulator, currentValue) {
			    return accumulator + currentValue;
			}, 0);
			
			var sumGA = GA.reduce(function(accumulator, currentValue) {
			    return accumulator + currentValue;
			}, 0);
			RC = RC.map(RC => RC / sumRC);
			GA = GA.map(GA => GA / sumGA);
			
			if (ft === "RAISED-COSINE") {
			    RBStempI = this.conv(RBS5[0], RC);
			    RBStempQ = this.conv(RBS5[1], RC);
			} else if (ft === "GAUSSIAN") {
			    RBStempI = this.conv(RBS5[0], GA);
			    RBStempQ = this.conv(RBS5[1], GA);
			}
			
			for (var i = 0; i < RBS5[0].length; i++) {
			    RBS6[0][i] = RBStempI[(N - 1) * 3 / 8 + i];
			    RBS6[1][i] = RBStempQ[(N - 1) * 3 / 8 + i];
			}
			
			return RBS6;
		},
		iqmismatch : function(RBS6, Gain0, Mismatch_Gain, Mismatch_Phase){
			var RBS7 = [new Array(RBS6[0].length), new Array(RBS6[0].length)];

			for (var i = 0; i < RBS6[0].length; i++) {
			    RBS7[0][i] = (RBS6[0][i] * math.pow(10, (Gain0 + 0.5 * Mismatch_Gain) / 20) * math.cos(2 * math.PI / 360 * Mismatch_Phase / 2)) - (RBS6[1][i] * math.pow(10, (Gain0 - 0.5 * Mismatch_Gain) / 20) * math.sin(2 * math.PI / 360 * Mismatch_Phase / 2));
			    RBS7[1][i] = -1 * ((RBS6[0][i] * math.pow(10, (Gain0 + 0.5 * Mismatch_Gain) / 20) * math.sin(2 * math.PI / 360 * Mismatch_Phase / 2)) - (RBS6[1][i] * math.pow(10, (Gain0 - 0.5 * Mismatch_Gain) / 20) * math.cos(2 * math.PI / 360 * Mismatch_Phase / 2)));
			}
			return RBS7;
		},
		conv : function(vec1, vec2){
			var disp = 0;
			var convVec = [];
			
			for (var j = 0; j < vec2.length; j++) {
			    convVec.push(vec1[0] * vec2[j]);
			}
			disp = disp + 1;
			for (var i = 1; i < vec1.length; i++) {
			    for (j = 0; j < vec2.length; j++) {
			        if ((disp + j) !== convVec.length) {
			            convVec[disp + j] = convVec[disp + j] + (vec1[i] * vec2[j])
			        } else {
			            convVec.push(vec1[i] * vec2[j]);
			        }
			    }
			    disp = disp + 1;
			}
			return convVec;
		},
		Rfblock : function(RBS7, object, isTx){
			if(value === null||typeof value =="undefined"||value === ''){
				return 0;
			}else{
				var Comp = this.getStringToZero(object['type']);
				var Gain = "";
				var NF = "";
				var OIP3 = "";
				var IIP3 = isTx?"":OIP3-Gain;
				var Pdbm_AMPM;
				var k;
				var F_fup;
				var F_flo;
				var F_order;
				var Apass;
				var Fs;
				
				var RBS8 = [new Array(RBS7[0].length), new Array(RBS7[0].length)];
				
				if(Comp==0){
					
				}else if(Comp==='AMP-AM'){
					
				}else if(Comp==='AMP-PM'){
					
				}else if(Comp==='BPF-BUTTER'){
					
				}else if(Comp==='BPF-CHEBY'){
					
				}
			}
		}
	}
	
	
})(window);