<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>

<liferay-portlet:resourceURL var="scienceAppListURL" id="scienceAppList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="selectScienceAppListURL" escapeXml="false" id="selectScienceAppList" copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>

<%
	//Custom Model	
	String SCIENCE_APP = (String) request.getAttribute("SCIENCE_APP");

	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";
%>

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

<h4 style="padding: 0px 0px 12px 0px;"><liferay-ui:message key="edison-asset-select-related-information"/></h4>
<div id="selectInfo">
</div>

<!--table view -->
<div class="tabletopbox clear">
	<div class="search">
		<div class="searchbox">
			<input name="<portlet:namespace/>textfield" type="text"
				id="<portlet:namespace/>textfield"
				placeholder="<liferay-ui:message key='edison-virtuallab-app-name' />"
				size="40"
				onKeydown="if(event.keyCode ==13)<portlet:namespace/>searchListEntry();" />
			<input type="button" name="fullsize" id="fullsize" value=""
				class="btnsearch"
				onclick="<portlet:namespace/>searchListEntry();">
		</div>
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
</div>

<script type="text/javascript">
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
			selectEntryArr.push(String(event.item.key));
			<portlet:namespace/>insertParentPageApp();
		});  

		//selectInfo에서 x를 눌렀을때
		textboxlist.entries.after('remove',function(event, a, b){
			
			if(event.item.tab != "Y" && typeof event.item.tab == 'undefined'){
				var index = selectEntryArr.indexOf(event.item.key);
				if(index != -1){
					//현재 check된 값도 풀리기
					$("#<portlet:namespace/>modelSeq_"+event.item.key).attr("checked", false);					
					//삭제
					selectEntryArr.splice(index, 1);
				}
			}
			<portlet:namespace/>insertParentPageApp();
		}); 
		
		<portlet:namespace/>initData("<%=SCIENCE_APP%>","N");
		<portlet:namespace/>searchListEntry(1);
	});
	
});

//link된 asset textboxlist에 붙이기
function <portlet:namespace/>initData(value, tab){
	
	var scienceAppIdListJson = '${scienceAppIdListJson}';
	var scienceAppIdList;

	if("" == scienceAppIdListJson){
		scienceAppIdList = new Array();
	}else{
		scienceAppIdList = JSON.parse(scienceAppIdListJson);
	}
	
	var searchData = {			
			"<portlet:namespace/>scienceAppIdList" : scienceAppIdList
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
		url: "<%=selectScienceAppListURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.scienceAppList.length;
			var dataList = data.scienceAppList;
			if(dataSize > 0){
				for(var i = 0 ; i < dataSize; i++ ){
					//현재 탭의 link 모델 붙이기
					var modelSeq = dataList[i].scienceAppId;
					var title  = dataList[i].scienceAppName + " v" + dataList[i].scienceAppVersion;					
					
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
	var checked = $("#<portlet:namespace/>modelSeq_"+modelSeq).is(":checked");
	var preStatus = <portlet:namespace/>selectEntryChecked(modelSeq);
	
	title = title+" v" + version;
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
	
	$header = $("#<portlet:namespace/>scienceAppTableHeader");
	placeholderText = "<liferay-ui:message key="edison-table-list-header-app-title"/> or <liferay-ui:message key="edison-appstore-solver-name"/>";
	
	$header.show();
	$title.show();
	$("#<portlet:namespace/>entryTableBody tr:not(:first)").remove();
	$("#<portlet:namespace/>textfield").attr("placeholder", placeholderText);
}

//검색어 입력시 목록
function <portlet:namespace/>searchList(p_currentPage){
	var searchValue = $("#<portlet:namespace/>textfield").val();
	
	if(searchValue == ""){
		alert(Liferay.Language.get('edison-board-enter-title-alert'));
		$("#<portlet:namespace/>textfield").focus();
		return false;
	}
	
	<portlet:namespace/>searchListEntry(p_currentPage);
}

//목록 출력
function <portlet:namespace/>searchListEntry(p_currentPage){
	var searchValue = $("#<portlet:namespace/>textfield").val();

	var searchData = {
			"<portlet:namespace/>searchText":searchValue,
			"<portlet:namespace/>currentPage" : p_currentPage
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=scienceAppListURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.scienceAppList.length;
			var dataList = data.scienceAppList;

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
					var twoDepthValue ="";
					var version = "";
					
					modelSeq = dataList[i].scienceAppId;
					oneDepthValue = dataList[i].scienceAppName;
					twoDepthValue = dataList[i].scienceAppTitle;
					version = dataList[i].scienceAppVersion;
					
					//checkbox check여부 검사 및 체크
					$checkBox = $("<input/>").attr("type","checkbox").attr("name","<portlet:namespace/>entryId")
											 .attr("id","<portlet:namespace/>modelSeq_"+modelSeq).attr("value",modelSeq)
											 .attr("checked", <portlet:namespace/>selectEntryChecked(modelSeq));
											 
					
					$checkBox.attr("onClick","<portlet:namespace/>entryLinkCheck("+modelSeq+",'"+oneDepthValue+"','"+version+"');");
					$title = $("<label/>").append($checkBox).append('&nbsp;').append(oneDepthValue);
					$("<td></td>").addClass("TL").append($title).appendTo($tr);
					$("<td></td>").addClass("TC").append(version).appendTo($tr);
					$("<td></td>").append(
											$("<div/>").addClass("TL")
													   .addClass("ellipsis")
													   .append(twoDepthValue)
										).appendTo($tr);
						
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

function <portlet:namespace/>insertParentPageApp(){
	Liferay.Util.getOpener().<portlet:namespace/>setselectEntryArr(selectEntryArr);
}

</script>