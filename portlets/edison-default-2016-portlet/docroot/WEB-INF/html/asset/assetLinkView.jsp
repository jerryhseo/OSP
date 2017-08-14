<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<%
	//Tab Setting
	String tabNames = (String) request.getAttribute("tabNames");
	String tabsValues = (String) request.getAttribute("tabsValues");
	String portletNameSpace = "_" + portletDisplay.getId() + "_" + "tagScript";

	//Custom Model
	String SCIENCE_APP = (String) request.getAttribute("SCIENCE_APP");
	String CONTENT = (String) request.getAttribute("CONTENT");
	String OPEN_DATA = (String) request.getAttribute("OPEN_DATA");
	String PROJECT = (String) request.getAttribute("PROJECT");
%>

<liferay-portlet:resourceURL var="searchListCustomModelURL" id="searchListCustomModel" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="retrieveListLinkedAssetEntryURL" id="retrieveListLinkedAssetEntry" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="updateRelateAssetStatusURL" id="updateRelateAssetStatus" copyCurrentRenderParameters="false" escapeXml="false" />


<style>
	.textboxlistentry-remove, .textboxlistentry-text{
		cursor:pointer;
	}
	.aui input[type="radio"], .aui input[type="checkbox"]{
		margin:0px;
	}
	#selectInfo li.textboxlist-input-container{ 
  		display:none;  
	} 
	
	#selectInfo {
		margin-bottom:10px;
/* 	    border: none; */
/* 	    border-bottom: 1px solid #999; */
	    min-height:50px;
	}
	

</style>
<script>
//선택한 Tab Id
var selectedTabId = "<%=SCIENCE_APP%>";
var textboxlist =null;
var selectEntryArr = [];
$(function(){
	<portlet:namespace/>tableHeaderChange('');
	
	AUI().use( 'aui-textboxlist-deprecated', function(A) {
		textboxlist = new A.TextboxList({
			contentBox: '#selectInfo',
// 	        dataSource: states,
	        matchKey: 'title',
	        schema: {resultFields: ['key', 'title']},
	        typeAhead: true,
		}).render();

		textboxlist.entries.after('add',function(event){
			selectEntryArr.push(event.item.key);
		});  

		//remove가 됐을떄
		//x를 눌렀을때 이벤트
		textboxlist.entries.after('remove',function(event, a, b){
			if(event.item.tab != "Y" && typeof event.item.tab == 'undefined'){
				var index = selectEntryArr.indexOf(event.item.key);
				if(index != -1){
					//현재 check된 값도 풀리기
					$("#<portlet:namespace/>modelSeq_"+event.item.key).attr("checked", false);
					//<portlet:namespace/>entryLinkCheck(event.item.key, event.item.title);
					<portlet:namespace/>updateRelateAssetStatus(false, event.item.key);
					
					//삭제
					selectEntryArr.splice(index, 1);
				}
			}
		}); 

		<portlet:namespace/>initData("<%=SCIENCE_APP%>","N");
	});
});

//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
	<portlet:namespace/>tableHeaderChange(value);
	selectedTabId = value;

	selectEntryArr = [];
	<portlet:namespace/>initData(value, "Y");

	//목록
	var searchValue = $("#<portlet:namespace/>textfield").val();
	if(searchValue != ""){
		<portlet:namespace/>searchListEntry();
	}
}

//link된 asset textboxlist에 붙이기
function <portlet:namespace/>initData(value, tab){

	//선택된 탭의 현재 link 목록가져오기
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var searchData = {
			"<portlet:namespace/>classModel":value,
			"<portlet:namespace/>sourceEntryId":sourceEntryId,
	};

	textboxlist.entries.each(function(object, index, array){
		if(object.className != value){
			object["tab"] = tab;
			this.remove(object);
			
		}
	});
	
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
			
			if(dataSize > 0){
				for(var i = 0 ; i < dataSize; i++ ){
					//현재 탭의 link 모델 붙이기
					
					var modelSeq = dataList[i].modelSeq;
					var title = dataList[i].title;
					
					if(value == "<%=SCIENCE_APP%>" ){
						title  = dataList[i].name + " v" + dataList[i].version;
					}else{
						if(dataList[i].version != "" && typeof dataList[i].version !== "undefined"){
							title  = title + " v" + dataList[i].version;
						}	
					}
						
					//value -> 클릭한 탭의 모델명
					textboxlist.entries.add({
						key: modelSeq,
						className: value,
						title : title,
					});
				}
			}
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}
function <portlet:namespace/>entryLinkCheck(modelSeq, title, version){
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var sourceClassName = $("#<portlet:namespace/>sourceClassName").val();
	var sourceClassPK = $("#<portlet:namespace/>sourceClassPK").val();
	
	var checked = $("#<portlet:namespace/>modelSeq_"+modelSeq).is(":checked");
	var preStatus = <portlet:namespace/>selectEntryChecked(modelSeq);
	
	if(sourceClassPK == modelSeq && selectedTabId == sourceClassName){
		alert(Liferay.Language.get("edison-asset-update-related-checked-fail-alert"))
		$("#<portlet:namespace/>modelSeq_"+modelSeq).attr("checked", false);
	}else{
		if(version != "" && typeof version != "undefined"){
		 	title = title+" v" + version;	
		}
		if(checked && !preStatus){
			
			textboxlist.entries.add({
				key: modelSeq,
				className : selectedTabId,
				title : title,
			});
			$("#selectInfo li.textboxlist-input-container").css("display", "none");
			
		}else{// if(!checked && preStatus)
			textboxlist.entries.removeKey(title);
		}
		<portlet:namespace/>updateRelateAssetStatus(checked, modelSeq);
	}
}

function <portlet:namespace/>updateRelateAssetStatus(checked, modelSeq){
	//check 시 link 정보 update
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var searchData = {
			"<portlet:namespace/>checkYn":checked,
			"<portlet:namespace/>classModel":selectedTabId,
			"<portlet:namespace/>sourceEntryId":sourceEntryId,
			/* "<portlet:namespace/>targetEntryId":entryId, */
			"<portlet:namespace/>targetModelSeq":modelSeq,
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=updateRelateAssetStatusURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var status = data.status;
			
			if(status == "fail"){
				alert(Liferay.Language.get("edison-asset-update-fail-alert"));
				$("#<portlet:namespace/>modelSeq_"+modelSeq).attr("checked", false);
			}
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}
function <portlet:namespace/>selectEntryChecked(modelSeq){
	var index = selectEntryArr.indexOf(modelSeq);	
	if(index != -1){
		return true;
	}
	return false;
}

//모델에 따른 테이블 해더 변경
function <portlet:namespace/>tableHeaderChange(value){
	$("thead").hide();
	
	$title = $("#<portlet:namespace/>enterEntryTitle");
	
	var placeholderText = "<liferay-ui:message key="edison-table-list-header-title"/>";

	if(value == "" || value == "<%=SCIENCE_APP%>"){
		$header = $("#<portlet:namespace/>scienceAppTableHeader");
		placeholderText = "<liferay-ui:message key="edison-table-list-header-app-title"/> or <liferay-ui:message key="edison-appstore-solver-name"/>";
	}else if(value == "<%=OPEN_DATA%>"){
		$header = $("#<portlet:namespace/>existVersionTableHeader");
	}else{
		$header = $("#<portlet:namespace/>otherTableHeader");
	}
	
	$header.show();
	/* $colspan = $header.find("th").length;
	$title.attr("colspan", $colspan); */
	
	$title.show();
	//$("#<portlet:namespace/>entryTableBody tr:not(:has(#1))").show();
	//$("#<portlet:namespace/>entryTableTitle").show();
	$("#<portlet:namespace/>entryTableBody tr:not(:first)").remove();
	
		$("#<portlet:namespace/>textfield").attr("placeholder", placeholderText);
	//$("#<portlet:namespace/>textfield").val("");

}

//검색어 입력시 목록 
function <portlet:namespace/>searchListEntry(p_currentPage){
	var searchValue = $("#<portlet:namespace/>textfield").val();

	if(searchValue == ""){
		alert("검색할 제목을 입력하세요");
		return false;
	} 

	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var searchData = {
			"<portlet:namespace/>classModel":selectedTabId,
			"<portlet:namespace/>searchText":searchValue,
			"<portlet:namespace/>currentPage" : p_currentPage,
			"<portlet:namespace/>sourceEntryId":sourceEntryId,
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchListCustomModelURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.dataList.length;
			var dataList = data.dataList;

			$entryTableBody = $("#<portlet:namespace/>entryTableBody");
			$title.hide();
			$("#<portlet:namespace/>entryTableBody tr:not(:first)").remove();
			$("#<portlet:namespace/>paging").empty();
			
			if(dataSize > 0){
				for(var i = 0 ; i < dataSize; i++ ){
					$tr = $("<tr></tr>").appendTo($entryTableBody);
					
					if(i%2 == 1){
							$tr.addClass("tablebgtr");
					}
					
					var modelSeq = 0; 
					var oneDepthValue = ""; 
					var version = ""; 
					var twoDepthValue ="";
					
					if(selectedTabId == "<%=SCIENCE_APP%>"){
						modelSeq = dataList[i].modelSeq;
						oneDepthValue = dataList[i].name;
						twoDepthValue = dataList[i].title;
						version = dataList[i].version;
					}else{
						modelSeq = dataList[i].modelSeq;
						oneDepthValue = dataList[i].title;
						twoDepthValue = cutStr(dataList[i].description, 90);
						version = "";
						
						if(dataList[i].version != "" && typeof dataList[i].version != "undefined"){
							version = dataList[i].version;
						}	
					}
					
					//checkbox check여부 검사 및 체크
					$checkBox = $("<input/>").attr("type","checkbox").attr("name","<portlet:namespace/>entryId")
											 .attr("id","<portlet:namespace/>modelSeq_"+modelSeq).attr("value",modelSeq)
											 .attr("checked", <portlet:namespace/>selectEntryChecked(modelSeq));
											 
					
					$checkBox.attr("onClick","<portlet:namespace/>entryLinkCheck("+modelSeq+",'"+oneDepthValue+"','"+version+"');");
					$title = $("<label/>").append($checkBox).append('&nbsp;').append(oneDepthValue);
					$("<td></td>").addClass("TL").append($title).appendTo($tr);
					if(version != ""){
						$("<td></td>").addClass("TC").append(version).appendTo($tr);
					}
					$("<td></td>").addClass("TL").append(twoDepthValue).appendTo($tr);
						
						
				}
		
				$("#<portlet:namespace/>paging").html(data.paging);
			}else{
				$tr = $("<tr></tr>").appendTo($entryTableBody);
				$("<td></td>").addClass("TC").attr("colspan",3).append("<liferay-ui:message key='edison-there-are-no-data'/>").appendTo($tr);
			}
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}
<%-- function <portlet:namespace/>saveRelateAssetStatus(){
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var searchData = {
			"<portlet:namespace/>selectEntryArr":selectEntryArr,
			"<portlet:namespace/>sourceEntryId":sourceEntryId,
	};
	
	//배열 형태로 서버쪽 전송을 위한 설정
	jQuery.ajaxSettings.traditional = true;

	jQuery.ajax({
		type: "POST",
		url: "<%=updateRelateAssetStatusURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
} --%>
</script>

<input type="hidden" name="<portlet:namespace/>sourceEntryId" id="<portlet:namespace/>sourceEntryId" value="${sourceEntryId }"/>
<input type="hidden" name="<portlet:namespace/>sourceClassName" id="<portlet:namespace/>sourceClassName" value="${sourceClassName}"/>
<input type="hidden" name="<portlet:namespace/>sourceClassPK" id="<portlet:namespace/>sourceClassPK" value="${sourceClassPK}"/>

<div class="contabmenu">
	<edison-ui:tabs names="<%=tabNames%>" tabsValues="<%=tabsValues%>" refresh="<%=false%>" param="tabValue" onClick="<%=portletNameSpace%>" minwidth="200" />
</div>

<br />
<h4><liferay-ui:message key="edison-asset-select-related-information"/></h4>
<div id="selectInfo">
</div>

<!--table view -->
<div class="tabletopbox clear">
	<div class="search">
		<div class="searchbox">
			<input name="<portlet:namespace/>textfield" type="text"
				id="<portlet:namespace/>textfield"
				placeholder="<liferay-ui:message key="edison-table-list-header-title"/>
				size="40"
				onKeydown="if(event.keyCode ==13)<portlet:namespace/>searchListEntry(1);" />
			<input type="button" name="fullsize" id="fullsize" value=""
				class="btnsearch"
				onclick="<portlet:namespace/>searchListEntry(1);">
		</div>

		<%-- <input type="button" name="fullsize" id="fullsize"
			value="<liferay-ui:message key="edison-button-all-search"/>"
			class="button01"
			onclick="<portlet:namespace/>searchListEntry();"> --%>
	</div>
</div>

<div class="table1_list borderno">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="관련 Entry 테이블">
		<thead id="<portlet:namespace/>scienceAppTableHeader">
			<tr>
				<th width="35%"><liferay-ui:message key="edison-appstore-solver-name" /></th>
				<th width="10%"><liferay-ui:message key="edison-table-list-header-version" /></th>
				<th width="55%"><liferay-ui:message key="edison-table-list-header-app-title" /></th>
			</tr>
		</thead>
		<thead id="<portlet:namespace/>existVersionTableHeader">
			<tr>
				<th width="35%"><liferay-ui:message key="edison-table-list-header-title"/></th>
				<th width="10%"><liferay-ui:message key="edison-table-list-header-version" /></th>
				<th width="55%"><liferay-ui:message key="edison-table-list-header-content"/></th>
			</tr>
		</thead>
		<thead id="<portlet:namespace/>otherTableHeader">
			<tr>
				<th width="40%"><liferay-ui:message key="edison-table-list-header-title"/></th>
<%-- 				<th width="10%"><liferay-ui:message key="edison-table-list-header-version" /></th> --%>
				<th width="60%"><liferay-ui:message key="edison-table-list-header-content"/></th>
			</tr>
		</thead>

		<tbody id="<portlet:namespace/>entryTableBody">
			<tr >
				<td colspan="3" class="TC" id="<portlet:namespace/>enterEntryTitle">
					<liferay-ui:message key='edison-asset-related-information' />
				</td>
			</tr>
		</tbody>
	</table>
</div>
<div class="paging">
	<div id="<portlet:namespace/>paging"
		style="width: 100%; text-align: center;"></div>
<%-- </div>
	<div class="boardbtnbox" style="text-align: right; width: 99%;">
	<input name="relateAssetBt" type="button" value="<liferay-ui:message key="edison-button-board-ok"/>" class="button01b" onclick=""/>
</div> --%>


