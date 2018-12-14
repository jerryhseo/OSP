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

/* div.tooltip {	
	position: absolute;			
	text-align: center;			
	background: lightsteelblue;	
	border: 0px;		
	border-radius: 8px;			
	pointer-events: none;
    padding: 10px;
    width: auto;
    height: auto;
} */

div.tooltip {
	line-height: 1;
	padding: 6px;
	background: rgba(0, 0, 0, 0.8);
	color: #fff;
	border-radius: 4px;
	font-size: 12px;
}
 
/* Creates a small triangle extender for the tooltip */
div.tooltip:after {
	box-sizing: border-box;
	display: block;
	font-size: 10px;
	width: 100%;
	line-height: 1;
	color: rgba(0, 0, 0, 0.8);
	content: "\25BC";
	position: absolute;
	text-align: center;
	top: 100%;
    left: 0;
    margin: -2px 0 0 0;
}

</style>
<div class="h20"></div>
<div class="row">
    <div class="col-md-6 text-center" id="chart">
        
    </div>
    <div class="col-md-6">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default" id="<portlet:namespace/>plot-setting-area">
                    <div class="panel-heading clearfix ">
                        <h2 class="panel-title">Settings</h2>
                    </div>
                    <div class="panel-body">
                        <div class="form-inline form-group">
                            <label class="form-control-static title">Frequency</label>
                            <div class="input-group">
                                <input type="text" name="<portlet:namespace/>frequency" id="<portlet:namespace/>frequency" class="form-control" value="5" autofocus>
                                <span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
                                <select class="form-control" name="<portlet:namespace/>frequency-addon" id="<portlet:namespace/>frequency-addon">
                                    <option value="1">Hz</option>
                                    <option value="1000">kHz</option>
                                    <option value="1000000">MHz</option>
                                    <option value="1000000000" selected="selected">GHz</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-inline form-group">
                            <label class="form-control-static title">Characteristic Impedance(?íÅ_?üé)</label>
                            <input type="number" name="<portlet:namespace/>impedance" id="<portlet:namespace/>impedance" class="form-control" value="50" required>
                        </div>
                        <form action="" id="<portlet:namespace/>settingsform" onsubmit="return false;">
                            <div class="form-inline form-group">
                                <label class="form-control-static title">Load Impedance(?íÅ_?ë≥)</label>
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
                            <%-- <div class="col-md-9">
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
                            <div class="input-group col-md-12" style="margin-top: 15px;">
                                <input type="number" id="<portlet:namespace/>elements-select-value-text" class="form-control" onkeyup="<portlet:namespace/>elementsSettingText();" min="0" max="1000" value="1"/>
                                <div class="input-group-btn">
                                    <button class="btn btn-primary" onclick="<portlet:namespace/>elementSelect();return false;">
                                        <span class="icon-signal">
                                            Select
                                        </span>
                                    </button>
                                </div>
                            </div> --%>
                            
                            
                            <div class="input-group col-md-12" style="margin-top: 15px;">
                                <input type="text" id="<portlet:namespace/>elements-select-value-text" class="form-control" value="1" readonly="readonly"/>
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
    //Í∏∞Ï???Point Ï≤¥ÌÅ¨ ?¨Î?
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

			//e.stopPropagation();
        });
        
        svg.select("#svg_circle").on("click",function(e){
            if(!<portlet:namespace/>stdPoint){
                var coords = d3.mouse(this);
                var mouserData = chart.getPointValue(Math.floor(coords[0]),Math.floor(coords[1]));
                
                chart.addMarkerFromMouse(svg, mouserData.real["value"], mouserData.imaginary["value"], Math.floor(coords[0]), Math.floor(coords[1]), "<portlet:namespace/>stdPoint", "<portlet:namespace/>stdPoint");
                <portlet:namespace/>readOnlyPlotSettings();
                <portlet:namespace/>appendDataPoints("<portlet:namespace/>stdPoint");
                <portlet:namespace/>stdPoint = true;
                
            }else{
                <portlet:namespace/>clear();
            }
			//e.stopPropagation();
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
                
                var positionData = chart.getPointPosition(settingReal, settingImg);
                var mouserData = chart.getPointValue(positionData.x,positionData.y);
                
                <portlet:namespace/>guideCircleMove(mouserData);
                chart.addMarkerFromMouse(svg, settingReal*1, settingImg*1, positionData.x, positionData.y, "<portlet:namespace/>stdPoint", "<portlet:namespace/>stdPoint");
                <portlet:namespace/>readOnlyPlotSettings();
                <portlet:namespace/>appendDataPoints("<portlet:namespace/>stdPoint");
                <portlet:namespace/>stdPoint = true;
            }
        }
        
    }
    
    
    /* Settings - plot : read-only */
	function <portlet:namespace/>readOnlyPlotSettings(){
		if(!<portlet:namespace/>stdPoint){
			var $plotSettingArea = $('#<portlet:namespace/>plot-setting-area');
			var $select = $plotSettingArea.find('select');
			
			$plotSettingArea.find('input').prop('readonly', true);
			$plotSettingArea.find('select option[value!="'+$select.val()+'"]').remove();
			$select.css({	'background-color' : '#eeeeee', 'opacity': 1	});
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
        
        <portlet:namespace/>clearVirtualGroup();
        <portlet:namespace/>appendSchematicImage(virtualPoint.attr("pre_id"),id,type,value);
        virtualPoint.remove();
        <portlet:namespace/>appendLine(virtualPoint.attr("pre_id"), id, type);
        <portlet:namespace/>appendDataPoints(id);
    }
    
    
    
    function <portlet:namespace/>virtualPoint(){
    	
        if(<portlet:namespace/>stdPoint){
            <portlet:namespace/>clearVirtualGroup();
        	
            //element info
            var frequency = $('#<portlet:namespace/>frequency').val() * $('#<portlet:namespace/>frequency-addon option:selected').val();
            var impedance = $('#<portlet:namespace/>impedance').val();
            var elementType = $('.elementMethod>input[name=<portlet:namespace/>element-type]:checked').val();
            var elementValue = <portlet:namespace/>maxElementValueByElementType(elementType);
            
            //prev point info
            var stdPoint;
            var idSize = 1;
            var length = $('.<portlet:namespace/>point').length;
            if(length > 0){
                stdPoint = $('#<portlet:namespace/>point_'+length);
                idSize += length;
            }else{
                stdPoint = $('#<portlet:namespace/>stdPoint');
            }
            
            var stdPointCx = stdPoint.attr('cx');
            var stdPointCy = stdPoint.attr('cy');
            var stdPointReal = stdPoint.attr('real');
            var stdPointImaginary = stdPoint.attr('imaginary');
            var stdPointMouserData = chart.getPointValue(stdPointCx, stdPointCy);
            
            
            //path info and create path object
            var endPointData = chart.getElementPoint(frequency, impedance, stdPointReal, stdPointImaginary, elementValue, elementType);
            var endPointPositionData = chart.getPointPosition(endPointData['real'], endPointData['imaginary']);
            var pointLineCircle = chart.addPointLine(stdPointCx, stdPointCy, endPointPositionData.x, endPointPositionData.y, elementType);
            var path = d3.path();
            path.arc(pointLineCircle['cx'], pointLineCircle['cy'], pointLineCircle['r'], pointLineCircle['startAngle'], pointLineCircle['endAngle'], pointLineCircle['anticlockwise']);
			
            
            var arcGroup = svg.append('svg:g')
                .attr('id', '<portlet:namespace/>virtualGroup');
            
            var arcPath = arcGroup.append('path')
            	.attr('clip-path', 'url(#chart-area)')
                .attr('id', '<portlet:namespace/>virtualPath')
				.attr('fill', 'none')
				.attr('stroke', '#02ff3e')
				.attr('stroke-width', 3)
				.attr('stroke-dasharray', '5, 1')
				.attr('d', path.toString());
            
            
            var pointCircle = arcGroup.append('circle')
                .attr('id', '<portlet:namespace/>virtualPoint')
                .attr('r', 6)
                .attr('fill', '#02ff3e')
                .attr('stroke', 'none')
                .attr('cx', stdPointCx)
                .attr('cy', stdPointCy)
                .attr('real', stdPointReal)
                .attr('imaginary', stdPointImaginary)
                .attr('index', idSize)
                .attr('pre_id', stdPoint.attr('id'))
                .attr('cursor', 'move')
                .call(d3.drag().on('drag', dragged));
            
                
			//tooltip ?ùÏÑ±
        	var tooltip = d3.select('body').append('div')
        		.attr('id', '<portlet:namespace/>tooltip')
        		.attr('class', 'tooltip')
        		.style('opacity', 1);
			
			
        	//set element value
			$('#<portlet:namespace/>elements-select-value-text').val(<portlet:namespace/>getConvertSchematicValue(elementType, stdPointMouserData, stdPointMouserData));
        	
			//set tooltip info
			<portlet:namespace/>changeVirtualTooltip(stdPointReal, stdPointImaginary);
			
			
			//drag ?¥Î≤§???®Ïàò
			function dragged(d) {
				var $circle = d3.select(this);
                var m = d3.mouse(this);
                
                arcPath.transition()
                    .duration(50)
                    .attrTween('d', function(d){
                    	
                        var arcPathNode = d3.select(this).node();
                        var pos = <portlet:namespace/>findClosestPoint(arcPathNode, m);
                    	
                    	//clip-path circle infomation
                    	var $clipCircle = d3.select('#chart-area circle');
                    	var px = $clipCircle.attr('cx');
                    	var py = $clipCircle.attr('cy');
                    	var pr = $clipCircle.attr('r')
                        
                        //Î∞òÏ?Î¶ÑÏù¥ Í∞ôÏùÑ Í≤ΩÏö∞, drag stop
                      	if(<portlet:namespace/>pointDistance(px, py, pos[0], pos[1]) > pr){
                        	return false;
                        }else{
                            var mouserData = chart.getPointValue(Math.floor(pos[0]), Math.floor(pos[1]));
                            $circle
                                .attr('cx', pos[0])
                                .attr('cy', pos[1])
                                .attr('real', mouserData.real['value'])
								.attr('imaginary', mouserData.imaginary['value']);
                            
                            //set element value
                            $('#<portlet:namespace/>elements-select-value-text').val(<portlet:namespace/>getConvertSchematicValue(elementType, mouserData, stdPointMouserData));
							
                            //set tooltip info
                            <portlet:namespace/>changeVirtualTooltip(mouserData.real['value'], mouserData.imaginary['value']);	
                        }
				})
			}
			
			
			//tooltip info Î≥ÄÍ≤?
			function <portlet:namespace/>changeVirtualTooltip(real, imaginary){
				var offset = $('#<portlet:namespace/>virtualPoint').offset();
				var top = Number(offset.top - 56);
				var left = Number(offset.left - 43);
				
				if(real !== '' && imaginary !== ''){
					tooltip.html('real : ' + real + '</br>imaginary : ' + imaginary);
				}
				
				tooltip
					.style('top',	function(d){	return top + 'px'	})
					.style('left',	function(d){	return left + 'px'	});
		    }
			
			//window resize tooltip position
			$( window ).resize(function() {
				<portlet:namespace/>changeVirtualTooltip('', '');
	       	});
        }
    }
    
    
    //VirtualGroup clear
    function <portlet:namespace/>clearVirtualGroup(){
    	$('#<portlet:namespace/>virtualGroup, #<portlet:namespace/>tooltip').remove();
    }
    
    
    //element Type Î≥?element value
    function <portlet:namespace/>maxElementValueByElementType(elementType){
        if(elementType === 'ser_cap' || elementType === 'sht_ind' || elementType === 'sht_res' ){
        	return 0.001;
        }else{
        	return 1000;
  	  	}
    }
    
    
    //?êÏ†ê ?¨Ïù¥??Í±∞Î¶¨
	function <portlet:namespace/>pointDistance(x1, y1, x2, y2) {
		var width = Math.abs(x1 - x2);
		var height = Math.abs(y1 - y2);
		var lineLength = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
		
		return Math.floor(lineLength);
	}
    
    
    //virtual point value => element valueÎ°?Î≥Ä??
    function <portlet:namespace/>getConvertSchematicValue(elementType, currMouserData, prevMouserData){
    	
		var result = '';
    	var frequency = parseFloat($('#<portlet:namespace/>frequency').val() * $('#<portlet:namespace/>frequency-addon option:selected').val());
    	var impedance = parseFloat($("#<portlet:namespace/>impedance").val());
    	
    	var current_real = parseFloat(currMouserData.real['value']) * impedance;
    	var current_imaginary = parseFloat(currMouserData.imaginary['value']) * impedance;
    	var previous_real = parseFloat(prevMouserData.real['value']) * impedance;
    	var previous_imaginary = parseFloat(prevMouserData.imaginary['value']) * impedance;

    	console.log('#################################');
    	console.log(prevMouserData);
    	console.log(' currReal : ' + current_real);
    	console.log(' currImaginary : ' + current_imaginary);
    	console.log(' prevReal : ' + previous_real);
    	console.log(' prevImaginary : ' + previous_imaginary);
    	console.log('#################################');
    	
		
		switch (elementType) {
			case 'ser_cap' :
				result = 'C = ' + ((-1 / (2 * math.pi * frequency * (current_imaginary - previous_imaginary))) * math.pow(10, 12)).toFixed(5);
				break;
				
			case 'ser_ind' :
				result = 'L = ' + ((current_imaginary - previous_imaginary) / (2 * math.pi * frequency)*math.pow(10, 9)).toFixed(5);
				break;
				
			case 'ser_res' :
				result = 'R = ' + ((current_real - previous_real)).toFixed(5);
				break;
				
			case 'sht_cap' :
                var admin_current_real = current_real / (math.pow(current_real, 2) + math.pow(current_imaginary, 2));
                var admin_current_imaginary = -1 * current_imaginary / (math.pow(current_real, 2) + math.pow(current_imaginary, 2));
                var admin_previous_real = previous_real / (math.pow(previous_real, 2) + math.pow(previous_imaginary, 2));
                var admin_previous_imaginary = -1 * previous_real / (math.pow(previous_real, 2) + math.pow(previous_imaginary, 2));
	             
				result = 'C = ' + ((admin_current_imaginary - admin_previous_imaginary) / (2 * math.pi * frequency)*math.pow(10,12)).toFixed(5);
				break;
				
			case 'sht_ind' :
				var admin_current_real = current_real / (math.pow(current_real, 2) + math.pow(current_imaginary, 2));
                var admin_current_imaginary = -1 * current_imaginary / (math.pow(current_real, 2) + math.pow(current_imaginary, 2));
                var admin_previous_real = previous_real / (math.pow(previous_real, 2) + math.pow(previous_imaginary, 2));
                var admin_previous_imaginary = -1 * previous_real / (math.pow(previous_real, 2) + math.pow(previous_imaginary, 2));
				
				result = 'L = ' + ((-1 / (2 * math.pi * frequency * (admin_current_imaginary - admin_previous_imaginary))) * math.pow(10, 9)).toFixed(5);
				break;
				
			case 'sht_res' :
				var admin_current_real = current_real / (math.pow(current_real, 2) + math.pow(current_imaginary, 2));
                var admin_current_imaginary = -1 * current_imaginary / (math.pow(current_real, 2) + math.pow(current_imaginary, 2));
                var admin_previous_real = previous_real / (math.pow(previous_real, 2) + math.pow(previous_imaginary, 2));
                var admin_previous_imaginary = -1 * previous_real / (math.pow(previous_real, 2) + math.pow(previous_imaginary, 2));
                var admin_resistor = 1 / (admin_current_real - admin_previous_real);
	             
				result = 'R = ' + (admin_resistor).toFixed(5);
			break;
		}
		
		
		if(result !== ''){
			var msgObject = chart.getSchematicText(elementType, result);
			result = msgObject['msg'];
		}
		
		return result;
    }
    
    
    // mouse point??Í∞Ä??Í∞ÄÍπåÏö¥ path??point Íµ¨ÌïòÍ∏?
    function <portlet:namespace/>findClosestPoint(pathNode, point) {
        var pathLength = pathNode.getTotalLength();
        var precision = 8;
        var best;
		var bestLength;
		var bestDistance = Infinity;

		for (
				var scan, scanLength = 0, scanDistance;
				scanLength <= pathLength;
				scanLength += precision
		){
			
			if ((scanDistance = <portlet:namespace/>distanceCalculation(scan = pathNode.getPointAtLength(scanLength))) < bestDistance) {
				best = scan;
				bestLength = scanLength;
				bestDistance = scanDistance;
			}
		}

		precision /= 2;
		while (precision > 0.5) {
			var before;
			var after;
			var beforeLength;
			var afterLength;
			var beforeDistance;
			var afterDistance;
			
            if(
            		(beforeLength = bestLength - precision) >= 0
            	&&	(beforeDistance = <portlet:namespace/>distanceCalculation(before = pathNode.getPointAtLength(beforeLength))) < bestDistance
            ){
				best = before;
				bestLength = beforeLength;
				bestDistance = beforeDistance;
				
            }else if(
            		(afterLength = bestLength + precision) <= pathLength
           		&&	(afterDistance = <portlet:namespace/>distanceCalculation(after = pathNode.getPointAtLength(afterLength))) < bestDistance
           	){
				best = after;
				bestLength = afterLength;
				bestDistance = afterDistance;
				
            }else{
				precision /= 2;
			}
		}
	
		best = [best.x, best.y];
		best.distance = Math.sqrt(bestDistance);
		
		return best;
		
		
	    function <portlet:namespace/>distanceCalculation(p) {
			var dx = p.x - point[0];
			var dy = p.y - point[1];
			
			return dx * dx + dy * dy;
		}
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*Schematic img add*/
    function <portlet:namespace/>appendSchematicImage(prePointId, currentPointId, elementType, elementValue){
        
        var ol = $("#<portlet:namespace/>schematic-area-action");
        var li = $("<li/>").appendTo(ol);
        var prePoint = $("#"+prePointId);
        var currentPoint = $("#"+currentPointId);
        var preReal = prePoint.attr("real");
        var preImag = prePoint.attr("imaginary");
        var currentReal = currentPoint.attr("real");
        var currentImag = currentPoint.attr("imaginary");
        var fre = $("#<portlet:namespace/>frequency").val()*$("#<portlet:namespace/>frequency-addon option:selected").val();
        //var msgObject = chart.getSchematicText(elementType,elementValue);

        $("<img/>").attr("src","${contextPath}/images/smithchart/schematic/"+elementType+".png").appendTo(li);
        $("<p/>").html(elementValue).appendTo(li);
        //$("<p/>").html(msgObject["msg"]).appendTo(li);
    }
    
    
    
    function <portlet:namespace/>appendLine(prePointId, currentPointId, elementType){
        var preMouseX = $("#"+prePointId).attr("cx");
        var preMouseY = $("#"+prePointId).attr("cy");
        var curMouserX = $("#"+currentPointId).attr("cx");
        var cutMouseY = $("#"+currentPointId).attr("cy");
        var pointLineCircle = chart.addPointLine(preMouseX, preMouseY, curMouserX, cutMouseY, elementType);
        var path = d3.path();
        
        path.arc(pointLineCircle["cx"], pointLineCircle["cy"], pointLineCircle["r"], pointLineCircle["startAngle"], pointLineCircle["endAngle"], pointLineCircle["anticlockwise"]);
        
        svg.append("path")
            .attr("d", path.toString())
            .attr('stroke', '#2196F3')
			.attr('stroke-width', 3)
			.attr('stroke-dasharray', '5, 1')
            .attr("fill", "none");
    }
    
    
    /*DataPoints*/
    function <portlet:namespace/>appendDataPoints(pointId){
        var point = $("#"+pointId); 
        var impedance = $("#<portlet:namespace/>impedance").val();
        var data = chart.getData(point.attr("real"), point.attr("imaginary"), point.attr("cx"), point.attr("cy"), impedance);
        var frequency = $("#<portlet:namespace/>frequency").val()
        var frequencyAddon = $("#<portlet:namespace/>frequency-addon option:selected").html();
        
        $tbody = $("#<portlet:namespace/>data-point-tbody");
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
            content: '?àÎ°ú??Í∏∞Ï??êÏùÑ ?ùÏÑ± ?òÏãúÍ≤†Ïäµ?àÍπå? <br/> ?¥Îãπ ?ïÎ≥¥???Ä?•ÎêòÏßÄ ?äÏäµ?àÎã§.',
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
