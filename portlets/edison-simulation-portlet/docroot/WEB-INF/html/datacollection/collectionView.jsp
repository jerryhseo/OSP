<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<script src="${contextPath}/js/owl-carousel/owl.carousel.min.js"></script>
<link href="${contextPath}/css/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/jquery.imageview.css" rel="stylesheet" type="text/css" />
<script src="<c:url value="/js/jquery.imageview.js" />"></script>
<style>
.owl-item {
	width: 260px !important;
	height: 140px;
}

.owl-item .contentbox {
	width: 260px !important;
	height: 140px;
}

#collectionImageArea {
	height: 72px;
	margin: 0 auto;
}

#collectionImageArea {
	width: 760px;
	padding-top: 30px;
}

.commleftbox div.owl-carousel {
	margin: 0;
	padding: 0;
}

.commleftbox div.owl-carousel div.owl-iteml {
	float: left;
	margin: 28px 20px;
	width: 234px;
	height: 140px;
	border-radius: 2px;
	background-color: #636d76;
}

.commleftbox div.owl-carousel div.owl-iteml:first-child {
	margin-left: 110px;
}

.shortcut {
/*     padding: 2px 20px 2px 15px; */
/*     height: 30px; */
    background: url(../images/swicon_02.png) no-repeat 13px 7px;
    background-color: #7a92aa;
/*     padding-left: 37px; */
    border-radius: 3px;
    -webkit-border-radius: 1px;
    border: solid 0px #6a6a6b;
    min-width: 70px;
    color: #fff;
    font-weight: 600;
    font-size: 15px;
    text-shadow: 0px 0px 0px #777;
}
.edison .table1_list a{
	color : #00aaff;
}
</style>


<liferay-portlet:renderURL var="detailViewDataCollectionURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" >
	<liferay-portlet:param name="myRender" value="detailViewDataCollection" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isAdmin" value="${isAdmin}" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchLine" value="${searchLine}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="manageDataCollectionURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>" >
	<liferay-portlet:param name="myRender" value="manageDataCollection" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="mode" value="<%=Constants.UPDATE %>" />
	<liferay-portlet:param name="collectionId" value="${collection.collectionId}" />
	<liferay-portlet:param name="isAdmin" value="${isAdmin}" />
	<liferay-portlet:param name="searchText" value="${searchText}" />
	<liferay-portlet:param name="currentPage" value="${currentPage}" />
	<liferay-portlet:param name="searchLine" value="${searchLine}" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="retrieveListAnalyzerURL" escapeXml="false" id="retrieveListAnalyzer" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="deleteDataEntryByCollectionURL" escapeXml="false" id="deleteDataEntryByCollection" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="Workbench_WAR_OSPWorkbenchportlet">
	<liferay-portlet:param name="workbenchType" value="DATACOLLECTION" />
	<liferay-portlet:param name="customId" value="0" />
	<liferay-portlet:param name="classId" value="0" />
	<liferay-portlet:param name="jobUuid" value="" />
	<liferay-portlet:param name="testYn" value="false" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="monitoringAnalysisURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="Workbench_WAR_OSPWorkbenchportlet" windowState="<%= LiferayWindowState.POP_UP.toString()%>" >
<liferay-portlet:param name="workbenchType" value="CONTENT" />
</liferay-portlet:renderURL>
<!--상단 비쥬얼 및 타이틀-->

<div class="topvisual">
	<div class="visualimg">
		<c:choose>
			<c:when test="${!empty dcIcon}">
				<img src="/documents/${dcIcon.fileRepositoryId }/${dcIcon.fileEntryId }/${dcIcon.fileTitle }/${dcIcon.fileUuid }?imageThumbnail=2" width="104px" height="78px">
			</c:when>
			<c:otherwise>
				<img src="${contextPath }/images/sciencedata/noimage.png" width="104" height="78">
			</c:otherwise>
		</c:choose>
	</div>
	<div class="visualtxt">${collection.name } ( V ${collection.version } )</div>
	<div class="visualtxt2">
		<c:if test="${redirectURL ne ''}"> 
			<span><a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName } </a>  > </span> Data Collection
		</c:if>
			
	</div>
</div>



<c:if test="${isAdmin == true}"> 
	<div class="topvisualbtnbox">  
		<aui:form action="<%=manageDataCollectionURL%>" method="post" name="updateCollectionForm">
			<input  type="submit" class="btn btn-default topbtn" style="width: 100px;" value="<liferay-ui:message key='edison-virtuallab-scienceapp-management'/>"  />
		</aui:form>
	</div>
</c:if>
		
<div class="h10"></div>
	
<!--좌측-->
<div class="commleft">
	<c:if test="${!empty dcMainImg}">
		<div class="commleftbox">
			<div id="collectionImageArea" class="owl-carousel owl-theme" style="display:table-cell;">
				<c:forEach items="${dcMainImg }" var="mainImg" >
					<div class="owl-item contentbox">
						<c:choose>
							<c:when test="${fn:contains(header['User-Agent'],'Safari/534.57.2')}">
								<img src="/documents/${mainImg.fileRepositoryId }/${mainImg.fileEntryId }/${mainImg.fileTitle }/${mainImg.fileUuid }?imageThumbnail=2"  width="240px" style="height: 140px;">
							</c:when>
							<c:otherwise>
								<a href="/documents/${mainImg.fileRepositoryId }/${mainImg.fileUuid}" title="${mainImg.fileTitle }">
								<img src="/documents/${mainImg.fileRepositoryId }/${mainImg.fileEntryId }/${mainImg.fileTitle }/${mainImg.fileUuid }?imageThumbnail=2"  width="240px" style="height: 140px;">
								</a>
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
			<c:if test="${fn:length(dcMainImg) > 3}">   
				<div class="cleft"><a id="rollingPrev" href="#rollingPrev" title="preview"><img src="${contextPath}/images/sciencedata/arrow_left.png" width="28" height="49"></a></div>
				<div class="cright"><a id="rollingNext" href="#rollingNext" title="nextview"><img src="${contextPath}/images/sciencedata/arrow_right.png" width="28" height="49"></a></div>
			</c:if>
		</div>
		<%-- <div  style="text-align: center"> <!-- class="commleftbox" -->
			<img src="/documents/${dcMainImg.fileRepositoryId }/${dcMainImg.fileEntryId }/${dcMainImg.fileTitle }/${dcMainImg.fileUuid }" width="250">
		</div> --%>
	</c:if>
	<!-- data type list -->
	<div class="virtitlebox">
		<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
		<div class="virtitle">
			<liferay-ui:message key="edison-data-collection-select-data-type-list"/>
		</div>
	</div>
	<div class="h10"></div>
	
	<div class="table1_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<colgroup>
			
				<c:if test="${dataTypeEditorMap.columnCnt == 1}">
					<col width="40%" />
					<col width="20%" />
					<col width="40%" />
				</c:if>
				
				<c:if test="${dataTypeEditorMap.columnCnt > 1}">
					<col width="30%" />
					<col width="20%" />
					<col width="25%" />
					<col width="25%" />
				</c:if>
			</colgroup>
			<thead>
				<th><liferay-ui:message key="edison-table-list-header-data-type-name"/></th>
				<th><liferay-ui:message key="edison-virtuallab-version"/></th>
							
				<c:if test="${!empty dataTypeEditorMap.editor}">
					<th class="TC">Editor</th>
				</c:if>
				<c:if test="${!empty dataTypeEditorMap.analyzer}">			
					<th class="TC">Analyzer</th>
				</c:if>
			</thead>
			<tbody>
				<tr>
					<c:if test="${!empty dataType}">
						<td class="TC">${dataType.name }</td>
						<td class="TC">${dataType.version }</td>
					</c:if>			
					<c:if test="${!empty dataTypeEditorMap.editor}">
						<td class="TC">
						<c:forEach var="editor" items="${dataTypeEditorMap.editor }">
							 ${editor.typeName} <br/>
						</c:forEach>
						</td>
					</c:if>
					<c:if test="${!empty dataTypeEditorMap.analyzer}">			
						<td class="TC">
							<c:forEach var="analyzer" items="${dataTypeEditorMap.analyzer }">
								${analyzer.typeName} <br/>
							</c:forEach>
						</td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!-- data entry list -->
	<div class="virtitlebox">
		<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
		<div class="virtitle">
			Data Entry 
		</div>
	</div>
	<div class="h10"></div>
	
	<div class="table1_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<colgroup>
				<c:choose>
					<c:when test="${isAdmin == true}">
						<col width="20%" />
						<col width="44%" />
						<col width="10%" />
						<col width="13%" />
						<col width="13%" />
					</c:when>
					<c:otherwise>
						<col width="30%" />
						<col width="45%" />
						<col width="10%" />
						<col width="15%" />
					</c:otherwise>
				</c:choose>
			</colgroup>
			<thead>
				<tr>
					<th><liferay-ui:message key="edison-table-list-header-file-nm"/></th>
					<th><liferay-ui:message key="edison-simulation-execute-simulation-description"/></th>
					<th><liferay-ui:message key="edison-table-list-header-download"/></th>
					<th><liferay-ui:message key="edison-table-list-header-run"/></th>
					<c:if test="${isAdmin == true}">
						<th><liferay-ui:message key="edison-button-board-delete"/></th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(dataEntryList) ==0 }">
						<tr>
							<td class="TC" colspan="5"><liferay-ui:message key="edison-there-are-no-data"/></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${dataEntryList }" var="dataEntry" varStatus="status">
							<tr>
								<td class="TC"><a href="#;return false;" onclick="<portlet:namespace/>openWorkbenchAnalysisDialogue('${dataType.typeId}', '${dataEntry.entryId}', '/documents/${dataEntry.repositoryId}/${dataEntry.uuid}')" >${dataEntry.title }</a></td>
								<td style="word-break: break-word;">${dataEntry.comments }</td>
								<td class="TC">
									<img src="${contextPath}/images/file_download_icon.png" style="cursor:pointer;" onClick="<portlet:namespace/>fileDownload('${dataEntry.fileEntryId }')"/>
								</td>
								<td class="TC">
								<c:if test="${dataEntry.simulationSubjectId != '0'}">
									<img src="${contextPath}/images/btn_simulrun.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>moveWorkbench('${collection.name } ','${dataEntry.entryId }','${dataEntry.simulationSubjectId}')"/>
<%-- 									<img src="${contextPath}/images/sciencedata/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>moveWorkbench('${dataEntry.entryId }','${dataEntry.simulationSubjectId}')"/> --%>
								</c:if>
								</td>
								
								<c:if test="${isAdmin == true}">
									<td>
										<input type="button" value="삭제 " class="button06" onclick="<portlet:namespace/>deleteDataEntryByCollection('${dataEntry.entryId}')">
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<!-- 관련정보 -->
	<div class="relatedAssetPortlet"> 
		<liferay-portlet:runtime portletName="edisonrelateasset_WAR_edisondefault2016portlet" 
		defaultPreferences="" 
		queryString="&entryId=${entryId}&isMgrBtn=false&isVirTitle=true&redirectURL=${redirectURL }&redirectName=${redirectName }"/>
	</div> 
</div>

<!--우측-->
<div class="commright">
	<div class="commrighttxt">
		<ul>
			<li>
				<liferay-ui:message key="edison-table-list-header-title"/>
			</li>
			<li class="stxt">
				${collection.title } 
			</li>
		</ul>
	</div>
	<div class="commrighttxt">
		<ul>
			<li>
				<liferay-ui:message key="edison-simulation-execute-simulation-description"/>
			</li>
			<li class="stxt">
				<div style="word-wrap: break-word;max-height:200px; overflow-y: auto">
					<c:forEach var="description" items="${collection.description }" varStatus="status">
						<p style="margin: 0px">${description}</p>
					</c:forEach>
				</div>
			</li>
			<li>
				<liferay-ui:message key="edison-science-appstore-view-tab-category"/>
			</li>
			<li class="stxt">
				<!-- 카테고리 -->
				<c:forEach var="category" items="${categoryNameList }">
					${category } <br/>
				</c:forEach>
			</li>
		</ul>
	</div>
	<div>
		<liferay-portlet:runtime portletName="edisonrelateassetstatistic_WAR_edisonsimulationproject2017portlet" defaultPreferences="" queryString="&modelId=${entryId}"/>		
	</div>	
	
	<c:if test="${fn:length(collectionHistory) > 0 }">
	<div class="commrighttxt">
		<ul>
			<li>
				<liferay-ui:message key="history"/>
			</li>
			<c:forEach items="${collectionHistory }" var="history">
				<li class="stxt2"> ${history.version } </li>
				<li class="stxt2"> ${history.createDate } </li>
				<li class="stxt2" style="text-align: center">
					<input type="button" style="font-size:10px;"onclick="<portlet:namespace/>moveCollection('${history.collectionId }');" value="<liferay-ui:message key='edison-asset-button-shortcut' />"  class="shortcut" />
				</li>
			</c:forEach>
		</ul>
	</div>
	</c:if>
</div>

<div id="<portlet:namespace/>post-dialog" style="display:none; background-color:white; padding:0px;" class="newWindow">
	<div class="newWheader" id="<portlet:namespace/>post-dialog-title" style="cursor: move;">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle"><liferay-ui:message key="edison-simulation-monitoring-post-process-choice"/></div>
		</div>
		<div class="newWclose" style="cursor: pointer;">
			<img id="<portlet:namespace/>post-dialog-close-btn" name="<portlet:namespace/>jobparameter-dialog-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21">
		</div>
	</div>
	<div style="padding: 30px;overflow:auto; max-height:400px;" class="newWcont01">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1" style="word-break: break-all;table-layout: fixed;">
			<colgroup>
				<col width="300px" />
<!-- 				<col width="*" /> -->
			</colgroup>
			<thead>
				<tr>
<%-- 					<th scope="col" class="left"><liferay-ui:message key="edison-simulation-execute-port-label-portname"/></th> --%>
					<th scope="col" class="left"><liferay-ui:message key="edison-simulation-monitoring-post-process-nm"/></th>
				</tr>
			</thead>
			<tbody id="<portlet:namespace/>post-dialog-content" style="font-size: 25px;">
				
			</tbody>
		</table>
	</div>
</div>

<div id="<portlet:namespace/>show-analyzer-dialog">
	<div id="<portlet:namespace/>show-analyzer-dialog-content">
	</div>
</div>

<script type="text/javascript">

AUI().ready(function() {
	var rolling = $("#collectionImageArea");
	
	//rolling
	rolling.owlCarousel({
		items : 3,
		autoPlay : 8000,
		rewindSpeed : 2000,
		pagination : false
	});
	rolling.data('owlCarousel').reinit();
	
	$("#rollingPrev").click(function(e){
		e.preventDefault();
		rolling.trigger('owl.prev');
		rolling.trigger('owl.play',5000);
	});
	
	$("#rollingNext").click(function(e){
		e.preventDefault();
		rolling.trigger('owl.next');
		rolling.trigger('owl.play',5000);
	});
	
	$('#collectionImageArea').imageview({
			targetSelector: 'a',
			srcAttr: 'href',
			titleAttr: 'title'
	});
	
	var viewStatus = "${viewStatus}";
	if(viewStatus == "shortcuts"){
		$(".edison #wrap .content").css("width", "1220px").css("margin", "auto");
	}
	
	$("#<portlet:namespace/>post-dialog").dialog({
		autoOpen: false,
		width: 700,
		height: 'auto',
		modal: true,
		resizable: true,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
	    	$(this).removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget-content");
	    	$(this).parent().css('overflow','visible');
	    	
	    	$("body").css('overflow','hidden');
	    	
	    	$("#<portlet:namespace/>post-dialog-close-btn").bind('click',function(){
	    		$("#<portlet:namespace/>post-dialog").dialog("close");
	    	});
	    	
	    	$('.ui-widget-overlay').bind('click',function(){
	    		$("#<portlet:namespace/>post-dialog").dialog("close");
			})
	    },
	    close: function() {
	    	$("body").css('overflow','');
	    	$("#<portlet:namespace/>post-dialog-content").empty();
	    }
	}).draggable({
			handle: "#<portlet:namespace/>post-dialog-title"
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	
	
	$("#<portlet:namespace/>show-analyzer-dialog").dialog({
		autoOpen: false,
		width: 1200,
		height: 800,
		modal: true,
		resizable: false,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
			/* $('.ui-dialog-titlebar-close').bind('click',function(){
				$("#<portlet:namespace/>show-analyzer-dialog").dialog("close");
	    	});
			$('.ui-widget-overlay').bind('click',function(){
				$("#<portlet:namespace/>show-analyzer-dialog").dialog("close");
	    	}); */
	    },
	    close: function() {
// 			$("#<portlet:namespace/>show-analyzer-dialog").dialog("close");
			$("#<portlet:namespace/>show-analyzer-dialog-content").empty();
			location.reload();
	    }
	})

});

function <portlet:namespace/>openWorkbenchAnalysisDialogue(dataTypeId, dataEntryId, filePath){
	$.ajax({
		type:"POST",
		url : "<%=retrieveListAnalyzerURL%>",
		data : {
			"<portlet:namespace/>dataTypeId" : dataTypeId
		},
		async:false,
		success:function(data){
			var dataMap = data.analyzer;
			
			if(dataMap.length > 0){
				$dialogBody = $("#<portlet:namespace/>post-dialog-content");
			
				for(var i=0; i< dataMap.length; i++){
					$tr = $("<tr></tr>").addClass("postlist").attr("onClick",
							"event.cancelBubble=true;openAnalyzerWindow('" + 
							dataMap[i].exeFileName +"', '" + 
							dataMap[i].title+"', '" + 
							filePath+"');")
							.appendTo($dialogBody);
					$("<td></td>").addClass("TC").css("cursor","pointer").text(dataMap[i].name).appendTo($tr);
				}
				$("#<portlet:namespace/>post-dialog").dialog("open");
			}else{
				alert(Liferay.Language.get("edison-data-collection-not-mapping-analyzer"))
			}
		}
	})
}

function openAnalyzerWindow(portletId, portletTitle, filePath){
  	if($("#<portlet:namespace/>post-dialog").dialog('isOpen')){
  		$("#<portlet:namespace/>post-dialog").dialog("close");
  	}
	  	
	var param = "";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_pathType=url";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_url="+filePath;
		param += "&_Workbench_WAR_OSPWorkbenchportlet_contentId="+portletId;
// 		param += "&_Workbench_WAR_OSPWorkbenchportlet_parentPath="+filePath;
// 		param += "&_Workbench_WAR_OSPWorkbenchportlet_fileName="+fileName;xxx
// 		param += "&_Workbench_WAR_OSPWorkbenchportlet_portletId="+portletId;
		param += "&_Workbench_WAR_OSPWorkbenchportlet_height=0.73";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_relative=false";
		param += "&_Workbench_WAR_OSPWorkbenchportlet_loadNow=true";
		
		var URL = "<%=monitoringAnalysisURL%>" + param;
		$("#<portlet:namespace/>show-analyzer-dialog").load(URL).dialog('open');
	<%-- AUI().use("liferay-portlet-url", function(a) {
		var renderURL = Liferay.PortletURL.createRenderURL();
		renderURL.setPortletId( 'Workbench_WAR_OSPWorkbenchportlet');
		renderURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE.toString() %>');
	  	renderURL.setParameter("workbenchType", "MORANALYSIS");
		renderURL.setParameter("pathType", "file");
		
		renderURL.setParameter("parentPath", filePath);
		renderURL.setParameter("fileName", fileName);
		
		renderURL.setParameter("portletId", portletId);
		renderURL.setParameter("relative", false);
		renderURL.setParameter("loadNow", true);
		
		/* var url = renderURL.toString();
		//console.log( 'URL: ' + url );
		Liferay.Util.openWindow({
			dialog: {
				cache: false,
	          	destroyOnClose: true,
				centered: true,
				modal: true,
				resizable: false,
				width:1230, 
				height:850
			},
			id: portletId ,
			title: portletTitle,
			uri : url//+ '&p_p_auth='+ renderURL.params.token
		}); */
		
	}); --%>
	
}

function <portlet:namespace/>deleteDataEntryByCollection(dataEntryId){
	if(!confirm(Liferay.Language.get("edison-data-entry-delete-by-data-collection-alert"))){
		return false;
	}

	$.ajax({
		type:"POST",
		url : "<%=deleteDataEntryByCollectionURL%>",
		data : {
			"<portlet:namespace/>dataEntryId" : dataEntryId
		},
		async:false,
		success:function(data){
			var message = data.resultMsg;
			if(message == "SUCCESS"){
				alert(Liferay.Language.get("edison-data-delete-success"))
				location.reload();
			}else{
				alert("error ---> delete dataEntry");
			}
		},error:function(data){
			alert("error ---> delete dataEntry");
		}
	})
}

function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}

function <portlet:namespace/>moveCollection(collectionId){
	var URL = "<%=detailViewDataCollectionURL%>";
	URL += "&<portlet:namespace/>collectionId="+collectionId;
	location.href = URL;
}

function <portlet:namespace/>moveWorkbench(collectionName, entryId, simulationSubjectId){
	var thisPortletNamespace = "_Workbench_WAR_OSPWorkbenchportlet_";
	var params = "&" +thisPortletNamespace+ "scienceAppId=" + simulationSubjectId;
		params += "&" +thisPortletNamespace+ "entryId=" + entryId;
		params += "&" +thisPortletNamespace+ "collectionName=" + collectionName;
	
	location.href = "<%=workbenchURL %>" + params;	
}

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}
</script>