<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<liferay-portlet:resourceURL var="prepareAnalyzerUrl" id="prepareAnalyzer" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="checkAnalyzerUrl" id="checkAnalyzer" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="readFileURL" id="readFile" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:resourceURL var="removeRemoteFilePathURL" id="removeRemoteFilePath" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="executeAnalyzerUrl" id="executeAnalyzer" copyCurrentRenderParameters="false" escapeXml="false">
	<portlet:param name="projectId" value="${project.projectId}"/>
</liferay-portlet:resourceURL>

<style type="text/css">
#p_p_id_StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_parametric_ .osp-editor .header{
	display: none;
}
#<portlet:namespace/>progress-bound{margin-top: 10px; margin-left: 13px;}
.progress > .progress-percent{position: absolute; left: 45%; margin-top: 7px;}
</style>



<div class="dashboard-content span12" style="margin-left: 0px !important;height: 304px; overflow-y:auto;">
	<div id="<portlet:namespace/>inputdeck-editor" style="display: none;">
		<div class="h10"></div>
		
		<div id="<portlet:namespace/>inputdeck-editor"  class="btn-group pull-right">
	        <button type="button" class="btn btn-primary" id="saveBtn" onclick="<portlet:namespace/>parameterDraw();" title="DRAW">
	            <i class='icon-large icon-spinner'> Draw</i>
	        </button>
    	</div>
    	
		<div class="h10"></div>
		
		<liferay-portlet:runtime portletName="StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_parametric" queryString=""/>
		
	</div>
    
    <div id="<portlet:namespace/>progress-bound" class="progress progress-striped active span11"  style="display: none;height: 30px;">
      <div class="bar" style="width: 50%;"></div>
      <span id="<portlet:namespace/>progress-percent" class="progress-percent">0%</span>
    </div>
    <div class="h10"></div>
</div>

<script type="text/javascript">
var <portlet:namespace/>progressBarTimer;

function <portlet:namespace/>parameterInitEditor(type,structure,instance){
	var srcData = new OSP.InputData();
	srcData.type(type);
	srcData.context(structure);
	var eventData = {
		targetPortlet: 'StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_'+instance,
		data: OSP.Util.toJSON(srcData)
	};
	Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
}


function <portlet:namespace/>createParameterNode(analyzerJob, geoNode, selectedNode){
    if($("#navigatorTree").jstree(true).is_parent(geoNode)){
        <portlet:namespace/>removeJstreeChildren(geoNode);
    }
    var outputData = new OSP.InputData(analyzerJob.outputData);
    var nodeData = new DASH.data();
    nodeData.executeId(analyzerJob.analyzerUuid);
    nodeData.nodeType(DASH.Constants.TYPE_GEO_PARAMETER);
    nodeData.analyzerJob = analyzerJob;
    var paramNode = {
        "id": analyzerJob.analyzerUuid,
        "text": outputData.name(),
        "type": DASH.Constants.NODE_CODE,
        "data": nodeData
    };
    
    $("#navigatorTree").jstree(true).create_node(geoNode.id, paramNode, "last");
    $("#navigatorTree").jstree(true).open_node(geoNode);
    if(selectedNode){
	    $("#navigatorTree").jstree(true).select_node(analyzerJob.analyzerUuid);
    }
    <portlet:namespace/>updateProject(false, 'none');
}

function <portlet:namespace/>removeJstreeChildren(parentNode){
    var children = $("#navigatorTree").jstree(true).get_children_dom(parentNode);
    $("#navigatorTree").jstree(true).delete_node(children);
}

function <portlet:namespace/>checkAnalyzerJob(analyzerJob){
    if(<portlet:namespace/>progressBarTimer){
        clearTimeout(<portlet:namespace/>progressBarTimer);
    }
    $.ajax({
        url : '${checkAnalyzerUrl}',
        type : 'POST',
        dataType : 'json',
        data : {
            "<portlet:namespace/>analyzerJob" : JSON.stringify(analyzerJob)
        },
        success : function(result){
            if(result){
                <portlet:namespace/>displayProgressBar(result.time);
                if(result.time < 100){
                    <portlet:namespace/>progressBarTimer = 
                        setTimeout(<portlet:namespace/>checkAnalyzerJob, 15000, analyzerJob);
                }else{
                	<portlet:namespace/>parameterInitEditor(OSP.Enumeration.PathType.FILE_CONTENT,result.out,'parametric');
                	clearTimeout(<portlet:namespace/>progressBarTimer);
                }
            }
        },
        error : function(){
            <portlet:namespace/>displayProgressBar(-1);
            if(console){
                console.log('[ERROR] AJAX FAILED during checkAnalyzerJob');
            }
        }
    });
}

function <portlet:namespace/>displayProgressBar(time){
    $("#<portlet:namespace/>inputdeck-editor").hide();
    $("#<portlet:namespace/>progress-bound").hide();
    if(time >= 100){
        $("#<portlet:namespace/>inputdeck-editor").show();
    }
    
    if(time < 100){
        $("#<portlet:namespace/>progress-bound").show();
        $("#<portlet:namespace/>progress-bound > .bar").css("width", time + "%");
        $("#<portlet:namespace/>progress-percent").text(time + " %");
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

function <portlet:namespace/>executeAnalyzer(analyzerJob, inputFileName, fileId, fileContent){
    $.ajax({
        url : '${executeAnalyzerUrl}',
        type : 'POST',
        dataType : 'json',
        data : {
            "<portlet:namespace/>analyzerJob" : JSON.stringify(analyzerJob),
            "<portlet:namespace/>inputFileName" : inputFileName,
            "<portlet:namespace/>fileId" : fileId,
            "<portlet:namespace/>fileContent" : fileContent
        },
        success : function(result){
            if(result.isComplete){
                <portlet:namespace/>setXYPlotterResultPath(analyzerJob);
                <portlet:namespace/>checkAnalyzerJob(analyzerJob);
            }else{
                alert("It did not run properly. Please contact your administrator.");
            }
        },error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert("[ERROR] AJAX FAILED during executeAnalyzer -->"+textStatus+": "+jqXHR.responseText);
			}else{
				alert("[ERROR] AJAX FAILED during executeAnalyzer -->"+textStatus+": "+errorThrown);
			}  
		}
    });
}

function <portlet:namespace/>getSelectedGeometryNode(){
    return  $("#navigatorTree").jstree("get_selected", true)[0];
}

function <portlet:namespace/>prepareAnalyzer(appName, appVersion, geoNode, fileContent, draw){
	setTimeout(function(){
		var geoNodeFileId = geoNode.data[DASH.Constants.DATA_FILE_ID];
	    var analyzerUuid = "";
	    if($("#navigatorTree").jstree(true).is_parent(geoNode)){
	    	analyzerUuid = geoNode.children[0];
	    }
	    $.ajax({
	        url : '${prepareAnalyzerUrl}',
	        type : 'POST',
	        dataType : 'json',
	        data : {
	            "<portlet:namespace/>appName" : appName,
	            "<portlet:namespace/>appVersion" : appVersion,
	            "<portlet:namespace/>fileId" : geoNodeFileId,
	            "<portlet:namespace/>analyzerUuid" : analyzerUuid
	        },
	        success : function(analyzerJob){
        		if(draw){
                	<portlet:namespace/>createParameterNode(analyzerJob, geoNode, true);
                	<portlet:namespace/>executeAnalyzer(analyzerJob, "input.inp", geoNode.data[DASH.Constants.DATA_FILE_ID],fileContent);
//                 	<portlet:namespace/>wait(2000);
//                 	<portlet:namespace/>getReadFile(analyzerJob);
                }else{
                	<portlet:namespace/>createParameterNode(analyzerJob, geoNode, false);
    	            <portlet:namespace/>executeAnalyzer(analyzerJob, geoNode.text, geoNode.data[DASH.Constants.DATA_FILE_ID],'');
                }
	        },
	        error : function(){
	            if(console){
	                console.log('[ERROR] AJAX FAILED during prepareAnalyzer');
	            }
	        }
	    });
	    bEnd();
	},1000);
    
}

function <portlet:namespace/>getReadFile(analyzerJob){
    $.ajax({
        url : '${readFileURL}',
        type : 'POST',
        data : {
            "<portlet:namespace/>analyzerJob" : JSON.stringify(analyzerJob)
        },
        dataType:"text",
        success : function(result){
        	//XY GRID
        	var srcData = new OSP.InputData();
        	srcData.type(OSP.Enumeration.PathType.CONTEXT);
        	srcData.context(result);
        	var eventData = {
        		targetPortlet: 'OneDPlot_WAR_OSPAnalyzersportlet',
        		data: OSP.Util.toJSON(srcData)
        	};
        	Liferay.fire( OSP.Event.OSP_LOAD_DATA, eventData );
        },
        error : function(){
            if(console){
                console.log('[ERROR] AJAX FAILED during checkAnalyzerJob');
            }
        }
    });
}



function <portlet:namespace/>parameterDraw(){
	var eventData = {
		targetPortlet: 'StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_parametric'
	};
	bStart();
	setTimeout(function(){
	    Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData );
	},1000);
		
}

Liferay.on(OSP.Event.OSP_RESPONSE_DATA,function(e) {
	
	if(e.portletId == "StructuredDataEditor_WAR_OSPStructuredDataEditorportlet_INSTANCE_parametric"){
		var tree = $("#navigatorTree").jstree(true);
		var selectNode = $("#navigatorTree").jstree("get_selected");
		var node = tree.get_node(selectNode);
		var node_data_appName = node.data.analyzerJob.appName;
		if(node_data_appName!=DASH.Constants.SHAPE_ANALYSIS_APP){
			$.ajax({
		        url : '${removeRemoteFilePathURL}',
		        type : 'POST',
		        data : {
		            "<portlet:namespace/>analyzerJob" : JSON.stringify(node.data.analyzerJob) 
		        },
		        success : function(analyzerJob){
		        	var parentNode = tree.get_node(node.parent);
		        	var dataType = new OSP.DataType();
		        	dataType.deserializeStructure(e.data.context_);
		        	var dataStructure = dataType.structure();
					var fileContent = dataStructure.activeParameterFormattedInputs().toString().replace(/,/gi, "");
		        	
	// 	        	var fileContent = e.data.data.activeParameterFormattedInputs().toString().replace(/,/gi, "");
		        	<portlet:namespace/>prepareAnalyzer(DASH.Constants.getShapeAnalysisApp('${site}'),DASH.Constants.SHAPE_ANALYSIS_VERSION, parentNode, fileContent,true);
		        },error:function(jqXHR, textStatus, errorThrown){
					if(jqXHR.responseText !== ''){
						alert("[ERROR] AJAX FAILED during removeRemoteFilePath -->"+textStatus+": "+jqXHR.responseText);
					}else{
						alert("[ERROR] AJAX FAILED during removeRemoteFilePath -->"+textStatus+": "+errorThrown);
					}
				}
			});
		}else{
			var parentNode = tree.get_node(node.parent);
			var dataType = new OSP.DataType();
			dataType.deserializeStructure(e.data.context_);
	    	var dataStructure = dataType.structure(); 
	    	var fileContent = dataStructure.activeParameterFormattedInputs().toString().replace(/,/gi, "");
	    	<portlet:namespace/>prepareAnalyzer(DASH.Constants.getShapeAnalysisApp('${site}'),DASH.Constants.SHAPE_ANALYSIS_VERSION, parentNode, fileContent,true);
		}
	}
});

function <portlet:namespace/>runAnalyzer(){
	
	bStart();
	setTimeout(function(){
		var geoNode = <portlet:namespace/>getSelectedGeometryNode();
	    <portlet:namespace/>prepareAnalyzer(DASH.Constants.getShapeAnalysisParamApp('${site}'),DASH.Constants.SHAPE_ANALYSIS_PARAM_VERSION,geoNode,'',false);
	},1000);
	
	/*
	$("#<portlet:namespace/>inputdeck-editor").show();
	<portlet:namespace/>openAccordionParam(true); // test : mesher 실행 성공이라고 가정

	evt.preventDefault();
	return false;
	*/
}
</script>
