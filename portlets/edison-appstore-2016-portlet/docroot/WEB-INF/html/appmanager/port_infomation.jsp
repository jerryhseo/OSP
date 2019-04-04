<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="org.kisti.edison.util.EdisonHttpUtil"%>


<portlet:actionURL var="submitURL" copyCurrentRenderParameters="<%=false%>" name="appAction">
	<portlet:param name="clickTab" value="${clickTab}"/>
	<portlet:param name="actionType" value="portInfomation"/>
	<portlet:param name="isPort" value="${isPort}"/>
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
	
	<portlet:param name="searchValue" value="${searchValue}"/>
	<portlet:param name="searchOption" value="${searchOption}"/>
	<portlet:param name="searchStatus" value="${searchStatus}"/>
	<portlet:param name="p_curPage" 	value="${p_curPage}" />
	<portlet:param name="redirectURL" 	value="${redirectURL}" />
</portlet:actionURL>

<liferay-portlet:renderURL var="portNameAddURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="portNameAdd" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="sampleFileSearchURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="portSampeFileSearch" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="portAppSelectorURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="portAppSelector" />
</liferay-portlet:renderURL>


<liferay-portlet:resourceURL var="searchDataTypeURL" escapeXml="false" id="searchDataType" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="deletePortSampleFileURL" escapeXml="false" id="deleteFile" copyCurrentRenderParameters="false">
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
</liferay-portlet:resourceURL>

<style type="text/css">
	.aui .long_field{
		width: 350px !important;
	}
	
	.aui .short_field{
		width: 150px !important;
	}
	
	.aui .too_long_field{
		width: 500px !important;
	}
	
	.aui .swrightcont .alert{
		margin-top: 10px;
	}
	
	.aui .text_field{
		width: 100%;
		resize: none;
		height:auto;
		margin-bottom: 10px;
	}
</style>

<aui:form name="frm" method="POST" action="<%=submitURL%>">
	<aui:input name="inputPorts" type="hidden" value="" label=""/>
	<aui:input name="outputPorts" type="hidden" value="" label=""/>
	<aui:input name="logPorts" type="hidden" value="" label=""/>
	<aui:input name="initLayout" type="hidden" value="false" label=""/>
	<aui:input name="actionMode" value="${mode}" type="hidden"/>
</aui:form>
<div class="edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			Command Line
		</h3>
		<div class="btn-group pull-right">
			<input class=" button02_2" onclick="<portlet:namespace/>goList();" value="<liferay-ui:message key='edison-button-board-list'/>" type="button">
			
			<c:if test="${data.status gt 1901003}">
				<input class=" button02_1" onclick="<portlet:namespace/>copyScienceApp();" value="<liferay-ui:message key='edison-appstore-copy'/>" type="button">
			</c:if>
			<c:if test="${appStatusButtonView}">
				<c:if test="${data.status eq '1901001'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901002');" value="<liferay-ui:message key='edison-appstore-status-request'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901002' && isAdmin}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901001');" value="<liferay-ui:message key='edison-appstore-status-denial'/>" type="button">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901003'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				
				<c:if test="${data.status eq '1901004'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
				</c:if>
			</c:if>
			
			<input class=" button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');return false;" value="<liferay-ui:message key='edison-button-save'/>" type="button">
			
			<c:if test="${ownerThan}">
				<input class=" button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.DELETE%>');return false;" value="<liferay-ui:message key='delete'/>" type="button">
			</c:if>
		</div>
	</div>
</div>
<div style="margin:0 auto;text-align: center;">
	<textarea id="commandTextArea" disabled="disabled" class="text_field"></textarea>
</div>
<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-science-appstore-toolkit-input-port' />
		</h3>
		
		<div class="btn-group pull-right">
			<button class="btn btn-default noUpdateHidden" type="button" onclick="<portlet:namespace/>portNameOpen('${scienceAppId}','INPUT');"><span class="icon-search"> <liferay-ui:message key='edison-science-appstore-toolkit-input-port'/> <liferay-ui:message key='add'/></span></button>
		</div>
	</div>
	<table class = "table table-bordered table-hover edison-table">
		<thead>
			<th width="10%"><liferay-ui:message key='edison-table-list-header-index' /></th>
			<th width="30%"><liferay-ui:message key='edison-table-list-header-port-name' /></th>
			<th width="*"><liferay-ui:message key='edison-data-collection-select-data-type-list' /></th>
			<th width="15%">Sample File</th>
			<th width="15%"><liferay-ui:message key='required' /></th>
			<th width="5%">Default</th>
			<th width="10%"><liferay-ui:message key='edison-button-board-delete' /></th>
		</thead>
		<tbody id="<portlet:namespace/>inputPortListBody">
			
		</tbody>
	</table>
</div>


<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-science-appstore-toolkit-log-port' />
		</h3>
		
		<div class="btn-group pull-right">
			<button class="btn btn-default noUpdateHidden" type="button" onclick="<portlet:namespace/>portNameOpen('${scienceAppId}','LOG');"><span class="icon-search"> <liferay-ui:message key='edison-science-appstore-toolkit-log-port'/> <liferay-ui:message key='add'/></span></button>
		</div>
	</div>
	<table class = "table table-bordered table-hover edison-table">
		<thead>
			<th width="10%"><liferay-ui:message key='edison-table-list-header-index' /></th>
			<th width="20%"><liferay-ui:message key='edison-table-list-header-port-name' /></th>
			<th width="20%"><liferay-ui:message key='edison-data-collection-select-data-type-list' /></th>
			<th width="15%">
				<liferay-ui:message key='edison-table-list-header-port-type' />
				<liferay-ui:icon-help message="edison-science-appstore-port-type-message"/>
			</th>
			<th width="20%">
				File Path
				<liferay-ui:icon-help message="edison-science-appstore-file-path-message"/>
			</th>
			<th width="5%">Default</th>
			<th width="10%"><liferay-ui:message key='edison-button-board-delete' /></th>
		</thead>
		<tbody id="<portlet:namespace/>logPortListBody">
			
		</tbody>
	</table>
</div>


<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-science-appstore-toolkit-out-port' />
		</h3>
		
		<div class="btn-group pull-right">
			<button class="btn btn-default noUpdateHidden" type="button" onclick="<portlet:namespace/>portNameOpen('${scienceAppId}','OUTPUT');"><span class="icon-search"> <liferay-ui:message key='edison-science-appstore-toolkit-out-port'/> <liferay-ui:message key='add'/></span></button>
		</div>
	</div>
	<table class = "table table-bordered table-hover edison-table">
		<thead>
			<th width="10%"><liferay-ui:message key='edison-table-list-header-index' /></th>
			<th width="20%"><liferay-ui:message key='edison-table-list-header-port-name' /></th>
			<th width="20%"><liferay-ui:message key='edison-data-collection-select-data-type-list' /></th>
			<th width="15%">
				<liferay-ui:message key='edison-table-list-header-port-type' /> 
				<liferay-ui:icon-help message="edison-science-appstore-port-type-message"/>
			</th>
			<th width="20%">
				File Path
				<liferay-ui:icon-help message="edison-science-appstore-file-path-message"/>
			</th>
			<%-- <th width="8%"><liferay-ui:message key='required' /></th> --%>
			<th width="5%">Default</th>
			<th width="10%"><liferay-ui:message key='edison-button-board-delete' /></th>
		</thead>
		<tbody id="<portlet:namespace/>outputPortListBody">
			
		</tbody>
	</table>
</div>

<div id="port-name-dialog" title="port-name" class="bigpopupbox" style="display: none;">
	
</div>

<div id="port-sampe-file-dialog" title="sample-file" style="display: none;">
	
</div>

<div id="port-app-selector-dialog" title=""port-app-selector" class="bigpopupbox" style="display: none;">
	
</div>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>drawPort('INPUT','${data.inputPorts}',false);
	<portlet:namespace/>drawPort('LOG','${data.logPorts}',false);
	<portlet:namespace/>drawPort('OUTPUT','${data.outputPorts}',false);
// 	<portlet:namespace/>noUpdateDisabled('${data.status}');
});

	var scienceApp = new OSP.ScienceApp();
	/* port infomation change check */
	var <portlet:namespace/>portChange = false;
	
	function <portlet:namespace/>portNameOpen(scienceAppId,portType){
		var renderParameter = "";
		renderParameter += "&<portlet:namespace/>scienceAppId="+scienceAppId;
		renderParameter += "&<portlet:namespace/>portType="+portType;
		
		var URL = "<%=portNameAddURL%>"+renderParameter;
		$("#port-name-dialog").load(URL).dialog("open");
	}
	
	function <portlet:namespace/>dataTypeSearchOpen(portType,portName){
		$("body").css('overflow','hidden');
		AUI().use("liferay-portlet-url", function(a) {
			var portletURL = Liferay.PortletURL.createRenderURL();
			portletURL.setPortletMode("view");
			portletURL.setWindowState("pop_up");
			portletURL.setPortletId("edisondatatypeeditor_WAR_edisonappstore2016portlet");
			portletURL.setParameter("portType", portType);
			portletURL.setParameter("portName", portName);
			portletURL.setParameter("searchByPrePage", "APP");
			portletURL.setParameter("redirectURL", "<%=themeDisplay.getURLCurrent()%>");
			portletURL.setParameter("redirectName", Liferay.Language.get('edison-appstore-myapp-list'));
			Liferay.Util.openWindow(
					{
						dialog: {
							width:1024,
							height:900,
							cache: false,
							draggable: false,
							resizable: false,
							modal: true,
							destroyOnClose: true,
							after: {
								render: function(event) {
									$("button.btn.close").on("click", function(e){
										$("body").css('overflow','');
									});
								}
							}
						},
					id: "dataTypeSearchDialog",
					uri: portletURL.toString(),
					title: "SEARCH"
					}
				);
		});
// 		$("#port-search-dialog").load(URL).dialog("open");
	}
	
	$("#port-name-dialog").dialog({
		autoOpen: false,
		width: 420,
		height: 'auto',
	    modal: true,
	    resizable: false,
	    dialogClass: 'no-dialog-padding',
	    show: {effect:'fade', speed: 800}, 
        hide: {effect:'fade', speed: 800},
	    open: function(event, ui) {
	    	$(this).parent().css('overflow', 'visible');
	    	$(this).css('overflow', 'visible');
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#port-name-dialog").dialog("close");
	    	});
	    },
	    close: function() {
	    	$("#port-name-dialog").empty();
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	
	
	$("#port-sampe-file-dialog").dialog({
		autoOpen: false,
		width: 900,
		height: '300',
	    modal: true,
	    resizable: false,
	    show: {effect:'fade', speed: 800}, 
        hide: {effect:'fade', speed: 800},
        dialogClass: 'no-dialog-padding',
	    open: function(event, ui) {
	    	$(this).parent().css('overflow', 'visible');
	    	$(this).css('overflow', 'visible');
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#port-sampe-file-dialog").dialog("close");
	    	});
	    },
	    close: function() {
	    	$("#port-sampe-file-dialog").empty();
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	$("#port-app-selector-dialog").dialog({
		autoOpen: false,
		width: 900,
		height: '400',
	    modal: true,
	    resizable: false,
	    show: {effect:'fade', speed: 800}, 
        hide: {effect:'fade', speed: 800},
        dialogClass: 'no-dialog-padding',
	    open: function(event, ui) {
	    	$(this).parent().css('overflow', 'visible');
	    	$(this).css('overflow', 'visible');
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#port-app-selector-dialog").dialog("close");
	    	});
	    },
	    close: function() {
	    	$("#port-app-selector-dialog").empty();
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	
	function <portlet:namespace/>closeDueDeletePortSampeFile(p_fileEntryId){
		var deleteForm = {
				"<portlet:namespace/>fileEntryId" : p_fileEntryId,
				"<portlet:namespace/>fileType" : "portType"
				};
		
		jQuery.ajax({
			type: "POST",
 			url: "<%=deletePortSampleFileURL%>",
			data: deleteForm,
	  		async : false,
			success: function(data) {
				
			},error:function(data,e){ 
				alert("deleteFile System error!");	
			}
		});
	}
	
	
	function <portlet:namespace/>drawPort(portType,data,portChangeCheck){
		var draw = false;
		if(data!=''){
			if(portType=='INPUT'){
				scienceApp.deserializeInputPorts(JSON.parse(data));
				$("#<portlet:namespace/>inputPorts").val(JSON.stringify(scienceApp.inputPorts()));
			}else if(portType=='OUTPUT'){
				scienceApp.deserializeOutputPorts(JSON.parse(data));
				$("#<portlet:namespace/>outputPorts").val(JSON.stringify(scienceApp.outputPorts()));
			}else if(portType=='LOG'){
				scienceApp.deserializeLogPorts(JSON.parse(data));
				$("#<portlet:namespace/>logPorts").val(JSON.stringify(scienceApp.logPorts()));
			}
			draw = true;
		}else{
			if(portType=='INPUT'){
				$("#<portlet:namespace/>inputPorts").val("");
				$("#<portlet:namespace/>inputPorts").val(JSON.stringify(scienceApp.inputPorts()));
			}else if(portType=='OUTPUT'){
				$("#<portlet:namespace/>outputPorts").val("");
				$("#<portlet:namespace/>outputPorts").val(JSON.stringify(scienceApp.outputPorts()));
			}else if(portType=='LOG'){
				$("#<portlet:namespace/>logPorts").val("");
				$("#<portlet:namespace/>logPorts").val(JSON.stringify(scienceApp.logPorts()));
			}
			draw = true;
		}
		
		if(portChangeCheck&&'${data.portExist}'=='true'&&!<portlet:namespace/>portChange){
			<portlet:namespace/>portChange = true;
		}
		
		if(draw){
			var $body;
			var array;
			var colspan = 0;
			if(portType=='INPUT'||portType=='LOG'){
				colspan = 7;
			}else{
				colspan = 8;
			}
			
			if(portType=='INPUT'){
				$body = $("#<portlet:namespace/>inputPortListBody");
				array = scienceApp.inputPortsArray();
				//Command Line 그리기
				$("#commandTextArea").empty();
				$("#commandTextArea").text("${data.exeFileName}");
			}else if(portType=='OUTPUT'){
				$body = $("#<portlet:namespace/>outputPortListBody");
				array = scienceApp.outputPortsArray();
			}else if(portType=='LOG'){
				$body = $("#<portlet:namespace/>logPortListBody");
				array = scienceApp.logPortsArray();
			}
			$body.find("tr:not(:has(#1))").remove();
			
			if(array.length==0){
				$rowResult = $("<tr/>").appendTo($body);
				$("<td/>").text(Liferay.Language.get('edison-there-are-no-data')).attr("colspan",colspan).addClass("center").appendTo($rowResult);
			}else{
				var outPutforms = OSP.Constants.getDefinedPathTypes();
				for(var i=0; i<array.length;i++){
					
					$rowResult = $("<tr/>");
					var data = array[i];
					$("<td/>").css("text-align","center")
					 		  .text(i+1).appendTo($rowResult);
					
					$("<td/>").css("text-align","center")
					 		  .text(data[OSP.Constants.NAME]).appendTo($rowResult);
					
					$("<td/>").css("text-align","center")
					 		  .text(data[OSP.Constants.DATA_TYPE]['name']).appendTo($rowResult);
					var dataTypeName = data[OSP.Constants.DATA_TYPE]['name'];
					var dataTypeVersion = data[OSP.Constants.DATA_TYPE]['version'];
					var defaultAppId = 0;
					
					if(portType=='INPUT'){
						var samplePath = '';
						if(typeof data[OSP.Constants.SAMPLE] != "undefined"){
							samplePath = data[OSP.Constants.SAMPLE][OSP.Constants.ID];
						}
						
						defaultAppId = data[OSP.Constants.DEFAULT_EDITOR];
						
						$('#commandTextArea').append(" "+data[OSP.Constants.NAME]+" "+dataTypeName);
						
						$sampleFileTd = $("<td/>").css("text-align","center").appendTo($rowResult);
						$("<img/>").attr("src","${contextPath}/images/appmanager/file_download_icon.png")
								   .attr("onClick","<portlet:namespace/>sample_file_event('"+data[OSP.Constants.NAME]+"','"+samplePath+"','"+dataTypeName+"','"+dataTypeVersion+"')")
								   .attr("width","22px")
								   .attr("height","22px")
								   .css("cursor","pointer")
								   .appendTo($sampleFileTd);
					}else{
						defaultAppId = data[OSP.Constants.DEFAULT_ANALYZER];
						
						$select = $("<select/>").attr("onChange","<portlet:namespace/>changeDataType('"+portType+"','"+data[OSP.Constants.NAME]+"',this.value)")
												.attr("id","<portlet:namespace/>"+portType+"_"+data[OSP.Constants.NAME]+"_dataType")
												.addClass("aui-field-select noupdate");
						
						for(var j=0; j<outPutforms.length;j++){
							$("<option/>").val(outPutforms[j]).html(outPutforms[j]).appendTo($select);
						}
						
						$select.find("option[value="+data[OSP.Constants.OUTPUT_DATA][OSP.Constants.TYPE]+"]").attr("selected","selected");
						
						
						$("<td/>").css("text-align","center")
								  .append($select).appendTo($rowResult);
						$("<td/>").css("text-align","center")
								  .append(
										$("<input/>").attr("type","text")
													 .addClass("field checkFilePath noupdate")
													 .attr("id","<portlet:namespace/>"+portType+"_"+data[OSP.Constants.NAME]+"_fileName")
													 .attr("data_name",data[OSP.Constants.NAME])
													 .on("focusout",function(){
														 <portlet:namespace/>changeFileName(portType,$(this).attr("data_name"),$(this).val());
													 })
													 .val(data[OSP.Constants.OUTPUT_DATA][OSP.Constants.PARENT]+data[OSP.Constants.OUTPUT_DATA][OSP.Constants.NAME])
										  )
								  .appendTo($rowResult);
					}
					
					if(portType =='INPUT'){
						$select = $("<select/>").attr("onChange","<portlet:namespace/>changeMandatory('"+data[OSP.Constants.NAME]+"','"+portType+"',this.value)")
												.addClass("aui-field-select noupdate");

						$("<option/>").val("N")
									 .html("N")
									 .appendTo($select);
						
						$("<option/>").val("Y")
									 .html("Y")
									 .appendTo($select);
						
						$("<td/>").css("text-align","center").append($select).appendTo($rowResult);
					}
					
					$appSelectorTd = $("<td/>").css("text-align","center").appendTo($rowResult);
					$("<img/>").attr("src","${contextPath}/images/appmanager/btn_monitor_visual.png")
							   .attr("onClick","<portlet:namespace/>portAppSelectorEvent('"+portType+"','"+data[OSP.Constants.NAME]+"','"+dataTypeName+"','"+dataTypeVersion+"','"+defaultAppId+"')")
							   .attr("width","22px")
							   .attr("height","22px")
							   .css("cursor","pointer")
							   .appendTo($appSelectorTd);
					
					
					$("<td/>").css("text-align","center")
							  .append(
									 $("<button/>").attr("type","button")
									 			  .addClass("btn btn-warning noUpdateHidden")
									 			  .attr("onClick","<portlet:namespace/>deleteMap('"+data[OSP.Constants.NAME]+"','"+portType+"')")
									 			  .html("<span class='icon-trash'> "+Liferay.Language.get('edison-button-board-delete')+"</span>")
									  ).appendTo($rowResult);
					
					if(data[OSP.Constants.MANDATORY]){
						$select.find("option[value=Y]").attr("selected","selected");
					}
					
					$body.append($rowResult);
				}
			}
		}
	}
	
	function <portlet:namespace/>sample_file_event(portName, samplePath, dataTypeName, dataTypeVersion){
		var renderParameter = "";
		renderParameter += "&<portlet:namespace/>scienceAppId="+"${scienceAppId}";
		renderParameter += "&<portlet:namespace/>portName="+portName;
		renderParameter += "&<portlet:namespace/>samplePath="+samplePath;
		renderParameter += "&<portlet:namespace/>dataTypeName="+dataTypeName;
		renderParameter += "&<portlet:namespace/>dataTypeVersion="+dataTypeVersion;
		var URL = "<%=sampleFileSearchURL%>"+renderParameter;
		$("#port-sampe-file-dialog").load(URL).dialog("open");
	}
	
	function <portlet:namespace/>portAppSelectorEvent(portType, portName, dataTypeName, dataTypeVersion, defaultAppId){
		var renderParameter = "";
		renderParameter += "&<portlet:namespace/>portType="+portType;
		renderParameter += "&<portlet:namespace/>portName="+portName;
		renderParameter += "&<portlet:namespace/>dataTypeName="+dataTypeName;
		renderParameter += "&<portlet:namespace/>dataTypeVersion="+dataTypeVersion;
		renderParameter += "&<portlet:namespace/>defaultAppId="+defaultAppId;
		var URL = "<%=portAppSelectorURL%>"+renderParameter;
		$("#port-app-selector-dialog").load(URL).dialog("open");
	}
	
	function <portlet:namespace/>changeDataType(portType,name,value){
		var targetObejct = null;
		if(portType=="OUTPUT"){
			targetObejct = scienceApp.outputPort(name);
		}else if(portType=="LOG"){
			targetObejct = scienceApp.logPort(name);
		}
		
		targetObejct.outputDataPathType(value);
		var fileName = $("#<portlet:namespace/>"+portType+"_"+name+"_fileName").val();
		var stringRegx = /\//gi;
		if(stringRegx.test(fileName)){
			var names = fileName.split("\/");
			fileName = names[names.length-1];
		}
		
		var result = true;
		
		if(fileName.trim!=""){
			if(value==OSP.Constants.FILE){
				result = targetObejct.outputData().fileName(fileName);
			}else if(value==OSP.Constants.EXT){
				result = targetObejct.outputData().extension(fileName);
			}else if(value==OSP.Constants.FOLDER){
				result = targetObejct.outputData().folderName(fileName);
			}
			
// 			console.log(JSON.stringify(targetObejct,null,4))

			if(!result){
				$("#<portlet:namespace/>_"+portType+"_"+name+"_fileName").val('');
			}
		}
		
		<portlet:namespace/>drawPort(portType,'',false);
	}
	
	function <portlet:namespace/>changeFileName(portType,name,value){
		var targetObejct = null;
		if(portType=="OUTPUT"){
			targetObejct = scienceApp.outputPort(name);
		}else if(portType=="LOG"){
			targetObejct = scienceApp.logPort(name);
		}
		var dataPathType = targetObejct.outputDataPathType();
		var result = true;
		
		/* 2019.04.04 _ Add suffix when data path type is Folder */
		if(dataPathType == "folder"){
			if(value.substr(value.length-1) != "/"){
				value += "/";
			}
		}
		
		// value에 / 포함 여부 체크
		var name = value;
		var stringRegx = /\//gi;
		if(stringRegx.test(name)){
			
			var names = name.split("\/");
			name = names[names.length-1];
			
			var valueStartIndex = 0;
			if(value.charAt(0)=="/"){
				valueStartIndex = 1;
			}
			var parent = value.slice(valueStartIndex,value.length-name.length);
			targetObejct.outputData().parent(parent);
		}
		if(dataPathType==OSP.Constants.FILE){
			result = targetObejct.outputData().fileName(name);
		}else if(dataPathType==OSP.Constants.EXT){
			result = targetObejct.outputData().extension(name);
		}else if(dataPathType==OSP.Constants.FOLDER){
			result = targetObejct.outputData().folderName(name);
		}
		
// 		console.log(JSON.stringify(targetObejct,null,4));
		
		if(!result){
			alert(Liferay.Language.get('expression-is-not-valid'));
			$("#<portlet:namespace/>_"+portType+"_"+name+"_fileName").val('');
		}
		<portlet:namespace/>drawPort(portType,'',false);
	}
	
	function <portlet:namespace/>changeMandatory(name,portType,status){
		var manadatory = false;
		if(status=='Y'){
			manadatory = true;
		}
		if(portType=='INPUT'){
			scienceApp.inputPort(name).mandatory(manadatory);
		}else if(portType=='OUTPUT'){
			scienceApp.outputPort(name).mandatory(manadatory);
		}else if(portType=='LOG'){
			scienceApp.logPort(name).mandatory(manadatory);
		}
		
		<portlet:namespace/>drawPort(portType,'',false);
	}
	
	function <portlet:namespace/>deleteMap(name,portType){
		if(confirm(Liferay.Language.get('data-delete-confirm'))){
			if(portType=='INPUT'){
				scienceApp.removeInputPort(name);
			}else if(portType=='OUTPUT'){
				scienceApp.removeOutputPort(name);
			}else if(portType=='LOG'){
				scienceApp.removeLogPort(name);
			}
			
			<portlet:namespace/>drawPort(portType,'',true);
		}
	}
	
	function <portlet:namespace/>addPort(portType,dataTypeId,portName){
		var dataTypeOBJ = <portlet:namespace/>searchDataType(portType,dataTypeId);
		if(portType=='INPUT'){
			var inputPortObj = scienceApp.newInputPort();
			inputPortObj.name(portName);
			inputPortObj.defaultEditor(dataTypeOBJ.defaultApp);
			inputPortObj.dataType(dataTypeOBJ.name,dataTypeOBJ.version);
			inputPortObj.mandatory(true);
// 			inputPortObj.setSample(dataTypeOBJ.sampleFileId,dataTypeOBJ.sampleFileParent,dataTypeOBJ.sampleFileName,OSP.Constants.FILE,true);
			scienceApp.addInputPort(inputPortObj);
		}else if(portType=='OUTPUT'){
			var outPutPortObj = scienceApp.newOutputPort();
			outPutPortObj.name(portName);
			outPutPortObj.setOutputDataPath(0,'','',OSP.Constants.FILE,true);
			outPutPortObj.defaultAnalyzer(dataTypeOBJ.defaultApp);
			outPutPortObj.dataType(dataTypeOBJ.name,dataTypeOBJ.version);
			outPutPortObj.mandatory(true);
			scienceApp.addOutputPort(outPutPortObj);
		}else if(portType=='LOG'){
			var logPortObj = scienceApp.newLogPort();
			logPortObj.name(portName);
			logPortObj.setOutputDataPath(0,'','',OSP.Constants.FILE,true);
			logPortObj.defaultAnalyzer(dataTypeOBJ.defaultApp);
			logPortObj.dataType(dataTypeOBJ.name,dataTypeOBJ.version);
			scienceApp.addLogPort(logPortObj);
		}
		
		console.log(JSON.stringify(scienceApp,null,4));
		<portlet:namespace/>drawPort(portType,'',true);
	}
	
	function <portlet:namespace/>searchDataType(portType,dataTypeId){
		var searchData = {
				"<portlet:namespace/>dataTypeId" : dataTypeId,
				"<portlet:namespace/>portType" : portType
				};
		var returnOBJ;
		jQuery.ajax({
			type: "POST",
			url: "<%=searchDataTypeURL%>",
			data: searchData,
	  		async : false,
	  		dataType: 'json',
			success: function(data) {
				  var obj = {
					name:  data.name,
					version: data.version,
					sampleFileParent:data.sampleFileParent,
					sampleFileId:data.sampleFileId,
					sampleFileName:data.sampleFileName,
					defaultApp:data.defaultApp
				  };
				  returnOBJ =  obj;
			},error:function(jqXHR, textStatus, errorThrown){ 
				if(jqXHR.responseText !== ''){
					alert(textStatus+": "+jqXHR.responseText);
				}else{
					alert(textStatus+": "+errorThrown);
				}  
				return false;
			}
		});
		return returnOBJ;
	}
	
	function <portlet:namespace/>actionCall(mode){
		if(mode=='<%=Constants.DELETE%>'){
			if(confirm(Liferay.Language.get('edison-appstore-delete-data-alert'))){
				<portlet:namespace/>frm.<portlet:namespace/>actionMode.value = mode;
			}else{
				return false;
			}
		}
		
		var checkVal = true;
		$("input[class*=checkFilePath][id$=fileName]").each(function(){
			var $this = $(this);
			var val = $this.val();
			if(val.trim()==""){
				alert(Liferay.Language.get('this-field-is-mandatory'));
				setTimeout(function() {$this.focus();}, 50);
				checkVal = false;
				return false;
			}
		});
		
		if(checkVal){
			
			if(mode=='<%=Constants.ADD%>'){
				if(<portlet:namespace/>portChange){
					alert("Port 정보 변경으로 인하여 Layout을 새롭게 작성 해야 합니다.");
					$("#<portlet:namespace/>initLayout").val("true");
				}
			}
			
			/* 2018.11.13 _ Set Default Parent File Path */
			setDefaultParentFilePath("log");
			setDefaultParentFilePath("output");
			
			submitForm(<portlet:namespace/>frm);
		}
	}
	
	function setDefaultParentFilePath(portType){
		jsonData = JSON.parse($("#<portlet:namespace/>"+portType+"Ports").val());
		
		updatePortData = false;
		for(var i=0; i<Object.keys(jsonData).length; i++){
			mainKey = Object.keys(jsonData)[i];
			
			mainJsonData = jsonData[mainKey];
			type = mainJsonData[OSP.Constants.OUTPUT_DATA][OSP.Constants.TYPE];
			if(type != "file"){
				portParent_ = mainJsonData[OSP.Constants.OUTPUT_DATA][OSP.Constants.PARENT];
				
				var stringRegx = /\//gi;
				if(!stringRegx.test(portParent_)){
					mainJsonData[OSP.Constants.OUTPUT_DATA][OSP.Constants.PARENT] = "result/";
				}
				updatePortData = true;
			} else {
				updatePortData = false;
			}
		}
		
		if(updatePortData){
			$("#<portlet:namespace/>"+portType+"Ports").val(JSON.stringify(jsonData));
		}
	}
</script>

<aui:script>
	Liferay.provide(window, 'updateDataFromPopUp', function(portType,portName,dataTypeId){
		<portlet:namespace/>closePopup('dataTypeSearchDialog');
		<portlet:namespace/>addPort(portType,dataTypeId,portName);
		$("body").css('overflow','');
	});
	
	Liferay.provide(window, 'redirectURL', function(URL) {
		location.href = URL;
	});
	
	Liferay.provide(window,'<portlet:namespace />closePopup',
		function(popupIdToClose) {
			Liferay.Util.getWindow(popupIdToClose).destroy(); // You can try toggle/hide whatever You want
			},
		['liferay-util-window']
	);
 </aui:script>

