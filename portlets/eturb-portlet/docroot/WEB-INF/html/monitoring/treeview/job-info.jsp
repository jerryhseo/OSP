<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@page import="org.kisti.edison.util.EdisonHttpUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="org.kisti.eturb.util.MonitoringStatusConstatns"%>
<c:set var="jobStatusSuccess" value="<%=MonitoringStatusConstatns.SUCCESS%>" scope="page" />
<liferay-portlet:resourceURL var="monitoringSearchParam"    escapeXml="false" id="searchJobParam" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="monitoringStatusUpdate"   escapeXml="false" id="updateJobStatus" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="monitoringSearchJobURL"   escapeXml="false" id="searchJobList" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="monitoringDeleteJobURL"   escapeXml="false" id="deleteJob" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="stopSimulationAPI" escapeXml="false" id="stopAPICall" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="errorSimulationAPI"       escapeXml="false" id="errorAPICall" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="scienceAppMiddleFileURL"      escapeXml="false" id="scienceAppMiddleFile" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="fileDownloadUrl" escapeXml="false" id="fileDownload" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getResultFilesUrl" escapeXml="false" id="getResultFiles" copyCurrentRenderParameters="false"/>
<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false"
 plid="${workBenchPlid}" 
 portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet"
 windowState="<%=LiferayWindowState.NORMAL.toString()%>" 
 portletMode="<%=LiferayPortletMode.VIEW.toString()%>">
    <liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="monitoringAnalysisURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}"
    portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet" windowState="<%= LiferayWindowState.POP_UP.toString()%>">
    <liferay-portlet:param name="workbenchType" value="MORANALYSIS" />
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
    <c:if test="${job.jobStatus eq 1701011}">
    <div class="btn-group pull-right">
      <button class="btn btn-primary" onclick="<portlet:namespace/>fn_collectionPopup();"><liferay-ui:message key="edison-simulation-monitoring-export-job-info" /></button>
    </div>
    </c:if>
  </div>
  <table class="table table-bordered table-hover edison-table">
    <colgroup>
      <col width="*">
      <col width="10%">
      <col width="14%">
      <col width="20%">
    </colgroup>
    <thead>
      <tr>
        <th scope="col"><liferay-ui:message key="edison-simulation-execute-job-create-list-job-name" /></th>
        <th scope="col"><liferay-ui:message key="edison-simulation-execute-job-create-list-state" /></th>
        <th scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-check-moderate" /></th>
        <th scope="col"><liferay-ui:message key="edison-simulation-monitoring-table-header-job-manage" /></th>
      </tr>
    </thead>
    <tbody id="mtbody">
      <tr id="<portlet:namespace/>monitoring-tr">
        <td>${job.jobTitle}</td>
        <td class="center"><img src="${contextPath}/images/monitoring/<%=themeDisplay.getLanguageId()%>/${job.jobStatusImg}" /></td>
        <td id="middle_check" class="center" logFileProcess-state="${job.jobLogFileProcessorYn }"></td>
        <td class="center"> 
          <img src="${contextPath}/images/monitoring/btn_monitor_rerun.png" style="cursor: pointer;"
            onclick="<portlet:namespace/>restartSimulation('${job.scienceAppId}', '${job.jobUuid}');" alt="Workbench"
            title="Workbench">
        </td>
      </tr>
    </tbody>
  </table>
</div>

</div>
<div class="virtitlebox">
    <img src="${contextPath}/images/title_virtual.png" height="20" width="20">
    <div class="virtitle">입력파일</div>
</div>
<div class="h10"></div>

<div class="filelist01">
    <ul id="<portlet:namespace/>input-file-list">
    </ul>
</div>

<div id="<portlet:namespace/>result-file-wrapper">
    <div class="virtitlebox">
        <img src="${contextPath}/images/title_virtual.png" height="20" width="20">
        <div class="virtitle">결과파일</div>
    </div>
    <div class="h10"></div>
    
    <div class="filelist01">
        <ul id="<portlet:namespace/>result-file-list">
        </ul>
    </div>
</div>
<div id="<portlet:namespace/>jobparameter-dialog"
    title="<liferay-ui:message key="edison-simulation-execute-job-detail"/>"
    style="display: none; background-color: white; padding: 0px;" class="newWindow">
    <div class="newWheader" id="<portlet:namespace/>jobparameter-dialog-title" style="cursor: move;">
        <div class="newWtitlebox">
            <img src="${contextPath}/images/title_newWindow.png" width="34" height="34">
            <div class="newWtitle">
                <liferay-ui:message key="edison-simulation-execute-job-detail" />
            </div>
        </div>
        <div class="newWclose" style="cursor: pointer;">
            <img id="<portlet:namespace/>jobparameter-dialog-dialog-close-btn"
                name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn"
                src="${contextPath}/images/btn_closeWindow.png" width="21" height="21">
        </div>
    </div>
    <div id="<portlet:namespace/>jobparameter-dialog-content" style="padding: 30px;" class="newWcont01"></div>
</div>
<div id="<portlet:namespace/>error-dialog" style="display: none; background-color: white; padding: 0px;"
    class="newWindow">
    <div class="newWheader" id="<portlet:namespace/>error-dialog-title" style="cursor: move;">
        <div class="newWtitlebox">
            <img src="${contextPath}/images/title_newWindow.png" width="34" height="34">
            <div class="newWtitle">
                <liferay-ui:message key="edison-simulation-monitoring-post-process-choice" />
            </div>
        </div>
        <div class="newWclose" style="cursor: pointer;">
            <img id="<portlet:namespace/>error-dialog-close-btn"
                name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn"
                src="${contextPath}/images/btn_closeWindow.png" width="21" height="21">
        </div>
    </div>
    <div style="padding: 30px; overflow: auto; max-height: 400px;" class="newWcont01">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1"
            style="word-break: break-all; table-layout: fixed;">
            <colgroup>
                <col width="300px" />
                <col width="*" />
            </colgroup>
            <thead>
                <tr>
                    <th scope="col" class="left"><liferay-ui:message
                            key="edison-simulation-execute-port-label-portname" /></th>
                    <th scope="col" class="left"><liferay-ui:message
                            key="edison-simulation-monitoring-post-process-nm" /></th>
                </tr>
            </thead>
            <tbody id="<portlet:namespace/>error-dialog-content">
            </tbody>
        </table>
    </div>
</div>
<div id="<portlet:namespace/>post-dialog" style="display:none; background-color:white; padding:0px;" class="newWindow">
    <div class="newWheader" id="<portlet:namespace/>post-dialog-title" style="cursor: move;">
        <div class="newWtitlebox"><img src="${contextPath}/images/title_newWindow.png" width="34" height="34">
            <div class="newWtitle"><liferay-ui:message key="edison-simulation-monitoring-post-process-choice"/></div>
        </div>
        <div class="newWclose" style="cursor: pointer;">
            <img id="<portlet:namespace/>post-dialog-close-btn" 
                name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" 
                src="${contextPath}/images/btn_closeWindow.png" width="21" height="21">
        </div>
    </div>
    <div style="padding: 30px;overflow:auto; max-height:400px;" class="newWcont01">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1" 
            style="word-break: break-all;table-layout: fixed; line-height: 1.3em;">
            <colgroup>
                <col width="300px" />
                <col width="*" />
            </colgroup>
            <thead>
                <tr>
                    <th scope="col" class="left"><liferay-ui:message key="edison-simulation-execute-port-label-portname"/></th>
                    <th scope="col" class="left">Data Type</th>
                </tr>
            </thead>
            <tbody id="<portlet:namespace/>post-dialog-content"></tbody>
        </table>
    </div>
</div>

<div id="<portlet:namespace/>show-analyzer-dialog">
    <div id="<portlet:namespace/>show-analyzer-dialog-content"></div>
</div>

<script type="text/javascript">
AUI().ready(function() {
    var jobData = ${jobData.jobData};
    <portlet:namespace/>drawInputData(jobData);
    if("${job.jobStatus}" == 1701011){
        $("#<portlet:namespace/>result-file-wrapper").show();
        <portlet:namespace/>getResultFiles('${job.jobUuid}');
    }else{
        $("#<portlet:namespace/>result-file-wrapper").hide();
    }
    
    <portlet:namespace/>monitoringController(
        '${job.jobSeqNo}', '${job.simulationUuid}', '${job.jobUuid}', '${job.scienceAppId}', '${job.jobStatus}');
    $("#<portlet:namespace/>jobparameter-dialog").dialog({
        autoOpen: false,
        width: '600',
        height: '580',
        modal: true,
        resizable: true,
        show: {effect:'fade', speed: 800}, 
        hide: {effect:'fade', speed: 800},
        open: function(event, ui) {
            $(this).removeClass("ui-widget-content");
            $(this).parent().removeClass("ui-widget-content");
            $(this).parent().css('overflow','visible');
            
            $("body").css('overflow','hidden');
            
            $("#<portlet:namespace/>jobparameter-dialog-dialog-close-btn").bind('click',function(){
                $("#<portlet:namespace/>jobparameter-dialog").dialog("close");
            });
            
            $('.ui-widget-overlay').bind('click',function(){
                $("#<portlet:namespace/>jobparameter-dialog").dialog("close");
            })
            
        },
        close: function() {
            $("#<portlet:namespace/>jobparameter-dialog-content").empty();
            $("body").css('overflow','');
        }
    }).draggable({
            handle: "#<portlet:namespace/>jobparameter-dialog-title"
    }).dialog("widget").find(".ui-dialog-titlebar").remove();
    
    
     $("#<portlet:namespace/>post-dialog").dialog({
        autoOpen: false,
        width: 700,
        height: 'auto',
        modal: true,
        resizable: true,
        show: {effect:'fade', speed: 800}, 
        hide: {effect:'fade', speed: 800},
        open: function(event, ui) {
            $(this).removeClass("ui-widget-content");
            $(this).parent().removeClass("ui-widget-content");
            $(this).parent().css('overflow','visible');
            
            $("body").css('overflow','hidden');
            
            $("#<portlet:namespace/>post-dialog-close-btn").bind('click',function(){
                $("#<portlet:namespace/>post-dialog").dialog("close");
            });
            
            $('.ui-widget-overlay').bind('click',function(){
                $("#<portlet:namespace/>post-dialog").dialog("close");
            });
        },
        close: function() {
            $("body").css('overflow','');
            $("#<portlet:namespace/>post-dialog-content").empty();
        }
    }).draggable({
            handle: "#<portlet:namespace/>post-dialog-title"
    }).dialog("widget").find(".ui-dialog-titlebar").remove();
     
     $("#<portlet:namespace/>show-analyzer-dialog").dialog({
         autoOpen: false,
         width: 1200,
         height: 800,
         modal: true,
         resizable: false,
         show: {effect:'fade', speed: 800}, 
         hide: {effect:'fade', speed: 800},
         open: function(event, ui) {
         },
         close: function() {
             //location.reload();
         }
     });
    
});

function <portlet:namespace/>getResultFiles(jobUuid){
    $.ajax({
        url : '${getResultFilesUrl}',
        type : 'POST',
        dataType : 'json',
        data : {
            <portlet:namespace/>jobUuid : jobUuid
        },
        success : function(resultFiles){
            <portlet:namespace/>drawResultData(resultFiles);
        },
        error : function(){
            if(console){
                console.log('[ERROR] AJAX FAILED during get ResultFiles');
            }
        }
    });
}

function <portlet:namespace/>drawResultData(result){
    var fileList = $("#<portlet:namespace/>result-file-list");
    var $li = $("<li/>", {
        "class": "job-file-item"
    }).append($("<img/>", {
        "src": "${contextPath}/images/folderm/file03.png"
    }));
    $.each(result.files, function(){
        var data = this;
        var thisLi = $li.clone();
        thisLi.data(data);
        thisLi.find("img").attr("title", data.name);
        thisLi.append($("<div/>", {
            "class": "name-word-wrap",
            "title":  data.name,
            "text": data.name 
        }));
        thisLi.append($("<div/>", {
            "text": <portlet:namespace/>getFileSize(data.size)
        }));
        
        fileList.append(thisLi);
    });
    <portlet:namespace/>addContextMenuToResultData();
}

function <portlet:namespace/>addContextMenuToResultData(){
    $.contextMenu({
        selector: "#<portlet:namespace/>result-file-list > .job-file-item",
        build: function ($trigger, e) {
            var fileData = $trigger.data();
            console.log(fileData);
            return {
                items: {
                    "download-file": {
                        name: "Download",
                        icon: " icon-download-alt",
                        callback: function (key, options) {
                            <portlet:namespace/>IBFileDownload(fileData.id);
                        }
                    },"add-to-open-data": {
                        name: "Add to Open data",
                        icon: " icon-save",
                        callback: function (key, options) {
                            <portlet:namespace/>createDataEntry(fileData.name, fileData.id, '${job.jobUuid}');
                        }
                    }
                }
            };
        }
    });
}

function <portlet:namespace/>createDataEntry(fileNm, fileId, jobUuid){
    AUI().use("liferay-portlet-url", function(a) {
        var portletURL = Liferay.PortletURL.createRenderURL();
        portletURL.setPortletMode("view");
        portletURL.setPlid('${myPagePlid}');
        portletURL.setPortletId("edisondataentry_WAR_edisonsimulationportlet");
        portletURL.setWindowState("<%=LiferayWindowState.MAXIMIZED.toString()%>");
        portletURL.setParameter("myRender", "manageViewDataEntry");
        portletURL.setParameter("jobUuid", jobUuid);
        portletURL.setParameter("monitoringResultFileId", fileId);
        portletURL.setParameter("monitoringResultFileNm", fileNm);
        portletURL.setParameter("jobSeqNo", '${job.jobSeqNo}');
        portletURL.setParameter("groupId", '<%=themeDisplay.getSiteGroupId()%>');
        portletURL.setParameter("simulationSubjectId", '${job.scienceAppId}');
        portletURL.setParameter("simulationUuid", '${job.simulationUuid}');
        portletURL.setParameter("redirectURL", "<%=EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent())%>");
        portletURL.setParameter("redirectName", Liferay.Language.get('edison-simulation-monitoring-title'));
        window.open(portletURL.toString(), "_blank");
    });
}

function <portlet:namespace/>IBFileDownload(fileId){
    var url = '${icebreakerUrl}/api/file/download?id='+fileId;
    window.location.href = url;
}

function <portlet:namespace/>getFileSize(bytes){
    var thresh = 1024;
    if(Math.abs(bytes) < thresh) {
        return bytes + ' B';
    }
    var units = ['KB','MB','GB','TB','PB','EB','ZB','YB'];
    var u = -1;
    do {
        bytes /= thresh;
        ++u;
    } while(Math.abs(bytes) >= thresh && u < units.length - 1);
    return bytes.toFixed(1)+' '+units[u];
}


function <portlet:namespace/>drawInputData(jobData){
    var fileList = $("#<portlet:namespace/>input-file-list");
    var $li = $("<li/>", {
        "class": "job-file-item"
    }).append($("<img/>", {
        "src": "${contextPath}/images/folderm/file02.png"
    }));
    $.each(jobData, function(){
        var data = new OSP.InputData(this);
        var thisLi = $li.clone();
        thisLi.data(data);
        thisLi.find("img").attr("title", data.portName());
        thisLi.append($("<div/>", {
            "class": "name-word-wrap",
            "title":  data.portName(),
            "text": data.portName() 
        }));
        fileList.append(thisLi);
    });
    <portlet:namespace/>addContextMenuToInputData();
}

function <portlet:namespace/>addContextMenuToInputData(){
    $.contextMenu({
        selector: "#<portlet:namespace/>input-file-list > .job-file-item",
        build: function ($trigger, e) {
            console.log($trigger.data());
            return {
                items: {
                    "download-file": {
                        name: "Download",
                        icon: " icon-download-alt",
                        callback: function (key, options) {
                            <portlet:namespace/>inputFileDownload($trigger.data(), '${job.jobUuid}');
                        }
                    }
                }
            };
        }
    });
}

function <portlet:namespace/>inputFileDownload(ospInputData, jobUuid){
    var data = {
        <portlet:namespace/>jobUuid: jobUuid,
        <portlet:namespace/>portName: ospInputData.portName()
    };
    
    var base = '<%=fileDownloadUrl.toString()%>';
    var sep = (base.indexOf('?') > -1) ? '&' : '?';
    var url = base + sep + $.param(data);
    location.href = url;
}

//재실행
function <portlet:namespace/>restartSimulation(p_scienceAppId, p_jobUuid){
    var thisPortletNamespace = "_SimulationWorkbench_WAR_OSPWorkbenchportlet_";

    var URL = "<%=workbenchURL%>";
    var params = "&" +thisPortletNamespace+ "scienceAppId=" + p_scienceAppId;
    params += "&" +thisPortletNamespace+ "jobUuid=" + p_jobUuid;
    params += "&" +thisPortletNamespace+ "customId=0";
    params += "&" +thisPortletNamespace+ "classId=0";
    params += "&" +thisPortletNamespace+ "testYn=false";

    location.href = URL + params;
}


//모니터링 Job 삭제
//jobSeqNo 가 0일 때는 JOB테이블의 리스트 삭제
function <portlet:namespace/>deleteMonitoring(simulationUuid, jobSeqNo){
  var deleteData = {
          "<portlet:namespace/>simulationUuid": simulationUuid,
          "<portlet:namespace/>groupId": "<%=themeDisplay.getSiteGroupId()%>",
          "<portlet:namespace/>jobSeqNo": jobSeqNo
          };
  
  if(confirm("<liferay-ui:message key='edison-simulation-monitoring-delete-job-question'/>")){
      jQuery.ajax({
          type: "POST",
          url: "<%=monitoringDeleteJobURL%>",
          data  : deleteData,
          async : false,
          success: function(msg) {
              if(msg=="SUCCESS"){
                  alert('Delete Success');
                  $("#<portlet:namespace/>monitoringSearch").submit();
              }else{
                  alert("Delete Failed\n"+msg+"");
              }
          },
          error:function(msg){
              alert("System deleteMonitoring error"+msg);
          }
      });
  }
}

//시뮬레이션 중지
function <portlet:namespace/>stop_simulation(jobSeqNo,simulationUuid,jobUuid,area){
    if(confirm('<liferay-ui:message key="edison-simulation-monitoring-canceling-a-job" />')){
        <portlet:namespace/>stopSimulationAPI(jobSeqNo, simulationUuid, jobUuid, area);
    }else{
        return;
    }
}

//작업 취소 API 호출 
function <portlet:namespace/>stopSimulationAPI(jobSeqNo, simulationUuid, jobUuid, area){
    if(jobUuid == ""){
        <portlet:namespace/>updateStatus(jobSeqNo, simulationUuid, "<%=MonitoringStatusConstatns.CANCELED%>");
        alert('<liferay-ui:message key="edison-simulation-monitoring-job-was-canceled" />');
    }else{
        var stopData = {
                "<portlet:namespace/>simulationUuid": simulationUuid,
                "<portlet:namespace/>jobUuid": jobUuid,
                "<portlet:namespace/>groupId": "<%=themeDisplay.getSiteGroupId()%>"
                };
        jQuery.ajax({
            type: "POST",
            url: "<%=stopSimulationAPI%>",
            data  : stopData,
            dataType:"text",
            success:function(msg){
                if(msg=="200"){
                    <portlet:namespace/>updateStatus(jobSeqNo,simulationUuid,"<%=MonitoringStatusConstatns.CANCELED%>");
                    <portlet:namespace/>eventChangeStatusView(area.attr("id"),"<%=MonitoringStatusConstatns.CANCELED%>",simulationUuid,jobSeqNo,false);
                    alert('<liferay-ui:message key="edison-simulation-monitoring-job-was-canceled" />');
                }else if(msg=="405"){
                    alert('<liferay-ui:message key="edison-simulation-monitoring-the-state-can-not-cancel" />');
                }else if(msg=="404"){
                    alert('<liferay-ui:message key="edison-simulation-monitoring-no-job" />');
                }else if(msg=="400"){
                    alert("Bad Paramter.response CODE:"+msg);
                }else{
                    alert('<liferay-ui:message key="edison-simulation-monitoring-bad-request" />.response CODE:'+msg);
                }
            },
            error:function(msg){
                alert("System Exception stopSimulationAPI: " + msg);
            }
        });
    }
}

/*이벤트에 따른 상태 관련 화면 변경*/
function <portlet:namespace/>eventChangeStatusView(rowId, jobStatus, simulationUuid, jobSeqNo, jobStatusSearch){
    var searchData = {
            "<portlet:namespace/>simulationUuid": simulationUuid, 
            "<portlet:namespace/>jobSeqNo": jobSeqNo,
            "<portlet:namespace/>groupId": "<%=themeDisplay.getSiteGroupId()%>",
            "<portlet:namespace/>jobStatusSearch": jobStatusSearch
        };
    
    jQuery.ajax({
        type: "POST",
        url: "<%=monitoringSearchJobURL%>",
        data  : searchData,
        async : false,
        dataType: 'json',
        success: function(msg) {
            var data = msg.data;
            if(jobStatus!=data.jobStatus){
                $trArea = $("#"+rowId);
                $status_area = $trArea.find("td:eq(4)");
                
                $status_area.empty();
                $status_area.addClass("TC").append(
                        $("<img/>").attr("src","${contextPath}/images/monitoring/<%=themeDisplay.getLanguageId()%>/"+data.jobStatusImg)
                );
                $trArea.attr("data-status",data.jobStatus);
                if(data.cluster != "EDISON-RESTORE"){
                    <portlet:namespace/>monitoringController(data.jobSeqNo,data.simulationUuid,data.jobUuid,data.scienceAppId,data.jobStatus,$trArea)
                }   
            }
        },
        error:function(msg){
            alert("System Exception eventChangeStatusView: " + msg);
        }
    });
};


//DB 상태 UPDATE
function <portlet:namespace/>updateStatus(jobSeqNo,simulationUuid,updateStatus){
    var updateData = {
        "<portlet:namespace/>jobSeqNo": jobSeqNo,
        "<portlet:namespace/>simulationUuid": simulationUuid,
        "<portlet:namespace/>groupId": "<%=themeDisplay.getSiteGroupId()%>",
        "<portlet:namespace/>jobStatus":updateStatus
    };
    jQuery.ajax({
        type: "POST",
        url: "<%=monitoringStatusUpdate%>",
        data  : updateData,
        error:function(msg){
            alert("System Exception updateStatus: " + msg);
        }
    });
}


//중간보기
function <portlet:namespace/>graph_event(scienceAppId, jobUuid, simulationUuid){
  var searchData = {
              "<portlet:namespace/>scienceAppId": scienceAppId,
              "<portlet:namespace/>groupId": "<%=themeDisplay.getSiteGroupId()%>"
              };
  jQuery.ajax({
      type: "POST",
      url: "<%=scienceAppMiddleFileURL%>",
      data  : searchData,
      dataType: 'json',
      success:function(data){
          if(data.fileState){
              openAnalyzerWindow( 
                      data.portMap.exeFileName, 
                      data.shipmentForm,
                      data.lookUpPath,
                      data.filePath);
          }else{
              alert(Liferay.Language.get('edison-simulation-monitoring-services-are-not-support'));
          }
      },
      error:function(msg){
          alert("System Exception error graph_event: " + msg);
      }
  });
}

//결과보기 - 에러보기
function <portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid){
    var searchData = {
            "<portlet:namespace/>simulationUuid": simulationUuid,
            "<portlet:namespace/>jobSeqNo,": jobSeqNo,
            "<portlet:namespace/>jobUuid": jobUuid,
            "<portlet:namespace/>groupId": "<%=themeDisplay.getSiteGroupId()%>"
            };
    jQuery.ajax({
        type: "POST",
        url: "<%=errorSimulationAPI%>",
        data  : searchData,
        datatype:"text",
        success:function(data){
            if(data!=""){
                var dataSize = data.length;
                var dataMap = data.portMapList;
                $dialogBody = $("#<portlet:namespace/>error-dialog-content");
                
                for(var i=0; i< dataMap.length; i++){
                    $tr = $("<tr></tr>").addClass("postlist").attr("onClick",
                            "event.cancelBubble=true; openAnalyzerWindow('" + 
                            dataMap[i].exeFileName +"', '" + 
                            dataMap[i].shipmentForm +"', '" +
                            dataMap[i].lookUpPath +"', '" +
                            dataMap[i].filePath +"');")
                            .appendTo($dialogBody);
                    $("<td></td>").addClass("TC").css("cursor","pointer").text(dataMap[i].portName).appendTo($tr);
                    $("<td></td>").addClass("TC").css("cursor","pointer").text(dataMap[i].name).appendTo($tr);
                }
                $("#<portlet:namespace/>error-dialog").dialog("open");
            }
            
        },
        error:function(msg){
            alert("System Exception[error_event]: " + msg);
        }
    });
}

//상태값에 따른 모니터링 작업 영역 update
function <portlet:namespace/>monitoringController(jobSeqNo,simulationUuid,jobUuid,scienceAppId,jobStatus){
    $trArea = $("#<portlet:namespace/>monitoring-tr");
    $jobControllArea =  $trArea.children("td[id=job_controll]");
    $middleCheckArea = $trArea.children("td[id=middle_check]");
    $resultViewArea = $trArea.children("td[id=result_view]");
    
    //초기화
    $jobControllArea.empty();
    $middleCheckArea.empty();
    $resultViewArea.empty();
    
    //대기,처리중
    if(jobStatus=="<%=MonitoringStatusConstatns.QUEUED%>" 
        || jobStatus=="<%=MonitoringStatusConstatns.RUNNING%>"){
        $("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_cancel.png")
                   .css("cursor","pointer")
                   .click(function(){<portlet:namespace/>stop_simulation(jobSeqNo,simulationUuid,jobUuid,$trArea);})
                   .appendTo($jobControllArea);
        
        
        //처리중일 경우 중간 결과 확인 할 수 있도록
        if(jobStatus=="<%=MonitoringStatusConstatns.RUNNING%>"){
            if($middleCheckArea.attr("logFileProcess-state")=="Y"){
                $("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_error.png")
                .attr("width","22px")
                .attr("height","22px")
                   .click(function(){<portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid);})
                   .css("cursor","pointer")
                   .appendTo($middleCheckArea);
                
                $middleCheckArea.append("&nbsp;");
            }
        }
    }else if(jobStatus=="<%=MonitoringStatusConstatns.SUCCESS%>"){
        if($middleCheckArea.attr("logFileProcess-state")=="Y"){
            $("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_error.png")
                        .attr("width","22px")
                        .attr("height","22px")
                       .click(function(){<portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid);})
                       .css("cursor","pointer")
                       .appendTo($middleCheckArea);
            
            $middleCheckArea.append("&nbsp;");
        }
        
        $("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_visual.png")
                   .attr("width","22px")
                   .attr("height","22px")
                   .css("cursor","pointer")
                   .click(function(e){
                       event.cancelBubble=true;
                       <portlet:namespace/>popOutputPorts(simulationUuid, jobUuid);
                   }).appendTo($resultViewArea);
    //실패
    }else if(jobStatus=="<%=MonitoringStatusConstatns.FAILED%>"){
        if($middleCheckArea.attr("logFileProcess-state") == "Y"){
            $("<img/>").attr("src","${contextPath}/images/monitoring/btn_monitor_error.png")
                       .attr("width","22px")
                       .attr("height","22px")
                       .click(function(){<portlet:namespace/>error_event(simulationUuid, jobSeqNo, jobUuid);})
                       .css("cursor","pointer")
                       .appendTo($middleCheckArea);
            
            $middleCheckArea.append("&nbsp;");
        }
    }
}


function <portlet:namespace/>popOutputPorts(simulationUuid, jobUuid){
    var outputPortsJson = '${outputPortJson}';
    if(outputPortsJson && outputPortsJson != ''){
        var scienceApp = new OSP.ScienceApp();
        scienceApp.deserializeOutputPorts($.parseJSON(outputPortsJson));
        var outputPorts = scienceApp.outputPortsArray();
        console.log(scienceApp.outputPortsArray());
        $("#<portlet:namespace/>post-dialog").dialog("open");
        
        $dialogBody = $("#<portlet:namespace/>post-dialog-content");
        for(var i=0; i< outputPorts.length; i++){
            var outputPort = outputPorts[i];
            var outputPath = OSP.Util.mergePath(
                <portlet:namespace/>getJobOutputFolder(simulationUuid, jobUuid), outputPort.outputData().parent());
            outputPort.outputData().parent(outputPath);
            var $tr = $("<tr></tr>").addClass("postlist").appendTo($dialogBody);
            $("<td></td>").addClass("TC").css("cursor", "pointer").text(outputPort.name()).appendTo($tr);
            $("<td></td>").addClass("TC").css("cursor", "pointer")
                .text(outputPort.dataType().name + " " + outputPort.dataType().version).appendTo($tr);
            $tr.data(outputPort);
            $tr.click(function(e){
               e.stopPropagation();
               <portlet:namespace/>displayAnalyzer($(this).data());
            });
        }
    }
}


function openAnalyzerWindow(portletId, shipmentForm, lookUpPath, filePath){
    AUI().use("liferay-portlet-url", function(a) {
        if($("#<portlet:namespace/>post-dialog").dialog('isOpen')){
            $("#<portlet:namespace/>post-dialog").dialog("close");
        }
        var param = "";
        param += "&_Workbench_WAR_OSPWorkbenchportlet_pathType="+shipmentForm;
        param += "&_Workbench_WAR_OSPWorkbenchportlet_parentPath="+lookUpPath;
        param += "&_Workbench_WAR_OSPWorkbenchportlet_fileName="+filePath;
        param += "&_Workbench_WAR_OSPWorkbenchportlet_portletId="+portletId;
        param += "&_Workbench_WAR_OSPWorkbenchportlet_relative=true";
        param += "&_Workbench_WAR_OSPWorkbenchportlet_loadNow=true";
        param += "&_Workbench_WAR_OSPWorkbenchportlet_height=0.73";
        param += "&_Workbench_WAR_OSPWorkbenchportlet_connector=<%=portletDisplay.getId()%>";
        
        var URL = "<%=monitoringAnalysisURL%>" + param;
        $("#<portlet:namespace/>show-analyzer-dialog").load(URL).dialog('open');
    });
}

function <portlet:namespace/>displayAnalyzer(outputPort){
    $("#<portlet:namespace/>post-dialog").dialog("close");
    
    AUI().use("liferay-portlet-url", function(a) {
        if($("#<portlet:namespace/>post-dialog").dialog('isOpen')){
            $("#<portlet:namespace/>post-dialog").dialog("close");
        }
        var renderURL = Liferay.PortletURL.createRenderURL();
        var inputData = outputPort.outputData();
        renderURL.setPortletId(outputPort.defaultAnalyzer());
        renderURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
        renderURL.setParameter("inputData", JSON.stringify(inputData));
        renderURL.setParameter('eventEnable', false);
        
        $("#<portlet:namespace/>show-analyzer-dialog").load(renderURL.toString()).dialog('open');
    });
}

function <portlet:namespace/>getJobOutputFolder(simulationUuid, jobUuid){
    return simulationUuid + '/' + jobUuid + '.job';
}

//시뮬레이션 파라미터 정보 조회
function <portlet:namespace/>searchSimulationParam(simulationUuid, jobSeqNo, jobUuid){
    var searchData = {
            "<portlet:namespace/>simulationUuid": simulationUuid,
            "<portlet:namespace/>jobSeqNo": jobSeqNo,
            "<portlet:namespace/>jobUuid": jobUuid
            };
    jQuery.ajax({
        type: "POST",
        url: "<%=monitoringSearchParam%>",
        data  : searchData,
        async : false,
        success: function(data) {
            $content = $("#<portlet:namespace/>jobparameter-dialog-content");
            $content = $content.empty();
            
            $("<div>").addClass("tbcell070101").append(
                    $("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
                    ).append("<liferay-ui:message key='edison-button-board-submit-time'/>").appendTo($content);
            $("<div>").addClass("tbcell070302").html(data.jobSubmitDt).appendTo($content);
            
            $("<div>").addClass("tbcell070101").append(
                    $("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
                    ).append("<liferay-ui:message key='edison-simulation-monitoring-table-header-complete-time'/>").appendTo($content);
            
            var timeHtmlStr = "";
            if(data.jobEndDt!=""){
                timeHtmlStr = data.jobEndDt;
            }

            if(data.jobStatus=="<%=MonitoringStatusConstatns.QUEUED%>"){
                timeHtmlStr += "<span>(<liferay-ui:message key='edison-simulation-monitoring-submit'/>)</span>";
            }else if(data.jobStatus=="<%=MonitoringStatusConstatns.RUNNING%>"){
                timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-table-header-waiting-time"/>  '+data.stayDt+"/"+'<liferay-ui:message key="edison-simulation-monitoring-running"/>'+")</span>";
            }else if(data.jobStatus=="<%=MonitoringStatusConstatns.SUSPENDED%>"){
                timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-table-header-waiting-time"/>  '+data.stayDt+"/"+'<liferay-ui:message key="edison-simulation-monitoring-error"/>'+")</span>";
            }else if(data.jobStatus=="<%=MonitoringStatusConstatns.FAILED%>"){
                timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-error" />'+")</span>";
            }else if(data.jobStatus=="<%=MonitoringStatusConstatns.SUBMISSION_FAILED%>"){
                timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-error" />'+")</span>";
            }else if(data.jobStatus=="<%=MonitoringStatusConstatns.CANCELED%>"){
                timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-cancel" />'+")</span>";
            }else{
                timeHtmlStr += "<span>("+'<liferay-ui:message key="edison-simulation-monitoring-table-header-waiting-time"/>  '+data.stayDt+"/"+'<liferay-ui:message key="edison-simulation-monitoring-table-header-running-time"/>  '+data.executeDt+")</span>";
            }
            $("<div>").addClass("tbcell070202").css("border-bottom","solid 1px #dadada").html(timeHtmlStr).appendTo($content);
            
            if(data.inputPorts != "" && data.simulationJobData != "") {
                var inputPorts = JSON.parse(data.inputPorts);
                var simulationJobData = JSON.parse(data.simulationJobData);
                
                var portNameList = Object.keys(inputPorts);
                
                $("<div>").addClass("tbcell070101").append(
                        $("<img/>").attr("src","${contextPath}/images/monitoring/contents_arr.png")
                        ).append("<liferay-ui:message key='edison-simulation-execute-job-pre' />").appendTo($content);
                
                
                console.log(inputPorts);
                console.log(simulationJobData);
                for (var i = 0; i < simulationJobData.length; i++) {
                    <%-- TODO : simulation jobdata job 상세정보 기능 --%>
                    
                    var inputData = new OSP.InputData(simulationJobData[i]);
                    var optionHtmlStr = "";
                    if(inputData.type() === OSP.Enumeration.PathType.DLENTRY_ID){
                        optionHtmlStr += "<p style='color:black; margin: 0px;'>PortName : "
                            + inputData.portName() + "</p><p style='white-space: pre-wrap; word-break: break-all;'>SAMPLE FILE</p>";
                    }else if(inputData.type() === OSP.Enumeration.PathType.FILE_CONTENT
                        || inputData.type() === OSP.Enumeration.PathType.CONTEXT){
                        optionHtmlStr += "<p style='color:black; margin: 0px;'>PortName : "
                            + inputData.portName() + "</p><p style='white-space: pre-wrap; word-break: break-all;'>"
                            + inputData.context() + "</p>"
                    }else if(inputData.type() === OSP.Enumeration.PathType.FILE){
                        optionHtmlStr += "<p style='color:black; margin: 0px;'>PortName : "
                            + inputData.portName() + "</p><p style='white-space: pre-wrap; word-break: break-all;'>" 
                            + "File Name : " + inputData.name() + "</p>"
                    }
                    $("<div>").addClass("tbcell070201").html(optionHtmlStr).appendTo($content);
                }
            }
            $("#<portlet:namespace/>jobparameter-dialog").dialog("open");
        },
        error:function(msg){
            alert("System searchSimulationParam : " + msg);
        }
    });
}
</script>
