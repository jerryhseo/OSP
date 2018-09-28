<link type="text/css" rel="stylesheet" href="${contextPath}/css/mesh-modal.css" media="screen"/>
<liferay-portlet:resourceURL var="kflowSaveMeshURL" id="kflowSaveMesh" copyCurrentRenderParameters="false" escapeXml="false">
    <portlet:param name="projectId" value="${project.projectId}"/>
</liferay-portlet:resourceURL>

<style type="text/css">
	#<portlet:namespace/>create-mesh-modal{
		width: 50% !important;
		margin: 30px auto !important;
		padding-left: 0px !important;
		overflow-y : hidden !important;
	}
	
	#p_p_id_StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_meshparametric_ .osp-editor .header{
		display: none;
	}
</style>
<aui:form name="mesh-form" method="POST" action="<%=submitURL%>"> 
	<div class="modal fade" id="<portlet:namespace/>create-mesh-modal" role="dialog" style="display: none;">
		<div class="modal-dialog" style="width: 100%;">
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
</aui:form>
<script type="text/javascript">
$(function(){
	$("#<portlet:namespace/>save-mesh").click(function(e){
		e.preventDefault();
		<portlet:namespace/>getSDEData();
	});
});

function <portlet:namespace/>getSDEData(){
	var eventData = {
		targetPortlet: 'StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_meshparametric'
	};
	Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData );
}

Liferay.on(OSP.Event.OSP_RESPONSE_DATA,function(e) {
	if(e.portletId == "StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_meshparametric"){
		var dataType = new OSP.DataType();
    	dataType.deserializeStructure(e.data.context_);
    	var dataStructure = dataType.structure();
		var fileContent = dataStructure.activeParameterFormattedInputs().toString().replace(/,/gi, "");
		
		<portlet:namespace/>submitMeshData(fileContent);
	}
});

function <portlet:namespace/>submitMeshData(sdeData){
	$("#<portlet:namespace/>create-mesh-modal").modal("hide");
	bStart();
	setTimeout(function(){
		var postData = {
	            "<portlet:namespace/>appName" : DASH.Constants.getMakeMeshApp('${site}'),
	            "<portlet:namespace/>appVersion" : DASH.Constants.MAKE_MESH_AERO_2D_VERSION,
	            "<portlet:namespace/>datData" : $("#<portlet:namespace/>datFileSelect").val(),
	            "<portlet:namespace/>sdeData" : sdeData
	        };
		$.ajax({
			url : '${kflowSaveMeshURL}',
	        type : 'POST',
	        dataType : 'json',
	        data : postData,
	        success : function(meshOutput){
	        	if(meshOutput.isComplete){
                    <portlet:namespace/>addMeshDataNode(meshOutput.analyzerJob,meshOutput.fileId,meshOutput.fileName);
                    <portlet:namespace/>updateProject(false, '');
                }else{
                    alert("Mesh program can not be executed.");
                }
	        	bEnd();
	        },error:function(jqXHR, textStatus, errorThrown){
	            bEnd();
	            if(jqXHR.responseText !== ''){
	                alert("submitMeshData-->"+textStatus+": "+jqXHR.responseText);
	            }else{
	                alert("submitMeshData-->"+textStatus+": "+errorThrown);
	            }  
	        }
		});
	}, 2000);
	
}

function <portlet:namespace/>addMeshDataNode(analyzerJob,fileId,fileName){
    if($("#navigatorTree").jstree(true).get_node(analyzerJob.analyzerUuid, false)){
        var prevNode = $("#navigatorTree").jstree(true).get_node(analyzerJob.analyzerUuid, false);
        var outputData = new OSP.InputData(analyzerJob.outputData);
        prevNode.data.analyzerJob = analyzerJob;
    }else{
        var parentNodeId = "<portlet:namespace/>" + DASH.Constants.MESHES_PARENT_FOLDER_ID;
        var outputData = new OSP.InputData(analyzerJob.outputData);
        var nodeData = new DASH.data();
        nodeData.executeId(analyzerJob.analyzerUuid);
        nodeData.file(fileId);
        nodeData.nodeType(DASH.Constants.TYPE_VIEW_MESH);
        nodeData.analyzerJob = analyzerJob;
        var meshDataNode = {
            "id": analyzerJob.analyzerUuid,
            "text": fileName,
            "type": DASH.Constants.NODE_VIEW_FILE,
            "data": nodeData
        };
        $("#navigatorTree").jstree(true).create_node(parentNodeId, meshDataNode, "last");
    }
    
    var fileArray = new Array();
    var fileObject = new Object();
    fileObject.fileId = fileId;
    fileObject.name = fileName;
    fileArray.push(fileObject);
    <portlet:namespace/>callETurbAnalyzerAddObject("add.mesh", fileArray);
}

function <portlet:namespace/>createMesh(){
	
	<portlet:namespace/>resetAirfoilForm();
	
	$("#<portlet:namespace/>create-mesh-modal")
    .modal("toggle")
    .css({
        "width":  "650px",
        "margin-left": function () {
            return -($(this).width() / 2);
        }
    });
}

function <portlet:namespace/>resetAirfoilForm(){
	$("#<portlet:namespace/>datFileSelect  option").remove();
	
	var geoNode = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>" + DASH.Constants.GEOMETRIES_PARENT_FOLDER_ID, false);
	$.each(geoNode.children, function (i){
		var node = $("#navigatorTree").jstree(true).get_node(this, false);
		if(node.data[DASH.Constants.DATA_NODE_TYPE]==DASH.Constants.TYPE_VIEW_DAT){
			$("<option/>").val(node.data[DASH.Constants.DATA_FILE_ID]).html(node.text).appendTo("#<portlet:namespace/>datFileSelect");
		}
	});
}
</script>