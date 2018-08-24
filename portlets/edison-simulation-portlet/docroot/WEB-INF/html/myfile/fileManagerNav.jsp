<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><img src="images/Dropbox-logo.png" alt=""></a>
		</div>
		
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li title="Level up" class="toolbar"><a href="#" class="glyphicon glyphicon-level-up"></a></li>
				<li title="Upload" class="toolbar"><a href="#" class="glyphicon glyphicon-upload"></a></li>
				<li title="New folder" class="toolbar toolbar-new-folder"><a href="#" class="glyphicon glyphicon-folder-open"></a></li>
				<li title="Grid view" class="toolbar toolbar-gridview"><a href="#" class="glyphicon glyphicon-th"></a></li>
				<li title="List view" class="toolbar toolbar-listview"><a href="#" class="glyphicon glyphicon-th-list"></a></li>
			</ul>
			
			<form class="navbar-form navbar-right" role="search">
				<div class="form-group">
					<input type="text" class="form-control nav-inp fileSearchBox" placeholder="Search">
				</div>
			</form>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>
<ol class="breadcrumb">
	<li><a href="#">Home</a></li>
	<li><a href="#">Library</a></li>
	<li class="active">Data</li>
</ol>