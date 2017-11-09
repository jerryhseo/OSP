<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="searchInputDeckURL" id="searchInputDeck" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateInputDeckURL" id="updateInputDeck" copyCurrentRenderParameters="false" />

<h3>Inputdeck Migration</h3>
<p>${totalCnt}</p>
수행건수 : <p id="changeInputdeckCnt">0</p>
<input type="button" value="실행" onclick="search();"/>

<div class="row-fluid">
	<pre id="<portlet:namespace/>oldStructure" class="span6" style="overflow-y:auto; max-height:400px"></pre>
	<pre id="<portlet:namespace/>newStructure" class="span6" style="overflow-y:auto; max-height:400px"></pre>
</div>

<script type="text/javascript">

	function search(){
		jQuery.ajax({
			type: "POST",
			url: "<%=searchInputDeckURL%>",
			async : false,
			dataType: 'json',
			success: function(msg) {
				var inputdeckList = msg.dataList;
				for(var i = 0; i < inputdeckList.length; i++) {
					var typeId = inputdeckList[i].typeId;
					var json = JSON.parse(inputdeckList[i].JSON);
					
					var dataType = new OSP.DataType();
					var newStructure = dataType.newDataStructure();
					newStructure.upgrade(json, $('#<portlet:namespace/>newStructure'));
					console.log("AFTER--->>"+JSON.stringify(newStructure));
					update(typeId,JSON.stringify(newStructure));
				}
			},error:function(msg,e){ 
				alert("function search "+e);
				return false;
			}
		});
	}
	
	
	function update(typeId,changeInputdeckForm){
		var dataForm = {
				"<portlet:namespace/>typeId" : typeId,
				"<portlet:namespace/>inputdeckForm" : changeInputdeckForm
		};
		jQuery.ajax({
			type: "POST",
			url: "<%=updateInputDeckURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var num = $("#changeInputdeckCnt").html();
				$("#changeInputdeckCnt").html(Number(num)+1);
			},error:function(msg,e){ 
				alert("function update "+e);
				return false;
			}
		});
	}
</script>
