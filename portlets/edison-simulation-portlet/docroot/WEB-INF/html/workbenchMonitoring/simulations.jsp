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
<liferay-theme:defineObjects />

<liferay-portlet:resourceURL var="simulationsFrameUrl" id="simulationsFrame" copyCurrentRenderParameters="false"/>

<aui:form method="post" name="monitoringSearch">
  <aui:input name="currentPage" type="hidden" value="1"/>
  <aui:input name="scienceAppId" type="hidden" value="${param.scienceAppId}"/>
  <aui:input name="simulationUuid" type="hidden" value="${param.simulationUuid}"/>
  <aui:input name="testYn" type="hidden" value="${param.testYn}"/>
  <aui:input name="classId" type="hidden" value="${param.classId}"/>
  <aui:input name="customId" type="hidden" value="${param.customId}"/>
  <aui:input name="jobUuid" type="hidden" value="${param.jobUuid}"/>
  <div class="newtable borderno">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="<portlet:namespace/>simulations-table">
      <colgroup>
        <col width="*">
        <col width="40%">
        <col width="5%">
      </colgroup>
      <thead>
        <tr>
          <th><liferay-ui:message key="edison-table-list-header-simulation-nm" /></th>
          <th><liferay-ui:message key="edison-table-list-header-simulation-created-date" /></th>
          <th></th>
        </tr>
      </thead>
      <tbody id="mtbody">
        <c:choose>
          <c:when test="${!empty simulations}">
            <c:forEach items="${simulations}" var="model" varStatus="data">
              <tr simulation-id="${model.simulationUuid}" 
                class="<portlet:namespace/>loadJobs simulation-row${param.simulationUuid eq model.simulationUuid ? ' on' : ''}">
                <td>
                  ${model.simulationTitle}
                </td>
                <td class="TC"><fmt:formatDate value="${model.simulationCreateDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><a href="javascript:;" class="<portlet:namespace/>-remove-simulation"><i class="icon-trash"></i></a></td>
              </tr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr>
              <td colspan="3" class="TC"><liferay-ui:message key='edison-there-are-no-data' /></td>
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
$(function(){
  var isInit = "${param.isInit}";
  if(isInit !== "true"){
    $(".<portlet:namespace/>loadJobs").unbind("click");
    $(".<portlet:namespace/>-remove-simulation").unbind("click");
    $(".<portlet:namespace/>-remove-simulation").click(function(e){
      e.preventDefault();
      e.stopPropagation();
      // TODO: remove simulation
      console.log("remove simulation");
    });
    $(".<portlet:namespace/>loadJobs").click(function(e){
      e.preventDefault();
      e.stopPropagation();
      var simulationUuid = $(this).attr("simulation-id");
      fireOspSimulationSelected(simulationUuid);
      $("#<portlet:namespace/>simulations-table tr").removeClass("on");
      $(this).addClass("on");
    });
    if($("#<portlet:namespace/>simulations-table tr.simulation-row").length  === 0){
      fireOspNewSimulation();
    }
	  if($("#<portlet:namespace/>simulations-table tr.simulation-row").length > 0
            && $("#<portlet:namespace/>simulations-table tr.on").length === 0 ){
        $("#<portlet:namespace/>simulations-table tr.simulation-row:first").addClass("on");
        selectFirstRow();
	  }
  }
});

function selectFirstRow(){
  $("#<portlet:namespace/>simulations-table tr").each(function(_){
    var tr = this;
    if($(tr).hasClass("on")){
      fireOspSimulationSelected($(tr).attr("simulation-id"));
    }
  });
}

function fireOspNewSimulation(){
  var eventData = {
      portletId: <portlet:namespace/>thisPortletId,
      targetPortlet: <portlet:namespace/>connector,
      data: {
        "scienceAppId": $("#<portlet:namespace/>scienceAppId").val()
      }
    };
  Liferay.fire( OSP.Event.OSP_CREATE_SIMULATION, eventData );
}

function fireOspSimulationSelected(simulationUuid){
  var eventData = {
      portletId: <portlet:namespace/>thisPortletId,
      targetPortlet: <portlet:namespace/>connector,
      data: {
        "simulationUuid": simulationUuid
      }
    };
  Liferay.fire(OSP.Event.OSP_SIMULATION_SELECTED, eventData);
}

function <portlet:namespace/>loadSimulations(p_currentPage){
  if(p_currentPage){
    $("#<portlet:namespace/>currentPage").val(p_currentPage);
  }
  var postData = $("#<portlet:namespace/>monitoringSearch").serializeObject();
  $("#<portlet:namespace/>simulations-wrapper").empty();
  $("#<portlet:namespace/>simulations-wrapper").load(
      "${simulationsFrameUrl}",
      postData,
      function(_){
        // TODO
      });
}

</script>