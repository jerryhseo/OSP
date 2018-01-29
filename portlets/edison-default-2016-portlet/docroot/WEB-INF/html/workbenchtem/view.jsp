<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<link rel="stylesheet" href="${contextPath}/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/css/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminCustom.css">

<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<link rel="stylesheet" href="${contextPath}/css/workbench.css">

<style type="text/css">
	.edison-workbench-template .sidebar-collapse .sidebar .pagination{
		display: none;
	}
	
	.edison-workbench-template .sidebar .pagination{
		padding-left: 45px;
		display: inline-block;
	}
	
	.edison-workbench-template .sidebar .treeview-menu i.success{
		color: green;
	}
	.edison-workbench-template .sidebar .treeview-menu i.running{
		color: blue;
	}
	.edison-workbench-template .sidebar .treeview-menu i.fail{
		color: red;
	}
	
	.edison-workbench-template .sidebar .sidebar-menu li.menu-open ul.treeview-menu{
		padding-left: 13px;
	}
	
	.edison-workbench-template .sub-col{
 		outline: 3px solid #dddddd;
	}
	
	.edison-workbench-template .workbench-layout-area{
		position: relative;
		padding-left: 30px;
		padding-right: 30px;
	}
	.edison-workbench-template .workbench-layout-area .row{
		position: relative;
	}
	
	.edison-workbench-template .content-header{
/* 		padding-bottom: 15px; */
	}
	
	

	.edison-workbench-template .vertical {
		top: 50%;
		left: 50%;
		margin-top: -20px;
		margin-left: -20px;
 		background: url(${contextPath}/images/workbench/arrow_lr.png) center no-repeat; 
/* 		border: 1px solid red; */
	}
	
	
	.edison-workbench-template .horizontal {
		left: 50%;
		margin-top: -20px;
		margin-left: -20px;
/*  		border: 1px solid green; */
		background: url(${contextPath}/images/workbench/arrow_tb.png) center no-repeat;
	}
	
    .edison-workbench-template .devider {
		position: absolute;
		z-index: 3;
		width: 40px;
		height: 40px;
		cursor: move;
/* 		border-radius: 50%; */
/* 		border: 1px solid #dedede; */
	}
	
	/*aui css error*/
	.edison-workbench-template .collapse {
		display: none;
	}
	
	#port-remote {
	    position: absolute;
	    z-index: 9;
	}
	
	#mydivheader {
	    cursor: move;
	    z-index: 10;
	}
</style>
<script type="text/javascript">
$(function() {
	$( "#port-remote" ).draggable({
		containment: "parent",
		cursor: "move"
	}
	);
	/**
	* Layout Scroll
	* vertical Left 기준 Width check -> data-equal-id="LEFT DIV" data-remainder-id="RIGHT DIV"
	* horizontal top 기준 Width check -> data-equal-id="BOTTOM DIV" data-remainder-id="TOP DIV"
	*
	**/
	var isDevider = false;
	var object,container,equalDiv,RemainderDiv,moveType;
	$(".devider").mousedown(function(e) {
		isDevider = true;
		object = e.currentTarget;
		container = $(this).parent();
		equalDiv = $("#"+$(this).attr("data-equal-id"));
		RemainderDiv = $("#"+$(this).attr("data-remainder-id"));
		moveType = $(this).hasClass("vertical") ? "vertical" : "horizontal"
	});
	
	$("body").mouseup(function(a) {
		if(isDevider){
			isDevider = false;
		}
	});
	$("body").mousemove(function(e) {
		if(isDevider){
			if(moveType =="vertical"){
				var offsetRight = container.width() - (e.clientX - container.offset().left);
				var moveLeft = Math.round(offsetRight / container.width() * 100);
				
				var objectLeftPositon = 100-moveLeft;
				if(objectLeftPositon<20){
					objectLeftPositon = 20;
				}else if(objectLeftPositon>80){
					objectLeftPositon = 80;
				}
				var objectRightPositon = 100-objectLeftPositon;
				
				$(object).css("left",objectLeftPositon+"%");
				$(equalDiv).css("width",objectLeftPositon+"%");
				$(RemainderDiv).css("width",objectRightPositon+"%");
			}else{
// 				var offsetTop = container.height() - (e.clientY - container.offset().top);
				console.log("container.height()-->"+container.height()+"__e.clientY-->"+(e.clientY)+"__container.offset().top-->"+container.offset().top);
// 				console.log("container.height()-->"+container.height()+"__e.clientY-->"+(e.clientY-15)+"__container.offset().top-->"+(container.offset().top)+"__offsetTop--->"+offsetTop);
// 				var offsetBottom = container.height() - offsetTop;
				var offsetBottom = e.clientY;
// 				var moveTop = Math.round(offsetTop / container.height() * 100);
				
// 				var objectTopPositon = 100-moveTop;
				
// 				if(objectTopPositon<20){
// 					objectTopPositon = 20;
// 				}else if(objectTopPositon>80){
// 					objectTopPositon = 80;
// 				}
				
// 				var objectBottomPositon = 100-objectTopPositon;
				$(object).css("top",offsetBottom+"px");
// 				$(equalDiv).find("div.sub-col").css("height",offsetTop+"px");
// 				$(RemainderDiv).find("div.sub-col").css("height",offsetBottom+"px");
			}
			
		}
	});
	var namespace = "<portlet:namespace/>";
    var jqPortletBoundaryId = "#p_p_id" + namespace;
    $.Mustache.addFromDom();
    var panelTemplateData = {
      "search-job-info": {
          "col": 4,
          "body": "tpl-menu-panel-setting"
      }
    };
    
	
	$(jqPortletBoundaryId + " .sidebar-btn").click(function(e){
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        var templateData = panelTemplateData[btnType];
        if(templateData){
            activateLi(this);
//             templateData["boxtitle"] = $(this).text();
			templateData["boxtitle"] = "JOB Infomation";
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
<script id="tpl-menu-panel-setting" type="text/html">
<form class="form-horizontal">
  <div class="box-body">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="email" class="form-control" id="title" placeholder="Title" value="{{form.title}}">
    </div>
	<table id="example2" class="table table-bordered table-hover">
		<tr>
			<th>생성시간</th>
			<td>2017-12-10 15:30:20</td>
		</tr>
		<tr>
			<th>제출 시간</th>
			<td>2017-12-10 15:31:20</td>
		</tr>
		<tr>
			<th>실행시간</th>
			<td>2017-12-10 15:32:30</td>
		</tr>
		<tr>
			<th>작업상태</th>
			<td>SUCCESS</td>
		</tr>
	</table>
  </div>
  <div class="box-footer">
    <button type="submit" class="btn btn-primary btn-flat ">save</button>
    <button type="submit" class="btn btn-danger btn-flat pull-right">Delete</button>
  </div>
</form>
</script>

<div class="row hold-transition skin-black-light sidebar-mini" id="body-div">
<div class="wrapper">
	<aside class="main-sidebar">
		<section class="sidebar">
			<ul class="sidebar-menu top" data-widget="tree">
				<li class="header">
					<div class="header-inner">
						<h2>Bowling</h2>
						<h4>v1.0.0</h4>
						<button class="btn btn-default btn-xs" type="button"><span class="icon-book"> Manual</span></button>
					</div>
				</li>
				<li>
					 <a href="#"> <i class="fa fa-lg fa-file"></i> <span>New Simulation</span> </a>
				</li>
				<li class="treeview menu-open">
					<a href="#" onclick="">
						<i class="fa fa-lg fa-folder"></i> 
						<span>Simulation-1</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-left pull-right"></i>
<!-- 							<small class="label pull-right bg-red">5</small> -->
						</span>
					</a>
					<ul class="treeview-menu" style="display: block;">
						<li>
							<span class="btn-group" style="margin-left: 120px;margin-top: 5px;">
								<button class="btn btn-default btn-sm" type="button" title="Job Create"><i class="fa fa-plus-circle"></i></button>
								<button class="btn btn-default btn-sm" type="button" title="Edit Simulation"><i class="fa fa-edit"></i></button>
							</span>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-circle fail"></i>
								<span>Simulation-JOB-1</span>
								<span class="label label-primary pull-right"><i class="icon-arrow-right sidebar-btn" data-btn-type="search-job-info"></i></span>
							</a>
							
						</li>
						<li class="active">
							<a href="">
								<i class="fa fa-circle success"></i>
								<span>Simulation-JOB-2</span>
								<span class="label label-primary pull-right"><i class="icon-arrow-right sidebar-btn" data-btn-type="search-job-info"></i></span>
							</a>
						</li>
						<li>
							<a href="">
								<i class="fa fa-circle running"></i>
								<span>Simulation-JOB-3</span>
								<span class="label label-primary pull-right"><i class="icon-arrow-right sidebar-btn" data-btn-type="search-job-info"></i></span>
							</a>
						</li>
						<li>
							<a href="">
								<i class="fa fa-circle"></i>
								<span>Simulation-JOB-3</span>
								<span class="label label-primary pull-right"><i class="icon-arrow-right sidebar-btn" data-btn-type="search-job-info"></i></span>
							</a>
						</li>
					</ul>
				</li>
			</ul>
			<section id="<portlet:namespace/>pagin" class="text-center">
				<ul class="pagination">
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">»</a></li>
				</ul>
			</section>
			
			<ul class="sidebar-menu bottom" data-widget="tree">
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
	</aside>
	
	<div class="content-wrapper">
		<div class="menu-panel">
			<div class="row" id="<portlet:namespace/>menu-panel-box"></div>
		</div>
		<section class="content-header">
			<div class="btn-group btn-breadcrumb">
				<a href="#" class="btn btn-default"><span class="fa fa-history">  App Manager</span></a>
				<span class="btn btn-primary">Bowling</span>
				<a href="#" class="btn btn-primary"><span class="fa fa-folder">  Simulation-1</span></a>
				<a href="#" class="btn btn-primary"><span class="fa fa-desktop">  Simulation-JOB-2</span></a>
				<span class="btn btn-primary">Simulation Layout</span>
<!-- 				<a href="#" class="btn btn-primary"><span class="icon-edit">  입력</span></a> -->
<%-- 				<button class="btn btn-default" disabled type="button" onClick="<portlet:namespace/>getUserInfo('owner');"><span class="icon-user"> <liferay-ui:message key='edison-appstore-solver-transfer' /></span></button> --%>
<!-- 				<a href="#" class="btn btn-default"><span class="icon-bar-chart">  모니터링</span></a> -->
<!-- 				<a href="#" class="btn btn-default"><span class="icon-picture">  분석</span></a> -->
			</div>
			
<!-- 			<ol class="breadcrumb"> -->
<!-- 				<li><i class="fa fa-folder"></i> Simulation-1</li> -->
<!-- 				<li><i class="fa fa-desktop"></i> Simulation-JOB-2</li> -->
<!-- 			</ol> -->
		</section>
		
		<section class="content workbench-layout-area">
			<div class="row" id="row-3">
				<div class="col-md-6 sub-col" id="column-3">
					
				</div>
				<div class="devider vertical" data-equal-id="column-3" data-remainder-id="column-4"></div>
				<div class="col-md-6 sub-col" id="column-4">
					
				</div>
			</div>
			<div class="devider horizontal" data-equal-id="row-4" data-remainder-id="row-3"></div>
			<div class="row" id="row-4">
				<div class="col-md-12 sub-col">
					
				</div>
			</div>
		</section>
		<div class="panel panel-primary" id="port-remote" style="left: 1300px;top: 0px">
				<div class="panel-heading" id="mydivheader">Port Selector</div>
				<div class="panel-body">
					<div class="panel-group" id="accordion">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse_1">
											입력
										</a>
								</h4>
							</div>
							<div id="collapse_1" class="collapse">
								<div class="panel-body">
									<ul class="list-group">
										<li class="list-group-item list-group-item-default">
											-inp1
										</li>
										<li class="list-group-item list-group-item-default">
											-inp2
										</li>
										<li class="list-group-item list-group-item-default">
											-inp3
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse_2">
											로그
										</a>
								</h4>
							</div>
							<div id="collapse_2" class="collapse">
								<div class="panel-body">
									<ul class="list-group">
										<li class="list-group-item list-group-item-default">
											-inp1
										</li>
										<li class="list-group-item list-group-item-default">
											-inp2
										</li>
										<li class="list-group-item list-group-item-default">
											-inp3
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse_3">
											출력
										</a>
								</h4>
							</div>
							<div id="collapse_3" class="collapse">
								<div class="panel-body">
									<ul class="list-group">
										<li class="list-group-item list-group-item-default">
											-inp1
										</li>
										<li class="list-group-item list-group-item-default">
											-inp2
										</li>
										<li class="list-group-item list-group-item-default">
											-inp3
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					프로비넌스 엔진 <liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/>
					<input type="checkbox" data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-on="Enabled" data-off="Disabled">
					<button type="submit" class="btn btn-primary btn-flat ">제출</button>
				</div>
			</div>
	</div>
</div>
</div>

<script src="${contextPath}/js/jquery-knob/jquery.knob.min.js"></script>
<script src="${contextPath}/js/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="${contextPath}/js/fastclick/fastclick.js"></script>
<script src="${contextPath}/js/adminlte/adminlte.js"></script>
<script src="${contextPath}/js/lib/jquery.mustache.js"></script>
<script src="${contextPath}/js/lib/mustache.min.js"></script>





