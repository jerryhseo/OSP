<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>



<style type="text/css">
div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content .controllpanel {
	height: 35px;
	border-bottom: 1px solid #d3d3d3;
	width: 100%;
	display: none;
	margin-top: -9px;
}

div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content .controllpanel ul{
	list-style: none;
	float: right;
	margin: 0px;
	width: 100%;
	display: table;
}

div#_BladeDataEditor_WAR_OSPMeshGeneratorModuleportlet_content .controllpanel ul li{
	display: table-cell;
	text-align: center;
	vertical-align: middle;
	background-color: #FFFFFF;
}
</style>
<div class="panel panel-default blade-full-height">
	<div class="panel-heading blade-panel-heading">
		<i class="icon-chevron-right"></i> Navigator
	</div>
	<div class="panel-body">
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
							<span class="font">Analyze</span>
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
				
				<li>
					<a class='btn btn-small' href='#' onclick="<portlet:namespace/>closePanel();return false;">
						<i class='icon-large icon-collapse-top'> 
							<span class="font">Close</span>
						</i>
					</a>
				</li>
			</ul>
		</div>
		
		<div id="<portlet:namespace/>navigatorTree">
			
		</div>
	</div>
</div>

<script type="text/javascript">
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>INIT_ROOT_DATA = [
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

var <portlet:namespace/>INIT_DISPLAY_DATA = {
		"camera" : {
			"position" : {
				"z" : 0,
				"y" : 0,
				"x" : 0
			}
		}
	};
	
/***********************************************************************
* Portlet Function
***********************************************************************/
function <portlet:namespace/>navigatorInitJstree(){
	var rootData = <portlet:namespace/>INIT_ROOT_DATA;
	
	$("#<portlet:namespace/>navigatorTree").jstree({
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
		$("#<portlet:namespace/>navigatorTree").jstree('open_all');
		
		
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
}

function <portlet:namespace/>searchNavigator(simulationUuid, jobSeqNo){
	
}


function <portlet:namespace/>selectedNode(treeData){
	var node_id = treeData.node.id;
	var node_data_type = treeData.node.data[MESH.Constants.DATA_NODE_TYPE];
	
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
		var viewConArr = MESH.Constants.getControllTypes(node_data_type);
		controllPanel.find(".con-button").css("display","none");
		controllPanel.attr("data-from-id",node_id);
		controllPanel.attr("data-size",viewConArr.length);
		if(viewConArr.length!=0){
			for (var i = 0, x = viewConArr.length; i < x; i++) {
				if(viewConArr[i]===MESH.Constants.CON_MESH_VIEW){
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
	
	
	/* parameter event - start*/
	if(node_data_type == MESH.Constants.TYPE_GEO_PARAMETER
	    && <portlet:namespace/>checkAnalyzerJob){
	    <portlet:namespace/>checkAnalyzerJob(treeData.node.data["analyzerJob"]);
	}
	
	if(node_data_type == MESH.Constants.TYPE_GEO_PARAMETER
	    && <portlet:namespace/>setXYPlotterResultPath){
		console.log("SETXY_PLOT");
	    <portlet:namespace/>setXYPlotterResultPath(treeData.node.data["analyzerJob"]);
	}
	/* parameter event - end*/
}

/***********************************************************************
 * Tree Event section
 ***********************************************************************/
function <portlet:namespace/>addNode(parentId, node){
	$("#navigatorTree").jstree().create_node(parentId,OSP.Util.toJSON(node),"last",false);
	$("#navigatorTree").jstree("open_node", parentId);
}


/***********************************************************************
 * Controll Panel Event section
 ***********************************************************************/
function <portlet:namespace/>openFile(){
	var nodes = $("#<portlet:namespace/>navigatorTree").jstree('get_selected', false);
	var type = nodes[0].replace("<portlet:namespace/>","");
	var fileExt = MESH.Constants.getFileExtension(type);
	
	var selectedNode = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node(nodes[0],false);
	
	var fileIdFilter = "";
	for (var i = 0, x = selectedNode.children.length; i < x; i++) {
		var treeData = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node(selectedNode.children[i],false);
		var node_data_file_id = treeData.data[MESH.Constants.DATA_FILE_ID];
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
		portletURL.setPortletId("edisonfilemanager_WAR_edisonsimulationportlet");
		portletURL.setParameter("fileExt",fileExt);
		portletURL.setParameter("fileSearchType",type);
		portletURL.setParameter("fileIdFilter",fileIdFilter);
		
		Liferay.Util.openWindow(
			{
				dialog: {
					width:1024,
					height:850,
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
</script>
<aui:script>
Liferay.provide(window, "getFileIdAndName", function(fileSearchType,object){
	<portlet:namespace/>closePopup("<portlet:namespace/>fileSearchDialog");
	
	var parentId = "<portlet:namespace/>"+fileSearchType;
	var command = "";
	
	if(fileSearchType===MESH.Constants.GEOMETRIES_PARENT_FOLDER_ID){
		command = "add.geometry";
	}else if(fileSearchType===MESH.Constants.MESHES_PARENT_FOLDER_ID){
		command = "add.mesh";
	}
	
	var fileArray = new Array();
	for (var i = 0, x = object.length; i < x; i++) {
		var fileMap = object[i];
		var data = new MESH.data();
		data.file(fileMap[0]);
		data.nodeType(MESH.Constants.getTypeByFileName(fileMap[1]));
		var node = new MESH.node();
		node.addNode(fileMap[1], MESH.Constants.NODE_VIEW_FILE, OSP.Util.toJSON(data));
		
		<portlet:namespace/>addNode(parentId,node);
		
		var fileObject = new Object();
		fileObject.fileId = fileMap[0];
		fileObject.name = fileMap[1];
		fileArray.push(fileObject);
	}
	
<!-- 	<portlet:namespace/>callETurbAnalyzerAddObject(command,fileArray) -->
	$("body").css('overflow','');
});

Liferay.provide(window,'<portlet:namespace />closePopup',
	function(popupIdToClose) {
		Liferay.Util.getWindow(popupIdToClose).destroy(); // You can try toggle/hide whatever You want
		},
	['liferay-util-window']
);
</aui:script>
