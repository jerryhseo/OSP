<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		
<div class="table2_list" style="width:100%; clear: both;">	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" id="jobSubmitTable">
		<colgroup>
			<col width="10%">
			<col >
			<col width="10%">
			<col width="17%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th><liferay-ui:message key="edison-table-list-header-index" /></th>
				<th><liferay-ui:message key="edison-simulation-execute-job-create-list-job-name" /></th>
				<th><liferay-ui:message key="edison-simulation-execute-job-create-list-state" /></th>
				<th><liferay-ui:message key="edison-simulation-execute-job-create-list-submit-time" /></th>
				<th><liferay-ui:message key="edison-simulation-execute-job-input-value" /></th>
			</tr>
		</thead>
		<tbody id="jobSubmitList">
		</tbody>
	</table>
</div>
<div class="buttonbox">
	<input id="submitBtn" type="button" value="Submit" class="button05" onClick="<portlet:namespace/>submitJob()">
	<input id="moveMonitoring" type="button" value="Monitoring" class="button05" onClick="<portlet:namespace/>moveMonitoring()">
</div>

<style>
<!--

#progress_bar_wrap2 {
	width:500px;  
	padding: 10px 30px 30px 30px; 
	background-color:#f7f7f7;
	border-top: 1px solid #e8e8e8; 
    border-right: 1px solid #e8e8e8; 
    border-left: 1px solid #e8e8e8; 
    border-bottom: 1px solid #e8e8e8; 
    overflow-y: hidden;
}
#progress_bar_line {
	padding:1px; 
	border-top: 1px solid #CCCCCC; 
    border-right: 1px solid #CCCCCC; 
    border-left: 1px solid #CCCCCC; 
    border-bottom: 1px solid #CCCCCC; 
}
#progress_bar2 {
	width: 100%;
	background-image:url(/edison-simulation-portlet/images/progress_bar.jpg);
	height:20px; 
	text-align:right; 
	line-height:15px; 
	font-size:11px; color:#000000;
} 

-->
</style>
<!-- 	Progress Bar	  -->
<div id="progress_bar_wrap2" style="display: none;">
    <div id="progress_bar_line">
        <div id="progress_bar2"> 1/100 &nbsp;</div>    
    </div>
</div>

<script type="text/javascript">
	//작업제출시 프로그래스바 설정
	$("#progress_bar_wrap2").dialog({
			resizable: false,
			height:50,
			width:700,
			modal: true,			
			draggable: false,
			autoOpen : false
	});
	
	//프로그래스바 탑 툴바제거
	$("#progress_bar_wrap2").siblings('div.ui-dialog-titlebar').remove();	 
 
 
	var submittedJobCount = 0;
	var successJobCount = 0;
	var progress_percent = 0;
	var createCount = 0;
	var setTimeoutDelay = 1;
	var idx = 0;
	
	function createJobForm() {
		var inputDeckPortName = "";
		var inputDeckData = "";
		
		var inputPortJson = $("#<portlet:namespace/>inputPortJson").val();
		if(inputPortJson != "") {
			inputPortJson = JSON.parse(inputPortJson);
			var portNameList = Object.keys(inputPortJson);
			
			for (var i = 0; i < portNameList.length; i++) {
				var editorType = $("#<portlet:namespace/>" + portNameList[i] + "_type").val();
				if(editorType == "Inputdeck") {
					inputDeckPortName = portNameList[i];
					inputDeckData = $("#<portlet:namespace/>" + inputDeckPortName).text();
					break;
				}
			}
		}
		
		var isSubmit = $("#<portlet:namespace/>isSubmit").val();
		if(isSubmit=="false"){
			$('#jobSubmitList').empty();
		}else{
			$('#jobSubmitList tr[simulationUuid=0]').remove();
		}
		
		
		var inputDeckFileJSON = "";
		
		if(inputDeckPortName != "" && inputDeckData != "") {
			inputDeckData = JSON.parse(inputDeckData);
		}
		// inputDeckFileJSON 면 Sweep 상태임
		if(inputDeckData.length > 1) {
			$.each(inputDeckData, function(index, value){
				<portlet:namespace/>drawSubmitList(inputDeckPortName,index+1);
			});
		} else {
			<portlet:namespace/>drawSubmitList(inputDeckPortName,1);
		}
		
	}
	
	function pad(num, size) {
	    var s = num+"";
	    while (s.length < size) s = "0" + s;
	    return s;
	}
	
	function <portlet:namespace/>drawSubmitList(inputDeckPortName,inputDeckFileViewNumber) {
		var count = ($('#jobSubmitList tr').length + 1);
		$trNode = $("<tr/>").addClass("jobTrClass")
							.attr("id", "jobCreateTrId_" + count)
							.attr("gruopId", 0)
							.attr("simulationUuid", 0)
							.attr("jobUuid", 0)
							.attr("jobSeqNo", count);
		
		$("<td/>").css("font-color","gray").addClass("TC").text(count).appendTo($trNode);
		$("<td/>").css("font-color","gray").addClass("TC").text("#" + pad(count, 3) + " " + $('#<portlet:namespace/>simulation_title').val()).appendTo($trNode);
		$("<td/>").css("font-color","gray").addClass("TC").text("READY").appendTo($trNode);
		$("<td/>").css("font-color","gray").addClass("TC").text(" ").appendTo($trNode);    	 
		$("<td/>").css("font-color","gray").addClass("TC").html("<img src=\"${contextPath }/images/fileicon.png\" style=\"cursor:pointer;\" onClick=\"inputDeckFileView('" + inputDeckPortName + "','" + count + "','" + inputDeckFileViewNumber + "')\">").appendTo($trNode);

		if($('#jobSubmitList tr').length==0){
			$trNode.appendTo($('#jobSubmitList'));
		}else{
			$('#jobSubmitList tr:last').after($trNode);
		}
	}
	
	var <portlet:namespace/>isSubmitIng = false;
	function <portlet:namespace/>submitJob() {
		var isAppSubmitStatus = $("#<portlet:namespace/>isAppSubmitStatus").val();
		if(isAppSubmitStatus=="true"&&!<portlet:namespace/>isSubmitIng){
			<portlet:namespace/>isSubmitIng = true;
			$("#<portlet:namespace/>isSubmit").val("true");
			
			$("#progress_bar2").css("width", "100%");
	   		$("#progress_bar2").text("JOB SUBMITTING");
			$("#progress_bar_wrap2").dialog("open");
			
			setTimeout(function(){
				var submitRowCnt = Number($('#jobSubmitList tr[jobUuid!=0]').length);
				executeJob(0,submitRowCnt)
			}, 100);
			<portlet:namespace/>isSubmitIng = false;
		}else{
			if(!<portlet:namespace/>isSubmitIng){
				alert("JOB SUBMIT FAILED");
			}
		}
	}
	
	function executeJob(jobSeqNo,submitRowCnt) {
		var portDataLocation = 0;
   		if(jobSeqNo == 0) {
   			$('#jobSubmitList tr').each(function(index){
				if($(this).attr("simulationUuid") == "0"){
					jobSeqNo = index+1;
					return false;
				}
			});
   			portDataLocation = 1;
   		}else{
   			if($("#<portlet:namespace/>isSubmit").val()=="true"){
   				portDataLocation = jobSeqNo-submitRowCnt;
   			}else{
   				portDataLocation = jobSeqNo;
   			}
   		}
		var submitParam = {
			"<portlet:namespace/>groupId": $("#<portlet:namespace/>groupId").val(),
			"<portlet:namespace/>simulationUuid": $("#<portlet:namespace/>simulationUuid").val(),
			"<portlet:namespace/>cluster": $("#<portlet:namespace/>cluster").val(),
			"<portlet:namespace/>scienceAppId" : $("#<portlet:namespace/>scienceAppId").val(),
			"<portlet:namespace/>scienceApp_name" : $("#<portlet:namespace/>scienceApp_name").val(),
			"<portlet:namespace/>simulation_title" : $('#<portlet:namespace/>simulation_title').val(),
			"<portlet:namespace/>simulation_description" : $("#<portlet:namespace/>simulation_description").val(),
			"<portlet:namespace/>code_mpi_type" : $("#<portlet:namespace/>scienceAppRunType").val(),
			"<portlet:namespace/>code_mpi_module" : $("#<portlet:namespace/>scienceAppParallelModule").val(),
			"<portlet:namespace/>code_mpi_number" : $("#<portlet:namespace/>code_mpi_number").val(),
			"<portlet:namespace/>inputPortJson" : $("#<portlet:namespace/>inputPortJson").val(),
			"<portlet:namespace/>jobSeqNo" : jobSeqNo,
			"<portlet:namespace/>testYn" : testYn
		};
		
		var inputPortJson = $("#<portlet:namespace/>inputPortJson").val();
		if(inputPortJson != "") {
			inputPortJson = JSON.parse(inputPortJson);
			var portNameList = Object.keys(inputPortJson);
			
			for (var i = 0; i < portNameList.length; i++) {
				var portDataType = $("#<portlet:namespace/>" + portNameList[i] + "_type").val();
				var portFILE = $("#<portlet:namespace/>" + portNameList[i] + "_file").val();
				var portDATA = $("#<portlet:namespace/>" + portNameList[i]).text();
				if(portDataType == "Inputdeck") {
					if(portDATA != "") {
						var portDATA = JSON.parse(portDATA);

						if(typeof portDATA == "object") {
							portDATA = portDATA[(portDataLocation -1)];
							var portDataArray = new Array();
							portDataArray[0] = portDATA;
							portFILE = portDATA["file-content"];
							portDATA = JSON.stringify(portDataArray);
						}
					}
				}
				
				submitParam["<portlet:namespace/>" + portNameList[i] + "_TYPE"] = portDataType;
				submitParam["<portlet:namespace/>" + portNameList[i] + "_FILE"] = portFILE;
				submitParam["<portlet:namespace/>" + portNameList[i] + "_DATA"] = portDATA;

			}
		}
		jQuery.ajax({
			type: "POST",
			url: "<%=submitJobURL%>",
			async : false,
			data  : submitParam,
			success: function(data) {
				if(data.message == "SUCCESS"){
					$('#jobSubmitList tr').attr("simulationUuid", data.simulationUuid);
					$("#jobCreateTrId_" + data.jobSeqNo).attr("jobUuid", data.jobUuid);
					$("#<portlet:namespace/>simulationUuid").val(data.simulationUuid);
					$("#<portlet:namespace/>cluster").val(data.cluster);
					$('#jobSubmitList #jobCreateTrId_' + data.jobSeqNo + ' td:nth-child(3)').text(data.status);
					$('#jobSubmitList #jobCreateTrId_' + data.jobSeqNo + ' td:nth-child(4)').text(data.submitDt);
					if(data.jobStatus==1701004){
						$("#<portlet:namespace/>isAppSubmitStatus").val("false");
					}
					
 					var submittedJobCount = $('#jobSubmitList tr').length;
					
					if(data.jobSeqNo < submittedJobCount) {
						executeJob((Number(data.jobSeqNo) + 1),submitRowCnt);
					} else {
						$("#progress_bar_wrap2").dialog("close");
					}
					
					if(testYn == "Y") {
						setTimeout(window.location.reload(), 1000*5);
					}
				}else {
					$("#progress_bar_wrap2").dialog("close");
					alert(data.message);
				}
				
			},
			error:function(data,e){
				$("#progress_bar_wrap2").dialog("close");
			},
			complete:function(){
			}
		});
	}
	
	function inputDeckFileView(id, rowNum, index) {
		$('#<portlet:namespace/>jobparameter-dialog-content').empty();
		
		var thisTR = $('#jobCreateTrId_' + rowNum);
		var simulationUuid = thisTR.attr("simulationUuid");
		var jobUuid = thisTR.attr("jobUuid");
		var htmlStr = "<div class=\"tbcell070201\">SID: "+simulationUuid + "</div><div class=\"tbcell070201\">JID: "+jobUuid+"</div>";
		$('#<portlet:namespace/>jobparameter-dialog-content').append(htmlStr);
		
		var inputPortJson = $("#<portlet:namespace/>inputPortJson").val();
		if(inputPortJson != "") {
			inputPortJson = JSON.parse(inputPortJson);
			var portNameList = Object.keys(inputPortJson);
			var inputDeckData = "";
			if(jobUuid!=0){
				inputDeckData = <portlet:namespace/>searchSimulationData(jobUuid);
			}else{
				inputDeckData = $('#<portlet:namespace/>' + id).text();
			}
			
			for (var i = 0; i < portNameList.length; i++) {
				var data = $("#<portlet:namespace/>" + portNameList[i]).text();
				
				if(portNameList[i] != id && data != "") {
					var dataType = $('#<portlet:namespace/>' + portNameList[i] + "_type").val();
					if(dataType == "File") {
						var htmlStr = "<div class=\"tbcell070101\">▶JOB INPUTPORT - " + portNameList[i] + "</div>";
						$('#<portlet:namespace/>jobparameter-dialog-content').append(htmlStr);
						data = JSON.parse(data);
						if(data["fileName"] == "SAMPLE") {
							$('#<portlet:namespace/>jobparameter-dialog-content').append("SAMPLE FILE");
						} else {
							$('#<portlet:namespace/>jobparameter-dialog-content').append("File Name : " + data["fileName"]);
						}
					}else if(dataType == "Text") {
						var htmlStr = "<div class=\"tbcell070101\">▶JOB INPUTPORT - " + portNameList[i] + "</div>";
						$('#<portlet:namespace/>jobparameter-dialog-content').append(htmlStr);
						data = JSON.parse(data);
						$('#<portlet:namespace/>jobparameter-dialog-content').append("File Name : " + data["fileName"]);
					}  else {
						var htmlStr = "<div class=\"tbcell070101\">▶JOB INPUTPORT</div>";
						$('#<portlet:namespace/>jobparameter-dialog-content').append(htmlStr);
						$('#<portlet:namespace/>jobparameter-dialog-content').append(data);
					}
				} else if (portNameList[i] == id && inputDeckData != "") {
					var htmlStr = "<div class=\"tbcell070101\">▶JOB INPUTDECK - " + id + "</div>";
					$('#<portlet:namespace/>jobparameter-dialog-content').append(htmlStr);
					
					inputDeckData = JSON.parse(inputDeckData);
					if(typeof inputDeckData == "object") {
						var inputdeckContent = "";
						if(jobUuid!=0){
							inputdeckContent = inputDeckData[id][0]["file-content"];
						}else{
							inputdeckContent = inputDeckData[(index -1)]["file-content"];
						}
						$('#<portlet:namespace/>jobparameter-dialog-content').append(inputdeckContent);
					}
				}
			}
		}
		
		$("#<portlet:namespace/>jobparameter-dialog").dialog("open");
	}
	
	
	
	function <portlet:namespace/>searchSimulationData(jobUuid){
		var searchData = {
				"<portlet:namespace/>jobUuid": jobUuid
		};
		var returnData = "";
		jQuery.ajax({
			type: "POST",
			url: "<%=searchSimulationJobDataURL%>",
			data  : searchData,
			async : false,
	 		dataType: 'json',
			success: function(data) {
				returnData =  JSON.stringify(data);
			},
			error:function(msg){
				alert("System searchSimulationData : " + msg);
			}
		});
		return returnData;
	}
</script>