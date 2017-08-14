<%@page import="javax.portlet.PortletPreferences"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init-workbench.jsp"%>

<%
  PortletPreferences preferences = portletDisplay.getPortletSetup();
  preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
  preferences.store();
%>

<link href="${contextPath}/css/workbench-monitoring.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/custom-pace.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.aui input[type="text"], .aui input[type="password"], .aui textarea, .aui .edison_select { margin-bottom: 0px; }
.file_tr:hover {background-color: #e0e0e0;}

.job-info-text{font-size: 11px; font-weight: normal; line-height: 10px; padding-top: 5px;}
.status-text{font-size: 15px; font-weight: normal; line-height: 10px; padding-top: 5px; float: left;}
#<portlet:namespace/>current-status.pace > .pace-progress{-webkit-transform: translate3d(50%, 0px, 0px); -ms-transform: translate3d(50%, 0px, 0px); transform: translate3d(50%, 0px, 0px);}
#<portlet:namespace/>this-portlet .form-horizontal .control-group {margin-bottom: 5px !important; margin-top: 5px !important;}
#<portlet:namespace/>this-portlet .form-horizontal legend {margin-bottom: 10px !important; padding-left: 10px !important; font-size: 15px;}
#<portlet:namespace/>this-portlet .form-horizontal label{ font-size: 11px; font-weight: normal;line-height: 10px;}
#<portlet:namespace/>out-log-wrapper{margin-top: 10px;}
#<portlet:namespace/>out-log-wrapper > pre{padding: 5px; margin-left: 5px; margin-right:5px; font-size: 10px;}
</style>
<liferay-portlet:renderURL var="currentUrl" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="jobResultFrameUrl" id="jobResultFrame" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="jobInfoUrl" id="jobInfo" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="readOutLogUrl" id="readOutLog" copyCurrentRenderParameters="false"/>
<div id="<portlet:namespace/>this-portlet">
<aui:form name="job-status">
  <aui:input name="jobUuid" type="hidden" value="${param.jobUuid}"/>
</aui:form>
<div class="form-horizontal" >
  <fieldset>
    <legend>Information</legend>
    <div class="control-group">
      <label class="control-label" for="<portlet:namespace/>job-title">Job Title</label>
      <div class="controls">
        <div id="<portlet:namespace/>job-title" class="job-info-text"></div>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="<portlet:namespace/>simulation-uuid">Simulation UUID</label>
      <div class="controls">
        <div id="<portlet:namespace/>simulation-uuid" class="job-info-text"></div>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="<portlet:namespace/>job-uuid">Job UUID</label>
      <div class="controls">
        <div id="<portlet:namespace/>job-uuid" class="job-info-text"></div>
      </div>
    </div>
    <div class="control-group" style="display: none;">
      <label class="control-label" for="<portlet:namespace/>start-time">Start Time</label>
      <div class="controls">
        <div id="<portlet:namespace/>start-time" class="job-info-text"></div>
      </div>
    </div>
    <div class="control-group" style="display: none;">
      <label class="control-label" for="<portlet:namespace/>end-time">End Time</label>
      <div class="controls">
        <div id="<portlet:namespace/>end-time" class="job-info-text"></div>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="<portlet:namespace/>current-status">Current Status</label>
      <div class="controls">
        <div>
          <div id="<portlet:namespace/>job-status-text" class="status-text"></div><span id="<portlet:namespace/>ball-point" style="float: left;"></span>
          <div id="<portlet:namespace/>current-status" class="pace pace-active" style="display: none;">
            <div class="pace-progress">
              <div class="pace-progress-inner"></div>
            </div>
            <div class="pace-activity"></div>
          </div>
        </div>
      </div>
    </div>
    
  </fieldset>
</div>
<div id="<portlet:namespace/>result-file-wrapper" class="form-horizontal">
</div>

<div id="<portlet:namespace/>out-log-wrapper" style="display: none;">
    <pre></pre>
</div>
</div>
<script>
// TODO : move global vairables from here to doc.ready function
$(function(){
  var <portlet:namespace/>thisPortletId = "<%=themeDisplay.getPortletDisplay().getId()%>";
  var <portlet:namespace/>connector; 
  var <portlet:namespace/>refreshTimer;
  
  Liferay.on(OSP.Event.OSP_HANDSHAKE, function(e){
    if(e.targetPortlet === <portlet:namespace/>thisPortletId){
      <portlet:namespace/>connector = e.portletId;
      var events = [
        OSP.Event.OSP_EVENTS_REGISTERED,
        OSP.Event.OSP_REQUEST_JOB_UUID
      ];
      var eventData = {
        portletId: <portlet:namespace/>thisPortletId,
        targetPortlet: <portlet:namespace/>connector,
        data: events
      };
      Liferay.fire(OSP.Event.OSP_REGISTER_EVENTS, eventData);
    }
  });
  
  Liferay.on(OSP.Event.OSP_EVENTS_REGISTERED, function(e){
    if(e.targetPortlet === <portlet:namespace/>thisPortletId){
      var eventData = {
          portletId: <portlet:namespace/>thisPortletId,
          targetPortlet: <portlet:namespace/>connector
      };
      Liferay.fire(OSP.Event.OSP_REQUEST_JOB_UUID, eventData);
    }
  });
  
  Liferay.on(OSP.Event.OSP_RESPONSE_JOB_UUID, function(e){
    console.log(OSP.Event.OSP_RESPONSE_JOB_UUID, " recieved\n", e);
    if(e.targetPortlet === <portlet:namespace/>thisPortletId){
      if(e.data && e.data.jobUuid){
          <portlet:namespace/>clearReadOutLogTimer();
          <portlet:namespace/>getJobInfo(e.data.jobUuid);
      }
    }
  });
  
  Liferay.on(OSP.Event.OSP_REFRESH_JOB_STATUS, function(e){
    console.log(OSP.Event.OSP_REFRESH_JOB_STATUS, " recieved\n", e);
    if(e.targetPortlet === <portlet:namespace/>thisPortletId ||
    	e.targetPortlet === 'BROADCAST' ){
      <portlet:namespace/>removeResultFiles();
      <portlet:namespace/>clearReadOutLogTimer();
      if(e.data && e.data.jobUuid){
        <portlet:namespace/>getJobInfo(e.data.jobUuid);
      }
    }
  });
  
  Liferay.on(
     OSP.Event.OSP_JOB_STATUS_CHANGED,
     function(e){
       if(e.data && e.data.jobUuid){
         <portlet:namespace/>getJobInfo(e.data.jobUuid);
       }
   });
  
  function <portlet:namespace/>getJobInfo(jobUuid){
    $.ajax({
      url: '<%=jobInfoUrl.toString()%>',
      type:'POST',
      dataType:'json',
      data:{
        <portlet:namespace/>jobUuid: jobUuid
      },
      success:function(jobInfo){
        if(jobInfo){
          <portlet:namespace/>displayJonInfo(jobInfo)
          <portlet:namespace/>readOutLog(jobInfo);
          if(jobInfo.jobStatusString === "SUCCESS" 
              && $("#<portlet:namespace/>result-files").length === 0){
            <portlet:namespace/>loadJobResultFrame(jobInfo.jobUuid);
          }
        }
      },
      error: function(){
        if(console){
          console.log('[ERROR] AJAX FAILED during getJobInfo');
        }
      }
    });
  }
  
  var <portlet:namespace/>outLogRepository = (function(){
      var outLogArray = [];
      var outLogMap = {};
      return {
          saveLog : function(jobUuid, outLog){
              if(outLogMap[jobUuid]){
                  outLogMap[jobUuid].lastPosition = outLog.lastPosition;
              }else{
                  outLogMap[jobUuid] = {
                      lastPosition: outLog.lastPosition
                  };
                  outLogArray.push(jobUuid);
              }
              while(outLogArray.length > 5){
                  var rejectionTarget = outLogArray.shift();
                  delete outLogMap[rejectionTarget];
                  $("#<portlet:namespace/>out-log-wrapper > pre[id="+jobUuid+"]").remove();
              }
          },
          checkAndReadLog: function(jobUuid){
              $("#<portlet:namespace/>out-log-wrapper > pre").hide();
              if(outLogMap[jobUuid]){
                $("#<portlet:namespace/>out-log-wrapper > pre[id="+jobUuid+"]").show();
                return outLogMap[jobUuid].lastPosition;
              }else{
                $("#<portlet:namespace/>out-log-wrapper").append($("<pre>", {id: jobUuid}));
                return 0;
              }
          },
          getLastPosition: function(jobUuid){
              if(outLogMap[jobUuid]){
                return outLogMap[jobUuid].lastPosition;
              }else{
                return 0;
              }
          }
      };
  })();
  
  function <portlet:namespace/>readOutLog(jobInfo){
      <portlet:namespace/>clearReadOutLogTimer();
      if(!(jobInfo.jobStatusString === "RUNNING" || jobInfo.jobStatusString === "FAILED")){
          if($("#<portlet:namespace/>out-log-wrapper").is(":visible")){
              $("#<portlet:namespace/>out-log-wrapper").hide();
          }
          return;
      }
      var lastPosition = <portlet:namespace/>outLogRepository.getLastPosition(jobInfo.jobUuid);
      
      $("#<portlet:namespace/>out-log-wrapper").show();
      $.ajax({
        url: '<%=readOutLogUrl.toString()%>',
        type:'POST',
        dataType:'json',
        data:{
          <portlet:namespace/>jobUuid: jobInfo.jobUuid,
          <portlet:namespace/>simulationUuid: jobInfo.simulationUuid,
          <portlet:namespace/>lastPosition: lastPosition
        },
        success:function(outLog){
            <portlet:namespace/>outLogRepository.checkAndReadLog(jobInfo.jobUuid);
            <portlet:namespace/>outLogRepository.saveLog(jobInfo.jobUuid, outLog);
            if(lastPosition === 0){
                $("#<portlet:namespace/>out-log-wrapper > pre[id="+jobInfo.jobUuid+"]").text(outLog.outLog);
            }else if(lastPosition != outLog.lastPosition){
              $("#<portlet:namespace/>out-log-wrapper > pre[id="+jobInfo.jobUuid+"]").append(outLog.outLog);
            }
            if(jobInfo.jobStatusString === "RUNNING"){
              <portlet:namespace/>refreshTimer = 
                  setTimeout(<portlet:namespace/>readOutLog, 1000, jobInfo);
            }
        },
        error: function(){
          if(console){
            console.log('[ERROR] AJAX FAILED during getJobInfo');
          }
        }
      });
  }
  
  function <portlet:namespace/>clearReadOutLogTimer(){
      if(<portlet:namespace/>refreshTimer){
          clearTimeout(<portlet:namespace/>refreshTimer);
      }
  }
});


function <portlet:namespace/>showProgressBall(jobStatusString){
  $("#<portlet:namespace/>current-status").removeClass("queued running success failed");
  <portlet:namespace/>moveProgressBall(jobStatusString);
  if(jobStatusString === "RUNNING"){
    $("#<portlet:namespace/>current-status").addClass("running");
    <portlet:namespace/>animateProgressBall();
  }else{
    <portlet:namespace/>stopProgressBall();
  }
  if(jobStatusString === "QUEUED"){ $("#<portlet:namespace/>current-status").addClass("queued"); }
  if(jobStatusString === "INITIALIZE_FAILED"){ $("#<portlet:namespace/>current-status").addClass("failed"); }
  if(jobStatusString === "SUBMISSION_FAILED"){ $("#<portlet:namespace/>current-status").addClass("failed"); }
  if(jobStatusString === "FAILED"){ $("#<portlet:namespace/>current-status").addClass("failed"); }
  if(jobStatusString === "CANCEL"){ $("#<portlet:namespace/>current-status").addClass("cancel"); }
  if(jobStatusString === "CANCELED"){ $("#<portlet:namespace/>current-status").addClass("cancel"); }
  if(jobStatusString === "SUCCESS"){ $("#<portlet:namespace/>current-status").addClass("success"); }
}

function <portlet:namespace/>stopProgressBall(){
  $("#<portlet:namespace/>current-status").removeClass("pace-activated");
}

function <portlet:namespace/>moveProgressBall(jobStatusString){
  $("#<portlet:namespace/>current-status").hide();
  var referencePoint = $("#<portlet:namespace/>ball-point").position();
  var topCoordinate = referencePoint.top;
  var leftCoordinate = referencePoint.left - 15;
  if(jobStatusString === "RUNNING"){
    topCoordinate -= 90;
  }else{
    topCoordinate -= 95;
  }
  $("#<portlet:namespace/>current-status").animate({top: topCoordinate, left: leftCoordinate}, 0, function(){});
  $("#<portlet:namespace/>current-status").show();
}

function <portlet:namespace/>animateProgressBall(){
  if(!$("#<portlet:namespace/>current-status").hasClass("pace-activated")){
    $("#<portlet:namespace/>current-status").addClass("pace-activated");
  }
}

function <portlet:namespace/>hideProgressBall(){
  $("#<portlet:namespace/>current-status").hide();
}

function <portlet:namespace/>displayJonInfo(jobInfo){
  $("#<portlet:namespace/>job-title").text(jobInfo.jobTitle);
  $("#<portlet:namespace/>simulation-uuid").text(jobInfo.simulationUuid);
  $("#<portlet:namespace/>job-uuid").text(jobInfo.jobUuid);
  
  $("#<portlet:namespace/>job-status-text").text(jobInfo.jobStatusString);
  
  if(jobInfo.jobStartDt && jobInfo.jobStartDt.time){
    $("#<portlet:namespace/>start-time").parents(".control-group").show();   
    $("#<portlet:namespace/>start-time").text(new Date(jobInfo.jobStartDt.time));
  }else{
    $("#<portlet:namespace/>start-time").parents(".control-group").hide();
  }
  if(jobInfo.jobEndDt && jobInfo.jobEndDt.time){
    $("#<portlet:namespace/>end-time").parents(".control-group").show();
    $("#<portlet:namespace/>end-time").text(new Date(jobInfo.jobEndDt.time));
  }else{
    $("#<portlet:namespace/>end-time").parents(".control-group").hide();
  }
  <portlet:namespace/>showProgressBall(jobInfo.jobStatusString);
}

function <portlet:namespace/>removeResultFiles(){
  $("#<portlet:namespace/>result-file-wrapper").empty();
}

function <portlet:namespace/>loadJobResultFrame(jobUuid){
  $("#<portlet:namespace/>result-file-wrapper").load(
      "${jobResultFrameUrl}",
      { <portlet:namespace/>jobUuid: jobUuid },
      function(_){});
}
</script>
