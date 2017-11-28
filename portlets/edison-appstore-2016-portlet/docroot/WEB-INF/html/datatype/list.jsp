<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>


<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/datatypeeditor.css" media="screen"/>


<%
	String portletWindowState = (String)request.getAttribute("portletWindowState");
%>
<%-- <liferay-portlet:resourceURL var="dataTypeSearchURL" escapeXml="false" id="dataTypeSearch" copyCurrentRenderParameters="false"> --%>
<%-- </liferay-portlet:resourceURL> --%>
<liferay-portlet:renderURL var="dataTypeSearchURL" copyCurrentRenderParameters="false" windowState="<%=portletWindowState%>">
	<liferay-portlet:param name="searchByPrePage" value="${searchByPrePage}" />
	<liferay-portlet:param name="portType" value="${portType}" />
	<liferay-portlet:param name="portName" value="${portName}" />
	<c:if test="<%=LiferayWindowState.isPopUp(request)%>">
        <liferay-portlet:param name="redirectName" value="${redirectName}" />
        <liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	</c:if>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="dataTypeViewURL" escapeXml="false" id="dataTypeView" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="dataTypeModifyValidationURL" escapeXml="false" id="dataTypeModifyValidation" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="dataTypeRenderURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>">
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<portlet:param name="myRender" value="dataTypeModifyRender" />
</liferay-portlet:renderURL>

<portlet:actionURL var="submitURL" copyCurrentRenderParameters="false" name="dataTypeAction">
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
</portlet:actionURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<!-- http://61.85.104.246:8080/web/portal/my-edison?p_p_id=edisondatatypeeditor_WAR_edisonappstore2016portlet&p_p_lifecycle=0&p_p_state=pop_up&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1 -->
<form id="<portlet:namespace/>modifyDataTypeForm" name="<portlet:namespace/>modifyDataTypeForm" method="post">
	<aui:input name="dataTypeId" type="hidden"/>
	<aui:input name="mode" type="hidden"/>
</form>

<div class="virtitlebox">
	<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
	<div class="virtitle">
		Data Type Editor
	</div>
</div>
<div class="swpopleft">
	<div class="tablesw_list borderno">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<colgroup>
				<col width="80%" >
				<col width="*" >
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="TL"><liferay-ui:message key="edison-table-list-header-data-type-name"/></th>
					<th scope="col"><liferay-ui:message key="version"/></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty listDataType}">
						<c:forEach items="${listDataType}" var="dataMap">
							<tr class="bgcolor2" id="portTypeTr_${dataMap.typeId}" style="cursor: pointer;word-break:break-word" onclick="<portlet:namespace/>dataTypeView('${dataMap.typeId}')">
								<td>${dataMap.name}</td>
								<td>V${dataMap.version}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">
								<liferay-ui:message key="edison-there-are-no-data"/>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<div id="pageListDiv" class="paging">
		${pagingStr}
	</div>
</div>

<div class="swpopright">
	<input class="addIp button005_1" onclick="<portlet:namespace/>initDataTypeList();" value="<liferay-ui:message key="edison-button-board-initialize"/>" type="button" style="float: right;">
	
	<div class="swtabtitle"><liferay-ui:message key="preview"/></div>
	<div class="swsearchbox">
		<div class="searchbox" style="float:right;">
			<input type="text" id="<portlet:namespace/>searchName" size="30" maxlength="30" onKeydown="if(event.keyCode ==13){<portlet:namespace/>searchDataTypeList(1);return false;}" value="${searchName}" autofocus="autofocus"/>
			<input type="button" id="keyWordB" onclick="<portlet:namespace/>searchDataTypeList(1);return false;"/>
		</div>
	</div>
	<div class="swrightcon" style="height: 450px;">
		<div id="<portlet:namespace/>sampleFilePreviewDiv" style="display: none;">
			<div class="divLine">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
				SAMPLE FILE
			</div>
		
		</div>
		<div id="<portlet:namespace/>commentDiv" style="display: none;">
			<div class="divLine">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
				COMMENT
			</div>
		</div>
		<div id="<portlet:namespace/>editorPreviewDiv" style="display: none;">
			<div class="divLine">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
				EDITOR
			</div>
		</div>
		<div id="<portlet:namespace/>analyzerPreviewDiv" style="display: none;">
			<div class="divLine">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
				ANALYZER
			</div>
		</div>
		<div id="<portlet:namespace/>variableDiv" style="display: none;">
			<div class="divLine">
				<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
				STRUCTURE
			</div>
			<div id="<portlet:namespace/>inputPreview" class="inputPreview">
				<liferay-portlet:runtime portletName="StructuredDataViewer_WAR_OSPAnalyzersportlet_INSTANCE_datatype" queryString=""/>
<%-- 				<liferay-portlet:runtime portletName="structureddataviewer_WAR_OSPAnalyzersportlet" queryString=""/> --%>
			</div>
		</div>
	</div>
	<div class="swrightconbtnbox">
		<input class="addIp button08_2" onclick="<portlet:namespace/>dataTypeModify('<%=Constants.ADD%>');" value="<liferay-ui:message key="edison-appstore-add"/>" type="button"/>
		<c:choose>
			<c:when test="<%=LiferayWindowState.isPopUp(request)%>">
				<input class="addIp button08_2 choiceButton" onclick="<portlet:namespace/>dataTypeChoice();" value="<liferay-ui:message key="select"/>" type="button"  style="display: none;"/>
			</c:when>
			<c:otherwise>
				<input class="addIp button08_2 choiceButton" onclick="<portlet:namespace/>dataTypeModify('<%=Constants.COPY%>');" value="<liferay-ui:message key="copy"/>" type="button" style="display: none;"/>
				<input class="addIp button08_2 choiceButton" onclick="<portlet:namespace/>dataTypeModify('<%=Constants.UPDATE%>');" value="<liferay-ui:message key="update-data"/>" type="button" style="display: none;"/>
				<input class="addIp button08_2 choiceButton" onclick="<portlet:namespace/>dataTypeModify('<%=Constants.DELETE%>');" value="<liferay-ui:message key="delete"/>" type="button" style="display: none;"/>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script type="text/javascript">
	function <portlet:namespace/>searchDataTypeList(p_curPage){
		var searchParameter = "";
		if($("#<portlet:namespace/>searchName").val()!=""){
			searchParameter += "&<portlet:namespace/>searchName="+$("#<portlet:namespace/>searchName").val();
		}
		searchParameter += "&<portlet:namespace/>p_curPage="+p_curPage;
		location.href="<%=dataTypeSearchURL%>"+searchParameter;
	}
	
	function <portlet:namespace/>initDataTypeList(){
		var searchParameter = "";
		$("#<portlet:namespace/>searchName").val('');
		searchParameter += "&<portlet:namespace/>p_curPage="+1;
		location.href="<%=dataTypeSearchURL%>"+searchParameter;
	}
	
	var dataTypeId = "";
	function <portlet:namespace/>dataTypeView(typeId){
		dataTypeId = typeId;
		
		$("tr[class$=selected]").removeClass("selected");
		$portTypeTr = $("#portTypeTr_"+typeId);
		$portTypeTr.addClass(" selected");
		
		$(".choiceButton").show();
		
		//파일 초기화
		$("#<portlet:namespace/>sampleFilePreviewDiv").hide();
		$("#<portlet:namespace/>sampleFilePreviewDiv").find("div[class='down_date']").remove();
		
		//COMMENT
		$("#<portlet:namespace/>commentDiv").hide();
		$("#<portlet:namespace/>commentDiv").find("div[class='comment']").remove();
		
		//EDITOR
		$("#<portlet:namespace/>editorPreviewDiv").hide();
		$("#<portlet:namespace/>editorPreviewDiv").find("div[class='editor']").remove();
		
		//분석기
		$("#<portlet:namespace/>analyzerPreviewDiv").hide();
		$("#<portlet:namespace/>analyzerPreviewDiv").find("div[class='analyzer']").remove();
		
		//inputdeck view 초기화
		$("#<portlet:namespace/>variableDiv").hide();
// 		$("#<portlet:namespace/>inputPreview ").empty();
		
		var viewForm = {
				"<portlet:namespace/>typeId": typeId
		};
		jQuery.ajax({
			type: "POST",
			url: "<%=dataTypeViewURL%>",
			async : false,
			data  : viewForm,
			dataType: 'json',
			success: function(result) {
				if (result.description != ""){
					$parentDiv = $("#<portlet:namespace/>commentDiv");
					$("<div/>").addClass("comment").css("font-size","13px").css("word-break","break-all").html( result.description).appendTo($parentDiv);
					$parentDiv.show();
				}
				
				if (typeof result.sampleTitle != "undefined"){
					$div = $("#<portlet:namespace/>sampleFilePreviewDiv");
					$div.show();
					$("<div/>").addClass("down_date").css("cursor","pointer").css("display","inline-block")
							   .css("text-decoration","underline").css("color","blue")
							   .attr("onclick","<portlet:namespace/>fileDownload('"+result.sampleFileId+"')")
							   .text(result.sampleTitle)
							   .appendTo($div);
				};
				
				if (typeof result.editor != "undefined"){
					$parentDiv = $("#<portlet:namespace/>editorPreviewDiv");
					$("<div/>").addClass("editor").css("font-size","13px").html( result.editor).appendTo($parentDiv);
					$parentDiv.show();
				};
				
				if (typeof result.analyzer != "undefined"){
					$parentDiv = $("#<portlet:namespace/>analyzerPreviewDiv");
					$("<div/>").addClass("analyzer").css("font-size","13px").html(result.analyzer).appendTo($parentDiv);
					$parentDiv.show();
				};
				if(result.isStructurePresent){
					$("#<portlet:namespace/>variableDiv").show();
					<portlet:namespace/>addInputDeckAnalyzer(result.structure);
				}
				
				
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
					alert(textStatus+": "+jqXHR.responseText);
				}else{
					alert(textStatus+": "+errorThrown);
				}  
			}
		});
	}
	
	function <portlet:namespace/>addInputDeckAnalyzer(structure){
		var data={
			targetPortlet: 'StructuredDataViewer_WAR_OSPAnalyzersportlet_INSTANCE_datatype',
			portletId:'<%=portletDisplay.getId()%>',
			data: {
				type_: 'structuredData',
				context_: structure
			}
		};
		Liferay.fire('OSP_LOAD_DATA',data);
	}
	
	function <portlet:namespace/>dataTypeModify(mode){
		if('${portletWindowState}'=='<%=LiferayWindowState.POP_UP.toString()%>'){
			if(mode=='<%=Constants.ADD%>'){
				if(!confirm(Liferay.Language.get('edison-science-appstore-data-type-add-popup-msg'))){
					return false;
				}else{
					AUI().use("liferay-portlet-url", function(a) {
						var portletURL = Liferay.PortletURL.createRenderURL();
						portletURL.setParameter("myRender", "dataTypeModifyRender");
						portletURL.setPortletMode("view");
						portletURL.setWindowState("<%=LiferayWindowState.MAXIMIZED.toString()%>");
						portletURL.setPortletId("edisondatatypeeditor_WAR_edisonappstore2016portlet");
						portletURL.setParameter("redirectURL", "${redirectURL}");
						portletURL.setParameter("redirectName", "${redirectName}");
						
						portletURL.setParameter("scienceAppId", "${scienceAppId}");
						portletURL.setParameter("portType", "${portType}");
						portletURL.setParameter("portName", "${portName}");
						
						
						
						Liferay.Util.getOpener().redirectURL(portletURL);
					});
				}
			}
		}else{
			if(mode=='<%=Constants.DELETE%>'){
				if(!confirm(Liferay.Language.get('edison-science-appstore-data-type-delete-confirm-message'))){
					return false;
				}
			}else if(mode=='<%=Constants.COPY%>'){
				if(!confirm(Liferay.Language.get('edison-science-appstore-data-type-copy-msg'))){
					return false;
				}
			}
			
			
			if(mode=='<%=Constants.UPDATE%>'||mode=='<%=Constants.DELETE%>'){
				<portlet:namespace/>dataTypeModifyValidation(mode);
			}
			
			document.<portlet:namespace/>modifyDataTypeForm.<portlet:namespace/>dataTypeId.value=dataTypeId;
			document.<portlet:namespace/>modifyDataTypeForm.<portlet:namespace/>mode.value=mode;
			
			if(mode=='<%=Constants.COPY%>'||mode=='<%=Constants.DELETE%>'){
				document.<portlet:namespace/>modifyDataTypeForm.action = "<%=submitURL%>";
			}else{
				//UPDATE, ADD
				document.<portlet:namespace/>modifyDataTypeForm.action = "<%=dataTypeRenderURL%>";
			}
			
			document.<portlet:namespace/>modifyDataTypeForm.submit();
		}
	}
	
	function <portlet:namespace/>dataTypeModifyValidation(mode){
		var searchForm = {
				"<portlet:namespace/>typeId": dataTypeId,
				"<portlet:namespace/>mode": mode
		};
		jQuery.ajax({
			type: "POST",
			url: "<%=dataTypeModifyValidationURL%>",
			async : false,
			data  : searchForm,
			dataType: 'json',
			success: function(data) {
				if(!data.result){
					alert(Liferay.Language.get('edison-science-appstore-data-type-modify-error-message'));
					throw "stop";
				}
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
					alert(textStatus+": "+jqXHR.responseText);
				}else{
					alert(textStatus+": "+errorThrown);
				}  
			}
		});
	}
	
	function <portlet:namespace/>dataTypeChoice(){
		if('${searchByPrePage}'=='APP'){
			if('${portType}'=='INPUT'){
				if(!$("#<portlet:namespace/>editorPreviewDiv").is(":visible")){
					alert(Liferay.Language.get('edison-science-appstore-data-type-choice-error-msg',['Editor']));
					return false;
				}
			}else{
				if(!$("#<portlet:namespace/>analyzerPreviewDiv").is(":visible")){
					alert(Liferay.Language.get('edison-science-appstore-data-type-choice-error-msg',['Analyzer']));  
					return false;
				}
			}
			Liferay.Util.getOpener().updateDataFromPopUp('${portType}','${portName}',dataTypeId);
		}else{
			Liferay.Util.getOpener().updateDataFromPopUp(dataTypeId);
		}
		
	}
	
	function <portlet:namespace/>fileDownload(p_fileEntryId){
		location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
	}
</script>
