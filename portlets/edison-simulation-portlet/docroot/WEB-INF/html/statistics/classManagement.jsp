<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<%
//Tab Setting
	String tabNames =  CustomUtil.strNull(request.getAttribute("tabNames"));
	String selectedGroupId = CustomUtil.strNull(request.getAttribute("selectedGroupId"));
	String tabsValues =  CustomUtil.strNull(request.getAttribute("tabsValues"));
	String portletNameSpace = "_"+portletDisplay.getId()+"_"+"tagScript";
%>
<head>
<liferay-portlet:renderURL var="statisticsViewURL" portletMode='view'></liferay-portlet:renderURL>
<liferay-portlet:resourceURL var="classStatisticsListURL" 	escapeXml="false"	 id="classStatisticsList"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="classStatisticsExcelDownLoadURL"		id="classStatisticsExcelDownLoad"	escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="saveClickTab" id="cickTab" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateClassStatisticsURL" id="updateClassStatistics" copyCurrentRenderParameters="false" escapeXml="false"/>

<script src="${contextPath}/js/Highcharts-4.2.7/js/highcharts.js"></script>
<script src="${contextPath}/js/Highcharts-4.2.7/js/highcharts-more.js"></script>
<script src="${contextPath}/js/Highcharts-4.2.7/js/highcharts-3d.js"></script>
<script src="${contextPath}/js/Highcharts-4.2.7/js/modules/exporting.js"></script>
<script src="${contextPath}/js/Highcharts-4.2.7/js/themes/gray.js"></script>

<script type="text/javascript">


AUI().ready(function() {
	<portlet:namespace/>dataSearch();
	
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
});
function <portlet:namespace/>dataSearch(){
	bStart();
	var searchForm = $("form[name=<portlet:namespace/>statisticsForm]").serialize();
	
	$.ajax({
		type: "POST",
		url: "<%=classStatisticsListURL%>",
		async:true,
		data: searchForm,
		success:function(data){
			var statisticsList = data.dataList;
			var $rowResult;
			$("#classStatisticsListBody tr:not(:has(#1))").remove();
			
			var university = "";//
			var universityId = "";
			var lab = "";
			var labId = "";
			var classCount = 0;//기관 rowspan 하기위해 클래스 개수 구하기
			var labCount = 0;
			if(statisticsList.length != 0) {
				for(var i = 0; i < statisticsList.length; i++) {
					$rowResult = $("<tr/>").appendTo("#classStatisticsListBody");

					var nowUniversity = statisticsList[i].university;
					var nowUniversityId = statisticsList[i].universityId;
					
					var nowLabId = statisticsList[i].virtualLabId;
					var nowLab = statisticsList[i].virtualLabTitle;
					
					if(university != nowUniversity){
						
						if(classCount != 0){
							$("#<portlet:namespace/>"+universityId).attr("rowspan", classCount);
							if(labCount != 0){
								$("#<portlet:namespace/>"+universityId+"_"+labId).attr("rowspan", labCount);
							} 
						}
						university = nowUniversity;
						universityId = nowUniversityId;
						lab=nowLab;
						labId = nowLabId;
						$("<td/>").addClass("center").attr("id","<portlet:namespace/>"+universityId).text(nowUniversity).css("text-align","center").appendTo($rowResult);
						
						var checkThisClass = $("<input/>").addClass("<portlet:namespace/>checkClass").attr("type", "checkbox").attr("name", "<portlet:namespace/>checkClass").attr("value", labId);
						$("<td/>").addClass("center").append(checkThisClass).appendTo($rowResult);
						$("<td/>").addClass("center").attr("id","<portlet:namespace/>"+universityId+"_"+labId).text(nowLab).css("text-align","center").appendTo($rowResult);
						classCount = 0;
						labCount = 0;
					}else {//기관이 같음 
						if(lab != nowLab){//가상실험실이 다른경우
							if(labCount != 0){//rowspan
								$("#<portlet:namespace/>"+universityId+"_"+labId).attr("rowspan", labCount);
							}
							lab=nowLab;
							labId = nowLabId;
							var checkThisClass = $("<input/>").addClass("<portlet:namespace/>checkClass").attr("type", "checkbox").attr("name", "<portlet:namespace/>checkClass").attr("value", labId);
							$("<td/>").addClass("center").append(checkThisClass).appendTo($rowResult);
							$("<td/>").addClass("center").attr("id","<portlet:namespace/>"+universityId+"_"+labId).text(nowLab).css("text-align","center").appendTo($rowResult);
							
							labCount = 0;
						}						
					} 
					
					$("<td/>").addClass("center").text(statisticsList[i].classTitle).css("text-align","center").appendTo($rowResult);
					$("<td/>").addClass("center").text(statisticsList[i].virtualLabPersonName).css("text-align","center").appendTo($rowResult);
					$("<td/>").addClass("center").text(statisticsList[i].lastModifiedDt).css("text-align","center").appendTo($rowResult);

					classCount++;
					labCount++;
				} 
				$("#<portlet:namespace/>"+universityId).attr("rowspan", classCount);
				$("#<portlet:namespace/>"+universityId+"_"+labId).attr("rowspan", labCount);
			}else if(statisticsList.length== 0){
				$rowResult = $("<tr/>");
				$("<td/>").addClass("center").text("<liferay-ui:message key='edison-class-statistics-no-status' />")
				  .css("text-align","center")
				  .attr("colSpan", "9")
				  .appendTo($rowResult);
				$("#classStatisticsListBody").append($rowResult);
			}	
		},complete: function(){
			bEnd();
		}
	});
}

function <portlet:namespace/>excelDown(){
	var form = $("form[name=<portlet:namespace/>statisticsForm]").serialize();
	var url = "<%=classStatisticsExcelDownLoadURL%>"+"&"+form;
	window.location.href = spaceDelete(url);
}

</script>
</head>
<body>
<%
	if((Boolean)request.getAttribute("tabViewYn")){
%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<style>
#data_wrap{
	margin: 10px 0px;
}

#classStatisticsListBody td{
	word-wrap: break-word;
}
</style>

	<div class="contabmenu tabWidth"> 
		<edison-ui:tabs 
			names="<%=tabNames%>" 
			tabsValues="<%=tabsValues%>" 
			value="<%=selectedGroupId%>" 
			refresh="<%=false%>" 
			onClick="<%=portletNameSpace%>"
			minwidth="170"
		/>
	</div>
	
	<div style="clear: both;height:40px;"></div> 
	<%
		}
	%>
	<div class="container">
		<form class="form-inline" name="<portlet:namespace/>statisticsForm"  method="post" onsubmit="return false;">
			<aui:input type="hidden" name="selectedGroupId" value="${selectedGroupId}"/>
			<div class="row">
				<div class="col-md-3">
					<h2 style="margin: 0px;"> 
						<img src="${contextPath}/images/sub_tit_bl.png" class="search-main-title-image">
						<span style="margin-left: 10px;"> ${pageTitle} </span> 
					</h2>
				</div>
				<div class="col-md-9 text-right">
					<div class='input-group'>
						<select class="form-control" id="<portlet:namespace/>universityCode" name="<portlet:namespace/>universityCode" style="width:150px;" onchange="<portlet:namespace/>dataSearch();">
							<option value="" selected><liferay-ui:message key="edison-create-account-field-title-university"/></option>
							<c:forEach var="code" items="${orgCodes}">
								<option value="${code.cd}">${code.cdNm}</option>
							</c:forEach>
						</select>
					</div>
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
	
	<div class="table-responsive panel edison-panel">
		<div class="h20"></div>
		<div style="text-align: right;">
			<button id="<portlet:namespace/>executeClassStatistcs" class="btn btn-default">Sync</button>
		</div>
		<div class="panel-heading clearfix"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
			<colgroup>
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="20%" />
				<col width="15%" />
				<col width="15%" />
			</colgroup>
			<thead>
				<tr>
					<th align="center" scope="col"><liferay-ui:message key='edison-create-account-field-title-university' /></th>
					<th align="center" scope="col">
						<liferay-ui:message key='edison-statistics-select-all' />
						<br/>
						<input type="checkbox" id="<portlet:namespace/>allCheck" name="<portlet:namespace/>allCheck" />
					</th>
					<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' /></th>
					<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtualclass' /></th>
					<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-professor' /></th>
					<th align="center" scope="col"><liferay-ui:message key='edison-statistics-last-modified-date' /></th> <!-- 최근 수정일자 -->
				</tr>
			</thead>
			<tbody id="classStatisticsListBody">
			</tbody>
		</table>
	</div>
	<div class="h20"></div>
	
	<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">

	//liferay-ui 탭 이벤트 return Script
	function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
		var searchData = {"<portlet:namespace/>groupId":value};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=saveClickTab%>",
			async : false,
			data  : searchData,
			success: function(data) {
				window.location.href="<%=statisticsViewURL%>";
			},error:function(data,e){
				alert("tagScript ERROR-->"+e);
			}
		});
	}
	
	$("#<portlet:namespace/>allCheck").click(function(){
		if(this.checked){
			$(".<portlet:namespace/>checkClass").prop('checked', true);
		} else {
			$(".<portlet:namespace/>checkClass").prop('checked', false);
		}
	});
	
	$("#<portlet:namespace/>executeClassStatistcs").click(function(){
		
		var checkedLabIds = new Array();
		$('.<portlet:namespace/>checkClass:checked').each(function(){
			checkedLabIds.push($(this).val());
		})
		
		jQuery.ajax({
			type: "POST",
			url: "<%=updateClassStatisticsURL%>",
			async : false,
			data  : {"<portlet:namespace/>checkedLabIds" : checkedLabIds.toString()},
			success: function(data) {
				location.reload();
			},error:function(data,e){
				alert("<liferay-ui:message key='edison-data-update-error' />");
			}
		});
	});
</script>
</body>