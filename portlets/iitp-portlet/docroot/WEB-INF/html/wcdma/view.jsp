<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link rel="stylesheet" href="${contextPath}/css/toastr.min.css">
<link rel="stylesheet" href="${contextPath}/css/jquery-confirm/jquery-confirm.min.css">

<style type="text/css">
	
</style>
<div class="h20"></div>
<div class="row">
	<div class="col-md-8">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">System Block Diagram</h2>
			</div>
			<div class="panel-body">
				<img src="${contextPath}/images/wcdma/WCDMA_BlockDiagram6.png" class="img-responsive">
				<h5>
					<p class="icon-arrow-right"></p><u>Simulation Parameter</u>
				</h5>
					
				<div class="form-group col-md-2">
					<label>SF(4~256)</label>
					<input class="form-control" type="text" value="4"/>
				</div>
				<div class="form-group col-md-2">
					<label>Samples / Chip</label>
					<input class="form-control" type="text" />
				</div>
				<div class="form-group col-md-4">
					<label>Pulse Shaping Filter</label>
					<select class="form-control">
						<option value="RAISED-COSINE">Raised Cosine</option>
						<option value="GAUSSIAN">Gaussian</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<label>Filter Taps</label>
					<input class="form-control" type="text" />
				</div>
				<div class="form-group col-md-2">
					<label>Roll-off Factor</label>
					<input class="form-control" type="text" />
				</div>
			</div>
			<div class="panel-footer">
				<div class="btn-group">
					<button class="btn btn-primary">
						<span class="icon-bar-chart">
							RF Design Parameter
						</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Constellation</h2>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-8">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">RF Design Parameter</h2>
			</div>
			<div class="panel-body">
				<table class="table table-responsive table-bordered" id="<portlet:namespace/>table-rf-designer-parameter">
					<thead>
						<tr>
							<th>
								<input class="form-control" type="text" value="Mixer" id="tx-mixer" disabled/>
							</th>
							<th>
								<select class="form-control" id="tx-block-1">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-2">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-3">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-4">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-5">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<input class="form-control" type="text" value="P/L" id="channel-pl" disabled/>
							</th>
							<th>
								<input class="form-control" type="text" value="AWGN" id="channel-awgn" disabled/>
							</th>
							<th>
								<select class="form-control" id="rx-block-1">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="rx-block-2">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="rx-block-3">
									<option value="NONE">None</option>
									<option value="AMPLIFIER">Amplifier</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<input class="form-control" type="text" value="Mixer" id="rx-mixer" disabled/>
							</th>
							<th>
								<input class="form-control" type="text" value="LPF" id="rx-lpf" disabled/>
							</th>
							<th>
								<input class="form-control" type="text" value="BBA" id="rx-bba" disabled/>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><img src="${contextPath}/images/wcdma/mixer.png" class="img-responsive" id="<portlet:namespace/>image-tx-mixer"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-tx-block-1"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-tx-block-2"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-tx-block-3"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-tx-block-4"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-tx-block-5"></td>
							<td><img src="${contextPath}/images/wcdma/PL.png" class="img-responsive"    id="<portlet:namespace/>image-channel-pl"></td>
							<td><img src="${contextPath}/images/wcdma/AWGN.png" class="img-responsive"  id="<portlet:namespace/>image-channel-awgn"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-rx-block-1"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-rx-block-2"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-rx-block-3"></td>
							<td><img src="${contextPath}/images/wcdma/mixer.png" class="img-responsive" id="<portlet:namespace/>image-rx-mixer"></td>
							<td><img src="${contextPath}/images/wcdma/LPF.png" class="img-responsive"   id="<portlet:namespace/>image-rx-lpf"></td>
							<td><img src="${contextPath}/images/wcdma/BBA.png" class="img-responsive"   id="<portlet:namespace/>image-rx-bba"></td>
						</tr>
						<tr>
							<td colspan="6">
								<h5><p class="icon-arrow-right"></p><u>Tx Block</u> </h5>
							</td>
							<td colspan="2">
								<h5><p class="icon-arrow-right"></p><u>Channel</u> </h5>
							</td>
							<td colspan="6">
								<h5><p class="icon-arrow-right"></p><u>Rx Block</u> </h5>
							</td>
						</tr>
						<tr>
							<td id="<portlet:namespace/>data-area-tx-mixer">
							</td>
							<td id="<portlet:namespace/>data-area-tx-block-1">
							</td>
							<td id="<portlet:namespace/>data-area-tx-block-2">
							</td>
							<td id="<portlet:namespace/>data-area-tx-block-3">
							</td>
							<td id="<portlet:namespace/>data-area-tx-block-4">
							</td>
							<td id="<portlet:namespace/>data-area-tx-block-5">
							</td>
							<td id="<portlet:namespace/>data-area-channel-pl">
							</td>
							<td id="<portlet:namespace/>data-area-channel-awgn">
							</td>
							<td id="<portlet:namespace/>data-area-rx-block-1">
							</td>
							<td id="<portlet:namespace/>data-area-rx-block-2">
							</td>
							<td id="<portlet:namespace/>data-area-rx-block-3">
							</td>
							<td id="<portlet:namespace/>data-area-rx-mixer">
							</td>
							<td id="<portlet:namespace/>data-area-rx-lpf">
							</td>
							<td id="<portlet:namespace/>data-area-rx-bba">
							</td>
						</tr>
						<tr>
							<td class="text-center" style="vertical-align: middle;">
								Tx Total :
							</td>
							<td colspan="5">
								<div class="form-group col-md-4">
									<label>Gain(db)</label>
									<input class="form-control" type="text" disabled/>
								</div>
								<div class="form-group col-md-4">
									<label>NF(dB)</label>
									<input class="form-control" type="text" disabled/>
								</div>
								<div class="form-group col-md-4">
									<label>OIP3(dB)</label>
									<input class="form-control" type="text" disabled/>
								</div>
							</td>
							<td colspan="2" class="text-center" style="vertical-align: middle;">
								<button class="btn btn-primary"> 
									<span class="icon-bar-chart"> Calculation </span> 
								</button> 
							</td>
							<td class="text-center" style="vertical-align: middle;">
								Rx Total :
							</td>
							<td colspan="5">
								<div class="form-group col-md-4">
									<label>Gain(db)</label>
									<input class="form-control" type="text" disabled/>
								</div>
								<div class="form-group col-md-4">
									<label>NF(dB)</label>
									<input class="form-control" type="text" disabled/>
								</div>
								<div class="form-group col-md-4">
									<label>IIP3(dB)</label>
									<input class="form-control" type="text" disabled/>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">BER</h2>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-8">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Power Level Diagram</h2>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Channel Infomation</h2>
			</div>
		</div>
	</div>
</div>


<!-- Plotly libs -->
<script src="${contextPath}/js/plotly/plotly-latest.min.js"></script>

<script type="text/javascript">
var <portlet:namespace/>RF_DESIGN_PARAMETER = {
	"form":{
		"CHANNEL-PL":{
			"image":"PL.png",
			"input":[
				{"label":"G_Tx(dB)","id":"g-tx"},
				{"label":"G_Rx(dB)","id":"g-rx"},
				{"label":"L_unit(dB)","id":"l-unit"},
				{"label":"L_dec(dB)","id":"l-dec"},
				{"label":"Distance","id":"distance"}
			]
		},
		"TX-MIXER":{
			"image":"mixer.png",
			"input":[
				{"label":"Gain(dB)","id":"gain"},
				{"label":"NF(dB)","id":"nf"},
				{"label":"OIP3(dB)","id":"oip"},
				{"label":"Fc(MHz)","id":"fc"},
				{"label":"imbal(dB)","id":"imbal-db"},
				{"label":"imbal(deg)","id":"imbal-deg"}
			]
		},
		"CHANNEL-AWGN":{
			"image":"AWGN.png",
			"input":[
				{"label":"SNR(dB)","id":"snr"}
			]
		},
		"NONE":{
			"image":"Short.png",
			"input":[
				
			]
		},
		"AMPLIFIER":{
			"image":"Amp.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"OIP3(dB)","id":"oip"},
					{"label":"AM_PM(deg)","id":"am-pm"},
					{"label":"Pwr_A/P(dB)","id":"pwr-ap"}
				]
		},
		"BPF-BUTTER":{
			"image":"BPF.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"OIP3(dB)","id":"oip"},
					{"label":"Order","id":"order"},
					{"label":"F_up(MHz)","id":"f-up"},
					{"label":"F_lo(MHz)","id":"f-lo"}
				]
		},
		"BPF-CHEBY":{
			"image":"BPF.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"OIP3(dB)","id":"oip"},
					{"label":"Order","id":"order"},
					{"label":"F_up(MHz)","id":"f-up"},
					{"label":"F_lo(MHz)","id":"f-lo"},
					{"label":"Ripple(dB)","id":"ripple"}
				]
		},
		"RX-MIXER":{
			"image":"mixer.png",
			"input":[
				{"label":"Gain(dB)","id":"gain"},
				{"label":"NF(dB)","id":"nf"},
				{"label":"IIP3(dB)","id":"iip3"},
				{"label":"Fc(MHz)","id":"fc"},
				{"label":"imbal(dB)","id":"imbal-db"},
				{"label":"imbal(deg)","id":"imbal-deg"}
			]
		},
		"RX-LPF":{
			"image":"LPF.png",
			"input":[
				{"label":"Gain(dB)","id":"gain"},
				{"label":"NF(dB)","id":"nf"},
				{"label":"IIP3(dB)","id":"iip3"},
				{"label":"Order","id":"order"},
				{"label":"Fcut(kHz)","id":"fcut"},
				{"label":"Ripple(deg)","id":"ripple"}
			]
		},
		"RX-BBA":{
			"image":"BBA.png",
			"input":[
				{"label":"Gain(dB)","id":"gain"},
				{"label":"NF(dB)","id":"nf"},
				{"label":"IIP3(dB)","id":"iip3"},
				{"label":"AM_PM(kHz)","id":"am-pm"},
				{"label":"Pwr_A/P(dB)","id":"pwr-ap"}
			]
		}
	},
	"data":{
		
		
	}
}

$(document).ready(function(){
	<portlet:namespace/>init();
// 	console.log(<portlet:namespace/>RF_DESIGN_PARAMETER.data);


	/*select EVENT*/
	$("#<portlet:namespace/>table-rf-designer-parameter thead select").change(function (e) {
		var key = $(this).val();
		var id=$(this).attr("id");
		<portlet:namespace/>rfDesignerParameterImageChange(id,key);
		<portlet:namespace/>rfDesignerParameterDataChange(id,key);
	});

	/*data-binded EVENT*/
	$("#<portlet:namespace/>table-rf-designer-parameter input.data-binded").change(function (e) {
		var thisValue = $(this).val();
		var thisName = $(this).attr("name");
		var parentKey = $(this).attr("parent-key");
		
		<portlet:namespace/>RF_DESIGN_PARAMETER.data[parentKey][thisName] = thisValue;
	});
});

function <portlet:namespace/>init() {
	$("#<portlet:namespace/>table-rf-designer-parameter thead input").each(function(){
		<portlet:namespace/>rfDesignerParameterDataChange($(this).attr("id"),$(this).attr("id").toUpperCase());
	});
}

function <portlet:namespace/>rfDesignerParameterImageChange(id,key) {
	var imageName = <portlet:namespace/>RF_DESIGN_PARAMETER.form[key].image;
	var path = "${contextPath}/images/wcdma/"+imageName;
	$("#<portlet:namespace/>image-"+id.toLowerCase()).attr("src",path)
}

function <portlet:namespace/>rfDesignerParameterDataChange(id,key) {
	var input = <portlet:namespace/>RF_DESIGN_PARAMETER.form[key].input;
	$dataArea = $("#<portlet:namespace/>data-area-"+id.toLowerCase());
	$dataArea.empty();
	
	var data = {};
	if(input.length!=0){
		for(var i=0;i<input.length;i++){
			$div = $("<div/>").addClass("form-group").appendTo($dataArea);
			$("<label/>").html(input[i].label).appendTo($div);
			$("<input/>").addClass("form-control data-binded").attr("id",input[i].id).attr("name",input[i].id).attr("parent-key",id)
						 .attr("type","text").val(0).appendTo($div);
			data[input[i].id] = "0";
		}
	}
	<portlet:namespace/>RF_DESIGN_PARAMETER.data[key] = data;
}
</script>
