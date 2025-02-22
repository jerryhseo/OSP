<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<liferay-portlet:resourceURL var="surveyQuestionListURL" id="surveyQuestionList" />
<liferay-portlet:resourceURL var="surveyAnswerListURL" id="surveyAnswerList" />
<liferay-portlet:resourceURL var="surveyVoteInsertURL" id="surveyVoteInsert" />

<liferay-portlet:renderURL var="surveyVoteURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.NORMAL.toString() %>" />

<style type="text/css">
input[type="radio"].no-margin{
	margin: 0 5px 0 0;
}

#_edisonvirtuallabclasssurvey_WAR_edisonvirtuallab2016portlet_questionTable label{
	display: inline;
	margin-right: 10px;
}
</style>

<script>

$(document).ready(function () {		
	var surveySeqNo = "${surveyMap.surveySeqNo}";
	surveySetting(surveySeqNo);
	
});

function surveySetting(surveySeqNo){
	
	jQuery.ajax({
		type: "POST",
		url: "<%=surveyQuestionListURL%>",
		async : false,
		data  : {"<portlet:namespace/>surveySeqNo" : surveySeqNo},
		success: function(data) {	    			
			var dataMap = eval('(' + data + ')');
			$questionTable = $("#<portlet:namespace/>questionTable");
			$questionTable.empty();
			
			if(dataMap.dataList.length==0){
				
				$questionTable.append("<tr><td colspan=7>"+"<liferay-ui:message key='edison-there-are-no-data' />"+"</td></tr>");
				
			}else{
				
				for(var i = 0 ; i < dataMap.dataList.length; i++ ){					
					var num = i+1;
					$questionTable.append($("<input/>").attr("type","hidden").attr("name","<portlet:namespace/>questionSeqNo"+num).val(dataMap.dataList[i].questionSeqNo));
					$questionTable.append($("<input/>").attr("type","hidden").attr("name","<portlet:namespace/>questionDivCd"+num).val(dataMap.dataList[i].questionDivCd));
					$trNode = $("<tr/>");
					$("<th>" + "<liferay-ui:message key='edison-virtuallab-surveyResultList-survey' />"+num+"</th>").appendTo($trNode);					
					$("<th>" + "<liferay-ui:message key='edison-virtuallab-surveyResultList-question-title' />" + "</th>").appendTo($trNode);					
					$("<td/>").addClass("question").addClass(dataMap.dataList[i].questionDivCd).attr("colspan", "3").text(dataMap.dataList[i].questionTitle).appendTo($trNode);
					$questionTable.append($trNode);	
					if(dataMap.dataList[i].questionDivCd == "SVY_02_001"){
						$questionTr = $("<tr/>").appendTo($questionTable);
						$questionTr.append($("<th/>").text("<liferay-ui:message key='edison-virtuallab-surveyResultList-answer-number' />"))
						$questContentTd = $("<td/>").appendTo($questionTr);
						
// 						var rowCnt = 1;
						if(dataMap.dataList[i].question1 != null && dataMap.dataList[i].question1 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("1").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question1)
							).appendTo($questContentTd);
							
						}	
						if(dataMap.dataList[i].question2 != null && dataMap.dataList[i].question2 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("2").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question2)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question3 != null && dataMap.dataList[i].question3 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("3").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question3)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question4 != null && dataMap.dataList[i].question4 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("4").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question4)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question5 != null && dataMap.dataList[i].question5 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("5").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question5)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question6 != null && dataMap.dataList[i].question6 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("6").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question6)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question7 != null && dataMap.dataList[i].question7 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("7").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question7)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question8 != null && dataMap.dataList[i].question8 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("8").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question8)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question9 != null && dataMap.dataList[i].question9 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("9").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question9)
							).appendTo($questContentTd);
						}	
						if(dataMap.dataList[i].question10 != null && dataMap.dataList[i].question10 != ''){
							$("<label/>").append(
								$("<input/>").attr("type","radio").attr("name", "<portlet:namespace/>objecttivityAnswer"+num).val("10").addClass("no-margin")
							).append(
								$("<span/>").text(dataMap.dataList[i].question10)
							).appendTo($questContentTd);
						}	
						
						$trNode.find("th:first").attr("rowspan", 2);
						
					}else if(dataMap.dataList[i].questionDivCd == "SVY_02_002"){
						$("<tr/>").append($("<th/>").text("<liferay-ui:message key='edison-virtuallab-surveyResultList-answer' />"))
						  .append($("<td/>").attr("colspan","3").append($("<textarea/>").attr("name","<portlet:namespace/>subjectivityAnswer"+num).attr("cols","100").attr("rows", "4").attr("spellcheck","false").css("width","95%").css("resize", "none"))).appendTo($questionTable);
						$trNode.find("th:first").attr("rowspan", "2");
					}
											
				}			
			}
			
		},error:function(data,e){ 
			alert(e);				
			return false;
		}
	});
}

function onclickConfirm(){
	
	var validationCheck = true;
	//객관식 Validation 체크
	$(".SVY_02_001").each(function(i,val){
		//alert($(this).parent().parent().find("input[type=radio]").attr("name"));
		var radioName = $(this).parent().next().find("input[type=radio]").attr("name");
		//.prop("tagName")
		if($(":radio[name="+radioName+"]:checked").length < 1){
			alert($(this).text()+"<liferay-ui:message key='edison-virtuallab-please-survey-respond' />");
			validationCheck = false;
			return false;
		}
	});
	
	if(validationCheck){
		//주관식 Validation 체크
		$(".SVY_02_002").each(function(i,val){
			var textAreaName = $(this).parent().next().find("textarea").attr("name");
			if($("textarea[name="+textAreaName+"]").val()==""){
				validationCheck = false;
				alert($(this).text()+"<liferay-ui:message key='edison-virtuallab-please-survey-respond' />");
				$("textarea[name="+textAreaName+"]").focus();
				return false;
			}else{
				$("textarea[name="+textAreaName+"]").val($("textarea[name="+textAreaName+"]").val().replace(/,/gi, "."));
			}
		});
	}
	
	
	if(validationCheck){
		var paramData = $("form[name=surveyForm]").serialize();			
		jQuery.ajax({
			type: "POST",
			url: "<%=surveyVoteInsertURL%>",
			async : false,
			data  : paramData,
			success: function(data) {
				if(data == '200') {
					alert("<liferay-ui:message key='edison-virtuallab-complete-in-survey' />");
				} else {
					alert("<liferay-ui:message key='edison-data-insert-error' />");
				}
			},error:function(data,e){ 
				alert(e);				
				return false;
			},complete:function(){
				historyback();
			}
		});
	}
}

function historyback(){
	window.location.href = "<%= surveyVoteURL%>&classId=${classId}";	
}

</script>

<div class="table-responsive panel edison-panel" style="margin: 0 auto;">
	<form name="surveyForm" method="post">
		<input type="hidden" name="<portlet:namespace/>surveySeqNo" value="${surveyMap.surveySeqNo}">
		<input type="hidden" name="<portlet:namespace/>virtualLabId" value="${virtualLabId}">
		<input type="hidden" name="<portlet:namespace/>classId" value="${classId}">
		
		<div class="panel-heading clearfix">
			<h1>
				<img src="${contextPath}/images/title_virtual.png" />
				<liferay-ui:message key='edison-virtuallab-survey' />
			</h1>
		</div>
		
		<div class="table1_list" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
				<colgroup>
					<col width="15%" />
					<col width="75%" />
				</colgroup>
				<tr>
					<th><liferay-ui:message key='edison-virtuallab-surveyResultList-survey-title2' /></th>
					<td>
						${surveyMap.surveyTitle}
					</td>
				</tr>
			</table>
		</div>
		<div class="table1_list" style="border-top: 0px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data" >
				<colgroup>
					<col width="15%" />
					<col width="10%" />
					<col width="70%" />
					<col width="5%" />
				</colgroup>
				<tbody id="<portlet:namespace/>questionTable">
				</tbody>
			</table>
		</div>
	</form>
	<div style="text-align: right; margin-top: 10px;">
		<input type="button" class="btn btn-default" onclick="historyback()" value="<liferay-ui:message key='cancel' />" />
		<input type="button" class="btn btn-default" onclick="onclickConfirm()"value="<liferay-ui:message key='edison-button-register' />" />
	</div>
</div>
