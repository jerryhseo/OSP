<%@page import="com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil"%>
<%@page import="com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.repository.model.Folder" %>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFolderConstants" %>
<%@page import="com.liferay.portlet.documentlibrary.service.DLAppServiceUtil" %>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileEntry" %>
<%@page import="com.liferay.portlet.asset.model.AssetLinkConstants" %>
<%@page import="com.liferay.portlet.asset.model.AssetLink" %>

<%@include file="/html/init.jsp"%>




<div class="blog-container-background">
<%
User currentUser = PortalUtil.getUser(request);
ChallengeTeam challengeTeam = null;
//challengeTeam = ChallengeTeamLocalServiceUtil.getUserCurrentTeam(scopeGroupId, currentUser.getUserId());

challengeTeam = (ChallengeTeam)request.getAttribute(WebKeys.CHALLENGE_TEAM);

boolean currentIsLeader = false;
String url = "";
List<ChallengeTeamMember> memberList=null;
if(challengeTeam != null){
	memberList = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMembers(scopeGroupId, challengeTeam.getChallengeTeamId());

	ChallengeTeamMember leaderMember = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamLeaderMember(challengeTeam.getChallengeTeamId(), scopeGroupId);
	if(leaderMember.getApplyUserId()==currentUser.getUserId()){
		currentIsLeader=true;
	}
	
	
	
	//Related Asset entry Icon view
	
	
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			ChildChallenge.class.getName(), challengeTeam.getChildChallengeId());
	
	List<AssetLink> linkList = AssetLinkLocalServiceUtil.getLinks(assetEntry.getEntryId(), AssetLinkConstants.TYPE_RELATED);
	
	for(AssetLink link : linkList){
		long entryId = link.getEntryId2();
		if(entryId == assetEntry.getEntryId())
			break;
		AssetEntry linkedEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
		if(!linkedEntry.getClassName().equals(DLFileEntry.class.getName())){
			break;
		}
		
		DLFileEntry dlFileEntry02 = DLFileEntryLocalServiceUtil.getDLFileEntry(linkedEntry.getClassPK());
		
		
		url = "";
		url = "/documents/"+ dlFileEntry02.getRepositoryId()+"/"+dlFileEntry02.getUuid();
	}
}
%>

<portlet:renderURL var="editMyTeamURL">
	<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>"/>
	<portlet:param name="mvcPath" value="/html/challengeparticipation/editmyteam.jsp"/>
</portlet:renderURL>

<div class="blog-container" style="background: #fff;border-width:1px;border-color:#e5ded8!important;border-style:solid;">
	<div class="blog-header">
		<div class="blog-author">
			<img src="<%=url%>" align="left" vspace="15px" hspace="15px"/>
			<div style="color:black;font-size:80px;margin-top:20px;margin-left:15px;margin-bottom:15px;position:relative;"><%=challengeTeam.getTeamName() %></div>
			<br clear="left">
		</div>
	</div>
	<div class="blog-body">
		<div class="blog-summary" style="width:100%; margin-top:5px;">
		<p>
			<strong><%=LanguageUtil.get(pageContext,"challenge-user-field") %></strong><br>
			<%=challengeTeam.getSubject(themeDisplay.getLocale()) %><br><br>
			<strong><%=LanguageUtil.get(pageContext,"challenge-user-papername") %></strong><br>
			<%=challengeTeam.getPaperName(themeDisplay.getLocale()) %><br><br>
			<strong><%=LanguageUtil.get(pageContext,"challenge-user-paperabstract") %></strong><br>
			<%=challengeTeam.getPaperAbstract(themeDisplay.getLocale()) %><br><br>
		</p>
		</div>
		<div class="blog-tags">
			<div style="color:#e5ded8;font-size:30px;margin-left:15px;margin-bottom:15px;position:relative;">Download</div>
			<ul>
				<portlet:resourceURL var="getMyTeamPaperFile">
					<portlet:param name="type" value="paperFile"/>
					<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId())  %>" />
				</portlet:resourceURL> 
				<portlet:resourceURL var="getMyTeamPaperFilePDF">
					<portlet:param name="type" value="paperFilePDF"/>
					<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId())  %>" />
				</portlet:resourceURL>
				<portlet:resourceURL var="getMyTeamPresentationFile">
					<portlet:param name="type" value="presentationFile"/>
					<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
				</portlet:resourceURL>
				<c:if test="<%=challengeTeam.isPaperStatusDOC() %>">
					<li style="margin-left:0; margin-top:10px;"><a href="<%=getMyTeamPaperFile%>"><%=challengeTeam.getPaperFileName() %></a></li>
				</c:if>
				<c:if test="<%=challengeTeam.isPaperStatusPDF() %>">
					<li style="margin-left:0; margin-top:10px;"><a href="<%=getMyTeamPaperFilePDF%>"><%=challengeTeam.getPaperPDFFileName() %></a></li>
				</c:if>
				<c:if test="<%=challengeTeam.isPresentationStatus() %>">
					<li style="margin-left:0; margin-top:10px;"><a href="<%=getMyTeamPresentationFile%>"><%=challengeTeam.getPresentationFileName() %></a></li>
				</c:if>
			</ul>
		</div>
		<div class="blog-footer" style="border-top:1px solid #e5ded8;">
			<ul>
				<li class="published-date"><%=challengeTeam.getCreateDate() %></li>
				<li class="comments" onclick="<portlet:namespace/>openSearchDialog()">
					<%=LanguageUtil.get(pageContext, "challenge-member-add") %>
				</li>
				<li class="comments" onclick="javascript: location.href='<%=editMyTeamURL.toString()%>';">
					<%=LanguageUtil.get(pageContext, "challenge-team-update") %>
				</li>
			</ul>
		</div>
	</div>
</div>


<%if(challengeTeam!=null)
	for(ChallengeTeamMember member : memberList){
		if(member.isLeader()){
			//leader member infor
%>
<div class="blog-container">
	<div class="blog-header">
		<div class="blog-author">
			<div style="float:left; width:15%"><div class="blog-author-icon" style='background:url("<%=UserLocalServiceUtil.getUser(member.getApplyUserId()).getPortraitURL(themeDisplay)%>")'></div></div>
			<div style="float:left; width:85%"><h3><%=UserLocalServiceUtil.getUser(member.getApplyUserId()).getScreenName()%></h3></div>
		</div>
	</div>
	<div class="blog-body">
		<div class="blog-title">	
		</div>
		<div class="blog-summary" style="width:100%; margin-top:5px;"><p>
			<br><br>
			<strong><%=LanguageUtil.get(pageContext,"challenge-team-teamleadername") %></strong>
			<%=member.getApplyUserName() %><br>
			<strong><%=LanguageUtil.get(pageContext,"challenge-team-teamleaderuniversity") %></strong>
			<%=member.getInstitute(themeDisplay.getLocale()) %><br>
			<strong><%=LanguageUtil.get(pageContext,"challenge-team-teamleadermajor") %></strong>
			<%=member.getMajor(themeDisplay.getLocale()) %><br>
			<strong><%=LanguageUtil.get(pageContext,"challenge-team-phone") %></strong>
			<%=member.getPhone() %><br>
			<strong><%=LanguageUtil.get(pageContext,"challenge-team-teamleadergrade") %></strong>
			<%=member.getGrade() %></p>
		</div>
		<div class="blog-footer">
			<ul>
				<li class="published-date"><%=member.getCreateDate() %></li>
				<c:if test="<%=currentIsLeader||(currentUser.getUserId() == member.getApplyUserId()) %>">
					<portlet:actionURL name="withdrawChallengeTeam" var="withdrawChallengeTeam" >
						<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>"/>
						<portlet:param name="challengeTeamMemberId" value="<%=String.valueOf(member.getChallengeTeamMemberId()) %>"/>
						<portlet:param name="mvcPath" value="<%=themeDisplay.getURLHome() %>"/>
					</portlet:actionURL>
					<li class="comments" onclick="javascript: location.href='<%=withdrawChallengeTeam%>';">
						<%=LanguageUtil.get(pageContext, "challenge-team-withdraw") %>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>


<%		
		}else{
			//other member infor
%>

<div class="blog-container">
	<div class="blog-header">
		<div class="blog-author">
			<div style="float:left; width:15%"><div class="blog-author-icon" style='background:url("<%=UserLocalServiceUtil.getUser(member.getApplyUserId()).getPortraitURL(themeDisplay)%>")'></div></div>
			<div style="float:left; width:85%"><h3><%=UserLocalServiceUtil.getUser(member.getApplyUserId()).getScreenName()%></h3></div>
		</div>
	</div>
	<div class="blog-body">
		<div class="blog-title">
		</div>
		<div class="blog-summary"><p>
			<br>
			<%=LanguageUtil.get(pageContext,"challenge-member-name") %>
			<%=member.getApplyUserName() %><br>
			<%=LanguageUtil.get(pageContext,"challenge-member-falculty") %>
			<%=member.getInstitute(themeDisplay.getLocale()) %><br>
			<%=LanguageUtil.get(pageContext,"challenge-member-major") %>
			<%=member.getMajor(themeDisplay.getLocale()) %><br>
			<%=LanguageUtil.get(pageContext,"challenge-member-grade") %>
			<%=member.getGrade() %>
			</p>
		</div>
		<div class="blog-footer">
			<ul>
				<li class="published-date"><%=member.getCreateDate() %></li>
				<c:if test="<%=currentIsLeader||(currentUser.getUserId() == member.getApplyUserId()) %>">
					<portlet:actionURL name="withdrawChallengeTeam" var="withdrawChallengeTeam" >
						<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>"/>
						<portlet:param name="challengeTeamMemberId" value="<%=String.valueOf(member.getChallengeTeamMemberId()) %>"/>
						<portlet:param name="mvcPath" value="<%=themeDisplay.getURLHome() %>"/>
					</portlet:actionURL>
					<li class="comments" onclick="javascript: location.href='<%=withdrawChallengeTeam%>';">
						<%=LanguageUtil.get(pageContext, "challenge-team-withdraw") %>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>


<%		
			
		}
	}
%>
</div>


<portlet:resourceURL var="getUserInfo" id="getUserInfo">
	<portlet:param name="type" value="getuserInformation"/>
	<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>"/>
	<portlet:param name="currentUserId" value="<%=String.valueOf(currentUser.getUserId())%>" />
</portlet:resourceURL>

<portlet:actionURL name="addChallengeTeamMember" var="addChallengeTeamMember" copyCurrentRenderParameters="false">
	<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>"/>
	<portlet:param name="currentUserId" value="<%=String.valueOf(currentUser.getUserId())%>" />
	<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
</portlet:actionURL>




<div id="<portlet:namespace/>searchFormDialog" title="<%=LanguageUtil.get(pageContext, "challenge-user-idsearch") %>" >
	<input style="margin-top:100px;margin-left:80px;" type="text" id="<portlet:namespace/>userSearchName" class="input-medium search-query" maxlength="15" placeholder='<%=LanguageUtil.get(pageContext, "challenge-user-idsearch") %>' />
	<input style="margin-top:100px;" id="<portlet:namespace/>seachMember" type="button" class="btn btn-primary btnsearch" value='<%=LanguageUtil.get(pageContext, "challenge-member-add") %>' />
</div>

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
							<td><select name="<portlet:namespace/>inputUserGrade">
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

<script>
$("#<portlet:namespace/>searchFormDialog").dialog({
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
$("#<portlet:namespace/>seachMember").click(function(){
	$("#<portlet:namespace/>searchFormDialog").dialog("close");
	<portlet:namespace/>getUserInfo();
});

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

function <portlet:namespace/>openSearchDialog(){
	$("#<portlet:namespace/>searchFormDialog").dialog("open");
}

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
