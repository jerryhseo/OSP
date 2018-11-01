<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<portlet:resourceURL var='downManualFileURL' id='downManualFile' escapeXml="false"></portlet:resourceURL>

<style type="text/css">

</style>
<!-- App Name -->
<div class="logo">
	<div class="logo-lg">
		<h3 style="margin-top:10px;" id="<portlet:namespace/>appName"></h3>
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
			<li id="<portlet:namespace/>header-li-simulation">
				<i class="fa fa-cubes fa-2x"></i><br>
				<span class="nav-icon-text">Simulations</span>
			</li>
			<li id="<portlet:namespace/>header-li-edit">
				<i class="fa fa-cogs fa-2x"></i><br>
				<span class="nav-icon-text">Edit</span>
			</li>
			<li class="divider-vertical"></li>
			<li id="<portlet:namespace/>header-li-new">
				<i class="fa fa-plus-square-o fa-2x"></i><br>
				<span class="nav-icon-text">New</span>
			</li>
			<li id="<portlet:namespace/>header-li-save">
				<i class="fa fa-save fa-2x"></i><br>
				<span class="nav-icon-text">Save</span>
			</li>
			<li id="<portlet:namespace/>header-li-copy">
				<i class="fa fa-copy fa-2x"></i><br>
				<span class="nav-icon-text">Copy</span>
			</li>
			<li id="<portlet:namespace/>header-li-delete">
				<i class="fa fa-trash-o fa-2x"></i><br>
				<span class="nav-icon-text">Delete</span>
			</li>
			<li id="<portlet:namespace/>header-li-submit">
				<i class="fa fa-cloud-upload fa-2x"></i><br>
				<span class="nav-icon-text">Submit</span>
			</li>
			<li>
				<i class="fa fa-window-close-o fa-2x"></i><br>
				<span class="nav-icon-text">cancel</span>
			</li>
			<li class="divider-vertical"></li>
			<li id="<portlet:namespace/>header-li-log">
				<i class="fa fa-desktop fa-2x"></i><br>
				<span class="nav-icon-text">System Log</span>
			</li>
			<li id="<portlet:namespace/>header-li-download">
				<i class="fa fa-cloud-download fa-2x"></i><br>
				<span class="nav-icon-text">Download</span>
			</li>
			<li class="divider-vertical <portlet:namespace/>header-manual"></li>
			<li>
				<i class="fa fa-share-square-o fa-2x"></i><br>
				<span class="nav-icon-text">Open Data</span>
			</li>
			<li class="<portlet:namespace/>header-manual" id="<portlet:namespace/>header-li-manual">
				<i class="fa fa-book fa-2x"></i><br>
				<span class="nav-icon-text">Manual</span>
			</li>
		</ul>
	</div>
	<!-- Navbar Right Menu -->
</nav>

<script type="text/javascript">
/***********************************************************************
* Global variables
***********************************************************************/


/***********************************************************************
* Initailization section and handling Liferay events
***********************************************************************/
Liferay.on(OSP.Event.OSP_HANDSHAKE, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet == myId){
		<portlet:namespace/>connector = e.portletId;
		<portlet:namespace/>parentNamespace = "_"+e.portletId+"_";
		
		var events = [
			OSP.Event.OSP_EVENTS_REGISTERED,
			OSP.Event.OSP_RESPONSE_APP_INFO
		];
		
		var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector,
				data: events
			};
		Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
	}
	
});

Liferay.on(OSP.Event.OSP_EVENTS_REGISTERED,function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		var eventData = {
			portletId: myId,
			targetPortlet: e.portletId
		}
		Liferay.fire( OSP.Event.OSP_REQUEST_APP_INFO, eventData );
	}
});

Liferay.on(OSP.Event.OSP_RESPONSE_APP_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		<portlet:namespace/>init(e.data.scienceApp);
	}
});

/***********************************************************************
* Portlet AJAX Function
***********************************************************************/
function <portlet:namespace/>init(scienceApp){
	
	
	$("#<portlet:namespace/>header-li-simulation").click(function(e){
		e.stopPropagation();
		e.preventDefault(); 
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector,
				isCopy : false
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_SIMULATION_MODAL, eventData);
	});
	
	$("#<portlet:namespace/>header-li-edit").click(function(e){
		e.stopPropagation();
		e.preventDefault(); 
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_SIMULATION_EDIT_VIEW, eventData);
	});
	
	
	$("#<portlet:namespace/>header-li-new").click(function(e){
		e.stopPropagation();
		e.preventDefault(); 
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_NEW_JOB_VIEW, eventData);
	});
	
	
	$("#<portlet:namespace/>header-li-save").click(function(e){
		e.stopPropagation();
		e.preventDefault(); 
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
		};
		Liferay.fire(OSP.Event.OSP_SAVE_SIMULATION, eventData);
	});
	
	
	$("#<portlet:namespace/>header-li-delete").click(function(e){
		e.stopPropagation();
		e.preventDefault(); 
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_DELETE_JOB_VIEW, eventData);
	});
	
	$("#<portlet:namespace/>header-li-download").click(function(e){
		e.stopPropagation();
		e.preventDefault(); 
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_JOB_RESULT_VIEW, eventData);
	});
	
	$("#<portlet:namespace/>header-li-copy").click(function(e){
		e.stopPropagation();
		e.preventDefault(); 
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector,
				isCopy : true
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_SIMULATION_MODAL, eventData);
	});
	
	
	$("#<portlet:namespace/>header-li-submit").click(function(){
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
				
		};
		Liferay.fire(OSP.Event.OSP_SUBMIT_JOB, eventData);
	});
	
	
	$("#<portlet:namespace/>header-li-log").click(function(){
		var myId = '<%=portletDisplay.getId()%>';
		var eventData = {
				portletId : myId,
				targetPortlet : <portlet:namespace/>connector
				
		};
		Liferay.fire(OSP.Event.OSP_REQUEST_JOB_LOG_VIEW, eventData);
	});
	
	
	
	<portlet:namespace/>drawAppInfomation(scienceApp);
}
function <portlet:namespace/>drawAppInfomation(data){
	$("#<portlet:namespace/>appName").html(cutStr(data.name(),12)).attr("title",data.name());
	$("#<portlet:namespace/>appVersion").html("Ver "+data.version());
	
	
	if(data.currentManualId()!=0){
		$(".<portlet:namespace/>header-manual").css("display","block");
		$("#<portlet:namespace/>header-li-manual").bind("click",function(){
			<portlet:namespace/>manualDownLoad(data.currentManualId());
		});
	}else{
		$(".<portlet:namespace/>header-manual").css("display","none");
	}
}


function <portlet:namespace/>manualDownLoad(manualId){
	window.location.href="<%=downManualFileURL.toString()%>&<portlet:namespace/>fileEntryId="+manualId;
}
</script>


