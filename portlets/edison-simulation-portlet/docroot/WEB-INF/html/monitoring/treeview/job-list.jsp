<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@page import="org.kisti.edison.util.EdisonHttpUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="org.kisti.edison.util.MonitoringStatusConstatns"%>
<c:set var="jobStatusSuccess" value="<%=MonitoringStatusConstatns.SUCCESS%>" scope="page" />
<liferay-portlet:resourceURL var="transferSimulationDataUrl" escapeXml="false" id="transferSimulationData" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getJobsUrl" id="getJobs" copyCurrentRenderParameters="false"/>
<liferay-portlet:renderURL var="collectionPopupURL" portletName="sdrcommon_WAR_SDR_baseportlet" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
    <portlet:param name="action" value="collectionPopup" />
    <portlet:param name="targetGroupId" value="${sdrGroupId}" />
    <portlet:param name="allCollection" value="true" />
</liferay-portlet:renderURL>
<style>
.job-file-item {cursor: pointer;}
.job-file-item img {
    -webkit-transform: scale(1);
    transform: scale(1);
    -webkit-transition: .2s ease-in-out;
    transition: .2s ease-in-out;
}
.job-file-item:hover img,
.job-file-item.context-menu-active img {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
}
.modal{left: 32% !important; width: 65% !important;}
.modal-body{height: 500px;}
.name-word-wrap{text-overflow:ellipsis; white-space:nowrap; word-wrap:normal; width: 70px; overflow:hidden;}
</style>
<div class="table-responsive panel edison-panel">
  <div class="panel-heading clearfix">
    <h3 class="panel-title pull-left">
      <img src="${contextPath}/images/title_virtual.png" width="18" height="18" class="title-img">
      <liferay-ui:message key="edison-simulation-monitoring-job-info" />
    </h3>
    <c:if test="${hasSuccess}">
    <div class="btn-group pull-right">
      <button class="btn btn-primary" onclick="<portlet:namespace/>fn_collectionPopup();"><liferay-ui:message key="edison-simulation-monitoring-export-job-info" /></button>
    </div>
    </c:if>
  </div>
  <table class="table table-bordered table-hover edison-table">
    <colgroup>
      <col width="*">
      <col width="10%">
      <col width="20%">
    </colgroup>
    <thead>
      <tr>
        <th scope="col"><liferay-ui:message key="edison-simulation-execute-job-create-list-job-name" /></th>
        <th scope="col"><liferay-ui:message key="edison-simulation-execute-job-create-list-state" /></th>
        <th scope="col"><liferay-ui:message key="edison-simulation-monitoring-job-info" /></th>
      </tr>
    </thead>
    <tbody id="mtbody">
    <c:forEach items="${jobs}" var="job">
      <tr id="<portlet:namespace/>monitoring-tr">
        <td>${job.jobTitle}</td>
        <td class="center"><img src="${contextPath}/images/monitoring/<%=themeDisplay.getLanguageId()%>/${job.jobStatusImg}" /></td>
        <td class="center"> 
            <img src="${contextPath}/images/monitoring/bnt_info.png" style="cursor: pointer;"
              onclick="<portlet:namespace/>displayJobInfo('${job.jobUuid}', '${job.scienceAppId}');" alt="Job Info"
              title="Workbench">
        </td>
      </tr>
    </c:forEach>
    <c:if test="${fn:length(jobs) eq 0}">
      <tr>
        <td colspan="3" style="text-align: center;">No Data</td>
      </tr>
    </c:if>
    </tbody>
  </table>
</div>

<script type="text/javascript">
AUI().ready(function() {
});

function <portlet:namespace/>fn_collectionPopup(){
    AUI().use('aui-base','liferay-portlet-url','aui-node', function(A) {
        Liferay.Util.openWindow({
            dialog : {
                constrain : true,
                modal : true,
                cache: false,
                destroyOnClose: true,
                width : '980px'
            },
            id : 'sdrcommon_collectionPopup',
            title : 'Collection Popup',
            uri : '${collectionPopupURL}'
        });
    });
}

function sdrcommon_collectionPopup(result){
    $.ajax({
        url: "${transferSimulationDataUrl}",
        async: false,
        data : {
            "<portlet:namespace/>collectionId": result.value,
            "<portlet:namespace/>simulationUuid": "${simulationUuid}"
        },
        method: 'POST',
        timeout: 10000,
      }).done(function (result) {
    	  var successMsg = result.successMsg;
  		var errorMsg = result.errorMsg;
  		
  		var alertMsg = "";
  		if(successMsg!=""){
  			alertMsg += "Successfully Transfer JobData To SDR <br/>";
  			alertMsg += successMsg.replace(/,/gi, ',<br/>');
  		}
  		
  		if(errorMsg!=""){
  			alertMsg += "<br/> Partially Failed <br/>";
  			alertMsg += errorMsg.replace(/,/gi, ',<br/>');
  		}
  		
  		if(alertMsg==""){
  			alertMsg = "No data to send to SDR.";
  		}
  		
  		alert(<portlet:namespace/>replaceAll(alertMsg,'<br/>','\r\n'));
      }).error(function (msg) {
          console.log(msg);
      });
}

function <portlet:namespace/>replaceAll(str, searchStr, replaceStr) {
	  return str.split(searchStr).join(replaceStr);
	}

function <portlet:namespace/>displayJobInfo(jobUuid, scienceAppId){
    <portlet:namespace/>displayJob(jobUuid, scienceAppId);
}
</script>
