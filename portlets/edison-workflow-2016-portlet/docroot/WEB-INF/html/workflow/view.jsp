<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="getEditorPortletUrl" escapeXml="false" id="getEditorPortlet"
  copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="getAuthTokenUrl" escapeXml="false" id="getAuthToken"
  copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="getSpecificSiteGroupIdUrl" escapeXml="false" id="getSpecificSiteGroupId"
  copyCurrentRenderParameters="false" />  
<liferay-portlet:resourceURL var="getIcebreakerAccessTokenUrl" escapeXml="false" id="getIcebreakerAccessToken"
  copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="getWorkflowInstancesUrl" escapeXml="false" id="getWorkflowInstances"
  copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateWorkflowConfUrl" escapeXml="false" id="updateWorkflowConf"
  copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="savePortletSessionValueURL" id="savePortletSessionValue"
  copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="getSimulationResultDirectoryURL" id="getSimulationResultDirectory"
  copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="uploadWorkflowTutorialURL" id="uploadWorkflowTutorial"
  copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="downloadWorkflowTutorialURL" id="downloadWorkflowTutorial"
  copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:renderURL var="resultDownLoadURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>"
  copyCurrentRenderParameters="false">
  <liferay-portlet:param name="myaction" value="resultDownLoad" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>

<script src="${contextPath}/js/science_platform_events.js"></script>
<script>
var var_save_success_message =  Liferay.Language.get("edison-workflow-save-success-message");
var var_new_workflow_confirm_message = Liferay.Language.get("edison-workflow-new-confirm-message");
var var_remove_workflow_confirm_message = Liferay.Language.get("edison-workflow-remove-confirm-message");
var var_prepare_remove_workflow_message = Liferay.Language.get("edison-workflow-prepare-remove-message");
var var_success_remove_workflow_message = Liferay.Language.get("edison-workflow-success-remove-message");
var var_prepare_copy_workflow_message = Liferay.Language.get("edison-workflow-prepare-copy-message");
var var_success_copy_workflow_message = Liferay.Language.get("edison-workflow-success-copy-message");
var var_validation_required_message = Liferay.Language.get("edison-workflow-validation-required-message");
var var_data_empty_message = Liferay.Language.get("edison-workflow-data-empty-message");
var var_success_run_workflow_message = Liferay.Language.get("edison-workflow-success-run-message");
var var_run_workflow_save_err_message = Liferay.Language.get("edison-workflow-run-save-err-message");
var var_run_workflow_empty_err_message = Liferay.Language.get("edison-workflow-run-empty-err-message");
var var_remove_app_confirm = Liferay.Language.get("edison-workflow-remove-app-confirm");
var var_confirm_romove_workflow_instance_message = Liferay.Language.get("edison-workflow-remove-workflow-instance-confirm");
var var_private_default_editor_message = Liferay.Language.get("edison-workflow-privat-default-editor-message");
var var_conf_workflow_empty_err_message = Liferay.Language.get("edison-workflow-empty-workflow-conf-message");
var var_cannot_load_analyzer_message = Liferay.Language.get("edison-workflow-cannot-load-analyzer-message");
var var_cannot_load_intermediate_result_message = Liferay.Language.get("edison-workflow-cannot-intermediate-result-message");
var var_no_available_analyzer_message = Liferay.Language.get("edison-workflow-no-available-analyzer-message");
var var_workflow_status_not_found_message = Liferay.Language.get("edison-workflow-status-not-found");
var contextPath = '${contextPath}';
</script>
<style>
.script-viewer{overflow-y: scroll; height: 425px;}
</style>
<h1>
  <img src="${contextPath}/images/title_virtual.png"/>
  <liferay-ui:message key="edison-simulation-workflow" />
</h1>
<div id="science-app" class="wf-container">
  <div class="wftitlebox001">
    <input type="text" name="worfklow-definition-name" id="worfklow-definition-name" /> 
   	<span> 
   		<input class="btn btn-default" id="wf-new-button" value="<liferay-ui:message key='edison-workflow-create-new'/>" type="button"> 
   		<input class="btn btn-default" id="wf-save-button" value="<liferay-ui:message key='edison-workflow-save'/>" type="button"> 
		<input class="btn btn-default" id="wf-copy-button" value="<liferay-ui:message key='edison-workflow-copy'/>" type="button"> 
		<input class="btn btn-default" id="wf-remove-button" value="<liferay-ui:message key='edison-workflow-delete'/>" type="button"> 
		<input class="btn btn-default" id="wf-run-button" value="<liferay-ui:message key='edison-workflow-run'/>" type="button">
		<input class="btn btn-default" id="wf-conf-button" value="<liferay-ui:message key='edison-wf-conf-button'/>" type="button"> 
		<input class="btn btn-default" id="wf-list-refresh-button" value="refresh" type="button" style="display: none !important;">
		</span>
	</div>
  <div class="lefttabm">
    <ul>
      <li class="on science-app"><liferay-ui:message key="edison-workflow-science-app" /></li>
      <li class="running-workflow"><liferay-ui:message key="edison-workflow-running-workflow" /></li>
      <li class="my-workflow"><liferay-ui:message key="edison-workflow-my-workflow" /></li>
      <li class="public-workflow"><liferay-ui:message key="edison-workflow-open-workflow" /></li>
    </ul>
  </div>
  <div class="leftwrap wrap">
    <div class="lefttreemenu science-app">
      <div class="input-group">
		<input id="science-app-search" class="form-control" name="science-app-search" type="text" size="40" value="" autocomplete="off"
				placeholder="<liferay-ui:message key='edison-workflow-science-app-filter'/>" /> 
		<div class="input-group-btn">
			<button id="keyWordB" class="btn btn-default" type="button">
				<i class="icon-search"></i>
			</button>
		</div>
      </div>
      <div class="lefttreemenu1 wf-jstree" id="app-list"></div>
    </div>
  </div>
  <div class="alert"></div>
  <div class="rightwrap">
    <div id="wf-workflow-canvas" class="apparea wf-drop jsplumb-drag-select"></div>
  </div>
</div>
<div id="running-workflow" class="wf-container hidden">
  <div class="wftitlebox001">
    <h2 class="workflow-name-h2"></h2>
    <span> 
    	<input class="btn btn-default button03" style="display: none;" id="wf-runing-pause-button"
				value="<liferay-ui:message key='edison-workflow-runing-pause'/>" type="button"> <input
      class="btn btn-default button03" style="display: none;" id="wf-runing-resume-button"
      value="<liferay-ui:message key='edison-workflow-runing-resume'/>" type="button"> <input
      class="btn btn-default button03" id="wf-runing-remove-button" value="<liferay-ui:message key='edison-workflow-delete'/>"
      type="button">
    </span>
  </div>
  <div class="lefttabm">
    <ul>
      <li class="on science-app"><liferay-ui:message key="edison-workflow-science-app" /></li>
      <li class="running-workflow"><liferay-ui:message key="edison-workflow-running-workflow" /></li>
      <li class="my-workflow"><liferay-ui:message key="edison-workflow-my-workflow" /></li>
      <li class="public-workflow"><liferay-ui:message key="edison-workflow-open-workflow" /></li>
    </ul>
  </div>
  <div class="wfconwrap wrap">
    <div class="lefttablemenu">
      <div class="searchbox clearfix" style="padding: 0px;">
      	<div class="input-group">
        	<input id="search-running-workflow-name" class="form-control" name="search-running-workflow-name" type="text" size="40" value=""
          		 style="width: 28%; float: right;"	autocomplete="off" placeholder="<liferay-ui:message key='edison-workflow-workflow-instance-name-filter'/>" />
        	<div class="input-group-btn">
	        	<button id="search-running-workflow-name-large-btn" class="btn btn-default" type="button"><i class="icon-search"></i></button>
        	</div>
       	</div>
      </div>
      <div class="tabletopwf line-per-page">
        <select id="running-workflow-line-per-page" name="running-workflow-line-per-page" class="form-control">
          <option value="10">10
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="20">20
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="30">30
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="40">40
            <liferay-ui:message key="edison-search-views" /></option>
        </select>
      </div>
      <div class="tabletopwf status">
        <select id="running-workflow-search-status" name="running-workflow-search-status" class="form-control">
          <option value="" selected="selected"><liferay-ui:message key="edison-workflow-running-status" /></option>
          <option value="RUNNING">Running</option>
          <option value="COMPLETED">Completed</option>
          <option value="PAUSED">Paused</option>
          <option value="FAILED">Failed</option>
          <option value="CANCELED">Canceled</option>
        </select>
      </div>
      <div class="slideclosearrow">
        <a class="wf-slide-toggle" href="#slide-close"><img
          src="${contextPath}/images/Workflow/slideclose_arrow.png" width="30" height="48"></a>
      </div>
      <div style="height: 37px;"></div>
      <div class="table-responsive panel edison-panel">
      	<div class="panel-heading clearfix">
      	</div>
        <table class="table1_list table table-bordered table-hover edison-table">
          <colgroup>
            <col width="20%">
            <col width="20%">
            <col width="15%">
            <col width="15%">
            <col width="30%">
            <!-- <col width="15%">
            <col width="15%"> -->
          </colgroup>
          <thead>
            <tr>
              <th scope="col"><liferay-ui:message key="edison-workflow-instance-name" /></th>
              <th scope="col"><liferay-ui:message key="edison-workflow-definition" /></th>
              <th scope="col"><liferay-ui:message key="edison-table-list-header-run" />ID</th>
              <th scope="col"><liferay-ui:message key="edison-workflow-running-status" /></th>
              <th scope="col"><liferay-ui:message key="edison-workflow-running-jobs" /></th>
              <%-- <th scope="col"><liferay-ui:message key="edison-workflow-running-start-date" /></th>
              <th scope="col"><liferay-ui:message key="edison-workflow-running-end-date" /></th> --%>
            </tr>
          </thead>
          <tbody class="workflow-list-tbody">
          </tbody>
        </table>
        
		<div id="running-workflow-paging" class="text-center"></div>
      </div>
    </div>
  </div>
  <div class="leftwrap wrap" style="display: none;">
    <div class="lefttreemenu running-workflow">
      <div class="searchbox">
			<div class="input-group">
				<input id="search-running-workflow-name-small" class="form-control" name="search-running-workflow-name" type="text" size="40"
						value="" autocomplete="off"
						placeholder="<liferay-ui:message key='edison-workflow-workflow-instance-name-filter'/>" /> 
				<div class="input-group-btn">
						<button id="search-running-workflow-name-small-btn" class="btn btn-default" type="button"><i class="icon-search"></i></button>
				</div>
			</div>
      </div>
      <div class="openslidearrow">
        <a class="wf-slide-toggle" href="#slide-open"><img src="${contextPath}/images/Workflow/slideopen_arrow.png"
          width="32" height="51"></a>
      </div>
      <div class="lefttreemenu1 wf-jstree" id="workflow-instance-list"></div>
    </div>
  </div>
  <div class="alert"></div>
  <div class="rightwrap" style="display: none;">
    <div id="running-workflow-canvas" class="right apparea"></div>
  </div>


  <!-- inputports Dialog 시작 -->
  <div id="running-workflow-inputports-dialog" title="<liferay-ui:message key="edison-simulation-execute-job-detail"/>" style="display:none; background-color:white; padding:0px;" class="newWindow">
	<div class="newWheader" id="<portlet:namespace/>jobparameter-dialog-title" style="cursor: move;">
			<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
				<div class="newWtitle"><liferay-ui:message key="edison-workflow-input-port-info"/></div>
			</div>
			<div class="newWclose" style="cursor: pointer;">
				<img id="running-workflow-inputports-dialog-close-btn" name="running-workflow-inputports-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
			</div>
	</div>
	<div id="running-workflow-inputports-dialog-content" style="padding: 30px;" class="newWcont01">
      <div class="input-port-name"></div>
      <div class="input-port-content"></div>
	</div>
</div>
 <!-- inputPorts Dialog 끝 -->


  <div id="running-workflow-log" class="table-responsive panel edison-panel" style="display: none;">
     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1_list table table-bordered table-hover edison-table">
       <colgroup>
         <col width="*%">
         <col width="15%">
         <col width="25%">
         <col width="25%">
         <!-- <col width="*"> -->
       </colgroup>
       <thead>
         <tr>
           <th scope="col"><liferay-ui:message key="edison-virtuallab-app-name" /></th>
           <th scope="col"><liferay-ui:message key="edison-table-list-header-status" /></th>
           <th scope="col"><liferay-ui:message key="edison-workflow-running-start-date" /></th>
           <th scope="col"><liferay-ui:message key="edison-workflow-running-end-date" /></th>
           <!-- <th scope="col">실행결과</th> -->
         </tr>
       </thead>
       <tbody id="running-workflow-log-tbody">
         <tr class="bgcolor">
           <td class="center" colspan="4"><liferay-ui:message key="edison-workflow-data-empty-message" /></td>
         </tr>
       </tbody>
     </table>
  </div>
</div>

<div id="my-workflow" class="wf-container hidden">
  <div class="wftitlebox001">
    <h2 id="my-workflow-name" class="workflow-name-h2"></h2>
    <span>
      <input class="btn btn-default button03" id="wf-modify-button" value="<liferay-ui:message key='edison-workflow-edit'/>" type="button" />
      <input class="btn btn-default button03" id="wf-my-copy-button" value="<liferay-ui:message key='edison-workflow-import'/>" type="button">
      <input class="btn btn-default button03" id="wf-my-remove-button" value="<liferay-ui:message key='edison-workflow-delete'/>" type="button">
    </span>
  </div>
  <div class="lefttabm">
    <ul>
      <li class="science-app"><liferay-ui:message key="edison-workflow-science-app" /></li>
      <li class="running-workflow"><liferay-ui:message key="edison-workflow-running-workflow" /></li>
      <li class="my-workflow"><liferay-ui:message key="edison-workflow-my-workflow" /></li>
      <li class="public-workflow"><liferay-ui:message key="edison-workflow-open-workflow" /></li>
    </ul>
  </div>
  <div class="wfconwrap wrap" style="display: none;">
    <div class="lefttablemenu">
      <div class="searchbox">
      	<div class="input-group">
	        <input id="search-my-workflow-name-large" class="form-control" name="search-my-workflow-name" type="text" size="40" value=""
	          autocomplete="off" placeholder="<liferay-ui:message key='edison-workflow-workflow-name-filter'/>" /> 
	          <div class="input-group-btn">
					<button id="search-my-workflow-name-large-btn" class="btn btn-default" type="button"><i class="icon-search"></i></button>
	          </div>
      	</div>
      </div>
      <div class="tabletopwf">
        <select id="my-workflow-line-per-page" name="my-workflow-line-per-page" class="form-control">
          <option value="10">10
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="20">20
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="30">30
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="40">40
            <liferay-ui:message key="edison-search-views" /></option>
        </select>
      </div>
      <div class="slideclosearrow">
        <a class="wf-slide-toggle" href="#slide-close"><img
          src="${contextPath}/images/Workflow/slideclose_arrow.png" width="30" height="48"></a>
      </div>
      <div style="height: 37px;"></div>
      <div class="table-responsive panel edison-panel">
      	<div class="panel-heading clearfix">
      	</div>
          <table class="table1_list table table-bordered table-hover edison-table">
            <colgroup>
              <col width="20%">
              <col width="20%">
              <col width="15%">
              <col width="15%">
              <col width="15%">
              <col width="15%">
            </colgroup>
            <thead>
              <tr>
                <th scope="col"><liferay-ui:message key="edison-workflow-name" /></th>
                <th scope="col"><liferay-ui:message key="edison-simulation-execute-simulation-description" /></th>
                <th scope="col"><liferay-ui:message key="edison-virtuallab-owner" /></th>
                <th scope="col">Copied from</th>
                <th scope="col"><liferay-ui:message key="edison-workflow-create-date" /></th>
                <th scope="col"><liferay-ui:message key="edison-workflow-public-status" /></th>
              </tr>
            </thead>
            <tbody id="my-workflow-tbody" class="workflow-list-tbody">
            </tbody>
          </table>
          
	      <div id="my-workflow-paging" class="text-center"></div>
      </div>
    </div>
  </div>
  <div class="leftwrap wrap">
    <div class="leftlistbox my-workflow">
      <div class="searchbox">
          <div class="input-group">
	        <input id="search-my-workflow-name-small" class="form-control" name="search-my-workflow-name" type="text" size="40" value=""
	          autocomplete="off" placeholder="<liferay-ui:message key='edison-workflow-workflow-name-filter'/>" /> 
	          
          	<div class="input-group-btn">
          		<button id="search-my-workflow-name-small-btn" class="btn btn-default" type="button"><i class="icon-search"></i></button>
          	</div>
          </div>
      </div>
      <div class="openslidearrow">
        <a class="wf-slide-toggle" href="#"><img src="${contextPath}/images/Workflow/slideopen_arrow.png" width="32"
          height="51"></a>
      </div>
      <div class="listboxtopsp">
        <div class="workflow-list-div" id="my-workflow-div"></div>
      </div>
    </div>
  </div>
  <div class="alert"></div>
  <div class="rightwrap">
    <div id="my-workflow-canvas" class="right apparea"></div>
  </div>
</div>

<div id="public-workflow" class="wf-container hidden">
  <div class="wftitlebox001">
    <h2 id="public-workflow-name" class="workflow-name-h2"></h2>
    <span> <input class="btn btn-default button03" id="wf-public-copy-button"
      value="<liferay-ui:message key='edison-workflow-import'/>" type="button">
    </span>
  </div>
  <div class="lefttabm">
    <ul>
      <li class="science-app"><liferay-ui:message key="edison-workflow-science-app" /></li>
      <li class="running-workflow"><liferay-ui:message key="edison-workflow-running-workflow" /></li>
      <li class="my-workflow"><liferay-ui:message key="edison-workflow-my-workflow" /></li>
      <li class="public-workflow"><liferay-ui:message key="edison-workflow-open-workflow" /></li>
    </ul>
  </div>
  <div class="wfconwrap wrap" style="display: none;">
    <div class="lefttablemenu">
      <div class="searchbox">
      	<div class="input-group">
	        <input id="search-public-workflow-name-large" class="form-control" name="search-public-workflow-name" type="text" size="40" value=""
	          autocomplete="off" placeholder="<liferay-ui:message key='edison-workflow-workflow-name-filter'/>" /> 
			<div class="input-group-btn">
				<button id="search-public-workflow-name-large-btn" class="btn btn-default" type="button"><i class="icon-search"></i></button>
			</div>
      	</div>
      </div>
      <div class="tabletopwf">
        <select id="public-workflow-line-per-page" name="public-workflow-line-per-page" class="form-control">
          <option value="10">10
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="20">20
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="30">30
            <liferay-ui:message key="edison-search-views" /></option>
          <option value="40">40
            <liferay-ui:message key="edison-search-views" /></option>
        </select>
      </div>
      <div class="slideclosearrow">
        <a class="wf-slide-toggle" href="#slide-close"><img
          src="${contextPath}/images/Workflow/slideclose_arrow.png" width="30" height="48"></a>
      </div>
      <div style="height: 37px;"></div>
      <div class="table-responsive panel edison-panel">
		  <div class="panel-heading clearfix">
		  </div>
          <table class="table1_list table table-bordered table-hover edison-table">
            <colgroup>
              <col width="20%">
              <col width="20%">
              <col width="15%">
              <col width="15%">
              <col width="15%">
              <col width="15%">
            </colgroup>
            <thead>
              <tr>
                <th scope="col"><liferay-ui:message key="edison-workflow-name" /></th>
                <th scope="col"><liferay-ui:message key="edison-simulation-execute-simulation-description" /></th>
                <th scope="col"><liferay-ui:message key="edison-virtuallab-owner" /></th>
                <th scope="col">Copied from</th>
                <th scope="col"><liferay-ui:message key="edison-workflow-create-date" /></th>
                <th scope="col"><liferay-ui:message key="edison-workflow-public-status" /></th>
              </tr>
            </thead>
            <tbody id="public-workflow-tbody" class="workflow-list-tbody">
            </tbody>
          </table>
	      <div id="public-workflow-paging" class="text-center"></div>
      </div>
    </div>
  </div>
  <div class="leftwrap wrap">
    <div class="leftlistbox public-workflow">
      <div class="searchbox">
      	<div class="input-group">
	        <input id="search-public-workflow-name-small" class="form-control" name="search-public-workflow-name" type="text" size="40" value=""
	          autocomplete="off" placeholder="<liferay-ui:message key='edison-workflow-workflow-name-filter'/>" /> 
      		<div class="input-group-btn">
      			<button id="search-public-workflow-name-small-btn" class="btn btn-default" type="button"><i class="icon-search"></i></button>
      		</div>
      	</div>
      </div>
      <div class="openslidearrow">
        <a class="wf-slide-toggle" href="#"><img src="${contextPath}/images/Workflow/slideopen_arrow.png" width="32"
          height="51"></a>
      </div>
      <div class="listboxtopsp">
        <div class="workflow-list-div" id="public-workflow-div"></div>
      </div>
    </div>
  </div>
  <div class="alert"></div>
  <div class="rightwrap">
    <div id="public-workflow-canvas" class="right apparea"></div>
  </div>
</div>
<div id="workflow-instance-dialog" style="display: none; background-color: white; padding: 0px;" class="newWindow">
  <div class="newWheader" id="workflow-instance-dialog-title">
    <div class="newWtitlebox">
      <img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
      <div class="newWtitle">Workflow Instance Name</div>
    </div>
    <div class="newWclose" style="cursor: pointer;">
      <img id="workflow-instance-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png"
        width="21" height="21">
    </div>
  </div>
  <div id="workflow-instance-dialog-cont" style="padding: 30px;" class="newWcont01">
    <p>
      <liferay-ui:message key="edison-workflow-running-instance-name-message" />
      .
    </p>
    <input id="workflow-instance-name-input" type="text" name="workflow-instance-name" style="width: 85%;" /> 
    <input id="workflow-instance-run" class="button03" style="float: right;" type="button" name="save" value="Run" />
  </div>
</div>
<div id="<portlet:namespace/>error-dialog" style="display: none; background-color: white; padding: 0px;"
  class="newWindow">
  <div class="newWheader" id="<portlet:namespace/>error-dialog-title" style="cursor: move;">
    <div class="newWtitlebox">
      <img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
      <div id="<portlet:namespace/>error-dialog-title-text" class="newWtitle">Log</div>
    </div>
    <div class="newWclose" style="cursor: pointer;">
      <img id="<portlet:namespace/>error-dialog-close-btn"
        name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn"
        src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
    </div>
  </div>
  <div id="<portlet:namespace/>error-dialog-content" style="padding: 30px;" class="newWcont01"></div>
</div>
<div id="<portlet:namespace/>wf-conf-dialog" title="<liferay-ui:message key='edison-workflow-conf-title' />"
  class="newWindow" style="display: none; background-color: white; padding: 0px;">
  <div class="newWheader">
    <div class="newWtitlebox">
      <img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
      <div class="newWtitle">
        <liferay-ui:message key='edison-workflow-conf-title' />
      </div>
    </div>
    <div class="newWclose" style="cursor: pointer;">
      <img id="<portlet:namespace/>wf-conf-dialog-close-btn" name="wf-conf-dialog-close-btn"
        src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="25" height="25"
        style="cursor: pointer; float: right;" />
    </div>
  </div>
  <div style="padding: 30px;" class="newWcont01">
    <aui:form action="<%=updateWorkflowConfUrl%>" method="post" id="updateWorkflowConfForm"
      name="updateWorkflowConfForm" target="updateWorkflowConfFrame">
      <aui:input id="workflowId" name="workflowId" value="${mode}" type="hidden" />
      <aui:input id="tutorialFileEntryId" name="tutorialFileEntryId" type="hidden" />
      <div class="table-responsive panel edison-panel" style="width: 85%; padding: 15px 0px 15px 0px; margin: 0 auto;">
      	<div class="panel-heading clearfix">
      	</div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1_list table table-bordered table-hover edison-table">
          <colgroup>
            <col width="25%" />
            <col width="75%" />
          </colgroup>
          <tbody>
            <tr>
              <td><liferay-ui:message key='edison-workflow-conf-title-input' /></td>
              <td>
                <div>
                  <liferay-ui:input-localized id="title" name="title" xml="" type="input" spellcheck="false"
                    style="width: 95%;">
                  </liferay-ui:input-localized>
                </div>
              </td>
            </tr>
            <tr>
              <td><liferay-ui:message key='edison-workflow-conf-lang-input' /></td>
              <td>&nbsp;&nbsp; <label style="display: inline;"> <input type="checkbox"
                  id="<portlet:namespace/>serviceLanguageAll" name="<portlet:namespace/>select_languageId" value="all">
              </label> <liferay-ui:message key="full" /> <c:forEach var="lang" items="${ableLang}">
                  <label style="display: inline;"> <input type="checkbox"
                    id="<portlet:namespace/>select_languageId" name="<portlet:namespace/>select_languageId"
                    value="${lang}" /> <img width="17px" height="12px"
                    src="${contextPath}/images/Workflow/toplink_${lang}.gif" /> <liferay-ui:message
                      key='edison-board-radiobutton-${lang}' />
                  </label>
                </c:forEach>
              </td>
            </tr>
            <tr>
              <td><liferay-ui:message key='edison-workflow-conf-public-input' /></td>
              <td>&nbsp;&nbsp; <label style="display: inline;"> <input type="checkbox"
                  id="<portlet:namespace/>isPublic" name="<portlet:namespace/>isPublic" value="1">
              </label>
              </td>
            </tr>
            <tr>
              <td><liferay-ui:message key='edison-workflow-conf-description-input' /></td>
              <td>
                <div>
                  <liferay-ui:input-localized name="description" id="description" xml="" type="textarea" rows="3"
                    spellcheck="false" style="width: 95%; resize:none;">
                  </liferay-ui:input-localized>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        </div>
        </aui:form>
        <aui:form name="workflowTutorialFileForm" method="POST" action="<%=uploadWorkflowTutorialURL%>"
          enctype="multipart/form-data" onSubmit="return false;">
        <div class="table-responsive panel edison-panel" style="width: 85%; margin: 0 auto;">
        	<div class="panel-heading clearfix">
        	</div>
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1_list table table-bordered table-hover edison-table">
            <colgroup>
              <col width="25%">
              <col width="*">
              <col width="20%">
              <col width="25%">
            </colgroup>
            <tr>
              <td>튜로리얼<br/>업로드</td>
              <td>
                <aui:input id="workflowIdForFileEntry" name="workflowIdForFileEntry" value="" type="hidden" />
                <aui:input name="workflowTutorial" type="file" label="">
                  <aui:validator name="required" />
                  <aui:validator name="acceptFiles">'pdf'</aui:validator>
                  <aui:validator name="custom" errorMessage="fileErrorMsg">
                      function (val, fieldNode, ruleValue) {
                        var fileObj = document.getElementById("<portlet:namespace />workflowTutorial");
                        var returnStatus = false;
                        if(typeof fileObj.files[0] != "undefined"){
                          var fileSize = Math.ceil(fileObj.files[0].size/1024);
                          if(fileSize<=200*1024){
                            returnStatus = true;
                          }
                        }else{
                          returnStatus = true;
                        }

                        return returnStatus;
                      }
                    </aui:validator>
                </aui:input>
              </td>
              <td class="TC">
                <input class="addIp button02_1" value="file save" type="submit" id="<portlet:namespace/>fileSave" />
              </td>
              <td>
                <span id="fileUpladMsg"></span>
              </td>
            </tr>
          </table>
      </div>
        </aui:form>
      <div style="float: right; padding-right: 60px;">
        <input id="<portlet:namespace />updateWorkflowConf" name="<portlet:namespace />updateWorkflowConf"
          onclick="updateWorkflowConf();" class="button03" type="button"
          value="<liferay-ui:message key='edison-workflow-conf-save-button' />" />
      </div>
  </div>
</div>
<div id="<portlet:namespace />result-down-dialog" style="display: none; background-color: white; padding: 0px;"
  class="newWindow"></div>

<!--  Progress Bar    -->
<div id="progress_bar_wrap2" style="display: none;">
    <div id="progress_bar_line">
        <div id="progress_bar2"><span id="percent"></span></div>
    </div>
</div>
<div id="<portlet:namespace/>analyzer-dialog" style="display: none; background-color: white; padding: 0px;"
  class="newWindow">
  <div class="newWheader" id="<portlet:namespace/>analyzer-dialog-title" style="cursor: move;">
    <div class="newWtitlebox">
      <img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
      <div id="<portlet:namespace/>analyzer-dialog-title-text" class="newWtitle">Analyzer</div>
    </div>
    <div class="newWclose" style="cursor: pointer;">
      <img id="<portlet:namespace/>analyzer-dialog-close-btn"
        name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn"
        src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
    </div>
  </div>
  <div id="<portlet:namespace/>analyzer-dialog-content" class="newWcont01"></div>
</div>

<div id="<portlet:namespace/>editor-dialog" style="display: none; background-color: white; padding: 0px;"
  class="newWindow">
  <input type="hidden" id="<portlet:namespace/>editor-portlet-id" value="" />
  <input type="hidden" id="<portlet:namespace/>editor-caller-window-id" value="" />
  <div class="newWheader" id="<portlet:namespace/>editor-dialog-title" style="cursor: move;">
    <div class="newWtitlebox">
      <img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
      <div id="<portlet:namespace/>editor-dialog-title-text" class="newWtitle">Editor</div>
    </div>
    <div class="newWclose" style="cursor: pointer;">
      <img id="<portlet:namespace/>editor-dialog-close-btn"
        name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn"
        src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
    </div>
  </div>
  <div id="<portlet:namespace/>editor-dialog-content" class="newWcont01"></div>
  <div style="float: right; padding-right: 60px; padding-bottom: 20px;">
    <input id="<portlet:namespace/>saveEditorData" name="<portlet:namespace />saveEditorData"
      onclick="<portlet:namespace/>fireEditorSaveEvent();" class="button03" type="button"
      value="<liferay-ui:message key='edison-workflow-conf-save-button'/>"/>
  </div>
</div>

<iframe id="updateWorkflowConfFrame" name="updateWorkflowConfFrame" style="display: none;"></iframe>
<script type="text/javascript" src="${contextPath}/js/lib/jquery.form.min.js"></script>
<script>
  var AVAILABLE_LANGUAGE = [];
  <c:forEach var="lang" items="${ableLang}">
  	AVAILABLE_LANGUAGE.push("${lang}");
  </c:forEach>
  var DEFAULT_LANGUAGE = '${defaultLang}';

  var isNotLoaded = true;
  var wfPortletGlobalData = wfPortletGlobalData ? wfPortletGlobalData : {wfElements : {}};
  function getEditorPortletUrl(portletId){
    var url = "<%=getEditorPortletUrl%>";
    return synchronousAjaxHelper.post(url, {"<portlet:namespace/>portletId" : portletId})["portletURL"];
  }
  function getAuthToken(portletId){
    var url = "<%=getAuthTokenUrl%>";
    return synchronousAjaxHelper.post(url, {"<portlet:namespace/>portletId" : portletId});
  }
  function getSpecificSiteGroupId(){
    var url = "<%=getSpecificSiteGroupIdUrl%>";
    var result = synchronousAjaxHelper.post(url, {});
    return result["groupId"];
  }
  function getIcebreakerAccessToken(){
    var url = "<%=getIcebreakerAccessTokenUrl%>";
    var result = synchronousAjaxHelper.post(url, {});
    return result;
  }
  function getCompanyGroupId(){
    return "${companyGroupId}";
  }
  function getSiteGroupId(){
    return "${groupId}";
  }

  function getWorkflowInstances(searchKeyword, p_curPage, linePerPage, status){
    var url = "<%=getWorkflowInstancesUrl%>" ;
    var params = {};
    if(searchKeyword || searchKeyword === 0){
      params["<portlet:namespace/>title"] = searchKeyword;
    }
    if(p_curPage){
      params["<portlet:namespace/>p_curPage"] = p_curPage;
    }
    if(linePerPage){
      params["<portlet:namespace/>linePerPage"] = linePerPage;
    }
    if(status){
      params["<portlet:namespace/>status"] = status;
    }
    params["<portlet:namespace/>pagingClassName"] = "running-workflow-paging";
    var result = synchronousAjaxHelper.post(url, params);
    return result["workflowMap"];
  }

  function getSimulationLookUpPath(sciApp){
    var url = "<%=getSimulationResultDirectoryURL%>";
    var workflowUserId
      = sciApp["workflowStatus"]["userId"] ?  sciApp["workflowStatus"]["userId"] : "";
    var jsonData = {
        "<portlet:namespace/>groupId" : sciApp["groupId"],
        "<portlet:namespace/>simulationUuid" : sciApp["workflowStatus"]["jobs"][0]["ibSimUuid"],
        "<portlet:namespace/>jobUuid" : sciApp["workflowStatus"]["jobs"][0]["ibUuid"],
        "<portlet:namespace/>userId" : workflowUserId
    }
    return synchronousAjaxHelper.post(url, jsonData)["lookUpPath"];
  }
  
  function popScriptViewer(appData, jsPlumbWindowId){
    var scriptContent = "<pre class='script-viewer'>" + appData.inputports.script["input-value"] + "</pre>";
    var $content = $("#<portlet:namespace/>error-dialog-content");
    $("#<portlet:namespace/>error-dialog-title-text").text("Script Viewer");
    $content.html(scriptContent);
    $("#<portlet:namespace/>error-dialog").dialog("open");
  }

  function popAnalyzerWindow(analyzer, port, jsPlumbWindowId, sciApp){
    var portletId = analyzer["exeFileName"];
    var callbackFunc = function(){
      var ospPath = port.outputData();
      var inputData = new OSP.InputData();
      var simUuid = sciApp.workflowStatus.jobs[0].ibSimUuid;
      var jobUuid = sciApp.workflowStatus.jobs[0].ibUuid;
      var userScreenName = sciApp.workflowStatus.userId;
      var parentPaths = [userScreenName, "jobs", simUuid, jobUuid+".job", ospPath.parent()];
      inputData.type(OSP.Enumeration.PathType.FILE);
      if(ospPath.relative()){
        console.log("parentPaths.join", parentPaths.join("/"));
        inputData.parent(parentPaths.join("/"));
      }else{
        inputData.parent(ospPath.parent());
      }
      inputData.name(ospPath.name());
      inputData.relative(ospPath.relative());
      Liferay.fire(
        OSP.Event.OSP_HANDSHAKE,
        {
          targetPortlet: portletId,
          height: "500",
          portletId: "<%=themeDisplay.getPortletDisplay().getId()%>",
          data: inputData
        });
    };
    showAnalyzerWindow(portletId, callbackFunc);
  }
  
  function showAnalyzerWindow(portletId, callbackFunc){
    AUI().use("liferay-portlet-url", function(A) {
      var authToken = getAuthToken(portletId);
      var token = authToken["authToken"];
      var renderURL = Liferay.PortletURL.createRenderURL();
      renderURL.setPortletId(portletId);
      renderURL.setPortletMode("view");
      renderURL.setWindowState("exclusive");
      $("#<portlet:namespace/>analyzer-dialog-content").empty();
      $("#<portlet:namespace/>analyzer-dialog-content").load(
          renderURL.toString() + "&p_p_auth=" + token , {}, callbackFunc);
      $("#<portlet:namespace/>analyzer-dialog").dialog("open");
    });
  }

  function savePortletSessionValue(endPointId, value, renderURL){
    $.ajax({
    	url: "<%=savePortletSessionValueURL%>",
    	type:"post",
    	dataType: "text",
    	data:{
    		"<portlet:namespace/>dialogId" : endPointId,
    		"<portlet:namespace/>value": value
    	},
    	success:function(data){
    	  openWindow(renderURL, endPointId);
    	}
    });
  }
  
  $("#<portlet:namespace/>analyzer-dialog").dialog({
    autoOpen : false,
    width : '65%',
    modal : true,
    resizable : false,
    show : {
      effect : 'fade',
      speed : 800
    },
    hide : {
      effect : 'fade',
      speed : 800
    },
    draggable : true,
    open : function(event, ui) {
      $(this).removeClass("ui-widget-content");
      $(this).parent().removeClass("ui-widget-content");
      $(this).parent().css('overflow', 'visible');

      $("body").css('overflow', 'hidden');
      $("#<portlet:namespace/>analyzer-dialog-close-btn").bind('click', function() {
        $("#<portlet:namespace/>analyzer-dialog").dialog("close");
      });

      $('.ui-widget-overlay').bind('click', function() {
        $("#<portlet:namespace/>analyzer-dialog").dialog("close");
      })
    },
    close : function() {
      $("#<portlet:namespace/>analyzer-dialog-content").empty();
      $("body").css('overflow', '');
    }
  }).draggable({
    handle : "#<portlet:namespace/>analyzer-dialog-title"
  }).dialog("widget").find(".ui-dialog-titlebar").remove();

  $("#<portlet:namespace/>editor-dialog").dialog({
    autoOpen : false,
    width : '65%',
    modal : true,
    resizable : false,
    show : {
      effect : 'fade',
      speed : 800
    },
    hide : {
      effect : 'fade',
      speed : 800
    },
    draggable : true,
    open : function(event, ui) {
      $(this).removeClass("ui-widget-content");
      $(this).parent().removeClass("ui-widget-content");
      $(this).parent().css('overflow', 'visible');

      $("body").css('overflow', 'hidden');
      $("#<portlet:namespace/>editor-dialog-close-btn").bind('click', function() {
        $("#<portlet:namespace/>editor-dialog").dialog("close");
      });

      $('.ui-widget-overlay').bind('click', function() {
        $("#<portlet:namespace/>editor-dialog").dialog("close");
      })
    },
    close : function() {
      $("#<portlet:namespace/>editor-dialog-content").empty();
      $("body").css('overflow', '');
    }
  }).draggable({
    handle : "#<portlet:namespace/>editor-dialog-title"
  }).dialog("widget").find(".ui-dialog-titlebar").remove();

  function <portlet:namespace/>fireEditorSaveEvent(){
    AUI().use( function(A) {
      var workflowInfo = JSON.parse($("#<portlet:namespace/>editor-caller-window-id").val());
      var srcData = {
          targetPortlet: $("#<portlet:namespace/>editor-portlet-id").val(),
          portletId: "<%=themeDisplay.getPortletDisplay().getId() %>",
          workflowInfo: workflowInfo
        };
      Liferay.fire(OSP.Event.OSP_REQUEST_DATA, srcData);
    });
    $("#<portlet:namespace/>editor-dialog").dialog("close");
  }

  Liferay.on(OSP.Event.OSP_RESPONSE_DATA, function(eventData){
    if(eventData.targetPortlet === "<%=themeDisplay.getPortletDisplay().getId()%>"){
      var workflowInfo = JSON.parse($("#<portlet:namespace/>editor-caller-window-id").val());
      var editorType = workflowInfo["editorType"];
      var editorData;
      if(editorType == "Inputdeck"){
        editorData = JSON.stringify(eventData["data"]["data"]);
      }else if(editorType == "File"){
        editorData = JSON.stringify(eventData["data"]);
      }else if(editorType == "Text"){
        editorData = eventData["data"];
      }
      var fileContent = "";
      
      if(wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]]) {
        if(!wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]){
          wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]] = {};
        }
        wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]["input-value"] = editorData;
        wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]["editorType"] = workflowInfo["editorType"];
      }else{
        var portJson = {};
        portJson[workflowInfo["portName"]] = {};
        portJson[workflowInfo["portName"]]["input-value"] = editorData;
        portJson[workflowInfo["portName"]]["editorType"] = workflowInfo["editorType"];
        wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]] = portJson;
      }
      
      if(editorType == "Inputdeck") {
        var editorDataStructure = eventData["data"]["data"];
        var inputs = editorDataStructure.activeParameterFormattedInputs();
        fileContent = inputs[0].join("");
      }else if(editorType == "Text"){
        fileContent = editorData;
      }else if(editorType == "File"){
        var portInfo = wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]];
        portInfo["fileName"] = eventData["data"].name();
        portInfo["parentPath"] = eventData["data"].parent();
        portInfo["pathType"] = eventData["data"].type();
      }
      wfPortletGlobalData["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]["file-content"] = fileContent;
    }
  });
  
  function popScriptEditorWindow(appData, wfWindowId){
    var workflowInfo = {
        jsPlumbWindowId: wfWindowId,
        portName: "converter-script",
        editorType: "Text"
    };
    var portletId = "${textEditor.exeFileName}";
    var srcData = new OSP.InputData();
    srcData.type(OSP.Enumeration.PathType.CONTEXT);
    if(wfPortletGlobalData["wfElements"][wfWindowId]
        && wfPortletGlobalData["wfElements"][wfWindowId]["converter-script"]){
      var portData = wfPortletGlobalData["wfElements"][wfWindowId]["converter-script"]["input-value"];
      srcData.context(Liferay.Util.escapeHTML(portData));
    }else{
      srcData.context("");
    }
    
    showEditorWindow(portletId, workflowInfo, JSON.stringify(srcData));
  }
  
  function showEditorWindow(portletId, workflowInfo, inputData, callbackFunc){
    AUI().use("liferay-portlet-url", function(A) {
      var authToken = getAuthToken(portletId);
      var plid = authToken["plid"];
      var token = authToken["authToken"];
      var renderURL = Liferay.PortletURL.createRenderURL();
      var editorNamespace = "_" + portletId + "_";
      var postData = {};
      renderURL.setPortletId(portletId);
      renderURL.setPortletMode("view");
      renderURL.setWindowState("exclusive");
      renderURL.setPlid(plid);
      
      postData[editorNamespace + "inputData"] = inputData;
      postData[editorNamespace + "eventEnable"] = false;
      postData[editorNamespace + "connector"] = "<%=themeDisplay.getPortletDisplay().getId()%>";
      
      $("#<portlet:namespace/>editor-dialog-content").empty();
      $("#<portlet:namespace/>editor-caller-window-id").val(JSON.stringify(workflowInfo));
      $("#<portlet:namespace/>editor-portlet-id").val(portletId);
      $("#<portlet:namespace/>editor-dialog-content").load(
          renderURL.toString() + "&p_p_auth=" + token, postData/*, callbackFunc */);
      $("#<portlet:namespace/>editor-dialog").dialog("open");
    });
    
  }

  function popEditorWindow(editor, port, jsPlumbWindowId){
    var portData = "";
    var portName = port.name();
    var editorType = editor["editorType"];
    var portletId = editor["exeFileName"];
    var workflowInfo = {
      jsPlumbWindowId: jsPlumbWindowId,
      portName: portName,
      editorType: editorType
    };
    var callbackFunc = function(){};
    var inputData = "";
    
    if(editorType == "Inputdeck") {
      if(wfPortletGlobalData["wfElements"][jsPlumbWindowId]
        && wfPortletGlobalData["wfElements"][jsPlumbWindowId][portName]
        && wfPortletGlobalData["wfElements"][jsPlumbWindowId][portName]["editorType"] === "Inputdeck"){
  	    portData = wfPortletGlobalData["wfElements"][jsPlumbWindowId][portName]["input-value"];
  	  }else{
  	    portData = editor["structure"];
  	  }
      var srcData = new OSP.InputData();
      srcData.type(OSP.Enumeration.PathType.STRUCTURED_DATA);
      srcData.context(JSON.parse(portData));
	    inputData = JSON.stringify(srcData);
    }else if(editorType == "Text"){
      if(wfPortletGlobalData["wfElements"][jsPlumbWindowId]
        && wfPortletGlobalData["wfElements"][jsPlumbWindowId][portName]){
        portData = wfPortletGlobalData["wfElements"][jsPlumbWindowId][portName]["file-content"];
      }else{
        portData = "";
      }
      var srcData = new OSP.InputData();
      srcData.type(OSP.Enumeration.PathType.CONTEXT);
      srcData.context(Liferay.Util.escapeHTML(portData));
      inputData = JSON.stringify(srcData);
    }else if(editorType == "File"){
      var srcData = new OSP.InputData();
      srcData.setPath('', '${username}', '', OSP.Constants.FOLDER, true);
      inputData = JSON.stringify(srcData);
    }
    showEditorWindow(portletId, workflowInfo, inputData, callbackFunc);
  }

  function openWindow(renderURL, endPointId) {
    var dialogId = endPointId;
    renderURL.setName("Popup");
    renderURL.setWindowState("pop_up");
    renderURL.setParameter("dialogId", endPointId);
    renderURL.setParameter("workflowType", "workflow");

    var url = renderURL.toString();
    Liferay.Util.openWindow({
      dialog : {
        cache : false,
        destroyOnClose : true,
        centered : true,
        modal : true,
        resizable : false,
        width : renderURL.params.width,
        height : renderURL.params.height
      },
      id : dialogId,
      title : renderURL.params.portletTitle,
      uri : url + "&p_p_auth=" + renderURL.params.token
    });
  }

  function serviceLanguageCheckBox() {
    /*서비스 언어 전체선택/해제 */
    $("#<portlet:namespace/>serviceLanguageAll").click(function() {
      if ($("#<portlet:namespace/>serviceLanguageAll").prop("checked")) {
        $("input[id=<portlet:namespace/>select_languageId]").prop("checked", true);
      } else {
        $("input[id=<portlet:namespace/>select_languageId]").prop("checked", false);
      }
    });

    var ableLangLength = '<c:out value="${fn:length(ableLang)}"/>'; //available Language Length
    $("input[id=<portlet:namespace/>select_languageId]").click(function() {
      var checkLength = $("input[id=<portlet:namespace/>select_languageId]:checked").length;

      if (ableLangLength == checkLength) {
        $("#<portlet:namespace/>serviceLanguageAll").prop("checked", true);
      } else {
        $("#<portlet:namespace/>serviceLanguageAll").prop("checked", false);
      }
    });
  }

  Liferay.provide(window, 'fetchResult', function(taskId, portName, value) {
    var content = {
      taskId : taskId,
      portName : portName,
      value : value
    };

    var payload = OSPEvent.createEvent("stringPort", OSPEvent.Constants.TYPE_BROADCAST,
        '<portlet:namespace/>', '', content);

    Liferay.fire('ipc-event-content-broadcat', payload);
  }, [ 'aui-base', 'liferay-util-window', 'aui-dialog-iframe-deprecated', 'liferay-util-window' ]);

  Liferay.provide(window, 'closePopup', function(popupId) {
    var popupDialog = Liferay.Util.Window.getById(popupId);
    popupDialog.destroy();
  }, [ 'aui-base', 'aui-dialog', 'aui-dialog-iframe', 'liferay-util-window' ]);

  Liferay.on(OSPEvent.Constants.IPC_EVENT_CONTENT_BROADCAST, function(event) {
    var json = event.getEventData();
    var jsonData = JSON.stringify(json);

    if (wfPortletGlobalData["wfElements"][json["taskId"]]) {
      wfPortletGlobalData["wfElements"][json["taskId"]][json["portName"]] = json["value"];
    } else {
      var portJson = {};
      portJson[json["portName"]] = json["value"];
      wfPortletGlobalData["wfElements"][json["taskId"]] = portJson;
    }
  });

  $("#<portlet:namespace/>error-dialog").dialog({
    autoOpen : false,
    width : '855',
    height : '580',
    modal : true,
    resizable : false,
    show : {
      effect : 'fade',
      speed : 800
    },
    hide : {
      effect : 'fade',
      speed : 800
    },
    draggable : true,
    open : function(event, ui) {
      $(this).removeClass("ui-widget-content");
      $(this).parent().removeClass("ui-widget-content");
      $(this).parent().css('overflow', 'visible');

      $("body").css('overflow', 'hidden');
      $("#<portlet:namespace/>error-dialog-close-btn").bind('click', function() {
        $("#<portlet:namespace/>error-dialog").dialog("close");
      });

      $('.ui-widget-overlay').bind('click', function() {
        $("#<portlet:namespace/>error-dialog").dialog("close");
      })
    },
    close : function() {
      $("#<portlet:namespace/>error-dialog-content").empty();
      $("body").css('overflow', '');
    }
  }).draggable({
    handle : "#<portlet:namespace/>error-dialog-title"
  }).dialog("widget").find(".ui-dialog-titlebar").remove();

  $("#<portlet:namespace/>wf-conf-dialog").dialog({
    autoOpen : false,
    width : '855',
    height : '580',
    modal : true,
    resizable : false,
    show : {
      effect : 'fade',
      speed : 800
    },
    hide : {
      effect : 'fade',
      speed : 800
    },
    draggable : true,
    open : function(event, ui) {
      $(this).removeClass("ui-widget-content");
      $(this).parent().removeClass("ui-widget-content");
      $(this).parent().css('overflow', 'visible');

      $("body").css('overflow', 'hidden');
      $("#<portlet:namespace/>wf-conf-dialog-close-btn").bind('click', function() {
        $("#<portlet:namespace/>wf-conf-dialog").dialog("close");
      });

      $('.ui-widget-overlay').bind('click', function() {
        $("#<portlet:namespace/>wf-conf-dialog").dialog("close");
      })
    },
    close : function() {
      $("#<portlet:namespace/>wf-conf-dialog-content").empty();
      $("body").css('overflow', '');
    }
  }).draggable({
    handle : "#<portlet:namespace/>wf-conf-dialog-title"
  }).dialog("widget").find(".ui-dialog-titlebar").remove();

  function updateWorkflowConf(){
    $("#worfklow-definition-name").val($("#<portlet:namespace/>title_"+DEFAULT_LANGUAGE+"").val());
    $("#<portlet:namespace/>wf-conf-dialog").dialog("close");
    $("#<portlet:namespace/>updateWorkflowConfForm").submit();
  }

  function refreshListButtonClick(){
    $("#wf-list-refresh-button").click();
  }

  function openWorkflowConfigPopup(workflowData) {
    $("#<portlet:namespace/>workflowTutorialFileForm")[0].reset();
    $("#<portlet:namespace/>updateWorkflowConfForm")[0].reset();
    $("#<portlet:namespace/>workflowId").val(workflowData["workflowId"]);
    $("#<portlet:namespace/>workflowIdForFileEntry").val(workflowData["workflowId"]);
    setWorkflowTutorialFile(workflowData["tutorialFileEntryId"]);
    $("#<portlet:namespace/>titleContentBox div:last").click();
    $("#<portlet:namespace/>descriptionContentBox div:last").click();
    if(workflowData["titleMap"] && workflowData["titleMap"].indexOf("<?xml") > -1){
      var xmlTitleDoc = $.parseXML(workflowData["titleMap"]);
      var xmlDescriptionDoc = $.parseXML(workflowData["descriptionMap"]);
      var $xmlTitle = $(xmlTitleDoc);
      var $xmlDescription = $(xmlDescriptionDoc);
      $.each(AVAILABLE_LANGUAGE, function(i){
        var lang = this;
        var title = $xmlTitle.find("Title[language-id='"+lang+"']").text();
        var description = $xmlDescription.find("Description[language-id='"+lang+"']").text();
        $("#<portlet:namespace/>title_"+lang+"").val(title);
        $("#<portlet:namespace/>description_"+lang+"").val(description);
      });
    }else{
      $("#<portlet:namespace/>titleContentBox div:last").click();
      $("#<portlet:namespace/>descriptionContentBox div:last").click();
      $("#<portlet:namespace/>title_"+DEFAULT_LANGUAGE+"").val(workflowData["title"]);
      $("#<portlet:namespace/>description_"+DEFAULT_LANGUAGE+"").val(workflowData["description"]);
    }
    $("#<portlet:namespace/>title").attr("placeholder",$(xmlTitleDoc).find("[language-id ='"+DEFAULT_LANGUAGE+"']").text()).val($(xmlTitleDoc).find("[language-id ='"+DEFAULT_LANGUAGE+"']").text());
    $("#<portlet:namespace/>description").attr("placeholder",$(xmlDescriptionDoc).find("[language-id ='"+DEFAULT_LANGUAGE+"']").text()).val($(xmlDescriptionDoc).find("[language-id ='"+DEFAULT_LANGUAGE+"']").text());
    $("#<portlet:namespace/>titleContentBox").find("li[data-value='"+DEFAULT_LANGUAGE+"']").trigger("click");
    $("#<portlet:namespace/>descriptionContentBox").find("li[data-value='"+DEFAULT_LANGUAGE+"']").trigger("click");
    var isPublic = workflowData["isPublic"];
    $("input:checkbox[id='<portlet:namespace/>isPublic']").prop("checked", isPublic);
    $targetLanguage = workflowData["targetLanguage"];
    if($targetLanguage != ""){
		$checkedVal = $targetLanguage.split(",");
		if($checked =! ""){
			$("input[name=<portlet:namespace/>select_languageId]").each(function() {
				$box = $(this).val();
				for(var i=0; i< $checkedVal.length; i++){
					if($box == $checkedVal[i]){
						$(this).attr("checked", true);
					}
				}
			});
		}
	}
    $("#<portlet:namespace/>wf-conf-dialog").dialog("open");
  }

  function openWorkflowSimulationLogPopup(data, logType){
    var $content = $("#<portlet:namespace/>error-dialog-content");
    $("#<portlet:namespace/>error-dialog-title-text").text(logType);
    data = data.replace(/'/g, "&apos;").replace(/"/g, "&quot;");
    $content.html(data);
    $("#<portlet:namespace/>error-dialog").dialog("open");
  }

  function openSolverDeatilPopup(scienceAppId) {
    var groupId = getSiteGroupId();
    var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
    var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
        params += "&" + thisPortletNamespace + "groupId=" + groupId;
    window.open("<%=scienceAppDetailUrl%>" + params);
  }

  function addScienceAppData(endPointId, arrData) {
    var inputJson = $.parseJSON(JSON.stringify(arrData[0]));

    if (typeof endPointId === 'string') {
      var portName = endPointId.substr(endPointId.lastIndexOf("|") + 1);
      var elementId = endPointId.substr(0, (endPointId.lastIndexOf("|")));
      if (wfPortletGlobalData["wfElements"][elementId]) {
        wfPortletGlobalData["wfElements"][elementId][portName] = inputJson;
      } else {
        var portJson = {};
        portJson[portName] = inputJson;
        wfPortletGlobalData["wfElements"][elementId] = portJson;
      }
    }
  }
  function workflowAppResultDownload(jobUuid, groupId){
  	var URL = "<%=resultDownLoadURL%>&<portlet:namespace/>jobUuid="+jobUuid+"&<portlet:namespace/>groupId="+groupId;
  	$resultDialog = $("#<portlet:namespace/>result-down-dialog").dialog({
  		autoOpen: true,
  		width: '500',
  		height: '580',
  		modal: true,
  		resizable: true,
  		show: {effect:'fade', speed: 800},
  		hide: {effect:'fade', speed: 800},
  		open: function(event, ui) {
  	    	$(this).removeClass("ui-widget-content");
  	    	$(this).parent().removeClass("ui-widget-content");
  	    	$(this).parent().css('overflow','visible');
  	    	$(this).load(URL);

  	    	$("body").css('overflow','hidden')

  	    	$("#<portlet:namespace/>result-down-dialog-close-btn").bind('click',function(){
  	    		$("#<portlet:namespace/>result-down-dialog").dialog("close");
  	    	});

  	    	$('.ui-widget-overlay').bind('click',function(){
  	    		$("#<portlet:namespace/>result-down-dialog").dialog("close");
  			})
  	    },
  	    close: function() {
  	    	$("body").css('overflow','');
  	    }
  	}).draggable({
  			handle: "#<portlet:namespace/>result-dialog-title"
  	}).dialog("widget").find(".ui-dialog-titlebar").remove();
  }

  AUI().ready(function () {
    //파일 업로드 시  프로그래스바 설정
    $("#progress_bar_wrap2").dialog({
      resizable: false,
      height: 50,
      width: 700,
      modal: true,
      draggable: false,
      autoOpen: false
    });

    //프로그래스바 탑 툴바제거
    $("#progress_bar_wrap2").siblings('div.ui-dialog-titlebar').remove();
    var bar = $('#progress_bar2');
    var percent = $('#percent');
    var options = {
      timeout: 3000
    };

    $('#<portlet:namespace/>workflowTutorialFileForm').ajaxForm({
      beforeSubmit: function (arr, $form, options) {
        if ($("#<portlet:namespace/>workflowTutorial").hasClass("success-field")) {
          return true;
        } else {
          return false;
        }
      },
      beforeSend: function () {
        if (!$("#<portlet:namespace/>workflowTutorial").hasClass("error-field")) {
          $("#progress_bar_wrap2").dialog("open");
          var percentVal = '0%';
          bar.width(percentVal);
          percent.html(percentVal);
          return true;
        } else {
          return false;
        }
      },
      uploadProgress: function (event, position, total, percentComplete) {
        var percentVal = percentComplete + '%';
        bar.width(percentVal)
        percent.html(percentVal);
      },
      error: function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.responseText !== '') {
          alert(textStatus + ": " + jqXHR.responseText);
        } else {
          alert(textStatus + ": " + errorThrown);
        }
        return false;
      },
      success: function (msg) {
        var percentVal = '100%';
        bar.width(percentVal)
        percent.html(percentVal);
      },
      complete: function (xhr) {
        $("#progress_bar_wrap2").dialog("close");
        var out = $.parseJSON(xhr.responseText);
        var fileName = out["fileName"];
        setWorkflowTutorialFile(out["fileEntryId"]);
        if(console){
	        console.log(out);
        }
      }
    });
  });

  function getWorkflowTutorialFileDownloadUrl(fileEntryId){
   	return "<%=downloadWorkflowTutorialURL%>&<portlet:namespace/>fileEntryId=" + fileEntryId;
  }

  function setWorkflowTutorialFile(fileEntryId){
    $("#fileUpladMsg").empty();
    if(fileEntryId){
      var tutorialDownloadUrl = getWorkflowTutorialFileDownloadUrl(fileEntryId);
      $("#fileUpladMsg").append($("<a>", {
        "text" : "Tutorial File",
        "href" : tutorialDownloadUrl,
        "style": "text-decoration: underline;"
      }));
      $("#<portlet:namespace/>tutorialFileEntryId").val(fileEntryId);
    }else{
      $("#<portlet:namespace/>tutorialFileEntryId").val("");
    }
  }

  serviceLanguageCheckBox();
</script>
