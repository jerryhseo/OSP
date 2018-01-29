<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="getSpecificSiteGroupIdUrl" escapeXml="false" id="getSpecificSiteGroupId"
  copyCurrentRenderParameters="false" />
<link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">
<script>
var var_save_success_message =  Liferay.Language.get("edison-workflow-save-success-message");
var var_create_first_message = "Create First.";
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

#body-div .menu-panel-box-app .header-inner{
  padding: 15px 5px 10px 10px;
}

.menu-panel-box-app .search-input {
  border: none;
  outline: 0;
  margin-left: 5px !important;
}

.menu-panel-box-app .search-input:focus {
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

.hidden{display: none;}
.jsplumb-endpoint:hover{cursor: pointer; z-index: 9999;}

.wftitlebox001 > input[type="text"]{width: 55%; margin-bottom: 2px;}
.wftitlebox001 > span > input[type="button"]{margin-right: 5px;}
.wftitlebox001 > h2 {float: left;}
.apparea > div {z-index: 2;}

.ui-selectable-helper { position: absolute; z-index: 100; border:1px dotted black; }
.ui-selected{border: solid 1px #555555; box-shadow: 3px 3px 7px #c6c6c6;}
.ui-selecting{background-color: rgba( 30, 30, 30,0.4 ); box-shadow: 3px 3px 7px #c6c6c6; border: solid 1px #000;}

.toast-designer-pos { top: 120px; right: 12px; }

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
                Workflow Designer
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
              <a href="#" class="sidebar-btn" data-btn-type="import">
                <i class="fa fa-lg fa-download"></i>
                <span>Import</span>
              </a>
            </li>
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
            <li>
              <a href="#" class="sidebar-btn" data-btn-type="apps">
                <i class="fa fa-lg fa-th"></i>
                <span>Apps</span>
              </a>
            </li>
          </ul>
          <ul class="sidebar-menu bottom" data-widget="tree">
            <li class="treeview">
              <a href="#" class="sidebar-btn" data-btn-type="execute">
                <i class="fa fa-lg fa-play-circle"></i>
                <span>Execute</span>
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
        <span id="<portlet:namespace/>workflow-title">Title</span>
        <small>sub title</small>
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
<div class="col-md-{{col}}">
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

<script id="tpl-menu-panel-table-box" type="text/html">
<div class="col-md-{{col}}">
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

<script id="tpl-menu-panel-apps" type="text/html">
<div class="col-md-{{col}} app-column">
  <div class="box box-solid">
    <div class="box-header with-border header-inner">
      <h3 class="box-title">
        <i class="fa fa-search"/>
        <input type="text" name="search" class="search-input" placeholder="{{boxtitle}}" />
      </h3>
      <div class="box-tools pull-right">
        <button type="button" class="btn btn-box-tool menu-panel-close"><i class="fa fa-times"></i></button>
      </div>
    </div>
    <div class="box-body">
    </div>
  </div>
</div>
</script>
<script id="tpl-menu-panel-load" type="text/html">
<table id="example2" class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>Rendering engine</th>
      <th>Browser</th>
      <th>Platform(s)</th>
      <th>Engine version</th>
      <th>CSS grade</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Trident</td>
      <td>Internet
        Explorer 4.0
      </td>
      <td>Win 95+</td>
      <td> 4</td>
      <td>X</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Netscape Navigator 9</td>
      <td>Win 98+ / OSX.2+</td>
      <td>1.8</td>
      <td>A</td>
    </tr>
    </tfoot>
  </table>
</script>

<script id="tpl-menu-panel-pagination" type="text/html">
<div class="box-footer clearfix">
  <ul class="pagination pagination-sm no-margin pull-right">
    <li><a href="#">&laquo;</a></li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">&raquo;</a></li>
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
    <button type="button" class="btn btn-primary btn-flat pull-right func" name="create">{{form.submitname}}</button>
  </div>
</form>
</script>

<script id="tpl-menu-panel-setting" type="text/html">
<form class="form-horizontal" onsubmit="return false;">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="email" class="form-control" id="title" placeholder="Title" value="{{form.title}}">
    </div>
    <div class="form-group">
      <label for="description" >Description</label>
      <textarea class="form-control" rows="5" id="description">{{form.description}}</textarea>
    </div>
    <div class="form-group"></div>
    <div class="form-group">
      <label for="description">Export as App</label>
      <button type="button" class="btn btn-info btn-flat pull-right">Export</button>
    </div>
  </div>
  <div class="box-footer">
    <button type="button" class="btn btn-primary btn-flat ">Save</button>
    <button type="button" class="btn btn-danger btn-flat pull-right">Delete</button>
  </div>
</form>
</script>

<script>
$(document).ready(function(){
  var namespace = "<portlet:namespace/>";
  var jqPortletBoundaryId = "#p_p_id" + namespace;
  $.Mustache.addFromDom();
  toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": true,
      "progressBar": false,
      "positionClass": "toast-designer-pos",
      "preventDuplicates": true,
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
  var designer = new Designer(namespace, $, OSP, toastr);
  var uiPanel = new UIPanel(namespace, $, designer);
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
      $(jqPortletBoundaryId + " div.content-wrapper").actual("height")
      - $(jqPortletBoundaryId + " section.content-header").actual("outerHeight"));
    $("#" + namespace + "menu-panel-box-app .box-body").css("height",
      $("#" + namespace + "menu-panel-box-app").actual("height") 
        - $(".menu-panel .box.box-solid > .box-header").actual("outerHeight"));
  });
});

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
</script>
</div>