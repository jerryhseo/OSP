<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link rel="stylesheet" href="${contextPath}/css/toastr.min.css">
<link rel="stylesheet" href="${contextPath}/css/jquery-confirm/jquery-confirm.min.css">

<style type="text/css">

div.wcdma-rf-designer #_WCDMARFDesigner_WAR_iitpportlet_power-input-area .form-group{
	margin-top: 5px;
}

div.wcdma-rf-designer #_WCDMARFDesigner_WAR_iitpportlet_table-rf-designer-parameter .form-group label{
	font-size: 11px;
}
div.wcdma-rf-designer #_WCDMARFDesigner_WAR_iitpportlet_table-rf-designer-parameter .form-group .form-control{
	text-align: center;
}

.toast-designer-pos {top: 70px; left: 80%;}
</style>
<div class="h20"></div>
<div class="row">
	<div class="col-md-8">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">System Block Diagram</h2>
			</div>
			<div class="panel-body" id="<portlet:namespace/>simulationParameter">
				<img src="${contextPath}/images/wcdma/WCDMA_BlockDiagram6.png" class="img-responsive">
				<h5>
					<p class="icon-arrow-right"></p><u>Simulation Parameter</u>
				</h5>
						
				<form action="" id="<portlet:namespace/>parameterform" onsubmit="return false;">
					<div class="form-group col-md-2">
						<label>SF(4~256)</label>
						<input class="form-control simulation-parameter" id="SF" name="SF" type="text" value="4" required/>
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group col-md-2">
						<label>Samples / Chip</label>
						<input class="form-control simulation-parameter" id="zero" name="zero" type="text" value="8" required/>
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group col-md-2">
						<label>Pulse Shaping Filter</label>
						<select class="form-control simulation-parameter" id="ft" name="ft" >
							<option value="RAISED-COSINE" selected="selected">Raised Cosine</option>
							<option value="GAUSSIAN">Gaussian</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<label>Filter Taps</label>
						<input class="form-control simulation-parameter" id="N" name="N" type="text" value="33" required/>
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group col-md-2">
						<label>Roll-off Factor</label>
						<input class="form-control simulation-parameter" id="rolloff" name="rolloff" type="text" value="0.22" required/>
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group col-md-2">
						<label>3dB BW(MHz)</label>
						<input class="form-control simulation-parameter" id="B" name="B" type="text" value="5" required/>
						<div class="help-block with-errors"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Constellation</h2>
			</div>
			<div class="panel-body" >
				<div id="constellation-plot-content" style="height: 390px;">
					
				</div>
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
									<option value="AMP-AM">Amp(AM/AM)</option>
									<option value="AMP-PM">Amp(AM/PM)</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-2">
									<option value="NONE">None</option>
									<option value="AMP-AM">Amp(AM/AM)</option>
									<option value="AMP-PM">Amp(AM/PM)</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-3">
									<option value="NONE">None</option>
									<option value="AMP-AM">Amp(AM/AM)</option>
									<option value="AMP-PM">Amp(AM/PM)</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-4">
									<option value="NONE">None</option>
									<option value="AMP-AM">Amp(AM/AM)</option>
									<option value="AMP-PM">Amp(AM/PM)</option>
									<option value="BPF-BUTTER">BPF(Butter)</option>
									<option value="BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="tx-block-5">
									<option value="NONE">None</option>
									<option value="AMP-AM">Amp(AM/AM)</option>
									<option value="AMP-PM">Amp(AM/PM)</option>
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
									<option value="RX-AMP-AM">Amp(AM/AM)</option>
									<option value="RX-AMP-PM">Amp(AM/PM)</option>
									<option value="RX-BPF-BUTTER">BPF(Butter)</option>
									<option value="RX-BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="rx-block-2">
									<option value="NONE">None</option>
									<option value="RX-AMP-AM">Amp(AM/AM)</option>
									<option value="RX-AMP-PM">Amp(AM/PM)</option>
									<option value="RX-BPF-BUTTER">BPF(Butter)</option>
									<option value="RX-BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="rx-block-3">
									<option value="NONE">None</option>
									<option value="RX-AMP-AM">Amp(AM/AM)</option>
									<option value="RX-AMP-PM">Amp(AM/PM)</option>
									<option value="RX-BPF-BUTTER">BPF(Butter)</option>
									<option value="RX-BPF-CHEBY">BPF(Cheby)</option>
								</select>
							</th>
							<th>
								<input class="form-control" type="text" value="Mixer" id="rx-mixer" disabled/>
							</th>
							<th>
								<select class="form-control" id="rx-lpf">
									<option value="NONE">None</option>
									<option value="LPF-BUTTER">LPF(Butter)</option>
									<option value="LPF-CHEBY">LPF(Cheby)</option>
								</select>
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
							<td><img src="${contextPath}/images/wcdma/PL.png"    class="img-responsive" id="<portlet:namespace/>image-channel-pl"></td>
							<td><img src="${contextPath}/images/wcdma/AWGN.png"  class="img-responsive" id="<portlet:namespace/>image-channel-awgn"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-rx-block-1"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-rx-block-2"></td>
							<td><img src="${contextPath}/images/wcdma/Short.png" class="img-responsive" id="<portlet:namespace/>image-rx-block-3"></td>
							<td><img src="${contextPath}/images/wcdma/mixer.png" class="img-responsive" id="<portlet:namespace/>image-rx-mixer"></td>
							<td><img src="${contextPath}/images/wcdma/LPF.png"   class="img-responsive" id="<portlet:namespace/>image-rx-lpf"></td>
							<td><img src="${contextPath}/images/wcdma/BBA.png"   class="img-responsive" id="<portlet:namespace/>image-rx-bba"></td>
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
						<tr id="<portlet:namespace/>calculationResultDiv">
							<td class="text-center" style="vertical-align: middle;">
								Tx Total :
							</td>
							<td colspan="5">
								<div class="form-group col-md-6">
									<label>Gain(db)</label>
									<input class="form-control" id="<portlet:namespace/>txGain" type="text" disabled/>
								</div>
								<div class="form-group col-md-6">
									<label>NF(dB)</label>
									<input class="form-control" id="<portlet:namespace/>txNF" type="text" disabled/>
								</div>
							</td>
							<td colspan="2" class="text-center" style="vertical-align: middle;">
								<button class="btn btn-primary" onclick="<portlet:namespace/>calculation();"> 
									<span class="icon-bar-chart"> Calculation </span> 
								</button> 
							</td>
							<td class="text-center" style="vertical-align: middle;">
								Rx Total :
							</td>
							<td colspan="5">
								<div class="form-group col-md-6">
									<label>Gain(db)</label>
									<input class="form-control" id="<portlet:namespace/>rxGain" type="text" disabled/>
								</div>
								<div class="form-group col-md-6">
									<label>NF(dB)</label>
									<input class="form-control" id="<portlet:namespace/>rxNF" type="text" disabled/>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">BER</h2>
			</div>
			<div class="panel-body">
				<div id="ber-plot-content" style="height: 650px;">
					
				</div>
			</div>
			<div class="panel-footer">
				<div class="row" style="margin-left: -15px;margin-right: -15px;">
					<div class="col-md-3">
						SNR Range : 
					</div>
					<div class="col-md-3">
						<label>From(dB)</label>
						<input class="form-control" id="<portlet:namespace/>from" type="text" value="1"/>
					</div>
					<div class="col-md-3">
						<label>To(dB)</label>
						<input class="form-control" id="<portlet:namespace/>to" type="text" value="3"/>
					</div>
					<div class="col-md-3">
						<button class="btn btn-primary" onclick="<portlet:namespace/>berplot();"> 
							<span class="icon-bar-chart"> Plot </span> 
						</button>
					</div>
				</div>
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
			<div class="panel-body form-inline" id="<portlet:namespace/>power-levem-diagram-body">
				<div class="form-group">
					<label>P_in(dBm)</label>
					<input class="form-control" type="text" id="<portlet:namespace/>power-input-pin"/>
				</div>
				<div class="form-group">
					<label>Channel</label>
					<select class="form-control" id="<portlet:namespace/>power-select" onchange="<portlet:namespace/>powerChannelChange(this);return false;">
						<option value="LOG-DISTANCE">Log Distance</option>
						<option value="HATA">Hata</option>
						<option value="EGLI">EGLI'S</option>
						<option value="COST">COST231</option>
					</select>
				</div>
				<button class="btn btn-primary" onClick="<portlet:namespace/>powerLevelGrid();"> 
					<span class="icon-bar-chart"> Plot </span> 
				</button>
				
				<section id="<portlet:namespace/>power-input-area" style="display: none;">
					<div class="form-group">
						<label>Distance(km)</label>
						<input class="form-control" name="distance" type="text"/>
					</div>
					<div class="form-group">
						<label>Hb(m)</label>
						<input class="form-control" name=hb type="text"/>
					</div>
					<div class="form-group">
						<label>Hm(m)</label>
						<input class="form-control" name="hm" type="text"/>
					</div>
					<div class="form-group">
						<label>F_in(MHz)</label>
						<input class="form-control" name="f-in" type="text"/>
					</div>
					<div class="form-group" id="<portlet:namespace/>hata-select-area">
						<label>Enviroment</label>
						<select class="form-control" name="environment1">
							<option value="URBAN">Urban</option>
							<option value="SUBURBS">Suburbs</option>
							<option value="RURAL">Rural</option>
						</select>
					</div>
					<div class="form-group" id="<portlet:namespace/>cost-select-area">
						<label>Enviroment</label>
						<select class="form-control" name="environment2">
							<option value="RURAL">Rural</option>
							<option value="URBAN">Urban</option>
						</select>
					</div>
				</section>
				<div id="power-plot-content" style="height: 450px;">
						
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default" style="min-height: 390px">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Channel Infomation</h2>
			</div>
			<div class="panel-body">
				<pre id="<portlet:namespace/>channel-infomation-pre">
L_unit : Loss at Unit Distance
L_dec : Loss in dB/decade
G_Tx : Tx Antenna Gain
G_Rx : Rx Antenna Gain
D : Normalized Distance

L(dB) = L_unit + [L_dec*log(D)] - G_Tx - G_Rx
				</pre>
			</div>
		</div>
	</div>
</div>


<!-- Plotly libs -->
<script src="${contextPath}/js/plotly/plotly-latest.min.js"></script>

<!--numeric JS-->
<script src="${contextPath}/js/numeric/numeric.min.js"></script>

<!--math JS-->
<script src="${contextPath}/js/mathjs/math.min.js"></script>


<script src="${contextPath}/js/wcdma/designer.js"></script>
<script src="${contextPath}/js/wcdma/fomular.js"></script>

<script src="${contextPath}/js/lib/toastr.min.js"></script>

<!--bootstrap validation JS-->
<script src="${contextPath}/js/validation/validator.min.js"></script>

<script type="text/javascript">
var toastr;

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
				{"label":"OIP3(dB)","id":"oip3"},
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
		"AMP-AM":{
			"image":"Amp.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"OIP3(dB)","id":"oip3"}
				]
		},
		"AMP-PM":{
			"image":"Amp.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"OIP3(dB)","id":"oip3"},
					{"label":"AM_PM(deg)","id":"am-pm"},
					{"label":"Pwr_A/P(dB)","id":"pwr-ap"}
				]
		},
		"BPF-BUTTER":{
			"image":"BPF.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"OIP3(dB)","id":"oip3"},
					{"label":"Order","id":"order"},
					{"label":"F_up(MHz)","id":"f-up"},
					{"label":"F_lo(MHz)","id":"f-lo"},
					{"label":"Apass(dB)","id":"apass"}
				]
		},
		"BPF-CHEBY":{
			"image":"BPF.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"OIP3(dB)","id":"oip3"},
					{"label":"Order","id":"order"},
					{"label":"F_up(MHz)","id":"f-up"},
					{"label":"F_lo(MHz)","id":"f-lo"},
					{"label":"Apass(dB)","id":"apass"}
				]
		},
		"RX-AMP-AM":{
			"image":"Amp.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"IIP3(dB)","id":"iip3"}
				]
		},
		"RX-AMP-PM":{
			"image":"Amp.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"IIP3(dB)","id":"iip3"},
					{"label":"AM_PM(deg)","id":"am-pm"},
					{"label":"Pwr_A/P(dB)","id":"pwr-ap"}
				]
		},
		"RX-BPF-BUTTER":{
			"image":"BPF.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"IIP3(dB)","id":"iip3"},
					{"label":"Order","id":"order"},
					{"label":"F_up(MHz)","id":"f-up"},
					{"label":"F_lo(MHz)","id":"f-lo"},
					{"label":"Apass(dB)","id":"apass"}
				]
		},
		"RX-BPF-CHEBY":{
			"image":"BPF.png",
			"input":[
					{"label":"Gain(dB)","id":"gain"},
					{"label":"NF(dB)","id":"nf"},
					{"label":"IIP3(dB)","id":"iip3"},
					{"label":"Order","id":"order"},
					{"label":"F_up(MHz)","id":"f-up"},
					{"label":"F_lo(MHz)","id":"f-lo"},
					{"label":"Apass(dB)","id":"apass"}
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
				{"label":"IIP3(dB)","id":"iip3"}
			]
		},
		"LPF-BUTTER":{
			"image":"LPF.png",
			"input":[
				{"label":"Gain(dB)","id":"gain"},
				{"label":"NF(dB)","id":"nf"},
				{"label":"IIP3(dB)","id":"iip3"},
				{"label":"Order","id":"order"},
				{"label":"Fcut(kHz)","id":"fcut"},
				{"label":"Apass(dB)","id":"apass"}
			]
		},
		"LPF-CHEBY":{
			"image":"LPF.png",
			"input":[
				{"label":"Gain(dB)","id":"gain"},
				{"label":"NF(dB)","id":"nf"},
				{"label":"IIP3(dB)","id":"iip3"},
				{"label":"Order","id":"order"},
				{"label":"Fcut(kHz)","id":"fcut"},
				{"label":"Apass(dB)","id":"apass"}
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
		var id = $(this).attr("id");
		<portlet:namespace/>rfDesignerParameterImageChange(id,key);
		<portlet:namespace/>rfDesignerParameterDataChange(id,key);
	});

	/*data-binded EVENT*/
	$(document).on("change","#<portlet:namespace/>table-rf-designer-parameter input.data-binded",function (e) {
		event.stopPropagation();
		e.preventDefault();
		var thisValue = $(this).val();
		var thisName = $(this).attr("name");
		var parentKey = $(this).attr("parent-key");
		
		<portlet:namespace/>RF_DESIGN_PARAMETER.data[parentKey][thisName] = thisValue;
	});
	
	toastr.options = {
		"closeButton": true,
		"debug": false,
		"newestOnTop": true,
		"progressBar": false,
		"positionClass": "toast-designer-pos",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "slideDown",
		"hideMethod": "slideUp"
	};
});

function <portlet:namespace/>init() {
	$("#<portlet:namespace/>table-rf-designer-parameter thead input").each(function(){
		/*초기 화면 Data 생성*/
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
			$("<input/>").addClass("form-control data-binded").attr("id",input[i].id).attr("name",input[i].id)
						 .attr("parent-key",id.toUpperCase())
						 .attr("type","text").val(0).appendTo($div);
			data[input[i].id] = "0";
		}
		data["type"] = key;
	}
	<portlet:namespace/>RF_DESIGN_PARAMETER.data[id.toUpperCase()] = data;
}

function <portlet:namespace/>powerChannelChange(sel){
	var value = sel.value;
	
	$powerInputArea = $("#<portlet:namespace/>power-input-area");
	if(value === "LOG-DISTANCE"){
		$powerInputArea.css("display","none");
	}else{
		$powerInputArea.css("display","block");
		
		$hataSelectArea = $powerInputArea.find("div#<portlet:namespace/>hata-select-area");
		$costSelectArea = $powerInputArea.find("div#<portlet:namespace/>cost-select-area");
		
		$hataSelectArea.css("display","none");
		$costSelectArea.css("display","none");
		
		if(value ==="HATA"){
			$hataSelectArea.css("display","block");
		}else if(value ==="EGLI"){
			
		}else if(value ==="COST"){
			$costSelectArea.css("display","block");
		}
	}
	
	<portlet:namespace/>channelInfomationGrid();
}
var <portlet:namespace/>CHANNEL_INFOMATION_TEXT = {
	"LOG-DISTANCE":{
		"text":[
			"L_unit : Loss at Unit Distance"
			+"\nL_dec : Loss in dB/decade"
			+"\nG_Tx : Tx Antenna Gain"
			+"\nG_Rx : Rx Antenna Gain"
			+"\nD : Normalized Distance"
			+"\n "
			+"\nL(dB) = L_unit + [L_dec*log(D)] - G_Tx - G_Rx"
		]
	},
	"HATA":{
		"text":[
			"D : Distance ( Base station to Mobile )"
			+"\nf : F_in + Fc"
			+"\nHb : Height of a Base station"
			+"\nHm : Height of a Mobile station"
			+"\n\n1) Urban"
			+"\nLu = 69.55+26.16log(f)-13.82log(Hb)-C+[44.9-6.55log(Hb)]*log(D)"
			+"\nC = 8.29(log(1.54Hm))^2 -1.1        (150<=f<=200)"
			+"\nC = 3.2(log(11.75Hm))^2 -4.97      (200<=f<=1500)"
			+"\n\n2) Suburbs"
			+"\nLsu = Lu -2(log(f/28))^2 -5.4"
			+"\nLu = 69.55+26.16log(f)-13.82log(Hb)+[44.9-6.55log(Hb)]*log(D)"
			+"\n\n3) Rural"
			+"Lr = Lu -4.78(log(f))^2 +18.33log(f) – 40.94"
			+"\nLu = 69.55+26.16log(f)-13.82log(Hb)+[44.9-6.55log(Hb)]*log(D)"
		]
	},
	"EGLI":{
		"text":[
			"D : Distance ( Base station to Mobile )"
			+"\nf : F_in + Fc"
			+"\nHb : Height of a Base station"
			+"\nHm : Height of a Mobile station"
			+"\n "
			+"\n1) Hm <= 10m"
			+"\nL = 76.3+40log(D)-20log(Hb)-10log(Hm)+20log(f)"
			+"\n "
			+"\n2) Hm > 10m"
			+"\nL = 85.9+40log(D)-20log(Hb)-10log(Hm)+20log(f)"
		]
	},
	"COST":{
		"text":[
			"D : Distance ( Base station to Mobile )"
			+"\nf : F_in + Fc"
			+"\nHb : Height of a Base station"
			+"\nHm : Height of a Mobile station"
			+"\n\n1) Rural"
			+"\nLr = 46.3+33.9log(f)-13.82log(Hb)-A+[44.9-6.55log(Hb)]*log(D)"
			+"\nA = (1.1log(f)-0.7)*Hm-(1.56log(f)-0.8)"
			+"\n\n2) Urban"
			+"\nLu = 46.3+33.9log(f)-13.82log(Hb)-A+[44.9-6.55log(Hb)]*log(D)+3"
			+"\nA = 8.29(log(1.54Hm))^2-1.1             (150<=f<=200)"
			+"\nA = 3.2(log(11.75Hm))^2-4.97           (200<=f<=1500)"
		]
	}
};

function <portlet:namespace/>channelInfomationGrid(){
	var channelValue = $("#<portlet:namespace/>power-select").val();
	$pre = $("#<portlet:namespace/>channel-infomation-pre");
	
	$pre.empty();
	
	var result = <portlet:namespace/>CHANNEL_INFOMATION_TEXT[channelValue].text;
	$pre.html(result);
}


function <portlet:namespace/>powerLevelGrid(){
	var pIn = DESIGNER.Constants.getNullToZero($("#<portlet:namespace/>power-input-pin").val());
	var channel = $("#<portlet:namespace/>power-select").val();
	
	$powerInputArea = $("#<portlet:namespace/>power-levem-diagram-body #<portlet:namespace/>power-input-area");
	var inputData = {};
	$powerInputArea.find("input[type='text'], select").each(function(){
		var key = $(this).attr("name");
		var value;
		if($(this).get(0).tagName =="INPUT"){
			value = parseFloat(DESIGNER.Constants.getNullToZero($(this).val()));
		}else{
			value = $(this).val();
		}
		inputData[key] = value;
	});
	
	var plotlyData = getPowerLevelDiagramData(<portlet:namespace/>RF_DESIGN_PARAMETER.data,pIn,channel,inputData);
	
	<portlet:namespace/>plotlyGrid('power-plot-content','No. of Stage','Power Level (dBm)',plotlyData);
}

function <portlet:namespace/>plotlyGrid(idStr,xTitle,yTitle,data){
	var layout = {
            xaxis: {
                title: xTitle
            },
            yaxis: {
                title: yTitle
            }
        };
	
	if(idStr==='ber-plot-content'){
		layout.yaxis['type'] = ['log'];
	}
	
	Plotly.newPlot(idStr, data, layout, {
        scrollZoom: false
    });
}

function <portlet:namespace/>calculation(){
	if (<portlet:namespace/>isValidate($("#<portlet:namespace/>parameterform"))) {
		<portlet:namespace/>simulationParameterUpdate();
		
		var calDiv = $("#<portlet:namespace/>calculationResultDiv");
		bStart();
// 		var sampleData = {
// 			"CHANNEL-AWGN": {"snr": "2", "type": "CHANNEL-AWGN"},
// 			"CHANNEL-PL": {"distance": "1","g-rx": "0","g-tx": "0","l-dec": "0","l-unit": "40","type": "CHANNEL-PL"},
// 			"RX-BBA": {"gain": "34", "nf": "28", "iip3": "20", "type": "RX-BBA"},
// 			"RX-BLOCK-1": {"gain": "16", "nf": "1.5", "iip3": "8", "type": "RX-AMP-AM"},
// 			"RX-LPF": {"apass": "3","fcut": "3.5","gain": "-2","iip3": "35","nf": "25","order": "3","type": "LPF-BUTTER"},
// 			"RX-MIXER": {fc: "2112","gain": "15","iip3": "11","imbal-db": "0","imbal-deg": "0","nf": "10","type": "RX-MIXER"},
// 			"SIMULATION-PARAMETER": {"B": "5","N": "33","SF": "4","ft": "RAISED-COSINE","rolloff": "0.22","zero": "8"},
// 			"TX-BLOCK-1": {"apass": "3","f-lo": "2108.5","f-up": "2115.5","gain": "-2","nf": "4","oip3": "100","order": "3","type": "BPF-BUTTER"},
// 			"TX-BLOCK-2": {"gain": "25", "nf": "5", "oip3": "47", "type": "AMP-AM"},
// 			"TX-MIXER": {"fc": "2112","gain": "-1","imbal-db": "0.05","imbal-deg": "4","nf": "1","oip3": "10","type": "TX-MIXER"}
// 		};
// 		<portlet:namespace/>RF_DESIGN_PARAMETER.data = sampleData;
		
		setTimeout(function(){
			var plotlyData = calculation(<portlet:namespace/>RF_DESIGN_PARAMETER.data,calDiv,'<portlet:namespace/>');
			
			if(!plotlyData){
				toastr["error"]("", Liferay.Language.get('edison-data-event-error'));
			}else{
				console.log(plotlyData);
				<portlet:namespace/>plotlyGrid('constellation-plot-content','REAL','IMAG',plotlyData);
			}
			bEnd();
		},1000);
	}
}

function <portlet:namespace/>berplot(){
	if (<portlet:namespace/>isValidate($("#<portlet:namespace/>parameterform"))) {
		<portlet:namespace/>simulationParameterUpdate();
		
		bStart();
		setTimeout(function(){
			var from = $("input#<portlet:namespace/>from").val();
			var to = $("input#<portlet:namespace/>to").val();
			
			var plotlyData = berplot(<portlet:namespace/>RF_DESIGN_PARAMETER.data,from,to);
			if(!plotlyData){
				toastr["error"]("", Liferay.Language.get('edison-data-event-error'));
			}else{
				console.log(plotlyData);
				<portlet:namespace/>plotlyGrid('ber-plot-content','SNR(dB)','BER',plotlyData);
			}
			bEnd();
		},1000);
	}
}

function <portlet:namespace/>simulationParameterUpdate(){
	var simObj = {};
	
	//Simulation Parameter to Object
	$("#<portlet:namespace/>simulationParameter").find(".simulation-parameter")
		.each(function(index){
			var thisName = $(this).attr("name");
			var thisValue = $(this).val();
			simObj[thisName]=thisValue;
		}
	);
	
	<portlet:namespace/>RF_DESIGN_PARAMETER.data[DESIGNER.Constants.DesignerKey.SM_PARA] = simObj;
}

function <portlet:namespace/>isValidate(formObject) {
    formObject.validator('validate');
    return formObject.find(".has-error").length === 0;
}

</script>
