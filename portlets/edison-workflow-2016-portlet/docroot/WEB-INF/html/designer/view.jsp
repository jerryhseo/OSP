<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
  <link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
  <link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
  <link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">
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
  background-color: none;
  outline: 0;
  margin-left: 5px !important;
}

.menu-panel-box-app .search-input:focus {
  border: none;
  background-color: none;
  outline: 0;
}

</style>
  <div class="content-wrapper">
    <div class="menu-panel">
      <div class="row" id="<portlet:namespace/>menu-panel-box"></div>
      <div class="row menu-panel-box-app" id="<portlet:namespace/>menu-panel-box-app" style="display:none;"></div>
    </div>
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
      <div>
      </div>
    </section>
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
  <div class="col-md-{{col}}">
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
<form class="form-horizontal">
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
<form class="form-horizontal">
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

<script src="${contextPath}/js/lib/jquery.actual.min.js"></script>
<script src="${contextPath}/js/lib/jstree.js"></script>
<script src="${contextPath}/js/lib/util.js"></script>
<script src="${contextPath}/js/apptree.js"></script>
<script src="${contextPath}/js/designer.js"></script>
<script>
$(document).ready(function(){
  var namespace = "<portlet:namespace/>";
  $.Mustache.addFromDom();
  var designer = new Designer(namespace, $);

  aSyncAjaxHelper.post("/delegate/services/app/all", {
    companyGroupId: <portlet:namespace/>getCompanyGroupId(),
    groupId: <portlet:namespace/>getSiteGroupId()
  }, function(appData){
    $("#" + namespace + "menu-panel-box-app").mustache(
      'tpl-menu-panel-apps', {"boxtitle": "Apps", "col": 4});
    $("#" + namespace + "menu-panel-box-app").addClass("loaded");
    drawAppTree(
      "#" + namespace + "menu-panel-box-app .box-body",
      "#" + namespace + "menu-panel-box-app .search-input",
      appData);
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
</script>
</div>