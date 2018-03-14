<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="getScienceAppListURL" id="getScienceAppList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="virtualLabScienceAppListURL" id="virtualLabScienceAppList" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL var="classScienceAppInsertURL" id="classScienceAppInsert" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="classId" value="${classId}" />
	<liferay-portlet:param name="groupId" value="${groupId}" />
</liferay-portlet:resourceURL>

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

<script type="text/javascript">
var textboxlist =null;
var selectEntryArr = [];

AUI().ready(function() {
	
	
	
	$('#scienceAppTable tr').click(function(event) {
		if (event.target.type !== 'checkbox') {
			$(':checkbox', this).trigger('click');
		}
	});
	
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
					
					//삭제
					selectEntryArr.splice(index, 1);
				}
			}
		}); 
		
	});
	setTimeout(function(){ <portlet:namespace/>initdataSearch(); }, 1000*1);
	
	<portlet:namespace/>dataSearchList();
});


function <portlet:namespace/>initdataSearch() {
	var dataForm = {
		"<portlet:namespace/>classId" : "${classId}",
		"<portlet:namespace/>groupId" : "${groupId}",
		"<portlet:namespace/>virtualLabId" : "${virtualLabId}"
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabScienceAppListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var virtualLabScienceAppList = msg.virtualLabScienceAppList;
			
			if(virtualLabScienceAppList.length != 0) {
				for(var i=0;i<virtualLabScienceAppList.length;i++){
					textboxlist.entries.add({
						key: virtualLabScienceAppList[i].scienceAppId,
						title : virtualLabScienceAppList[i].scienceAppName,
					});
					
				}
			}
			
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}



function <portlet:namespace/>dataSearchList(reset) {
	if(reset == 0) {
		$("#<portlet:namespace/>searchField").val("");
	}
	var dataForm = {
		"<portlet:namespace/>classId" : "${classId}",
		"<portlet:namespace/>groupId" : "${groupId}",
		"<portlet:namespace/>virtualLabId" : "${virtualLabId}",
		"<portlet:namespace/>searchField" : $("#<portlet:namespace/>searchField").val()
	};
	var classId = "${classId}";
	jQuery.ajax({
		type: "POST",
		url: "<%=getScienceAppListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var virtualLabScienceAppList = msg.virtualLabScienceAppList;
			
			var rowResult;
			$("#<portlet:namespace/>scienceAppListBody tr:not(:has(#1))").remove();
			
			if(virtualLabScienceAppList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "4")
							.css("text-align","center")
							.addClass("appadmintxt")
							.css("height", "40px")
							.text("<liferay-ui:message key='edison-there-are-no-data' />")
							.appendTo($rowResult);
				$("#<portlet:namespace/>scienceAppListBody").append($rowResult);
			} else {
				for(var i=0;i<virtualLabScienceAppList.length;i++){
					if(classId == 0){
						
						$trNode = $("<tr/>").css("cursor","pointer");
						$checkBox = $("<input>").attr("type","checkbox")
												.attr("id", "<portlet:namespace/>modelSeq_"+virtualLabScienceAppList[i].scienceAppId)
												.attr("name","<portlet:namespace/>virtuallabScienceAppCheck")
												.attr("onclick", "<portlet:namespace/>entryLinkCheck("+virtualLabScienceAppList[i].scienceAppId+",'"+virtualLabScienceAppList[i].scienceAppName+"','"+virtualLabScienceAppList[i].scienceAppVersion+"');")
												.attr("value",virtualLabScienceAppList[i].scienceAppId);
						
						if(i%2 == 1){
	 						$trNode.addClass("tablebgtr");
	 					}
						
						if(virtualLabScienceAppList[i].virtuallabScienceAppCheck > 0){
							$checkBox.attr("checked", true);
						} else {
							$checkBox.attr("checked", false);
						}					
					}else{
						$trNode = $("<tr/>").css("cursor","pointer");
						$checkBox = $("<input>").attr("type","checkbox")
												.attr("id", "<portlet:namespace/>modelSeq_"+virtualLabScienceAppList[i].scienceAppId)
												.attr("name","<portlet:namespace/>scienceAppCheck")
												.attr("onclick", "<portlet:namespace/>entryLinkCheck("+virtualLabScienceAppList[i].scienceAppId+",'"+virtualLabScienceAppList[i].scienceAppName+"','"+virtualLabScienceAppList[i].scienceAppVersion+"');")
												.attr("value",virtualLabScienceAppList[i].scienceAppId);
												
						if(i%2 == 1){
	 						$trNode.addClass("tablebgtr");
	 					}
						
						if(virtualLabScienceAppList[i].scienceAppCheck > 0){
							$checkBox.attr("checked", true);
						} else {
							$checkBox.attr("checked", false);
						}
					}
					
					$("<td/>").append($checkBox).appendTo($trNode);
					$("<td/>").css("text-align","left")
								.css("word-wrap","break-word")
								.text(virtualLabScienceAppList[i].scienceAppName)
								.appendTo($trNode);
					
					$("<td/>").addClass("center")
								.text("Ver "+virtualLabScienceAppList[i].scienceAppVersion)
								.appendTo($trNode);
					
					$("<td/>").addClass("center")
								.text(virtualLabScienceAppList[i].userFirstName)
								.appendTo($trNode);
					$("#<portlet:namespace/>scienceAppListBody").append($trNode);
				}
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>insertClassScienceApp(){
	 var paramData = {
			"<portlet:namespace/>virtualLabId":$("#<portlet:namespace/>virtualLabId").val(),
			"<portlet:namespace/>selectEntryArr":selectEntryArr
	};
	jQuery.ajax({
		type: "POST",
		url: "<%=classScienceAppInsertURL%>",
		async : false,
		data : paramData,
		success: function(data) {
			if(data=="SUCCESS"){
				alert("<liferay-ui:message key='edison-virtuallab-save-data' />");
			}
			parent.<portlet:namespace/>dataSearchList();
		},
		error:function(e){
			alert("insertClassScienceApp="+e)
		}
	});
}

function <portlet:namespace/>onKeyDown(e){
	if(e.keyCode == 13){
		<portlet:namespace/>dataSearchList();
		return false;
	}
}

function <portlet:namespace/>entryLinkCheck(modelSeq, title, version){
	
	var checked = $("#<portlet:namespace/>modelSeq_"+modelSeq).is(":checked");
	var preStatus = <portlet:namespace/>selectEntryChecked(modelSeq);
	
	if(version != "" && typeof version != "undefined"){
	 	title = title+" v" + version;	
	}
	
	if(checked && !preStatus){
		
		textboxlist.entries.add({
			key: modelSeq,
			title : title
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

</script>

<form id="scienceAppForm" name="scienceAppForm" method="post" onsubmit="return false;" style="margin:0px;">
	<input type="hidden" id="<portlet:namespace/>virtualLabId" name="<portlet:namespace/>virtualLabId" value=${virtualLabId}>
	
	<div id="selectInfo">
	</div>
	
	<div class="tabletopbox clear">
		<div class="input-group">
			<input id="<portlet:namespace/>searchField" class="form-control" name="<portlet:namespace/>searchField" type="text" maxlength="15" placeholder="<liferay-ui:message key='edison-virtuallab-app-name' />" onkeypress="<portlet:namespace/>onKeyDown(event);" style="width: 50%; float: right;" />
			<div class="input-group-btn">
				<button id="search_button" name="search_button" type="button" class="btn btn-default" onClick="<portlet:namespace/>dataSearchList()"><i class="icon-search"></i></button>
				<button id="total_search_button" name="total_search_button" type="button" class="btn btn-default" onClick="<portlet:namespace/>dataSearchList(0)">
					Clear
				</button>
				<button id="total_search_button" name="total_search_button" type="button" class="btn btn-default" onClick="<portlet:namespace/>insertClassScienceApp()" ><liferay-ui:message key='edison-virtuallab-save' /></button>
				<button type="button" class="btn btn-default" onclick="Liferay.Util.getOpener().<portlet:namespace />closePopup('scienceAppDialog');"><liferay-ui:message key='close' /></button>
			</div>
		</div>
	</div>
</form>

<!--table view -->
<div class="table1_list table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
	</div>
	<table id="scienceAppTable" class="table table-bordered table-hover edison-table" width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
		<colgroup>
			<col width="5%" />
			<col width="60%" />
			<col width="10%" />
			<col width="25%" />
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-app-name' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-version' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-developer-name' /></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>scienceAppListBody">
		</tbody>
	</table>
</div>
