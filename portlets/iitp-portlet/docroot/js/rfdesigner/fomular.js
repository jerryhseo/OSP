/* Response Type - Chebyshev Flat Fun Start */
/* Main Graph Data*/
function getChebyshevGraphDataLH(filterData, filterType,orderNum){
	var returnData = [];
	
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var passbandRipple = parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	
	var x = [];
	var y = [];
	var tn = [];
	var gr = [];
	var xtrans= [];
	
	var farbitary = centerFrequency * 1;
	var z;
	if(filterType==="LOWPASS"){
		z = numeric.linspace(1, (stopFrequency) * 3 / 2, 10000);
	}else{
		z = numeric.linspace((stopFrequency) * 1 / 3, (centerFrequency) * 2, 10000);
	}
	
	for (var i = 0; i < 9999; i++) {
		if(filterType==="LOWPASS"){
			xtrans[i] = z[i] / farbitary;
		}else{
			xtrans[i] = farbitary / z[i];
		}
		
		tn[i] = math.cosh(orderNum * math.re(math.acosh(xtrans[i]))) * math.cos(orderNum * math.im(math.acosh(xtrans[i])));
		gr[i] = 1 + (math.pow(10, (passbandRipple / 10)) - 1) * math.pow(tn[i], 2);
		y[i] = (-10) * math.log10(gr[i]);
		x[i] = z[i];
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}

/* PopUp Graph Data*/
function getChebyshevGraphPassData(filterData, filterType,orderNum){
	var returnData = [];
	var x = [];
	var y = [];
	var tn = [];
	var gr = [];
	var xtrans= [];
	
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var passbandRipple = parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	
	var farbitary = centerFrequency * 1;
	var z;
	if(filterType==="LOWPASS"){
		z = numeric.linspace(1, (stopFrequency) * 3 / 2, 10000);
	}else{
		z = numeric.linspace((stopFrequency) * 1 / 3, (centerFrequency) * 2, 10000);
	}
	for (var i = 0; i < 9999; i++) {
		if(filterType==="LOWPASS"){
			xtrans[i] = z[i] / farbitary;
		}else{
			xtrans[i] = farbitary / z[i];
		}
		
		tn[i] = math.cosh(orderNum * math.re(math.acosh(xtrans[i]))) * math.cos(orderNum * math.im(math.acosh(xtrans[i])));
		gr[i] = 1 + (math.pow(10, (passbandRipple / 10)) - 1) * math.pow(tn[i], 2);
		y[i] = (10) * math.log10(gr[i]);
		x[i] = z[i];
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}

/* Main Graph Data*/
function getChebyshevGraphDataBAND(filterData, filterType,orderNum){
	var returnData = [];
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
	var z = numeric.linspace(w1out / 2, w2out + w1out / 2,1002);
	
	var xtrans = [];
	var x = [];
	var y = [];
	var k1 = [];
	var k2 = [];
	var tn = [];
	 var gr = [];
	 
	for (var i = 0; i < 1001; i++) {
		k1[i] = z[i] / woBPF;
		k2[i] = woBPF / z[i];
		xtrans[i] = filterType==="BANDPASS"?(1 / wdelta) * (k1[i] - k2[i]):wdelta * 1/(k1[i] - k2[i]);
		tn[i] = math.cosh(orderNum * math.re(math.acosh(xtrans[i]))) * math.cos(orderNum * math.im(math.acosh(xtrans[i])));
		gr[i] = 1 + (math.pow(10, (passbandRipple / 10)) - 1) * math.pow(tn[i], 2)
		x[i] = z[i];
		y[i] = (-10) * math.log10(gr[i]);
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}

/* PopUp Graph Data*/
function getChebyshevGraphBandData(filterData, filterType,orderNum){
	var returnData = [];
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
	var z = numeric.linspace(w1out / 2, w2out + w1out / 2,1002);
	
	var xtrans = [];
	var x = [];
	var y = [];
	var k1 = [];
	var k2 = [];
	var tn = [];
	 var gr = [];
	 
	for (var i = 0; i < 1001; i++) {
		k1[i] = z[i] / woBPF;
		k2[i] = woBPF / z[i];
		xtrans[i] = filterType==="BANDPASS"?(1 / wdelta) * (k1[i] - k2[i]):wdelta * 1/(k1[i] - k2[i]);
		tn[i] = math.cosh(orderNum * math.re(math.acosh(xtrans[i]))) * math.cos(orderNum * math.im(math.acosh(xtrans[i])));
		gr[i] = 1 + (math.pow(10, (passbandRipple / 10)) - 1) * math.pow(tn[i], 2)
		x[i] = z[i];
		y[i] = (10) * math.log10(gr[i]);
	}
	
	returnData.push(x);
	returnData.push(y);
	
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
		
		trace1GraphData = getChebyshevGraphDataLH(filterData, filterType,optimumOrder);
	}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
		var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
		var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
		var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
		var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
		
		var passbandRipple =  parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		optimumOrder = getChebyshevOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandRipple,stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		
		trace1GraphData = getChebyshevGraphDataBAND(filterData, filterType,optimumOrder);
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
			trace2GraphData = getChebyshevGraphDataLH(filterData, filterType,selectedOrder);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			trace2GraphData = getChebyshevGraphDataBAND(filterData, filterType,selectedOrder);
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

/* Get Chebyshev Order Number (Lowpass,Highpass)*/
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
	var denominator1 = math.acosh(math.sqrt((math.pow(10, A / 10) - 1) / (math.pow(10, Lar / 10) - 1)));
	var numerator1 = math.acosh(xtransmin);
	return math.ceil(denominator1 / numerator1);
}

/* Get Chebyshev Order Number (Bandpass,Bandstop)*/
function getChebyshevOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandRipple, stopbandAttenuation,filterType){
	if(filterType==="BANDPASS"){
		return getChebyshevOrderNumberBandPass(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandRipple, stopbandAttenuation);
	}else{
		return getChebyshevOrderNumberBandStop(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandRipple, stopbandAttenuation);
	}
}

function getChebyshevOrderNumberBandPass(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandRipple, stopbandAttenuation){
	 var wbp1 = passbandFreqL;
     var wbp2 = passbandFreqH;
     var ws1 = stopbandFreqL;
     var ws2 = stopbandFreqH;
     var wobpf = Math.sqrt(wbp1 * wbp2);
     var wdelta2 = (wbp2 - wbp1) / Math.sqrt(wbp1 * wbp2);
     var xtrans1 = (1 / wdelta2) * ((ws1 / wobpf) - (wobpf / ws1));
     var xtrans2 = (1 / wdelta2) * ((ws2 / wobpf) - (wobpf / ws2));
     var xtransmin = math.min(math.abs(xtrans1), math.abs(xtrans2));
     var Lar = passbandRipple;
     var A = stopbandAttenuation;
     var denominator1 = math.acosh(math.sqrt((math.pow(10, A / 10) - 1) / (math.pow(10, Lar / 10) - 1)));
     var numerator1 = math.acosh(xtransmin);
     return math.ceil(denominator1 / numerator1);
}

function getChebyshevOrderNumberBandStop(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandRipple, stopbandAttenuation){
	var OOptimum_Order = 1;
    do {
        var w1BSF = passbandFreqL;
        var w2BSF = passbandFreqH;
        var w1out = stopbandFreqL;
        var w2out = stopbandFreqH;
        var woBPF = math.sqrt(passbandFreqL * passbandFreqH);
        var wdelta = (passbandFreqH - passbandFreqL) / math.sqrt(passbandFreqL * passbandFreqH);
        var kk1 = stopbandFreqL / woBPF;
        var kk2 = woBPF / stopbandFreqL;
        var xxtrans = wdelta * 1 / (kk1 - kk2);
        var ttn = math.cosh(OOptimum_Order * math.re(math.acosh(xxtrans))) * math.cos(OOptimum_Order * math.im(math.acosh(xxtrans)));
        var ggr = 1 + (math.pow(10, (passbandRipple / 10)) - 1) * math.pow(ttn, 2)
        var yy = (10) * math.log10(ggr);
        OOptimum_Order++;
    } while (yy < stopbandAttenuation)

    var Optimum_Order1 = OOptimum_Order - 1;
	
    var OOOptimum_Order = 1;
    do {
        var w1BSF = passbandFreqL;
        var w2BSF = passbandFreqH;
        var w1out = stopbandFreqL;
        var w2out = stopbandFreqH;
        var woBPF = Math.sqrt(passbandFreqL * passbandFreqH);
        var wdelta = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqL * passbandFreqH);
        var kkk1 = stopbandFreqH / woBPF;
        var kkk2 = woBPF / stopbandFreqH;
        var xxxtrans = wdelta * 1 / (kkk1 - kkk2);
        var tttn = math.cosh(OOOptimum_Order * math.re(math.acosh(xxxtrans))) * math.cos(OOOptimum_Order * math.im(math.acosh(xxxtrans)));
        var gggr = 1 + (math.pow(10, (passbandRipple / 10)) - 1) * math.pow(tttn, 2)
        var yyy = (10) * math.log10(gggr);
        OOOptimum_Order++;
    } while (yyy < stopbandAttenuation)

    var Optimum_Order2 = OOOptimum_Order - 1;
    var Optimum_Order = math.max(Optimum_Order2, Optimum_Order1);
    
    return 	Optimum_Order;
	
	/*
	var wbs1 = passbandFreqL;
    var wbs2 = passbandFreqH;
    var wp1 = stopbandFreqL;
    var wp2 = stopbandFreqH;
    var stopatt = passbandRipple;
    var outatt = stopbandAttenuation;
    var wobpf = Math.sqrt(wbs1 * wbs2);
    var wdelta2 = (wbs2 - wbs1) / Math.sqrt(wbs1 * wbs2);
    var xtrans1 = wdelta2 * 1 / ((wp1 / wobpf) - (wobpf / wp1));
    var xtrans2 = wdelta2 * 1 / ((wp2 / wobpf) - (wobpf / wp2));
    var xtransmin = math.min(math.abs(xtrans1), math.abs(xtrans2));
    var Lar = passbandRipple;
    var A = stopbandAttenuation;
    var denominator1 = math.acosh(math.sqrt((math.pow(10, A / 10) - 1) / (math.pow(10, Lar / 10) - 1)));
    var numerator1 = math.acosh(xtransmin);
    var a = math.re(numerator1);
    var b = math.im(numerator1);
    return math.ceil(denominator1 * math.sqrt(a + b) / (math.pow(a, 2) + math.pow(b, 2)));
    */ 
}

/* Response Type - Chebyshev Flat Fun End */


/* Response Type - Maximally Flat Fun Start */
/* Main Graph Data*/
function getMaximallyGraphDataLH(filterData, filterType,orderNum){
	var returnData = [];
	
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
	
	var farbitary = centerFrequency * 1;
	var z = filterType==="LOWPASS"?numeric.linspace(1, (stopFrequency) * 3 / 2, 10000):numeric.linspace((stopFrequency) * 1 / 3, (centerFrequency) * 2, 10000);
	var xtrans = [];
	var gr = [];
	var x = [];
	var y = [];
	for (var i = 0; i < 9999; i++) {
		xtrans[i] = filterType==="LOWPASS"?z[i]/farbitary:farbitary/z[i];
		gr[i] = 1 + math.pow(xtrans[i], 2 * orderNum);
		x[i] = z[i];
		y[i] = (-10) * math.log10(gr[i]);
	}
	
	returnData.push(x);
	returnData.push(y);
	
	
	
	return returnData;
}

/* PopUp Graph Data*/
function getMaximallyGraphPassData(filterData, filterType,orderNum){
	var returnData = [];
	
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
	
	var farbitary = centerFrequency * 1;
	var z = filterType==="LOWPASS"?numeric.linspace(1, (stopFrequency) * 3 / 2, 10000):numeric.linspace((stopFrequency) * 1 / 3, (centerFrequency) * 2, 10000);
	var xtrans = [];
	var gr = [];
	var x = [];
	var y = [];
	for (var i = 0; i < 9999; i++) {
		xtrans[i] = filterType==="LOWPASS"?z[i]/farbitary:farbitary/z[i];
		gr[i] = 1 + math.pow(xtrans[i], 2 * orderNum);
		x[i] = z[i];
		y[i] = (10) * math.log10(gr[i]);
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}
	
/* Main Graph Data*/
function getMaximallyGraphDataBAND(filterData, filterType,orderNum){
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
	 
	var z = numeric.linspace(w1out / 2, w2out + w1out / 2, 1002)
	
	var returnData = [];
	
	var x = [];
	var y = [];
	var xtrans = [];
	var k1 = [];
	var k2 = [];
	
	for (var i = 0; i < 1001; i++) {
		k1[i] = z[i] / woBPF;
		k2[i] = woBPF / z[i];
		xtrans[i] = filterType==="BANDPASS"?(1 / wdelta) * (k1[i] - k2[i]):wdelta * 1/(k1[i] - k2[i]);
		x[i] = filterType==="BANDPASS"?z[i]:(z[i]);
		y[i] = (-10) * Math.log10(1 + Math.pow(xtrans[i], 2 * orderNum));
	}
	
	returnData.push(x);
	returnData.push(y);
	
	return returnData;
}

/* PopUp Graph Data*/
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
	 
	var z = numeric.linspace(w1out / 2, w2out + w1out / 2, 1002)
	
	var returnData = [];
	
	var x = [];
	var y = [];
	var xtrans = [];
	var k1 = [];
	var k2 = [];
	
	for (var i = 0; i < 1001; i++) {
		k1[i] = z[i] / woBPF;
		k2[i] = woBPF / z[i];
		xtrans[i] = filterType==="BANDPASS"?(1 / wdelta) * (k1[i] - k2[i]):wdelta * 1/(k1[i] - k2[i]);
		x[i] = filterType==="BANDPASS"?z[i]:(z[i]);
		y[i] = (10) * Math.log10(1 + Math.pow(xtrans[i], 2 * orderNum));
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
		
		trace1GraphData = getMaximallyGraphDataLH(filterData, filterType,optimumOrder);
	}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
		var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
		var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
		var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
		var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
		var passbandAttenuation =  parseFloat(filterData[DESIGNER.Constants.SPEC_PA]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		var optimumOrder = getMaximallyOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandAttenuation,stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		trace1GraphData = getMaximallyGraphDataBAND(filterData, filterType,optimumOrder);
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
			trace2GraphData = getMaximallyGraphDataLH(filterData, filterType,selectedOrder);
		}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
			trace2GraphData = getMaximallyGraphDataBAND(filterData, filterType,selectedOrder);
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

/* Get Maximally Order Number (Lowpass,Highpass)*/
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

/* Get Maximally Order Number (Bandpass,Bandstop)*/
function getMaximallyOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandAttenuation, stopbandAttenuation,filterType){
	if(filterType==="BANDPASS"){
		return getMaximallyOrderNumberBandPass(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandAttenuation, stopbandAttenuation);
	}else{
		return getMaximallyOrderNumberBandStop(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandAttenuation, stopbandAttenuation);
	}
}

function getMaximallyOrderNumberBandStop(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandAttenuation, stopbandAttenuation){
	var OOptimum_Order = 1;
    do {
        var w1BSF = passbandFreqL;
        var w2BSF = passbandFreqH;
        var w1out = stopbandFreqL;
        var w2out = stopbandFreqH;
        var woBPF = math.sqrt(passbandFreqL * passbandFreqH);
        var wdelta = (passbandFreqH - passbandFreqL) / math.sqrt(passbandFreqL * passbandFreqH);
        var kk1 = stopbandFreqL / woBPF;
        var kk2 = woBPF / stopbandFreqL;
        var xxtrans = wdelta * 1 / (kk1 - kk2);
        var yy = (10) * math.log10(1 + math.pow(xxtrans, 2 * OOptimum_Order));
        OOptimum_Order++;
    } while (yy < stopbandAttenuation)

    var Optimum_Order1 = OOptimum_Order - 1;
    
    var OOOptimum_Order = 1;
    do {
        var w1BSF = passbandFreqL;
        var w2BSF = passbandFreqH;
        var w1out = stopbandFreqL;
        var w2out = stopbandFreqH;
        var woBPF = Math.sqrt(passbandFreqL * passbandFreqH);
        var wdelta = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqL * passbandFreqH);
        var kkk1 = stopbandFreqH / woBPF;
        var kkk2 = woBPF / stopbandFreqH;
        var xxxtrans = wdelta * 1 / (kkk1 - kkk2);
        var yyy = (10) * math.log10(1 + math.pow(xxxtrans, 2 * OOOptimum_Order));
        OOOptimum_Order++;
    } while (yyy < stopbandAttenuation)

    var Optimum_Order2 = OOOptimum_Order - 1;
    var Optimum_Order = math.max(Optimum_Order2, Optimum_Order1);
	
    return Optimum_Order;
	/*
	var wbp1 = passbandFreqL;
	var wbp2 = passbandFreqH;
	var ws1 = stopbandFreqL;
	var ws2 = stopbandFreqH;
	var passatt = passbandAttenuation;
	var outatt = stopbandAttenuation;
	var wobpf = Math.sqrt(wbp1 * wbp2);
	var wdelta2 = (wbp2 - wbp1) / Math.sqrt(wbp1 * wbp2);
	var xtrans1 = (1 / wdelta2) * ((ws1 / wobpf) - (wobpf / ws1));
	var xtrans2 = (1 / wdelta2) * ((ws2 / wobpf) - (wobpf / ws2));
	var xtransmax = Math.max(xtrans1, xtrans2)
	var order1 = Math.ceil(Math.log10(Math.pow(10, (passatt / 10)) - 1) / (2 * Math.log10(xtransmax)));
	var order2 = Math.ceil(Math.log10(Math.pow(10, (outatt / 10)) - 1) / (2 * Math.log10(xtransmax)));
	return Math.max(order1, order2);
	*/
}

function getMaximallyOrderNumberBandPass(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandAttenuation, stopbandAttenuation){
	var wbs1 = stopbandFreqL;
	var wbs2 = stopbandFreqH;
	var wp1 = passbandFreqL;
	var wp2 = passbandFreqH;
	var stopatt = passbandAttenuation;
	var outatt = stopbandAttenuation;
	var wobpf = Math.sqrt(wbs1 * wbs2);
	var wdelta2 = (wbs2 - wbs1) / Math.sqrt(wbs1 * wbs2);
	var xtrans1 = wdelta2 * 1/((wp1 / wobpf) - (wobpf / wp1));
	var xtrans2 = wdelta2 * 1/((wp2 / wobpf) - (wobpf / wp2));
	var xtransmax = Math.max(xtrans1, xtrans2)
	var order1 = Math.ceil(Math.log10(Math.pow(10, (stopatt / 10)) - 1) / (2 * Math.log10(xtransmax)));
	var order2 = Math.ceil(Math.log10(Math.pow(10, (outatt / 10)) - 1) / (2 * Math.log10(xtransmax)));
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
	
	//Frequency impedance scaling 수식 동일 - 20180718
	return getFrequencyImpedanceScaling(filterData,filterType,optimumOrder,characteristicImpedance,gtable);
}

/*Filter Design lowpass Transmission Line Filter Func Start*/
function getSteppedImpedanceGtableVar(responseType,passbandRipple){
	var gtable;
	if(responseType==="MAXIMALLY"){
		gtable=[[0,0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [0,2,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [0,1.4142,	1.4142,	1,	0,	0,	0,	0,	0,	0,	0,	0],
            [0, 1,	2,	1,	1,	0,	0,	0,	0,	0,	0,	0],
            [0, 0.7654,	1.8478,	1.8478,	0.7654,	1,	0,	0,	0,	0,	0,	0],
            [0, 0.618,	1.618,	2,	1.618,	0.618,	1,	0,	0,	0,	0,	0],
            [0, 0.5176,	1.4142,	1.9318,	1.9318,	1.4142,	0.5176,	1,	0,	0,	0,	0],
            [0, 0.445,	1.247,	1.8019,	2,	1.8019,	1.247,	0.445,	1,	0,	0,	0],
            [0, 0.3902,	1.1111,	1.6629,	1.9615,	1.9615,	1.6629,	1.1111,	0.3902,	1,	0,	0],
            [0, 0.3473,	1,	1.5321,	1.8794,	2,	1.8794,	1.5321,	1,	0.3473,	1,	0],
            [0, 0.3129,	0.908,	1.4142,	1.782,	1.9754,	1.9754,	1.782,	1.4142, 0.908,	0.3129,	1]];
	}else if(responseType==="CHEBYSHEV"&&passbandRipple===3){
		gtable=[[0,0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [0,1.9953,	1,	0,	0	,0	,0	,0	,0	,0	,0	,0],
            [0, 3.1013,	0.5339,	5.8095,	0,	0,	0,	0,	0,	0,	0,0],
            [0, 3.3487	,0.7117,	3.3487,	1	,0	,0	,0	,0	,0	,0	,0],
            [0, 3.4289	,0.7483	,4.3471	,0.592,	5.8095	,0,	0,	0,	0,	0,	0],
            [0, 3.4817	,0.7618	,4.5381	,0.7618	,3.4817	,1,	0,	0	,0	,0	,0],
            [0, 3.5045	,0.7685	,4.6061	,0.7929	,4.4641	,0.6033,	5.8095,	0,	0,	0,	0],
            [0, 3.5182	,0.7723	,4.6386	,0.8039	,4.6386	,0.7723	,3.5182,	1	,0	,0	,0],
            [0, 3.5277	,0.7745	,4.6575	,0.8089	,4.699	,0.8018	,4.499,	0.6073,	5.8095	,0,	0],
            [0, 3.534	,0.776	,4.6692	,0.8118	,4.7272	,0.8118	,4.6692,	0.776	,3.534	,1,	0],
            [0, 3.5384	,0.7771,	4.6768,	0.8136,	4.7425	,0.8164,	4.726	,0.8051,	4.5142	,0.6091,	5.8095 ]];
	}else if(responseType==="CHEBYSHEV"&&passbandRipple===0.5){
		gtable=[[0,0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [0,0.6986,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0],
            [0,1.4029,	0.7071,	1.9841,	0,	0,	0,	0,	0,	0,	0,	0],
            [0,1.5963,	1.0967,	1.5963,	1,	0,	0,	0,	0,	0,	0,	0],
            [0,1.6703,	1.1926,	2.3661,	0.8419,	1.9841,	0,	0,	0,	0,	0,	0],
            [0,1.7058,	1.2296,	2.5408,	1.2296,	1.7058,	1,	0,	0,	0,	0,	0],
            [0,1.7254,	1.2479,	2.6064,	1.3137,	2.4758,	0.8696,	1.9841,	0,	0,	0,	0],
            [0,1.7372,	1.2583,	2.6381,	1.3444,	2.6381,	1.2583,	1.7372,	1,	0,	0,	0],
            [0,1.7451,	1.2647,	2.6564,	1.359,	2.6964,	1.3389,	2.5093,	0.8796,	1.9841,	0,	0],
            [0,1.7504,	1.269,	2.6678,	1.3673,	2.7239,	1.3673,	2.6678,	1.269,	1.7504,	1,	0],
            [0,1.7543,	1.2721,	2.6754,	1.3725,	2.7392,	1.3806,	2.7231,	1.3485,	2.5239,	0.8842,	1.9841]];
	}
	return gtable;
}

function getFilterDesignSteppedImpedanceData(filterData, responseType, filterType , characteristicImpedance, dielectricConstant, highest,lowestImpedance,heightImpedance){
	
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]*filterData[DESIGNER.Constants.SPEC_SF_ADD]);
	var passbandRipple = parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);

	
	var substrateH = parseFloat(highest);
	var er = parseFloat(dielectricConstant);
	var Zh = parseFloat(heightImpedance);
	var Zl = parseFloat(lowestImpedance);
	
	var Zo = parseFloat(characteristicImpedance);
	
	
	var gtable = getSteppedImpedanceGtableVar(responseType, passbandRipple);
	
	var optimumOrder = 0;
	if(responseType==="MAXIMALLY"){
		optimumOrder = getMaximallyOrderNumberLH(centerFrequency, stopFrequency, stopbandAttenuation,filterType);
	}else{
		optimumOrder = getChebyshevOrderNumberLH(centerFrequency, stopFrequency, passbandRipple, stopbandAttenuation,filterType);
	}
	
	 var inductor_bl = [];
     var capacitor_bl = [];
 
     
     for (var i=0; i < optimumOrder; i++){
         if (i % 2 == 0)
         {
             inductor_bl[i] = ((gtable[optimumOrder][i+1])*Zo/Zh)*180/math.pi;
             capacitor_bl[i]=0;
         }
         else
         {
             capacitor_bl[i]=((gtable[optimumOrder][i+1])*Zl/Zo)*180/math.pi;
             inductor_bl[i]=0;
         }
     }
     
     var l = [];
     var bl = [];
     var bl_r = [];

     for (var i=0; i<optimumOrder; i++){
         if (i % 2 == 0)
         {
             bl[i] = inductor_bl[i];
            // l[i] = 1000*bl[i] / 360 * ((3 * math.pow(10, 8) / (Center_Frequency)) / math.sqrt(er));
         }
         else
         {
             bl[i] = capacitor_bl[i];
             //l[i] = 1000*bl[i] / 360 * ((3 * math.pow(10, 8) / (Center_Frequency)) / math.sqrt(er));
         }
         
         
     }
     

     for (var i = 0; i < optimumOrder; i++)
     {
         bl_r[i] = bl[optimumOrder-i-1]
         l[i] = 1000 * bl_r[i] / 360 * ((3 * math.pow(10, 8) / (centerFrequency)) / math.sqrt(er));
     }
     
         
     var A=Zh/60*math.sqrt((er+1)/2)+(er-1)/(er+1)*(0.23+0.11/er);
     var w2_1=8*math.exp(A)/(math.exp(2*A)-2);
     var B=377*math.pi/2/Zl/math.sqrt(er);
         

     var w1_1=2/math.pi*(B-1-math.log(2*B-1)+(er-1)/2/er*(math.log(B-1)+0.39-0.61/er));
     var w1=w1_1*substrateH;
     var w2=w2_1*substrateH;


     var w = [];
     var z = [];

     for (var i = 0; i < optimumOrder; i++) {
         if (i % 2 == 0) {
             w[i] = w1;
             z[i] = Zl;
         }
         else {
             w[i] = w2;
             z[i] = Zh;
         }

    }
     
     
     var returnObject = {
 		"optimumOrder":optimumOrder,
 		"z":z,
 		"phase":bl_r,
 		"width":w,
 		"length":l
 	};
 
	return returnObject;
}

function getFilterDesignSteppedImpedanceGrid(object,tbody1){
	var optimumOrder = object.optimumOrder;
	var z = object.z;
	var phase = object.phase;
	var width = object.width;
	var length = object.length;
	tbody1.empty();
	
	for (var i = 0; i < optimumOrder; i++){
		var $tr = $("<tr/>");
		$("<td/>").addClass("col-md-3 text-center").html(z[i]).appendTo($tr);
		$("<td/>").addClass("col-md-3 text-center").html(phase[i]).appendTo($tr);
		$("<td/>").addClass("col-md-3 text-center").html(width[i]).appendTo($tr);
		$("<td/>").addClass("col-md-3 text-center").html(length[i]).appendTo($tr);
		$tr.appendTo(tbody1);
	}
}


/*Filter Design lowpass Transmission Line Filter Func End*/

/*Filter Design BandPass Transmission Line Filter Func Start*/
function getFilterDesignEndCoupeldData(filterData, responseType, filterType , characteristicImpedance, dielectricConstant, height){
	
	var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
	var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
	var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]*filterData[DESIGNER.Constants.SPEC_SFL_ADD]);
	var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]*filterData[DESIGNER.Constants.SPEC_SFH_ADD]);
	
	var passbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_PA])
	var passbandRipple =  parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
	
	var Width = getFilterDesignEndCoupeldOrderN(characteristicImpedance, dielectricConstant, height);
	
	var gtable = getGtableVar(responseType, passbandRipple);
	
	var optimumOrder = 0;
	if(responseType==="MAXIMALLY"){
		optimumOrder = getMaximallyOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandAttenuation,stopbandAttenuation,filterType);
	}else{
		optimumOrder = getChebyshevOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandRipple,stopbandAttenuation,filterType);
	}
	
	var j = [];
	var wdelta = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqL * passbandFreqH);
	
	for (var i = 0; i < optimumOrder+1; i++){
        if (i == 0){
            j[i] = math.sqrt(math.pi / 2 * wdelta / (gtable[optimumOrder][i] * gtable[optimumOrder][i+1]))
        }else if (i >= 1 && i < optimumOrder ){
            j[i] = math.pi*wdelta/2/math.sqrt(gtable[optimumOrder][i] * gtable[optimumOrder][i+1])
        }else if (i == optimumOrder){
            j[i] = math.sqrt(math.pi / 2 * wdelta / (gtable[optimumOrder][i] * gtable[optimumOrder][i+1]))
        }
    }
	
	var b = [];
    var bp = [];
    var theta = [];
    var c_g = [];
    var c_p = [];
    var spacing = [];
    var deltaL1 = [];
    var deltaL2 = [];
    var lamda_g = 1000*(3 * math.pow(10,8) / Math.sqrt(passbandFreqL * passbandFreqH))/ math.sqrt(dielectricConstant);
    var l = [];
    var L = [];
	
    for (var i =0; i<optimumOrder+1; i++)
    {
        b[i] = j[i] / (1 - math.pow(j[i], 2));
        c_g[i] = 1/50 * b[i] / (2*math.pi*math.sqrt(passbandFreqL * passbandFreqH));
        spacing[i] = math.acoth(math.exp(b[i] * lamda_g / height)) * 2 * height / math.pi;
        bp[i] = -2 * height / lamda_g * math.log(math.cosh(math.pi * spacing[i] / height));
        c_p[i] = 1 / 50 * bp[i] / (2 * math.pi * math.sqrt(passbandFreqL * passbandFreqH));
    }

    for (var i = 1; i < optimumOrder + 1; i++)
    {
        deltaL1[i] = Math.sqrt(passbandFreqL * passbandFreqH) * c_p[i - 1] * lamda_g;
        deltaL2[i] = Math.sqrt(passbandFreqL * passbandFreqH) * c_p[i] * lamda_g;
    }
     
    for (var i = 1; i < optimumOrder + 1; i++)
    {
        theta[i] = math.pi - 1 / 2 * (math.atan(2 * b[i - 1]) + math.atan(2 * b[i]));
        l[i] = lamda_g/(2*math.pi)*theta[i]-deltaL1[i]-deltaL2[i];
    }
     
    
    for (var i = 0; i < optimumOrder; i++)
    {
        L[i] = l[i+1];
    }

    var W_r = [];

    for (var i = 0; i < optimumOrder; i++) {

        W_r[i]  = Width;
    }
    
    var returnObject = {
    		"optimumOrder":optimumOrder,
    		"width":W_r,
    		"lenth":L,
    		"spacing":spacing
    	};
    
   	return returnObject;
}


function getFilterDesignEndCoupeldOrderN(characteristicImpedance,dielectricConstant,height){
	var Zo = characteristicImpedance;
	var er = dielectricConstant;
	var substrateH = height;
	
	var A = Zo / 60 * math.sqrt((er + 1) / 2) + (er - 1) / (er + 1) * (0.23 + 0.11 / er);
    var B = 377 * math.pi / 2 / Zo / math.sqrt(er);

    var Wh_slim = 8 * math.exp(A) / (math.exp(2 * A) - 2);
    var Wh_wide = 2 / math.pi * (B - 1 - math.log(2 * B - 1) + (er - 1) / 2 / er * (math.log(B - 1) + 0.39 - 0.61 / er));

    if (Wh_slim <= 2 && Wh_wide <= 2) {
        var W = Wh_slim * substrateH;

    } else if (Wh_slim >= 2 && Wh_wide >= 2) {
        var W = Wh_wide * substrateH;

    } else {
        var W = 0; // it means errors
    }

    return W;
}


/*Filter Design BandPass Transmission Line Filter Func End*/
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

/*Modal Prototype Grid Fun Start*/
function getProtoTypeData(filterData, responseType, filterType, optimumOrder, characteristicImpedance){
	var passbandRipple = parseFloat(filterData[DESIGNER.Constants.SPEC_PR]);
	
	var gtable = getGtableVar(responseType, passbandRipple);
	
	var returnObject = {
		"protoType":getProtoTypeScaling(filterType,optimumOrder,gtable),
		"frequencyScaling":getFrequencyScaling(filterData,filterType,optimumOrder,gtable),
		"frequencyImpedance":getFrequencyImpedanceScaling(filterData,filterType,optimumOrder,characteristicImpedance,gtable)
	};
		
	return returnObject;
}

function getFrequencyScaling(filterData,filterType,optimumOrder,gtable){
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	
	var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
	var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
	
	var wobpf_f = Math.sqrt(passbandFreqH * passbandFreqL);
	var wdelta_f = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqH * passbandFreqL);
	
	var inductor = [];
	var capacitor = [];
	
	for (var i = 0; i < optimumOrder; i++){
		if(filterType==="LOWPASS"){
			if(i % 2 ==0){
				capacitor[i] = math.pow(10,12)*((gtable[optimumOrder][i + 1])  / (2 * math.pi * centerFrequency));
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * ((gtable[optimumOrder][i + 1])  / (2 * math.pi * centerFrequency));
				capacitor[i] = 0;
			}
		}else if(filterType==="HIGHPASS"){
			if(i % 2 ==0){
				capacitor[i] = math.pow(10,12)*1 / ((gtable[optimumOrder][i + 1])  * (2 * math.pi * centerFrequency));
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) / ((gtable[optimumOrder][i + 1]) * (2 * math.pi * centerFrequency));
				capacitor[i] = 0;
			}
		}else if(filterType==="BANDPASS"){
			if(i % 2 ==0){
				inductor[i] = math.pow(10, 9) * wdelta_f / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f));
				capacitor[i] = math.pow(10, 12) * (gtable[optimumOrder][i + 1]) / (2 * math.pi * wobpf_f * wdelta_f);
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * (gtable[optimumOrder][i + 1]) / (2 * math.pi * wobpf_f * wdelta_f) ;
				capacitor[i] = math.pow(10, 12) * wdelta_f / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f));
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

function getFrequencyImpedanceScaling(filterData,filterType,optimumOrder,characteristicImpedance,gtable){
	var Zo = parseFloat(characteristicImpedance);
	
	var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]*filterData[DESIGNER.Constants.SPEC_CF_ADD]);
	var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]*filterData[DESIGNER.Constants.SPEC_PFL_ADD]);
	var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]*filterData[DESIGNER.Constants.SPEC_PFH_ADD]);
	
	var wobpf_f = Math.sqrt(passbandFreqH * passbandFreqL);
	var wdelta_f = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqH * passbandFreqL);
	
	var inductor = [];
	var capacitor = [];
	
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
				capacitor[i] = math.pow(10,12)* 1 / ((gtable[optimumOrder][i + 1]) * Zo * (2 * math.pi * centerFrequency));
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * Zo / ((gtable[optimumOrder][i + 1]) * (2 * math.pi * centerFrequency));
				capacitor[i] = 0;
			}
		}else if(filterType==="BANDPASS"){
			if(i % 2 ==0){
				inductor[i] = math.pow(10, 9) * wdelta_f * Zo / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f));
				capacitor[i] = math.pow(10, 12) * (gtable[optimumOrder][i + 1]) / (2 * math.pi * wobpf_f * wdelta_f) / Zo;
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * (gtable[optimumOrder][i + 1]) / (2 * math.pi * wobpf_f * wdelta_f) * Zo;
				capacitor[i] = math.pow(10, 12) * wdelta_f / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f) * Zo);
			}
		}else if(filterType==="BANDSTOP"){
			if(i % 2 ==0){
				inductor[i] = math.pow(10, 9) * Zo / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f * wdelta_f));
				capacitor[i] = math.pow(10, 12) * wdelta_f * gtable[optimumOrder][i + 1] / (2 * math.pi * wobpf_f * Zo);
			}else if(i % 2 == 1){
				inductor[i] = math.pow(10, 9) * (gtable[optimumOrder][i + 1]) * wdelta_f * Zo / (2 * math.pi * wobpf_f);
                capacitor[i] = math.pow(10, 12) * 1 / (gtable[optimumOrder][i + 1] * (2 * math.pi * wobpf_f * wdelta_f) * Zo);
			}
		}
	}
	
	var returnObject = {
		"optimumOrder":optimumOrder,
		"capacitor":capacitor,
		"inductor":inductor
		};
		
	console.log(returnObject);
	return returnObject;
}

function getProtoTypeScaling(filterType,optimumOrder,gtable){
	var inductor = [];
	var capacitor = [];
	
	for (var i = 0; i < optimumOrder; i++){
		if(filterType==="LOWPASS"){
			if(i % 2 ==0){
				capacitor[i] = gtable[optimumOrder][i + 1];
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = gtable[optimumOrder][i + 1] ;
				capacitor[i] = 0;
			}
		}else if(filterType==="HIGHPASS"){
			if(i % 2 ==0){
				capacitor[i] = gtable[optimumOrder][i + 1];
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = gtable[optimumOrder][i + 1];
				capacitor[i] = 0;
			}
		}else if(filterType==="BANDPASS"){
			if(i % 2 ==0){
				capacitor[i] = gtable[optimumOrder][i + 1];
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = gtable[optimumOrder][i + 1];
				capacitor[i] = 0;
			}
		}else if(filterType==="BANDSTOP"){
			if(i % 2 ==0){
				capacitor[i] = gtable[optimumOrder][i + 1];
				inductor[i] = 0;
			}else if(i % 2 == 1){
				inductor[i] = gtable[optimumOrder][i + 1];
				capacitor[i] = 0;
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
/*Modal Prototype Grid Fun End*/



/* Filter Design Table Grid */
function filterDesignTableGrid(object,tbody){
	var optimumOrder = object.optimumOrder;
	var capacitor = object.capacitor;
	var inductor = object.inductor;
	tbody.empty();
	
	for (var i = 0; i < optimumOrder; i++){
		var $tr = $("<tr/>");
		var num = i+1;
		var capacitorVal = DESIGNER.Constants.getNullToZero(capacitor[i],4);
		var inductorVal = DESIGNER.Constants.getNullToZero(inductor[i],4);
		
		$("<td/>").addClass("col-md-2 text-center").html(num).appendTo($tr);
		$("<td/>").addClass("col-md-5 text-center").html(capacitorVal).appendTo($tr);
		$("<td/>").addClass("col-md-5 text-center").html(inductorVal).appendTo($tr);
		$tr.appendTo(tbody);
	}
}

function getFilterDesignEndCoupeldGrid(object,tbody){
	var optimumOrder = object.optimumOrder;
	var width = object.width;
	var spacing = object.spacing;
	var lenth = object.lenth;
	tbody.empty();

	for (var i = 0; i <= optimumOrder; i++){
		var $tr = $("<tr/>");
		$("<td/>").addClass("col-md-4 text-center").html(DESIGNER.Constants.getNullToZero(width[i])).appendTo($tr);
		$("<td/>").addClass("col-md-4 text-center").html(spacing[i]).appendTo($tr);
		$("<td/>").addClass("col-md-4 text-center").html(DESIGNER.Constants.getNullToZero(lenth[i])).appendTo($tr);
		$tr.appendTo(tbody);
	}
}