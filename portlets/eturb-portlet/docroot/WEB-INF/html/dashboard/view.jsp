<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ include file="/common/init.jsp"%>

<script type="text/javascript" src="${contextPath}/js/jstree.min.js"></script>
<script src="${contextPath}/js/dashboard/dashboard.js"></script>
<script src="${contextPath}/js/jquery-confirm.min.js"></script>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jstree/themes/proton/style.css" media="screen"/>
<link rel="stylesheet" href="${contextPath}/css/jquery-confirm.min.css">
<link type="text/css" rel="stylesheet" href="${contextPath}/css/bootstrap-panel.css" media="screen"/>
<link rel="stylesheet" href="${contextPath}/css/font-awesome.min.css">

<style>

.eturb-editor-dashboard th.TC{
	text-align: center;
	vertical-align: middle;
}

/*
.eturb-editor-dashboard .btn-group{
	float: left;
	margin-top: 1px;
	margin-left:33px;
	margin-bottom: 3px;
	z-index: 1;
	vertical-align:middle;
}
*/
.eturb-editor-dashboard .btn-group{
    position: relative;
    display: block;
    font-size: 0;
    vertical-align: middle;
    white-space: nowrap;
}

.eturb-editor-dashboard .accordion {
	width: 100%;
	margin: 1em 0em;
}

.eturb-editor-dashboard .ui-accordion .ui-accordion-header{
	padding: 0px 0px 0px 2.2em;
	font-size: 15px;
}

.eturb-editor-dashboard .accordion h3 {
/* 	background: url(../images/dashboard/plus.png) no-repeat right center #F6F6F6; */
    /*border: 1px solid #3777AF;*/
    border : 1px solid #d3d3d3;
    color: #3777AF;
    display: block;
    font-size: 1.5em;
    margin: -1px 0 0 !important;
    -moz-transition: all 0.3s ease-in-out 0s;
    -webkit-transition: all 0.3s ease-in-out 0s;
    -o-transition: all 0.3s ease-in-out 0s;
    transition: all 0.3s ease-in-out 0s;
}

.eturb-editor-dashboard .accordion p {
	font-size: 1.2em;
	line-height: 1.4em;
	padding: 10px;
}

.eturb-editor-dashboard .accordion h3.active {
	background: url(../images/dashboard/delete.gif) no-repeat right center #ffffff;
}

.eturb-editor-dashboard .accordion h3:hover {
	background-color: #ffffff;
	color: #5999CF;
	cursor: pointer;
}

.eturb-editor-dashboard .ui-widget-content {
	 /* border: 2px solid #3777AF; */
	 /*border: 1px solid #aaa;*/
	 border-left: 1px solid #d3d3d3;
	 border-right: 1px solid #d3d3d3;
	 border-bottom: 1px solid #d3d3d3;
	 border-radius: 0px;
}

.eturb-editor-dashboard .ui-accordion .ui-accordion-content {
	padding: 0px;
}

.eturb-editor-dashboard .dashboard-content {
/* 	padding: 1em 0.5em; */
}

.eturb-editor-dashboard .dashboard-content {
/* 	padding: 1em 0.5em; */
}

.eturb-editor-dashboard .dashboard-content span.font{
	/*font-family: Arial, Nanum Barun Gothic, NanumGothic;*/
	font-family: Malgun Gothic;
}

.aui .eturb-editor-dashboard .btn-group > .btn{
	font-size: 12px;
}

.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
	border: 1px solid #d3d3d3;
/*     background: #fff url(images/ui-bg_glass_65_ffffff_1x400.png) 50% 50% repeat-x; */
    
}

.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active {
    border: 1px solid #d3d3d3;
/*     background: #e6e6e6 url(images/ui-bg_glass_75_e6e6e6_1x400.png) 50% 50% repeat-x; */
}


.paging{overflow:hidden; width:100%; height:80px; background:#fff; position:relative; text-align:center;}
.paging ul{margin:0; padding:0; margin-top:25px; display: inline-block; zoom: 1; *display: inline;}
.paging ul li{width:30px; height:30px; font-size:17px; float:left; margin:4px 5px; text-align:center;}
.paging ul li img{color:#4d545b; margin:-4px 0px;}
.paging ul li a{color:#4d545b;}
.paging ul li a:hover{color:#ff4200;}
.paging ul li.select{color:#ff4200;}
</style>

<div class="accordion" id="<portlet:namespace/>accordion">
	<h2 style="font-family: Malgun Gothic;font-size:15px;border-radius: 0px;">
		Navigator - 
		<c:choose>
			<c:when test="${fn:length(project.name) > 14}">
				<c:out value="${fn:substring(project.name,0,13)}"/>....
			</c:when>
			<c:otherwise>
				<c:out value="${project.name}"/>
			</c:otherwise> 
		</c:choose>
	</h2>
	<div>
		<%@ include file="./navigator.jsp" %>
	</div>

	<h2 id="<portlet:namespace/>acc-parameter" style="font-family: Malgun Gothic;font-size:15px;border-radius: 0px;">Parametric Airfoil</h2>
	<div>
		<%@ include file="./parameter.jsp" %>
	</div>
	
	<h2 id="<portlet:namespace/>acc-boundary" style="font-family: Malgun Gothic;font-size:15px;border-radius: 0px;">Boundary Conditions</h2>
	<div>
		<%@ include file="./surface.jsp" %>
	</div>
</div>
<c:choose>
	<c:when test="${site eq 'KFLOW'}">
		<%@ include file="./kflow-mesh-modal.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="./mesh-modal.jsp" %>
	</c:otherwise>
</c:choose>

<%@ include file="./app-modal.jsp" %>

<script type="text/javascript">
	$(function() {
		$("#<portlet:namespace/>accordion").accordion({
			heightStyle: "content",
			animate: false,
			activate: function( event, ui ) {
				var firstAccDiv = $("#<portlet:namespace/>accordion").accordion().children('div:eq(0)');
				firstAccDiv.show();
			},
			create: function( event, ui ) {
				$("#<portlet:namespace/>accordion > h2:gt(0)").hide();
			}
		});
		
		<portlet:namespace/>navigatorInitJstree();
		
		if('${site}' == 'ETURB'){
			<portlet:namespace/>parameterInitEditor(OSP.Enumeration.PathType.STRUCTURED_DATA,${parametric},'parametric');
		}else{
			<portlet:namespace/>parameterInitEditor(OSP.Enumeration.PathType.STRUCTURED_DATA,${parametric},'parametric');
			<portlet:namespace/>parameterInitEditor(OSP.Enumeration.PathType.STRUCTURED_DATA,${meshparametric},'meshparametric');
		}
	});
	
	
	function <portlet:namespace/>viewAccodianVisible(node_type){
		var viewAccArr = DASH.Constants.getAccodianTypes(node_type);
		$("#<portlet:namespace/>accordion > h2:gt(0)").hide();
		
		if(viewAccArr.length!=0){
			for (var i = 0, x = viewAccArr.length; i < x; i++) {
				var viewAcc = viewAccArr[i];
				var accViewH3 = viewAcc[0];
				var opened = viewAcc[1];
				var accOBJ = $("#<portlet:namespace/>acc-"+accViewH3);
				accOBJ.show();
				
				if(opened){
					var accNum = accOBJ.attr("aria-controls").replace(/[^0-9]/g,"");	
					$("#<portlet:namespace/>accordion").accordion("option", "active" , parseInt(accNum));
				} else{
					$("#<portlet:namespace/>accordion").accordion("option", "active" ,0);
				}
			}
		}else{
			$("#<portlet:namespace/>accordion").accordion("option", "active" ,0);
		}
		
		var isInitViewflag = (node_type != DASH.Constants.TYPE_GEO_PARAMETER);
		<portlet:namespace/>setNavigatorInitAttr(isInitViewflag);
	}
	
	function <portlet:namespace/>wait(msecs){
		var start = new Date().getTime();
		var cur = start;
		while(cur - start < msecs){
			cur = new Date().getTime();
		}
	}
</script>