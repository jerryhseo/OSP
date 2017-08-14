<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<%@ taglib uri="http://liferay.com/tld/security"
	prefix="liferay-security"%>
<%
	//Custom Model
	String SCIENCE_APP = (String) request.getAttribute("SCIENCE_APP");
	String CONTENT = (String) request.getAttribute("CONTENT");
	String OPEN_DATA = (String) request.getAttribute("OPEN_DATA");
	String PROJECT = (String) request.getAttribute("PROJECT");
%>
<liferay-portlet:resourceURL var="retrieveListLinkedAssetEntryURL"
	id="retrieveListLinkedAssetEntry" copyCurrentRenderParameters="false"
	escapeXml="false" />
<liferay-portlet:renderURL var="assetLinkViewDialogueURL"
	windowState="<%=LiferayWindowState.POP_UP.toString() %>"
	copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="assetLinkViewDialogue" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="projectDetailUrl" portletName="edisonsimulationproject_WAR_edisonsimulationproject2017portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myRender" value="detailView" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="edisonCopyParam" value="true" />
  <liferay-portlet:param name="myaction" value="detailView" />
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
.edison #wrap .content-sub {
	/*  	width:100%;  */
	
}

.relatedEntryBoxContentDiv{
/* 	width:80%; */
	margin: auto;
	text-align: center;
}
</style>
<script>

var selectedDataModelId = "";
var slideStatus = true;
$(document).ready(function(){
	$(".relatedEntryBoxPaging").hide();
	
    $(".centerm").on("click", "li", function(){
		selectedDataModelId = $(this).attr("id"); 
    	
    	if(selectedDataModelId != ""){
	    	var selectedStatusClassNm = $(this).attr("class"); //클릭한 li
	    	//전체 li 클래스 변경
	    	$(".centerm li").each(function( index ) {
				var dataClassNm = $(this).attr("data-class"); 
				var hasClassNm = $(this).attr("class"); //현재 클래스
				
				if(selectedStatusClassNm != "undefined"){
					if(hasClassNm != dataClassNm){ //m0*sel와 m0*으로 다른 경우
						$(this).removeClass(hasClassNm);
						$(this).addClass(dataClassNm); 
					}else{ //m0* 으로 같은 경우
						if(selectedStatusClassNm == dataClassNm){//클릭한 li가 m0*일때 m0*sel로 클래스 변경
							var newClassNm = selectedStatusClassNm+"sel";
							$(this).removeClass(hasClassNm);
							$(this).addClass(newClassNm);
						}
					}
				}
	    	});


	    	if(selectedStatusClassNm != "undefined" && selectedStatusClassNm != ""){
				$(".relatedEntryBoxPaging").hide(); //페이징 숨기기
		    	if(selectedStatusClassNm.indexOf("sel") != -1){ //슬라이드 올리고
		    		$("#relatedEntryBoxList").slideUp(function(){ $(this).clearQueue(); });
		    	}else{
					//값 띄우기 ajax
		    		<portlet:namespace/>getSelectedModelDataList(selectedDataModelId, 1);
		    	} 
	    	}
    	}
    });
});

//일반 콘텐츠 페이징조회
function <portlet:namespace/>getSelectedModelDataListPaging(p_currentPage){
	<portlet:namespace/>getSelectedModelDataList(selectedDataModelId, p_currentPage);
}

function  <portlet:namespace/>getSelectedModelDataList(classModel, p_currentPage){
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var searchData = {
			"<portlet:namespace/>classModel": classModel,
			"<portlet:namespace/>sourceEntryId":sourceEntryId,
			"<portlet:namespace/>currentPage" : p_currentPage,
			"<portlet:namespace/>pagingYn": true
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=retrieveListLinkedAssetEntryURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.dataLinkList.length;
			var dataList = data.dataLinkList;

			$relatedEntryBox = $("#relatedEntryBoxList");
			$relatedEntryBox.empty();
			
			$("#<portlet:namespace/>paging").empty();

			$relatedEntryBoxContentDiv = $("<div/>").addClass("relatedEntryBoxContentDiv").appendTo($relatedEntryBox);
			
			/* $infoUl = $("<div/>").addClass("box").css("width", 100/(dataSize+1)+"%");
			$infoUl.append($("<div/>").addClass("boxContent boxTitle").append($("<a/>").attr("href","#").append("제목")));
			$infoUl.append($("<div/>").addClass("boxContent boxDescription").append($("<a/>").attr("href","#").append("내용")));
			$infoUl.append($("<div/>").addClass("boxContent").append($("<a/>").attr("href","#").append("내용"))); 
			$infoUl.appendTo($relatedEntryBoxContentDiv); */
			
			if(dataSize > 0){
				for(var i=0;i<dataSize;i++){
					$ul = $("<ul/>");
					var fDepth = "";
					var sDepth = "";
					var linkBtn = "";
					if(classModel == "<%=SCIENCE_APP%>"){
// 						fDepth = $("<li/>").addClass("boxTitle").append($("<a/>").attr("href","#").append(dataList[i].name));
						var name = dataList[i].name; 
						if(typeof dataList[i].version != "undefined") name += " v" +dataList[i].version;
						fDepth = $("<li/>").addClass("boxTitle").append(name);
						
						var title = dataList[i].title;
						if(title.length > 135){
							description = cutStr(dataList[i].title, 135);
						}
						
// 						sDepth = $("<li/>").addClass("boxDescription").append($("<a/>").attr("href","#").append(title));
						sDepth = $("<li/>").addClass("boxDescription").append(title);
						linkBtn = $("<li/>").append(
								$("<a/>").attr("href","#")
								.attr("onclick", "<portlet:namespace/>shortcuts('"+dataList[i].modelSeq+"', '"+dataList[i].groupId+"')")
								.append(Liferay.Language.get("edison-asset-button-shortcut")).append("&nbsp;&gt;")
						);
					}else{
						var title = dataList[i].title;
						if(title.length > 65){
							title = cutStr(title, 65);
						}
						
						var description = dataList[i].description;
						
						if(description != ""){
							if(description.length > 135){
								description = cutStr(description, 135);
							}
						}else{
							description = dataList[i].title;
						}
// 						fDepth = $("<li/>").addClass("boxTitle").append($("<a/>").attr("href","javascript:void(0);").append(title));
// 						sDepth = $("<li/>").addClass("boxDescription").append($("<a/>").attr("href","javascript:void(0);").append(description));

						if(dataList[i].version != "" && typeof dataList[i].version != "undefined") title += " v" +dataList[i].version;

						fDepth = $("<li/>").addClass("boxTitle").append(title);
						sDepth = $("<li/>").addClass("boxDescription").append(description);
						
						linkBtn = $("<li/>").append(
								$("<a/>").attr("href","javascript:void(0);")
								.attr("onclick", "<portlet:namespace/>shortcuts('"+dataList[i].modelSeq+"', '"+dataList[i].modelDiv+"')")
								.append(Liferay.Language.get("edison-asset-button-shortcut")).append("&nbsp;&gt;")
								);
					}
					$ul.append(fDepth);
					$ul.append(sDepth);
					$ul.append(linkBtn);
					$ul.appendTo($relatedEntryBoxContentDiv);
				}
					
				
				if(data.paging != ""){
					$("#<portlet:namespace/>paging").html(data.paging);
					$(".relatedEntryBoxPaging").show();
				}
				//$relatedEntryBox.slideDown();
			}else{
				//$relatedEntryBox.css("display", "none");.
				$infoUl = $("<ul/>")
				$infoUl.append($("<li/>").addClass("boxTitle").append($("<a/>").attr("href","#").append("관련된 내용이 없습니다.")));
				$infoUl.append($("<li/>").addClass("boxDescription").append("&nbsp;"));
				$infoUl.append($("<li/>").append("&nbsp;")); 
				$infoUl.appendTo($relatedEntryBoxContentDiv); 
			}
			
			$relatedEntryBox.slideDown(function(){$(this).clearQueue();});
		},
		error: function(data){
			
		}
	});
}

function <portlet:namespace/>linkManagementPopup() //Relate AssetEntry 팝업 띄우기
{
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var sourceClassName = $("#<portlet:namespace/>sourceClassName").val();
	var sourceClassPK = $("#<portlet:namespace/>sourceClassPK").val();
	
	var URL = '<%=assetLinkViewDialogueURL%>' 
				+ '&<portlet:namespace/>sourceEntryId='+ sourceEntryId;
				
	Liferay.Util.openWindow({
		dialog: {
			cache: false,
          	destroyOnClose: true,
			after: {
				render: function(event) {
					$("button.btn.close").on("click", function(e){
						$("body").css('overflow','');
						
						/* alert("<liferay-ui:message key='edison-asset-update-related-information' />");*/
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
			height:730
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
		if(selectedDataModelId == "<%=SCIENCE_APP%>"){
		    var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
		    var params = "&" + thisPortletNamespace + "solverId=" + seq;
		  	    params += "&" + thisPortletNamespace + "groupId=" + div;
		  	    params += "&" + thisPortletNamespace + "isRedirect=false";
		  	    params += "&" + thisPortletNamespace + "viewStatus=shortcuts";
		    window.open("<%=scienceAppDetailUrl%>" + params);
		}else if(selectedDataModelId == "<%=CONTENT%>"){
			var thisPortletNamespace = "_edisoncontent_WAR_edisoncontent2016portlet_";
			var params = "&" + thisPortletNamespace + "contentDiv=" + div;
				params += "&" + thisPortletNamespace + "contentSeq=" + seq;
				params += "&" + thisPortletNamespace + "viewStatus=shortcuts";
			window.open("<%=contentDetailUrl%>" + params);
			
		}else if(selectedDataModelId == "<%=OPEN_DATA%>"){
		    var thisPortletNamespace = "_edisondatacollection_WAR_edisonsimulationportlet_";
		    var params = "&" + thisPortletNamespace + "collectionId=" + seq;
				params += "&" + thisPortletNamespace + "viewStatus=shortcuts";
		    window.open("<%=dataCollectionDetailUrl%>" + params);
		}else if(selectedDataModelId == "<%=PROJECT%>"){
			var thisPortletNamespace = "_edisonsimulationproject_WAR_edisonsimulationproject2017portlet_";
		    var params = "&" + thisPortletNamespace + "simulationProjectId=" + seq;
		    	params += "&" + thisPortletNamespace + "isRedirect=false";
				params += "&" + thisPortletNamespace + "viewStatus=shortcuts";
		    window.open("<%=projectDetailUrl%>" + params);
		}
	}); 
}
</aui:script>


	<aui:form>
		<aui:input type="hidden" value="${sourceEntryId }" id="sourceEntryId"
			name="sourceEntryId" />
		<aui:input type="hidden" value="${customModelAssetEntry.className }"
			name="sourceClassName" />
		<aui:input type="hidden" value="${customModelAssetEntry.classPK }"
			name="sourceClassPK" />
	</aui:form>

	<!--비쥬얼 임시 20170207-->
	<%-- <div class="visulwrap">
		<div class="visul">
			<img src="${contextPath}/images/asset/viauslimg.jpg" width="1220" height="678">
		</div>
	</div> --%>

	<!--중앙 메뉴-->
	<div class="centerm">
		<ul>
			<li id="<%=SCIENCE_APP%>" data-class="m01" class="m01"><liferay-ui:message
					key="<%=SCIENCE_APP%>" /></li>
			<li id="<%=CONTENT%>" data-class="m02" class="m02"><liferay-ui:message
					key="<%=CONTENT%>" /></li>
			<li id="<%=OPEN_DATA%>" data-class="m03" class="m03"><liferay-ui:message
					key="<%=OPEN_DATA%>" /></li>
			<li id="<%=PROJECT%>" data-class="m04" class="m04"><liferay-ui:message
					key="<%=PROJECT%>" /></li>
			<li>
			<c:if test="${isMgrBtn == true }">
				<a href="#"
				onClick="<portlet:namespace/>linkManagementPopup()"><img
					src="${contextPath}/images/asset/icon01.png" width="30" height="30"><br>관련정보관리</a>
			</c:if>
			</li>
		</ul>
	</div>

	<!--중앙 메뉴 하위메뉴-->
	<div id="relatedEntryBoxList" class="boxlistct"></div>
	<div class="paging relatedEntryBoxPaging" style="background: #e9f4ff;">
		<div id="<portlet:namespace/>paging"
			style="width: 100%; text-align: center;"></div>
	</div>

