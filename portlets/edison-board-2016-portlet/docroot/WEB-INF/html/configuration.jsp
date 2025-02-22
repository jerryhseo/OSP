<%@page import="com.liferay.portal.kernel.dao.jdbc.OutputBlob"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.template.TemplateHandler" %>
<%@ page import="com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %>
<%@ page import="com.liferay.portal.theme.PortletDisplay" %>
<%@ page import="org.kisti.edison.multiboard.model.Board" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>


 

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
	Map prefsMap = portletPreferences.getMap();
 	
	Set es = prefsMap.entrySet();
	Iterator entries = null;
	Iterator boardSettingEntries = null;
	if(es != null){
		entries = es.iterator();
		boardSettingEntries = es.iterator();
	}
	
	int i=1;
	
	String savedMainListYn = "";
	try{
		String[] mainListYn = (String[]) prefsMap.get("mainListYn");
		savedMainListYn =  mainListYn[0].toString();
	} catch(Exception e){
		savedMainListYn = "N";
	}
%>
<script src="/edison-board-2016-portlet/js/jquery.1.10.2.js"></script> 
<script src="/edison-board-2016-portlet/js/jquery-ui.1.10.4.js"></script>
<link rel="stylesheet" href="/edison-board-2016-portlet/css/jquery-ui.min.css" />

<style>
	.form-control{
		height: 10%;
	}
	
	.selectedViewStructure{
		color: #dddddd;
	}
</style>

<script type="text/javascript">

window.onload=function(){
	
	var viewStructureVal = $("#<portlet:namespace/>savedViewStructureValue").val();
	
	if(viewStructureVal == 'undefined' || viewStructureVal == null || viewStructureVal == ''){
		viewStructureVal = 'N';
	}
	$("input:radio[value=\""+viewStructureVal+"\"]").prop("checked", "true");
	$(".selectedViewStructure[value=\""+viewStructureVal+"\"]").css("color", "#000000");
}

var trIndex = "0";
var portletIdTr = "<%=PortalUtil.getPortletId(request)%>";
var boardTypeCount = String(Number("${boardDivCount}") + 1);

function <portlet:namespace/>addPreferences(){
	$newTr = $("<tr/>").attr("id", "_"+portletIdTr+"_tr_"+trIndex)
						.append($("<td></td>").text(trIndex))
						.append($("<td></td>").html("<input type=\"text\" id=\"<portlet:namespace/>keyTextBox\" name=\"<portlet:namespace/>keyTextBox\" value=\"\" size=\"20\">"))
						.append($("<td></td>").html("<input type=\"text\" id=\"<portlet:namespace/>valueTextBox\" name=\"<portlet:namespace/>valueTextBox\" value=\"\" size=\"20\">"))
						.append($("<td></td>").html("<input type=\"button\" value=\"Delete\" class=\"btn btn-default\" onclick=\"<portlet:namespace/>preferenceDelete('"+"_"+portletIdTr+"_tr_"+trIndex+"')\">"));
								
	$("#keySetBody").append($newTr);
	
	trIndex = String(Number(trIndex) + 1);
}

function <portlet:namespace/>addDiv(){
	$newTr = $("<tr/>").attr("id","<portlet:namespace/>div_" + boardTypeCount);
	
	$("<td/>").append(
		 $("<input/>").attr("name", "<portlet:namespace/>numberArray")
		 			  .attr("id","<portlet:namespace/>numberArray")
		 			  .attr("type","hidden")
		 			  .val(boardTypeCount)
	).append(
			 $("<input/>").attr("name", "<portlet:namespace/>divCd_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>divCd_" + boardTypeCount)
 			  .attr("type","text")
 			  .attr("size", "30")
	).appendTo($newTr);

	$("<td/>").append(
			 $("<input/>").attr("name", "<portlet:namespace/>titleNmFirst_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>titleNmFirst_" + boardTypeCount)
 			  .attr("type","text")
 			  .attr("size", "30")
	).appendTo($newTr);

	$("<td/>").append(
			 $("<input/>").attr("name", "<portlet:namespace/>titleNmSecond_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>titleNmSecond_" + boardTypeCount)
 			  .attr("type","text")
 			  .attr("size", "30")
	).appendTo($newTr);

	$("<td/>").append(
			 $("<input/>").attr("name", "<portlet:namespace/>contentNm_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>contentNm_" + boardTypeCount)
 			  .attr("type","text")
 			  .attr("size", "30")
	).appendTo($newTr);

	$("<td/>").append(
			 $("<input/>").attr("name", "<portlet:namespace/>divNm_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>divNm_" + boardTypeCount)
 			  .attr("type","text")
 			  .attr("size", "30")
	).appendTo($newTr);

	$("<td/>").addClass("TC").append(
			 $("<input/>").attr("name", "<portlet:namespace/>fileUpLoadUseYn_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>fileUpLoadUseYn_" + boardTypeCount)
 			  .attr("type","checkbox")
	).appendTo($newTr);

	$("<td/>").addClass("TC").append(
			 $("<input/>").attr("name", "<portlet:namespace/>popupYn_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>popupYn_" + boardTypeCount)
 			  .attr("type","checkbox")
	).appendTo($newTr);

	$("<td/>").addClass("TC").append(
			 $("<input/>").attr("name", "<portlet:namespace/>replyYn_" + boardTypeCount)
 			  .attr("id","<portlet:namespace/>replyYn_" + boardTypeCount)
 			  .attr("type","checkbox")
	).appendTo($newTr);


	$("#divSetBody").append($newTr);
	
	boardTypeCount = String(Number(boardTypeCount) + 1);
}

function <portlet:namespace/>preferenceDelete(trId){
	$("#"+trId).remove();
	<portlet:namespace/>fm.submit();
}

function <portlet:namespace/>selectMainListYn(selectedMainListYn){
	var divCd = $(".<portlet:namespace/>divCd option:selected").val();
	if(divCd == 100 && selectedMainListYn == 'Y'){
		$("tr.<portlet:namespace/>viewStructure").show();
	} else {
		$("tr.<portlet:namespace/>viewStructure").hide();
		<portlet:namespace/>changeView("N");
	}
}

function <portlet:namespace/>changeView(selectItem){
	$("input:radio[value=\""+selectItem+"\"]").prop("checked", "true");
	$(".selectedViewStructure").css("color", "#dddddd");
	$(".selectedViewStructure[value=\""+selectItem+"\"]").css("color", "#000000");
} 

</script>

	<div class="container-fluid container table-responsive panel edison-panel">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
       	<thead>
			<tr>
				<th>this Portlet plid (originalBoardPlid)</th>
				<th>this Portlet ID (originalBoardPortletName)</th>
			</tr>       	
	    </thead>
		<tr>
			<td><%=plid%></td>
			<td>
				Look and Feel > Advanced Styling : Portlet ID : #p_p_id_[<font style="font-weight: bold;" color="blue">linkPortletId</font>]_
			</td>
		</tr>
		<tr>
			<td colspan="2">Mini board Preference is mainListYn, originalBoardPlid, originalBoardPortletName</td>
		</tr>		
		</table>
	</div>
<br/><br/>


<aui:form action="<%= configurationURL %>" method="post" name="boardSetting">
<input name="<portlet:namespace/>myaction" type="hidden" value="option"/>

	<div class="table1_list borderno" style="display: none;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
		<colgroup>
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
		</colgroup>
       	<thead>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>    	
	    </thead>
		<tbody>
<%
	String contextPath = request.getContextPath();
	String key = "";
	String value = "";
	List<Map<String,Object>> boardDivList = (List<Map<String,Object>>)renderRequest.getAttribute("boardDivList");
	String divSortOption = "";
%>		
		</tbody>
	</table>
	</div>

<!-- <input type="submit" value="저장" /> -->

</aui:form>


<div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" >
		<colgroup>
			<col width="8%" />
			<col width="50%" />
		</colgroup>
		<tbody>
			<tr>
				<td><b>divSort</b></td>
				<td><liferay-ui:message key='edison-board-configuration-explain-divSort' /></td>
			</tr>
			<tr>
				<td><b>divCd</b></td>
				<td><liferay-ui:message key='edison-board-configuration-explain-divCd' /></td>
			</tr>
			<tr>
				<td><b>mainListYn</b></td>
				<td><liferay-ui:message key='edison-board-configuration-explain-mainListYn' /></td>
			</tr>
			<tr>
				<td><b>originalBoardPortletName</b></td>
				<td><liferay-ui:message key='edison-board-configuration-explain-originalBoardPortletName' /></td>
			</tr>
			<tr>
				<td><b>originalBoardPlid</b></td>
				<td><liferay-ui:message key='edison-board-configuration-explain-originalBoardPlid' /></td>
			</tr>
		</tbody>
	</table>
</div>
<br>
<aui:form action="<%= configurationURL %>" method="post" name="option">
<input name="<portlet:namespace/>myaction" type="hidden" value="option"/>

	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<input type="button" class="btn btn-default" value="Add preferences" onclick="<portlet:namespace/>addPreferences()">
		</div>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table" >
		<colgroup>
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
		</colgroup>
       	<thead>
			<tr>
				<th>Number</th>
				<th>Key</th>
				<th>Value</th>
				<th>Action</th>
			</tr>       	
	    </thead>
		<tbody id="keySetBody">
<%
	String key = "";
	String value = "";
	List<Map<String,Object>> boardDivList = (List<Map<String,Object>>)renderRequest.getAttribute("boardDivList");
	String divSortOption = "";
	String divCdOption = "";
	String divCd = "";
	
	if(entries.hasNext()){	//저장된 preferences 강 있는지 판별
		while (boardSettingEntries.hasNext()) {
			Map.Entry<String, String[]> thisEntry = (Map.Entry) boardSettingEntries.next();
			key = CustomUtil.strNull(thisEntry.getKey());
			value = CustomUtil.strNull(thisEntry.getValue()[0]);
			
			if(key.equals("divSort")){
				for(Map<String, Object> boardDiv : boardDivList){
					if(value.equals(boardDiv.get("divNm"))){
						divSortOption += "<option value=\""+boardDiv.get("divNm")+"\" selected>"+boardDiv.get("divNm")+"</option>";
					}else{
						divSortOption += "<option value=\""+boardDiv.get("divNm")+"\">"+boardDiv.get("divNm")+"</option>";
					}
				}
				out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
				out.print("	<td>"+i+"</td>\n");
				out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"divSort\" size=\"20\">divSort</td>\n");
				out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"btn btn-default\" >"+divSortOption+"</select></td>\n");
				out.print("<td></td>");
				out.print("</tr>\n");
				i=i+1;
			}else if(key.equals("divCd")){
				for(Map<String, Object> boardDiv : boardDivList){
					if(value.equals(String.valueOf(boardDiv.get("divCd")))){
						divCdOption += "<option value=\""+boardDiv.get("divCd")+"\" selected>"+boardDiv.get("divCd")+"</option>";
						divCd = String.valueOf(boardDiv.get("divCd"));
					}else{
						divCdOption += "<option value=\""+boardDiv.get("divCd")+"\">"+boardDiv.get("divCd")+"</option>";
					}
				}
				out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
				out.print("	<td>"+i+"</td>\n");
				out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"divCd\" size=\"20\">divCd</td>\n");
				out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"_"+PortalUtil.getPortletId(request)+"_divCd btn btn-default\">"+divCdOption+"</select></td>\n");
				out.print("<td></td>");
				out.print("</tr>\n");
				i=i+1;
			}else if(key.equals("mainListYn")){
				String mainListOption ="";
				if(value.equals("Y")){
					mainListOption = "<option value=\"Y\">Y</option><option value=\"N\">N</option>";
				}else{
					mainListOption = "<option value=\"Y\">Y</option><option value=\"N\" selected>N</option>";
				}
				out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
				out.print("	<td>"+i+"</td>\n");
				out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"mainListYn\" size=\"20\">mainListYn</td>\n");
				out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"btn btn-default\" onchange=\"_"+PortalUtil.getPortletId(request)+"_selectMainListYn(this.value)\">"+mainListOption+"</select></td>\n");
				out.print("<td></td>");
				out.print("</tr>\n");
				i=i+1;
				
				/* 공지사항 view type 선택 */
				if(divCd.equals("100")){
					String structureValue ="";
					String defaultRadioBtnChecked = "";
					
					String defaultRadioBtn = "<input type=\"radio\" class=\"_"+PortalUtil.getPortletId(request)+"_viewStructure\" id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" value=\"N\" style=\"display:none;\" "+defaultRadioBtnChecked+" />";
					
					structureValue = "<label style=\"text-align:center; cursor:pointer;\"><img src=\"/edison-board-2016-portlet/images/layout_list.png\" /><br>"
												+ "<input type=\"radio\" class=\"_"+PortalUtil.getPortletId(request)+"_viewStructure\" style=\"display:none;\" "
												+ "id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" "
												+ " value=\"list\" onchange=\"_"+PortalUtil.getPortletId(request)+"_changeView(this.value)\" />"
												+ "<div class=\"selectedViewStructure\" value=\"list\" >LIST</div></label>&emsp;"
									+"<label style=\"text-align:center; cursor:pointer;\"><img src=\"/edison-board-2016-portlet/images/layout_card.png\" /><br>"
												+ "<input type=\"radio\" class=\"_"+PortalUtil.getPortletId(request)+"_viewStructure\" style=\"display:none;\" "
												+ "id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" "
												+ " value=\"card\" onchange=\"_"+PortalUtil.getPortletId(request)+"_changeView(this.value)\" />"
												+ "<div class=\"selectedViewStructure\" value=\"card\" >CARD</div></label>"
									+ defaultRadioBtn;
					
					String displayOptionVal="";
					if(savedMainListYn.equals("N")){
						displayOptionVal = "none";
					}
					
					out.print("	<input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_viewStructureValue\" value=\"\" />");
					out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\" class=\"_"+PortalUtil.getPortletId(request)+"_viewStructure\" style=\"display:"+displayOptionVal+";\">\n");
					out.print("	<td>"+i+"</td>\n");
					out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"viewStructure\" size=\"20\">viewStructure</td>\n");
					out.print("<td colspan='2'>"+structureValue+"</td>");
					out.print("</tr>\n");
					i=i+1;
				}
			}else if(key.equals("originalBoardPortletName")){
				out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
				out.print("	<td>"+i+"</td>\n");
				out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"originalBoardPortletName\" size=\"20\">originalBoardPortletName</td>\n");
				out.print("	<td><input type=\"text\" id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"form-control\" value=\""+value+"\" size=\"20\" style=\"width:60%\"></td>\n");
				out.print("<td></td>");
				out.print("</tr>\n");
				i=i+1;
			}else if(key.equals("originalBoardPlid")){
				out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
				out.print("	<td>"+i+"</td>\n");
				out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"originalBoardPlid\" size=\"20\">originalBoardPlid</td>\n");
				out.print("	<td><input type=\"text\" id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"form-control\" value=\""+value+"\" size=\"20\" style=\"width:60%\"></td>\n");
				out.print("<td></td>");
				out.print("</tr>\n");
				i=i+1;
			}else if(key.equals("viewStructure")){
				out.print("	<input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_savedViewStructureValue\" value=\""+ value + "\" />");
			}
		}
		
		while (entries.hasNext()) {
			Map.Entry<String, String[]> thisEntry = (Map.Entry) entries.next();
			key = CustomUtil.strNull(thisEntry.getKey());
			value = CustomUtil.strNull(thisEntry.getValue()[0]);
			
			if(!key.equals("divSort") && !key.equals("divCd") && !key.equals("mainListYn") && !key.equals("originalBoardPortletName") && !key.equals("originalBoardPlid") && !key.equals("viewStructure")){
				out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
				out.print("	<td>"+i+"</td>\n");
				out.print("	<td><input type=\"text\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\""+key+"\" size=\"20\"></td>\n");
				out.print("	<td><input type=\"text\" id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" value=\""+value+"\" size=\"20\"></td>\n");
				out.print("	<td><input type=\"button\" value=\"Delete\" class=\"btn btn-default\" onclick=\"_"+PortalUtil.getPortletId(request)+"_preferenceDelete(\'_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\')\"></td>\n");
				out.print("</tr>\n");
				i=i+1;
			}
		}
	}else{
		
		for(Map<String, Object> boardDiv : boardDivList){
			
			divSortOption += "<option value=\""+boardDiv.get("divNm")+"\">"+boardDiv.get("divNm")+"</option>";
		}
		
		out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
		out.print("	<td>1</td>\n");
		out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"divSort\" size=\"20\">divSort</td>\n");
		out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"btn btn-default\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\">"+divSortOption+"</select></td>\n");
		out.print("<td></td>");
		out.print("</tr>\n");
		out.print("<tr>\n");
		i++;
		for(Map<String, Object> boardDiv : boardDivList){
			
			divCdOption += "<option value=\""+boardDiv.get("divCd")+"\">"+boardDiv.get("divCd")+"</option>";
		}
		
		out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
		out.print("	<td>2</td>\n");
		out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"divCd\" size=\"20\">divCd</td>\n");
		out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\"  class=\"btn btn-default\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\">"+divCdOption+"</select></td>\n");
		out.print("<td></td>");
		out.print("</tr>\n");
		out.print("<tr>\n");
		i++;
		
		out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
		out.print("	<td>3</td>\n");
		out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"mainListYn\" size=\"20\">mainListYn</td>\n");
		out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\"  class=\"btn btn-default\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\"><option value=\"Y\">Y</option><option value=\"N\" selected>N</option></select></td>\n");
		out.print("<td></td>");
		out.print("</tr>\n");
		i++;
		
		out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
		out.print("	<td>4</td>\n");
		out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"originalBoardPortletName\" size=\"20\">originalBoardPortletName</td>\n");
		out.print("	<td><input type=\"text\" id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"form-control\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" value=\"\" size=\"20\" style=\"width:150px;\"></td>\n");
		out.print("<td></td>");
		out.print("</tr>\n");
		i++;
		
		out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+i+"\">\n");
		out.print("	<td>5</td>\n");
		out.print("	<td><input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"originalBoardPlid\" size=\"20\">originalBoardPlid</td>\n");
		out.print("	<td><input type=\"text\" id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" class=\"form-control\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" value=\"\" size=\"20\" style=\"width:150px;\"></td>\n");
		out.print("<td></td>");
		out.print("</tr>\n");
		i++;
	}
%>		
		</tbody>
	</table>
	</div>

<script type="text/javascript">
trIndex = "<%=i%>";

</script>

<%-- 	<aui:button-row> --%>
<%-- 		<aui:button type="submit" /> --%>
<%-- 	</aui:button-row> --%>

<input type="submit" class="btn btn-primary" value="저장" />

</aui:form>


<aui:form action="<%= configurationURL %>" method="post" name="boardDiv">
	<input name="<portlet:namespace/>myaction" type="hidden" value="boardDiv" />
	
	<div class="table-responsive panel edison-panel">
		
		<div class="panel-heading clearfix">
			<h4>BoardDivs</h4>
		</div>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover edison-table">
			<colgroup>
				<col width="14%" />
				<col width="14%" />
				<col width="14%" />
				<col width="14%" />
				<col width="14%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>divCd(PK)</th>
					<%
						Locale[] availLocales = LanguageUtil.getAvailableLocales();
						for(int k=0;k<availLocales.length;k++){
							String localeStr = "Eng";
							if(availLocales[k].equals(Locale.TAIWAN)){ localeStr = "Tai"; }
	// 						else if(availLocales[k].equals(Locale.US)){ localeStr = "TAI"; }
							else if(availLocales[k].equals(Locale.KOREA)){ localeStr = "Kor"; }
							
							%>
							<th>titleNm<%=localeStr%></th>		
							<%
						}
					%>
					<th>contentNm</th>
					<th>divNm</th>
					<th>fileUpLoadUseYn</th>
					<th>popupYn</th>
					<th>replyYn</th>
				</tr>
			</thead>
			<tbody id="divSetBody">
				<c:forEach items="${boardDivList}" var="boardDiv" varStatus="status">
					<tr id="<portlet:namespace/>div_${status.count}">
						<td>
							<input type="hidden" id="<portlet:namespace/>numberArray" name="<portlet:namespace/>numberArray" value="${status.count}" />
							<input type="text" id="<portlet:namespace/>divCd_${status.count}" class="form-control" name="<portlet:namespace/>divCd_${status.count}" value="${boardDiv.divCd}" style="width: 100px;" />
						</td>
						<td>
							<input id="<portlet:namespace/>titleNmFirst_${status.count}" class="form-control" name="<portlet:namespace/>titleNmFirst_${status.count}" value="${boardDiv.titleNm1}" type="text" size="30" style="width: 120px;"/>
						</td>
						<td>
							<input id="<portlet:namespace/>titleNmSecond_${status.count}" class="form-control" name="<portlet:namespace/>titleNmSecond_${status.count}" value="${boardDiv.titleNm2}" type="text" size="30" style="width: 120px;"/>
						</td>
						<td>
							<input id="<portlet:namespace/>contentNm_${status.count}" class="form-control" name="<portlet:namespace/>contentNm_${status.count}" value="${boardDiv.contentNm}" type="text" size="30" style="width: 120px;"/>
						</td>
						<td>
							<input id="<portlet:namespace/>divNm_${status.count}" class="form-control" name="<portlet:namespace/>divNm_${status.count}" value="${boardDiv.divNm}" type="text" size="30" style="width: 120px;"/>
						</td>
						<td class="TC">
							<c:if test="${boardDiv.fileUpLoadUseYn}">
								<input id="<portlet:namespace/>fileUpLoadUseYn_${status.count}" name="<portlet:namespace/>fileUpLoadUseYn_${status.count}" type="checkbox" checked="checked"/>
							</c:if>
							<c:if test="${!boardDiv.fileUpLoadUseYn}">
								<input id="<portlet:namespace/>fileUpLoadUseYn_${status.count}" name="<portlet:namespace/>fileUpLoadUseYn_${status.count}" type="checkbox"/>
							</c:if>
						</td>
						<td class="TC">
							<c:if test="${boardDiv.popupYn}">
								<input id="<portlet:namespace/>popupYn_${status.count}" name="<portlet:namespace/>popupYn_${status.count}" type="checkbox" checked="checked"/>
							</c:if>
							<c:if test="${!boardDiv.popupYn}">
								<input id="<portlet:namespace/>popupYn_${status.count}" name="<portlet:namespace/>popupYn_${status.count}" type="checkbox"/>
							</c:if>
						</td>
						<td class="TC">
							<c:if test="${boardDiv.replyYn}">
								<input id="<portlet:namespace/>replyYn_${status.count}" name="<portlet:namespace/>replyYn_${status.count}" type="checkbox" checked="checked"/>
							</c:if>
							<c:if test="${!boardDiv.replyYn}">
								<input id="<portlet:namespace/>replyYn_${status.count}" name="<portlet:namespace/>replyYn_${status.count}" type="checkbox"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<input type="button" class="btn btn-primary" value="Add" onclick="<portlet:namespace/>addDiv()">
		<input type="submit" class="btn btn-primary" value="Save" style="float:right;">
	
	</div>
</aui:form>

