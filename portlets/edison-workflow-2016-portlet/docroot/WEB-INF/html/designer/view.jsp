<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="getSpecificSiteGroupIdUrl" escapeXml="false" id="getSpecificSiteGroupId"
  copyCurrentRenderParameters="false" />
<liferay-portlet:renderURL var="executorUrl" portletName="workflowsimulationexecutor_WAR_edisonworkflow2016portlet"
  windowState="<%=LiferayWindowState.POP_UP.toString() %>" >
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet"
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="registerWorkflowAppUrl" portletName="scienceappmanager_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" portletMode="view" plid="${scienceAppPlid}" >
  <liferay-portlet:param name="myRender" value="solverRender" />
</liferay-portlet:renderURL> 

<liferay-portlet:resourceURL var="addSampleFileURL" id="addSampleFile" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="deleteWfSampleFilesURL" id="deleteWfSampleFiles" copyCurrentRenderParameters="false"/>

<portlet:resourceURL var='fileDownloadURL' id='fileDownload' escapeXml="false"></portlet:resourceURL>

<link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">
<link rel="stylesheet" href="${contextPath}/css/jquery-confirm.min.css">
<link rel="stylesheet" href="${contextPath}/css/node.css">
<link rel="stylesheet" href="${contextPath}/css/group.css">

<!-- 2018.12.21 - Add jsPlumb CSS -->
<link rel="stylesheet" href="${contextPath}/css/jsplumb/jsplumbtoolkit-defaults.css">
<script>
var var_save_success_message =  Liferay.Language.get("edison-workflow-save-success-message");
var var_create_first_message = "Create First.";
var var_select_workflow_first_message = "Select workflow first.";
var var_create_success_message = "Workflow successfully created.";
var var_new_workflow_confirm_message = Liferay.Language.get("edison-workflow-new-confirm-message");
var var_remove_workflow_confirm_message = Liferay.Language.get("edison-workflow-remove-confirm-message");
var var_remove_with_app_confirm_message = Liferay.Language.get("edison-workflow-remove-with-app-confirm-message");
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
var var_workflow_register_app_error_message = Liferay.Language.get("edison-workflow-register-app-error-message");
var var_workflow_register_app_role_error_message = Liferay.Language.get("edison-workflow-register-app-role-error-message");
var var_workflow_config_data_error_message = Liferay.Language.get("edison-workflow-config-data-error-message");
var var_workflow_remove_status_error_message = Liferay.Language.get("edison-workflow-remove-status-error-message");



var var_is_developer = '${isDeveloper}';
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

.waitingbox{border-radius:3px; border:solid 1px #adadad; background-color:#adadad;}
.waitingbox span{font-size:18px; color:#114a69; font-weight:500;}

.loopbox{background-color:#f7b036;}

.runningbox{border-radius:3px; border:solid 1px #8db9e5; background-color:#8db9e5;}
.runningbox span{font-size:18px; color:#fff; font-weight:500;}

.failbox{border-radius:3px; border:solid 1px #f8799b; background-color:#f8799b;}
.failbox span{font-size:18px; color:#fff; font-weight:500;}

.donebox{border-radius:3px; border:solid 1px #76d6cd; background-color:#76d6cd;}
.donebox span{font-size:18px; color:#fff; font-weight:500;}

.pausebox{border-radius:3px; border:solid 1px #fd9b00; background-color:#fd9b00;}



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

.controls{
	top: 145px !important;
	left: auto !important;
	right: 23px !important;
	z-index: 10 !important;
	color: #FFF;
	position: absolute;
	display: flex;
}
.controls i{
	background-color: #5184a0;
	border-radius: 4px;
	cursor: pointer;
	padding: 4px;
	margin-right: 5px !important;
}

.selected-mode {
	color: #E4F013;
}
</style>

<div class="container-fluid">
  <div class="row hold-transition skin-black-light sidebar-mini" id="body-div">
    <div class="wrapper" style="border-top: 1px solid #e5e5e9;">
      <aside class="main-sidebar">
        <section class="sidebar">
          <ul class="sidebar-menu top" data-widget="tree">
            <li class="header">
              <div class="header-inner">
                Workflow Designer
              </div>
            </li>
            <li class="treeview <portlet:namespace/>init-tab">
              <a href="#" class="sidebar-btn" data-btn-type="new">
                <i class="fa fa-lg fa-file"></i>
                <span>New</span>
              </a>
            </li>
            <li class="treeview <portlet:namespace/>init-tab">
              <a href="#" class="sidebar-btn" data-btn-type="open">
                <i class="fa fa-lg fa-folder-open"></i>
                <span>Open</span>
              </a>
            </li>
<!--             <li class="treeview"> -->
<!--               <a href="#" class="sidebar-btn" data-btn-type="import"> -->
<!--                 <i class="fa fa-lg fa-download"></i> -->
<!--                 <span>Import</span> -->
<!--               </a> -->
<!--             </li> -->
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="save">
                <i class="fa fa-lg fa-floppy-o"></i>
                <span>Save</span>
              </a>
            </li>
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="save-as">
                <i class="fa fa-lg fa-floppy-o"></i>
                <span>Save As</span>
              </a>
            </li>
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="apps">
                <i class="fa fa-lg fa-th"></i>
                <span>Apps</span>
              </a>
            </li>
            <li class="treeview" id="<portlet:namespace/>active-app-register">
              <a href="#" class="sidebar-btn" data-btn-type="register-app">
                <i class="fa fa-lg fa-archive"></i>
                <span>Register App</span>
              </a>
            </li>
            <li class="treeview" id="<portlet:namespace/>active-app-config" style="display: none;">
              <a href="#" class="sidebar-btn" data-btn-type="config-app">
                <i class="fa fa-lg fa-archive"></i>
                <span>Configuration App</span>
              </a>
            </li>
            
            <li class="treeview">
              <a href="#" class="sidebar-btn" id="<portlet:namespace/>executor" data-btn-type="execute">
                <i class="fa fa-lg fa-play-circle"></i>
                <span>Execute</span>
              </a>
            </li>
          </ul>
          
           <ul class="sidebar-menu bottom" data-widget="tree">
            <li class="treeview">
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
        <small id="<portlet:namespace/>workflow-app-title"></small>
      </h1>
    </section>
    <!-- Main content -->
    <section class="content">
      <div id="wf-workflow-canvas" class="apparea wf-drop jsplumb-drag-select">
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
  <div class="modal-dialog vertical-align-center" role="document">
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

<div class="modal fade" id="<portlet:namespace/>wf-file-modal" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace/>wf-modal-label" style="display: none;">
<div class="vertical-alignment-helper">
  <div class="modal-dialog vertical-align-center" role="document">
    <div class="modal-content">
      <form id="<portlet:namespace/>sampleForm" method="POST" action="<%=addSampleFileURL%>" enctype="multipart/form-data">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="<portlet:namespace/>wf-modal-label"></h4>
	      </div>
	      <div class="modal-body">
	      </div>
	      <div class="modal-footer">
	      </div>
      </form>
    </div>
  </div>
</div>
</div>

<div id="<portlet:namespace/>portFileUploadDialog" title="파일업로드" class="bigpopupbox" style="display:none;">
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
<script src="${contextPath}/js/validator.min.js"></script>
<script src="${contextPath}/js/lib/jsplumbtoolkit.js"></script>
<script type="text/x-jtk-templates" src="${contextPath}/templete/templete.html"></script>


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

<script id="tpl-menu-panel-table-box" type="text/html">
<div class="{{panel-type}} col-md-{{col}}">
  <div class="box box-solid">
    <div class="box-header with-border header-inner">
      <h3 class="box-title">{{boxtitle}}</h3>
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

<script id="tpl-menu-panel-apps" type="text/html">
<div class="{{panel-type}} col-md-{{col}} app-column">
  <div class="box box-solid">
    <div class="box-header with-border header-inner">
      <h3 class="box-title">
        <i class="fa fa-search"/>
        <input type="text" name="search" class="search-input" placeholder="{{boxtitle}}" />
      </h3>
      <div class="box-tools pull-right">
        <button type="button" class="btn btn-box-tool menu-panel-close" onclick="<portlet:namespace/>closeAppPanel()"><i class="fa fa-times"></i></button>
      </div>
    </div>
    <div class="box-body">
    </div>
  </div>
</div>
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
      <label for="title" >Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title"
        placeholder="Title" value="{{form.title}}" required>
      <div class="help-block with-errors"></div>
    </div>
    <div class="form-group">
      <label for="description">Description</label>
      <textarea class="form-control data-binded" rows="5" id="description" name="description">{{form.description}}</textarea>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat pull-right func" name="create">Create</button>
  </div>
</form>
</script>

<script id="tpl-menu-panel-save" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title" >Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title"
        placeholder="Title" value="{{form.title}}" required>
      <div class="help-block with-errors"></div>
    </div>
    <div class="form-group">
      <label for="description">Description</label>
      <textarea class="form-control data-binded" rows="5" id="description" name="description">{{form.description}}</textarea>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat pull-right func" name="save">Save</button>
  </div>
</form>
</script>

<script id="tpl-menu-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control data-binded" id="title" name="title" placeholder="Title" value="{{form.title}}">
    </div>
    <div class="form-group">
      <label for="description" >Description</label>
      <textarea class="form-control data-binded" rows="5" name="description" id="description">{{form.description}}</textarea>
    </div>
    <div class="form-group"></div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat func" name="update">Save</button>
    <button type="button" class="btn btn-danger btn-flat pull-right func" name="delete">Delete</button>
  </div>
</form>
</script>

<script id="tpl-modal-wf-app-data-body" type="text/html">
{{#inputs}}
	<div class="form-group">
		<label for="{{name}}" class="control-label">{{name}}</label>
		<textarea class="form-control" rows="20" id="{{name}}" name="{{name}}" style="resize: none; ">{{value}}</textarea>
	</div>
{{/inputs}}
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

<script id="tpl-modal-wf-app-file-body" type="text/html">
<input type="hidden" name="<portlet:namespace/>fileType" value="{{formType}}">
{{#hiddens}}
<input type="hidden" name="<portlet:namespace/>{{name}}" value="{{value}}">	
{{/hiddens}}

{{#inputs}}
	<div class="form-group">
		<label for="{{title}}" class="control-label">{{title}}</label>
		{{#isAppFile}}
			<h5 style="cursor:pointer;" onClick="<portlet:namespace/>fileDownLoad('{{fileId}}')"><i class="icon-download-alt"></i> {{fileName}}</h5>
		{{/isAppFile}}
		{{#isFile}}
			<input type="file" class="form-control-file" name="<portlet:namespace/>sampleFile">
			{{#fileId}}
				<h5 style="cursor:pointer;" onClick="<portlet:namespace/>fileDownLoad('{{fileId}}')"><i class="icon-download-alt"></i> {{fileName}}</h5>
				<input type="hidden" name="<portlet:namespace/>preFileId" value="{{fileId}}">
			{{/fileId}}
		{{/isFile}}
		{{#isSelect}}
			<select class="form-control" name="<portlet:namespace/>{{name}}">
				{{#options}}
					<option value="{{value}}"  {{#selected}}selected="selected"{{/selected}}>{{optionName}}</option>
				{{/options}}
			</select>
		{{/isSelect}}
		{{#isInput}}
			<input type="text" class="form-control" name="<portlet:namespace/>{{name}}" value="{{value}}"/>
		{{/isInput}}
	</div>
{{/inputs}}
</script>

<script id="tpl-modal-file-footer" type="text/html">
    <button type="button" class="btn btn-default btn-flat" data-dismiss="modal" name="{{cancel}}">{{cancel}}</button>
    <button type="submit" class="btn btn-primary btn-flat" name="{{ok}}">{{ok}}</button>
</script>


<script src="${contextPath}/js/jquery-confirm.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.form.min.js"></script>
<script>
var JQ_PORTLET_BOUNDARY_ID = "#p_p_id<portlet:namespace/>";
$(document).ready(function(){
	
	$("ul.sidebar-menu.top > li.treeview").hide();
	$("ul.sidebar-menu.top > li.treeview.<portlet:namespace/>init-tab").show();
	
  var namespace = "<portlet:namespace/>";
  var loadedWorkflowId = '${workflowId}';

  
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
  var EDITOR_PORTLET_IDS = {
    "Text": '${textEditor.exeFileName}',
    "File": '${fileEditor.exeFileName}',
    "SDE": '${structuredEditor.exeFileName}'
  };
  
  /* Register WorkflowApp Data */
  var registerWorkflowAppURL = "<%=registerWorkflowAppUrl%>";
  var REGISTER_WORKFLOW_APP_PARAM = {
	"registerWorkflowAppURL": registerWorkflowAppURL,
	"portletName" : "_scienceappmanager_WAR_edisonappstore2016portlet_"
  }
  
  var designer = new Designer(namespace, $, OSP, toastr, false, EDITOR_PORTLET_IDS, true);
  var uiPanel = new UIPanel(namespace, $, designer, toastr, REGISTER_WORKFLOW_APP_PARAM);
  designer.setUiPanelInstance(uiPanel);
  var appTree = new AppTree(namespace, $, designer);
  var selectable = new Selectable(namespace, $, designer);

  aSyncAjaxHelper.post("/delegate/services/app/all", {
    companyGroupId: <portlet:namespace/>getCompanyGroupId(),
    groupId: <portlet:namespace/>getSiteGroupId()
  }, function(appData){
    $("#" + namespace + "menu-panel-box-app").mustache(
      'tpl-menu-panel-apps', {"boxtitle": "Apps", "col": 4});
    $("#" + namespace + "menu-panel-box-app").addClass("loaded");
    var apptreeSelector = "#" + namespace + "menu-panel-box-app .box-body";
    appTree.drawAppTree(apptreeSelector,
      "#" + namespace + "menu-panel-box-app .search-input",
      appData);
    appTree.bindDnd(document, apptreeSelector);
    $("#wf-workflow-canvas").css("height",
      $(JQ_PORTLET_BOUNDARY_ID + " div.content-wrapper").actual("height")
      - $(JQ_PORTLET_BOUNDARY_ID + " section.content-header").actual("outerHeight"));
    $("#" + namespace + "menu-panel-box-app .box-body").css("height",
      $("#" + namespace + "menu-panel-box-app").actual("height")
        - $(".menu-panel .box.box-solid > .box-header").actual("outerHeight"));
  });

  
  /*JSPlumb의 Canvas의 높이가 정확히 계산이 되기 위하여 3초간 Block*/
  bStart();
  setTimeout(function() {
	  if(loadedWorkflowId && loadedWorkflowId !== 'null'){
		uiPanel.openWorkflow(loadedWorkflowId);
	  }
    bEnd();
  }, 5000);
  
  
  
  $("#<portlet:namespace/>sampleForm").ajaxForm({
	  beforeSubmit: function(arr, $form, options){
		  bStart();
		  $("#<portlet:namespace/>wf-file-modal").modal("hide");
	  },
	  success: function(msg) {
		  var fileType = msg.fileType;
		  var nodeId = msg.nodeId;
		  
		  var sampleData = new Object();
		  if(nullToStr(msg.fileEntryId)!=""){
			  sampleData[OSP.Constants.CONTENT] = msg.fileEntryId;
			  sampleData[OSP.Constants.TYPE] = OSP.Constants.DLENTRY_ID;
			  sampleData[OSP.Constants.NAME] = msg.fileName;
		  }
		  
		  if(fileType==="wf-app"){
			  designer.setAppSampleData(nodeId,sampleData);
		  }else if(fileType==="wf-port"){
			  var preFileId = msg.preFileId;
			  
			  var portType = msg.portType;
			  var portId = msg.portId;
			  var defaultEditor = msg.defaultEditor;
			  var isWfSample = msg.isWfSample;
			  
			  designer.setPortSampleData(nodeId,portType,portId,preFileId,defaultEditor,isWfSample,sampleData);
		  }
		  
		  toastr["success"]("", Liferay.Language.get('edison-data-insert-success'));
	  },
	  error:function(jqXHR, textStatus, errorThrown){
		if(jqXHR.responseText !== ''){
// 			alert(textStatus+" ajaxForm : "+jqXHR.responseText);
		}else{
// 			alert(textStatus+"ajaxForm : "+errorThrown);
		}
		
		toastr["error"]("", Liferay.Language.get('edison-data-insert-error'));
		return false;
	},
	complete: function(xhr) {
		bEnd();
	}
  });
});

function <portlet:namespace/>moveToExecutor(workflowId){
    var thisPortletNamespace = "_workflowsimulationexecutor_WAR_edisonworkflow2016portlet_";
    var params = "&" + thisPortletNamespace + "workflowId=" + workflowId;
    location.href = "<%=executorUrl%>" + params;
}

function <portlet:namespace/>getCompanyGroupId(){
  return "${companyGroupId}";
}

function <portlet:namespace/>getSiteGroupId(){
  return "${groupId}";
}

function <portlet:namespace/>getSpecificSiteGroupId(){
  var url = "<%=getSpecificSiteGroupIdUrl%>";
  var result = synchronousAjaxHelper.post(url, {});
  return result["groupId"];
}

function <portlet:namespace/>openSolverDeatilPopup(scienceAppId) {
  var groupId = <portlet:namespace/>getSiteGroupId();
  var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
  var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
      params += "&" + thisPortletNamespace + "groupId=" + groupId;
  window.open("<%=scienceAppDetailUrl%>" + params);
}

function <portlet:namespace/>fileDownLoad(entryId){
	window.location.href="<%=fileDownloadURL.toString()%>&<portlet:namespace/>fileEntryId="+entryId;
}

function <portlet:namespace/>closeAppPanel() {
	$(".menu-panel").hide('slide', { direction: 'left' }, 500);
	$(JQ_PORTLET_BOUNDARY_ID + " .sidebar > .sidebar-menu > li.active").removeClass("active");
}

function <portlet:namespace/>deleteWfSampleFiles(nodeData){
    var wfSampleFileIds = nodeData.files;
    if(wfSampleFileIds != 'undifined' && wfSampleFileIds != null && wfSampleFileIds != ''){
	    $.ajax({
			url: "<%=deleteWfSampleFilesURL%>",
			cache: false,
			data: {
				"<portlet:namespace/>wfSampleFileIds" : wfSampleFileIds.toString()
			},
			success: function(response) {
				
			}, error:function(response,e){ 
				alert('Sample file deletion error');
			},complete: function(response){
				
			}
		});
    }
}

function cogClick(nodeId){
	$("#"+nodeId).contextmenu();
}
</script>
</div>
