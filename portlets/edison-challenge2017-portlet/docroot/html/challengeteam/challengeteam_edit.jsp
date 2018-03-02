<%@include file="/html/init.jsp"%>
<%
	String challengeFieldName = ParamUtil.getString(request, "challengeFieldName");
	boolean curretIsLeader = false;
	long challengeTeamId = ParamUtil.getLong(request, "challengeTeamId");
	long childChallengeId = ParamUtil.getLong(request, "childChallengeId");
	
	List<ChildChallenge> childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId, "Running");
	
	
	User currentUser = PortalUtil.getUser(request);
	
	ChallengeTeam challengeTeam = null;
	ChallengeTeamMember leaderMember = null;
	if(challengeTeamId > 0){
		challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
		leaderMember = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamLeaderMember(challengeTeam.getChallengeTeamId(), scopeGroupId);
		if(leaderMember.getApplyUserId() == currentUser.getUserId())
			curretIsLeader=true;
			
	}else{
		curretIsLeader=true;
	}
	
	Challenge challenge = ChallengeLocalServiceUtil.getChallenge(ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId).getChallengeId());
	
	String userUniversityCode = "University";
	String userMajor = "Major";
	
	
	userUniversityCode = currentUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
	userMajor = currentUser.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR).toString();
	
	
	long companyId = currentUser.getCompanyId();
	ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId,ExpandoTable.class.getName(),EdisonExpando.TALBE_NAME);
	ExpandoValue universityValue = ExpandoValueLocalServiceUtil.getValue(companyId, table.getClassNameId(), EdisonExpando.TALBE_NAME, EdisonExpando.CDNM, Long.valueOf(userUniversityCode));
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/challemgeteam/view.jsp"></portlet:param>
	<portlet:param name="challengeFieldName" value="<%= challengeFieldName %>"/>
</portlet:renderURL>



<portlet:actionURL name="addChallengeTeam" var="addChallengeTeamURL" >
	<portlet:param name="challengeFieldName" value="<%= challengeFieldName %>"/>
	<portlet:param name="challengeTeamId" value="<%= String.valueOf(challengeTeamId) %>"/>
</portlet:actionURL>


<form class="form-horizontal" id="<portlet:namespace/>updateTeamForm" enctype="multipart/form-data" method="POST" action="<%=addChallengeTeamURL%>">
	<table class="table">
		<tr>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-team-teamleadername") %></label></td>
			<td><liferay-ui:input-localized id="inputUserName" name="inputUserName" 
					xml="<%=(leaderMember!=null)?leaderMember.getApplyUserName():currentUser.getFullName() %>" 
					disabled="<%=true %>"></liferay-ui:input-localized></td>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-team-teamleaderemail") %></label></td>
			<td><input type="text" id="<portlet:namespace/>inputUserEmail" 
					name="<portlet:namespace/>inputUserEmail" 
					value="<%=(leaderMember!=null)?leaderMember.getEmail():currentUser.getDisplayEmailAddress() %>" disabled="<%=true %>"></input>
			</td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-team-teamleaderuniversity") %></label></td>
			<td><liferay-ui:input-localized id="inputUserUniversity" name="inputUserUniversity" 
					disabled="<%=true %>" xml="<%=(leaderMember!=null)?leaderMember.getInstitute(themeDisplay.getLocale()):universityValue.getData()%>">
					</liferay-ui:input-localized></td>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-team-teamleadermajor") %></label></td>
			<td><%=(leaderMember!=null)?leaderMember.getMajor(themeDisplay.getLocale()):userMajor %></td>
			
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-user-field") %></label></td>
			<td>
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
			<script>
				$('#<portlet:namespace/>inputSubject').val("<%=challengeTeam.getChildChallengeId()%>");
			</script>
			</td>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-team-teamleadergrade") %></label>
			<td><select name="<portlet:namespace/>inputGrade" id="<portlet:namespace/>inputGrade">
					<option value="1"><liferay-ui:message key="challenge-grade-1"></liferay-ui:message></option>
					<option value="2"><liferay-ui:message key="challenge-grade-2"></liferay-ui:message></option>
					<option value="3"><liferay-ui:message key="challenge-grade-3"></liferay-ui:message></option>
					<option value="4"><liferay-ui:message key="challenge-grade-4"></liferay-ui:message></option>
					<option value="5"><liferay-ui:message key="challenge-grade-5"></liferay-ui:message></option>
					<option value="6"><liferay-ui:message key="challenge-grade-6"></liferay-ui:message></option>
					<option value="7"><liferay-ui:message key="challenge-grade-7"></liferay-ui:message></option>
				</select>
				<script type="text/javascript">
					$('#<portlet:namespace/>inputGrade').val("<%=leaderMember.getGrade()%>");
				</script>
			</td>
		</tr>
		<tr>
			<td><label><liferay-ui:message key="challenge-team-name"></liferay-ui:message></label></td>
			<td colspan="3"><input style="width:80%" required="required" autofocus="autofocus" name="<portlet:namespace/>inputTeamName" id="<portlet:namespace/>inputTeamName" value='<%=(challengeTeam!=null)?challengeTeam.getTeamName():"" %>'></input></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-user-papername") %></label></td>
			<td colspan="3"><liferay-ui:input-localized  type="textarea" style="width:80%" name="inputPaperName" id="inputPaperName" xml='<%=(challengeTeam!=null)?challengeTeam.getPaperName():"" %>'></liferay-ui:input-localized></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-user-paperabstract") %></label></td>
			<td colspan="3"><liferay-ui:input-localized type="textarea" rows="10" style="width:80%" name="inputPaperAbstract" id="inputPaperAbstract" xml='<%=(challengeTeam!=null)?challengeTeam.getPaperAbstract():"" %>'></liferay-ui:input-localized></td>
		</tr>
		
		<tr rowspan="2">
			<td><label><%=LanguageUtil.get(pageContext, "challenge-user-papersubmission") %></label></td>
			<td colspan="2"><input type="file" name="<portlet:namespace/>papersubmission" accept=".doc, .docx"></input>
				<label>.doc, .docx</label>
			</td>
			<td>				
				<c:if test='<%=challengeTeam!=null && challengeTeam.getPaperStatusDOC() %>'>
					<portlet:resourceURL var="getMyTeamPaperFile">
						<portlet:param name="type" value="paperFile"/>
						<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId())  %>" />
					</portlet:resourceURL> 
					<a href="<%=getMyTeamPaperFile %>" >Download : <%=challengeTeam.getPaperFileName() %></a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td colspan="2">
				<input type="file" name="<portlet:namespace/>papersubmissionPDF" accept=".pdf"></input><label>.pdf </label>
			</td>
			<td>
				<c:if test='<%=challengeTeam!=null && challengeTeam.getPaperStatusPDF() %>'>		
					<portlet:resourceURL var="getMyTeamPaperFilePDF">
						<portlet:param name="type" value="paperFilePDF"/>
						<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId())  %>" />
					</portlet:resourceURL>
					<a href="<%=getMyTeamPaperFilePDF %>" >Download : <%=challengeTeam.getPaperPDFFileName()%></a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(pageContext, "challenge-user-presentationsubmission") %></label></td>
			<td colspan="2"><input type="file" id="<portlet:namespace/>presentationsubmission" name="<portlet:namespace/>presentationsubmission" accept=".ppt, .pptx"></input>
				<label>.ppt, .pptx</label>
			</td>
			<td>
				<c:if test='<%=challengeTeam!=null && challengeTeam.getPresentationStatus() %>'>
					<portlet:resourceURL var="getMyTeamPresentationFile">
						<portlet:param name="type" value="presentationFile"/>
						<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
					</portlet:resourceURL>
					<a href="<%=getMyTeamPresentationFile %>">Download : <%=challengeTeam.getPresentationFileName()%></a>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td colspan="3">
			<!-- submission button -->
			<portlet:renderURL var="homeURL">
				<portlet:param name="mvcPath" value="/html/challengeteam/view.jsp" />
				<portlet:param name="challengeFieldName" value="<%= challengeFieldName %>"/>
			</portlet:renderURL>
			<div class="control-group">
				<div class="controls">
					<button id="<portlet:namespace/>teamupdateButton" class="btn btn-primary" type="submit" >
						<%=(challengeTeamId>0)?LanguageUtil.get(pageContext, "challenge-team-update"):LanguageUtil.get(pageContext, "challenge-team-create") %></button>
					<button class="btn" type="reset"><%=LanguageUtil.get(pageContext, "challenge-reset") %></button>
					<button class="btn" onclick="javascript: location.href='<%=homeURL.toString() %>';"><%=LanguageUtil.get(pageContext, "challenge-cancel") %></button>	
				</div>
			</div>
			</td>
		</tr>
		
	</table>
</form>


<br><br>


<c:if test='<%= challengeTeam != null %>'>
<liferay-ui:search-container>
<liferay-ui:search-container-results
	results="<%=ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMembers(scopeGroupId, challengeTeam.getChallengeTeamId(), searchContainer.getStart(), searchContainer.getEnd())%>"
    total="<%=ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMemberCount(scopeGroupId, challengeTeam.getChallengeTeamId())%>" />

	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChallengeTeamMember" modelVar="challengeTeamMember">
		
		<portlet:renderURL var="viewChallengeTeam">
			<portlet:param name="mvcPath" value="/html/challengeteam/view_challengeteam.jsp" />
			<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
			<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
		</portlet:renderURL>
		
		
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-member-name") %>' value='<%=challengeTeamMember.getApplyUserName() %>'/>
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-member-major") %>' value='<%=challengeTeamMember.getMajor() %>'/>
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-member-falculty") %>' value='<%=challengeTeamMember.getInstitute(themeDisplay.getLocale()) %>'/>
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-member-grade") %>' value='<%=LanguageUtil.get(pageContext, "challenge-grade-"+challengeTeamMember.getGrade()) %>'/>
		
		
		
			<portlet:actionURL name="deleteChallengeTeamMember" var="deleteChallengeTeamMember">
				<portlet:param name="childChallengeId" value="<%=String.valueOf(childChallengeId) %>" />
				<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
				<portlet:param name="challengeTeamMemberId" value="<%=String.valueOf(challengeTeamMember.getChallengeTeamMemberId()) %>" />
			</portlet:actionURL>
			<liferay-ui:search-container-column-text>
			<liferay-ui:icon-menu>
			<liferay-ui:icon-delete  image="delete" message='<%=LanguageUtil.get(pageContext, "challenge-delete") %>' url="<%=deleteChallengeTeamMember.toString() %>" />
			</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>
</c:if>


<br><br>



<c:if test="<%=challengeTeam!=null %>">
<input type="text" id="<portlet:namespace/>userSearchName" class="input-medium search-query" maxlength="15" placeholder='<%=LanguageUtil.get(pageContext, "challenge-user-idsearch") %>' />
<input id="<portlet:namespace/>seachMember" type="button" class="btn btn-primary btnsearch" onClick="<portlet:namespace/>getUserInfo()" value='<%=LanguageUtil.get(pageContext, "challenge-member-add") %>' />


<portlet:resourceURL var="getUserInfo" id="getUserInfo">
	<portlet:param name="type" value="getuserInformation"/>
	<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>"/>
	<portlet:param name="currentUserId" value="<%=String.valueOf(currentUser.getUserId())%>" />
</portlet:resourceURL>

<portlet:actionURL name="addChallengeTeamMember" var="addChallengeTeamMember" copyCurrentRenderParameters="false">
	<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>"/>
	<portlet:param name="currentUserId" value="<%=String.valueOf(currentUser.getUserId())%>" />
	<portlet:param name="childChallengeId" value="<%=String.valueOf(childChallengeId) %>" />
</portlet:actionURL>

<div id="<portlet:namespace/>searchInformationResultDialog" title="<%=LanguageUtil.get(pageContext, "challenge-user-search-result") %>" >
		<form id="teamUpdateForm" name="teamMemberUpdateForm" method="post" action="<%=addChallengeTeamMember%>" > 
				<table class="table" style="width:80%">
					<colgroup>
						<col width="30%" />
						<col width="70%" />
					</colgroup>
					<tbody>
						<tr>
							<td><liferay-ui:message key='challenge-user-id' /></td>
							<td id="<portlet:namespace/>userScreenNameView"></td>
						</tr>
						<tr>
							<td><liferay-ui:message key='challenge-user-university' /></td>
							<td id="<portlet:namespace/>userUniversity"></td>
						</tr>
						<tr>
							<td><liferay-ui:message key='challenge-user-name' /></td>
							<td id="<portlet:namespace/>userFullName"></td>
						</tr>
						<tr>
							<td><label><%=LanguageUtil.get(pageContext, "challenge-user-grade") %></label>
							<td><select name="<portlet:namespace/>inputUserGrade" id="<portlet:namespace/>inputUserGrade">
									<option value="1"><%=LanguageUtil.get(pageContext, "challenge-grade-1") %></option>
									<option value="2"><%=LanguageUtil.get(pageContext, "challenge-grade-2") %></option>
									<option value="3"><%=LanguageUtil.get(pageContext, "challenge-grade-3") %></option>
									<option value="4"><%=LanguageUtil.get(pageContext, "challenge-grade-4") %></option>
									<option value="5"><%=LanguageUtil.get(pageContext, "challenge-grade-5") %></option>
									<option value="6"><%=LanguageUtil.get(pageContext, "challenge-grade-6") %></option>
									<option value="7"><%=LanguageUtil.get(pageContext, "challenge-grade-7") %></option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			<div style="text-align:center;">
				<input type="hidden" id="<portlet:namespace/>searchUserScreenName" name="<portlet:namespace/>searchUserScreenName"/>
				<input type="hidden" id="<portlet:namespace/>searchUserId" name="<portlet:namespace/>searchUserId"/>
				<button id="<portlet:namespace/>register_request_button" name="<portlet:namespace/>register_request_button" class="btn btn-primary" type="submit"><liferay-ui:message key='challenge-user-member-add' /></button>
			</div>
		</form>
</div>
</c:if>

<script>
$("#<portlet:namespace/>searchInformationResultDialog").dialog({
	autoOpen: false,
    width: 450,
    height: 300,
    modal: true,
    resizable: false,
    show: {effect:'fade', speed: 800}, 
    hide: {effect:'fade', speed: 800},
    open: function() {
    },
    close: function() {
    }
});

$("#<portlet:namespace/>register_request_button").click(function() {
	$("#<portlet:namespace/>searchInformationResultDialog").dialog("close");
});


//find user by screen name
function <portlet:namespace/>getUserInfo() {
	var searchName = $("#<portlet:namespace/>userSearchName").val();
	var currentName = $("#<portlet:namespace/>currentUserName").val();
	if(searchName == currentName){
		alert("Your Search data is same with you");
		return false;
	}
	var data = {
			<portlet:namespace/>searchName : searchName
	};
	jQuery.ajax({
		type: "POST",
		url: '${getUserInfo}',
		async : false,
		data :  data,
		dataType: "json",
		success: function(msg) {
			console.log("msg : ", msg);
			var memberUserInfo = msg;
			if(memberUserInfo.userType === undefined) {
				alert("User not found : undefined   ; " + result );
			} else if(memberUserInfo.userType == "none") {
				alert("User not found : none");
			}else if(memberUserInfo.userType == "user"){
				$("#<portlet:namespace/>userUniversity").text(memberUserInfo.userUniversity);
				$("#<portlet:namespace/>userFullName").text(memberUserInfo.userFullName);
				$("#<portlet:namespace/>userScreenNameView").text(memberUserInfo.userScreenNameView);
				$("#<portlet:namespace/>searchUserScreenName").val(memberUserInfo.userScreenName);
				$("#<portlet:namespace/>searchUserId").val(memberUserInfo.userId);
				$("#<portlet:namespace/>searchInformationResultDialog").dialog("open");
			}else{
				alert("You cannot add a user that you search..");
			}
		},error:function(msg,e){ 
			console.log("msg2 : ", msg);
			alert("function getUserInfo Error : "+e);
			return false;
		}
	});
	
}
</script>



