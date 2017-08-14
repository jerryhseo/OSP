<%@page import="javax.portlet.PortletPreferences"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-workbench.jsp"%>


<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();
%>

<link href="${contextPath}/css/workbench-monitoring.css" rel="stylesheet" type="text/css" />
<style>
  #<portlet:namespace/>simulations-table tr{cursor: pointer;}
  #<portlet:namespace/>simulations-table tr.on{background-color: rgba(141, 185, 229, 0.66) !important;}
  #<portlet:namespace/>simulations-table tr.on > td{background-color: rgba(141, 185, 229, 0.66) !important;}
  #<portlet:namespace/>simulations-table tr:hover > td{background-color: rgba(141, 185, 229, 0.66) !important;}
</style>
<liferay-portlet:renderURL var="currentUrl" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="simulationsFrameUrl" id="simulationsFrame" copyCurrentRenderParameters="false"/>
<div id="<portlet:namespace/>simulations-wrapper"></div>

<script>
var <portlet:namespace/>thisPortletId = "<%=themeDisplay.getPortletDisplay().getId()%>";
var <portlet:namespace/>workbenchPortletId;
var <portlet:namespace/>connector;

$(function(){
  var <portlet:namespace/>testYn = false;
  var <portlet:namespace/>classId = "";
  var <portlet:namespace/>customId = "";
  
  //////////////////////////////////// new event
   Liferay.on(
    OSP.Event.OSP_HANDSHAKE,
    function(e){
      var myId = '<%=portletDisplay.getId()%>';
      if( myId === e.targetPortlet ){
        initPage(function(){
          <portlet:namespace/>connector = e.portletId;
          var events = [
            OSP.Event.OSP_LOAD_DATA,
            OSP.Event.OSP_EVENTS_REGISTERED,
            OSP.Event.OSP_RESPONSE_MONITOR_INFO
          ];
          var eventData = {
            portletId: myId,
            targetPortlet: <portlet:namespace/>connector,
            data: events
          };
          Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
        });
      }
    }
  );
  
  Liferay.on( 
      OSP.Event.OSP_EVENTS_REGISTERED, 
      function(e){
        var myId = '<%=portletDisplay.getId()%>';
        if( e.targetPortlet === myId ){
          var eventData = {
              portletId: myId,
              targetPortlet: <portlet:namespace/>connector
          };
          
          Liferay.fire( OSP.Event.OSP_REQUEST_MONITOR_INFO, eventData );
        }
      }
  );
  
  Liferay.on(OSP.Event.OSP_RESPONSE_MONITOR_INFO, function(eventData){
    if(eventData.targetPortlet === <portlet:namespace/>thisPortletId ){
      if(eventData.data && eventData.data.scienceAppId){
        $("#<portlet:namespace/>scienceAppId").val(eventData.data.scienceAppId);
        //$("#<portlet:namespace/>testYn").val(<portlet:namespace/>testYn);
        $("#<portlet:namespace/>classId").val(eventData.data.classId);
        $("#<portlet:namespace/>customId").val(eventData.data.customId);
        <portlet:namespace/>loadSimulations();
      }else{
        errlog(eventData);
      }
    }
  });
  
  ////////////////////////////////////
  
  Liferay.on(OSP.Event.OSP_REFRESH_SIMULATIONS, function(eventData){
    if(eventData.targetPortlet === <portlet:namespace/>thisPortletId 
    		|| eventData.targetPortlet === "BROADCAST"){
      if(eventData.data && eventData.data.simulationUuid){
        $("#<portlet:namespace/>simulationUuid").val(eventData.data.simulationUuid);
        // $("#<portlet:namespace/>testYn").val(<portlet:namespace/>testYn);
        $("#<portlet:namespace/>classId").val(<portlet:namespace/>classId);
        $("#<portlet:namespace/>customId").val(<portlet:namespace/>customId);
        <portlet:namespace/>loadSimulations();
      }else{
        errlog(eventData, "data.simulationUuid Not Found");
      }
    }
  });
});

function initPage(callback){
  $("#<portlet:namespace/>simulations-wrapper").empty();
  $("#<portlet:namespace/>simulations-wrapper")
      .load("${simulationsFrameUrl}",{
        <portlet:namespace/>isInit : true
      }, callback);
}

function objectToBoolean(obj){
  if(obj){
    if(typeof obj === "string" 
        && (obj.toLowerCase() === "true"
          || obj.toLowerCase() === "y"
          || obj.toLowerCase() === "yes")){
      return true;
    }
    if(typeof obj === "boolean"){
      return obj;
    }
  }
  return false;
}

function errlog(eventData, msg){
  if(console){
    console.log("Unknown event data: " + (msg ? msg : "") + "\n", eventData);
  }
}
</script>
