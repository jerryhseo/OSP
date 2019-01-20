<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="getIcebreakerAccessTokenUrl" escapeXml="false" id="getIcebreakerAccessToken"
  copyCurrentRenderParameters="false" />
<liferay-portlet:renderURL var="designerUrl" portletName="workflowdesigner_WAR_edisonworkflow2016portlet"
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet"
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="copyParentNodeFilesURL" id="copyParentNodeFiles" copyCurrentRenderParameters="false"/>

<link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">
<link rel="stylesheet" href="${contextPath}/css/toastr.min.css">

<link rel="stylesheet" href="${contextPath}/css/jquery-confirm/jquery-confirm.min.css">
<link rel="stylesheet" href="${contextPath}/css/jsplumb/jsplumbtoolkit-defaults.css">
<link rel="stylesheet" href="${contextPath}/css/simulation-workbench.css">
<link rel="stylesheet" href="${contextPath}/css/workflow-executor.css">
<link rel="stylesheet" href="${contextPath}/css/node.css">
<link rel="stylesheet" href="${contextPath}/css/group.css">
<script>
var var_save_success_message =  Liferay.Language.get("edison-workflow-save-success-message");
var var_create_first_message = "Create First.";
var var_create_success_message = "Workflow successfully created.";
var var_pause_success_message = "Workflow paused.";
var var_resume_success_message = "Workflow restarted.";
var var_no_workflow_instance_msg = "Select workflowInstance first.";
var var_already_run_message = "This workflow has already been executed.";
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
    .apparea{position: relative; height: 100%; display: flex; flex-grow:1;}

    .wf-selected-node {
        -webkit-box-shadow: 9px 7px 7px 2px rgba(231,166,26,1);
        -moz-box-shadow: 9px 7px 7px 2px rgba(231,166,26,1);
        box-shadow: 9px 7px 7px 2px rgba(231,166,26,1);
    }

    .wf-selected-port {
        background-color: #ff8d00 !important;
    }
    .wf-selected-port-label {
        color: #ff8d00 !important;
        font-size: 14px !important;
    }

    .sidebar-menu > li > a.job-li {
        padding: 10px 5px 10px 12px;
        font-size: 12px;
    }

    #<portlet:namespace/>column-1 .header-inner {
        margin: 0px;
        padding: 15px 5px 10px 7px;
        border-bottom: none;
        font-size: 14px;
        line-height: 1;
    }

    #<portlet:namespace/>column-1 .label.label-primary.pull-right.sidebar-btn {
        cursor: pointer;
    }

    #<portlet:namespace/>column-1 .label.label-primary.pull-right.sidebar-btn:hover > i{
        color: orange;
    }

    nav.workbench-custom-nav ul > li:not(.<portlet:namespace/>divider-vertical){
        text-align: center;
        padding: 12px;
        cursor: pointer;
    }

    nav.workbench-custom-nav .<portlet:namespace/>divider-vertical {
        height: 50px;
        margin: 9px;
        border-left: 2px solid #f2f2f2;
        border-right: 1px solid #ffffff;
    }

    .nav li.<portlet:namespace/>divider-vertical{
        display: none;
    }

    .group-delete{
        display: none !important;
    }
    .workflow-executor.container-fluid { padding-left: 10px; padding-right: 10px;}

    .execute-panel.job-status-header{
    	width: 40%;
    	vertical-align: middle !important;
    }

    .execute-panel.job-status-body{
    	text-align: center;
    	vertical-align: middle !important;
    }

</style>
<div class="container-fluid workflow-executor">
  <div class="row" id="<portlet:namespace/>canvas" style="border-top: 1px solid #e5e5e9;">
    <div class="hold-transition skin-black-light sidebar-mini" id="body-div">
      <header class="main-header" id="<portlet:namespace/>column-2">
        <!-- App Name -->
        <div class="logo">
          <div class="logo-lg">
            <h3 style="font-size: 17px;" id="<portlet:namespace/>appName">Workflow Simulation</h3>
            <h5 id="<portlet:namespace/>appVersion"></h5>
          </div>
          <div class="logo-sm">
            <i class="fa fa-microchip" style="color:#00a65a;"></i>
          </div>
        </div>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar workbench-custom-nav">
          <!-- Navbar Left Menu -->
          <div class="navbar-left">
            <ul class="nav navbar-nav">
              <li id="<portlet:namespace/>header-li-simulation" class="top-btn" data-btn-type="open">
                <i class="fa fa-cubes fa-2x"></i><br>
                <span class="nav-icon-text">Simulations</span>
              </li>
              <li id="<portlet:namespace/>header-li-edit" class="top-btn" data-btn-type="setting">
                <i class="fa fa-cogs fa-2x"></i><br>
                <span class="nav-icon-text">Edit</span>
              </li>
              <li class="<portlet:namespace/>divider-vertical" id="<portlet:namespace/>job-li-divider" style="display: block;"></li>
              <li id="<portlet:namespace/>header-li-new" class="top-btn" data-btn-type="new-job" data-divider="job-li-divider">
                <i class="fa fa-plus-square-o fa-2x"></i><br>
                <span class="nav-icon-text">New</span>
              </li>
              <li id="<portlet:namespace/>header-li-save" data-btn-type="save-job" data-divider="job-li-divider">
                <i class="fa fa-save fa-2x"></i><br>
                <span class="nav-icon-text">Save</span>
              </li>
              <li id="<portlet:namespace/>header-li-copy" data-btn-type="copy-job" data-divider="job-li-divider">
                <i class="fa fa-copy fa-2x"></i><br>
                <span class="nav-icon-text">Copy</span>
              </li>
              <li id="<portlet:namespace/>header-li-delete" data-btn-type="delete-job" data-divider="job-li-divider">
                <i class="fa fa-trash-o fa-2x"></i><br>
                <span class="nav-icon-text">Delete</span>
              </li>
              <li class="<portlet:namespace/>divider-vertical" style="display: block;"></li>
              <li id="<portlet:namespace/>header-li-submit" data-divider="ib-li-divider" class="before-submit">
                <i class="fa fa-cloud-upload fa-2x"></i><br>
                <span class="nav-icon-text">Submit</span>
              </li>
              <li id="<portlet:namespace/>header-li-pause" data-divider="ib-li-divider" class="after-submit before-pause" style="display: none;">
                <i class="fa fa-pause-circle fa-2x"></i><br>
                <span class="nav-icon-text">Pause</span>
              </li>
              <li id="<portlet:namespace/>header-li-resume" data-divider="ib-li-divider" class="after-submit after-pause" style="display: none;">
                <i class="fa fa-play-circle fa-2x"></i><br>
                <span class="nav-icon-text">Resume</span>
              </li>
              <li id="<portlet:namespace/>header-li-rerun" data-divider="ib-li-divider" class="after-stop" style="display: none;">
                <i class="fa fa-undo fa-flip-horizontal fa-2x"></i><br>
                <span class="nav-icon-text">ReRun</span>
              </li>
              <li class="<portlet:namespace/>divider-vertical" style="display: block;"></li>
              <li id="<portlet:namespace/>header-li-data" class="top-btn" data-btn-type="designer" data-divider="data-li-divider">
                <i class="fa fa-share-square-o fa-2x"></i><br>
                <span class="nav-icon-text">Designer</span>
              </li>
              <li id="<portlet:namespace/>header-li-export" class="top-btn">
                <i class="fa fa-download fa-2x"></i><br>
                <span class="nav-icon-text">Export</span>
              </li>
            </ul>
          </div>
          <!-- Navbar Right Menu -->
        </nav>
      </header>

      <div class="wrapper">
        <aside class="main-sidebar">
          <section class="sidebar" id="<portlet:namespace/>column-1" section-type="system">
            <ul class="sidebar-menu top" data-widget="tree">
                <li class="header">
                    <div class="header-inner">
                        <i class="fa fa-folder"></i> No available Simulation Job
                    </div>
                </li>
            </ul>
          </section>
        </aside>
        <div class="content-wrapper">
          <div class="menu-panel">
            <div class="row menu-panel-box" id="<portlet:namespace/>menu-panel-box"></div>
            <div class="row menu-panel-box-app" id="<portlet:namespace/>menu-panel-box-app" style="display:none;"></div>
          </div>
          <section class="content" style="display: flex;">
            <div id="wf-workflow-canvas" class="apparea wf-drop jtk-surface">
              <div class="controls" can-undo="false" can-redo="false">
                <i class="fa fa-arrows selected-mode" mode="pan" title="Pan Mode"></i>
                <i class="fa fa-pencil" mode="select" title="Select Mode"></i>
                <i class="fa fa-home" reset title="Zoom To Fit"></i>
                <i class="fa fa-plus" zoom="in" title="Zoom In"></i>
                <i class="fa fa-minus" zoom="out" title="Zoom Out"></i>
              </div>
              <div id="miniview" style="position: absolute;top: 10px;right: 25px;z-index: 100;">

              </div>
              <div jtk-miniview-type="foo"></div>
            </div>
          </section>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="<portlet:namespace/>wf-modal" tabindex="-1" role="dialog"
  aria-labelledby="<portlet:namespace/>wf-modal-label" style="display: none;">
<div class="vertical-alignment-helper">
  <div class="modal-dialog modal-lg vertical-align-center" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="<portlet:namespace/>wf-modal-label"></h4>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
</div>

<script>
$.widget.bridge('uibutton', $.ui.button);
</script>
<script src="${contextPath}/js/jquery-knob/jquery.knob.min.js"></script>
<script src="${contextPath}/js/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="${contextPath}/js/fastclick/fastclick.js"></script>
<script src="${contextPath}/js/adminlte/adminlte.js"></script>
<script src="${contextPath}/js/lib/jquery.mustache.js"></script>
<script src="${contextPath}/js/lib/mustache.min.js"></script>
<script src="${contextPath}/js/lib/validator.min.js"></script>
<script src="${contextPath}/js/lib/jsplumbtoolkit.js"></script>
<script src="${contextPath}/js/lib/FileSaver.min.js"></script>
<script src="${contextPath}/js/constant.js"></script>
<script type="text/x-jtk-templates" src="${contextPath}/templete/execute-template.html"></script>

<script id="tpl-menu-panel-box" type="text/html">
<div class="{{panel-type}} col-md-{{col}}">
  <div class="box box-solid">
    <div class="box-header with-border header-inner">
      <h3 class="box-title default-title">{{boxtitle}}</h3>
      <div class="box-tools pull-right">
        <button type="button" class="btn btn-box-tool menu-panel-close"><i class="fa fa-times"></i></button>
      </div>
    </div>
    <div class="box-body">
    </div>
  </div>
</div>
</script>

<script id="tpl-menu-panel-search-header" type="text/html">
  <h3 class="box-title">
    <i class="fa fa-search"/>
    <input type="text" name="{{header.search-input-name}}" class="search-input" placeholder="{{boxtitle}}" />
  </h3>
</script>

<script id="tpl-menu-panel-load" type="text/html">
<table class="table table-bordered table-hover">
    <thead>
        <tr>
            {{#header.theads}}
            <th>{{.}}</th>
            {{/header.theads}}
        </tr>
    </thead>
    <tbody class="panel-tbody">
      <tr>
        <td colspan="{{header.theads.length}}">No Data</td>
      </tr>
    </tbody>
</table>
</script>

<script id="tpl-menu-panel-pagination" type="text/html">
  <div class="box-footer clearfix">
    <div class="btn-group" role="group">
    </div>
    <ul class="pagination pagination-sm no-margin pull-right">
    </ul>
  </div>
</script>

<script id="tpl-menu-panel-new" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title"
        placeholder="Title" value="{{form.title}}" required>
      <div class="help-block with-errors"></div>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat pull-right func" name="create">Create</button>
  </div>
</form>
</script>

<script id="tpl-menu-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Simulation Title</label>
      <input type="text" class="form-control data-binded" id="simulationTitle"
        name="simulationTitle" placeholder="Simulation Title" value="{{form.simulationTitle}}" readonly>
      <div class="help-block with-errors"></div>
    </div>
    <div class="form-group">
      <label for="title">Workflow Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.workflowTitle}}" readonly>
    </div>
    <div class="form-group">
      <label for="description" >Description</label>
      <textarea class="form-control data-binded" rows="5" name="description" id="description" readonly>{{form.workflowDescription}}</textarea>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat func" name="rename">Rename</button>
    <button type="button" class="btn btn-danger btn-flat pull-right func" name="delete">Delete</button>
  </div>
</form>
</script>

<script id="tpl-job-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Job Title</label>
      <input type="text" class="form-control data-binded" id="jobTitle"
        name="jobTitle" placeholder="Job Title" value="{{form.jobTitle}}" required>
      <div class="help-block with-errors"></div>
    </div>

	<table class="table table-bordered table-hover">
		<tbody class="panel-tbody">
			<tr>
				<th class="execute-panel job-status-header">
					<liferay-ui:message key='edison-simulation-execute-job-create-list-submit-time' />
				</th>
				<td class="execute-panel job-status-body">
					{{form.startTime}}
				</td>
			</tr>
			<tr>
				<th class="execute-panel job-status-header">
					<liferay-ui:message key='edison-simulation-monitoring-table-header-complete-time' />
				</td>
				<td class="execute-panel job-status-body">
					{{form.endTime}}
				</td>
			</tr>
		</tbody>
	</table>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat func" name="update">Rename</button>
	<button type="button" class="btn btn-info btn-flat func" name="copy">Copy</button>
    <button type="button" class="btn btn-danger btn-flat pull-right func" name="delete">Delete</button>
  </div>
</form>
</script>

<script id="tpl-modal-body" type="text/html">
{{#inputs}}
  <div class="form-group">
    <label for="{{name}}" class="control-label">{{name}}</label>
    <input type="text" class="form-control" name="{{name}}" value="{{value}}"/>
  </div>
{{/inputs}}
</script>
<script id="tpl-modal-footer" type="text/html">
    <button type="button" class="btn btn-default btn-flat" data-dismiss="modal" name="{{cancel}}">{{cancel}}</button>
    <button type="button" class="btn btn-primary btn-flat" name="{{ok}}">{{ok}}</button>
</script>
<script src="${contextPath}/js/moment.js"></script>
<script src="${contextPath}/js/moment-timezone.js"></script>
<script src="${contextPath}/js/moment-timezone-with-data-2012-2022.js"></script>
<script src="${contextPath}/js/lib/jquery-confirm.min.js"></script>
<script>
bStart();
var contextPath = '${contextPath}'
var namespace = "<portlet:namespace/>";
var jqPortletBoundaryId = "#p_p_id" + namespace;
var designer = null;
var executor = null;
var uiPanel = null;
$(document).ready(function(){
  consoleLog.setLoggingLevel({
    error : true,
    info : true,
    debug : false
  });
  var EDITOR_PORTLET_IDS = {
    "Text": '${textEditor.exeFileName}',
    "File": '${fileEditor.exeFileName}',
    "SDE": '${structuredEditor.exeFileName}'
  };
  var workflowId = "${workflowId}";
  // var workflowCount = ${workflowCount};
  $.Mustache.addFromDom();
  toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": true,
      "progressBar": false,
      "positionClass": "toast-designer-pos",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "5000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "slideDown",
      "hideMethod": "slideUp"
  };
  designer = new Designer(namespace, $, OSP, toastr, true, EDITOR_PORTLET_IDS, false);
  executor = new SimulationExecutor(namespace, $, designer, toastr);
  uiPanel = new UIPanelExecutor(namespace, $, designer, executor, toastr);
  designer.setUiPanelInstance(uiPanel);
  // var inputportModule = new WorkflowInputPort(namespace, $, designer, toastr, uiPanel, EDITOR_PORTLET_IDS);
  // designer.setWorkflowInputPortModule(inputportModule);

  //
  $("#exampleModal .modal-dialog").draggable({
      handle: ".modal-header"
  });
  _delay(function(){
    if(workflowId){
      uiPanel.openWorkflow(workflowId, false, function(){
        bEnd()
      });
    }
  }, 1000);
});

function <portlet:namespace/>moveToDesigner(){
    var thisPortletNamespace = "_workflowdesigner_WAR_edisonworkflow2016portlet_";
    var params = "&" + thisPortletNamespace + "workflowId=${workflowId}";

    /* 2019.01.16 _ state=pop_up -> state=maximized */
    var designerUrl = "<%=designerUrl%>";
    designerUrl = designerUrl.replace("pop_up", "maximized");
    location.href = designerUrl + params;
}

function <portlet:namespace/>getCompanyGroupId(){
  return "${companyGroupId}";
}

function <portlet:namespace/>getSiteGroupId(){
  return "${groupId}";
}

function <portlet:namespace/>getIcebreakerAccessToken(){
  var url = "<%=getIcebreakerAccessTokenUrl%>";
  var result = synchronousAjaxHelper.post(url, {});
  return result;
}

function <portlet:namespace/>openSolverDeatilPopup(scienceAppId) {
  var groupId = <portlet:namespace/>getSiteGroupId();
  var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
  var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
      params += "&" + thisPortletNamespace + "groupId=" + groupId;
  window.open("<%=scienceAppDetailUrl%>" + params);
}

AUI().ready(['liferay-util-window'], function(){
	Liferay.provide(window, "setJobDataFromModule", function(nodeId, portName, jobData, dialogId){
        uiPanel.setPortData(nodeId, portName, jobData)
        uiPanel.closePortPopup(nodeId, portName ,dialogId)
    });
	Liferay.provide(window, "closeFromModule", function(nodeId, portName, dialogId){
        uiPanel.closePortPopup(nodeId, portName, dialogId)
	});

	Liferay.provide(window, "setSimAndJobFromWorkbench", function(nodeId, simulationUuid, jobUuid, jobDataArray){
		<portlet:namespace/>closePopup("dataTypeSearchDialog");

		/* TODO 노드Id로 노드 찾아서 SimUuid, JobUuid 세팅해주기 */
		uiPanel.setSelectedJobFromWorkbench(nodeId, simulationUuid, jobUuid, jobDataArray);
		$("body").css('overflow','');
	});

	Liferay.provide(window,'<portlet:namespace />closePopup',function(popupIdToClose) {
		Liferay.Util.getWindow(popupIdToClose).destroy();
	});
});

function <portlet:namespace/>copyParentNodeFiles(params){
	var resultObj = new Object();
	var copyFileResult = false;
	var jobData = "";
	var getError="";
	$.ajax({
		url: "<%=copyParentNodeFilesURL%>",
		cache: false,
		async: false,
		data: {
			"<portlet:namespace/>copyFileObj" : JSON.stringify(params)
		},
		success: function(response) {
			copyFileResult = true;
			jobData = response.jobData;
		}, error:function(response,e){
			copyFileResult = false;
			jobData = "";
			getError = response.responseText;
		},complete: function(response){
			resultObj.copyFileResult = copyFileResult;
			resultObj.jobData = jobData;
			resultObj.error = getError;
		}
	});
	return resultObj;
}

</script>
</div>
