<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://www.edison.re.kr/tld/edison-ui" prefix="edison-ui" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="<%=request.getContextPath() %>" scope="page" />

<portlet:defineObjects />
<liferay-theme:defineObjects />

<liferay-portlet:resourceURL var="jobsFrameUrl" id="jobsFrame" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="submitJobUrl" id="submitJob" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="removeJobUrl" id="removeJob" copyCurrentRenderParameters="false"/>

<aui:form method="post" name="jobMonitoring">
  <aui:input name="currentPage" type="hidden" value="1"/>
  <aui:input name="simulationUuid" type="hidden" value="${param.simulationUuid}"/>
  <aui:input name="jobUuid" type="hidden" value="${param.jobUuid}"/>
  <aui:input name="isRefresh" type="hidden" value="${param.isRefresh}"/>
  <div class="newtable borderno">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="<portlet:namespace/>jobs-table">
      <colgroup>
        <col width="*">
        <col width="35%">
        <col width="18%">
        <col width="10%">
      </colgroup>
      <thead>
        <tr>
          <th><liferay-ui:message key="edison-simulation-execute-job-create-list-job-name" /></th>
          <th><liferay-ui:message key="edison-simulation-execute-job-create-list-submit-time" /></th>
          <th><liferay-ui:message key="edison-simulation-execute-job-create-list-state" /></th>
          <th></th>
        </tr>
      </thead>
      <tbody id="<portlet:namespace/>jobtable">
        <c:choose>
          <c:when test="${!empty jobs}">
            <c:forEach items="${jobs}" var="model" varStatus="data">
              <tr job-id="${model.jobUuid}" is-job-submit="${model.jobSubmit}"
                job-status="${model.jobStatus}"
                class="<portlet:namespace/>loadJob job-row${param.jobUuid eq model.jobUuid ? ' on' : ''}">
                <td>
                  ${model.jobTitle}
                </td>
                <td class="TC"><fmt:formatDate value="${model.jobSubmitDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="TC">
                  <img src="${contextPath}/images/monitoring/workbench/${model.jobStatus}.png"/>
                </td>
                <td class="TC">
                  <i class="icon-reorder icon-menu menu-list-button <portlet:namespace/>-menu-list-button"></i>
                  <c:if test="${model.jobSubmit}">
                    <ul class="job-menu-list">
                      <li><a href="javascript:;" class="<portlet:namespace/>-cancel-job"><i class="icon-remove-circle"> Cancel</i></a></li>
                      <li><a href="javascript:;" class="<portlet:namespace/>-copy-job"><i class="icon-copy"> Copy</i></a></li>
                      <li><a href="javascript:;" class="<portlet:namespace/>-delete-job"><i class="icon-trash"> Delete</i></a></li>
                    </ul>
                  </c:if>
                  <c:if test="${!model.jobSubmit}">
                    <ul class="job-menu-list">
                      <li><a href="javascript:;" class="<portlet:namespace/>-run-job"><i class="icon-play-circle"> Run</i></a></li>
                      <li><a href="javascript:;" class="<portlet:namespace/>-copy-job"><i class="icon-copy"> Copy</i></a></li>
                      <li><a href="javascript:;" class="<portlet:namespace/>-delete-job"><i class="icon-trash"> Delete</i></a></li>
                    </ul>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr>
              <td colspan="4" class="TC"><liferay-ui:message key='edison-there-are-no-data' /></td>
            </tr>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>
  </div>
</aui:form>
<div class="workbench-monitoring paging">
  <div style="width:100%; text-align: center;">
    ${paging}
  </div>
</div>
<script>
var INITIAL_JOB_EVENT_NAME = "OSP_DASH_INITIAL_JOB";
var SELECT_JOB_EVENT_NAME = "OSP_DASH_SELECT_JOB";
$(function(){
  $(".<portlet:namespace/>-run-job").unbind("click");
  $(".<portlet:namespace/>-copy-job").unbind("click");
  $(".<portlet:namespace/>-delete-job").unbind("click");
  $(".<portlet:namespace/>-cancel-job").unbind("click");
  $(".<portlet:namespace/>loadJob").unbind("click");
  $(".<portlet:namespace/>-menu-list-button").unbind("click");
  $(".<portlet:namespace/>-menu-list-button").click(function(e){
    var isThisVisible = $(this).next().is(":visible");
    <portlet:namespace/>hideToggleMenus();
    if(!isThisVisible){
      $(this).next().show();
    }
  });
  $(".<portlet:namespace/>-run-job").click(function(e){
    <portlet:namespace/>jobMenuButtonHanlder(e);
    <portlet:namespace/>runJob(<portlet:namespace/>getJobUuid(this));
  });
  $(".<portlet:namespace/>-copy-job").click(function(e){
    <portlet:namespace/>jobMenuButtonHanlder(e);
    <portlet:namespace/>copyJob(<portlet:namespace/>getJobUuid(this));
  });
  $(".<portlet:namespace/>-cancel-job").click(function(e){
    <portlet:namespace/>jobMenuButtonHanlder(e);
    <portlet:namespace/>cancelJob(<portlet:namespace/>getJobUuid(this));
  });
  $(".<portlet:namespace/>-delete-job").click(function(e){
    <portlet:namespace/>jobMenuButtonHanlder(e);
    <portlet:namespace/>removeJob(<portlet:namespace/>getJobUuid(this));
  });
  $(".<portlet:namespace/>loadJob").click(function(e){
    e.preventDefault();
    e.stopPropagation();
    if(!$(e.target).hasClass("<portlet:namespace/>-menu-list-button")){
      <portlet:namespace/>hideToggleMenus();
    }
    var jobUuid = $(this).attr("job-id");
    var isJobSubmit = ($(this).attr("is-job-submit") == 'true'); 
    var simulationUuid = $("#<portlet:namespace/>simulationUuid").val();
    var jobStatus = $(this).attr("job-status");
    <portlet:namespace/>fireSelectJob(SELECT_JOB_EVENT_NAME, simulationUuid, jobUuid, jobStatus, isJobSubmit);
    $("#<portlet:namespace/>jobs-table tr").removeClass("on");
    $(this).addClass("on");
  });
  <portlet:namespace/>selectLatestJob();
  <portlet:namespace/>pullJobs();
});

function <portlet:namespace/>pullJobs(){
  // 1701011 : success
  var isUncompletedJobExist = false;
  $("#<portlet:namespace/>jobtable > tr.job-row").each(function(_){
    var jobRow = this;
    var jobUuid = $(jobRow).attr("job-id");
    var jobStatus = $(jobRow).attr("job-status");
    var isJobSubmit = ($(jobRow).attr("is-job-submit") == 'true');
    if(<portlet:namespace/>prevStatus.isExist(jobUuid)
        && <portlet:namespace/>prevStatus.isThisTimeSuccess(jobUuid, jobStatus)){
      if(jobUuid == '${param.jobUuid}'){
        <portlet:namespace/>fireShowAnalyzer('${param.simulationUuid}', jobUuid, jobStatus, isJobSubmit);
      }else{
        // TODO: success alert
        console.log("success alert");
      }
    }
    <portlet:namespace/>prevStatus.setJobStatus(jobUuid, jobStatus);
    if(jobStatus == 0 || jobStatus == 1701005 || jobStatus == 1701006){
      isUncompletedJobExist = true;
    }
  });
  //<portlet:namespace/>prevStatus.log();
  <portlet:namespace/>prevStatus.clearStatusCache();
  if(<portlet:namespace/>refreshTimer){
    clearTimeout(<portlet:namespace/>refreshTimer);
  }
  if(isUncompletedJobExist){
    <portlet:namespace/>refreshTimer = setTimeout(<portlet:namespace/>loadJobs, 5000, '${param.currentPage}', true);
  }
}

function <portlet:namespace/>selectLatestJob(){
  var jobUuid = $("#<portlet:namespace/>jobUuid").val();
  var simulationUuid = $("#<portlet:namespace/>simulationUuid").val();
  var isJobSubmit = false;
  var jobStatus = null;
  simulationUuid = simulationUuid ? simulationUuid : null;
  if(!jobUuid || $(".<portlet:namespace/>loadJob[job-id='" + jobUuid + "']").length === 0){
    if($("#<portlet:namespace/>jobtable > tr.job-row").length > 0){
      var firstTr = $("#<portlet:namespace/>jobtable > tr.job-row:first");
      firstTr.addClass("on");
      jobUuid = firstTr.attr("job-id");
      isJobSubmit = (firstTr.attr("is-job-submit") == 'true');
      jobStatus = firstTr.attr("job-status");
    }else{
      jobUuid = null;
    }
  }
  if('${param.isRefresh}' != 'true'){
    <portlet:namespace/>fireSelectJob(INITIAL_JOB_EVENT_NAME, simulationUuid, jobUuid, jobStatus, isJobSubmit);
  }
}

function <portlet:namespace/>fireShowAnalyzer(simulationUuid, jobUuid, jobStatus, isJobSubmit){
  console.log("<portlet:namespace/>fireShowAnalyzer", simulationUuid, jobUuid, jobStatus, isJobSubmit);
  Liferay.fire(
    OSP.Event.OSP_JOB_STATUS_CHANGED,
    {
      targetPortlet: <portlet:namespace/>connector,
      portletId: <portlet:namespace/>thisPortletId,
      data: {
        simulationUuid: simulationUuid,
        jobUuid: jobUuid,
        jobStatus: jobStatus,
        jobSubmit: isJobSubmit
      }
    });
}

function <portlet:namespace/>fireOspJobSelected(simulationUuid, jobUuid){
  var eventData = {
      portletId: <portlet:namespace/>thisPortletId,
      targetPortlet: <portlet:namespace/>connector,
      data: {
        "simulationUuid": simulationUuid,
        "jobUuid": jobUuid
      }
    };
    Liferay.fire( OSP.Event.OSP_JOB_SELECTED, eventData );
}

function <portlet:namespace/>fireSelectJob(eventName, simulationUuid, jobUuid, jobStatus, isJobSubmit){
  if(jobUuid){
    $("#<portlet:namespace/>jobUuid").val(jobUuid);
    <portlet:namespace/>fireOspJobSelected(simulationUuid, jobUuid);
  }
  Liferay.fire(
    eventName,
    {
      targetPortlet: <portlet:namespace/>connector,
      sourcePortlet: <portlet:namespace/>thisPortletId,
      data: {
        simulationUuid: simulationUuid,
        jobUuid: jobUuid,
        jobStatus: jobStatus,
        jobSubmit: isJobSubmit
      }
    });
}

function <portlet:namespace/>submitJob(jobUuid){
  synchronousAjaxHelper.post("${submitJobUrl}", {
    "<portlet:namespace/>simulationUuid": $("#<portlet:namespace/>simulationUuid").val(),
    "<portlet:namespace/>jobUuid": jobUuid
  }, function(_){
    <portlet:namespace/>loadJobs('${param.currentPage}');
  });
}

function <portlet:namespace/>loadJobs(p_currentPage, isRefresh){
  $("#<portlet:namespace/>currentPage").val(p_currentPage);
  $("#<portlet:namespace/>isRefresh").val(isRefresh);
  var postData = $("#<portlet:namespace/>jobMonitoring").serializeObject();
  $("#<portlet:namespace/>jobs-wrapper").load(
      "${jobsFrameUrl}",
      postData,
      function(_){});
}

function <portlet:namespace/>runJob(jobUuid){
  // TODO: run job
}

function <portlet:namespace/>copyJob(jobUuid){
  // TODO: copy job
}

function <portlet:namespace/>cancelJob(jobUuid){
  // TODO: cancel job
}

function <portlet:namespace/>removeJob(jobUuid){
  if(confirm('<liferay-ui:message key="edison-simulation-monitoring-delete-job-question"/>')){
    synchronousAjaxHelper.post("${removeJobUrl}", {
      "<portlet:namespace/>simulationUuid": $("#<portlet:namespace/>simulationUuid").val(),
      "<portlet:namespace/>jobUuid": jobUuid
    }, function(_){
      var currentPage = '${param.currentPage}';
      if($("#<portlet:namespace/>jobtable > tr.job-row").length <= 1){
        var newCurrentPage = Number('${param.currentPage}') - 1;
        currentPage = newCurrentPage < 1 ? "1" : "" + newCurrentPage;
      }
      <portlet:namespace/>loadJobs(currentPage);
    });
  }
}

function <portlet:namespace/>jobMenuButtonHanlder(e){
  e.preventDefault();
  e.stopPropagation();
  <portlet:namespace/>hideToggleMenus();
}

function <portlet:namespace/>getJobUuid(that){
  return $(that).parent("li").parent("ul").parent("td").parent("tr").attr("job-id");
}
</script>