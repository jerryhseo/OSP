<%@ include file="/html/init.jsp" %>

<%
User currentUser = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser()));
	String currentUserSN = currentUser.getScreenName();

	long chTeamID = ParamUtil.getLong(request, "chTeamID");
	long childChallengeid = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID).getChildid();

	//Leder User member object, and challenge Team object
	ChallengeTeam manageTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);
	
	System.out.println("team id  :  ["+chTeamID+"]");
	ChallengeMember leaderMember = ChallengeMemberLocalServiceUtil.getLeaderMemeber(chTeamID);
	System.out.println("leader id  :  ["+leaderMember.getScreenName()+"]");

	
	//Team Member List information
	List<ChallengeMember> memberList = ChallengeMemberLocalServiceUtil.getChallengeTeamMemberByTeamID(chTeamID);
	
	long challengeid = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeid).getChallengeid();
	String fieldName = ChallengeLocalServiceUtil.getChallenge(challengeid).getName(themeDisplay.getLocale());
	
%>
<blockquote>
<h3><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teaminformation") %></h3>
</blockquote>

<portlet:actionURL name="updateTeam" var="updateTeam" windowState="normal">
	<portlet:param name="chTeamID" value="<%=String.valueOf(chTeamID) %>" />
	<portlet:param name="mvcPath" value="/html/teammanagement/view.jsp" />
</portlet:actionURL>

<form class="form-horizontal" id="<portlet:namespace/>updateTeamForm" enctype="multipart/form-data" method="POST" action="<%=updateTeam%>">
	<table class="table">
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teamleadername") %></label></td>
			<td><%=leaderMember.getFullName(themeDisplay.getLocale()) %></td>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teamleaderemail") %></label></td>
			<td><%=leaderMember.getEmail() %></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teamleaderuniversity") %></label></td>
			<td><%=leaderMember.getFalculty(themeDisplay.getLocale())%></td>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teamleadermajor") %></label></td>
			<td><%=leaderMember.getMajor(themeDisplay.getLocale()) %></td>	
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-field") %></label></td>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), fieldName) %></label></td>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teamleadergrade") %></label>
			<td><select name="<portlet:namespace/>inputUserGrade" id="<portlet:namespace/>inputUserGrade">
					<option value="1"><liferay-ui:message key="challenge-grade-1"></liferay-ui:message></option>
					<option value="2"><liferay-ui:message key="challenge-grade-2"></liferay-ui:message></option>
					<option value="3"><liferay-ui:message key="challenge-grade-3"></liferay-ui:message></option>
					<option value="4"><liferay-ui:message key="challenge-grade-4"></liferay-ui:message></option>
					<option value="5"><liferay-ui:message key="challenge-grade-5"></liferay-ui:message></option>
					<option value="6"><liferay-ui:message key="challenge-grade-6"></liferay-ui:message></option>
					<option value="7"><liferay-ui:message key="challenge-grade-7"></liferay-ui:message></option>
				</select>
		</td>
		</tr>
		<tr>
			<td><label><liferay-ui:message key="challenge-team-name"></liferay-ui:message></label></td>
			<td colspan="3"><liferay-ui:input-localized style="width:80%" required="required" autofocus="autofocus" name="inputTeamName" xml="<%=manageTeam.getTeamName() %>"></liferay-ui:input-localized></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-papername") %></label></td>
			<td colspan="3"><liferay-ui:input-localized  type="textarea" style="width:80%" name="inputPaperName" xml="<%=manageTeam.getPaperName() %>"></liferay-ui:input-localized></td>
		</tr>
		<tr>
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-paperabstract") %></label></td>
			<td colspan="3"><liferay-ui:input-localized type="textarea" rows="10" style="width:80%" name="inputpaperAbstract" xml="<%=manageTeam.getPaperAbstract() %>"></liferay-ui:input-localized></td>
		</tr>
		
		<tr rowspan="2">
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-papersubmission") %></label></td>
			<td colspan="2"><input type="file" name="<portlet:namespace/>papersubmission" accept=".doc, .docx"></input>
				<label>.doc, .docx</label>
			</td>
			<td>				
				<portlet:resourceURL var="getMyTeamPaperFile">
					<portlet:param name="type" value="paperFile"/>
					<portlet:param name="chTeamID" value="<%=String.valueOf(manageTeam.getPrimaryKey())  %>" />
				</portlet:resourceURL> 
				<a href="<%=getMyTeamPaperFile %>" >Download : <%=manageTeam.getPaperstatus()?manageTeam.getPaperFileName():"" %></a>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td colspan="2">
				<input type="file" name="<portlet:namespace/>papersubmissionPDF" accept=".pdf"></input><label>.pdf </label>
			</td>
			<td>				
				<portlet:resourceURL var="getMyTeamPaperFilePDF">
					<portlet:param name="type" value="paperFilePDF"/>
					<portlet:param name="chTeamID" value="<%=String.valueOf(manageTeam.getPrimaryKey())  %>" />
				</portlet:resourceURL>
				<a href="<%=getMyTeamPaperFilePDF %>" >Download : <%=manageTeam.getPaperPDFstatus()?manageTeam.getPaperPDFFileName():"" %></a>
			</td>
		</tr>
		<tr>
			
			<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-presentationsubmission") %></label></td>
			<td colspan="2"><input type="file" id="<portlet:namespace/>presentationsubmission" name="<portlet:namespace/>presentationsubmission" accept=".ppt, .pptx"></input>
				<label>.ppt, .pptx</label>
			</td>
			<td>
				<portlet:resourceURL var="getMyTeamPresentationFile">
					<portlet:param name="type" value="presentationFile"/>
					<portlet:param name="chTeamID" value="<%=String.valueOf(manageTeam.getPrimaryKey()) %>" />
				</portlet:resourceURL>
				<a href="<%=getMyTeamPresentationFile %>">Download : <%=manageTeam.getPresentationstatus()?manageTeam.getPresentationFileName():"" %></a>
			</td>
		</tr>
		
		
		
		
				
		<tr>
			<td>&nbsp;</td>
			<td colspan="3">
			<!-- submission button -->
			<portlet:renderURL var="homeURL">
				<portlet:param name="mvcPath" value="/html/teammanagement/view.jsp" />
			</portlet:renderURL>
			<div class="control-group">
				<div class="controls">
					<button id="<portlet:namespace/>teamupdateButton" class="btn btn-primary" type="submit" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-update") %></button>
						<button class="btn" onclick="javascript: location.href='<%=homeURL.toString() %>';"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-cancel") %></button>	
				</div>
			</div>
			</td>
		</tr>
		
	</table>	
</form>

<!-- This is member list section -->
<liferay-portlet:renderURL varImpl="interatorTeamListURL">
	<portlet:param name="mvcPath" value="/html/teammanagement/teammanagement.jsp" />
	<portlet:param name="chTeamID" value="<%=String.valueOf(chTeamID) %>" />
	<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container delta="5" iteratorURL="<%=interatorTeamListURL %>" deltaConfigurable="true" emptyResultsMessage="There-are-no-TeamMember">
	<liferay-ui:search-container-results 		
		total="<%= memberList.size() %>"
		results="<%= ListUtil.subList(memberList, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.ChallengeMember" 
			modelVar="currentMember" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-member-name") %>' value='<%=currentMember.getFullName() %>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-member-major") %>' value='<%=currentMember.getMajor() %>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-member-falculty") %>' value='<%=currentMember.getFalculty(themeDisplay.getLocale()) %>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-member-grade") %>' value='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-"+currentMember.getGrade()) %>'/>
			<portlet:actionURL name="deleteMember" var="deleteMember">
				<portlet:param name="memberID" value="<%=String.valueOf(currentMember.getPrimaryKey())%>" />
				<portlet:param name="chTeamID" value="<%=String.valueOf(currentMember.getChTeamid())%>"/>
				<portlet:param name="mvcPath" value="/html/teammanagement/view.jsp" />
			</portlet:actionURL>
			<liferay-ui:search-container-column-text>
				<liferay-ui:icon-menu>
				<liferay-ui:icon-delete  image="delete" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-delete") %>' url="<%=deleteMember.toString() %>" />
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
			
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>

<br><br>






<!-- user search information -->
<input type="hidden" id="<portlet:namespace/>currentuserName" value='<%=currentUserSN %>' />
<input type="text" id="<portlet:namespace/>userSearchName" class="input-medium search-query" maxlength="15" placeholder='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-idsearch") %>' />
<input id="<portlet:namespace/>seachMember" type="button" class="btn btn-primary btnsearch" onClick="<portlet:namespace/>getUserInfo()" value='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-member-add") %>' />


<portlet:actionURL name="addChallengeMember" var="addChallengeMember" copyCurrentRenderParameters="false">
	<portlet:param name="teamID" value="<%=String.valueOf(chTeamID) %>"/>
	<portlet:param name="chTeamID" value="<%=String.valueOf(chTeamID)%>"/>
	<portlet:param name="currentUserSN" value="<%=currentUserSN%>" />
	<portlet:param name="mvcPath" value="/html/teammanagement/teammanagement.jsp" />
	<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
</portlet:actionURL>
<div id="<portlet:namespace/>searchInformationResultDialog" title="<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-search-result") %>" >
		<form id="teamUpdateForm" name="teamMemberUpdateForm" method="post" action="<%=addChallengeMember%>" > 
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
							<td><label><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-grade") %></label>
							<td><select name="<portlet:namespace/>inputUserGrade">
									<option value="1"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-1") %></option>
									<option value="2"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-2") %></option>
									<option value="3"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-3") %></option>
									<option value="4"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-4") %></option>
									<option value="5"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-5") %></option>
									<option value="6"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-6") %></option>
									<option value="7"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-grade-7") %></option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			<div style="text-align:center;">
				<input type="hidden" id="<portlet:namespace/>userScreenName" name="<portlet:namespace/>userScreenName"/>
				<button id="<portlet:namespace/>register_request_button" name="<portlet:namespace/>register_request_button" class="btn btn-primary" type="submit"><liferay-ui:message key='challenge-user-member-add' /></button>
			</div>
		</form>
</div>


<portlet:resourceURL var="getUserInfo" id="getUserInfo">
	<portlet:param name="type" value="getuser"/>
</portlet:resourceURL>
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
				var result = msg.result;
				var memberUserInfo = msg.memberUserInfo;
				if(result === undefined) {
					alert("User not found : undefined   ; " + result );
					alert("test111 ; "+memberUserInfo);
				} else if(result == "none") {
					alert("User not found : none");
				}else if(result == "user"){
					$("#<portlet:namespace/>userUniversity").text(memberUserInfo.userUniversity);
					$("#<portlet:namespace/>userFullName").text(memberUserInfo.userFullName);
					$("#<portlet:namespace/>userScreenNameView").text(memberUserInfo.userScreenNameView);
					$("#<portlet:namespace/>userScreenName").val(memberUserInfo.userScreenName);
					$("#<portlet:namespace/>searchInformationResultDialog").dialog("open");
				}else{
					alert("You cannot add a user that you search..");
				}
			},error:function(msg,e){ 
				alert("function getUserInfo Error : "+e);
				return false;
			}
		});
		
	}	
</script>



