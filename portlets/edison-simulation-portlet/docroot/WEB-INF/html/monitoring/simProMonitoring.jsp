<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="org.kisti.edison.util.MonitoringStatusConstatns"%>

<liferay-portlet:resourceURL var="getSimulationMonitoringJobListURL" id="getSimulationMonitoringJobList" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="Workbench_WAR_OSPWorkbenchportlet">
	<liferay-portlet:param name="workbenchType" value="APPRERUN"/>
	<liferay-portlet:param name="classId" value="${simulationClassId}"/>
	<liferay-portlet:param name="customId" value="${simulationCustomId}"/>	
	<liferay-portlet:param name="testYn" value="false"/>
</liferay-portlet:renderURL>

<div class="commrighttxt">
	<ul id="<portlet:namespace/>monitoringList">
	</ul>
</div>
<div id="<portlet:namespace/>preNextBtn" style="text-align: center;">
	<input type="button" value="Previous" class="btn_wline" onclick="<portlet:namespace/>goPrePage();"/>
	<input type="button" value="Next" class="btn_wline" onclick="<portlet:namespace/>goNextPage();"/>
</div>
 


<script type="text/javascript">
var totalCount = 0;
var listSize = 3;
var currentPage = 1;

$(document).ready(function () {
	<portlet:namespace/>getSimulationMonitoringJobList(1);
});

function <portlet:namespace/>goPrePage(){
	var searchParameter = "";
	var p_curPage = currentPage-1;
	if(p_curPage != 0){
		<portlet:namespace/>getSimulationMonitoringJobList(p_curPage);
	}
}

function <portlet:namespace/>goNextPage(){
	var searchParameter = "";
	var p_curPage = currentPage+1;
	var curPageCount = currentPage * listSize;
	if( (totalCount - curPageCount) > 0){
		<portlet:namespace/>getSimulationMonitoringJobList(p_curPage);
	}
}

function <portlet:namespace/>getSimulationMonitoringJobList(p_curPage){
	var dataForm = {
			"<portlet:namespace/>currentPage":p_curPage,
			"<portlet:namespace/>listSize":listSize,
			"<portlet:namespace/>simulationCustomId": "${simulationCustomId}",
			"<portlet:namespace/>simulationClassId": "${simulationClassId}"
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=getSimulationMonitoringJobListURL%>",
		async : false,
		data  : dataForm,
		success: function(data) {
			var dataList = data.dataList;
			totalCount = data.totalCount;
			currentPage = data.currentPage;
			listSize = data.listSize;
			$("#<portlet:namespace/>monitoringList").empty();
			$("<li/>").html("<liferay-ui:message key='edison-simulation-monitoring-title' />")
			  		  .appendTo("#<portlet:namespace/>monitoringList");
			$("<li/>").css("text-align", "center")
					  .append($("<input/>").attr("type","button")
				    		  			   .attr("onclick", "<portlet:namespace/>goMonitoring('${simulationClassId}', '${simulationCustomId}');")
				    		  			   .addClass("btn_wline")
				    		  			   .val("View All")
				    	)
	          		  .appendTo("#<portlet:namespace/>monitoringList");
			if(dataList.length != 0){
				for(var i = 0; i < dataList.length; i++) {
					var liClass = "";
					if(i == dataList.length - 1){
						liClass = "stxt2";
					}else{
						liClass = "stxt2 last";
					}
					var $li = $("<li/>").addClass(liClass)
                    					.css("cursor", "pointer")
										.attr("onclick", "<portlet:namespace/>goWorkbench('"+dataList[i].scienceAppId+"','"+dataList[i].jobUuid+"');");
										
					$("<img/>").attr("src", "${contextPath}"+"/images/comm_proj/"+dataList[i].jobStatusImg)
							   .attr("width","176")
							   .attr("height","13")
							   .appendTo($li);
					$("<br>").appendTo($li);
					$("<span/>").html(dataList[i].scienceAppName)
								.appendTo($li);
					$("<br>").appendTo($li);
					$("<span/>").html(cutStr(dataList[i].jobTitle, 25))
								.appendTo($li);
					var start = $("<br>");
					start.appendTo($li);
					start.after("start : "+dataList[i].jobStartDt);
					var end = $("<br>");
					end.appendTo($li);
					end.after("end : "+dataList[i].jobStartDt);
						
					$li.appendTo("#<portlet:namespace/>monitoringList");
				}
				
				if(totalCount  < 4){
					$("#<portlet:namespace/>preNextBtn").css("display", "none");
				}else{
					$("#<portlet:namespace/>preNextBtn").css("display", "");
				}
			}else{
				$("#<portlet:namespace/>preNextBtn").css("display", "none");
			}
			
		},error:function(data,e){
			alert("getSimulationMonitoringJobList ERROR-->"+e);
		}
	});
}

function <portlet:namespace/>goWorkbench(targetScienceAppId, jobUuid){
	var URL = "<%=workbenchURL%>";
	var workbenchNameSpace = "_Workbench_WAR_OSPWorkbenchportlet_";
	URL += "&"+workbenchNameSpace+"scienceAppId="+targetScienceAppId+"&"+workbenchNameSpace+"jobUuid="+jobUuid;
	
 	location.href= URL;
}

</script>

<aui:script>
function <portlet:namespace/>goMonitoring(simulationClassId, simulationCustomId){
	AUI().use("liferay-portlet-url", function(a) {
	    var portletURL = Liferay.PortletURL.createRenderURL();
	    portletURL.setPlid("${monitoringPlid}");
	    portletURL.setPortletId("edisonmonitoring_WAR_edisonsimulationportlet");
	    portletURL.setParameter("simulationClassId", simulationClassId);
	    portletURL.setParameter("simulationCustomId", simulationCustomId);	    
	    window.open(portletURL.toString(), '_blank');
	});
}
</aui:script>


