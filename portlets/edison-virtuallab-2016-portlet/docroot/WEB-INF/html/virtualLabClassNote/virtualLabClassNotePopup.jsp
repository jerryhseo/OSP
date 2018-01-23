<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<%
	//Tab Setting
	String tabNames = (String) request.getAttribute("tabNames");
	String tabsValues = (String) request.getAttribute("tabsValues");
	String portletNameSpace = "_" + portletDisplay.getId() + "_" + "tagScript";

	//Custom Model
	String MY_FILE = (String) request.getAttribute("MY_FILE");
	String CONTENT = (String) request.getAttribute("CONTENT");
%>

<liferay-portlet:resourceURL var="searchListClassNoteURL" id="searchListClassNote" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="getListClassNoteURL" id="getListClassNote" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="updateClassNoteURL" id="updateClassNote" copyCurrentRenderParameters="false" escapeXml="false" />

<liferay-portlet:actionURL var="fileCreateURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="fileCreate" />
</liferay-portlet:actionURL>

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
	    min-height:50px;
	}
	

</style>
<script type="text/javascript">

var textboxlist =null;
var selectEntryArr = [];
$(function(){
	
	AUI().use( 'aui-textboxlist-deprecated', function(A) {
		textboxlist = new A.TextboxList({
			contentBox: '#selectInfo',
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
			var index = selectEntryArr.indexOf(event.item.key);
			if(index != -1){
				//현재 check된 값도 풀리기
				$("#<portlet:namespace/>contentSeq_"+event.item.key).attr("checked", false);
				<portlet:namespace/>updateClassNote(false, event.item.key);
				
				//삭제
				selectEntryArr.splice(index, 1);
			}
		}); 
		$("#<portlet:namespace/>fileDiv").hide();
		<portlet:namespace/>initData();
	});
});


//link된 asset textboxlist에 붙이기
function <portlet:namespace/>initData(){
	var classId = "${classId}";
	//선택된 탭의 현재 link 목록가져오기
	var searchData = {
			"<portlet:namespace/>classId":classId,
			"<portlet:namespace/>isContent": true,
	};
	
	//link 정보 초기화
	jQuery.ajax({
		type: "POST",
		url: "<%=getListClassNoteURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.dataLinkList.length;
			var dataList = data.dataLinkList;
			if(dataSize > 0){
				for(var i = 0 ; i < dataSize; i++ ){
					//현재 탭의 link 모델 붙이기
					
					var contentSeq = dataList[i].contentSeq;
					var title = dataList[i].title;
					
					//value -> 클릭한 탭의 모델명
					textboxlist.entries.add({
						key: contentSeq,
						title : title,
					});
				}
			}
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}

	function <portlet:namespace/>checkValidation(form) {
		var file = $("#<portlet:namespace/>my_local_file").val();
		if (!checkValue(form.<portlet:namespace/>fileDescription, "<liferay-ui:message key='registration' />", 1, 2000)) {
			return false;
		}else if(file == ""){
			alert("'파일'항목을 입력주세요");
			return false;
		}else {
			form.submitted = true;
			return true;
		}
		return false;
	}


//검색어 입력시 목록 
function <portlet:namespace/>searchListEntry(p_currentPage){
	var searchValue = $("#<portlet:namespace/>textfield").val();

	if(searchValue == ""){
		alert("검색할 제목을 입력하세요");
		return false;
	} 
	var groupId = "${groupId}";
	var classId = "${classId}";
	
	var searchData = {
			"<portlet:namespace/>searchText":searchValue,
			"<portlet:namespace/>currentPage" : p_currentPage,
			"<portlet:namespace/>classId":classId,
			"<portlet:namespace/>groupId":groupId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=searchListClassNoteURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var dataSize = data.dataList.length;
			var dataList = data.dataList;

			$entryTableBody = $("#<portlet:namespace/>entryTableBody");
			$title = $("#<portlet:namespace/>enterEntryTitle");
			$title.hide();
			$("#<portlet:namespace/>entryTableBody tr:not(:first)").remove();
			$("#<portlet:namespace/>paging").empty();
			
			if(dataSize > 0){
				for(var i = 0 ; i < dataSize; i++ ){
					$tr = $("<tr></tr>").appendTo($entryTableBody);
					
					if(i%2 == 1){
							$tr.addClass("tablebgtr");
					}
					
					var contentSeq = dataList[i].contentSeq;
					var title = dataList[i].title;
					var resume = cutStr(dataList[i].resume, 90);
						
					//checkbox check여부 검사 및 체크
					$checkBox = $("<input/>").attr("type","checkbox").attr("name","<portlet:namespace/>entryId")
											 .attr("id","<portlet:namespace/>contentSeq_"+contentSeq).attr("value",contentSeq)
											 .attr("checked", <portlet:namespace/>selectEntryChecked(contentSeq));
					$checkBox.attr("onClick","<portlet:namespace/>entryLinkCheck("+contentSeq+", '"+title+"');");
					
					var titleLabel = $("<label/>").append($checkBox).append('&nbsp;').append(title);
					$("<td/>").addClass("TL").append(titleLabel).appendTo($tr);
					$("<td/>").addClass("TL").append(resume).appendTo($tr);
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


function <portlet:namespace/>selectEntryChecked(contentSeq){
	var index = selectEntryArr.indexOf(Number(contentSeq));	
	if(index != -1){
		return true;
	}
	return false;
}

function <portlet:namespace/>entryLinkCheck(contentSeq, title){
	var checked = $("#<portlet:namespace/>contentSeq_"+contentSeq).is(":checked");
	var preStatus = <portlet:namespace/>selectEntryChecked(contentSeq);
	
	if(checked && !preStatus){
		
		textboxlist.entries.add({
			key: contentSeq,
			title : title,
		});
		$("#selectInfo li.textboxlist-input-container").css("display", "none");
		
		<portlet:namespace/>updateClassNote(checked, contentSeq);
		
	}else{// if(!checked && preStatus)
		textboxlist.entries.removeKey(title);
	}
	
	
}

function <portlet:namespace/>updateClassNote(checked, contentSeq){
	//check 시 link 정보 update
	var groupId = "${groupId}";
	var classId = "${classId }";
	var searchData = {
			"<portlet:namespace/>checkYn":checked,
			"<portlet:namespace/>groupId":groupId,
			"<portlet:namespace/>classId":classId,
			"<portlet:namespace/>contentSeq":contentSeq
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=updateClassNoteURL%>",
		async : false,
		data  : searchData,
		dataType: 'json',
		success: function(data) {
			var status = data.status;
			if(status != "success"){
				alert(Liferay.Language.get("edison-asset-update-fail-alert"));
				$("#<portlet:namespace/>contentSeq_"+contentSeq).attr("checked", false);
			}
		},error:function(data,e){
			alert("tagScript ERROR-->"+e);
		}
	});
}

//liferay-ui 탭 이벤트 return Script
function <portlet:namespace/>tagScript(tabUrl,tabNames,value,scriptName){
	<portlet:namespace/>tabChange(value);
	selectedTabId = value;
	selectEntryArr = [];
	<portlet:namespace/>initData(value, "Y");
	//목록
	var searchValue = $("#<portlet:namespace/>textfield").val();
	if(searchValue != ""){
		<portlet:namespace/>searchListEntry();
	}
}

function <portlet:namespace/>tabChange(value){
	$("#<portlet:namespace/>contentDiv").hide();
	$("#<portlet:namespace/>fileDiv").hide();
	
	var placeholderText = "<liferay-ui:message key="edison-table-list-header-title"/>";

	if(value == "" || value == "<%=CONTENT%>"){
		$("#<portlet:namespace/>contentDiv").show();
		$("#<portlet:namespace/>fileDiv").hide();
	}else{
		$("#<portlet:namespace/>contentDiv").hide();
		$("#<portlet:namespace/>fileDiv").show();
	}
	
	$("#<portlet:namespace/>textfield").attr("placeholder", placeholderText);

}

</script>

<div class="contabmenu">
	<edison-ui:tabs names="<%=tabNames%>" tabsValues="<%=tabsValues%>" refresh="<%=false%>" param="tabValue" onClick="<%=portletNameSpace%>" minwidth="200" />
</div>

<br />

<div id="<portlet:namespace/>contentDiv" class="table-responsive panel edison-panel">
	<div class="virtitlebox">
		<img src="/edison-virtuallab-2016-portlet/images/title_virtual.png" width="20" height="20"> 
		<div class="virtitle" style="margin-bottom: 20px;"><liferay-ui:message key="edison-asset-select-related-information"/></div>
	</div>
	<div id="selectInfo">
	</div>
	
	<!--table view -->
	<div class="tabletopbox clear">
		<div class="search">
			<div class="input-group">
				<input name="<portlet:namespace/>textfield" type="text" class="form-control"
					id="<portlet:namespace/>textfield"
					placeholder="<liferay-ui:message key="edison-table-list-header-title"/>"
					size="40"
					onKeydown="if(event.keyCode ==13)<portlet:namespace/>searchListEntry(1);" />
				<!-- <input  value=""
					class="btnsearch"
					> -->
				<div class="input-group-btn">
					<button class="btn btn-default" type="button" name="fullsize" id="fullsize" onclick="<portlet:namespace/>searchListEntry(1);">
						<i class="icon-search"></i>
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="table1_list borderno">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="관련 Entry 테이블" class="table table-bordered table-hover edison-table">
			<thead id="<portlet:namespace/>otherTableHeader">
				<tr>
					<th width="40%"><liferay-ui:message key="edison-table-list-header-title"/></th>
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
		<div id="<portlet:namespace/>paging" style="width: 100%; text-align: center;"></div>
	</div>
</div>

<div id="<portlet:namespace/>fileDiv">
	<div class="virtitlebox">
		<img src="/edison-virtuallab-2016-portlet/images/title_virtual.png" width="20" height="20"> 
		<div class="virtitle" ><liferay-ui:message key="edison-course-class-note-file"/></div>
	</div>
	<div class="fileContainer">
		<form id="<portlet:namespace/>createFileForm" name="<portlet:namespace/>createFileForm"  enctype="multipart/form-data" method="post" action="<%= fileCreateURL %>" onsubmit="return <portlet:namespace/>checkValidation(this);" style="padding-top : 70px; margin:0px;">
			<input id="<portlet:namespace/>classId" name="<portlet:namespace/>classId" type="hidden" value="${classId }">
			<input id="<portlet:namespace/>groupId" name="<portlet:namespace/>groupId" type="hidden" value="${groupId }">
			<input id="<portlet:namespace/>classNoteSeq" name="<portlet:namespace/>classNoteSeq" type="hidden" value="0">
			<div class="h20"></div>
			<div class="table2_list">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;" class="table table-bordered table-hover edison-table">
					<colgroup>
						<col width="20%" />
						<col width="80%" />
					</colgroup>
					<tbody>
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='description' /></th>
							<td class="puptxt">
								<liferay-ui:input-localized id="fileDescription" name="fileDescription" xml=""  rows="5" spellcheck="false" style="width: 85%; resize:none; margin:5px;" type="textarea"/>
							</td>
						</tr>
						
						<tr class="puptrline">
							<th class="puptitle"><liferay-ui:message key='edison-table-list-header-file' /></th>
							<td class="puptxt" >
								<input type="file" id="<portlet:namespace/>my_local_file" name="<portlet:namespace/>my_local_file">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="h20"></div>
			<div style="text-align: right; margin:20px 25px 30px 0px;">
				<input id="<portlet:namespace/>file_creation_button" name="<portlet:namespace/>file_creation_button" type="submit" value="<liferay-ui:message key='registration' />" class="btn btn-default" />
			</div>
		</form>
	</div>
</div>


