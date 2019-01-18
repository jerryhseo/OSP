<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="org.kisti.edison.model.EdisonExpando"%>
<%@page import="org.kisti.edison.util.EdisonExpndoUtil"%>

<%@page import="java.util.Map.Entry"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<html>
<head>

<liferay-portlet:resourceURL var="getOrganizationListURL" id="getOrganizationList" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="getOrganizationDetailInfoURL" id="getOrganizationDetailInfo" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="getOrganRegionNmURL" id="getOrganRegionNm" copyCurrentRenderParameters="false" escapeXml="false"/>
<liferay-portlet:resourceURL var="createOrganizationURL" id="createOrganization" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="updateOrganizationURL" id="updateOrganization" copyCurrentRenderParameters="false" escapeXml="false" />
<liferay-portlet:resourceURL var="deleteOrganizationURL" id="deleteOrganization" copyCurrentRenderParameters="false" escapeXml="false" />


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<base target="_self">
<title>기관 리스트</title>
<script src="/edison-2016-hook/js/jquery-1.10.2.min.js"></script>

<style type="text/css">
	.<portlet:namespace/>organ-search{
		width: 55%;
		text-align: right;
		border-bottom: 1px solid #dddddd;
	}
	
	.<portlet:namespace/>organ-view{
		float: left;
	}
	
	/* .<portlet:namespace/>organ-view.list{
		height: 500px;
		overflow-y: auto;
	} */
	
	.<portlet:namespace/>organ-view.detail{
		width: 100%;
		display: none;
		padding: 0% 5%;
	}
	
	.<portlet:namespace/>organ-view.detail table th, .<portlet:namespace/>organ-view.detail table td{
		padding: 15px !important;
	}
	
	.<portlet:namespace/>organ-view table{
		width: 100%;
	}
	
	.<portlet:namespace/>organ-view table th, .<portlet:namespace/>organ-view table td{
		border-bottom: 1px solid #dddddd;;
	}
	
	.<portlet:namespace/>organ-view table th, .<portlet:namespace/>organ-view table td{
		font-size: 15px;
		padding: 15px 10px;
	}
	
	.<portlet:namespace/>organTr:hover, .<portlet:namespace/>organTr.selected{
		cursor: pointer;
		background-color: #dddddd;
	}
	
	#<portlet:namespace/>organCdNmTd{
		text-align: left !important;
	}
	
	.subtitlearea{
		margin-left: 10px;
	}
	
	#<portlet:namespace/>searchInput{
		width: 70%;
		float: right;
	}
	
	.detailViewSubTitle{
		padding-left: 0px !important; 
		padding-right: 0px !important;
	}
	
	.swtabtitle{
		width: 35%;
		float: left;
		background: url(${pageContext.request.contextPath}/images/mypage/swarrow02.png) no-repeat 20px 13px;
		padding-left: 40px;
		border: solid 1px #d1d1d1;
		color: #ee843e;
		font-size: 15px;
		line-height: 2.4em;
		border-bottom: solid 1px #fff;
		z-index: 90;
		position: relative;
	}
	
	.swrightcon{
		height: 378px;
		width: 100%;
		padding: 20px;
		font-size: 15px;
		font-weight: 500;
		color: #555;
		outline: solid 1px #d1d1d1;
		margin-top: 33px;
		line-height: 1.6em;
		overflow-x: hidden;
		overflow-y: auto;
		margin: 1px;
	}
	
	#<portlet:namespace/>formActionBtn button{
		display: none;
	}
	
	#<portlet:namespace/>organAddBtnArea{
		float: right;
		margin: 18px 0px;
	}
	
</style>

</head>
<body>
	<div class="container table-responsive panel edison-panel">
		
		<div class="panel-heading clearfix detailViewSubTitle"> 
			<h2 class="pull-left" style="width: 40%;">
				<img src="${pageContext.request.contextPath}/images/sub_tit_bl.png"/>
				<span class="subtitlearea">
					<liferay-ui:message key="edison-create-account-field-title-university-management"/>
				</span>
			</h2>
			
			<!-- 검색 영역 -->
			<div class="btn-group pull-right" style="width: 60%; top: 20px;">
				<div class="input-group"> 
					<input type="text" id="<portlet:namespace/>searchInput" class="form-control" placeholder="<liferay-ui:message key="edison-table-list-header-orgNm"/>" onkeyup="<portlet:namespace/>searchOrgan();" onkeydown="if(event.keyCode ==13)<portlet:namespace/>searchOrgan();" value="" autofocus="autofocus">
					<div class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="<portlet:namespace/>searchOrgan();return false;"><i class="icon-search"></i></button>
						<button class="btn btn-default" onclick="<portlet:namespace/>searchInit();">Clear</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="panel-body">
			
			<div class="col-md-6 <portlet:namespace/>organ-view list">
				<table class="table table-bordered table-hover edison-table">
					<colgroup>
						<col width="25%" />
						<col width="50%" />
						<col width="25%" />
					</colgroup>
					
					<thead>
						<tr>
							<th>
								<liferay-ui:message key="edison-table-list-header-organization-code"/>
							</th>
							<th>
								<liferay-ui:message key="edison-create-account-field-title-university"/>
							</th>
							<th>
								<liferay-ui:message key="edison-table-list-header-region"/>
							</th>
						</tr>
					</thead>
					
					<tbody class="<portlet:namespace/>organ-list">
					</tbody>
				</table>
				
				<div>
					<span id="<portlet:namespace/>organAddBtnArea">
						<button class="btn btn-default" onclick="<portlet:namespace/>addDetailView();"><liferay-ui:message key="add"/></button>
					</span>
					<div class="text-center">
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<form id="<portlet:namespace/>organForm">
					<div class="swtabtitle">
						<liferay-ui:message key="edison-science-appstore-view-tab-detail-view"/>
					</div>
					<div class="swrightcon">
						<div class="<portlet:namespace/>organ-view detail">
							<input type="hidden" id="<portlet:namespace/>nextCd" name="<portlet:namespace/>nextCd" value="" />
							<input type="hidden" id="<portlet:namespace/>classPk" name="<portlet:namespace/>classPk" value="">					<!-- classPk -->
							<input type="hidden" id="<portlet:namespace/>valueId" name="<portlet:namespace/>valueId" value="">					<!-- valueId -->
							<input type="hidden" id="<portlet:namespace/>companyId" name="<portlet:namespace/>companyId" value="">				<!-- companyId -->
							<input type="hidden" id="<portlet:namespace/>tableId" name="<portlet:namespace/>tableId" value="">					<!-- tableId -->
							<input type="hidden" id="<portlet:namespace/>columnId" name="<portlet:namespace/>columnId" value="">				<!-- columnId -->
							<input type="hidden" id="<portlet:namespace/>rowId" name="<portlet:namespace/>rowId" value="">						<!-- rowId -->
							<input type="hidden" id="<portlet:namespace/>classNameId" name="<portlet:namespace/>classNameId" value="">			<!-- classNameId -->
							<input type="hidden" id="<portlet:namespace/>regionValueId" name="<portlet:namespace/>regionValueId" value="">		<!-- valueId -->
							<input type="hidden" id="<portlet:namespace/>regionColumnId" name="<portlet:namespace/>regionColumnId" value="">	<!-- columnId -->
							
							<div class="h20"></div>
							
							<table class="table table-bordered edison-table">
								<colgroup>
									<col width="30%;" />
									<col width="70%;" />
								</colgroup>
								
								<tr>
									<th>
										<liferay-ui:message key="edison-table-list-header-organization-code"/>
									</th>
									
									<td id="<portlet:namespace/>organCd" class="center">
									</td>
								</tr>
								
								<tr>
									<th>
										<liferay-ui:message key="edison-create-account-field-title-university"/>
									</th>
									
									<td id="<portlet:namespace/>organCdNmTd">
										<liferay-ui:input-localized xml="" id="organCdNm" name="organCdNm" cssClass=" field too_long_field" type="input"/>
									</td>
								</tr>
								
								<tr>
									<th>
										<liferay-ui:message key="edison-table-list-header-region"/>
									</th>
									
									<td id="<portlet:namespace/>organRegionNmTd">
										<aui:select id="organRegionNm" name="organRegionNm" label="" >
										</aui:select>
									</td>
								</tr>
							</table>
							
							
						</div>
						
					</div>
					
					<div class="h30"></div>
					
					<div id="<portlet:namespace/>formActionBtn" style="text-align: right;">
						<button id="<portlet:namespace/>addBtn" class="btn btn-default" onclick="<portlet:namespace/>addOranization();">ADD</button>
						<button id="<portlet:namespace/>updateBtn" class="btn btn-default" onclick="<portlet:namespace/>updateOranization();">UPDATE</button>
						<button id="<portlet:namespace/>deleteBtn" class="btn btn-default" onclick="<portlet:namespace/>deleteOranization();">DELETE</button>
						<button id="<portlet:namespace/>cancelBtn" class="btn btn-default" onclick="<portlet:namespace/>closeDetailView();">CLOSE</button>
					</div>
				</form>
			</div>
				
		</div>
	</div>
</body>

<script type="text/javascript">
	
	$(document).ready(function(){
		<portlet:namespace/>getOrganizationList(1);
	});
	
	function <portlet:namespace/>getOrganizationList(curPage){
		
		jQuery.ajax({
			type: "POST",
			url: "<%=getOrganizationListURL%>",
			data: {"<portlet:namespace/>curPage":curPage},
			async : true,
			success: function(data) {
				organList = data.organList;
				regionList = data.regionList;
				pagingStr = data.pagingStr;
				nextCd = data.nextCd;
				
				$("#<portlet:namespace/>nextCd").val(nextCd);
				
				tbody = $(".<portlet:namespace/>organ-list");
				tbody.html("");
				$(".text-center").html("");
				
				for(var i=0; i<organList.length; i++){
					tr = $("<tr/>").addClass("<portlet:namespace/>organTr")
								   .attr("id", "<portlet:namespace/>info_"+organList[i].cd)
								   .attr("organ-nm", organList[i].cdNm)
								   .attr("onclick", "<portlet:namespace/>detailView('"+organList[i].cd+"')")
								   .attr("viewType", "close");
					
					$("<td/>").addClass("center").attr("id", "<portlet:namespace/>organDetailCd").text(organList[i].cd).appendTo(tr);
					$("<td/>").addClass("center").attr("id", "<portlet:namespace/>organDetailCdNm").text(organList[i].cdNm).appendTo(tr);
					$("<td/>").addClass("center").attr("id", "<portlet:namespace/>organDetailRegionNm").text(organList[i].regionNm).appendTo(tr);
					
					tr.appendTo(tbody);
				}
				
				$(".text-center").append(pagingStr);
				
				selector = $("#<portlet:namespace/>organRegionNm");
				
				selector.html("");
				
				$("<option/>").val("").text("<liferay-ui:message key='full' />").appendTo(selector);
				for(var i=0; i<regionList.length; i++){
					$("<option/>").val(regionList[i].cd).text(regionList[i].cdNm).appendTo(selector);
				}
				
			},error: function(){
			},complete: function(){
			}
		});
		
	}
	
	// 상세보기(리스트) Click Event
	function <portlet:namespace/>detailView(organCd){
		
		jQuery.ajax({
			type: "POST",
			url: "<%=getOrganizationDetailInfoURL%>",
			data: {"<portlet:namespace/>organCd":organCd},
			async : true,
			success: function(data) {
				var expandoValueMap = data.expandoValueMap;
				var expandoRegionValueMap = data.expandoRegionValueMap;
				
				if(expandoValueMap != null && expandoValueMap != "" && expandoValueMap != "undefined"){
					$("#<portlet:namespace/>valueId").val(expandoValueMap.valueId);
					$("#<portlet:namespace/>companyId").val(expandoValueMap.companyId);
					$("#<portlet:namespace/>tableId").val(expandoValueMap.tableId);
					$("#<portlet:namespace/>columnId").val(expandoValueMap.columnId);
					$("#<portlet:namespace/>rowId").val(expandoValueMap.rowId);
					$("#<portlet:namespace/>classNameId").val(expandoValueMap.classNameId);
					$("#<portlet:namespace/>classPk").val(organCd);
					
					<portlet:namespace/>localizedInputSet("organCdNm",expandoValueMap.data);	// 기관명
				}
				
				if(expandoRegionValueMap != null && expandoRegionValueMap != "" && expandoRegionValueMap != "undefined"){
					$("#<portlet:namespace/>regionValueId").val(expandoRegionValueMap.valueId);
					$("#<portlet:namespace/>regionColumnId").val(expandoRegionValueMap.columnId);
					$("#<portlet:namespace/>organRegionNm").val(expandoRegionValueMap.data);		// 지역
				}
			},error: function(){
			},complete: function(){
			}
		});
		
		if($("#<portlet:namespace/>info_" + organCd).attr("viewType") == "close"){
			
			// 기관 선택 및 상세정보 화면 출력 관련 속성
			$(".<portlet:namespace/>organTr").attr("viewType", "close");
			$(".<portlet:namespace/>organTr").removeClass("selected");
			$("#<portlet:namespace/>info_" + organCd).addClass("selected");
			$("#<portlet:namespace/>info_" + organCd).attr("viewType", "show");
			
			$("#<portlet:namespace/>organCd").text(organCd);				// 기관 코드
//			$("#<portlet:namespace/>organRegionNm").val(regionCd);	// 지역
			
			// 버튼 출력
			$("#<portlet:namespace/>addBtn").hide();
			$("#<portlet:namespace/>updateBtn").show();
			$("#<portlet:namespace/>deleteBtn").show();
			$("#<portlet:namespace/>cancelBtn").show();
			
			// 상세정보 화면 출력
			$(".<portlet:namespace/>organ-view.detail").show();
			
		} else if($("#<portlet:namespace/>info_" + organCd).attr("viewType") == "show"){
			$(".<portlet:namespace/>organTr").attr("viewType", "close");
			<portlet:namespace/>closeDetailView();
		}
	}
	
	// 다국어 필드 값 셋팅
	function <portlet:namespace/>localizedInputSet(paramName,xmlData){
		// var availableLanguagesArray = dataType.structure().availableLanguageIds();
		
		var xml = $.parseXML( xmlData );
		$("#<portlet:namespace/>"+paramName+"ContentBox div:last").click();
		
		// var size = availableLanguagesArray.length;
		var size = $(xml).find("Data").length;
		for(var i=0;i<size;i++){
			var language = $(xml).find("Data").eq(i).attr("language-id");
			var languageValue = <portlet:namespace/>nullToStr($(xml).find("[language-id ='"+language+"']").text());
			$("#<portlet:namespace/>"+paramName+"_"+language).val(languageValue);
		}
		
		$("#<portlet:namespace/>"+paramName).attr("placeholder",$(xml).find("[language-id ='en_US']").text()).val($(xml).find("[language-id ='en_US']").text());
		$("#<portlet:namespace/>"+paramName+"ContentBox").find("li[data-value='en_US']").trigger("click");
	}
	
	// nullToStr
	function <portlet:namespace/>nullToStr(str){
		if(str==null||typeof str =="undefined"||!str){
			return "";
		}else{
			return str;
		}
	}
	
	// 상세보기 Close Button Event
	function <portlet:namespace/>closeDetailView(){
		
		$("#<portlet:namespace/>formActionBtn button").hide();
		$(".<portlet:namespace/>organ-view.detail").hide();
		
		$(".<portlet:namespace/>organTr").removeClass("selected");
		$(".<portlet:namespace/>organTr").attr("viewType", "close");
		
		$("#<portlet:namespace/>organCd").text("");
		$("#<portlet:namespace/>organCdNm").val("");
		$("#<portlet:namespace/>organRegionNm").val("");
		$("#<portlet:namespace/>organForm input[type=hidden]").val("")
	}
	
	/* 기관 추가 Bubbon Event */
	function <portlet:namespace/>addDetailView(){
		$(".<portlet:namespace/>organTr").removeClass("selected");
		$(".<portlet:namespace/>organTr").attr("viewType", "close");
		
		$("#<portlet:namespace/>organCd").text($("#<portlet:namespace/>nextCd").val());
		$("#<portlet:namespace/>organCdNm").val("");
		$("#<portlet:namespace/>organCdNm").attr("placeholder", "");
		$("#<portlet:namespace/>organRegionNm").val("");
		
		$("#<portlet:namespace/>addBtn").show();
		$("#<portlet:namespace/>updateBtn").hide();
		$("#<portlet:namespace/>deleteBtn").hide();
		$("#<portlet:namespace/>cancelBtn").show();
		
		$(".<portlet:namespace/>organ-view.detail").show();
	}
	
	// 기관 추가 ADD Button Event
	function <portlet:namespace/>addOranization(){
		var sendData = $("#<portlet:namespace/>organForm").serialize();
		
		jQuery.ajax({
			type: "POST",
			url: "<%=createOrganizationURL%>",
			data: sendData,
			async : true,
			success: function(data) {
				<portlet:namespace/>getOrganizationList(1);
				alert('<liferay-ui:message key="edison-data-insert-success"/>');
			},error: function(){
			},complete: function(){
			}
		});
	}
	
	// 상세보기 UPDATE Button Event
	function <portlet:namespace/>updateOranization(){
		var sendData = $("#<portlet:namespace/>organForm").serialize();
		
		jQuery.ajax({
			type: "POST",
			url: "<%=updateOrganizationURL%>",
			data: sendData,
			async : true,
			success: function(data) {
				<portlet:namespace/>getOrganizationList(1);
				alert('<liferay-ui:message key="edison-data-update-success"/>');
			},error: function(){
				alert('<liferay-ui:message key="edison-data-update-error"/>');
			},complete: function(){
			}
		});
	}
	
	// 기관 DELETE Button Event
	function <portlet:namespace/>deleteOranization(){
		
		var sendData = {"<portlet:namespace/>valueId":$("#<portlet:namespace/>valueId").val(),
						"<portlet:namespace/>classPk":$("#<portlet:namespace/>classPk").val()
					   }
		
		jQuery.ajax({
			type: "POST",
			url: "<%=deleteOrganizationURL%>",
			data: sendData,
			async : true,
			success: function(data) {
				var deleteSuccess = data.deleteSuccess;
				if(deleteSuccess){
					<portlet:namespace/>getOrganizationList(1);
					alert('<liferay-ui:message key="edison-data-delete-success"/>');
				} else {
					alert('<liferay-ui:message key="edison-organization-delete-failed-massage"/>');
				}
			},error: function(){
				alert('<liferay-ui:message key="edison-data-delete-error"/>');
			},complete: function(){
			}
		});
		
	}
	
	function <portlet:namespace/>searchInit(){
		var searchData = $("#<portlet:namespace/>searchInput").val("");
		<portlet:namespace/>searchOrgan();
	}
	
	// Search Function
	function <portlet:namespace/>searchOrgan(){
		var searchData = $("#<portlet:namespace/>searchInput").val();
		var searchDataUpper = searchData.toUpperCase();
		var searchDataLower = searchData.toLowerCase();
		
		if(searchData === null || searchData === ""){
			$(".<portlet:namespace/>organTr").show();
		} else {
			$(".<portlet:namespace/>organTr").hide();
			//Search된 값에 해당하는 File만 display
			$(".<portlet:namespace/>organTr[organ-nm*="+searchDataUpper+"]").show();
			$(".<portlet:namespace/>organTr[organ-nm*="+searchDataLower+"]").show();
		}
	}
	
	function <portlet:namespace/>searchDataTypeList(curPage){
		<portlet:namespace/>closeDetailView();
		<portlet:namespace/>getOrganizationList(curPage);
	}
</script>

</html>