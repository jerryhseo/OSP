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
  #<portlet:namespace/>jobs-table tr{cursor: pointer;}
  #<portlet:namespace/>jobs-table tr.on{background-color: rgba(141, 185, 229, 0.66) !important;}
  #<portlet:namespace/>jobs-table tr.on > td{background-color: rgba(141, 185, 229, 0.66) !important;}
  #<portlet:namespace/>jobs-table tr:hover > td{background-color: rgba(141, 185, 229, 0.66) !important;}
  .<portlet:namespace/>-menu-list-button:hover{color: #00aaff !important;}
</style>
<liferay-portlet:renderURL var="currentUrl" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="jobsFrameUrl" id="jobsFrame" copyCurrentRenderParameters="false"/>
<div id="<portlet:namespace/>jobs-wrapper"></div>

<script>
  var <portlet:namespace/>thisPortletId = "<%=themeDisplay.getPortletDisplay().getId()%>";
  var <portlet:namespace/>refreshTimer;
  var <portlet:namespace/>connector;
  var <portlet:namespace/>prevStatus = (function(){
    var jobStatus = {};
    var thisCheck = [];
    return {
      log: function(){
        console.log("jobStatus", jobStatus);
        console.log("thisCheck", thisCheck);
      },
      isExist: function(jobUuid){
        return jobStatus.hasOwnProperty(jobUuid);
      },
      isThisTimeSuccess: function(jobUuid, status){
        // 1701011 : success
        return jobStatus[jobUuid] != 1701011 && status == 1701011;
      },
      setJobStatus: function(jobUuid, status){
        jobStatus[jobUuid] = status;
        thisCheck.push(jobUuid);
      },
      clearStatusCache: function(){
        if(thisCheck.length > 0){
          for(var key in jobStatus){
            if($.inArray(key, thisCheck) === -1){
              delete jobStatus[key];
            }
          }
          thisCheck = [];
        }
      }
    };
  })();
  
  $(function(){
    $(document).click(function(e){
      <portlet:namespace/>hideToggleMenus();
    });

    Liferay.on(OSP.Event.OSP_HANDSHAKE, function(e){
      if(e.targetPortlet === <portlet:namespace/>thisPortletId){
        var myId = '<%=portletDisplay.getId()%>';
        <portlet:namespace/>initPage(function(){
          <portlet:namespace/>connector = e.portletId;
          var events = [
            OSP.Event.OSP_EVENTS_REGISTERED,
            OSP.Event.OSP_RESPONSE_MONITOR_INFO
          ];
          var eventData = {
            portletId: myId,
            targetPortlet: <portlet:namespace/>connector,
            data: events
          };
          Liferay.fire(OSP.Event.OSP_REGISTER_EVENTS, eventData);
        });
      }
    });
    
    Liferay.on( 
        OSP.Event.OSP_EVENTS_REGISTERED, 
        function(e){
          var myId = '<%=portletDisplay.getId()%>';
          if( e.targetPortlet === myId ){
            var eventData = {
                portletId: myId,
                targetPortlet: <portlet:namespace/>connector
            };
            Liferay.fire(OSP.Event.OSP_REQUEST_SIMULATION_UUID, eventData);
          }
        }
    );
    
    Liferay.on(OSP.Event.OSP_RESPONSE_SIMULATION_UUID, function(eventData){
      if(eventData.targetPortlet === <portlet:namespace/>thisPortletId ){
        if(eventData.data && eventData.data.simulationUuid){
          $("#<portlet:namespace/>simulationUuid").val(eventData.data.simulationUuid);
          <portlet:namespace/>loadJobs(1);
        }else{
          errlog(eventData, "data.simulationUuid not found");
        }
      }
    });

    Liferay.on(OSP.Event.OSP_REFRESH_JOBS, function(eventData){
      if(eventData.targetPortlet === <portlet:namespace/>thisPortletId
          || eventData.targetPortlet === "BROADCAST"){
        if(eventData.data && eventData.data.simulationUuid){
          $("#<portlet:namespace/>simulationUuid").val(eventData.data.simulationUuid);
          if(eventData.data.jobUuid){
            $("#<portlet:namespace/>jobUuid").val(eventData.data.jobUuid);
          }
          <portlet:namespace/>loadJobs();
        }else{
          <portlet:namespace/>errlog(eventData, "data.simulationUuid Not Found");
        }
      }
    });

    Liferay.on("OSP_MON_LOAD_JOBS", function(eventData){
      if(eventData.targetPortlet === "BROADCAST"
          || eventData.targetPortlet === <portlet:namespace/>thisPortletId){
        <portlet:namespace/>connector = eventData.connector;
        if(eventData.simulationUuid){
          $("#<portlet:namespace/>simulationUuid").val(eventData.simulationUuid);
          <portlet:namespace/>loadJobs(1);
        }else{
          <portlet:namespace/>errlog(eventData);
        }
      }
    });
    
  Liferay.on(OSP.Event.OSP_REQUEST_WORKING_JOB_INFO, function(eventData){
      if(eventData.targetPortlet === "BROADCAST"
          || eventData.targetPortlet === <portlet:namespace/>thisPortletId){
        var e = {
          portletId : <portlet:namespace/>thisPortletId,
          targetPortlet : eventData.portletId,
          data : {
            simulationUuid : $("#<portlet:namespace/>simulationUuid").val(),
            jobUuid : $("#<portlet:namespace/>jobUuid").val()
          }
        }
        Liferay.fire('OSP_JOB_SELECTED', e);
      }
    });
  });

  function <portlet:namespace/>initPage(callback){
    $("#<portlet:namespace/>jobs-wrapper").load("${jobsFrameUrl}", {
      <portlet:namespace/>isRefresh : "true"
    }, callback);
  }

  function <portlet:namespace/>hideToggleMenus(){
    $(".<portlet:namespace/>-menu-list-button").next().hide();
  }

  function <portlet:namespace/>errlog(eventData, msg){
    if(console){
      console.log("Unknown event data: " + (msg ? msg : "") + "\n", eventData);
    }
  }
</script>
