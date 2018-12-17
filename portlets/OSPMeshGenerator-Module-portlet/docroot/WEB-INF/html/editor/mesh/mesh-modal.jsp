<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>


<link type="text/css" rel="stylesheet" href="${contextPath}/css/editor/blade/mesh-modal.css" media="screen"/>
<liferay-portlet:resourceURL var="saveMeshURL" id="saveMesh" copyCurrentRenderParameters="false" escapeXml="false">

</liferay-portlet:resourceURL>

<style type="text/css">
	#<portlet:namespace/>create-mesh-modal .osp-editor .header{
		display: none;
	}
</style>


<div class="modal fade" id="<portlet:namespace/>create-mesh-modal" role="dialog" style="display: none;">
	<div class="modal-dialog" style="width: 80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Create Mesh</h4>
			</div>
			<div class="modal-body" style="height: 650px;">
				<div class="form-group">
					<label for="<portlet:namespace/>datFileSelect">Dat File Select</label>
					<select id="<portlet:namespace/>datFileSelect" class="form-control">
						
					</select>
				</div>
				<div class="container col-md-12">
					<div class="form-group">
						<div class="col-md-12">
							<label>SDE</label>
							<liferay-portlet:runtime portletName="StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_meshparametric" queryString=""/>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" title="create" id="<portlet:namespace/>save-mesh">
					<i class="icon-large icon-save"> <span id="<portlet:namespace/>save-mesh-btn-name">Create</span></i>
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$("#<portlet:namespace/>save-mesh").click(function(e){
		e.preventDefault();
		<portlet:namespace/>getSDEData();
	});
});

function <portlet:namespace/>submitMeshData(sdeData){
	$("#<portlet:namespace/>create-mesh-modal").modal("hide");
	<portlet:namespace/>currentTimeOut = setTimeout(function(){
		var postData = {
		    "<portlet:namespace/>appName" : MESH.Constants.getMakeMeshApp('${type}'),
		    "<portlet:namespace/>appVersion" : MESH.Constants.MAKE_MESH_AERO_2D_VERSION,
		    "<portlet:namespace/>datData" : $("#<portlet:namespace/>datFileSelect").val(),
		    "<portlet:namespace/>sdeData" : sdeData,
		    "<portlet:namespace/>projectId" : <portlet:namespace/>projectId
		};
		$.ajax({
			url : '${saveMeshURL}',
			type : 'POST',
			dataType : 'json',
			data : postData,
			success : function(meshOutput){
				if(meshOutput.isComplete){
					<portlet:namespace/>addMeshDataNode(meshOutput.analyzerJob,meshOutput.fileId,meshOutput.fileName);
					<portlet:namespace/>updateProject();
				}else{
					alert("Mesh program can not be executed.");
				}
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
				    alert("submitMeshData-->"+textStatus+": "+jqXHR.responseText);
				}else{
				    alert("submitMeshData-->"+textStatus+": "+errorThrown);
				}  
			},complete : function() {
				if(<portlet:namespace/>currentTimeOut){
					clearTimeout(<portlet:namespace/>currentTimeOut);
				}
				bEnd();
			}
		});
	}, 2000);
}

function <portlet:namespace/>getSDEData(){
	if(!$("#<portlet:namespace/>datFileSelect > option:selected").val()){
		alert(Liferay.Language.get('edison-this-field-is-required',['Dat File Select']));
		$("#<portlet:namespace/>datFileSelect").focus();
		return;
	}
	
	var eventData = {
		targetPortlet: 'StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_meshparametric'
	};
	
	bStart();
	<portlet:namespace/>currentTimeOut = setTimeout(function(){
		Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData );
	},1000);
}


function <portlet:namespace/>createMesh(){
	<portlet:namespace/>resetAirfoilForm();
	$("#<portlet:namespace/>create-mesh-modal").modal("toggle");
}

function <portlet:namespace/>addMeshDataNode(analyzerJob,fileId,fileName){
	if($("#<portlet:namespace/>navigatorTree").jstree(true).get_node(analyzerJob.analyzerUuid, false)){
		var prevNode = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node(analyzerJob.analyzerUuid, false);
		var outputData = new OSP.InputData(analyzerJob.outputData);
		prevNode.data.analyzerJob = analyzerJob;
	}else{
		var parentNodeId = "<portlet:namespace/>" + MESH.Constants.MESHES_PARENT_FOLDER_ID;
		var outputData = new OSP.InputData(analyzerJob.outputData);
		var nodeData = new MESH.data();
		nodeData.executeId(analyzerJob.analyzerUuid);
		nodeData.file(fileId);
		nodeData.nodeType(MESH.Constants.TYPE_VIEW_MESH);
		nodeData.analyzerJob = analyzerJob;
		var meshDataNode = {
				"id": analyzerJob.analyzerUuid,
				"text": fileName,
				"type": MESH.Constants.NODE_VIEW_FILE,
				"data": nodeData
		};
		
		$("#<portlet:namespace/>navigatorTree").jstree(true).create_node(parentNodeId, meshDataNode, "last");
		var fileArray = new Array();
		var fileObject = new Object();
		fileObject.fileId = fileId;
		fileObject.name = fileName;
		fileArray.push(fileObject);
		<portlet:namespace/>callMeshAnalyzerAddObject("add.mesh", fileArray);
	}
}

function <portlet:namespace/>resetAirfoilForm(){
	$("#<portlet:namespace/>datFileSelect  option").remove();
	
	var geoNode = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node("<portlet:namespace/>" + MESH.Constants.GEOMETRIES_PARENT_FOLDER_ID, false);
	$.each(geoNode.children, function (i){
		var node = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node(this, false);
		if(node.data[MESH.Constants.DATA_NODE_TYPE]==MESH.Constants.TYPE_VIEW_DAT){
			$("<option/>").val(node.data[MESH.Constants.DATA_FILE_ID]).html(node.text).appendTo("#<portlet:namespace/>datFileSelect");
		}
	});
}
</script>
