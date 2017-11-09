<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="icebreakerAccessURL" id="icebreakerAccess" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="icebreakerAccessDateUpdateURL" id="icebreakerAccessDateUpdate" copyCurrentRenderParameters="false" escapeXml="false"/>

<h3>Simulation Migration</h3>
<p>대기 : ${queueStatusSize} / 실행 : ${runningStatusSize}</p>

<div id="afterClick">
업데이트 건수 : <p id="updateCnt"></p>
Error 리스트 : <p id="errorList"></p>
</div>
<div style="float:right">
	<aui:button type="button" name="contentBtn" value="icebreaker Migration" onClick="getIcebreaker()" />
	<aui:button type="button" name="contentBtn" value="icebreaker Date Update" onClick="getIcebreakerDateUpdate()" />
</div>


<script>
$(function(){
	$("#afterClick").hide();
});

function getIcebreaker(){
	//var searchData = {"<portlet:namespace/>groupId":value};
	jQuery.ajax({
		type: "POST",
		url: "<%=icebreakerAccessURL%>",
		async : false,
		dataType: 'json',
		/* data  : searchData, */
		success: function(data) {
			$("#afterClick").show();
			$("#updateCnt").text(data.statusMap.updateCnt);
			
			var list = data.statusMap.errorList;

			for(var i=0;i<list.length;i++){
				$("#errorList").html($("#errorList").html()+list[i]+"<br/>");
			}
			
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}

function getIcebreakerDateUpdate(){
	jQuery.ajax({
		type: "POST",
		url: "<%=icebreakerAccessDateUpdateURL%>",
		async : false,
		dataType: 'json',
		/* data  : searchData, */
		success: function(data) {
			$("#afterClick").show();
			$("#updateCnt").text(data.statusMap.updateCnt);
			
			var list = data.statusMap.errorList;

			for(var i=0;i<list.length;i++){
				$("#errorList").html($("#errorList").html()+list[i]+"<br/>");
			} 
			
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}
</script>