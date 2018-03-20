<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<liferay-portlet:renderURL var="statisticsViewURL" portletMode='view'></liferay-portlet:renderURL>
<liferay-portlet:resourceURL var="getStatisticsExecURL"		id="getStatisticsExec"	escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="excelDownURL"		id="excelDown"	escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="searchSelect"		id="searchSelect"	escapeXml="false" copyCurrentRenderParameters="false"/>

<script type="text/javascript" src="${contextPath}/js/main.js"></script>

<% 
	//Tab Setting
	String tabNames = (String)request.getAttribute("tabNames");
	String visitSite = CustomUtil.strNull(request.getAttribute("visitSite"));
	String tabsValues = (String)request.getAttribute("tabsValues");
	String portletNameSpace = "_"+portletDisplay.getId()+"_"+"tagScript";
%>
<%
	if((Boolean)request.getAttribute("isPortalMain")){
%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>

<div class="contabmenu"> 
	<edison-ui:tabs 
		names="<%=tabNames%>" 
		tabsValues="<%=tabsValues%>" 
		value="<%=visitSite%>" 
		refresh="<%=false%>" 
		onClick="<%=portletNameSpace%>"
		minwidth="150"
	/>
</div>
<div style="clear: both;height:40px;"></div> 
<%
	}
%>

	<div class="container">
		<form class="form-inline" name="<portlet:namespace/>statisticsForm"  method="post" onsubmit="return false;">
			<aui:input type="hidden" name="visitSite" value="<%=visitSite%>"/>
			<aui:input type="hidden" name="status" value="<%=visitSite%>"/>
			<div class="row">
				<div class="col-md-4">
					<h2 style="margin: 0px;"> 
						<img src="${contextPath}/images/sub_tit_bl.png" class="search-main-title-image">
						<span style="margin-left: 10px;"> ${pageTitle} </span> 
					</h2>
				</div>
				<div class="col-md-8 text-right">
					<div class='input-group date'>
						<span class="input-group-addon">
							<span><liferay-ui:message key="begin-date"/></span>
						</span>
						<input type='text' id='<portlet:namespace/>startDt' name="<portlet:namespace/>startDt" readonly="readonly"  class="form-control" value="${preDay}"/>
						<span class="input-group-addon">
							<span class="icon-calendar"></span>
						</span>
					</div>
					<div class='input-group date' >
						<span class="input-group-addon">
							<span><liferay-ui:message key="end-date"/></span>
						</span>
						<input type='text' id='<portlet:namespace/>endDt' name='<portlet:namespace/>endDt' class="form-control" readonly="readonly" value="${toDay}"/>
						<span class="input-group-addon">
							<span class="icon-calendar"></span>
						</span>
						<div class="input-group-btn">
							<button class="btn btn-primary" onclick="<portlet:namespace/>dataSearch();">
								<liferay-ui:message key="edison-button-search" />
							</button>
							<button class="btn btn-primary" onclick="<portlet:namespace/>excelDown();">
								<i class="icon-download"></i>
								<span>  Excel</span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<div style="width:100%;margin-top:20px; ">
		<div id="container1" style="width: 44%; height: 350px; float: left;"></div>
		<div id="container2" style="width: 55%; height: 350px; float: right;"></div>
	</div>
	   
	<div style="clear: both;height:20px;"></div>
	
	<div id="data_wrap" style="clear: both; ">
		<div id="userTable_wrap">
			<div class="table-responsive panel edison-panel">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
					<thead>
						<tr> 
							<th width="8%"><liferay-ui:message key="edison-table-list-header-index" /></th>
							<th width="20%"><liferay-ui:message key="edison-create-account-field-title-university" /></th>
							<th width="8%"><liferay-ui:message key="edison-statistics-user-count" /></th>
							<th width="10%"><liferay-ui:message key="edison-simulation-monitoring-table-header-averate-running-time" /></th>
							<th width="10%"><liferay-ui:message key="edison-science-appstore-view-Execution-count" /></th>
							<th width="12%">CPU Time</th>
						</tr> 
					</thead>
					<tbody id="<portlet:namespace/>userTableBody">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div style="clear: both; width:100%;text-align:right; font-size: 14px; font-weight: bold;">
		<div style="float:right;height:33px;padding-top: 7px;">Result : <span id="totalSpan"></span></div>
	</div>	
	<br><br>
	
	<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl, tabNames, value, scriptName){	
	location.href="<%=statisticsViewURL%>&<portlet:namespace/>thisGroupId="+value;
}

function <portlet:namespace/>dataSearch(){
    bStart();
    setTimeout(function() {
    	var searchForm = $("form[name=<portlet:namespace/>statisticsForm]").serialize();
    	jQuery.ajax({
    		type: "POST",
    		url: "<%=getStatisticsExecURL%>",
    		data: searchForm,
    		async : false,
    		success: function(data) {
    			setTable(data.tableOrganigationList);
    			setPie(data.pieChartOrganigationList);
    			setBar(data.barChartDateList);
    		},error:function(msg){
    			alert("System Exception dataSearch: " + msg);
            },complete: function(){
                bEnd();
            }
    	});
    },500);
}


function setTable(dataList){
	
	$("#<portlet:namespace/>userTableBody tr:not(:has(#1))").remove();				
	
		var rownum = 0;
		if(dataList.length > 0){
			for(var a=0; a<dataList.length; a++){
				$userTableTr = $("<tr/>");
				$("<td/>").addClass("center").html(++rownum).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].affiliation).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].userCnt).appendTo($userTableTr);
				$("<td/>").addClass("center").html(addComma(dataList[a].averageRuntime)).appendTo($userTableTr);
				$("<td/>").addClass("center").html(addComma(dataList[a].jobCnt)).appendTo($userTableTr);
				$("<td/>").addClass("center").html(addComma(dataList[a].cputime)).appendTo($userTableTr);
				
				$("#<portlet:namespace/>userTableBody").append($userTableTr);
				
			}//for 
		}else{
			$("#totalSpan").html("<b>0 <liferay-ui:message key='edison-search-article'/></b>");
			$("<tr/>").append(
								$("<td/>").addClass("center").attr("colspan","6").html('<liferay-ui:message key="edison-there-are-no-data" />')
							).appendTo($("#<portlet:namespace/>userTableBody"));
		}
			
		$("#totalSpan").html("<b>"+rownum+" <liferay-ui:message key='edison-search-article'/></b>");
		}

function setPie(pieChartOrganigationList){	
	//init
	while(chart1.series.length > 0)chart1.series[0].remove(true);
	
	if(pieChartOrganigationList != null){
	
		var seriesData = {
				type:"pie",
				name:'<liferay-ui:message key="percentage" />',
				data:[]
		}
		
		var pieData = [];		
		if(pieChartOrganigationList.length > 0){
			for(var c=0; c<pieChartOrganigationList.length; c++){			
				var title = pieChartOrganigationList[c].affiliation;
				if(pieChartOrganigationList[c].cyberLabName != null && pieChartOrganigationList[c].cyberLabName != ""){
					title += " / " + pieChartOrganigationList[c].cyberLabName;
				}
				if(pieChartOrganigationList[c].className != null && pieChartOrganigationList[c].className != ""){
					title += " / " + pieChartOrganigationList[c].className;
				}			
				var cnt = pieChartOrganigationList[c].jobCnt;	
				pieData.push([title, cnt]);
			}//for
		}//if(pieChartOrganigationList.length > 0){				
		
		seriesData.data = pieData;
		chart1.addSeries(seriesData);
		chart1.redraw();	
	}
}

function setBar(barChartDateList){
	var searchForm = $("form[name=<portlet:namespace/>statisticsForm]").serialize();
	//init
	while(chart2.series.length > 0) chart2.series[0].remove(true);

	var newData=[];
	var xNameAxis = [];
		
	if(barChartDateList != null){
		for(var i=0;i<barChartDateList.length;i++){
			newData.push(barChartDateList[i].monthCnt);
			xNameAxis.push(barChartDateList[i].month);
		}
		chart2.xAxis[0].setCategories(xNameAxis);
		chart2.addSeries({name:'<liferay-ui:message key="edison-science-appstore-view-Execution-count" />',data:newData});
		
		$(chart2.yAxis[0].axisTitle.element).text('<liferay-ui:message key="edison-science-appstore-view-Execution-count" />');
		
		chart2.redraw();			
	}
}


function <portlet:namespace/>excelDown(){
	var url = "<%=excelDownURL%>"+"&"+$("form[name=<portlet:namespace/>statisticsForm]").serialize();
	window.location.href = spaceDelete(url);
}

function dtCheckBox(){
	if($("input:checkbox[name=dtCheck]").is(":checked")){
		$("#dt_wrap").hide();
	}else{
		$("#dt_wrap").show();
	}
}


	var chart1, chart2;
	
	AUI().ready(function() {
		$("#<portlet:namespace/>startDt").datepicker({
			dateFormat:"yy-mm-dd",
			changeMonth: true,
			changeYear: true,
			onClose: function( selectedDate ) {
				$("#<portlet:namespace/>endDt").datepicker("option", "minDate", selectedDate);
			}
			});
	
			$("#<portlet:namespace/>endDt").datepicker({
			dateFormat:"yy-mm-dd",
			changeMonth: true,
			changeYear: true,
			onClose: function( selectedDate ) {
				$("#<portlet:namespace/>startDt").datepicker("option", "maxDate", selectedDate);
			}
			});
	
			$("img.ui-datepicker-trigger").attr("style", "margin-left:2px; vertical-align:middle; cursor: Pointer; width: 18px;");
	
	
	
			chart1 = new Highcharts.Chart({
				chart: {
					renderTo: 'container1',
				type: 'pie'
				},
			title: {
					text: '<liferay-ui:message key="edison-appstore-affiliation-exec-sts"/>'
			    },
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: true,
						formatter: function() {
							return '<b>'+ cutStr(this.point.name,10) +'</b>: '+ this.point.y;
						},
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				}
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage}%</b>',
				percentageDecimals: 1
			}
			});
	
			chart2 = new Highcharts.Chart({
			chart: {
				renderTo: 'container2',
				type: 'line'
				},
			title: {
					text: '<liferay-ui:message key="edison-appstore-month-sts"/>'
			    },
			yAxis: {
				min: 0,
				title: {
				text: 'Total fruit consumption'
				},
				stackLabels: {
					enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			legend: {
				align: 'right',
				x: -100,
				verticalAlign: 'top',
				y: 20,
				floating: true,
				backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
				borderColor: '#CCC',
				borderWidth: 0,
				shadow: true
			},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.x +'</b><br/>'+
					this.series.name +': '+ this.y;
				}
			},
			plotOptions: {
				column: {
				cursor: 'pointer',
				stacking: 'normal',
				dataLabels: {
					enabled: true,
					color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
					}
				}
			}
			});
	
			<portlet:namespace/>dataSearch();
	});


</script>  
  
  