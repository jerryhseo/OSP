var t = 16384;
//var t = 32768;

/*Calculation Fomular*/
function calculation(filterData){
	console.log(filterData);
	
	var Gain0  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_MIXER]  ,'gain');
	var Gain1  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_1],'gain');
	var Gain2  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_2],'gain');
	var Gain3  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_3],'gain');
	var Gain4  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_4],'gain');
	var Gain5  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_5],'gain');
	var Gain6  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_1],'gain');
	var Gain7  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_2],'gain');
	var Gain8  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_3],'gain');
	var Gain9  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_MIXER]  ,'gain');
	var Gain10 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_LPF]    ,'gain');
	var Gain11 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BBA]    ,'gain');
	
	var linGain0  = math.pow(10, Gain0 / 10);
	var linGain1  = math.pow(10, Gain1 / 10);
	var linGain2  = math.pow(10, Gain2 / 10);
	var linGain3  = math.pow(10, Gain3 / 10);
	var linGain4  = math.pow(10, Gain4 / 10);
	var linGain5  = math.pow(10, Gain5 / 10);
	var linGain6  = math.pow(10, Gain6 / 10);
	var linGain7  = math.pow(10, Gain7 / 10);
	var linGain8  = math.pow(10, Gain8 / 10);
	var linGain9  = math.pow(10, Gain9 / 10);
	var linGain10 = math.pow(10, Gain10/ 10);
	var linGain11 = math.pow(10, Gain11/ 10);
	
//	console.log(linGain0+"_"+linGain1+"_"+linGain2+"_"+linGain3+"_"+linGain4+"_"+linGain5+"_"+linGain6+"_"+linGain7+"_"+linGain8+"_"+linGain9+"_"+linGain10+"_"+linGain11);
	
	
	var NF0  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_MIXER]  ,'nf');
	var NF1  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_1],'nf');
	var NF2  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_2],'nf');
	var NF3  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_3],'nf');
	var NF4  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_4],'nf');
	var NF5  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_5],'nf');
	var NF6  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_1],'nf');
	var NF7  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_2],'nf');
	var NF8  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BLOCK_3],'nf');
	var NF9  = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_MIXER]  ,'nf');
	var NF10 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_LPF]    ,'nf');
	var NF11 = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.RX_BBA]    ,'nf');
	
	
	var linNF0  = math.pow(10, NF0 / 10);
	var linNF1  = math.pow(10, NF1 / 10);
	var linNF2  = math.pow(10, NF2 / 10);
	var linNF3  = math.pow(10, NF3 / 10);
	var linNF4  = math.pow(10, NF4 / 10);
	var linNF5  = math.pow(10, NF5 / 10);
	var linNF6  = math.pow(10, NF6 / 10);
	var linNF7  = math.pow(10, NF7 / 10);
	var linNF8  = math.pow(10, NF8 / 10);
	var linNF9  = math.pow(10, NF9 / 10);
	var linNF10 = math.pow(10, NF10/ 10);
	var linNF11 = math.pow(10, NF11/ 10);
	
//	console.log(linNF0+"_"+linNF1+"_"+linNF2+"_"+linNF3+"_"+linNF4+"_"+linNF5+"_"+linNF6+"_"+linNF7+"_"+linNF8+"_"+linNF9+"_"+linNF10+"_"+linNF11);
	
	
	var TxGain = Gain0 + Gain1 + Gain2 + Gain3 + Gain4 + Gain5;
	var RxGain = Gain6 + Gain7 + Gain8 + Gain9 + Gain10 + Gain11;
	var TxNF = linNF0 + (linNF1 - 1) / linGain0 + (linNF2 - 1) / (linGain0 * linGain1) + (linNF3 - 1) / (linGain0 * linGain1 * linGain2) + (linNF4 - 1) / (linGain0 * linGain1 * linGain2 * linGain3) + (linNF5 - 1) / (linGain0 * linGain1 * linGain2 * linGain3 * linGain4);
	var RxNF = linNF6 + (linNF7 - 1) / linGain6 + (linNF8 - 1) / (linGain6 * linGain7) + (linNF9 - 1) / (linGain6 * linGain7 * linGain8) + (linNF10 - 1) / (linGain6 * linGain7 * linGain8 * linGain9) + (linNF11 - 1) / (linGain6 * linGain7 * linGain8 * linGain9 * linGain10);
	
//    document.getElementById("TxGain").value = TxGain;
//    document.getElementById("RxGain").value = RxGain;
//    document.getElementById("TxNF").value = 10 * math.log10(TxNF);
//    document.getElementById("RxNF").value = 10 * math.log10(RxNF);
	
	var RBS0 = DESIGNER.Constants.randbit(t);
	var RBS1 = RBS0.map(RBS0 => RBS0 * 2 - 1);
	var RBS2 = DESIGNER.Constants.stop(RBS1,t);
	
	var SF = 4; // remove code
	var RBS3 = DESIGNER.Constants.stretch(RBS2, SF);
	
	[RBS4, wn] = DESIGNER.Constants.spreading(RBS3, SF);
	var zero = 8;// remove code - Samples/Chip
	var RBS5 = DESIGNER.Constants.zeroinsert(RBS4, zero);
	
	var ft = "RAISED-COSINE";// remove code - Pulse Shaping Filter
	var rolloff = 0.22;// remove code - Roll-off Factor
	var B = 5;// remove code - 3dB BW(MHz)
	var N = 33;// Filter Taps
	
	var RBS6 = DESIGNER.Constants.pulsefilter(RBS5, ft, rolloff, N, zero, B);
	
	var Mismatch_Gain = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_MIXER]    ,'imbal-db');
	var Mismatch_Phase = getDataFormFilterData(filterData[DESIGNER.Constants.DesignerKey.TX_MIXER]    ,'imbal-deg');
	
	var RBS7 = DESIGNER.Constants.iqmismatch(RBS6, Gain0, Mismatch_Gain, Mismatch_Phase);
	var RBS8 = DESIGNER.Constants.Rfblock(RBS7,filterData[DESIGNER.Constants.DesignerKey.TX_BLOCK_1],true);
}


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
