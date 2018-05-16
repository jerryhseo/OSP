function getGraphData(filterData,filterType,selectedOrder){
	var x = [];
	var y = [];
	var y2 = [];
	
	if(filterType==="LOWPASS"||filterType==="HIGHPASS"){
		var centerFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_CF]);
		var stopFrequency = parseFloat(filterData[DESIGNER.Constants.SPEC_SF]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		var optimumOrder = getOrderNumberLH(centerFrequency, stopFrequency, stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		
		var xtrans = [];
		
		var firstVar = filterType==="LOWPASS"?centerFrequency:stopFrequency;
		var secondVar = filterType==="LOWPASS"?stopFrequency:centerFrequency;
		for (var i = 1; i < 1000; i++) {
			x[i] = math.log10(i * firstVar / (secondVar * 10));
			xtrans[i] = filterType==="LOWPASS"?(i * centerFrequency) / (stopFrequency * 10):1/((i * stopFrequency) / (centerFrequency * 10));
			y[i] = (-10) * math.log10(1 + Math.pow(xtrans[i], 2 * optimumOrder));
			y2[i] = (-10) * math.log10(1 + Math.pow(xtrans[i], 2 * selectedOrder));
		}
	}else if(filterType==="BANDPASS"||filterType==="BANDSTOP"){
		var passbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_PFL]);
		var passbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_PFH]);
		var stopbandFreqL = parseFloat(filterData[DESIGNER.Constants.SPEC_SFL]);
		var stopbandFreqH = parseFloat(filterData[DESIGNER.Constants.SPEC_SFH]);
		var passbandAttenuation =  parseFloat(filterData[DESIGNER.Constants.SPEC_PA]);
		var stopbandAttenuation = parseFloat(filterData[DESIGNER.Constants.SPEC_SA]);
		
		var optimumOrder = getOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL,stopbandFreqH,passbandAttenuation,stopbandAttenuation,filterType);
		$("p#optimum-order").html(optimumOrder);
		
		
		var w1BPF = passbandFreqL;
		var w2BPF = passbandFreqH;
		var w1out = stopbandFreqL;
		var w2out = stopbandFreqH;
		var woBPF = Math.sqrt(passbandFreqL * passbandFreqH);
		var wdelta = (passbandFreqH - passbandFreqL) / Math.sqrt(passbandFreqL * passbandFreqH);
		 
		var z = numeric.linspace(Math.pow(10, math.log10(woBPF) - 1.5 * math.log10(wdelta)), Math.pow(10, math.log10(woBPF) + 1.5 * math.log10(wdelta)), 10000);
		
		var xtrans = [];
		var k1 = [];
		var k2 = [];
		
		for (var i = 0; i < 9999; i++) {
			k1[i] = z[i] / woBPF
			k2[i] = woBPF / z[i]
			xtrans[i] = filterType==="BANDPASS"?(1 / wdelta) * (k1[i] - k2[i]):1/wdelta * 1 / (k1[i] - k2[i]);
			y[i] = (-10) * math.log10(1 + Math.pow(xtrans[i], 2 * optimumOrder));
			y2[i] = (-10) * math.log10(1 + Math.pow(xtrans[i], 2 * selectedOrder));
			x[i] = math.log10(z[i])
		}
	}
	
	var data = [];
	var trace1 = {
		x: x,
		y: y,
		type: 'scatter',
		name: 'Optimum Order'
	};
	data.push(trace1);
	
	var trace2 = {
		x: x,
		y: y2,
		type: 'scatter',
		name: 'Selected Order'
	};
	if(selectedOrder!=0){
		data.push(trace2);
	}
	console.log(data);
	return data;
}

function getOrderNumberLH(centerFrequency, stopFrequency, stopbandAttenuation,filterType){
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

function getOrderNumberBand(passbandFreqL, passbandFreqH, stopbandFreqL, stopbandFreqH, passbandAttenuation, stopbandAttenuation,filterType){
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
	return Math.max(order1, order2)
}