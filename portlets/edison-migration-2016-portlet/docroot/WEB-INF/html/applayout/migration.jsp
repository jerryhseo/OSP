<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="searchPortURL" id="searchPort" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updateLayoutURL" id="updateLayout" copyCurrentRenderParameters="false" />

<h3>APP LAYOUT Migration</h3>
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
					Layout.templateId('2-6-column-i');
					Layout.height(0.7);
					
					var column1 = Layout.addPortlet('column-1','edisonworkbenchmonitoring_WAR_edisonsimulationportlet',true);
					column1.height(0.3);
					
					var column2 = Layout.addPortlet('column-2','edisonworkbenchjobmonitoring_WAR_edisonsimulationportlet',true);
					column2.height(0.3);
					
					var column3 = Layout.addPortlet('column-3','Dashboard_WAR_OSPWorkbenchportlet',true);
					column3.height(0.4);
					
					var column4 = Layout.addPortlet('column-4','Information_WAR_OSPWorkbenchportlet',true);
					column4.height(0.2);
					
					var inputPortArray = scienceApp.inputPortsArray();
					var editorFirst = true;
					var column5 = null;
					for(var i=0; i<inputPortArray.length;i++){
						var data = inputPortArray[i];
						if(data[OSP.Constants.DEFAULT_EDITOR]==0){
							alert(Liferay.Language.get('edison-science-appstore-port-no-default-error-msg',[''+data[OSP.Constants.NAME]+'','Editor']));
							return false;
						}
						
						column5 = Layout.addPortlet('column-5',data[OSP.Constants.DEFAULT_EDITOR],editorFirst,data[OSP.Constants.NAME])
						editorFirst = false;
					}
					if(column5!=null){
						column5.height('0.8');
					}
						
					
					var column6 = Layout.addPortlet('column-6','edisonworkbenchjobstatusandresult_WAR_edisonsimulationportlet',false,'_DOWNLOAD_');
					column6.height('0.8');
					
					var column6CurrentId = false;
					var outputPortArray = scienceApp.outputPortsArray();
					for(var i=0; i<outputPortArray.length;i++){
						var data = outputPortArray[i];
						if(data[OSP.Constants.DEFAULT_ANALYZER]==0){
							alert(Liferay.Language.get('edison-science-appstore-port-no-default-error-msg',[''+data[OSP.Constants.NAME]+'','Analyzer']));
							return false;
						}

						if(i == 0){
							Layout.addPortlet('column-6',data[OSP.Constants.DEFAULT_ANALYZER],true,data[OSP.Constants.NAME]);
							column6CurrentId = true;
						}else{
							Layout.addPortlet('column-6',data[OSP.Constants.DEFAULT_ANALYZER],false,data[OSP.Constants.NAME]);
						}
					}
					
					var logPortArray = scienceApp.logPortsArray();
					if(logPortArray.length>0){
						for(var i=0; i<logPortArray.length;i++){
							var data = logPortArray[i];
							if(data[OSP.Constants.DEFAULT_ANALYZER]==0){
								alert(Liferay.Language.get('edison-science-appstore-port-no-default-error-msg',[''+data[OSP.Constants.NAME]+'','Analyzer']));
								return false;
							}
							
							var logPortDisplay = false;
							if(!column6CurrentId){
								column6CurrentId = true;
								logPortDisplay = true;
							}
							Layout.addPortlet('column-6',data[OSP.Constants.DEFAULT_ANALYZER],logPortDisplay,data[OSP.Constants.NAME]);
						}
					}
					
//	  				console.log(JSON.stringify(Layout,null,4));
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
				"<portlet:namespace/>templetId" : '2-6-column-i'
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
