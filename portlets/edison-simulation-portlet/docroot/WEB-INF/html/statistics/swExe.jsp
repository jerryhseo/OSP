<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<script type="text/javascript" src="${contextPath}/js/main.js"></script>

<liferay-portlet:renderURL var="statisticsViewURL" portletMode='view'></liferay-portlet:renderURL>
<liferay-portlet:resourceURL var="getStatisticsSwExeURL"		id="getStatisticsSwExe"	escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="excelDownURL"		id="excelDown"	escapeXml="false" copyCurrentRenderParameters="false"/>

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

<style>
	.<portlet:namespace/>category{
		cursor: pointer;
		text-align: center;
	}
	
</style>

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
	<div style="clear: both;height:20px;"></div>
	
	<h2>
		<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png" />
		<liferay-ui:message key='edison-statistics-app-exec' />
	</h2>
	
	<div class="h10"></div>
	
	<div class="tabletoptab">
		<input class="box01" type="text" id="<portlet:namespace/>startDt" name="<portlet:namespace/>startDt" readonly="readonly" value="${preDay}"/> 
			~	<input class="box01" type="text" id="<portlet:namespace/>endDt" name="<portlet:namespace/>endDt" readonly="readonly" value="${toDay}"/>
	</div>
	
	<div class="search03">
		<input type="button" name="fullsize" id="fullsize" value="<liferay-ui:message key="edison-button-search" />" class="btn btn-default" onclick="<portlet:namespace/>dataSearch();"/>
	</div>
	
	<div style="clear: both;height:20px;"></div>
	
	<h3>Science App</h3>
	<div style="clear: both;height:20px;"></div>
	
	<div id="<portlet:namespace/>categoryList">
		<!-- 카테고리 출력 -->
		<c:if test="${categoryList ne '' and categoryList ne null}">
			<table id="<portlet:namespace/>categoryTable" style="width: 100%;">
				<tr style="border-left: 1px solid #e5e5e5; border-right: 1px solid #e5e5e5;">
					<c:forEach var="category" items="${categoryList}" varStatus="status">
						<c:if test="${0 < status.index}">
							<td style="width: 1px; padding: 20px 0px; border: none;">
								<div>
									<img src="${contextPath}/images/categ_divline01.gif" />
								</div>
							</td>
						</c:if>
						<td category_id="${category.categoryId}" category_name="${category.categoryTitle}" parentCategory_id="${category.parentCategoryId}" class="<portlet:namespace/>category" width="<fmt:formatNumber value='${100/fn:length(categoryList)}' pattern='.0' />%">
							<div>
								<img id="<portlet:namespace/>categoryImage_${category.categoryId}" src="" style="margin: 15px 0px 0px; height: 76px; max-width: 100px;" />
							</div>
							<div style="height: 55px; margin: 0px auto;">
								${category.categoryTitle}
							</div>
						</td>
					</c:forEach>
				</tr>
			</table>
		</c:if>
	</div>
	
	<div style="clear: both;height:20px;"></div>
	
	<%
		}
	%>
	<div class="tabletopbox clear">
		<form name="<portlet:namespace/>statisticsForm" method="post">
			<input type="hidden" name="<portlet:namespace/>status" id="<portlet:namespace/>status">
			<input type="hidden" name="<portlet:namespace/>visitSite" id="<portlet:namespace/>visitSite" value="<%=visitSite%>">
			<input type="hidden" name="<portlet:namespace/>scienceAppName" id="<portlet:namespace/>scienceAppName" value="${scienceAppName}">
			<input type="hidden" name="<portlet:namespace/>parentGroupId" id="<portlet:namespace/>parentGroupId" value="">
			<input type="hidden" name="<portlet:namespace/>categoryId" id="<portlet:namespace/>categoryId" value="">
			
			<%-- <div class="tabletoptab">
		  		<input class="box01" type="text" id="<portlet:namespace/>startDt" name="<portlet:namespace/>startDt" readonly="readonly" value="${preDay}"/> 
					~	<input class="box01" type="text" id="<portlet:namespace/>endDt" name="<portlet:namespace/>endDt" readonly="readonly" value="${toDay}"/>
			</div>
			
			<div class="search03">
				<input type="button" name="fullsize" id="fullsize" value="<liferay-ui:message key="edison-button-search" />" class="btn btn-default" onclick="<portlet:namespace/>dataSearch();"/>
			</div> --%>
		</form>
	</div>
	
	
	<div style="width:100%;margin-top:20px; ">
		<div id="container1" style="width: 55%; height: 350px; float: left;"></div>
		<div id="container2" style="width: 45%; height: 350px; float: right;"></div>
	</div>
	
	<div style="clear: both; width:100%;text-align:right; font-size: 14px; font-weight: bold;margin-bottom: 5px;">
		<br>
		<div class="boardbtn2" style="float:right;">
			<input type="button" name="fullsize" id="fullsize" value="Excel Download" class="btn btn-default" onClick="excelDown()"/>
		</div>
	</div>
	<br> <br>
		
	<div class="table-responsive panel edison-panel ">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="실행 현황 테이블" class="table table-bordered table-hover edison-table">
			<colgroup>
				<col width="10%" />
				<col width="*" />
				<col width="20%" />
				<col width="10%" />
				<col width="7%" />
				<col width="7%" />
				<col width="10%" />
				<col width="7%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-index" /></th>
					<th scope="col"><liferay-ui:message key="edison-appstore-solver-name"/></th>
					<th scope="col"><liferay-ui:message key="edison-create-account-field-title-university"/></th>
					<th scope="col"><liferay-ui:message key="edison-virtuallab-owner"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-version"/></th>
					<th scope="col"><liferay-ui:message key="edison-statistics-user-count"/></th>
					<th scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-averate-running-time"/><br/>(sec)</th>
					<th scope="col"><liferay-ui:message key="edison-science-appstore-view-Execution-count"/></th>
					<th scope="col"><liferay-ui:message key="edison-table-list-header-date"/></
				</tr>
			</thead>
			<tbody id="<portlet:namespace/>userTableBody">
			</tbody>
		</table>
	</div>
	
	<div style="clear: both; width:100%;text-align:right; font-size: 14px; font-weight: bold;margin-bottom:5px;">
		<div style="float:right;height:33px;padding-top: 7px;">Result : <span id="totalSpan"></span></div>
	</div>
	
	<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl, tabNames, value, scriptName){
	var parameters = "";
	var scienceAppName = "${scienceAppName}";
	parameters += "&<portlet:namespace/>thisGroupId="+value;
	
	if(scienceAppName != ""){
		parameters += "&<portlet:namespace/>scienceAppName="+scienceAppName;
	}
	
	location.href="<%=statisticsViewURL%>"+parameters;
}

function <portlet:namespace/>dataSearch(){
    bStart();
	var searchForm = $("form[name=<portlet:namespace/>statisticsForm]").serialize();
	
	jQuery.ajax({
		type: "POST",
		url: "<%=getStatisticsSwExeURL%>",
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
}


function setTable(dataList){
	
	$("#<portlet:namespace/>userTableBody tr:not(:has(#1))").remove();				
	
	if(dataList != null){
		var rownum = 0;
		if(dataList.length > 0){
			for(var a=0; a<dataList.length; a++){
				$userTableTr = $("<tr/>");
				$("<td/>").addClass("center").html(dataList.length-rownum).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].scienceApp_name).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].scienceApp_affiliation_name).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].mgtName).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].scienceApp_version).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].user_count).appendTo($userTableTr);
				$("<td/>").addClass("center").html(addComma(dataList[a].averageRuntime)).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].exe_count).appendTo($userTableTr);
				$("<td/>").addClass("center").html(dataList[a].mgtDate).appendTo($userTableTr);
				
				$("#<portlet:namespace/>userTableBody").append($userTableTr);
				++rownum
			}//for 
		} else {
			$("<tr/>").append(
					$("<td/>").addClass("center").attr("colspan","9").html('<liferay-ui:message key="edison-there-are-no-data" />')
				).appendTo("#<portlet:namespace/>userTableBody");
		}
	}else{

		$("<tr/>").append(
							$("<td/>").addClass("center").attr("colspan","9").html('<liferay-ui:message key="edison-there-are-no-data" />')
						).appendTo("#<portlet:namespace/>userTableBody");
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
				var title = pieChartOrganigationList[c].scienceApp_name;			
				var cnt = pieChartOrganigationList[c].exe_count;	
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
			newData.push(barChartDateList[i].count);
			xNameAxis.push(barChartDateList[i].month);
		}
		chart2.xAxis[0].setCategories(xNameAxis);
		chart2.addSeries(
				{name:'<liferay-ui:message key="edison-science-appstore-view-Execution-count" />',data:newData}
				);
		
// 		$(chart2.yAxis[0].axisTitle.element).text('<liferay-ui:message key="edison-science-appstore-view-Execution-count" />');
		
		chart2.redraw();			
	}
}

function excelDown(){
	var url = "<%=excelDownURL%>"+"&"+$("form[name=<portlet:namespace/>statisticsForm]").serialize();
	window.location.href = spaceDelete(url);
}


	var chart1, chart2;
	
	AUI().ready(function() {
		$("#<portlet:namespace/>startDt").datepicker({
			showButtonPanel: true,
			showOn: 'button',
			dateFormat:"yy-mm-dd",
			changeMonth: true,
			changeYear: true,
			buttonImage: "${contextPath}/images/calendar.png",
			buttonImageOnly: true,
			onClose: function( selectedDate ) {
				$("#<portlet:namespace/>endDt").datepicker("option", "minDate", selectedDate);
			}
			});
	
			$("#<portlet:namespace/>endDt").datepicker({
			showButtonPanel: true,
			showOn: 'button',
			dateFormat:"yy-mm-dd",
			changeMonth: true,
			changeYear: true,
			buttonImage: "${contextPath}/images/calendar.png",
			buttonImageOnly: true,
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
				text: '<liferay-ui:message key="edison-appstore-search-sts"/>',
	            percentageDecimals: 1
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
				text: '<liferay-ui:message key="edison-appstore-month-sts"/>',
			    },
			yAxis: {
				min: 0,
				title: {
					text: '<liferay-ui:message key="edison-science-appstore-view-Execution-count" />',
				},
				labels: {
		                format: '{value}'
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
					this.series.name +': '+ addComma(this.y);
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
	
	$(".<portlet:namespace/>category").on("click", <portlet:namespace/>categoryClickHandler);
	
	function <portlet:namespace/>categoryClickHandler(e){
		var parentGroupId = $(this).attr("parentCategory_id");
		var categoryId = $(this).attr("category_id");
		var categoryName = $(this).attr("category_name");
		
		$("#<portlet:namespace/>scienceAppName").val(categoryName);
		$("#<portlet:namespace/>categoryId").val(categoryId);
		$("#<portlet:namespace/>parentGroupId").val(parentGroupId);
		
		<portlet:namespace/>dataSearch();
		
		$("#<portlet:namespace/>scienceAppName").val("");
		$("#<portlet:namespace/>categoryId").val("");
		$("#<portlet:namespace/>parentGroupId").val("");
	}
	
	var <portlet:namespace/>categoriesInitData = ${categoriesJsonString};
	$(document).ready(function(){
		
		$.each(<portlet:namespace/>categoriesInitData, function(i) {
			var that = this;
			$("#<portlet:namespace/>categoryImage_"+that.categoryId).attr("src","${contextPath}/images/solverType/"+that.image+".png");
		});
		
	});
	
	
</script>
