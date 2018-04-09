<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>


<liferay-portlet:resourceURL var="getProjectListURL" id="getProjectList" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="updateProjectURL" id="updateProject" copyCurrentRenderParameters="false" escapeXml="false">
	<portlet:param name="projectId" value="${project.projectId}"/>
</liferay-portlet:resourceURL>
<liferay-portlet:resourceURL var="removeSimulationWithPathURL" id="removeSimulationWithPath" copyCurrentRenderParameters="false" escapeXml="false">
	<portlet:param name="projectId" value="${project.projectId}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="getWorkbenchAppListURL" id="getWorkbenchAppList" escapeXml="false" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet_INSTANCE_pEhDhPfYftLJ">
    <liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP"/>
</liferay-portlet:renderURL>


<portlet:actionURL var="submitURL" copyCurrentRenderParameters="false" name="projectAction">
	<portlet:param name="projectId" value="${project.projectId}"/>
</portlet:actionURL>

<liferay-portlet:renderURL var="searchURL" copyCurrentRenderParameters="false"/>

	
<style type="text/css">
.project-active {
	background-color: #ddd;
}
.project-inactive {
	background-color: red;
}

.aui .long_field{
	width: 350px;
}
	
.eturb-editor-dashboard .controllpanel {
	height: 35px;
	/*border-bottom: 2px solid #3777AF;*/
	border-bottom: 1px solid #d3d3d3;
	position: absolute;
	width: 99.5%;
	display: none;
	z-index: 10;
}

.eturb-editor-dashboard .controllpanel ul{
	list-style: none;
	float: right;
	margin: 0px;
	width: 100%;
	display: table;
}

.eturb-editor-dashboard .controllpanel ul li{
	display: table-cell;
	text-align: center;
	vertical-align: middle;
	height: 35px;
	background-color: #FFFFFF;
}

.eturb-editor-dashboard .mflefttree{
	padding-top: 35px;
/* 	font-size: 12px; */
}
i .test{
	font-family: Arial, Nanum Barun Gothic, NanumGothic;
}
</style>
<script type="text/javascript">
var local = true;
var initRootData = null;
<c:choose>
	<c:when test="${bcUse eq true}">
		initRootData = [
			{
				"id":"<portlet:namespace/>GEO",
				"text":"Geometries",
				"type":"root-geo",
				"data":{"NodeType_":"root-geo"}
			},
			{
				"id":"<portlet:namespace/>MESH",
				"type":"root-mesh",
				"text":"Meshes",
				"data":{"NodeType_":"root-mesh"}
			},
			{
				"id":"<portlet:namespace/>BOUNDARY",
				"type":"root-boundary",
				"text":"Boundary Conditions",
				"data":{"NodeType_":"root-boundary"}
			}
		];
	</c:when>
	<c:otherwise>
		initRootData = [
			{
				"id":"<portlet:namespace/>GEO",
				"text":"Geometries",
				"type":"root-geo",
				"data":{"NodeType_":"root-geo"}
			},
			{
				"id":"<portlet:namespace/>MESH",
				"type":"root-mesh",
				"text":"Meshes",
				"data":{"NodeType_":"root-mesh"}
			}
		];
	</c:otherwise>
</c:choose>

var initDisplayData = {
	"camera" : {
		"position" : {
			"z" : 0,
			"y" : 0,
			"x" : 0
		}
	}
};

function <portlet:namespace/>setNavigatorInitAttr(initState) {
	if (initState) {
		$("#<portlet:namespace/>dashboard-content").attr('style', "height:630px;overflow-y:auto;");	
	} else {
		$("#<portlet:namespace/>dashboard-content").attr('style', "height:279px;overflow-y:auto;");
	}
}

function <portlet:namespace/>navigatorInitJstree(){
	var rootData;
	if("${project.projectId}"!=""){
		rootData = new Object(${project.projectStructure});
	}else{
		rootData = initRootData;
	}
// 	 ,"wholerow","state"

	$("#navigatorTree").jstree({
		"core" : {
			"data": rootData,
			"themes" : {
				"name" : "proton",
				"responsive" : true
			},
			"check_callback" : true
		},
		"types" : {
			"root" : {
			},
			"root-geo" : {
			},
			"root-mesh" : {
			},
			"root-boundary" : {
			},
			"view-icon" : {
				"icon" : "icon-eye-open <portlet:namespace/>node-click-icon"
			},
			"un-view-icon" : {
				"icon" : "icon-eye-close <portlet:namespace/>node-click-icon"
			},
			"code-icon" : {
				"icon" : "icon-code"
			}
		},
		"state" : { 
			"key" : "checklist_tree_state" 
		},
		"progressive_render" : true,
		"plugins" : ["types","json_data"]
	}).bind("loaded.jstree", function(event, data) {
		$("#navigatorTree").jstree('open_all');
		
		var geoNode = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.GEOMETRIES_PARENT_FOLDER_ID,false);
		var geometryGroup = <portlet:namespace/>returnFileObject(geoNode.children);
		
		var meshNode = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.MESHES_PARENT_FOLDER_ID,false);
		var meshGroup = <portlet:namespace/>returnFileObject(meshNode.children);
		
		
		<portlet:namespace/>callETurbAnalyzerLoadProject(geometryGroup,meshGroup);
		
//		Boundary Conditions Draw Table
// 		var boundaryNode = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.BOUNDARY_PARENT_FOLDER_ID,false);
		<portlet:namespace/>surfaceDraw();
	}).bind("open_node.jstree", function(event, data) {
	}).bind("select_node.jstree",function(event, data){
		if(typeof data.event!= "undefined"){
			if(data.event.target.tagName === "I"){
				return false;
			}
		}else{
			return false;
		}
		
		console.log(data);
		<portlet:namespace/>selectedNode(data);
	});
	
	if(${openProjectModal}){
		<portlet:namespace/>searchProjectList(1);
	}
	
	Liferay.on('eTurb_Dashboard_call',function(event) {
		var cmd = event.cmd;
		var param = event.param;
		
		console.log('eTurb_Dashboard_call');
		console.log(cmd);
		console.log(param);
		
		if (cmd == "get.display") {
			<portlet:namespace/>updateProject(true,param);
		}else if(cmd == "get.bcdata"){
			
			var node_id = param.id;
			var node = $("#navigatorTree").jstree(true).get_node(node_id,false);
			var data = new DASH.data(param.data);
			data.bcfunc(node.data[DASH.Constants.DATA_BC]);
			data.nodeType(node.data[DASH.Constants.DATA_NODE_TYPE]);
			node.data = OSP.Util.toJSON(data);
			
			<portlet:namespace/>surfaceDraw();
			bEnd();
		}else if(cmd == "block.end"){
			bEnd();
		}
	});
	
	

	$("#<portlet:namespace/>dashboard-content").on("click","i.<portlet:namespace/>node-click-icon", function(){
		var tree = $("#navigatorTree").jstree(true);
		var selectNode = $("#navigatorTree").jstree("get_selected");
		
		var node = tree.get_node(selectNode)
		var node_data_type = node.data[DASH.Constants.DATA_NODE_TYPE];
		
		var visible;
		if($(this).hasClass("icon-eye-open")){
			visible = false;
		}else{
			visible = true;
		}
		
		var command = "";
		if(node_data_type==DASH.Constants.TYPE_VIEW_DAT||node_data_type==DASH.Constants.TYPE_VIEW_FILE){
			command = visible?"show.geometry":"hide.geometry";
		}else if(node_data_type==DASH.Constants.TYPE_VIEW_MESH){
			command = visible?"show.mesh":"hide.mesh";
		}else if(node_data_type==DASH.Constants.TYPE_VIEW_SURFACE){
			command = visible?"show.entity":"hide.entity";
		}
		
		/*contrix lab event - START*/
		if(node_data_type==DASH.Constants.TYPE_VIEW_SURFACE){
			console.log("command-->"+command+"data-->"+JSON.stringify(node.data));
			if(typeof node.data["partObject"] !== "undefined"){
				Liferay.fire( 'eTurb_Analyzer_call', {
					cmd: command,
					param: {
						"command" : command,
						"data": JSON.stringify(node.data)
					}
				});
			}else{
				alert("해당 NODE의 BC가 존재하지 않습니다.");
				return false;
			}
		}else{
			Liferay.fire( 'eTurb_Analyzer_call', {
					cmd: command,
					param: {
						"command" : command,
						"name": node.text,
						"url": "",
						"fileId": node.data[DASH.Constants.DATA_FILE_ID]
					}
				});
		}
		/*contrix lab event - END*/
		
		if(visible){
			$(this).addClass("icon-eye-open");
			$(this).removeClass("icon-eye-close");
			node.type = DASH.Constants.NODE_VIEW_FILE;
			node.icon = "icon-eye-open <portlet:namespace/>node-click-icon";
		}else{
			$(this).addClass("icon-eye-close");
			$(this).removeClass("icon-eye-open");
			node.type = DASH.Constants.NODE_UNVIEW_FILE;
			node.icon = "icon-eye-close <portlet:namespace/>node-click-icon";
		}
	});
}

function <portlet:namespace/>returnFileObject(nodes){
	var fileArray = new Array();
	if (!nodes || nodes.length == 0){
		var fileObject = new Object();
		fileObject.entity = [];
		fileObject.name = [];
		fileObject.url = [];
		fileArray.push(fileObject);
		return JSON.stringify(fileArray);
	}else{
		for (var i = 0, x = nodes.length; i < x; i++) {
			var fileObject = new Object();
			var treeData = $("#navigatorTree").jstree(true).get_node(nodes[i],false);
			if(treeData.type==DASH.Constants.NODE_UNVIEW_FILE){
				fileObject.visible = false;
			}else{
				fileObject.visible = true;
			}
			fileObject.fileId = treeData.data[DASH.Constants.DATA_FILE_ID];
			fileObject.name = treeData.text;
			fileArray.push(fileObject);
		}
		return JSON.stringify(fileArray);
	}
	
}


/* Tree event Function - START*/
function <portlet:namespace/>addNode(parentId, node){
	$("#navigatorTree").jstree().create_node(parentId,OSP.Util.toJSON(node),"last",false);
	$("#navigatorTree").jstree("open_node", parentId);
	
	if(parentId==="<portlet:namespace/>"+DASH.Constants.BOUNDARY_PARENT_FOLDER_ID){
		<portlet:namespace/>surfaceDraw();
	}
}

function <portlet:namespace/>removeNode(){
	$.confirm({
		boxWidth: '30%',
		useBootstrap: false,
		title: 'Confirm!',
		content: '해당 파일을 삭제 하시겠습니까?',
		buttons: {
			confirm: function () {
				
				try{
					var tree = $("#navigatorTree").jstree(true);
					
					var selectNode = $("#navigatorTree").jstree("get_selected");
					var node = tree.get_node(selectNode);
					var node_data_type = node.data[DASH.Constants.DATA_NODE_TYPE];
					
					
					if(node_data_type===DASH.Constants.TYPE_VIEW_SURFACE){
						<portlet:namespace/>surfaceDraw();
					}else{
						var command = "";
						if(node_data_type===DASH.Constants.TYPE_VIEW_FILE){
							command = "remove.geometry";
						}else if(node_data_type===DASH.Constants.TYPE_VIEW_DAT){
							command = "remove.geometry";
							if(tree.is_parent(node)){
						    	var executeId = node.children[0];
								<portlet:namespace/>removeSimulation(executeId);
						    }
						}else if(node_data_type===DASH.Constants.TYPE_VIEW_MESH){
							command = "remove.mesh";
						}
						
						var fileArray = new Array();
						fileArray.push({
							'fileId': node.data[DASH.Constants.DATA_FILE_ID],
							'name': node.text
						});
						
						<portlet:namespace/>callETurbAnalyzerAddObject(command,fileArray);
						
						$("#<portlet:namespace/>controllpanel").hide();
					}
				}catch(exception){
					
				}finally{
					tree.delete_node(selectNode);
					
					<portlet:namespace/>updateProject(false,"none");
				}
			},
			cancel: function () {
				
			}
		}
	});
}

//meshFile Export click
function <portlet:namespace/>exportNode(){
    
    if("${bcUse}" == "true"){
        <portlet:namespace/>exportApp();
        return;
    }
    
    //var selectNode = $("#navigatorTree").jstree("node").get_node("data");
    var tree = $("#navigatorTree").jstree(true);
    var selectNode = $("#navigatorTree").jstree("get_selected");
    var node = tree.get_node(selectNode);
    
    var data = node.data;
    var meshFileName = node.text;
    var meshFileId = data[DASH.Constants.DATA_FILE_ID];
    var fileExt = "";
    
    // \.개수 추출
    var dotCnt = meshFileName.match(/\./g).length;
    
    if(dotCnt <= 1){
        index = meshFileName.lastIndexOf("\.");
    } else {
        index = n_indexOf(meshFileName, "\.", dotCnt-1);
    }
    
    fileExt = meshFileName.substr(index+1, meshFileName.length);
    <portlet:namespace/>openWorkbenchApp(meshFileName, meshFileId, fileExt);
}

// \.이 여러개인 경우 Index 추출
function n_indexOf(str, searchValue, nth){
    var times=0, num=null;
    
    while(times < nth && num !==-1){
        num = str.indexOf(searchValue, num+1);
        times++;
    }
    return num;
}

// show workbench App List
function <portlet:namespace/>openWorkbenchApp(meshFileName, meshFileId, fileExt){
    var appList = DASH.meshApp(fileExt);
    
    var sendData = {
        "<portlet:namespace/>appList" : appList
        };
    
    $.ajax({
        type: "POST",
        data : sendData,
        url: "<%= getWorkbenchAppListURL %>",
        traditional : true,
        success : function(data) {
            var appIdList = data.appIdList;
            var appNames = data.appNames;
            //app id 추출해서 popup(dialog)에 출력
            
            var modalBody = $("#<portlet:namespace/>appListModal .modal-body");
            modalBody.html("");
            var ul = $("<ul/>").addClass("panel-body sortable-ui ui-sortable");
            for(var i=0; i<appIdList.length; i++){
                $li = $("<li/>").addClass("airfoil btn btn-default").attr("appId", appIdList[i])
	                            .attr("onclick", "<portlet:namespace/>moveWorkBench(\'"+appIdList[i]+"\',\'"+meshFileId+"\')")
	                            .css("height", "25px").css("padding-top", "5px").css("cursor", "pointer")
	                            .css("background","none").css("border", "none");
                $("<button/>").addClass("btn btn-default").text(appNames[i]).css("width", "100%").appendTo($li);
                $li.appendTo(ul);
                
            }
            ul.appendTo(modalBody);
            
        }, //end Success
        error : function(data) {
            $.alert({
                title: 'Alert!',
                content: 'Workbench App 호출에 실패하였습니다.',
                boxWidth: '30%',
                useBootstrap: false
            });
        }
    });
    
    /* if('${bcUse}'){
        //$("#<portlet:namespace/>appExportModal").modal({ "backdrop": "static", "keyboard": false });
        $("#<portlet:namespace/>app-export-modal").modal({ "backdrop": "static", "keyboard": false });
    } else {
    } */
    $("#<portlet:namespace/>appListModal").modal({ "backdrop": "static", "keyboard": false });
}

// Workbench로 이동
function <portlet:namespace/>moveWorkBench(targetScienceAppId, meshFileId) {
    var URL = "<%=workbenchURL%>";
    URL += "&_SimulationWorkbench_WAR_OSPWorkbenchportlet_INSTANCE_pEhDhPfYftLJ_scienceAppId="+targetScienceAppId;
//     URL += "&_Workbench_WAR_OSPWorkbenchportlet_meshFileId="+meshFileId;
    
    location.href= URL;
}


function <portlet:namespace/>selectedNode(treeData){
	if(${projectLoad}){
		var node_id = treeData.node.id;
		var node_data_type = treeData.node.data[DASH.Constants.DATA_NODE_TYPE];
		
		/*Controll Panel EVENT - START*/
		var controllPanel = $("#<portlet:namespace/>controllpanel");
		
		var contorllDataId = controllPanel.attr("data-from-id");
		if(contorllDataId===node_id){
			var contorllDataSize = controllPanel.attr("data-size");
			if(contorllDataSize!=0){
				if(controllPanel.is(":visible")==true){
					controllPanel.hide();
				}else{
					controllPanel.slideDown('fast',null);
				}
			}
			
		}else{
			var viewConArr = DASH.Constants.getControllTypes(node_data_type);
			controllPanel.find(".con-button").css("display","none");
			controllPanel.attr("data-from-id",node_id);
			controllPanel.attr("data-size",viewConArr.length);
			if(viewConArr.length!=0){
				for (var i = 0, x = viewConArr.length; i < x; i++) {
					if(viewConArr[i]===DASH.Constants.CON_MESH_VIEW){
						if(typeof treeData.node.data["analyzerJob"]!=="undefined"){
							$("#<portlet:namespace/>con-"+viewConArr[i]).attr("onclick","<portlet:namespace/>showMesh('"+treeData.node.data["analyzerJob"]+"')")
							$("#<portlet:namespace/>con-"+viewConArr[i]).show();
						}
					}else{
						$("#<portlet:namespace/>con-"+viewConArr[i]).show();
					}
				}
				controllPanel.slideDown('fast',null);
			}else{
				controllPanel.hide();
			}
		}
		/*Controll Panel EVENT - END*/
		
		/*Accordion EVENT - START*/
		<portlet:namespace/>viewAccodianVisible(node_data_type);
		/*Accordion EVENT - END*/
		
	    /* parameter event - start*/
	    if(node_data_type == DASH.Constants.TYPE_GEO_PARAMETER
	        && <portlet:namespace/>checkAnalyzerJob){
	        <portlet:namespace/>checkAnalyzerJob(treeData.node.data["analyzerJob"]);
	    }
		
	    if(node_data_type == DASH.Constants.TYPE_GEO_PARAMETER
	        && <portlet:namespace/>setXYPlotterResultPath){
	    	console.log("SETXY_PLOT");
	        <portlet:namespace/>setXYPlotterResultPath(treeData.node.data["analyzerJob"]);
	    }
	    /* parameter event - end*/
		
		/*contrix lab event - START*/
		if(node_data_type==DASH.Constants.TYPE_ROOT_GEO||node_data_type==DASH.Constants.TYPE_ROOT_MESH||node_data_type==DASH.Constants.TYPE_VIEW_SURFACE){
			var command = "";
			if(node_data_type==DASH.Constants.TYPE_ROOT_GEO){
				command = 'select.geometry';
			}else if(node_data_type==DASH.Constants.TYPE_ROOT_MESH){
				command = 'select.mesh';
			}else if(node_data_type==DASH.Constants.TYPE_VIEW_SURFACE){
				command = 'select.entity';
			}
			
			if(node_data_type==DASH.Constants.TYPE_VIEW_SURFACE){
				console.log("command-->"+command+"data-->"+JSON.stringify(treeData.node.data));
				if(typeof treeData.node.data["partObject"] !== "undefined"){
					Liferay.fire( 'eTurb_Analyzer_call', {
						cmd: command,
						param: {
							"command" : command,
							"data": JSON.stringify(treeData.node.data)
						}
					});
				}
				
			}else{
				Liferay.fire( 'eTurb_Analyzer_call', {
						cmd: command,
						param: {
							"command" : command
						}
					});
			}
		}
		/*contrix lab event - END*/
	
	}else{
		$.alert({
			title: 'Alert!',
			content: '프로젝트 생성 후 수행이 가능 합니다.',
			boxWidth: '30%',
			useBootstrap: false
		});
	}
}

function <portlet:namespace/>setXYPlotterResultPath(analyzerJob){
    //var analyzerJob = $.parseJSON(strJson);
    var resultPath = analyzerJob.resultPath;
    Liferay.fire('setXYPlotterResultPath', {
        cmd: "setResultPath",
        param: {
            "resultPath": resultPath
        }
    }); 
}

function <portlet:namespace/>openFile(){
	var nodes = $("#navigatorTree").jstree('get_selected', false);
	var type = nodes[0].replace("<portlet:namespace/>","");
	var fileExt = DASH.Constants.getFileExtension(type);
	
	var selectedNode = $("#navigatorTree").jstree(true).get_node(nodes[0],false);
	
	var fileIdFilter = "";
	for (var i = 0, x = selectedNode.children.length; i < x; i++) {
		var treeData = $("#navigatorTree").jstree(true).get_node(selectedNode.children[i],false);
		var node_data_file_id = treeData.data[DASH.Constants.DATA_FILE_ID];
		if(fileIdFilter==""){
			fileIdFilter = node_data_file_id; 
		}else{
			fileIdFilter += ","+node_data_file_id;
		}
	}
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setWindowState("pop_up");
		portletURL.setPortletId("eturbeditormyfile_WAR_eturbportlet");
		portletURL.setParameter("fileExt",fileExt);
		portletURL.setParameter("fileSearchType",type);
		portletURL.setParameter("fileIdFilter",fileIdFilter);
		
		Liferay.Util.openWindow(
			{
				dialog: {
					width:1024,
					height:800,
					cache: false,
					draggable: false,
					resizable: false,
					modal: true,
					destroyOnClose: true,
					after: {
						render: function(event) {
							$("button.btn.close").on("click", function(e){
								$("body").css('overflow','');
							});
						}
					}
				},
			id: "<portlet:namespace/>fileSearchDialog",
			uri: portletURL.toString(),
			title: "File SEARCH"
			}
		);
	});
}

function <portlet:namespace/>closePanel(){
	$("#<portlet:namespace/>controllpanel").hide();
}

function <portlet:namespace/>addBCGroup(){
	var childrenData = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.BOUNDARY_PARENT_FOLDER_ID,false).children;
	var parentId = "<portlet:namespace/>"+DASH.Constants.BOUNDARY_PARENT_FOLDER_ID;
	
	var data = new DASH.data();
	data.nodeType(DASH.Constants.TYPE_VIEW_SURFACE);
	
	var node = new DASH.node();
	node.addNode('BC_' + (childrenData.length + 1),DASH.Constants.NODE_VIEW_FILE,OSP.Util.toJSON(data));
	
	<portlet:namespace/>addNode(parentId,node);
}
 /* Tree event Function - END*/
function <portlet:namespace/>load(){
	var newData = [{
			"id":"<portlet:namespace/>GEO",
			"text":"Geometries",
			"type":"open",
			"data":{"aaaa":"dddddd"}
		},
		{
			"id":"<portlet:namespace/>MESH",
			"text":"Meshes",
			"type":"open"
		}
	];
	
	$("#navigatorTree").jstree(true).settings.core.data = newData;
	$("#navigatorTree").jstree(true).refresh();
}

/* Analyzer Function - START*/
function <portlet:namespace/>callETurbAnalyzerLoadProject(geometryGroup,meshGroup) {
	var analyzerStructure = ${projectLoad}?JSON.stringify(${project.analyzerStructure}):JSON.stringify(initDisplayData);
	Liferay.fire( 'eTurb_Analyzer_call', {
		cmd: "loadProject",
		param: {
			"command" : "loadProject",
			"geometryGroup": geometryGroup,
			"meshGroup": meshGroup,
			"token": "${vcToken}",
			"sceneInfo":analyzerStructure
		}
	});
}

function <portlet:namespace/>callETurbAnalyzerAddObject(command, object) {
	Liferay.fire( 'eTurb_Analyzer_call', {
		cmd: command,
		param: {
			"command" : command,
			"data": JSON.stringify(object),
			"token": "${vcToken}"
		}
	});
}

function <portlet:namespace/>callETurbAnalyzerOfDisplay() {
	/*
	if(local){
		<portlet:namespace/>updateProject(true,'');
		return false;
	}*/

	Liferay.fire( 'eTurb_Analyzer_call', {
		cmd: "responseSceneInfo",
		param: {
			"command" : "responseSceneInfo"
		}
	});
}

function <portlet:namespace/>callETurbAnalyzerLoadBc() {
	var selectNode = $("#navigatorTree").jstree("get_selected");
	Liferay.fire( 'eTurb_Analyzer_call', {
		cmd: "add.entity",
		param: {
			"command" : "add.entity",
			"data": selectNode
		}
	});
}
/* Analyzer Function - END*/

/*PROJECT Function - START*/
function <portlet:namespace/>projectSearch(projectId){
	var URL = "<%=searchURL%>";
	URL += "&<portlet:namespace/>projectId="+projectId;
	window.location.href = URL;
}

function <portlet:namespace/>projectLoad(){
	console.log('>>>> projectload : select project Id : ' + <portlet:namespace/>selectedProjectId);
	
	var URL = "<%=searchURL%>";
	URL += "&<portlet:namespace/>projectId="+<portlet:namespace/>selectedProjectId;
	window.location.href = URL;
	return false;
}

function <portlet:namespace/>projectAction(mode){
	<portlet:namespace/>frm.<portlet:namespace/>actionMode.value = mode;
	
	if(mode==="<%=Constants.ADD%>"){
		var projectTree = JSON.stringify(initRootData);
		var analyzerStructure = JSON.stringify(initDisplayData);
		<portlet:namespace/>frm.<portlet:namespace/>projectStructure.value = projectTree;
		<portlet:namespace/>frm.<portlet:namespace/>analyzerStructure.value = analyzerStructure;
		
		submitForm(<portlet:namespace/>frm);
	}else{
		$.confirm({
			boxWidth: '30%',
			useBootstrap: false,
			title: 'Confirm!',
			content: '해당 프로젝트를 삭제 하시겠습니까?',
			buttons: {
				confirm: function () {
					<portlet:namespace/>frm.<portlet:namespace/>projectName.value = "DELETE";
					submitForm(<portlet:namespace/>frm);
				},
				cancel: function () {
					
				}
			}
		});
	}
}

function <portlet:namespace/>updateProject(resultAlert,display){
	var v = $('#navigatorTree').jstree(true).get_json('#', {no_a_attr:true,no_li_attr:true,no_state:true})
	var projectStructure = JSON.stringify(v);
	var analyzerStructure = "";
	if(display=="none"){
		analyzerStructure = "";
	}else{
		analyzerStructure = display==""?JSON.stringify(initDisplayData):JSON.stringify(display);
	}
	var updateData = {
			"<portlet:namespace/>projectStructure":projectStructure,
			"<portlet:namespace/>analyzerStructure":analyzerStructure
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=updateProjectURL%>",
			async : false,
			data  : updateData,
			dataType: 'json',
			success: function(data) {
				if(resultAlert){
					$.alert({
						title: 'Alert!',
						content: '저장되었습니다.',
						boxWidth: '30%',
						useBootstrap: false
					});
				}
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
					alert(textStatus+": "+jqXHR.responseText);
				}else{
					alert(textStatus+": "+errorThrown);
				}  
			}
		});
}

function <portlet:namespace/>projectNameOpen(confirmOpen){
	if(confirmOpen){
		$.confirm({
			boxWidth: '30%',
			useBootstrap: false,
			title: 'Confirm!',
			content: '저장되지 않은 데이터는 복구되지 않습니다. 계속하시겠습니까?',
			buttons: {
				confirm: function () {
					$("#<portlet:namespace/>projectNameModal").modal({ "backdrop": "static", "keyboard": false });
				},
				cancel: function () {
					
				}
			}
		});
	}else{
		if(($("#<portlet:namespace/>projectModal").data('bs.modal') || {}).isShown){
			$("#<portlet:namespace/>projectModal").modal("toggle");
		}
		$("#<portlet:namespace/>projectNameModal").modal({ "backdrop": "static", "keyboard": false });
		
	}
}

var <portlet:namespace/>selectedProjectId = null;
function <portlet:namespace/>selectProjectId(projectId) {
	//console.log(<portlet:namespace/>selectedProjectId + ', ' + projectId);
	
	$('#openBtn').removeClass('btn-success');
	$('#openBtn').addClass('btn-info');
	
	$('#projectId_' + <portlet:namespace/>selectedProjectId).removeClass("project-active");
	$('#projectId_' + projectId).addClass('project-active');
	
	<portlet:namespace/>selectedProjectId = projectId;
	return false;
}

function <portlet:namespace/>searchProjectList(p_curPage){
	$tableTBody = $("#<portlet:namespace/>projectModalTbody");
	$tableTBody.empty();
	
	$tablePage = $("#<portlet:namespace/>projectModalPaging");
	$tablePage.empty();
	
	var searchData = {
		"<portlet:namespace/>p_curPage":p_curPage
	};
	
	
	jQuery.ajax({
		type: "POST",
		url: "<%=getProjectListURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.resultList.length;
			if(dataSize!=0){
				$tr = $("<tr/>").appendTo($tableTBody);
				var dataMap = data.resultList;
				for(var i = 0; i <dataSize; i++) {
					$rowResult = $("<tr/>").css("cursor","pointer").attr("id", 'projectId_' + dataMap[i].projectId).attr("onclick","<portlet:namespace/>selectProjectId('" + dataMap[i].projectId+ "')").appendTo($tableTBody);
					//$rowResult = $("<tr/>").css("cursor","pointer").attr("onclick","<portlet:namespace/>projectSearch('"+dataMap[i].projectId+"')").appendTo($tableTBody);
					//$rowResult = $("<tr/>").css("cursor","pointer").appendTo($tableTBody);
					$("<td/>").text(i+1)
					  .css("text-align","center")
					  .appendTo($rowResult);
					$("<td/>").text(dataMap[i].name)
							  .attr('projectId', dataMap[i].projectId)
							  //.css("text-align","center")
							  .css("text-align","left")
							  .appendTo($rowResult);
					$("<td/>").text(dataMap[i].createDate)
							  .css("text-align","center")
							  .appendTo($rowResult);
				}
				
				$tablePage.append(data.pageStr);
			}else{
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "3")
						  .css("text-align","center")
						  .text(Liferay.Language.get('edison-there-are-no-data'))
						  .appendTo($rowResult);
				$tableTBody.append($rowResult);
			}
			
			$("#<portlet:namespace/>projectModal").modal({ "backdrop": "static", "keyboard": false });
			
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}
		}
	});
}
/*PROJECT Function - END*/
function <portlet:namespace/>removeSimulation(executeId){
	jQuery.ajax({
		type: "POST",
		url: "<%=removeSimulationWithPathURL%>",
		async : false,
        data : {
            "<portlet:namespace/>executeId" : executeId
        },
		success: function(data) {
			
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}
		}
	});
}
</script>
<aui:script>
/*File Search  Return Event - START*/
	Liferay.provide(window, "getFileIdAndName", function(fileSearchType,object){
		<portlet:namespace/>closePopup("<portlet:namespace/>fileSearchDialog");
		
		var parentId = "<portlet:namespace/>"+fileSearchType;
		var command = "";
		
		if(fileSearchType===DASH.Constants.GEOMETRIES_PARENT_FOLDER_ID){
			command = "add.geometry";
		}else if(fileSearchType===DASH.Constants.MESHES_PARENT_FOLDER_ID){
			command = "add.mesh";
		}
		
		var fileArray = new Array();
		for (var i = 0, x = object.length; i < x; i++) {
			var fileMap = object[i];
			var data = new DASH.data();
			data.file(fileMap[0]);
			data.nodeType(DASH.Constants.getTypeByFileName(fileMap[1]));
			var node = new DASH.node();
			node.addNode(fileMap[1], DASH.Constants.NODE_VIEW_FILE, OSP.Util.toJSON(data));
			
			<portlet:namespace/>addNode(parentId,node);
			
			var fileObject = new Object();
			fileObject.fileId = fileMap[0];
			fileObject.name = fileMap[1];
			fileArray.push(fileObject);
		}
		
		<portlet:namespace/>callETurbAnalyzerAddObject(command,fileArray)
		$("body").css('overflow','');
	});
	
	Liferay.provide(window,'<portlet:namespace />closePopup',
		function(popupIdToClose) {
			Liferay.Util.getWindow(popupIdToClose).destroy(); // You can try toggle/hide whatever You want
			},
		['liferay-util-window']
	);
/*File Search Return Event - START*/

</aui:script>
<div id="<portlet:namespace/>controllpanel" class="controllpanel">
	<ul>
		<li class="con-button" id="<portlet:namespace/>con-file" onclick="<portlet:namespace/>openFile();return false;">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-file-text check-icon'> 
					<span class="font">File</span>
				</i>
			</a>
		</li>
		
		<li class="con-button" id="<portlet:namespace/>con-create" onclick="<portlet:namespace/>createMesh(); return false;">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-edit'> 
					<span class="font">Create</span>
				</i>
			</a>
		</li>
		
		<li class="con-button" id="<portlet:namespace/>con-bccreate" onclick="<portlet:namespace/>addBCGroup(); return false;">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-edit'> 
					<span class="font">Group Create</span>
				</i>
			</a>
		</li>
		
		<li class="con-button" id="<portlet:namespace/>con-loadbc" onclick="<portlet:namespace/>callETurbAnalyzerLoadBc();return false;">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-download'>
					<span class="font">Load BC</span> 
				</i>
			</a>
		</li>
		
		<li class="con-button" id="<portlet:namespace/>con-solver">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-external-link'> 
					<span class="font">Solver</span>
				</i>
			</a>
		</li>
		
		<li class="con-button" id="<portlet:namespace/>con-analyzer" onclick="<portlet:namespace/>runAnalyzer();">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-play-circle'> 
					<span class="font">Analyzer</span>
				</i>
			</a>
		</li>
		
		<li class="con-button" id="<portlet:namespace/>con-delete" onclick="<portlet:namespace/>removeNode();">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-trash'>
					<span class="font">Delete</span> 
				</i>
			</a>
		</li>
		
		
		<li class="con-button" id="<portlet:namespace/>con-mesh-view">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-edit'>
					<span class="font">View</span> 
				</i>
			</a>
		</li>
		
		<li class="con-button" id="<portlet:namespace/>con-export" onclick="<portlet:namespace/>exportNode();">
			<a class='btn btn-small' href='#'>
				<i class='icon-large icon-share'>
					<span class="font">Export to Solver</span> 
				</i>
			</a>
		</li>
		
		<li>
			<a class='btn btn-small' href='#' onclick="<portlet:namespace/>closePanel();return false;">
				<i class='icon-large icon-collapse-top'> 
					<span class="font">Close</span>
				</i>
			</a>
		</li>
	</ul>
</div>

<div class="dashboard-content" id="<portlet:namespace/>dashboard-content" style='height:630px;overflow-y:auto;'>
	<div class="btn-group">
		<c:choose>
			<c:when test="${!empty project}">
				<ul style="list-style: none;float: right;padding-top:2px;padding-bottom:2px;width: 100%;display: table;border-bottom: 1px solid #d3d3d3;">
					<li style="display: table-cell;text-align: center;vertical-align: middle;height: 20px;background-color: #fff;">
						<button type="button" class="btn btn-secondary" onclick="<portlet:namespace/>projectNameOpen(true);" title="NEW">
							<!-- <i class='icon-large icon-save'> -->
							<i class="fa fa-file-o" style="font-size:14px;">
								<span class="font" style="margin-left: 4px;font-size:12px;">New</span>
							</i> 
						</button>
						<button type="button" class="btn btn-secondary" onclick="<portlet:namespace/>callETurbAnalyzerOfDisplay();" title="SAVE">
							<i class='icon-large icon-save'> 
								<span class="font" style="font-size: 12px;">Save</span>
							</i>
						</button>
						<button type="button" class="btn btn-secondary" onclick="<portlet:namespace/>searchProjectList(1);" title="LOAD">
							<i class="icon-large icon-cloud-download"> 
								<span class="font" style="font-size: 12px;">Load</span>
							</i>
						</button>
						<button type="button" class="btn btn-secondary" onclick="<portlet:namespace/>projectAction('<%=Constants.DELETE%>');" title="DELETE">
							<i class='icon-large icon-trash'> 
								<span class="font" style="font-size: 12px;">Delete</span>
							</i>
						</button>
<%-- 						<button type="button" class="btn btn-secondary" onclick="<portlet:namespace/>exportApp();" title="EXPORT"> --%>
<!-- 							<i class='icon-large icon-share'>  -->
<!-- 								<span class="font" style="font-size: 12px;">Export</span> -->
<!-- 							</i> -->
<!-- 						</button> -->
					</li>
				</ul>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-secondary" id="saveBtn" onclick="<portlet:namespace/>projectNameOpen(false);" title="NEW">
					<i class='icon-large icon-save'>
						<span class="font">New</span> 
					</i>
				</button>
				
				<button type="button" class="btn btn-primary" id="saveBtn" onclick="<portlet:namespace/>searchProjectList(1);" title="LOAD">
					<i class="icon-large icon-cloud-download"> 
						<span class="font">Load</span>
					</i>
				</button>
			</c:otherwise>
		</c:choose>
	</div>
	<div id="navigatorTree" class="mflefttree">
		
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="<portlet:namespace/>projectModal" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace/>projectModal" style="display: none;">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center" role="document">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">프로젝트 선택</h4>
					</div>
					<div class="modal-body">
						<table class="table table-hover table-project">
							<thead>
								<tr>
									<th></th>
									<th class="TC">name</th>
									<th class="TC">create Date</th>
								</tr>
							</thead>
							<tbody id="<portlet:namespace/>projectModalTbody">
								
							</tbody>
						</table>
						
						<div id="<portlet:namespace/>projectModalPaging" class="paging">
							
						</div>
					</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-success" id="openBtn" onclick="<portlet:namespace/>projectLoad()" title="Open">
							<i class='icon-large icon-save' id="openBtnText"> Open</i>
						</button>
						<button type="button" class="btn btn-success" id="saveBtn" onclick="<portlet:namespace/>projectNameOpen(false);" title="NEW">
							<i class='icon-large icon-save'> New</i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<aui:form name="frm" method="POST" action="<%=submitURL%>">
		<aui:input name="actionMode" value="" type="hidden"/>
		<aui:input name="projectStructure" value="" type="hidden"/>
		<aui:input name="analyzerStructure" value="" type="hidden"/>
		
		<div class="modal fade" id="<portlet:namespace/>projectNameModal" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace/>projectNameModal" style="display: none;">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center" role="document">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">새 프로젝트</h4>
						</div>
						<div class="modal-body">
							<aui:input name="projectName" type="text" cssClass="long_field" label="" value="" maxLength="15">
								<aui:validator name="required"/>
							</aui:input>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-success" id="saveBtn" onclick="<portlet:namespace/>projectAction('<%=Constants.ADD%>');" title="NEW">
								<i class='icon-large icon-save'> New</i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</aui:form>
</div>

<img id="loadingBox" src="${contextPath}/images/processing.gif" width="700px" style="display: none;"/>

<!-- Workbench List 출력 -->
<div class="modal fade" id="<portlet:namespace/>appListModal" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace/>appListModal" style="display: none;">
	<div class="vertical-alignment-helper">
		<div class="modal-dialog vertical-align-center" role="document">
	        <!-- Modal content-->
	        <div class="modal-content table-responsive panel edison-panel">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal">&times;</button>
	                <h4 class="modal-title">Solver Selection</h4>
	            </div>
	            <div class="modal-body table-responsive panel edison-panel">
	                
	            </div>
	        </div>
		</div>
	</div>
</div>