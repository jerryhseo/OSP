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
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- search form -->
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu top" data-widget="tree">
        <li class="header">
            <div class="header-inner">
                Workflow Designer
            </div>
        </li>
        <li>
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                        <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                </div>
            </form>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Tree Menu</span>
          </a>
          <ul class="treeview-menu">
            <li><a href=""><i class="fa"></i>Menu 1</a></li>
            <li><a href=""><i class="fa"></i>Menu 2</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Tree Menu</span>
          </a>
          <ul class="treeview-menu">
            <li><a href=""><i class="fa"></i>Menu 1</a></li>
            <li><a href=""><i class="fa"></i>Menu 2</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Tree Menu</span>
          </a>
          <ul class="treeview-menu">
            <li><a href=""><i class="fa"></i>Menu 1</a></li>
            <li><a href=""><i class="fa"></i>Menu 2</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Tree Menu</span>
          </a>
          <ul class="treeview-menu">
            <li><a href=""><i class="fa"></i>Menu 1</a></li>
            <li><a href=""><i class="fa"></i>Menu 2</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Tree Menu</span>
          </a>
          <ul class="treeview-menu">
            <li><a href=""><i class="fa"></i>Menu 1</a></li>
            <li><a href=""><i class="fa"></i>Menu 2</a></li>
          </ul>
        </li>
        <li>
          <a href="#function" onclick="alert('onclick'); return false;">
            <i class="fa fa-th"></i> <span>JS Function</span>
          </a>
        </li>
      </ul>
      <ul class="sidebar-menu bottom" data-widget="tree">
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Tree Menu</span>
          </a>
          <ul class="treeview-menu">
            <li><a href=""><i class="fa"></i>Menu 1</a></li>
            <li><a href=""><i class="fa"></i>Menu 2</a></li>
          </ul>
        </li>
        <li>
          <a href="#function" onclick="alert('onclick'); return false;">
            <i class="fa fa-th"></i> <span>JS Function</span>
          </a>
        </li>
        <li>
          <div class="sidebar-toggle-wrapper" class="sidebar-toggle" id="sidebar-toggle" data-toggle="push-menu" role="button"  >
            <a href="#" class="sidebar-toggle" >
              <i class="fa fa-angle-left fa-2x pull-right"></i>
              <span class="sr-only">Toggle navigation</span>
            </a>
          </div>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
      <a id="mobile-toggle" href="#mobile-toggle" data-toggle="push-menu">
        <i class="fa fa-compress"></i>
        <span class="sr-only">Toggle navigation</span>
      </a>
        Title
        <small>sub title</small>
      </h1>
      <!-- <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol> -->
    </section>
    

    <!-- Main content -->
    <section class="content">
        <h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p><h1>My First Heading</h1>
<p>My first paragraph.</p>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->
</div>

  
<script src="${contextPath}/js/jquery-knob/dist/jquery.knob.min.js"></script>
<script src="${contextPath}/js/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="${contextPath}/js/fastclick/lib/fastclick.js"></script>
<script src="${contextPath}/js/adminlte/adminlte.js"></script>
<script>
</script>
</div>