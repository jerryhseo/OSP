<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link rel="stylesheet" href="${contextPath}/css/jquery-confirm/jquery-confirm.min.css">

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




/* The slider itself */
.smith-chart .slider {

    -webkit-appearance: none;  /* Override default CSS styles */
    appearance: none;
    width: 100%; /* Full-width */
    height: 30px; /* Specified height */
    background: #d3d3d3; /* Grey background */
    outline: none; /* Remove outline */
    opacity: 0.7; /* Set transparency (for mouse-over effects on hover) */
    -webkit-transition: .2s; /* 0.2 seconds transition on hover */
    transition: opacity .2s;
}

/* Mouse-over effects */
.smith-chart  .slider:hover {
    opacity: 1; /* Fully shown on mouse-over */
}

/* The slider handle (use -webkit- (Chrome, Opera, Safari, Edge) and -moz- (Firefox) to override default look) */ 
.smith-chart  .slider::-webkit-slider-thumb {
    -webkit-appearance: none; /* Override default look */
    appearance: none;
    width: 25px; /* Set a specific slider handle width */
    height: 25px; /* Slider handle height */
    background: #4CAF50; /* Green background */
    cursor: pointer; /* Cursor on hover */
}

.smith-chart  .slider::-moz-range-thumb {
    width: 25px; /* Set a specific slider handle width */
    height: 25px; /* Slider handle height */
    background: #4CAF50; /* Green background */
    cursor: pointer; /* Cursor on hover */
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
								<input type="text" name="<portlet:namespace/>frequency" id="<portlet:namespace/>frequency" class="form-control" value="500000000" autofocus>
								<span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
								<select class="form-control" name="<portlet:namespace/>frequency-addon" id="<portlet:namespace/>frequency-addon">
					                <option value="1">Hz</option>
					                <option value="1000">kHz</option>
					                <option value="1000000">MHz</option>
					                <option value="1000000000">GHz</option>
					            </select>
							</div>
						</div>
						<div class="form-inline form-group">
							<label class="form-control-static title">Characteristic Impedance(𝒁_𝟎)</label>
							<input type="number" name="<portlet:namespace/>impedance" id="<portlet:namespace/>impedance" class="form-control" value="50" required>
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
					<div class="panel-footer text-center" id="<portlet:namespace/>elements-select" style="display: none;">
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
							<div class="col-md-9">
								<div class="row">
									<label class="form-control-static col-md-2">0 ~ 1</label>
									<div class="col-md-7">
										<input type="range" class="<portlet:namespace/>elements-select-value slider" id="<portlet:namespace/>elements-select-value1" min="0" max="1" step="0.01" value="0" onchange="<portlet:namespace/>elementsSetting()"/>
									</div>
									<div class="col-md-3">
										<input type="text" id="<portlet:namespace/>elements-select-value1-text" value="0" class="form-control" disabled/>
									</div>
								</div>
								<div class="row">
									<label class="form-control-static col-md-2">1 ~ 999</label>
									<div class="col-md-7">
										<input type="range" class="<portlet:namespace/>elements-select-value slider" id="<portlet:namespace/>elements-select-value2" min="1" max="999" step="1" value="0" onchange="<portlet:namespace/>elementsSetting();"/>
									</div>
									<div class="col-md-3">
										<input type="text" id="<portlet:namespace/>elements-select-value2-text" value="0" class="form-control" disabled/>
									</div>
								</div>
							</div>
							<div class="input-group col-md-3" style="margin-top: 15px;">
								<input type="number" id="<portlet:namespace/>elements-select-value-text" class="form-control" onkeyup="<portlet:namespace/>elementsSettingText();" min="0" max="1000" value="1"/>
								<div class="input-group-btn">
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
								<li><img alt="" src="${contextPath}/images/smithchart/schematic/load_impedance.png"/></li>
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
						<th width="25%">Y</th>
						<th width="20%">Q</th>
						<th width="20%">Reflection Coefficient</th>
						<th width="15%">Frequency</th>
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
						<th width="25%">Y</th>
						<th width="20%">Q</th>
						<th width="20%">Reflection Coefficient</th>
						<th width="15%">Frequency</th>
					</tr>
				</thead>
				<tbody id="<portlet:namespace/>data-point-tbody">
				</tbody>
			</table>
		</div>
</div>

<!--D3 JS-->
<script src="${contextPath}/js/lib/d3.min.js"></script>
<script src="${contextPath}/js/lib/d3.v4.min.js"></script>
<!--Smith JS-->
<script src="${contextPath}/js/smithchart/smith.js"></script>

<!--math JS-->
<script src="${contextPath}/js/mathjs/math.min.js"></script>

<!--bootstrap validation JS-->
<script src="${contextPath}/js/validation/validator.min.js"></script>

<script src="${contextPath}/js/jquery-confirm/jquery-confirm.min.js"></script>

<script type="text/javascript">
	//기준점 Point 체크 여부
	var <portlet:namespace/>stdPoint = false;
	var chart = new smith.chart();
	var svg;
	$(function() {
		svg = d3.select("#chart").append('svg');
		chart.draw(svg);
		
		svg.select("#svg_circle").on("mousemove",function(e){
			var coords = d3.mouse(this);
			var mouserData = chart.getPointValue(Math.floor(coords[0]),Math.floor(coords[1]));
			<portlet:namespace/>guideCircleMove(mouserData);
			
			var impedance = $("#<portlet:namespace/>impedance").val();
			var data = chart.getData(mouserData.real["value"],mouserData.imaginary["value"],Math.floor(coords[0]),Math.floor(coords[1]),impedance);
			
			var frequency = $("#<portlet:namespace/>frequency").val()
			var frequencyAddon = $("#<portlet:namespace/>frequency-addon option:selected").html();
			
			$("#<portlet:namespace/>cursor_z").html(data["Z"]);
			$("#<portlet:namespace/>cursor_y").html(data["Y"]);
			$("#<portlet:namespace/>cursor_q").html(data["Q"]);
			$("#<portlet:namespace/>cursor_reflection").html(data["Reflection"]);
			$("#<portlet:namespace/>cursor_frequency").html(frequency+"  "+frequencyAddon);

// 			e.stopPropagation();
		});
		
		svg.select("#svg_circle").on("click",function(e){
			if(!<portlet:namespace/>stdPoint){
				var coords = d3.mouse(this);
				var mouserData = chart.getPointValue(Math.floor(coords[0]),Math.floor(coords[1]));
				
				
				chart.addMarkerFromMouse(svg,mouserData.real["value"],mouserData.imaginary["value"],Math.floor(coords[0]),Math.floor(coords[1]),"<portlet:namespace/>stdPoint","<portlet:namespace/>stdPoint");
				
				<portlet:namespace/>appendDataPoints("<portlet:namespace/>stdPoint");
				
				<portlet:namespace/>stdPoint = true;
			}else{
				<portlet:namespace/>clear();
			}
// 			e.stopPropagation();
		});
		
		$(".elementMethod>input[name=<portlet:namespace/>element-type]").change(function(e){
			if(<portlet:namespace/>stdPoint){
				if(!$("#<portlet:namespace/>elements-select").is(':visible')){
					$("#<portlet:namespace/>elements-select" ).show();
				}
				<portlet:namespace/>virtualPoint();
			}
			
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
		if(<portlet:namespace/>stdPoint){
			<portlet:namespace/>clear();
		}else{
			if (<portlet:namespace/>isValidate($("#<portlet:namespace/>settingsform"))) {
				var impedance = $("#<portlet:namespace/>impedance").val();
				var settingReal = $("#<portlet:namespace/>real").val()/impedance;
				var settingImg = $("#<portlet:namespace/>imaginary").val()/impedance;
				
				var positionData = chart.getPointPosition(settingReal,settingImg);
				var mouserData = chart.getPointValue(positionData.x,positionData.y);
				
				<portlet:namespace/>guideCircleMove(mouserData);
				
				chart.addMarkerFromMouse(svg,settingReal*1,settingImg*1,positionData.x,positionData.y,"<portlet:namespace/>stdPoint","<portlet:namespace/>stdPoint");
				
				<portlet:namespace/>appendDataPoints("<portlet:namespace/>stdPoint");
				
				<portlet:namespace/>stdPoint = true;
			}
		}
		
	}
	
	/*Schematic button select*/
	function <portlet:namespace/>elementSelect(){
		var type = $(".elementMethod>input[name=<portlet:namespace/>element-type]:checked").val();
		var value = $("#<portlet:namespace/>elements-select-value-text").val();
		
		var virtualPoint = $("#<portlet:namespace/>virtualPoint");
		var real = virtualPoint.attr("real");
		var imag = virtualPoint.attr("imaginary")
		var mouseX = virtualPoint.attr("cx");
		var mouseY = virtualPoint.attr("cy");
		
		var id = "<portlet:namespace/>point_"+virtualPoint.attr("index");
		var className = "<portlet:namespace/>point";
		chart.addMarkerFromMouse(svg,real,imag,mouseX,mouseY,id,className);
		
		
		<portlet:namespace/>appendSchematicImage(virtualPoint.attr("pre_id"),id,type,value);
		
		virtualPoint.remove();
		
		<portlet:namespace/>appendLine(virtualPoint.attr("pre_id"),id,type);
		
		<portlet:namespace/>appendDataPoints(id);
	}
	
	function <portlet:namespace/>elementsSetting(){
		if(<portlet:namespace/>stdPoint){
			var elementSum = 0;
			$(".<portlet:namespace/>elements-select-value").each(function(index,e){
				elementSum += $(this).val()*1;
				$("#"+$(this).attr("id")+"-text").val($(this).val());
			})
			
			$("#<portlet:namespace/>elements-select-value-text").val(elementSum);
			
			<portlet:namespace/>virtualPoint();
		}
	}
	
	function <portlet:namespace/>elementsSettingText(){
		if(<portlet:namespace/>stdPoint){
			var elementText = $("#<portlet:namespace/>elements-select-value-text");
			var elementValue = elementText.val();
			
			var elementStr = elementValue.toString();
			
			var index = elementStr.indexOf('.');
			var floorValue = 0;
			var intValue = 0;
			if(index>0){
				floorValue = "0."+elementStr.substring(index+1,index+3);
				intValue = elementStr.substring(0,index);
			}else{
				intValue = elementStr;
			}
			
			$("#<portlet:namespace/>elements-select-value1").val(floorValue);
			$("#<portlet:namespace/>elements-select-value1-text").val(floorValue);
			
			$("#<portlet:namespace/>elements-select-value2").val(intValue);
			$("#<portlet:namespace/>elements-select-value2-text").val(intValue);
			
			<portlet:namespace/>virtualPoint();
		}
	}
	
	function <portlet:namespace/>virtualPoint(){
		if(<portlet:namespace/>stdPoint){
			var frequency = $("#<portlet:namespace/>frequency").val()*$("#<portlet:namespace/>frequency-addon option:selected").val();
			var impedance = $("#<portlet:namespace/>impedance").val();
			var elementValue = $("#<portlet:namespace/>elements-select-value-text").val()*1;
			var elementType = $(".elementMethod>input[name=<portlet:namespace/>element-type]:checked").val();
			
			var stdPoint;
			var idSize = 1;
			/*해당 Element의 최근 Point값 체크*/
			var length = $(".<portlet:namespace/>point").length;
			if(length >0){
				stdPoint = $("#<portlet:namespace/>point_"+length);
				idSize += length;
			}else{
				stdPoint = $("#<portlet:namespace/>stdPoint");
			}
			
			var cReal = stdPoint.attr("real");
			var cImag = stdPoint.attr("imaginary");
			
			var virtualPointData = chart.getElementPoint(frequency,impedance,cReal,cImag,elementValue,elementType);
			
			var positionData = chart.getPointPosition(virtualPointData["real"],virtualPointData["imaginary"]);
			var mouserData = chart.getPointValue(positionData.x,positionData.y);
			
			if($("#<portlet:namespace/>virtualPoint").length>0){
				$("#<portlet:namespace/>virtualPoint").attr('cx',positionData.x)
			      .attr('cy',positionData.y)
			      .attr('real',virtualPointData["real"])
			      .attr('imaginary',virtualPointData["imaginary"])
			      .attr('pre_id',stdPoint.attr("id"));
			}else{
				svg.append('circle')
			      .attr('fill','#EE8888')
			      .attr('cx',positionData.x)
			      .attr('cy',positionData.y)
			      .attr('real',virtualPointData["real"])
			      .attr('imaginary',virtualPointData["imaginary"])
			      .attr('index',idSize)
			      .attr('pre_id',stdPoint.attr("id"))
			      .attr('r',5)
			      .attr('id',"<portlet:namespace/>virtualPoint")
			      .attr("stroke-width", 2);
			}
			
		}
	}
	
	/*Schematic img add*/
	function <portlet:namespace/>appendSchematicImage(prePointId,currentPointId,elementType,elementValue){
		
		var ol = $("#<portlet:namespace/>schematic-area-action");
		var li = $("<li/>").appendTo(ol);
		
		var prePoint = $("#"+prePointId);
		var currentPoint = $("#"+currentPointId);
		var preReal = prePoint.attr("real");
		var preImag = prePoint.attr("imaginary");
		var currentReal = currentPoint.attr("real");
		var currentImag = currentPoint.attr("imaginary");
		var fre = $("#<portlet:namespace/>frequency").val()*$("#<portlet:namespace/>frequency-addon option:selected").val();
		
		console.log(prePointId,currentPointId,preReal,preImag,currentReal,currentImag,elementType,fre);
		var msgObject = chart.getSchematicText(elementType,elementValue);
		
		$("<img/>").attr("src","${contextPath}/images/smithchart/schematic/"+elementType+".png").appendTo(li);
		$("<p/>").html(msgObject["msg"]).appendTo(li);
	}
	
	
	
	function <portlet:namespace/>appendLine(prePointId,currentPointId,elementType){
		var preMouseX = $("#"+prePointId).attr("cx");
		var preMouseY = $("#"+prePointId).attr("cy");
		var curMouserX = $("#"+currentPointId).attr("cx");
		var cutMouseY = $("#"+currentPointId).attr("cy");
		
		var pointLineCircle = chart.addPointLine(preMouseX,preMouseY,curMouserX,cutMouseY,elementType);
		
		var path = d3.path();
		console.log(pointLineCircle);
		path.arc(pointLineCircle["cx"], pointLineCircle["cy"], pointLineCircle["r"],pointLineCircle["startAngle"],pointLineCircle["endAngle"],pointLineCircle["anticlockwise"]);
		
		svg.append("path").attr("d", path.toString()).attr("stroke", "firebrick")
		.attr("stroke-width", 2)
		.attr("fill", "none");
	}
	
	/*DataPoints*/
	function <portlet:namespace/>appendDataPoints(pointId){
		var point = $("#"+pointId); 
		var impedance = $("#<portlet:namespace/>impedance").val();
		var data = chart.getData(point.attr("real"),point.attr("imaginary"),point.attr("cx"),point.attr("cy"),impedance);
		$tbody = $("#<portlet:namespace/>data-point-tbody");
		
		var frequency = $("#<portlet:namespace/>frequency").val()
		var frequencyAddon = $("#<portlet:namespace/>frequency-addon option:selected").html();
		
		$tr = $("<tr/>").appendTo($tbody);
		$("<td/>").html(data["Z"]).addClass("center").appendTo($tr);
		$("<td/>").html(data["Y"]).addClass("center").appendTo($tr);
		$("<td/>").html(data["Q"]).addClass("center").appendTo($tr);
		$("<td/>").html(data["Reflection"]).addClass("center").appendTo($tr);
		$("<td/>").html(frequency+"  "+frequencyAddon).addClass("center").appendTo($tr);
	}
	
	
	function <portlet:namespace/>clear(){
		$.confirm({
			title: 'Data Clear',
			content: '새로운 기준점을 생성 하시겠습니까? <br/> 해당 정보는 저장되지 않습니다.',
			buttons: {
				confirm: function () {
					location.reload();
				},
				cancel: function () {
					
				}
			}
		});
	}
	
	
	function <portlet:namespace/>isValidate(formObject) {
		formObject.validator('validate');
		return formObject.find(".has-error").length === 0;
	}
</script>
