<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="searchPortURL" id="searchPort" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateLayoutURL" id="updateLayout" copyCurrentRenderParameters="false" />

<style>
.subtitlearea{
	margin-left: 10px;
}
</style>

<h2>
	<img src="${contextPath}/images/sub_tit_bl.png"/>
	<span class="subtitlearea">
		<liferay-ui:message key='edison-migration-title' />
	</span>
</h2>

<div class="h30"></div>

<h3>APP LAYOUT Migration</h3>
<h3>Default : flow-1-row-1-column</h3>
<p>${totalCnt}</p>
수행건수 : <p id="changeLayoutCnt">0</p>
NOT UPDATE 건수 : <p id="notUpdateCnt">0</p>
에러건수 : <p id="changeErrorCnt">0</p>
<input type="button" class="btn btn-default" value="실행" onclick="search();"/>

<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>

<script type="text/javascript">
	function search(){
		bStart();
		setTimeout(function(){
			var dataList = JSON.parse('${swList}');
			for(var i=0;i<dataList['sw'].length;i++){
				var scienceAppId = dataList['sw'][i];
				ajaxSearchApp(scienceAppId);
			}
			bEnd();
		}, 2000);
	}
	
	function ajaxSearchApp(scienceAppId){
		console.log(scienceAppId);
		var dataForm = {
				"<portlet:namespace/>scienceAppId" : scienceAppId
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=searchPortURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var updateStatus = msg.updateStatus;
				if(updateStatus){
					var dataMap = msg.data;
					var scienceApp = new OSP.ScienceApp();
					if(typeof dataMap.inputPorts != 'undefined'){
						scienceApp.deserializeInputPorts(JSON.parse(dataMap.inputPorts));
					}
					
					if(typeof dataMap.outputPorts != 'undefined'){
						scienceApp.deserializeOutputPorts(JSON.parse(dataMap.outputPorts));
					}
					
					if(typeof dataMap.logPorts != 'undefined'){
						scienceApp.deserializeLogPorts(JSON.parse(dataMap.logPorts));
					}
					
					
					
					var Layout = new OSP.Layout();
					Layout.templateId("flow-1-row-1-column");
					
					//System Default Portlet Set
					Layout.addPortlet('column-1','SimulationDashboard_WAR_edisonsimulationportlet',true);
					Layout.addPortlet('column-2','SimulationBreadcrumb_WAR_OSPWorkbenchportlet',true);
					Layout.addPortlet('column-3','ScienceAppPort_WAR_OSPWorkbenchportlet',true);
					
					
					var inputPortArray = scienceApp.inputPortsArray();
					var editorFirst = true;
					for(var i=0; i<inputPortArray.length;i++){
						var data = inputPortArray[i];
						if(data[OSP.Constants.DEFAULT_EDITOR]==0){
							alert(Liferay.Language.get('edison-science-appstore-port-no-default-error-msg',[''+data[OSP.Constants.NAME]+'','Editor']));
							return false;
						}
						Layout.addPortlet('column-4',data[OSP.Constants.DEFAULT_EDITOR],editorFirst,data[OSP.Constants.NAME])
						editorFirst = false;
					}
					
					
					var logPortArray = scienceApp.logPortsArray();
					if(logPortArray.length>0){
						var logFirst = true;
						for(var i=0; i<logPortArray.length;i++){
							var data = logPortArray[i];
							if(data[OSP.Constants.DEFAULT_ANALYZER]==0){
								alert(Liferay.Language.get('edison-science-appstore-port-no-default-error-msg',[''+data[OSP.Constants.NAME]+'','Analyzer']));
								return false;
							}
							
							Layout.addPortlet('column-5',data[OSP.Constants.DEFAULT_ANALYZER],logFirst,data[OSP.Constants.NAME]);
							logFirst = false;
						}
					}
					
					
					var outputPortArray = scienceApp.outputPortsArray();
					if(outputPortArray.length>0){
						var outPutFirst = true;
						for(var i=0; i<outputPortArray.length;i++){
							var data = outputPortArray[i];
							if(data[OSP.Constants.DEFAULT_ANALYZER]==0){
								alert(Liferay.Language.get('edison-science-appstore-port-no-default-error-msg',[''+data[OSP.Constants.NAME]+'','Analyzer']));
								return false;
							}
							Layout.addPortlet('column-6',data[OSP.Constants.DEFAULT_ANALYZER],outPutFirst,data[OSP.Constants.NAME]);
							outPutFirst = false;
						}
					}
	  				console.log(JSON.stringify(Layout,null,4));
	 				update(scienceAppId,Layout);
				}else{
					var num = $("#notUpdateCnt").html();
					$("#notUpdateCnt").html(Number(num)+1);
				}
			},error:function(msg,e){
			}
		});
	}
	
	
	function update(scienceAppId,layoutJSON){
		var dataForm = {
				"<portlet:namespace/>scienceAppId" : scienceAppId,
				"<portlet:namespace/>layout" : JSON.stringify(layoutJSON),
				"<portlet:namespace/>templetId" : 'flow-1-row-1-column'
		};
		jQuery.ajax({
			type: "POST",
			url: "<%=updateLayoutURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var num = $("#changeLayoutCnt").html();
				$("#changeLayoutCnt").html(Number(num)+1);
			},error:function(msg,e){ 
				var num = $("#changeErrorCnt").html();
				$("#changeErrorCnt").html(Number(num)+1);
			}
		});
	}
</script>
