<%@include file="/html/init.jsp"%>

<%
	List<ChildChallenge> childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId,
			"Running");

	User currentUser = PortalUtil.getUser(request);

	String userUniversityCode = "University";
	String userMajor = "Major";

	userUniversityCode = currentUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
	userMajor = currentUser.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR).toString();

	long companyId = currentUser.getCompanyId();
	ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId, ExpandoTable.class.getName(),
			EdisonExpando.TALBE_NAME);
	ExpandoValue universityValue = ExpandoValueLocalServiceUtil.getValue(companyId, table.getClassNameId(),
			EdisonExpando.TALBE_NAME, EdisonExpando.CDNM, Long.valueOf(userUniversityCode));
	
	request.setCharacterEncoding("UTF-8");
%>
<c:if test="<%=childChallenges == null%>">
	<script>
		history.back();
	</script>
</c:if>


<portlet:actionURL name="addChallengeTeam" var="addChallengeTeamURL">
	<portlet:param name="challengeTeamId" value="0" />
	<portlet:param name="mvcPath"
		value="/html/challengeparticipation/view.jsp" />
</portlet:actionURL>

<div class="form-style-5">
	<form id="<portlet:namespace/>updateTeamForm"
		enctype="multipart/form-data" method="POST"
		action="<%=addChallengeTeamURL%>">
		<fieldset style="margin-top: 20px">
			<legend><span class="number">1</span><%=LanguageUtil.get(pageContext, "challenge-info-agreement-guide")%></legend>
			<label style="font-size:16px;"><%=LanguageUtil.get(pageContext, "challenge-info-agreement-subject")%></label>
			<div id='<portlet:namespace/>agreementArea' style="width:70%!important;margin-left:20px;">
				<label>1. <%=LanguageUtil.get(pageContext, "challenge-info-agreement-purpose1")%></label>
				<label>(1)<%=LanguageUtil.get(pageContext, "challenge-info-agreement-purpose2")%></label>
				<label>(2)<%=LanguageUtil.get(pageContext, "challenge-info-agreement-purpose3")%></label>
				<br>
				
				<label>2. <%=LanguageUtil.get(pageContext, "challenge-info-agreement-period1")%></label>
				<label>(1)<%=LanguageUtil.get(pageContext, "challenge-info-agreement-period2")%></label>
				<label>(2)<%=LanguageUtil.get(pageContext, "challenge-info-agreement-period3")%></label>
				<br>
				
				<label>3. <%=LanguageUtil.get(pageContext, "challenge-info-agreement-list4")%></label>
				<label><%=LanguageUtil.get(pageContext, "challenge-info-agreement-list5")%></label>
				<br>
				
				<label>4. <%=LanguageUtil.get(pageContext, "challenge-info-agreement-list1")%></label>
				<!-- 필수 정보 -->
				<label><%=LanguageUtil.get(pageContext, "challenge-info-agreement-list2")%></label>
				<label><%=LanguageUtil.get(pageContext, "challenge-info-agreement-question")%></label>
				<label><input type="radio" autofocus="autofocus" name="<portlet:namespace/>agreement1" value="agree" checked="checked"/>
					<%=LanguageUtil.get(pageContext, "challenge-info-agreement-agree")%></label>
				<label><input type="radio" name="<portlet:namespace/>agreement1" value="disagree"/>
					<%=LanguageUtil.get(pageContext, "challenge-info-agreement-disagree")%></label>
				<br>
				<!-- 선택 정보 -->
				<label><%=LanguageUtil.get(pageContext, "challenge-info-agreement-list3")%></label>
				<label><%=LanguageUtil.get(pageContext, "challenge-info-agreement-question")%></label>
				<label class="nowrap"><input type="radio" name="<portlet:namespace/>agreement2" value="<%=true %>" checked="checked"/>
					<%=LanguageUtil.get(pageContext, "challenge-info-agreement-agree")%></label>
				<label class="nowrap"><input type="radio" name="<portlet:namespace/>agreement2" value="<%=false %>"/>
					<%=LanguageUtil.get(pageContext, "challenge-info-agreement-disagree")%></label>
			</div>	
		</fieldset>
		<br>
		<br>
		<fieldset>
			<legend>
				<span class="number">2</span><%=LanguageUtil.get(pageContext, "challenge-user-field")%></legend>
			<!-- Apply Field Name Selection -->
			<div style="float: left; width: 20%; padding: 10px;">
				<label><%=LanguageUtil.get(pageContext, "challenge-user-field")%></label>
			</div>
			<div style="float: left; width: 70%; padding: 10px;">
				<select name="<portlet:namespace/>inputSubject"
					id="<portlet:namespace/>inputSubject">
					<%
						for (ChildChallenge child : childChallenges) {
							String option = "<option value=" + child.getChildChallengeId() + ">";
							String field = ChallengeLocalServiceUtil.getChallenge(child.getChallengeId())
									.getField(themeDisplay.getLocale());
							option += field;
							option += "</option>";
							out.println(option);
						}
					%>
				</select>
			</div>
		</fieldset>
		<!-- Basic Apply User Information -->
		<fieldset>
			<legend>
				<span class="number">3</span><%=LanguageUtil.get(pageContext, "challenge-team-leaderinformation")%></legend>
			<!-- Current User Name : Team Leader -->
			<div>
				<div style="float: left; width: 10%; padding: 10px;">
					<label><%=LanguageUtil.get(pageContext, "challenge-team-teamleadername")%></label>
				</div>
				<div
					style="float: left; width: 30%; padding: 10px; padding-right: 85px;">
					<input type="text" id="<portlet:namespace/>inputUserName"
						style="height: 35px;" name="<portlet:namespace/>inputUserName"
						value="<%=currentUser.getFullName()%>" disabled="<%=true%>"></input>
				</div>

				<!-- Current User E-mail : Team Leader -->
				<div style="float: left; width: 10%; padding: 10px;">
					<label><%=LanguageUtil.get(pageContext, "challenge-team-teamleaderemail")%></label>
				</div>
				<div style="float: left; width: 30%; padding: 10px;">
					<input type="text" id="<portlet:namespace/>inputUserEmail"
						style="height: 35px;" name="<portlet:namespace/>inputUserEmail"
						value="<%=currentUser.getDisplayEmailAddress()%>"
						disabled="<%=true%>">
				</div>
			</div>

			<!-- Current User Institute : Team Leader -->
			<div>
				<div style="float: left; width: 10%; padding: 10px;">
					<label><%=LanguageUtil.get(pageContext, "challenge-team-teamleaderuniversity")%></label>
				</div>
				<div
					style="float: left; width: 30%; padding: 10px; padding-right: 85px;">
					<liferay-ui:input-localized id="inputUserUniversity"
						style="height:35px;" name="inputUserUniversity"
						disabled="<%=true%>" xml="<%=universityValue.getData()%>" />
				</div>

				<!-- Current User Major : Team Leader -->
				<div style="float: left; width: 10%; padding: 10px;">
					<label><%=LanguageUtil.get(pageContext, "challenge-team-teamleadermajor")%></label>
				</div>
				<div
					style="float: left; width: 30%; padding: 10px; margin-bottom: 20px;">
					<input type="text" style="height: 35px;" id="inputUserMajor"
						name="inputUserMajor" value="<%=userMajor%>" disabled="<%=true%>"></input>
				</div>
			</div>

			<!-- Current User Phone Number : Team Leader -->
			<div>
				<div style="float: left; width: 10%; padding: 10px;">
					<label><%=LanguageUtil.get(pageContext, "challenge-team-phone")%></label>
				</div>
				<div
					style="float: left; width: 30%; padding: 10px; padding-right: 85px;">
					<span> <select
						style="float: left; width: 20%; padding: 10px;"
						id="<portlet:namespace/>phone1" name="<portlet:namespace/>phone1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="019">019</option>
					</select> <label style="float: left; width: 1%; margin-top: 8px;">-</label>
						<input
						style="float: left; width: 20%; padding: 10px; height: 35px;"
						type="text" id="<portlet:namespace/>phone2"
						name="<portlet:namespace/>phone2" size="4"
						onkeypress="onlyNumber(this);" required /> <label
						style="float: left; width: 1%; margin-top: 8px;">-</label> <input
						style="float: left; width: 20%; padding: 10px; height: 35px;"
						type="text" id="<portlet:namespace/>phone3"
						name="<portlet:namespace/>phone3" size="4"
						onkeypress="onlyNumber(this);" required/>
					</span>
				</div>

				<!-- Current User Grade : Team Leader -->
				<div style="float: left; width: 10%; padding: 10px;">
					<label><%=LanguageUtil.get(pageContext, "challenge-team-teamleadergrade")%></label>
				</div>
				<div style="float: left; width: 30%; padding: 10px;">
					<select name="<portlet:namespace/>inputGrade"
						id="<portlet:namespace/>inputGrade">
						<option value="1"><liferay-ui:message
								key="challenge-grade-1"></liferay-ui:message></option>
						<option value="2"><liferay-ui:message
								key="challenge-grade-2"></liferay-ui:message></option>
						<option value="3"><liferay-ui:message
								key="challenge-grade-3"></liferay-ui:message></option>
						<option value="4"><liferay-ui:message
								key="challenge-grade-4"></liferay-ui:message></option>
						<option value="5"><liferay-ui:message
								key="challenge-grade-5"></liferay-ui:message></option>
						<option value="6"><liferay-ui:message
								key="challenge-grade-6"></liferay-ui:message></option>
						<option value="7"><liferay-ui:message
								key="challenge-grade-7"></liferay-ui:message></option>
					</select>
				</div>
			</div>
		</fieldset>

		<!-- Apply Team Information -->
		<fieldset>
			<legend>
				<span class="number">4</span><%=LanguageUtil.get(pageContext, "challenge-team")%></legend>

			<!-- Team Name : Apply Team Information -->
			<label><liferay-ui:message key="challenge-team-name"></liferay-ui:message></label>
			<input type="text" style="height: 35px;" required="required"
				name="<portlet:namespace/>inputTeamName"
				id="<portlet:namespace/>inputTeamName" value=""></input>

			<!-- Team Paper Name : Apply Team Information -->
			<label style="margin-top: 5px;"><%=LanguageUtil.get(pageContext, "challenge-user-papername")%></label>
			<liferay-ui:input-localized type="textarea" name="inputPaperName"
				id="inputPaperName" xml=""></liferay-ui:input-localized>

			<!-- Team Paper Abstract : Apply Team Information -->
			<label style="margin-top: 5px;"><%=LanguageUtil.get(pageContext, "challenge-user-paperabstract")%></label>
			<liferay-ui:input-localized type="textarea" rows="10"
				name="inputPaperAbstract" id="inputPaperAbstract" xml=""></liferay-ui:input-localized>

			<!-- Team Paper File Upload : Apply Team Information -->
			<label style="margin-top: 20px;"><%=LanguageUtil.get(pageContext, "challenge-user-papersubmission")%></label>
			<!-- DOC File Upload : Apply Team Information -->
			<div class="filebox bs3-primary preview-image">
				<input class="upload-name"
					value="<%=LanguageUtil.get(pageContext, "challenge-team-file-select")%>"
					disabled="disabled" style="width: 200px;"> <label
					for="<portlet:namespace/>papersubmission"
					style="margin-bottom: 0px;">UpLoad</label> <input type="file"
					name="<portlet:namespace/>papersubmission"
					id="<portlet:namespace/>papersubmission" class="upload-hidden"
					accept=".doc, .docx" /> <label
					style="margin-bottom: 0px; background-color: #1abc9c; border-color: #1abc9c;">.doc,
					.docx</label>
			</div>

			<!-- PDF File Upload : Apply Team Information -->
			<div class="filebox bs3-primary preview-image"
				style="margin-top: 5px;">
				<input class="upload-name"
					value="<%=LanguageUtil.get(pageContext, "challenge-team-file-select")%>"
					disabled="disabled" style="width: 200px;"> <label
					for="<portlet:namespace/>papersubmissionPDF"
					style="margin-bottom: 0px;">UpLoad</label> <input type="file"
					name="<portlet:namespace/>papersubmissionPDF"
					id="<portlet:namespace/>papersubmissionPDF" class="upload-hidden"
					accept=".pdf" /> <label
					style="margin-bottom: 0px; background-color: #1abc9c; border-color: #1abc9c;">.pdf</label>
			</div>

			<!-- Team Presentation File Upload : Apply Team Information -->
			<label style="margin-top: 15px;"><%=LanguageUtil.get(pageContext, "challenge-user-presentationsubmission")%></label>
			<div class="filebox bs3-primary preview-image">
				<input class="upload-name"
					value="<%=LanguageUtil.get(pageContext, "challenge-team-file-select")%>"
					disabled="disabled" style="width: 200px;"> <label
					for="<portlet:namespace/>presentationsubmission"
					style="margin-bottom: 0px;">UpLoad</label> <input type="file"
					name="<portlet:namespace/>presentationsubmission"
					id="<portlet:namespace/>presentationsubmission"
					id="<portlet:namespace/>presentationsubmission"
					class="upload-hidden" accept=".ppt, .ppt" /> <label
					style="margin-bottom: 0px; background-color: #1abc9c; border-color: #1abc9c;">.ppt,
					.ppt</label>
			</div>
		</fieldset>

		<fieldset>
			<input id="<portlet:namespace/>teamsubmitButton"
				name="<portlet:namespace/>teamsubmitButton" type="submit"
				value='<%=LanguageUtil.get(pageContext, "challenge-team-create")%>'
				style="float: left;" /> <input type="reset"
				value='<%=LanguageUtil.get(pageContext, "challenge-reset")%>'
				style="float: left;" /> <input type="button"
				onclick="javascript: location.href='<%=themeDisplay.getURLHome()%>';"
				value='<%=LanguageUtil.get(pageContext, "challenge-cancel")%>'
				style="float: left;" />
		</fieldset>
	</form>
</div>

<br>
<br>


<script>
	//agreement of person's information using statement

	$("input[name=<portlet:namespace/>agreement1]").click(function() {
		check_submit();
	});
	function check_submit() {
		if ($("input[name=<portlet:namespace/>agreement1]:checked").val() == 'disagree') {
			$(":submit").attr("disabled", true);
			alert("If you are not agreement that using and collection of personal information, you cannot participate this software challenge.");
			alert("<%=LanguageUtil.get(pageContext, "challenge-info-agreement-list5")%>");
		} else {
			$(":submit").removeAttr("disabled");
		}
	}
	
	
	$("input[name=<portlet:namespace/>agreement2]").click(function() {
		set_phoneValue();
	});
	function set_phoneValue() {
		console.log("test set Phone 0 : "+$("input[name=<portlet:namespace/>agreement2]:checked").val());
		if($("input[name=<portlet:namespace/>agreement2]:checked").val()=="false"){
			console.log("test set phone1 : ");
			$("#<portlet:namespace/>phone1").val("010").attr("disabled", true);
			$("#<portlet:namespace/>phone2").val("0000").attr("disabled", true);
			$("#<portlet:namespace/>phone3").val("0000").attr("disabled", true);

		}else{
			console.log("test set phone2");
			$("#<portlet:namespace/>phone1").attr("disabled", false).val("010");
			$("#<portlet:namespace/>phone2").attr("disabled", false).val("");
			$("#<portlet:namespace/>phone3").attr("disabled", false).val("");
		}
	}
	

	function onlyNumber(obj) {
		$(obj).keyup(function(){
			$(this).val($(this).val().replace(/[^0-9]/g,""));
			if($(this).val().length > 4){
				$(this).val($(this).val().substring(0, 4));
			}
	        
	    }); 
	}

	/*
	AUI().use('aui-form-validator', function(A) {
		new A.FormValidator({
			boundingBox : '#<portlet:namespace/>updateTeamForm',
			rules : {
				<portlet:namespace/>inputTeamName : {
					required : true
				},
				<portlet:namespace/>phone2 : {
					required : true,
					maxLength : 4,
					minLength : 3
				},
				<portlet:namespace/>phone3 : {
					required : true,
					maxLength : 4,
					minLength : 3
				}
			},
			fieldStrings : {
				<portlet:namespace/>inputTeamName : {
					//required : "Please enter the Team Name."
				},
				<portlet:namespace/>phone2 : {
					//required : "Please enter the Phone Number.",
					//maxLength : "Maximum is 4 disits Phone Number",
				},
				<portlet:namespace/>phone3 : {
					//required : "Please enter the Phone Number.",
					//maxLength : "Maximum is 4 disits Phone Number",
					//minLength : "Minimum is 3 disits Phone Number",
				}
			},
			showAllMessages : true
		})
	});
	*/
</script>

