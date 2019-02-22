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
			RX_BBA          :'RX-BBA',
			SM_PARA         :'SIMULATION-PARAMETER'
		},
		getNullToZero : function(value){
			var num_check=/^([0-9]*)[\.]?([0-9])?$/;
			
			if(value === null||typeof value =="undefined"||value === ''){
				return 0;
			}else{
				if(isNaN(value)){
					return 0;
				}else{
					return Number(value);
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
		blockCalu : function(RBS7, object, Fs,isRightCal){
			var RBS8 = [new Array(RBS7[0].length), new Array(RBS7[0].length)];
			
			if(object === null||typeof object =="undefined"||object === ''){
				RBS8 = RBS7;
			}else{
				var Comp 		= this.getStringToZero(object['type']);
				var Gain 		= getDataFormFilterData(object,'gain');
				var NF   		= getDataFormFilterData(object,'nf');
				var OIP3 		= getDataFormFilterData(object,'oip3');
				var IIP3 		= isRightCal?getDataFormFilterData(object,'iip3'):OIP3-Gain;
				var Pdbm_AMPM	= getDataFormFilterData(object,'pwr-ap');
				var k			= getDataFormFilterData(object,'am-pm');
				var F_fup		= getDataFormFilterData(object,'f-up');
				var F_flo		= getDataFormFilterData(object,'f-lo');
				var F_order		= getDataFormFilterData(object,'order');
				var Apass		= getDataFormFilterData(object,'apass');
				
				var RBS8 = [new Array(RBS7[0].length), new Array(RBS7[0].length)];
				
				if(Comp==0){
					RBS8 = RBS7;
				}else if(Comp==='AMP-AM'||Comp==='RX-AMP-AM'){
					var theta = math.atan2(RBS7[1], RBS7[0]);
					var alpha1 = math.pow(10, Gain / 20);
					var P_IIP3 = 0.001 * math.pow(10, IIP3 / 10);
					var A_IIP3 = math.sqrt(2 * P_IIP3 * 50);
					var alpha3 = -(4 / 3 * alpha1 / math.pow(A_IIP3, 2));
					var mag = new Array(RBS7[0].length);
					var A = new Array(RBS7[0].length);
					var cos = math.cos(theta);
					var sin = math.sin(theta);
					
					for (var i = 0; i < RBS7[0].length; i++) {
					    mag[i] = math.complex(RBS7[0][i], RBS7[1][i]);
					    mag[i] = math.abs(mag[i]);
					}
					
					A = mag.map(mag => alpha1 * mag + 3 / 4 * alpha3 * math.pow(mag, 3));
					
					for (var i = 0; i < RBS7[0].length; i++) {
					    RBS8[0][i] = A[i] * cos[i];
					    RBS8[1][i] = A[i] * sin[i];
					}
				}else if(Comp==='AMP-PM'||Comp==='RX-AMP-PM'){
					var theta = math.atan2(RBS7[1], RBS7[0]);
					var alpha1 = math.pow(10, Gain / 20);
					
					var P_IIP3 = 0.001 * math.pow(10, IIP3 / 10);
					var A_IIP3 = math.sqrt(2 * P_IIP3 * 50);
					var alpha3 = -(4 / 3 * alpha1 / math.pow(A_IIP3, 2));
					var Pw_AMPM = math.pow(10, ((Pdbm_AMPM - 30) / 10));
					var A0 = math.sqrt(2 * Pw_AMPM);
					var c2 = k / A0;
					var c3 = -k / (3 * math.pow(A0, 2));
					var mag = new Array(RBS7[0].length);
					var A = new Array(RBS7[0].length);
					var deg = new Array(RBS7[0].length);
					var phi = new Array(RBS7[0].length);
					var cos = new Array(RBS7[0].length);
					var sin = new Array(RBS7[0].length);
					
					for (var i = 0; i < RBS7[0].length; i++) {
					    mag[i] = math.complex(RBS7[0][i], RBS7[1][i]);
					    mag[i] = math.abs(mag[i]);
					}
					A = mag.map(mag => alpha1 * mag + 3 / 4 * alpha3 * math.pow(mag, 3));
					deg = A.map(A => c2 * math.pow(A / 5 / math.sqrt(2), 2) + c3 * math.pow(A / 5 / math.sqrt(2), 3));
					
					for (var i = 0; i < RBS7[0].length; i++) {
					    phi[i] = degtorad(deg[i]);
					    cos[i] = math.cos(theta[i] + phi[i]);
					    sin[i] = math.sin(theta[i] + phi[i]);
					    RBS8[0][i] = A[i] * cos[i];
					    RBS8[1][i] = A[i] * sin[i];
					}
				}else if(Comp==='BPF-BUTTER'||Comp==='RX-BPF-BUTTER'){
					var epsilon_LPF = math.sqrt(math.pow(10, Apass / 10) - 1);
					var F_fcent = math.sqrt(F_fup * F_flo);
					var F_fc = (F_fup - F_fcent) * math.pow(10, 6);
					var L = RBS7[0].length;
					var f = new Array(L);
					var T_LPF = new Array(L);
					
					for (var i = 1; i < L; i++) {
					    f[0] = -Fs / 2;
					    f[i] = f[i - 1] + (Fs / L);
					}
					T_LPF = f.map(f => (math.pow(10, Gain / 20)) / math.sqrt(1 + math.pow(epsilon_LPF, 2) * math.pow(f / F_fc, 2 * F_order)));
					
					var SI = this.myfftshift(this.myfft(RBS7[0]));
					var SQ = this.myfftshift(this.myfft(RBS7[1]));
					var SI2 = new Array(L);
					var SQ2 = new Array(L);
					for (var i = 0; i < L; i++) {
					    SI2[i] = math.multiply(SI[i], T_LPF[i]);
					    SQ2[i] = math.multiply(SQ[i], T_LPF[i]);
					}
					RBS8[0] = math.re(this.myifft(this.myfftshift(SI2)));
					RBS8[1] = math.re(this.myifft(this.myfftshift(SQ2)));
				}else if(Comp==='BPF-CHEBY'||Comp==='RX-BPF-CHEBY'){
					var epsilon_LPF = math.sqrt(math.pow(10, Apass / 10) - 1);
					var F_fcent = math.sqrt(F_fup * F_flo);
					var F_fc = (F_fup - F_fcent) * math.pow(10, 6);
					var L = RBS7[0].length;
					var f = new Array(L);
					var T_LPF = new Array(L);
					
					for (var i = 1; i < L; i++) {
					    f[0] = -Fs / 2;
					    f[i] = f[i - 1] + (Fs / L);
					}
					
					for (var i = 0; i < L; i++) {
					    if (math.abs(f[i]) <= F_fc) {
					        T_LPF[i] = (math.pow(10, Gain / 20)) / math.sqrt(1 + math.multiply(math.pow(epsilon_LPF, 2), math.pow(math.cos(math.multiply(math.acos(f[i] / F_fc), F_order)), 2)));
					    } else if (math.abs(f[i]) > F_fc) {
					        T_LPF[i] = (math.pow(10, Gain / 20)) / math.sqrt(1 + math.multiply(math.pow(epsilon_LPF, 2), math.pow(math.cosh(math.multiply(math.acosh(math.abs(f[i]) / F_fc), F_order)), 2)));
					    }
					}
					
					var SI = this.myfftshift(this.myfft(RBS7[0]));
					var SQ = this.myfftshift(this.myfft(RBS7[1]));
					var SI2 = new Array(L);
					var SQ2 = new Array(L);
					for (var i = 0; i < L; i++) {
					    SI2[i] = math.multiply(SI[i], T_LPF[i]);
					    SQ2[i] = math.multiply(SQ[i], T_LPF[i]);
					}
					RBS8[0] = math.re(this.myifft(this.myfftshift(SI2)));
					RBS8[1] = math.re(this.myifft(this.myfftshift(SQ2)));
				}
			}
			
			return RBS8;
		},
		Pathloss : function(RBS8, G_Tx, G_Rx, L_unit, L_dec, D) {
			var RBS9 = [new Array(RBS8[0].length), new Array(RBS8[0].length)];
			
			var RBS9I = math.re(RBS8[0]); 
			var RBS9Q = math.re(RBS8[1]);
			
			var theta = math.atan2(RBS9Q, RBS9I);
			var T_loss = -(L_unit + (L_dec * math.log10(D)) - G_Tx - G_Rx);
			var T_loss_G = math.pow(10, T_loss / 20);
			var cos = math.cos(theta);
			var sin = math.sin(theta);
			
			for (var i = 0; i < RBS8[0].length; i++) {
				RBS9[0][i] = T_loss_G * math.abs(math.complex(RBS9I[i], RBS9Q[i])) * cos[i];
				RBS9[1][i] = T_loss_G * math.abs(math.complex(RBS9I[i], RBS9Q[i])) * sin[i];
			}
			
			return [RBS9, T_loss];
		},
		AWGNC:function(RBS9, SNR_dB){
			var RBS10 = [new Array(RBS9[0].length), new Array(RBS9[0].length)];
			var P = new Array(RBS9[0].length);
			var SNR_lin = math.pow(10, SNR_dB / 10);
			var L_env = RBS9[0].length;
			var QPSK_Cx_atten = new Array(L_env);
			var QPSK_Cx_n = new Array(L_env);
			var noise = new Array(L_env);
			
			for (var i = 0; i < L_env; i++) {
			    QPSK_Cx_atten[i] = math.complex(RBS9[0][i], RBS9[1][i]);
			    P[i] = math.pow(math.abs(QPSK_Cx_atten[i]), 2);
			}
			
			var signalP = math.sum(P) / L_env;
			
			for (var i = 0; i < L_env; i++) {
			    noise[i] = math.multiply(math.sqrt(signalP / SNR_lin), math.complex(this.gaussianRand(), this.gaussianRand()));
			    QPSK_Cx_n[i] = math.sum(QPSK_Cx_atten[i], noise[i]);
			}
			
			for (var i = 0; i < L_env; i++) {
			    RBS10[0][i] = math.re(QPSK_Cx_n[i]);
			    RBS10[1][i] = math.im(QPSK_Cx_n[i]);
			}
			
			return RBS10;
		},
		BBblock : function(RBS89, object, Fs){
			var RBS810 = [new Array(RBS89[0].length), new Array(RBS89[0].length)];
			
			if(object === null||typeof object =="undefined"||object === ''){
				RBS810 = RBS89;
			}else{
				var Comp 		= this.getStringToZero(object['type']);
				var Gain 		= getDataFormFilterData(object,'gain');
				var NF   		= getDataFormFilterData(object,'nf');
				var IIP3 		= getDataFormFilterData(object,'iip3');
				var F_fc2 		= getDataFormFilterData(object,'fcut');
				var F_order 	= getDataFormFilterData(object,'order');
				var Apass	 	= getDataFormFilterData(object,'apass');
				
				if(Comp==0){
					RBS810 = RBS89;
				}else if(Comp==='LPF-BUTTER'){
					var epsilon_LPF = math.sqrt(math.pow(10, Apass / 10) - 1);
					var L = RBS89[0].length;
					var f = new Array(L);
					var T_LPF = new Array(L);
					var F_fc = F_fc2 * math.pow(10, 6);
					
					for (var i = 1; i < L; i++) {
					    f[0] = -Fs / 2;
					    f[i] = f[i - 1] + (Fs / L);
					}
					T_LPF = f.map(f => (math.pow(10, Gain / 20)) / math.sqrt(1 + math.pow(epsilon_LPF, 2) * math.pow(f / F_fc, 2 * F_order)));
					
					var SI = this.myfftshift(this.myfft(RBS89[0]));
					var SQ = this.myfftshift(this.myfft(RBS89[1]));
					var SI2 = new Array(L);
					var SQ2 = new Array(L);
					for (var i = 0; i < L; i++) {
					    SI2[i] = math.multiply(SI[i], T_LPF[i]);
					    SQ2[i] = math.multiply(SQ[i], T_LPF[i]);
					}
					RBS810[0] = math.re(this.myifft(this.myfftshift(SI2)));
					RBS810[1] = math.re(this.myifft(this.myfftshift(SQ2)));
				}else if(Comp==='LPF-CHEBY'){
					var epsilon_LPF = math.sqrt(math.pow(10, Apass / 10) - 1);
					var L = RBS89[0].length;
					var f = new Array(L);
					var T_LPF = new Array(L);
					var F_fc = F_fc2 * math.pow(10, 6);
					
					
					for (var i = 1; i < L; i++) {
					    f[0] = -Fs / 2;
					    f[i] = f[i - 1] + (Fs / L);
					}
					
					for (var i = 0; i < L; i++) {
					    if (math.abs(f[i]) <= F_fc) {
					        T_LPF[i] = (math.pow(10, Gain / 20)) / math.sqrt(1 + math.multiply(math.pow(epsilon_LPF, 2), math.pow(math.cos(math.multiply(math.acos(f[i] / F_fc), F_order)), 2)));
					    } else if (math.abs(f[i]) > F_fc) {
					        T_LPF[i] = (math.pow(10, Gain / 20)) / math.sqrt(1 + math.multiply(math.pow(epsilon_LPF, 2), math.pow(math.cosh(math.multiply(math.acosh(math.abs(f[i]) / F_fc), F_order)), 2)));
					    }
					}
					
					var SI = this.myfftshift(this.myfft(RBS89[0]));
					var SQ = this.myfftshift(this.myfft(RBS89[1]));
					var SI2 = new Array(L);
					var SQ2 = new Array(L);
					for (var i = 0; i < L; i++) {
					    SI2[i] = math.multiply(SI[i], T_LPF[i]);
					    SQ2[i] = math.multiply(SQ[i], T_LPF[i]);
					}
					RBS810[0] = math.re(this.myifft(this.myfftshift(SI2)));
					RBS810[1] = math.re(this.myifft(this.myfftshift(SQ2)));
				}
			}
			
			return RBS810;
		},
		BBblock2 : function(RBS7, object){
			var RBS8 = [new Array(RBS7[0].length), new Array(RBS7[0].length)];
			if(object === null||typeof object =="undefined"||object === ''){
				
			}else{
				var Gain 		= getDataFormFilterData(object,'gain');
				var NF   		= getDataFormFilterData(object,'nf');
				var IIP3 		= getDataFormFilterData(object,'iip3');
				
				var alpha1 = math.pow(10, Gain / 20);
				var P_IIP3 = 0.001 * math.pow(10, IIP3 / 10);
				var A_IIP3 = math.sqrt(2 * P_IIP3 * 50);
				var alpha3 = -(4 / 3 * alpha1 / math.pow(A_IIP3, 2));
				
				for (var i = 0; i < RBS7[0].length; i++) {
					RBS8[0][i] = math.multiply(alpha1, RBS7[0][i]) + math.multiply(alpha3, math.pow(RBS7[0][i], 3));
					RBS8[1][i] = math.multiply(alpha1, RBS7[1][i]) + math.multiply(alpha3, math.pow(RBS7[1][i], 3));
				}
			}
			return RBS8;
		},
		integrate : function(RBS10, SF, t, Fs, zero){
			var RBS11 = [new Array(RBS10[0].length / zero), new Array(RBS10[0].length / zero)];
			var B_int = [new Array(zero), new Array(zero)];
			var constell = [new Array(RBS10[0].length / zero), new Array(RBS10[0].length / zero)];
			
			var Tc = 1 / (Fs / zero);
			var period = new Array(zero);
			
			for (var i = 1; i < zero; i++) {
			    period[0] = 0;
			    period[i] = period[i - 1] + (Tc / zero);
			}
			
			for (var i = 0; i < t * SF / 2; i++) {
			
			    for (var j = 0; j < t; j++) {
			        B_int[0][j] = RBS10[0][zero * i + j];
			        B_int[1][j] = RBS10[1][zero * i + j];
			    }
			    var temp_sum0 = this.trapz(period, B_int[0], Tc);
			    var temp_sum1 = this.trapz(period, B_int[1], Tc);
			
			    constell[0][i] = temp_sum0;
			    constell[1][i] = temp_sum1;
			
			    if (temp_sum0 > 0) {
			        RBS11[0][i] = 1;
			    } else {
			        RBS11[0][i] = -1;
			    }
			
			    if (temp_sum1 > 0) {
			        RBS11[1][i] = 1;
			    } else {
			        RBS11[1][i] = -1;
			    }
			}
			var aver0 = math.sum(math.abs(constell[0])) / (t * SF / 2);
			var aver1 = math.sum(math.abs(constell[1])) / (t * SF / 2);
			constell[0] = math.divide(constell[0], aver0);
			constell[1] = math.divide(constell[1], aver1);
			
			return [RBS11, constell];
		},
		despreading : function(RBS11, wn, SF) {
			var RBS12 = [new Array(RBS11[0].length), new Array(RBS11[0].length)];

			for (var i = 0; i < 2; i++) {
			    for (var j = 0; j < RBS11[0].length; j++) {
			        RBS12[i][j] = RBS11[i][j] * wn[i][j % SF];
			    }
			}
			return RBS12;
		},
		downsample : function(RBS12, SF) {
			var RBS13 = [new Array(RBS12[0].length / SF), new Array(RBS12[0].length / SF)];

			for (var i = 0; i < RBS12[0].length / SF; i++) {
			    RBS13[0][i] = RBS12[0][i * SF];
			    RBS13[1][i] = RBS12[1][i * SF];
			}
			return RBS13;
		},
		patose : function(RBS13, t) {
			var RBS14 = new Array(t);
			for (var i = 0; i < t; i++) {
			    var ii = i % 2;
			    var jj = math.floor(i / 2);
			    RBS14[i] = RBS13[ii][jj];
			}
			return RBS14;
		},
		BERfunc : function(RBS14, RBS1, t) {
			var ber = 0;
			for (var i = 0; i < t; i++) {
			    if (RBS1[i] == RBS14[i]) {
			        ber = ber + 1;
			    } else {
			        ber = ber;
			    }
			}
			var ber2 = (t - ber) / t;
			return ber2;
		},
		gaussianRand : function() {
			var rand = 0;

			for (var i = 0; i < 4; i += 1) {
				rand = rand + math.random();
			}
			var rand2 = rand / 4;
			var randn = -3 + (rand2 * 6);
			
			return randn;
		},
		trapz : function(x, y, T) {
			var temp_sum = 0;
			for (var i = 0; i < x.length - 1; i++) {
			    temp_sum = temp_sum + y[i] + y[i + 1];
			}
			temp_sum = temp_sum * T / (x.length - 1) / 2;
			return temp_sum;
		},
		myfftshift : function(Y) {
			var L = Y.length;
			var shiftm = new Array(L);
			var L2 = L / 2;
			for (var i = 0; i < L2; i++) {
			    shiftm[i] = Y[i + L2];
			    shiftm[i + L2] = Y[i];
			}
			return shiftm;
		},
		myfft : function(input) {
			var p = math.log2(input.length);
			var M = 1; // height
			var M2 = M * 2; // double height
			var N = input.length; // width
			var N2 = N / 2; // half width
			var WW = math.exp(math.multiply(math.sqrt(-1), -1 * math.PI / N2));
			var JJ = new Array(N2);
			
			for (var i = 0; i < N2; i++) {
			    JJ[0] = 0;
			    JJ[i] = JJ[i - 1] + 1;
			}
			
			var Y = [new Array(N)];
			for (var i = 0; i < N; i++) {
			    Y[0][i] = input[i];
			}
			
			W = [new Array(N2)];
			for (var i = 0; i < N2; i++) {
			    W[0][i] = math.pow(WW, JJ[i]);
			}
			
			for (var L = 0; L < p - 1; L++) {
			    var u = new Array(M);
			    for (var i = 0; i < M; i++) {
			        u[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            u[i][j] = Y[i][j];
			        }
			    }
			
			    var v = new Array(M);
			    for (var i = 0; i < M; i++) {
			        v[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            v[i][j] = Y[i][j + N2];
			        }
			    }
			
			    var t = new Array(M);
			    for (var i = 0; i < M; i++) {
			        t[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            t[i][j] = math.sum(u[i][j], v[i][j]);
			        }
			    }
			
			    var S = new Array(M);
			    for (var i = 0; i < M; i++) {
			        S[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            S[i][j] = math.multiply(math.subtract(u[i][j], v[i][j]), W[i][j]);
			
			        }
			    }
			
			    var Y = new Array(M2);
			    for (var i = 0; i < M2; i++) {
			        Y[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            if (i < M)
			                Y[i][j] = t[i][j];
			            else
			                Y[i][j] = S[i - M][j];
			        }
			    }
			
			    var U = new Array(M);
			    for (var i = 0; i < M; i++) {
			        U[i] = new Array(N2 / 2);
			        for (var j = 0; j < N2 / 2; j++) {
			            U[i][j] = W[i][j * 2];
			        }
			    }
			
			    var W = new Array(M2);
			    for (var i = 0; i < M2; i++) {
			        W[i] = new Array(N2 / 2);
			        for (var j = 0; j < N2 / 2; j++) {
			            W[i][j] = U[i % M][j];
			        }
			    }
			
			    var M = M2; // height
			var M2 = M * 2; // double height
			var N = N2; // width
			var N2 = N / 2; // half width
			}
			
			
			var u = new Array(M);
			var v = new Array(M);
			for (var i = 0; i < M; i++) {
			    u[i] = Y[i][0];
			    v[i] = Y[i][1];
			}
			
			var Y = new Array(M * 2);
			for (var i = 0; i < M; i++) {
			    Y[i] = math.sum(u[i], v[i]);
			    Y[i + M] = math.subtract(u[i], v[i]);
			}
			
			return Y;
		},
		myifft : function(input) {
			var p = math.log2(input.length);
			var M = 1; // height
			var M2 = M * 2; // double height
			var N = input.length; // width
			var N2 = N / 2; // half width
			var WW = math.exp(math.multiply(math.sqrt(-1), -1 * math.PI / N2));
			var JJ = new Array(N2);
			
			for (var i = 0; i < N2; i++) {
			    JJ[0] = 0;
			    JJ[i] = JJ[i - 1] + 1;
			}
			JJ = math.multiply(JJ, -1);
			
			var Y = [new Array(N)];
			for (var i = 0; i < N; i++) {
			    Y[0][i] = input[i];
			}
			
			W = [new Array(N2)];
			for (var i = 0; i < N2; i++) {
			    W[0][i] = math.pow(WW, JJ[i]);
			}
			
			for (var L = 0; L < p - 1; L++) {
			    var u = new Array(M);
			    for (var i = 0; i < M; i++) {
			        u[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            u[i][j] = Y[i][j];
			        }
			    }
			
			    var v = new Array(M);
			    for (var i = 0; i < M; i++) {
			        v[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            v[i][j] = Y[i][j + N2];
			        }
			    }
			
			    var t = new Array(M);
			    for (var i = 0; i < M; i++) {
			        t[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            t[i][j] = math.sum(u[i][j], v[i][j]);
			        }
			    }
			
			    var S = new Array(M);
			    for (var i = 0; i < M; i++) {
			        S[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            S[i][j] = math.multiply(math.subtract(u[i][j], v[i][j]), W[i][j]);
			
			        }
			    }
			
			    var Y = new Array(M2);
			    for (var i = 0; i < M2; i++) {
			        Y[i] = new Array(N2);
			        for (var j = 0; j < N2; j++) {
			            if (i < M)
			                Y[i][j] = t[i][j];
			            else
			                Y[i][j] = S[i - M][j];
			        }
			    }
			
			    var U = new Array(M);
			    for (var i = 0; i < M; i++) {
			        U[i] = new Array(N2 / 2);
			        for (var j = 0; j < N2 / 2; j++) {
			            U[i][j] = W[i][j * 2];
			        }
			    }
			
			    var W = new Array(M2);
			    for (var i = 0; i < M2; i++) {
			        W[i] = new Array(N2 / 2);
			        for (var j = 0; j < N2 / 2; j++) {
			            W[i][j] = U[i % M][j];
			        }
			    }
			
			    var M = M2; // height
			var M2 = M * 2; // double height
			var N = N2; // width
			var N2 = N / 2; // half width
			}
			
			
			var u = new Array(M);
			var v = new Array(M);
			for (var i = 0; i < M; i++) {
			    u[i] = Y[i][0];
			    v[i] = Y[i][1];
			}
			
			var Y = new Array(M * 2);
			for (var i = 0; i < M; i++) {
			    Y[i] = math.sum(u[i], v[i]);
			    Y[i + M] = math.subtract(u[i], v[i]);
			}
			Y = math.multiply(Y, 1 / math.pow(2, p));
			
			return Y;
		}
	}
	
	
})(window);