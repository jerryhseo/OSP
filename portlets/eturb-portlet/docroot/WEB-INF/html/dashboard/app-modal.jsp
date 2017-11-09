<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<liferay-portlet:resourceURL var="fileExportURL" id="fileExport" escapeXml="false" copyCurrentRenderParameters="false" />
<!-- <liferay-portlet:resourceURL var="getWorkbenchAppListURL" id="getWorkbenchAppList" escapeXml="false" copyCurrentRenderParameters="false" /> -->

<%-- <liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="Workbench_WAR_OSPWorkbenchportlet">
    <liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP"/>
    <liferay-portlet:param name="classId" value="${classId}"/>
    <liferay-portlet:param name="customId" value="${customId}"/>
    <liferay-portlet:param name="jobUuid" value="0"/>
    <liferay-portlet:param name="testYn" value="false"/>
</liferay-portlet:renderURL> --%>

<style>
#<portlet:namespace/>app-export-modal .modal-geo{
    height: 400px;
}

#<portlet:namespace/>app-export-modal .modal-mesh{
    height: 110px;
}

#<portlet:namespace/>app-export-modal .modal-mesh .selectpicker{
    margin-left: 10px;
}

#<portlet:namespace/>app-export-modal .modal-boundary{
    height: 280px;
}

#<portlet:namespace/>app-export-modal .boundary_conditions_div{
    height: 220px; 
    overflow: auto;
}

#<portlet:namespace/>app-export-modal .interval{
    height: 10px;
}

.list-group-item{
    border:1px solid #ddd;
    width : 300px;
    padding: 10px 0px 0px 10px;
}

#<portlet:namespace/>app-export-modal .title{
    padding-left: 10px;
}

#<portlet:namespace/>app-export-modal .title span{
    font-size: 14px;
}

</style>

<div class="modal fade" id="<portlet:namespace/>app-export-modal" role="dialog" style="display: none;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">EXPORT</h4>
            </div>
            <div class="modal-body">
                
                <div class="modal-geo" style="float: left; width: 49%;">
                    <div class="title geo">
                        <i class="icon-folder-close-alt"></i> <span></span>
                    </div>
                    
                    <div class="interval"></div>
                    
                    <div class="span5 panel panel-default panel-airfoil" style="padding-top:15px; padding-bottom:15px; width:90%; height:350px; overflow: auto;">  <!-- Scroll -->
                        <ul class="list-group">
                        </ul>
                    </div>
                </div>
                
                <div class="moda-body-right" style="float: left; width: 49%;">
                    <div class="modal-mesh">
                        <div class="title mesh">
                            <i class="icon-folder-close-alt"></i> <span></span>
                        </div>
                        
                        <div class="interval"></div>
                        
                        <select class="selectpicker">
                        </select>
                    </div>
                    
                    <div class="modal-boundary">
                        <div class="title boundary">
                            <i class="icon-folder-close-alt"></i> <span></span>
                        </div>
                        
                        <div class="interval"></div>
                        
                        <div class="span5 panel panel-default panel-airfoil" style="padding-top:15px; padding-bottom:15px; width:90%; height:240px; overflow: auto;">
                            <ul class="list-group">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="exportBtn" onclick="<portlet:namespace/>operExport();" title="EXPORT">
                    <i class='icon-large icon-share'> Export</i>
                </button>
            </div>
        </div>
    </div>
</div>

<%-- <div class="modal fade" id="<portlet:namespace/>appListModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">App List</h4>
            </div>
            <div class="modal-body">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="saveBtn" onclick="" title="NEW">
                    <i class='icon-large icon-share'> <liferay-ui:message key='choice'/></i>
                </button>
            </div>
        </div>
    </div>
</div> --%>
        
<script>

function <portlet:namespace/>exportApp(){
    $("#<portlet:namespace/>app-export-modal").modal("toggle")
    .css({
            "width":  "850px",
            "margin-left": function () {
                return -($(this).width() / 2);
            }
    });
    
    var geoNode = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.GEOMETRIES_PARENT_FOLDER_ID, false);
    $(".title.geo span").text(geoNode.text);
    <portlet:namespace/>makeNodesItem(geoNode, "geo");
    
    var meshNode = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.MESHES_PARENT_FOLDER_ID, false);
    $(".title.mesh span").text(meshNode.text);
    <portlet:namespace/>makeNodesItem(meshNode, "mesh");
    
    var boundaryNode = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.BOUNDARY_PARENT_FOLDER_ID,false);
    $(".title.boundary span").text(boundaryNode.text);
    <portlet:namespace/>makeNodesItem(boundaryNode, "boundary");
    
    
}

function <portlet:namespace/>getNodes(paramNode){
    var nodes = [];
    $.each(paramNode.children, function (i){
        var node = $("#navigatorTree").jstree(true).get_node(this, false);
        if(typeof node.text === "string" ){
            nodes.push(node);
        }
    });
    return nodes;
}

function <portlet:namespace/>makeNodesItem(paramNode, nodeType){
    <portlet:namespace/>clearNodesItem(nodeType)
    
    var nodes = <portlet:namespace/>getNodes(paramNode);
    
    if(nodeType == "mesh"){
        var nodesListGroup = $(".modal-" + nodeType + " .selectpicker");
        $("<option/>").text("<liferay-ui:message key='edison-simulation-execute-user-define-select-your-item'/>").appendTo(nodesListGroup);
        $.each(nodes, function(i){
            $("<option/>").attr("name", nodeType+"_file").attr("fileId", nodes[i].data[DASH.Constants.DATA_FILE_ID]).text(nodes[i].text).appendTo(nodesListGroup);
        });
    } else {
	    var nodesListGroup = $(".modal-" + nodeType + " .list-group");
	    $.each(nodes, function(i){
	        var nodeItem = $("<li/>").attr("class", "list-group-item");
	        var label = $("<label/>");
	        var fileId = "";
	        
	        $input = $("<input/>").attr("type","checkbox")
	                              .attr("name",nodeType+"_file")
	                              .attr("name",nodeType+"_file")
	                              .css("margin-right","10px")
	                              .css("margin-top","0px")
	                              .appendTo(label);
	        
	        if(nodeType == "geo"){
	            $input.attr("fileId",nodes[i].data[DASH.Constants.DATA_FILE_ID]);
	            $input.attr("fileName",nodes[i].text);
	        } else if(nodeType == "boundary"){
	            if(typeof nodes[i].data["partObject"] == "undefined"){
	                return true;
	            }
	            
	            $input.attr("nodeId",nodes[i].id);
	        }
	        
	        $("<i/>").addClass("icon-folder-close-alt").text("  " + nodes[i].text).appendTo(label);
	        
	        label.appendTo(nodeItem);
	        nodeItem.appendTo(nodesListGroup);
	    });
    }
}

function <portlet:namespace/>clearNodesItem(nodeType){
    if(nodeType == "mesh"){
        $(".modal-" + nodeType + " .selectpicker").html('');
    } else {
        $(".modal-" + nodeType + " .list-group").html('');
    }
}

function <portlet:namespace/>operExport(){
    /* mesh Data에 대한 Validation Check */
    if(!<portlet:namespace/>selectMeshFileCheck()){
        return;
    }
    
    var obj = new Object();
    
    /* 선택된 파일(geo, mesh, boundary) ID 가져오기 */
    var fileIdArray = [];
    var fileNameArray = [];
    $("input[name=geo_file]:checked").each(function(){
        fileIdArray.push($(this).attr("fileId"));
        fileNameArray.push($(this).attr("fileName"));
    });
    
    $meshFileOption = $("option[name=mesh_file]:selected");
    var meshFileId = $meshFileOption.attr("fileId")
    fileIdArray.push(meshFileId);
    var meshFileName = $meshFileOption.text();
    fileNameArray.push(meshFileName);
    
    var boundaryArray = [];
    $("input[name=boundary_file]:checked").each(function(){
        var bcData = $("#navigatorTree").jstree(true).get_node($(this).attr("nodeId"),false).data;
        boundaryArray.push(JSON.stringify(bcData));
    });
    
    var sendData = {
        "<portlet:namespace/>fileIds" : fileIdArray,
        "<portlet:namespace/>fileNames" : fileNameArray,
        "<portlet:namespace/>bcData" : boundaryArray
        };
    
    $("#<portlet:namespace/>app-export-modal .close").click();
    bStart();
    
    <portlet:namespace/>updateProject(false,"none");
    
    $.ajax({
        type: "POST",
        data : sendData,
        url: "<%= fileExportURL %>",
        traditional : true,
        success : function(data) {
            bEnd();
            var fileName = data.fileName;
            <portlet:namespace/>confirmMoveWorkbench(fileName, meshFileName, meshFileId);
        }, //end Success
        error : function(data) {
            $.alert({
                title: 'Alert!',
                content: '파일 생성에 실패하였습니다.',
                boxWidth: '30%',
                useBootstrap: false
            });
        } // end Error
    }); //end ajax
}

function <portlet:namespace/>confirmMoveWorkbench(fileName, meshFileName, meshFileId){
    $.confirm({
        boxWidth: '30%',
        useBootstrap: false,
        title: 'Confirm!',
        content: fileName + ' 파일이 생성되었습니다.'+ '<br/>' +'워크벤치로 이동하시겠습니까?',
        buttons: {
            confirm: function () {
                // workbench app 선택하는 dialog 띄우기
                <portlet:namespace/>oepnMoveWorkbench(meshFileName, meshFileId);
            },
            cancel: function () {
                
            }
        }
    });
}

function <portlet:namespace/>oepnMoveWorkbench(meshFileName, meshFileId){
    var appList = DASH.meshApp(meshFileName);
    
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
                $("<li/>").addClass("airfoil").attr("appId", appIdList[i])
                          .attr("onclick", "<portlet:namespace/>moveWorkBench(\'"+appIdList[i]+"\',\'"+meshFileId+"\')").text(appNames[i])
                          .css("height", "25px").css("padding-top", "5px").css("cursor", "pointer").appendTo(ul);
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
    
    $("#<portlet:namespace/>appListModal").modal("show");
}

function <portlet:namespace/>selectMeshFileCheck(){
    var meshFileId = $("option[name=mesh_file]:selected").attr("fileId");
    if(meshFileId == null || meshFileId =="" || meshFileId == "undefined"){
        $.alert({
            title: 'Alert!',
            content: 'Meshes File을 선택해주세요.',
            boxWidth: '30%',
            useBootstrap: false
        });
        return false;
    }
    
    return true;
}

<%-- function <portlet:namespace/>moveWorkBench(targetScienceAppId, meshFileId) {
    var URL = "<%=workbenchURL%>";
    URL += "&_Workbench_WAR_OSPWorkbenchportlet_scienceAppId="+targetScienceAppId;
    URL += "&_Workbench_WAR_OSPWorkbenchportlet_meshFileId="+meshFileId;
    
    location.href= URL;
} --%>

</script>