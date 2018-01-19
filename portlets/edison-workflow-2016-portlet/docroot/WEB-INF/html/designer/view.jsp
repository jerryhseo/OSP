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
            <i class="fa fa-lg fa-file"></i> <span>New</span>
          </a>
        </li>
        <li class="treeview">
          <a href="#" class="sidebar-btn" data-btn-type="open">
            <i class="fa fa-lg fa-folder-open"></i> <span>Open</span>
          </a>
        </li>
        <li class="treeview">
          <a href="#" class="sidebar-btn" data-btn-type="import">
            <i class="fa fa-lg fa-download"></i> <span>Import</span>
          </a>
        </li>
        <li class="treeview">
          <a href="#" class="sidebar-btn" data-btn-type="save">
            <i class="fa fa-lg fa-floppy-o"></i> <span>Save</span>
          </a>
        </li>
        <li class="treeview">
          <a href="#" class="sidebar-btn" data-btn-type="save-as">
            <i class="fa fa-lg fa-floppy-o"></i> <span>Save As</span>
          </a>
        </li>
        <li>
          <a href="#" class="sidebar-btn" data-btn-type="apps">
            <i class="fa fa-lg fa-th"></i> <span>Apps</span>
          </a>
        </li>
      </ul>
      <ul class="sidebar-menu bottom" data-widget="tree">
        <li class="treeview">
          <a href="#" class="sidebar-btn" data-btn-type="execute">
            <i class="fa fa-lg fa-play-circle"></i> <span>Execute</span>
          </a>
        </li>
        <li>
          <a href="#" class="sidebar-btn" data-btn-type="setting">
            <i class="fa fa-lg  fa-gear"></i> <span>Setting</span>
          </a>
        </li>
        <li>
          <div class="sidebar-toggle-wrapper" class="sidebar-toggle" id="sidebar-toggle" data-toggle="push-menu" role="button"  >
            <a href="#" class="sidebar-toggle" >
              <i class="fa fa-lg fa-angle-left fa-2x pull-right"></i>
              <span class="sr-only">Toggle navigation</span>
            </a>
          </div>
        </li>
      </ul>
    </section>
  </aside>
  <style>

</style>
  <div class="content-wrapper">
    <div class="menu-panel">
      <div class="row" id="<portlet:namespace/>menu-panel-box">
      </div>
    </div>
    <section class="content-header">
      <h1>
      <a id="mobile-toggle" href="#mobile-toggle" data-toggle="push-menu">
        <i class="fa fa-lg fa-compress"></i>
        <span class="sr-only">Toggle navigation</span>
      </a>
        Title
        <small>sub title</small>
      </h1>
    </section>
    <!-- Main content -->
    <section class="content">
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
      <td>Trident</td>
      <td>Internet
        Explorer 5.0
      </td>
      <td>Win 95+</td>
      <td>5</td>
      <td>C</td>
    </tr>
    <tr>
      <td>Trident</td>
      <td>Internet
        Explorer 5.5
      </td>
      <td>Win 95+</td>
      <td>5.5</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Trident</td>
      <td>Internet
        Explorer 6
      </td>
      <td>Win 98+</td>
      <td>6</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Trident</td>
      <td>Internet Explorer 7</td>
      <td>Win XP SP2+</td>
      <td>7</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Trident</td>
      <td>AOL browser (AOL desktop)</td>
      <td>Win XP</td>
      <td>6</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Firefox 1.0</td>
      <td>Win 98+ / OSX.2+</td>
      <td>1.7</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Firefox 1.5</td>
      <td>Win 98+ / OSX.2+</td>
      <td>1.8</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Firefox 2.0</td>
      <td>Win 98+ / OSX.2+</td>
      <td>1.8</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Firefox 3.0</td>
      <td>Win 2k+ / OSX.3+</td>
      <td>1.9</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Camino 1.0</td>
      <td>OSX.2+</td>
      <td>1.8</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Camino 1.5</td>
      <td>OSX.3+</td>
      <td>1.8</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Netscape 7.2</td>
      <td>Win 95+ / Mac OS 8.6-9.2</td>
      <td>1.7</td>
      <td>A</td>
    </tr>
    <tr>
      <td>Gecko</td>
      <td>Netscape Browser 8</td>
      <td>Win 98SE+</td>
      <td>1.7</td>
      <td>A</td>
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
      <input type="email" class="form-control" id="title" placeholder="Title" value="{{form.title}}">
    </div>
    <div class="form-group">
      <label for="description">Description</label>
      <textarea class="form-control" rows="5" id="description">{{form.description}}</textarea>
    </div>
  </div>
  <div class="box-footer">
    <button type="submit" class="btn btn-primary btn-flat pull-right">{{form.submitname}}</button>
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
    <button type="submit" class="btn btn-primary btn-flat ">Save</button>
    <button type="submit" class="btn btn-danger btn-flat pull-right">Delete</button>
  </div>
</form>
</script>


<script>
$(document).ready(function(){
    var namespace = "<portlet:namespace/>";
    var jqPortletBoundaryId = "#p_p_id" + namespace;
    $.Mustache.addFromDom();
    var panelTemplateData = {
      "apps": {
          "col": 4 
      }, "new": {
          "col": 6,
          "body": "tpl-menu-panel-new",
          "form":{
              "submitname": "Create"    
          }
      }, "open": {
          "col": 10, 
          "body": "tpl-menu-panel-load",
          "footer": "tpl-menu-panel-pagination"
      }, "import": {
          "col": 10, 
          "body": "tpl-menu-panel-load",
          "footer": "tpl-menu-panel-pagination"
      }, "save-as": {
          "col": 6,
          "body": "tpl-menu-panel-new",
          "form":{
              "submitname": "Save"
          }
      }, "setting": {
          "col": 6,
          "body": "tpl-menu-panel-setting"
      }
    };
    
    $(jqPortletBoundaryId + " .sidebar-btn").click(function(e){
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        var templateData = panelTemplateData[btnType];
        if(templateData){
            activateLi(this);
            templateData["boxtitle"] = $(this).text();
            $("#" + namespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
            $("#" + namespace + "menu-panel-box .box-body").mustache(templateData["body"], templateData);
            if(templateData["footer"]){
                $("#" + namespace + "menu-panel-box .box").append($.Mustache.render(templateData["footer"], templateData));
            }
            $("#" + namespace + "menu-panel-box .menu-panel-close").click(function(e){
                e.preventDefault();
                $(".menu-panel").hide('slide', {direction:'left'}, 500);
                $(jqPortletBoundaryId + " .sidebar > .sidebar-menu > li.active").removeClass("active");
            });
            $(".menu-panel").show('slide', {direction:'left'}, 500);
        }
    });
    
    function activateLi(jqLink){
        $(jqPortletBoundaryId + " .sidebar > .sidebar-menu > li.active").removeClass("active");
        $(jqLink).parent("li").addClass("active");
    }
    
 });
</script>
</div>