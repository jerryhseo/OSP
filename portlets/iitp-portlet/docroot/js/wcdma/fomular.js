/*plot Power Level Diagram*/
function getPowerLevelDiagramData(filterData,pIn,channel,inputData){
	var channelPlData = filterData[DESIGNER.Constants.DesignerKey.CHANNEL_PL];
	
	var L_unit = parseFloat(channelPlData['l-unit']);
	var L_dec  = parseFloat(channelPlData['l-dec']);
	var G_Tx   = parseFloat(channelPlData['g-tx']);
	var G_Rx   = parseFloat(channelPlData['g-rx']);
	var D      = parseFloat(channelPlData['distance']);
	
	
	var Gain0 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_MIXER],'gain');
	var Gain1 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_1],'gain');
	var Gain2 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_2],'gain');
	var Gain3 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_3],'gain');
	var Gain4 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_4],'gain');
	var Gain5 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_5],'gain');
	var Gain6 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_1],'gain');
	var Gain7 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_2],'gain');
	var Gain8 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_3],'gain');
	var Gain9 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_MIXER],'gain');
	var Gain10 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_LPF],'gain');
	var Gain11 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BBA],'gain');
	
	
	var Fc = parseFloat(filterData[DESIGNER.Constants.DesignerKey.TX_MIXER]['fc']);
	
	
	
	var Channel = channel;
	var P_in = parseFloat(pIn);
	var Environment1 = inputData['environment1'];
	var Environment2 = inputData['environment2'];
	var F_in		 = inputData['f-in'];
    var Distance 	 = inputData['distance'];
    var Hb 			 = inputData['hb'];
    var Hm 			 = inputData['hm'];
    var f = F_in + Fc;
	
    console.log(L_unit,L_dec,G_Tx,G_Rx,D,Fc,Channel,P_in);
    console.log(Gain0,Gain1,Gain2,Gain3,Gain4,Gain5,Gain6,Gain7,Gain8,Gain9,Gain10,Gain11);
    console.log(Environment1,Environment2,F_in,Distance,Hb,Hm);
	
    var L_dB;
    if (Channel === 'LOG-DISTANCE') {
    	L_dB = -1 * (L_unit + (L_dec * math.log10(D)) - G_Tx - G_Rx);
    }else if(Channel === 'HATA'){
    	if (Environment1 === 'URBAN') {
    		if (150 <= f < 200) {
                var c = 8.29 * math.pow((math.log10(1.54 * Hm)), 2) - 1.1;
                L_dB = -1 * (69.55 + 26.16 * math.log10(f) - 13.82 * math.log10(Hb) - c + (44.9 - 6.55 * math.log10(Hb)) * math.log10(Distance));
            } else if (200 <= f < 1500) {
                var c = 3.2 * math.pow((math.log10(11.75 * Hm)), 2) - 4.97
                L_dB = -1 * (69.55 + 26.16 * math.log10(f) - 13.82 * math.log10(Hb) - c + (44.9 - 6.55 * math.log10(Hb)) * math.log10(Distance));
            }
    	}else if (Environment1 === 'SUBURBS') {
    		Lu = 69.55 + 26.16 * math.log10(f) - 13.82 * math.log10(Hb) + (44.9 - 6.55 * math.log10(Hb)) * math.log10(Distance);
            L_dB = -1 * (Lu - 2 * math.pow((math.log10(f / 28)), 2) - 5.4);
    	}else if (Environment1 === 'RURAL') {
    		 Lu = 69.55 + 26.16 * math.log10(f) - 13.82 * math.log10(Hb) + (44.9 - 6.55 * math.log10(Hb)) * math.log10(Distance);
             L_dB = -1 * (Lu - 4.78 * math.pow(math.log10(f), 2) + 18.33 * math.log10(f) - 40.94);
    	}
    }else if(Channel === 'EGLI'){
    	if (Hm <= 10) {
            L_dB = -1 * (76.3 + 40 * math.log10(Distance) - 20 * math.log10(Hb) - (10 * math.log10(Hm)) + 20 * math.log10(f));

        } else if (Hm > 10) {
            L_dB = -1 * (85.9 + 40 * math.log10(Distance) - 20 * math.log10(Hb) - (10 * math.log10(Hm)) + 20 * math.log10(f));
        }
    }else if(Channel === 'COST'){
    	if (Environment2 === 'RURAL') {
    		var A = (1.1 * math.log10(f) - 0.7) * Hm - (1.56 * math.log10(f) - 0.8)
            L_dB = -1 * (46.3 + 33.9 * math.log10(f) - 13.82 * math.log10(Hb) - A + (44.9 - 6.55 * math.log10(Hb)) * math.log(Distance));
    	}else if (Environment2 === 'URBAN') {
    		if (150 <= f < 200) {
                var A = 8.29 * math.pow((math.log10(1.54 * Hm)), 2) - 1.1;
                L_dB = -1 * (46.3 + 33.9 * math.log10(f) - 13.82 * math.log10(Hb) - A + (44.9 - 6.55 * math.log10(Hb)) * math.log10(Distance) + 3);

            } else if (200 <= f < 1500) {
                var A = 3.2 * math.pow((math.log10(11.75 * Hm)), 2) - 4.97;
                L_dB = -1 * (46.3 + 33.9 * math.log10(f) - 13.82 * math.log10(Hb) - A + (44.9 - 6.55 * math.log10(Hb)) * math.log10(Distance) + 3);

            }
    	}
    }
    
    var stage = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14];
    var P_gain = [0, Gain0, Gain1, Gain2, Gain3, Gain4, Gain5, L_dB, 0, Gain6, Gain7, Gain8, Gain9, Gain10, Gain11];
    var P_level = [];
    
    P_level[0] = P_in + P_gain[0];
    
    for (var i = 1; i < 15; i++) {
        P_level[i] = P_level[i - 1] + P_gain[i];

    }

    var trace1 = {
        x: stage,
        y: P_level,
        type: 'scatter',

    };
    
    var data = [trace1];
    console.log(data);
    return data;
}

function getDataFormFilterData(data,findKey){
	if(typeof data=="undefined"){
		return 0;
	}else{
		return parseFloat(DESIGNER.Constants.getNullToZero(data[findKey]));
	}
}
