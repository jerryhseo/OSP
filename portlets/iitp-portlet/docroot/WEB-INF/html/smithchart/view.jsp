<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="graphFileActionURL" id="graphFileAction" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<link rel="stylesheet" href="${contextPath}/css/rSlider.min.css">

<style type="text/css">
.smith-chart div.panel-body label.title{
	width: 25%;
}

.smith-chart #svg_circle{
	cursor: crosshair;
}

.smith-chart div.panel-body ul#SchematicArea img{
	width: 60px;
}
.smith-chart div.panel-body ul#SchematicArea li{
	float:left;
}
.smith-chart div.panel-body ul#SchematicArea li p{
	writing-mode: vertical-rl;
	-webkit-writing-mode: vertical-rl;
	-ms-writing-mode: vertical-rl;
	padding-left: 22px;
	padding-top: 5px;
}




.smith-chart .elementBtnGroup .elementMethod{
	padding: 40px;
	position: relative;
}
.smith-chart .elementBtnGroup .elementMethod .method {
	position: absolute;
	right: 3px;
	top: 3px;
	bottom: 3px;
	left: 3px;
	background-position: center;
	background-repeat: no-repeat;
	border: 2px solid transparent;
	transition: all 0.5s;
}


.smith-chart .elementBtnGroup .elementMethod .ser_cap{
	background-image:url(/iitp-portlet/images/smithchart/buttons/ser_cap.png);
	background-size: 50px;
}
.smith-chart .elementBtnGroup .elementMethod .ser_ind{
	background-image:url(/iitp-portlet/images/smithchart/buttons/ser_ind.png);
	background-size: 50px;
}
.smith-chart .elementBtnGroup .elementMethod .ser_res{
	background-image:url(/iitp-portlet/images/smithchart/buttons/ser_res.png);
	background-size: 50px;
}
.smith-chart .elementBtnGroup .elementMethod .sht_cap{
	background-image:url(/iitp-portlet/images/smithchart/buttons/sht_cap.png);
	background-size: 50px;
}
.smith-chart .elementBtnGroup .elementMethod .sht_ind{
	background-image:url(/iitp-portlet/images/smithchart/buttons/sht_ind.png);
	background-size: 50px;
}
.smith-chart .elementBtnGroup .elementMethod .sht_res{
	background-image:url(/iitp-portlet/images/smithchart/buttons/sht_res.png);
	background-size: 50px;
}
</style>
<div class="h20"></div>
<div class="row">
	<div class="col-md-6 text-center" id="chart">
		
	</div>
	<div class="col-md-6">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading clearfix ">
						<h2 class="panel-title">Settings</h2>
					</div>
					<div class="panel-body">
						<div class="form-inline form-group">
							<label class="form-control-static title">Frequency</label>
							<div class="input-group">
								<input type="text" name="<portlet:namespace/>frequency" id="<portlet:namespace/>frequency" class="form-control" autofocus>
								<span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
								<select class="form-control" name="passband-freq-L-addon">
					                <option value="1">Hz</option>
					                <option value="1000">kHz</option>
					                <option value="1000000">MHz</option>
					                <option value="1000000000">GHz</option>
					            </select>
							</div>
						</div>
						<div class="form-inline form-group">
							<label class="form-control-static title">Characteristic Impedance(𝒁_𝟎)</label>
							<input type="text" name="passband-freq-L" class="form-control">
						</div>
						<form action="" id="<portlet:namespace/>settingsform" onsubmit="return false;">
							<div class="form-inline form-group">
								<label class="form-control-static title">Load Impedance(𝒁_𝑳)</label>
								<label class="form-control-static">Real</label>
								<input type="text" name="<portlet:namespace/>real" id="<portlet:namespace/>real" class="form-control" required>
								<label class="form-control-static">Imaginary</label>
								<input type="text" name="<portlet:namespace/>imaginary" id="<portlet:namespace/>imaginary" class="form-control" required>
							</div>
						</form>
					</div>
					<div class="panel-footer text-center">
						<button class="btn btn-primary" onclick="<portlet:namespace/>plotSettings();return false;">
							<span class="icon-signal">
								plot
							</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading clearfix ">
						<h2 class="panel-title">Elements</h2>
					</div>
					<div class="panel-body">
						<div class="btn-group elementBtnGroup btn-group-justified" data-toggle="buttons" id="<portlet:namespace/>noFlowLayoutArea">
							<label class="btn elementMethod">
								<div class="method ser_cap"></div>
								<input type="radio" name="<portlet:namespace/>element-type" value="ser_cap">
							</label>
							<label class="btn elementMethod">
								<div class="method ser_ind"></div>
								<input type="radio" name="<portlet:namespace/>element-type" value="ser_ind"> 
							</label>
							<label class="btn elementMethod">
								<div class="method ser_res"></div>
								<input type="radio" name="<portlet:namespace/>element-type" value="ser_res"> 
							</label>
							<label class="btn elementMethod">
								<div class="method sht_cap"></div>
								<input type="radio" name="<portlet:namespace/>element-type" value="sht_cap"> 
							</label>
							<label class="btn elementMethod">
								<div class="method sht_ind"></div>
								<input type="radio" name="<portlet:namespace/>element-type" value="sht_ind"> 
							</label>
							<label class="btn elementMethod">
								<div class="method sht_res"></div>
								<input type="radio" name="<portlet:namespace/>element-type" value="sht_res"> 
							</label>
						</div>
					</div>
					<div class="panel-footer text-center" id="<portlet:namespace/>elements-select">
						<div class="row" style="margin-left: 0px;margin-right: 0px;margin-top: 15px">
							<div class="col-md-10">
								<input type="text" id="<portlet:namespace/>elements-select-value" class="slider"/>
							</div>
							<div class="col-md-2">
								<button class="btn btn-primary" onclick="<portlet:namespace/>elementSelect();return false;">
									<span class="icon-signal">
										Select
									</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading clearfix ">
						<h2 class="panel-title">Schematic</h2>
					</div>
					<div class="panel-body">
						<div class="row" style="margin-left: 0px;margin-right: 0px;" class="pull-left">
							<ul id="SchematicArea">
								<li><img alt="" src="${contextPath}/images/smithchart/schematic/load_impedance.png"/><p>aaaaaaaaa</p></li>
								<ol id="<portlet:namespace/>schematic-area-action">
								</ol>
								<li>
									<img alt="" src="${contextPath}/images/smithchart/schematic/source.png"/>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-6">
		<div class="table-responsive panel edison-panel">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<img src="${pageContext.request.contextPath}/images/title.png" width="18" height="18" class="title-img"/>
					Cursor
				</h3>
			</div>
			<table class = "table table-bordered table-hover edison-table">
				<thead>
					<tr>
						<th width="20%">Z</th>
						<th width="20%">Y</th>
						<th width="20%">Q</th>
						<th width="20%">Reflection Coefficient</th>
						<th width="20%">Frequency</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="center" id="<portlet:namespace/>cursor_z">0</td>
						<td class="center" id="<portlet:namespace/>cursor_y">0</td>
						<td class="center" id="<portlet:namespace/>cursor_q">0</td>
						<td class="center" id="<portlet:namespace/>cursor_reflection">0</td>
						<td class="center" id="<portlet:namespace/>cursor_frequency">0</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-6">
		<div class="table-responsive panel edison-panel">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<img src="${pageContext.request.contextPath}/images/title.png" width="18" height="18" class="title-img"/>
					DataPoints
				</h3>
			</div>
			<table class = "table table-bordered table-hover edison-table">
				<thead>
					<tr>
						<th width="20%">Z</th>
						<th width="20%">Y</th>
						<th width="20%">Q</th>
						<th width="20%">Reflection Coefficient</th>
						<th width="20%">Frequency</th>
					</tr>
				</thead>
				<tbody id="<portlet:namespace/>data-point-tbody">
				</tbody>
			</table>
		</div>
</div>

<!--D3 JS-->
<script src="${contextPath}/js/lib/d3.min.js"></script>

<!--Smith JS-->
<script src="${contextPath}/js/smithchart/smith.js"></script>

<!--math JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/mathjs/4.2.2/math.min.js"></script>

<!--bootstrap validation JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>

<!--Slider JS-->
<script src="${contextPath}/js/lib/rSlider.min.js"></script>


<script type="text/javascript">
	//기준점 Point 체크 여부
	var <portlet:namespace/>stdPoint = false;
	var chart = new smith.chart();
	var svg;
	var <portlet:namespace/>mySlider;
	$(function() {
		svg = d3.select("#chart").append('svg');
		chart.draw(svg);
		
		<portlet:namespace/>mySlider = new rSlider({
	        target: '#<portlet:namespace/>elements-select-value',
	        values: {min:1,max:100},
	        step: 1,
	        range: false,
	        tooltip: true,
	        scale: false,
	        labels: false,
            onChange: function (vals) {
                console.log(vals);
            }
	    });

		$("#<portlet:namespace/>elements-select").css("display","none");
		
		svg.select("#svg_circle").on("mousemove",function(e){
			var coords = d3.mouse(this);
			var mouserData = chart.getPointValue(Math.floor(coords[0]),Math.floor(coords[1]));
			<portlet:namespace/>guideCircleMove(mouserData);
			
			var data = chart.getData(mouserData.real["value"],mouserData.imaginary["value"],Math.floor(coords[0]),Math.floor(coords[1]));
			
			$("#<portlet:namespace/>cursor_z").html(data["Z"]);
			$("#<portlet:namespace/>cursor_y").html(data["Y"]);
			$("#<portlet:namespace/>cursor_q").html(data["Q"]);
			$("#<portlet:namespace/>cursor_reflection").html(data["Reflection"]);
// 			$("#<portlet:namespace/>cursor_frequency").html(data["Z"]);

// 			e.stopPropagation();
		});
		
		svg.select("#svg_circle").on("click",function(e){
			if(!<portlet:namespace/>stdPoint){
				var coords = d3.mouse(this);
				var mouserData = chart.getPointValue(Math.floor(coords[0]),Math.floor(coords[1]));
				
				
				chart.addMarkerFromMouse(svg,mouserData.real["value"],mouserData.imaginary["value"],Math.floor(coords[0]),Math.floor(coords[1]),"<portlet:namespace/>stdPoint");
				
				<portlet:namespace/>appendDataPoints("<portlet:namespace/>stdPoint");
				
				<portlet:namespace/>stdPoint = true;
			}
// 			e.stopPropagation();
		});
		
		$(".elementMethod>input[name=<portlet:namespace/>element-type]").change(function(e){
			if(!$("#<portlet:namespace/>elements-select").is(':visible')){
				$("#<portlet:namespace/>elements-select" ).show();
			}
			
			<portlet:namespace/>mySlider.setValues(1);
			e.stopPropagation();
		});
	});
	
	/*Guid Line Move*/
	function <portlet:namespace/>guideCircleMove(mouserData){
		if(!<portlet:namespace/>stdPoint){
			$("#mouse_round_r").attr("cx",mouserData.real["mouse_x_cx"]).attr("r",mouserData.real["mouse_x_r"]);
			$("#mouse_curve_r").attr("cy",mouserData.imaginary["mouse_y_cy"]).attr("r",mouserData.imaginary["mouse_y_r"]);
			
			$("#mouse_round_l").attr("cx",mouserData.real["mouse_l_x_cx"]).attr("r",mouserData.real["mouse_l_x_r"]);
			$("#mouse_curve_l").attr("cy",mouserData.imaginary["mouse_l_y_cy"]).attr("r",mouserData.imaginary["mouse_l_y_r"]);
		}
	}
	
	/*Settings - plot*/
	function <portlet:namespace/>plotSettings(){
		if (<portlet:namespace/>isValidate($("#<portlet:namespace/>settingsform"))) {
			var settingReal = $("#<portlet:namespace/>real").val();
			var settingImg = $("#<portlet:namespace/>imaginary").val();
			
			var positionData = chart.getPointPosition(settingReal*1,settingImg*1);
			var mouserData = chart.getPointValue(positionData.x,positionData.y);
			
			<portlet:namespace/>guideCircleMove(mouserData);
			
			chart.addMarkerFromMouse(svg,settingReal*1,settingImg*1,positionData.x,positionData.y,"<portlet:namespace/>stdPoint");
			
			<portlet:namespace/>appendDataPoints("<portlet:namespace/>stdPoint");
			
			<portlet:namespace/>stdPoint = true;
		}
	}
	
	function <portlet:namespace/>virtualPoint(){
		
	}
	
	/*Schematic button select*/
	function <portlet:namespace/>elementSelect(){
		var type = $(".elementMethod>input[name=<portlet:namespace/>element-type]:checked").val();
		var value = <portlet:namespace/>mySlider.getValue();
		<portlet:namespace/>appendSchematicImage(type,value,"","");
	}
	
	/*Schematic img add*/
	function <portlet:namespace/>appendSchematicImage(schematicType,schematicValue,prePointId,currentPointId){
		
		var ol = $("#<portlet:namespace/>schematic-area-action");
		var li = $("<li/>").appendTo(ol);
		$("<img/>").attr("src","${contextPath}/images/smithchart/schematic/"+schematicType+".png").appendTo(li);
// 		<li><img alt="" src="${contextPath}/images/smithchart/schematic/ser_ind.png"/><p>aaaaaaaaa</p></li>
// 		<li><img alt="" src="${contextPath}/images/smithchart/schematic/ser_res.png"/><p>aaaaaaaaa</p></li>
// 		<li><img alt="" src="${contextPath}/images/smithchart/schematic/ser_cap.png"/><p>aaaaaaaaa</p></li>
// 		<li><img alt="" src="${contextPath}/images/smithchart/schematic/sht_ind.png"/><p>aaaaaaaaa</p></li>
// 		<li><img alt="" src="${contextPath}/images/smithchart/schematic/sht_res.png"/><p>aaaaaaaaa</p></li>
// 		<li><img alt="" src="${contextPath}/images/smithchart/schematic/sht_cap.png"/><p>aaaaaaaaa</p></li>
	}
	
	/*DataPoints*/
	function <portlet:namespace/>appendDataPoints(pointId){
		var point = $("#"+pointId); 
		var data = chart.getData(point.attr("real"),point.attr("imaginary"),point.attr("cx"),point.attr("cy"));
		$tbody = $("#<portlet:namespace/>data-point-tbody");
		
		$tr = $("<tr/>").appendTo($tbody);
		$("<td/>").html(data["Z"]).addClass("center").appendTo($tr);
		$("<td/>").html(data["Y"]).addClass("center").appendTo($tr);
		$("<td/>").html(data["Q"]).addClass("center").appendTo($tr);
		$("<td/>").html(data["Reflection"]).addClass("center").appendTo($tr);
		$("<td/>").html("").addClass("center").appendTo($tr);
	}
	
	
	function <portlet:namespace/>clear(){
		
	}
	
	
	function <portlet:namespace/>isValidate(formObject) {
		formObject.validator('validate');
		return formObject.find(".has-error").length === 0;
	}
</script>
