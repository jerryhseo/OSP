<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<!-- ckeditor -->
<%@ page import="com.liferay.portal.kernel.util.StringBundler"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@ page import="com.liferay.portal.model.Group"%>
<!-- ------- -->

<head>

<meta charset="utf-8">

<!-- 2018.09.10 _ Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<liferay-portlet:renderURL plid="${simulationPlid}" portletName="_SIMULATION_WAR_edisonportlet_" portletMode="view" var="exeURL">
	<liferay-portlet:param name="p_p_state" value="normal"/>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="solverFavoriteURL" 		escapeXml="false" id="solverFavorite"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="isSiteMemberURL" 		escapeXml="false" id="isSiteMember" 	 copyCurrentRenderParameters="false"/>
	
<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="updateSovelInfoActionUrl" portletMode="view" >
	<portlet:param name="myAction" value="updateSolverInfoAction"/>
	<portlet:param name="descriptionId" value="${solver.descriptionId}"/>
	<portlet:param name="solverId" value="${solver.scienceAppId}"/>
	<portlet:param name="redirectName" value="${redirectName}"/>
	<portlet:param name="redirectURL" value="${redirectURL}"/>
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="renderViewURL">
	<portlet:param name="myaction" value="detailView" />
	<portlet:param name="edionCopyParam" value="true" />
</liferay-portlet:renderURL>
<liferay-portlet:resourceURL var="addFavoriteAppURL" id="addFavoriteApp" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteFavoriteAppURL" id="deleteFavoriteApp" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="scienceAppCategoryURL" id="scienceAppCategory" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="swStatisticsURL" copyCurrentRenderParameters="false" plid="${scienceAppExecPlid}" 
  portletName="edisonstatisticsappexec_WAR_edisonsimulationportlet">
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" 
	windowState="<%=LiferayWindowState.POP_UP.toString()%>" portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet">
	<liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP"/>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="setMyRatingsEntryURL"  escapeXml="false" id="setMyRatingsEntry" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getScienceAppPaperListURL"  escapeXml="false" id="getScienceAppPaperList" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getDataTypeIdURL" id="getDataTypeId" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="getdataTypeViewURL" id="dataTypeView" copyCurrentRenderParameters="false" portletName="edisondatatypeeditor_WAR_edisonappstore2016portlet" />

<liferay-portlet:resourceURL var="retrieveListLinkedAssetEntryURL" id="retrieveListLinkedAssetEntry" copyCurrentRenderParameters="false" portletName="edisonrelateasset_WAR_edisondefault2016portlet" />

<liferay-portlet:resourceURL var="getStatisticsSwExeURL"	id="getStatisticsSwExe"	escapeXml="false" copyCurrentRenderParameters="false" portletName="edisonstatisticsappexec_WAR_edisonsimulationportlet"/>
<liferay-portlet:resourceURL var="getPortSampleFileURL"	id="getPortSampleFile"	escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="getScienceAppReviewListURL"	id="getScienceAppReviewList"	escapeXml="false" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="insertDefaultTypeBoardURL"	id="insertDefaultTypeBoard"	escapeXml="false" copyCurrentRenderParameters="false" portletName="edisoncomment_WAR_edisonboard2016portlet" />
<liferay-portlet:resourceURL var="deleteReviewURL"	id="deleteCommentList"	escapeXml="false" copyCurrentRenderParameters="false" portletName="edisoncomment_WAR_edisonboard2016portlet" />
<liferay-portlet:resourceURL var="updateReviewURL" id="updateCommentList" escapeXml="false" copyCurrentRenderParameters="false" portletName="edisoncomment_WAR_edisonboard2016portlet" />

<liferay-portlet:resourceURL var="getScienceAppHistoryListURL"	id="getScienceAppHistoryList"	escapeXml="false" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="projectDetailUrl" portletName="edisonsimulationproject_WAR_edisonsimulationproject2017portlet" 
	windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
	<liferay-portlet:param name="myRender" value="detailView" />
	<liferay-portlet:param name="isRedirect" value="false" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
	windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
	<liferay-portlet:param name="myaction" value="detailView" />
	<liferay-portlet:param name="isRedirect" value="false" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="contentDetailUrl" portletName="edisoncontent_WAR_edisoncontent2016portlet" 
	windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
	<liferay-portlet:param name="myaction" value="generalModifyView" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="dataCollectionDetailUrl" portletName="edisondatacollection_WAR_edisonsimulationportlet" 
	windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
	<liferay-portlet:param name="myRender" value="detailViewDataCollection" />
</liferay-portlet:renderURL>


<c:set var="actionUrl" value="<%=updateSovelInfoActionUrl%>"/>
<%
	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";
	
	/* QNA 이동시 P_P_ID 확인 */
	HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);
	httpRequest = PortalUtil.getOriginalServletRequest(httpRequest);
	String p_p_id = CustomUtil.strNull(httpRequest.getParameter("p_p_id"), "");
	/* QNA 이동시 P_P_ID 확인 */
	
%>

<style type="text/css">
	.yellowbtn{padding:5px 20px 8px 20px; background:#ffc75d;  border-radius:3px; -webkit-border-radius:3px; border:solid 1px #ccc; color:#704c1d; font-weight:600; font-size:15px;}
	.yellowbtn a:hover{ color:#000;}
	
	.favorites {
		cursor:pointer;
		display:none;
		
	}
	
	.appcell01 {
		text-align:center;
		font-size: 13px;
		font-weight: 600;
		color: #0e445a;
		background-color: #c7c7c7;
		padding: 9px;
		border-bottom: solid 1px #c0c0c0;
		max-width: 100px;
	}
	
	.appcell02 {
		font-size: 13px;
		font-weight: 600;
		color: #0e445a;
		background-color: #ddd;
		max-width: 100px;
		padding: 9px;
		border-bottom: solid 1px #c0c0c0;
	}
	
	.rolling-image {width: 1220px;margin: 0 auto;}
	.rolling-image>.rolling-button { float: left; padding-left: 5px; margin-top:32px; margin-right:21px;}
	
	.infoBtnbox input{float: right; margin-left: 3px; }
	
	.wbba {word-break: break-all;}
	
	.detailViewSubTitle{padding-left: 0px !important;}
	
	
	.CodeMirror, .CodeMirror-scroll {
		min-height: 200px;
	}
	.CodeMirror {
		height: 300px;
	}
</style>

	<link href="${contextPath}/css/commu.css" rel="stylesheet" type="text/css" />
	
	<link media="all" rel="stylesheet" href="${contextPath}/css/jquery-confirm.css" />
	
	<!-- 2018.09.10 _ CSS -->
	<link media="all" rel="stylesheet" href="${contextPath}/AdminLTE-2.4.5/bower_components/bootstrap/dist/css/bootstrap.min.css" />
	<link media="all" rel="stylesheet" href="${contextPath}/AdminLTE-2.4.5/bower_components/font-awesome/css/font-awesome.min.css" />
	<link media="all" rel="stylesheet" href="${contextPath}/AdminLTE-2.4.5/bower_components/Ionicons/css/ionicons.min.css" />
	<link media="all" rel="stylesheet" href="${contextPath}/AdminLTE-2.4.5/bower_components/jvectormap/jquery-jvectormap.css" />
	<link media="all" rel="stylesheet" href="${contextPath}/AdminLTE-2.4.5/dist/css/AdminLTE.css" />
	
	
	<style>

	/* Strat - Page CSS */
		/* Page height Setting */
		.wrapper, .main-sidebar, .content-wrapper{
			min-height: 900px !important;
			height: 100%;
		}
		
		/* Bootstrap Padding Setting */
		.app-panel{
			padding-left: 15px !important;
			padding-right: 15px !important;
		}
		
		.box-header{
			font-size: 20px;
		}
	/* End - Page CSS */
	
	/* Left User Image And Info CSS - Start */
		.user-img, .user-info, .app-info{
			margin-bottom: 15px;
			text-align: center;
			color: white;
		}
		
		.app-info{
			margin-bottom: 25px;
		}
		
		.user-info.sub, .app-info > div{
			margin-top: 5px;
		}
	/* Left User Image And Info CSS - End */
		
	/* Left Menu Panel CSS - Start */
	#<portlet:namespace/>tabInMenuPanel i{
		margin-right: 5px;
	}
	/* Left Menu Panel CSS - End */
	
	/* Strat - Content Panel CSS */
		/* Content Panel Setting */
		#<portlet:namespace/>contentPanel{
			padding-top: 20px;
			overflow: hidden;
		}
		
		/* Content Text View Area Setting */
		
		#<portlet:namespace/>scienceAppDescription .info-box-contents{
			padding: 20px 10px;
			white-space: pre-line;
		}
		
		/* Start - Science App Title Panel */
			/* Science App Title Panel Setting */
			#<portlet:namespace/>scienceAppImage{
				padding: 10px 0px 10px 20px;
				margin-right: 20px;
			}
			
			#<portlet:namespace/>scienceAppTitlePanel{
				min-height: 100px;
				padding: 10px 0px 10px 20px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis;
			}
			
			/* Science App Image, Title Position Setting */
			.science-app-title{
				float: left;
			}
			
			/* Science App Image Setting */
			#<portlet:namespace/>scienceAppImage{
				width: 105px;
				margin-right: 20px;
			}
			
			/* Science App Title Setting */
			#<portlet:namespace/>scienceAppMainTitle{
				margin-bottom: 10px;
				margin-left: 0px;
				padding: 0px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis;
			}
			
			/* Science App Name Setting */
			#<portlet:namespace/>scienceAppNavTitle{
				font-size: 20px;
				padding: 0px 5px;
				margin: 0px;
				width: 100%;
				white-space: pre-line;
				word-break: break-all;
			}
			
			/* App Excute Button Setting In Science App Title Panel */
			.btn-glyphicon {
				padding:8px;
				background:#ffffff;
			}
			
			.btn.icon-btn {
				padding: 10px 10px 10px 3px;
				border-radius:50px !important;
			}
			
			.<portlet:namespace/>appExecuteBtnInScienceAppTitlePanel{
				padding-top: 50px;
			}
			
			.<portlet:namespace/>appExecuteBtnInScienceAppTitlePanel > button{
				float: right;
			}
			
			.event-btn-in-title{
				width: 48px;
				height: 48px;
				margin-bottom: 5px;
			}
		/* End - Science App Title Panel */
		
		/* Strat - Science App Basic Info Section */
			/* Height Setting In Science App Basic Info */
			#<portlet:namespace/>scienceAppBasicInfoSection{
				min-height: 0px;
			}
			
			#<portlet:namespace/>scienceAppRating .info-box-number{
				padding: 0px;
				font-size: 12px;
			}
			
			#<portlet:namespace/>scienceAppRating label{
				margin-bottom: 0px;
			}
			
			/* Text Setting in Science App Basic Info Section */
			.info-box{
				box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.07) !important;
			}
			
			.info-box-number{
				text-align: center;
				padding: 12px 0px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis;
			}
			
		/* End - Science App Basic Info Section */
		
		/* Strat - Science App Content Section CSS */
			.box-header{
				font-size: 20px;
			}
			
			#<portlet:namespace/>scienceAppContentSection .box-body{
				padding: 10px;
				background-color: #fff;
			}
			
			.<portlet:namespace/>paper-list{
				border-bottom: 1px solid #ecf0f5;
				padding-left: 10px !important; 
			}
			
			/* Content Title Icon Setting */
			.<portlet:namespace/>science-app-content-title{
				color: blue;
				margin-right: 5px;
			}
			
			/* Content DIV Setting */
			#<portlet:namespace/>contentLeftArea, #<portlet:namespace/>contentRightArea{
				padding-top: 10px;
			}
			
			#<portlet:namespace/>contentDescription > div,
			#<portlet:namespace/>scienceAppHistory > div, 
			#<portlet:namespace/>contentLeftArea > div, 
			#<portlet:namespace/>contentRightArea > div{
				padding: 0px;
			}
		
			/* Science App Description Setting */
			#<portlet:namespace/>scienceAppDescription,
			#<portlet:namespace/>scienceAppHistory{
				min-height: 180px;
			}
			
			.<portlet:namespace/>open-app:HOVER{
				background-color: #efefef;
			}
			
			/* Science App Relate Paper Setting */
			#<portlet:namespace/>scienceAppPaper .pagination{
				margin: 10px 0px 5px 0px;
			}
			
			/* Science App Relate Info Setting */
			#<portlet:namespace/>relateInfoScienceApp, #<portlet:namespace/>relateInfoContent,
			#<portlet:namespace/>relateInfoOpenData, #<portlet:namespace/>relateInfoSimulation{
				box-shadow: none; 
				margin-bottom: 10px; 
				border-top: 1px solid #f4f4f4;
			}
			
			.<portlet:namespace/>relate-info-body{
				padding: 10px !important;
				font-size: 14px;
				white-space: nowrap;
				text-overflow: ellipsis;
				overflow: hidden;
			}
			
			.<portlet:namespace/>relate-info-body:HOVER{
				background-color: #d2d6de;
				cursor: pointer;
			}
			
			.<portlet:namespace/>relate-info-header{
				margin: 0px !important;
				padding: 5px 10px !important; 
				border-bottom: 0px !important;
				border-left: 5px solid #00a7d0;
			}
			
			
			
		/* End - Science App Content Section CSS */
		
		/* Start - Rating Popup Setting */
			.rating:not(:checked) > input {
				position:absolute;
				top:-9999px;
				clip:rect(0,0,0,0);
			}
		
			.rating:not(:checked) > label {
				float:right;
				width:1em;
				overflow:hidden;
				white-space:nowrap;
				cursor:pointer;
				font-size:300%;
				color:#ddd;
			}
		
			.rating:not(:checked) > label:before {
				content: '★ ';
			}
		
			.rating > input:checked ~ label {
				color: dodgerblue;
			}
		
			.rating.my-rating:not(:checked) > label:hover,
			.rating.my-rating:not(:checked) > label:hover ~ label {
				color: dodgerblue;
			}
		
			.rating.my-rating > input:checked + label:hover,
			.rating.my-rating > input:checked + label:hover ~ label,
			.rating.my-rating > input:checked ~ label:hover,
			.rating.my-rating > input:checked ~ label:hover ~ label,
			.rating.my-rating > label:hover ~ input:checked ~ label {
				color: dodgerblue;
			}
		
			.rating.my-rating > label:active {
				position:relative;
				top:2px;
				left:2px;
			}
			
			.rating.average-rating input:hover, .rating.average-rating label:hover,
			.rating.average-rating input, .rating.average-rating label {
				cursor: default !important;
			}
			
			.<portlet:namespace/>relate-info-header{
				font-size: 16px;
			}
			
			.<portlet:namespace/>relate-info-header:HOVER{
				cursor: pointer;
				background-color: #efefef;
			}
			
			.<portlet:namespace/>relate-info-header.border-warning{
				border-color: #f39c12;
			}
			
			#<portlet:namespace/>scienceAppPortInfo .tab-content > .active{
				width: 100%;
				display: inline-block;
			}
			
			#<portlet:namespace/>scienceAppPortInfoTitle a{
				cursor: pointer;
			}
			
		/* End - Rating Popup Setting */
	/* Strat - Content Panel CSS */
	
	.canvasPanel{
		border: 0px !important;
		padding-left: 60px !important; 
	}
	
	#<portlet:namespace/>selectPortInfo_view{
		border-top: 1px solid #efefef;
		white-space: pre-line; 
		margin: 30px 15px 0px;
		padding: 0px 20px;
		display: none;
	}
	
	@media all and (max-width: 550px){
		.item.<portlet:namespace/>paper-list > div{
			width: 80% !important;
		}
		.item.<portlet:namespace/>paper-list > button{
			width: 20% !important;
		}
	}
	
	@media all and (max-width: 600px){
		#comment-md-preview-container{
			display: none !important;
		}
	}
	
	@media all and (min-width: 770px){
		.<portlet:namespace/>executeBtn{
			width: 50%;
		}
	}
	
	@media all and (max-width: 770px){
		.<portlet:namespace/>executeBtn{
			width: 100%;
		}
	}
	
	@media all and (max-width: 980px){
		.<portlet:namespace/>review-input-group textarea{
			width: 100% !important;
			float: none !important;
		}
		.<portlet:namespace/>review-input-group button{
			margin-top: 10px !important;
			padding: 6px 12px !important;
		}
	}
	
	@media all and (max-width: 1000px){
		#<portlet:namespace/>scienceAppImage{
			display: none;
		}
	}
	
	@media all and (max-width: 1200px){
		#<portlet:namespace/>inputPortInfo_content,
		#<portlet:namespace/>outputPortInfo_content,
		#<portlet:namespace/>logPortInfo_content{
			text-align: center;
		}
	}
	
	/* 포트 정보 */
	.<portlet:namespace/>port-info-btn.active{
		border-top: 0px !important;
		border-bottom: 3px solid #3c8dbc;
	}
	
	/* 관련 논문 */
	#<portlet:namespace/>scienceAppPaper .box-body li.item.<portlet:namespace/>paper-list > div{
		white-space: nowrap;
		text-overflow: ellipsis;
		overflow: hidden;
	}
	
	/* 관련 정보 */
	#<portlet:namespace/>scienceAppRelateData{
		display: none;
	}
	
	#<portlet:namespace/>scienceAppRelateData .box-body{
		white-space: pre-line;
	}
	
	/* 타임라인 */
	#<portlet:namespace/>contentTimelineArea .timeline{
		display: inline-block;
	}
	
	.timeline-item{
		word-break: break-word;
		white-space: pre-line;
	}
	
	.timeline > li > .timeline-item > .timeline-header{
		padding: 5px 10px 10px !important;
	}
	
	.timeline > li > .timeline-item > .timeline-header > a{
		text-decoration: none;
	}
	
	.timeline > li > .timeline-item > .timeline-header > a{
		white-space: pre-line;
		font: 14px;
	}
	
	#<portlet:namespace/>mdEditor{
		margin-top: 25px;
	}
	
	#<portlet:namespace/>mdEditor .md-editor>textarea{
		background-color: #fff;
	}
	
	.<portlet:namespace/>timeline-set-btn:HOVER{
		cursor: pointer;
		background-color: #e7e7e7;
	}
	
	#<portlet:namespace/>leftManualLabel{
		border-top: solid 1px #3f4c52;
		background: linear-gradient(#183648,#102a39);
		color: #a0cee1;
		font-size: 13px;
	}
	
	#<portlet:namespace/>leftRunLabel,
	#<portlet:namespace/>leftDownloadLabel{
		border-top: solid 1px #3f4c52;
		background: linear-gradient(#183c43,#222d32);
		color: #a0cee1;
		font-size: 13px;
	}
	
	.skin-blue .sidebar-menu>li.header {
		color: #aeafaf;
		border-top: solid 1px #494d4f;
		border-bottom: solid 1px #000;
		background: linear-gradient(#272a2c,#232323);
	}
	
	</style>
	
	<!-- 2018. 11. 06 CSS 변경 -->
	<style type="text/css">
		#<portlet:namespace/>scienceAppContent .tab-content > div{
			word-break: break-word;
			white-space: pre-line;
		}
	</style>
	
</head>

<!-- 2018.09.10 _ adminLTE Theme in ScienceApp Detail Page -->
<body>
	<img id="loadingBox" src="${contextPath}/images/loading.gif" width="400" style="display: none;"/>
	<c:if test="${isMainAppSearch eq true }">
		<div class="subvisualwrap"> 
			<div class="subnaviwrap"> 
				<div class="subnaviwrap"></div> 
			</div> 
		</div>
	</c:if>

	<form name="exeform" method="post" action="<%=exeURL%>" style="margin:0px;">
		<input name="<portlet:namespace />id"    type="hidden"/>
	</form>	
	
	<form method="post" name="formFavorite" id="formFavorite" style="margin:0px;">
		<!-- 검색어 저장 Start -->
		<input name="<portlet:namespace/>solverId"	 id="<portlet:namespace/>solverId"			type="hidden" value="${params.solverId}"/>
		<input name="<portlet:namespace/>groupId"	 id="<portlet:namespace/>groupId"			type="hidden" value="${params.groupId}"/>
		<input name="<portlet:namespace/>userId"	 id="<portlet:namespace/>userId"			type="hidden" value="${params.userId}"/>
		<!-- 검색어 저장 End -->
	</form>
	
	<div class=" skin-blue sidebar-menu sidebar-mini">
		<div class="content-wrapper" style="margin-left: 0px;">
			
			<!-- Title -->
			<section id="<portlet:namespace/>scienceAppTitle" class="content-header <portlet:namespace/>science-app-title" style="max-width: 1200px; margin-left: auto; margin-right: auto;">
				<h1>${solver.name}</h1>
				<!-- <ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>EDISON CFD</a></li>
					<li class="active">유동 범용 해석</li>
				</ol> -->
			</section>
			
			<section id="<portlet:namespace/>scienceAppContent" class="content <portlet:namespace/>science-app-content" style="max-width: 1200px;">
				<div class="row">
					<!-- ScienceApp Info -->
					<div class="col-md-3">
						<div class="box box-solid">
							<div class="box-body box-profile">
								<!-- <img  src="./img/sumnail.png" alt="2D Comp P"> -->
								<img class="profile-user-img img-responsive" alt="${solver.currentTitle}" src="/documents/${solver.iconRepositoryId}/${solver.iconId}/${solver.iconTitle}/${solver.iconUuid}?imageThumbnail=2" width="104px" style="height: 78px !important;" onerror="this.src='${contextPath}/images/comm_proj/noimage.png'">
				
								<h3 class="profile-username text-center" style="word-break: break-all; white-space: pre-line;">
									${solver.currentTitle}
								</h3>
				
								<!-- <a href="#" class="btn btn-info " style="width: 49%;"><i class="fa fa-book" style="margin-right: 5px;"></i><b>Manual</b></a>
								<a href="#" class="btn btn-primary " style="width: 49%;"><b><i class="fa fa-play-circle-o" style="margin-right: 5px;"></i>Run</b></a> -->
								
								<c:if test="${!empty solver.current_manualId}">
									<a class="btn btn-info <portlet:namespace/>executeBtn" onclick="<portlet:namespace/>fileDownload('${solver.current_manualId}');">
										<i class="fa fa-book" style="margin-right: 5px;"></i>
										<b>Manual</b>
									</a>
								</c:if>
								
								<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel ne downloadOnly}">
									<a class="btn btn-primary <portlet:namespace/>executeRun <portlet:namespace/>executeBtn" onclick="<portlet:namespace/>moveWorkbench('${params.solverId}');">
										<b>
											<i class="fa fa-play-circle-o" style="margin-right: 5px;"></i>
											Run
										</b>
									</a>
								</c:if>
								
								<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel eq downloadOnly}">
									<a class="btn btn-primary <portlet:namespace/>executeBtn" onclick="<portlet:namespace/>fileDownload('${solver.srcFileId }');">
										<i class="fa fa-download" style="margin-right: 5px;"></i>
										<b>Download</b>
									</a>
								</c:if>
								
								<ul class="list-group list-group-unbordered" style="margin-top: 10px; margin-bottom: 0px; word-break: break-all; white-space: pre-line;">
									
									<% if(isLogin) {%>
										<li class="list-group-item">
											<b><liferay-ui:message key='edison-appstore-bookmark' /></b> 
											<div class="pull-right">
												<!-- 즐겨찾기 -->
												<button id="<portlet:namespace/>appFavoriteBtn_on" class="btn btn-danger btn-xs" type="button" style="display: none; padding: 0px 10px;" onclick="<portlet:namespace/>deleteFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
													<i class="icon-star" style="color: yellow;"></i>
												</button>
												
												<button id="<portlet:namespace/>appFavoriteBtn_off" class="btn btn-default  btn-xs" type="button" style="display: none; padding: 0px 10px;" onclick="<portlet:namespace/>addFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
													<i class="icon-star-empty"></i>
												</button>
											</div>
										</li>
										
										
									<%} %>
									
									<li class="list-group-item" onclick="<portlet:namespace/>myScienceAppRating();" style="cursor:pointer;">
										<b><liferay-ui:message key='edison-science-appstore-view-tab-rating' /></b> 
										<a class="pull-right">${averageScore} (${totalEntries} vote)</a>
									</li>
									
									<li class="list-group-item">
										<b><liferay-ui:message key='version' /></b> 
										<a class="pull-right">${solver.version}</a>
									</li>
									<li class="list-group-item">
										<b><liferay-ui:message key='edison-science-appstore-view-app-type' /></b> 
										<a class="pull-right">${solver.appType}</a>
									</li>
									<li class="list-group-item">
										<b><liferay-ui:message key='developer' /></b> 
										<a class="pull-right">
											<c:forEach var="developer" items="${solver.developers }" varStatus="status">
												<c:if test="${not status.last}"><p style="margin: 0 0 12px 22px;">${developer}</p></c:if>
												<c:if test="${status.last}"><p style="margin: 0 0 0 22px;">${developer}</p></c:if>
											</c:forEach>
										</a>
									</li>
									<li class="list-group-item">
										<b><liferay-ui:message key='edison-table-list-header-date' /></b> 
										<a class="pull-right">${solver.statusDate}</a>
									</li>
								</ul>
								
							</div>
						</div>
						
						<div class="box box-solid" style="word-break: break-word; white-space: pre-line;">
							<div class="box-body">
								<strong>
									<i class="fa fa-pencil margin-r-5"></i> 
									<liferay-ui:message key='edison-science-appstore-view-tab-category' />
								</strong>
								
								<p>
									<c:if test="${childrenCategoryList ne null}">
										<c:forEach var="childrenCategory" items="${childrenCategoryList}" varStatus="status">
											<span class="label label-danger">${childrenCategory.name}</span>
										</c:forEach>
									</c:if>
								</p>
								
								<hr>
								<strong>
									<i class="fa fa-book margin-r-5"></i> 
									<liferay-ui:message key='statistics' />
								</strong>
								<p class="text-muted" style="margin-left: 10px;">
									Simulation Users : <fmt:formatNumber value="${simulationUsersCnt}" pattern="#,###" /> <br>
									SImulation Runs : <fmt:formatNumber value="${exeCnt}" pattern="#,###" />
								</p>
								
								<hr>
								
								<strong>
									<i class="fa fa-map-marker margin-r-5"></i> 
									<liferay-ui:message key='related-content' />
								</strong>
								
								<p class="text-muted" style="margin-left: 10px;">
									<liferay-ui:message key="org.kisti.edison.science.model.ScienceApp" /> : 
									<span id="<portlet:namespace/>appInfoScienceAppCnt"></span>
									<br/>
									
									<liferay-ui:message key="org.kisti.edison.content.model.Content" /> : 
									<span id="<portlet:namespace/>appInfoContentCnt"></span>
									<br/>
									
									<liferay-ui:message key="org.kisti.edison.model.SimulationProject" /> : 
									<span id="<portlet:namespace/>appInfoSimulationCnt"></span>
									<br/>
									
									<liferay-ui:message key="community" /> : 
									<span id="<portlet:namespace/>appInfoOpenDataCnt"></span>
								</p>
							</div>
						</div>
					</div>
					
					<!-- ScienceApp Content -->
					<div class="col-md-9">
						<div class="nav-tabs-custom edison">
							<ul id="<portlet:namespace/>contentTabMenu" class="nav nav-tabs">
								<li tab-type="detailInfo"><a href="#<portlet:namespace/>contentDetailInfo" data-toggle="tab">상세정보</a></li>
								<li tab-type="review"><a href="#<portlet:namespace/>contentReview" data-toggle="tab">리뷰</a></li>
								<li tab-type="relatedData"><a href="#<portlet:namespace/>contentRelatedData" data-toggle="tab">관련 자료</a></li>
								<li tab-type="statistics"><a href="#<portlet:namespace/>contentStatistics" data-toggle="tab">통계</a></li>
							</ul>
							
							<div class="tab-content">
								<div id="<portlet:namespace/>contentDetailInfo" class="tab-pane" style="padding: 10px">
									<div class="post">
										<strong>
											<liferay-ui:message key='edison-science-appstore-view-tab-detail-view' />
										</strong>
										
										<hr>
										
										<%
											for(Locale aLocale : locales){
												String languageId = LocaleUtil.toLanguageId(aLocale);
												String descriptionKey = "description_"+languageId;
										%>	
											<c:set var="descriptionKey" value="<%=descriptionKey%>"></c:set>
											<div id="<portlet:namespace/>descriptionDiv_<%=languageId%>" class="info-box-contents" style="">
												${solver.description[descriptionKey] }
											</div>
										<%
											}
										%>	
									</div>
									
									<div id="<portlet:namespace/>inputPortInfoArea" class="post table-responsive panel edison-panel" style="display: none;">
										<strong>
											Input Port
										</strong>
										
										<hr>
										
										<table id="<portlet:namespace/>inputPortTable" class="table table-bordered table-hover edison-table">
											<colgroup>
												<col width="5%">
												<col width="15%">
												<col width="35%">
												<col width="30%">
												<col width="15%">
											</colgroup>
											
											<thead>
											</thead>
											
											<tbody>
											</tbody>
										</table>
									</div>
									
									<div id="<portlet:namespace/>logPortInfoArea" class="post table-responsive panel edison-panel" style="display: none;">
										<strong>
											Log Port
										</strong>
										
										<hr>
										
										<table id="<portlet:namespace/>logPortTable" class="table table-bordered table-hover edison-table">
											<colgroup>
												<col width="5%">
												<col width="15%">
												<col width="30%">
												<col width="25%">
												<col width="25%">
											</colgroup>
											
											<thead>
											</thead>
											
											<tbody>
											</tbody>
										</table>
									</div>
									
									<div id="<portlet:namespace/>outputPortInfoArea" class="post table-responsive panel edison-panel" style="display: none;">
										<strong>
											Output Port
										</strong>
										
										<hr>
										
										<table id="<portlet:namespace/>outputPortTable" class="table table-bordered table-hover edison-table">
											<colgroup>
												<col width="5%">
												<col width="15%">
												<col width="30%">
												<col width="25%">
												<col width="25%">
											</colgroup>
											
											<thead>
											</thead>
											
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
								
								<div id="<portlet:namespace/>contentReview" class="tab-pane" style="padding: 10px">
									<div id="<portlet:namespace/>contentReviewList">
									</div>
									
									<hr>
									<% if(isLogin) {%>
									<div id="<portlet:namespace/>contentReviewWrite">
										<div class="form-group">
											<div class="box-header">
												<label>새로운 리뷰 등록</label>
											</div>
											<div class="box-body">
												<textarea id="<portlet:namespace/>newReview" class="form-control" rows="3" placeholder="Type a comment"></textarea>
											</div>
											<div class="box-footer">
												<div class="pull-right">
													<button type="submit" class="btn btn-primary" onclick="<portlet:namespace/>saveReview(0)">
														<i class="fa fa-check"></i> Save
													</button>
													<button type="button" class="btn btn-default">
														<i class="fa fa-close"></i> Cancel
													</button>
												</div>
											</div>
										</div>
									</div>
									<% }%>
								</div>
								
								<div id="<portlet:namespace/>contentRelatedData" class="tab-pane" style="padding: 10px">
									<!-- Relate Paper List -->
									<div id="<portlet:namespace/>scienceAppPaper" class="post">
										<div class="box-solid edison-panel">
											<strong>
												<liferay-ui:message key='edison-science-app-paper' />
											</strong>
											<div class="box-body">
											</div>
											
											<div class="text-center" style="border-top: 1px solid #f4f4f4;">
											</div>
										</div>
									</div>
									
									<!-- Relate Data List -->
									<div id="<portlet:namespace/>scienceAppRelateData" class="post">
										<div class="box-solid edison-panel">
											<strong>
												<liferay-ui:message key='edison-science-appstore-view-relate-info' />
											</strong>
											<div class="box-body" style="margin-top: 15px;">
												
												<div>
													<div id="<portlet:namespace/>relateInfoScienceApp" class="box collapsed-box" style="display: none;">
														<div class="box-header with-border <portlet:namespace/>relate-info-header" data-widget="collapse">
															<liferay-ui:message key="org.kisti.edison.science.model.ScienceApp" /> 
															<span id="<portlet:namespace/>relateInfoScienceAppCnt"></span>
														</div>
													</div>
													
													<div id="<portlet:namespace/>relateInfoContent" class="box collapsed-box" style="display: none;">
														<div class="box-header with-border <portlet:namespace/>relate-info-header" data-widget="collapse">
															<liferay-ui:message key="org.kisti.edison.content.model.Content" /> 
															<span id="<portlet:namespace/>relateInfoContentCnt"></span>
														</div>
													</div>
													
													<div id="<portlet:namespace/>relateInfoOpenData" class="box collapsed-box" style="display: none;">
														<div class="box-header with-border <portlet:namespace/>relate-info-header" data-widget="collapse">
															<liferay-ui:message key="com.kisti.osp.icecap.model.DataCollection" /> 
															<span id="<portlet:namespace/>relateInfoOpenDataCnt"></span>
															
														</div>
													</div>
													
													<div id="<portlet:namespace/>relateInfoSimulation" class="box collapsed-box" style="display: none;">
														<div class="box-header with-border <portlet:namespace/>relate-info-header" data-widget="collapse">
															<liferay-ui:message key="org.kisti.edison.model.SimulationProject" />
															<span id="<portlet:namespace/>relateInfoSimulationCnt"></span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div id="<portlet:namespace/>contentStatistics" class="tab-pane" style="padding: 10px;">
									<div id="<portlet:namespace/>scienceAppMonthlyExeStatistics" class="post" style="padding: 10px;">
										<!-- ScienceApp Monthly Statistics -->
										<strong>
											<liferay-ui:message key='edison-appstore-month-sts' />
										</strong>
										
										<div class="pull-right">
											<button class="btn btn-default" onclick="<portlet:namespace/>moveScienceAppExecStatistice('${solver.name}','${solverGroupId}');">
												<liferay-ui:message key='edison-professor-detail' />
												<i class="icon-expand-alt"></i> 
											</button>
										</div>
										
										<div class="box-body">
											<div class="chart" style="margin: 20px 0px;">
												<canvas id="<portlet:namespace/>monthlyExeChart" style="height: 180px;"></canvas>
											</div>
										</div>
									</div>
									
									<div id="<portlet:namespace/>scienceAppStatistics" class="post" style="padding: 10px;">
										<!-- ScienceApp Monthly Statistics -->
										<strong>
											<liferay-ui:message key='edison-appstore-affiliation-exec-sts' />
										</strong>
										
										<div class="pull-right">
											<button class="btn btn-default" onclick="<portlet:namespace/>moveScienceAppExecStatistice('${solver.name}','${solverGroupId}');">
												<liferay-ui:message key='edison-professor-detail' />
												<i class="icon-expand-alt"></i> 
											</button>
										</div>
										
										<div class="box-body">
												<div class="chart" style="margin: 20px 0px;">
													<canvas id="<portlet:namespace/>scienceAppChart" style="height: 180px;"></canvas>
												</div>
											</div>
									</div>
									
								</div>
								
							</div> <!-- End tab-content -->
						</div> <!-- End nav-tabs-custom -->
						
					</div>
				</div>
			</section>
			
		</div> <!-- End wrapper -->
	</div> <!-- End hold-transition skin-blue sidebar-mini -->
	
</body>

	<script type="text/javascript" src="${contextPath}/js/highcharts.js"></script>
	<script src="${contextPath}/js/owl-carousel/owl.carousel.min.js"></script>
	<script src="${contextPath}/js/fancybox/jquery.fancybox.pack.js"></script>
	<script type="text/javascript" src="${contextPath}/editor/ckeditor/ckeditor.js" charset="utf-8"></script>

	<script src="${contextPath}/js/jquery-confirm.js"></script>
	
	<!-- 2018.09.10 _ JS -->
	<script src="${contextPath}/AdminLTE-2.4.5/bower_components/fastclick/lib/fastclick.js"></script>
	<script src="${contextPath}/AdminLTE-2.4.5/dist/js/adminlte.js"></script>
	<script src="${contextPath}/AdminLTE-2.4.5/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<script src="${contextPath}/AdminLTE-2.4.5/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${contextPath}/AdminLTE-2.4.5/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="${contextPath}/AdminLTE-2.4.5/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${contextPath}/AdminLTE-2.4.5/bower_components/chart.js/Chart.js"></script>
	
	<!-- 2018.09.13 _ toastr -->
	<link rel="stylesheet" href="${contextPath}/css/toastr.min.css">
	<script src="${contextPath}/js/toastr.min.js"></script>
	
	<!-- 2018.10.05 _ Bootstrap-toggle -->
	<link rel="stylesheet" href="${contextPath}/css/bootstrap-toggle.min.css">
	<script src="${contextPath}/js/bootstrap-toggle.min.js"></script>
	
	<!-- 2018.10.22 _ Markdown JS -->
	<%-- <script src="${contextPath}/js/marked.js"></script>
	<script src="${contextPath}/js/bootstrap-markdown.js"></script>
	<script src="${contextPath}/js/markdown-jquery.filedrop.js"></script>
	<script src="${contextPath}/js/markdown-script.js"></script> --%>
	
<script>

/* 2018.09.13 _ Set toastr */
var toastr;

toastr.options = {
	"closeButton": true,
	"debug": false,
	"newestOnTop": true,
	"progressBar": false,
	"positionClass": "toast-designer-pos",
	"preventDuplicates": false,
	"onclick": null,
	"showDuration": "300",
	"hideDuration": "1000",
	"timeOut": "5000",
	"extendedTimeOut": "1000",
	"showEasing": "swing",
	"hideEasing": "linear",
	"showMethod": "slideDown",
	"hideMethod": "slideUp"
};

var chart1;
/* var solverId = "${params.solverId}"; */

$(document).ready(function(){
	
	/* Delete Run Button - Mobile */
	if(Liferay.Browser.isMobile() && 0 < $(".<portlet:namespace/>executeRun").length){
		$(".<portlet:namespace/>executeRun").remove();
	}
		
	/* Meta Data(Tag) Setting */
	scienceAppMetaSetting()
	
	var fa = "${favorite}";
	
	if(fa == 0){
		$("#favorites_off").show();
		$("#favorites_off").css("display", "inline");
		
		$("#<portlet:namespace/>appFavoriteBtn_off").show();
	}else{
		$("#favorites_on").show();
		$("#favorites_on").css("display", "inline");
		
		$("#<portlet:namespace/>appFavoriteBtn_on").show();
	}

	var viewStatus = "${viewStatus}";
	
	if(viewStatus == "shortcuts"){
		$(".edison #wrap .content").css("width", "1220px").css("margin", "auto");
	}
	
	$("#<portlet:namespace/>show-analyzer-dialog").dialog({
		autoOpen: false,
		width: 1000,
		height: 'auto',
		modal: true,
		resizable: true,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
		},
		close: function() {
			/* $("body").css('overflow',''); */
		}
	})
	
	/* Default Tab Click */
	<portlet:namespace/>clickSicneceAppContentTab("detailInfo");
	
	/*  관련 논문 리스트 출력 */
	<portlet:namespace/>searchScienceAppPaper(1);
	
	/* 포트 정보 View */
	if(${isPortEmpty}){
		$("#<portlet:namespace/>scienceAppPortInfo").css("display", "block");
		
		if('${inputPorts}' != null && '${inputPorts}' != '' && '${inputPorts}' != 'undefined' && '${inputPorts}' != 'false' && '${inputPorts}' != '{}'){
			<portlet:namespace/>getScienceAppPortList('INPUT', '${inputPorts}');
		}
		
		if('${outputPorts}' != null && '${outputPorts}' != '' && '${outputPorts}' != 'undefined' && '${outputPorts}' != 'false' && '${outputPorts}' != '{}'){
			<portlet:namespace/>getScienceAppPortList('OUTPUT', '${outputPorts}');
		}
		
		if('${logPorts}' != null && '${logPorts}' != '' && '${logPorts}' != 'undefined' && '${logPorts}' != 'false' && '${logPorts}' != '{}'){
			<portlet:namespace/>getScienceAppPortList('LOG', '${logPorts}');
		}
	} else {
		$("#<portlet:namespace/>scienceAppPortInfo").css("display", "none");
	}
	
	$(".<portlet:namespace/>port-info-btn:last").click();
	
	/* 관련 정보 View */
	<portlet:namespace/>getRelateAssetEntryList("${scienceAppModelName}", "SCIENCE_APP");
	<portlet:namespace/>getRelateAssetEntryList("${contentModelName}", "CONTENT");
	<portlet:namespace/>getRelateAssetEntryList("${openDataModelName}", "OPEN_DATA");
	<portlet:namespace/>getRelateAssetEntryList("${simulationProjectModelName}", "SIMULATION_PROJECT");
	
	/* 앱 리뷰 */
	<portlet:namespace/>getScienceAppReviewList(0);
	$(".<portlet:namespace/>review-editor").text("");
	
	changeLocale("${solver.selectLocaleId}");
	
	// <portlet:namespace/>getScienceAppHistory();
});

/* Tab Click Event For Statistics */
$("#<portlet:namespace/>contentTabMenu > li").click(function(){
	tabType = $(this).attr("tab-type");
	if(tabType == "statistics"){
		$("#<portlet:namespace/>contentStatistics").show();
		/* 앱 실행 통계 */
		<portlet:namespace/>getScienceAppExeStatistics();
	} else {
		$("#<portlet:namespace/>contentStatistics").hide();
	}
});

/* Contents Tab Click Event */
function <portlet:namespace/>clickSicneceAppContentTab(tabType){
	$("#<portlet:namespace/>contentTabMenu > li[tab-type='"+ tabType +"'] > a").click();
}

/* Get ScienceApp History  */
function <portlet:namespace/>getScienceAppHistory(){
	
	var name = "${solver.name}";
	var companyId = "${solver.companyId}";
	var groupId = "${solver.groupId}";
	
	var sendData = {
			"<portlet:namespace/>name": name,
			"<portlet:namespace/>companyId": companyId,
			"<portlet:namespace/>groupId": groupId
	}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=getScienceAppHistoryListURL%>",
		data : sendData,
		async : false,
		success: function(data) {
			
			getScienceAppHistoryList = data.getScienceAppHistoryList;
			
			var appHistoryDiv = $("#<portlet:namespace/>scienceAppHistory .box-body");
			var historyTable = $("<table/>").addClass("table table-condensed");
			
			var colGroup = $("<colgroup/>");
			$("<col/>").attr("width", "20%").appendTo(colGroup);
			$("<col/>").attr("width", "60%").appendTo(colGroup);
			$("<col/>").attr("width", "20%").appendTo(colGroup);
			colGroup.appendTo(historyTable);
			
			var tbody = $("<tbody/>");
			var headerTr = $("<tr/>");
			$("<th/>").text("Version").appendTo(headerTr);
			$("<th/>").text("Modified Date").appendTo(headerTr);
			$("<th/>").text("<liferay-ui:message key='edison-workflow-conf-public-input' />").appendTo(headerTr);
			headerTr.appendTo(tbody);
			
			for(var idx=0; idx < getScienceAppHistoryList.length; idx++){
				
				var getScienceAppHistoryMap = getScienceAppHistoryList[idx];
				
				var scienceAppId = getScienceAppHistoryMap.scienceAppId;
				/* var name = getScienceAppHistoryList.name; */
				var version = getScienceAppHistoryMap.version;
				var modifiedDate = getScienceAppHistoryMap.modifiedDate;
				var status = getScienceAppHistoryMap.status;
				var groupId = getScienceAppHistoryMap.groupId;
				
				var bodyTr = $("<tr/>");
				$("<td/>").css("text-align","center").text(version).appendTo(bodyTr);
				$("<td/>").css("text-align","center").text(modifiedDate).appendTo(bodyTr);
				if(status == '1901003'){
					$("<td/>").css("text-align","center").append( $("<span/>").addClass("badge bg-red").text("<liferay-ui:message key='private' />") ).appendTo(bodyTr);
				} else {
					$("<td/>").css("text-align","center").append( $("<span/>").addClass("badge bg-green").text("<liferay-ui:message key='open' />") ).appendTo(bodyTr);
					
					if(scienceAppId != "${solver.scienceAppId}"){
						bodyTr.addClass("<portlet:namespace/>open-app")
								.attr("onclick", "<portlet:namespace/>shortcuts(" + scienceAppId + ", " + groupId + ", 'SCIENCE_APP');")
								.css("cursor", "pointer");
					} else {
						bodyTr.css("background-color", "#efefef");
					}
				}
				
				bodyTr.appendTo(tbody);
			}
			
			tbody.appendTo(historyTable);
			historyTable.appendTo(appHistoryDiv);
			
		},error:function(msg,e){ 
			alert("<liferay-ui:message key='edison-data-event-error' />\nHistory Error");
			return false;
		}
	});
}

/* Insert Review */
function <portlet:namespace/>saveReview(boardSeq){
	
	var content = "";
	if(boardSeq  == 0){
		content = $("#<portlet:namespace/>newReview").val();
	} else {
		content = $("#<portlet:namespace/>reply_"+boardSeq).val();
	}
	
	if(content == "" || content == null || content == "undefined"){
		alert("<liferay-ui:message key='edison-board-enter-content-alert' />");
		return;
	}
	
	var sendData = {
		"_edisoncomment_WAR_edisonboard2016portlet_content": content,
		"_edisoncomment_WAR_edisonboard2016portlet_customId": 'app_${solver.scienceAppId}',
		"_edisoncomment_WAR_edisonboard2016portlet_divSort": '',
		"_edisoncomment_WAR_edisonboard2016portlet_divCd": '0',
		"_edisoncomment_WAR_edisonboard2016portlet_groupBoardSeq": boardSeq
	}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=insertDefaultTypeBoardURL%>",
		data : sendData,
		async : false,
		success: function(data) {
			location.reload();
		},error:function(msg,e){ 
			alert("<liferay-ui:message key='edison-data-event-error' />\nSave Review Error");
			return false;
		}
	});
}

/* Update Review */
function <portlet:namespace/>updateReview(boardSeq){
	
	var content = $("#<portlet:namespace/>reply_" + boardSeq).val();
	
	var sendData = {
		"_edisoncomment_WAR_edisonboard2016portlet_content": content,
		"_edisoncomment_WAR_edisonboard2016portlet_customId": 'app_${solver.scienceAppId}',
		"_edisoncomment_WAR_edisonboard2016portlet_boardSeq": boardSeq
	}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=updateReviewURL%>",
		data : sendData,
		async : false,
		success: function(data) {
			location.reload();
		},error:function(msg,e){ 
			alert("<liferay-ui:message key='edison-data-event-error' />\nUpdate Review Error");
			return false;
		}
	});
}

/* Get ScienceApp Review Event */
function <portlet:namespace/>getScienceAppReviewList(groupBoardSeq){
	var scienceAppId = '${solver.scienceAppId}';
	
	var sendData = {
		"<portlet:namespace/>scienceAppId": scienceAppId,
		"<portlet:namespace/>groupBoardSeq": groupBoardSeq,
		"<portlet:namespace/>divCd": 0
	}
	
	jQuery.ajax({
		type: "POST",
		url: "<%=getScienceAppReviewListURL%>",
		data : sendData,
		async : false,
		success: function(data) {
			getScienceAppReviewList = data.getScienceAppReviewList;
			
			if(groupBoardSeq == 0){
				<portlet:namespace/>viewScienceAppReview(getScienceAppReviewList, groupBoardSeq, "review");
			} else {
				<portlet:namespace/>viewScienceAppReview(getScienceAppReviewList, groupBoardSeq, "reply");
			}
			
			changeLocale("${solver.selectLocaleId}");
		},error:function(msg,e){ 
			alert("<liferay-ui:message key='edison-data-event-error' />\nGet Review List Error");
			return false;
		}
	});
	
}

/* View ScienceApp Review List */
function <portlet:namespace/>viewScienceAppReview(getScienceAppReviewList, groupBoardSeq, boardType){
	var newReview = "";
	var replyPostCss = "";
	
	if(boardType == "review"){
		newReview = $("#<portlet:namespace/>contentReviewList");
	} else if(boardType == "reply"){
		replyPostCss = "background-color: #efefef; border-radius: 10px; padding: 5px 10px;";
		
		newReview = $("#<portlet:namespace/>replyList_" + groupBoardSeq);
		if(newReview.hasClass("active")){
			newReview.html("");
			newReview.removeClass("active");
		} else {
			newReview.addClass("active")
		}
	}
	
	var writerDate = '';
	for(var idx=0; idx<getScienceAppReviewList.length; idx++){
		var getScienceAppReviewMap = getScienceAppReviewList[idx];
		
		var getWriterDate = getScienceAppReviewMap.writerDate;
		
		/* Review Content 생성 */
		var boardSeq = getScienceAppReviewMap.boardSeq;
		var groupBoardSeq = getScienceAppReviewMap.groupBoardSeq;
		var content = (getScienceAppReviewMap.content).replace(/&lt;/gi, "<");
		var writerId = getScienceAppReviewMap.writerId;
		var writerTime = getScienceAppReviewMap.writerTime;
		var writerDateForReply = getScienceAppReviewMap.writerDateForReply;
		var screenName = getScienceAppReviewMap.screenName;
		var replyCnt = getScienceAppReviewMap.replyCnt;
		
		/* Create Reply List */
		var reviewPost = $("<div/>").addClass("post")
									.attr("id", "<portlet:namespace/>board_" + boardSeq);
		var reviewContentDiv = $("<div/>").attr("style", replyPostCss);
		
		var userBlockDiv = $("<div/>").addClass("user-block");
		
		$("<img/>").addClass("img-circle img-bordered-sm")
					.attr("src", "${contextPath}/images/comm_proj/noimage.png")
					.appendTo(userBlockDiv);
		var userNameSpan = $("<span/>").addClass("username");
		$("<a/>").text(screenName)
				 .appendTo(userNameSpan);
		var reviewManageBtn = $("<div/>").addClass("pull-right btn0boc-tool")
										.attr("id", "<portlet:namespace/>reviewManageBtn")
				 						.appendTo(userNameSpan);
		userNameSpan.appendTo(userBlockDiv);
		
		$("<span/>").addClass("description")
					.text("Shared publicly - " + writerDateForReply)
					.appendTo(userBlockDiv);
		
		userBlockDiv.appendTo(reviewContentDiv);
		
		$("<div/>").css("margin", "0px 50px 15px")
					.html(content)
					.appendTo(reviewContentDiv);
		
		reviewContentDiv.appendTo(reviewPost);
		
		var listInlineUl = $("<ul/>").addClass("list-inline").css("margin-left", "0px");
		
		var commentsLi = $("<li/>").addClass("pull-right");
		$("<a/>").addClass("list-black text-xm")
				 .attr("onclick", "<portlet:namespace/>getScienceAppReviewList(" + boardSeq + ")")
				 .css("cursor", "pointer")
				 .append( $("<i/>").addClass("fa fa-comments-o margin-r-5").text("Comments (" + replyCnt + ")") )
				 .appendTo(listInlineUl);
		commentsLi.appendTo(listInlineUl);
		
		listInlineUl.appendTo(reviewPost);
		
		$("<div/>").attr("id", "<portlet:namespace/>replyList_" + boardSeq)
					.css("margin", "15px")
					.appendTo(reviewPost);;
		
		if(${isSignedIn}){
			var reviewInputGroup = $("<div/>").addClass("<portlet:namespace/>review-input-group").css("width", "100%").css("display", "table");
			
			$("<textarea/>").addClass("form-control input-sm")
							.attr("id", "<portlet:namespace/>reply_"+boardSeq)
							.attr("rows", "2")
							.attr("type", "text")
							.attr("placeholder", "Type a comment")
							.css("width", "90%")
							.css("float", "left")
							.appendTo(reviewInputGroup);
			
			var reviewBtnDiv = $("<div/>").css("width", "100%")
										  .css("text-align", "right")
										  .css("margin-top", "7px");
			$("<button/>").addClass("btn btn-info")
							.attr("id", "<portlet:namespace/>replySaveBtn_" + boardSeq)
							.attr("onclick", "<portlet:namespace/>saveReview("+boardSeq+")")
							.append( $("<i/>").addClass("fa fa-check").text("Save") )
							.appendTo(reviewBtnDiv);
			$("<button/>").addClass("btn btn-info")
							.attr("id", "<portlet:namespace/>replyUpdateBtn_" + boardSeq)
							.attr("onclick", "<portlet:namespace/>updateReview("+boardSeq+")")
							.css("margin-right", "10px")
							.css("display", "none")
							.append( $("<i/>").addClass("fa fa-check").text("Update") )
							.appendTo(reviewBtnDiv);
			$("<button/>").addClass("btn btn-default")
							.attr("id", "<portlet:namespace/>replyCancelBtn_" + boardSeq)
							.attr("onclick", "<portlet:namespace/>cancelReview("+boardSeq+")")
							.css("display", "none")
							.text("Cancel")
							.appendTo(reviewBtnDiv);
			
			reviewBtnDiv.appendTo(reviewInputGroup);
			reviewInputGroup.appendTo(reviewPost);
			/* reviewBtnDiv.appendTo(reviewPost); */
		}
		
		reviewPost.appendTo(newReview);
		
		if('${thisUserId}' == writerId){
			
			$("<i/>").addClass("fa fa-pencil margin-r-5")
					 .attr("onclick", "<portlet:namespace/>updateScienceAppReview(" + boardSeq + ")")
					 .css("cursor", "pointer")
					 .appendTo(reviewManageBtn);
			$("<i/>").addClass("fa fa-times")
					 .attr("onclick", "<portlet:namespace/>deleteScienceAppReview(" + boardSeq + ")")
					 .css("margin-left", "10px")
					 .css("cursor", "pointer")
					 .appendTo(reviewManageBtn);
		}
	}
}

/* View Reply Editor */
function <portlet:namespace/>showReviewEditor(boardSeq, content, orderType){
	var reviewEditor = $("#<portlet:namespace/>reviewEditor_"+boardSeq);
	var reviewUpdateEditor = $("#<portlet:namespace/>reply_"+boardSeq);
	reviewEditor.html("").show();
	
	$("<textarea/>").addClass("<portlet:namespace/>review-editor")
					.attr("rows", "5")
					.attr("placeholder", "<liferay-ui:message key='edison-board-enter-content-alert' />")
					.css("width", "100%")
					.css("resize", "none")
					.text(content)
					.appendTo(reviewEditor);
	
	var commentBtnDiv = $("<div/>").addClass("pull-right")
									.css("margin", "10px 0px");
	
	if(orderType == 'INSERT'){
		$("<button/>").addClass("btn btn-primary")
						.attr("onclick", "<portlet:namespace/>saveReview("+boardSeq+")")
						.css("margin-right", "10px")
						.css("padding-left", "20px")
						.css("padding-right", "20px")
						.text("SAVE")
						.appendTo(commentBtnDiv);
		
		$("#<portlet:namespace/>replySaveBtn_"+boardSeq).show();
	} else if(orderType == 'UPDATE'){
		$("#<portlet:namespace/>replySaveBtn_"+boardSeq).hide();
		$("#<portlet:namespace/>replyUpdateBtn_"+boardSeq).show();
		$("#<portlet:namespace/>replyCancelBtn_"+boardSeq).show();
		
		$("<button/>").addClass("btn btn-primary")
						.attr("onclick", "<portlet:namespace/>updateReview("+boardSeq+")")
						.css("margin-right", "10px")
						.text("UPDATE")
						.appendTo(commentBtnDiv);
		
		$("<button/>").addClass("btn btn-default")
						.attr("onclick", "<portlet:namespace/>cancelReview("+boardSeq+")")
						.text("CANCEL")
						.appendTo(commentBtnDiv);
	}
	
	commentBtnDiv.appendTo(reviewEditor);
	reviewUpdateEditor.text(content).focus();
}

function <portlet:namespace/>cancelReview(boardSeq){
	$("#<portlet:namespace/>reply_"+boardSeq).text("");
	$("#<portlet:namespace/>replySaveBtn_"+boardSeq).show();
	$("#<portlet:namespace/>replyUpdateBtn_"+boardSeq).hide();
	$("#<portlet:namespace/>replyCancelBtn_"+boardSeq).hide();
}

function <portlet:namespace/>updateScienceAppReview(boardSeq){
	
	var selectLocaleId = "${solver.selectLocaleId}";
	var content = "";
	
	<%
	for(Locale aLocale : locales){
		String languageId = LocaleUtil.toLanguageId(aLocale);
	%>	
		var languageId = "<%=languageId%>";
		
		if(languageId == selectLocaleId ){
			content = $("#<portlet:namespace/>board_"+boardSeq+ " content[language-id="+languageId+"]").html().replace(/(<([^>]+)>)/gi, "");
		}
	<%	
		}
	%>
	
	<portlet:namespace/>showReviewEditor(boardSeq, content, 'UPDATE')
}


/* Delete ScienceApp Review */
function <portlet:namespace/>deleteScienceAppReview(boardSeq){
	
	$.confirm({
		boxWidth: '30%',
		useBootstrap: false,
		title: 'Confirm!',
		content: '<p style="font-size: 15px;"><liferay-ui:message key='edison-science-appstore-review-delete' /></p>',
		buttons: {
			confirm: function () {
				
				jQuery.ajax({
					type: "POST",
					url: "<%=deleteReviewURL%>",
					data : {"_edisoncomment_WAR_edisonboard2016portlet_boardSeq" : boardSeq},
					async : false,
					success: function(data) {
						result = data.result;
						
						if(result){
							location.reload();
						}
					},error:function(msg,e){ 
						alert("<liferay-ui:message key='edison-data-event-error' />\nDelete Review Error");
						return false;
					}
				});
				
			},
			cancel: function () {
			}
		}
	});
}


$(document).on({
	mouseenter: function(){
		$(this).attr("src","${contextPath}/images/scienceappstoreview/<%=themeDisplay.getLanguageId()%>/btn_original_over.png");
	},
	mouseleave: function(){
		$(this).attr("src","${contextPath}/images/scienceappstoreview/<%=themeDisplay.getLanguageId()%>/btn_original.png");
	}
}, "#linkImg");

/* ReferenceLink popup */
$(document).on( "click", "#linkImg", function(){
	var url = spaceDelete($(this).attr("data-url"));
	window.open(url,"_blank");
});

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

function <portlet:namespace/>submitForm(){
    if(
    	document.<portlet:namespace/>solverInfoForm.onsubmit &&
    	!document.<portlet:namespace/>solverInfoForm.onsubmit()
    ){
        return false;
    }
 	document.<portlet:namespace/>solverInfoForm.submit();
}

function <portlet:namespace/>detailInfoModify(){
	var formFavorite = document.formFavorite;
	var solverInfoForm = document.<portlet:namespace/>solverInfoForm;
	
	var solverId = formFavorite.<portlet:namespace/>solverId.value;
	var groupId = formFavorite.<portlet:namespace/>groupId.value;
		
	$('<input />').attr('type', 'hidden').attr('name', "<portlet:namespace/>groupId").attr('value', groupId).appendTo('#<portlet:namespace/>solverInfoForm');
	
	<portlet:namespace/>submitForm();
}

function <portlet:namespace/>solverInfoFormCheck(){
	 /* 에디터의 내용이 textarea에 적용된다. */
	   
	<%
		for(Locale aLocale : locales){
			String languageId = LocaleUtil.toLanguageId(aLocale);
	%>	
			var languageId = "<%=languageId%>";   
			var contentValue = CKEDITOR.instances["<portlet:namespace/>description_"+languageId].getData();
			var content = $.trim(contentValue.replace(/&nbsp;/g, ''));
			$("#<portlet:namespace/>description_"+languageId).val(content);
	<%	
		}
	%>
	return true;
}

function changeLocale(selectLocaleId){
	<%
	for(Locale aLocale : locales){
		String languageId = LocaleUtil.toLanguageId(aLocale);
	%>	
		var languageId = "<%=languageId%>";
		
		if(languageId != selectLocaleId ){
			$("#<portlet:namespace/>descriptionDiv_"+languageId).hide();
			$("#<portlet:namespace/>contentTimelineArea .timeline-body content[language-id="+languageId+"]").hide();
			$("#<portlet:namespace/>contentReviewList .post content[language-id="+languageId+"]").hide();
		}else{
			$("#<portlet:namespace/>descriptionDiv_"+languageId).show();
			$("#<portlet:namespace/>contentTimelineArea .timeline-body content[language-id="+languageId+"]").show();
			$("#<portlet:namespace/>contentReviewList .post content[language-id="+languageId+"]").show();
		}
	<%	
		}
	%>
	var solverInfoForm = document.<portlet:namespace/>solverInfoForm;
	/* TODO */
	/* solverInfoForm.<portlet:namespace/>selectLocaleId.value = selectLocaleId; */
}

function <portlet:namespace/>addFavoriteApp(solverId,groupId) {
	var dataForm = {
		"<portlet:namespace/>solverId" : solverId,
		"<portlet:namespace/>groupId" : groupId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=addFavoriteAppURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var result = msg.result;
			if(result == '200') {
				$("#favorites_off").hide();
				$("#favorites_on").show();
				$("#favorites_on").css("display", "inline");
				
				$("#<portlet:namespace/>appFavoriteBtn_on").show();
				$("#<portlet:namespace/>appFavoriteBtn_off").hide();
				
			}else{
				alert("<liferay-ui:message key='edison-data-insert-error' />");
			}
		},error:function(msg,e){ 
			alert("<liferay-ui:message key='edison-data-event-error' />\nAdd FavoriteApp Error");
			return false;
		}
	});
}

function <portlet:namespace/>deleteFavoriteApp(solverId,groupId) {
	if(confirm("<liferay-ui:message key='edison-appstore-favorite-app-delete-alert' />")){	
		var dataForm = {
			"<portlet:namespace/>solverId" : solverId,
			"<portlet:namespace/>groupId" : groupId
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=deleteFavoriteAppURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var result = msg.result;
				if(result == '200') {
					$("#favorites_off").show();
					$("#favorites_on").hide();
					$("#favorites_off").css("display", "inline");
					
					$("#<portlet:namespace/>appFavoriteBtn_on").hide();
					$("#<portlet:namespace/>appFavoriteBtn_off").show();
				}
			},error:function(msg,e){ 
				alert("<liferay-ui:message key='edison-data-event-error' />\nDelete FavoriteApp Error");
				return false;
			}
		});

	}
}
function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}

/* 워크밴치 실행 : 사이언스앱 Run */
function <portlet:namespace/>moveWorkbench(targetScienceAppId){
	
	var isSiteMember = false;
	var URL = "";
	
	/* Site Member Check */
	jQuery.ajax({
		type: "POST",
		url: "<%=isSiteMemberURL%>",
		async : false,
		success: function(msg) {
			isSiteMember = msg.isSiteMember;
			if(isSiteMember){
				URL = "<%=workbenchURL%>";
				URL += "&_SimulationWorkbench_WAR_OSPWorkbenchportlet_scienceAppId="+targetScienceAppId;
				/* window.open(URL); */
			} else {
				/*  Site Member가 아닌 경우 사이트 가입 여부 Confirm */
				if(confirm("<liferay-ui:message key='edison-default-site-no-user' />"+"\n"+"<liferay-ui:message key='edison-default-site-join-regist-confirm' />")){
					
					URL = "<%=themeDisplay.getPortalURL()%>";
					URL += "/my-edison?";
					URL +=	"p_p_id=edisonmypage_WAR_edisondefault2016portlet";
					URL +=	"&_edisonmypage_WAR_edisondefault2016portlet_clickTab=siteJoin";
					/* window.open(URL, "_self"); */ 
				}
			}
		},error:function(msg,e){ 
			alert("<liferay-ui:message key='edison-data-event-error' />\nMove Workbench Error");
			return false;
		}
	});
	
	/*  팝업 차단 우회 -- 사용자가 의도한 팝업이 아닌 경우(ex. 다른 function 호출 또는 ajax 안에서 window.open) 팝업 차단 발생 */
	if(isSiteMember){
		window.open(URL);
	} else {
		window.open(URL, "_self");
	}
	
	<%-- AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setWindowState("<%=LiferayWindowState.NORMAL.toString()%>");
		portletURL.setPlid("${workBenchPlid}");
		portletURL.setPortletId("SimulationWorkbench_WAR_OSPWorkbenchportlet");
		portletURL.setParameter("workbenchType", "SIMULATION_WITH_APP");
		portletURL.setParameter("scienceAppId", targetScienceAppId);
		
		portletURL.setParameter("redirectName", "My Project");
		portletURL.setParameter("redirectURL", "${redirectURL}");
		window.open(portletURL);
		window.location.href = portletURL;
	}); --%>
}

	<portlet:namespace/>scienceAppRating();

	/* view ScienceApp Average Ratings */
	function <portlet:namespace/>scienceAppRating(){
		
		var averageScore = "${averageScore}";
		var totalEntries = "${totalEntries}";
		
		var scienceAppRatingStar = $("<div/>").addClass("rating average-rating")
											  .css("display", "inline-block");
		
		for(var i=0; i<5; i++){
			$("<input/>").attr("type", "radio").attr("id", "appStar"+(5-i)).attr("name","appRating").attr("disabled", "disabled").val(5-i).appendTo(scienceAppRatingStar);
			$("<label/>").attr("for", "appStar"+(5-i)).appendTo(scienceAppRatingStar);
		}
		
		var scienceAppRatingTxt = averageScore + " (" + totalEntries + " <liferay-ui:message key='edison-science-appstore-view-tab-rating-people' />)";
		
		var scienceAppRating = $("<div/>").addClass("rating-txt")
											  .css("display", "inline-block")
											  .css("font-size", "18px")
											  .css("margin-left", "5px")
											  .text(scienceAppRatingTxt);
		
		scienceAppRatingStar.appendTo($("#<portlet:namespace/>scienceAppRating .info-box-number"));
		scienceAppRating.appendTo($("#<portlet:namespace/>scienceAppRating .info-box-number"));
		
		if(0 < averageScore){
			$("input[name='appRating'][id='appStar"+parseInt(averageScore)+"']").attr("checked","checked");
		}
	}

	/* View Science App Rating Register Popup */
	function <portlet:namespace/>myScienceAppRating(){
		
		if(!${isSignedIn}){
			alert("<liferay-ui:message key='edison-simulation-please-login' />");
			return false;
		}
		
		var averageScore = "${averageScore}";
		var totalEntries = "${totalEntries}"
		var myRatingsEntryIsEmpty = "${myRatingsEntryIsEmpty}";
		
		var myRating = $("<div/>").addClass("rating my-rating")
								  .css("padding", "10px 20px")
								  .css("height", "70px")
								  .css("width", "70%")
								  .css("text-align", "left")
								  .css("display", "inline-block");
		
		var myRatingTitle = $("<div/>").css("width", "35%")
									   .css("float", "left")
									   .css("font-size", "20px")
									   .css("padding-top", "15px")
									   .text("<liferay-ui:message key='edison-science-appstore-view-tab-rating' /> <liferay-ui:message key='register' />").appendTo(myRating);
		for(var i=0; i<5; i++){
			$("<input/>").attr("type", "radio").attr("id", "star"+(5-i)).attr("name","rating").val(5-i).appendTo(myRating);
			$("<label/>").attr("for", "star"+(5-i)).appendTo(myRating);
		}
		
		var myScore = $("<div/>").css("width", "25%")
								 .css("font-size", "20px")
								 .css("position", "absolute")
								 .css("top", "70%")
								 .css("right", "8%")
								 .attr("id", "<portlet:namespace/>myRatingsScore")
								 .appendTo(myRating);
		
		
		/*  평점 등록 여부에 따른 저장 버튼 Text 변경 및 Score Setting */
		var regBtnTxt = 'SAVE';
		if(myRatingsEntryIsEmpty === 'false'){
			regBtnTxt = 'UPDATE';
		}
		
		/* var dialogBody = $("<div/>").append(averageRating).append(myRating); */
		var dialogBody = $("<div/>").append(myRating);
		
		$.confirm({
			title: "<liferay-ui:message key='edison-workflow-science-app' /> <liferay-ui:message key='edison-science-appstore-view-tab-rating' />",
			titleClass: 'box-header with-border',
			content: dialogBody,
			columnClass: 'col-md-6 col-md-offset-3',
			buttons: {
				formSubmit: {
					text: regBtnTxt,
					btnClass: "btn btn-default <portlet:namespace/>setRatingsBtn",
					action: function(){
						<portlet:namespace/>setMyRatingsEntry();
					}
				},
				cancel: function(){
				}
			},
			onOpen: function(){
				if(myRatingsEntryIsEmpty === 'false'){
					score = "${myRatingsScore}";
					var modifiedDt = "${myRatingsModifiedDate}";
					
					var myRatingModifiedDt = $("<div/>").css("width", "40%")
														.css("font-size", "14px")
														.css("position", "absolute")
														.css("top", "77%")
														.css("right", "-15%")
														.text("<liferay-ui:message key='date' /> : " + modifiedDt)
														.appendTo(myRating);
					
					$("#<portlet:namespace/>myRatingsScore").text("(" + score + ")");
					
					<portlet:namespace/>checkMyScore(score);
				} else {
					<portlet:namespace/>checkMyScore(0);
				}
			}
		});
		
	}

	/* Rating Button Event */
	$(document).on( "click", "input[name='rating']", function(){
		var checkedScore = $(this).val();
		$("#<portlet:namespace/>myRatingsScore").text("("+checkedScore+")")
		$(".<portlet:namespace/>setRatingsBtn").attr("disabled", false).css("cursor", "pointer");
	});

	/* My Srore Check */
	function <portlet:namespace/>checkMyScore(score){
		
		if(0 < score){
			$("input[name='rating'][id='star"+score+"']").attr("checked","checked");
		} else {
			$(".<portlet:namespace/>setRatingsBtn").attr("title", "<liferay-ui:message key='edison-science-appstore-view-tab-rating-save-button-msg' />").attr("disabled", "disabled").css("cursor", "default");
		}
	}

	/* Save Or Update My Score */
	function <portlet:namespace/>setMyRatingsEntry(){
		var scienceAppId = '${solver.scienceAppId}';
		var ratingScore = $("input[name='rating']:checked").val();
		if(ratingScore == '' || ratingScore == null || ratingScore == 'undefined'){
			return false;
		}
		
		var sendData = {
							"<portlet:namespace/>scienceAppId": scienceAppId,
							"<portlet:namespace/>ratingScore": ratingScore 
						}
		
		jQuery.ajax({
			type: "POST",
			url: "<%=setMyRatingsEntryURL%>",
			data : sendData,
			async : false,
			success: function(msg) {
				result = msg.result;
				
				if(result){
					location.reload();
				}
			},error:function(msg,e){ 
				alert("<liferay-ui:message key='edison-data-event-error' />\nSet Rating Error");
				return false;
			}
		});
	}
	
	/* Search ScienceApp Paper List */
	function <portlet:namespace/>searchScienceAppPaper(curPage){
		
		var scienceAppId = '${solver.scienceAppId}';
		
		var sendData = {
				"<portlet:namespace/>scienceAppId": scienceAppId,
				"<portlet:namespace/>curPage": curPage 
			}
		
		jQuery.ajax({
			type: "POST",
			url: "<%=getScienceAppPaperListURL%>",
			data : sendData,
			async : false,
			success: function(data) {
				var scienceAppPaperList = data.scienceAppPaperList;
				var pagingStr = data.pagingStr;
				
				var scienceAppPaperBoxBody = $("#<portlet:namespace/>scienceAppPaper .box-body");
				
				if(0 < scienceAppPaperList.length){
					
					scienceAppPaperBoxBody.html("");
					
					var scienceAppPaperUl = $("<ul/>").addClass("products-list product-list-in-box");
					
					$.each(scienceAppPaperList, function(idx, scienceAppPaper){
						var scienceAppPaperType = scienceAppPaper.paperType;
						
						if(scienceAppPaperType == 'file'){
							var scienceAppPaperLi = $("<li/>").addClass("item <portlet:namespace/>paper-list")
							
							$("<div/>").text(scienceAppPaper.paperFileTitle)
									   .css("width", "85%")
									   .css("float","left")
									   .css("padding-top", "5px")
									   .appendTo(scienceAppPaperLi);
							
							$("<button/>").addClass("btn bg-olive margin")
										  .attr("onclick", "<portlet:namespace/>fileDownload('" + scienceAppPaper.paperValue + "');")
										  .attr("type", "button")
										  .css("width", "15%")
										  .css("float", "left")
										  .css("font-size", "12px")
										  .css("padding", "4px")
										  .append($("<i/>").addClass("icon-download-alt").text(" DOWN"))
										  .appendTo(scienceAppPaperLi);
							
							scienceAppPaperLi.appendTo(scienceAppPaperUl);
						}
					});
					
					$.each(scienceAppPaperList, function(idx, scienceAppPaper){
						var scienceAppPaperType = scienceAppPaper.paperType;
						
						if(scienceAppPaperType == 'link'){
							var scienceAppPaperLi = $("<li/>").addClass("item <portlet:namespace/>paper-list")
							
							$("<div/>").text(scienceAppPaper.paperValue)
									   .css("width", "85%")
									   .css("float","left")
									   .css("padding-top", "5px")
									   .appendTo(scienceAppPaperLi);
							
							$("<button/>").addClass("btn bg-orange margin")
										  .attr("onclick", "window.open('" + scienceAppPaper.paperValue + "', '_blank');")
										  .attr("type", "button")
										  .css("width", "15%")
										  .css("float", "left")
										  .css("font-size", "12px")
										  .css("padding", "4px")
										  .append($("<i/>").addClass("icon-link").text(" LINK"))
										  .appendTo(scienceAppPaperLi);
							
							scienceAppPaperLi.appendTo(scienceAppPaperUl);
						}
					});
					
					scienceAppPaperUl.appendTo(scienceAppPaperBoxBody);
					
					if(pagingStr != null){
						$("#<portlet:namespace/>scienceAppPaper .text-center").html("");
						$("#<portlet:namespace/>scienceAppPaper .text-center").append(pagingStr);
					}
					
				} else {
					$("#<portlet:namespace/>scienceAppPaper").css("display", "none");
				}
				
			},error:function(msg,e){ 
				alert("<liferay-ui:message key='edison-data-event-error' />\nSearch Paper Error");
				return false;
			}
		});
	}
	
	/* 관련 정보 가져오기  */
	function <portlet:namespace/>getRelateAssetEntryList(className, selectType){
		
		var sendData = {
				"_edisonrelateasset_WAR_edisondefault2016portlet_classModel" : className,
				"_edisonrelateasset_WAR_edisondefault2016portlet_pagingYn" : "false",
				"_edisonrelateasset_WAR_edisondefault2016portlet_sourceEntryId" : "${scienceAppEntryId}"
		};
		
		$.ajax({
			type: "POST",
			url: "<%=retrieveListLinkedAssetEntryURL%>",
			async : false,
			data : sendData,
			success: function(data) {
				
				var dataSize = data.dataLinkList.length;
				var dataList = data.dataLinkList;
				
				if(selectType === "SCIENCE_APP"){
					$("#<portlet:namespace/>appInfoScienceAppCnt").text(dataSize);
				} else if(selectType === "CONTENT"){
					$("#<portlet:namespace/>appInfoContentCnt").text(dataSize);
				} else if(selectType === "OPEN_DATA"){
					$("#<portlet:namespace/>appInfoOpenDataCnt").text(dataSize);
				} else if(selectType === "SIMULATION_PROJECT"){
					$("#<portlet:namespace/>appInfoSimulationCnt").text(dataSize);
				}
				
				if(0 < dataSize){
					
					$("#<portlet:namespace/>scienceAppRelateData").show();
					var viewDiv = "";
					
					if(selectType === "SCIENCE_APP"){
						viewDiv = $("#<portlet:namespace/>relateInfoScienceApp");
						viewDiv.show();
						$("#<portlet:namespace/>relateInfoScienceAppCnt").text("(" + dataSize + ")");
					} else if(selectType === "CONTENT"){
						viewDiv = $("#<portlet:namespace/>relateInfoContent");
						viewDiv.show();
						$("#<portlet:namespace/>relateInfoContentCnt").text("(" + dataSize + ")");
					} else if(selectType === "OPEN_DATA"){
						viewDiv = $("#<portlet:namespace/>relateInfoOpenData");
						viewDiv.show();
						$("#<portlet:namespace/>relateInfoOpenDataCnt").text("(" + dataSize + ")");
					} else if(selectType === "SIMULATION_PROJECT"){
						viewDiv = $("#<portlet:namespace/>relateInfoSimulation");
						viewDiv.show();
						$("#<portlet:namespace/>relateInfoSimulationCnt").text("(" + dataSize + ")");
					}
					
					for(var i = 0 ; i < dataSize; i++ ){
						var dataType = "S";
						var dataTypeClass = "info";
						var moveParameter = "'" + dataList[i].modelSeq + "', '" + dataList[i].modelDiv + "', '" + selectType + "'";
						var relateInfoTitle = dataList[i].title + " v" + dataList[i].version;
						
						if(selectType === "SCIENCE_APP"){
							if(dataList[i].modelDiv == "Editor"){
								dataType = "E";
								dataTypeClass = "success";
							}else if(dataList[i].modelDiv == "Analyzer"){
								dataType = "D";
								dataTypeClass = "warning";
							}
							
							moveParameter = "'" + dataList[i].modelSeq + "', '" + dataList[i].groupId + "', '" + selectType + "'";
						} else if(selectType === "CONTENT"){
							/* moveParameter = "'" + dataList[i].modelSeq + "', '" + dataList[i].modelDiv + "', '" + selectType + "'"; */
							
							dataType = "C";
							dataTypeClass = "primary";
						} else if(selectType === "OPEN_DATA"){
							relateInfoTitle = dataList[i].title;
							dataType = "D";
							dataTypeClass = "primary";
						} else if(selectType === "SIMULATION_PROJECT"){
							relateInfoTitle = dataList[i].title;
							dataType = "P";
							dataTypeClass = "primary";
						}
						
						var boxBody = $("<div/>").addClass("box-body").addClass("<portlet:namespace/>relate-info-body")
												 .attr("onclick", "<portlet:namespace/>shortcuts(" + moveParameter + ");")
												 .css("display", "none").css("margin", "0% 2%")
												 .css("border-top", "1px solid #f4f4f4")
												 .css("padding-top", "10px !important")
												 .css("padding-bottom", "10px !important");
						
						$("<i/>").addClass("icon-angle-right").appendTo(boxBody);
						
						$("<span/>").addClass("label label-"+dataTypeClass)
									.css("margin", "0px 5px").css("padding", "2px 4px 2px 3px")
									.text(dataType).appendTo(boxBody);
						$("<span/>").text(relateInfoTitle).appendTo(boxBody);
						
						boxBody.appendTo(viewDiv);
						
					}
				}
				
			},error:function(msg,e){ 
				alert("<liferay-ui:message key='edison-data-event-error' />\nGet RelateAssetEntry Error");
				return false;
			}
		});
	}

	/* 관련 정보 클릭 시 해당 데이터 View */
	function <portlet:namespace/>shortcuts(seq, groupId, category) {
		AUI().use("liferay-portlet-url", function(a) {
			
			if(category == "SCIENCE_APP"){
				var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
				var params = "&" + thisPortletNamespace + "solverId=" + seq;
					params += "&" + thisPortletNamespace + "groupId=" + groupId;
				window.open("<%=scienceAppDetailUrl%>" + params);
			}else if(category == "CONTENT"){
				var thisPortletNamespace = "_edisoncontent_WAR_edisoncontent2016portlet_";
				var params = "&" + thisPortletNamespace + "contentDiv=" + groupId;
					params += "&" + thisPortletNamespace + "contentSeq=" + seq;
				window.open("<%=contentDetailUrl%>" + params);
			}else if(category == "OPEN_DATA"){
				var thisPortletNamespace = "_edisondatacollection_WAR_edisonsimulationportlet_";
				var params = "&" + thisPortletNamespace + "collectionId=" + seq;
				window.open("<%=dataCollectionDetailUrl%>" + params);
			}else if(category == "SIMULATION_PROJECT"){
				var thisPortletNamespace = "_edisonsimulationproject_WAR_edisonsimulationproject2017portlet_";
				var params = "&" + thisPortletNamespace + "simulationProjectId=" + seq;
				window.open("<%=projectDetailUrl%>" + params);
			}
		}); 
	}
	
	/* Port Tab Click Event */
	function <portlet:namespace/>viewPortInfo(portType){
		var selectPortInfo = $("#<portlet:namespace/>selectPortInfo_view");
		selectPortInfo.html("");
		
		if(portType == 'INPUT'){
			$(".tab-pane.active").removeClass("active");
			$("#<portlet:namespace/>inputPortInfo_content").addClass("active");
		} else if(portType == 'OUTPUT'){
			$(".tab-pane.active").removeClass("active");
			$("#<portlet:namespace/>outputPortInfo_content").addClass("active");
		} else if(portType == 'LOG'){
			$(".tab-pane.active").removeClass("active");
			$("#<portlet:namespace/>logPortInfo_content").addClass("active");
		}
		$(".<portlet:namespace/>port-info-btn").removeClass("active");
		$(".<portlet:namespace/>port-info-btn[dataType="+portType+"]").addClass("active");
	}
	
	/* Port List View Event */
	var scienceApp = new OSP.ScienceApp();
	function <portlet:namespace/>getScienceAppPortList(portType, scienceAppPortStr){
		
		var portInfoViewDiv = "";
		var scienceAppPortArray = null;
		
		var portInfoTableHead = "";
		var portInfoTableBody = "";
		
		$("#<portlet:namespace/>"+portType.toLowerCase()+"PortInfoArea").show();
		portInfoViewDiv = $("#<portlet:namespace/>"+portType.toLowerCase()+"PortInfo_content");
		portInfoTableHead = $("#<portlet:namespace/>"+portType.toLowerCase()+"PortTable > thead");
		portInfoTableBody = $("#<portlet:namespace/>"+portType.toLowerCase()+"PortTable > tbody");
		if(portType=='INPUT'){
			/* $("#<portlet:namespace/>inputPortInfoArea").show(); */
			scienceApp.deserializeInputPorts(JSON.parse(scienceAppPortStr));
			/* portInfoViewDiv = $("#<portlet:namespace/>inputPortInfo_content");
			
			portInfoTableHead = $("#<portlet:namespace/>inputPortTable > thead");
			portInfoTableBody = $("#<portlet:namespace/>inputPortTable > tbody"); */
			
			scienceAppPortArray = scienceApp.inputPortsArray();
		}else if(portType=='OUTPUT'){
			/* $("#<portlet:namespace/>outputPortInfoArea").show(); */
			scienceApp.deserializeOutputPorts(JSON.parse(scienceAppPortStr));
			/* portInfoViewDiv = $("#<portlet:namespace/>outputPortInfo_content");
			
			portInfoTableHead = $("#<portlet:namespace/>outputPortTable > thead");
			portInfoTableBody = $("#<portlet:namespace/>outputPortTable > tbody"); */
			
			scienceAppPortArray = scienceApp.outputPortsArray();
		}else if(portType=='LOG'){
			/* $("#<portlet:namespace/>logPortInfoArea").show(); */
			scienceApp.deserializeLogPorts(JSON.parse(scienceAppPortStr));
			/* portInfoViewDiv = $("#<portlet:namespace/>logPortInfo_content");
			
			portInfoTableHead = $("#<portlet:namespace/>logPortTable > thead");
			portInfoTableBody = $("#<portlet:namespace/>logPortTable > tbody"); */
			
			scienceAppPortArray = scienceApp.logPortsArray();
		}
		
		$("#<portlet:namespace/>emptyScienceAppPort").hide();
		
		if(scienceAppPortArray.length !=0){
			
			titleTr = $("<tr/>");
			$("<th/>").addClass("text-center").text("#").appendTo(titleTr);
			$("<th/>").addClass("text-center").text("Port Name").appendTo(titleTr);
			$("<th/>").addClass("text-center").text("Datatype").appendTo(titleTr);
			$("<th/>").addClass("text-center").text("Editor").appendTo(titleTr);
			if(portType=='INPUT'){
				$("<th/>").addClass("text-center").text("Sample").appendTo(titleTr);
			} else {
				$("<th/>").addClass("text-center").text("Analyzeer").appendTo(titleTr);
			}
			titleTr.appendTo(portInfoTableHead);
			
			for(var i=0; i<scienceAppPortArray.length; i++){
				
				var portJsonData = scienceAppPortArray[i];
				
				var portName = portJsonData[OSP.Constants.NAME];
				var dataTypeName = portJsonData[OSP.Constants.DATA_TYPE]['name'];
				var dataTypeVersion = portJsonData[OSP.Constants.DATA_TYPE]['version'];
				var sampleFile = portJsonData[OSP.Constants.SAMPLE];
				var sampleFileId = '';
				if(sampleFile != 'undefined' && sampleFile != null){
					sampleFileId = sampleFile['id_'];
				}
				
				contentTr = $("<tr/>").addClass("text-center");
				$("<td/>").text(i+1).appendTo(contentTr);
				$("<td/>").text(portName).appendTo(contentTr);
				$("<td/>").text(dataTypeName).appendTo(contentTr);
				
				
				var selectPortTypeId='';
				var searchPortData = {
						"<portlet:namespace/>dataTypeName" : dataTypeName,
						"<portlet:namespace/>dataTypeVersion" : dataTypeVersion
					};
				$.ajax({
					type: "POST",
					url: "<%=getDataTypeIdURL%>",
					async : false,
					data : searchPortData,
					success: function(data) {
						selectPortTypeId = data.typeId;
					},error:function(msg,e){ 
						alert("<liferay-ui:message key='edison-data-event-error' />\nGet Select Port Info Error");
						return false;
					}
				});
				
				if(selectPortTypeId != "" && selectPortTypeId != "0"){
					$.ajax({
						type: "POST",
						url: "<%=getdataTypeViewURL%>",
						async : false,
						data : {"_edisondatatypeeditor_WAR_edisonappstore2016portlet_typeId" : selectPortTypeId},
						dataType: 'json',
						success: function(data) {
							
							if (typeof data.editor != "undefined"){
								$("<td/>").text(data.editor).appendTo(contentTr);
							} else {
								$("<td/>").text("-").appendTo(contentTr);
							}
							
							if(portType!='INPUT'){
								if (typeof data.analyzer != "undefined"){
									$("<td/>").text(data.analyzer).appendTo(contentTr);
								} else {
									$("<td/>").text("-").appendTo(contentTr);
								}
							}
							
						},error:function(msg,e){ 
							alert("<liferay-ui:message key='edison-data-event-error' />\nGet Select Port Info Error");
							return false;
						}
					});
				} 
				
				if(portType=='INPUT' && sampleFileId != ''){
					var sampleDownBtn = $("<a/>").addClass("btn btn-primary btn-block btn-xs")
												 .attr("onclick","<portlet:namespace/>fileDownload('"+sampleFileId+"')")
												 .append(
													 $("<i/>").addClass("fa fa-save")
													 		  .css("margin-right", "5px")
													 		  .append("<b>Down</b>")
												)
					
					$("<td/>").append(sampleDownBtn).appendTo(contentTr);
				} else if(portType=='INPUT' && sampleFileId == ''){
					$("<td/>").text('-').appendTo(contentTr);
				}
				
				contentTr.appendTo(portInfoTableBody);
				portInfoTableBody
			}
		}
		
		
	}
	
	/* DataType(Port) Click Event - Info View */
	function <portlet:namespace/>getSelectPortInfo(selectPort){
		
		var portName = $(selectPort).attr("portName");
		var sampleFileId = $(selectPort).attr("sampleFileId");
		var dataTypeName = $(selectPort).attr("dataTypeName");
		var dataTypeVersion = $(selectPort).attr("dataTypeVersion");
		
		var selectPortTypeId = "";
		
		$div = $("#<portlet:namespace/>selectPortInfo_view");
		$div.html("");
		$div.show();
		
		/* 기본 정보 View */
		var closeBtnDiv = $("<div/>").css("text-align", "right").css("margin", "15px 10px");
		$("<i/>").addClass("icon-remove btn btn-default")
				 .attr("onclick", "<portlet:namespace/>closePortInfo()")
				 .css("font-size", "16px").appendTo(closeBtnDiv);
		closeBtnDiv.appendTo($div);
		
		var portDefaultInfo = $("<div/>").css("margin-bottom", "20px");
		var portNameDiv = $("<div/>").css("margin-top", "10px");
		var dataTypeNameDiv = $("<div/>").css("margin-top", "10px");
		
		$("<i/>").addClass("icon-tag").css("font-size", "16px")
				 .text(" Port Name : " + portName).appendTo(portNameDiv);
		$("<i/>").addClass("icon-tag").css("font-size", "16px")
				 .text(" Datatype Name : " + dataTypeName + " v" + dataTypeVersion).appendTo(dataTypeNameDiv);
		portNameDiv.appendTo(portDefaultInfo);
		dataTypeNameDiv.appendTo(portDefaultInfo);
		portDefaultInfo.appendTo($div);
		
		
		/* Sample File */
		if(sampleFileId != ''){
			$.ajax({
				type: "POST",
				url: "<%=getPortSampleFileURL%>",
				async : false,
				data : {"<portlet:namespace/>sampleFileId" : sampleFileId},
				success: function(data) {
					var sampleFileName = data.fileName;
					
					if (typeof sampleFileName != "undefined"){
						var sampleFileDiv = $("<div/>").css("margin-top", "10px");
						$("<i/>").addClass("icon-file").css("font-size", "16px")
								.text(" Sample File").appendTo(sampleFileDiv);
						$("<div/>").addClass("down_date").css("display","block")
								   .css("text-decoration","underline").css("color","blue").css("padding", "10px 20px")
								   .append($("<span/>").attr("onclick","<portlet:namespace/>fileDownload('"+sampleFileId+"')")
													   .css("cursor","pointer").text(sampleFileName)
										   )
								   .appendTo(sampleFileDiv);
						sampleFileDiv.appendTo($div);
					};
				},error:function(msg,e){ 
					alert("<liferay-ui:message key='edison-data-event-error' />\nGet Select Port Info Error");
					return false;
				}
			});
		}
		
		var searchPortData = {
				"<portlet:namespace/>dataTypeName" : dataTypeName,
				"<portlet:namespace/>dataTypeVersion" : dataTypeVersion
			};
		
		$.ajax({
			type: "POST",
			url: "<%=getDataTypeIdURL%>",
			async : false,
			data : searchPortData,
			success: function(data) {
				selectPortTypeId = data.typeId;
			},error:function(msg,e){ 
				alert("<liferay-ui:message key='edison-data-event-error' />\nGet Select Port Info Error");
				return false;
			}
		});
		
		if(selectPortTypeId != "" && selectPortTypeId != "0"){
			$.ajax({
				type: "POST",
				url: "<%=getdataTypeViewURL%>",
				async : false,
				data : {"_edisondatatypeeditor_WAR_edisonappstore2016portlet_typeId" : selectPortTypeId},
				dataType: 'json',
				success: function(data) {
					
					if (data.description != ""){
						var commentDiv = $("<div/>").css("margin-top", "10px");
						$("<i/>").addClass("icon-comment").css("font-size", "16px")
								.text(" Comment").appendTo(commentDiv);
						$("<div/>").addClass("comment").css("font-size","13px")
								   .css("word-break","break-all").css("padding", "10px 20px")
								   .html(data.description).appendTo(commentDiv);
						commentDiv.appendTo($div);
					}
					
					if (typeof data.editor != "undefined"){
						var editortDiv = $("<div/>").css("margin-top", "10px");
						$("<i/>").addClass("icon-edit").css("font-size", "16px")
								.text(" Editor").appendTo(editortDiv);
						$("<div/>").addClass("editor").css("font-size","13px").css("padding", "10px 20px")
								   .html( data.editor).appendTo(editortDiv);
						editortDiv.appendTo($div);
					};
					
					if (typeof data.analyzer != "undefined"){
						var analyzertDiv = $("<div/>").css("margin-top", "10px");
						$("<i/>").addClass("icon-edit").css("font-size", "16px")
								.text(" Analyzer").appendTo(analyzertDiv);
						$("<div/>").addClass("analyzer").css("font-size","13px").css("padding", "10px 20px")
									.html(data.analyzer).appendTo(analyzertDiv);
						analyzertDiv.appendTo($div);
					};
					
					if(data.isStructurePresent){
						
						var structureDiv = $("<div/>").css("margin-top", "10px");
						$("<i/>").addClass("icon-caret-right").css("font-size", "16px")
								.text(" Structure").appendTo(structureDiv);
						var convasDiv = $("<div/>");
						$("<pre/>").addClass("span12 canvasPanel").attr("id", "<portlet:namespace/>structureConvas")
									.css("font-size","13px").appendTo(convasDiv);
						convasDiv.appendTo(structureDiv);
						
						structureDiv.appendTo($div);
						
						<portlet:namespace/>addInputDeckAnalyzer(data.structure);
					}
					
				},error:function(msg,e){ 
					alert("<liferay-ui:message key='edison-data-event-error' />\nGet Select Port Info Error");
					return false;
				}
			});
		} else {
			return false;
		}
	}
	
	/* Close Port Info */
	function <portlet:namespace/>closePortInfo(){
		$div = $("#<portlet:namespace/>selectPortInfo_view");
		$div.html("");
		$div.hide();
	}

	var <portlet:namespace/>dataType = new OSP.DataType();
	function <portlet:namespace/>addInputDeckAnalyzer(structure){
		<portlet:namespace/>dataType.deserializeStructure(structure);
		<portlet:namespace/>loadStructuredData( <portlet:namespace/>dataType );
	}

	/* Draw DataType Structure */
	function <portlet:namespace/>loadStructuredData( dataType ){
		dataType.showStructuredDataViewer(
				'<portlet:namespace/>',
				$('#<portlet:namespace/>structureConvas'),
				'<%=renderRequest.getContextPath()%>',
				'<%=themeDisplay.getLanguageId()%>'
		);
	}
	
	/* Get Execute Statistics */
	function <portlet:namespace/>getScienceAppExeStatistics(){
		
		var scienceAppName = '${solver.name}';
		var groupId = '${solverGroupId}';
		var startDt = '${endDt}';
		var endDt = '${startDt}';
		
		var sendData = {
				"_edisonstatisticsappexec_WAR_edisonsimulationportlet_startDt" : startDt,
				"_edisonstatisticsappexec_WAR_edisonsimulationportlet_endDt" : endDt,
				"_edisonstatisticsappexec_WAR_edisonsimulationportlet_scienceAppName" : scienceAppName,
				"_edisonstatisticsappexec_WAR_edisonsimulationportlet_appExecGroupId" : groupId
		}
		
		bStart();
		jQuery.ajax({
			type: "POST",
			url: "<%=getStatisticsSwExeURL%>",
			data : sendData,
			async : false,
			success: function(data) {
				var monthlyExeStatistics = data.barChartDateList;
				var appStatistics = data.pieChartOrganigationList;
				
				<portlet:namespace/>drawScienceAppExeStatistics(monthlyExeStatistics);
				<portlet:namespace/>drawScienceAppStatistics(appStatistics);
				
				/* if(monthlyExeStatistics != null && monthlyExeStatistics != '' && 0<monthlyExeStatistics.length){
				} else {
					$("#<portlet:namespace/>scienceAppMonthlyExeStatistics").hide();
				}
				
				if(appStatistics != null && appStatistics != '' && 0<appStatistics.length){
				} else {
					$("#<portlet:namespace/>scienceAppStatistics").hide();
				} */
			},error:function(msg,e){ 
				alert("<liferay-ui:message key='edison-data-event-error' />\nGet Statistics SW Execute Error");
				return false;
			},complete: function(){
				bEnd();
			}
		});
	}

	/* Draw Monthly Execute Statistics */
	function <portlet:namespace/>drawScienceAppExeStatistics(monthlyExeStatistics){
		
		var labelsArray = new Array();
		var dataArray = new Array();
		
		for(var i=0; i<monthlyExeStatistics.length; i++){
			var month = monthlyExeStatistics[i]["month"];
			var count = monthlyExeStatistics[i]["count"];
			
			labelsArray.push(month);
			dataArray.push(count);
		}
		
		var areaChartData = {
			labels  : labelsArray,
			datasets: [
				{
					label               : 'Digital Goods',
					fillColor           : 'rgba(60,141,188,0.9)',
					strokeColor         : 'rgba(60,141,188,0.8)',
					pointColor          : '#3b8bba',
					pointStrokeColor    : 'rgba(60,141,188,1)',
					pointHighlightFill  : '#fff',
					pointHighlightStroke: 'rgba(60,141,188,1)',
					data                : dataArray
				}
			]
		}
		
		var areaChartOptions = {
			/* Boolean - If we should show the scale at all */
			showScale : true,
			/* Boolean - Whether grid lines are shown across the chart */
			scaleShowGridLines : false,
			/* String - Colour of the grid lines */
			scaleGridLineColor : 'rgba(0,0,0,.05)',
			/* Number - Width of the grid lines */
			scaleGridLineWidth : 1,
			/* Boolean - Whether to show horizontal lines (except X axis) */
			scaleShowHorizontalLines: true,
			/* Boolean - Whether to show vertical lines (except Y axis) */
			scaleShowVerticalLines : true,
			/* Boolean - Whether the line is curved between points */
			bezierCurve : true,
			/* Number - Tension of the bezier curve between points */
			bezierCurveTension      : 0.3,
			/* Boolean - Whether to show a dot for each point */
			pointDot : false,
			/* Number - Radius of each point dot in pixels */
			pointDotRadius : 4,
			/* Number - Pixel width of point dot stroke */
			pointDotStrokeWidth : 1,
			/* Number - amount extra to add to the radius to cater for hit detection outside the drawn point */
			pointHitDetectionRadius : 20,
			/* Boolean - Whether to show a stroke for datasets */
			datasetStroke : true,
			/* Number - Pixel width of dataset stroke */
			datasetStrokeWidth      : 2,
			/* Boolean - Whether to fill the dataset with a color */
			datasetFill : true,
			/* Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container */
			maintainAspectRatio     : true,
			/* Boolean - whether to make the chart responsive to window resizing */
			responsive : true
		}

		/* Create the area chart */
		var areaChartCanvas = $('#<portlet:namespace/>monthlyExeChart').get(0).getContext('2d');
		var areaChart = new Chart(areaChartCanvas);
		areaChart.Line(areaChartData, areaChartOptions);
	}

	/* Draw ScienceApp Statistics - Pie Chart */
	function <portlet:namespace/>drawScienceAppStatistics(appStatistics){
		
		var jsonObj = new Object();
		var pieDataArray = new Array();
		
		for(var i=0; i<appStatistics.length; i++){
			var exeCount = appStatistics[i]["exe_count"];
			var organization = appStatistics[i]["scienceApp_affiliation_name"];
			var color = '';
			
			if(i%6 == 0){
				color = '#f56954';
			} else if(i%6 == 1){
				color = '#00a65a';
			} else if(i%6 == 2){
				color = '#f39c12';
			} else if(i%6 == 3){
				color = '#00c0ef';
			} else if(i%6 == 4){
				color = '#3c8dbc';
			} else if(i%6 == 5){
				color = '#d2d6de';
			}
			
			jsonObj.value = exeCount;
			jsonObj.color = color;
			jsonObj.highlight = color;
			jsonObj.label = organization;
			
			pieDataArray.push(jsonObj);
		}
		
		var PieData = pieDataArray;
		
		var pieOptions = {
			/* Boolean - Whether we should show a stroke on each segment */
			segmentShowStroke    : true,
			/* String - The colour of each segment stroke */
			segmentStrokeColor   : '#fff',
			/* Number - The width of each segment stroke */
			segmentStrokeWidth   : 2,
			/* Number - The percentage of the chart that we cut out of the middle */
			percentageInnerCutout: 50, // This is 0 for Pie charts
			/* Number - Amount of animation steps */
			animationSteps       : 100,
			/* String - Animation easing effect */
			animationEasing      : 'easeOutBounce',
			/* Boolean - Whether we animate the rotation of the Doughnut */
			animateRotate        : true,
			/* Boolean - Whether we animate scaling the Doughnut from the centre */
			animateScale         : false,
			/* Boolean - whether to make the chart responsive to window resizing */
			responsive           : true,
			/* Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container */
			maintainAspectRatio  : true,
		}
		
		/* Create pie or douhnut chart */
		var pieChartCanvas = $('#<portlet:namespace/>scienceAppChart').get(0).getContext('2d')
		var pieChart = new Chart(pieChartCanvas)
		pieChart.Doughnut(PieData, pieOptions)
	}
	

	/* 2018.09.18 Science App Meta Data(Tag) Setting */
	function scienceAppMetaSetting(){
		
		$("head:first title").text("${solver.name}");
		
		var pageFirstHeadElement = $("head:first");
		$("<meta name='title' content='${solver.name}'>").appendTo(pageFirstHeadElement);
		
		/* var scienceAppDescriptionContent = "${solver.description[descriptionKey]}"; */
		/* scienceAppDescriptionContent.replace(/(<([^>]+)>)/gi, ""); */
		
		/* $("<meta name='description' content='" + scienceAppDescriptionContent + "'>").appendTo(pageFirstHeadElement); */
		$("<meta name='keywords' content=''>").appendTo(pageFirstHeadElement);
		
	}
	
	/* 2018.10.25 Close Review-Reply List */
	function <portlet:namespace/>closeReplyList(boardSeq){
	$("#<portlet:namespace/>replyList_" + boardSeq + " ul.timeline.reply").html("");
	$("#<portlet:namespace/>replyList_" + boardSeq).hide();
	$("a[viewReply=" + boardSeq +"]").show();
	$("a[closeReply=" + boardSeq +"]").hide();
}
	
</script>

<aui:script>
	

function <portlet:namespace/>moveScienceAppExecStatistice(solverName, groupId) {
	
	var URL = "<%=swStatisticsURL%>";
	URL += "&_edisonstatisticsappexec_WAR_edisonsimulationportlet_scienceAppName="+solverName;
	URL += "&_edisonstatisticsappexec_WAR_edisonsimulationportlet_appExecGroupId="+groupId;
	window.open(URL);
	
	/* AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${scienceAppExecPlid}");  
		portletURL.setPortletId("edisonstatisticsappexec_WAR_edisonsimulationportlet");
		portletURL.setParameter("scienceAppName", solverName);
		portletURL.setParameter("appExecGroupId", groupId);
		window.open(portletURL.toString(), '_blank');
	}); */
}

function <portlet:namespace/>runAnalizer() {
	/* alert(Liferay.Language.get("edison-science-appsotre-view-develop-not-yet-exception-alert")); */
	/* AUI().use("liferay-portlet-url", function(a) {
		renderURL.setPortletId( 'Workbench_WAR_OSPWorkbenchportlet');
		renderURL.setWindowState('<%=LiferayWindowState.POP_UP.toString() %>');
	  	renderURL.setParameter("workbenchType", "SINGLERUN");
		renderURL.setParameter("portletId", portletId);
	  	
		renderURL.setParameter("pathType", "file");
		renderURL.setParameter("parentPath", filePath);
		renderURL.setParameter("fileName", fileName);
		renderURL.setParameter("relative", false);
		renderURL.setParameter("loadNow", true);
		
		$("#<portlet:namespace/>show-analyzer-dialog-content").load(renderURL.toString(),{}, function(){
		  $("#<portlet:namespace/>show-analyzer-dialog").dialog('open');
		 });
		
	}); */
}

function <portlet:namespace/>runEditor() {
	alert(Liferay.Language.get("edison-science-appsotre-view-develop-not-yet-exception-alert"));
}

</aui:script>