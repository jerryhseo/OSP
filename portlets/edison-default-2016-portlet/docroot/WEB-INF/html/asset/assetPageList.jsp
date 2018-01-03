<%@page
	import="org.kisti.edison.asset.portlet.AssetCustomModelConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>
<%
	//Custom Model
	String COMMUNITY = (String) request.getAttribute("COMMUNITY");
	String SCIENCE_APP = (String) request.getAttribute("SCIENCE_APP");
	String CONTENT = (String) request.getAttribute("CONTENT");
	String OPEN_DATA = (String) request.getAttribute("OPEN_DATA");
	String PROJECT = (String) request.getAttribute("PROJECT");
	
%>

<liferay-portlet:resourceURL var="retrieveListLinkedAssetEntryURL" 	id="retrieveListLinkedAssetEntry" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:renderURL var="assetLinkViewDialogueURL"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>"
	copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="assetLinkViewDialogue" />
</liferay-portlet:renderURL>

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
	
<style>
#<portlet:namespace />categoryArea {
	width: 20%;
	padding : 12px 15px 10px 20px;
	margin:0;
}

#<portlet:namespace />titleArea, #<portlet:namespace />detailArea {
	width: 34%;
	padding: 12px 15px 10px 20px;
	margin :0;
}

#<portlet:namespace />titleArea > li{
	word-break:break-all;
}

.detailAreaBt {
/* 	text-align: right; */
	position: absolute;
	right: 2%;
	bottom: 5%;
}
</style>
<script>
var categoryAreaValue = "<%=SCIENCE_APP%>";
$(function(){
	<portlet:namespace/>setAssetLinkList();
	
	$("ul#<portlet:namespace/>categoryArea").on("click", "li", function(){
		/* $(this).find('a').addClass('click');
		$(this).siblings().find('a').removeClass("click");
	
		categoryAreaValue = $("#<portlet:namespace/>categoryArea a.click").parent().attr("id");
		 */
		 
		$(this).addClass('active');
		$(this).siblings().removeClass("active");
		categoryAreaValue = $(this).attr("id");
		<portlet:namespace/>setAssetLinkList();
	});
	
	$("ul#<portlet:namespace/>titleArea").on("click", "li", function(){
		$(this).addClass('active');
		$(this).siblings().removeClass("active");
		
		//클릭한 내용 넣기
		$detailArea = $("#<portlet:namespace/>detailArea");
		$detailArea.empty();

		var description = $(this).attr("data-model-3depth"); //app -> title, content등 -> description
		if(description != undefined || description != ""){
			$detailArea.append($("<div/>").addClass("detailContentWrapper").append($("<span/>").css("word-break","break-word").append(description)));
			
			var seq = $(this).attr("data-model-id");
			var div = $(this).attr("data-model-2depth");
			
			if(categoryAreaValue == "<%=COMMUNITY%>"){
				seq = $(this).attr("data-model-community-url");
				div = $(this).attr("data-model-friendly-url");
			}
			var gotoBtn= $("<div/>").addClass("detailAreaBt")
							.append(
									$("<input/>").attr("type","button").addClass("btn_s")
												.attr("value","<liferay-ui:message key='edison-asset-button-shortcut'/>")
												.attr("onclick", "<portlet:namespace/>shortcuts('"+seq+"', '"+div+"')")
								);
			
			$detailArea.append(gotoBtn);
		}
	});
});


function <portlet:namespace/>setAssetLinkList(){
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var searchData = {
			"<portlet:namespace/>classModel": categoryAreaValue,
			"<portlet:namespace/>sourceEntryId":sourceEntryId,
	};
	
	var length = 45;
	if('${isMgrBtn}' == 'false' || '${isAppstore}' == 'true'){
		length = 30;
	}
						
	//link 정보 초기화
	jQuery.ajax({
		type: "POST",
		url: "<%=retrieveListLinkedAssetEntryURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.dataLinkList.length;
			var dataList = data.dataLinkList;
			
			$titleArea = $("#<portlet:namespace/>titleArea");
			$titleArea.empty();
			$("#<portlet:namespace/>detailArea").empty();
			
			if(dataSize > 0){
				for(var i = 0 ; i < dataSize; i++ ){
					
					if(categoryAreaValue == "<%=SCIENCE_APP%>"){
						var imgSrc = "${contextPath}/images/asset/icon_s.png";
						if(dataList[i].modelDiv == "Editor"){
							imgSrc = "${contextPath}/images/asset/icon_e.png";
						}else if(dataList[i].modelDiv == "Analyzer"){
							imgSrc = "${contextPath}/images/asset/icon_a.png";
						}
						
						var liTag = $("<li/>")
						 				.attr("data-model-id", dataList[i].modelSeq)
										.attr("data-model-2depth", dataList[i].groupId)
										.attr("data-model-3depth", dataList[i].title)
										.append(
												$("<img/>").attr("src", imgSrc)
												)
										.append("&nbsp;&nbsp;"+cutStr(dataList[i].name, length))
										.append("&nbsp;&nbsp;v"+dataList[i].version+"&nbsp;&nbsp;")
										.append(
												$("<img/>").attr("src","${contextPath}/images/asset/arrow01.png")
															.attr("width",6)
															.attr("height",6)
												);
						$titleArea.append(liTag);
						
					}else if(categoryAreaValue == "<%=COMMUNITY%>"){
						var communityUrl = data.communityUrl;
						
						var liTag = $("<li/>")
										.attr("data-model-id", dataList[i].modelSeq)
										.attr("data-model-community-url", communityUrl)
										.attr("data-model-friendly-url", dataList[i].friendlyURL)
										.append(
												$("<img/>").attr("src", "${contextPath}/images/asset/icon_c.png")
												)
										.append("&nbsp;&nbsp;"+dataList[i].name+"&nbsp;&nbsp;")
										.append(
												$("<img/>").attr("src","${contextPath}/images/asset/arrow01.png")
															.attr("width",6)
															.attr("height",6)
											);
						$titleArea.append(liTag);		
						
					}else{
						var title = cutStr(dataList[i].title, length);
						
						var imgSrc = "${contextPath}/images/asset/icon_c.png";
						if(categoryAreaValue == "<%=PROJECT%>"){
							imgSrc = "${contextPath}/images/asset/icon_p.png";
						}else if(categoryAreaValue == "<%=OPEN_DATA%>"){
							imgSrc = "${contextPath}/images/asset/icon_d.png";
							title = title+"&nbsp;&nbsp;v"+dataList[i].version;
						}
						var liTag = $("<li/>")
										.attr("data-model-id", dataList[i].modelSeq)
										.attr("data-model-2depth", dataList[i].modelDiv)
										.attr("data-model-3depth", dataList[i].description)
										.append(
												$("<img/>").attr("src", imgSrc)
												)
										.append("&nbsp;&nbsp;"+title+ "&nbsp;&nbsp;")
										.append(
												$("<img/>").attr("src","${contextPath}/images/asset/arrow01.png")
															.attr("width",6)
															.attr("height",6)
											);
						$titleArea.append(liTag);
					}
				}
			}
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	}); 
}

function <portlet:namespace/>linkManagementPopup() //Relate AssetEntry 팝업 띄우기
{
	
	if(!confirm(Liferay.Language.get("edison-asset-button-popup-alert"))){
		return;
	}
	
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var sourceClassName = $("#<portlet:namespace/>sourceClassName").val();
	var sourceClassPK = $("#<portlet:namespace/>sourceClassPK").val();

	var URL = '<%=assetLinkViewDialogueURL.toString()%>' 
				+ '&<portlet:namespace/>sourceEntryId='+ sourceEntryId
				+ '&<portlet:namespace/>sourceClassName='+ sourceClassName
				+ '&<portlet:namespace/>sourceClassPK='+ sourceClassPK
				
	Liferay.Util.openWindow({
		dialog: {
			cache: false,
          	destroyOnClose: true,
			after: {
				render: function(event) {
					$("button.btn.close").on("click", function(e){
						$("body").css('overflow','');
						
						/*alert("<liferay-ui:message key='edison-asset-update-related-information' />");*/
						location.reload(); 
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
		title: Liferay.Language.get("edison-asset-management-title"),
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
</script>

<aui:script>
function <portlet:namespace/>shortcuts(seq, div) {
	AUI().use("liferay-portlet-url", function(a) {

		var URL = "";
		if(categoryAreaValue == "<%=COMMUNITY%>"){ //사이트 URL 생성
			location.href = seq + div; 
		}else{
			if(categoryAreaValue == "<%=SCIENCE_APP%>"){
			    var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
			    var params = "&" + thisPortletNamespace + "solverId=" + seq;
			  	    params += "&" + thisPortletNamespace + "groupId=" + div;
			    window.open("<%=scienceAppDetailUrl%>" + params);
			}else if(categoryAreaValue == "<%=CONTENT%>"){
				var thisPortletNamespace = "_edisoncontent_WAR_edisoncontent2016portlet_";
				var params = "&" + thisPortletNamespace + "contentDiv=" + div;
					params += "&" + thisPortletNamespace + "contentSeq=" + seq;
				window.open("<%=contentDetailUrl%>" + params);
			}else if(categoryAreaValue == "<%=OPEN_DATA%>"){
			    var thisPortletNamespace = "_edisondatacollection_WAR_edisonsimulationportlet_";
			    var params = "&" + thisPortletNamespace + "collectionId=" + seq;
			    window.open("<%=dataCollectionDetailUrl%>" + params);
			}else if(categoryAreaValue == "<%=PROJECT%>"){
			    var thisPortletNamespace = "_edisonsimulationproject_WAR_edisonsimulationproject2017portlet_";
			    var params = "&" + thisPortletNamespace + "simulationProjectId=" + seq;
			    window.open("<%=projectDetailUrl%>" + params);
			}
		}
	}); 
}
</aui:script>

<form id="<portlet:namespace/>relatedAssetForm" method="post" >
	<aui:input type="hidden" value="${sourceEntryId }" name="sourceEntryId" />
	<aui:input type="hidden" value="${customModelAssetEntry.className }" name="sourceClassName" />
	<aui:input type="hidden" value="${customModelAssetEntry.classPK }" name="sourceClassPK" />
</form>


<c:choose>
	<c:when test="${isVirTitle == true }">
		<div class="edison-panel">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
					관련 자료
				</h3>
				<div class="btn-group pull-right">
					<button class="btn btn-info" type="button" onclick="<portlet:namespace/>linkManagementPopup();"><span class="icon-cog"> 관련정보관리</span></button>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<h3 class="styleh32" style="font-size: 22px; color: #000;">
			관련자료
			<c:if test="${isMgrBtn == true }">
				<span style="float: right;"> <input type="button"
					value="관련정보관리" class="btn_blue"
					onClick="<portlet:namespace/>linkManagementPopup()" />
				</span>
			</c:if>
		</h3>
	</c:otherwise>
</c:choose>


<div class="ulWrapper">
	<ul class="selectCategory" id="<portlet:namespace/>categoryArea">
		<li id="<%=SCIENCE_APP%>" class="active"><liferay-ui:message
				key="<%=SCIENCE_APP%>" />&nbsp;&nbsp;<img
			src="${contextPath}/images/asset/arrow01.png" width="6" height="10"></li>

		<li id="<%=CONTENT%>"><liferay-ui:message key="<%=CONTENT%>" />&nbsp;&nbsp;<img
			src="${contextPath}/images/asset/arrow01.png" width="6" height="10"></li>

		<li id="<%=OPEN_DATA%>"><liferay-ui:message
				key="<%=OPEN_DATA%>" />&nbsp;&nbsp;<img
			src="${contextPath}/images/asset/arrow01.png" width="6" height="10"></li>

		<li id="<%=PROJECT%>"><liferay-ui:message key="<%=PROJECT%>" />&nbsp;&nbsp;<img
			src="${contextPath}/images/asset/arrow01.png" width="6" height="10"></li>

		<li id="<%=COMMUNITY%>"><liferay-ui:message key="<%=COMMUNITY%>" />&nbsp;&nbsp;<img
			src="${contextPath}/images/asset/arrow01.png" width="6" height="10"></li>
	</ul>
	<ul class="selectCategory" id="<portlet:namespace/>titleArea">
	</ul>
	<div class="selectCategory" id="<portlet:namespace/>detailArea">
	</div>
</div>
<div style="clear: both"></div>


