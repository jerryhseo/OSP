<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>


<style type="text/css">
.eturb-editor-dashboard .table-surface td{
	vertical-align: middle;
}
.eturb-editor-dashboard .table-surface tr{
	line-height: 25px;
}
.aui select, .aui input[type="file"] {
	height: 25px;
}
</style>
<script type="text/javascript">
function <portlet:namespace/>surfaceDraw(){
	var boundaryNodes = $("#navigatorTree").jstree(true).get_node("<portlet:namespace/>"+DASH.Constants.BOUNDARY_PARENT_FOLDER_ID,false).children;
	var surfaceOptionGroupArray = DASH.Constants.getSurfaceTypeGroups();
	
	$tableTBody = $("#<portlet:namespace/>surfaceTbody");
	$tableTBody.empty();
	
	$optionSelectors = $("<select/>").addClass("selectpicker");
// 	$("<option/>").val("").html("").appendTo($optionSelectors);
	
	for (a = 0; a < surfaceOptionGroupArray.length; a++) {
		var surfaceGroup = surfaceOptionGroupArray[a];
		$optgroup = $("<optgroup/>").attr("label",surfaceGroup[1]).appendTo($optionSelectors);
		var surfaceOptionsArray = DASH.Constants.getSurfaceTypes(surfaceGroup[0]);
		for (j = 0; j < surfaceOptionsArray.length; j++) {
			var surfaceOption = surfaceOptionsArray[j];
			$("<option/>").val(surfaceOption[0]).html(surfaceOption[1]).appendTo($optgroup);
		}
	}
	
	if(typeof boundaryNodes!="undefined"){
		for (i=0;i<boundaryNodes.length; i++) {
			var treeData = $("#navigatorTree").jstree(true).get_node(boundaryNodes[i],false);
			var node_id = treeData.id;
			var node_data_bc = treeData.data[DASH.Constants.DATA_BC];
			var node_text = treeData.text;
			$surfaceTr = $("<tr/>").appendTo($tableTBody);
			
			$cloneOption = $optionSelectors.clone();
			
			$cloneOption.find("option[value="+node_data_bc+"]").attr("selected","true");
			
			$cloneOption.attr("data-node",node_id);
			$cloneOption.attr("onchange","<portlet:namespace/>changeBc('"+node_id+"',this)");
			
			$("<td/>").append(
					$cloneOption
			).appendTo($surfaceTr);
			
			$("<td/>").text(node_text).appendTo($surfaceTr);
			
			var node_data_po = treeData.data["partObject"];
			if(typeof node_data_po == "undefined"){
				$("<td/>").appendTo($surfaceTr);
			}else{
				$("<td/>").appendTo($surfaceTr).append(
					$("<i/>").addClass("icon-fb-boolean")
				);
			}
		}
	}
}

function <portlet:namespace/>changeBc(node_id,sel){
	var node = $("#navigatorTree").jstree(true).get_node(node_id,false);
	var node_data = node.data;
	
	var data = new DASH.data(node_data);
	data.bcfunc(sel.value);
	node.data = OSP.Util.toJSON(data);
}
</script>

<div class="dashboard-content">
	<table class="table table-striped table-surface">
		<tbody id="<portlet:namespace/>surfaceTbody" style="font-family: Malgun Gothic;font-size:12px">
		</tbody>
	</table>
</div>