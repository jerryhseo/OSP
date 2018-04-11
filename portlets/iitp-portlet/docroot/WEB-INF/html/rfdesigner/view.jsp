<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<!-- Plotly libs -->
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>

<script src="${contextPath}/js/lib/jquery.mustache.js"></script>
<script src="${contextPath}/js/lib/mustache.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>

<style type="text/css">
	
	.rf-designer #_RFDesigner_WAR_iitpportlet_mustache-panel-box .form-group,.rf-designer #_RFDesigner_WAR_iitpportlet_mustache-panel-box .row{
 		margin-left: 0px;
 		margin-right: 0px;
 		margin-bottom: 5px;
 		padding-left: 0px;
		padding-right: 0px;
	}
	
	.rf-designer .panel-body .form-group,.rf-designer .panel-body .row{
 		margin-left: -15px;
 		margin-right: -15px;
	}
	
	.rf-designer .panel-body .my-group .form-control{
		width:100%;
	}
	
	.rf-designer .panel-body .my-title{
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		line-height: normal;
		padding-bottom: 8px;
		font-weight: 600;
	}
	
	.rf-designer option:disabled{
		background-color: #555555;
	}
	
	.rf-designer .img-responsive{
		margin: 0 auto;
	}
	
	.rf-designer .border-grid{
/* 		border: 1px solid #337ab7; */
/* 		padding-top: 5px; */
	}
</style>
<div class="h20"></div>
<div class="row">
	<div class="col-md-6 border-grid">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default" style="min-height: 390px">
					<div class="panel-heading clearfix ">
						<h2 class="panel-title">Filter Options</h2>
					</div>
					<div class="panel-body form-horizontal">
						<div class="form-group">
							<label for="inputEmail3" class="col-md-5 control-label">Response Type</label>
							<div class="col-md-7">
								<div class="radio">
									<label>
										<input type="radio" name="optionsRadios1" id="optionsRadios1" value="CHEBYSHEV" class="mustache-radio">
										Chebyshev
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="optionsRadios1" id="optionsRadios1" value="MAXIMALLY" class="mustache-radio">
										Maximally Flat
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-md-5 control-label">Filter Type</label>
							<div class="col-md-7">
								<div class="radio">
									<label>
										<input type="radio" name="optionsRadios2" id='<portlet:namespace/>radioFilterType' value="LOWPASS" class="mustache-radio">
										Lowpass
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="optionsRadios2" id="<portlet:namespace/>radioFilterType" value="HIGHPASS" class="mustache-radio">
										Highpass
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="optionsRadios2" id="<portlet:namespace/>radioFilterType" value="BANDPASS" class="mustache-radio">
										Bandpass
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="optionsRadios2" id="<portlet:namespace/>radioFilterType" value="BANDSTOP" class="mustache-radio">
										Bandstop
									</label>
								</div>
							</div>
						</div>
						<div class="form-group col-md-12">
							<label for="inputEmail3">Characteristic Impedance</label>
							<div class="input-group">
								<input type="email" class="form-control" id="inputEmail3">
								<div class="input-group-addon">&#937;</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default" style="min-height: 390px">
					<div class="panel-heading clearfix ">
						<h2 class="panel-title">Specification</h2>
					</div>
					<div class="panel-body form-horizontal" id="<portlet:namespace/>mustache-panel-box">
						
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="input-group">
					<select class="form-control" id="<portlet:namespace/>filter-type">
						<option value="LUMPED-FILTER">Lumped-Element Filter</option>
						<option value="TRANSMIATION-FILTER">Transmission Line Filter</option>
					</select>
					<div class="input-group-btn">
						<button class="btn btn-primary disabled" id="design-filter">
							<span class="icon-bar-chart">
								Design Filter
							</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="h10"></div>
	</div>
	<div class="col-md-6 border-grid">
		<div class="panel panel-default" style="min-height: 390px;">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Line Calculator</h2>
			</div>
			<div class="panel-body">
				<label class="radio-inline">
					<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Single Microstrip Line
				</label>
				<label class="radio-inline">
					<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Coupied Microstrip Line
				</label>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-6  border-grid">
		<div class="panel panel-default" style="min-height: 500px;">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Graph</h2>
			</div>
			<div class="panel-body form-horizontal">
				<div class="row">
					<div class="col-md-12">
						<div id="plot-content" style="height: 450px;">
						
						</div>
					</div>
				</div>
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-md-5 control-label">Optimum Order (N)</label>
						<div class="col-md-7">
							<p class="form-control-static">0</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-5 control-label">Selected Order (N)</label>
						<div class="col-md-3 input-group">
							<input type="text" class="form-control">
							<div class="input-group-btn">
								<button class="btn btn-primary">Update</button>
							</div>
						</div>
						<div class="col-md-3">
						</div>
					</div>
					<div class="text-center">
						<span>
							*Optimum order is determined based on highest slope of the graph.
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6  border-grid">
		<div class="panel panel-default" style="min-height: 636px;">
			<div class="panel-heading clearfix ">
				<h2 class="panel-title">Filter Design</h2>
			</div>
			<div class="panel-body" id='<portlet:namespace/>filter-design'>
				
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
var layout = {
	xaxis: {
		title :'log(w/wc)'
	},
	yaxis: {
		title :'Attenuation (db)'
	}
};

/***********************************************************************
 * Mustache Data
 ***********************************************************************/
var <portlet:namespace/>PANEL_DATA = {
	"tpl-filter-lowpass": {
		"boxtitle":"LPF",
		"body": "specification-type-low-high",
		"option": "",
		"form": {}
	},
	"tpl-filter-highpass": {
		"boxtitle":"HPF",
		"body": "specification-type-low-high",
		"option": "",
		"form": {}
	},
	"tpl-band-bandpass": {
		"boxtitle":"BPF",
		"body": "specification-filter-type-band",
		"option": "",
		"form": {}
	},
	"tpl-band-bandstop": {
		"boxtitle":"BSF",
		"body": "specification-filter-type-band",
		"option": "",
		"form": {}
	}
};

/***********************************************************************
* Global Var
***********************************************************************/
var <portlet:namespace/>templateData;

$(document).ready(function(){
	Plotly.newPlot('plot-content', null, layout, {scrollZoom: true});
	
	$.Mustache.addFromDom();
	
	$("#p_p_id<portlet:namespace/>").on("change",".mustache-radio",function(e){
		e.preventDefault();
		var resposeType = nullToStr($("input[name='optionsRadios1']:checked").val());
		var filterType = nullToStr($("input[name='optionsRadios2']:checked").val());
		
		/*PANEL_DATA_SETTING*/
		if(resposeType!=""&&filterType!=""){
			var disabledOption = false;
			if(filterType==="LOWPASS"){
				<portlet:namespace/>templateData = <portlet:namespace/>PANEL_DATA["tpl-filter-lowpass"];
			}else if(filterType==="HIGHPASS"){
				<portlet:namespace/>templateData = <portlet:namespace/>PANEL_DATA["tpl-filter-highpass"];
			}else if(filterType==="BANDPASS"){
				<portlet:namespace/>templateData = <portlet:namespace/>PANEL_DATA["tpl-band-bandpass"];
				disabledOption = true;
			}else if(filterType==="BANDSTOP"){
				<portlet:namespace/>templateData = <portlet:namespace/>PANEL_DATA["tpl-band-bandstop"];
				disabledOption = true;
			}
			
			if(resposeType==="CHEBYSHEV"){
				<portlet:namespace/>templateData.option = false;
			}else{
				<portlet:namespace/>templateData.option = true;
			}
			
			/*SELECT BOX EVENT*/
			if(disabledOption){
				$("#filter-type option[value='LUMPED-FILTER']").prop("selected", true);;
				$("#filter-type").css("cursor", "not-allowed");
			}else{
				$("#filter-type").css("cursor", "pointer");
			}
			
			$("#filter-type option[value='TRANSMIATION-FILTER']").attr('disabled', disabledOption);
			
			$("#<portlet:namespace/>mustache-panel-box").empty().mustache(<portlet:namespace/>templateData["body"], <portlet:namespace/>templateData);
			
			$("#<portlet:namespace/>mustache-panel-box .data-binded").change(function (e) {
				var thisValue = $(this).val();
				var thisName = $(this).attr("name");
				<portlet:namespace/>templateData.form[thisName] = thisValue;
			});
			
			if($("button#design-filter").hasClass("disabled")){
				$("button#design-filter").removeClass("disabled");
				$("button#design-filter").attr("onclick","<portlet:namespace/>gridFilter();return false;");
			}
		}
	});
});

function <portlet:namespace/>isValidate() {
	$("#<portlet:namespace/>mustache-panel-box form").validator('validate');
	return $("#<portlet:namespace/>mustache-panel-box form").find(".has-error").length === 0;
}

function <portlet:namespace/>gridFilter(){
	var filterData = {};
	if (<portlet:namespace/>isValidate()) {
		$("#<portlet:namespace/>mustache-panel-box .data-binded").each(function(e){
			var name = $(this).attr("name");
			if(typeof <portlet:namespace/>templateData.form[name] != "undefined"){
				filterData[name] = <portlet:namespace/>templateData.form[name];
			}else{
				return false;
			}
		});
	}
	console.log(filterData);
	<portlet:namespace/>gridFilterDesign();
}


function <portlet:namespace/>gridFilterDesign(){
	var selectFilterType = $("#<portlet:namespace/>filter-type option:selected").val();
	var $filterDiv = $("#<portlet:namespace/>filter-design");
	$filterDiv.empty();
	
	if(selectFilterType==="LUMPED-FILTER"){
		var imageName = "";
		var radioFilterType = $("#<portlet:namespace/>radioFilterType:checked").val();
		if(radioFilterType==="LOWPASS"){
			imageName = "cktLPF";
		}else if(radioFilterType==="HIGHPASS"){
			imageName = "cktHPF";
		}else if(radioFilterType==="BANDPASS"){
			imageName = "cktBPF";
		}else if(radioFilterType==="BANDSTOP"){
			imageName = "cktBSF";
		}
		
		var $firstRow = $("<div/>").addClass("row").appendTo($filterDiv);
		var $firstDiv = $("<div/>").addClass("col-md-12").appendTo($firstRow);
		$("<img/>").attr("src","${contextPath}/images/rfdesigner/filter-design/"+imageName+".png").addClass("img-responsive").appendTo($firstDiv);
		
		var $secondRow = $("<div/>").addClass("row").appendTo($filterDiv);
		var $secondDiv = $("<div/>").addClass("col-md-12 form-group").appendTo($secondRow);
		$("<textarea/>").attr("rows","18").addClass("form-control").css("resize","none").appendTo($secondDiv);
	}else{
		
	}
}
</script>


<script id="specification-type-low-high" type="text/html">
<h3 class="panel-title my-title">
    <img src="/iitp-portlet/images/title.png" width="20" height="20"/> 
    {{boxtitle}}
</h3>
<form onsubmit="return false;">
    <div class="form-group">
        <label>Center Frequency</label>
        <div class="form-inline row">
            <div class="form-group col-sm-6">
                <input type="text" name="center-frequency" class="form-control data-binded" value="{{form.center-frequency}}" required autofocus>
            </div>
            <div class="form-group col-sm-6">
                <select class="form-control" name="center-frequency-addon">
                    <option value="Hz">Hz</option>
                    <option value="kHz">kHz</option>
                    <option value="MHz">MHz</option>
                    <option value="GHz">GHz</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
            <label>Stop Frequency</label>
        <div class="form-inline row">
            <div class="form-group col-sm-6">
                    <input type="text" name="stop-frequency" class="form-control data-binded" value="{{form.stop-frequency}}" required>
            </div>
            <div class="form-group col-sm-6">
                <select class="form-control" name="stop-frequency-addon">
                    <option value="Hz">Hz</option>
                    <option value="kHz">kHz</option>
                    <option value="MHz">MHz</option>
                    <option value="GHz">GHz</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label>Passband Ripple (Rp)</label>
        <div class="input-group">
            {{#option}}    
                <input type="text" class="form-control" disabled> 
            {{/option}}
            {{^option}}
                <input type="text" name="passband-ripple" class="form-control data-binded" value="{{form.passband-ripple}}" required>
            {{/option}}
            <span class="input-group-addon">dB</span>
        </div>
    </div>
    <div class="form-group">
        <label>Passband Ripple (Rp)</label>
        <div class="input-group">
            <input type="text" name="stopband-attenuation" class="form-control data-binded" value="{{form.stopband-attenuation}}" required>
            <span class="input-group-addon">dB</span>
        </div>
    </div>
</form>
</script>
<script id="specification-filter-type-band" type="text/html">
<h3 class="panel-title my-title">
    <img src="/iitp-portlet/images/title.png" width="20" height="20"/> 
    {{boxtitle}}
</h3>
<form class="form-horizontal" onsubmit="return false;">
<div class="form-group">
    <div class="form-inline row">
        <div class="col-md-6">
            <label class="form-control-static">Passband Freq (L)</label>
        </div>
        <div class="form-group col-md-3 my-group">
            <input type="text" name="passband-freq-L" class="form-control data-binded" value="{{form.passband-freq-L}}" autofocus required>
        </div>
        <div class="form-group col-md-3">
            <select class="form-control" name="passband-freq-L-addon">
                <option value="Hz">Hz</option>
                <option value="kHz">kHz</option>
                <option value="MHz">MHz</option>
                <option value="GHz">GHz</option>
            </select>
        </div>
    </div>
    <div class="form-inline row">
        <div class="col-md-6">
            <label class="form-control-static">Stopband Freq (L)</label>
        </div>
        <div class="form-group col-md-3 my-group">
            <input type="text" name="stopband-freq-L" class="form-control data-binded" value="{{form.stopband-freq-L}}" required>
        </div>
        <div class="form-group col-md-3">
            <select class="form-control" name="stopband-freq-L-addon">
                <option value="Hz">Hz</option>
                <option value="kHz">kHz</option>
                <option value="MHz">MHz</option>
                <option value="GHz">GHz</option>
            </select>
        </div>
    </div>
    <div class="form-inline row">
        <div class="col-md-6">
            <label class="form-control-static">Passband Freq (H)</label>
        </div>
        <div class="form-group col-md-3 my-group">
            <input type="text" name="passband-freq-H" class="form-control data-binded" value="{{form.passband-freq}}" required>
        </div>
        <div class="form-group col-md-3">
            <select class="form-control" name="passband-freq-H-addon">
                <option value="Hz">Hz</option>
                <option value="kHz">kHz</option>
                <option value="MHz">MHz</option>
                <option value="GHz">GHz</option>
            </select>
        </div>
    </div>
    <div class="form-inline row">
        <div class="col-md-6">
            <label class="form-control-static">Stopband Freq (H)</label>
        </div>
        <div class="form-group col-md-3 my-group">
            <input type="text" name="stopband-freq-H" class="form-control data-binded" value="{{form.stopband-freq}}" required>
        </div>
        <div class="form-group col-md-3">
            <select class="form-control" name="stopband-freq-H-addon">
                <option value="Hz">Hz</option>
                <option value="kHz">kHz</option>
                <option value="MHz">MHz</option>
                <option value="GHz">GHz</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        {{#option}}
        <label>Passband Attenuation</label>
        <div class="input-group">
            <input type="text" name="passband-attenuation" class="form-control data-binded" value="{{form.passband-attenuation}}" required>
            <span class="input-group-addon">dB</span>
        </div>
        {{/option}} {{^option}}
        <label>Passband Ripple (Rp)</label>
        <div class="input-group">
            <input type="text" name="passband-ripple" class="form-control data-binded" value="{{form.passband-ripple}}" required>
            <span class="input-group-addon">dB</span>
        </div>
        {{/option}}
    </div>
    <div class="form-group">
        <label>Stopband Attenuation</label>
        <div class="input-group">
            <input type="text" name="stopband-attenuation" class="form-control data-binded" value="{{form.stopband-attenuation}}">
            <div class="input-group-addon">dB</div>
        </div>
    </div>
</div>
</form>
</script>