<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>

<liferay-portlet:renderURL var="scienceAppManagementURL" windowState="<%= LiferayWindowState.POP_UP.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="scienceAppManagement" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="detailViewURL" windowState="<%= LiferayWindowState.MAXIMIZED.toString()%>" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="detailView" />
	<liferay-portlet:param name="simulationProjectId" value="${simulationProjectId}" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isRedirect" value="${isRedirect}" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="listSize" value="${listSize}" />
	<liferay-portlet:param name="methodName" value="${methodName}" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL secure="<%=request.isSecure() %>" var="createSimulationProjectUrl">
	<portlet:param name="myaction" value="createSimulationProject" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL secure="<%=request.isSecure() %>" var="updateSimulationProjectUrl">
	<portlet:param name="myaction" value="updateSimulationProject" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL secure="<%=request.isSecure() %>" var="deleteSimulationProjectUrl">
	<portlet:param name="myaction" value="deleteSimulationProject" />
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="deleteIconFileURL" escapeXml="false" id="deleteIconFile" copyCurrentRenderParameters="false">
	<portlet:param name="simulationProjectId" value="${simulationProjectId}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="deleteImageFileURL" escapeXml="false" id="deleteImageFile" copyCurrentRenderParameters="false">
	<portlet:param name="simulationProjectId" value="${simulationProjectId}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="selectScienceAppListURL" escapeXml="false" id="selectScienceAppList" copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="getRequestMemberListURL" escapeXml="false" id="getRequestMemberList" copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="updateSimulationProjectMemberURL" escapeXml="false" id="updateSimulationProjectMember" copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="deleteSimulationProjectMemberURL" escapeXml="false" id="deleteSimulationProjectMember" copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>

<%
	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";
	
	String textAreaPlaceholder = LanguageUtil.get(themeDisplay.getLocale(),"Enter-one-value-per-line");
	String exceptionNameMsg = LanguageUtil.get(themeDisplay.getLocale(),"edison-app-validation-name-exception-msg");
	String simulationProjectId = CustomUtil.strNull(request.getAttribute("simulationProjectId"));
	
	if("".equals(simulationProjectId)){
		for(Locale aLocale : locales){
			String languageId = LocaleUtil.toLanguageId(aLocale);
			if(localesStr.equals("")){
				localesStr += languageId;
			}else{
				localesStr += ","+languageId;
			}
		}
	}
%>

<div class="visualtxt3">
	<h3>
		<c:choose>
			<c:when test="${empty simulationProjectId && empty redirectName }">
				<liferay-ui:message key='edison-simulation-project-create-project' />
			</c:when>
			<c:when test="${empty simulationProjectId && not empty redirectName }">
				<a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName} </a>  > <liferay-ui:message key='edison-simulation-project-create-project' />
			</c:when>
			<c:when test="${not empty simulationProjectId && empty redirectName }">
				<liferay-ui:message key='edison-simulation-project-management' />
			</c:when>
			<c:when test="${not empty simulationProjectId && not empty redirectName }">
			   <a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName} </a>  > <liferay-ui:message key='edison-simulation-project-management' />
			</c:when>
		</c:choose>
	</h3>
</div>
<div class="virtitlebox">
	<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
	<div class="virtitle">
		<liferay-ui:message key='edison-science-appstore-toolkit-default-information' />
	</div>
	<div style="width:60%; float:right; text-align:right; padding-top:15px;">
		<c:if test="${empty simulationProjectId}">
			<input class="addIp button02_1" onclick="<portlet:namespace/>submitSimulationProject('create'); return false;" value="<liferay-ui:message key='edison-button-register' />" type="button">
		</c:if>
		<c:if test="${not empty simulationProjectId}">
			<input class="addIp button02_2" onclick="<portlet:namespace/>goDetailView();" value="<liferay-ui:message key='edison-simulation-monitoring-table-header-detail' />" type="button">
			<input class="addIp button02_1" onclick="<portlet:namespace/>submitSimulationProject('update'); return false;" value="<liferay-ui:message key='edison-button-board-modify' />" type="button">
			<input class="addIp button02_1" onclick="<portlet:namespace/>deleteSimulationProject(); return false;" value="<liferay-ui:message key='delete'/>" type="button">
		</c:if>
	</div>
</div>
<div class="h10"></div>
<div class="table1_list">
	<aui:form name="frm" method="POST">
		<aui:input type="hidden" name="simulationProjectId" value="${simulationProjectId}"></aui:input>
		<aui:input type="hidden" name="redirectName" value="${redirectName}"></aui:input>
		<aui:input type="hidden" name="redirectURL" value="${redirectURL}"></aui:input>
		<aui:input type="hidden" name="isRedirect" value="${isRedirect}"></aui:input>
		<aui:input type="hidden" name="searchText" value="${searchText}"></aui:input>
		<aui:input type="hidden" name="currentPage" value="${currentPage}"></aui:input>
		<aui:input type="hidden" name="listSize" value="${listSize}"></aui:input>
		<aui:input type="hidden" name="methodName" value="${methodName}"></aui:input>
		
		<table id="<portlet:namespace/>basicInfo" width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
			<colgroup>
				<col width="20%">
				<col width="40%">
				<col width="40%">
			</colgroup>
			<tr>
				<th>
					<liferay-ui:message key='edison-table-list-header-title' /><span class="requiredField"> *</span>
				</th>
				<td colspan="2">
					<liferay-ui:input-localized name="title" xml="${data.title}" scssClass="too_long_field" type="input" style="width: 50%" maxLength="70"/>
				</td>
			</tr>
			<c:if test="${not empty simulationProjectId}">
				<tr>
					<th><liferay-ui:message key='edison-simulation-project-public-status' /><span class="requiredField"> *</span></th>
					<td colspan="2">
						<aui:select name="projectOpenYn" label="" onChange="changeProjectOpenYnState();">
							<option value="false"><liferay-ui:message key='edison-simulation-project-private' /></option>
							<option value="true"><liferay-ui:message key='edison-simulation-project-public' /></option>
						</aui:select>
					</td>
				</tr>
				<tr class="simproStateOpen" style="display: none;">
					<th><liferay-ui:message key='edison-content-service-language' /><span class="requiredField"> *</span></th>
					<td colspan="2">
						<aui:select name="targetLanguage" label="">
							<option value=""><liferay-ui:message key='full' /></option>
							<%
							String siteDefaultLanuageId = LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale());
							
							for(Locale aLocale : locales){
								String languageId = LocaleUtil.toLanguageId(aLocale);
								if(localesStr.equals("")){
									localesStr += languageId;
								}else{
									localesStr += ","+languageId;
								}
								
								String languageNm = aLocale.getDisplayName(themeDisplay.getLocale());
							%>
								<aui:option label="<%=languageNm%>" value="<%=languageId%>" selected="<%=languageId.equals(siteDefaultLanuageId) %>"/>
							<%} %>
						</aui:select>
					</td>
				</tr>
				<tr class="simproStateOpen" style="display: none;">
					<th><liferay-ui:message key='edison-simulation-project-representative-image' /><span class="requiredField"> *</span></th>
					<td colspan="2">
						<div id="<portlet:namespace/>fileTDArea">
							<div id="<portlet:namespace/>fileDivDefault">
								<input type="file" name="<portlet:namespace/>project_image" />
								<input type="button" value="<liferay-ui:message key='edison-button-file-add' />" class="button06" onClick="<portlet:namespace/>moreFileTag()" style="cursor:pointer;"/>
							</div>
						</div>
						<div id="<portlet:namespace/>fileListDiv">
							<c:if test="${data.projectImageList ne null}">
								<c:forEach var="projectImage" items="${data.projectImageList}">
									<div class="projectImageList">
										<div class="down_date"  onclick="<portlet:namespace/>fileDownload('${projectImage.imageId }')" style="cursor: pointer;display: inline-block;">
											${projectImage.imageTitle}
										</div>
										<img src='${contextPath}/images/icon_dustbin.png' width='13' height='14' style="cursor:pointer" 
											onClick="<portlet:namespace/>deleteImageFile('${projectImage.imageId}');" />
									</div>	
								</c:forEach>
							</c:if>
						</div>
						<div id="clear"></div>
					</td>
				</tr>
				<tr class="simproStateOpen" style="display: none;">
					<th><liferay-ui:message key='icon' /><span class="requiredField"> *</span></th>
					<td colspan="2">
						<input type="file" id="<portlet:namespace/>project_icon" name="<portlet:namespace/>project_icon" />
						<c:if test="${data.iconId ne null}">
							<div class="down_date simProIconClass" id="<portlet:namespace/>simProIcon" onclick="<portlet:namespace/>fileDownload('${data.iconId }')" style="cursor: pointer;display: inline-block;">
								${data.iconTitle}
							</div>
							<img src='${contextPath}/images/icon_dustbin.png' class="simProIconClass" width='13' height='14' style="cursor:pointer" 
									onClick="<portlet:namespace/>deleteIconFile('${data.iconId}','simProIconClass');" />
						</c:if>
						<div id="clear"></div>
					</td>
				</tr>
				<tr class="simproStateOpen" style="display: none;">
					<th><liferay-ui:message key='edison-workflow-conf-description-input' /><span class="requiredField"> *</span></th>
					<td colspan="2">
						<liferay-ui:input-localized name="explain" xml="${data.explain}" type="textarea" cssClass="text_field" style="width: 60%; resize: none;" cols="80" rows="5"/>
					</td>
				</tr>
			</c:if>				
			<tr>
				<th rowspan="${fn:length(parentCategoryList)+1}">
					<liferay-ui:message key='edison-science-appstore-view-tab-category' /><span class="requiredField"> *</span>
				</th>
			</tr>
			<c:forEach items="${parentCategoryList}" var="parentCategory">
				<tr>
					<td id="<portlet:namespace/>${parentCategory.value}_parentTd" colspan="2">
						<span id="<portlet:namespace/>${parentCategory.value}_parent_open" style="cursor: pointer;" onclick="<portlet:namespace/>openRootCategory('OPEN','${parentCategory.value}');">${parentCategory.name}(OPEN)</span>
						<span id="<portlet:namespace/>${parentCategory.value}_parent_close" style="cursor: pointer;display: none;" onclick="<portlet:namespace/>openRootCategory('CLOSE','${parentCategory.value}');">${parentCategory.name}(CLOSE)</span>
					</td>
					<td id="<portlet:namespace/>${parentCategory.value}_childrenTd" style="display: none;" colspan="1">
						<c:set value="${parentCategory.value}" var="parentCategoryValue"/>
						<c:forEach items="${childrenCategoryGroupMap[parentCategoryValue]}" var="childrenCategory">
							<c:set value="${parentCategory.value}_${childrenCategory.value}_Children_Category" var="childrenCategoryName"/>
							<aui:input name="childrenCategory" id="${childrenCategoryName}" label="${childrenCategory.name}" value="${parentCategory.value}_${childrenCategory.value}" type="checkbox"/>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${not empty simulationProjectId}">
				<c:choose>
					<c:when test="${fn:length(data.scienceAppList) eq 0 || fn:length(data.scienceAppList) eq 1}">
						<tr id="useApp">
							<th>
								<liferay-ui:message key='edison-simulation-project-use-app' /><br>
								<input type="button" value="<liferay-ui:message key='edison-simulation-project-app-management' />" class="button06" onClick="<portlet:namespace/>scienceAppManagementOpen();" style="cursor:pointer;"/>
							</th>
							<c:if test="${fn:length(data.scienceAppList) eq 0}">
								<td colspan="2"></td>
							</c:if>
							<c:if test="${fn:length(data.scienceAppList) eq 1}">
								<c:forEach var="scienceApp" items="${data.scienceAppList}" varStatus="status">
									<td colspan="2">
										<div style="float:left;  width: 50%;">${scienceApp.scienceAppName}</div>
										<div style="float:left;  width: 25%;">${scienceApp.scienceAppVersion}</div>
										<div style="float:left;  width: 25%;">${scienceApp.userFirstName}</div>
										<input type="hidden" name="<portlet:namespace/>scienceAppId" value="${scienceApp.scienceAppId}" />
									</td>
								</c:forEach>
							</c:if>
						</tr>						
					</c:when>					
					<c:otherwise>
						<tr id="useApp">
							<th rowspan="${fn:length(data.scienceAppList)+1}" >
								<liferay-ui:message key='edison-simulation-project-use-app' /><br>
								<input type="button" value="<liferay-ui:message key='edison-simulation-project-app-management' />" class="button06" onClick="<portlet:namespace/>scienceAppManagementOpen();" style="cursor:pointer;"/>
							</th>
						</tr>
						<c:forEach var="scienceApp" items="${data.scienceAppList}" varStatus="status">
							<tr class="useAppList">
								<td colspan="2">
									<div style="float:left;  width: 50%;">${scienceApp.scienceAppName}</div>
									<div style="float:left;  width: 25%;">${scienceApp.scienceAppVersion}</div>
									<div style="float:left;  width: 25%;">${scienceApp.userFirstName}</div>
									<input type="hidden" name="<portlet:namespace/>scienceAppId" value="${scienceApp.scienceAppId}" />
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</c:if>
		</table>      	
    </aui:form>  	
</div>

<c:if test="${not empty simulationProjectId}">
	<div class="h10"></div>
	<div>
		<liferay-portlet:runtime portletName="edisonrelateasset_WAR_edisondefault2016portlet" defaultPreferences="" queryString="&entryId=${data.entryId}&isMgrBtn=true&isVirTitle=true"/>
	</div>

	<div class="h10"></div>
	<div style="margin-bottom: 60px;">
		<div class="virtitlebox">
			<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
			<div class="virtitle">
				<liferay-ui:message key='edison-simulation-project-member-management' />
			</div>
		</div>
	</div>		
	<div class="tabletopbox01">
		<div class="search">
			<div class="searchbox">
				<input type="text" id="<portlet:namespace/>searchText"  name="<portlet:namespace/>searchText" 
					value="${searchText}" onkeypress="if(event.keyCode==13)<portlet:namespace/>getRequestMemberList(1);" placeholder="<liferay-ui:message key='edison-simulation-project-member-placeholder'/>" />
				<input type="button" onClick="<portlet:namespace/>getRequestMemberList(1);" class="btnsearch" />
			</div>
			<input type="button" value="<liferay-ui:message key='edison-button-all-search' />" onClick="<portlet:namespace/>searchAllClick();" class="button03">
		</div>	
		<div class="tabletopright">
			<select id="<portlet:namespace/>searchRequestState" name="<portlet:namespace/>searchRequestState" onchange="<portlet:namespace/>getRequestMemberList(1);" class="selectview">
				<option value=""><liferay-ui:message key='full' /></option>
				<option value="2003001" <c:if test="${searchRequestState == '2003001' }"> selected="selected"</c:if>><liferay-ui:message key='edison-simulation-project-join-request' /></option>
				<option value="2003002" <c:if test="${searchRequestState == '2003002' }"> selected="selected"</c:if>><liferay-ui:message key='edison-virtuallab-approve' /></option>				
			</select>
		</div>
	</div>
		
	<div class="table1_list borderno">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" >
			<colgroup id="boardColgroup">
				<col width="8%" />
				<col/>
				<col width="12%" />
				<col width="12%" />
				<col width="12%" />
				<col width="12%" />
				<col width="12%" />
			</colgroup>
			<thead>
			<tr>
				<th><liferay-ui:message key='edison-table-list-header-index' /></th>
				<th><liferay-ui:message key='edison-table-list-header-name' /></th>
				<th><liferay-ui:message key='edison-table-list-header-userid' /></th>
				<th><liferay-ui:message key='edison-table-list-header-orgNm' /></th>
				<th><liferay-ui:message key='edison-virtuallab-tablerow-request-date' /></th>
				<th><liferay-ui:message key='edison-simulation-project-change-date' /></th>
				<th><liferay-ui:message key='edison-simulation-project-step' /></th>
				<th></th>
			</tr>
			</thead>
			<tbody id="<portlet:namespace/>requestMemberListBody">
			</tbody>
		</table>
	</div>
	<div id="<portlet:namespace/>paging" class="paging"></div>
</c:if>	

<script type="text/javascript">
var selectEntryArr = [];

AUI().ready(function() {
	
	var simulationProjectId = "${simulationProjectId}";
	if(simulationProjectId != ""){
		changeProjectOpenYn('${data.projectOpenYn}');
		changeCategory('${data.parentCategory}','${data.childrenCategory}');
		changeLanguage('${data.targetLanguage}');
		<portlet:namespace/>getRequestMemberList(1);
	}
	
});

function <portlet:namespace/>scienceAppManagementOpen() //Relate AssetEntry 팝업 띄우기
{
	var scienceAppIdList = new Array();
	$('input[name="<portlet:namespace/>scienceAppId"]').each(function() {
		scienceAppIdList.push($(this).val());
	});
	
	var scienceAppIdListJson = "";
	if(scienceAppIdList.length > 0 ){
		var numArr = [];
		$.each(scienceAppIdList, function(i){
			numArr.push(Number(this));
		});
		scienceAppIdListJson = JSON.stringify(numArr);
	}
	
	var URL = '<%=scienceAppManagementURL.toString()%>' 
				+ '&<portlet:namespace/>scienceAppIdListJson='+scienceAppIdListJson;
				
	Liferay.Util.openWindow({
		dialog: {
			cache: false,
          	destroyOnClose: true,
			after: {
				render: function(event) {
					$("button.btn.close").on("click", function(e){
						$("body").css('overflow','');
						
						<portlet:namespace/>drawSimulationProjectScienceAppLink(selectEntryArr);
					});
				}
			},
			on: {
				close: function(event) {
					$("body").css('overflow','');
				}
			},  
			centered: true,
			modal: true,
			resizable: false,
			width:1000, 
			height:720
		},
		title: Liferay.Language.get("edison-workflow-science-app"),
		uri : URL,
		dialogIframe: {
			on: {
				load : function(evt) {
					$("body").css('overflow','hidden');
				}
			}
		}
	});  

}

function <portlet:namespace/>validationSimulationProject(mode){
	
	var check = /[^가-힣A-Za-z0-9!\"#$%&\'()*+,-./:;<=>?@\[\\\]^_`{|}~\s]/ ;
	
	if(mode == 'create'){
		//제목의 다국어 필드중 하나라도 입력 되어 있는지 체크
		var id = "<portlet:namespace/>title_";
		
		var localeStr = "<%=localesStr%>";
		var localeArray = localeStr.split(",");
		var noneTitleCount = 0;
		
		for(var i=0; i< localeArray.length; i++){
			var id = "<portlet:namespace/>title_" + localeArray[i];
			var inputValue = $("input[name="+id+"]").val();
			if(inputValue == "" || inputValue == undefined){
				noneTitleCount++;
			}else{
				if(check.test(inputValue)){
					alert(Liferay.Language.get('edison-simulation-project-title-exception-msg'));
					$("#<portlet:namespace/>title").focus();
					return false;
				}				
			}
			
			if(i == localeArray.length - 1 ){
				if(noneTitleCount >= localeArray.length){
					alert("<liferay-ui:message key='edison-board-enter-title-alert' />");
					$("#<portlet:namespace/>title").focus();
					return false;
				}
			}
		}
	}else{
		var $projectOpenYn = $("select[id='<portlet:namespace/>projectOpenYn']");
		if($projectOpenYn.val() == "true"){	//공개
			
			$targetLanguage = $("#<portlet:namespace/>targetLanguage option:selected");
			var lanuageCode = $targetLanguage.val();
			
			//다국어 필드 체크
			if(lanuageCode==""){
				var localeStr = "<%=localesStr%>";
				var localeArray = localeStr.split(",");
				
				for(var i=0; i< localeArray.length; i++){
					var titleId = "<portlet:namespace/>title_" + localeArray[i];
					var explainId = "<portlet:namespace/>explain_" + localeArray[i];
					var titleValue = $("input[name="+titleId+"]").val();
					var explainValue = $("input[name="+explainId+"]").val();
					
					if(titleValue == "" || titleValue == undefined){
						alert(Liferay.Language.get('edison-error-another-language-alret-message'));
						$("#<portlet:namespace/>title").focus();
						return false;
					}else{
						if(check.test(titleValue)){
							alert(Liferay.Language.get('edison-simulation-project-title-exception-msg'));
							$("#<portlet:namespace/>title").focus();
							return false;
						}
					}
					
					if(explainValue == "" || explainValue == undefined){
						alert(Liferay.Language.get('edison-error-another-language-alret-message'));
						$("#<portlet:namespace/>explain").focus();
						return false;
					}
				}
			}else{
				var titleId = "<portlet:namespace/>title_" + lanuageCode;
				var explainId = "<portlet:namespace/>explain_" + lanuageCode;
				var titleValue = $("input[name="+titleId+"]").val();
				var explainValue = $("input[name="+explainId+"]").val();
				
				if(titleValue == "" || titleValue == undefined){
					alert(Liferay.Language.get('edison-error-another-language-alret-message'));
					$("#<portlet:namespace/>title").focus();
					return false;
				}else{
					if(check.test(titleValue)){
						alert(Liferay.Language.get('edison-simulation-project-title-exception-msg'));
						$("#<portlet:namespace/>title").focus();
						return false;
					}
				}
				
				if(explainValue == "" || explainValue == undefined){
					alert(Liferay.Language.get('edison-error-another-language-alret-message'));
					$("#<portlet:namespace/>explain").focus();
					return false;
				}
			}
			
			//대표이미지 validation
			var currImage = $(".projectImageList");
			if(currImage.length == 0){
				var project_image = $("input[name=<portlet:namespace/>project_image]");
				var project_image_count = 0;
				
				project_image.each(function() {
					var project_image_val = $(this).val();
					if(!(project_image_val == "" || project_image_val == undefined)){
						project_image_count++;
					}
				});
				
				if(project_image_count == 0){
					alert(Liferay.Language.get('edison-simulation-project-image-exception-msg'));
					return false;
				}
			}
			
			//아이콘 validation
			var currIcon = $("#<portlet:namespace/>simProIcon");
			if(currIcon.length == 0){
				var project_icon = $("#<portlet:namespace/>project_icon").val();
				if(project_icon == "" || project_icon == undefined){
					alert(Liferay.Language.get('edison-simulation-project-icon-exception-msg'));
					return false;
				}
			}
			
		}else{	//비공개
			//제목 validation
			var id = "<portlet:namespace/>title_";
			
			var localeStr = "<%=localesStr%>";
			var localeArray = localeStr.split(",");
			var noneTitleCount = 0;
			for(var i=0; i< localeArray.length; i++){
				var id = "<portlet:namespace/>title_" + localeArray[i];
				var inputValue = $("input[name="+id+"]").val();
				if(inputValue == "" || inputValue == undefined){
					noneTitleCount++;
				}else{
					if(check.test(inputValue)){
						alert(Liferay.Language.get('edison-simulation-project-title-exception-msg'));
						$("#<portlet:namespace/>title").focus();
						return false;
					}				
				}
				
				if(i == localeArray.length - 1 ){
					if(noneTitleCount >= localeArray.length){
						alert(Liferay.Language.get('edison-board-enter-title-alert'));
						$("#<portlet:namespace/>title").focus();
						return false;
					}
				}
			}
		}
	}
	
	if( $(":checkbox[name*='childrenCategoryCheckbox']:checked").length==0 ){
		alert(Liferay.Language.get('edison-science-appstore-category-error'));
		return false;
	}
	
	return true;
}

function <portlet:namespace/>submitSimulationProject(mode){
	
		var validation = <portlet:namespace/>validationSimulationProject(mode);
		
		if(validation){
			<portlet:namespace/>frm.encoding = "multipart/form-data";
			if(mode == 'create'){
				$("#<portlet:namespace/>frm").attr("action", "<%=createSimulationProjectUrl%>" );
			}else if(mode == 'update'){
				$("#<portlet:namespace/>frm").attr("action", "<%=updateSimulationProjectUrl%>" );
			}
			
			$("input[name=<portlet:namespace/>childrenCategory]").prop("disabled",true);
			submitForm(<portlet:namespace/>frm);
		}
}

function <portlet:namespace/>deleteSimulationProject(){
	if(confirm(Liferay.Language.get('edison-simulation-project-delete-alert') )){
		var deleteActionURL= "<%=deleteSimulationProjectUrl%>";
		$("#<portlet:namespace/>frm").attr("action", deleteActionURL);
		submitForm(<portlet:namespace/>frm);
	}
}

function changeLanguage(val){
	var $tLanguage = $("select[id='<portlet:namespace/>targetLanguage']");
	if(val.indexOf(",")>-1){
		$tLanguage.val("");
	}else{
		$tLanguage.val(val);
	}
}

function changeCategory(parentCategory,childrenCategory){
	var childrenArray = childrenCategory.split(',');
	var parentCategoryId = "";
	var parentCategoryArray = new Array();
	for(var i=0; i< childrenArray.length; i++){
		$("input:checkbox[value="+childrenArray[i]+"]").attr("checked",true);
	}
	
	var parentArray = parentCategory.split(',');
	for(var i=0; i< parentArray.length; i++){
		<portlet:namespace/>openRootCategory("OPEN",parentArray[i]);
	}
}

function <portlet:namespace/>openRootCategory(status,rootCatogoryId) {
	$parentTd = $("#<portlet:namespace/>"+rootCatogoryId+"_parentTd");
	$childrenTd = $("#<portlet:namespace/>"+rootCatogoryId+"_childrenTd");
	
	var hiddenSpan;
	var showSpan;
	if(status=="OPEN"){
		hiddenSpan = $("#<portlet:namespace/>"+rootCatogoryId+"_parent_open");
		showSpan 	= $("#<portlet:namespace/>"+rootCatogoryId+"_parent_close");
		
		$parentTd.removeAttr("colspan");
		$childrenTd.show();
	}else{
		hiddenSpan = $("#<portlet:namespace/>"+rootCatogoryId+"_parent_close");
		showSpan 	= $("#<portlet:namespace/>"+rootCatogoryId+"_parent_open");
		
		$childrenTd.hide();
		$parentTd.attr("colspan","2");
	}
	
	hiddenSpan.hide();
	showSpan.show();
}

function changeProjectOpenYn(val){
	var $projectOpenYn = $("select[id='<portlet:namespace/>projectOpenYn']");
	if(val != ""){
		$projectOpenYn.val(val);
	}
	changeProjectOpenYnState();
}

function changeProjectOpenYnState(){
	var $projectOpenYn = $("select[id='<portlet:namespace/>projectOpenYn']");
	var projectOpenYn = $projectOpenYn.val();
	if(projectOpenYn == "true"){
		$(".simproStateOpen").css("display", "");
	}else{
		$(".simproStateOpen").css("display", "none");
	}
}

function <portlet:namespace/>drawSimulationProjectScienceAppLink(scienceAppIdList)
{	
	var scienceAppList = <portlet:namespace/>getScienceAppList(scienceAppIdList);
	
	$(".useAppList").remove();
	$("#useApp").empty();
	
	if(scienceAppList.length > 1) {
		
		$th = $("<th/>").attr("rowspan", scienceAppList.length+1)
						.text("<liferay-ui:message key='edison-simulation-project-use-app' />")
						.append("<br>")
						.append($("<input/>").attr("type","button")
											 .attr("onclick", "<portlet:namespace/>scienceAppManagementOpen();")
											 .addClass("button06")
											 .css("cursor", "pointer")
											 .val("<liferay-ui:message key='edison-simulation-project-app-management' />")
											 );
		$('#useApp').append($th);
		
		for(var i = 0; i < scienceAppList.length; i++){
			$tr = $("<tr/>").addClass("useAppList");
			$td = $("<td/>").attr("colspan", "2");
			$("<div/>").css("float", "left")
					   .css("width", "50%")
					   .text(scienceAppList[i].scienceAppName)
					   .appendTo($td);
			$("<div/>").css("float", "left")
					   .css("width", "25%")
					   .text(scienceAppList[i].scienceAppVersion)
					   .appendTo($td);
			$("<div/>").css("float", "left")
					   .css("width", "25%")
					   .text(scienceAppList[i].userFirstName)
					   .appendTo($td);
			$("<input/>").attr("type", "hidden")
						 .attr("name", "<portlet:namespace/>scienceAppId")
						 .val(scienceAppList[i].scienceAppId)
						 .appendTo($td);
			$td.appendTo($tr);
			$('#useApp').after($tr);
		}	
	}else if(scienceAppList.length == 1){
		
		$th = $("<th/>").attr("rowspan", 1)
						.text("<liferay-ui:message key='edison-simulation-project-use-app' />")
						.append("<br>")
						.append($("<input/>").attr("type","button")
											 .attr("onclick", "<portlet:namespace/>scienceAppManagementOpen();")
											 .addClass("button06")
											 .css("cursor", "pointer")
											 .val("<liferay-ui:message key='edison-simulation-project-app-management' />")
								);
		$('#useApp').append($th);
		
		for(var i = 0; i < scienceAppList.length; i++){
			$td = $("<td/>").attr("colspan", "2");
			$("<div/>").css("float", "left")
				   .css("width", "50%")
				   .text(scienceAppList[i].scienceAppName)
				   .appendTo($td);
			$("<div/>").css("float", "left")
				   .css("width", "25%")
				   .text(scienceAppList[i].scienceAppVersion)
				   .appendTo($td);
			$("<div/>").css("float", "left")
				   .css("width", "25%")
				   .text(scienceAppList[i].userFirstName)
				   .appendTo($td);
			$("<input/>").attr("type", "hidden")
					 .attr("name", "<portlet:namespace/>scienceAppId")
					 .val(scienceAppList[i].scienceAppId)
					 .appendTo($td);
			$("#useApp").append($td);
		}		
		
	}else{
		$th = $("<th/>").attr("rowspan", 1)
		.text("<liferay-ui:message key='edison-simulation-project-use-app' />")
		.append("<br>")
		.append($("<input/>").attr("type","button")
							 .attr("onclick", "<portlet:namespace/>scienceAppManagementOpen();")
							 .addClass("button06")
							 .css("cursor", "pointer")
							 .val("<liferay-ui:message key='edison-simulation-project-app-management' />")
				);
		$('#useApp').append($th);
		
		$td = $("<td/>").attr("colspan", "2");
		$('#useApp').append($td);
	}
}

function <portlet:namespace/>getScienceAppList(scienceAppIdList){
	var scienceAppList;
	var deleteForm = {
			"<portlet:namespace/>scienceAppIdList" : scienceAppIdList
			};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=selectScienceAppListURL%>",
		data: deleteForm,
  		async : false,
		success: function(data) {
			scienceAppList = data.scienceAppList;
		},error:function(data,e){ 
		}
	});
	
	return scienceAppList;
}

function <portlet:namespace/>setselectEntryArr(entryArr){
	selectEntryArr = entryArr;	
}

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

function <portlet:namespace/>deleteIconFile(p_fileEntryId, objectClass){
	if(!confirm(Liferay.Language.get('file-delete-confirm'))) return;
	var deleteForm = {
			"<portlet:namespace/>fileEntryId" : p_fileEntryId
			};
	
	jQuery.ajax({
		type: "POST",
			url: "<%=deleteIconFileURL%>",
		data: deleteForm,
  		async : false,
		success: function(data) {
			alert(Liferay.Language.get('edison-data-delete-success'));
			$("."+objectClass).remove();
			
		},error:function(data,e){ 
			alert("deleteFile System error!");	
		}
	});
}

function <portlet:namespace/>deleteImageFile(p_fileEntryId){
	if(!confirm(Liferay.Language.get('file-delete-confirm'))) return;
	var deleteForm = {
			"<portlet:namespace/>deleteFileEntryId" : p_fileEntryId,
			"<portlet:namespace/>iconId" : "${data.iconId}"
			};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteImageFileURL%>",
		data: deleteForm,
  		async : false,
		success: function(data) {
			alert(Liferay.Language.get('edison-data-delete-success'));
			var projectImageList = data.projectImageList;
			
			$("#<portlet:namespace/>fileListDiv").empty();
			
			if(projectImageList == "undefinded" || projectImageList.length == 0){
			}else{
				
				for(var i = 0 ; i < projectImageList.length; i++ ){
					$rowResult = $("<div/>");
					$("<div/>").addClass("down_date")
							   .attr("onClick", "<portlet:namespace/>fileDownload('"+projectImageList[i].imageId+"');")
							   .text(projectImageList[i].imageTitle)
							   .css("cursor", "pointer")
							   .css("display", "inline-block")
							   .appendTo($rowResult);
					$("<img/>").attr("src", "${contextPath}/images/icon_dustbin.png")
							   .attr("onClick", "<portlet:namespace/>deleteImageFile('"+projectImageList[i].imageId+"');")
							   .width(12)
							   .height(14)
							   .css("cursor", "pointer")
							   .appendTo($rowResult);
					
					$("#<portlet:namespace/>fileListDiv").append($rowResult);
				}
			}
		},error:function(data,e){ 
			alert("deleteFile System error!");	
		}
	});
}

var fileIndex = 0;
function <portlet:namespace/>moreFileTag()
{	
	fileIndex++;
	var frmTag = "<div id=\"<portlet:namespace/>fileDiv"+fileIndex+"\">";
	frmTag += "<input type=\"file\" name=\"<portlet:namespace/>project_image\" />&nbsp;";
	frmTag += "<input type=\"button\" value=\"delete\" style=\"cursor:pointer;\" class=\"button06\" onClick=\"<portlet:namespace/>deleteFileTag(\'<portlet:namespace/>fileDiv"+fileIndex+"\')\" />";
	frmTag += "</div>";
	
	$("#<portlet:namespace/>fileTDArea").append(frmTag);
}

function <portlet:namespace/>deleteFileTag(objId){	
	$("#"+objId).remove();
}

function <portlet:namespace/>getRequestMemberList(p_currentPage){
	var dataForm = {
					"<portlet:namespace/>simulationProjectId" : "${simulationProjectId}",			
					"<portlet:namespace/>methodName" : "<portlet:namespace/>getRequestMemberList",
					"<portlet:namespace/>currentPage" : p_currentPage,
					"<portlet:namespace/>searchText" : $("#<portlet:namespace/>searchText").val(),
					"<portlet:namespace/>searchRequestState" : $("#<portlet:namespace/>searchRequestState option:selected").val()
					};
	jQuery.ajax({
		type: "POST",
		url: "<%=getRequestMemberListURL%>",
		data: dataForm,
  		async : false,
		success: function(data) {
			var requestMemberList = data.requestMemberList;		
			
			$("#<portlet:namespace/>requestMemberListBody tr:not(:has(#1))").remove();			
			$vRow = $("<tr/>");
			
			if(requestMemberList.length == 0){
				$("<td/>").attr("colspan", "8")
				  .css("height", "40px")
				  .addClass("TC")
				  .html("<liferay-ui:message key='edison-there-are-no-data' />")
				  .appendTo($vRow);				

				$("#<portlet:namespace/>requestMemberListBody").append($vRow);
			}else{
				
				for(var i = 0 ; i < requestMemberList.length; i++ ){
					
					$vRow = $("<tr/>").addClass("onMouseHover");
					
 					if(i%2 == 1){
 						$vRow.addClass("tablebgtr");
 					}
					
					$("<td/>").text(data.seq-i)
							  .addClass("TC")
							  .appendTo($vRow);
					$("<td/>").html(requestMemberList[i].firstName)
							  .addClass("TC")
							  .appendTo($vRow);
					$("<td/>").html(requestMemberList[i].screenName)
							  .addClass("TC")
							  .appendTo($vRow);
					$("<td/>").html(requestMemberList[i].universityFieldNm )
							  .addClass("TC")
							  .appendTo($vRow);
					$("<td/>").html(requestMemberList[i].requestDate )
							  .addClass("TC")
							  .appendTo($vRow);
					$("<td/>").html(requestMemberList[i].processDate )
					          .addClass("TC")
					          .appendTo($vRow);
					$("<td/>").html(requestMemberList[i].requestStateNm )
			                  .addClass("TC")
			                  .appendTo($vRow);
					if(requestMemberList[i].requestState == "2003001"){
						$vtd = $("<td/>").addClass("TC");
						$("<input></input>").attr("type", "button")
											.addClass("btn_blueg")
											.val("<liferay-ui:message key='edison-virtuallab-approve'/>")
											.attr("onclick", "<portlet:namespace/>changeRequestState('"+requestMemberList[i].requestSeq+"', '2003002', '"+
													requestMemberList[i].userId+"', '"+p_currentPage+"');")
											.appendTo($vtd);
						$vtd.append("&nbsp;");
						$("<input></input>").attr("type", "button")
											.addClass("btn_blueg")
											.val("<liferay-ui:message key='edison-button-board-delete'/>")
											.attr("onclick", "<portlet:namespace/>deleteRequestMember('"+requestMemberList[i].requestSeq+"');")
											.appendTo($vtd);
						$vtd.appendTo($vRow);
					}else if(requestMemberList[i].requestState == "2003002"){
						$vtd = $("<td/>").addClass("TC");
						$("<input></input>").attr("type", "button")
											.addClass("btn_blueg")
											.val("<liferay-ui:message key='edison-default-site-join-leave'/>")
											.attr("onclick", "<portlet:namespace/>changeRequestState('"+requestMemberList[i].requestSeq+"', '2003001', '"+
													requestMemberList[i].userId+"', '"+p_currentPage+"');")
											.appendTo($vtd);
						$vtd.appendTo($vRow);          
					}
					
					$("#<portlet:namespace/>requestMemberListBody").append($vRow);
				}
			}
			
			$("#<portlet:namespace/>paging").html(data.paging);
			
		},error:function(data,e){ 
			alert("list:::requestMemberList===>"+e);
		},complete:function(){
			//boardSearchList("1",divCd);
		}
	});
}

function <portlet:namespace/>searchAllClick(){
	
	$("#<portlet:namespace/>searchText").val("");
	$("#<portlet:namespace/>searchRequestState").val("");
	<portlet:namespace/>getRequestMemberList(1);
}

function <portlet:namespace/>changeRequestState(requestSeq, requestState, requestUserId, p_currentPage){
	
	var dataForm = {
			"<portlet:namespace/>requestSeq" : requestSeq,
			"<portlet:namespace/>simulationProjectId" : "${simulationProjectId}",
			"<portlet:namespace/>requestState" : requestState,
			"<portlet:namespace/>requestUserId" : requestUserId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=updateSimulationProjectMemberURL%>",
		data: dataForm,
  		async : false,
		success: function(data) {
			if(requestState == "2003002"){
				alert(Liferay.Language.get('edison-simulation-project-join-request-approve-msg'));
			}else{
				alert(Liferay.Language.get('edison-simulation-project-join-request-leave-msg'));
			} 
			<portlet:namespace/>getRequestMemberList(p_currentPage);
		},error:function(data,e){ 
			alert("update Member System error!");	
		}
	});
}

function <portlet:namespace/>deleteRequestMember(requestSeq){
	if(!confirm(Liferay.Language.get('edison-simulation-project-join-requestdelete-alert'))) return;
	
	var dataForm = {
			"<portlet:namespace/>requestSeq" : requestSeq,
			"<portlet:namespace/>simulationProjectId" : "${simulationProjectId}"
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=deleteSimulationProjectMemberURL%>",
		data: dataForm,
  		async : false,
		success: function(data) {
			alert(Liferay.Language.get('edison-simulation-project-join-request-delete-msg'));
			<portlet:namespace/>getRequestMemberList(1);
		},error:function(data,e){ 
			alert("delete Member System error!");	
		}
	});
}

function <portlet:namespace/>goDetailView(){	
	location.href = "<%=detailViewURL%>";
	
}

function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}
</script>
	
