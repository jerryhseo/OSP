<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.kisti.edison.science.service.constants.InputdeckConstants"%>
<%@ include file="/common/init.jsp"%>
<style type="text/css">
	.inputdeck-portlet #inputDeckParameterLeft #inputDeckHeader{
		background-color: #f7f7f7;
		border-bottom: 1px solid #b4b4b4;
		font-weight: 600;
		padding: 15px 0px;
	}
	
	.inputdeck-portlet div.content{
		width: 100%;
		margin: auto;
	}
	
	.inputdeck-portlet div.left{
		width: 51%;
		float: left;
	}
	
	.inputdeck-portlet div.shortleft{
		width: 49%;
		float: left;
	}
	
	.inputdeck-portlet div.right{
		width: 48%;
		float: right;
	}
	
	.inputdeck-portlet .long_field{
		width: 350px;
	}
	
	.inputdeck-portlet .short_field{
		width: 150px;
	}
	
	.inputdeck-portlet .form-inline input[type=text].to_short_field{
		width: 70px;
		margin-right: 5px;
	}
	
	.inputdeck-portlet .numberCheck{
		-webkit-ime-mode : disabled; 
		-moz-ime-mode : disabled; 
		-ms-ime-mode : disabled; 
		ime-mode : disabled; 
	}
	
	.inputdeck-portlet .parameter{
 		display: none;
	}
	
	.inputdeck-portlet .actieveBtn{
 		display: none;
	}
	
	.aui .checkbox{
		font-weight: 600;
	}
	
	.aui input[type="radio"], .aui input[type="checkbox"]{
		margin: 0px;
	}
	
	.aui select{
		width: auto;
	}
	
	.aui .input-text-wrapper{
		display: initial;
	}

	/*추가*/	
	td#<portlet:namespace/>variableTableTd{
		padding:0px;
	}
	
	@media (min-width: 1200px) {
	.aui .row {
		margin-left: 0px;
	}
	.aui . row{
		margin-left:0px;
	}
</style>
<div class="edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			<liferay-ui:message key='edison-science-appstore-inputdeck-title' />
		</h3>
	</div>
</div>
<div class="table1_list" >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="10%">
			<col width="20%">
			<col width="10%">
			<col width="20%">
			<col width="10%">
			<col width="20%">
			<col width="10%">
			<col width="*%">
		</colgroup>
		<tr>
			<th>value delimiter</th>
			<td align="center">
				<select onChange="<portlet:namespace/>lineFormat();" id="valueDelimiter" class="aui-field-select">
					<option value=" = ">EQUAL</option>
					<option value=" ">SPACE</option>
				</select>
			</td>
			
			<th>Line delimiter</th>
			<td align="center">
				<select onChange="<portlet:namespace/>lineFormat();" id="lineDelimiter" class="aui-field-select">
					<option value=" ;">SEMICOLON</option>
					<option value=" :">COLON</option>
					<option value="">NULL</option>
				</select>
			</td>
			
			<th>Comment Char</th>
			<td align="center">
				<input type="text" size="5" maxlength="1" id="commentChar" class="field" onkeyup="<portlet:namespace/>lineFormat();"/>
			</td>
			
			<th><liferay-ui:message key='preview' /></th>
			<td align="center">
				<input type="text" id="linePreview" class="field" style="font-size: 13px;font-weight: bold;width: 120px;" readonly="readonly" disabled="disabled" value="KEY = VALUE ;"/>
			</td>
		</tr>
		<tr>
			<th>Vector bracket</th>
			<td align="center">
				<select onChange="<portlet:namespace/>vectorFormat();" id="vectorBracket" class="aui-field-select">
					<option value="SQUARE">SQUARE</option>
					<option value="SQUARE_SPACE">SQUARE_SPACE</option>
					<option value="ROUND">ROUND</option>
					<option value="ROUND_SPACE">ROUND_SPACE</option>
				</select>
			</td>
			
			<th>Vector Delimiter</th>
			<td align="center">
				<select onChange="<portlet:namespace/>vectorFormat();" id="vectorDelimiter" class="aui-field-select">
					<option value=" ">SPACE</option>
					<option value=",">COMMA</option>
				</select>
			</td>
			
			<th><liferay-ui:message key='preview' /></th>
			<td colspan="3">
				<input type="text" id="vectorPreview" class="field" style="font-size: 13px;font-weight: bold;" readonly="readonly" disabled="disabled" value="[A B C]"/>
			</td>
		</tr>
	</table>
</div>

<div class="h15"></div>

<div class="content">
	<div class="shortleft table2_list activate edison-panel" id="activateDiv" style="display: none;">
		<table id="<portlet:namespace/>activateTable" class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th width="10%"></th>
					<th width="23%">Name</th>
					<th width="*" colspan="3"><liferay-ui:message key='edison-science-appstore-inputdeck-variable-activation-conditions' /></th>
					<th width="13%">value</th>
				</tr>
			</thead>
			<tbody id="inputDeckActivateBody">
			</tbody>
		</table>
		<div id="btnGroupBottom">
			<button class="btn btn-default"onclick="<portlet:namespace/>closeActivateDiv();"><span class="icon-remove"> <liferay-ui:message key='cancel' /></span></button>
			<button class="btn btn-default" id="inputDeckAcivateSave"><span class="icon-edit"> <liferay-ui:message key='update' /></span></button>
		</div>
	</div>
	
	
	
	<div class="shortleft table2_list activateSearch" id="activateSearchDiv" style="display: none;">
		<table id="<portlet:namespace/>activateSearchTable" style="width: 100%;">
			<colgroup>
				<col width="20%">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<th>Name</th>
					<th>Value</th>
				</tr>
			</thead>
			<tbody id="inputDeckActivateSearchBody">
			</tbody>
		</table>
		<div id="btnGroupBottom">
			<input type="button" class="button0801" onclick="<portlet:namespace/>closeActivateSearchDiv();" value="<liferay-ui:message key='edison-button-board-ok' />" />
		</div>
	</div>
	
	
	
	<div class="left" id="inputDeckParameterLeft">
		<div class="row" style="margin: 0px;" id="inputDeckHeader">
			<div class="col-md-3 text-center">Variable Name</div>
			<div class="col-md-7 text-center">Value</div>
			<div class="col-md-2 text-center">Description</div>
		</div>
		<div class="row" id="<portlet:namespace/>variableTableTd" style="margin: 0px 30px;">
		
		</div>
	</div>
	<aui:form name="frm">
	<div class="right">
		<div class="table1_list" >
			<table border="0" width="100%" class="data_property">
				<colgroup>
					<col width="25%">
					<col width="20%">
					<col width="10%">
					<col width="*%">
				</colgroup>
				<tbody id="inputParameterBody">
					<!--타입 -->
					<tr>
						<th>
							<liferay-ui:message key='edison-table-list-header-type' /> <span class="requiredField">*</span>
						</th>
						<td colspan="3">
							<select id="inputDeckType" onChange="<portlet:namespace/>changeType(this.value,'add');" class="aui-field-select">
								<option value=""><liferay-ui:message key='choice'/></option>
								<option value="<%=InputdeckConstants.TYPE_NUMERIC%>"><%=InputdeckConstants.TYPE_NUMERIC%></option>
								<option value="<%=InputdeckConstants.TYPE_STRING%>"><%=InputdeckConstants.TYPE_STRING%></option>
								<option value="<%=InputdeckConstants.TYPE_LIST%>"><%=InputdeckConstants.TYPE_LIST%></option>
								<option value="<%=InputdeckConstants.TYPE_VECTOR%>"><%=InputdeckConstants.TYPE_VECTOR%></option>
								<option value="<%=InputdeckConstants.TYPE_GROUP%>"><%=InputdeckConstants.TYPE_GROUP%></option>
								<option value="<%=InputdeckConstants.TYPE_COMMENT%>"><%=InputdeckConstants.TYPE_COMMENT%></option>
							</select>
							<span class="requiredField"> ※  <liferay-ui:message key='edison-science-appstore-inputdeck-init-message' /></span>
						</td>
					</tr>
					<!--변수명 -->
					<tr>
						<th>
							<liferay-ui:message key='edison-table-list-header-variable-name' /> <span class="requiredField">*</span>
						</th>
						<td colspan="3">
							<input type="text" class="long_field field" id="inputDeckName"/>
						</td>
					</tr>
					<!--InputDeck 활성화-->
					<tr id="active" class="parameter">
						<th>
							Active <liferay-ui:icon-help message="edison-science-appstore-inputdeck-help-active-message"/>
						</th>
						<td colspan="3">
							<select id="activeValue" class="aui-field-select">
								<option value="true">true</option>
								<option value="false">false</option>
							</select>
						</td>
					</tr>
					
					<tr id="varRepet" class="parameter">
						<th>
							<liferay-ui:message key='edison-science-appstore-inputdeck-variable-clone' />
						</th>
						<td colspan="3">
							<select id="inputDeckVarClone" class="aui-field-select">
								<option value=""><liferay-ui:message key='--empty--' /></option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select>
						</td>
					</tr>
					
					<!--별칭 -->
					<tr id="nickName" class="parameter" validation="required">
						<th>
							<liferay-ui:message key='edison-table-list-header-nick-name' /><span class="requiredField">*</span>
						</th>
						<td colspan="3">
							<liferay-ui:input-localized name="inputDeckNickName" xml=" " cssClass="long_field field"  type="input"/>
						</td>
					</tr>
					
					<!--설명 -->
					<tr id="description" class="parameter">
						<th>
							<liferay-ui:message key='edison-simulation-execute-simulation-description' />
						</th>
						<td colspan="3">
							<liferay-ui:input-localized name="inputDeckDescription" xml=" " cssClass="long_field field"  type="input"/>
						</td>
					</tr>
					
					<!--단위-->
					<tr id="unit" class="parameter">
						<th>
							Unit <liferay-ui:icon-help message="edison-science-appstore-inputdeck-help-unit-message"/>
						</th>
						<td colspan="3">
							<input type="text" id="inputDeckUnit" class="short_field field" maxlength="15"/>
						</td>
					</tr>
					
					<!--최소값 -->
					<tr id="valueDomainMin" class="parameter">
						<th>
							<liferay-ui:message key='edison-appstore-min' />
						</th>
						<td class="TC">
							<select id="inputDeckMinSelect" class="aui-field-select">
								<option value="=">>=</option>
								<option value="<">></option>
							</select>
						</td>
						<td colspan="2">
							<input type="text" id="inputDeckMinValue" class="to_short_field numberCheck field" onkeydown="return checkNumberExp(event,this.value);"/>
						</td>
					</tr>
					
					<!--최대값 -->
					<tr id="valueDomainMax" class="parameter">
						<th>
							<liferay-ui:message key='edison-appstore-max' />
						</th>
						<td class="TC">
							<select id="inputDeckMaxSelect" class="aui-field-select">
								<option value="="><=</option>
								<option value=">"><</option>
							</select>
						</td>
						<td colspan="2">
							<input type="text" id="inputDeckMaxValue" class="to_short_field numberCheck field" onkeydown="return checkNumberExp(event,this.value);"/>
						</td>
					</tr>
					
					<!--Numeric 기본값 -->
					<tr id="numericValue" class="parameter" validation="required">
						<th>
							<liferay-ui:message key='edison-table-list-header-default-data' /> <span class="requiredField">*</span>
						</th>
						<td colspan="3">
							<input type="text" id="inputDeckNumericValue" class="short_field numberCheck field" onkeydown="return checkNumberExp(event,this.value);"/>
						</td>
					</tr>
					
					<!--String 기본값 -->
					<tr id="stringValue" class="parameter" validation="required">
						<th>
							<liferay-ui:message key='edison-table-list-header-default-data' /> <span class="requiredField">*</span>
						</th>
						<td colspan="3">
							<input type="text" class="long_field field" id="inputDeckStringValue" />
						</td>
					</tr>
					
					<!--sweep -->
					<tr id="sweep" class="parameter" >
						<th id="sweepTh" rowspan="3">
							<input type="checkbox" name="inputDeckSweepCheck" id="inputDeckSweepCheck" onchange="<portlet:namespace/>changeSweep();"/>
							<label for="inputDeckSweepCheck" style="display: inline;font-weight: 600;">Sweep <liferay-ui:icon-help message="edison-science-appstore-inputdeck-help-sweep-message"/></label>
						</th>
						<th>
							<liferay-ui:message key='edison-appstore-min' />
						</th>
						<td class="TC">
							<select style="width: 60px;" id="inputDeckSweepMinSelect" class="aui-field-select">
								<option value="=">>=</option>
								<option value="<">></option>
							</select>
						</td>
						<td>
							<input type="text" class="short_field sweepInput numberCheck field" onkeydown="return checkNumberExp(event,this.value);" id="inputDeckSweepMinValue" />
						</td>
					</tr>
					<tr id="sweep" class="parameter">
						<th>
							<liferay-ui:message key='edison-appstore-max' />
						</th>
						<td class="TC">
							<select id="inputDeckSweepMaxSelect" class="aui-field-select">
								<option value="="><=</option>
								<option value=">"><</option>
							</select>
						</td>
						<td>
							<input type="text" class="short_field sweepInput numberCheck field" onkeydown="return checkNumberExp(event,this.value);" id="inputDeckSweepMaxValue"/>
						</td>
					</tr>
					<tr id="sweep" class="parameter">
						<td class="TC">
							<select style="width: 110px;" id="inputDeckSweepType" class="aui-field-select">
								<option value="value">By Value</option>
								<option value="slice">By Slice</option>
							</select>
						</td>
						<td colspan="2">
							<input type="text" class="short_field sweepInput numberCheck field" onkeydown="return checkNumberExp(event,this.value);" id="inputDeckSweepTypeValue"/>
						</td>
					</tr>
					
					
					<!--dimension -->
					<tr id="dimension" class="parameter">
						<th>
							Dimension
						</th>
						<td colspan="3">
							<select onChange="<portlet:namespace/>changeDimension(this.value);" id="inputDeckDemensionSelect" class="aui-field-select">
								<option value ="2"><liferay-ui:message key='edison-science-appstore-inputdeck-dimension-2'/></option>
								<option value ="3"><liferay-ui:message key='edison-science-appstore-inputdeck-dimension-3'/></option>
								<option value ="4"><liferay-ui:message key='edison-science-appstore-inputdeck-dimension-4'/></option>
							</select>
						</td>
					</tr>
					<tr id="dimension" class="parameter">
						<th>
							<liferay-ui:message key='edison-table-list-header-default-data'/> <span class="requiredField">*</span>
						</th>
						<td colspan="3" id="dimensionValueTd" class="form-inline">
							<input type="text" class="to_short_field numberCheck field" id="inputDeckDimensionValue_0" onkeydown="return checkNumberExp(event,this.value);"/>
							<input type="text" class="to_short_field numberCheck field" id="inputDeckDimensionValue_1" onkeydown="return checkNumberExp(event,this.value);"/>
						</td>
					</tr>
					
					
					
					<!--list-->
					<tr id="list" class="parameter">
						<td colspan="4">
							<span class="requiredField"> ※  <liferay-ui:message key='edison-science-appstore-inputdeck-list-message' /></span>
						</td>
					</tr>
					
					<tr id="list" class="parameter">
						<th>
							Name
						</th>
						<td>
							<liferay-ui:input-localized name="inputDeckListName" xml=" " cssClass="short_field field"  type="input"/>
						</td>
						<td rowspan="2" class="TC">
							<button class="btn btn-default btn-sm" type="button" onClick="<portlet:namespace/>listBtnEvent('add');"><span class="icon-edit"> <liferay-ui:message key='update' /></span></button>
							<button class="btn btn-default btn-sm" type="button" onClick="<portlet:namespace/>listBtnEvent('delete');"><span class="icon-trash"> <liferay-ui:message key='delete' /></span></button>
						</td>
						<td rowspan="2">
							<select id="inputDeckListParemter" onchange="listItemSearch(this.value);" class="aui-field-select">
								
							</select>
						</td>
					</tr>
					<tr id="list" class="parameter">
						<th>
							Value
						</th>
						<td>
							<input type="text" id="para_listValue" class="short_field noupdate field"/>
						</td>
					</tr>
					
					<!--comment-->
					<tr id="comment" class="parameter">
						<th>
							<liferay-ui:message key='edison-table-list-header-content' /> <liferay-ui:icon-help message="edison-science-appstore-inputdeck-help-comment-message"/>
						</th>
						<td colspan="3">
							<input type="text" class="long_field field" id="inputDeckComment"/>
						</td>
					</tr>
					
					<!--그룹선택-->
					<tr id="groupChoice" class="parameter">
						<th>
							<liferay-ui:message key='edison-science-appstore-inputdeck-group-choice' />
						</th>
						<td colspan="3">
							
						</td>
					</tr>
					
					<!-- 변수 선택 -->
					<tr id="variableChoice" class="parameter">
						<th>
							<liferay-ui:message key='edison-science-appstore-inputdeck-var-choice' />
						</th>
						<td colspan="3" id="variableChoiceTdArea">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="btnGroupBottom" class="addBtnGroup">
			<button class="btn btn-default parameter" id="variableActivationInsertBtn" type="button" onclick="<portlet:namespace/>addActivateOpen(true,true);"><span class="icon-check">   <liferay-ui:message key='edison-science-appstore-inputdeck-variable-activation-conditions' /></span></button>
			<button class="btn btn-default actieveBtn" type="button" onclick="<portlet:namespace/>changeType('','add');"><span class="icon-remove">   <liferay-ui:message key='cancel' /></span></button>
			<button class="btn btn-default actieveBtn" type="button" onclick="<portlet:namespace/>paraSave(true,false);"><span class="icon-search">   <liferay-ui:message key='add' /></span></button>
		</div>
		<div id="btnGroupBottom" class="updateBtnGroup">
			<button class="btn btn-default parameter" id="variableActivationUpdateBtn" type="button" onclick="<portlet:namespace/>activateSearchOpen(false,false);"><span class="icon-check">   <liferay-ui:message key='edison-science-appstore-inputdeck-variable-activation-conditions' /></span></button>
			<button class="btn btn-default actieveBtn" type="button" onclick="<portlet:namespace/>changeType('','add');"><span class="icon-remove">   <liferay-ui:message key='cancel' /></span></button>
			<button class="btn btn-default actieveBtn" type="button" onclick="<portlet:namespace/>paraSave(true,true);"><span class="icon-edit">   <liferay-ui:message key='update' /></span></button>
			<button class="btn btn-default actieveBtn" type="button" onclick="<portlet:namespace/>paraDelete();"><span class="icon-trash">   <liferay-ui:message key='edison-button-board-delete' /></span></button>
		</div>
	</div>
	</aui:form>
</div>


<script type="text/javascript">
	
	var <portlet:namespace/>dataType = new OSP.DataType();
	
	function <portlet:namespace/>display(){
		$('#<portlet:namespace/>variableTableTd').empty();
// 		console.log(JSON.stringify(dataType,null,4));
		
		<portlet:namespace/>dataType.showStructuredDataEditor(
				'<portlet:namespace/>', 
				$('#<portlet:namespace/>variableTableTd'),
				'<%=request.getContextPath()%>',
				'<%=themeDisplay.getLanguageId()%>');
	}
	
	
	Liferay.on( OSP.Event.OSP_REQUEST_DATA, function(eventData){
		var structure = {
			data: <portlet:namespace/>dataType.structure()
		};
		
		Liferay.fire( OSP.Event.OSP_RESPONSE_DATA, structure );
	});
	
	
	Liferay.on('OSP_SHOW_SDE', function(eventData){
		if(eventData.targetPortlet === 'BROADCAST' ||
		   eventData.targetPortlet === '<portlet:namespace/>' ){
			if( eventData.strStructure!="" ){
				<portlet:namespace/>dataType.deserializeStructure(JSON.parse(eventData.strStructure));
				<portlet:namespace/>init(true);
			}else{
				<portlet:namespace/>dataType.structure(<portlet:namespace/>dataType.newDataStructure(JSON.parse('{}')));
				<portlet:namespace/>init(false);
			}
			
			<portlet:namespace/>display();
		}
	});
	
	function <portlet:namespace/>init(isDataExist){
		<portlet:namespace/>dataType.structure().parameterFormSpace(false);
		<portlet:namespace/>dataType.structure().vectorFormSpace(false);
		<portlet:namespace/>dataType.structure().sweepMax(1);
		<portlet:namespace/>dataType.structure().defaultLanguageId('${defaultLanguageId}');
		var availableLanguageIds = '${availableLanguageIds}'.split(",");
		
		for(var i=0;i<availableLanguageIds.length;i++){
			<portlet:namespace/>dataType.structure().addLanuageId(availableLanguageIds[i]);
		}
		
// 		$(document).tooltip();
// 		$("img[width='20']").tooltip();
		
		if(isDataExist){
			$("#valueDelimiter").val(<portlet:namespace/>dataType.structure().parameterFormValueDelimiter()).prop("selected",true);
			var parameterDelimite = "";
			if(<portlet:namespace/>dataType.structure().parameterFormParameterDelimiter()){
				parameterDelimite = <portlet:namespace/>dataType.structure().parameterFormParameterDelimiter();
			}
			$("#lineDelimiter").val(parameterDelimite).prop("selected",true);
			
			var commentChar = "";
			if(<portlet:namespace/>dataType.structure().parameterFormCommentChar()){
				commentChar = <portlet:namespace/>dataType.structure().parameterFormCommentChar();
			}
			$("#commentChar").val(commentChar);
			
			var text = commentChar+"KEY"+<portlet:namespace/>dataType.structure().parameterFormValueDelimiter()+"VALUE"+parameterDelimite;
			$("#linePreview").val(text);
			
			
			$("#vectorBracket").val(<portlet:namespace/>dataType.structure().vectorFormBraceChar()).prop("selected",true);
			$("#vectorDelimiter").val(<portlet:namespace/>dataType.structure().vectorFormDelimiter()).prop("selected",true);
			$("#vectorPreview").val(<portlet:namespace/>dataType.structure().vectorFormSample());
		}else{
			<portlet:namespace/>dataType.structure().setParameterForm(" = "," ;","",'');
			<portlet:namespace/>dataType.structure().setVectorForm(OSP.Constants.SQUARE," ","[A B C]");
		}
	}
	
	function <portlet:namespace/>lineFormat(val){
		var valueDelimiter = $("#valueDelimiter option:selected").val();
		var parameterDelimiter = $("#lineDelimiter option:selected").val();
		var commentChar = $("#commentChar").val();
		
		var text = commentChar+"KEY"+valueDelimiter+"VALUE"+parameterDelimiter;
		$("#linePreview").val(text);
		<portlet:namespace/>dataType.structure().setParameterForm(valueDelimiter,parameterDelimiter,commentChar,'');
	}
	
	function <portlet:namespace/>vectorFormat(){
		var vectorBracketStartValue = "";
		var vectorBracketEndValue = "";
		
		var vectorBracket = $("#vectorBracket option:selected").val();
		
		if(vectorBracket==OSP.Constants.SQUARE){
			vectorBracketStartValue = "[";
			vectorBracketEndValue = "]";
		}else if(vectorBracket==OSP.Constants.SQUARE_SPACE){
			vectorBracketStartValue = "[ ";
			vectorBracketEndValue = " ]";
		}else if(vectorBracket==OSP.Constants.ROUND){
			vectorBracketStartValue = "(";
			vectorBracketEndValue = ")";
		}else if(vectorBracket==OSP.Constants.ROUND_SPACE){
			vectorBracketStartValue = "( ";
			vectorBracketEndValue = " )";
		}
		
		var vectorDelimiter = $("#vectorDelimiter option:selected").val();
		
		var text = vectorBracketStartValue+"A"+vectorDelimiter+"B"+vectorDelimiter+"C"+vectorBracketEndValue;
		$("#vectorPreview").val(text);
		
		<portlet:namespace/>dataType.structure().setVectorForm(vectorBracket,vectorDelimiter,text);
		
		//1|2|3
		//기존 vector value 변경
// 		var nameArray = inputdeckForm.getVariableNamesByType(OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR);
// 		if(nameArray.length!=0){
// 			var <portlet:namespace/>editor = OSPInputdeck.createEditor(inputdeckForm, '<portlet:namespace/>', $("#<portlet:namespace/>variableTableTd"), defaultLanguage)
// 			<portlet:namespace/>editor.showForm(<portlet:namespace/>searchParameter);
// 		}
	}
	
	var numericShowTrJson 	= '{"active":true,"nickName":false,"description":true,"groupChoice":true,"varRepet":false,"unit":true,"valueDomainMin":true,"valueDomainMax":true,"numericValue":true,"stringValue":false,"sweep":true,"dimension":false,"list":false,"comment":false,"variableActive":true,"variableChoice":false}';
	var stringShowTrJson	= '{"active":true,"nickName":false,"description":true,"groupChoice":true,"varRepet":false,"unit":false,"valueDomainMin":false,"valueDomainMax":false,"numericValue":false,"stringValue":true,"sweep":false,"dimension":false,"list":false,"comment":false,"variableActive":true,"variableChoice":false}';
	var listShowTrJson 		= '{"active":true,"nickName":false,"description":true,"groupChoice":true,"varRepet":false,"unit":false,"valueDomainMin":false,"valueDomainMax":false,"numericValue":false,"stringValue":false,"sweep":false,"dimension":false,"list":true,"comment":false,"variableActive":true,"variableChoice":false}';
	var vectorShowTrJson 	= '{"active":true,"nickName":false,"description":true,"groupChoice":true,"varRepet":false,"unit":false,"valueDomainMin":false,"valueDomainMax":false,"numericValue":false,"stringValue":false,"sweep":false,"dimension":true,"list":false,"comment":false,"variableActive":true,"variableChoice":false}';
	var groupShowTrJson 	= '{"active":true,"nickName":true,"description":false,"groupChoice":false,"varRepet":false,"unit":false,"valueDomainMin":false,"valueDomainMax":false,"numericValue":false,"stringValue":false,"sweep":false,"dimension":false,"list":false,"comment":false,"variableActive":false,"variableChoice":true}';
	var commentShowTrJson	= '{"active":true,"nickName":false,"description":false,"groupChoice":true,"varRepet":false,"unit":false,"valueDomainMin":false,"valueDomainMax":false,"numericValue":false,"stringValue":false,"sweep":false,"dimension":false,"list":false,"comment":true,"variableActive":false,"variableChoice":false}';
	
	
	Liferay.on( 'parameterSelected', function(event){
		var parameterObj = event.sourceParameter;
		
		console.log("===========parameterSelected_fire============");
		console.log(JSON.stringify(parameterObj,null,4));
		console.log("=============================================");
		
		<portlet:namespace/>changeType(parameterObj.type(),'update');
		
		$("#varRepet").show();
		$("#inputDeckVarClone").val("");
		
		$('#inputDeckType option[value='+parameterObj.type()+']').prop('selected', true);
		$("#inputDeckName").val(parameterObj.name());
		
		var searchJson;
		var type = parameterObj.type();
		
		if(type==OSP.Constants.NUMERIC){
			searchJson = JSON.parse(numericShowTrJson);
		}else if(type==OSP.Constants.STRING){
			searchJson = JSON.parse(stringShowTrJson);
		}else if(type==OSP.Constants.LIST){
			searchJson = JSON.parse(listShowTrJson);
		}else if(type==OSP.Constants.VECTOR){
			searchJson = JSON.parse(vectorShowTrJson);
		}else if(type==OSP.Constants.GROUP){
			searchJson = JSON.parse(groupShowTrJson);
		}else if(type==OSP.Constants.COMMENT){
			searchJson = JSON.parse(commentShowTrJson);
		}
		
		$("#inputParameterBody > tr[class=parameter]").each(function() {
			var key = $(this).attr("id");
			if($(this).is(":visible")&&searchJson[key]){
				if(key=='nickName'){
					var xml = parameterObj.localizedNameTextXML(<portlet:namespace/>dataType.structure().availableLanguageIds(),<portlet:namespace/>dataType.structure().defaultLanguageId());
					<portlet:namespace/>localizedInputSet('inputDeckNickName',xml);
				}else if(key=='active'){
					$("#activeValue option[value='"+parameterObj.active()+"']").prop("selected",true);
				}else if(key=='description'){
					var xml = parameterObj.localizedDescriptionXML(<portlet:namespace/>dataType.structure().availableLanguageIds(),<portlet:namespace/>dataType.structure().defaultLanguageId());
					<portlet:namespace/>localizedInputSet('inputDeckDescription',xml);
				}else if(key=='groupChoice'){
					
				}else if(key=='stringValue'){
					$("#inputDeckStringValue").val(parameterObj.value());
				}else if(key=='list'){
					var listMap = parameterObj.listItems();
					$select  = $("#inputDeckListParemter");
					
					var availableLanguagesArray = <portlet:namespace/>dataType.structure().availableLanguageIds();
					for (var val in listMap){
						var listItem = parameterObj.listItem(val);
						$listOption = $("<option/>").val(listItem.value()).html(listItem.localizedText('${defaultLanguageId}')).appendTo($select);
						
						for(var i=0;i<availableLanguagesArray.length; i++){
							$listOption.attr(availableLanguagesArray[i],listItem.localizedText(availableLanguagesArray[i]));
						}
					}
				}else if(key=='dimension'){
					var value = parameterObj.value();
					var dimension = parameterObj.dimension();
					
					<portlet:namespace/>changeDimension(dimension);
					$("#inputDeckDemensionSelect").val(dimension).prop("selected",true);
					
					for(var i=0; i<dimension;i++){
						$("#inputDeckDimensionValue_"+i).val(value[i]);
					}
				}else if(key=='valueDomainMin'){
					var lowerLimit = parameterObj.rangeLowerBoundary();
					if(lowerLimit){
						$("#inputDeckMinValue").val(lowerLimit);
						$("#inputDeckMinSelect option[value='"+parameterObj.rangeOperand().charAt(0)+"']").prop("selected",true);
					}
				}else if(key=='valueDomainMax'){
					var upperLimit = parameterObj.rangeUpperBoundary();
					if(upperLimit){
						$("#inputDeckMaxValue").val(upperLimit);
						$("#inputDeckMaxSelect option[value='"+parameterObj.rangeOperand().charAt(1)+"']").prop("selected",true);
					}
					
				}else if(key=='sweep'){
					if(parameterObj.sweepable()){
						var lowerSweep =parameterObj.sweepRangeLowerBoundary();
						var upperSweep =parameterObj.sweepRangeUpperBoundary();
						var opSweep = parameterObj.sweepRangeOperand();
						
						$("#inputDeckSweepCheck").prop("checked",true);
						
						$("#inputDeckSweepMinValue").val(lowerSweep);
						$("#inputDeckSweepMaxValue").val(upperSweep);
						
						$("#inputDeckSweepMinSelect option[value='"+opSweep.charAt(0)+"']").prop("selected",true);
						$("#inputDeckSweepMaxSelect option[value='"+opSweep.charAt(1)+"']").prop("selected",true);
						
						var inputDeckSweepTypeValue = '';
						
						if(parameterObj.sweepMethod()==OSP.Enumeration.SweepMethod.BY_SLICE){
							$("#inputDeckSweepTypeValue").val(<portlet:namespace/>nullToStr(parameterObj.sweepSliceCount()));
						}else{
							$("#inputDeckSweepTypeValue").val(<portlet:namespace/>nullToStr(parameterObj.sweepSliceValue()));
						}
						
						$("#inputDeckSweepType").val(parameterObj.sweepMethod()).prop("selected",true);
						
						$(".sweepInput").prop('readOnly', false);
					}
				}else if(key=='numericValue'){
					$("#inputDeckNumericValue").val(parameterObj.value());
				}else if(key=='comment'){
					$("#inputDeckComment").val(parameterObj.value());
				}else if(key=='unit'){
					$("#inputDeckUnit").val(<portlet:namespace/>nullToStr(parameterObj.unit()));
				}else if(key=="variableChoice"){
					var groupAttachParameter = parameterObj.parameters();
					for(paramName in groupAttachParameter){
						var targetParameter = groupAttachParameter[paramName];
						$("#variableChoiceTdArea input:checkbox[id='"+targetParameter+"']").prop("checked",true);
					}
				}
				searchJson[key] = false;
			}
		});
	});
	
	function <portlet:namespace/>changeType(type,activeType){
		
		if($('#activateDiv').is(":visible")){
			<portlet:namespace/>closeActivateDiv();
		}
		
		if($('#activateSearchDiv').is(":visible")){
			<portlet:namespace/>closeActivateSearchDiv();
		}
		
		
		var json;
		if(type==OSP.Constants.NUMERIC){
			json = JSON.parse(numericShowTrJson);
		}else if(type==OSP.Constants.STRING){
			json = JSON.parse(stringShowTrJson);
		}else if(type==OSP.Constants.LIST){
			json = JSON.parse(listShowTrJson);
		}else if(type==OSP.Constants.VECTOR){
			json = JSON.parse(vectorShowTrJson);
		}else if(type==OSP.Constants.GROUP){
			json = JSON.parse(groupShowTrJson);
		}else if(type==OSP.Constants.COMMENT){
			json = JSON.parse(commentShowTrJson);
		}
		
		$(".parameter").hide();
		
		if(type!=""){
			$(".actieveBtn").show();
			if(json["variableActive"]){
				
				if(activeType=="add"){
					$("#variableActivationInsertBtn").show();
				}else{
					$("#variableActivationUpdateBtn").show();
				}
				
			}
			
			$("#inputDeckType option:eq(0)").removeAttr("selected");
			
		}else{
			$("#inputDeckType option:eq(0)").prop("selected",true);
			$(".actieveBtn").hide();
		}
		
		$(".parameter").each(function() {
			if(type!=""){
				var key = $(this).attr("id");
				if(json[key]){
					$(this).show();

					//group 조회 화면일 경우
					if(key=="groupChoice"){
						if(activeType=='add'){
							var variableGroupArray = <portlet:namespace/>dataType.structure().parameterNames(OSP.Constants.GROUP);
							$td = $("#"+key).children("td");
							$td.empty();
							
							if(variableGroupArray[0]!=""){
								$select = $("<select/>").attr("id","inputDeckGroupChoiceSelect").addClass("aui-field-select");
								
								$("<option/>").val("").html(Liferay.Language.get('--empty--')).appendTo($select);
								for(var i=0;i<variableGroupArray.length;i++){
									var variableGroup = <portlet:namespace/>dataType.structure().parameter(variableGroupArray[i]);
									
									$("<option/>").val(variableGroup.name()).html(variableGroup.localizedNameText('${defaultLanguageId}')).appendTo($select);
								}
								$select.appendTo($td);
							}
						}else{
							$(this).hide();
						}
						
					}
					
					//변수 선택 조회 화면일 경우
					if(key=="variableChoice"){
						var parameters = <portlet:namespace/>dataType.structure().parameters();
						$td = $("#variableChoiceTdArea");
						$td.empty();
						
						
						for(var paramName in parameters){
							var targetParameter = parameters[paramName];
							if(targetParameter.type() != OSP.Constants.GROUP){
								$p = $("<p>").css("margin","0px").appendTo($td);
								$p.append(
									$("<input>").attr("type","checkbox").val(targetParameter.name())
												.attr("name","varChoiceCheck")
												.attr("id",targetParameter.name())
									).append(
										$("<label>").attr("for",targetParameter.name()).css("display","inline").html(targetParameter.name())
									)
							}
						}
						
					}
					
					//list 일 경우
					if(key=='list'){
						$("#inputDeckListParemter").empty();
					}else if(key=='sweep'){
						$("#inputDeckSweepCheck").prop("checked",false);
						$(".sweepInput").prop('readOnly', true);
					}
					
					
					if(activeType=='add'){
						if($(this).find(".noupdate").length>0){
							$(this).find(".noupdate").prop('disabled', false);
						}
					}else{
						if($(this).find(".noupdate").length>0){
							$(this).find(".noupdate").prop('disabled', true);
						}
					}
				}
			}
			
			//파라미터 초기화
			$("#inputDeckName").val("");
			$(this).children("td").find(":text").val("");
			$(this).children("td").find(":text").removeAttr("placeholder");
			$(this).children("td").find("input[type=hidden]").val("");
			
		});
		
		
		
		
		if(activeType=='add'){
			$("#inputDeckName").prop("disabled",false);
			$(".addBtnGroup").show();
			$(".updateBtnGroup").hide();
		}else{
			$("#inputDeckName").prop("disabled",true);
			$(".addBtnGroup").hide();
			$(".updateBtnGroup").show();
			
			
// 			$("#variableActivationBtn").show();
		}
	}
	
	function <portlet:namespace/>paraSave(saveAfterClose, isupdate){
		
		var type = $("#inputDeckType option:selected").val();
		var paraName = $("#inputDeckName").val().trim();
		
		if(paraName==''){
			alert(Liferay.Language.get('this-field-is-mandatory'));
			$("#inputDeckName").focus();
			return false;
		}
		if(!/^[\_?a-zA-Z]+[0-9]*$/.test(paraName)){
			alert(Liferay.Language.get('expression-is-not-valid-ex-3',['parma_1','param1','param']));
			$("#inputDeckName").focus();
			return false;
		}
		
		var json;
		if(type==OSP.Constants.NUMERIC){
			json = JSON.parse(numericShowTrJson);
		}else if(type==OSP.Constants.STRING){
			json = JSON.parse(stringShowTrJson);
		}else if(type==OSP.Constants.LIST){
			json = JSON.parse(listShowTrJson);
		}else if(type==OSP.Constants.VECTOR){
			json = JSON.parse(vectorShowTrJson);
		}else if(type==OSP.Constants.GROUP){
			json = JSON.parse(groupShowTrJson);
		}else if(type==OSP.Constants.COMMENT){
			json = JSON.parse(commentShowTrJson);
		}
		
		//validation
		$("#inputParameterBody > tr[class=parameter]").each(function() {
			var key = $(this).attr("id");
			if(json[key]){
				if(typeof $(this).attr("validation") != "undefined"){
					if(!<portlet:namespace/>checkRequiredField(key)){
						throw "stop";
					}
				}
				
				//리스트 일 경우
				if(key=='list'){
					if($("#inputDeckListParemter option").length==0){
						alert(Liferay.Language.get('this-field-is-mandatory'));
						$("#inputDeckListParemter").focus();
						throw "stop";
					}
				}
				
				//벡터 일경우 
				if(key=='dimension'){
					$("#dimensionValueTd").find(":text").each(function(){
						if($(this).val()==""){
							alert(Liferay.Language.get('this-field-is-mandatory'));
							$(this).focus();
							throw "stop";
						}
						
					})
				}
				
				
				if(key=='valueDomainMax'){
					var valueDomainMinValue = parseFloat($("#inputDeckMinValue").val());
					var valueDomainMaxValue = parseFloat($("#inputDeckMaxValue").val());
					if(valueDomainMinValue!=""&&valueDomainMaxValue!=""){
						if(valueDomainMinValue>=valueDomainMaxValue){
							alert(Liferay.Language.get('edison-science-appstore-inputdeck-numeric-size-error-message'));
							$(this).focus();
							throw "stop";
						}
					}
				}
				
				if(key=='sweep'){
					if($("#inputDeckSweepCheck").prop( "checked" )){
						var sweepDomainMinValue = parseFloat($("#inputDeckSweepMinValue").val());
						var sweepDomainMaxValue = parseFloat($("#inputDeckSweepMaxValue").val());
						var sweepTypeValue = $("#inputDeckSweepTypeValue").val();
						
						if($("#inputDeckSweepMinValue").val()==""){
							alert(Liferay.Language.get('this-field-is-mandatory'));
							$("#inputDeckSweepMinValue").focus();
							throw "stop";
						}
						
						if($("#inputDeckSweepMaxValue").val()==""){
							alert(Liferay.Language.get('this-field-is-mandatory'));
							$("#inputDeckSweepMaxValue").focus();
							throw "stop";
						}
						
						if(sweepDomainMinValue>=sweepDomainMaxValue){
							alert(Liferay.Language.get('edison-science-appstore-inputdeck-numeric-size-error-message'));
							throw "stop";
						}
						
						var valueDomainMinValue = parseFloat($("#inputDeckMinValue").val());
						var valueDomainMaxValue = parseFloat($("#inputDeckMaxValue").val());
						if(valueDomainMinValue!=""&&valueDomainMinValue>sweepDomainMinValue){
							alert(Liferay.Language.get('edison-science-appstore-inputdeck-numeric-range-error-message'));
							$("#inputDeckSweepMinValue").focus();
							throw "stop";
						}
						
						if(valueDomainMaxValue!=""&&valueDomainMaxValue<sweepDomainMaxValue){
							alert(Liferay.Language.get('edison-science-appstore-inputdeck-numeric-range-error-message'));
							$("#inputDeckSweepMaxValue").focus();
							throw "stop";
						}
						
					}
					
					
				}
				
				
			}
		});
		
		//variable 객체 생성
		var paraType = $("#inputDeckType option:selected").val();
		var variable = null;
		
		if(isupdate){
			variable = <portlet:namespace/>dataType.structure().parameter(paraName);
			//UPDATE 이면서 NUMERIC 객체 일경우 SWEEP 객체 초기화
			if(paraType==OSP.Constants.NUMERIC){
				variable.removeProperty(OSP.Constants.SWEEP);
			}else if(paraType==OSP.Constants.GROUP){
				variable.removeProperty(OSP.Constants.PARAMETERS);
			}
			
			//변수복사
			if($("#inputDeckVarClone option:selected").val()!=""){
				var cnt = $("#inputDeckVarClone option:selected").val();
				if(variable.type()==OSP.Constants.GROUP){
					<portlet:namespace/>dataType.structure().cloneParameterGroup(variable,cnt);
				}else{
					<portlet:namespace/>dataType.structure().cloneParameter(variable,cnt);
				}
			}
		}else{
			variable = <portlet:namespace/>dataType.structure().newParameter(paraName,paraType)
		}
		
		if(variable==false){
			alert(Liferay.Language.get('edison-table-list-header-variable-name')+" "+Liferay.Language.get('duplicate'));
			$("#inputDeckName").focus();
			return false;
		}
		
		//data 저장
		<portlet:namespace/>saveData(variable,json);
		
		//data save 후 초기화
		if(saveAfterClose){
			<portlet:namespace/>changeType('','add');
			<portlet:namespace/>display();
		}
		
		return true;
	}
	
	
	function <portlet:namespace/>saveData(variable,json){
		var saveJson = json;
		$("#inputParameterBody > tr[class=parameter]").each(function() {
			var key = $(this).attr("id");
			
			if(saveJson[key]){
				if(key=='active'){
					var value = $("#activeValue option:selected").val();
					variable.active(true);
					if(value=="true"){
						variable.disabled(false);
					}else{
						variable.disabled(true);
					}
				}else if(key=='nickName'){
					<portlet:namespace/>dynamicInputSave(variable,key);
				}else if(key=='description'){
					<portlet:namespace/>dynamicInputSave(variable,key);
				}else if(key=='groupChoice'){
					var groupValue = <portlet:namespace/>nullToStr($("#inputDeckGroupChoiceSelect option:selected").val());
					if(groupValue!=""){
						var groupVariable = <portlet:namespace/>dataType.structure().parameter(groupValue);
						groupVariable.attachParameter(variable.name());
					}
				}else if(key=='unit'){
					variable.unit($("#inputDeckUnit").val());
				}else if(key=='valueDomainMax'){
					var valueDomainMinChar = $("#inputDeckMinSelect option:selected").val();
					var valueDomainMinValue = $("#inputDeckMinValue").val();
					
					var valueDomainMaxChar = $("#inputDeckMaxSelect option:selected").val();
					var valueDomainMaxValue = $("#inputDeckMaxValue").val();
					
					if(valueDomainMinValue!=""&&valueDomainMaxValue!=""){
						variable.setRange(valueDomainMinValue,valueDomainMaxValue,valueDomainMinChar+valueDomainMaxChar);
					}
				}else if(key=='numericValue'){
					variable.value($("#inputDeckNumericValue").val());
				}else if(key=='stringValue'){
					variable.value($("#inputDeckStringValue").val());
				}else if(key=='sweep'){
					if($("#inputDeckSweepCheck").prop( "checked" )){
						variable.sweepable(true);
						variable.maxSweepSlice(20);
						
						var sweepDomainMinChar = $("#inputDeckSweepMinSelect option:selected").val();
						var sweepDomainMinValue = $("#inputDeckSweepMinValue").val();
						var sweepDomainMaxChar = $("#inputDeckSweepMaxSelect option:selected").val();
						var sweepDomainMaxValue = $("#inputDeckSweepMaxValue").val();
						
						
						var sweepTypeSlice = $("#inputDeckSweepType option:selected").val();
						var sweepTypeValue = $("#inputDeckSweepTypeValue").val();
						variable.sweepMethod(sweepTypeSlice);
						
						if(sweepTypeSlice==OSP.Enumeration.SweepMethod.BY_SLICE){
							variable.sweepSliceCount(sweepTypeValue);
						}else{
							variable.sweepSliceValue(sweepTypeValue);
						}
						
						if(sweepDomainMinValue!=""&&sweepDomainMaxValue!=""){
							var result = variable.setSweepRange(sweepDomainMinValue,sweepDomainMaxValue,sweepDomainMinChar+sweepDomainMaxChar);
						}
					}else{
						variable.sweepable(false);
						variable.sweeped(false);
						
					}
					
					
				}else if(key=='dimension'){
					var value = $("#inputDeckDemensionSelect option:selected").val();
					variable.dimension(value);
					var text = "";
					var vectorValueArray = [];
					for(var i=0;i<value;i++){
						var inputDeckDimensionValue = $("#inputDeckDimensionValue_"+i).val();
						
						vectorValueArray.push(Number(inputDeckDimensionValue));
					}
					variable.value(vectorValueArray);
				}else if(key=='list'){
					var availableLanguagesArray = <portlet:namespace/>dataType.structure().availableLanguageIds();
					var defaultValue = "";
					
					$("#inputDeckListParemter option").each(function(){
						variable.addListItem($(this).val());
						if(defaultValue==""){
							defaultValue = $(this).val();
						}
						
						for(var j=0;j<availableLanguagesArray.length; j++){
							var lanuage = availableLanguagesArray[j];
							var lanuageValue = $(this).attr(lanuage);
							variable.localizedListItemText($(this).val(),lanuage,lanuageValue);
						}
					});
					
					//List Type 일 경우 option의 첫번째 값이 default 값이 된다.
					variable.value(defaultValue);
					
				}else if(key=='comment'){
					variable.value($("#inputDeckComment").val());
				}else if(key=='variableChoice'){
					$("#variableChoiceTdArea  input:checkbox:checked").each(function (index) {
						variable.attachParameter($(this).val());
					});
				}
				
				saveJson[key] = false;
			}
		});
	}
	
	
	function <portlet:namespace/>paraDelete(){
		if(confirm(Liferay.Language.get('edison-science-appstore-inputdeck-delete-message'))){
			var paraName = $("#inputDeckName").val().trim();
			<portlet:namespace/>dataType.structure().removeParameter(paraName);
			
			<portlet:namespace/>changeType('','add');
			
			<portlet:namespace/>display();
		}
		
	}
	
	
	
	//활성화 조건 DIV open
	function <portlet:namespace/>activateSearchOpen(showMsg,isEdit){
		<portlet:namespace/>addActivateOpen(showMsg,isEdit);
		<portlet:namespace/>drowActivateSearchOption();
	}
	
	function <portlet:namespace/>closeActivateDiv(){
		$("#activateDiv").hide('slide', {direction: 'left'}, 500);
		$('#inputDeckParameterLeft').show(400);
		$("#inputDeckActivateBody tr").remove();
	}
	
	
	function <portlet:namespace/>addActivateOpen(showMsg,isEdit){
		var addActiave = false;
		if(!$('#activateDiv').is(":visible")){
			var paraName = $("#inputDeckName").val().trim();
			
			if(showMsg){
				if(confirm(Liferay.Language.get('edison-science-appstore-inputdeck-activation-add-message'))){
					addActiave = <portlet:namespace/>paraSave(true,false);
				}
			}else{
				addActiave = true;
			}
			
			if(addActiave){
	 			<portlet:namespace/>drowActivateOption(paraName);
		 		$("#inputDeckAcivateSave").attr("onclick","<portlet:namespace/>inputDeckActivate('"+paraName+"','"+isEdit+"')");
		 		
				$('#activateDiv').show('slide', {direction: 'left'}, 700);
				$('#inputDeckParameterLeft').hide(600);
			}
		}
	}
	
	
	//활성화 조건 변수 출력
	function <portlet:namespace/>drowActivateOption(paraName){
		var variableActivateArray = <portlet:namespace/>dataType.structure().parameterNames(OSP.Constants.NUMERIC,OSP.Constants.LIST);
		if(variableActivateArray[0]!=""){
			for(var i=0;i<variableActivateArray.length;i++){
				var variable = <portlet:namespace/>dataType.structure().parameter(variableActivateArray[i])
				if(variable.name()!=paraName){
					switch( variable.type() ){
					case OSP.Constants.NUMERIC:
						<portlet:namespace/>drowActivateInputNumberForm(variable);
						break;
					case OSP.Constants.LIST:
						<portlet:namespace/>drowActivateListForm(variable);
						break;
					}
				}
			}
		}
	}
	
	function <portlet:namespace/>drowActivateInputNumberForm(variable){
		$tbody = $("#inputDeckActivateBody");
		
		var lowerLimit = <portlet:namespace/>nullToStr(variable.rangeLowerBoundary());
		var upperLimit = <portlet:namespace/>nullToStr(variable.rangeUpperBoundary());
		var name = variable.name();
		
		$tr = $("<tr/>").attr("activateType",OSP.Constants.NUMERIC).attr("activateName",variable.name()).appendTo($tbody);
		
		$("<td/>").append(
				$("<input/>").attr("type","checkbox").css("margin","0px").attr("id",name+"_active_checkbox")
				).addClass("center").appendTo($tr);
		
		$("<td/>").append(
				$("<label>").attr("for",name+"_active_checkbox").css("display","inline").html(name)
				).appendTo($tr);
		
		$numericTd = $("<td/>").attr("colspan","4").addClass("center").appendTo($tr);
		$numericDiv = $("<div/>").addClass("form-inline").appendTo($numericTd);
		$numericFormGroup = $("<div/>").addClass("input-group").appendTo($numericDiv);
		
		$("<input/>").attr("type","text").addClass("form-control")
					 .attr("onkeydown","return onlyNumber(event)")
					 .attr("onkeyup","removeChar(event)")
					 .attr("id","inputDeckActivateMinValue")
					 .val(lowerLimit)
					 .css("ime-mode","disabled").css("width","70px").appendTo($numericFormGroup);
		
		$("<span/>").addClass("input-group-addon")
					.css("width","0px")
					.css("padding-left","0px")
					.css("padding-right","0px")
					.css("border","none").appendTo($numericFormGroup);
		
		$("<select/>").append(
					$("<option/>").val("=").html("<=")
					 ).append(
					$("<option/>").val("<").html("<")
					 ).attr("id","inputDeckActivateMinSelect").addClass("form-control").appendTo($numericFormGroup);
		
		$("<span/>").addClass("input-group-addon")
					.css("width","0px")
					.css("padding-left","0px")
					.css("padding-right","0px")
					.css("border","none").appendTo($numericFormGroup);
		
		$("<input/>").attr("type","text").addClass("form-control")
					 .attr("onkeydown","return onlyNumber(event)")
					 .attr("onkeyup","removeChar(event)")
					 .attr("id","assignValue")
					 .attr("placeholder","value")
					 .css("ime-mode","disabled").css("width","70px").appendTo($numericFormGroup);
		
		$("<span/>").addClass("input-group-addon")
					.css("width","0px")
					.css("padding-left","0px")
					.css("padding-right","0px")
					.css("border","none").appendTo($numericFormGroup);

		$("<select/>").append(
					$("<option/>").val("=").html(">=")
					 ).append(
					$("<option/>").val(">").html(">")
					 ).attr("id","inputDeckActivateMaxSelect").addClass("form-control").appendTo($numericFormGroup);
		
		$("<span/>").addClass("input-group-addon")
					.css("width","0px")
					.css("padding-left","0px")
					.css("padding-right","0px")
					.css("border","none").appendTo($numericFormGroup);
		
		
		$("<input/>").attr("type","text").addClass("form-control")
					 .attr("onkeydown","return onlyNumber(event)")
					 .attr("onkeyup","removeChar(event)")
					 .attr("id","inputDeckActivateMaxValue")
					 .val(upperLimit)
					 .css("ime-mode","disabled").css("width","70px").appendTo($numericFormGroup);
		
		
// 		$("<td/>").append(
// 				$("<input/>").attr("type","text").addClass("to_short_field")
// 				.attr("id","assignValue")
// 				).addClass("center").appendTo($tr);
		
// 		$tr.appendTo($tbody);
	}
	
	
	function <portlet:namespace/>drowActivateListForm(variable){
		
		$tbody = $("#inputDeckActivateBody");
		var name = variable.name();
		
		$tr = $("<tr/>").attr("activateType",OSP.Constants.LIST).attr("activateName",name);
		$("<td/>").append(
				$("<input/>").attr("type","checkbox").css("margin","0px").attr("id",name+"_active_checkbox")
				).addClass("center").appendTo($tr);
		
		$("<td/>").append(
				$("<label>").attr("for",name+"_active_checkbox").css("display","inline").html(name)
				).appendTo($tr);
		
		$select = $("<select/>").attr("id","inputDeckActivateListFrom_"+name).addClass("form-control");
		
		var listMap = variable.localizedListItems('${defaultLanguageId}');
		
		for (var key in listMap){
			$("<option/>").val(key).html(listMap[key]).appendTo($select);
		}
		
		$("<td/>").append($select).addClass("center").appendTo($tr);
		
		$("<td/>").append(
					$("<button/>").addClass("btn btn-primary btn-xs")
					.html(Liferay.Language.get('add'))
					.attr("onClick","<portlet:namespace/>activateListMove('add','"+name+"')")
				).append(
					$("<br/>")
				).append(
					$("<button/>").addClass("btn btn-primary btn-xs")
					.html(Liferay.Language.get('delete'))
					.attr("onClick","<portlet:namespace/>activateListMove('remove','"+name+"')")
				).addClass("center").appendTo($tr);
		
		$("<td/>").addClass("center").append(
							$("<select/>").attr("id","inputDeckActivateListTarget_"+name).addClass("form-control")
							              .attr("onChange","<portlet:namespace/>addActivateListValue('"+name+"',this.value)")
				  ).appendTo($tr);
		
		
		$("<td/>").append(
				$("<input/>").attr("type","text").addClass("to_short_field form-control")
				.attr("id","assignValue")
				.focusout(function(){
					var selectedVal = $("#inputDeckActivateListTarget_"+name).find("option:selected").val();
					if (typeof selectedVal != 'undefined'){
						$(this).attr("inputDeckActivateListTarget_"+selectedVal+"_aValue",$(this).val());
					}
				})
		).addClass("center").appendTo($tr);
		
		$tr.appendTo($tbody);
	}
	
	
	
	//활성화 조건 변수 조회
	function <portlet:namespace/>drowActivateSearchOption(){
		var name = $("#inputDeckName").val().trim();
		
		//variable 객체 조회
		var activateSearchVariable = <portlet:namespace/>dataType.structure().parameter(name);
		var container = activateSearchVariable.activateConditions();
		
		if(container){
			$tbody = $("#inputDeckActivateBody");
			container.forEach(function(condition, index) {
				$tr = $tbody.find("tr[activatename='"+condition.parameterName()+"']");
				//checkbox
				if(!$tr.find(":checkbox").prop("checked")){
					$tr.find(":checkbox").prop("checked",true);
				}
				
				switch( condition.type() ){
					case OSP.Constants.NUMERIC:
						var lowerLimit = condition.rangeLowerBoundary();
						var upperLimit = condition.rangeUpperBoundary();
						var opperand = condition.rangeOperand();
						
						$tr.find("input[id=inputDeckActivateMinValue]").val(lowerLimit);
						
						$tr.find("input[id=inputDeckActivateMaxValue]").val(upperLimit);
						
						$minSelect = $tr.find("select[id=inputDeckActivateMinSelect]");
						$minSelect.find("option[value='"+opperand.charAt(0)+"']").prop("selected",true);
						
						$maxSelect = $tr.find("select[id=inputDeckActivateMaxSelect]");
						$maxSelect.find("option[value='"+opperand.charAt(1)+"']").prop("selected",true);
						$tr.find("input[id=assignValue]").val(condition.assignValue());
					break;
					case OSP.Constants.LIST:
						$fromSelect = $tr.find("#inputDeckActivateListFrom_"+condition.parameterName());
						$targetSelect = $tr.find("#inputDeckActivateListTarget_"+condition.parameterName());
						$assignValue = $tr.find("input[id=assignValue]");
						
						var activateListValue = condition.value();
						$fromSelectOption = $fromSelect.find("option[value="+activateListValue+"]");
						var fromHtml = $fromSelectOption.html();
						$fromSelectOption.remove();
						$("<option/>").val(activateListValue).html(fromHtml).appendTo($targetSelect);
						$assignValue.attr("inputDeckActivateListTarget_"+activateListValue+"_aValue",condition.assignValue());
						if($assignValue.val()==""){
							$assignValue.val(condition.assignValue());
						}
					break;
				}
			});
		}
	}
	
	//활성화 변수 저장
	function <portlet:namespace/>inputDeckActivate(paraName,isEdit){
		var targetVariable = <portlet:namespace/>dataType.structure().parameter(paraName);
		if(isEdit){
			targetVariable.cleanActivateConditions();
		}
		
		$("#inputDeckActivateBody ").children("tr").each(function(){
			if($(this).find(":checkbox").prop("checked")){
				if($(this).attr("activatetype")==OSP.Constants.NUMERIC){
					var name = $(this).attr("activateName");
					var minValue = $(this).find("input[id=inputDeckActivateMinValue]").val();
					var maxValue = $(this).find("input[id=inputDeckActivateMaxValue]").val();
					
					var minChar = $(this).find("select[id=inputDeckActivateMinSelect] option:selected").val();
					var maxChar = $(this).find("select[id=inputDeckActivateMaxSelect] option:selected").val();
					
					var assignValue = $(this).find("input[id=assignValue]").val();
					var numericActivate = targetVariable.addActivateCondition(name, minValue, maxValue, minChar+maxChar, assignValue);
					//문의
// 					if(numericActivate==OSPInputdeck.Errors.LOWER_LIMIT_INVALID){
// 						$(this).find("input[id=inputDeckActivateMinValue]").focus();
// 						throw stop;
// 					}else if(numericActivate==OSPInputdeck.Errors.UPPER_LIMIT_INVALID){
// 						$(this).find("input[id=inputDeckActivateMaxValue]").focus();
// 						throw stop;
// 					}
				}
				
				if($(this).attr("activatetype")==OSP.Constants.LIST){
					var name = $(this).attr("activateName");
					$assignObject = $(this).find("input[id=assignValue]");
					
					$(this).find($("#inputDeckActivateListTarget_"+name)).find("option").each(function(){
						var assignValue = $assignObject.attr("inputDeckActivateListTarget_"+$(this).val()+"_aValue");
						if(typeof assignValue == "undefined"){
							assignValue = "";
						}
						targetVariable.addActivateCondition(name, $(this).val(), assignValue);
					});
				}
			}
		});
		<portlet:namespace/>closeActivateDiv();
	}
	
	function <portlet:namespace/>addActivateListValue(name,val){
		$assignValue = $("#inputDeckActivateBody").find("tr[activatename='"+name+"']").find("input[id=assignValue]");
		var value = $assignValue.attr("inputDeckActivateListTarget_"+val+"_aValue");
		if(value!=""){
			$assignValue.val(value);
		}else{
			$assignValue.val("");
		}
	}
	
	//활성화 select event
	function <portlet:namespace/>activateListMove(state,name){
		var fromSelect 
		var targetSelect;
		if(state=='add'){
			fromSelect = $("#inputDeckActivateListFrom_"+name);
			targetSelect = $("#inputDeckActivateListTarget_"+name);
		}else{
			fromSelect = $("#inputDeckActivateListTarget_"+name);
			targetSelect = $("#inputDeckActivateListFrom_"+name);
		}
		
		fromSelect.find("option:selected").clone().appendTo(targetSelect);
		fromSelect.find("option:selected").remove();
	}
	
	
	function <portlet:namespace/>changeSweep(){
		if(!$('#activateDiv').is(":visible")){
			if($("#inputDeckSweepCheck").prop( "checked" )){
				//Inputdeck 에서는 하나의 Sweep 변수가 가질수 있다.
// 				if(inputdeckForm.getSweepedVariable() != null){
// 					alert(Liferay.Language.get('edison-science-appstore-inputdeck-duplicate-sweep-error-message'));
// 					$("#inputDeckSweepCheck").prop("checked",false);
// 					return false;
// 				}else{
					$(".sweepInput").prop('readOnly', false);
					$("#variableActivationBtn").hide();
// 				}
			}else{
				$(".sweepInput").prop('readOnly', true);
				$("#variableActivationBtn").show();
			}
		}else{
			alert(Liferay.Language.get('edison-science-appstore-inputdeck-sweep-activation-error-message'));
			return false;
		}
	}
	
	function <portlet:namespace/>changeDimension(val){
		$td = $("#dimensionValueTd");
		$td.empty();
		for(var i=0;i<val;i++){
			$("<input/>").attr("type","text")
						 .addClass("to_short_field numberCheck field")
						 .attr("onkeydown","return checkNumberExp(event,this.value)")
						 .attr("name","inputDeckDimensionValue_"+i)
						 .attr("id","inputDeckDimensionValue_"+i)
						 .appendTo($td);
		}
	}
	
	function <portlet:namespace/>listBtnEvent(type){
		$selectBox = $("#inputDeckListParemter");
		$option = $("<option/>")
		if(type=='add'){
			var value = $("#para_listValue").val();
			
			if(value==''){
				alert(Liferay.Language.get('this-field-is-mandatory'));
				$("#para_listValue").focus();
				return false;
			}else{
				if(!$("#para_listValue").prop("disabled")){
					if($selectBox.find("option[value='"+value+"']").length>0){
						alert("value "+Liferay.Language.get('duplicate'));
						$("#para_listValue").focus();
						return false;
					}
				}else{
					$option = $("#inputDeckListParemter option[value="+value+"]");
				}
			}
			
			var availableLanguagesArray = <portlet:namespace/>dataType.structure().availableLanguageIds();
			var size = availableLanguagesArray.length;
			for(var i=0;i<size; i++){
				var lanuage = availableLanguagesArray[i];
				var lanuageValue = $("#<portlet:namespace/>inputDeckListName_"+lanuage).val();
				if(typeof lanuageValue == "undefined"){
					alert(Liferay.Language.get('edison-error-another-language-alret-message'));
					$("#<portlet:namespace/>inputDeckListName").focus();
					return false;
				}else if(lanuageValue == ''){
					alert(Liferay.Language.get('edison-error-another-language-alret-message'));
					$("#<portlet:namespace/>inputDeckListName").focus();
					return false;
				}else{
 					$option.attr(lanuage,lanuageValue);
 					
 					if(lanuage=='${defaultLanguageId}'){
 						$option.html(lanuageValue);
 					}
				}
				
				$("#<portlet:namespace/>inputDeckListName_"+lanuage).val("");
			}
			
			$option.val(value);
			$("#<portlet:namespace/>inputDeckListName").val("");
			$("#<portlet:namespace/>inputDeckListName").removeAttr("placeholder");
			$("#para_listValue").val("");
			
			$option.appendTo($selectBox);
			
			$("#inputDeckListName").val("");
			$("#para_listValue").val("");
		}else{
			$("#inputDeckListParemter option:selected").remove();
		}
	}
	
	function listItemSearch(value){
		if(value==''){
			value = $("#select_box option:selected").val();
		}
		
		$option = $("#inputDeckListParemter option[value="+value+"]");
		$("#para_listValue").val(value);
		
		var availableLanguagesArray = <portlet:namespace/>dataType.structure().availableLanguageIds();
		
		var data = new Object();
		for(var i=0;i<availableLanguagesArray.length; i++){
			data[availableLanguagesArray[i]] = <portlet:namespace/>nullToStr($option.attr(availableLanguagesArray[i]));
		}
		var jsonData = JSON.stringify(data);
		var xml = OSP.Util.toLocalizedXml(JSON.parse(jsonData),<portlet:namespace/>dataType.structure().availableLanguageIds(),<portlet:namespace/>dataType.structure().defaultLanguageId());
		<portlet:namespace/>localizedInputSet('inputDeckListName',xml);
	}
	
	
	//다국어 필드 및 일반 필드 저장
	function <portlet:namespace/>dynamicInputSave(variable,key){
		$tr = $("tr[class=parameter][id="+key+"]");
		$input = $tr.children("td").find("input");
		
		if($tr.children("td").children("span").hasClass("input-localized-input")){
			var availableLanguagesArray = <portlet:namespace/>dataType.structure().availableLanguageIds();
			var size = availableLanguagesArray.length;
			var id=$input.attr("id");
			
			for(var i=0;i<size; i++){
				var lanuage = availableLanguagesArray[i];
				var lanuageValue = <portlet:namespace/>nullToStr($("#"+id+"_"+lanuage).val());
				
				if(key=="description"){
					variable.localizedDescription(lanuage,lanuageValue);
				}else{
					variable.localizedNameText(lanuage,lanuageValue);
				}
			}
			
		}
	}
	
	
	//다국어 필드 값 셋팅
	function <portlet:namespace/>localizedInputSet(paramName,xmlData){
		var availableLanguagesArray = <portlet:namespace/>dataType.structure().availableLanguageIds();
		
		var xml = $.parseXML( xmlData );
		$("#<portlet:namespace/>"+paramName+"ContentBox div:last").click();
		
		var size = availableLanguagesArray.length;
		for(var i=0;i<size;i++){
			var lanuage = availableLanguagesArray[i];
			var languageValue = <portlet:namespace/>nullToStr($(xml).find("[language-id ='"+lanuage+"']").text());
			$("#<portlet:namespace/>"+paramName+"_"+lanuage).val(languageValue);
		}
		
		$("#<portlet:namespace/>"+paramName).attr("placeholder",$(xml).find("[language-id ='${defaultLanguageId}']").text()).val($(xml).find("[language-id ='${defaultLanguageId}']").text());
		$("#<portlet:namespace/>"+paramName+"ContentBox").find("li[data-value='${defaultLanguageId}']").trigger("click");
	}
	
	//nullToStr
	function <portlet:namespace/>nullToStr(str){
		if(str==null||typeof str =="undefined"||!str){
			return "";
		}else{
			return str;
		}
	}
	
	
	function <portlet:namespace/>checkRequiredField(key){
		$tr = $("tr[class=parameter][id="+key+"]");
		$input = $tr.children("td").find("input");
		var result = true;
		
		if(key=='nickName'){
			var lanuage = '${siteDefaultLanguageId}';
			var id=$input.attr("id");
			var lanuageValue = $("#"+id+"_"+lanuage).val();
			if(typeof lanuageValue == "undefined"){
				alert(Liferay.Language.get('edison-error-another-language-alret-message'));
				$input.focus();
				return false;
			}else if(lanuageValue == ''){
				alert(Liferay.Language.get('edison-error-another-language-alret-message'));
				$input.focus();
				return false;
			}
		}else{
			if($input.val()==""){
				alert(Liferay.Language.get('this-field-is-mandatory'));
				$input.focus();
				return false;
			}
		}
		
		return result;
	}
	
	
	function checkNumberExp(event,value){
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 189 || keyID == 190 || keyID == 69 || keyID == 110){
			if(keyID == 69){
				if(value.indexOf("e")>-1){
					event.target.value = value;
					return false;
				}else{
					return;
				}
			}else{
				return;
			}
			
		}else{
			event.target.value = value;
			return false;
		}

	}
	
	function onlyNumber(event){
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
			return;
		else
			return false;
	}
	
	function removeChar(event) {
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
			return;
		else
			event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
</script>