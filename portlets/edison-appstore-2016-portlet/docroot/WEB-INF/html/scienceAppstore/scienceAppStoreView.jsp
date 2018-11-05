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
<!-- ckeditor -->
 <%
 		Map<String, String> fileBrowserParamsMap = (Map<String, String>)request.getAttribute("liferay-ui:input-editor:fileBrowserParams");
 		
 		String fileBrowserParams = marshallParams(fileBrowserParamsMap);
		
 		StringBundler sb = new StringBundler(8);
		String portletId = portletDisplay.getRootPortletId();
		String mainPath = themeDisplay.getPathMain();

		String doAsUserId = themeDisplay.getDoAsUserId();
		long doAsGroupId = themeDisplay.getDoAsGroupId();
 		Locale siteLocale = themeDisplay.getLocale();
		String doasLocale = siteLocale.getLanguage();

		if (doAsGroupId == 0) {
			doAsGroupId = (Long)themeDisplay.getSiteGroupId();
		}
		
		Group group = GroupLocalServiceUtil.getGroup(doAsGroupId);
		String currentFolder = "/"+doAsGroupId+" - "+"edison"+"/";
		
		sb.append(mainPath);
		sb.append("/portal/fckeditor?p_p_id=");
		sb.append(HttpUtil.encodeURL(portletId));
		sb.append("&doAsUserId=");
		sb.append(HttpUtil.encodeURL(doAsUserId));
		sb.append("&doAsGroupId=");
		sb.append(HttpUtil.encodeURL(String.valueOf(doAsGroupId)));
		sb.append(fileBrowserParams);

		String connectorURL = HttpUtil.encodeURL(sb.toString());
%>
<!-- ------------- -->


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
	<link media="all" rel="stylesheet" href="${contextPath}/AdminLTE-2.4.5/dist/css/skins/_all-skins.min.css" />
	
	<!-- 2018.10.22 _ Markdown CSS -->
	<%-- <link media="all" rel="stylesheet" href="${contextPath}/css/bootstrap-markdown.min.css" />
	<link media="all" rel="stylesheet" href="${contextPath}/css/markdown-style.css" /> --%>
	
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
	
	.content-wrapper{
		background-color: #ededed !important;
	}
	
	.box{
		border-top: 2px solid #3eccca !important;
		box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.07) !important;
	}
	
	</style>
	
</head>

<!-- 2018.09.10 _ adminLTE Theme in ScienceApp Detail Page -->
<body>
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
		<div class="wrapper">
			
			<header class="main-header" style="display: none;">
				<a class="logo" style="text-decoration: block;">
					<span class="logo-lg">
						<b>ScienceApp</b>
					</span>
				</a>
				<nav class="navbar navbar-static-top">
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
						<span class="sr-only">Toggle navigation</span>
					</a>
					<!-- Navbar Right Menu -->
					<div class="navbar-custom-menu">
					</div>
				</nav>
			</header>
			
			<!-- 좌측 메뉴 -->
			<div id="<portlet:namespace/>scienceAppLeftMenu" class="main-sidebar">
			
				<div id="<portlet:namespace/>menuPanel" class="sidebar">
					<div id="<portlet:namespace/>infoInMenuPanel" class="user-panel">
						<div id="<portlet:namespace/>userImageInMenuPanel" class="user-img" align="center">
							<img src="${ownerInfo.portraitURL}" class="img-circle" style="max-height: 80px;">
						</div>
						
						<div id="<portlet:namespace/>userInfoInMenuPanel" class="user-info">
							<div>${ownerInfo.firstName}</div>
							<div class="user-info sub">
								<span>${ownerInfo.screenName}</span> | <span>${ownerInfo.universityFieldNm}</span>
							</div>
						</div>
						
						<div id="<portlet:namespace/>appInfoInMenuPanel" class="app-info">
							
							<div>
								<liferay-ui:message key='edison-table-list-header-date' /> : ${solver.statusDate }
							</div>
						</div>	<!-- End app-info -->
						
					</div> <!-- End user-panel -->
					
					<ul id="<portlet:namespace/>tabInMenuPanel" class="sidebar-menu" data-widget="tree">
						
						<c:if test="${!empty solver.current_manualId}">
							<li class="treeview">
								<label id="<portlet:namespace/>leftManualLabel" style="width: 100%; padding: 12px 5px 12px 15px; cursor: pointer;" onclick="<portlet:namespace/>fileDownload('${solver.current_manualId}');">
									<a href="#">
										<i class="icon-file-alt"></i>
										<span>MANUAL</span>
									</a> 
								</label>
							</li>
						</c:if>
						
						<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel ne downloadOnly}">
							<li class="treeview <portlet:namespace/>executeRun">
								<label id="<portlet:namespace/>leftRunLabel" style="width: 100%; padding: 12px 5px 12px 15px; cursor: pointer;" onclick="<portlet:namespace/>moveWorkbench('${params.solverId}');">
									<a href="#">
										<i class="icon-play-circle"></i>
										<span>RUN</span> 
									</a>
								</label>
							</li>
						</c:if>
						
						<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel eq downloadOnly}">
							<li class="treeview">
								<label id="<portlet:namespace/>leftDownloadLabel" style="width: 100%; padding: 12px 5px 12px 15px; cursor: pointer;" onclick="<portlet:namespace/>fileDownload('${solver.srcFileId }');">
									<a href="#">
										<i class="icon-cloud-download"></i>
										<span>DOWNLOAD</span>
									</a> 
								</label>
							</li>
						</c:if>
						
						<!-- MENU -->
						<li class="header">
							<!-- <i class="icon-chevron-right"></i> -->
							MENU
						</li>
						
						<li class="treeview">
							<label style="width: 100%; padding: 12px 5px 12px 15px; cursor: pointer;">
								<a href=""> 
									<i class="icon-home"></i>
									<span>앱 정보</span> 
									<span id="appInfoMenu" class="pull-right-container" style="float: right;"> 
										<i class="icon-chevron-right"></i>
									</span>
								</a>
							</label>
						</li>
						
						<li class="treeview">
							<label style="width: 100%; padding: 12px 5px 12px 15px; cursor: pointer;">
								<a href=""> 
									<i class="icon-th"></i>
									<span>관련 정보</span> 
									<span id="referInfoMenu" class="pull-right-container" style="float: right;"> 
									</span>
								</a>
							</label>
						</li>
					</ul>
					
				</div> <!-- End slidebar -->
			</div> <!-- End main-slidebar -->
			
			
			<div id="<portlet:namespace/>contentPanel" class="content-wrapper science-app-view">
				
				<!-- ScienceApp Title Section -->
				<section id="<portlet:namespace/>scienceAppTitleSection">
				
					<div class="col-md-12 col-sm-12 col-xs-12">
						
						<!-- ScienceApp Title Section -->
						<div class="box-body box box-solid container-fluid">
							<div class="row">
							
								<div id="<portlet:namespace/>scienceAppImage" class="col-md-1">
									<img src="/documents/${solver.iconRepositoryId}/${solver.iconId}/${solver.iconTitle}/${solver.iconUuid}?imageThumbnail=2" width="104px" style="height: 78px !important;" onerror="this.src='${contextPath}/images/comm_proj/noimage.png'">
								</div>
								
								<div id="<portlet:namespace/>scienceAppTitlePanel" class="col-md-7">	
									<div style="float: left; width: 100%;">
										<div id="<portlet:namespace/>scienceAppMainTitle" class="visualtxt">
											${solver.name} ver${solver.version}
										</div>
										
										<div id="<portlet:namespace/>scienceAppNavTitle" class="visualtxt2">
											${solver.currentTitle} 
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									
									<div class="btn-group pull-right <portlet:namespace/>appExecuteBtnInScienceAppTitlePanel">
										<!-- RUN -->
										<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel ne downloadOnly}">
											<button class="btn btn-success <portlet:namespace/>executeRun" type="button" style="padding-left: 20px; padding-right: 20px;" onclick="<portlet:namespace/>moveWorkbench('${params.solverId}');">
												<i class="icon-play-circle"></i>
												RUN
											</button>
										</c:if>
										
										<!-- MANUAL -->
										<c:if test="${!empty solver.current_manualId}">
											<button class="btn btn-primary" type="button" onclick="<portlet:namespace/>fileDownload('${solver.current_manualId}');">
												<i class="icon-file-alt"></i>
												MANUAL
											</button>
										</c:if>
										
										<!-- DOWNLAOD -->
										<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel eq downloadOnly}">
											<button class="btn btn-info" type="button" onclick="<portlet:namespace/>fileDownload('${solver.srcFileId }');">
												<i class="icon-cloud-download"></i>
												DOWNLOAD
											</button>
										</c:if>
										
										<% if(isLogin) {%>
											<!-- 즐겨찾기 -->
											<button id="<portlet:namespace/>appFavoriteBtn_on" class="btn btn-danger" type="button" style="display: none;" onclick="<portlet:namespace/>deleteFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
												<i class="icon-star" style="color: yellow;"></i>
												<liferay-ui:message key='edison-appstore-bookmark' />
											</button>
											
											<button id="<portlet:namespace/>appFavoriteBtn_off" class="btn btn-default" type="button" style="display: none;" onclick="<portlet:namespace/>addFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
												<i class="icon-star-empty"></i>
												<liferay-ui:message key='edison-appstore-bookmark' />
											</button>
										
											<!-- Rating -->
											<button class="btn btn-warning" type="button" onclick="<portlet:namespace/>myScienceAppRating();" style="padding-left: 20px; padding-right: 20px;">
												<i class="icon-signal"></i>
												<liferay-ui:message key='edison-science-appstore-view-tab-rating' />
											</button>
										<%} %>
										
									</div>
								</div>
								
							</div>
						</div>
					</div>
					
				</section><!-- End ScienceApp Title Section -->
				
				<section id="<portlet:namespace/>scienceAppBasicInfoSection" class="content">
					
					<!-- App Name -->
					<div id="<portlet:namespace/>scienceAppName" class="col-md-4 col-sm-6 col-xs-12 app-panel">
						<div class="info-box">
							<span class="info-box-icon bg-aqua">
								<i class="icon-home"></i>
							</span>
							<div class="info-box-content">
								<span class="info-box-text">
									<liferay-ui:message key='edison-science-appstore-view-app-type' />
								</span>
								<span class="info-box-number" title="${solver.appType }">
									${solver.appType}
								</span>
							</div>
						</div>
					</div>
					
					<!-- ScienceApp Excute Count -->
					<div id="<portlet:namespace/>scienceAppExecuteCnt" class="col-md-4 col-sm-6 col-xs-12 app-panel">
						<div class="info-box">
							<span class="info-box-icon bg-green">
								<i class="icon-user"></i>
							</span>
							<div class="info-box-content">
								<span class="info-box-text">
								
									<liferay-ui:message key='edison-workflow-science-app' /> 
									<liferay-ui:message key='edison-science-appstore-view-Execution-count' /> 
									(<liferay-ui:message key='edison-simulation-monitoring-table-header-averate-running-time' />)
								</span>
								<span class="info-box-number">
									<fmt:formatNumber value="${exeCnt}" pattern="#,###" /> (<fmt:formatNumber value="${avgExeTime}" pattern="#,###" /> sec)
									<%-- ${exeCnt} (${avgExeTime} sec) --%>
								</span>
							</div>
						</div>
					</div>
					
					<!-- Category -->
					<%-- <div id="<portlet:namespace/>scienceAppCategory" class="col-md-3 col-sm-6 col-xs-12 app-panel">
						<div class="info-box">
							<span class="info-box-icon bg-red">
								<i class="icon-tags"></i>
							</span>
							<div class="info-box-content">
								<span class="info-box-text">
									<liferay-ui:message key='edison-science-appstore-view-tab-category' />
								</span>
								<span class="info-box-number" title="${childrenCategory.name}">
									<c:if test="${childrenCategoryList ne null}">
										<c:forEach var="childrenCategory" items="${childrenCategoryList}" varStatus="status">
											<c:if test="${not status.last}">
												${childrenCategory.name}
											</c:if>
											<c:if test="${status.last}">
												${childrenCategory.name}
											</c:if>
										</c:forEach>
									</c:if>
								</span>
							</div>
						</div>
					</div> --%>
					
					<!-- Developer -->
					<%-- <div id="<portlet:namespace/>scienceAppDeveloper" class="col-md-3 col-sm-6 col-xs-12 app-panel">
						<div class="info-box">
							<span class="info-box-icon bg-green">
								<i class="icon-user"></i>
							</span>
							<div class="info-box-content">
								<span class="info-box-text">
									<liferay-ui:message key='developer' />
								</span>
								<span class="info-box-number" title="${developer}">
									<c:forEach var="developer" items="${solver.developers }" varStatus="status">
										<c:if test="${not status.last}">
											${developer}
										</c:if>
										<c:if test="${status.last}">
											${developer}
										</c:if>
									</c:forEach>
								</span>
							</div>
						</div>
					</div> --%>
					
					<!-- Rating -->
					<div id="<portlet:namespace/>scienceAppRating" class="col-md-4 col-sm-6 col-xs-12 app-panel">
						<div class="info-box">
							<span class="info-box-icon bg-yellow">
								<i class="icon-signal"></i>
							</span>
							<div class="info-box-content">
								<span class="info-box-text">
									<liferay-ui:message key='edison-science-appstore-view-tab-rating' />
								</span>
								<span class="info-box-number">
									
								</span>
							</div>
						</div>
					</div>
					
				</section>
				
				<section id="<portlet:namespace/>scienceAppContentSection" class="content">
					
					<section id="<portlet:namespace/>contentDescription" class="col-md-12 col-sm-12 col-xs-12 app-panel">
						<!-- ScienceApp Description -->
						<div class="col-md-12 col-sm-12 col-xs-12 box box-info">
							<div id="<portlet:namespace/>scienceAppDescription" class="box-solid">
								<div class="box-header with-border">
									<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
									<liferay-ui:message key='edison-science-appstore-view-tab-detail-view' />
								</div>
								<div class="box-body">
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
							</div>
						</div>
					</section>
					
					<section id="<portlet:namespace/>scienceAppHistory" class="col-md-2 col-sm-12 col-xs-12 app-panel" style="display: none;">
						<div class="col-md-12 col-sm-12 col-xs-12 box box-primary">
							<div id="<portlet:namespace/>scienceAppHistory" class="box-solid">
								<div class="box-header with-border">
									<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
									HISTORY
								</div>
								<div class="box-body">
								</div>
							</div>
						</div>
					</section>
					
					<!-- ScienceApp Monthly Statistics -->
					<section id="<portlet:namespace/>scienceAppMonthlyExeStatistics" class="col-md-8 col-sm-12 col-xs-12 app-panel">
						<div class=" box box-primary">
							<div class="box-solid edison-panel">
								<div class="box-header with-border">
									<div class="pull-left">
										<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
										<liferay-ui:message key='edison-appstore-month-sts' />
									</div>
									<div class="pull-right">
										<button class="btn btn-default" onclick="<portlet:namespace/>moveScienceAppExecStatistice('${solver.name}','${solverGroupId}');">
											<i class="icon-expand-alt"></i> 
											<liferay-ui:message key='edison-professor-detail' />
										</button>
									</div>
								</div>
								<div class="box-body">
									<div class="chart" style="margin: 20px 0px;">
										<canvas id="<portlet:namespace/>monthlyExeChart" style="height: 180px;"></canvas>
									</div>
								</div>
							</div>
						</div>
					</section>
					
					<!-- ScienceApp Statistics -->
					<section id="<portlet:namespace/>scienceAppStatistics" class="col-md-4 col-sm-12 col-xs-12 app-panel">
						<div class="box box-info">
							<div class="box-solid edison-panel">
								<div class="box-header with-border">
									<div class="pull-left">
										<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
										<liferay-ui:message key='edison-appstore-affiliation-exec-sts' />
									</div>
									<div class="pull-right">
										<button class="btn btn-default" onclick="<portlet:namespace/>moveScienceAppExecStatistice('${solver.name}','${solverGroupId}');">
											<i class="icon-expand-alt"></i> 
											<liferay-ui:message key='edison-professor-detail' />
										</button>
									</div>
								</div>
								<div class="box-body">
									<div class="chart" style="margin: 20px 0px;">
										<canvas id="<portlet:namespace/>scienceAppChart" style="height: 180px;"></canvas>
									</div>
								</div>
							</div>
						</div>
					</section>
					
					<!-- Science App Left Content Section -->
					<section id="<portlet:namespace/>contentLeftArea" class="col-md-6 col-sm-12 col-xs-12 app-panel">
						
						<!-- Relate Paper List -->
						<div id="<portlet:namespace/>scienceAppPaper" class="col-md-12 col-sm-12 col-xs-12 box box-warning">
							<div class="box-solid edison-panel">
								<div class="box-header with-border">
									<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
									<liferay-ui:message key='edison-science-app-paper' />
								</div>
								<div class="box-body">
								</div>
								
								<div class="text-center" style="border-top: 1px solid #f4f4f4;">
								</div>
							</div>
						</div>
						
						<!-- Relate Data List -->
						<div id="<portlet:namespace/>scienceAppRelateData" class="col-md-12 col-sm-12 col-xs-12 box box-success">
							<div class="box-solid">
								<div class="box-header with-border">
									<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
									<liferay-ui:message key='edison-science-appstore-view-relate-info' />
								</div>
								<div class="box-body">
									
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
						
					</section> <!-- End Science App Left Content Section -->
					
					<!-- Science App Right Content Section -->
					<section id="<portlet:namespace/>contentRightArea" class="col-md-6 col-sm-12 col-xs-12 app-panel">
					
						<!-- Port Description -->
						<div class="col-md-12 col-sm-12 col-xs-12 box box-info" id="<portlet:namespace/>scienceAppPortInfo">
							<div class="nav-tabs-custom" style="min-height: 200px; box-shadow: none;">
								<ul id="<portlet:namespace/>scienceAppPortInfoTitle" class="nav nav-tabs pull-right">
									<li id="<portlet:namespace/>logPortInfoArea" class="<portlet:namespace/>port-info-btn" dataType="LOG" onclick="<portlet:namespace/>viewPortInfo('LOG')" style="display: none;">
										<a>
											LOG
										</a>
									</li>
									<li id="<portlet:namespace/>outputPortInfoArea" class="<portlet:namespace/>port-info-btn" dataType="OUTPUT" onclick="<portlet:namespace/>viewPortInfo('OUTPUT')" style="display: none;">
										<a>
											OUTPUT
										</a>
									</li>
									<li id="<portlet:namespace/>inputPortInfoArea" class="<portlet:namespace/>port-info-btn" dataType="INPUT" onclick="<portlet:namespace/>viewPortInfo('INPUT')" style="display: none;">
										<a>
											INPUT
										</a>
									</li>
									<li class="pull-left header" style="padding: 10px;">
										<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
										<liferay-ui:message key="edison-science-appstore-view-port-info" />
									</li>
								</ul>
								
								<div class="tab-content" style="padding: 10px 20px;">
									<div class="tab-pane" id="<portlet:namespace/>inputPortInfo_content">
									</div>
									
									<div class="tab-pane" id="<portlet:namespace/>outputPortInfo_content">
									</div>
									
									<div class="tab-pane" id="<portlet:namespace/>logPortInfo_content">
									</div>
									
									<div id="<portlet:namespace/>selectPortInfo_view">
									</div>
									
								</div>
							</div>
						</div>
						
					</section> <!-- End Science App Right Content Section -->
					
					<section id="<portlet:namespace/>contentTimelineArea" class="col-md-12 col-sm-12 col-xs-12 app-panel">
						<div class="col-md-12 col-sm-12 col-xs-12 container-fluid box box-info">
							<div class="box-solid edison-panel row">
							
								<div class="box-header with-border">
									<i class="icon-chevron-right <portlet:namespace/>science-app-content-title"></i>
									Review
								</div>
								<div class="box-body">
									<div class="col-md-12" style="margin: 10px 0px 20px 0px;">
										<ul class="timeline main" style="width: 100%;">
										</ul>
										
										<div style="width: 100%; padding: 20px 30px;">
											<% if(!isLogin) {%>
												<textarea class="<portlet:namespace/>review-editor" rows="3" cols="" placeholder="<liferay-ui:message key='edison-simulation-please-login' />." disabled="disabled" style="resize: none;">
												</textarea>
											<% } else { %>
												<textarea id="<portlet:namespace/>reviewEditor_0" class="<portlet:namespace/>review-editor" rows="5" cols="" placeholder="<liferay-ui:message key='edison-board-enter-content-alert' />" style="resize: none;">
												</textarea>
												<div class="pull-right" style="margin: 10px 0px;">
													<button id="<portlet:namespace/>reviewSaveBtn" class="btn btn-primary" onclick="<portlet:namespace/>saveReview(0)">SAVE</button>
												</div>
											<% }%>
										</div>
										
										<%-- 
										<% if(!isLogin) {%>
											<div id="<portlet:namespace/>mdEditor">
												<input class="form-control" type="text" placeholder="<liferay-ui:message key='edison-simulation-please-login' />." disabled="disabled" />
											</div>
											
											<div id="<portlet:namespace/>mdEditor" class="col-md-12" style="display: none;">
										<% } else { %>
											<div id="<portlet:namespace/>mdEditor" class="col-md-12">
										<% }%>
												<div id="comment-md-preview-container" class=""> 
													<div style="font-size: 20px; font-weight: 600; margin-bottom: 15px;">
														Preview
													</div>
													
													<div class="well well-sm well-light md-preview margin-top-10" id="comment-md-preview" style="height: 200px;">
													</div>
												</div>
												
												<div id="comment-md-input-container" class="">
													<textarea id="comment-md" name="comment" placeholder="<liferay-ui:message key='edison-board-enter-content-alert' />"></textarea>
												</div>
												
												<div id="<portlet:namespace/>commentBtn" class="pull-right" style="margin: 10px 0px;">
													<button id="<portlet:namespace/>reviewSaveBtn" class="btn btn-primary" onclick="<portlet:namespace/>reviewSave(0)">SAVE</button>
													<button id="<portlet:namespace/>reviewReplySaveBtn" class="btn btn-primary" style="display: none;">SAVE</button>
													<button id="<portlet:namespace/>reviewCancelBtn" class="btn btn-default" onclick="<portlet:namespace/>writeReview()" style="display: none;">Cancel</button>
												</div>
											</div>
										 --%>
									</div>
								</div>
							</div>
						</div>
								
					</section>
					
				</section> <!-- End Science App Content Section -->
				
			</div> <!-- End content-wrapper science-app-view -->
			
		</div> <!-- End wrapper -->
	</div> <!-- End hold-transition skin-blue sidebar-mini -->
	
	<!-- 원본 -->
<div style="display: none;">
	<div class="topvisual">
		<div class="visualimg">
			<img src="/documents/${solver.iconRepositoryId}/${solver.iconId}/${solver.iconTitle}/${solver.iconUuid}?imageThumbnail=2" width="104px" style="height: 78px !important;" onerror="this.src='${contextPath}/images/comm_proj/noimage.png'">
		</div> 
		<div class="visualtxt" id="scienceAppTitle"></div>
		<div class="visualtxt2">
			<c:choose>
				<c:when test="${empty redirectName }">
					${solver.name}
				</c:when>
				<c:otherwise>
					<a onClick="<portlet:namespace/>historyBack();"
						style="cursor: pointer;"> ${redirectName} </a>  > ${solver.name}
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 좌측	 -->
	<div class="commleft panel edison-panel" style="padding: 0px 18px 0px 0px;">
		<form id="<portlet:namespace/>solverInfoForm" name="<portlet:namespace/>solverInfoForm" method="POST"  action="${actionUrl}" onsubmit="return <portlet:namespace/>solverInfoFormCheck()">
			<input type="hidden" id="<portlet:namespace/>selectLocaleId" name="<portlet:namespace/>selectLocaleId" value="${solver.selectLocaleId}"/>
			<div class="panel-heading clearfix detailViewSubTitle" style="border-bottom: 0px;">
				<h3 class="panel-title pull-left">
					<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
					<liferay-ui:message key='edison-science-appstore-view-tab-detail-view' />
				</h3>
				
				<div class="btn-group pull-right">
					<c:if test="${contentCheckAuth eq 'TRUE' }">
						<input type="button" id="tabs-1" class="btn btn-default" value="<liferay-ui:message key='edison-button-board-modify' />" onClick="<portlet:namespace/>detailInfoModify(); return false;"/>
					</c:if>
						<input type="button" id="tabs-1" class="btn btn-default" 
							value="<liferay-ui:message key='edison-science-appstore-view-tab-sw-statistics' />" onClick="<portlet:namespace/>moveScienceAppExecStatistice('${solver.name}','${solverGroupId}');"/>	
				</div>
			</div>
				
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<c:if test="${contentCheckAuth eq 'FALSE' }">
					<tr>
					<td width="100%">
						<%
							for(Locale aLocale : locales){
								String languageId = LocaleUtil.toLanguageId(aLocale);
								String descriptionKey = "description_"+languageId;
						%>	
							<c:set var="descriptionKey" value="<%=descriptionKey%>"></c:set>
							<div id="<portlet:namespace/>descriptionDiv_<%=languageId%>">
								${solver.description[descriptionKey] }
							</div>
						<%
							}
						%>
					</td>	
					</tr>
				</c:if>
				<c:if test="${contentCheckAuth eq 'TRUE' }">
					<tr>
						<td width="100%">
							<aui:select name="serviceLocaleId" label="" onChange="changeLocale(this.value)">
								<%
								for(Locale aLocale : locales){
									String languageId = LocaleUtil.toLanguageId(aLocale);
									if(localesStr.equals("")){
										localesStr += languageId;
									}else{
										localesStr += ","+languageId;
									}
									
									String languageNm =aLocale.getDisplayName(themeDisplay.getLocale());
								%>
									<c:set var="langId" value="<%=languageId%>"></c:set>
									<aui:option label="<%=languageNm%>" value="<%=languageId%>" selected="${solver.selectLocaleId eq langId ? true : false}"/>
								<%} %>
							</aui:select>
							<br>
						</td>
					</tr>
					<tr>
						<td width="100%">
						<%
							for(Locale aLocale : locales){
								String languageId = LocaleUtil.toLanguageId(aLocale);
								String descriptionKey = "description_"+languageId;
						%>	
							<c:set var="descriptionKey" value="<%=descriptionKey%>"></c:set>
							<div id="<portlet:namespace/>descriptionDiv_<%=languageId%>">
								<textarea id="<portlet:namespace/>description_<%=languageId%>" name="<portlet:namespace/>description_<%=languageId%>" style="width:100%;height:300px;">${solver.description[descriptionKey] }</textarea>
							</div>
						<%
							}
						%>	
						</td>
					</tr>
				</c:if>
			</table>
			
			<c:if test="${!empty solver.openLevel && !empty solver.srcFileId}">
				<div class="h4" style="float: left;">	
					<c:if test="${solver.openLevel eq 'OPEN_SOURCE'}">
						<liferay-ui:message key='edison-science-appstore-view-source-code-download' />
					</c:if>
					<c:if test="${solver.openLevel eq 'OPEN_RUN_ONLY'}">
						<liferay-ui:message key='edison-science-appstore-view-execute-file-download' />
					</c:if>
					<c:if test="${!empty solver.srcFileId }">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
							<tr>
								<td>
									<span style="cursor:pointer; margin-right:5px;" onclick="<portlet:namespace/>fileDownload('${solver.srcFileId }')" class="onMouseHover">
										<img src="${contextPath}/images/fileicon2.png" width="16" height="16" />&nbsp;${solver.srcFileTitle }
									</span>
								</td>
							</tr>	
						</table>
					</c:if>
				</div>	
			</c:if>	
			
		</form>
		<!--추천 프로젝트-->
		<%-- <div>
			<liferay-portlet:runtime
				portletName="edisonscienceappprolink_WAR_edisonappstore2016portlet"
				defaultPreferences="" 
				queryString="&entryId=${scienceAppEntryId}&redirectName=${redirectName}&redirectURL=${redirectURL}&isMgrBtn=${contentCheckAuth eq 'TRUE' ? 'true' : 'false' }"/>
		</div> --%>
		<!--관련자료 -->
		<div>
			<liferay-portlet:runtime portletName="edisonrelateasset_WAR_edisondefault2016portlet" defaultPreferences="" queryString="&entryId=${scienceAppEntryId}&isMgrBtn=${contentCheckAuth eq 'TRUE' ? 'true' : 'false' }&isVirTitle=true&isAppstore=true"/>
		</div>
		<!--Q&A 게시판 -->
		<div>
		</div>
	</div>
	
	<div class="commrighttop" style="border-radius: 0; width: 210px;">
		<div class="commtopbox" style="border-radius: 0; border: none;">
			<img src="${ownerInfo.portraitURL}" style="width:65px !important; height:65px !important;">
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
	<!-- 우측	 -->
	<div class="commright">
		<div class="commrighttxt">
			<ul>
				<li class="stxt2">
				<% if(isLogin) {%>
					<div id="favorites_off" class="favorites" style="display:none;" onclick="<portlet:namespace/>addFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
						<img src="${contextPath}/images/scienceappstorelist/favoriteicon.png" width="20" height="18" /> <liferay-ui:message key='edison-appstore-bookmark' />
					</div>
					<div id="favorites_on" class="favorites" style="display:none;" onclick="<portlet:namespace/>deleteFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
						<img src="${contextPath}/images/scienceappstorelist/favoriteiconon.png" width="20" height="18" /> <liferay-ui:message key='edison-appstore-bookmark' />
					</div>
				<% } %>
				</li>
				<li class="stxt2 wbba"><liferay-ui:message key='edison-virtuallab-version' /> : ${solver.version }</li>
				<li class="stxt2 wbba"><liferay-ui:message key='edison-table-list-header-orgNm' /> : ${solver.affiliation }</li>
				<li class="stxt2 wbba"><liferay-ui:message key='edison-table-list-header-date' /> : ${solver.statusDate }</li>
				<li class="stxt2 wbba">
					<ul>
						<li class="stxt2 wbba"><liferay-ui:message key='developer' /></li>
						<li class="stxt2 wbba" style="padding-bottom: 0px;">
							<c:forEach var="developer" items="${solver.developers }" varStatus="status">
								<c:if test="${not status.last}"><p style="margin: 0 0 12px 22px;">${developer}</p></c:if>
								<c:if test="${status.last}"><p style="margin: 0 0 0 22px;">${developer}</p></c:if>
							</c:forEach>
						</li>
					</ul>
				</li>
				<li class="stxt2 last" style="text-align:center;">
					<c:if test="${!empty solver.current_manualId}">
						<img src="${contextPath}/images/scienceappstorelist/btn_manual.jpg" width="75" height="25" style="cursor:pointer;" onClick="<portlet:namespace/>fileDownload('${solver.current_manualId}')" />
					</c:if>
					<c:if test="${empty solver.current_manualId}">
						<img src="${contextPath}/images/btn_manual_none.jpg" width="75" height="30" />
					</c:if>
					
					<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel ne downloadOnly}">
						<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="25" style="cursor:pointer;" onClick="<portlet:namespace/>moveWorkbench('${params.solverId}');"/>
					</c:if>
					
					<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel eq downloadOnly}">
						<button class="btn btn-default" style="cursor:pointer; width: 80px; height: 25px; padding: 0px;" 
							onclick="<portlet:namespace/>fileDownload('${solver.srcFileId }')">
						<i class="icon-download-alt"></i>Download
					</button>
					</c:if>
				</li>
			</ul>
			<ul>
				<li><liferay-ui:message
						key='edison-science-appstore-view-tab-category' /></li>
				<c:if test="${childrenCategoryList ne null}">
					<c:forEach var="childrenCategory"
						items="${childrenCategoryList}" varStatus="status">
						<c:if test="${not status.last}">
							<li class="stxt2 wbba">${childrenCategory.name}</li>
						</c:if>
						<c:if test="${status.last}">
							<li class="stxt2 wbba last">${childrenCategory.name}</li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${childrenCategoryList eq null}">
					<li class="stxt2 wbba last"></li>
				</c:if>
			</ul>
		</div>
		
		<!--관련자료 통계-->
		<div>
			<liferay-portlet:runtime
				portletName="edisonrelateassetstatistic_WAR_edisonsimulationproject2017portlet"
				defaultPreferences=""
				queryString="&modelId=${scienceAppEntryId}" />
		</div>
		<c:if test="${fn:length(historyAppList) > 0}">
			<div class="commrighttxt">
				<ul>
					<li>
						History
					</li>
				</ul>
			</div>			
			<div class="commrighttxt">
				<c:forEach var="historyApp" items="${historyAppList}" varStatus="status">
					<ul>
						<li class="stxt2 wbba"><liferay-ui:message key="edison-virtuallab-app-name"/> : ${solver.name}</li>
						<li class="stxt2 wbba"><liferay-ui:message key='edison-virtuallab-version' /> : ${historyApp.version }</li>
						<li class="stxt2 wbba"><liferay-ui:message key='edison-table-list-header-date' /> : ${historyApp.statusDate }</li>
						<li class="stxt2 wbba <c:if test="${not status.last}">last</c:if>" style="text-align:center;">
							<c:if test="${!empty historyApp.current_manualId}">
								<img src="${contextPath}/images/scienceappstorelist/btn_manual.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>fileDownload('${historyApp.current_manualId}')" />
							</c:if>
							<c:if test="${empty historyApp.current_manualId}">
								<img src="${contextPath}/images/btn_manual_none.jpg" width="75" height="30" />
							</c:if>
							<c:if test="${historyApp.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn}">
								<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>moveWorkBench('${historyApp.scienceAppId}');"/>
							</c:if>
							<c:if test="${historyApp.appType eq 'Editor' and isSignedIn}">							
								<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>runEditor('${historyApp.scienceAppId}', ${historyApp.exeFileNm});"/>
							</c:if>
							<c:if test="${historyApp.appType eq 'Analyzer' and isSignedIn}">
							${historyApp}
								<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>runAnalizer(('${historyApp.scienceAppId}');"/>
							</c:if>
						</li>
					</ul>
				</c:forEach>
			</div>
		</c:if>
	</div>
	<c:if test="${isMainAppSearch eq true }">	
	</div>
	</c:if>

	<div id="<portlet:namespace/>show-analyzer-dialog">
		<div id="<portlet:namespace/>show-analyzer-dialog-content"></div>
	</div>
</div>

</body>
<style>
	tab_content{
		display:none;
	}
</style>

<!-- ckeditor  -->
<%!
public String marshallParams(Map<String, String> params) {
	StringBundler sb = new StringBundler();

	if (params != null) {
		for (Map.Entry<String, String> configParam : params.entrySet()) {
				sb.append(StringPool.AMPERSAND);
				sb.append(configParam.getKey());
				sb.append(StringPool.EQUAL);
				sb.append(HttpUtil.encodeURL(configParam.getValue()));
		}
	}

	return sb.toString();
}
%>
	<!-- -------------  -->
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
	
	/* $("#<portlet:namespace/>scienceAppMainTitle").append(cutStr("${solver.name }", 80));
	$("#<portlet:namespace/>scienceAppMainTitle").append(cutStr("${solver.name }", 80)); */
	
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

	/* setCKeditor(); */
	
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
	
	$("#nsubtopwrap").css("display", "none");
	
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
	
	/* 앱 실행 통계 */
	<portlet:namespace/>getScienceAppExeStatistics();
	
	/* 앱 리뷰 */
	<portlet:namespace/>getScienceAppReviewList(0);
	$(".<portlet:namespace/>review-editor").text("");
	
	changeLocale("${solver.selectLocaleId}");
	
	<portlet:namespace/>testGetHistory();
});

function <portlet:namespace/>testGetHistory(){
	
	var name = "${solver.name}";
	var companyId = "${solver.companyId}";
	var groupId = "${solver.groupId}";
	
	var sendData = {
			"<portlet:namespace/>name": name,
			"<portlet:namespace/>companyId": companyId,
			"<portlet:namespace/>groupId": groupId
	}
	
	console.log(name + " / " + companyId + " / " + groupId);
	
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
			alert("History Error");
			return false;
		}
	});
}

/* Insert Review */
function <portlet:namespace/>saveReview(boardSeq){
	
	var content = "";
	if(boardSeq  == 0){
		content = $("#<portlet:namespace/>reviewEditor_" + boardSeq).val();
	} else {
		content = $("#<portlet:namespace/>reviewEditor_" + boardSeq + " textarea").val();
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
			alert("Save Review Error");
			return false;
		}
	});
}

/* Update Review */
function <portlet:namespace/>updateReview(boardSeq){
	
	var content = $("#<portlet:namespace/>reviewEditor_" + boardSeq + " textarea").val();
	
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
			alert("Update Review Error");
			return false;
		}
	});
}

/* Get ScienceApp Timeline */
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
				<portlet:namespace/>viewScienceAppReview(getScienceAppReviewList, groupBoardSeq, "timeline");
			} else {
				<portlet:namespace/>viewScienceAppReview(getScienceAppReviewList, groupBoardSeq, "reply");
			}
			
			changeLocale("${solver.selectLocaleId}");
		},error:function(msg,e){ 
			alert("Get Review List Error");
			return false;
		}
	});
	
}

/* View ScienceApp Review List */
function <portlet:namespace/>viewScienceAppReview(getScienceAppReviewList, groupBoardSeq, boardType){
	var timeline = "";
	
	if(boardType == "timeline"){
		
		timeline = $("#<portlet:namespace/>contentTimelineArea .timeline.main");
		
	} else if(boardType == "reply"){
		
		$("#<portlet:namespace/>replyList_" + groupBoardSeq).show();
		$("a[viewReply=" + groupBoardSeq +"]").hide();
		$("a[closeReply=" + groupBoardSeq +"]").show();
		timeline = $("#<portlet:namespace/>replyList_" + groupBoardSeq + " > .timeline.reply");
		timeline.html("");
		
	}
	
	var writerDate = '';
	for(var idx=0; idx<getScienceAppReviewList.length; idx++){
		var getScienceAppReviewMap = getScienceAppReviewList[idx];
		
		var getWriterDate = getScienceAppReviewMap.writerDate;
		
		/* Timeline Date li 생성 */
		if(boardType == "timeline" && writerDate != getWriterDate){
			writerDate = getWriterDate;
			var timeLabel = $("<li/>").addClass("time-label");
			$("<span/>").addClass("bg-green")
						.text(writerDate)
						.appendTo(timeLabel);
			
			timeLabel.appendTo(timeline);
		}
		
		/* Timeline Content 생성 */
		var boardSeq = getScienceAppReviewMap.boardSeq;
		var groupBoardSeq = getScienceAppReviewMap.groupBoardSeq;
		var content = (getScienceAppReviewMap.content).replace(/&lt;/gi, "<");
		var writerId = getScienceAppReviewMap.writerId;
		var writerTime = getScienceAppReviewMap.writerTime;
		var writerDateForReply = getScienceAppReviewMap.writerDateForReply;
		var screenName = getScienceAppReviewMap.screenName;
		var replyCnt = getScienceAppReviewMap.replyCnt;
		
		var timelineContent = $("<li/>");
		$("<i/>").addClass("fa fa-user bg-aqua").appendTo(timelineContent);
		
		var timelineItem = $("<div/>").addClass("timeline-item")
									  .attr("boardSeq", boardSeq);
		
		var screenNameArea = $("<h3/>").addClass("timeline-header no-border")
		$("<a/>").attr('writerId', writerId).text(screenName).appendTo(screenNameArea);
		
		if('${thisUserId}' == writerId){
			var btnGroup = $("<div/>").addClass("pull-right btn-group");
			
			var writerDt = "";
			if(boardType == "timeline"){
				writerDt = writerTime;
			} else if(boardType == "reply"){
				writerDt = writerDateForReply;
			}
			
			$("<div/>").addClass("btn btn-default btn-xs")
						.css("padding-left", "20px")
						.css("padding-right", "20px")
						.css("cursor", "default")
						.css("border", "none")
						.append( $("<i/>").addClass("icon-time").text(" " + writerDt) )
						.appendTo(btnGroup);
			
			settingBtn = $("<button/>").addClass("btn btn-info btn-xs dropdown-toggle")
										.attr("type", "button")
										.attr("data-toggle", "dropdown")
										.append( $("<i/>").addClass("icon-cog") )
										.appendTo(btnGroup);
			
			/* DropDown Button Setting */
			var dropdownMenuUl = $("<ul/>").addClass("dropdown-menu pull-right").attr("role", "menu");
			$("<li/>").addClass("<portlet:namespace/>timeline-set-btn")
						.attr("onclick", "<portlet:namespace/>updateScienceAppReview(" + boardSeq + ")")
						.append( 
							$("<a/>").css("padding", "6px 20px").append( $("<i/>").addClass("icon-edit").text(" UPDATE") )
								)
						.appendTo(dropdownMenuUl);
			$("<li/>").addClass("<portlet:namespace/>timeline-set-btn")
						.attr("onclick", "<portlet:namespace/>deleteScienceAppReview(" + boardSeq + ")")
						.append(
							$("<a/>").css("padding", "6px 20px").append( $("<i/>").addClass("icon-trash").text(" DELETE") )
								)
						.appendTo(dropdownMenuUl);
			
			dropdownMenuUl.appendTo(btnGroup);
			
			btnGroup.appendTo(screenNameArea);
			
		}
		screenNameArea.appendTo(timelineItem);
		
		/* View content */
		var timelineBody = $("<div/>").addClass("timeline-body")
									  .attr("id", "<portlet:namespace/>board_"+boardSeq)
									  .attr("groupBoardSeq", groupBoardSeq)
									  .html(content);
		
		
		var viewReplyAnchor = "";
		var closeReplyAnchor = "";
		var replyRegistAnchor = "";
		/* Exist Reply */
		if(0 < replyCnt){
			/* getScienceAppReviewList() viewReplyList()  */
			viewReplyAnchor = $("<a/>").attr("onclick", "<portlet:namespace/>getScienceAppReviewList("+boardSeq+");")
										.attr("viewReply", boardSeq)
										.css("text-decoration", "none")
										.css("margin-right", "20px")
										.css("cursor", "pointer")
										.text("<liferay-ui:message key='edison-comment-view' /> (" + replyCnt + ")");
			
			closeReplyAnchor = $("<a/>").attr("onclick", "<portlet:namespace/>closeReplyList("+boardSeq+");")
										.attr("closeReply", boardSeq)
										.css("text-decoration", "none")
										.css("margin-right", "20px")
										.css("display", "none")
										.css("cursor", "pointer")
										.text("<liferay-ui:message key='edison-comment-close' />");
		}
		
		/* Write Reply Anchor */
		if(${isSignedIn}){
			replyRegistAnchor = $("<a/>").attr("onclick", "<portlet:namespace/>showReviewEditor("+boardSeq+", '', 'INSERT');")
									.css("text-decoration", "none")
									.css("cursor", "pointer")
									.text("<liferay-ui:message key='edison-comment-register' />")
		}
		
		$("<div/>").css("padding-top", "20px")
					.append(viewReplyAnchor)
					.append(closeReplyAnchor)
					.append(replyRegistAnchor)
					.appendTo(timelineBody);
		
		timelineBody.appendTo(timelineItem);
		timelineItem.appendTo(timelineContent);
		
		/* Reply List Area */
		$("<div/>").attr("id", "<portlet:namespace/>replyList_"+boardSeq)
					.css("padding", "20px 50px")
					.css("display", "none")
					.append($("<ul>").addClass("timeline reply").css("width", "100%"))
					.appendTo(timelineContent);
		
		/* Reply Editor Area */
		$("<div/>").addClass("<portlet:namespace/>reply-input")
					.attr("id", "<portlet:namespace/>reviewEditor_"+boardSeq)
					.css("display", "none")
					.css("padding", "20px 30px 20px 70px")
					.appendTo(timelineContent);
		
		timelineContent.appendTo(timeline);
	}
	
	$("<li/>").append($("<i/>").addClass("fa fa-clock-o bg-gray"))
			  .css("margin-bottom", "25px")
			  .appendTo(timeline);
}

/* View Reply Editor */
function <portlet:namespace/>showReviewEditor(boardSeq, content, orderType){
	var reviewEditor = $("#<portlet:namespace/>reviewEditor_"+boardSeq);
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
	} else if(orderType == 'UPDATE'){
		$("<button/>").addClass("btn btn-primary")
						.attr("onclick", "<portlet:namespace/>updateReview("+boardSeq+")")
						.css("margin-right", "10px")
						.text("UPDATE")
						.appendTo(commentBtnDiv);
	}
	
	
	$("<button/>").addClass("btn btn-default")
					.attr("onclick", "<portlet:namespace/>cancelReview("+boardSeq+")")
					.text("CANCEL")
					.appendTo(commentBtnDiv);
	
	commentBtnDiv.appendTo(reviewEditor).focus();
}

function <portlet:namespace/>cancelReview(boardSeq){
	var reviewEditor = $("#<portlet:namespace/>reviewEditor_"+boardSeq);
	reviewEditor.html("").hide();
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
						alert("Delete Review Error");
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

function setCKeditor(){  
	var fileBrowserConectorURL = "<%=connectorURL%>";
	fileBrowserConectorURL = fileBrowserConectorURL +"&currentFolder=${currentFolder}";
	var ckEditorLanguage = "<%=doasLocale%>";
	CKEDITOR.config.autoParagraph = false;
	CKEDITOR.config.tabSpaces = 0;
	if("${contentCheckAuth}" == 'TRUE'){
		<%
			for(Locale aLocale : locales){
				String languageId = LocaleUtil.toLanguageId(aLocale);
		%>	
			var languageId = "<%=languageId%>";
			CKEDITOR.replace( "<portlet:namespace/>description_"+languageId , {
				filebrowserImageBrowseUrl: "/edison-appstore-2016-portlet/editor/ckeditor/filemanger/browser.html?Connector="+fileBrowserConectorURL,
				language: ckEditorLanguage,
			    filebrowserUploadUrl: null,
			    toolbar : [
			        		['Styles', 'FontSize', '-', 'TextColor', 'BGColor'],
			         		['Bold', 'Italic', 'Underline', 'Strike'],
			         		['Subscript', 'Superscript'],
			         		'/',
			         		['Undo', 'Redo', '-', 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'SelectAll', 'RemoveFormat'],
			         		['Find', 'Replace', 'SpellChecker', 'Scayt'],
			         		['Outdent','Indent','Blockquote'],
			         		['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			         		'/',
			         		['Source'],
			         		['Link', 'Unlink', 'Anchor'],
			         		['Image', 'Flash',  'Table', '-', 'Smiley', 'SpecialChar', 'LiferayPageBreak'],
			         		['A11YBtn']
			         	]
			});
			
			if(languageId != "${solver.selectLocaleId}" ){
				$("#<portlet:namespace/>descriptionDiv_"+languageId).hide();
			}
		<%	
			}
		%>
	}else{
		<%
		for(Locale aLocale : locales){
			String languageId = LocaleUtil.toLanguageId(aLocale);
		%>	
			var languageId = "<%=languageId%>";
			if(languageId != "${solver.selectLocaleId}" ){
				$("#<portlet:namespace/>descriptionDiv_"+languageId).hide();
			}
		<%	
			}
		%>
	}
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
		}else{
			$("#<portlet:namespace/>descriptionDiv_"+languageId).show();
			$("#<portlet:namespace/>contentTimelineArea .timeline-body content[language-id="+languageId+"]").show();
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
			alert("Add FavoriteApp Error");
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
				alert("Delete FavoriteApp Error");
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
			alert("Move Workbench Error");
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
				alert("Set Rating Error");
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
						$(".text-center").html("");
						$(".text-center").append(pagingStr);
					}
					
				} else {
					$("#<portlet:namespace/>scienceAppPaper").css("display", "none");
				}
				
			},error:function(msg,e){ 
				alert("Search Paper Error");
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
				alert("Get RelateAssetEntry Error");
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
		
		if(portType=='INPUT'){
			$("#<portlet:namespace/>inputPortInfoArea").show();
			scienceApp.deserializeInputPorts(JSON.parse(scienceAppPortStr));
			portInfoViewDiv = $("#<portlet:namespace/>inputPortInfo_content");
			
			scienceAppPortArray = scienceApp.inputPortsArray();
		}else if(portType=='OUTPUT'){
			$("#<portlet:namespace/>outputPortInfoArea").show();
			scienceApp.deserializeOutputPorts(JSON.parse(scienceAppPortStr));
			portInfoViewDiv = $("#<portlet:namespace/>outputPortInfo_content");
			
			scienceAppPortArray = scienceApp.outputPortsArray();
		}else if(portType=='LOG'){
			$("#<portlet:namespace/>logPortInfoArea").show();
			scienceApp.deserializeLogPorts(JSON.parse(scienceAppPortStr));
			portInfoViewDiv = $("#<portlet:namespace/>logPortInfo_content");
			
			scienceAppPortArray = scienceApp.logPortsArray();
		}
		
		$("#<portlet:namespace/>emptyScienceAppPort").hide();
		
		if(scienceAppPortArray.length !=0){
			
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
				
				
				$("<button/>").addClass("btn btn-app col-md-4 col-sm-12")
							  .attr("title", dataTypeName)
							  .attr("portName", portName)
							  .attr("sampleFileId", sampleFileId)
							  .attr("dataTypeName", dataTypeName)
							  .attr("dataTypeVersion", dataTypeVersion)
							  .attr("onclick", "<portlet:namespace/>getSelectPortInfo(this)")
							  .css("margin", "5px 0px")
							  .css("overflow", "hidden")
							  .css("white-space", "nowrap")
							  .css("text-overflow", "ellipsis")
							  .text(dataTypeName)
							  .appendTo(portInfoViewDiv);
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
					alert("Get Select Port Info Error");
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
				alert("Get Select Port Info Error");
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
					alert("Get Select Port Info Error");
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
				alert("Get Statistics SW Execute Error");
				return false;
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
		areaChart.Line(areaChartData, areaChartOptions)
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