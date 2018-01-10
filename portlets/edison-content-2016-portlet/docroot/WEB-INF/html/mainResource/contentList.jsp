<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<style type="text/css">
	.edison-mainResource{
		background-color: #adcbe3;
	}
	.edison-mainResource .fillgauge {
		width: 100%;
	}
	.edison-mainResource .clusterName {
		text-align: center;
		color: #000000;
		margin-bottom: 40px;
	}
	.edison-mainResource .title {
		text-align: center;
		color: #FFFFFF;
		font-size: 30px;
		margin: 40px 0px;
	}
</style>

<div class="container">
	<div class="row">
		<div class="col-lg-12 title">
			SYSTEM RESOURCE STATISTICS
		</div>		
	</div>
	<div class="row">
		<div class="col-xs-4 col-md-2">
			<svg id="fillgauge1" class="fillgauge"></svg>
			<div id="clusterName1" class="clusterName"></div>
		</div>
		<div class="col-xs-4 col-md-2">
			<svg id="fillgauge2" class="fillgauge"></svg>
			<div id="clusterName2" class="clusterName"></div>
		</div>
		<div class="col-xs-4 col-md-2">
			<svg id="fillgauge3" class="fillgauge"></svg>
			<div id="clusterName3" class="clusterName"></div>
		</div>
		<div class="col-xs-4 col-md-2">
			<svg id="fillgauge4" class="fillgauge"></svg>
			<div id="clusterName4" class="clusterName"></div>
		</div>
		<div class="col-xs-4 col-md-2">
			<svg id="fillgauge5" class="fillgauge"></svg>
			<div id="clusterName5" class="clusterName"></div>
		</div>
		<div class="col-xs-4 col-md-2">
			<svg id="fillgauge6" class="fillgauge"></svg>
			<div id="clusterName6" class="clusterName"></div>
		</div>
	</div>
</div>

<script src="http://d3js.org/d3.v2.min.js" language="JavaScript"></script>
<script src="${contextPath}/js/liquidFillGauge.js" language="JavaScript"></script>
<script type="text/javascript">
	<c:forEach items="${clusterList}" var="item" varStatus="status">
		var amountUsingResource = "${item.usage}";
		var config = liquidFillGaugeDefaultSettings();
		config.circleColor = "#F9F9F9";
		config.waveColor = "#5BC8A0";
		config.waveAnimateTime = 1000;
		config.waveHeight = 0.1;
		if(0 < amountUsingResource){
			config.minValue = 10;
		}
		
		var gauge = loadLiquidFillGauge("fillgauge${status.count}", amountUsingResource, config);
		
		$("#clusterName${status.count}").text("${item.clusterName}");
	</c:forEach>
</script>
