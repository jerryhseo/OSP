<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="graphFileActionURL" id="graphFileAction" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>



<style type="text/css">
.smith-chart div.panel-body label.title{
	width: 25%;
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
						<div class="form-inline form-group">
							<label class="form-control-static title">Load Impedance(𝒁_𝑳)</label>
							<label class="form-control-static">Real</label>
							<input type="text" name="<portlet:namespace/>real" id="<portlet:namespace/>real" class="form-control" >
							<label class="form-control-static">Imaginary</label>
							<input type="text" name="<portlet:namespace/>imaginary" id="<portlet:namespace/>imaginary" name="passband-freq-L" class="form-control" >
						</div>
					</div>
					<div class="panel-footer text-center">
						<button class="btn btn-primary">
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
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
							<div class="col-md-2">
								<img alt="" src="${contextPath}/images/smithchart/buttons/ser_cap.png" class="img-responsive"/>
								<p class="text-center">Ser Cap</p>
							</div>
							<div class="col-md-2">
								<img alt="" src="${contextPath}/images/smithchart/buttons/ser_ind.png" class="img-responsive"/>
								<p class="text-center">Ser Ind</p>
							</div>
							<div class="col-md-2">
								<img alt="" src="${contextPath}/images/smithchart/buttons/ser_res.png" class="img-responsive"/>
								<p class="text-center">Ser Req</p>
							</div>
							<div class="col-md-2">
								<img alt="" src="${contextPath}/images/smithchart/buttons/sht_cap.png" class="img-responsive"/>
								<p class="text-center">Ser Cap</p>
							</div>
							<div class="col-md-2">
								<img alt="" src="${contextPath}/images/smithchart/buttons/sht_ind.png" class="img-responsive"/>
								<p class="text-center">Ser Ind</p>
							</div>
							<div class="col-md-2">
								<img alt="" src="${contextPath}/images/smithchart/buttons/sht_res.png" class="img-responsive"/>
								<p class="text-center">Ser Res</p>
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
								<ol id="SchematicAreaAction">
									<li><img alt="" src="${contextPath}/images/smithchart/schematic/ser_ind.png"/><p>aaaaaaaaa</p></li>
									<li><img alt="" src="${contextPath}/images/smithchart/schematic/ser_res.png"/><p>aaaaaaaaa</p></li>
									<li><img alt="" src="${contextPath}/images/smithchart/schematic/ser_cap.png"/><p>aaaaaaaaa</p></li>
									<li><img alt="" src="${contextPath}/images/smithchart/schematic/sht_ind.png"/><p>aaaaaaaaa</p></li>
									<li><img alt="" src="${contextPath}/images/smithchart/schematic/sht_res.png"/><p>aaaaaaaaa</p></li>
									<li><img alt="" src="${contextPath}/images/smithchart/schematic/sht_cap.png"/><p>aaaaaaaaa</p></li>
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
				<tbody>
					<tr>
						<td class="center">0</td>
						<td class="center">0</td>
						<td class="center">0</td>
						<td class="center">0</td>
						<td class="center">0</td>
					</tr>
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

<script type="text/javascript">
	//기준점 Point 체크 여부
	var <portlet:namespace/>stdPont = false;
	$(function() {
		var svg = d3.select("#chart").append('svg');
		var chart = new smith.chart();
		chart.draw(svg);
		
		svg.select("#svg_circle").on("mousemove",function(e){
			var coords = d3.mouse(this);
			var mouserData = chart.getPointValue(Math.floor(coords[0]),Math.floor(coords[1]));
			
			var data = chart.getData(mouserData.real["value"],mouserData.imaginary["value"],Math.floor(coords[0]),Math.floor(coords[1]));
			
			$("#mouse_round").attr("cx",mouserData.real["mouse_x_cx"]).attr("r",mouserData.real["mouse_x_r"]);
			$("#mouse_curve").attr("cy",mouserData.imaginary["mouse_y_cy"]).attr("r",mouserData.imaginary["mouse_y_r"]);
			
			$("#<portlet:namespace/>real").val(mouserData.real["value"]);
			$("#<portlet:namespace/>imaginary").val(mouserData.imaginary["value"]);
			
			$("#<portlet:namespace/>cursor_z").html(data["Z"]);
			$("#<portlet:namespace/>cursor_y").html(data["Y"]);
			$("#<portlet:namespace/>cursor_q").html(data["Q"]);
			$("#<portlet:namespace/>cursor_reflection").html(data["Reflection"]);
// 			$("#<portlet:namespace/>cursor_frequency").html(data["Z"]);
			
		});
	});
</script>
