/* Response Type - Chebyshev Flat Fun Start */
function getChebyshevGraphPassData(filterData, filterType,orderNum){
	var returnData = [];
	var x = [];
	var y = [];
	
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var passbandRipple = parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	
	var farbitary = stopFrequency * 10
	var z = numeric.linspace((stopFrequency)/1000, (centerFrequency) * 1000, 10000);
	for (var i = 0; i < 9999; i++) {
		var xtrans = 0;
		if(filterType==="LOWPASS"){
			xtrans = z[i] / farbitary;
		}else{
			xtrans = farbitary / z[i];
		}
		
		var tn = math.cosh(2 * orderNum * math.re(math.acosh(xtrans))) + math.cos(2 * orderNum * math.im(math.acosh(xtrans)));
		var gr = 1 + math.pow(10, (passbandRipple / 10) - 1) * math.pow(tn, 2)
		y[i] = (-10) * math.log10(gr);
		x[i] = math.log10(z[i]);
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}

function getChebyshevGraphBandData(filterData, filterType,orderNum){
	var returnData = [];
	var x = [];
	var y = [];
	
	var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
	var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
	var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
	var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
	
	var passbandRipple =  parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
	
	var w1BPF = passbandFreqL;
	var w2BPF = passbandFreqH;
	var w1out = stopbandFreqL;
	var w2out = stopbandFreqH;
	var woBPF = math.sqrt(passbandFreqL * passbandFreqH);
	var wdelta = (passbandFreqH - passbandFreqL) / math.sqrt(passbandFreqL * passbandFreqH);
	var z = numeric.linspace(math.pow(10, math.log10(woBPF) - 1.5 * math.log10(wdelta)), math.pow(10, math.log10(woBPF) + 1.5 * math.log10(wdelta)), 10000);
	for (var i = 0; i < 9999; i++) {
		var k1 = z[i] / woBPF;
		var k2 = woBPF / z[i];
		var xtrans = filterType==="BANDPASS"?(1 / wdelta) * (k1 - k2):wdelta * 1/(k1 - k2);
		var tn = math.cosh(2 * orderNum * math.re(math.acosh(xtrans))) + math.cos(2 * orderNum * math.im(math.acosh(xtrans)));
		var gr = 1 + math.pow(10, (passbandRipple / 10) - 1) * math.pow(tn, 2)
		x[i] = math.log10(z[i]);
		y[i] = (-10) * math.log10(gr);
	}
	
	returnData.push(x);
	returnData.push(y);
	
	console.log(returnData);
	return returnData;
}

function getChebyshevGraphData(filterData,filterType,selectedOrder){
	
	var data = [];
	var optimumOrder = 0;
	var trace1GraphData;
	if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
		var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
		var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
		var passbandRipple = parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		optimumOrder = getChebyshevOrderNumberLH(centerFrequency, stopFrequency, passbandRipple, stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		
		trace1GraphData = getChebyshevGraphPassData(filterData, filterType,optimumOrder);
	}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
		var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
		var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
		var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
		var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
		
		var passbandRipple =  parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		optimumOrder = getChebyshevOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandRipple,stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		
		trace1GraphData = getChebyshevGraphBandData(filterData, filterType,optimumOrder);
	}
	
	data.push({
		x: trace1GraphData[0],
		y: trace1GraphData[1],
		type: 'scatter',
		name: 'Optimum Order'
	});
	
	
	if(selectedOrder!=0){
		var trace2GraphData;
		if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
			trace2GraphData = getChebyshevGraphPassData(filterData, filterType,selectedOrder);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			trace2GraphData = getChebyshevGraphBandData(filterData, filterType,selectedOrder);
		}
		
		data.push({
			x: trace2GraphData[0],
			y: trace2GraphData[1],
			type: 'scatter',
			name: 'Selected Order'
		});
	}
	
	return data;
}

function getChebyshevOrderNumberLH(centerFrequency, stopFrequency, passbandRipple, stopbandAttenuation,filterType){
	var wc = centerFrequency;
	var w = stopFrequency;
	var xtransmin = 0;
	if(filterType==="LOWPASS"){
		xtransmin = w / wc;
	}else if(filterType==="HIGHPASS"){
		xtransmin = wc / w;
	}
	
	var Lar = passbandRipple;
	var A = stopbandAttenuation;
	var denominator1 = math.acosh(math.sqrt((math.pow(10, A / 10 - 1) / (math.pow(10, Lar / 10 - 1)))));
	var numerator1 = math.acosh(xtransmin);
	var a = math.re(numerator1);
	var b = math.im(numerator1);
	
	
	return math.ceil(denominator1 * math.sqrt(a + b) / (math.pow(a, 2) + math.pow(b, 2)));
}

function getChebyshevOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandRipple, stopbandAttenuation,filterType){
	var wobpf = Math.sqrt(passbandFreqL * passbandFreqH);
	var wdelta2 = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqL * passbandFreqH);
	var xtrans1 = filterType==="BANDPASS"?(1 / wdelta2) * ((stopbandFreqL / wobpf) - (wobpf / stopbandFreqL)):wdelta2 * 1 / ((stopbandFreqL / wobpf) - (wobpf / stopbandFreqL));
	var xtrans2 = filterType==="BANDPASS"?(1 / wdelta2) * ((stopbandFreqH / wobpf) - (wobpf / stopbandFreqH)):wdelta2 * 1 / ((stopbandFreqH / wobpf) - (wobpf / stopbandFreqH));
	
	var xtransmin = math.min(math.abs(xtrans1), math.abs(xtrans2));
	var Lar = passbandRipple;
	var A = stopbandAttenuation;
	var denominator1 = math.acosh(math.sqrt((math.pow(10, A / 10 - 1) / (math.pow(10, Lar / 10 - 1)))));
	var numerator1 = math.acosh(xtransmin);
	
	if(filterType==="BANDPASS"){
		return math.ceil(denominator1 / numerator1);
	}else{
		var a = math.re(numerator1);
		var b = math.im(numerator1);
		return math.ceil(denominator1 * math.sqrt(a + b) / (math.pow(a, 2) + math.pow(b, 2)));
	}
}


/* Response Type - Chebyshev Flat Fun End */


/* Response Type - Maximally Flat Fun Start */
function getMaximallyGraphPassData(filterData, filterType,orderNum){
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
	
	
	var firstVar = filterType==="LOWPASS"?centerFrequency:stopFrequency;
	var secondVar = filterType==="LOWPASS"?stopFrequency:centerFrequency;
	
	var returnData = [];
	var x = [];
	var y = [];
	
	for (var i = 1; i < 1000; i++) {
		x[i] = math.log10(i * firstVar / (secondVar * 10));
		var xtrans = filterType==="LOWPASS"?(i * centerFrequency) / (stopFrequency * 10):1/((i * stopFrequency) / (centerFrequency * 10));
		y[i] = (-10) * math.log10(1 + Math.pow(xtrans, 2 * orderNum));
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}

function getMaximallyGraphBandData(filterData, filterType,orderNum){
	var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
	var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
	var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
	var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
	var passbandAttenuation =  parseFloat(filterData[DESIGNER.Constants.SPEC_PA]);
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
	
	var w1BPF = passbandFreqL;
	var w2BPF = passbandFreqH;
	var w1out = stopbandFreqL;
	var w2out = stopbandFreqH;
	var woBPF = Math.sqrt(passbandFreqL * passbandFreqH);
	var wdelta = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqL * passbandFreqH);
	 
	var z = numeric.linspace(Math.pow(10, math.log10(woBPF) - 1.5 * math.log10(wdelta)), Math.pow(10, math.log10(woBPF) + 1.5 * math.log10(wdelta)), 10000);
	
	var returnData = [];
	var x = [];
	var y = [];
	
	for (var i = 0; i < 9999; i++) {
		var k1 = z[i] / woBPF
		var k2 = woBPF / z[i]
		var xtrans = filterType==="BANDPASS"?(1 / wdelta) * (k1 - k2):1/wdelta * 1 / (k1 - k2);
		x[i] = math.log10(z[i])
		y[i] = (-10) * math.log10(1 + Math.pow(xtrans, 2 * orderNum));
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}

function getMaximallyGraphData(filterData,filterType,selectedOrder){
	var data = [];
	var trace1GraphData;
	
	if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
		var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
		var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		var optimumOrder = getMaximallyOrderNumberLH(centerFrequency, stopFrequency, stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		
		trace1GraphData = getMaximallyGraphPassData(filterData, filterType,optimumOrder);
	}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
		var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
		var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
		var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
		var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
		var passbandAttenuation =  parseFloat(filterData[DESIGNER.Constants.SPEC_PA]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		var optimumOrder = getMaximallyOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandAttenuation,stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		trace1GraphData = getMaximallyGraphBandData(filterData, filterType,optimumOrder);
	}
	data.push({
		x: trace1GraphData[0],
		y: trace1GraphData[1],
		type: 'scatter',
		name: 'Optimum Order'
	});
	
	
	if(selectedOrder!=0){
		var trace2GraphData;
		if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
			trace2GraphData = getMaximallyGraphPassData(filterData, filterType,selectedOrder);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			trace2GraphData = getMaximallyGraphBandData(filterData, filterType,selectedOrder);
		}
		
		data.push({
			x: trace2GraphData[0],
			y: trace2GraphData[1],
			type: 'scatter',
			name: 'Selected Order'
		});
	}
	
	return data;
}

function getMaximallyOrderNumberLH(centerFrequency, stopFrequency, stopbandAttenuation,filterType){
	var wc = centerFrequency;
	var w = stopFrequency;
	var k = 0;
	var outatt = stopbandAttenuation;
	
	if(filterType==="LOWPASS"){
		k = w / wc;
	}else if(filterType==="HIGHPASS"){
		k = wc / w;
	}
	
	return Math.ceil(math.log10(Math.pow(10, (outatt / 10)) - 1) / (2 * math.log10(k)));
}

function getMaximallyOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandAttenuation, stopbandAttenuation,filterType){
	var wbs1 = filterType==="BANDPASS"?passbandFreqL:stopbandFreqL;
	var wbs2 = filterType==="BANDPASS"?passbandFreqH:stopbandFreqH;
	var wp1 = filterType==="BANDPASS"?stopbandFreqL:passbandFreqL;
	var wp2 = filterType==="BANDPASS"?stopbandFreqH:passbandFreqH;
	var passatt = passbandAttenuation;
	var outatt = stopbandAttenuation;
	var wobpf = Math.sqrt(wbs1 * wbs2);
	var wdelta2 = (wbs2 - wbs1) / Math.sqrt(wbs1 * wbs2);
	var xtrans1 = filterType==="BANDPASS"?(1 / wdelta2) * ((wp1 / wobpf) - (wobpf / wp1)):wdelta2 * 1/((wp1 / wobpf) - (wobpf / wp1));
	var xtrans2 = filterType==="BANDPASS"?(1 / wdelta2) * ((wp2 / wobpf) - (wobpf / wp2)):wdelta2 * 1/((wp2 / wobpf) - (wobpf / wp2));
	var xtransmax = Math.max(xtrans1, xtrans2)
	var order1 = Math.ceil(math.log10(Math.pow(10, (passatt / 10)) - 1) / (2 * math.log10(xtransmax)));
	var order2 = Math.ceil(math.log10(Math.pow(10, (outatt / 10)) - 1) / (2 * math.log10(xtransmax)));
	return Math.max(order1, order2);
}

/* Response Type - Maximally Flat Fun End */

/* line calculator Fun Start */
function getLineCalculatorWidth(characteristicImpedance,dielectricConstant,height){
	var A = characteristicImpedance / 60 * math.sqrt((dielectricConstant + 1) / 2) + (dielectricConstant - 1) / (dielectricConstant + 1) * (0.23 + 0.11 / dielectricConstant);
	var B = 377 * math.pi / 2 / characteristicImpedance / math.sqrt(dielectricConstant);
	
	var Wh_slim = 8 * math.exp(A) / (math.exp(2 * A) - 2);
    var Wh_wide = 2 / math.pi * (B - 1 - math.log(2 * B - 1) + (dielectricConstant - 1) / 2 / dielectricConstant * (math.log(B - 1) + 0.39 - 0.61 / dielectricConstant));
	
    if (Wh_slim <= 2 && Wh_wide <= 2) {
        var W = Wh_slim * height;
        
    } else if (Wh_slim >= 2 && Wh_wide >= 2) {
        var W = Wh_wide * height;
        
    } else {
        var W = 0; // it means errors
    }
    
    return W;
}

function getLineCalculatorCharImp(dielectricConstant,height,width){
	 var Wh = width / height;
     var eff1 = (dielectricConstant + 1) / 2 + (dielectricConstant - 1) / 2 * (math.pow((1 + 12 / Wh), (-0.5)) + 0.04 * math.pow((1 + Wh), 2));
     var eff2 = (dielectricConstant + 1) / 2 + (dielectricConstant - 1) / 2 * (math.pow((1 + 12 / Wh), (-0.5)));
     var Z0_1 = 60 / math.sqrt(eff1) * math.log(8 / Wh + Wh / 4);
     var Z0_2 = 120 * math.pi / (eff2 * (Wh + 1.393 + 0.667 * math.log(Wh + 1.444)));
     
     if (Wh <= 1) {
         var Z0 = Z0_1;

     } else if (Wh >= 1) {
         var Z0 = Z0_1;

     } else {
         var Z0 = 0; // it means errors
     }

     return Z0;
}
/* line calculator Fun End */
/* Filter Design Fun Start */
function getGtableVar(responseType,passbandRipple){
	var gtable;
	if(responseType==="MAXIMALLY"){
		gtable=[[1,0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [1,2,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [1,1.4142,	1.4142,	1,	0,	0,	0,	0,	0,	0,	0,	0],
            [1, 1,	2,	1,	1,	0,	0,	0,	0,	0,	0,	0],
            [1, 0.7654,	1.8478,	1.8478,	0.7654,	1,	0,	0,	0,	0,	0,	0],
            [1, 0.618,	1.618,	2,	1.618,	0.618,	1,	0,	0,	0,	0,	0],
            [1, 0.5176,	1.4142,	1.9318,	1.9318,	1.4142,	0.5176,	1,	0,	0,	0,	0],
            [1, 0.445,	1.247,	1.8019,	2,	1.8019,	1.247,	0.445,	1,	0,	0,	0],
            [1, 0.3902,	1.1111,	1.6629,	1.9615,	1.9615,	1.6629,	1.1111,	0.3902,	1,	0,	0],
            [1, 0.3473,	1,	1.5321,	1.8794,	2,	1.8794,	1.5321,	1,	0.3473,	1,	0],
            [1, 0.3129,	0.908,	1.4142,	1.782,	1.9754,	1.9754,	1.782,	1.4142, 0.908,	0.3129,	1]];
	}else if(responseType==="CHEBYSHEV"&&passbandRipple===3){
		gtable=[[1,0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [1,1.9953,	1,	0,	0	,0	,0	,0	,0	,0	,0	,0],
            [1, 3.1013,	0.5339,	5.8095,	0,	0,	0,	0,	0,	0,	0,0],
            [1, 3.3487	,0.7117,	3.3487,	1	,0	,0	,0	,0	,0	,0	,0],
            [1, 3.4289	,0.7483	,4.3471	,0.592,	5.8095	,0,	0,	0,	0,	0,	0],
            [1, 3.4817	,0.7618	,4.5381	,0.7618	,3.4817	,1,	0,	0	,0	,0	,0],
            [1, 3.5045	,0.7685	,4.6061	,0.7929	,4.4641	,0.6033,	5.8095,	0,	0,	0,	0],
            [1, 3.5182	,0.7723	,4.6386	,0.8039	,4.6386	,0.7723	,3.5182,	1	,0	,0	,0],
            [1, 3.5277	,0.7745	,4.6575	,0.8089	,4.699	,0.8018	,4.499,	0.6073,	5.8095	,0,	0],
            [1, 3.534	,0.776	,4.6692	,0.8118	,4.7272	,0.8118	,4.6692,	0.776	,3.534	,1,	0],
            [1, 3.5384	,0.7771,	4.6768,	0.8136,	4.7425	,0.8164,	4.726	,0.8051,	4.5142	,0.6091,	5.8095 ]];
	}else if(responseType==="CHEBYSHEV"&&passbandRipple===0.5){
		gtable=[[1,0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [1,0.6986,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [1,1.4029,	0.7071,	1.9841,	0,	0,	0,	0,	0,	0,	0,	0],
            [1,1.5963,	1.0967,	1.5963,	1,	0,	0,	0,	0,	0,	0,	0],
            [1,1.6703,	1.1926,	2.3661,	0.8419,	1.9841,	0,	0,	0,	0,	0,	0],
            [1,1.7058,	1.2296,	2.5408,	1.2296,	1.7058,	1,	0,	0,	0,	0,	0],
            [1,1.7254,	1.2479,	2.6064,	1.3137,	2.4758,	0.8696,	1.9841,	0,	0,	0,	0],
            [1,1.7372,	1.2583,	2.6381,	1.3444,	2.6381,	1.2583,	1.7372,	1,	0,	0,	0],
            [1,1.7451,	1.2647,	2.6564,	1.359,	2.6964,	1.3389,	2.5093,	0.8796,	1.9841,	0,	0],
            [1,1.7504,	1.269,	2.6678,	1.3673,	2.7239,	1.3673,	2.6678,	1.269,	1.7504,	1,	0],
            [1,1.7543,	1.2721,	2.6754,	1.3725,	2.7392,	1.3806,	2.7231,	1.3485,	2.5239,	0.8842,	1.9841]];
	}
	return gtable;
}

function getFilterDesignData(filterData, responseType, filterType, characteristicImpedance){
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	
	
	var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
	var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
	var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
	var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
	
	var passbandRipple =  parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	var passbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_PA])
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
	
	var optimumOrder = 0;
	if(responseType==="MAXIMALLY"){
		if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
			optimumOrder = getMaximallyOrderNumberLH(centerFrequency, stopFrequency, stopbandAttenuation,filterType);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			optimumOrder = getMaximallyOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandAttenuation,stopbandAttenuation,filterType);
		}
	}else if(responseType==="CHEBYSHEV"){
		if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
			optimumOrder = getChebyshevOrderNumberLH(centerFrequency, stopFrequency, passbandRipple, stopbandAttenuation,filterType);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			optimumOrder = getChebyshevOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandRipple,stopbandAttenuation,filterType);
		}
	}
	
	var gtable = getGtableVar(responseType, passbandRipple);
	var Zo = characteristicImpedance;
	
	
	
	var inductor = [];
	var capacitor = [];
	var wobpf_f = Math.sqrt(passbandFreqH * passbandFreqL);
	var wdelta_f = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqH * passbandFreqL);
	
	for (var i = 0; i < optimumOrder; i++){
		if(filterType==="LOWPASS"){
			if(i % 2 ==0){
				capacitor[i] = math.pow(10,12)*((gtable[optimumOrder][i + 1]) / Zo / (2 * math.pi * centerFrequency));
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * ((gtable[optimumOrder][i + 1]) * Zo / (2 * math.pi * centerFrequency));
				capacitor[i] = 0;
			}
		}else if(filterType==="HIGHPASS"){
			if(i % 2 ==0){
				capacitor[i] = math.pow(10,12)*1 / ((gtable[optimumOrder][i + 1]) * Zo * (2 * math.pi * centerFrequency));
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * Zo / ((gtable[optimumOrder][i + 1]) * (2 * math.pi * centerFrequency));
				capacitor[i] = 0;  
			}
		}else if(filterType==="BANDPASS"){
			if(i % 2 ==0){
				inductor[i] = math.pow(10, 9) * (gtable[optimumOrder][i + 1]) / (2 * math.pi * wobpf_f * wdelta_f);
				capacitor[i] = math.pow(10, 12) * wdelta_f / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f));
			}else if(i % 2 == 1){
				capacitor[i] = math.pow(10, 12) * (gtable[optimumOrder][i + 1]) / (2 * math.pi * wobpf_f * wdelta_f);
				inductor[i] = math.pow(10, 9) * wdelta_f / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f));
			}
		}else if(filterType==="BANDSTOP"){
			if(i % 2 ==0){
				inductor[i] = math.pow(10, 9) * 1 / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f * wdelta_f));
				capacitor[i] = math.pow(10, 12) * wdelta_f * gtable[optimumOrder][i + 1] / (2 * math.pi * wobpf_f);
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * (gtable[optimumOrder][i + 1]) * wdelta_f / (2 * math.pi * wobpf_f);
				capacitor[i] = math.pow(10, 12) * 1 / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f * wdelta_f));
			}
		}
	}
	
	var returnObject = {
		"optimumOrder":optimumOrder,
		"capacitor":capacitor,
		"inductor":inductor
	};
	
	return returnObject;
}

/* Filter Design Table Grid */
function filterDesignTableGrid(object,tbody1,tbody2){
	var optimumOrder = object.optimumOrder;
	var capacitor = object.capacitor;
	var inductor = object.inductor;
	tbody1.empty();
	
	for (var i = 0; i < optimumOrder; i++){
		var $tr = $("<tr/>");
		var num = i+1;
		$("<td/>").addClass("col-md-2 text-center").html(num).appendTo($tr);
		$("<td/>").addClass("col-md-5 text-center").html(capacitor[i]).appendTo($tr);
		$("<td/>").addClass("col-md-5 text-center").html(inductor[i]).appendTo($tr);
		$tr.appendTo(tbody1);
	}
	
	
	tbody2.empty().append(tbody1.children().clone(true));
}

/* Filter Design Fun End */
/* Modal Gtable Grid Fun Start */
function getGtableData(responseType,passbandRipple){
	var gtable;
	if(responseType==="MAXIMALLY"){
		gtable = [[2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [1.4142, 1.4142, 1, 0, 0, 0, 0, 0, 0, 0, 0],
            [1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0],
            [0.7654, 1.8478, 1.8478, 0.7654, 1, 0, 0, 0, 0, 0, 0],
            [0.618, 1.618, 2, 1.618, 0.618, 1, 0, 0, 0, 0, 0],
            [0.5176, 1.4142, 1.9318, 1.9318, 1.4142, 0.5176, 1, 0, 0, 0, 0],
            [0.445, 1.247, 1.8019, 2, 1.8019, 1.247, 0.445, 1, 0, 0, 0],
            [0.3902, 1.1111, 1.6629, 1.9615, 1.9615, 1.6629, 1.1111, 0.3902, 1, 0, 0],
            [0.3473, 1, 1.5321, 1.8794, 2, 1.8794, 1.5321, 1, 0.3473, 1, 0],
            [0.3129, 0.908, 1.4142, 1.782, 1.9754, 1.9754, 1.782, 1.4142, 0.908, 0.3129, 1]];
	}else if(responseType==="CHEBYSHEV"&&passbandRipple===3){
		gtable = [[1.9953, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [3.1013, 0.5339, 5.8095, 0, 0, 0, 0, 0, 0, 0, 0],
            [3.3487, 0.7117, 3.3487, 1, 0, 0, 0, 0, 0, 0, 0],
            [3.4289, 0.7483, 4.3471, 0.592, 5.8095, 0, 0, 0, 0, 0, 0],
            [3.4817, 0.7618, 4.5381, 0.7618, 3.4817, 1, 0, 0, 0, 0, 0],
            [3.5045, 0.7685, 4.6061, 0.7929, 4.4641, 0.6033, 5.8095, 0, 0, 0, 0],
            [3.5182, 0.7723, 4.6386, 0.8039, 4.6386, 0.7723, 3.5182, 1, 0, 0, 0],
            [3.5277, 0.7745, 4.6575, 0.8089, 4.699, 0.8018, 4.499, 0.6073, 5.8095, 0, 0],
            [3.534, 0.776, 4.6692, 0.8118, 4.7272, 0.8118, 4.6692, 0.776, 3.534, 1, 0],
            [3.5384, 0.7771, 4.6768, 0.8136, 4.7425, 0.8164, 4.726, 0.8051, 4.5142, 0.6091, 5.8095]];
	}else if(responseType==="CHEBYSHEV"&&passbandRipple===0.5){
		gtable = [[0.6986, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [1.4029, 0.7071, 1.9841, 0, 0, 0, 0, 0, 0, 0, 0],
            [1.5963, 1.0967, 1.5963, 1, 0, 0, 0, 0, 0, 0, 0],
            [1.6703, 1.1926, 2.3661, 0.8419, 1.9841, 0, 0, 0, 0, 0, 0],
            [1.7058, 1.2296, 2.5408, 1.2296, 1.7058, 1, 0, 0, 0, 0, 0],
            [1.7254, 1.2479, 2.6064, 1.3137, 2.4758, 0.8696, 1.9841, 0, 0, 0, 0],
            [1.7372, 1.2583, 2.6381, 1.3444, 2.6381, 1.2583, 1.7372, 1, 0, 0, 0],
            [1.7451, 1.2647, 2.6564, 1.359, 2.6964, 1.3389, 2.5093, 0.8796, 1.9841, 0, 0],
            [1.7504, 1.269, 2.6678, 1.3673, 2.7239, 1.3673, 2.6678, 1.269, 1.7504, 1, 0],
            [1.7543, 1.2721, 2.6754, 1.3725, 2.7392, 1.3806, 2.7231, 1.3485, 2.5239, 0.8842, 1.9841]];
	}
	return gtable;
}


function elementValuesTableGrid(filterData, responseType,optimumOrder,tbody){
	
	var passbandRipple =  parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	var gtable = getGtableData(responseType, passbandRipple);
	
	tbody.empty();
	
	for (var i = 0; i < gtable.length; i++){
		var num = i+1;
		var $tr = $("<tr/>").appendTo(tbody);
		if(num===optimumOrder){$tr.addClass("info");}
		
		$("<th/>").html("N"+num).appendTo($tr);
		for(var j = 0; j<gtable[i].length; j++){
			$("<td/>").html(gtable[i][j]).appendTo($tr);
		}
	}
}

/* Modal Gtable Grid Fun End */

/*Modal Detemine Filter Order Grid Fun Start*/
function getDetemineFilterOrderGraphData(responseType, filterData,filterType,optimumOrder){
	var trace1Data,trace2Data,trace3Data;
	
	var preOptimumOrder = optimumOrder-1;
	var nextOptimumOrder = optimumOrder+1;
	
	var data = [];
	if(responseType==="CHEBYSHEV"){
		if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
			trace1Data = getChebyshevGraphPassData(filterData, filterType,optimumOrder);
			trace2Data = getChebyshevGraphPassData(filterData, filterType,preOptimumOrder);
			trace3Data = getChebyshevGraphPassData(filterData, filterType,nextOptimumOrder);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			trace1Data = getChebyshevGraphBandData(filterData, filterType,optimumOrder);
			trace2Data = getChebyshevGraphBandData(filterData, filterType,preOptimumOrder);
			trace3Data = getChebyshevGraphBandData(filterData, filterType,nextOptimumOrder);
		}
	}else{
		if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
			trace1Data = getMaximallyGraphPassData(filterData, filterType,optimumOrder);
			trace2Data = getMaximallyGraphPassData(filterData, filterType,preOptimumOrder);
			trace3Data = getMaximallyGraphPassData(filterData, filterType,nextOptimumOrder);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			trace1Data = getMaximallyGraphBandData(filterData, filterType,optimumOrder);
			trace2Data = getMaximallyGraphBandData(filterData, filterType,preOptimumOrder);
			trace3Data = getMaximallyGraphBandData(filterData, filterType,nextOptimumOrder);
		}
	}
	
	
	data.push({
		x: trace1Data[0],
		y: trace1Data[1],
		type: 'scatter',
		name: 'N'
	});
	
	data.push({
		x: trace2Data[0],
		y: trace2Data[1],
		type: 'scatter',
		name: 'N-1'
	});
	
	data.push({
		x: trace3Data[0],
		y: trace3Data[1],
		type: 'scatter',
		name: 'N+1'
	});
	
	$("b#modal-optimum-order").html(optimumOrder);
	$("b#modal-pre-optimum-order").html(preOptimumOrder);
	$("b#modal-next-optimum-order").html(nextOptimumOrder);
	
	return data;
}
/*Modal Detemine Filter Order Grid Fun End*/

