<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:renderURL var="managetmentSimulationProjectUrl"
	windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>"
	copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="management" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="isRedirect" value="${isRedirect}" />
	<liferay-portlet:param name="simulationProjectId"
		value="${simulationProjectId}" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="requestSimulationProjectMemberURL"
	escapeXml="false" id="requestSimulationProjectMember"
	copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>

<link href="${contextPath}/css/owl-carousel/owl.carousel.css"
	rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/owl-carousel/owl.theme.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="${contextPath}/css/main.css" media="screen" />
<link type="text/css" rel="stylesheet"
	href="${contextPath}/css/jquery.imageview.css" media="screen" />

<style type="text/css">
.owl-item {
	width: 260px !important;
	height: 140px;
}

.owl-item .contentbox {
	width: 260px !important;
	height: 140px;
}

#projectImageArea {
	height: 72px;
	margin: 0 auto;
}

#projectImageArea {
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
	width: 240px;
	height: 140px;
	border-radius: 2px;
	background-color: #636d76;
}

.commleftbox div.owl-carousel div.owl-iteml:first-child {
	margin-left: 110px;
}

.wbba {
	word-break: break-all;
}

.<portlet:namespace/>mprightcont .edison-comment{
	width: 100%;
}
</style>

<!--상단 비쥬얼 및 타이틀-->
<div class="topvisual">
	<div class="visualimg">
		<img
			src="/documents/${data.iconRepositoryId}/${data.iconId}/${data.iconTitle}/${data.iconUuid}?imageThumbnail=2"
			width="104px" style="height: 78px !important;"
			onerror="this.src='${contextPath}/images/comm_proj/noimage.png'">
	</div>
	<div class="visualtxt" id="projectTitle"></div>
	<div class="visualtxt2">
		<c:choose>
			<c:when test="${empty redirectName }">
				Simulation Project
			</c:when>
			<c:otherwise>
				<a onClick="<portlet:namespace/>historyBack()"
					style="cursor: pointer;"> ${redirectName} </a>  > Simulation Project
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="topvisualbtnbox">
	<c:if test="${isOwner eq true || isAdmin eq true}">
		<input type="button"
			value="<liferay-ui:message key='edison-simulation-project-manage-btn'/>"
			class="btn btn-default"
			onclick="<portlet:namespace/>goSimulationProjectManagement();" />
	</c:if>

	<c:if
		test="${isOwner eq false && isMember eq false && data.projectOpenYn eq true && isMemberRequest eq false && isTempUser eq false}">
		<input type="button" id="<portlet:namespace/>requestMemberBtn"
			value="<liferay-ui:message key='edison-simulation-project-join-btn'/>"
			class="btn btn-default"
			onclick="<portlet:namespace/>requestSimulationProjectMember();" />
	</c:if>

</div>

<c:set var="authYn" value="N" />
<c:if test="${isOwner eq true || isAdmin eq true || isMember eq true}">
	<c:set var="authYn" value="Y" />
</c:if>

<div class="commleft" style="padding: 0px 18px 0px 0px;">
	<c:if
		test="${data.projectOpenYn eq true && fn:length(data.projectImageList) > 0}">
		<div class="commleftbox">
			<div id="projectImageArea" class="owl-carousel owl-theme"
				style="display: table-cell;">
				<c:forEach var="projectImage" items="${data.projectImageList}">
					<div class="owl-item contentbox">
						<c:choose>
							<c:when test="${fn:contains(header['User-Agent'],'Safari/534.57.2')}">
								<img src="/documents/${projectImage.imageRepositoryId}/${projectImage.imageId}/${projectImage.imageTitle}/${projectImage.imageUuid}?imageThumbnail=2"
									width="240px" style="height: 140px;">
							</c:when>
							<c:otherwise>
								<a href="/documents/${projectImage.imageRepositoryId}/${projectImage.imageUuid}" title="${projectImage.imageTitle}"><img
								src="/documents/${projectImage.imageRepositoryId}/${projectImage.imageId}/${projectImage.imageTitle}/${projectImage.imageUuid}?imageThumbnail=2"
								width="240px" style="height: 140px;"></a>
							</c:otherwise>
						</c:choose>						
					</div>
				</c:forEach>
			</div>
			<c:if test="${fn:length(data.projectImageList) > 3}">
				<div class="cleft">
					<a id="rollingPrev" href="#rollingPrev" title="preview"><img
						src="${contextPath}/images/comm_proj/arrow_left.png" width="28"
						height="49"></a>
				</div>
				<div class="cright">
					<a id="rollingNext" href="#rollingNext" title="nextview"><img
						src="${contextPath}/images/comm_proj/arrow_right.png" width="28"
						height="49"></a>
				</div>
			</c:if>
		</div>
	</c:if>
	
	<!--사용 앱-->
	<div>
		<liferay-portlet:runtime
			portletName="edisonuseapp_WAR_edisonsimulationproject2017portlet"
			defaultPreferences=""
			queryString="&modelName=SimulationProject&modelId=${simulationProjectId}&authYn=${authYn}" />
	</div>
	
	<!--커멘트 -->
	<!--
		customId="project_"${simulationProjectId}
		&isMember=${isMember}   -> true or false
		&authYn=${authYn}   -> Y or N
		&modelId=${simulationProjectId}     -> 현재 프로젝트 모델의 ID(Long 데이터)
	-->
	<div class="<portlet:namespace/>mprightcont">
		<liferay-portlet:runtime
			portletName="edisoncomment_WAR_edisonboard2016portlet"
			defaultPreferences=""
			queryString="&customId=project_1&isMember=${isMember}&authYn=${authYn}&modelId=${simulationProjectId}" />
	</div>
	
	<!--관련자료 -->
	<div>
		<c:if test="${not empty data.entryId}">
			<liferay-portlet:runtime
				portletName="edisonrelateasset_WAR_edisondefault2016portlet"
				defaultPreferences=""
				queryString="&entryId=${data.entryId}&isVirTitle=true" />
		</c:if>
	</div>
	
</div>

<div class="commrighttop" style="border-radius: 0; width: 210px;">
	<div class="commtopbox" style="border-radius: 0; border: none;">
		<img src="${ownerInfo.portraitURL}" style="width:65px !important; height:65px !important;" >
	</div>
	<div class="commtopboxtxt" style="top: 7px; line-height: 1.2em;">
		<div>${ownerInfo.firstName}</div>
		<div>
			<span>${ownerInfo.screenName}</span>
		</div>
		<div class="ellipsis">
			<span>${ownerInfo.universityFieldNm}</span>
		</div>
	</div>
</div>

<!--우측-->
<div class="commright">

	<c:if test="${data.projectOpenYn eq true}">
		<div class="commrighttxt">
			<ul>
				<li><liferay-ui:message
						key='edison-workflow-conf-description-input' /></li>
				<li class="stxt2 wbba last" style="overflow-y: auto; max-height: 100px;">
					${data.currExplain}
				</li>
			</ul>
			<ul>
				<li><liferay-ui:message
						key='edison-science-appstore-view-tab-category' /></li>
				<c:if test="${data.childrenCategoryList ne null}">
					<c:forEach var="childrenCategory"
						items="${data.childrenCategoryList}" varStatus="status">
						<c:if test="${not status.last}">
							<li class="stxt2 wbba">${childrenCategory.name}</li>
						</c:if>
						<c:if test="${status.last}">
							<li class="stxt2 wbba last">${childrenCategory.name}</li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${data.childrenCategoryList eq null}">
					<li class="stxt2 wbba last"></li>
				</c:if>
			</ul>
		</div>
	</c:if>
	<!--관련자료 통계-->
	<div>
		<liferay-portlet:runtime
			portletName="edisonrelateassetstatistic_WAR_edisonsimulationproject2017portlet"
			defaultPreferences=""
			queryString="&modelId=${data.entryId}&authYn=${authYn}" />
	</div>
	<!--모니터링-->
	<c:if test="${authYn eq 'Y'}">
		<div>
			<liferay-portlet:runtime
				portletName="edisonmonitoring_WAR_edisonsimulationportlet"
				defaultPreferences=""
				queryString="&portletState=inSimulationProject&simulationClassId=${simulationClassId}&simulationCustomId=${simulationProjectId}" />
		</div>
	</c:if>
</div>

<script src="<c:url value="/js/owl-carousel/owl.carousel.min.js" />"></script>
<script src="<c:url value="/js/jquery.imageview.js" />"></script>
<script type="text/javascript">

AUI().ready(function() {
	
	$("#projectTitle").append(cutStr("${data.currTitle}", 50));
	
	var projectOpenYn = ${data.projectOpenYn};
	if(projectOpenYn){
		var rolling = $("#projectImageArea");
		
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
		
		$('#projectImageArea').imageview({
				targetSelector: 'a',
				srcAttr: 'href',
				titleAttr: 'title'
		});
	}
	
	var viewStatus = "${viewStatus}";
	
	if(viewStatus == "shortcuts"){
		$(".edison #wrap .content").css("width", "1220px").css("margin", "auto");
	}
	
});

function <portlet:namespace/>goSimulationProjectManagement(){
	location.href="<%=managetmentSimulationProjectUrl%>";
}

function <portlet:namespace/>requestSimulationProjectMember(){
	
	var dataForm = {			
			"<portlet:namespace/>simulationProjectId" : "${simulationProjectId}"
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=requestSimulationProjectMemberURL%>",
		data: dataForm,
  		async : false,
		success: function(data) {
			alert(Liferay.Language.get('edison-simulation-project-join-request-msg'));
			$("#<portlet:namespace/>requestMemberBtn").hide();
		},error:function(data,e){ 
			alert("request Member System error!");	
		}
	});
}

function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}
</script>