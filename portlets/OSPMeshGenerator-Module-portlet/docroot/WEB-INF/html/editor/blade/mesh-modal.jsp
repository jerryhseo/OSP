<%@page import="org.kisti.edison.osp.editor.util.MeshType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<liferay-portlet:resourceURL var="saveMeshURL" id="saveMesh" copyCurrentRenderParameters="false" escapeXml="false">
	
</liferay-portlet:resourceURL>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/editor/blade/mesh-modal.css" media="screen"/>
<style type="text/css">
#<portlet:namespace/>create-mesh-modal .btn-group {float: none; margin-left:10px; margin-bottom: 0px; z-index: auto;display: inline-block;}
#<portlet:namespace/>create-mesh-modal .modal-body { max-height: none !important; }
#<portlet:namespace/>create-mesh-modal .required-message::-webkit-input-placeholder { /* Chrome/Opera/Safari */
  color: red;
}
#<portlet:namespace/>create-mesh-modal .required-message::-moz-placeholder { /* Firefox 19+ */
  color: red;
}
#<portlet:namespace/>create-mesh-modal .required-message:-ms-input-placeholder { /* IE 10+ */
  color: red;
}
#<portlet:namespace/>create-mesh-modal .required-message:-moz-placeholder { /* Firefox 18- */
  color: red;
}
</style>
	<div class="modal fade" id="<portlet:namespace/>create-mesh-modal" role="dialog" style="display: none;">
		<div class="modal-dialog" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Create Mesh</h4>
				</div>
				<div class="modal-body mesh-type one">
					<section class="">
						<div class="span12">
							<section class="row-fluid-test">
								<div class="span8">
									<aui:input name="airfoilsCount" type="number" cssClass="form-control input-small" readonly="true"
									label="Number of Airfoils : " value="" maxLength="15" inlineLabel="left">
									</aui:input>
								</div>
								<div class="span4">
								
								</div>
							</section>
						</div>
					</section>
					<section class="" >
						<div class="span12" style="margin-top: 30px; margin-bottom: 20px; min-height: 220px;">
							<div class="row-fluid">
								<div class="span5 panel panel-default panel-airfoil" style="padding-top: 15px; padding-botom: 15px;">
									<div class="span12">
										<ul id="<portlet:namespace/>mesh-drag-source" class="panel-body sortable-ui">
											
										</ul>
									</div>
								</div>
								<div class="span1" style="min-height: 225px; padding-top: 90px;">
									<i class="icon-arrow-right icon-3x"></i>
								</div>
								<div class="span6 panel panel-primary panel-airfoil" style="margin-left: 15px; width: 45%;">
									<div class="panel-body" style="padding: 0px;">
										<div class="span3" style="padding-top: 15px; padding-botom: 15px; border-right: 1px solid #337ab7; min-height: 225px;">
											<ul class="airfoil-order" id="<portlet:namespace/>mesh-target-ordinal">
												
											</ul>
										</div>
										<div class="span9" style="padding-top: 15px;">
											<ul id="<portlet:namespace/>mesh-drag-target" class="sortable-ui">
											
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
					<section class="mb-10">
						<div class="span2">
							<label for="<portlet:namespace/>staggerAngle"class="control-label">Stagger Angle :</label>
						</div>
						<div id="<portlet:namespace/>staggerAngle-wrapper" class="span10 ml-2 airfoil-req-wrapper">
							<input type="number" name="<portlet:namespace/>staggerAngle" class="form-control airfoil-req" style="width: 100%; display: inline; float: left;">
						</div>
					</section>
					<section class="mb-10">
						<div class="span2">
							<label for="<portlet:namespace/>pitchGap"class="control-label">Pitch Gap :</label>
						</div>
						<div id="<portlet:namespace/>pitchGap-wrapper" class="span10 ml-2 airfoil-req-wrapper">
							<input type="number" name="<portlet:namespace/>pitchGap" class="form-control airfoil-req" style="width: 100%; display: inline; float: left;">
						</div>
					</section>
					<section class="mb-10">
						<div class="span2">
							<label for="<portlet:namespace/>axialGap"class="control-label">Axial Gap :</label>
						</div>
						<div id="<portlet:namespace/>axialGap-wrapper" class="span10 ml-2 airfoil-req-wrapper">
							<aui:input type="number" name="axialGap" style="width: 100%; display: inline; float: left;"
							cssClass="form-control airfoil-req" label=""/>
						</div>
					</section>
				</div>
				<div class="modal-body mesh-type two" style="display: none">
				
				</div>
				<div class="modal-body mesh-type three" style="display: none">
				
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
		if(<portlet:namespace/>isEmptyAirfoliForm()){
			return;
		}
		var airfoils = [];
		$.each($("#<portlet:namespace/>mesh-drag-target > li"), function(i){
			var fileName = $(this).text();
			var nodeData = $(this).data();
			airfoils.push({
				"seq": i,
				"fileName": fileName,
				"parameterFilePath": nodeData.resultPath_
			});
		});
		
		var saveJson = {
			"type": $("#<portlet:namespace/>mesh-type").val(),
			"airfoilsCount": $("#<portlet:namespace/>airfoilsCount").val(),
			"staggerAngle": <portlet:namespace/>getInputArrayValues("<portlet:namespace/>staggerAngle-wrapper"),
			"pitchGap": <portlet:namespace/>getInputArrayValues("<portlet:namespace/>pitchGap-wrapper"),
			"axialGap": <portlet:namespace/>getInputArrayValues("<portlet:namespace/>axialGap-wrapper"),
			"airfoils": airfoils
		};
		
		<portlet:namespace/>submitMeshData(saveJson, $("#<portlet:namespace/>analyzerUuid").val());
	});
	
	function <portlet:namespace/>getInputArrayValues(wrapperId){
		var values = [];
		$("#" + wrapperId).find(".airfoil-req").each(function(){
			values.push($(this).val());
		});
		return values;
	}
	
	$(".sortable-ui").sortable({
		connectWith : "ul",
		tolerance : "pointer",
		receive : function(event, ui){
			var airfoilsCount = $("#<portlet:namespace/>mesh-drag-target > li").length;
			$("#<portlet:namespace/>airfoilsCount").val(airfoilsCount);
			/*count 개수에 따라서 Input 추가하기*/
			if($(event.target).attr("id") == "<portlet:namespace/>mesh-drag-target" && airfoilsCount > 1){
				<portlet:namespace/>addParamInputForm(airfoilsCount);
				<portlet:namespace/>resizeParamInputWidth(airfoilsCount);
			}
			if($(event.target).attr("id") == "<portlet:namespace/>mesh-drag-source"){
				<portlet:namespace/>removeParamInputForm();
				<portlet:namespace/>resizeParamInputWidth(airfoilsCount);
			}
		}
	});
});

function <portlet:namespace/>isEmptyAirfoliForm(){
	var returnValue = false;
	var firstEmptyInput;
	var nodes = $("#<portlet:namespace/>mesh-drag-target > li");
	if(!nodes.length || nodes.length < 1){
		alert("more airfoil");
		returnValue = true;
	}
	$.each($(".airfoil-req"), function(i){
		if(!$(this).val()){
			returnValue = true;
			$(this).focus();
			$(this).blur();
			if(!firstEmptyInput){
				firstEmptyInput = $(this);
			}
			$(this).attr("placeholder", '<liferay-ui:message key="this-field-is-required"/>');
			if(!$(this).hasClass("required-message")){
				$(this).addClass("required-message");
			}
		}else{
			$(this).attr("placeholder", "");
			if($(this).hasClass("required-message")){
				$(this).removeClass("required-message");
			}
		}
	});
	
	if(firstEmptyInput){
		firstEmptyInput.focus();
	}
	return returnValue;
}

function <portlet:namespace/>addParamInputForm(count){
	$("<input/>", {
		type: "number",
		name: "<portlet:namespace/>staggerAngle",
		"class": "form-control airfoil-req added-airfoil"
	}).appendTo($("#<portlet:namespace/>staggerAngle-wrapper"));
	
	$("<input/>", {
		type: "number",
		name: "<portlet:namespace/>pitchGap",
		"class": "form-control airfoil-req added-airfoil"
	}).appendTo($("#<portlet:namespace/>pitchGap-wrapper"));
	
	if(count > 2){
		$("<input/>", {
			type: "number",
			name: "<portlet:namespace/>axialGap",
			"class": "form-control airfoil-req added-airfoil"
		}).appendTo($("#<portlet:namespace/>axialGap-wrapper"));
	}
}

function <portlet:namespace/>removeParamInputForm(){
	$(".airfoil-req-wrapper").each(function(_){
		$(this).find(".added-airfoil").last().remove();
	});
}

function <portlet:namespace/>resizeParamInputWidth(count){
	var width1 = 100 / count;
	var width2 = 100 / ((count - 1) < 0 ? 0 : (count - 1));
	$(".airfoil-req-wrapper").each(function(_){
		var thisWidth = width1 - 3;
		if($(this).attr("id") == "<portlet:namespace/>axialGap-wrapper"){
			thisWidth = width2 - 3;
		}
		$(this).find(".form-control.airfoil-req").each(function(i){
			$(this).css("width", (thisWidth > 100 ? 97 : thisWidth) + "%");
			if(i > 0){
				$(this).css("margin-left", "5px");
			}
		});
	});
}

function <portlet:namespace/>submitMeshData(meshData, analyzerUuid){
	$("#<portlet:namespace/>create-mesh-modal").modal("hide");
	bStart();
	setTimeout(function(){
		var stringMeshData = JSON.stringify(meshData);
		var postData = {
			"<portlet:namespace/>appName" : MESH.Constants.getMakeMeshApp('${type}'),
			"<portlet:namespace/>appVersion" : DASH.Constants.MAKE_MESH_AERO_2D_VERSION,
			"<portlet:namespace/>analyzerUuid" : analyzerUuid,
			"<portlet:namespace/>meshData" : stringMeshData,
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
					<portlet:namespace/>updateProject(false);
				}else{alert("Mesh program can not be executed.");
					
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

function <portlet:namespace/>createMesh(){
	<portlet:namespace/>resetAirfoilForm();
	<portlet:namespace/>addAirfoil(<portlet:namespace/>getDotDatGeoNodes());
	$("#<portlet:namespace/>save-mesh").show();
	$("#<portlet:namespace/>create-mesh-modal").modal("toggle");
}


function <portlet:namespace/>addAirfoil(geoNodes){
    var dummyNode = $("<li/>",{
        "class": "dummy-airfoil",
        "text": " "
    });
    $.each(geoNodes, function(i){
       var geoNode = this;
       var geoChildrenNode = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node(geoNode.children[0]);
       var data = {
               "fileId_": geoNode.data[MESH.Constants.DATA_FILE_ID],
                "resultPath_": geoChildrenNode.data.analyzerJob.resultPath
            };
       
       var nodeLi = $("<li/>",{
           "class": "airfoil",
           "text": geoNode.text
       }).data(data);
       $("#<portlet:namespace/>mesh-drag-source").append(nodeLi);
       $("#<portlet:namespace/>mesh-drag-source-dummy").append(dummyNode.clone());
       <portlet:namespace/>increaseTargetOrdinalNumber(i + 1);
    });
}


function <portlet:namespace/>increaseTargetOrdinalNumber(i){
    $("#<portlet:namespace/>mesh-target-ordinal").append($("<li/>",{
        "text": <portlet:namespace/>getOrdinalSuffixOf(i)
    }));
}

function <portlet:namespace/>getOrdinalSuffixOf(i){
    var j = i % 10, k = i % 100;
    if(j == 1 && k != 11){
        return i + "st";
    }
    if(j == 2 && k != 12){
        return i + "nd";
    }
    if(j == 3 && k != 13){
        return i + "rd";
    }
    return i + "th";
}

function <portlet:namespace/>addMeshDataNode(analyzerJob,fileId,fileName){
	if($("#<portlet:namespace/>navigatorTree").jstree(true).get_node(analyzerJob.analyzerUuid, false)){
		var prevNode = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node(analyzerJob.analyzerUuid, false);
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
		
		$("#<portlet:namespace/>navigatorTree").jstree(true).create_node(parentNodeId, meshDataNode, "last");
		var fileArray = new Array();
		var fileObject = new Object();
		fileObject.fileId = fileId;
		fileObject.name = fileName;
		fileArray.push(fileObject);
// 		<portlet:namespace/>callETurbAnalyzerAddObject("add.mesh", fileArray);
	}
}

function <portlet:namespace/>resetAirfoilForm(){
	$("#<portlet:namespace/>mesh-target-ordinal").empty();
	$("#<portlet:namespace/>mesh-drag-target").empty();
	$("#<portlet:namespace/>mesh-drag-source").empty();
	$("#<portlet:namespace/>mesh-drag-source-dummy").empty();
	$("#<portlet:namespace/>analyzerUuid").val("");
	$("#<portlet:namespace/>airfoilsCount").val(0);
	
	$(".added-airfoil").remove();
	$(".required-message").attr("placeholder", "");
	$(".required-message").removeClass(".required-message");
	<portlet:namespace/>resizeParamInputWidth(1);
	
	$("#<portlet:namespace/>staggerAngle").val("");
	$("#<portlet:namespace/>pitchGap").val("");
	$("#<portlet:namespace/>axialGap").val("");
}

function <portlet:namespace/>getDotDatGeoNodes(){
    var geoNode = $("#<portlet:namespace/>navigatorTree").jstree(true)
    .get_node("<portlet:namespace/>" + MESH.Constants.GEOMETRIES_PARENT_FOLDER_ID, false);

    var geoNodes = [];  
    $.each(geoNode.children, function (i){
        var node = $("#<portlet:namespace/>navigatorTree").jstree(true).get_node(this, false);
        if(node.children.length > 0 && node.data[MESH.Constants.DATA_NODE_TYPE]==MESH.Constants.TYPE_VIEW_DAT){
            geoNodes.push(node);
        }
    });
    return geoNodes;
}
</script>