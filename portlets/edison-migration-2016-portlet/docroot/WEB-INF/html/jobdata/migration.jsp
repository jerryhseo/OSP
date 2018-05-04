<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="searchJobURL" id="searchJob" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="searchJobDataURL" id="searchJobData" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateJobDataURL" id="updateJobData" copyCurrentRenderParameters="false" />

<h3>Job Data Migration</h3>
<p>edison_chicken.EDSIM_SimulationJobData -> edison2_release.EDSIM_SimulationJobData</p>
<p>JSON 형식 변경</p>
<div class="input-group col-md-5">
	<span class="input-group-addon">User ScreenName</span>
	<input class="form-control" type="text" id="userScreenName" onKeydown="if(event.keyCode ==13)search();">
	<span class="input-group-btn">
		<button type="button" class="btn btn-default" onclick="search();">조회</button>
	</span>
</div>

<div class="h10"></div>
<button type="button" class="btn btn-primary pull-right" id="<portlet:namespace/>updateBtn" style="display: none;">UPDATE</button>
<div class="h10"></div>
<div class="container">
	<div class="col-md-2" id="jobs" style="padding-left: 0px;">
		
	</div>
	<pre id="<portlet:namespace/>oldJson" class="col-md-5" style="overflow-y:auto; max-height:700px;min-height:300px;"></pre>
	<pre id="<portlet:namespace/>newJson" class="col-md-5" style="overflow-y:auto; max-height:700px;min-height:300px;"></pre>
</div>

<script type="text/javascript">
function search(){
	jQuery.ajax({
		type: "POST",
		url: "<%=searchJobURL%>",
		async : false,
		dataType: 'json',
		data :  {"<portlet:namespace/>userScreenName" : $("#userScreenName").val()},
		success: function(result) {
			$jobs = $("#jobs");
			$jobs.empty();
			for(var i = 0; i < result.length; i++) {
				$panel = $("<div/>").addClass("panel panel-default").appendTo($jobs);
				$("<div/>").addClass("panel-body").css("cursor","pointer")
				.attr("onclick","convertor('"+result[i].jobUuid+"','"+result[i].appId+"')")
				.html(cutStr(result[i].jobUuid,15)).appendTo($panel);
			}
		},error:function(msg,e){ 
			alert("function search "+e);
			return false;
		}
	});
}

function convertor(jobUuid,appId){
	jQuery.ajax({
		type: "POST",
		url: "<%=searchJobDataURL%>",
		async : false,
		dataType: 'json',
		data :  {"<portlet:namespace/>jobUuid" : jobUuid,"<portlet:namespace/>appId" : appId},
		success: function(result) {
			$("#<portlet:namespace/>oldJson").empty();
			$("#<portlet:namespace/>oldJson").html(JSON.stringify(JSON.parse(result.oldJSON), undefined, 2));
			
			$("#<portlet:namespace/>newJson").empty();
			$("#<portlet:namespace/>newJson").html(JSON.stringify(JSON.parse(result.newJSON), undefined, 2));
			
			if(result.newJSON==="false"){
				$("#<portlet:namespace/>updateBtn").css("display","none").attr("onclick","");
			}else{
				$("#<portlet:namespace/>updateBtn").css("display","block").attr("onclick","update('"+jobUuid+"','"+appId+"')");;
			}
		},error:function(msg,e){ 
			alert("function convertor "+e);
			return false;
		}
	});
}

function update(jobUuid,appId){
	jQuery.ajax({
		type: "POST",
		url: "<%=updateJobDataURL%>",
		async : false,
		dataType: 'json',
		data :  {"<portlet:namespace/>jobUuid" : jobUuid,"<portlet:namespace/>appId" : appId},
		success: function(result) {
			alert("SUCCESS");
		},error:function(msg,e){ 
			alert("function update "+e);
			return false;
		}
	});
}

</script>