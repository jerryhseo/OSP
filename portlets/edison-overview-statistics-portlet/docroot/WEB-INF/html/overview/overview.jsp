<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<liferay-portlet:resourceURL var="getSiteUserTotalCountURL" id="getSiteUserTotalCount" escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getSiteUserForChartURL" id="getSiteUserForChart" escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getExecuteUserTotalCountURL" id="getExecuteUserTotalCount" escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getExecuteUserForChartURL" id="getExecuteUserForChart" escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getSimulationJobTotalCountURL" id="getSimulationJobTotalCount" escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getSimulationJobForChartURL" id="getSimulationJobForChart" escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getScienceAppTotalCountURL" id="getScienceAppTotalCount" escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getScienceAppForChartURL" id="getScienceAppForChart" escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getContentsTotalCountURL" id="getContentsTotalCount" escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getContentsForChartURL" id="getContentsForChart" escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getCitationsTotalCountURL" id="getCitationsTotalCount" escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getCitationsForChartURL" id="getCitationsForChart" escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getCategoryTotalCountURL" id="getCategoryTotalCount" escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getCategoryForChartURL" id="getCategoryDataForChart" escapeXml="false" copyCurrentRenderParameters="false"/>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<script type="text/javascript" src="${contextPath}/js/plotly/plotly.js"></script>
<script type="text/javascript" src="${contextPath}/js/plotly/plotly-basic.js"></script>

<style>
	.<portlet:namespace/>overview-category{
		width: 15.9%;
		float: left;
		list-style: none;
		font-size: 32px;
		color: #383838;
		text-align: center;
		margin-right: 0.9%;
		border: solid 1px #9cbfd6;
		font-weight: 500;
		padding-top: 30px;
		padding-bottom: 30px;
		background-color: #fcfcfc;
		cursor: pointer;
	}
	
	.<portlet:namespace/>overview-category:hover,
	.<portlet:namespace/>overview-category.selected{
		width: 15.9%;
		float: left;
		list-style: none;
		font-size: 32px;
		color: #383838;
		text-align: center;
		margin-right: 0.95%;
		border: solid 5px #cdcaf1;
		font-weight: 500;
		padding-top: 27px;
		padding-bottom: 30px;
		background-color: #d6f9fd;
	}
	
	.<portlet:namespace/>overview-category-icon{
		/* float: left;
		width: 25%;
		font-size: 35px;
		text-align: center; */
	}
	
	.<portlet:namespace/>overview-category-content{
		/* float: left;
		width: 70%;
		padding-left: 10%; */
	}
	
	.<portlet:namespace/>overview-category-title{
		font-size: 14px;
		color: #98999b;
		padding: 7px 0 20px 0;
	}
	
	.<portlet:namespace/>overview-chart{
		text-align: center;
		min-height: 350px !important;
		padding-left: 0px !important;
		padding-right: 0px !important;
	}
	
</style>

<div style="clear: both;height:40px;"></div> 
<div class="container">
	<form class="form-inline" name="<portlet:namespace/>statisticsForm"  method="post" onsubmit="return false;">
		<%-- <aui:input type="hidden" name="visitSite" value="<%=visitSite%>"/> --%>
		<div class="row">
			<div class="col-md-4">
				<h2 style="margin: 0px;"> 
					<img src="${contextPath}/images/sub_tit_bl.png" class="search-main-title-image">
					<span style="margin-left: 10px;">Overview Statistics</span> 
				</h2>
			</div>
		</div>
	</form>
</div>

<div style="width:100%;margin-top:20px; ">
	<div id="<portlet:namespace/>overviewChar_2" class="<portlet:namespace/>overview-chart col-md-5">
	</div>
	<div id="<portlet:namespace/>overviewChar_1" class="<portlet:namespace/>overview-chart col-md-7">
	</div>
	<div id="<portlet:namespace/>overviewChar_3" class="<portlet:namespace/>overview-chart col-md-12" style="display: none;">
	</div>
</div>

<div class="h40"></div>

<div id="<portlet:namespace/>overviewCategory">
	<div>
		<div id="<portlet:namespace/>overviewSiteUserTab" class="<portlet:namespace/>overview-category" onclick="<portlet:namespace/>getCategoryTotalCount('siteUser', 1)">
			<div class="<portlet:namespace/>overview-category-icon">
				<img src="${contextPath}/images/category/icon01.png">
				<div class="<portlet:namespace/>overview-category-title">
					<liferay-ui:message key='edison-overview-statistics-site-user-message' />&nbsp;
					<liferay-ui:icon-help message="edison-overview-statistics-site-user-help-message"/>
				</div>
			</div>
			<div class="<portlet:namespace/>overview-category-content">
				<div id="<portlet:namespace/>overviewSiteUser" class="<portlet:namespace/>overview-category-value"> 
				</div>
			</div>
		</div>
	</div>
	<div>
		<div id="<portlet:namespace/>overviewExecuteUserTab" class="<portlet:namespace/>overview-category" onclick="<portlet:namespace/>getCategoryTotalCount('executeUser', 1)">
			<div class="<portlet:namespace/>overview-category-icon">
				<img src="${contextPath}/images/category/icon02.png">
				<div class="<portlet:namespace/>overview-category-title">
					<liferay-ui:message key='edison-overview-statistics-execute-user-message' />&nbsp;
					<liferay-ui:icon-help message="edison-overview-statistics-execute-user-help-message"/>
				</div>
			</div>
			<div class="<portlet:namespace/>overview-category-content">
				<div id="<portlet:namespace/>overviewExecuteUser" class="<portlet:namespace/>overview-category-value"> 
				</div>
			</div>
		</div>
	</div>
	<div>
		<div id="<portlet:namespace/>overviewSimulationJobTab" class="<portlet:namespace/>overview-category" onclick="<portlet:namespace/>getCategoryTotalCount('simulationJob', 1)">
			<div class="<portlet:namespace/>overview-category-icon">
				<img src="${contextPath}/images/category/icon03.png">
				<div class="<portlet:namespace/>overview-category-title">
					<liferay-ui:message key='edison-overview-statistics-simulation-job-message' />
				</div>
			</div>
			<div class="<portlet:namespace/>overview-category-content">
				<div id="<portlet:namespace/>overviewSimulationJob" class="<portlet:namespace/>overview-category-value"> 
				</div>
			</div>
		</div>
	</div>
	<div>
		<div id="<portlet:namespace/>overviewScienceAppTab" class="<portlet:namespace/>overview-category" onclick="<portlet:namespace/>getCategoryTotalCount('scienceApp', 1)">
			<div class="<portlet:namespace/>overview-category-icon">
				<img src="${contextPath}/images/category/icon04.png">
				<div class="<portlet:namespace/>overview-category-title">
					<liferay-ui:message key='edison-overview-statistics-science-app-message' />&nbsp;
				</div>
			</div>
			<div class="<portlet:namespace/>overview-category-content">
				<div id="<portlet:namespace/>overviewScienceApp" class="<portlet:namespace/>overview-category-value"> 
				</div>
			</div>
		</div>
	</div>
	<div>
		<div id="<portlet:namespace/>overviewContentsTab" class="<portlet:namespace/>overview-category" onclick="<portlet:namespace/>getCategoryTotalCount('contents', 1)">
			<div class="<portlet:namespace/>overview-category-icon">
				<img src="${contextPath}/images/category/icon05.png">
				<div class="<portlet:namespace/>overview-category-title">
					<liferay-ui:message key='edison-overview-statistics-contents-message' />&nbsp;
				</div>
			</div>
			<div class="<portlet:namespace/>overview-category-content">
				<div id="<portlet:namespace/>overviewContents" class="<portlet:namespace/>overview-category-value"> 
				</div>
			</div>
		</div>
	</div>
	<div>
		<div id="<portlet:namespace/>overviewCitationsTab" class="<portlet:namespace/>overview-category" onclick="<portlet:namespace/>getCategoryTotalCount('citations', 1)" style="margin-right: 0px;">
			<div class="<portlet:namespace/>overview-category-icon">
				<img src="${contextPath}/images/category/icon06.png">
				<div class="<portlet:namespace/>overview-category-title">
					<liferay-ui:message key='edison-overview-statistics-citations-message' />&nbsp;
				</div>
			</div>
			<div class="<portlet:namespace/>overview-category-content">
				<div id="<portlet:namespace/>overviewCitations" class="<portlet:namespace/>overview-category-value"> 
				</div>
			</div>
		</div>
	</div>
</div>

<div class="h20"></div>
	
<br><br>
<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
	$( document ).ready(function() {
	
		<portlet:namespace/>getCategoryTotalCount("siteUser", 1);
		<portlet:namespace/>getCategoryTotalCount("executeUser", 0);
		<portlet:namespace/>getCategoryTotalCount("simulationJob", 0);
		<portlet:namespace/>getCategoryTotalCount("scienceApp", 0);
		<portlet:namespace/>getCategoryTotalCount("contents", 0);
		<portlet:namespace/>getCategoryTotalCount("citations", 0);
		
	});
	
	/* Get Category Total Count */
	function <portlet:namespace/>getCategoryTotalCount(searchCategory, drawChart){
		
		var overviewCategoryValue = null;
		var selectedOverviewTab = null;
		var textSuffix = Liferay.Language.get("edison-overview-statistics-count");
		if(searchCategory == 'siteUser'){
			overviewCategoryValue = $("#<portlet:namespace/>overviewSiteUser");
			textSuffix = Liferay.Language.get("edison-overview-statistics-people");
			selectedOverviewTab = $("#<portlet:namespace/>overviewSiteUserTab");
		} else if(searchCategory == 'executeUser'){
			overviewCategoryValue = $("#<portlet:namespace/>overviewExecuteUser");
			textSuffix = Liferay.Language.get("edison-overview-statistics-people");
			selectedOverviewTab = $("#<portlet:namespace/>overviewExecuteUserTab");
		} else if(searchCategory == 'simulationJob'){
			overviewCategoryValue = $("#<portlet:namespace/>overviewSimulationJob");
			selectedOverviewTab = $("#<portlet:namespace/>overviewSimulationJobTab");
		} else if(searchCategory == 'scienceApp'){
			overviewCategoryValue = $("#<portlet:namespace/>overviewScienceApp");
			selectedOverviewTab = $("#<portlet:namespace/>overviewScienceAppTab");
		} else if(searchCategory == 'contents'){
			overviewCategoryValue = $("#<portlet:namespace/>overviewContents");
			selectedOverviewTab = $("#<portlet:namespace/>overviewContentsTab");
		} else if(searchCategory == 'citations'){
			overviewCategoryValue = $("#<portlet:namespace/>overviewCitations");
			selectedOverviewTab = $("#<portlet:namespace/>overviewCitationsTab");
		}
		
		var getCategoryTotalCount = 0;
		
		var sendData = {
			"<portlet:namespace/>searchCategory" : searchCategory
		};
		
		$.ajax({
			type: "POST",
			url: "<%=getCategoryTotalCountURL%>",
			async : false,
			data : sendData,
			success: function(data) {
				getCategoryTotalCount = data.getCategoryTotalCount;
				
				resultText = getCategoryTotalCount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				overviewCategoryValue.text(resultText);
			},error:function(msg){
				alert("System Exception dataSearch: " + msg);
			},complete: function(){
			}
		});
		
		if(drawChart==1){
			$(".<portlet:namespace/>overview-category").removeClass("selected");
			selectedOverviewTab.addClass("selected");
			<portlet:namespace/>getCategoryCountForChart(searchCategory, "MONTH");
		}
	}
	
	/* Get Category Data For Chart */
	function <portlet:namespace/>getCategoryCountForChart(searchCategory, dateFormat){
		
		/* ChartTitle */
		var barChartTitle = "";
		var pieChartTitle = "";
		
		/* Bar Chart Data */
		var chartCategoryByDateArr = new Array();
		var barChartData = new Array();
		var lineChartData = new Array();
		var hasBarChartData = false;
		
		/* Pie Chart Data */
		var chartCategoryBySiteArr = new Array();
		var viewDataBySite = new Array();
		var hasPieChartData = false;
		
		if(searchCategory == 'siteUser'){
			barChartTitle = Liferay.Language.get("edison-overview-statistics-site-user-bar-chart-title");
			pieChartTitle = Liferay.Language.get("edison-overview-statistics-site-user-pie-chart-title");
		} else if(searchCategory == 'executeUser'){
			barChartTitle = Liferay.Language.get("edison-overview-statistics-execute-user-bar-chart-title");
		} else if(searchCategory == 'simulationJob'){
			barChartTitle = Liferay.Language.get("edison-overview-statistics-simulation-job-bar-chart-title");
			pieChartTitle = Liferay.Language.get("edison-overview-statistics-simulation-job-pie-chart-title");
		} else if(searchCategory == 'scienceApp'){
			barChartTitle = Liferay.Language.get("edison-overview-statistics-science-app-bar-chart-title");
			pieChartTitle = Liferay.Language.get("edison-overview-statistics-science-app-pie-chart-title");
		} else if(searchCategory == 'contents'){
			barChartTitle = Liferay.Language.get("edison-overview-statistics-contents-bar-chart-title");
			pieChartTitle = Liferay.Language.get("edison-overview-statistics-contents-pie-chart-title");
		} else if(searchCategory == 'citations'){
			barChartTitle = Liferay.Language.get("edison-overview-statistics-citations-bar-chart-title");
			pieChartTitle = Liferay.Language.get("edison-overview-statistics-citations-pie-chart-title");
		}
		var sendData = {
			"<portlet:namespace/>dateFormat" : dateFormat,
			"<portlet:namespace/>searchCategory" : searchCategory,
			"<portlet:namespace/>pieChartCategory" : "${pieChartCategory}"
		}
		
		bStart();
		setTimeout(function(){
			$.ajax({
				type: "POST",
				url: "<%=getCategoryForChartURL%>",
				async : false,
				data : sendData,
				success: function(data) {
					getCategoryDataByDate = data.getCategoryDataByDate;
					getCategoryDataBySite = data.getCategoryDataBySite;
					
					if(getCategoryDataByDate != null && 0 < getCategoryDataByDate.length){
						hasBarChartData = true;
						for(var i=0; i<getCategoryDataByDate.length; i++){
							categoryDataByDateMap = getCategoryDataByDate[i];
							
							chartCategoryByDateArr[i] = categoryDataByDateMap.createDate;
							barChartData[i] = categoryDataByDateMap.count;
							lineChartData[i] = categoryDataByDateMap.cumulativeCount;
						}
					} else {
						hasBarChartData = false;
					}
					
					if(getCategoryDataBySite != null && 0 < getCategoryDataBySite.length){
						hasPieChartData = true;
						for(var i=0; i<getCategoryDataBySite.length; i++){
							categoryDataBySiteMap = getCategoryDataBySite[i];
							
							chartCategoryBySiteArr[i] = categoryDataBySiteMap.siteName;
							viewDataBySite[i] = categoryDataBySiteMap.count;
						}
					} else {
						hasPieChartData = false;
					}
					
					/* Draw chart */
					if(hasBarChartData){
						<portlet:namespace/>drawBarChart(chartCategoryByDateArr, barChartData, lineChartData, barChartTitle, hasPieChartData);
					}
					if(hasPieChartData){
						<portlet:namespace/>drawPieChart(chartCategoryBySiteArr, viewDataBySite, pieChartTitle);
					}
					
					if(!hasBarChartData && !hasPieChartData){
						$("#<portlet:namespace/>overviewChar_1").hide();
						$("#<portlet:namespace/>overviewChar_2").hide();
						
						var dataEmptyMsg = $("<div/>").text(Liferay.Language.get("edison-workflow-data-empty-message")).css("padding", "10% 0%").css("font-size", "18px");
						$("#<portlet:namespace/>overviewChar_3").html("");
						$("#<portlet:namespace/>overviewChar_3").append(dataEmptyMsg).show();
					}
					
				},error:function(msg){
					alert("System Exception dataSearch: " + msg);
				},complete: function(){
					bEnd();
				}
			});
		}, 500);
		
	}
	
	/* Draw Bar chart */
	function <portlet:namespace/>drawBarChart(chartCategory, barChartData, lineChartData, chartTitle, hasPieChartData){
		var barChartData = {
			type: 'bar',
			x: chartCategory,
			y: barChartData,
			name: Liferay.Language.get("edison-overview-statistics-bar-chart-category-name")
		}
		
		var lineChartData = {
			type: 'scatter',
			x: chartCategory,
			y: lineChartData,
			name: Liferay.Language.get("edison-overview-statistics-line-chart-category-name")
		}

		var data = [barChartData, lineChartData];
		var layout = {
			title: chartTitle,
			plot_bgcolor: '#2E2E2E',
			paper_bgcolor: '#2E2E2E',
			font: {
				color: '#fff'
			},
			xaxis:{
				exponentformat : 'none',
				color: '#fff'
			},
			yaxis:{
				exponentformat : 'none',
				color: '#fff'
			},
			legend: {
				orientation: 'h',
				xanchor: 'center',
				y: -0.2,
				x: 0.5
			}
		};
		
		if(hasPieChartData){
			$("#<portlet:namespace/>overviewChar_1").show();
			$("#<portlet:namespace/>overviewChar_2").show();
			$("#<portlet:namespace/>overviewChar_3").html("").hide();
			Plotly.newPlot('<portlet:namespace/>overviewChar_1', data, layout);
		} else {
			$("#<portlet:namespace/>overviewChar_1").hide();
			$("#<portlet:namespace/>overviewChar_2").hide();
			$("#<portlet:namespace/>overviewChar_3").html("").show();
			Plotly.newPlot('<portlet:namespace/>overviewChar_3', data, layout);
		}
	}
	
	/* Draw Pie chart */
	function <portlet:namespace/>drawPieChart(chartCategory, viewData, chartTitle){
		var chartData = {
			values: viewData,
			labels: chartCategory,
			domain: {column: 0},
			hoverinfo: 'label+value',
			hole: .3,
			type: 'pie'
		}

		var data = [chartData];

		var layout = {
			title: chartTitle,
			width: 490,
			grid: {rows: 1, columns: 1},
			showlegend: false,
			plot_bgcolor: '#2E2E2E',
			paper_bgcolor: '#2E2E2E',
			font: {
				color: '#fff',
			},
			annotations: [
				{
					font: {
						size: 14,
						color:'#fff'
					},
					text: "",
					showarrow: false
				}
			],
			color : '#000'
		}

		Plotly.newPlot('<portlet:namespace/>overviewChar_2', data, layout);
	}
	
</script>
