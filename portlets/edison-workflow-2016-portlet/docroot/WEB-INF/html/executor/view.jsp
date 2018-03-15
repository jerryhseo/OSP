<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="getIcebreakerAccessTokenUrl" escapeXml="false" id="getIcebreakerAccessToken"
  copyCurrentRenderParameters="false" />
<liferay-portlet:renderURL var="designerUrl" portletName="workflowdesigner_WAR_edisonworkflow2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
</liferay-portlet:renderURL>
<link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script>
var var_save_success_message =  Liferay.Language.get("edison-workflow-save-success-message");
var var_create_first_message = "Create First.";
var var_select_workflow_first_message = "Select workflow first.";
var var_create_success_message = "Workflow successfully created.";
var var_no_workflow_instance_msg = "Select workflowInstance first.";
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
.menu-panel-box-app .box-body.jstree{
  overflow-y: auto;
}
.menu-panel-box-app .box-body.jstree ul > li > a{
  text-overflow: ellipsis; white-space: nowrap; word-wrap: normal; overflow: hidden; width: 85%;
}

#body-div .menu-panel-box-app .header-inner,
 #body-div .menu-panel-box .header-inner{
  padding: 15px 5px 10px 10px;
}

.search-input {
  border: none;
  outline: 0;
  margin-left: 5px !important;
}

.search-input:focus {
  border: none;
  outline: 0;
}

.menu-panel{
  pointer-events:none;
  background:none !important;
}

.menu-panel-box,
.menu-panel-box-app > .app-column{
  pointer-events: auto;
}

.menu-panel tbody.panel-tbody > tr {
  cursor: pointer;
}

.default-title{line-height: 1.3 !important;}

/* workflow science app box */
.apparea{position: relative;}

.waitingbox{border-radius:3px; border:solid 1px #00abe3; background-color:#b6e1f8;}
.waitingbox span{font-size:18px; color:#114a69; font-weight:500;}

.loopbox{background-color:#f7b036;}

.runningbox{border-radius:3px; border:solid 1px #00abe3; background-color:#8db9e5;}
.runningbox span{font-size:18px; color:#fff; font-weight:500;}

.failbox{border-radius:3px; border:solid 1px #00abe3; background-color:#f8799b;}
.failbox span{font-size:18px; color:#fff; font-weight:500;}

.donebox{border-radius:3px; border:solid 1px #00abe3; background-color:#76d6cd;}
.donebox span{font-size:18px; color:#fff; font-weight:500;}

.wf-box {
  box-sizing: content-box;
  padding: 30px 5px 5px 15px;
  width: 100px !important;
  height: 110px !important;
  position: absolute;
  cursor: move;
  border-radius: 3px;
  border: solid 1px #00abe3;
}

.wf-box .wf-app-title, .wf-container .jstree-leaf{white-space: nowrap; overflow: hidden; text-overflow: ellipsis;}
.wf-box .wf-app-title{font-size: 15px; font-weight: 500; color: #fff;}
.wf-box .wf-app-status{position: relative; top: 25px;}
.waitingbox .wf-app-title{ color: #114a69; border-color: #5492ba; }
.pausebox{border-radius:3px; border:solid 1px #CA412B; background-color:#fb6e50;}
.wf-box .addIp { font-weight: 500; text-decoration: none; text-indent: 0px; 
	line-height: 0px; -moz-border-radius: 3px; -webkit-border-radius: 3px; border-radius: 3px; 
	text-align: center; vertical-align: middle; display: inline-block; font-size: 12px; 
	color: #fff; padding: 10px; text-shadow: #ade6ff 0px 0px 0px; border-width: 1px; border-style: solid; }
.waitingbox .addIp{ background: #6ba0c3; border-color: #3371a8; }
.runningbox .addIp{ background: #3a81c0; border-color: #3371a8; padding-right: 25px !important; background-position: 60px; background-repeat:no-repeat; background-image: url(../images/Workflow/ajax-loader.gif);}
.failbox .addIp{ background: #c84444; border-color: #b73535; }
.pausebox .addIp{ background: #4E5A68; border-color: #4E5A68; }
.donebox .addIp{ background: #32a993; border-color: #2e9886; }
.wf-app-status-icon{ top: 25px; left: 10px; position: relative;}

.wf-converter{}
.wf-converter.wf-dynamic > .wf-app-title{overflow: visible; white-space: normal;}
.waitingbox.wf-converter.wf-dynamic{background: #44b4c5;}
.waitingbox.wf-converter.wf-static{background: #3181c6;}    

.wf-box.wf-controller{
    width: 150px !important;
    height: 120px !important;
    padding: 0px !important;
    border: none !important;
}
.wf-box > svg {
    width: 180px;
    height: 140px;
}
.wf-box g.fc-decision > text{
    font-size: 15px;
    font-weight: bold;
    fill:  #114a69;
}
.wf-box g.fc-decision > .fc-rhombus{
    stroke: #00abe3;
    fill: #44b4c5;
}

.hidden{display: none;}
.jsplumb-endpoint:hover{cursor: pointer; z-index: 9999;}
.jsplumb-overlay{z-index: 1 !important;}

.wftitlebox001 > input[type="text"]{width: 55%; margin-bottom: 2px;}
.wftitlebox001 > span > input[type="button"]{margin-right: 5px;}
.wftitlebox001 > h2 {float: left;}
.apparea > div {z-index: 2;}

.ui-selectable-helper { position: absolute; z-index: 100; border:1px dotted black; }
.ui-selected{border: solid 1px #555555; box-shadow: 3px 3px 7px #c6c6c6;}
.ui-selected.wf-controller g.fc-decision > .fc-rhombus {stroke: #555555; filter: drop-shadow( 3px 3px 7px #c6c6c6 );}
.ui-selected.wf-controller{border: none; box-shadow: none;}

.ui-selecting.wf-controller g.fc-decision > .fc-rhombus{fill:rgba( 30, 30, 30,0.4 ); stroke: #555555; filter: drop-shadow( 3px 3px 7px #c6c6c6 );}
.ui-selecting.wf-converter,
.ui-selecting.wf-app{background-color: rgba( 30, 30, 30,0.4 ) !important; box-shadow: 3px 3px 7px #c6c6c6; border: solid 1px #000;}

.toast-designer-pos { top: 120px; right: 12px; }

.vertical-alignment-helper {
    display:table;
    height: 100%;
    width: 100%;
}
.vertical-align-center {
    display: table-cell;
    vertical-align: middle;
}
.modal-content {
    width:inherit;
    height:inherit;
    margin: 0 auto;
}

</style>
<div class="container-fluid">
  <div class="row hold-transition skin-black-light sidebar-mini" id="body-div">
    <div class="wrapper">
      <aside class="main-sidebar">
        <section class="sidebar">
          <ul class="sidebar-menu top" data-widget="tree">
            <li class="header">
              <div class="header-inner">
                Simulation Executor
              </div>
            </li>
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="new">
                <i class="fa fa-lg fa-file"></i>
                <span>New</span>
              </a>
            </li>
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="open">
                <i class="fa fa-lg fa-folder-open"></i>
                <span>Open</span>
              </a>
            </li>
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="save">
                <i class="fa fa-lg fa-floppy-o"></i>
                <span>Save</span>
              </a>
            </li>
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="execute">
                <i class="fa fa-lg fa-play-circle"></i>
                <span>Run</span>
              </a>
              <ul class="treeview-menu">
                <li><a href="#" class="sidbar-run-btn" data-btn-type="run"><i></i><span>Run Simulation</span></a></li>
                <li><a href="#" class="sidbar-run-btn" data-btn-type="rerun"><i></i><span>Rerun Simulation</span></a></li>
                <li><a href="#" class="sidbar-run-btn" data-btn-type="pause"><i></i><span>Pause</span></a></li>
                <li><a href="#" class="sidbar-run-btn" data-btn-type="restart"><i></i><span>Restart</span></a></li>
                <li><a href="#" class="sidbar-run-btn" data-btn-type="status"><i></i><span>Status</span></a></li>
              </ul>
            </li>
          </ul>
          <ul class="sidebar-menu bottom" data-widget="tree">
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="designer">
                <i class="fa fa-lg fa-pencil-square-o"></i>
                <span>Edit</span>
              </a>
            </li>
            <li>
              <a href="#" class="sidebar-btn" data-btn-type="setting">
                <i class="fa fa-lg  fa-gear"></i>
                <span>Setting</span>
              </a>
            </li>
            <li>
              <div class="sidebar-toggle-wrapper" class="sidebar-toggle" id="sidebar-toggle" data-toggle="push-menu" role="button">
                <a href="#" class="sidebar-toggle">
                  <i class="fa fa-lg fa-angle-left fa-2x pull-right"></i>
                  <span class="sr-only">Toggle navigation</span>
                </a>
              </div>
            </li>
          </ul>
        </section>
      </aside>
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        <a id="mobile-toggle" href="#mobile-toggle" data-toggle="push-menu">
          <i class="fa fa-lg fa-compress"></i>
          <span class="sr-only">Toggle navigation</span>
        </a>
        <span id="<portlet:namespace/>workflow-title"></span>
        <small id="<portlet:namespace/>workflow-sub-title"></small>
      </h1>
    </section>
    <!-- Main content -->
    <section class="content">
      <div id="wf-workflow-canvas" class="apparea wf-drop jsplumb-drag-select"></div>
    </section>
    <div class="menu-panel" style="top: 0;">
      <div class="row menu-panel-box" id="<portlet:namespace/>menu-panel-box"></div>
      <div class="row menu-panel-box-app" id="<portlet:namespace/>menu-panel-box-app" style="display:none;"></div>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>

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

<script id="tpl-menu-panel-new" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="workflowInstanceTitle" >Simulation Title</label>
      <input type="text" class="form-control data-binded" id="workflowInstanceTitle" name="workflowInstanceTitle" 
        placeholder="Simulation Title" value="{{form.workflowInstanceTitle}}" required>
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
      <input type="text" class="form-control data-binded" id="workflowInstanceTitle"
        name="workflowInstanceTitle" placeholder="Simulation Title" value="{{form.workflowInstanceTitle}}" required>
      <div class="help-block with-errors"></div>
    </div>
    <div class="form-group">
      <label for="title">Workflow Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}" readonly>
    </div>
    <div class="form-group">
      <label for="description" >Description</label>
      <textarea class="form-control data-binded" rows="5" name="description" id="description" readonly>{{form.description}}</textarea>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat func" name="update">Save</button>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
<script>
$(document).ready(function(){
  consoleLog.setLoggingLevel({
    error : true,
    info : true,
    debug : false
  });
  var namespace = "<portlet:namespace/>";
  var jqPortletBoundaryId = "#p_p_id" + namespace;
  var workflowId = "${workflowId}";
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
  var designer = new Designer(namespace, $, OSP, toastr, true);
  var executor = new SimulationExecutor(namespace, $, designer, toastr,'<%=LiferayWindowState.EXCLUSIVE%>');
  var uiPanel = new UIPanelExecutor(namespace, $, designer, executor, toastr);
  var inputportModule = new WorkflowInputPort(namespace, $, designer, toastr, uiPanel);
  designer.setWorkflowInputPortModule(inputportModule);
  /*
  var uiPanel = new UIPanel(namespace, $, designer, toastr);
  var appTree = new AppTree(namespace, $, designer);
  var selectable = new Selectable(namespace, $, designer);
  */

  uiPanel.openWorkflow(workflowId);
  $("#exampleModal .modal-dialog").draggable({
      handle: ".modal-header"
  });
  _delay(function(){
      $("#wf-workflow-canvas").css("height", 
        $(jqPortletBoundaryId + " div.content-wrapper").actual("height")
        - $(jqPortletBoundaryId + " section.content-header").actual("outerHeight"));
  }, 3000);
});

function <portlet:namespace/>moveToDesigner(){
    var thisPortletNamespace = "_workflowdesigner_WAR_edisonworkflow2016portlet_";
    var params = "&" + thisPortletNamespace + "workflowId=${workflowId}";
    location.href = "<%=designerUrl%>" + params;
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

</script>
</div>